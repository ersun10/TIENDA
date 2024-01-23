/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import javax.swing.text.AttributeSet;

/**
 *
 * @author luisj
 */
public class jtextfields extends javax.swing.JTextField{
    
   // Sobreescribimos la clase createDefaultModel
    @Override
    protected javax.swing.text.Document createDefaultModel()
    {
        return new PlainDocumentDouble();
    }
    
    protected class PlainDocumentDouble extends javax.swing.text.PlainDocument
    {
       @Override
       public void insertString (int offs, String str, AttributeSet a)
                throws javax.swing.text.BadLocationException
        {
            // Fuente: almacena el contenido de la caja de texto
            char[] fuente = str.toCharArray();
            // Resultado: alamacena el contenido de la caja de texto validado
            char[] resultado = new char[fuente.length];
            int j = 0;
            
            // Almacenar en resultado los carácteres válidos de fuente
            for (int i = 0; i < fuente.length; i++)
            {
                if(fuente[i] >= '0' && fuente[i] <= '9' || fuente[i] == '.' || fuente[i] == '-')
                {
                    resultado[j++] = fuente[i];
                    
                } else {
                    java.awt.Toolkit.getDefaultToolkit().beep();
                }
                
                super.insertString(offs, new String(resultado, 0, j), a);
            }
            
        }
    }
    
    
}
