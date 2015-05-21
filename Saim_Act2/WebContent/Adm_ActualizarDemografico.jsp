<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="adm_logica.Conexion" import="java.sql.*,java.util.*" %>
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
<!--Para poder convertir la direccion-->
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;language=es"></script>
<!------------------------------------------------------------------------------------------------------------ by: Greys Hernandez  -->

<meta http-equiv="Content-Type" content="text/html; ISO-8859-1" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<script language=javascript src="Validaciones.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Actualizar Demografico</title>
<style type="text/css">
#buscar { background:url(Imagenes/Buscar.jpg) no-repeat; border:4; width:76px; height:30px; }
#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias {width:565px; Cursor : pointer; border:1px solid black; display:none; margin-left: 0px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
</style>
<script type="text/javascript">
// CONVIERTE LA DIRECCION EN LONGITUD Y LATITUD PARA PODER REALIZAR EL PROCESO DE GEOREFERENCIACION
var geocoder;
var map;

 function initialize(){
  geocoder = new google.maps.Geocoder();
  var latlng = new google.maps.LatLng(11.001723, -74.813172);
  var mapOptions = {
    zoom: 16,
    center: latlng,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  }
  map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);

}
 // ubicar punto
 function punto(){
	 longitud=document.getElementById("txtlongitud").value;
	 latitud=document.getElementById("txtlatitud").value;
	 nombre=document.getElementById("txtnombre").value;
	 Papellido=document.getElementById("txtpapellido").value;
	 nombrecompleto=nombre+" "+Papellido;
	    
	 var marker = new google.maps.Marker({
         map: map,
         position: new google.maps.LatLng(latitud,longitud),
         title: nombrecompleto
        //icon: 'http://gmaps-samples.googlecode.com/svn/trunk/markers/green/blank.png'
     });    
     map.setCenter(marker.getPosition());
}


 function codeAddress(){

		optCiudad=document.getElementById("cbmun").selectedIndex;
		ciudad=document.getElementById("cbmun").options[optCiudad].text;
		    
		optDepartamento=document.getElementById("cbdep").selectedIndex;   
		departamento=document.getElementById("cbdep").options[optDepartamento].text;

		optPais=document.getElementById("cbnacionalidad").selectedIndex; 
		pais=document.getElementById("cbnacionalidad").options[optPais].text;
		
		direccion=document.getElementById("txtdiregeorre").value;	
		
	    var address=direccion+" "+ciudad+"-"+departamento+" "+pais;

	    nombre=document.getElementById("txtnombre").value;
	    Papellido=document.getElementById("txtpapellido").value;
	    nombrecompleto=nombre+" "+Papellido;
	 geocoder.geocode( { 'address': address}, function(results, status) {

	     if (status == google.maps.GeocoderStatus.OK) {
			                
			  //  map.setCenter(results[0].geometry.location);  
			   	   		    
			    var marker = new google.maps.Marker({
		            map: map,
		            position: new google.maps.LatLng(results[0].geometry.location.lat(),results[0].geometry.location.lng()),
		            title: nombrecompleto
		           //icon: 'http://gmaps-samples.googlecode.com/svn/trunk/markers/green/blank.png'
	            });    
			    updatePosition(results[0].geometry.location);  
			    map.setCenter(marker.getPosition());
	         		     
	 	 } else {
	 	 	 
	 	   	if (status==google.maps.GeocoderStatus.ZERO_RESULTS){
	 	   		   alert("Geocode no tuvo éxito por las siguientes razones: la codificación geográfica se ha realizado correctamente pero no ha devuelto ningún resultado. Esto puede ocurrir si en la codificación geográfica se incluye una dirección (address) inexistente");
	  	     }else{
	  	    	 if (status==google.maps.GeocoderStatus.OVER_QUERY_LIMIT){
	                   alert("Geocode no tuvo éxito por las siguientes razones: se ha excedido el cupo de solicitudes");
	             }else{
	                   
	              	 if(status==google.maps.GeocoderStatus.REQUEST_DENIED){
	                       alert("Geocode no tuvo éxito por las siguientes razones: se ha denegado la solicitud por algún motivo");
	                 }else{
	                  	 if(status==google.maps.GeocoderStatus.INVALID_REQUEST){
	                           alert("Geocode no tuvo éxito por las siguientes razones: no se ha especificado la solicitud (address)");
	                     }
	                 }

	            }
	        }
	  	     //  alert("Geocode no tuvo éxito por las siguientes razones: " + status);
	   }
	    
	 });

	}

 //actualiza mis cajas ocultas con la longitud y la latitud
 function updatePosition(latLng){
	document.getElementById("txtlongitud").value=latLng.lng();    
    document.getElementById("txtlatitud").value=latLng.lat();    
 }
 function mapa(){
	 initialize();
	 codeAddress();
 }
 //actualiza mis cajas ocultas con la longitud y la latitud
 function Link(){
	//window.close();
	// window.location.href="geo_vista_geografica.jsp";
	window.open("http://localhost:8080/Saim/geo_vista_geografica.jsp");
}


 function aparece(){
     div = document.getElementById("efecto");
 	    if (div.style.display=="none"){
 			   div.style.display=""; 
 	    } 
  }
 	 
  function desaparece(){
 	div = document.getElementById("efecto");
 		if (div.style.display!="none"){
 			   div.style.display="none";
 			   limpiar();
 		} 
 	 
  }
 </script>
