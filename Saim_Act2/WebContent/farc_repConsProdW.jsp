<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Reporte Por Producto</title>
		
		<script language="javascript" type="text/javascript" src="farc_Consultas.js"></script>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
	
		
		
		
<%
	//String fi= request.getParameter("fi");
	String tano=request.getParameter("Iano");
	String tmeses=request.getParameter("Imes");
	String tdia=request.getParameter("Idia");
	String ftano=request.getParameter("Fano");
	String ftmeses=request.getParameter("Fmes");
	String ftdia=request.getParameter("Fdia");
	String bodega=request.getParameter("bodega");
	String cm=request.getParameter("cm");
	String treporte=request.getParameter("treporte");
	//String ftent=request.getParameter("ftent");
//	String Fechai=tano+"-"+tmeses+"-"+tdia;
	//String Fechaf=ftanos+"-"+ftmeses+"-"+ftdia; 
	//String pend=request.getParameter("pend");
	
%>
<script language="javascript" type="text/javascript" >
function mostrarepc(){
	//alert();
	var tano=<%=tano%>;
	var tmeses=<%=tmeses%>;
	var tdia=<%=tdia%>;
	var ftano=<%=ftano%>;
	var ftmeses=<%=ftmeses%>;
	var ftdia=<%=ftdia%>;
	var bodega1="<%=bodega%>";
	var cm=<%=cm%>;
	var treporte=<%=treporte%>;
	//alert(bodega1);
	//alert(treporte);
	if((bodega1=="todas")&&(treporte=="1")){
		VistarepConsProd(tano, tmeses, tdia, ftano, ftmeses, ftdia,cm);
	}else{
		if((bodega1!="todas")&&(treporte=="1")){
			VistarepConsProdBod(tano, tmeses, tdia, ftano, ftmeses, ftdia,cm, bodega1);
			}else{
				if((bodega1=="todas")&&(treporte=="2")){
					VistaDispPacProd(tano, tmeses, tdia, ftano, ftmeses, ftdia,cm);
				}else{
						if((bodega1!="todas")&&(treporte=="2")){
							VistaDispPacProdB(tano,tmeses,tdia,ftano,ftmeses,ftdia,bodega1,cm);
							}else{
								if(treporte=="3"){
									//alert("entrado a 3");
									VistaEntProd(tano,tmeses,tdia,ftano,ftmeses,ftdia,bodega1,cm);
									}else{
										if(treporte=="4"){
											VistaSalProd(tano,tmeses,tdia,ftano,ftmeses,ftdia,bodega1,cm);
										}else{
											if(treporte=="5"){
												VistaTrasProd(tano,tmeses,tdia,ftano,ftmeses,ftdia,bodega1,cm);
											}else{
												if(treporte=="6"){
													VistaDevProd(tano,tmeses,tdia,ftano,ftmeses,ftdia,bodega1,cm);
												}		
											}
										}
									}
								}
				}
			}
	}
}	

</script>

</head>

<body onload="mostrarepc()" >

	
		
		
	
		
		<br><br>
		<table   align="center" width="95%">
		<tr><td class=style66 ><div align="center">
		<% if(treporte.equals("1")){ 
			out.println("REPORTE DE CONSUMO POR PRODUCTO");
			}else{
				if(treporte.equals("2")){
					out.println("RELACION DE DISPENSACION A PACIENTES POR PRODUCTO");
				}else{
					if(treporte.equals("3")){
						out.println("RELACION DE ENTRADAS POR PRODUCTO");
					}else{
						if(treporte.equals("4")){
							out.println("RELACION DE SALIDAS POR PRODUCTO ");
						}else{
							if(treporte.equals("5")){
								out.println("RELACION DE TRASLADOS POR PRODUCTO ");
							}else{
								if(treporte.equals("6")){
									out.println("RELACION DE DEVOLUCIONES POR PRODUCTO ");
								}
							}
						}
					}
				}
			}%> </div></td></tr>
		<tr><td>
		<div id="reporte" align="center"></div>
		</td></tr>
		</table>
