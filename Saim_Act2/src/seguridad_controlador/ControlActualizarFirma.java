package seguridad_controlador;

import java.awt.Image;
import java.io.*;

import java.util.Iterator;
import java.util.List;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
//import org.apache.commons.io.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import seguridad_bean.Usuario;
import seguridad_logica.MetodoOpcionesAutorizadas;
import seguridad_logica.Metodo_Usuario;

/**
 * Servlet implementation class Yosi
 */
public class ControlActualizarFirma extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Object logger;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	
		
		
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("control af post");
		//String CodUsuario=req.getParameter("CodUsuario");
		String idProducto = "0";
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		//String destination = "C:\\Saim\\WebContent\\Imagenes\\firma";
		String destination = "C:\\Firma-Saim";
		DiskFileItemFactory factory1 = new DiskFileItemFactory();
		FileItemFactory factory = new DiskFileItemFactory();	
		//factory1.setSizeThreshold( 1024 );
		factory1.setRepository( new File( destination ) );
		ServletFileUpload upload = new ServletFileUpload(factory);	
		ServletFileUpload uploader = new ServletFileUpload( factory1 );
		List items = null;
		List items1 = null;
		
	
		 
		   try {
	        	
				items = upload.parseRequest(req);
				items1 = uploader.parseRequest(req);
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
	       
			Iterator itr = items.iterator();
			while(itr.hasNext())  {
				FileItem item = (FileItem) itr.next();
				
	            
				
				if(item.isFormField()) { 
					String fieldName = item.getFieldName();
					if(fieldName.equals("CodUsuario")){      
						idProducto = item.getString();
						System.out.println("este es el id1 "+idProducto);
					}
				}
			}
			itr = items.iterator();	
		
		
		 
		while(itr.hasNext()) {
			FileItem item = (FileItem) itr.next(); 
			System.out.println("este es el id222 "+idProducto);
			
			if(! item.isFormField() ){ 
				try {
					Metodo_Usuario mu=new Metodo_Usuario();
					System.out.println("este es el id33333"+idProducto);
					//mu.InsertarFirmaUsuario(item.getInputStream(),item.getSize(), CodUsuario);
					mu.InsertarFirmaUsuario(item.getInputStream(),item.getSize(), idProducto);
					//res.sendRedirect("seg_IngresarFirmausuario.jsp");
					String documento="";
					ResultSet rs=null;
					rs= mu.numerodocumento(idProducto);
					if(rs.next()){
						documento =rs.getString(1);
					}
					//System.out.println("firma actualizada "+documento);
					res.sendRedirect("Seg_Creacion.jsp?documento="+documento);
					 
				} catch (Exception e) {
					e.printStackTrace();
				}   
				
			}
		}
		
		
		//upload al servidor
		
		 try
	        {
				System.out.println("este es el id44444"+idProducto);
	                Iterator iterator = items.iterator();
	               
	                while( iterator.hasNext() )
	                {
						System.out.println("este es el id55555"+idProducto);
	                        FileItem item = (FileItem) iterator.next();
	                        	//System.out.println("este es el id"+idProducto);
	                       // iterator = items.iterator();	
	                        System.out.println("este es el id666"+idProducto);
	                        String nombre= item.getName();
	                       String extension = nombre.substring( nombre.lastIndexOf( "."));
	                        System.out.println("ext "+nombre);
	                        File file = new File( destination, idProducto+".jpg" );
	                        System.out.println("este es el id777"+idProducto);
	                        item.write( file );
	                       
	                        
	                }
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
		
		//fin upload servidor
		
		
		
	}//			
}
