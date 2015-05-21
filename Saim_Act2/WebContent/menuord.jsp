<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="STYLESHEET" type="text/css" href="estilo1.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Menu Principal</title>
<script language="javascript" type="text/javascript" src="ajaxseguridad.js"></script>
<link rel="StyleSheet" href="dtree.css" type="text/css" />
	<script type="text/javascript" src="dtree.js"></script>
</head>
<%
String codi =(String)session.getAttribute("codusuario");

if(codi==null){
	codi="";
}
%>
<body>
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
	    	   Conexion con1=new Conexion();
	       	st = con1.conn.createStatement();
	       	st1 = con1.conn.createStatement();
	       	rs=st.executeQuery("select distinct seg_opciones_menu.opm_codigo,seg_opciones_menu.nombre from seg_menu, seg_opciones_menu,seg_opciones_disponibles,seg_opciones_autorizadas where seg_menu.men_codigo=seg_opciones_menu.men_codigo_fk and  seg_opciones_autorizadas.usu_codigo_fk='"+codi+"' and seg_opciones_autorizadas.opd_codigo_fk=seg_opciones_disponibles.opd_codigo and seg_opciones_disponibles.opm_codigo_fk=seg_opciones_menu.opm_codigo and seg_menu.nombre='ORDEN DE COMPRA'");%>
	      	 d = new dTree('d');
		        d.add(0,-1,'');
				<%while(rs.next()){
		    	 rs1=st1.executeQuery("select distinct opm.nombre,opd.nombre,opd.url from seg_opciones_disponibles opd,seg_opciones_autorizadas opa,seg_usuario sgu,seg_opciones_menu opm,seg_menu men where sgu.usu_codigo='"+codi+"' and opa.usu_codigo_fk='"+codi+"' and men.men_codigo=opm.men_codigo_fk and opm.opm_codigo=opd.opm_codigo_fk and opd.opd_codigo=opa.opd_codigo_fk and opd.opm_codigo_fk='"+rs.getString(1)+"'");
	       	%>
					d.add(<%=j%>,0,'<%=rs.getString(2)%> ','');
					<%while(rs1.next()){%>
					d.add(<%=p%>,<%=j%>,'<%=rs1.getString(2)%>','<%=rs1.getString(3)%>');
					<%p=p+j;%>
				<%}//rs1.close();%>
			 	 <%j=p+1;
				}
				rs1.getStatement().close();
				rs.getStatement().close();
				con1.cerrar();
				//con.cerrar();
				 }catch(SQLException e){
					 System.out.println(e);
	      	}
%>
		document.write(d);
	</script>
</body>
</html>