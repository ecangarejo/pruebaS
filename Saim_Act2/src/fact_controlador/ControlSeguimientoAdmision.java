package fact_controlador;

import hic_metodo.MetodoMultiplePacientes;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;

public class ControlSeguimientoAdmision extends HttpServlet {

	/**
	 * Se guarda y consulta todo lo relacionado con una admision.
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		String codIma_fk=req.getParameter("codIma_fk");
		String codExa_fk=req.getParameter("codExa_fk");
		String codMedicamento_fk=req.getParameter("codMedicamento_fk");
		String codAdm_fk=req.getParameter("codAdm_fk");
		String codPac_fk=req.getParameter("codPac_fk");
		String codUsu_fk=req.getParameter("codUsu_fk");
		String hora=req.getParameter("hora");
		String fecha=req.getParameter("fecha");
		
		String NomPac = req.getParameter("NomPac");
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;	
		ResultSet rs5=null;
		ResultSet rs6=null;
		ResultSet rs7=null;
		ResultSet rs8=null;
		MetodoMultiplePacientes mmp=new MetodoMultiplePacientes();
		
		
		/*if(va.equals("1")){
			int Cont=0;
			out.print("<table width='100%' border='1'>");
			String validar="";
			try {
				rs3=mmp.CargarlistarPacientesPorAtender();
				while(rs3.next()){
					validar=rs3.getString(2);
					out.print("<tr><td width='87%'>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(2)+"</td><td width='13%'><input name='chkPac' type='checkbox' id='chkPac' value='"+rs3.getString(1)+"' /></td></tr>");
					Cont=Cont+1;
					
				}
				out.print("<input name='txtCont' type='hidden' id='txtCont' value='"+Cont+"' />");
				if(validar==""){
					out.print("No Existe Registro de Este Paciente.");
				}
				rs3.getStatement().getConnection().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			out.print("</table>");
		}*/
		
		/*if(va.equals("1.1")){
			int Cont=0;
			out.print("<table width='100%' border='1'>");
			String validar="";
			try {
				rs3=mmp.listarPacientesPorAtender(NomPac);
				while(rs3.next()){
					validar=rs3.getString(2);
					out.print("<tr><td width='87%'>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(2)+"</td><td width='13%'><input name='chkPac' type='checkbox' id='chkPac' value='"+rs3.getString(1)+"' /></td></tr>");
					Cont=Cont+1;
					
				}
				out.print("<input name='txtCont' type='hidden' id='txtCont' value='"+Cont+"' />");
				if(validar==""){
					out.print("No Existe Registro de Este Paciente.");
				}
				rs3.getStatement().getConnection().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			out.print("</table>");			
		}*/
		
		/*if(va.equals("0")){
			// Proceso la información y genero el xls
			HSSFWorkbook objWB = new HSSFWorkbook();
			// Creo la hoja
			HSSFSheet hoja1 = objWB.createSheet("hoja 1");
			// Proceso la información y genero el xls.
			HSSFRow fila = hoja1.createRow((short)1);
			// Creamos la celda, aplicamos el estilo y definimos
			// el tipo de dato que contendrá la celda
			HSSFCell celda = fila.createCell((short)0);
			//celda.setCellStyle(estiloCelda);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			// Finalmente, establecemos el valor
			celda.setCellValue("Un valor");
			// Volcamos la información a un archivo.
			String strNombreArchivo = "C:/libro1.xls";
			File objFile = new File(strNombreArchivo);
			FileOutputStream archivoSalida = new FileOutputStream(objFile);
			objWB.write(archivoSalida);
			archivoSalida.close();			
		}*/
		
		///////////////
	/*	final JFileChooser selector=new JFileChooser();
		JButton abrir=new JButton("Abrir");
		abrir.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent arg0) {
        selector.showOpenDialog(getParent());   }
		});
		add(abrir);
*/
		////////////////
		if(va.equals("2")){			
			File f = null;
			 JFileChooser selector = new JFileChooser(".");
			 try
			 {
			   int value = selector.showSaveDialog(null);
			   if( value == JFileChooser.APPROVE_OPTION)
			   {
			     f = selector.getSelectedFile();
			     String ff=f+".txt";
			     PrintWriter output= new PrintWriter(new FileWriter(ff));
			     rs=mmp.SqlPruebaRips();
			     while(rs.next()){
			    	 output.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6));
			     }
			     rs.getStatement().getConnection().close();			    
			     output.close();
			   }
			//   System.out.println("file selected: "+f.getAbsolutePath() );
			 }
			 catch(Exception e)
			 {
			   e.printStackTrace();
			 }			
		}
		
	}

}
