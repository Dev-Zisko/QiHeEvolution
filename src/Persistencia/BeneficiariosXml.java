/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import dominio.Alumno;
import dominio.Beneficiario;
import dominio.Seminario;
import dominio.Sesion;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Christian D
 */
public class BeneficiariosXml {
    
      private Element root;
    private String fileLocation = "src/Xml/Beneficiarios.xml";
    java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
    
    public BeneficiariosXml() {
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
    
    private Element BeneficiariotoXmlElement(Beneficiario nBeneficiario){
        
        Element beneficiario = new Element ("Beneficiario");
        Element nombre = new Element("Nombre");
        Element telefono = new Element("Telefono");
        Element correo = new Element("Correo");
        Element observaciones = new Element("Observaciones");
        Element fecha = new Element("FechaIngreso");
        
        nombre.setText(nBeneficiario.getNombre());
        telefono.setText(nBeneficiario.getTelefono().toString());
        correo.setText(nBeneficiario.getCorreo());
        observaciones.setText(nBeneficiario.getObservaciones());
        fecha.setText(sdf.format(nBeneficiario.getFechaIngreso()));
        beneficiario.addContent(nombre);
        
        beneficiario.addContent(correo);
        beneficiario.addContent(telefono);
        beneficiario.addContent(observaciones);
        beneficiario.addContent(fecha);
        
     
         return beneficiario;
    }
    
    
   
    
    
     public Element buscarRaiz(String nombre){        
        Element aux = BeneficiariosXml.buscar(root.getChildren(), nombre, "Nombre");
        return aux;
       
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
    
    public boolean agregarBeneficiario(Beneficiario nBeneficiario) {
        boolean resultado = false;
       
        Beneficiario verificar = buscarBeneficiario(nBeneficiario.getNombre());
        if(verificar == null){
           root.addContent(BeneficiariotoXmlElement((Beneficiario) nBeneficiario));
            resultado = updateDocument();  
        }         
        
        return resultado;
        
    }
    public void convertirAlumno(String nombre){
        boolean resultado = false;
             
             Element tipo = new Element("Tipo");
             tipo.setText("Alumno");
             Element certificado = new Element("Certificado");
             certificado.setText("false");
             Element numeroSeminarios = new Element("NumeroSeminarios");
             numeroSeminarios.setText("0");
            
             buscarRaiz(nombre).addContent(tipo);
             buscarRaiz(nombre).addContent(certificado);
             buscarRaiz(nombre).addContent(numeroSeminarios);
            
             resultado = updateDocument();
             
             
    }
    
    public void agregarAsistenciasToAlumno(Seminario sem, String nombreAlumno){ //Agrega Seminarios a un alumno
        boolean resultado = false;
        
        Element seminario = new Element("Seminario");
        seminario.setText(sem.getNombre());
        if(VerificarEventoToBeneficiario(sem.getNombre(), nombreAlumno, "Seminario") == false){
            buscarRaiz(nombreAlumno).addContent(seminario);
            resultado = updateDocument();
        }
          
      
    }
    
    public void agregarAsistenciasToBeneficiario(Sesion ses, String nombreBeneficiario){ //agrega Sesiones a un beneficiario o alumno
        boolean resultado = false;
        Element sesion = new Element("Sesion");
        sesion.setText(sdf.format(ses.getFecha()));
        
        if(VerificarEventoToBeneficiario(sdf.format(ses.getFecha()),nombreBeneficiario, "Sesion")==false){ 
        buscarRaiz(nombreBeneficiario).addContent(sesion);
        resultado = updateDocument();
        }
    }
    
    
    
    public ArrayList<Beneficiario> todosLosBeneficiarios() {
        ArrayList<Beneficiario> resultado = new ArrayList<Beneficiario>();     
        for (Object it : root.getChildren()) {
            Element xmlElem = (Element) it;
            try {               
                if(new String("Alumno").equals(xmlElem.getChildText("Tipo"))){
                resultado.add(AlumnoToObject(xmlElem)); 
                               
                }
                else
                resultado.add(BeneficiarioToObject(xmlElem));
                    
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }
    
    public ArrayList<Alumno> todosLosAlumnos(){
         ArrayList<Alumno> resultado = new ArrayList<Alumno>();     
        for (Object it : root.getChildren()) {
            Element xmlElem = (Element) it;
            try {               
                if(new String("Alumno").equals(xmlElem.getChildText("Tipo")))
                resultado.add(AlumnoToObject(xmlElem));  
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultado; 
    }
    public ArrayList<String> todasLasAsistenciasSeminarios(String nombre) { // Devuelve un arrayList que contienen los seminarios a los que ha asistido un alumno
        ArrayList<String> resultado= new ArrayList<String>();
        if(buscarRaiz(nombre).getChildText("Tipo").equals("Alumno")){
          for (Object it : buscarRaiz(nombre).getChildren("Seminario") ) {
            Element xmlElem = (Element) it;
            
            
            resultado.add(xmlElem.getText());
           }
       }
        return resultado;
    }
    
    public ArrayList<String> todasLasAsistenciasSesiones(String nombre){
        ArrayList<String> resultado= new ArrayList<String>();
        
          for (Object it : buscarRaiz(nombre).getChildren("Sesion") ) {
            Element xmlElem = (Element) it;
            
            
            resultado.add(xmlElem.getText());
           }
       
        return resultado;
        
    }
    
    public static Element buscar(List raiz, String dato, String tag) { //Permite obtener un elemento que sea un dato unico en una lista de Xml
        Iterator i = raiz.iterator();
        while (i.hasNext()) {
            Element e = (Element) i.next();
            if (dato.equals(e.getChild(tag).getText())) {
                return e;
            }
        }
        return null;
    }
    
    public boolean VerificarEventoToBeneficiario(String dato, String nombreBeneficiario, String tag){ //Verifica si ya el evento esta relacionado con el Beneficiario
      List lista =  buscarRaiz(nombreBeneficiario).getChildren(tag);
      Iterator i = lista.iterator();
      
      while(i.hasNext()){
          Element e = (Element) i.next();
          if(dato.equals(e.getText()))
              return true;
          }
        return false; 
    }
    
   
    
    
    
    public Beneficiario buscarBeneficiario(String nombre) {
       
        Element aux = new Element("Beneficiario");
        List Beneficiarios = this.root.getChildren("Beneficiario");
        
        
        while (aux != null) {
           
            aux = BeneficiariosXml.buscar(Beneficiarios, nombre, "Nombre");
            
            if (aux != null) {
                try {
                
                       
                     if(!aux.getChildren("Tipo").isEmpty() && aux.getChildText("Tipo").equals("Alumno")){ //Si el tipo no esta vacio y contiene Alumno
                        return AlumnoToObject(aux);
                  }
                  
                  
                  else
                     return BeneficiarioToObject(aux);
                  
                    
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
     
        return null;
    }
    
    private Beneficiario BeneficiarioToObject(Element element) throws ParseException {
       
       Beneficiario nBeneficiario = new Beneficiario(element.getChildText("Nombre"),element.getChildText("Telefono"),element.getChildText("Correo"),element.getChildText("Observaciones"), sdf.parse(element.getChildText("FechaIngreso")));
       return nBeneficiario;
    }
     private Alumno AlumnoToObject (Element element) throws ParseException {
       Alumno nAlumno = new Alumno(element.getChildText("Nombre"),element.getChildText("Telefono"),element.getChildText("Correo"),element.getChildText("Observaciones"), sdf.parse(element.getChildText("FechaIngreso")),Boolean.parseBoolean(element.getChildText("Certificado")),Integer.parseInt(element.getChildText("NumeroSeminarios")));
       return nAlumno;
     }
    
     
     public void ActualizarBeneficiario(Beneficiario temporal, String nombre) {
         
         Element aux = buscarRaiz(nombre);
         if(aux != null){
         aux.getChild("Nombre").setText(temporal.getNombre());
         aux.getChild("Telefono").setText(temporal.getTelefono());
         aux.getChild("Correo").setText(temporal.getCorreo());
         aux.getChild("Observaciones").setText(temporal.getObservaciones());
         
         updateDocument();
         }
        
     }
    public boolean borrarBeneficiario(String nombre) {
        boolean resultado = false;
        Element aux = buscarRaiz(nombre);
        List Personas = this.root.getChildren("Beneficiario");
            
            if (aux != null) {
                Personas.remove(aux);
                resultado = updateDocument();
                
            }
        
        return resultado;
    }
        
    public void ActualizarAsistenciasSeminario(String seminario, String seminarioAntiguo){
        List listai =root.getChildren("Beneficiario");
        Iterator i = listai.iterator();
        while(i.hasNext()){
            Element ben = (Element) i.next();
            List listaj = ben.getChildren("Seminario");
            Iterator j = listaj.iterator();
            while(j.hasNext()){
                Element sem = (Element) j.next();
                if(sem.getText().equals(seminarioAntiguo))
                sem.setText(seminario);
            }
        }
        updateDocument();
    }  
           
        
}
