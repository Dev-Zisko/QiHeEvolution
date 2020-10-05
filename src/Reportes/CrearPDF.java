/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;
import Persistencia.BeneficiariosXml;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Christian D
 */
public class CrearPDF {
   
     
     public  PdfPTable ConvertirTabla (JTable tabla, int columnas){
         PdfPTable tablaPdf = new PdfPTable(columnas);
         
         for(int i=0;i<tabla.getRowCount();i++){
             
             for(int j=0;j<tabla.getColumnCount();j++){
                 PdfPCell celda = new PdfPCell( new Phrase((tabla.getValueAt(i, j).toString())) ) ;
                 celda.setColspan(i); // an entire row
                 tablaPdf.addCell(celda);  
             }
             
         }
         
         return tablaPdf;
     }
     
     
     
     public void CargarTablasPdf(Document documento,PdfPTable tablaSeminarios, PdfPTable tablaSesiones, File Destino) {
         PdfPCell celda;

        try {
            PdfPTable tituloSeminario = new PdfPTable(1);
            celda = new PdfPCell( new Phrase("Seminarios: " )) ;
            celda.setBackgroundColor(BaseColor.GREEN);
            tituloSeminario.addCell(celda);
            
            PdfPTable tituloSesion = new PdfPTable(1);
            celda = new PdfPCell( new Phrase("Sesiones: " )) ;
            celda.setBackgroundColor(BaseColor.GREEN);
            tituloSesion.addCell(celda);
            documento.add(new Phrase(Chunk.NEWLINE));
            documento.add(tituloSeminario);
            documento.add(tablaSeminarios);
            documento.add(new Phrase(Chunk.NEWLINE));
            documento.add(tituloSesion);
            documento.add(tablaSesiones);

        } catch (DocumentException ex) {
            ex.getMessage();
        }

       
         
    }
    
   public void CrearTituloPDF(Document documento, String titulo) throws DocumentException{
        
       documento.add(new Paragraph(titulo,
				FontFactory.getFont("arial",   // fuente
				18,                            // tamaño
				Font.ITALIC,                   // estilo
				BaseColor.BLACK)));             // color
      documento.add(new Paragraph((new Phrase(Chunk.NEWLINE))));
    
   }
   
   
   public void CrearInformacionPersonaPDF(Document documento, String nombre, String correo, String telefono, String fechaIngreso, String observaciones, Boolean certificado, String cantidadSeminarios) throws DocumentException{
       Paragraph parrafo = new Paragraph();
       parrafo.add(new Phrase("Nombre: "+nombre));
       parrafo.add(new Phrase(Chunk.NEWLINE));
       parrafo.add(new Phrase("Telefono: "+telefono));
       parrafo.add(new Phrase(Chunk.NEWLINE));
       parrafo.add(new Phrase("Correo: "+correo));
       parrafo.add(new Phrase(Chunk.NEWLINE));
       parrafo.add(new Phrase("Fecha de Ingreso: "+fechaIngreso));
       parrafo.add(new Phrase(Chunk.NEWLINE));
       if(observaciones.equals(""))
       observaciones= "Ninguna";   
       parrafo.add(new Phrase("Observaciones: "+observaciones));
       parrafo.add(new Phrase(Chunk.NEWLINE));
       System.out.println(cantidadSeminarios);
       if(!cantidadSeminarios.equals("")){
            parrafo.add(new Phrase("Cantidad de Seminarios: "+cantidadSeminarios));
            parrafo.add(new Phrase(Chunk.NEWLINE));
            if(certificado==true)
            parrafo.add(new Phrase("Certificado: si tiene. "));
            else
            parrafo.add(new Phrase("Certificado: no tiene. "));
            
            parrafo.add(new Phrase(Chunk.NEWLINE));
       }
       
        documento.add(parrafo);
       
   }
   
   public void crearListaCorreosPDF(Document documento ,JTable tabla, String tipo, BeneficiariosXml datos) throws DocumentException{
        Paragraph parrafo = new Paragraph();
        for(int i =0; i<tabla.getRowCount();i++){
            
            if(tipo.equals("Asistentes")){
                 parrafo.add(new Phrase(datos.buscarBeneficiario(tabla.getValueAt(i, 0).toString()).getCorreo()));
                 parrafo.add(new Phrase(Chunk.NEWLINE)); 
                 
            }
            else if(tipo.equals("Beneficiarios")){
                   
                 parrafo.add(new Phrase(tabla.getValueAt(i, 1).toString()));
                 parrafo.add(new Phrase(Chunk.NEWLINE)); 
                    
                }
           
            }
        
             documento.add(parrafo);
                  
            
        
       
   }
   
   public void crearLogoPDF(Document documento){
       try{
       
	Image foto = Image.getInstance("src/imagenes/Icono2.png");
	foto.scaleToFit(100, 100);
	foto.setAlignment(Chunk.ALIGN_MIDDLE);
	documento.add(foto);
       
        }
       catch ( Exception e ){
	e.printStackTrace();
}
   }
     
   
     
    public File Colocar_Destino(File ruta_destino) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF", "pdf", "PDF");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            ruta_destino = fileChooser.getSelectedFile().getAbsoluteFile();
        }
        if(result ==JFileChooser.CANCEL_OPTION)
            ruta_destino = null;
        return ruta_destino;
    }
    
}
