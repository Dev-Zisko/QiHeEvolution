package Persistencia;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTextPane;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian D
 */
public class DatosAsociacionXml {
    private Element root;
   // private String fileLocation = "C:\\Users\\chdra\\Desktop\\ProyectoV3.0\\src\\Xml\\DatosAsociacion.xml";
   private String fileLocation = "src/Xml/DatosAsociacion.xml";
    
    public DatosAsociacionXml() {
         try {
            SAXBuilder builder = new SAXBuilder(false);
            Document doc = null;
            doc = builder.build(fileLocation);
            root = doc.getRootElement();
        } catch (JDOMException ex) {
            System.out.println("No se pudo iniciar la operacion por: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("No se pudo iniciar la operacion por: " + ex.getMessage());
        }
    }
    
     public void ActualizarAsociacion(JTextPane nombre,JTextPane telefono, JTextPane direccion, JTextPane rif, DatosAsociacionXml datos){
       boolean resultado = false;
       root.getChild("Nombre").setText(nombre.getText());
       root.getChild("Telefono").setText(telefono.getText());
       root.getChild("Direccion").setText(direccion.getText());
       root.getChild("Rif").setText(rif.getText());
       resultado = updateDocument();
     }
     
     public void establecerPrecioSesion(Double precio){
      boolean resultado = false;
      root.getChild("PrecioSesion").setText(precio.toString());
      resultado = updateDocument();
     }
      public Element buscarRaiz(){
        Element aux = this.root;
        return aux;
        
    }
      
     public double traerPrecio(){
         return Double.parseDouble(root.getChildText("PrecioSesion"));
     }
      
      public boolean updateDocument() {
        try {
            XMLOutputter out = new XMLOutputter(org.jdom.output.Format.getPrettyFormat());
            FileOutputStream file = new FileOutputStream(fileLocation);
            out.output(root, file);
            file.flush();
            file.close();
            return true;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return false;
        }
    }
}