<script type="text/javascript">
function demografico(e){	

	var codpac,nombre,papellido,sapellido,cedula,fecha,tipo,afiliacion,nivel,genero,nacionalidad,direccion,telefijo,teleofi,telecel,ocupacion,empresa,zona,religion,estadocivil,raza,estrato,email,codimun,numcontrato,longitud,latitud,barrio;
	tipo=e.tipodoc.value;

	var de=e.cbdep.selectedIndex;
	dep=e.cbdep.options[de].text;

	var mun=e.cbmun.selectedIndex;
	muni=e.cbmun.options[mun].text;

	var afi=e.select2.selectedIndex;
	afiliacion=e.select2.options[afi].text;
	
	var ni=e.cbnivel.selectedIndex;
	nivel=e.cbnivel.options[ni].text;

	var ge=e.select4.selectedIndex;
	genero=e.select4.options[ge].text;
	
	var na=e.cbnacionalidad.selectedIndex;
	nacionalidad=e.cbnacionalidad.options[na].text;
	
	var zo=e.cbzona.selectedIndex;
	zona=e.cbzona.options[zo].text;
	//alert("ok");
	var es=e.cmbEstadocivil.selectedIndex;
	estadocivil=e.cmbEstadocivil.options[es].text;
	
	var est=e.select7.selectedIndex;
	estrato=e.select7.options[est].text;

	nombre=e.txtnombre.value;

	papellido=e.txtpapellido.value;

	sapellido=e.txtsapellido.value;
	//alert(nombre+" "+papellido+" "+sapellido);
    cedula=e.txtnumdocu.value;

    fecha=e.txtfechanaci.value;

   direccion=encodeURIComponent(e.txtdire.value);
   //direccion=document.getElementById("txtdire").value;
  //  alert("direccion nueva"+" "+direccion);

 /*   var i;
    for(i=0;i<direccion.length;i++){
      direccion=direccion.replace('#','Nº');
    }*/

    telefijo=e.txtelefijo.value;

    teleofi=e.txteleoficina.value;

    telecel=e.txtcelular.value;

    ocupacion=e.txtocupacion.value;

    empresa=e.txtemp.value;

    religion=e.txtreligion.value;

    raza=e.txtraza.value;
  
    email=e.txtemail.value;

    codimun=e.txtcodmun.value;

    numcontrato=e.txtcodigoconv.value;

    codpac=e.txtcodigopac.value;

    longitud=e.document.getElementById("txtlongitud").value;
    latitud=document.getElementById("txtlatitud").value;
    barrio=document.getElementById("txtbarrio").value;

    var codEnt=e.txtcodentidad.value;
    //alert(codEnt)

    if((nivel=="SELECCIONE...")){
    	nivel="";
    }
    if((fecha=="")||(fecha=="aaaa/mm/dd")){
    	fecha="0000/00/00";
    }
    
    if((telefijo=="")||(teleofi=="")||(telecel=="")){
    	telefijo="0";
    	teleofi="0";
    	telecel="0";
    }

    if(fecha==""){
    	alert("Falta Ingresar La Fecha!!!");
    }
    
  	if(direccion==""){
		alert("Falta Ingresar La Direccion!!!");
		}
	
	if(cedula==""){
		alert("Falta Ingresar La Identificacion!!!");
		}
	if(papellido==""){
		alert("Falta Ingresar El Primer Apellido!!!");
		}
	if(nombre==""){
		alert("Falta Ingresar El Nombre!!!");
		}
	if(afiliacion=="SELECCIONE..."){
		alert("Falta Seleccionar El Tipo de Afiliacion!!!");
		}
	if(genero=="SELECCIONE..."){
		alert("Falta Seleccionar El Genero!!!");
		}
	if(nacionalidad=="SELECCIONE..."){
		alert("Falta Seleccionar La Nacionalidad!!!");
		}
	if(ocupacion==""){
		alert("Falta Seleccionar La Ocupacion!!!");
		}	
	/*if(zona=="SELECCIONE..."){
		alert("Falta Seleccionar La Zona!!!");
		}
	if(estadocivil=="SELECCIONE..."){
		alert("Falta Seleccionar El Estado Civil!!!");
		}*/
	if(estrato=="SELECCIONE..."){
		alert("Falta Seleccionar El Estrato!!!");
		}
	if(codimun==""){
		alert("Falta Seleccionar El Municipio!!!");
		}
	if(dep=="SELECCIONE..."){
		alert("Falta Seleccionar El Departamento!!!");
		}
	if(muni=="SELECCIONE..."){
	    alert("Falta Seleccionar El Municipio!!!");
	    }
     /*  if((longitud=="")&&(latitud=="")){
         alert("Debe Actualizar Direccion Con El Nuevo Formato Ej: CARRERA 50 #80-90");
       }*/
    
    if((fecha!="")&&(direccion!="")&&(cedula!="")&&(papellido!="")&&(nombre!="")&&(afiliacion!="SELECCIONE...")&&(genero!="SELECCIONE...")&&(nacionalidad!="SELECCIONE...")&&(ocupacion!="")&&(estrato!="SELECCIONE...")&&(codimun!="")&&(dep!="SELECCIONE...")&&(muni!="SELECCIONE...")){
   window.location.href="ControlActualizarDemografico?va=5&nom="+nombre+"&pape="+papellido+"&sape="+sapellido+"&ced="+cedula+"&fe="+fecha+"&dir="+direccion+"&telefi="+telefijo+"&teleofici="+teleofi+"&telecel="+telecel+"&ocu="+ocupacion+"&emp="+empresa+"&re="+religion+"&ra="+raza+"&ema="+email+"&codmun="+codimun+"&numco="+numcontrato+"&ti="+tipo+"&afi="+afiliacion+"&ni="+nivel+"&ge="+genero+"&naci="+nacionalidad+"&zona="+zona+"&esta="+estadocivil+"&estra="+estrato+"&codpac="+codpac+"&codEnt="+codEnt+"&long="+longitud+"&lati="+latitud+"&bar="+barrio,true;
   alert("Ingreso Exitoso...!");
   window.location.href="Adm_ActualizarDemografico.jsp?z=1";
 }

}

