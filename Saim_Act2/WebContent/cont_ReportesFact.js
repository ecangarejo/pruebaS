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
///////////////////////////////////////////////////////////////////////////////////////

function GenerarRepNC(){
	
	ajax=getXMLObject();
	var tanos=document.getElementById('Iano').value;
	var tdia=document.getElementById('Idia').value;
	/*if(dia.selectedIndex == -1){
		return;
		}
	valor =dia.Value;
	txt = dia.options[dia.selectedIndex].text;
	tdia=txt;*/
	var tmeses=document.getElementById('Imes').value;
	/*if(mes.selectedIndex == -1){
		return;
		}
	valor =mes.Value;
	txt = mes.options[mes.selectedIndex].text;
	tmes=txt;*/
	var ftanos=document.getElementById('Fano').value;
	var ftdia=document.getElementById('Fdia').value;
	var Ftmeses=document.getElementById('Fmes').value;
	var Ent=document.getElementById('Ent').value;
	var tnota=document.getElementById('Tipo').value;
	if((tmeses=="40")||(tdia=="40")||(tanos=="40")||(ftanos=="40")||(ftdia=="40")||(Ftmeses=="40")){
		alert("No ha seleccionado todos los Datos ");
	}else{
		pagina1="reporteNC.jsp?tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+Ftmeses+"&ftdia="+ftdia+"&ftent="+Ent+"&tnota="+tnota;
		var opciones="toolbar=yes, menubar=yes , location=yes ";
		opciones=opciones+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
		ajax.open("POST","ControlReportesFact",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				window.open(pagina1,"NUEVA",opciones);
			}
		}
		
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=datos");
	}
}

function vistarepNC(tanos,tmeses,tdia,ftanos,ftmeses,ftdia,ftent,tnota){
	ajax=getXMLObject();
	//alert(tnota);
	var contenido=document.getElementById('reporte');
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte de Notas Credito .....Espere un momento...</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlReportesFact",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=reportenc&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&ftent="+ftent+"&tnota="+tnota);
}


function vistarep(tanos,tmeses,tdia,ftanos,ftmeses,ftdia,ftent){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	document.getElementById('reporte').innerHTML='<br><br><p align="center">Cargando Reporte de Facturas.....Espere un momento...</p><img src="Imagenes/ani.gif" align="center">';
	ajax.open("POST","ControlReportesFact",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&ftent="+ftent);
}

function vistarepAnu(tanos,tmeses,tdia,ftanos,ftmeses,ftdia,ttp,ftent){
	ajax=getXMLObject();
	//alert("Ttp="+ttp);
	//alert("vistarepAnu"+ftent);
	var contenido=document.getElementById('reporte');
	//alert("vistarep=reporteAnu&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&ttp="+ttp);
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte de Anuladas.....Espere un momento...</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlReportesFact",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=reporteAnu&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&ttp="+ttp+"&ftent="+ftent);
}

function vistaRepFactNC(tanos,tmeses,tdia,ftanos,ftmeses,ftdia,ftent){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	//alert("vista rep de nota credito"+ftent);
	//alert("valor=reportenc&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"ftent=&quot;"+ftent+"&quot;");
	ajax.open("POST","ControlReportesFact",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=reporteFactNC&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&ftent="+ftent);
}


function vistareprad(tanos,tmeses,tdia,ftanos,ftmeses,ftdia,ftent,tipo){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte de Radicado.....Espere un momento...</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlReportesFact",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=reportefacrad&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&ftent="+ftent+"&tipo="+tipo);
}

function vistarepRC(tanos,tmeses,tdia,ftanos,ftmeses,ftdia,ftent){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte de Recibos de Caja.....Espere un momento...</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlReportesFact",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=reporterc&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&ftent="+ftent);
}


function GenerarRepFactRadicado(){
	
	ajax=getXMLObject();

	var tanos=document.getElementById('Iano').value;
	var tdia=document.getElementById('Idia').value;
	var tmeses=document.getElementById('Imes').value;
	var Ent=document.getElementById('Ent').value;
	var ftanos=document.getElementById('Fano').value;
	var ftdia=document.getElementById('Fdia').value;
	var Ftmeses=document.getElementById('Fmes').value;
	var tipo=document.getElementById('TipoR').value;
	if((tmeses=="40")||(tdia=="40")||(tanos=="40")||(ftanos=="40")||(ftdia=="40")||(Ftmeses=="40")){
		alert("No ha seleccionado todos los Datos ");
	}else{
		//alert("valor=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+Ftmeses+"&ftdia="+ftdia);
		pagina="reporteradicado.jsp?tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+Ftmeses+"&ftdia="+ftdia+"&ftent="+Ent+"&tipo="+tipo;
		var opciones="toolbar=yes, menubar=yes , location=yes ";
		opciones=opciones+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
		ajax.open("POST","ControlReportesFact",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				//alert(ajax.reposenText);
				//contenido.innerHTML=ajax.responseText;
				//window.location.href="reporte.jsp?tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+Ftmeses+"&ftdia="+ftdia;
				//mostrarep(tanos,tmeses,tdia,ftanos,ftanos,Ftmeses,Ftmeses,ftdia);
				window.open(pagina,"NUEVA",opciones);
			}
		}
		
			
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=datos");
	}
}



