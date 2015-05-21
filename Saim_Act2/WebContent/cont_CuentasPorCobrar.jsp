<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<html xmlns='http://www.w3.org/1999/xhtml'>
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %> 
<head>
<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
<link rel='STYLESHEET' type='text/css' href='estilo_lab1.css'>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Cuentas por Cobrar</title>
<style type='text/css'>
<!--
.style13 {color: #FFFFFF}
-->
</style>
<script language=javascript src="cont_Facturas.js"></script>
<script language="javascript">
function fecha(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  
	  var dia = time1.getDate()

	  var temp1 = "" + ((dia < 10) ? "0" : "") + dia
	  temp1 += ((mes < 10) ? "/0" : "/") + mes
	  temp1 += ((año < 10) ? "0" : "/") + año
	 document.getElementById('txtFechaPago').value = temp1
	// id = setTimeout("fecha()",1000)	 
		  		}
</script>
</head>

<body onload="fecha();SinTerminar()">
<%String codigou =(String)session.getAttribute("codusuario");
%>
<%
Conexion con=new Conexion();
	ResultSet rs2;
	Statement st2 = null;
	   String idu="";         
	      	
	        st2 = con.conn.createStatement();
	       	
	       	rs2=st2.executeQuery("select usuario from seg_usuario  where usu_codigo="+codigou+"");%>
				<%while(rs2.next()){
					
		        idu=rs2.getString(1);
				}
				rs2.getStatement().close();
				
				ResultSet rs6;
				Statement st6=null;
				st6=con.conn.createStatement();
				rs6=st6.executeQuery("SELECT COUNT(estado) FROM seg_mensajes WHERE estado=0 AND remitente="+codigou+"");
				int madmin=0;
				if(rs6.next()){
					madmin=rs6.getInt(1);
				}
				rs6.getStatement().close();
				con.cerrar();
	       	%>

<%
String cod=request.getParameter("codigo");
if(codigou==null){
	codigou="";
}
if(cod==null){
	cod="";
}

if(codigou.equals("")){%>
<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");
window.location.href="Seg_login.jsp";
</script>
<%}else{%>
<form>
<table width="100%" border='0'>
<tr >
<td>
	  <table width="100%">
			<tr>
			<td>
			<div > <a  href="mensajes.jsp?TB_iframe=true&height=520&width=700"  class="thickbox"> Mensajes <font color=red size=medium><b><%=madmin %></b></font></a> <%if (madmin>0){ %><bgsound src="Imagenes/INNERMK.WAV" loop="2"><img src="Imagenes/sobr0001.gif" /><%}%></div>
			</td>
			<td align="right">
			<div align="right" id="usuario" align="right" class="style11">Bienvenido,<b><%=idu%></b> |<a href="Seguridad_login?accion=cerrar&CodUsu=<%=codigou %>" style="color: white">--Cerrar Session--</a></div>
			</td>
			
		</tr>
	</table>
</td>
</tr>

 
<tr>  
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Contabilidad.jsp">Contabilidad</a>-Cuentas Por Pagar-Cuentas Por Cobrar</b></div> 
</td>
</tr>
</table>


  <table border='0' width='100%' ><tr><td colspan='7' class='style12'><div id='cabecera2' class='style11' align='center'>
    <p>PAGO DE FACTURAS </p>
  </div></td></tr>
	
    
	<tr>
	  <td width='13%' class='style12'>ENTIDAD</td><td colspan='4'><select name='cmbEntidad' id='cmbEntidad'>
	    <option value='Seleccione'>Seleccione</option>
      <% 
       ResultSet rs3=null;
		 Statement st3 = null;  
		 try{
		 	Conexion con2=new Conexion();
		 	st3 = con2.conn.createStatement();
		 	rs3=st3.executeQuery("SELECT ent_nit,nombre_entidad FROM adm_entidad ORDER BY nombre_entidad");%>
		 	<%while(rs3.next()){%>
			<option value="<%=rs3.getString(1)%>"><%=rs3.getString(2) %></option>
		 	<%}
		 	rs3.getStatement().close();
		 	con2.cerrar();
		}catch(SQLException e){
			 
			 System.out.println(e);
		out.println("no se pudo realizar la conexion!<br/>"); 
		}%>



	    </select>Busqueda Agrupada? <input type="checkbox" name='checkbox' value='checkbox' id='agrupado' />
	  </td><td width='20%' class='style12'>&nbsp;</td>
	  <td width='6%'>&nbsp;</td></tr>
	
	<tr>
	  <td class='style12'>MOVIMIENTO</td>
	  <td width='20%'><select name='cmbConcepto' id='cmbConcepto'>
	    <option value='Seleccione'>Seleccione</option>

  <% 
       ResultSet rs4=null;
		 Statement st4 = null;  
		 try{
		 	Conexion con4=new Conexion();
		 	st4 = con4.conn.createStatement();
		 	rs4=st4.executeQuery("SELECT * FROM cont_tipo_concepto");%>
		 	<%while(rs4.next()){%>
			<option value="<%=rs4.getString(1)%>"><%=rs4.getString(2) %></option>
		 	<%}
		 	rs4.getStatement().close();
		 	con4.cerrar();
		}catch(SQLException e){
			 
			 System.out.println(e);
		out.println("no se pudo realizar la conexion!<br/>"); 
		}%>
      </select>	  </td>
	  <td width='20%' class='style12'>FECHA (dd/mm/AAAA)</td>
	<td width='7%' class='style12'><input name='txtFechaPago' type='text' id='txtFechaPago' onKeyUp="masca(this,'/',patron,true)"></td>
	<td class='style12'>CONCEPTO</td>
	
	<td class='style12'><textarea name='txtObservacionRC' id='txtObservacionRC' cols='35' rows='3'></textarea></td><td>&nbsp;</td></tr>
	<tr>
	  <td class='style12'>FACTURA</td>
	  <td><input name='txtFactura' type='text' id='txtFactura' onkeyUp='AutoCompletaFact()'><div id='autoCompletaFactu'></div></td>
	  <td class='style12'>SALDO</td>
	  <td class='style12'><input name='txtSaldo' type='text' id='txtSaldo' readonly=""></td>
	  <td class='style12'>VALOR</td>
	  <td class='style12'><input name='txtAbono' type='text' id='txtAbono'></td>
	  <td><input name='btnIngresar' type='button' id='btnIngresar' value='Ingresar' onclick='GuardarReciboCajaFactu()'></td>
    </tr>
	<tr>
	  <td class='style12'>&nbsp;</td>
	  <td colspan='4'><div id='DocAfectados'><input name='txtCodReciboCaja' type='hidden' id='txtCodReciboCaja' /></div></td>
	  <td class='style12'><div id=ResumenCaja></div></td>
	  <td>&nbsp;</td>
    </tr>
	
  <tr>
    <td class='style12' >FORMA PAGO </td>
    <td class='style12' ><select name='cmbFormaPago' id='cmbFormaPago' ><option value='Seleccione'>Seleccione</option>

  <% 
       ResultSet rs5=null;
		 Statement st5 = null;  
		 try{
		 	Conexion con5=new Conexion();
		 	st5 = con5.conn.createStatement();
		 	rs5=st5.executeQuery("SELECT * FROM cont_tipo_pago where tipo=1 and estado=0");%>
		 	<%while(rs5.next()){%>
			<option value="<%=rs5.getString(1)%>"><%=rs5.getString(2) %></option>
		 	<%}
		 	rs5.getStatement().close();
		 	//con5.cerrar();
		}catch(SQLException e){
			 
			 System.out.println(e);
		out.println("no se pudo realizar la conexion!<br/>"); 
		}%>
    </select></td>
    <td class='style12' >VALOR PAGADO<input name='txtValorPagado' type='text' id='txtValorPagado'></td>

    <td class='style12' >BANCO<select name='cmbBanco' id='cmbBanco'><option value='Seleccione'>Seleccione</option>
 <% 
       ResultSet rs7=null;
		 Statement st7 = null;  
		 try{
		 	Conexion con7=new Conexion();
		 	st7 = con7.conn.createStatement();
		 	rs7=st7.executeQuery("SELECT * FROM cont_bancos WHERE  estado='Activo'");%>
		 	<%while(rs7.next()){%>
			<option value="<%=rs7.getString(1)%>"><%=rs7.getString(2) %></option>
		 	<%}
		 	rs7.getStatement().close();
		 	con7.cerrar();
		}catch(SQLException e){
			 
			 System.out.println(e);
		out.println("no se pudo realizar la conexion!<br/>"); 
		}%>
    </select></td>
    <td ><input name='btnAsignarTipPago' type='button' id='btnAsignarTipPago' value='Ingresar' onclick='AsignarTipoPago()'></td>
    <td >&nbsp;</td>
  </tr>

  <tr>
    <td class='style12' >&nbsp;</td>
    <td colspan='4' ><div id='FormaPago'><input id='txtValorPagadoR' type='hidden' value="0" /></div></td>
    <td colspan='4' ><table><tr><td class='style12'>Deducciones</td></tr>

							<tr><td><select name='cmbDeducciones' id='cmbDeducciones'>
      <option value='Seleccione'>Seleccione</option>

	  <% 
      // ResultSet rs5=null;
	//	 Statement st5 = null;  
		 try{
		 	Conexion con5=new Conexion();
		 	st5 = con5.conn.createStatement();
		 	rs5=st5.executeQuery("SELECT * FROM cont_tipo_pago where tipo=0 and estado=0");%>
		 	<%while(rs5.next()){%>
			<option value="<%=rs5.getString(1)%>"><%=rs5.getString(2) %></option>
		 	<%}
		 	rs5.getStatement().close();
		 	con5.cerrar();
		}catch(SQLException e){
			 
			 System.out.println(e);
		out.println("no se pudo realizar la conexion!<br/>"); 
		}%>
    </select> 

</td></tr>

<tr><td class='style12'>Valor: <input name='txtValorDeduccion' type='text' id='txtValorDeduccion' /> </td></tr>
<tr><td><input name='btnDeducir' type='button' id='btnDeducir' value='Deducir' onclick='IngresarDeduccion()'></td></tr>
<tr><td><div id='Dedu'>
<input name='txtTotalValorDeduccion' type='hidden' id='txtTotalValorDeduccion' value='0' />
</div></td></tr></table></td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td class='style12' >&nbsp;</td>
    <td colspan='4' align='center' ><input name='btnFinalizarRC' type='button' id='btnFinalizarRC' value='Finalizar' onclick='FinalizarRC()'></td>
    <td >&nbsp;</td>
    <td ><input name='txtCodusuario' type='hidden' id='txtCodusuario' value="<%=codigou %>"/><input name='txtCodFactura' type='hidden' id='txtCodFactura' /></td>
  </tr>
  </table>
	
</form>
<%} %>
</body>
</html>
