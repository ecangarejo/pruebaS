<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" import="java.util.*" %>
<!-- Los import -->
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css"> 
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Formato Preliminar</title>
<script language="javascript" type="text/javascript" src="ajaxseguridad.js"></script>
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<style type="text/css">
#ResultadosBusqueda{height:336px;  overflow : scroll ; color: #215b8b;}
</style>
<script language="javascript"></script>
<script language="javascript" type="text/javascript"  charset="UTF-8"  src="hic_SeleccionarPacientes.js" ></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" />
<%
String usuario=request.getParameter("CodUsuario");
String CodFormato=request.getParameter("CodFor");
String fecha=request.getParameter("Fecha");
String hora=request.getParameter("Hora");
String CodPac=request.getParameter("CodPac");
String CodAdmision=request.getParameter("CodAdm");
String CodForPac=request.getParameter("CodForPac");
String pend=request.getParameter("pend");

String codigou =(String)session.getAttribute("codusuario");
if(codigou==null)
{
	codigou="";
}
		%>		
				

</head>

<body>
		<%
		if(codigou.equals("")){%>
		
		<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");window.location.href="Seg_login.jsp";</script>
		<%}else{
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
				con.cerrar();
				
				Conexion con1=new Conexion();
				ResultSet rs3;ResultSet rs4;
				ResultSet rs5;ResultSet rs6;
	     %>
<table width="100%" class="contpre">
	<tr align="center" >
	<td >
	<!--  <img src="Imagenes/cab2.JPG" width="100%">-->
	<b>VISTA PRELIMINAR FORMATO</b>
	</td>
	</tr>
	<tr>
	<td>
		<div id="cabecera1" align="center"  >
		<table width="100%" border="0" class="contpre">
			
		<%
				int Contador=0;
				Statement st3 = null;Statement st4 = null;
				Statement st5 = null;Statement st6 = null;
				
				st3=con1.conn.createStatement();
				rs3=st3.executeQuery("SELECT distinct hfr.nombre_formato,har.codigo,hres.fecha,hres.hora,CONCAT(apac.tipo_documento,'-',apac.numero_documento) as Documento,CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) as Nombre,apac.genero,apac.fecha_nacimiento,aen.nombre_entidad as eps,(year(CURDATE())-year(apac.fecha_nacimiento)) - (right(CURDATE(),5) < right(apac.fecha_nacimiento, 5)) as edad FROM hic_formato hfr,hic_area har,hic_pregunta hpr,hic_resultado hres,adm_paciente apac,seg_usuario sgu,seg_datos_personales sdt,adm_convenio acv,adm_entidad aen,hic_formato_area hfa WHERE hres.CodFormato_fk="+CodFormato+" and hpr.codigo=hres.codPreg_fk and har.codigo=hres.codArea_Fk and hfr.codigo=hres.CodFormato_fk and hres.fecha='"+fecha+"'  and hres.hora='"+hora+"' and apac.pac_codigo_paciente=hres.codPac_fk and hres.codPac_fk="+CodPac+" and hres.codUsu_Fk="+usuario+"  and hres.codUsu_Fk=sgu.usu_codigo and sdt.dat_codigo=sgu.dat_codigo_fk and aen.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=apac.conv_numero_contrato_fk and hfa.codigo_area_fk=har.codigo and hfa.codigo_formato_fk=hfr.codigo order by hfa.codigo asc");
				System.out.println("SELECT distinct hfr.nombre_formato,har.codigo,hres.fecha,hres.hora,CONCAT(apac.tipo_documento,'-',apac.numero_documento) as Documento,CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) as Nombre,apac.genero,apac.fecha_nacimiento,aen.nombre_entidad as eps,(year(CURDATE())-year(apac.fecha_nacimiento)) - (right(CURDATE(),5) < right(apac.fecha_nacimiento, 5)) as edad FROM hic_formato hfr,hic_area har,hic_pregunta hpr,hic_resultado hres,adm_paciente apac,seg_usuario sgu,seg_datos_personales sdt,adm_convenio acv,adm_entidad aen,hic_formato_area hfa WHERE hres.CodFormato_fk="+CodFormato+" and hpr.codigo=hres.codPreg_fk and har.codigo=hres.codArea_Fk and hfr.codigo=hres.CodFormato_fk and hres.fecha='"+fecha+"'  and hres.hora='"+hora+"' and apac.pac_codigo_paciente=hres.codPac_fk and hres.codPac_fk="+CodPac+" and hres.codUsu_Fk="+usuario+"  and hres.codUsu_Fk=sgu.usu_codigo and sdt.dat_codigo=sgu.dat_codigo_fk and aen.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=apac.conv_numero_contrato_fk and hfa.codigo_area_fk=har.codigo and hfa.codigo_formato_fk=hfr.codigo order by hfa.codigo asc");
				if(rs3.next()){%>
  		<tr>
		<td><label class="titulopre">NOMBRE PACIENTE:</label> <%=rs3.getString(6) %> </td>
    	<td><label class="titulopre">DOCUMENTO:</label> <%=rs3.getString(5) %></td>
    	<td><label class="titulopre">GENERO:</label> <%=rs3.getString(7) %></td>
    	<td><label class="titulopre">FECHA NACIMIENTO:</label> <%=rs3.getString(8)%><label class="titulopre">&nbsp;&nbsp; EDAD: </label> <%=rs3.getString(10)%></td>
		</tr>
	  	<tr>
	    <td colspan="2"><label class="titulopre">ENTIDAD:</label> <%=rs3.getString(9)%></td>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	  	</tr>	  
		</table>
		</div>
		</td> 
		</tr>

		<tr  id="principal1">  
		<td>
			<div>
				<table width="100%"> 
				<tr>
					<td width="100%">  
						<form id="form1" name="form1">
						<table width="100%" class="style66" >
  						<tr>
    						<td colspan="2"><div align="center" class="style11" id="cabecera2"><%=rs3.getString(1)%></div> </td>
 						</tr>
						<tr align="center">
							<td colspan="2">
							<div align="right" >Bienvenido , <b><%=idu%></b> | <a href="mensajes.jsp?TB_iframe=true&height=520&width=700"   class="thickbox"> Mensajes </a>|<a href="Seguridad_login?accion=cerrar" >--Cerrar Session--</a></div>
							</td>  
						</tr>
		
				<%}
				rs3.getStatement().close();
				
				st4=con1.conn.createStatement();
				rs4=st4.executeQuery("SELECT distinct hfr.nombre_formato,har.codigo,hres.fecha,hres.hora,CONCAT(apac.tipo_documento,'-',apac.numero_documento) as Documento,CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) as Nombre,apac.genero,apac.fecha_nacimiento,aen.nombre_entidad as eps,(year(CURDATE())-year(apac.fecha_nacimiento)) - (right(CURDATE(),5) < right(apac.fecha_nacimiento, 5)) as edad FROM hic_formato hfr,hic_area har,hic_pregunta hpr,hic_resultado hres,adm_paciente apac,seg_usuario sgu,seg_datos_personales sdt,adm_convenio acv,adm_entidad aen,hic_formato_area hfa WHERE hres.CodFormato_fk="+CodFormato+" and hpr.codigo=hres.codPreg_fk and har.codigo=hres.codArea_Fk and hfr.codigo=hres.CodFormato_fk and hres.fecha='"+fecha+"'  and hres.hora='"+hora+"' and apac.pac_codigo_paciente=hres.codPac_fk and hres.codPac_fk="+CodPac+" and hres.codUsu_Fk="+usuario+"  and hres.codUsu_Fk=sgu.usu_codigo and sdt.dat_codigo=sgu.dat_codigo_fk and aen.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=apac.conv_numero_contrato_fk and hfa.codigo_area_fk=har.codigo and hfa.codigo_formato_fk=hfr.codigo order by hfa.codigo asc");
				while(rs4.next()){
					String CodArea=rs4.getString(2);
					st5=con1.conn.createStatement();
					rs5=st5.executeQuery("SELECT har.nombre_area,hpr.nombre_pregunta,replace(hres.resultados,'@','Ã±')as resultados,hpr.unidad,hres.codigo FROM hic_formato hfr,hic_area har,hic_pregunta hpr,hic_resultado hres,adm_paciente apac,seg_usuario sgu,seg_datos_personales sdt WHERE hres.CodFormato_fk="+CodFormato+" and hpr.codigo=hres.codPreg_fk and har.codigo=hres.codArea_Fk and hfr.codigo=hres.CodFormato_fk and hres.fecha='"+fecha+"'  and hres.hora='"+hora+"' and apac.pac_codigo_paciente=hres.codPac_fk and hres.codPac_fk="+CodPac+" and hres.codUsu_Fk="+usuario+"  and hres.codUsu_Fk=sgu.usu_codigo and sdt.dat_codigo=sgu.dat_codigo_fk and har.codigo="+CodArea+"");
					if(rs5.next()){%>
						<tr><td colspan="2"><div align="center"><%=rs5.getString(1)%></div></td></tr>
				<%
					st6=con1.conn.createStatement();
					rs6=st6.executeQuery("SELECT har.nombre_area,hpr.nombre_pregunta,replace(hres.resultados,'@','Ã±')as resultados,hpr.unidad,hres.codigo FROM hic_formato hfr,hic_area har,hic_pregunta hpr,hic_resultado hres,adm_paciente apac,seg_usuario sgu,seg_datos_personales sdt WHERE hres.CodFormato_fk="+CodFormato+" and hpr.codigo=hres.codPreg_fk and har.codigo=hres.codArea_Fk and hfr.codigo=hres.CodFormato_fk and hres.fecha='"+fecha+"'  and hres.hora='"+hora+"' and apac.pac_codigo_paciente=hres.codPac_fk and hres.codPac_fk="+CodPac+" and hres.codUsu_Fk="+usuario+"  and hres.codUsu_Fk=sgu.usu_codigo and sdt.dat_codigo=sgu.dat_codigo_fk and har.codigo="+CodArea+"");
					while(rs6.next()){%>
		 		<tr>
    			<td colspan="2"></td>
    			</tr>

  				<tr>
    				<td width="199" height="123"><label><%=rs6.getString(2) %>
      				<input name="txtCodResultado" type="hidden" id="txtCodResultado" value="<%=rs6.getString(5)%>" />
    				</label></td>
   					 <td width="755"><textarea name="txtRespuesta" cols="80" rows="10" id="txtRespuesta" onblur='ActualizarResultados()' ><%=rs6.getString(3)%></textarea></td>
 			 </tr>
			<%
				Contador=Contador+1;
				}
				rs6.getStatement().close();
			}
				rs5.getStatement().close();
			}
			rs4.getStatement().close();
			
			%>
		
 			 <tr>
			<input name="txtTotal" type="hidden" id="txtTotal" value="<%=Contador%>" />
			<input name="CodAdm" type="hidden" id="CodAdm" value="<%=CodAdmision%>" />
   			 <td colspan="2"><div align="center"></div></td>
    		</tr>
			</table>
			<input name="txtCodusuario" type="hidden" id="txtCodusuario" value="<%=codigou %>"/>
			</form></td></tr>
			
			</table>
				<br>
			<div align="center"><input name="guardarfor" type="button" value="Guardar Formato"  onclick="guardarf('<%=CodAdmision%>','<%=CodFormato %>','<%=CodPac %>','<%=usuario %>','<%=pend %>','<%=CodForPac %>')"/><input name="eliminarfor" type="button" value="Eliminar Formato"  onclick="ClearFor('<%=CodForPac %>','<%=usuario %>','<%=pend %>')" /></div>
				</div>
			</td>
			</tr>
</table>
			
<%} %>
</body>
</html>
