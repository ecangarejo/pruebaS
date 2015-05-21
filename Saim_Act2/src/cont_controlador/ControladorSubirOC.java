package cont_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cont_metodo.MetodoSubirOC;

public class ControladorSubirOC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out=res.getWriter();
		MetodoSubirOC msoc=new MetodoSubirOC();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rsp=null;
		ResultSet rsl=null;
		ResultSet rsa=null;
		
		if(va.equals("FormBusq")){
					 out.println("<table width='100%'>");
					 out.println("<tr><td>Orden de Compra No. :</td><td><input type='text' id='consecoc' /></td><td>No. de Factura :</td><td><input type='text' id='nofactura' /></td></tr>");
					 out.println("<tr><td>Fecha de Descargue :</td><td><input type='text' id='fecdesc' onKeyup='masca(this,patron,true,01,01,2010)' onblur='checkfecha1()' /></td><td>Fecha de Insercion :</td><td><input type='text' id='fecins' onKeyup='masca(this,patron,true,01,01,2010)' onblur='checkfecha2()' /></td></tr>");
					 out.println("<tr><td>Usuario:</td><td>");
					 out.println("<select id='codus' class='rep'><option value='no'>---</option>");
					 rs=msoc.BuscarListUsuarios();
					 try {
						while(rs.next()){
							 out.println("<option value='"+rs.getString(2)+"' >"+rs.getString(1)+"</option>");
						 }rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 out.println("</select>");
					 out.println("</td><td>Proveedor: </td><td>");
					 rs=msoc.BuscarProvedores();//se consulta la tabla tercero pues la tabla contpredoc guarda es el codigo del tercero. 
					 out.println("<select id='terc' class='rep' ><option value='no'>---</option>");
					 try {
						while(rs.next()){
							 out.println("<option value='"+rs.getString(2)+"'>"+rs.getString(1)+"</option>");
						 }rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.println("</select>");
					 out.println("</td></tr>");
					 out.println("<tr align='center' ><td colspan='4'><input type='button' id='btn' value='Buscar' onclick='Buscar()'/></td></tr>");
					 out.println("</table>");
		}
		
		
		if(va.equals("Lista")){
			
			String OC=req.getParameter("OC");
			String nofactura=req.getParameter("nofactura");
			String fechadesc=req.getParameter("fechadesc");
			String fechains=req.getParameter("fechains");
			String codus=req.getParameter("codus");
			String terc=req.getParameter("terc");
			String detfechdes[]=fechadesc.split("/");
			String detfecins[]=fechains.split("/");
			String fdesc="";
			String fins="";
			out.println("<table width='100%'>");
			out.println("<tr class='contpre' bgcolor='#E2E8EF' align='center'><td>Item</td><td>No. OC</td><td>No. Factura</td><td>Proveedor</td><td>Concepto</td><td>Valor Total</td><td>Fecha de Descargue</td><td>Fecha de Insercion</td><td>Descargado Por</td><td>Año</td><td>Periodo</td><td>Estado del Periodo</td></tr>");
					
			String sql="";
			if(!OC.equals("")){
				sql=" AND o.consec='"+OC+"' ";
			}
			
			if(!nofactura.equals("")){
				sql=sql+" AND cp.no_factura='"+nofactura+"' ";
				
			}
			
			if(!fechadesc.equals("")){
				fdesc=detfechdes[3]+"-"+detfechdes[2]+"-"+detfechdes[1];
				System.out.println("fdesc"+fdesc);
				sql=sql+" AND cp.fecha_descargue='"+fdesc+"' ";	
			}
			
			if(!fechains.equals("")){
				fins=detfecins[3]+"-"+detfecins[2]+"-"+detfecins[1];
				
				sql=sql+" AND cp.fecha_insercion='"+fins+"' ";	
			}
			
			if((!codus.equals(""))&&(!codus.equals("no"))){
				sql=sql+" AND u.usu_codigo='"+codus+"' ";
			}
			
			if(!terc.equals("")&&(!terc.equals("no"))){
				sql=sql+" AND t.codigo='"+terc+"' ";
			}
			
			String sqll="SELECT  o.consec, cp.no_factura, cp.fecha_descargue, cp.fecha_insercion, u.usuario , t.razon_social, SUM(cp.valor_debito) , o.concepto, cp.anio, cp.periodo, cp.cod_ordenfk FROM  cont_predoc cp, ord_orden_compra o, cont_terceros t, seg_usuario u "+
						"WHERE  cp.cod_ordenfk=o.codigo AND cp.cod_tercerofk=t.codigo AND u.usu_codigo=cp.cod_userfk AND cp.estado=0 "+sql+" GROUP BY cp.no_factura,o.consec ORDER BY t.razon_social ASC ";
			rs=msoc.BuscarOrdenes(sqll);
			int i=1;
			int est=0;
			try {
				while(rs.next()){
					out.println("<tr class='rep'><td><input type='hidden' id='codoc"+i+"' value='"+rs.getString(11)+"' /><input type='hidden' id='nofact"+i+"' value='"+rs.getString(2)+"' />"+i+"</td><td>"+rs.getString(1)+"</td><td><a href='#' onclick='Verificar("+i+")'>"+rs.getString(2)+"</a></td><td>"+rs.getString(6)+"</td><td>"+rs.getString(8)+"</td><td>"+FormatMoneda(rs.getString(7))+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td align='center'>"+rs.getString(9)+"</td><td align='center'>"+rs.getString(10)+"</td>");
						rs1=msoc.BuscarEstAnioPeriodo(rs.getString(9),rs.getString(10));
						String estperiodo="";
						if(rs1.next()){
							estperiodo=rs1.getString(1);
						}rs1.getStatement().getConnection().close();
						if(estperiodo.equals("")){
							out.println("<td bgcolor='#F1FCC6' align='center'>Solicitar Creacion</td>");
							est=1;
						}else{
							if(estperiodo.equals("0")){
								out.println("<td bgcolor='#E2FCE1' align='center'>Activo</td>");
								est=2;
							}else{
								out.println("<td bgcolor='#FCE1E5' align='center' >Desactivado</td>");
								est=3;
							}
						}
						out.println("<input type='hidden' id='estper' value='"+est+"' >");
					out.println("</tr>");
					i=i+1;
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("VerPreCargue")){
			String codoc=req.getParameter("codoc");
			String nofact=req.getParameter("nofact");
			String user=req.getParameter("user");
			String estper=req.getParameter("estper");
			
			out.println("<table width='100%'>");
			out.println("<tr align='center' class='contpre'><td>Codigo de Cuenta</td><td>Nombre de Cuenta</td><td>Debito</td><td>Credito</td></tr>");
			rs=msoc.VerPrecargue(codoc,nofact);
			try {
				while(rs.next()){
					out.println("<tr><td class='rep'>"+rs.getString(11)+"</td><td>"+rs.getString(12)+"</td><td align='center'>"+rs.getString(7)+"</td><td align='center'>"+rs.getString(15)+"</td></tr>");
					
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if((estper.equals(1))||(estper.equals(2))){
				
				out.println("<tr><td colspan='4'><input type='button' value='Terminar' disabled   /></td></tr>");
			}else{
				out.println("<tr><td colspan='4'><input type='button' value='Terminar'  /></td></tr>");
			}
			out.println("</table>");
			
		}
		
		
	}
	
	/**************************************/
	
	public String FormatMoneda(String valor){		
		String temp2="";String temp1="";
		int ud=1;int logCad = valor.length();		
		for (int i=logCad-1;i>=0;i--){			
			temp2=valor.substring(i,i+1);
			//ystem.out.println("temp2 -"+temp2);
			
			//System.out.println("temp1 -"+temp1);
			//System.out.println("TEMP22 -"+temp2);
			if((temp1.equals("."))||(temp1.equals("0"))){
				//System.out.println("eNTRANDO!!!"+temp1);
			}
			if(temp2.equals(".")){
				ud=0;
				temp2+=temp1;
				temp1=temp2;
			}else{
				temp2+=temp1;
				if(ud==3){
					if(i!=0){
						temp1="."+temp2;
						}else{
						temp1=temp2;
						}ud=0;
				}else{
					temp1=temp2;
					}
			}
				ud++;
		}
		temp1="$ "+temp1;
		return temp1;
	}
	
	
	
	
	
	/**************************************/

}
