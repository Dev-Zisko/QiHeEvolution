/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//Import itextpdf
import Persistencia.BeneficiariosXml;
import Persistencia.DatosAsociacionXml;
import Persistencia.FechasXml;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import java.io.IOException;
import com.toedter.calendar.JDateChooser;
import dominio.Asociacion;
import java.util.Date;
import dominio.Beneficiario;
import dominio.Egreso;
import dominio.Fecha;
import dominio.Ingreso;
import dominio.Seminario;
import dominio.Sesion;
import interfaz.personalizados.PanelFondo;
import java.applet.*;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import Reportes.CrearPDF;
import com.itextpdf.text.PageSize;
import dominio.Alumno;
import java.awt.Desktop;
import java.awt.Image;
import java.util.List;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdom.Element;



/**
 *
 * @author Christian D
 */
public class Controladora {
 
    AudioClip sonido;
    Asociacion asociacion = new Asociacion("Escuela Abierta de Artes Sanadoras Qi-He","04141419921","Calle Roraima, av. Rio de Janeiro. Chuao. Clínica Aquamater. Miranda, Venezuela.","j-4071-26822");
    java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
    Cursor cursor;
    ImageIcon imagenCursor;
    Toolkit TK;
    Document listaCorreos = new Document(PageSize.LETTER, 20, 20, 20, 20); //Crea documento donde se guardara la lista de correos
    CrearPDF pdf = new CrearPDF(); 
    String seminarioTemp, fechaSemTemp;
    
    
//Constructores
    public Controladora(JFrame VentanaPrincipal, PanelFondo panelBeneficiarios, PanelFondo panelSeminarios, PanelFondo panelSesiones, PanelFondo panelIngresosyEgresos, PanelFondo panelSeminariosD, PanelFondo panelLogo) {
        //Constructor VentanaPrincipal
        VentanaPrincipal.setLocationRelativeTo(null);
        panelBeneficiarios.setBackground("src/imagenes/Fondo2Program.png");
        panelSeminarios.setBackground("src/imagenes/Fondo2Program.png");
        panelSesiones.setBackground("src/imagenes/Fondo2Program.png");
        panelIngresosyEgresos.setBackground("src/imagenes/Fondo2Program.png"); 
        panelSeminariosD.setBackground("src/imagenes/Fondo2Program.png");
        panelLogo.setBackground("src/imagenes/Imagen1.png");
    }

    public Controladora(JFrame Ventana, PanelFondo panel) {  //Constructor ventana Login, registrar, Pacientes y alumnos
        panel.setBackground("src/imagenes/Fondo2Program.png");
       //panel.setBackground(this.getClass().getResource("imagenes/Fondo.jpg").getPath());
    }

    public Controladora() {
    }
    
    
    
  //Ventanas  
    
    public void IconoVentana(JFrame Ventana){ //Metodo que coloca el icono a las ventanas creadas.
       
        Ventana.setIconImage(new ImageIcon(getClass().getResource("/imagenes/Icono2.png")).getImage());
    }
    
    public void ActivarVentana(JFrame ventana, JFrame ventana2){ //Inicia una ventana y cierra la otra
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        ventana2.dispose();
    }
    
    public void ActivarVentana(JFrame ventana){
         ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
    }
    
    public void IniciarVentana(JFrame ventana){
        ventana.setVisible(true);
    }
          
    public void IntercambiarPaneles (PanelFondo panelA, PanelFondo panelB, PanelFondo panelC, PanelFondo panelD, PanelFondo panelE ){
        panelA.setVisible(true);
        panelB.setVisible(false);
        panelC.setVisible(false);
        panelD.setVisible(false);
        panelE.setVisible(false);
    }
    
    public void IntercambiarPanelSesion(PanelFondo panelA, PanelFondo panelB){
       panelA.setVisible(true);
       panelB.setVisible(false); 
    }
    