//PARA QUE ENTRE PRESIONANDO LA TECLA ENTER
function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}
function validarcom(h,tecla, e){	 
   //	opc = false;
	var s=h.txtnumdocu.value;
	//var n=0;
	//var pos=0;
	for(i=0;i<s.length;i++){
	   valor = parseInt(s.charAt(i));
		if(isNaN(valor)){
			//n++;
			alert("Solo Se Permiten Valores Numericos");
			document.getElementById("txtnumdocu").value="";
			
		}
	}
	tecla = e.keyCode;  e.which;
    if(tecla == 13){ //Codigo Ascii de la tecla ENTER evento si se presiona enter
        buscar(h);  		
	
	}

}
function buscar(h){
	  var tipo,cedula;
      //var gen=h.tipodoc.selectedIndex;
	  tipo=h.tipodoc.value;
      cedula=h.txtnumdocu.value;
      if((tipo=="SELECCIONE...")&&(cedula=="")){
         alert("Ingrese Tipo Y Numero De Documento");
      }else{
         if(cedula==""){
          alert("Debe Ingresar Numero De Documento");
         }else{
             if(tipo=="SELECCIONE..."){
                alert("Debe De Ingresar Tipo De Documento");
             }else{
            	 window.location.href="ControlActualizarDemografico?va=0&tipo="+tipo+"&ced="+cedula,true;
            	 // alert(window.location.href="ControlActualizarDemografico?va=0&tipo="+tipo+"&ced="+cedula,true);
           }
        	
         }
      }
}

var patron = new Array(4,2,2)
var patron2 = new Array(1,3,3,3,3)  
function mascara(d,sep,pat,nums){
if(d.valant != d.value){
	val = d.value
	largo = val.length
	val = val.split(sep)
	val2 = ''
	for(r=0;r<val.length;r++){
		val2 += val[r]
			
	}
	if(nums){
		for(z=0;z<val2.length;z++){
			if(isNaN(val2.charAt(z))){
				letra = new RegExp(val2.charAt(z),"g")
				val2 = val2.replace(letra,"")
			}
		}
	}
	val = ''
	val3 = new Array()
	for(s=0; s<pat.length; s++){
    
		val3[s] = val2.substring(0,pat[s])
		val2 = val2.substr(pat[s])
	}
	for(q=0;q<val3.length; q++){
		if(q ==0){
			val = val3[q]
		}
		else{
			if(val3[q] != ""){
				val += sep + val3[q]
				}
		}
	}
	d.value = val
	d.valant = val
	}
}
function focustxt(){
	document.getElementById("txtnumdocu").focus();
}

</script>

<script language="javascript" src="1.js"></script>
<script languaje="javascript" src="Adm_Ping.js"></script>

</head>

<body onLoad="ajaxNacionalidad(),focustxt(),initialize(),punto();" id="back">


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
				
				ResultSet rs4;
				Statement st4=null;
				st4=con.conn.createStatement();
				rs4=st4.executeQuery("SELECT COUNT(estado) FROM seg_mensajes WHERE estado=0 AND remitente="+codigou+"");
				int madmin=0;
				if(rs4.next()){
					madmin=rs4.getInt(1);
				}
				rs4.getStatement().close();
				con.conn.close();
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
			<div  id="usuario" class="style11">Bienvenido,<b><%=idu%></b> |<a href="Seguridad_login?accion=cerrar&CodUsu=<%=codigou %>" style="color: white">--Cerrar Session--</a></div>
			</td>
			
		</tr>
	</table>
</td>
</tr>
<tr>
<td>
	<div id="cabecera1" align="center" class="style6"><b><a href="menu.jsp">Menu Principal</a>-Actualizar Demografico</b></div>
</td>
</tr>

<tr  id="principal1">
<td>

<div>

	<table width="100%"> 
		<tr>
			<td width="100%">   
					<br />
<form id="form2" style="margin-top:-16px;" name="form2" onkeypress = "return pulsar(event);">
<%
String r=request.getParameter("r");
String cedula=request.getParameter("cedula");
System.out.println("cedula "+cedula);

if(r==null){r="1";}
if(cedula==null){cedula="";}


String tip=request.getParameter("tipo");
System.out.println("tip "+tip);
if(tip==null){tip="";}


