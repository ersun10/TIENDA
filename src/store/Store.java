/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

import Modelo.Conexion;
import Controlador.Operaciones;
import Modelo.colorjpanel;
import java.awt.BorderLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;

/**
 *
 * @author luisj
 */
public class Store extends javax.swing.JFrame {

    private javax.swing.JTextField jtxtIngreso;
    private javax.swing.JTextField jtxtGastos;
    private javax.swing.JTextField jtxtBeneficios;
    private javax.swing.JLabel jlblFecha;
    private javax.swing.JLabel jlblIngreso;
    private javax.swing.JLabel jlblGastos;
    private javax.swing.JLabel jlblBeneficios;
    private javax.swing.JLabel jlblPrincipal;
    private javax.swing.JComboBox jcomboDia;
    private javax.swing.JComboBox jcomboMes;
    private javax.swing.JComboBox jcomboAno;
    private javax.swing.JButton jbtIngresar;
    private javax.swing.JButton jbtBorrarUlt;
    private javax.swing.JButton jbtBorrar;
    private javax.swing.JButton jbtResumen;
    private javax.swing.JScrollPane jscrollTabla;
    public javax.swing.JTable jDatos;
    private javax.swing.JPanel jpanelPrincipal;
    private javax.swing.JPanel jpaneDatos;
    private javax.swing.JPanel jpaneBotones;
    private javax.swing.JPanel jpaneResultado;
    private javax.swing.JPanel jpane2;

    // Otras variables necesarias
    private String month;
    private int day;
    private int year;
    private Double ingreso;
    private Double gastos;
    private Double beneficio;

    javax.swing.table.DefaultTableModel modelo;

    // Instancias a las clases necesarias
    final Conexion conec;
    final Operaciones oper;
    final resumenjdialog resu;

    // Creación del constructor
    public Store() {
        conec = new Conexion();
        oper = new Operaciones();
        resu = new resumenjdialog(this, true);
        jDatos = new javax.swing.JTable();
        setTitle("Frutería Alba");
        setSize(500, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        initTable();
        initComponents();
    }

    private void initComponents() {
        // Administrador de diseño
        getContentPane().setLayout(new java.awt.GridLayout(4, 1));
        // Manejador de cierre de ventana en X
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        // Manejador de evento de las cajas de texto
        java.awt.event.FocusListener jtxt = new java.awt.event.FocusListener() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtfocuslost(evt);
            }

            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {

            }

        };

        // Nueva fuente
        java.awt.Font fuente = new java.awt.Font("MV Boli", 1, 24);

        // Inicializando objetos
        jtxtIngreso = new Modelo.jtextfields();
        jtxtGastos = new Modelo.jtextfields();
        jtxtBeneficios = new javax.swing.JTextField();
        jlblFecha = new javax.swing.JLabel();
        jlblIngreso = new javax.swing.JLabel();
        jlblGastos = new javax.swing.JLabel();
        jlblBeneficios = new javax.swing.JLabel();
        jpanelPrincipal = new colorjpanel();
        jlblPrincipal = new javax.swing.JLabel();
        ajustarJCombos();
        jbtIngresar = new javax.swing.JButton();
        jbtBorrarUlt = new javax.swing.JButton();
        jbtResumen = new javax.swing.JButton();
        jscrollTabla = new javax.swing.JScrollPane(jDatos);
        jpaneDatos = new javax.swing.JPanel();
        jpaneBotones = new javax.swing.JPanel();
        jpaneResultado = new javax.swing.JPanel();
        jpane2 = new javax.swing.JPanel();
        jbtBorrar = new javax.swing.JButton();

        ingreso = 0.0;
        gastos = 0.0;
        beneficio = 0.0;