    public void Visible(boolean condicion, JLabel labelCantidad, JScrollPane cantidadSeminarios, JCheckBox certificado, JScrollPane tabla){
           labelCantidad.setEnabled(condicion);
           cantidadSeminarios.setEnabled(condicion);
           labelCantidad.setVisible(condicion);
           cantidadSeminarios.setVisible(condicion);
           certificado.setVisible(condicion);
           tabla.setVisible(condicion);
                      
       
       
    }
  //Text fields  
    public void EscribirTextfield(JTextField campo, String texto){ //metodo que borrra el texto inicial en los campos de texto
        
     if(campo.getText().equals(texto))
       campo.setText(null);
    }
    public void VacioTextfield(JTextField campo, String texto){ // metodo que restaura el valor inicial si se encuentra vacio
         if(campo.getText().equals(""))
         campo.setText(texto);
    }
   public void BorrarTextfield(JTextField campo){ // metodo que coloca en null el textField
       campo.setText(null);
   }
   
  
   
    
  
//Campos
   public void RestablecerCamposBeneficiario(JTextField nombre, JTextField correo, JTextField telefono, JTextArea observaciones, JDateChooser fecha){
      nombre.setText("Nombre Completo:");
    
    correo.setText("Dirección de Correo Electrónico:");
    telefono.setText("Numero de Telefono:"); 
    observaciones.setText("");
    fecha.setDate(null);
   }
   
   public void RestablecerCamposSeminario(JTextField nombre, JDateChooser fecha){
       nombre.setText("Nombre Seminario:");
       fecha.setDate(null);
   }
   
   public void RestablecerCamposIngresoSeminario(JTextField precio,JTextField debito,JTextField efectivo, JDateChooser fecha){
    precio.setText("Precio del Seminario:");
    debito.setText("Pagos con tarjeta de débito:");
    efectivo.setText("Pagos en efectivo:");
    fecha.setDate(null);
    
   }
   
   public void RestablecerCamposIngresoSesion(JTextField debito, JTextField efectivo , JDateChooser fecha){
      debito.setText("Pagos con tarjeta de débito:");
      efectivo.setText("Pagos en efectivo:");
      fecha.setDate(null);
   }
   
   public void RestablecerCamposIngreso(JTextField efectivo, JTextField debito, JTextField razon, JDateChooser fecha){
       efectivo.setText("Monto total en efectivo:");
       debito.setText("Monto total con tarjeta de débito:");
       razon.setText("Razón o motivo del ingreso:");
       fecha.setDate(null);
   }
   
   public void RestablecerCamposEgreso(JTextField valor, JTextField razon, JDateChooser fecha){
       valor.setText("Valor del egreso:");
       razon.setText("Razón del egreso:");
       fecha.setDate(null);
   }
   
   public void RestablecerCamposEgresoMaterial(JTextField nombre , JTextField precio, JTextField cantidad , JDateChooser fecha){
     nombre.setText("Nombre del material:");
     precio.setText("Precio por unidad o paquete:");
     cantidad.setText("Cantidad de elementos adquiridos:");
     fecha.setDate(null);
   }
   //Tablas
       
    public void LlenarTablaBeneficiarios(JTable tabla, ArrayList<Beneficiario> beneficiarios ){
       
           
           for(Beneficiario beneficiario: beneficiarios){
           
            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
            String[] row = {beneficiario.getNombre(),beneficiario.getCorreo(),beneficiario.getTelefono(),sdf.format(beneficiario.getFechaIngreso()) };
            dtm.addRow(row);
            tabla.setModel(dtm);
           }
    }
    
      public void LlenarTablaBeneficiariosAsistentes(JTable tabla, ArrayList<Beneficiario> beneficiarios ){
       
           
           for(Beneficiario beneficiario: beneficiarios){
           
            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
            String[] row = {null,beneficiario.getNombre(),beneficiario.getCorreo(),beneficiario.getTelefono(),sdf.format(beneficiario.getFechaIngreso()) };
            dtm.addRow(row);
            tabla.setModel(dtm);
           }
    }
      
      
      
      public void DespejarTablaBeneficiariosCheck(JTable tabla, BeneficiariosXml datos, FechasXml datos2, String nombreEvento, String fecha, String tipoEvento ) throws ParseException{
          int x=0;
          for (int i=0; i<tabla.getRowCount();i++){ //Recorre la tabla por filas hasta el final
            
              if(tabla.getValueAt(i, 0)!=null){  //Si el checkbox es distinto de null ( es true)
                  
                  
                  x = i;
                  if(datos.buscarRaiz(tabla.getValueAt(x, 1).toString()).getChildren("Tipo").isEmpty()==true && tipoEvento.equals("Seminario"))
                  datos.convertirAlumno(tabla.getValueAt(x, 1).toString()); //Convierte al beneficiario en alumno dentro del xml
                  if(tipoEvento.equals("Seminario")){
                  datos.agregarAsistenciasToAlumno(datos2.SeminarioToObject(datos2.buscar(datos2.buscarRaiz(sdf.parse(fecha)).getChild("Seminarios").getChildren("Seminario"),nombreEvento,"NombreSeminario")),tabla.getValueAt(x, 1).toString());
                  datos2.agregarAsistenteSeminario(tabla.getValueAt(x, 1).toString(),nombreEvento, fecha);
                  }
                  else if(tipoEvento.equals("Sesion")){
                   datos.agregarAsistenciasToBeneficiario(datos2.SesionToObject(datos2.buscar(datos2.buscarRaiz(sdf.parse(fecha)).getChild("Sesiones").getChildren("Sesion"),fecha,"FechaSesion")),tabla.getValueAt(x, 1).toString());
                   datos2.agregarAsistenteSesion(tabla.getValueAt(x, 1).toString(), fecha);
                  }
                  tabla.setValueAt(null, i, 0);
                  
              }
          }  
      }
    
