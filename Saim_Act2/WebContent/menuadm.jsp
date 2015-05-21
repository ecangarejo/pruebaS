<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Menu Principal</title>
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<script language="javascript" type="text/javascript" src="ajaxseguridad.js"></script>
<link rel="StyleSheet" href="dtree.css" type="text/css" />
	<script type="text/javascript" src="dtree.js"></script>
    <script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<%
String codigou =(String)session.getAttribute("codusuario");

if(codigou==null){
	codigou="";
}

%>

<body id="back">
<script> 
/*if (history.forward(1)){ 
location.replace(history.forward(-1)) 

}*/ 

</script> 
<%

if(codigou.equals("")){%>
<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");
window.location.href="Seg_login.jsp";
</script>

<%}else{
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
	       	%>
<table width="100%">
	<tr align="right">
		<td>
			 <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"  style="color: white" > --Cerrar Session--</a></div>
		</td>
	</tr>
	
	<tr>
		<td>
			<div id="cabecera1" align="center"  class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-Admisiones</b></div>
		</td>
	</tr>	
	<tr id="principal1" class="style6">
		<td>				<table>	
						<tr> 
								<td>									
					                 <br><br>
<script type="text/javascript">
<%
				int j=1;
				int p=2;
String ee, entidad, er;
	int a=0;
	int est;
	ResultSet rs;	
	  ResultSet rs1=null;
	       Statement st = null;
	     Statement st1= null;       
	       try{
	       	st = con.conn.createStatement();
	       	st1 = con.conn.createStatement();
	       	rs=st.executeQuery("select distinct seg_opciones_menu.opm_codigo,seg_opciones_menu.nombre from seg_menu, seg_opciones_menu,seg_opciones_disponibles,seg_opciones_autorizadas where seg_menu.men_codigo=seg_opciones_menu.men_codigo_fk and  seg_opciones_autorizadas.usu_codigo_fk='"+codigou+"' and seg_opciones_autorizadas.opd_codigo_fk=seg_opciones_disponibles.opd_codigo and seg_opciones_disponibles.opm_codigo_fk=seg_opciones_menu.opm_codigo and seg_menu.nombre='ADMISIONES'");%>
	        d = new dTree('d');
		        d.add(0,-1,'');
				<%while(rs.next()){
		    	 rs1=st1.executeQuery("select distinct opm.nombre,opd.nombre,opd.url from seg_opciones_disponibles opd,seg_opciones_autorizadas opa,seg_usuario sgu,seg_opciones_menu opm,seg_menu men where sgu.usu_codigo='"+codigou+"' and opa.usu_codigo_fk='"+codigou+"' and men.men_codigo=opm.men_codigo_fk and opm.opm_codigo=opd.opm_codigo_fk and opd.opd_codigo=opa.opd_codigo_fk and opd.opm_codigo_fk='"+rs.getString(1)+"'");
	       	%>
					d.add(<%=j%>,0,'<%=rs.getString(2)%> ','');
					<%while(rs1.next()){%>
					d.add(<%=p%>,<%=j%>,'<%=rs1.getString(2)%>','<%=rs1.getString(3)%>');
					<%p=p+j;%>
				<%}%>
			 	 <% j=p+1;
				}
				rs1.getStatement().close();
				rs.getStatement().close();
				 }catch(SQLException e){					 
					 System.out.println(e);
	            out.println("no se pudo realizar la conexion!<br/>"+e); 
	      	}   

%>
		document.write(d);
	</script>
								</td>
								<td>
<br><br><br><br><br><br><br><br><br><br><br><br><br>       
									<div id="imagLa" style="margin-left:200px"></div>
								</td>
							</tr>
						</table>
<%} %>
</body>
</html>