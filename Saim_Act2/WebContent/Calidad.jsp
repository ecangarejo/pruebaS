<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Calidad</title>
<script language="javascript" type="text/javascript" src="ajaxseguridad.js"></script>
<link rel="StyleSheet" href="dtree.css" type="text/css" />
<script type="text/javascript" src="dtree.js"></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
</head>
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
%>

<body>
<script> 
//if (history.forward(1)){ 
//location.replace(history.forward(-1)) 

//} 

</script> 
<%
if(codigou.equals("")){%>
	<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");
	window.location.href="Seg_login.jsp";
	</script>
<%}else{
%>
<%
	Conexion con1 = new Conexion();
	ResultSet rs2;
	Statement st2 = null;
	String idu="";
	st2 = con1.conn.createStatement();
	rs2=st2.executeQuery("select usuario from seg_usuario  where usu_codigo="+codigou+"");%>
	<%while(rs2.next()){
		idu=rs2.getString(1);
	}
	rs2.getStatement().close();
	
	ResultSet rs4;
	Statement st4=null;
	st4=con1.conn.createStatement();
	rs4=st4.executeQuery("SELECT COUNT(estado) FROM seg_mensajes WHERE estado=0 AND remitente="+codigou+"");
	int madmin=0;
	if(rs4.next()){
		madmin=rs4.getInt(1);
	}
	rs4.getStatement().close();
	
	%>
	<table width="100%">
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
			<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-Calidad</b></div>
		</td>
	</tr>
	<tr id="principal1" class="style6">
		<td>
		<div>
		<table>
		<tr> 
			<td><br><br>
			<script type="text/javascript">
			<%
			int j = 1; 
			int p = 2;
			String ee, entidad, er;
			int a = 0;
			int est;
			ResultSet rs;
		  	ResultSet rs1 = null;
		    Statement st = null;
		    Statement st1 = null;
		    try{
		    	st = con1.conn.createStatement();
		       	st1 = con1.conn.createStatement();
		       	rs = st.executeQuery("select distinct seg_opciones_menu.opm_codigo,seg_opciones_menu.nombre from seg_menu, seg_opciones_menu,seg_opciones_disponibles,seg_opciones_autorizadas where seg_menu.men_codigo=seg_opciones_menu.men_codigo_fk and  seg_opciones_autorizadas.usu_codigo_fk='"+codigou+"' and seg_opciones_autorizadas.opd_codigo_fk=seg_opciones_disponibles.opd_codigo and seg_opciones_disponibles.opm_codigo_fk=seg_opciones_menu.opm_codigo and seg_menu.nombre='CALIDAD'");%>
		      	d = new dTree('d');
			    d.add(0,-1,'MENU CALIDAD');
				<%while(rs.next()){
					rs1 = st1.executeQuery("select distinct opm.nombre,opd.nombre,opd.url from seg_opciones_disponibles opd,seg_opciones_autorizadas opa,seg_usuario sgu,seg_opciones_menu opm,seg_menu men where sgu.usu_codigo='"+codigou+"' and opa.usu_codigo_fk='"+codigou+"' and men.men_codigo=opm.men_codigo_fk and opm.opm_codigo=opd.opm_codigo_fk and opd.opd_codigo=opa.opd_codigo_fk and opd.opm_codigo_fk='"+rs.getString(1)+"'");
		       	%>
					d.add(<%=j%>,0,'<%=rs.getString(2)%> ','');
					<%while(rs1.next()){%>
						<%%>
						d.add(<%=p%>,<%=j%>,'<%=rs1.getString(2)%>','<%=rs1.getString(3)%>');
						<%p=p+j;%>
					<%}%>
					<% j = p+1;
				}
				rs.getStatement().close();
				rs1.getStatement().close();
				con1.cerrar();
			} catch(SQLException e){
				System.out.println(e);
		        out.println("no se pudo realizar la conexion!<br/>"); 
		    }%>
			document.write(d);
			</script>
			</td>
			<td>
				<br /><br /><br /><br /><br /><br /><br /><br /><br /><br />   
				<div id="imagLa"></div> 
			</td>
		</tr>
		</table>
		</div>
		</td>
	</tr>
	</table>
<%} %>
</body>
</html>