      public void DespejarCorreosCheck(JTable tabla){
          
          for (int i=0; i<tabla.getRowCount();i++){
              if(tabla.getValueAt(1,0)!=null){ //si el check es true
                  
                  
              }
          }
      }
    
    

    public void LlenarTablaSeminarios(JTable tabla,FechasXml datos){
        
         ArrayList<Fecha> fechas = datos.todasLasFechas();
        for(Fecha i: fechas){
           
           
             
             ArrayList<Seminario>seminarios = datos.todosLosSeminarios(datos.buscarRaiz(i.getFecha()).getChild("Seminarios"));
            
            for(Seminario j: seminarios){
               
               DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                String[] row = {j.getNombre(),sdf.format(i.fecha)};
                dtm.addRow(row);
                tabla.setModel(dtm); 
            }
            
        }
        
    }
      public void LlenarTablaSesion(JTable tabla, FechasXml datos){
          ArrayList<Fecha> fechas = datos.todasLasFechas();
          for(Fecha i: fechas){
            ArrayList<Sesion> sesiones = datos.todasLasSesiones(datos.buscarRaiz(i.getFecha()).getChild("Sesiones"));
            
            for(Sesion j: sesiones){
            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
            String[] row = {sdf.format(j.fecha)};
            dtm.addRow(row);
            tabla.setModel(dtm);
            }
        }
    }
    
    public void LlenarTablaSeminariosD(JTable tablaD, JTable tablaS){
        DefaultTableModel dtm = (DefaultTableModel) tablaD.getModel();
        
        
        
        for(int k=0; k<tablaS.getRowCount();k++){
        int j = 0;    
            
        if(tablaD.getRowCount()==0){
                    String[] row = {tablaS.getValueAt(k, 0).toString()};
                    dtm.addRow(row);
                    tablaD.setModel(dtm);  
        }
        else{
            for (int i=0;i<tablaD.getRowCount(); i++){
                
                if(!tablaD.getValueAt(i, 0).equals(tablaS.getValueAt(k, 0).toString()))                
                   j++;   
             }   
            if(j==tablaD.getRowCount()){
                 String[] row = {tablaS.getValueAt(k, 0).toString()};
                 dtm.addRow(row);
                 tablaD.setModel(dtm);
            }
        }
        }
        
    }
    
  
    
    public void LlenarTablaIngresos(JTable tabla, JTextPane panel, JTextPane saldo, FechasXml datos){
        Double ingreso = 0.0;
        ArrayList<Fecha> fechas = datos.todasLasFechas();
        for(Fecha i: fechas){
            ArrayList<Ingreso> ingresos = datos.todosLosIngresos(datos.buscarRaiz(i.getFecha()).getChild("Ingresos"));
            
            
            for(Ingreso j: ingresos){
              ingreso= ingreso + j.getTotal();
                DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                String[] row = {j.getTotal().toString(),j.getTotalDebito().toString(),j.getTotalEfectivo().toString(),j.getRazon(),sdf.format(j.getFecha())};
                dtm.addRow(row);
                tabla.setModel(dtm);  
                
            }
        }
        panel.setText(ingreso.toString());
        asociacion.saldoTotal.setIngresoTotal(ingreso);
        MostrarSaldoTotal(saldo);
       
    }
    
    public void LlenarTablaEgresos(JTable tabla, JTextPane panel, JTextPane saldo, FechasXml datos){
        Double egreso =0.0;
        
        ArrayList<Fecha> fechas = datos.todasLasFechas();
        for(Fecha i: fechas){
            ArrayList<Egreso> egresos = datos.todosLosEgresos(datos.buscarRaiz(i.getFecha()).getChild("Egresos"));
            for(Egreso j: egresos){
                egreso =  egreso + j.getValorTotal();
                DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                String[] row = {j.getValorTotal().toString(), j.getRazon(),sdf.format(j.getFecha())};
                dtm.addRow(row);
                tabla.setModel(dtm);   
            }
        }    
        panel.setText(egreso.toString());
        asociacion.saldoTotal.setEgresoTotal(egreso);
        MostrarSaldoTotal(saldo);
    }
    