        // Propiedades de objetos
        jpanelPrincipal.setLayout(null);
        jlblPrincipal.setText("SISTEMA DE GESTIÓN DE TIENDA");
        jlblPrincipal.setFont(fuente);
        jlblPrincipal.setBounds(20, 50, 450, 30);
        jlblFecha.setText("Fecha del nuevo registro: ");
        jlblFecha.setBounds(20, 30, 300, 30);
        jcomboDia.setBounds(170, 30, 80, 30);
        jcomboMes.setBounds(260, 30, 100, 30);
        jcomboMes.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcomboMesItemStateChanged(evt);
            }
        });
        jcomboAno.setBounds(370, 30, 100, 30);
        jlblIngreso.setText("Ingresos");
        jlblIngreso.setBounds(20, 70, 100, 30);
        jtxtIngreso.setBounds(20, 100, 100, 30);
        jtxtIngreso.addFocusListener(jtxt);
        jlblGastos.setText("Gastos");
        jlblGastos.setBounds(180, 70, 100, 30);
        jtxtGastos.setBounds(180, 100, 100, 30);
        jtxtGastos.addFocusListener(jtxt);
        jlblBeneficios.setText("Beneficios");
        jlblBeneficios.setBounds(340, 70, 100, 30);
        jtxtBeneficios.setEditable(false);
        jtxtBeneficios.setBounds(340, 100, 100, 30);
        jpaneDatos.setBorder(new javax.swing.border.TitledBorder("Datos a ingresar"));
        jbtIngresar.setText("Ingresar");
        jbtIngresar.setBounds(180, 10, 100, 30);
        jbtIngresar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtIngresarActionPerformed(evt);
            }
        });

        jbtBorrarUlt.setText("Borrar última entrada");
        jbtBorrarUlt.setBounds(10, 120, 145, 30);
        jbtBorrarUlt.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBorrarUltActionPerformed(evt);
            }
        });
        jbtBorrar.setText("Borrar dato");
        jbtBorrar.setBounds(320, 120, 150, 30);
        jbtBorrar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtBorrarActionPerformed(evt);
            }
        });
        jbtResumen.setText("Mostrar Resumen");
        jbtResumen.setBounds(165, 60, 130, 30);
        jbtResumen.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtMostrarResumenActionPerformed(evt);
            }
        });

        jlblPrincipal.setForeground(java.awt.Color.WHITE);

        // Agregando controles a los paneles
        jpanelPrincipal.add(jlblPrincipal);
        jpanelPrincipal.add(jpane2);

        // Agregando controles al panel de datos
        jpaneDatos.setLayout(null);
        jpaneDatos.add(jlblFecha);
        jpaneDatos.add(jcomboDia);
        jpaneDatos.add(jcomboMes);
        jpaneDatos.add(jcomboAno);
        jpaneDatos.add(jlblIngreso);
        jpaneDatos.add(jtxtIngreso);
        jpaneDatos.add(jlblGastos);
        jpaneDatos.add(jtxtGastos);
        jpaneDatos.add(jlblBeneficios);
        jpaneDatos.add(jtxtBeneficios);

        // Agregando controles al panel de botones
        jpaneBotones.setLayout(null);
        jpaneBotones.add(jbtIngresar);
        jpaneBotones.add(jbtBorrarUlt);
        jpaneBotones.add(jbtResumen);
        jpaneBotones.add(jbtBorrar);

        // Agregando controles al panel de resultados
        jscrollTabla.setViewportView(jDatos);

        // Agregando objetos al formulario
        getContentPane().add(jpanelPrincipal);
        getContentPane().add(jpaneDatos);
        getContentPane().add(jpaneBotones);
        getContentPane().add(jscrollTabla, BorderLayout.CENTER);
    }

    private void initTable() {

        jDatos.setModel(oper.mostrarDatosTabla());
        // Con esto, hacemos que las celdas de la tabla jDatos no se puedan editar
        for (int i = 0; i < jDatos.getColumnCount(); i++) {
            Class<?> col_class = jDatos.getColumnClass(i);
            jDatos.setDefaultEditor(col_class, null);        // remueve el editor de celdas
        }

    }

    // Funciones de eventos
    private void exitForm(java.awt.event.WindowEvent evt) {
        System.exit(0);
    }

    @SuppressWarnings("unchecked")
    private void ajustarJCombos() {

        // Vectores inicializadores de los jComboBox
        String[] vectorDia = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "10",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] vectorDia2 = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "10",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
        String[] vectorDia3 = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "10",
            "21", "22", "23", "24", "25", "26", "27", "28", "29"};
        String[] vectorMes = new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
            "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        String[] vectorAno = new String[3000];

        java.time.LocalDateTime locaDate = java.time.LocalDateTime.now();
        int dia = locaDate.getDayOfMonth();
        int mes = locaDate.getMonthValue();
        year = locaDate.getYear();

        // Primero ajusto el mes para que así, dependiendo del mes, mostrar una determinada cantidad de días
        jcomboMes = new javax.swing.JComboBox<>(vectorMes);
        jcomboMes.setSelectedIndex(mes - 1);
        // Ahora dependiendo del mes, establezco la cantidad de días correspondientes
        if (jcomboMes.getSelectedItem() == "Enero" || jcomboMes.getSelectedItem() == "Marzo" || jcomboMes.getSelectedItem() == "Mayo" || jcomboMes.getSelectedItem() == "Julio"
                || jcomboMes.getSelectedItem() == "Agosto" || jcomboMes.getSelectedItem() == "Octubre" || jcomboMes.getSelectedItem() == "Diciembre") {
            jcomboDia = new javax.swing.JComboBox<>(vectorDia);
        } else if (jcomboMes.getSelectedItem() == "Abril" || jcomboMes.getSelectedItem() == "Junio" || jcomboMes.getSelectedItem() == "Septiembre"
                || jcomboMes.getSelectedItem() == "Noviembre") {
            jcomboDia = new javax.swing.JComboBox<>(vectorDia2);
        } else if (jcomboMes.getSelectedItem() == "Febrero") {
            jcomboDia = new javax.swing.JComboBox<>(vectorDia3);
        }

        jcomboAno = new javax.swing.JComboBox<>();
        for (int i = 0; i <= year + 3; i++) {
            jcomboAno.addItem(i);
        }
        jcomboDia.setSelectedIndex(dia - 1);

        jcomboAno.setSelectedIndex(year);

    }

    @SuppressWarnings("unchecked")
    private void jcomboMesItemStateChanged(java.awt.event.ItemEvent evt) {

        // Eliminamos todos los valores del jcomboBox de los días
        jcomboDia.removeAllItems();

        // Vectores inicializadores de los jComboBox
        String[] vectorDia = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "10",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] vectorDia2 = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "10",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
        String[] vectorDia3 = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "10",
            "21", "22", "23", "24", "25", "26", "27", "28", "29"};

        if (jcomboMes.getSelectedItem() == "Enero" || jcomboMes.getSelectedItem() == "Marzo" || jcomboMes.getSelectedItem() == "Mayo" || jcomboMes.getSelectedItem() == "Julio"
                || jcomboMes.getSelectedItem() == "Agosto" || jcomboMes.getSelectedItem() == "Octubre" || jcomboMes.getSelectedItem() == "Diciembre") {
            // Realizamos un bucle que rellena el jcomboDia con el número de días adecuado dependiendo del mes
            for (int i = 0; i < vectorDia.length; i++) {
                jcomboDia.addItem(vectorDia[i]);
            }

        } else if (jcomboMes.getSelectedItem() == "Abril" || jcomboMes.getSelectedItem() == "Junio" || jcomboMes.getSelectedItem() == "Septiembre"
                || jcomboMes.getSelectedItem() == "Noviembre") {
            for (int i = 0; i < vectorDia2.length; i++) {
                jcomboDia.addItem(vectorDia2[i]);
            }
        } else if (jcomboMes.getSelectedItem() == "Febrero") {
            for (int i = 0; i < vectorDia3.length; i++) {
                jcomboDia.addItem(vectorDia3[i]);
            }
        }
        
        // Establezco el día actual en la jcomboDia para que, aun que cambie de mes, muestre el día actual.
        java.time.LocalDateTime locaDate = java.time.LocalDateTime.now();
        int dia = locaDate.getDayOfMonth();
        jcomboDia.setSelectedIndex(dia - 1);

    }

    private void jbtIngresarActionPerformed(java.awt.event.ActionEvent evt) {

        day = Integer.parseInt(jcomboDia.getSelectedItem().toString());
        month = jcomboMes.getSelectedItem().toString();
        year = Integer.parseInt(jcomboAno.getSelectedItem().toString());

        try {

            ingreso = Double.parseDouble(jtxtIngreso.getText());
            gastos = Double.parseDouble(jtxtGastos.getText());
            beneficio = Double.parseDouble(jtxtBeneficios.getText());
            oper.ingresar(day, month, year, ingreso, gastos, beneficio);
            jtxtIngreso.setText("");
            jtxtGastos.setText("");
            jtxtBeneficios.setText("");

        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Por favor, asegúrese de que los datos introducidos sean correctos", "Error al ingresar", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        initTable();

    }

    private void jbtMostrarResumenActionPerformed(java.awt.event.ActionEvent evt) {

        
        resu.setVisible(true);
    }

    private void jtxtfocuslost(java.awt.event.FocusEvent evt) {
        Object jtxtobj = evt.getSource();

        String ingresos = jtxtIngreso.getText();
        String gasto = jtxtGastos.getText();

        if (ingresos != "" && gasto != "") {
            try {
                ingreso = Double.parseDouble(ingresos);
                gastos = Double.parseDouble(gasto);
                Double resultado = ingreso - gastos;
                jtxtBeneficios.setText(resultado.toString());

            } catch (NumberFormatException ex) {

            }
        }

    }

    private void jbtBorrarActionPerformed(java.awt.event.ActionEvent evt) {
        int indice = 0;
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jDatos.getModel();
        if (jDatos.getSelectedRow() != -1) {
            indice = (Integer) model.getValueAt(jDatos.getSelectedRow(), 0);
        }
        oper.borrarDatos(indice);
        initTable();
    }

    private void jbtBorrarUltActionPerformed(java.awt.event.ActionEvent evt) {
        int indice;
        // Guardamos en la variable ultima el último indice de la última fila de la jtable
        int ultima = jDatos.getRowCount() - 1;
        // Compruebo con if que la jtable no está vacía
        if (ultima != -1) {
            // Borro la fila que me interesa, en este caso es la primera por las coordenadas del getValue(fila, columna)
            indice = (Integer) jDatos.getValueAt(0, 0);
            oper.borrarDatos(indice);
        }

        initTable();

    }

    public static void main(String[] args) {

        // Administrador de diseño
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo establecer el aspecto deseado: ");
        }

        new Store().setVisible(true);

    }

}
