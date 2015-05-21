package pre_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pre_metodo.MetodoPresupuesto;


public class ControlPresupuesto extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out=res.getWriter();
		MetodoPresupuesto mpre=new MetodoPresupuesto();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		ResultSet rs7=null;
		ResultSet rsa=null;
		ResultSet rsb=null;
		ResultSet rsc=null;
		ResultSet rsp=null;
		ResultSet rsl=null;
		
		String user=req.getParameter("user");
		
		if(va.equals("PreCont")){
		 out.println("<table >");	
		 out.println("<tr><td>Defina Periodo: </td>" +
		 			 "<td><input type='text' id='Rpi' onkeyup='valAnoPeriodo(0)' size='3'/> al <input type='text' id='Rpf' onkeyup='valAnoPeriodo(2)' size='3'/></td>" +
		 			 "</tr><tr><td>Año :</td><td ><input type='text'  id='Ano'  onkeyup='valAnoPeriodo(1)'  size='5'/></td></tr>" +
		 			 "<tr><td>Tipo :</td><td><select id='tipo' class='rep' onchange='Tipo()'><option value='---' >---</option>"); 
		 rs=mpre.BusTipo();
		 try {
			while(rs.next()){
				 out.println("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");
			 }
			 
			 out.println("</select></td>");
			 rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 out.println("</td></tr>"+
				 	 "<tr><td colspan='2'><div id='visttipo' ></div></td></tr>"+
		 			 "<tr><td>Concepto : </td><td ><textarea id='concep' cols='72' rows='3' class='rep'></textarea></td></tr>");
		 out.println("<tr><td colspan='2' > <input type='button'  value='Crear' id='bpre' onclick='Cpre()' /></td></tr>");
			
		}
		
		if(va.equals("PreGuardCont")){
			String rpi=req.getParameter("rpi");
			String rpf=req.getParameter("rpf");
			String ano=req.getParameter("ano");
			String concep=req.getParameter("concep");
			String t=req.getParameter("tipo");
			String suc=req.getParameter("suc");
			String ccosto=req.getParameter("ccosto");
			String subcc=req.getParameter("subcc");
			int tipo = Integer.parseInt(t);
			System.out.println("valor de tipo "+tipo);
			rs=mpre.BuscarPresupuesto(rpi,rpf,ano,concep,tipo,suc,ccosto,subcc);
			try {
				if(rs.next()){
					out.println("1");
				}else{
					out.println("2");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("NuevoPresupuesto")){
			String rpi=req.getParameter("rpi");
			String rpf=req.getParameter("rpf");
			String ano=req.getParameter("ano");
			String concep=req.getParameter("concep");
			String t=req.getParameter("tipo");
			String suc=req.getParameter("suc");
			String ccosto=req.getParameter("ccosto");
			String subcc=req.getParameter("subcc");
			int tipo = Integer.parseInt(t);
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			
			mpre.GuardarPre(rpi,rpf,ano,concep,t,suc,ccosto,subcc,Fecha,Hora,user);
			rs=mpre.BuscarCodPre(rpi,rpf,ano,concep,t,suc,ccosto,subcc,Fecha,Hora,user);
			String codpre="";
			try {
				if(rs.next()){
				 codpre=rs.getString(1);	
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if((tipo==1)||(tipo==3)){
				/*PRESUPUESTO GENERAL Y POR SUCURSAL */
				out.println("<table width='100%' >");
				rs1=mpre.BusPre(codpre);
				String descrip="";
				if(tipo==1){
					descrip="GENERAL";
				}else{
					descrip="POR SUCURSAL";
				}
				try {
					if(rs1.next()){
						out.println("<tr><td colspan='2'><br>PRESUPUESTO "+descrip+" CORRESPONDIENTE AL PERIODO "+rs1.getString(2)+" AL "+rs1.getString(3)+" DEL AÑO "+rs1.getString(4)+"<br></td></tr>");
					}rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.println("<tr><td>Digite cuenta : </td><td><input type='text' id='ctaaux' onkeyup='autoCompletCtaAux("+codpre+")' class='rep' /></td></tr>");
				out.println("<tr><td></td><td><div id='vistcta' ></div><input type='hidden' id='codcta' /></td></tr>");
				out.println("<tr><td colspan='2'><div id='vistpresu'></div></td></tr>");
				out.println("</table>");
			}else{
				/*PRESUPUESTO  PARA INVENTARIO E INVENTARIO POR SUCURSAL */
				rs1=mpre.BusPre(codpre);
				out.println("<table width='100%' >");
				String descrip="";
				if(tipo==2){
					descrip="DE INVENTARIO GENERAL";
				}else{
					descrip="DE INVENTARIO POR SUCURSAL";
				}
				try {
					if(rs1.next()){
						out.println("<tr><td colspan='2' align='center' ><br>PRESUPUESTO "+descrip+" CORRESPONDIENTE AL PERIODO "+rs1.getString(2)+" AL "+rs1.getString(3)+" DEL AÑO "+rs1.getString(4)+"<br></td></tr>");
					}rs1.getStatement().getConnection().close();
					out.println("<tr align='center'>");
					out.println("<td ><br><label><input name='radiobutton' type='radio' value='6' id='xProduc'  onclick='Radio("+codpre+")'  >Realizar Presupuesto por Producto</label></td>");
					out.println("<td ><br><label><input name='radiobutton' type='radio' value='6' id='xGrupo'  onclick='Radio("+codpre+")'  >Realizar Presupuesto por Grupo</label></td>");
					out.println("</tr>");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.println("</table>");
			}
		}
		
		if(va.equals("xProduc")){//Inventario xproduc
			String codpre=req.getParameter("codpre");
			rs1=mpre.BusPre(codpre);
			out.println("<table width='100%' >");
			String descrip="";
			try {
				//encabezado de visualizacion de donde estamos 
			if(rs1.next()){
				if(rs1.getString(9).equals("2")){
					descrip="DE INVENTARIO GENERAL";
				}else{
					descrip="DE INVENTARIO POR SUCURSAL";
				}
					out.println("<tr><td colspan='2' align='center'><br>PRESUPUESTO "+descrip+" CORRESPONDIENTE AL PERIODO "+rs1.getString(2)+" AL "+rs1.getString(3)+" DEL AÑO "+rs1.getString(4)+"<br></td></tr>");
				}rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//final de encabezado 
			out.println("<tr><td width='30%'>Digite Producto</td><td width='70%'><input type='text' id='produc' onkeyup='AutoCompProduc("+codpre+")' size='30'/></td></tr>");
			out.println("<tr><td><input type='hidden' id='codprod' /><input type='hidden' id='codprogru' /></td><td><div id='vprodu' ></div></td></tr>");
			out.println("<tr><td colspan='2'><div id='vistaprepro'></div></td></tr>");
			String verif=req.getParameter("verif");
			if(verif.equals("1")){
				out.println("<tr><td colspan='2'><br><div id='Fpre'><input type='button'  value='Finalizar Presupuesto' onclick='FinalizarP("+codpre+")' /> </div></td></tr>");
			}
			
		}
		
		if(va.equals("xGrupo")){ //Inventario por grupo
			String codpre=req.getParameter("codpre");
			rs1=mpre.BusPre(codpre);
			out.println("<table width='100%' >");
			String descrip="";
			try {
			//encabezado de visualizacion de donde estamos 
			if(rs1.next()){
				if(rs1.getString(9).equals("2")){
					descrip="DE INVENTARIO GENERAL";
				}else{
					descrip="DE INVENTARIO POR SUCURSAL";
				}			
					out.println("<tr><td colspan='2' align='center'><br>PRESUPUESTO "+descrip+" CORRESPONDIENTE AL PERIODO "+rs1.getString(2)+" AL "+rs1.getString(3)+" DEL AÑO "+rs1.getString(4)+"<br></td></tr>");
				}rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//final de encabezado 
			
			out.println("<tr><td>Digite Grupo</td><td><input type='text' id='grupo' onkeyup='AutoComGrupo("+codpre+")'/></td></tr>");
			out.println("<tr><td><input type='hidden' id='codgrup' /><input type='hidden' id='codprogru' /></td><td><div id='vgrupo' ></div></td></tr>");
			out.println("<tr><td colspan='2'><div id='vistapregrup'></div></td></tr>");
			
			String verif=req.getParameter("verif");
			if(verif.equals("1")){
				out.println("<tr><td colspan='2'><br><div id='Fpre'><input type='button'  value='Finalizar Presupuesto' onclick='FinalizarP("+codpre+")' /> </div></td></tr>");
			}
			
		}
		
		if(va.equals("AutoCompProdu")){
			String codpre=req.getParameter("codpre");
			String texto=req.getParameter("texto");
			rs=mpre.BuscarProducto(texto,1);
			out.println("<table>");
			try {
				while(rs.next()){
					out.println("<tr><td class='rep'><a href='#' onclick='AsigPro("+rs.getString(1)+","+codpre+")' >"+rs.getString(2)+" - "+rs.getString(3)+" - "+rs.getString(10)+"</td></tr>");
				}rs.getStatement().getConnection().close(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</table>");
			
		}
		
		if(va.equals("AutoComGrup")){
			
			String codpre=req.getParameter("codpre");
			String texto=req.getParameter("texto");
			rs=mpre.BuscarProducto(texto, 2);
			out.println("<table>");
			try {
				while(rs.next()){
					out.println("<tr><td class='rep'><a href='#' onclick='AsigGrup("+rs.getString(9)+","+rs.getString(10)+","+codpre+")' >"+rs.getString(16)+" - "+rs.getString(15)+"</a></td></tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("Infprod")){
			String codprod=req.getParameter("codprod");
			String codpre=req.getParameter("Codpre");
			rs=mpre.BusProducto(codprod);
			String DatosProd="";
			try {
				if(rs.next()){
					DatosProd=rs.getString(1)+"_"+rs.getString(2)+"_"+rs.getString(3)+"_"+rs.getString(12);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print(DatosProd);	
		}
		
		if(va.equals("Infgrup")){
			
			String codgru=req.getParameter("codgru");
			System.out.println("Valor de codgru"+codgru);
			String DatosGru="";
			rs=mpre.BusGrupo(codgru);
			try {
				if(rs.next()){
					DatosGru=rs.getString(1)+"_"+rs.getString(2)+"_"+rs.getString(4);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Datos Grupos "+DatosGru);
			out.println(DatosGru);
		}
		
		if(va.equals("VGru1")){ 
			/*** Visualiza todos los producto asociados  un ej:
			 * -----------------------------------------------------------------------------
			 * Grupo: Medicamentos
			 * _____________________________________________________________________________
			 * Producto  					|	   ENERO   | 	  FEBRERO    |	    MARZO  |
			 * ACETAMINOFEN
			 * LOSARTAN
			 */
			
			String codpre=req.getParameter("codpre");
			String codgru=req.getParameter("codgru");
			String codprogru=req.getParameter("codprogru");
			out.println("<table width='100%'");
			rs=mpre.BusProdxGrupo(codgru);//Busca todo los productos asociado al codgru
			rs1=mpre.BusGrupo(codgru);
			int i=1;
			int h=0;
			try {
				if(rs1.next()){
					out.println("<tr><td>Grupo : "+rs1.getString(2)+"</td></tr>"); //Encabezado
				}rs1.getStatement().getConnection().close();
				
				out.println("<tr><td>");
					out.println("<table width='100%'");
						rs2=mpre.BusPre(codpre);
						String pini="";
						String pfin="";
						if(rs2.next()){
							pini=rs2.getString(2);
							pfin=rs2.getString(3);
						}rs2.getStatement().getConnection().close();
						out.println("<tr>");
							out.println("<td></td><td></td>");
							int j=1;
							rs3=mpre.BuscarMeses(pini, pfin);
							while(rs3.next()){
								out.println("<td width='7%'>"+rs3.getString(2)+"<input type='hidden' id='cmes"+j+"' value='"+rs3.getString(1)+"' title='cmes"+j+"'/></td>");
								j=j+1;
							}rs3.getStatement().getConnection().close();
						out.println("</tr>");	
						out.println("<tr>");
							out.println("<td width='4%'>REF.</td><td width='12%'>PRODUCTO</td>");
							rs3=mpre.BuscarMeses(pini, pfin);
							while(rs3.next()){
								out.println("<td>Cant. | Valor</td>");
							}rs3.getStatement().getConnection().close();
						out.println("</tr>");
						while(rs.next()){
								
							out.println("<tr class='rep'>");
							out.println("<td width='4%'>"+rs.getString(2)+"</td><td width='12%'> "+rs.getString(3)+"</td>");
							for(int k=1;k<j;k++){
								out.println("<td><input type='text' id='cant"+i+""+k+"'  title='cant"+i+""+k+"' value='0' size='4' style='width:30%' class='rep'/>|");
								out.println("<input type='text' id='val"+i+""+k+"' title='val"+i+""+k+"' value='0' size='5'  style='width:50%' class='rep' /><input type='hidden' id='prod"+i+""+k+"' value='"+rs.getString(1)+"' /></td>");
							}
							
							out.println("</tr>");
							i=i+1;
						}rs.getStatement().getConnection().close();
						if(i!=1){
							
							out.println("<tr><td><input type='button' id='btnasigg' value='Agregar' onclick='AgregarGrup("+j+","+i+","+codpre+","+codgru+")' /></td><td><input type='button' id='Limpg'  onclick='RecargueVisual(2,"+codpre+",1)' value='Limpiar' /></td><td><input type='button' id='VerPreli' value='Preliminar' onclick=Preliminar("+codpre+") /></td></tr>");
							//out.println("<tr><td colspan='3'><br><input type='button'  value='Finalizar Presupuesto22' onclick='FPreInv()' /> </td></tr>");
						}
					out.println("</table>");
				out.println("</td></tr>");
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		if(va.equals("VProd1")){//Vista de los meses, casilla de cantidad y precio para productos 
			String codprod=req.getParameter("codprod");
			String codpre=req.getParameter("codpre");
			String pini="";
			String pfin="";
			rs=mpre.BusPre(codpre);
			try {
				if(rs.next()){
					pini=rs.getString(2);
					pfin=rs.getString(3);
					
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs1=mpre.BuscarMeses(pini, pfin);
			int i=1;
			out.println("<table width='60%' >");
			out.println("<tr align='center'><td width='20%'>MES</td width='15%'><td>Cantidad</td><td width='30%'>Valor</td></tr>");
			try {
				while(rs1.next()){
					out.println("<tr class='rep'><td>"+rs1.getString(2)+"</td><td align='center'><input type='text' id='cant"+i+"' style='width:30%' class='rep' value='0'/></td><td align='center'><input type='text' id='val"+i+"' style='width:80%' class='rep' value='0' /><input type='hidden' id='cmes"+i+"' value='"+rs1.getString(1)+"'/></td></tr>");
					i=i+1;
				}rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i!=1){
				
			out.println("<tr><td><input type='button' id='btnasigp' value='Agregar' onclick='AgregarProdu("+codprod+","+codpre+","+i+")' /></td><td><input type='button' id='Limp' onclick='RecargueVisual(1,"+codpre+",1)' value='Limpiar' /></td><td><input type='button' onclick='Preliminar("+codpre+")' value='Preliminar' /></td></tr>");
			//out.println("<tr><td colspan='3' align='center' ><br><input type='button'  value='Finalizar Presupuesto' onclick='FPreInv("+codpre+","+i+")' /> </td></tr>");
			}
		}
	
		if(va.equals("AgProduc")){
			String valores=req.getParameter("valores");
			String cantidades=req.getParameter("cantidades");
			String codprod=req.getParameter("codprod");
			String codpre=req.getParameter("codpre");
			String meses=req.getParameter("meses");
			String codprogru=req.getParameter("codprogru");
			String ident =req.getParameter("i");
			
			int i= Integer.parseInt(ident);
			String cmes[]=meses.split("_");
			String valor[]=valores.split("_");
			String cant[]=cantidades.split("_");
			
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			
			rs1=mpre.BuscarCuenta(codprogru);//Busca la cuenta asociada al producto 
			String codcta="";
			String nombrecta="";
			String cta="";
			try {
				if(rs1.next()){
					codcta=rs1.getString(9);
					nombrecta=rs1.getString(12);
					cta=rs1.getString(10);
				}rs1.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			mpre.Savecta2(codcta, cta, nombrecta, Fecha, Hora, user, codpre, codprod);
			rs1=mpre.BuscodCta2(codcta, cta, nombrecta, Fecha, Hora, user, codpre, codprod);
			String coddetpre="";
			try {
				if(rs1.next()){
					coddetpre=rs1.getString(1);
				}rs1.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(int j=0;j<valor.length;j++){
				rs=mpre.BusNombMes(cmes[j]);//Busca el nombre del mes segun la posicion de i 
				String nmes="";
				try {
					if(rs.next()){
						nmes=rs.getString(2);
					}rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				mpre.InsertValores(valor[j],cmes[j],coddetpre,nmes);
				mpre.InsertPreDetInventario(cant[j],valor[j],nmes,codpre,codprod,Fecha,Hora,coddetpre,codprogru);
				System.out.println(" cant "+cant[j]+" ,  valor "+valor[j]+", mes: "+nmes+", codpre="+codpre+", codprod="+codprod+", fecha ="+Fecha+", hora "+Hora+", coddetpre="+coddetpre+", codprogru "+codprogru);
			}
			
		}
		
		
		if(va.equals("AgGrupo")){
			String cantidades=req.getParameter("cantidades");
			String productos=req.getParameter("productos");
			String valores=req.getParameter("valores");
			String meses=req.getParameter("meses");
			System.out.println("cantidades "+cantidades);
			String cant[]=cantidades.split("_");
			//String prod[]=productos.split("_");
			String valor[]=valores.split("_");
			String mes[]=meses.split("_");
			/*for(int y=0;y<valor.length;y++){
				
				System.out.println("valor de valor "+valor[y]);
			}
			for(int x=0;x<mes.length;x++){
				
				System.out.println("valor de mes "+mes[x]);
			}
			
			for(int z=0;z<cant.length;z++){
				
				System.out.println("valor de cant "+cant[z]);
			}*/
			
			
			//String j=req.getParameter("j");
			String codpre=req.getParameter("codpre");
			//String codgru=req.getParameter("codgru");
			
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			
			String codprogru=req.getParameter("codprogru");
			
			rs1=mpre.BuscarCuenta(codprogru);//Busca la cuenta asociada al producto 
			String codcta="";
			String nombrecta="";
			String cta="";
			try {
				if(rs1.next()){
					codcta=rs1.getString(9);
					nombrecta=rs1.getString(12);
					cta=rs1.getString(10);
				}rs1.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			mpre.Savecta2(codcta, cta, nombrecta, Fecha, Hora, user, codpre, productos);
			rs1=mpre.BuscodCta2(codcta, cta, nombrecta, Fecha, Hora, user, codpre, productos);
			String coddetpre="";
			try {
				if(rs1.next()){
					coddetpre=rs1.getString(1);
				}rs1.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(int k=0;k<valor.length;k++){
			
				rs=mpre.BusNombMes(mes[k]);//Busca el nombre del mes segun la posicion de i 
				String nmes="";
				try {
					if(rs.next()){
						nmes=rs.getString(2);
					}rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				mpre.InsertValores(valor[k],mes[k],coddetpre,nmes);
				mpre.InsertPreDetInventario(cant[k],valor[k],nmes,codpre,productos,Fecha,Hora,coddetpre,codprogru);
			}
			
		}
		
		if(va.equals("GuardarP")){
			
			String codpre=req.getParameter("codpre");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			
			try {
				rs=mpre.BusPresu(codpre);
				if(rs.next()){
					mpre.CambiarEstPre(codpre,"1",user,Fecha,Hora,"");
					out.println("1");
				}else{
					out.println("2");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("VerDetCta")){
			String codpre=req.getParameter("codpre");
			String codcta=req.getParameter("codcta");
			String cod=req.getParameter("cod");
			String nombre=req.getParameter("nombre");
			String y=req.getParameter("y");
			rs=mpre.BuscPeriodo(codpre);
			String pini="";
			String pfin="";
			try {
				if(rs.next()){
					pini=rs.getString(2);
					pfin=rs.getString(3);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rs1=mpre.BuscarMeses(pini,pfin);
			int i=1;
			out.println("<table>");
			try {
				while(rs1.next()){
					out.println("<tr><td>"+rs1.getString(2)+"</td><td><input type='text' id='val"+i+"' value='0'  /><input type='hidden' id='cmes"+i+"' value='"+rs1.getString(1)+"' /></td></tr>");
					
					i=i+1;
				}rs1.getStatement().getConnection().close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("<tr><td><input type='button' id='btna' onclick='Agregarcta("+i+","+codcta+","+codpre+","+y+")' value=Agregar /><input type='button' id='btnp' onclick='Ponderar("+i+")' value=Ponderar /></td><td><input type='button' id='btnl' onclick='AgregarOCta("+codpre+","+y+")' value=Limpiar /></td></tr>");
			out.println("</table>");	
			
		}
		
		if(va.equals("GuardarDetCta")){
			String i=req.getParameter("i");
			String codcta=req.getParameter("codcta");
			String codpre=req.getParameter("codpre");
			String codmes=req.getParameter("codmes");
			String cadenava=req.getParameter("cadenava");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			String [] valor=cadenava.split("_");
			String [] cmes=codmes.split("_");	
			int j = Integer.parseInt(i);
			rs=mpre.BuscarDatosCta(codcta);
			String nombre="";
			String ref="";
			try {
				if(rs.next()){
					nombre=rs.getString(3);
					ref=rs.getString(2);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("cadenava "+cadenava);
			System.out.println("codmes "+codmes);
			System.out.println("nombre cuenta "+nombre);
			mpre.Savecta(codcta,ref,nombre,Fecha,Hora,user,codpre);
			rs1=mpre.BuscodCta(codcta,ref,nombre,Fecha,Hora,user,codpre);
			String coddetcta="";
			try {
				if(rs1.next()){
					coddetcta=rs1.getString(1);
				}rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					for (j = 0; j < valor.length; j++) {
						System.out.println("valor de codigo del mes "+cmes[j]);
						rs2=mpre.BusNombMes(cmes[j]);
						String nmes="";
						try {
							if(rs2.next()){
								nmes=rs2.getString(2);
							}rs2.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						mpre.InsertValores(valor[j],cmes[j],coddetcta,nmes);
					}
		}
		
		if(va.equals("1.1")){
			
			String t="";
			String codpre=req.getParameter("codpre");
			rs=mpre.BusPre(codpre);
			try {
				if(rs.next()){
					t=rs.getString(9);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int tipo = Integer.parseInt(t);
						
			if((tipo==1)||(tipo==3)){
				/*PRESUPUESTO GENERAL Y POR SUCURSAL */
				out.println("<table>");
				rs1=mpre.BusPre(codpre);
				try {
					if(rs1.next()){
						out.println("<tr><td colspan='2'><br>PRESUPUESTO CORRESPONDIENTE AL PERIODO "+rs1.getString(2)+" AL "+rs1.getString(3)+" DEL AÑO "+rs1.getString(4)+"<br></td></tr>");
					}rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.println("<tr><td>Digite cuenta : </td><td><input type='text' id='ctaaux' onkeyup='autoCompletCtaAux("+codpre+",0)' class='rep' /></td></tr>");
				out.println("<tr><td></td><td><div id='vistcta' ></div><input type='hidden' id='codcta' /></td></tr>");
				out.println("<tr><td colspan='2'><div id='vistpresu'></div></td></tr>");
				out.println("<tr><td><input type='button' id='btnf' onclick='FinalizarP("+codpre+")' value='Finalizar Presupuesto' /></td><td><input type='button' id='btnp' value='Ver Preliminar' onclick='Preliminar("+codpre+")' /></td></tr>");
				out.println("</table>");
			}else{
				/*PRESUPUESTO  PARA INVENTARIO E INVENTARIO POR SUCURSAL */
			}
			
		}
		
		if(va.equals("1.2")){
			String codpre=req.getParameter("codpre");
			String firma="";
			rs=mpre.BusPre(codpre);
			rs1=mpre.BusPresu(codpre);
			rs3=mpre.BuscarNombreEmpre();
			String valorsum="";
			int i=0;
			int h=0;
			try {
				if(rs.next()){
					if((rs.getString(9).equals("1"))||(rs.getString(9).equals("3"))){
						//Presupuesto General de ctas 
						// Tipo 3 Presupuesto de cuentas por suscursal, centro de costo y subcentro de costo 
						out.println("<table align='justify' >");
						out.println("<tr class='cabpre' ><td colspan='5'>PRESUPUESTO GENERAL ");
						if(rs3.next()){
							out.println(rs3.getString(2));
						}rs3.getStatement().getConnection().close();
						out.println(" - CORRESPONDIENTE AL PERIODO "+rs.getString(2)+" al "+rs.getString(3)+" del Año "+rs.getString(4)+" </td></tr>");
						out.println("<tr class='contpre'><td><b><br>No. Presupuesto: </b></td><td><br>"+rs.getString(1)+"</td><td><br><b>Fecha de Generacion: </b></td><td><br>"+rs.getString(5)+"</td> </tr>");
						out.println("<tr class='contpre'><td><b>Concepto : </b></td><td>"+rs.getString(13)+"</td><td><b>Fecha de Emision : </b></td><td>");
						rs4=mpre.BuscarFechaR(codpre);
						if(rs4.next()){
							out.println(rs4.getString(5));
							firma=rs4.getString(4);
							
						}rs4.getStatement().getConnection().close();
						out.println("</td></tr>");
						if(rs.getString(9).equals("3")){
							rsa=mpre.Bsuc(rs.getString(10));
							out.println("<tr class='contpre'>");
							out.println("<td><b>Sucursal :</b> ");
							out.println("<td>");
							if(rsa.next()){
								out.println(rsa.getString(2));
							}rsa.getStatement().getConnection().close();
							out.println("</td>");
														
							if(rs.getString(11).equals("todas")){
								
								out.println("<td>Centro Costo: TODAS </td>");
							}else{
								rsb=mpre.Bccosto(rs.getString(11));
								while(rsb.next()){
									out.println("<td>Centro Costo: "+rsb.getString(3)+"</td>");
								}rsb.getStatement().getConnection().close();
								
								if(rs.getString(12).equals("todas")){
									
									out.println("<td>SubCentro Costo: TODAS </td>");
									
								}else{
									rsc=mpre.Bsubcc(rs.getString(12));
									while(rsc.next()){
										out.println("<td>SubCentro Costo:"+rsc.getString(2)+"</td>");
									}rsc.getStatement().getConnection().close();
								}
							}
							out.println("</tr>");
							
							
						}						
						out.println("<tr><td colspan='5'><table width='100%'>");
						out.println("<tr class='contpre' bgcolor='#DBE4F2'><td width='10%'>Codigo Cuenta </td><td width='25%'>Cuenta</td>");
						rs2=mpre.BuscarMeses(rs.getString(2), rs.getString(3));
						while(rs2.next()){
							out.println("<td>"+rs2.getString(2)+"</td>");
						}rs2.getStatement().getConnection().close();
						
						rs2=mpre.BuscarMesesDesc(rs.getString(2), rs.getString(3));
						String cadena="";
						while(rs2.next()){
							cadena=rs2.getString(2)+"_"+cadena;
							i=i+1;
						}rs2.getStatement().getConnection().close();
						System.out.println("valor de cadena "+cadena);
						out.println("<td>Total Presupuestado</td></tr>");
						long sum=0;
						while(rs1.next()){
						out.println("<tr class='rep'><td>"+rs1.getString(4)+"</td><td>"+rs1.getString(5)+"</td>");
						String [] detcadena=cadena.split("_");
							for(int j=0;j<i;j++){
							String valor=rs1.getString(detcadena[j])+"";
							BigDecimal big = new BigDecimal(valor);  
							big = big.setScale(2, RoundingMode.HALF_UP);
							String str = big.toString();
								out.println("<td>"+FormatMoneda(str)+"</td>");
								System.out.println("<td>"+rs1.getString(detcadena[j])+"</td>");
								System.out.println("valor de str"+str+" valor de valor "+valor+" valor de I="+i+" valor de j="+j);
								double prueba=Double.parseDouble(valor);
								System.out.println("valor de prueba "+prueba);
								Long sub = (long)prueba;
								System.out.println("valor de sub"+sub);
								//long sub=Long.parseLong(valor);
								sum=sub+sum;
							}
							
							String s=String.valueOf(sum);
							BigDecimal big = new BigDecimal(s);  
							big = big.setScale(2, RoundingMode.HALF_UP);
							 String ss = big.toString();
							out.println("<td>"+FormatMoneda(ss)+"</td>");
							
							valorsum=s+"_"+valorsum;
							sum=0;
							h=h+1;
						}rs1.getStatement().getConnection().close();
						
						String [] detvalorsum=valorsum.split("_");
						float tvs=0;
						String sss="";
						System.out.println("valorsum= "+valorsum+" valor de h= "+h);
						for(int k=0;k<h;k++){
							System.out.println("valor de detvalorsum"+detvalorsum[k]+" cuando k es "+k);
							BigDecimal big = new BigDecimal(detvalorsum[k]);  
							big = big.setScale(2, RoundingMode.HALF_UP);
							 String vs = big.toString();
							 float subvs=Float.parseFloat(vs);
							 tvs=tvs+subvs;
							 String s=String.valueOf(tvs);
								BigDecimal bigg = new BigDecimal(s);  
								bigg = bigg.setScale(2, RoundingMode.HALF_UP);
								 sss = bigg.toString();
						} 
						out.println("</tr>");
						out.println("</table>");
						out.println("</td></tr>");
						out.println("<tr><td colspan='5' class='contpre'><br> <b> Total Presupuestado : </b> "+FormatMoneda(sss)+"</td></tr>");
						out.println("<tr><td colspan='5'>");
						out.println("<table width='100%'>");
					//Final del Reporte 
							rsp=mpre.BusUsu(firma);
								if(rs.getString(7).equals("1")){
									if(rsp.next()){
										out.println("<tr class='contpre'><td  width='35%'><b><br><br>Presupuesto Emitido Por: </b></td><td><br><br>"+rsp.getString(1)+"</td></tr>");
										out.println("<tr class='contpre'><td  width='35%'> </td><td colspan='2'>"+rsp.getString(2)+" "+rsp.getString(3)+"</td></tr>");
									}rsp.getStatement().getConnection().close();
								}else{
									rs5=mpre.BuscarEstadosPre(codpre);
									while(rs5.next()){
										if(rs5.getString(7).equals("1")){
											out.println("<tr class='contpre' ><td width='35%'><b><br><br>Presupuesto Emitido Por: </b></td><td ><br><br>"+rs5.getString(8)+"</td></tr>");
											out.println("<tr class='contpre' ><td  width='35%'>  </td><td >"+rs5.getString(9)+" "+rs5.getString(10)+"</td></tr>");
										}else{
											if(rs5.getString(7).equals("2")){
												out.println("<tr class='contpre' ><td  width='35%'><br><b>Presupuesto Aprobado Por:</b> </td><td ><br>"+rs5.getString(8)+"</td></tr>");
												out.println("<tr class='contpre' ><td  width='35%'> </td><td colspan='2'>"+rs5.getString(9)+" "+rs5.getString(10)+"</td></tr>");
											}else{
												if(rs5.getString(7).equals("3")){
													///AUN NO SE QUE VOY A HACER CON EL ESTADO EJECUTADO SI LO VOY A HACER AUTOMATICO O LO VA  HACER EL USUARIO 
												}else{
													if(rs5.getString(7).equals("4")){
														out.println("<tr class='contpre' ><td  width='35%'><br><b>Presupuesto Anulado Por: </td><td ><br>"+rs5.getString(8)+"</td></tr>");
														out.println("<tr class='contpre'><td  width='35%'>  </td><td colspan='2'>"+rs5.getString(9)+" "+rs5.getString(10)+"</td></tr>");
													}else{
														if(rs5.getString(7).equals("5")){
															out.println("<tr class='contpre'><td  width='35%'><br><b>Presupuesto Rechazado Por: </td><td ><br>"+rs5.getString(8)+"</td></tr>");
															out.println("<tr class='contpre'><td  width='35%'>  </td><td colspan='2'>"+rs5.getString(9)+" "+rs5.getString(10)+"</td></tr>");
														}
													}
												}
											}
											
										}
									}rs5.getStatement().getConnection().close();
								}
							out.println("</table>");
						out.println("</td></tr>");
						out.println("</table>");
					}else{
						if((rs.getString(9).equals("2"))||(rs.getString(9).equals("4"))){
							//2 Presupuesto de Inventario de Productos
							//4 Presupuesto de Inventario de Productos por sucursal, centro de costo y subcentro de costo
							rs3=mpre.BuscarNombreEmpre();
							out.println("<table align='justify' width='90%'>");
							out.println("<tr class='cabpre' ><td colspan='5'>PRESUPUESTO DE INVENTARIO ");
							if(rs3.next()){
								out.println(rs3.getString(2));
							}rs3.getStatement().getConnection().close();
							out.println(" - CORRESPONDIENTE AL PERIODO "+rs.getString(2)+" al "+rs.getString(3)+" del Año "+rs.getString(4)+" </td></tr>");
							out.println("<tr class='contpre'><td><b><br>No. Presupuesto: </b></td><td><br>"+rs.getString(1)+"</td><td><br><b>Fecha de Generacion: </b></td><td><br>"+rs.getString(5)+"</td> </tr>");
							out.println("<tr class='contpre'><td><b>Concepto : </b></td><td>"+rs.getString(13)+"</td><td><b>Fecha de Emision : </b></td><td>");
							rs4=mpre.BuscarFechaR(codpre);
							if(rs4.next()){
								out.println(rs4.getString(5));
								firma=rs4.getString(4);
								
							}rs4.getStatement().getConnection().close();
							out.println("</td></tr>");
							
							if(rs.getString(9).equals("4")){
										rsa=mpre.Bsuc(rs.getString(10));
										out.println("<tr class='contpre'>");
										out.println("<td><b>Sucursal :</b> ");
										out.println("<td>");
										if(rsa.next()){
											out.println(rsa.getString(2));
										}rsa.getStatement().getConnection().close();
										out.println("</td>");
																	
										if(rs.getString(11).equals("todas")){
											
											out.println("<td>Centro Costo: TODAS </td>");
										}else{
											rsb=mpre.Bccosto(rs.getString(11));
											while(rsb.next()){
												out.println("<td>Centro Costo: "+rsb.getString(3)+"</td>");
											}rsb.getStatement().getConnection().close();
											
											if(rs.getString(12).equals("todas")){
												
												out.println("<td>SubCentro Costo: TODAS </td>");
												
											}else{
												rsc=mpre.Bsubcc(rs.getString(12));
												while(rsc.next()){
													out.println("<td>SubCentro Costo:"+rsc.getString(2)+"</td>");
												}rsc.getStatement().getConnection().close();
											}
										}
										out.println("</tr>");	
							}//Fin de validacion si es igual 4 para colocar encabezado de sucursal, centro y sub centro de costo 
							
							out.println("<tr><td colspan='5' ><br></td></tr>");
							long TotalPresu=0;
							out.println("<tr>");
								out.println("<td colspan='5'>");
									out.println("<table width='100%'");
										rs4=mpre.BuscarEncGrupo(codpre);
										while(rs4.next()){
											rs5=mpre.BusGrupo(rs4.getString(13));
											String NombreGrupo="";
											if(rs5.next()){
											NombreGrupo=rs5.getString(2);
											}rs5.getStatement().getConnection().close();
											rs5=mpre.BuscarMesesDesc (rs.getString(2), rs.getString(3));
											String Mes="";
											String Meses="";
											long SubTotalPxG=0;
											int k=1;
											while(rs5.next()){
												Mes=rs5.getString(2);
												Meses=rs5.getString(2)+"_"+Meses;
												SubTotalPxG=(rs4.getLong(Mes))+SubTotalPxG;
												k=k+1;
											}rs5.getStatement().getConnection().close();
											TotalPresu=SubTotalPxG+TotalPresu;
											String sub=String.valueOf(SubTotalPxG);
										out.println("<tr class='contpre'  ><td bgcolor='#DFECF4' valign='middle'><b>Grupo:</b></td><td bgcolor='#DFECF4'>"+NombreGrupo+"</td><td bgcolor='#DFECF4'><b>Cta:</b></td><td bgcolor='#DFECF4'>"+rs4.getString(14)+"</td><td bgcolor='#DFECF4'><b>Presupuestado : </b></td><td bgcolor='#DFECF4'>"+FormatMoneda(sub)+"</td></tr>");
										out.println("<tr><td colspan='6'>");
											out.println("<table width='100%' >");
											out.println("<tr class='contpre' ><td colspan='2'></td>");
											String MESS[]=Meses.split("_");
											System.out.println("Valor de MESS.length "+MESS.length);
												for(int j=0;j<MESS.length;j++){
													System.out.println("<td colspan='3'  >"+MESS[j]+"</td>");
													out.println("<td colspan='3' bgcolor='#C0D9EB'><b>"+MESS[j]+"</b></td>");
												}
												out.println("</tr>");
												out.println("<tr class='contpre'><td>Ref</td><td>Producto</td>");
												for(int x=0;x<MESS.length;x++){
													out.println("<td>Cantidad</td><td>Presupuesto Unitario</td><td>Valor Presupuestado</td>");
												}
												out.println("<td>Total Presupuestado</td>");
												out.println("</tr>");
												rs6=mpre.BusAgruProducto(codpre);
												while(rs6.next()){
													out.println("<tr class='rep' font=bold >");
														out.println("<td>"+rs6.getString(2)+"</td><td>"+rs6.getString(3)+"</td>");
														for(int z=0;z<MESS.length;z++){
															rs7=mpre.BusDetPres(codpre,rs6.getString(1),MESS[z],rs4.getString(13));
															long pu=0;
															if(rs7.next()){
																out.println("<td>"+rs7.getString(2)+"</td>");
																if(rs7.getLong(2)!=0){
																	pu=((rs7.getLong(3))/(rs7.getLong(2)));
																}
																String puu=String.valueOf(pu);
																out.println("<td>"+FormatMoneda(puu)+"</td>");
																out.println("<td>"+FormatMoneda(rs7.getString(3))+"</td>");
															}rs7.getStatement().getConnection().close();
														}
														out.println("</tr>");
												}rs6.getStatement().getConnection().close();
											out.println("</table>");
										out.println("</td></tr>");
										}rs4.getStatement().getConnection().close();
									out.println("</table>");
								out.println("</td>");
							out.println("</tr>");
							String presupuestado=String.valueOf(TotalPresu);
							out.println("<tr><td colspan='5' class='contpre'><br><b> Total Presupuestado =</b> "+FormatMoneda(presupuestado)+"</td></tr>");
							out.println("</table>");
						}else{
							out.println("No existe un reporte para los parametros dados. ");
						}
					}
					//rs1=mpre.BusPresu(codpre);
				}rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("1.3")){
			System.out.println(" 1.3");
			String codpre=req.getParameter("codpre");
			int i=0;
			int h=0;
			long total=0;
			String tipo="";
			String valorsum="";		
						rs=mpre.BusPre(codpre);
						rs1=mpre.BusPresu(codpre);
						try {
							out.println("<table  width='100%'>");
							out.println("<tr class='cabpre'>");
							out.println("<td>Vista Preliminar </td>");
							out.println("</tr>");
							out.println("<tr>");
							out.println("<td>");
									if(rs.next()){
										if((rs.getString(9).equals("1"))||(rs.getString(9).equals("3"))){
												//PARA PRESUPUESTO PARA GENERAL Y DE SUCURSAL 
													tipo=rs.getString(9);
													out.println("<table width='100%'>");
													out.println("<tr class='contpre' bgcolor='#DBE4F2'><td width='10%'>Codigo Cuenta </td><td width='25%'>Cuenta</td>");
													rs2=mpre.BuscarMeses(rs.getString(2), rs.getString(3));
													while(rs2.next()){
														out.println("<td>"+rs2.getString(2)+"</td>");
													}rs2.getStatement().getConnection().close();
													
													rs2=mpre.BuscarMesesDesc(rs.getString(2), rs.getString(3));
													String cadena="";
													while(rs2.next()){
														cadena=rs2.getString(2)+"_"+cadena;
														i=i+1;
													}rs2.getStatement().getConnection().close();
													out.println("<td>Total Presupuestado</td>");
													out.println("</tr>");
													float sum=0;
													
													String [] detcadena=cadena.split("_");
													while(rs1.next()){
													out.println("<tr class='rep'><td>"+rs1.getString(4)+"</td><td>"+rs1.getString(5)+"</td>");
													System.out.println("cadena "+cadena);
													
														for(int j=0;j<i;j++){
															System.out.println(" detcadena "+detcadena[j]+" cuando j es "+j);
														String valor=rs1.getString(detcadena[j])+"";
														BigDecimal big = new BigDecimal(valor);  
														big = big.setScale(2, RoundingMode.HALF_UP);
														 String str = big.toString();
															out.println("<td>"+FormatMoneda(str)+"</td>");
															double prueba=Double.parseDouble(valor);
															System.out.println("valor de prueba "+prueba);
															Long sub = (long)prueba;
															System.out.println("valor de sub"+sub);
															//long sub=Long.parseLong(valor);
															sum=sub+sum;
															
															System.out.println("<td>"+rs1.getString(detcadena[j])+"</td>");
														}
														String s=String.valueOf(sum);
														BigDecimal big = new BigDecimal(s);  
														big = big.setScale(2, RoundingMode.HALF_UP);
														 String ss = big.toString();
														out.println("<td>"+FormatMoneda(ss)+"</td>");
														
														valorsum=s+"_"+valorsum;
														sum=0;
														h=h+1;
													}rs1.getStatement().getConnection().close();
												
													out.println("</tr>");
													
													out.println("</table>");
										}else{
											//PARA PRESUPUESTO DE INVENTARIO Y INVENTARIO POR SUSCURSAL 
											tipo=rs.getString(9);
											out.println("<table width='100%'>");
											out.println("<tr><td colspan='4'></td>");
												
												rs4=mpre.BuscarEncGrupo(codpre);
												while(rs4.next()){
													rs5=mpre.BusGrupo(rs4.getString(13));
													String NombreGrupo="";
													if(rs5.next()){
													NombreGrupo=rs5.getString(2);
													}rs5.getStatement().getConnection().close();
													rs5=mpre.BuscarMesesDesc (rs.getString(2), rs.getString(3));
													String Mes="";
													String Meses="";
													long SubTotalPxG=0;
													int k=1;
													while(rs5.next()){
														Mes=rs5.getString(2);
														Meses=rs5.getString(2)+"_"+Meses;
														k=k+1;
													}rs5.getStatement().getConnection().close();
													String MESS[]=Meses.split("_");
													for(int j=0;j<MESS.length;j++){
														out.println("<td colspan='3'>"+MESS[j]+"</td>");
													}
													out.println("</tr>");
											out.println("<tr class='contpre' bgcolor='#DBE4F2'>Grupo</td><td>Cuenta</td><td>Ref</td><td>Producto</td>");
												for(int j=0;j<MESS.length;j++){
													out.println("<td>Cantidad</td><td> Presupuesto Unitario</td><td>Valor Presupuestado</td>");
												}
												out.println("<td>Total Presupuestado</td>");
												out.println("</tr>");
												
												
												rs6=mpre.BusAgruProducto(codpre);
												while(rs6.next()){
													out.println("<tr class='rep'><td>"+NombreGrupo+"</td>");
													out.println("<td>"+rs6.getString(5)+"-"+rs6.getString(5)+"</td>");//cuenta
													out.println("<td>"+rs6.getString(2)+"</td>");//ref producto
													out.println("<td>"+rs6.getString(3)+"</td>");//Nombre producto
													long subtotal=0;
													for(int z=0;z<MESS.length;z++){
														rs7=mpre.BusDetPres(codpre,rs6.getString(1),MESS[z],rs4.getString(13));
														long pu=0;
														if(rs7.next()){
															out.println("<td>"+rs7.getString(2)+"</td>");//Cantidad
															if(rs7.getLong(2)!=0){
																pu=((rs7.getLong(3))/(rs7.getLong(2)));
															}
															String puu=String.valueOf(pu);
															out.println("<td>"+FormatMoneda(puu)+"</td>");//Presupuesto Unitario
															out.println("<td>"+FormatMoneda(rs7.getString(3))+"</td>");//presupuestado
															long vpre=rs.getLong(3);
															subtotal=vpre+subtotal;
															total=total+subtotal;
														}rs7.getStatement().getConnection().close();	
													}//fin para
													String Subt=String.valueOf(subtotal);
													out.println("<td>"+FormatMoneda(Subt)+"</td>");
													out.println("</tr>");
												}rs6.getStatement().getConnection().close();
											out.println("</table>");
												}rs4.getStatement().getConnection().close();
										}//FIN SI NO 
											
									}rs.getStatement().getConnection().close();
								out.println("</td>");
								out.println("</tr>");
								if((tipo.equals("1"))||(tipo.equals("3"))){
									String [] detvalorsum=valorsum.split("_");
									float tvs=0;
									String sss="";
									for(int k=0;k<h;k++){
										BigDecimal big = new BigDecimal(detvalorsum[k]);  
										big = big.setScale(2, RoundingMode.HALF_UP);
										 String vs = big.toString();
										 float subvs=Float.parseFloat(vs);
										 tvs=tvs+subvs;
										 String s=String.valueOf(tvs);
											BigDecimal bigg = new BigDecimal(s);  
											bigg = bigg.setScale(2, RoundingMode.HALF_UP);
											 sss = bigg.toString();
										 
									}
									out.println("<tr><td class='contpre' align='center'><br> <b> Total Presupuestado : </b> "+FormatMoneda(sss)+"</td></tr>");
								
								}else{
									String totalp=String.valueOf(total);
									out.println("<tr><td class='contpre' align='center'><br> <b> Total Presupuestado : </b> "+FormatMoneda(totalp)+"</td></tr>");
								}
									out.println("</table>");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
			
		}
		
		if(va.equals("AutoCta")){
			String texto=req.getParameter("texto");
			String codpre=req.getParameter("codpre");
			String y=req.getParameter("y");
			rs=mpre.BusCta(texto);
			out.println("<table>");
			System.out.println("entrando a  AutoCta");
			int  i=1;
			try {
				while(rs.next()){
					out.println("<tr><td><a class='rep' href='#' onclick='asigcta("+rs.getString(1)+","+rs.getString(2)+","+codpre+","+i+","+y+")' >"+rs.getString(2)+" | "+rs.getString(3)+"<input type='hidden' id='nombre"+i+"' value='"+rs.getString(3)+"' /></a></td></td>");
				i=i+1;
				}
				out.println("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("VerifCta")){
			String codpre=req.getParameter("codpre");
			String codcta=req.getParameter("codcta");
			String val="0";
			rs=mpre.BusCtaPre(codpre,codcta);
			try {
				if(rs.next()){
					val="1";
				}else{
					val="2";
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println(val);
		}
		
		if(va.equals("VerPreExist")){
			rs=mpre.BusPrePorEmit(user);
			out.println("<table width='100%'>");
			out.println("<tr><td class='cabpre' colspan='10' align='center' >Lista de Presupuesto por Emitir <br> </td></tr>");
			out.println("<tr class='contpre' align='center' bgcolor='#DBE4F2'><td>No. Presupuesto </td><td>Tipo de Presupuesto</td><td>Concepto</td><td>Periodo Inicial</td><td>Periodo Final</td><td>Año</td><td>Fecha</td><td>Hora</td><td colspan='2'>Accion</td></tr>");
			try {
				while(rs.next()){
					out.println("<tr class='rep' align='center'><td >"+rs.getString(1)+"</td><td>"+rs.getString(14)+"</td><td>"+rs.getString(13)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td><a href='#' onclick='AbrirP("+rs.getString(1)+")' >Abrir</a></td><td><a href='#' onclick='AnuPre("+rs.getString(1)+")'>Anular</a></td></tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("<tr><td  colspan='10' align='center'><div id='anular' ></div></td></tr>");
			out.println("</table>");
			
			
		}
		
		if(va.equals("Open")){
			String codpre=req.getParameter("codpre");
			
			rs=mpre.BusPre(codpre);
			try {
				if(rs.next()){
					if((rs.getString(9).equals("1")||(rs.getString(9).equals("3")))){
						//PRESUPUESTO CONTABLE GENERAL Y CONTABLE POR SUCURSAL 
						out.println("<table width='100%' >");
						out.println("<tr class='contpre'><td align='right'>No. Presupuesto: </td><td align='left'>"+rs.getString(1)+"</td><td align='right'>Periodo Inicial:</td><td align='left'>"+rs.getString(2)+"</td><td align='right'>Periodo Final:</td><td align='left'>"+rs.getString(3)+"</td><td align='right'>Año:</td><td align='left'>"+rs.getString(4)+"</td></tr>");
						if(rs.getString(9).equals("3")){
							out.println("<tr class='contpre'>");
							rsa=mpre.Bsuc(rs.getString(10));
						
							out.println("<td>Sucursal :</td>");
							out.println("<td>");
							if(rsa.next()){
								out.println(rsa.getString(2));
							}rsa.getStatement().getConnection().close();
							out.println("</td>");
														
							if(rs.getString(11).equals("todas")){
								
								out.println("<td>Centro Costo: </td><td>TODAS</td>");
							}else{
								rsb=mpre.Bccosto(rs.getString(11));
								while(rsb.next()){
									out.println("<td>Centro Costo: </td><td>"+rs.getString(3)+"</td>");
								}rsb.getStatement().getConnection().close();
								
								if(rs.getString(12).equals("todas")){
									
									out.println("<td>SubCentro Costo:</td><td> TODAS </td>");
									
								}else{
									rsc=mpre.Bsubcc(rs.getString(12));
									while(rsc.next()){
										out.println("<td>SubCentro Costo:</td><td>"+rsc.getString(2)+"</td>");
									}rsc.getStatement().getConnection().close();
								}
							}
							out.println("</tr>");
											
						}else{};
						out.println("<tr class='contpre'>");
						out.println("<td><br>Concepto:</td><td colspan='7'><br><textarea id='concep' cols='172' rows='3' class='rep' onblur='GConcep("+codpre+")'>"+rs.getString(13)+"</textarea></td>");
						out.println("</tr>");
						out.println("<tr ><td colspan='9' class='lineacab'><br></td></tr>");
						out.println("<tr align='center' ><td colspan='9'><table width='100%' ><tr><td class='contpre' width='30%' align='center' ><br>Digite cuenta : </td><td width='70%' align='justify'><br><input type='text' id='ctaaux' onkeyup='autoCompletCtaAux("+codpre+",1)' class='rep' size='20' style='width:70%' /></td></tr>");
						out.println("<tr ><td width='30%'></td><td width='70%'><div id='vistcta' align='justify'></div><input type='hidden' id='codcta' /></td></tr>");
						out.println("<tr ><td ><div id='vistpresu'></div></td></tr></table>" +
								"</td></tr>");
						out.println("<tr><td colspan='5' align='center'><input type='button' id='btnf' onclick='FinalizarP("+codpre+")' value='Finalizar Presupuesto' /></td><td colspan='5' align='center' ><input type='button' id='btnp' value='Ver Preliminar' onclick='Preliminar("+codpre+")' /></td></tr>");
						out.println("</table>");
					
						
					}else{
						//PRESUPUESTO INVENTARIO GENERAL Y INVENTARIO POR SUCURSAL 
						
					}
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("Anupre")){
			
			String codpre=req.getParameter("codpre");
			out.println("<table width='100%'>");
			out.println("<tr><td class='contpre'>Observacion de Anulacion Presupuesto No. "+codpre+"</td></tr>");
			out.println("<tr><td><textarea id='obsanu' cols='80' rows='4' class='rep'></textarea></td></tr>");
			out.println("<tr><td><input type='button' value='Anular' onclick='GuardarAnu("+codpre+")'</td></tr>");
			out.println("</table>");
		}
		
		if(va.equals("GuardarAnu")){
			
			String codpre=req.getParameter("codpre");
			String obs=req.getParameter("ob");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			try {
				mpre.CambiarEstPre(codpre,"4", user, Fecha, Hora, obs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("VerPrexA")){
			rs=mpre.BusPrexA();
			out.println("<table width='100%'>");
			out.println("<tr><td class='cabpre' colspan='11' align='center' ><br> LISTADO <br> </td></tr>");
			out.println("<tr class='contpre' align='center' bgcolor='#DBE4F2'><td>No. Presupuesto </td><td>Tipo de Presupuesto</td><td>Concepto</td><td>Periodo Inicial</td><td>Periodo Final</td><td>Año</td><td>Fecha</td><td>Hora</td><td colspan='3'>Accion</td></tr>");
			try {
				while(rs.next()){
					out.println("<tr class='rep' align='center'><td >"+rs.getString(1)+"</td><td>"+rs.getString(14)+"</td><td>"+rs.getString(13)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td><a href='#' onclick='VDet("+rs.getString(1)+")' >Ver detalle</a></td><td><a href='#' onclick='Aprob("+rs.getString(1)+")' >Aprobar</a></td><td><a href='#' onclick='Rechaza("+rs.getString(1)+")'>Rechazar</a></td></tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("<tr><td  colspan='11' align='center'><br><div id='observaciones' ></div></td></tr>");
			out.println("</table>");
		}
		
		if(va.equals("VistA")){
			// aprobacion
			String codpre=req.getParameter("codpre");
			out.println("<table width='100%'>");
			out.println("<tr><td class='contpre'>Observacion de Aprobacion Presupuesto No. "+codpre+"</td></tr>");
			out.println("<tr><td><textarea id='obsanu' cols='80' rows='4' class='rep'></textarea></td></tr>");
			out.println("<tr><td><input type='button' value='Anular' onclick='GuardarApro("+codpre+")'</td></tr>");
			out.println("</table>");
		}
		
		if(va.equals("VistR")){
			//rechazo
			
			String codpre=req.getParameter("codpre");
			out.println("<table width='100%'>");
			out.println("<tr><td class='contpre'>Observacion de Rechazo Presupuesto No. "+codpre+"</td></tr>");
			out.println("<tr><td><textarea id='obsanu' cols='80' rows='4' class='rep'></textarea></td></tr>");
			out.println("<tr><td><input type='button' value='Anular' onclick='GuardarRec("+codpre+")'</td></tr>");
			out.println("</table>");
		}
		
		if(va.equals("GuardarAprob")){
			String codpre=req.getParameter("codpre");
			String obs=req.getParameter("ob");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			try {
				mpre.CambiarEstPre(codpre,"2", user, Fecha, Hora, obs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("GuardarRec")){
			String codpre=req.getParameter("codpre");
			String obs=req.getParameter("ob");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			try {
				mpre.CambiarEstPre(codpre,"5", user, Fecha, Hora, obs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("Gconcept")){
			String  codpre=req.getParameter("codpre");
			String  concep=req.getParameter("concep");
			mpre.GuardarConcep(codpre,concep);
		}
		
		if(va.equals("tipo")){
			String t=req.getParameter("t");
			if((t.equals("3"))||(t.equals("4"))){
				out.println("<table>");
				out.println("<tr>");
					out.println("<td>");
					out.println("Seleccione : </td><td> <select id='suc' onchange='sucu()' class='rep' ><option value='---'>---</option>");
					rs=mpre.BusSuc();
					try {
						while(rs.next()){
							out.println("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");
						}rs.getStatement().getConnection().close();
						out.println("</select>");
						out.println("&nbsp;</td>");
						out.println("<td><div id='centrocosto' ></div></td>");
						out.println("<td><div id='subccosto' ></div></td>");
						out.println("</tr>");
						out.println("</table>");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}else{
				out.println("");
			}
			
		}
		
		if(va.equals("suc")){
			String s=req.getParameter("s");
			if(s.equals("---")){
				
			}else{
				out.println("<table>");
				out.println("<tr><td>&nbsp; Centro de Costo: </td><td><select id='ccosto' class='rep' onchange='ccosto()' ><option value='todas'>TODAS</option>");
				rs=mpre.Busccosto(s);
				try {
					while(rs.next()){
						out.println("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");
					}rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.println("&nbsp;</td></tr>");
				out.println("</table>");
			}
		}
		
		if(va.equals("cc")){
			String ccost=req.getParameter("ccost");
			out.println("<table><tr><td>&nbsp;Subcentro Costo : </td><td><select id='subcc' class='rep' onchange='subcc()' ><option value='todas' >TODAS</option>");
			rs=mpre.BusSubcc(ccost);
			try {
				while(rs.next()){
					out.println("<option value='"+rs.getString(1)+"' >"+rs.getString(2)+"</option>");
				}rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("<td></tr>");
			out.println("</table>");
		}
		
	}
	
	
	/************************************/
	
	
	public String FormatMoneda(String valor){		
		String temp2="";String temp1=""; String temp3="";
		int ud=1;int logCad = valor.length();
		//System.out.println(" valor es : "+valor);
		//System.out.println(" valor de cadena "+logCad);
		for (int i=logCad-1;i>=0;i--){			
			temp2=valor.substring(i,i+1);
			temp3=temp2;
			temp2+=temp1;
			
			if(ud==3){
				if(temp3.equals(".")){
					if(i!=0){
						//System.out.println(" 1) valor de temp1"+temp1+" valor de temp2"+temp2);
						temp1=""+","+temp1;
						
					}else{
						//System.out.println(" 2) valor de temp1"+temp1+" valor de temp2"+temp2);
						temp1=temp2;
						}ud=0;
					 
				}else{
					if(i!=0){
						//System.out.println(" 3) valor de temp1"+temp1+" valor de temp2"+temp2);
						temp1="."+temp2;
						}else{
							//System.out.println(" 4) valor de temp1"+temp1+" valor de temp2"+temp2);
							temp1=temp2;
							}ud=0;
				}
				
			}else{temp1=temp2;}
			ud++;
		}
		temp1="$ "+temp1;
		return temp1;
	}
	
	
	/**************************************/
}