    public void LlenarTablaAsistentesSeminarios(JTable tabla, FechasXml datos, String nombreSeminario, String fechaEvento) throws ParseException{
            ArrayList<String> asistentes;
            if(nombreSeminario != null)
                asistentes = datos.todosLosAsistentesSeminario(nombreSeminario, fechaEvento);
            else
                asistentes = datos.todosLosAsistentesSesion(fechaEvento);
            for(String i : asistentes){
                DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                String[] row = {i};
                dtm.addRow(row);  
                tabla.setModel(dtm);
            }
       
    }
        
    
    
    public void LlenarTablaEventosAsistidos(JTable tabla, BeneficiariosXml datos, String nombreBeneficiario, String evento){
        ArrayList<String> eventos = null ;
        int cantidad=0;
        if(evento.equals("Seminario"))
            eventos = datos.todasLasAsistenciasSeminarios(nombreBeneficiario);
        else if(evento.equals("Sesion"))
            eventos = datos.todasLasAsistenciasSesiones(nombreBeneficiario);
        
        for(String i: eventos){
                DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                String[] row = {i};
                dtm.addRow(row);
                tabla.setModel(dtm);
                if(evento.equals("Seminario"))
                cantidad=cantidad+1;
        }
       
        if(evento.equals("Seminario")){
            if(cantidad>=12 && datos.buscarRaiz(nombreBeneficiario).getChildText("Certificado").equals("false"))
                datos.buscarRaiz(nombreBeneficiario).getChild("Certificado").setText("true");
            datos.buscarRaiz(nombreBeneficiario).getChild("NumeroSeminarios").setText(Integer.toString(cantidad));
            datos.updateDocument();
        }
    }
    public void LlenarTablaAlumnos(JTable tabla, BeneficiariosXml datos){
        ArrayList<Alumno> alumnos = datos.todosLosAlumnos();
        
         for(Alumno i: alumnos){
           
            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
            String[] row = {i.getNombre(),i.getTelefono(),i.getCorreo(),sdf.format(i.getFechaIngreso()) };
            dtm.addRow(row);
            tabla.setModel(dtm);
           }
        
    }
    
    public void FiltrarTabla(JTable tabla, String buscar,int columna, String tipo){
        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        TableRowSorter<TableModel> filtrar = new TableRowSorter<TableModel>(dtm);
        if(tipo.equals("string"))
        filtrar.setRowFilter(RowFilter.regexFilter("(?i)"+buscar, columna)); // el cero es el numero de la columna
        else if(tipo.equals("fecha"))
        filtrar.setRowFilter(RowFilter.regexFilter(buscar, columna)); // el cero es el numero de la columna   
        tabla.setRowSorter(filtrar);
        
        
    }
    
    public void ModificarTabla(JTable tabla, JToggleButton boton, JButton eliminar){
        
         if(boton.isSelected()){
            tabla.setEnabled(true);
            eliminar.setEnabled(true);
         }
            
        else{
            if(tabla.isEditing()==true)
            tabla.getCellEditor().stopCellEditing();
            tabla.clearSelection();
            tabla.setEnabled(false);
            eliminar.setEnabled(false);
            }
    }
    public void EliminarFila(JTable tabla){
         if( tabla.getSelectedRows().length > 0 ) {
        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel(); //TableProducto es el nombre de mi tabla ;) 
        dtm.removeRow(tabla.getSelectedRow()); 
         }
    }
    
   //Listas:
    
    public void ActualizarListaFecha(Date fecha,FechasXml datos){ //Crea el objeto tipo Fecha en la lista fechas
       
       if (fecha != null){
         
           Fecha fec = new Fecha(fecha);
           datos.agregarFecha(fec);
       
          
       }
    }
     
    public void ActualizarListaBeneficiarios(Beneficiario beneficiario){
         asociacion.beneficiarios.add(beneficiario);
          JOptionPane.showMessageDialog(null, "Beneficiario Registrado exitosamente!.");
    }
    
    
    public void ActualizarListaSeminario(JTextField nombre, Date fecha, FechasXml datos){ //Actualiza la Lista seminarios del objeto asociacion
                 Seminario seminario = new Seminario(ValidarMayusculas(nombre.getText()));
                 if(datos.agregarSeminario(seminario,fecha)==true)
                    JOptionPane.showMessageDialog(null, "Seminario registrado satisfactoriamente.");
                 else
                     JOptionPane.showMessageDialog(null, "Seminario Existente. No se ha registrado");
        

            
    }   
    