String ocupacion=request.getParameter("ocupacion");
System.out.println("ocupacion "+ocupacion);
if(ocupacion==null){ocupacion="";}


String municipio=request.getParameter("municipio");
System.out.println("municipio "+municipio);
if(municipio==null){municipio="";}


String entidad1=request.getParameter("entidad");
System.out.println("entidad1 "+entidad1);
if(entidad1==null){entidad1="";}


String tipodoc=request.getParameter("tipodoc");
System.out.println("tipodoc "+tipodoc);
if(tipodoc==null){tipodoc="SELECCIONE...";}


String ced=request.getParameter("ced");
System.out.println("ced "+ced);
if(ced==null){ced="";}


String afiliacion=request.getParameter("afiliacion");
System.out.println("afiliacion "+afiliacion);
if(afiliacion==null){afiliacion="";}


String nivel=request.getParameter("nivel");
System.out.println("nivel "+nivel);
if(nivel==null){nivel="";}


String papellido=request.getParameter("papellido");
System.out.println("papellido "+papellido);
if(papellido==null){papellido="";}


String sapellido=request.getParameter("sapellido");
System.out.println("sapellido "+sapellido);
if(sapellido==null){sapellido="";}


String nombre=request.getParameter("nombre");
//request.setCharacterEncoding("text/html;charset=UTF-8");
//request.setCharacterEncoding(nombre);
System.out.println("nombre "+nombre);
if(nombre==null){nombre="";}


String genero=request.getParameter("genero");
System.out.println("genero "+genero);
if(genero==null){genero="";}


String nacionalidad=request.getParameter("nacionalidad");
System.out.println("nacionalidad "+nacionalidad);
if(nacionalidad==null){nacionalidad="";}


String direccion=request.getParameter("direccion");
System.out.println("direccion "+direccion);
if(direccion==null){direccion="";}


String telfijo=request.getParameter("telfijo");
System.out.println("telfijo "+telfijo);
if(telfijo==null){telfijo="";}


String telofi=request.getParameter("telofi");
System.out.println("telofi "+telofi);
if(telofi==null){telofi="";}


String telcel=request.getParameter("telcel");
System.out.println("telcel "+telcel);
if(telcel==null){telcel="";}


String empresalab=request.getParameter("empresalab");
System.out.println("empresalab "+empresalab);
if(empresalab==null){empresalab="";}


String zonares=request.getParameter("zonares");
System.out.println("zonares "+zonares);
if(zonares==null){zonares="";}


String religion=request.getParameter("religion");
System.out.println("religion "+religion);
if(religion==null){religion="";}


String estadocv=request.getParameter("estadocv");
System.out.println("estadocv "+estadocv);
if(ocupacion==null){ocupacion="";}


String raza=request.getParameter("raza");
System.out.println("raza "+raza);
if(raza==null){raza="";}


String estrato=request.getParameter("estrato");
System.out.println("estrato "+estrato);
if(estrato==null){estrato="";}


String email=request.getParameter("email");
System.out.println("email "+email);
if(email==null){email="";}


String codigopac=request.getParameter("codigopac");
System.out.println("codigopac "+codigopac);
if(codigopac==null){codigopac="";}


String fechanac=request.getParameter("fechanac");
System.out.println("fechanac "+fechanac);
if(fechanac==null){fechanac="";}


String departamento=request.getParameter("dep");
System.out.println("departamento "+departamento);
if(departamento==null){departamento="";}


String codocu=request.getParameter("codocu");
System.out.println("codocu "+codocu);
if(codocu==null){codocu="";}


String codmun=request.getParameter("codmun");
System.out.println("codmun "+codmun);
if(codmun==null){codmun="";}


String codentidad=request.getParameter("codentidad");
System.out.println("codentidad "+codentidad);
if(codentidad==null){codentidad="";}

String longitud=request.getParameter("longitud");
System.out.println("longitud"+longitud);
if(longitud==null){longitud="";}

String latitud=request.getParameter("latitud");
System.out.println("latitud"+latitud);
if(latitud==null){latitud="";}

String barrio=request.getParameter("bar");
System.out.println("bar"+barrio);
if(barrio==null){barrio="";}

%>	 

