<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Reporte de Auxiliar de Contabilidad General</title>
		
		<script language="javascript" type="text/javascript" src="ajaxseguridad.js"></script>
<script language="javascript" type="text/javascript" src="cont_Reportes.js"></script>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
	
		
		
		
<%
	//String CodUsuario= request.getParameter("CodUsuario");
	String tiporepor=request.getParameter("tiporepor");
	String RC1=request.getParameter("RC1");
	String RC2=request.getParameter("RC2");
	String MC=request.getParameter("MC");
	String AC=request.getParameter("AC");
	String MC2=request.getParameter("MC2");
	String TDOC=request.getParameter("TDOC");
	String Suc="";
	String ccosto="";
	String texto="";
	if(tiporepor.equals("1")){
			Suc=request.getParameter("Suc");
			System.out.println("Datos recibidos de ConsultarRepAuxiliarSucursalyCcosto "+RC1+" "+RC2+" "+MC+" "+AC+" "+MC2+" "+TDOC+" "+tiporepor);
			texto="REPORTE AUXILIAR DE CONTABILIDAD  POR SUCURSAL Y CUENTA ";
	}else{

		Suc=request.getParameter("Suc");
		ccosto=request.getParameter("ccosto");
		System.out.println("valor de ccosto "+ccosto+" en el jsp");
		texto="REPORTE DE AUXILIAR DE CONTABILIDAD POR SUCURSAL - CUENTA DE COBRO Y CUENTA ";
		System.out.println("Datos recibidos de ConsultarRepAuxiliarSucursalyCcosto "+RC1+" "+RC2+" "+MC+" "+AC+" "+MC2+" "+TDOC+" "+tiporepor);
	}
	
	//String[] detnivelSeleccionado = nivelSeleccionado.split("_");
	//int j=0;
	//String nivel="";
	//for (int i = 0; i < detnivelSeleccionado.length; i++) {
	//	nivel
	//}
	
	
%>
<script language="javascript" type="text/javascript" >
function mostrarep(){

	
	var RC1='<%=RC1%>';
	
	var RC2='<%=RC2%>';
	//alert("1");
	var MC='<%=MC%>';
	var MC2='<%=MC2%>';
	var TDOC='<%=TDOC%>';
	var Suc='<%=Suc%>';
	var ccosto='<%=ccosto%>';
	var tiporepor='<%=tiporepor%>';
	//alert(MC); 
	//alert("2");
	var AC=<%=AC%>;
	//alert("3");
	
	//alert("4");
	//alert(ccosto);
	vistaRepAuxiliarSucyCCosto(RC1, RC2, MC, AC,MC2,TDOC,Suc, tiporepor,ccosto);
}

</script>

</head>

<body onload=mostrarep()>
<%
	
		
	
		//<a href="exportarfact.jsp?Fechai=<%=Fechai&Fechaf=<%=Fechaf" >Exportar a Excel</a>
		%>
		
		<br>


		<div id="reporte" align="center" width='100%'>
		
		<%		
     
		%></div>



		
	