     public boolean ActualizarListaSesion(Date fecha, FechasXml datos){
         boolean x;
         if(fecha!=null){
            Sesion sesion = new Sesion(fecha);
            x=datos.agregarSesion(sesion, fecha);
            if(x==true)
                JOptionPane.showMessageDialog(null, "Sesion registrada exitosamente!");
            return x;
           
         }
         else
             return false;
     }
     
     public void ActualizarListaIngresos( Ingreso ingreso, Date fecha, FechasXml datos ){
       datos.agregarIngreso(ingreso, fecha);
        JOptionPane.showMessageDialog(null, "Ingreso registrado exitosamente!");
        
     }
     
     public void ActualizarListaEgresos(Egreso egreso, Date fecha , FechasXml datos){
        datos.agregarEgreso(egreso, fecha);
         JOptionPane.showMessageDialog(null, "Egreso registrado satisfactoriamente.");
     }
     
     public void eliminarListaSeminario(String nombre){ //sin uso
         for(Fecha i : asociacion.fechas){
             for(Seminario j: i.seminarios){
                 if(j.getNombre().equals(nombre))
                     i.seminarios.remove(j);
                 
             }
             
         }
      }   
     
     
     public void toggleAsociacion(JTextPane nombre,JTextPane telefono, JTextPane direccion, JTextPane rif, JToggleButton boton, JButton boton2){
         if(boton.isSelected()){
            nombre.setEditable(true);
            direccion.setEditable(true);
            telefono.setEditable(true);
            rif.setEditable(true);
            boton2.setVisible(true);
            boton2.setEnabled(true);
         }
         else{
            nombre.setEditable(false);
            direccion.setEditable(false);
            telefono.setEditable(false);
            rif.setEditable(false);
            boton2.setVisible(false);
            boton2.setEnabled(false);
         } 
     }  
     
     public void MostrarDatosPersona(JTextPane nombre, JTextPane telefono, JTextPane correo,JTextArea observaciones,JTextPane fechaIngreso,JTextPane cantidadSeminarios,JCheckBox certificado, BeneficiariosXml datos, String nombrePer){
         Beneficiario persona = datos.buscarBeneficiario(nombrePer);
         nombre.setText(persona.getNombre());
         telefono.setText(persona.getTelefono());
         correo.setText(persona.getCorreo());
         observaciones.setText(persona.getObservaciones());
         fechaIngreso.setText(sdf.format(persona.getFechaIngreso()));
         if(persona instanceof Alumno){
         cantidadSeminarios.setText(Integer.toString(((Alumno) persona).getNumeroSeminarios()));
         if(((Alumno) persona).isCertificado())
         certificado.setSelected(true);
         
            
         }
         
     }
    
     
    //Mostrar
     public void MostrarSaldoTotal(JTextPane panel){
       panel.setText(asociacion.saldoTotal.calcularSaldoTotal().toString());
         
     }
     
     public void MostrarInformacion(JTextPane nombre, JTextPane telefono, JTextPane direccion, JTextPane rif,Element raiz){
         nombre.setText(raiz.getChildText("Nombre"));
         telefono.setText(raiz.getChildText("Telefono"));
         direccion.setText(raiz.getChildText("Direccion"));
         rif.setText(raiz.getChildText("Rif"));
     }
    // Verificaciones:
    public boolean ComprobarFecha(Date fecha , FechasXml datos){ //Verifica si el objeto fecha existe, y si no devuelve falso
        
        boolean x=false;
         if(datos.buscarFecha(sdf.format(fecha))!=null){
           x=true;
         }
        return(x);
    }
    
    public boolean ComprobarAlumno(BeneficiariosXml datos, String nombre){
        boolean x=false;
        if(!datos.buscarRaiz(nombre).getChildren("Tipo").isEmpty() && datos.buscarRaiz(nombre).getChildText("Tipo").equals("Alumno"))
            x=true;
        return x;
    }
    
    
        
        //Musica
    public void IniciarMusica (){
      sonido = java.applet.Applet.newAudioClip(getClass().getResource("/Musica/musica.wav"));
      sonido.play(); 
     
    }
    
    public void DetenerMusica(){
        sonido.stop();
    }
    