<table width="100%" class="style6" cellspacing="1"> 
  <tr> 
    <td colspan="4" align="center" id="cabecera2" class="style11">ACTUALIZAR DATOS DEMOGRAFICOS </td>
    </tr>
  <tr>
    <td width="148" align="right"><span >Tipo Documento</span></td>
    <td width="207" align="left">
     <select name="tipodoc" size="1" id="tipodoc" class="style3" >
      <option value="<%=tipodoc%>"><%=tipodoc%></option> 
	   <% 
		 ResultSet rs3=null;
		 Statement st3 = null;  
		 try{
		 	Conexion con2=new Conexion();
		 	st3 = con2.conn.createStatement();
		 	rs3=st3.executeQuery("SELECT sigla,descripcion FROM adm_tipodocumento");%>
		 	<%while(rs3.next()){%>
			<option value="<%=rs3.getString(1)%>"><%=rs3.getString(1)+":"%><%=rs3.getString(2) %></option>
		 	<%}
		 	rs3.getStatement().close();
		 	con2.cerrar();
		}catch(SQLException e){
			 
			 System.out.println(e);
		out.println("no se pudo realizar la conexion!<br/>"); 
		}%>
     </select><span class="style8">*</span></td>

    <td width="129" align="right"><span class="Estilo8">N&ordm; Identificacion</span></td> 
    <td width="251" align="left">
    <input name="txtnumdocu" type="text" id="txtnumdocu" value="<%=ced%>" maxlength="20" />
     <span class="style8">*</span>
      <input name="btnBuscar" type="button" id="btnBuscar" class="boton4" value="Buscar" onclick="buscar(form2)" /></td>
    </tr>
 <%if(r.equals("0")){%>
<tr>
<div>
<table width="100%">
<br />
<tr>
	<td width="100%" id="lateral4" class="style6">
		<br/>
		<div id="cabecera2" style="margin-top:-16px;" class="style11" align="center">GEOREFERENCIACION</div>
		<br/>
		<!-- Muestra mapita -->
		 <center><hr width="100%" size="2" class="style6"/></center>
	 	<div id="map_canvas" style="width: 320px; height: 250px;"></div>
		<!-- Muestra la direccion -->
		<label>
			<input name="txtdiregeorre" type="hidden" id="txtdiregeorre"  style="border:0px" readonly maxlength="100" class="simpletext">
		</label>
		<center><hr width="100%" size="2" class="style6"/></center>
		&nbsp;&nbsp;&nbsp;&nbsp;<input type="image" src="Imagenes\MapaPuntos.jpg" width="20%" height="10%" onclick="Link()">
	  </td>

<!-- Columna 2 -->
<td width="100%" class="style6"> 
<table width="100%" class="style6"> 
    <tr>
    <td colspan="4" id="cabecera2"><div class="style11" align="center"> DATOS A ACTUALIZAR </div></td>
    </tr>

 <tr>
    <td width="128">&nbsp;</td>
    <td width="186">&nbsp;</td>
    <td width="186">&nbsp;</td>
    <td width="245">&nbsp;</td>
  </tr>

    <tr>
    <td align="left"><span class="Estilo8">Tipo Afiliacion</span></td>
    <td align="left"><select name="select2" id="select2" onchange="ajaxEps()" >
      <%if (afiliacion.equals("Cotizante")) {%>
      <option><%=afiliacion%></option>
      <option>Particular</option>
      <option>Beneficiario</option>
	  <option>Desplazado</option>
      <%}%>
      <%if (afiliacion.equals("Particular")) {%>
      <option><%=afiliacion%></option>
      <option>Cotizante</option>
      <option>Beneficiario</option>
      <option>Desplazado</option>
      <%}%>
      <%if (afiliacion.equals("Beneficiario")) {%>
      <option><%=afiliacion%></option>
      <option>Cotizante</option>
      <option>Particular</option>
	  <option>Beneficiario</option>
      <%}%>
 <%if (afiliacion.equals("Desplazado")) {%>
      <option><%=afiliacion%></option>
      <option>Cotizante</option>
      <option>Particular</option>
	  <option>Desplazado</option>
      <%}%>
      <%if (afiliacion.equals("")) {%>
      <option>Cotizante</option>
      <option>Beneficiario</option>
      <option>Particular</option>
	  <option>Desplazado</option>
      <%}%>
    </select>
      <span class="style8">*</span></td>
    <td align="left"><span >Nivel Cotizante</span></td>
    <td align="left"><select name="cbnivel" id="cbnivel">
      <%if (nivel.equals("")) {%>
      <option>I</option>
      <option>II</option>
      <option>III</option>
      <%}%>
      <%if (nivel.equals("I")) {%>
      <option selected="selected"><%=nivel%></option>
      <option>II</option>
      <option>III</option>
      <%}%>
      <%if (nivel.equals("II")) {%>
      <option selected="selected"><%=nivel%></option>
      <option>I</option>
      <option>III</option>
      <%}%>
      <%if (nivel.equals("III")) {%>
      <option selected="selected"><%=nivel%></option>
      <option>I</option>
      <option>II</option>
      <%}%>
    </select></td>
    </tr>
  <tr>
    <td align="left"><span >Primer Apellido</span></td>
    <td align="left"><input name="txtpapellido" type="text" id="txtpapellido" onkeyup="this.value=this.value.toUpperCase(),caracter(form2.txtpapellido);" onkeypress="ajaxEps()" maxlength="20" value="<%=papellido %>"/>
      <span class="style8">*</span></td>
    <td align="left"><span >Segundo Apellido</span></td>
    <td align="left"><input name="txtsapellido" type="text" id="txtsapellido" onkeyup="this.value=this.value.toUpperCase(),caracter(form2.txtsapellido);" onchange="datos();"  maxlength="20" value="<%=sapellido %>"/></td>
    </tr>
  <tr>
    <td align="left"><span >Nombres</span></td>
    <td align="left"><input name="txtnombre" type="text" id="txtnombre" onkeyup="this.value=this.value.toUpperCase(),caracter(form2.txtnombre);"  onkeypress="ajaxOcupacion()"onchange="" maxlength="20" value="<%=nombre %>"/>
      <span class="style8">*</span></td>
    <td align="left"><span >Fecha Nacimiento(aaaa/mm/dd)</span></td>
    <td align="left"><input type="text" name="txtfechanaci" id="txtfechanaci"   onkeyup="mascara(this,'/',patron,true)" maxlength="10" value="<%=fechanac %>" />
      <span class="style8">*</span></td>
    </tr>
  <tr>
    <td align="left"><span >Nacionalidad</span></td>
    <td align="left"><select name="cbnacionalidad" size="1" id="cbnacionalidad" onchange="ajaxDep(form2)"  >
      <option selected="selected"><%=nacionalidad%></option>
    </select>
      <span class="style8">*</span></td>
    <td align="left"><span class="Estilo8">Direccion</span></td>
    <td align="left"><input name="txtdire" type="text" id="txtdire"  value="<%=direccion %>" onFocus="aparece()" readonly/>
      <span class="style8">*</span></td>
    </tr>
<tr id="efecto" style="display:none">
<td width="128">&nbsp;</td>
    <td width="186">&nbsp;</td>

<td colspan="2">
 <table>
     <tr class="style6"><td>EJ: CARRERA</td><td><center>73</center></td><td><center>B</center></td><td><center>2</center></td><td><center>#</center></td><td><center>85</center></td><td><center>A</center></td><td><center>1</center></td><td><center>80</center></td></tr>
     <tr><td>
        
        <label>
              <select name="ComboDir1" id="ComboDir1" onChange="Concatenar();">
			  <option selected="selected">--</option>
			 <% 
			 ResultSet rst=null;
			 Statement st0 = null;  
			 try{
			 	Conexion con2=new Conexion();
			 	st0 = con2.conn.createStatement();
			 	rst=st0.executeQuery("SELECT descripcion FROM adm_combo1_direccion");%>
			 	<%while(rst.next()){%>
				<option value="<%=rst.getString(1)%>"><%=rst.getString(1)%></option>
			 	<%}
			 	rst.getStatement().close();
			 	con2.cerrar();
			}catch(SQLException e){
				 
				 System.out.println(e);
			out.println("no se pudo realizar la conexion!<br/>"); 
			}%>
			      </select>
			</label></td>
            <td>
			<input name="txtDirnum1" type="text" id="txtDirnum1"  maxlength="3" size="2" onKeyUp="Concatenar();" /> </td>
			 <td><label>
			  <select name="ComboDir2" id="ComboDir2" onChange="Concatenar();">
			  <option selected="selected">--</option>
			 <% 
			 ResultSet rr=null;
			 Statement sta= null;  
			 try{
			 	Conexion con2=new Conexion();
			 	sta= con2.conn.createStatement();
			 	rr=sta.executeQuery("SELECT descripcion FROM adm_combo2_direccion");%>
			 	<%while(rr.next()){%>
				<option value="<%=rr.getString(1)%>"><%=rr.getString(1)%></option>
			 	<%}
			 	rr.getStatement().close();
			 	con2.cerrar();
			}catch(SQLException e){
				 
				 System.out.println(e);
			out.println("no se pudo realizar la conexion!<br/>"); 
			}%>
			</select>
			</label> </td>
             <td>
			<input name="txtDirnum2" type="text" id="txtDirnum2"  maxlength="3" size="2" onKeyUp="Concatenar();" /> </td>
			 <td><label>
			  <select name="ComboDir3" id="ComboDir3" onChange="Concatenar();">
			  <option selected="selected">--</option>
			 <% 
			 ResultSet r1=null;
			 Statement s1= null;  
			 try{
			 	Conexion con2=new Conexion();
			 	s1= con2.conn.createStatement();
			 	r1=s1.executeQuery("SELECT Descripcion FROM adm_combo3_direccion");%>
			 	<%while(r1.next()){%>
				<option value="<%=r1.getString(1)%>"><%=r1.getString(1)%></option>
			 	<%}
			 	r1.getStatement().close();
			 	con2.cerrar();
			}catch(SQLException e){
				 
				 System.out.println(e);
			out.println("no se pudo realizar la conexion!<br/>"); 
			}%>
			      </select>
			</label></td>
             <td>
             <label>
			  <input name="txtDirnum3" type="text" id="txtDirnum3"  maxlength="3" size="2"  onKeyUp="Concatenar(),mapa()" />
			      </label> </td>
	         <td><label>
			  <select name="ComboDir4" id="ComboDir4" onChange="Concatenar();">
			  <option selected="selected">--</option>
			 <% 
			 ResultSet r2=null;
			 Statement s2= null;  
			 try{
			 	Conexion con2=new Conexion();
			 	s2= con2.conn.createStatement();
			 	r2=s2.executeQuery("SELECT descripcion FROM adm_combo2_direccion");%>
			 	<%while(r2.next()){%>
				<option value="<%=r2.getString(1)%>"><%=r2.getString(1)%></option>
			 	<%}
			 	r2.getStatement().close();
			 	con2.cerrar();
			}catch(SQLException e){
				 
				 System.out.println(e);
			out.println("no se pudo realizar la conexion!<br/>"); 
			}%>
			      </select>
			</label> </td>
              <td>
             <label>
			  <input name="txtDirnum4" type="text" id="txtDirnum4"  maxlength="3" size="2"  onKeyUp="Concatenar()" />
			      </label> </td>
		
			 <td>
			<input name="txtDirnum5" type="text" id="txtDirnum5"  maxlength="3" size="2"   onkeyUp="Concatenar(),mapa()">
             </td>


      </tr></table>
</td>
</tr>
  <tr>
    <td align="left"><span >Telefono Fijo</span></td>
    <td align="left"><input name="txtelefijo" type="text" id="txtelefijo" onkeyup="validarte(form2.txtelefijo);" onchange="datos();"  maxlength="12" value="<%=telfijo %>"/></td>
    <td align="left"><span >Genero</span></td>
    <td align="left"><select name="select4" id="select4" >
      <%if (genero.equals("Masculino")) {%>
      <option selected="selected"><%=genero%></option>
      <option>Femenino</option>
      <%}%>
      <%if (genero.equals("Femenino")) {%>
      <option selected="selected"><%=genero%></option>
      <option>Masculino</option>
      <%}%>
      <%if (genero.equals("")) {%>
      <option>Masculino</option>
      <option>Femenino</option>
      <%}%>
    </select>
      <span class="style8">*</span></td>
    </tr>
  <tr>
    <td align="left"><span >Email</span></td>
    <td align="left"><input name="txtemail" type="text" id="txtemail" onchange="checkmail(form2.txtemail);" maxlength="30" value="<%=email %>"/></td>
    <td align="left"><span >Telefono Celular</span></td>
    <td align="left"><input name="txtcelular" type="text" id="txtcelular" onkeyup="validarte(form2.txtcelular);" onchange="datos();"  maxlength="12" value="<%=telcel %>"/></td>
    </tr>
  <tr>
    <td align="left">Ocupacion</td>
    <td align="left"><input name="txtocupacion" type="text" id="txtocupacion" value=<%=codocu %> />
      <span class="style8">*</span></td>
    <td align="left"><span >Departamento</span></td>
    <td align="left"><select name="cbdep" size="1" id="cbdep" onchange="ajaxxMun(form2)">
      <option selected="selected"><%=departamento%></option>
     <% 
        String codpais=request.getParameter("CodPais");
       
			 ResultSet rr1=null;
			 Statement ss= null;  
			 try{
			 	Conexion con6=new Conexion();
			 	ss= con6.conn.createStatement();
			 	rr1=ss.executeQuery("SELECT nombre FROM adm_departamento WHERE pais_codigo_fk='"+codpais+"' ORDER BY nombre ");%>
			 	<%while(rr1.next()){%>
				<option value="<%=rr1.getString(1)%>"><%=rr1.getString(1)%></option>
			 	<%}
			 	rr1.getStatement().close();
			 	con6.cerrar();
			}catch(SQLException e){
				 
				 System.out.println(e);
			out.println("no se pudo realizar la conexion!<br/>"); 
			}%>
    </select>
      <span class="style8">*</span></td>
    </tr>
  <tr>
    <td align="left"><span >Municipio</span></td>
    <td align="left"><select name="cbmun" size="1" id="cbmun" onchange="CodigoMun(form2)">
      <option selected="selected"><%=municipio%></option>
       <% 
       
             String codepart=request.getParameter("CodDep");
       
			 ResultSet rr3=null;
			 Statement ss3= null;  
			 try{
			 	Conexion con6=new Conexion();
			 	ss3= con6.conn.createStatement();
			 	rr3=ss3.executeQuery("SELECT nombre FROM adm_municipio WHERE dept_codigo_fk='"+codepart+"' ORDER BY nombre ");%>
			 	<%while(rr3.next()){%>
               	<option value="<%=rr3.getString(1)%>"><%=rr3.getString(1)%></option>
			 	<%}
			 	rr3.getStatement().close();
			 	con6.cerrar();
			}catch(SQLException e){
				 
				 System.out.println(e);
			out.println("no se pudo realizar la conexion!<br/>"); 
			}%>
    </select>
      <span class="style8">*</span></td>
    <td align="left"><span >Estado Civil</span></td>

    <td align="left"><select name="cmbEstadocivil" id="cmbEstadocivil" >
      <%if (estadocv.equals("Soltero(a)")) {%>
      <option selected="selected"><%=estadocv%></option>
      <option>Casado(a)</option>
      <option>Viudo(a)</option>
      <%}%>
      <%if (estadocv.equals("Casado(a)")) {%>
      <option selected="selected"><%=estadocv%></option>
      <option>Soltero(a)</option>
      <option>Viudo(a)</option>
      <%}%>
      <%if (estadocv.equals("Viudo(a)")) {%>
      <option selected="selected"><%=estadocv%></option>
      <option>Casado(a)</option>
      <option>Soltero(a)</option>
      <%}%>
      <%if (estadocv.equals("")) {%>
      <option>Casado(a)</option>
      <option>Soltero(a)</option>
      <option>Viudo(a)</option>
      <%}%>
    <%if (estadocv.equals("SELECCIONE...")) {%>
<option selected="selected"><%=estadocv%></option>
      <option>Casado(a)</option>
      <option>Soltero(a)</option>
      <option>Viudo(a)</option>
      <%}%>
    </select>
      <span class="style8"></span></td>
    </tr>
  <tr>
    <td align="left"><span >Telefono Oficina</span></td>
    <td align="left"><input name="txteleoficina" type="text" id="txteleoficina" onkeyup="validarte(form2.txteleoficina);" onchange="datos();"  maxlength="12" value="<%=telofi%>"/></td>
    <td align="left"><span >Raza</span></td>
    <td align="left"><input name="txtraza" type="text" id="txtraza" onkeyup="this.value=this.value.toUpperCase(),caracter(form2.txtraza);" onchange="datos();"  maxlength="20" value="<%=raza %>"/></td>
    </tr>
  <tr>
    <td align="left"><span >Religion</span></td>
    <td align="left"><input name="txtreligion" type="text" id="txtreligion" onkeyup="this.value=this.value.toUpperCase(),caracter(form2.txtreligion);" onchange="datos();"  maxlength="20" value="<%=religion %>"/></td>
    <td align="left"><span >Estrato</span></td>
    <td align="left"><select name="select7" id="select7">
      <%if (estrato.equals("1")) {%>
      <option selected="selected"><%=estrato%></option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
      <option>6</option>
      <%}%>
      <%if (estrato.equals("2")) {%>
      <option selected="selected"><%=estrato%></option>
      <option>1</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
      <option>6</option>
      <%}%>
      <%if (estrato.equals("3")) {%>
      <option selected="selected"><%=estrato%></option>
      <option>1</option>
      <option>2</option>
      <option>4</option>
      <option>5</option>
      <option>6</option>
      <%}%>
      <%if (estrato.equals("4")) {%>
      <option selected="selected"><%=estrato%></option>
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>5</option>
      <option>6</option>
      <%}%>
      <%if (estrato.equals("5")) {%>
      <option selected="selected"><%=estrato%></option>
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>6</option>
      <%}%>
      <%if (estrato.equals("6")) {%>
      <option selected="selected"><%=estrato%></option>
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
      <%}%>
      <%if (estrato.equals("")) {%>
      <option selected="selected"><%=estrato%></option>
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
      <option>6</option>
      <%}%>
      <%if (estrato.equals("0")) {%>
      <option selected="selected"><%=estrato%></option>
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
      <option>6</option>
      <%}%>
    </select>
      <span class="style8"></span></td>
    </tr>
  <tr>
    <td align="left"><span >Zona Residencia</span></td>
    <td align="left"><select name="cbzona" id="cbzona">
      <%if (zonares.equals("")) {%>
      <option>Rural</option>
      <option>Urbana</option>
      <%}%>
      <%if (zonares.equals("Rural")) {%>
      <option selected="selected"><%=zonares%></option>
      <option>Urbana</option>
      <%}%>
      <%if (zonares.equals("Urbana")) {%>
      <option selected="selected"><%=zonares%></option>
      <option>Rural</option>
      <%}%>
	  <%if (zonares.equals("SELECCIONE...")) {%>
      <option selected="selected"><%=zonares%></option>
      <option>Rural</option>
	  <option>Urbana</option>
      <%}%>
    </select>
      <span class="style8"></span></td>
    <td align="left">Empresa</td>
    <td align="left"><input name="txtemp" type="text" id="txtemp" onkeyup="this.value=this.value.toUpperCase(),caracter(form2.txtemp);" onchange="datos();"  maxlength="20" value="<%=empresalab %>"/></td>
    </tr>
     <tr>
	 <td align="left"><span >Barrio</span></td>
	 <td align="left"><input name="txtbarrio" type="text" id="txtbarrio" onkeyup="this.value=this.value.toUpperCase(),caracter(form2.txtbarrio);" onchange="datos();"  maxlength="20" value="<%=barrio%>"/></td>
	</tr>
  <tr>
    <td align="left"><span >Nombre Entidad</span></td>
    <td colspan="3" align="left"><input name="cbeps" type="text" id="cbeps" size="90" value="<%=entidad1 %>" onkeyup="autocompleta_nombre()" />
      <span class="style8">*</span></td>
  </tr>
	
  <tr>
    <td align="left">&nbsp;</td>
    <td colspan="3" align="left"><div id='sugerencias'></div></td>
    </tr>
  <tr>
    <td colspan="2" align="center"><span class="style8">Campos Requeridos * </span></td>
    <td align="center"><input name="txtcodentidad" type="hidden" id="txtcodentidad" readonly="" />
      <input name="txtcodmun" type="hidden" id="txtcodmun" value="<%=codmun%>"  readonly="" />
      <input name="txtcodigopac" type="hidden" id="txtcodigopac" value="<%=codigopac %>" readonly="" />      
      <input name="txtcodigoconv" type="hidden" id="txtcodigoconv"  value="<%=codentidad%>"  readonly="" />  
  <!-- Para poder ubicar el punto en el mapa  -->
     <input name="txtlongitud" type="hidden" id="txtlongitud"  value="<%=longitud%>"  readonly/>  
     <input name="txtlatitud" type="hidden" id="txtlatitud"  value="<%=latitud%>"  readonly />  
  <!--------------------------------------------------------------------------------------------------------> 
   </td>
    <td align="center">&nbsp;</td>
  </tr>
   <tr>
    <td align="center"><label></label></td>
    <td align="center">&nbsp;</td>
    <td align="center"><input name="btnactualizar" class="boton4" type="button" id="btnactualizar" value="Actualizar" onclick="demografico(form2);" /></td>
    <td align="center"><label></label></td>
    </tr>
</table>

</td>

</tr>
</table>
</div>

</tr>
<%} %>
</table>
</form></td></tr></table>
</div>
</td></tr></table>
</body>
</html>