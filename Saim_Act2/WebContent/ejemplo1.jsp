
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="adm_bean.Area"%>
<%@ page import="adm_bean.SubArea"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
<script type="text/javascript">
var peticion = false;
var  testPasado = false;
try 
{
        peticion = new XMLHttpRequest();
    }
    catch (trymicrosoft) 
    {
    try
    {
        peticion = new ActiveXObject("Msxml2.XMLHTTP");
    } 
    catch (othermicrosoft) 
    {
        try 
        {
            peticion = new ActiveXObject("Microsoft.XMLHTTP");
        } 
        catch (failed) 
        {
        peticion = false;
        }
    }
}

if (!peticion)
alert("ERROR AL INICIALIZAR!");
 
function cargarCombo (url, comboAnterior, element_id) 
{
    //Obtenemos el contenido del div
    //donde se cargaran los resultados
    var element =  document.getElementById(element_id);
    //Obtenemos el valor seleccionado del combo anterior
    var valordepende = document.getElementById(comboAnterior)
    var x = valordepende.value
    //construimos la url definitiva
    //pasando como parametro el valor seleccionado
    var fragment_url = url+'?id_calificador='+ x ;
    element.innerHTML = '<img src="imagenes/loading.gif" />'; //opcional
    //abrimos la url
    peticion.open("GET", fragment_url); 
    peticion.onreadystatechange = function() 
    {
        if (peticion.readyState == 4) 
        {
            //escribimos la respuesta
            element.innerHTML = peticion.responseText;
        } 
    } 
   peticion.send(null); 
} 
</script>
<span class="Estilo5"><select name="calificador" 
    onchange="javascript:cargarCombo('ejemplo2.jsp', 'calificador', 'div_subcalificador')" id="calificador">
</span>
<% LinkedList ls =null;
ls = (LinkedList)request.getSession().getAttribute("list");
Area p = new Area();
for(int i= 0; i<ls.size();i++){
	p = (Area)ls.get(i);	
%>
<option value="<%= p.getCodigo() %>">
<% out.println(p.getNombre()); %>

</option>
<%} %>

</select>

<div id="div_subcalificador">