function GenerarRep(){
	
	ajax=getXMLObject();
	var tanos=document.getElementById('Iano').value;
	var tdia=document.getElementById('Idia').value;
	var tmeses=document.getElementById('Imes').value;
	
	var Ent=document.getElementById('Ent').value;
	/*if(Ent.selectedIndex == -1){
			return;
		}
	valor =Ent.Value;
	txt = Ent.options[Ent.selectedIndex].text;
	ftent=txt*/
	
	var ftanos=document.getElementById('Fano').value;
	var ftdia=document.getElementById('Fdia').value;
	var Ftmeses=document.getElementById('Fmes').value;
	
	if((tmeses=="40")||(tdia=="40")||(tanos=="40")||(ftanos=="40")||(ftdia=="40")||(Ftmeses=="40")){
		
		alert("No ha seleccionado todos los Datos ");
	
	 }else{
	
			//alert("valor=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+Ftmeses+"&ftdia="+ftdia);
			pagina="reporte.jsp?tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+Ftmeses+"&ftdia="+ftdia+"&ftent="+Ent;
			var opciones="toolbar=yes, menubar=yes , location=yes ";
			opciones=opciones+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
			ajax.open("POST","ControlReportesFact",true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
						window.open(pagina,"NUEVA",opciones);
				}
			}
			
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=datos");
	 }
}

	
		
	function GenerarRepRC(){
			
			ajax=getXMLObject();
			var tanos=document.getElementById('Iano').value;
			var tdia=document.getElementById('Idia').value;
			var tmeses=document.getElementById('Imes').value;
			
			var Ent=document.getElementById('Ent').value;
			/*if(Ent.selectedIndex == -1){
					return;
				}
			valor =Ent.Value;
			txt = Ent.options[Ent.selectedIndex].text;
			ftent=txt;*/
			var ftanos=document.getElementById('Fano').value;
			var ftdia=document.getElementById('Fdia').value;
			var Ftmeses=document.getElementById('Fmes').value;
			if((tmeses=="40")||(tdia=="40")||(tanos=="40")||(ftanos=="40")||(ftdia=="40")||(Ftmeses=="40")){
					alert("No ha seleccionado todos los Datos ");
			 }else{
							//alert("valor=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+Ftmeses+"&ftdia="+ftdia);
				pagina="reporteRC.jsp?tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+Ftmeses+"&ftdia="+ftdia+"&ftent="+Ent;
				var opciones="toolbar=yes, menubar=yes , location=yes ";
				opciones=opciones+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
				ajax.open("POST","ControlReportesFact",true);
				ajax.onreadystatechange = function(){
					if(ajax.readyState == 4){
						//alert(ajax.reposenText);
						//contenido.innerHTML=ajax.responseText;
						//window.location.href="reporte.jsp?tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+Ftmeses+"&ftdia="+ftdia;
						//mostrarep(tanos,tmeses,tdia,ftanos,ftanos,Ftmeses,Ftmeses,ftdia);
						window.open(pagina,"NUEVA",opciones);
					}
				}
									
				ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=datos");
			 }
	}

	
		
function GenerarRepAnu(){
			
			ajax=getXMLObject();
			var ttp=document.getElementById('td').value;
			var tanos=document.getElementById('Iano').value;
			var tdia=document.getElementById('Idia').value;
			var tmeses=document.getElementById('Imes').value;
			var Ent=document.getElementById('Ent').value;
			var ftanos=document.getElementById('Fano').value;
			var ftdia=document.getElementById('Fdia').value;
			var Ftmeses=document.getElementById('Fmes').value;
			if((tmeses=="40")||(tdia=="40")||(tanos=="40")||(ftanos=="40")||(ftdia=="40")||(Ftmeses=="40")){
				
				alert("No ha seleccionado todos los Datos ");
			
			 }else{
			
				pagina="reporteAnu.jsp?&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+Ftmeses+"&ftdia="+ftdia+"&ttp="+ttp+"&ftent="+Ent;
				var opciones="toolbar=yes, menubar=yes , location=yes ";
				opciones=opciones+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
				ajax.open("POST","ControlReportesFact",true);
				ajax.onreadystatechange = function(){
					if(ajax.readyState == 4){
						//alert(ajax.reposenText);
						//contenido.innerHTML=ajax.responseText;
						//window.location.href="reporte.jsp?tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+Ftmeses+"&ftdia="+ftdia;
						//mostrarep(tanos,tmeses,tdia,ftanos,ftanos,Ftmeses,Ftmeses,ftdia);
						window.open(pagina,"NUEVA",opciones);
					}
				}
				
				ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=datos");
		}
}
		
	
		
		
		function GenerarRepFactNC(){
			
			ajax=getXMLObject();
			var tanos=document.getElementById('Iano').value;
			var tdia=document.getElementById('Idia').value;
			var tmeses=document.getElementById('Imes').value;
			var Ent=document.getElementById('Ent').value;
			var ftanos=document.getElementById('Fano').value;
			var ftdia=document.getElementById('Fdia').value;
			var Ftmeses=document.getElementById('Fmes').value;
			if((tmeses=="40")||(tdia=="40")||(tanos=="40")||(ftanos=="40")||(ftdia=="40")||(Ftmeses=="40")){
				
				alert("No ha seleccionado todos los Datos ");
			
			 }else{
					//alert("valor=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+Ftmeses+"&ftdia="+ftdia);
					pagina="reporteFactNC.jsp?tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+Ftmeses+"&ftdia="+ftdia+"&ftent="+Ent;
					var opciones="toolbar=yes, menubar=yes , location=yes ";
					opciones=opciones+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
					ajax.open("POST","ControlReportesFact",true);
					ajax.onreadystatechange = function(){
						if(ajax.readyState == 4){
							//alert(ajax.reposenText);
							//contenido.innerHTML=ajax.responseText;
							//window.location.href="reporte.jsp?tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+Ftmeses+"&ftdia="+ftdia;
							//mostrarep(tanos,tmeses,tdia,ftanos,ftanos,Ftmeses,Ftmeses,ftdia);
							window.open(pagina,"NUEVA",opciones);
						}
					}
			
				ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=datos");
			 }
	}










	
