/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import dominio.Egreso;
import dominio.Fecha;
import dominio.Ingreso;
import dominio.IngresoSeminario;
import dominio.IngresoSesion;
import dominio.Egreso;
import dominio.EgresoMaterial;
import dominio.Seminario;
import dominio.Sesion;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class FechasXml {
    private Element root;
    private String fileLocation = "src/Xml/Fechas.xml";
    java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
    
    public FechasXml() {
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
    
    private Element FechatoXmlElement(Fecha nFecha){
        Element fecha = new Element("Fecha");
        
       
        Element date = new Element("DMY");
        date.setText(sdf.format(nFecha.getFecha()));
        fecha.addContent(date);
        Element seminarios = new Element("Seminarios");
        fecha.addContent(seminarios);
        Element sesiones = new Element ("Sesiones");
        fecha.addContent(sesiones);
        Element saldoDiario = new Element("SaldoDiario");        
        Element ingresos = new Element("Ingresos");
        fecha.addContent(ingresos);
        Element egresos = new Element("Egresos");
        fecha.addContent(egresos);
        
         return fecha;      
    }
   
     private Element SeminariotoXmlElement(Seminario nSeminario){
       
          Element seminario = new Element("Seminario");
          Element nombreSem = new Element("NombreSeminario");
          nombreSem.setText(nSeminario.getNombre());
          seminario.addContent(nombreSem);
          Element asistentesSem = new Element("AsistentesSeminario");
          seminario.addContent(asistentesSem);
          
         
          // For para recorrer asistentes y añadir contenido
          return seminario;
     }
     
     private Element AsistentestoXmlElement(String nNombre){
         Element asistente = new Element("Asistente");
         asistente.setText(nNombre);
         return asistente;
         
         
     }
     
     private Element SesiontoXmlElement(Sesion nSesion){
         Element sesion = new Element("Sesion");
         Element fechaSesion = new Element("FechaSesion");
         fechaSesion.setText(sdf.format(nSesion.getFecha()));
         sesion.addContent(fechaSesion);
         Element asistentesSes = new Element("AsistentesSesion");
         sesion.addContent(asistentesSes);
         return sesion;
     }
     
     private Element IngresotoXmlElement(Ingreso nIngreso){
            Element ingreso = new Element("Ingreso");
            Element razonIng = new Element ("RazonIngreso");
            Element totalE = new Element("TotalEfectivo");
            Element totalD = new Element("TotalDebito");
            Element totalI = new Element("ValorTotal");
            Element fechaIng = new Element("FechaIngreso");
            totalE.setText(nIngreso.getTotalEfectivo().toString());
            totalD.setText(nIngreso.getTotalDebito().toString());
            totalI.setText(Double.toString(Double.parseDouble(totalE.getText())+Double.parseDouble(totalD.getText())));
            razonIng.setText(nIngreso.getRazon());
            fechaIng.setText(sdf.format(nIngreso.getFecha())); // para transformar a object sdf.parse(String)
            ingreso.addContent(totalE);
            ingreso.addContent(totalD);
            ingreso.addContent(totalI);
            ingreso.addContent(fechaIng);
            ingreso.addContent(razonIng);
           
            //Instance of -ingresoSeminario
             if (nIngreso instanceof IngresoSeminario){
                 
             Element tipo = new Element("Tipo");
             tipo.setText("IngresoSeminario");
             Element precio = new Element("Precio");
             precio.setText(((IngresoSeminario) nIngreso).getPrecio().toString());
             Element asistentes = new Element("Asistentes");
             asistentes.setText(Integer.toString(((IngresoSeminario) nIngreso).getAsistentes()));
             Element efectivo = new Element("Efectivo");
             efectivo.setText(Integer.toString(((IngresoSeminario) nIngreso).getEfectivo()));
             Element debito = new Element("Debito");
             debito.setText(Integer.toString(((IngresoSeminario) nIngreso).getDebito()));
             ingreso.addContent(tipo);
             ingreso.addContent(precio);
             ingreso.addContent(asistentes);
             ingreso.addContent(efectivo);
             ingreso.addContent(debito);
            
             }
            
            //Instance of -IngresoSesion
            if(nIngreso instanceof IngresoSesion){
                Element tipo = new Element("Tipo");
                tipo.setText("IngresoSesion");
                Element precio = new Element("Precio");
                precio.setText(((IngresoSesion) nIngreso).getPrecio().toString());
             
                Element efectivo = new Element("Efectivo");
                efectivo.setText(Integer.toString(((IngresoSesion) nIngreso).getEfectivo()));
                Element debito = new Element("Debito");
                debito.setText(Integer.toString(((IngresoSesion) nIngreso).getDebito()));
                Element asistentes = new Element("Asistentes");
                asistentes.setText(Integer.toString(((IngresoSesion) nIngreso).getAsistentes()));
                
                ingreso.addContent(tipo);
                ingreso.addContent(precio);
                ingreso.addContent(efectivo);
                ingreso.addContent(debito);
                ingreso.addContent(asistentes);                
            }
         
         return ingreso;
     }
     
      private Element EgresotoXmlElement(Egreso nEgreso){
          
            Element egreso = new Element("Egreso");            
            Element valorT = new Element("ValorTotal");
            Element fechaEgr = new Element("FechaEgreso");
            Element razonEgr = new Element("RazonEgreso");
            valorT.setText(nEgreso.getValorTotal().toString());
            fechaEgr.setText(sdf.format(nEgreso.getFecha()));
            razonEgr.setText(nEgreso.getRazon());
            egreso.addContent(valorT);
            egreso.addContent(fechaEgr);
            egreso.addContent(razonEgr);
            
            
            //instance of -egresoMateriales
            if(nEgreso instanceof EgresoMaterial){
               
                Element material = new Element("NombreMaterial");
                material.setText(((EgresoMaterial) nEgreso).getNombrematerial());
                Element precioMaterial = new Element ("PrecioMaterial");
                precioMaterial.setText(((EgresoMaterial) nEgreso).getPrecio().toString());
                Element cantidadMaterial = new Element("CantidadMaterial");
                cantidadMaterial.setText(Integer.toString(((EgresoMaterial) nEgreso).getCantidad()));
                
                egreso.addContent(material);
                egreso.addContent(precioMaterial);
                egreso.addContent(cantidadMaterial);
                
            }
          
          
          return egreso;
      }
     
     
    
    public boolean agregarFecha(Fecha nFecha) {
        boolean resultado = false;
        root.addContent(FechatoXmlElement((Fecha) nFecha));
        resultado = updateDocument();
        return resultado;
    }
    
    public boolean agregarSeminario(Seminario nSeminario, Date fecha){
        boolean resultado = false;
        Seminario verificar = buscarSeminario(sdf.format(fecha),nSeminario.getNombre());
        if(verificar==null){
            buscarRaiz(fecha).getChild("Seminarios").addContent(SeminariotoXmlElement((Seminario) nSeminario));
            resultado = updateDocument();
        }
       
        return resultado;
    }
    
    public boolean agregarAsistenteSeminario(String nombreAsis,String nombreSem, String fecha) throws ParseException{
        boolean resultado = false;
        //Devuelve un elemento que tenga el mismo nombre que nombreAsis en la seccion Asistentes de la lista de seminarios correspondiente
        
      
            if(VerificarAsistenteToEvento(nombreAsis,sdf.parse(fecha),"Seminario" ,"Seminarios","AsistentesSeminario")==false){
                buscar(buscarRaiz(sdf.parse(fecha)).getChild("Seminarios").getChildren("Seminario"),nombreSem,"NombreSeminario").getChild("AsistentesSeminario").addContent(AsistentestoXmlElement(nombreAsis));
                resultado = updateDocument();
            }
            
        return resultado;
        
    }
     
    public boolean agregarSesion(Sesion nSesion, Date fecha){
        boolean resultado = false;
        Sesion verificar = buscarSesion(sdf.format(fecha));
        if(verificar == null){
            buscarRaiz(fecha).getChild("Sesiones").addContent(SesiontoXmlElement((Sesion) nSesion));
            resultado = updateDocument();
        }
        return resultado;
    }
    
    public boolean agregarAsistenteSesion(String nombreAsis, String fecha) throws ParseException{
        boolean resultado= false;
        if(VerificarAsistenteToEvento(nombreAsis,sdf.parse(fecha),"Sesion" ,"Sesiones","AsistentesSesion")==false){
            
            buscar(buscarRaiz(sdf.parse(fecha)).getChild("Sesiones").getChildren("Sesion"),fecha,"FechaSesion").getChild("AsistentesSesion").addContent(AsistentestoXmlElement(nombreAsis));
            resultado = updateDocument();
        }       
        return resultado;
    }
    
    public boolean agregarIngreso(Ingreso nIngreso, Date fecha){
        boolean resultado = false;
        buscarRaiz(fecha).getChild("Ingresos").addContent(IngresotoXmlElement((Ingreso)nIngreso));
        resultado = updateDocument();
        return resultado;
    }
    
    public boolean agregarEgreso(Egreso nEgreso, Date fecha){
        boolean resultado = false;
        buscarRaiz(fecha).getChild("Egresos").addContent(EgresotoXmlElement((Egreso)nEgreso));
        resultado = updateDocument();
        return resultado;
    }
    
    

    private Fecha FechaToObject(Element element)throws ParseException {
        Fecha nFecha = new Fecha(sdf.parse(element.getChildText("DMY")));
        return nFecha;
    }

    public Seminario SeminarioToObject(Element element)throws ParseException {
        Seminario nSeminario = new Seminario (element.getChildText("NombreSeminario"));
        return nSeminario;
    }
    public Sesion SesionToObject(Element element)throws ParseException {
        Sesion nSesion = new Sesion (sdf.parse(element.getChildText("FechaSesion")));
        return nSesion;
    }
    private Ingreso IngresoToObject(Element element)throws ParseException {
        Ingreso nIngreso = new Ingreso (Double.parseDouble(element.getChildText("TotalEfectivo")), Double.parseDouble(element.getChildText("TotalDebito")), element.getChildText("RazonIngreso"), sdf.parse(element.getChildText("FechaIngreso")));
        return nIngreso;
    }
     
   private IngresoSeminario IngresoSeminarioToObject(Element element)throws ParseException{
       IngresoSeminario nIngreso = new IngresoSeminario(Double.parseDouble(element.getChildText("Precio")),Integer.parseInt(element.getChildText("Efectivo")),Integer.parseInt(element.getChildText("Debito")),Double.parseDouble(element.getChildText("TotalEfectivo")),Double.parseDouble(element.getChildText("TotalDebito")),element.getChildText("RazonIngreso"),sdf.parse(element.getChildText("FechaIngreso")));       
       return nIngreso;
   }
   
   private IngresoSesion IngresoSesionToObject(Element element) throws ParseException{
       IngresoSesion nIngreso = new IngresoSesion(Double.parseDouble(element.getChildText("Precio")),Integer.parseInt(element.getChildText("Efectivo")),Integer.parseInt(element.getChildText("Debito")),Double.parseDouble(element.getChildText("TotalEfectivo")),Double.parseDouble(element.getChildText("TotalDebito")),element.getChildText("RazonIngreso"),sdf.parse(element.getChildText("FechaIngreso")));
       return nIngreso;
       
   }
      
      
      
    private Egreso EgresoToObject(Element element)throws ParseException {
        Egreso nEgreso = new Egreso (Double.parseDouble(element.getChildText("ValorTotal")), sdf.parse(element.getChildText("FechaEgreso")), element.getChildText("RazonEgreso"));
        return nEgreso;
    }
    private EgresoMaterial EgresoMaterialToObject(Element element)throws ParseException {
        EgresoMaterial nEgreso = new EgresoMaterial (element.getChildText("NombreMaterial"),Double.parseDouble(element.getChildText("PrecioMaterial")),Integer.parseInt(element.getChildText("CantidadMaterial")),Double.parseDouble(element.getChildText("ValorTotal")),sdf.parse(element.getChildText("FechaEgreso")),element.getChildText("RazonEgreso"));
        return nEgreso;
    }
    
     public ArrayList<Fecha> todasLasFechas() {
        ArrayList<Fecha> resultado = new ArrayList<Fecha>();
        for (Object it : root.getChildren()) {
            Element xmlElem = (Element) it;
            try {
                resultado.add(FechaToObject(xmlElem));
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }
     
      public ArrayList<Seminario> todosLosSeminarios(Element seminarios) {
        ArrayList<Seminario> resultado = new ArrayList<Seminario>();
        for (Object it : seminarios.getChildren("Seminario")) {
            Element xmlElem = (Element) it;
            try {
                resultado.add(SeminarioToObject(xmlElem));
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }
      
        public ArrayList<Sesion> todasLasSesiones(Element sesiones) {
            ArrayList<Sesion> resultado = new ArrayList<Sesion>();
            for (Object it : sesiones.getChildren("Sesion")) {
                Element xmlElem = (Element) it;
                try {
                    resultado.add(SesionToObject(xmlElem));
                } catch (ParseException ex) {
                 System.out.println(ex.getMessage());
                }
        }
        return resultado;
    }
        public ArrayList<Ingreso> todosLosIngresos(Element ingresos){
            ArrayList<Ingreso> resultado = new ArrayList<Ingreso>();
            for(Object it :  ingresos.getChildren("Ingreso")){
                Element xmlElem = (Element) it;
                try {
                      if(new String("Seminario").equals(xmlElem.getChildText("RazonIngreso")))
                        resultado.add(IngresoSeminarioToObject(xmlElem));
                      else if(new String("Sesion").equals(xmlElem.getChildText("RazonIngreso")))
                          resultado.add(IngresoSesionToObject(xmlElem));
                      else                         
                          resultado.add(IngresoToObject(xmlElem));
                } catch (ParseException ex) {
                 System.out.println(ex.getMessage());
                }
            }
         return resultado;
        }
        
          public ArrayList<Egreso> todosLosEgresos(Element egresos){
            ArrayList<Egreso> resultado = new ArrayList<Egreso>();
            for(Object it :  egresos.getChildren("Egreso")){
                Element xmlElem = (Element) it;
                try {
                      if(new String("Materiales").equals(xmlElem.getChildText("RazonEgreso")))
                        resultado.add(EgresoMaterialToObject(xmlElem));
                    
                      else                         
                          resultado.add(EgresoToObject(xmlElem));
                } catch (ParseException ex) {
                 System.out.println(ex.getMessage());
                }
            }
         return resultado;
        }
          
        public ArrayList<String> todosLosAsistentesSeminario(String nombreSem, String fechaSem) throws ParseException { // Devuelve un arrayList que contienen los seminarios a los que ha asistido un alumno
          ArrayList<String> resultado= new ArrayList<String>();
          List seminarios= buscarRaiz(sdf.parse(fechaSem)).getChild("Seminarios").getChildren("Seminario");
          Element seminario =buscar(seminarios,nombreSem,"NombreSeminario"); 
          for (Object it :seminario.getChild("AsistentesSeminario").getChildren("Asistente") ) {
              Element xmlElem = (Element) it;
              resultado.add(xmlElem.getText());
           }
       
        return resultado;
        }
        
        public ArrayList<String> todosLosAsistentesSesion(String fechaSes) throws ParseException{
            ArrayList<String> resultado= new ArrayList<String>();
            List seminarios= buscarRaiz(sdf.parse(fechaSes)).getChild("Sesiones").getChildren("Sesion");
            Element seminario =buscar(seminarios,fechaSes,"FechaSesion"); 
            for (Object it :seminario.getChild("AsistentesSesion").getChildren("Asistente") ) {
                Element xmlElem = (Element) it;
                resultado.add(xmlElem.getText());
           }
            return resultado;
        }
    
      
    public Element buscarRaiz(Date fecha){
        List Fechas = this.root.getChildren();
        Element aux = new Element("Fecha");
        aux =buscar(Fechas, sdf.format(fecha),"DMY");
        return aux;
        
    }
    public Element buscar(List raiz, String dato, String tag) {
        Iterator i = raiz.iterator();
        while (i.hasNext()) {
            Element e = (Element) i.next();
            if (dato.equals(e.getChild(tag).getText())) {
                return e;
            }
        }
        return null;
    }
    
    
      public Fecha buscarFecha(String fecha) {
        Fecha fec;
        Element aux = new Element("Fecha");
        List Fechas = this.root.getChildren("Fecha");
        
        
        while (aux != null) {
           
            aux =buscar(Fechas, fecha,"DMY");
            
            if (aux != null) {
                try {
                  
                    fec = FechaToObject(aux);
                  
                    return fec;
                    
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
     
        return null;
    }
      
        public Sesion buscarSesion(String fecha){
        Sesion ses;
        Element aux = new Element("Fecha");
        List sesiones=null;
        try {
            sesiones = buscarRaiz(sdf.parse(fecha)).getChild("Sesiones").getChildren("Sesion");
        } catch (ParseException ex) {
            Logger.getLogger(FechasXml.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        while (aux != null) {
           
            aux =buscar(sesiones, fecha,"FechaSesion");
           
            
            if (aux != null) {
                System.out.println("si encontro");
                try {
                  
                    ses = SesionToObject(aux);
                  
                    return ses;
                    
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
     
        return null;
    }
    
        public Seminario buscarSeminario(String fecha, String nombreSeminario){
        Seminario sem;
        Element aux = new Element("Fecha");
        List seminarios=null;
        try {
            seminarios = buscarRaiz(sdf.parse(fecha)).getChild("Seminarios").getChildren("Seminario");
        } catch (ParseException ex) {
            Logger.getLogger(FechasXml.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        while (aux != null) {
           
            aux =buscar(seminarios,nombreSeminario,"NombreSeminario");
           
            
            if (aux != null) {
                
                try {
                  
                    sem = SeminarioToObject(aux);
                  
                    return sem;
                    
                } catch (ParseException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
     
        return null;
    }
        
     public boolean VerificarAsistenteToEvento(String dato, Date fecha, String tag1 , String tag2, String tag3) throws ParseException{
      List lista =  buscarRaiz(fecha).getChild(tag2).getChildren(tag1);
      Iterator i = lista.iterator();
      
      while(i.hasNext()){
          Element e = (Element) i.next();
          e=e.getChild(tag3);
          List lista2 = e.getChildren("Asistente");
          Iterator j = lista2.iterator();
          while(j.hasNext()){
          
            Element f = (Element) j.next();
            if(dato.equals(f.getText())){
                JOptionPane.showMessageDialog(null, "Este evento ya contiene al asistente seleccionado, por favor ingrese un asistente distinto");   
                 return true;
            }
             
          }
          
        }
      
        return false; 
    }
     
     
     
    private boolean updateDocument() {
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
    
     public void ActualizarSeminario(Seminario temporal, String nombre, String fecha) {
         
        try {
            Element aux = buscar(buscarRaiz(sdf.parse(fecha)).getChild("Seminarios").getChildren("Seminario"),nombre,"NombreSeminario");
            
            aux.getChild("NombreSeminario").setText(temporal.getNombre());
            updateDocument();
            
            
            
        } catch (ParseException ex) {
            Logger.getLogger(FechasXml.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    
     }
        public boolean borrarSeminario(String nombre, String fecha) {
        
            boolean resultado = false;
            try {
            Element aux = buscar(buscarRaiz(sdf.parse(fecha)).getChild("Seminarios").getChildren("Seminario"),nombre,"NombreSeminario");
            //List Seminarios = this.root.getChildren("Fecha");
            List Seminarios = buscarRaiz(sdf.parse(fecha)).getChild("Seminarios").getChildren("Seminario");
           
            
                if (aux != null) {
                    Seminarios.remove(aux);
                    resultado = updateDocument();
                
                }
            } catch (ParseException ex) {
            Logger.getLogger(FechasXml.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resultado;
    }
        
        public void borrarSesion(String fecha){
        try {
            List lista = buscarRaiz(sdf.parse(fecha)).getChild("Sesiones").getChildren("Sesion");
            Element aux = buscar(lista, fecha, "FechaSesion");
            if(aux!=null){
                lista.remove(aux);
                updateDocument();
            }
        } catch (ParseException ex) {
            Logger.getLogger(FechasXml.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    public void borrarIngresoEgreso(String cantidad, String fecha, String tag1, String tag2){
        try {
            List lista = buscarRaiz(sdf.parse(fecha)).getChild(tag1).getChildren(tag2);
            Element aux = buscar(lista,cantidad,"ValorTotal");
            
            if(aux!=null){
                lista.remove(aux);
                updateDocument();
            }
  
        } catch (ParseException ex) {
            Logger.getLogger(FechasXml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarOtroIngresoEgreso(String razon, String fecha,String tag1, String tag2, String tag3 ){
        try {
            List lista = buscarRaiz(sdf.parse(fecha)).getChild(tag1).getChildren(tag2);
            Element aux = buscar(lista,razon,tag3);
            if(aux!=null){
                lista.remove(aux);
                updateDocument();
            }
        } catch (ParseException ex) {
            Logger.getLogger(FechasXml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
