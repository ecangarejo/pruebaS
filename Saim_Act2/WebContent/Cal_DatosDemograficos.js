function getXMLObject()  //XML OBJECT
{
	var xmlHttp = false;
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
	}
	catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
		}
		catch (e2) {
			xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
		}
	}
	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
	}
	return xmlHttp;  // Mandatory Statement returning the ajax object created
}
	var xmlhttp = new getXMLObject(); //xmlhttp holds the ajax object
/////////////////////////////////////////////////////////////////////////////////
	
	function ValDatDem(){
		ajax=getXMLObject();
		var Serv=document.getElementById('Serv').value;
		var tanos=document.getElementById('Iano').value;
		var tdia=document.getElementById('Idia').value;
		var tmes=document.getElementById('Imes').value;
		var Ent=document.getElementById('Ent').value;
		var ftanos=document.getElementById('Fano').value;
		var ftdia=document.getElementById('Fdia').value;
		var ftmes=document.getElementById('Fmes').value;
		//alert("entrado");
		//alert("valor=RepIndicadores&tanos="+tanos+"&tmeses="+tmes+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmes+"&ftdia="+ftdia+"&ftent="+Ent+"&Serv="+Serv);
		var contenido=document.getElementById("vista");
		var opciones="toolbar=yes, menubar=yes , location=yes ";
		opciones=opciones+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
		if((tanos==40)||(tdia==40)||(tmes==40)||(ftanos==40)||(ftdia==40)||(ftmes==40)||(Serv==40)||(Ent==40)){
			alert("No ha seleccionado todos los Datos ");
		}else{
			if((Serv==1)||(Serv=="todas")){
				//alert("entrando");
				GenerarDatosD(Serv,tanos,tdia,tmes,Ent,ftanos,ftdia,ftmes,0);
			}else{
					if((Serv!=40)){
						var area=document.getElementById('area').value;
						//alert("area "+area);
						GenerarDatosD(Serv,tanos,tdia,tmes,Ent,ftanos,ftdia,ftmes,area);
						
				
				}
		
		
			}
		}
	}
	
	

	function tipoarea(){
		ajax=getXMLObject();
		var serv=document.getElementById('Serv').value;
		//alert("pruebaa de onchange"+serv);
		var contenido=document.getElementById('subarea');
		if((serv=='todas')||(serv==1)){
			contenido.innerHTML="";
		}else{
		ajax.open("POST","ControlDatosDemograficos",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				//alert(ajax.resposeText);
				//alert("valor=VistaSubArea&serv="+serv);
				contenido.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VistaSubArea&serv="+serv);
		}
		
	}

function GenerarDatosD(Serv,tanos,tdia,tmes,Ent,ftanos,ftdia,ftmes,area){

	ajax=getXMLObject();
	//alert("generaDatosD");
	var opciones="toolbar=yes, menubar=yes , location=yes ";
	opciones=opciones+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
	//alert("Cal_ReporteGenDatosDemograficos.jsp?Serv="+Serv+"&tano="+tanos+"&tdia="+tdia+"&tmes="+tmes+"&ftent="+Ent+"&ftano="+ftanos+"&ftdia="+ftdia+"&ftmes="+ftmes+"&area="+area);
	var pagina="Cal_ReporteGenDatosDemograficos.jsp?Serv="+Serv+"&tano="+tanos+"&tdia="+tdia+"&tmes="+tmes+"&ftent="+Ent+"&ftano="+ftanos+"&ftdia="+ftdia+"&ftmes="+ftmes+"&area="+area;
	ajax.open("POST","ControlDatosDemograficos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML=ajax.responseText;
			//contenido.innerHTML="PRUEBA";
			//window.location.href="reporte.jsp?tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+Ftmeses+"&ftdia="+ftdia;
			//mostrarep(tanos,tmeses,tdia,ftanos,ftanos,Ftmeses,Ftmeses,ftdia);
			window.open(pagina,"NUEVA",opciones);
		}
		}		
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=datos");

}



function ReporteGeneralDDemo(Serv,ftent,area,tdia,tmes,tano,ftdia,ftmes,ftano){

	ajax=getXMLObject();
//	alert("ReporteGeneralDDemo")
	//alert("area"+area);
	var contenido=document.getElementById("reporte");
	var opciones="toolbar=yes, menubar=yes , location=yes ";
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte.....</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlDatosDemograficos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			
			contenido.innerHTML=ajax.responseText;
			//contenido.innerHTML="PRUEBA";
			//window.location.href="reporte.jsp?tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+Ftmeses+"&ftdia="+ftdia;
			//mostrarep(tanos,tmeses,tdia,ftanos,ftanos,Ftmeses,Ftmeses,ftdia);
			//window.open(pagina,"NUEVA",opciones);
		}
		}		
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepDatosDemograficos&tanos="+tano+"&tmeses="+tmes+"&tdia="+tdia+"&ftanos="+ftano+"&ftmeses="+ftmes+"&ftdia="+ftdia+"&ftent="+ftent+"&Serv="+Serv+"&area="+area);

}

function ReporteDetalleGenero(Serv, ftent, area,tdia,tmes,tano,ftdia,ftmes,ftano){
	ajax=getXMLObject();
	var contenido=document.getElementById("reporte");
	//alert("Reporte detalle de Genero Fechai Serv"+Serv+" ftent"+ftent+" area"+area+" tdia"+tdia+" tmes"+tmes+" tano"+tano+" ftdia"+ftdia+" ftmes"+ftmes+" ftano"+ftano);
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte.....</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlDatosDemograficos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
		}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ReporteGenero&Serv="+Serv+"&area="+area+"&tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent);
	
}


function ReporteDetalleEtareo(Serv, ftent, area,tdia,tmes,tano,ftdia,ftmes,ftano){
	ajax=getXMLObject();
	var contenido=document.getElementById("reporte");
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte.....</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlDatosDemograficos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
		}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ReporteEtareo&Serv="+Serv+"&area="+area+"&tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent);
	
}


function ReporteDetallePatologia(Serv, ftent, area,tdia,tmes,tano,ftdia,ftmes,ftano,codDiag){
	ajax=getXMLObject();
	var contenido=document.getElementById("reporte");
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte.....</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlDatosDemograficos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
		}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ReportePatologia&Serv="+Serv+"&area="+area+"&tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&codDiag="+codDiag);
	
}


function ReporteDetalleEntidadesFrecuentes(Serv, ftent, area,tdia,tmes,tano,ftdia,ftmes,ftano,codent){
	ajax=getXMLObject();
	//alert("ReporteEntidadesFrecuentes");
	var contenido=document.getElementById("reporte");
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte.....</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlDatosDemograficos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
		}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ReporteEntidadesFrecuentes&Serv="+Serv+"&area="+area+"&tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&codent="+codent);
	
}


function  Frecuentes(Serv, ftent,area,tdia,tmes,tano,ftdia,ftmes,ftano,codent){
	//alert("frecuentes");
}