    //Cursor
    public void IniciarCursor (JFrame ventana){
   
    imagenCursor = new ImageIcon(getClass().getResource("/imagenes/Cursor.png"));
    TK = Toolkit.getDefaultToolkit();
    this.cursor = TK.createCustomCursor(imagenCursor.getImage(), new Point(1,1) , "Cursor");
    ventana.setCursor(cursor);
        
    }
        
    
    public void GenerarListaCorreos( JTable tabla, BeneficiariosXml datos, String tipo){
         File ruta_destino =null;
         ruta_destino = pdf.Colocar_Destino(ruta_destino);
         String  titulo;
         
         if(tipo.equals("Beneficiarios"))
             titulo="Corrreo de Beneficiarios:";
         
         else
             titulo="Correos de los Asistentes:";
         
        try {      
            
            PdfWriter writer = PdfWriter.getInstance(listaCorreos, new FileOutputStream(ruta_destino + ".pdf"));
            if(ruta_destino!=null){
            listaCorreos.open();
            pdf.CrearTituloPDF(listaCorreos, titulo);
            pdf.crearListaCorreosPDF(listaCorreos, tabla, tipo, datos);
            listaCorreos.close();
            
            JOptionPane.showMessageDialog(null, "Archivo con la lista de correos generado exitosamente!");
            AbrirPDF(ruta_destino);
            }
        } catch (DocumentException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void GenerarPDFBeneficiario(Document documento, File ruta_destino, CrearPDF pdf, JTextPane nombre,JTextPane telefono,JTextPane correo,JTextPane fechaIngreso, JTextArea observaciones, JCheckBox certificado, JTextPane cantidadSeminarios, JTable tablaSeminarios, JTable tablaSesiones){
        try {
            
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(ruta_destino + ".pdf"));
            if(ruta_destino!=null){
            documento.open();
            pdf.crearLogoPDF(documento);
            pdf.CrearTituloPDF(documento, "Reporte Beneficiario");
            pdf.CrearInformacionPersonaPDF(documento, nombre.getText(), correo.getText(), telefono.getText(), fechaIngreso.getText(), observaciones.getText(), certificado.isSelected(), cantidadSeminarios.getText());
            pdf.CargarTablasPdf(documento, pdf.ConvertirTabla(tablaSeminarios, tablaSeminarios.getColumnCount()),pdf.ConvertirTabla(tablaSesiones, tablaSesiones.getColumnCount()), ruta_destino);
            documento.close();
            JOptionPane.showMessageDialog(null, "Archivo generado exitosamente!");
            AbrirPDF(ruta_destino);
            }
            
        } catch (DocumentException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void AbrirPDF(File ruta_destino){
        try {
            File path = new File(ruta_destino + ".pdf");
            Desktop.getDesktop().open(path);
            
            
        } catch (IOException ex) {
            Logger.getLogger(Controladora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  // Validaciones al registrar
        public boolean ValidarRegistroIngreso (JFrame ventana, JTextField totalEfectivo, JTextField totalDebito, JTextField razon, JDateChooser fecha){
            if(totalEfectivo.getText().equals("Monto total en efectivo:") || totalDebito.getText().equals("Monto total con tarjeta de débito:") || razon.getText().equals("Razón o motivo del ingreso:"))
                return false;
            else
                return true;
    }
    
    public boolean ValidarRegistroBeneficiario(JFrame ventana, JTextField nombreBeneficiario, JTextField telefonoBeneficiario, JTextField correoBeneficiario, JDateChooser fechaRegistro){
        if(nombreBeneficiario.getText().equals("Nombre Completo:") || telefonoBeneficiario.getText().equals("Numero de Telefono:") || correoBeneficiario.getText().equals("Dirección de Correo Electrónico:"))
            return false;
        else
            return true;
         
    }
    
    public boolean ValidarIngresoSesion(JFrame ventana, JTextField debito, JTextField efectivo, JDateChooser fecha){
        if(debito.getText().equals("Pagos con tarjeta de débito:") || efectivo.getText().equals("Pagos en efectivo:"))
            return false;
        else
            return true;
    }
    
    public boolean ValidarIngresoSeminario(JFrame ventana, JTextField precio, JTextField debito, JTextField efectivo, JDateChooser fecha){
        if(precio.getText().equals("Precio del Seminario:") || debito.getText().equals("Pagos con tarjeta de débito:") || efectivo.getText().equals("Pagos en efectivo:")) 
            return false;
        else
            return true;
    }
    
    public boolean ValidarEgresoMaterial(JFrame ventana, JTextField nombre, JTextField precio, JTextField cantidad, JDateChooser fecha){
    if(nombre.getText().equals("Nombre del material:") || precio.getText().equals("Precio por unidad o paquete:") || cantidad.getText().equals("Cantidad de elementos adquiridos:")) 
        return false;
    else
        return true;
       
    }
    
    public boolean ValidarEgreso(JFrame ventana, JTextField valor, JTextField razon, JDateChooser fecha){
    if((valor.getText().equals("Valor del egreso:")|| valor.getText().equals("")) || (razon.getText().equals("Razón del egreso:")|| razon.getText().equals("")))
        return false;
    else 
        return true;
    }
    
    
    
    public boolean ValidarSeminario(JFrame ventana, JTextField nombreSeminario){
        if(nombreSeminario.getText().equals("Nombre Seminario:"))
            return false;
        else
            return true;
        
    }
    //Validar datos introducidos:
    
    public void ValidarLetras(java.awt.event.KeyEvent evt){
    int k=(int)evt.getKeyChar();
       if ((k < 97 || k > 122) && (k<65 || k>90) && k!=20 && k!=8 && k!=32 && k!=127 && k==239 && k==249 && k==128)
       {
          evt.setKeyChar((char)KeyEvent.VK_CLEAR);
          JOptionPane.showMessageDialog(null,"Sólo debe ingresar letras","Error Datos",JOptionPane.ERROR_MESSAGE);
       }
    }
    
  
    public void ValidarEntero(java.awt.event.KeyEvent evt){
        int k=(int)evt.getKeyChar();
       if(((k < '0') || (k > '9')) && (k != KeyEvent.VK_BACK_SPACE) && k!=127)
       {
          evt.setKeyChar((char)KeyEvent.VK_CLEAR);
          JOptionPane.showMessageDialog(null,"Sólo debe ingresar números.","Error Datos",JOptionPane.ERROR_MESSAGE);
       }
    }
  
    public boolean ValidarFecha(JDateChooser campo){
      if(campo.getDate()==null){
         JOptionPane.showMessageDialog(null,"El campo fecha se encuentra vacio.","Error Datos",JOptionPane.ERROR_MESSAGE);
         return(true);
      }
      else
          return(false);
  }
         

   
    public void ValidarDouble(java.awt.event.KeyEvent evt){
        int k=(int)evt.getKeyChar();
       if(((k < '0') || (k > '9')) && (k != KeyEvent.VK_BACK_SPACE) && (k !='.') && k!=127){
       {
          evt.setKeyChar((char)KeyEvent.VK_CLEAR);
          JOptionPane.showMessageDialog(null,"Sólo se puede ingresar números o .","Error Datos",JOptionPane.ERROR_MESSAGE);
       }
    }
  }
  
    public String ValidarMayusculas(String nombre){
        
        char[] modificado = nombre.toCharArray();
        modificado[0] = Character.toUpperCase(modificado[0]);
        for (int i=0; i<nombre.length()-2; i++){
            if (modificado[i] == ' ')
                 modificado[i + 1] = Character.toUpperCase(modificado[i + 1]); 
        }
        
        String retorno = new String(modificado);
        return retorno;
  }
  
    public boolean validarEmail(String correo){   
        String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
        
    }
    public boolean validarTelefono(String numero){   
    String PATTERN_NUMBER = "^([0212||0424||0414||0416||0412||0426]{4})([0-9]{7})$";
                    

        Pattern pattern = Pattern.compile(PATTERN_NUMBER);
        Matcher matcher = pattern.matcher(numero);
        return matcher.matches();
    }
    
        public boolean validarNombres(String name){   
        String PATTERN_NAME = "^([A-Za-z ]{1,50})$";
        
        Pattern pattern = Pattern.compile(PATTERN_NAME);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
        
    }
    

        
    public void CalcularIngresoEgresoMes(JTable tabla, JTextPane panel){
         Double valor=0.0;
         for(int i=0;i<tabla.getRowCount();i++){
             valor=valor+(Double.parseDouble(tabla.getValueAt(i,0).toString()));
         }
         
         panel.setText(valor.toString());
     }
     
     //Modificar Datos
    public void TraerDatosBeneficiario(JTextField nombreBeneficiario,JTextField telefonoBeneficiario, JTextField correoBeneficiario, JDateChooser fechaRegistro,JTextArea observacionesBeneficiario,String nombre, BeneficiariosXml datos, JButton registrar){
        Beneficiario beneficiario = datos.buscarBeneficiario(nombre);
        nombreBeneficiario.setText(beneficiario.getNombre());
        telefonoBeneficiario.setText(beneficiario.getTelefono());
        correoBeneficiario.setText(beneficiario.getCorreo());
        observacionesBeneficiario.setText(beneficiario.getObservaciones());
        activarDesactivar(false, registrar);
        fechaRegistro.setEnabled(false);
        
     }
     
    public void EliminarBeneficiario(String nombre){
         BeneficiariosXml datos = new BeneficiariosXml();
         datos.borrarBeneficiario(nombre);
     }
     
    public void GuardarBeneficiario(String nombre, JTextField nombreBeneficiario, JTextField telefonoBeneficiario, JTextField correoBeneficiario, JDateChooser fechaRegistro, JTextArea observacionesBeneficiario, BeneficiariosXml datos){
         Beneficiario temporal=null; 
         if(!correoBeneficiario.getText().equals("Dirección de Correo Electrónico:")){ //Si el correo tiene una direccion
            
            
             temporal= new Beneficiario(ValidarMayusculas(nombreBeneficiario.getText()), telefonoBeneficiario.getText(),correoBeneficiario.getText() ,observacionesBeneficiario.getText() ,null); 
         }
         else{
              JOptionPane.showMessageDialog(null, "Ingrese una dirección de correo");
              correoBeneficiario.grabFocus();
             }
         
         datos.ActualizarBeneficiario(temporal,nombre );
         
    }
     
    public void TraerDatosSeminario(JTextField nombreSeminario, JDateChooser fechaSeminario, FechasXml datos, String nombreSem, String fecha){
       Seminario sem = datos.buscarSeminario(fecha, nombreSem);
       nombreSeminario.setText(sem.getNombre());
       fechaSeminario.setEnabled(false);
       seminarioTemp=nombreSem;
       fechaSemTemp=fecha;
    }
    public void GuardarSeminario( JTextField nombreSeminario, JDateChooser fechaSeminario, FechasXml datos2 , BeneficiariosXml datos1){
       Seminario temporal = new Seminario (nombreSeminario.getText());
       datos2.ActualizarSeminario(temporal, seminarioTemp, fechaSemTemp);
       
       
       datos1.ActualizarAsistenciasSeminario(temporal.getNombre(), seminarioTemp);
    }
    public void EliminarSeminario(String nombre, String fecha, FechasXml datos){
      
       datos.borrarSeminario(nombre, fecha);
     } 
    
    public void EliminarIngresoEgreso(String cantidad, String fecha, FechasXml datos, String tipo){
       if(tipo.equals("Ingreso"))
           datos.borrarIngresoEgreso(cantidad, fecha, "Ingresos","Ingreso");
       
           
       else if(tipo.equals("Egreso"))
           datos.borrarIngresoEgreso(cantidad, fecha,"Egresos", "Egreso");
    }
    
    public void EliminarOtroIngresoEgreso(String razon, String fecha,FechasXml datos, String tipo){
         if(tipo.equals("Ingreso"))
           datos.borrarOtroIngresoEgreso(razon, fecha, "Ingresos","Ingreso","RazonIngreso");
       
           
       else if(tipo.equals("Egreso"))
           datos.borrarOtroIngresoEgreso(razon, fecha,"Egresos", "Egreso","RazonEgreso");
    }
    
    public void EliminarSesion(String fecha, FechasXml datos){
        datos.borrarSesion(fecha);
    }
    
    public  void activarDesactivar(boolean verdadFalso,JButton RegistrarE){  
        RegistrarE.setEnabled(verdadFalso);
    }
    
    public String ObtenerUsuario(DatosAsociacionXml datos){
        return datos.buscarRaiz().getChildText("NombreUsuario");
        
      
    }
    public String ObtenerContraseña(DatosAsociacionXml datos){
        return datos.buscarRaiz().getChildText("Contraseña");
    }
    
    public void TraerDatosUsuario(JTextField usuario, JTextField contraseña){
        DatosAsociacionXml datos = new DatosAsociacionXml();
        usuario.setText(datos.buscarRaiz().getChildText("NombreUsuario"));
        contraseña.setText(datos.buscarRaiz().getChildText("Contraseña"));
    }
    
    public void GuardarUsuario(JTextField usuario, JTextField contraseña){
        DatosAsociacionXml datos = new DatosAsociacionXml();
        datos.buscarRaiz().getChild("NombreUsuario").setText(usuario.getText());
        datos.buscarRaiz().getChild("Contraseña").setText(contraseña.getText());
        datos.updateDocument();
    }
    
         
}
   






   




