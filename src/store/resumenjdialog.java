/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

import java.awt.event.ActionEvent;

/**
 *
 * @author luisj
 */
public class resumenjdialog extends javax.swing.JDialog{
    
    
    private javax.swing.JComboBox jcomboMonth;
    private javax.swing.JComboBox jcomboYear;
    private javax.swing.JTable jDatos;
    private javax.swing.JButton jbtMostrar;
    private javax.swing.JScrollPane jscroll;
    private javax.swing.JTextArea areaResultado;
    
    // Instancias a otras clases
    Controlador.Operaciones oper;
    Modelo.Conexion con;
    
    public resumenjdialog(javax.swing.JFrame parent, boolean modal)
    {
        super(parent, modal);
        setTitle("Resumen");
        setBounds(1200, 489, 500, 400);
        // Inicializamos las instancias
        con = new Modelo.Conexion();
        oper = new Controlador.Operaciones();
        jDatos = new javax.swing.JTable();
        initComponents();
        
    }
    
    private void initComponents()
    {
        // Cambiamos el administrador de diseño a null
        getContentPane().setLayout(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                exitForm(evt);
            }
        });
        
        // Inicializamos objetos
        
        jcomboMonth = new javax.swing.JComboBox<>();
        jcomboYear = new javax.swing.JComboBox<>();
        initCombos();
        jbtMostrar = new javax.swing.JButton();
        jscroll = new javax.swing.JScrollPane();
        areaResultado = new javax.swing.JTextArea();
      
        
        // Propiedades de los objetos
        
        jbtMostrar.setText("Mostrar resumen");
        jbtMostrar.setBounds(300, 30, 150, 30);
        jbtMostrar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtMostrarActionPerformed(evt);
            }
        });
        jcomboMonth.setBounds(30, 30, 100, 30);
        jcomboYear.setBounds(150, 30, 100, 30);
        jscroll.setBounds(0, 130, 482, 254);
        areaResultado.setText("");
        areaResultado.setEditable(false);
        areaResultado.setBounds(0, 70, 482, 63);
        
        // Añadiendo la tabla al jscroll
        jscroll.setViewportView(jDatos);
        
        
        // Añadiento controles al panel principal
        getContentPane().add(jcomboMonth);
        getContentPane().add(jcomboYear);
        getContentPane().add(jscroll);
        getContentPane().add(jbtMostrar);
        getContentPane().add(areaResultado);
    }
    
    private void exitForm(java.awt.event.WindowEvent evt)
    {
        this.setVisible(false);
    }
    
    @SuppressWarnings("unchecked")
    private void initCombos()
    {
        // Vectores inicializadores de los jComboBox
        
        String[] vectorMes = new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
            "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        String[] vectorAno = new String[3000];

        java.time.LocalDateTime locaDate = java.time.LocalDateTime.now();
        
        int mes = locaDate.getMonthValue();
        int year = locaDate.getYear();

        
        jcomboMonth = new javax.swing.JComboBox<>(vectorMes);
        jcomboYear = new javax.swing.JComboBox<>();
        for (int i = 0; i <= year + 3; i++) {
            jcomboYear.addItem(i);
        }
        
        jcomboMonth.setSelectedIndex(mes - 1);
        jcomboYear.setSelectedIndex(year);
    }
    
    private void jbtMostrarActionPerformed(java.awt.event.ActionEvent evt)
    {
        String mes = jcomboMonth.getSelectedItem().toString();
        int year = (Integer)jcomboYear.getSelectedItem();
        jDatos.setModel(oper.mostrarResumen(mes, year));
        
        // Obtenemos el modelo de la jtable
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel)jDatos.getModel();
        
        // Obtenemos el número de filas que tiene la tabla
        int contador;
        contador = jDatos.getRowCount() -1;
        Double ingresos = 0.0;
        Double gastos = 0.0;
        Double beneficios = 0.0;
        for ( int i = 0; i < contador + 1; i++)
        {
            ingresos += (Double)model.getValueAt(i, 4);
            gastos += (Double)model.getValueAt(i, 5);
            beneficios += (Double)model.getValueAt(i, 6);
            
        }
        
      // Con esto, hacemos que las celdas de la tabla jDatos no se puedan editar
        for (int i = 0; i < jDatos.getColumnCount(); i++) {
            Class<?> col_class = jDatos.getColumnClass(i);
            jDatos.setDefaultEditor(col_class, null);        // remueve el editor de celdas
        }  
    
     areaResultado.setText("El mes de " + mes + " de " + year + " ha ingresado un total de: " + ingresos + " €, \nlos gastos han sido de: " + gastos
     + " €,\ny ha obtenido un beneficio de: " + beneficios + " €.");
}
}
