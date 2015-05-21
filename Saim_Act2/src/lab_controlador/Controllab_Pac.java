/**
 * controlador: Controllab_Pac se encuentra el proceso para  
 * la actualizacion e insercion de los datos del paciente(modulo individual)
*/
package lab_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import lab_logica.MetodolabPa;
import lab_logica.lab_IngresoPac;

/**
 * Servlet implementation class Controllab_Pac
 */
public class Controllab_Pac extends HttpServlet {
	//private static final long serialVersionUID = 1L;
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cedula,nombre,edad,telefono,email,apellidos,genero,tipo,direccion,eps,fechana,codigo = null;
		response.setContentType("text/html;charset=UTF-8");
		java.sql.ResultSet rs1=null;
		
		cedula=request.getParameter("ced");
		nombre=request.getParameter("nom");
		edad=request.getParameter("eda");
		telefono=request.getParameter("tele");
		email=request.getParameter("ema");
		apellidos=request.getParameter("ape");
		genero=request.getParameter("gene");
		tipo=request.getParameter("ti");
		direccion=request.getParameter("dire");
		eps=request.getParameter("ep");
		
		fechana=request.getParameter("fechana");
		
		int k;
		for(k=0;k<nombre.length();k++){
			 nombre=nombre.replace('|','Ñ');  
		    }
		
		int h;
		for(h=0;h<apellidos.length();h++){
			apellidos=apellidos.replace('|','Ñ');  
		    }
		
		
		
		lab_IngresoPac ingresopac=new lab_IngresoPac();
		ingresopac.insertar(nombre,genero,cedula,eps,edad,telefono,direccion,email,apellidos,tipo,fechana);
		
		for(k=0;k<nombre.length();k++){
			 nombre=nombre.replace('Ñ','N');  
		    }
		
		int P;
		for(P=0;P<apellidos.length();P++){
			apellidos=apellidos.replace('Ñ','N');  
		    }
		String nombrecompleto=nombre+" "+apellidos;
		
        MetodolabPa labpa=new MetodolabPa();
		
		rs1=(java.sql.ResultSet) labpa.SQLlabPac(cedula, tipo);
		
		try {
			if(rs1.next()){
				codigo=rs1.getString(3);
			}
			
			
			
			response.sendRedirect("lab_Realizar_Examen.jsp?cod="+codigo+"&id="+1+"&nombre="+nombrecompleto+"&numero="+cedula+"&tipo="+tipo+"&z="+1+"&gene="+rs1.getString(4)+"&edad="+rs1.getString(5)+"");
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String yo=request.getParameter("yo");
		
		if(yo.equals("1")){
			String ced=request.getParameter("cedula");
			String ti=request.getParameter("tipo");
			
			MetodolabPa pa=new MetodolabPa();
			java.sql.ResultSet rs=null;
			rs=pa.Buscarcodpa(ced,ti);
			
			String codigo="";
			String nombre="";
			String genero="";
			String edad="";
			String telefono="";
			String direccion="";
			String apellidos="";
			String fecha_nacimiento="";
			PrintWriter pri=response.getWriter();
			
			try {
				if(rs.next()){
					codigo=rs.getString(1);
					nombre=rs.getString(2);
					genero=rs.getString(3);
					edad=rs.getString(4);
					telefono=rs.getString(5);
					direccion=rs.getString(6);
					apellidos=rs.getString(7);
					fecha_nacimiento=rs.getString(8);
				}
				
				if(codigo.equals("")){
					pri.print("Dato no Encontrado...!");
				}else{
					pri.print("<table width='100%' border='0'><tr><td colspan='4' id='cabecera2'><div align='center' class='style11'>DATOS DEL PACIENTE</div></td></tr><tr><td>&nbsp;</td></tr>");
                    pri.print("<tr class='style6'><td>Nombre:</td><td><label><input name='txtnom' type='text' id='txtnom' size='40' value='"+nombre+"' ></label></td><td>Apellidos:</td><td><input name='txtape' type='text' id='txtape' value='"+apellidos+"' size='50'></td></tr>");
                    pri.print("<tr class='style6'><td>Tipo Doc:</td><td><label><select name='cbtipo' id='cbtipo'><option selected>"+ti+"</option><option>CC</option><option>TI</option><option>RC</option><option>Nacido Vivo</option></select></label></td>");
                    pri.print("<td class='style6'>Cedula:</td><td><label><input name='txtced' type='text' id='txtced' value='"+ced+"'></label></td></tr>");
                    pri.print("<tr class='style6'><td>Edad:</td><td><input name='txtedad' type='text' id='txtedad' value='"+edad+"'></td><td>Direccion:</td><td><input name='txtdire' type='text' id='txtdire' value='"+direccion+"' size='50'></td></tr>");
                    pri.print("<tr class='style6'><td>Genero:</td><td><select name='cbgenero' id='cbgenero'><option value='"+genero+"' selected>"+genero+"</option><option value='Masculino'>Masculino</option><option value='Femenino'>Femenino</option></select></td><td>Telefono:</td><td><input name='txttelefono' type='text' id='txttelefono' value='"+telefono+"' ><input name='txtcod' type='hidden' id='txtcod' value='"+codigo+"' ></td></tr>");
                    pri.print("<tr class='style6'><td>Fecha Na: (aaaa-mm-dd) </td><td><label><input name='txtfecha' type='text' id='txtfecha' value='"+fecha_nacimiento+"'></label></td><td>&nbsp;</td><td><input name='btactulizar' type='button' class='boton4' id='btactulizar' onClick='actualizar();' value='Actualizar'></td></tr></table>");
 
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(yo.equals("2")){
			String ced=request.getParameter("cedula");
			String ti=request.getParameter("tipo");
			String nombre=request.getParameter("nombre");
			String apellido=request.getParameter("apellido");
			String edad=request.getParameter("edad");
			String direccion=request.getParameter("direccion");
			String telefono=request.getParameter("telefono");
			String genero=request.getParameter("genero");
			String codigo=request.getParameter("codigo");
			String fecha=request.getParameter("fecha");
			PrintWriter pri=response.getWriter();
			
			try {
			MetodolabPa pa=new MetodolabPa();
			
			pa.ActualizarPaciente(codigo, nombre, genero, ced, edad, telefono, direccion, apellido, ti, fecha);
			
			pri.print("Actualizacion exitosa..!!!");
			
			} catch (Exception e) {
				pri.print("Error al actualizar ");
				pri.print(" ...Verifique que los datos sean correctos");
			}
			
		}
	}
}
