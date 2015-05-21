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
var xmlhttp = new getXMLObject();

var mensajeReq = false;
var mensaje8 = false;
var mensaje9 = false;
var mensaje5 = false;
var mensaje3 = false;
var mensaje22 = false;
var cgoes="";
var conte="";
var contenido="";
var mensaje2 = false;
var codigos ="";
var codProblemas ="";
var mensaje = false;
var horaactual = "";
var Observaciones="";	



function crear() {	
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
	var radioButtons = document.getElementsByName("radiobutton");
	var codigous=document.getElementById("usu").value;
	
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			codProblemas ="";
			var val=radioButtons[x].id;    
			ajax.open("POST","ControlSoporte",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					if(ajax.responseText!=""){
						contenido.innerHTML = ajax.responseText;
					}else{
						alert("Solo puede tener Activa tres solicitudes");
					}
						
				}
			};
			var fechaHoy;
			fechaHoy = new Date();
			var Dia = fechaHoy.getDate();
			var Mes = fechaHoy.getMonth();
			var MesHoy = Mes+1;
			var aniohoy = fechaHoy.getFullYear();
			var horahoy = fechaHoy.getHours();
			var minhoy= fechaHoy.getMinutes();
			var seghoy = fechaHoy.getSeconds();
			var fechatoday = (aniohoy+"-"+MesHoy+"-"+Dia+"");
			
			
			
			
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val+"&codigous="+codigous+"&fechatoday="+fechatoday); //Posting txtname to Servlet
		}	     
	}
}


function ModificarSol(){
	var contenido=document.getElementById('Opcion');
	ajax=getXMLObject();
	ajax.open("POST","ControlSoporte",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
				contenido.innerHTML = ajax.responseText;	
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
}

function msjalerta(){
	var contenido=document.getElementById('Opcion');
	var codigous=document.getElementById("usu").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlSoporte",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);
			window.location.reload();
		}
		}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=9&codigous="+codigous); //Posting txtname to Servlet
}



function ventCrearSolicitud(codsol){

	//var formulario = document.getElementById('form1');
	
	prueba23(codsol);
	fondo = document.createElement('div');
	mensaje = document.createElement('div');
	fondo.setAttribute('id','fondo');
	mensaje.setAttribute('id','msgTus');
	document.getElementsByTagName('body')[0].appendChild(fondo);
	document.getElementsByTagName('body')[0].appendChild(mensaje);
	

}

function ventCrearSolicitudReq(codsol){

	//var formulario = document.getElementById('form1');
	
	pruebaReq(codsol);
	fondoReq = document.createElement('div');
	mensajeReq = document.createElement('div');
	fondoReq.setAttribute('id','fondo');
	mensajeReq.setAttribute('id','msgTus');
	document.getElementsByTagName('body')[0].appendChild(fondoReq);
	document.getElementsByTagName('body')[0].appendChild(mensajeReq);
	

}

function pruebaReq(codsol){
	ajax=getXMLObject();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){	
			//var actua=ajax.responseText;
			mensajeReq.innerHTML = ajax.responseText;	
			
		}
	}
	ajax.open("POST","ControlSoporte",true);
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=51&codsol="+codsol);
}
function ventCrearSolicitud3(codsol5){

	//var formulario5 = document.getElementById('form1');
	
	prueba5(codsol5);
	fondo5 = document.createElement('div');
	mensaje5 = document.createElement('div');
	fondo5.setAttribute('id','fondo');
	mensaje5.setAttribute('id','msgHis');
	document.getElementsByTagName('body')[0].appendChild(fondo5);
	document.getElementsByTagName('body')[0].appendChild(mensaje5);

}

function ventCrearSolicitud3Req(codsol5){

	//var formulario5 = document.getElementById('form1');
	
	prueba5Req(codsol5);
	fondo5Req = document.createElement('div');
	mensaje5Req = document.createElement('div');
	fondo5Req.setAttribute('id','fondo');
	mensaje5Req.setAttribute('id','msgHis');
	document.getElementsByTagName('body')[0].appendChild(fondo5Req);
	document.getElementsByTagName('body')[0].appendChild(mensaje5Req);

}

function prueba5Req(codsol5){
	ajax=getXMLObject();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){	
			
			mensaje5Req.innerHTML = ajax.responseText;	
			
		}
	}
	ajax.open("POST","ControlSoporte",true);
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=111&codsol="+codsol5);
}

function ventCrearSolicitud8(){

	//var formulario8 = document.getElementById('form1');
	
	validarfechareporte1();
	

}



function validarfechareporte2(){
	
	
	var fechainisub = document.getElementById("fchinisub").value;
	var fechafinsub = document.getElementById("fchfinsub").value;
	
	if(fechainisub.length<=9){
		alert("Debe digital la fecha inicial");
	}else{
		if(fechafinsub.length<=9){
			alert("Debe digitar la fecha final");
		}
		
	}
	if((fechainisub.length>9)&&(fechafinsub.length>9)){
		fondo9 = document.createElement('div');
		mensaje9 = document.createElement('div');
		fondo9.setAttribute('id','fondo');
		mensaje9.setAttribute('id','msgrepsub');
		document.getElementsByTagName('body')[0].appendChild(fondo9);
		document.getElementsByTagName('body')[0].appendChild(mensaje9);
		ajax=getXMLObject();
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var actua=ajax.responseText;
				mensaje9.innerHTML = ajax.responseText;
				
			}
		}
		
		var fechaHoyRep2;
		fechaHoyRep2 = new Date();
		var DiaRep2 = fechaHoyRep2.getDate();
		var MesRep2 = fechaHoyRep2.getMonth();
		var MesHoyRep2 = MesRep2+1;
		var aniohoyRep2 = fechaHoyRep2.getFullYear();
		var horahoyRep2 = fechaHoyRep2.getHours();
		var minhoyRep2= fechaHoyRep2.getMinutes();
		var seghoyRep2 = fechaHoyRep2.getSeconds();
		var fechatodayRep2 = (aniohoyRep2+"-"+MesHoyRep2+"-"+DiaRep2+"");
		
		ajax.open("POST","ControlSoporte",true);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=14&fechainisub="+fechainisub+"&fechafinsub="+fechafinsub+"&fechatodayRep2="+fechatodayRep2);
		
	}
	
}
function validarfechareporteReq2(){
	
	
	var fechainisubReq = document.getElementById("fchinisub").value;
	var fechafinsubReq = document.getElementById("fchfinsub").value;
	
	if(fechainisubReq.length<=9){
		alert("Debe digital la fecha inicial");
	}else{
		if(fechafinsubReq.length<=9){
			alert("Debe digitar la fecha final");
		}
		
	}
	if((fechainisubReq.length>9)&&(fechafinsubReq.length>9)){
		fondo9 = document.createElement('div');
		mensaje9 = document.createElement('div');
		fondo9.setAttribute('id','fondo');
		mensaje9.setAttribute('id','msgrepsub');
		document.getElementsByTagName('body')[0].appendChild(fondo9);
		document.getElementsByTagName('body')[0].appendChild(mensaje9);
		ajax=getXMLObject();
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var actua=ajax.responseText;
				mensaje9.innerHTML = ajax.responseText;
				
			}
		}
		
		var fechaHoyRepReq2;
		fechaHoyRepReq2 = new Date();
		var DiaRepReq2 = fechaHoyRepReq2.getDate();
		var MesRepReq2 = fechaHoyRepReq2.getMonth();
		var MesHoyRepReq2 = MesRepReq2+1;
		var aniohoyRepReq2 = fechaHoyRepReq2.getFullYear();
		var horahoyRepReq2 = fechaHoyRepReq2.getHours();
		var minhoyRepReq2= fechaHoyRepReq2.getMinutes();
		var seghoyRepReq2 = fechaHoyRepReq2.getSeconds();
		var fechatodayRepReq2 = (aniohoyRepReq2+"-"+MesHoyRepReq2+"-"+DiaRepReq2+"");
		
		ajax.open("POST","ControlSoporte",true);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=14.1&fechainisub="+fechainisubReq+"&fechafinsub="+fechafinsubReq+"&fechatodayRep2="+fechatodayRepReq2);
		
	}
	
}
function validarfechareporte1(){
	

	var fechaini = document.getElementById("fchini").value;
	var fechafin = document.getElementById("fchfin").value;
	
	if(fechaini.length<=9){
		alert("Debe digitar la fecha inicial");		
	}else{
		if(fechafin.length<=9){
			alert("Debe digitar la fecha final");
		}
	}
		if((fechaini.length>9)&&(fechafin.length>9)){
			fondo8 = document.createElement('div');
			mensaje8 = document.createElement('div');
			fondo8.setAttribute('id','fondo');
			mensaje8.setAttribute('id','msgrep');
			document.getElementsByTagName('body')[0].appendChild(fondo8);
			document.getElementsByTagName('body')[0].appendChild(mensaje8)
		ajax=getXMLObject();
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var actua=ajax.responseText;
				mensaje8.innerHTML = ajax.responseText;
				
			}
		}
		var fechaHoyRep;
		fechaHoyRep = new Date();
		var DiaRep = fechaHoyRep.getDate();
		var MesRep = fechaHoyRep.getMonth();
		var MesHoyRep = MesRep+1;
		var aniohoyRep = fechaHoyRep.getFullYear();
		var horahoyRep = fechaHoyRep.getHours();
		var minhoyRep= fechaHoyRep.getMinutes();
		var seghoyRep = fechaHoyRep.getSeconds();
		var fechatodayRep = (aniohoyRep+"-"+MesHoyRep+"-"+DiaRep+"");
		
		ajax.open("POST","ControlSoporte",true);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=13&fechaini="+fechaini+"&fechafin="+fechafin+"&fechatodayRep="+fechatodayRep);
		
		}else{
			
		}
	
	
}
function validarfechareporteReq1(){
	

	var fechainireq = document.getElementById("fchini").value;
	var fechafinreq = document.getElementById("fchfin").value;
	
	if(fechainireq.length<=9){
		alert("Debe digitar la fecha inicial");		
	}else{
		if(fechafinreq.length<=9){
			alert("Debe digitar la fecha final");
		}
	}
		if((fechainireq.length>9)&&(fechafinreq.length>9)){
			fondo8 = document.createElement('div');
			mensaje8 = document.createElement('div');
			fondo8.setAttribute('id','fondo');
			mensaje8.setAttribute('id','msgrep');
			document.getElementsByTagName('body')[0].appendChild(fondo8);
			document.getElementsByTagName('body')[0].appendChild(mensaje8)
		ajax=getXMLObject();
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var actua=ajax.responseText;
				mensaje8.innerHTML = ajax.responseText;
				
			}
		}
		var fechaHoyRep;
		fechaHoyRep = new Date();
		var DiaRep = fechaHoyRep.getDate();
		var MesRep = fechaHoyRep.getMonth();
		var MesHoyRep = MesRep+1;
		var aniohoyRep = fechaHoyRep.getFullYear();
		var horahoyRep = fechaHoyRep.getHours();
		var minhoyRep= fechaHoyRep.getMinutes();
		var seghoyRep = fechaHoyRep.getSeconds();
		var fechatodayRep = (aniohoyRep+"-"+MesHoyRep+"-"+DiaRep+"");
		
		ajax.open("POST","ControlSoporte",true);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=13.1&fechaini="+fechainireq+"&fechafin="+fechafinreq+"&fechatodayRep="+fechatodayRep);
		
		}else{
			
		}
	
	
}
function mostrarSol(){
	
	var contenidosol=document.getElementById('Opcion');
	var fechaini = document.getElementById("fchinibus").value;
	var fechafin = document.getElementById("fchfinbus").value;
	
	if(fechaini.length<=9){
		alert("Debe digitar la fecha inicial");		
	}else{
		if(fechafin.length<=9){
			alert("Debe digitar la fecha final");
		}
	}
		if((fechaini.length>9)&&(fechafin.length>9)){
		ajax=getXMLObject();
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var actua=ajax.responseText;
				contenidosol.innerHTML = ajax.responseText;
				
			}
		}
		ajax.open("POST","ControlSoporte",true);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=16&fechaini="+fechaini+"&fechafin="+fechafin);
		
		}else{
			
		}
	
	
}

function mostrarSolReq(){
	
	var contenidosol=document.getElementById('Opcion');
	var fechaini = document.getElementById("fchinibus").value;
	var fechafin = document.getElementById("fchfinbus").value;
	
	if(fechaini.length<=9){
		alert("Debe digitar la fecha inicial");		
	}else{
		if(fechafin.length<=9){
			alert("Debe digitar la fecha final");
		}
	}
		if((fechaini.length>9)&&(fechafin.length>9)){
		ajax=getXMLObject();
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var actua=ajax.responseText;
				contenidosol.innerHTML = ajax.responseText;
				
			}
		}
		ajax.open("POST","ControlSoporte",true);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=161&fechaini="+fechaini+"&fechafin="+fechafin);
		
		}else{
			
		}
	
	
}

function mostrarSolA(){
	
	var contenidosol=document.getElementById('Opcion');
	var codareabus = document.getElementById("cmbOpMenu").value;
	

		ajax=getXMLObject();
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var actua=ajax.responseText;
				contenidosol.innerHTML = ajax.responseText;
				
			}
		}
		ajax.open("POST","ControlSoporte",true);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=18&codareabus="+codareabus);
		
		
	
}
function mostrarSolAReq(){
	
	var contenidosol2=document.getElementById('Opcion');
	var codareabus = document.getElementById("cmbOpMenu").value;
	

		ajax=getXMLObject();
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var actua=ajax.responseText;
				contenidosol2.innerHTML = ajax.responseText;
				
			}
		}
		ajax.open("POST","ControlSoporte",true);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=181&codareabus="+codareabus);
		
		
	
}

function CambioOpcionesMenusop(){
	var CodigoAreaSop=document.getElementById("cmbMenusop").value;
	var OpcionesMenu=document.getElementById('OpcionesMenusop');	
	ajax=getXMLObject();
	ajax.open("POST","ControlSoporte",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			OpcionesMenu.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3.1&CodigoAreaSop="+CodigoAreaSop);
}
function CambioOpcionesMenusopReq(){
	var CodigoAreaSop=document.getElementById("cmbMenusop").value;
	var OpcionesMenu=document.getElementById('OpcionesMenusop');	
	ajax=getXMLObject();
	ajax.open("POST","ControlSoporte",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			OpcionesMenu.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3.12&CodigoAreaSop="+CodigoAreaSop);
}

function prueba23(codsol){
	ajax=getXMLObject();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){	
			//var actua=ajax.responseText;
			mensaje.innerHTML = ajax.responseText;	
			
		}
	}
	ajax.open("POST","ControlSoporte",true);
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5&codsol="+codsol);
}

function prueba5(codsol5){
	ajax=getXMLObject();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){	
			var actua=ajax.responseText;
			mensaje5.innerHTML = ajax.responseText;	
			
		}
	}
	ajax.open("POST","ControlSoporte",true);
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=11&codsol="+codsol5);
}


function Records(codsol){
	//alert(codsol);
	
	
	var fecha3;
	var Observaciones = document.getElementById('observacionesAlex').value;
	var mens = document.getElementById('est');
	var codisoli = document.getElementById('codisolici2').value;
	var select = document.getElementById('est');
	var est = select.childNodes;
	var codes = document.getElementById('estads').value;

	//if(codes!="1"){
		for(var j=0;j<est.length;j++){
			var chl = est[j].childNodes;
				for(var i=1;i<chl.length;i++){	
					codigos += chl[i].value+"_";
				}
				codigos = codigos.substring(0, codigos.length-1);
				codigos += ",";	
		}

		
		codigos = codigos.substring(0, codigos.length-1);
		
	
		for(var z=0;z<est.length;z++){
			var chle = est[z].childNodes;
				for(var i2=1;i2<chle.length;i2++){	
					conte += chle[i2].value+"_";
				}
				conte = conte.substring(0, conte.length-1);
				conte += ";";	
		}
		
		conte = conte.substring(0, conte.length-1);

		
		var prueba2 = conte.split(";");
		var prueba4 = "";		
		var contaes =0;	
		var contalon = 0;
		
		for(var t=0;t<prueba2.length;t++){
		  var pruebas3 = prueba2[t].split("_");
		  prueba4 += pruebas3[1]+"-";
		}
		prueba4 = prueba4.substring(0, prueba4.length-1);
		var cgoesta = prueba4.split("-");
		for(var w=0;w<cgoesta.length;w++){
			 
			if((cgoesta[w]=="5")||(cgoesta[w]=="6")){
			    	contaes++;
			}
			contalon++;
		}
		
			
	fecha3 = new Date();
	var day3 = fecha3.getDate();
	var mes3 = fecha3.getMonth();
	var mesactual3 = mes3+1;
	var anio3 = fecha3.getFullYear();
	var hora3 = fecha3.getHours();
	var min3= fecha3.getMinutes();
	var seg3 = fecha3.getSeconds();
	var fechaactual2 = (anio3+"-"+mesactual3+"-"+day3+"");
     horaactual2 = (hora3+":"+min3+":"+seg3+"");
     ajax=getXMLObject();
		ajax.open("POST","ControlSoporte",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				if(ajax.responseText!=""){
					window.location.reload();
				}else{
					alert("Datos modificados con exito.");
					window.location.reload();
					document.getElementsByTagName('body')[0].removeChild(fondo);
					document.getElementsByTagName('body')[0].removeChild(mensaje);
					fondo=false;
					mensaje=false;
				}
				
				
				
			}
		}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=6&codsol="+codsol+"&codigos="+codigos+"&Observaciones="+Observaciones+"&fechaactual2="+fechaactual2+"&horaactual2="+horaactual2);
	//}else
		//alert("No ha modificado ningun estado")

		}

function RecordsReq(codsol){
	//alert(codsol);
	
	
	var fecha3;
	var Observaciones = document.getElementById('observacionesAlex').value;
	var mens = document.getElementById('est');
	var codisoli = document.getElementById('codisolici2').value;
	var select = document.getElementById('est');
	var est = select.childNodes;
	var codes = document.getElementById('estads').value;

	//if(codes!="1"){
		for(var j=0;j<est.length;j++){
			var chl = est[j].childNodes;
				for(var i=1;i<chl.length;i++){	
					codigos += chl[i].value+"_";
				}
				codigos = codigos.substring(0, codigos.length-1);
				codigos += ",";	
		}

		
		codigos = codigos.substring(0, codigos.length-1);
		
	
		for(var z=0;z<est.length;z++){
			var chle = est[z].childNodes;
				for(var i2=1;i2<chle.length;i2++){	
					conte += chle[i2].value+"_";
				}
				conte = conte.substring(0, conte.length-1);
				conte += ";";	
		}
		
		conte = conte.substring(0, conte.length-1);

		
		var prueba2 = conte.split(";");
		var prueba4 = "";		
		var contaes =0;	
		var contalon = 0;
		
		for(var t=0;t<prueba2.length;t++){
		  var pruebas3 = prueba2[t].split("_");
		  prueba4 += pruebas3[1]+"-";
		}
		prueba4 = prueba4.substring(0, prueba4.length-1);
		var cgoesta = prueba4.split("-");
		for(var w=0;w<cgoesta.length;w++){
			 
			if((cgoesta[w]=="5")||(cgoesta[w]=="6")){
			    	contaes++;
			}
			contalon++;
		}
		
			
	fecha3 = new Date();
	var day3 = fecha3.getDate();
	var mes3 = fecha3.getMonth();
	var mesactual3 = mes3+1;
	var anio3 = fecha3.getFullYear();
	var hora3 = fecha3.getHours();
	var min3= fecha3.getMinutes();
	var seg3 = fecha3.getSeconds();
	var fechaactual2 = (anio3+"-"+mesactual3+"-"+day3+"");
     horaactual2 = (hora3+":"+min3+":"+seg3+"");
     ajax=getXMLObject();
		ajax.open("POST","ControlSoporte",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				if(ajax.responseText!=""){
					window.location.reload();
				}else{
					alert("Datos modificados con exito.");
					window.location.reload();
					document.getElementsByTagName('body')[0].removeChild(fondoReq);
					document.getElementsByTagName('body')[0].removeChild(mensajeReq);
					fondoReq=false;
					mensajeReq=false;
				}
				
				
				
			}
		}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=61&codsol="+codsol+"&codigos="+codigos+"&Observaciones="+Observaciones+"&fechaactual2="+fechaactual2+"&horaactual2="+horaactual2);
	//}else
		//alert("No ha modificado ningun estado")

		}
function ImprimirReporte(){
	var FechaActualRep = document.getElementById("FechaActualRep").value;
	var RepFechaIni = document.getElementById("fchestadosub").value;
	var RepFechaFin = document.getElementById("hraestadosub").value;
	var RepCodProbCom = document.getElementById("codprobcomunrep").value;
	pagina="sop_reporte1.jsp?RepFechaIni="+RepFechaIni+"&RepFechaFin="+RepFechaFin+"&RepCodProbCom="+RepCodProbCom+"&FechaActualRep="+FechaActualRep;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
	
}
function ImprimirReporte2(){
	var FechaActualRep2 = document.getElementById("FechaActualRep2").value;
	var RepFechaIni2 = document.getElementById("fchestadosub2").value;
	var RepFechaFin2 = document.getElementById("hraestadosub2").value;
	pagina="sop_reporte2.jsp?RepFechaIni2="+RepFechaIni2+"&RepFechaFin2="+RepFechaFin2+"&FechaActualRep2="+FechaActualRep2;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
	
}
function ventCrearSolicitud2(codsol2){

	//var formulario2 = document.getElementById('form1');
	
	prueba22(codsol2);
	fondo3 = document.createElement('div');
	mensaje3 = document.createElement('div');
	fondo3.setAttribute('id','fondo');
	mensaje3.setAttribute('id','msglistSol');
	document.getElementsByTagName('body')[0].appendChild(fondo3);
	document.getElementsByTagName('body')[0].appendChild(mensaje3);
	

}

function ventCrearSolicitud2Req(codsol2){

	//var formulario2 = document.getElementById('form1');
	
	prueba2Req(codsol2);
	fondo3Req = document.createElement('div');
	mensaje3Req = document.createElement('div');
	fondo3Req.setAttribute('id','fondo');
	mensaje3Req.setAttribute('id','msglistSol');
	document.getElementsByTagName('body')[0].appendChild(fondo3Req);
	document.getElementsByTagName('body')[0].appendChild(mensaje3Req);
	

}

function prueba2Req(codsol2,horaactual12){
	ajax=getXMLObject();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){	
			var actua=ajax.responseText;
			mensaje3Req.innerHTML = ajax.responseText;	
			
		}
	}
	ajax.open("POST","ControlSoporte",true);
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=82&codsol="+codsol2+"&horaactual12="+horaactual12);
}

function prueba22(codsol2,horaactual12){
	ajax=getXMLObject();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){	
			var actua=ajax.responseText;
			mensaje3.innerHTML = ajax.responseText;	
			
		}
	}
	ajax.open("POST","ControlSoporte",true);
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=8&codsol="+codsol2+"&horaactual12="+horaactual12);
}

function GuardarEstadistica(){
	
	var fechaEst;
	var codesttec = document.getElementById("EstadoA").value;
	fechaEst = new Date();
	var dayEst = fechaEst.getDate();
	var mesEst = fechaEst.getMonth();
	var mesactualEst = mesEst+1;
	var anioEst = fechaEst.getFullYear();
	var horaEst = fechaEst.getHours();
	var minEst = fechaEst.getMinutes();
	var segEst = fechaEst.getSeconds();
	var fechaactualEst = (anioEst+"-"+mesactualEst+"-"+dayEst+"");
	var horaactualEst = (horaEst+":"+minEst+":"+segEst+"");
	
	ajax=getXMLObject();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){	
			var actua=ajax.responseText;
			mensaje3.innerHTML = ajax.responseText;	
			
		}
	}
	ajax.open("POST","ControlSoporte",true);
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=12&codesttec="+codesttec+"&fechaactualEst="+fechaactualEst+"&horaactualEst="+horaactualEst);
}


function ver(){
	var fecha;
	var solicitudes = document.getElementById('listSolicitudes');
	ajax=getXMLObject();
	ajax.open("POST","ControlSoporte",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			
			var actualizar=ajax.responseText;
			solicitudes.innerHTML=actualizar;
		}
	}
	fecha = new Date();
	var day = fecha.getDate();
	var mes = fecha.getMonth();
	var mesactual = mes+1;
	var anio = fecha.getFullYear();
	var hora = fecha.getHours();
	var min = fecha.getMinutes();
	var seg = fecha.getSeconds();
	var fechaactual = (anio+"-"+mesactual+"-"+day+"");
	var horaactual = (hora+":"+min+":"+seg+"");
	
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=4&fechaactual="+fechaactual);
	}

function ver2(){
	var fecha;
	var solicitudes = document.getElementById('listSolicitudes');
	ajax=getXMLObject();
	ajax.open("POST","ControlSoporte",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			
			var actualizar=ajax.responseText;
			solicitudes.innerHTML=actualizar;
		}
	}
	fecha = new Date();
	var day = fecha.getDate();
	var mes = fecha.getMonth();
	var mesactual = mes+1;
	var anio = fecha.getFullYear();
	var hora = fecha.getHours();
	var min = fecha.getMinutes();
	var seg = fecha.getSeconds();
	var fechaactual = (anio+"-"+mesactual+"-"+day+"");
	var horaactual = (hora+":"+min+":"+seg+"");
	
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=41&fechaactual="+fechaactual);
	}


function Ingresarsolicitud(){
	var fecha;
	var EstDet = document.getElementById('EstSoliG').value;
	
	//var Observaciones = document.getElementById('observaciones').value;
	//var Observaciones1=encodeURIComponent(Observaciones);
	var problema = document.getElementById('P1').value;
	var Estado = document.getElementById('estado').value;
	var TipoSolicitud= document.getElementById('Hardware').value;
	var codarea = document.getElementById('area').value;
	var Codigou= document.getElementById("usu").value;
	var codiprioridad= document.getElementById("prioridad").value;
	
	if(codarea=="Seleccione"){
		alert("Debe Elegir el area a la que pertenece");
	}else{
		var codsubarea = document.getElementById('sbarea').value;
	     if(codsubarea=="Seleccione"){
	    	 alert("Debe elegir la subarea a la que pertenece");
	     }else{
	    	 if(codiprioridad=="Seleccione"){
	    		 alert("Debe seleccionar la prioridad de la solicitud");
	    	 }else{
				if(problema=="Seleccione"){
				alert("Debe seleccionar el/los problemas que presenta");
				}
			}
		}
	}
	if((codarea!="Seleccione")&&(codiprioridad!="Seleccione")&&(codsubarea!="Seleccione")&&(problema!="Seleccione") ){
		recargar();
		ajax=getXMLObject();
		ajax.open("POST","ControlSoporte",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				alert(ajax.responseText);
				window.location.reload();
				
			}
			}
	
		fecha = new Date();
		var day = fecha.getDate();
		var mes = fecha.getMonth();
		var mesactual = mes+1;
		var anio = fecha.getFullYear();
		var hora = fecha.getHours();
		var min = fecha.getMinutes();
		var seg = fecha.getSeconds();
		var fechaactual = (anio+"-"+mesactual+"-"+day+"");
		var horaactual = (hora+":"+min+":"+seg+"");
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2&TipoSolicitud="+TipoSolicitud+"&codsubarea="+codsubarea+"&Codigou="+Codigou+"&Estado="+Estado+"&fechaactual="+fechaactual+"&horaactual="+horaactual+"&codiprioridad="+codiprioridad+"&EstDet="+EstDet+"&problema="+problema+"&codProblemas="+codProblemas+"&contenido="+contenido);
		
	}
	}
	
function IngresarsolicitudReq(){
	var fecha;
	var EstDet = document.getElementById('EstSoliG').value;
	
	//var Observaciones = document.getElementById('observaciones').value;
	//var Observaciones1=encodeURIComponent(Observaciones);
	var problema = document.getElementById('P1').value;
	var TipoSolicitud= document.getElementById('Requerimiento').value;
	var codarea = document.getElementById('area').value;
	var Codigou= document.getElementById("usu").value;
	var codiprioridad= document.getElementById("prioridad").value;
	
	if(codarea=="Seleccione"){
		alert("Debe Elegir el area a la que pertenece");
	}else{
		var codsubarea = document.getElementById('sbarea').value;
	     if(codsubarea=="Seleccione"){
	    	 alert("Debe elegir la subarea a la que pertenece");
	     }else{
	    	 if(codiprioridad=="Seleccione"){
	    		 alert("Debe seleccionar la prioridad de la solicitud");
	    	 }else{
				if(problema=="Seleccione"){
				alert("Debe seleccionar el/los problemas que presenta");
				}
			}
		}
	}
	if((codarea!="Seleccione")&&(codiprioridad!="Seleccione")&&(codsubarea!="Seleccione")&&(problema!="Seleccione") ){
		recargar();
		ajax=getXMLObject();
		ajax.open("POST","ControlSoporte",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				alert(ajax.responseText);
				window.location.reload();
				
			}
			}
	
		fecha = new Date();
		var day = fecha.getDate();
		var mes = fecha.getMonth();
		var mesactual = mes+1;
		var anio = fecha.getFullYear();
		var hora = fecha.getHours();
		var min = fecha.getMinutes();
		var seg = fecha.getSeconds();
		var fechaactual = (anio+"-"+mesactual+"-"+day+"");
		var horaactual = (hora+":"+min+":"+seg+"");
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=22&TipoSolicitud="+TipoSolicitud+"&codsubarea="+codsubarea+"&Codigou="+Codigou+"&fechaactual="+fechaactual+"&horaactual="+horaactual+"&codiprioridad="+codiprioridad+"&EstDet="+EstDet+"&problema="+problema+"&codProblemas="+codProblemas+"&contenido="+contenido);
		
	}
	}
	

function MostrarSubArea2(){

	var CodArea=document.getElementById('area').value;
	var ActuMedi=document.getElementById("ActuMedi");

	
	if(CodArea!="Seleccione"){
		ajax=getXMLObject();
		ajax.open("POST","ControlSoporte",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				
				var actualizar=ajax.responseText;
				ActuMedi.innerHTML=actualizar;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&CodArea="+CodArea);
	
	}
	}
	

	
function MostrarProblemas(){
	var existe = false;
	var select = document.getElementById('P1');
	var sMultiple = document.getElementById('selmul');
	var codigous=document.getElementById("usu").value;
	var problema = false;
	
	if(select.value=="Seleccione"){
		alert("debe elegir una opcion")
	}else{
		
	var index = select.selectedIndex;
	var option = document.createElement('option');
	option.text = select.options[index].text;
	option.value = select.options[index].value;
	
	
	var detCodProblemas = codProblemas.split("-");
	for (var i=0;i<detCodProblemas.length;i++){
		if(select.options[index].value==detCodProblemas[i]){
			existe=true;
		}
	}
	
	
	if(!existe){
		contenido+=select.options[index].value;
		codProblemas += select.options[index].value+"-";
		sMultiple.add(option);
		if(confirm("Desea agregar una observacion")){
			//var formulario2 = document.getElementById('form12');		
			fondo2 = document.createElement('div');
			mensaje2 = document.createElement('div');
			fondo2.setAttribute('id','fondo');
			mensaje2.setAttribute('id','msgasi');
			document.getElementsByTagName('body')[0].appendChild(fondo2);
			document.getElementsByTagName('body')[0].appendChild(mensaje2);
			
			codProblemas = codProblemas.substring(0,codProblemas.length-1);
			ajax=getXMLObject();
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){	
					//if(ajax.responseText!=""){
						mensaje2.innerHTML = "<form id='form32' name='form32'>" +
							"<div id='Observacion'class='VentanaObservacionV' >" +
							"<input type='hidden' id='cdproblema' value='"+codProblemas+"'>" +
					        "<p class='ventobserva'><b>OBSERVACIONES<b></p>" +
					    	"<textarea name='textarea' id='observacionesaa2' rows='5' cols='60'class='ventanaobservacion'></textarea>" +
					    	"<br><br><input name='btnCancelar' type='button' id='btnEvoCoordenadas' value='Cerrar' onclick='cerrar(1)' class='btnCerrarVentana'/>" +
							"<input name='btnAceptar' type='button' id='btnEvoSolicitudes'  value='Aceptar' onclick='GuardarObservacion()' class='btnAceptarVentana'/>" +
							"</div>" +
							"</form>";
					
					
				}
			}
			ajax.open("POST","ControlSoporte",true);
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=7&codProblemas="+codProblemas);
			
		}else{
		contenido+="-|;";
		
	}
	}
	
		
	}
	
	
}
function GuardarObservacion(){
	var cont="";
	var observa = document.getElementById("observacionesaa2").value;
	
	contenido+="-"+observa+";";
	
	
	document.getElementsByTagName('body')[0].removeChild(fondo2);
	document.getElementsByTagName('body')[0].removeChild(mensaje2);
	fondo2=false;
	mensaje2=false;
}
	


function cerrar(ventana){
	if (ventana==0){
		document.getElementsByTagName('body')[0].removeChild(fondo);
		document.getElementsByTagName('body')[0].removeChild(mensaje);
		fondo=false;
		mensaje=false;
	}else
		if (ventana==1){
			contenido+="-|;";
			document.getElementsByTagName('body')[0].removeChild(fondo2);
			document.getElementsByTagName('body')[0].removeChild(mensaje2);
			fondo2=false;
			mensaje2=false;
		}else
			if(ventana==2){
				document.getElementsByTagName('body')[0].removeChild(fondo3);
				document.getElementsByTagName('body')[0].removeChild(mensaje3);
				fondo3=false;
				mensaje3=false;
				
			}else
				if(ventana==4){
					document.getElementsByTagName('body')[0].removeChild(fondo5);
					document.getElementsByTagName('body')[0].removeChild(mensaje5);
					fondo5=false;
					mensaje5=false;
		
				}else
					if(ventana==8){
						document.getElementsByTagName('body')[0].removeChild(fondo8);
						document.getElementsByTagName('body')[0].removeChild(mensaje8);
						fondo8=false;
						mensaje8=false;
						
					}else{
						if(ventana==9){
							document.getElementsByTagName('body')[0].removeChild(fondo9);
							document.getElementsByTagName('body')[0].removeChild(mensaje9);
							fondo9=false;
							mensaje9=false;
						}else
							if(ventana==10){
								document.getElementsByTagName('body')[0].removeChild(fondoReq);
								document.getElementsByTagName('body')[0].removeChild(mensajeReq);
								fondoReq=false;
								mensajeReq=false;
							}else
								if(ventana==11){
									document.getElementsByTagName('body')[0].removeChild(fondo3Req);
									document.getElementsByTagName('body')[0].removeChild(mensaje3Req);
									fondo3Req=false;
									mensaje3Req=false;
								}else
									if(ventana==12){
										document.getElementsByTagName('body')[0].removeChild(fondo5Req);
										document.getElementsByTagName('body')[0].removeChild(mensaje5Req);
										fondo5Req=false;
										mensaje5Req=false;
									}
					}
}
var patron = new Array(2,2,4);
function masca(d,pat,nums,annio,mes,dias){
	var sep="-";
	if(d.valant != d.value){
		val = d.value
		largo = val.length
		ini=val.substring(0,2);
		if((ini>31)||(ini=="00")){
			alert("Dia No Valido!!!");
			val=d.value="";
			d.focus();
		}
		val = val.split(sep);

		val2 = ''
		for(r=0;r<val.length;r++){
			val2 += val[r]
		}
		ini=val2.substring(2,4);
		if((ini=="04")||(ini=="06")||(ini=="09")||(ini=="11")){
			x=val2.substring(0,2);
			if(x=="31"){
				val2="30";
				val2=val2+ini;
			}
		}
		if((ini>12)||(ini=="00")){
			alert("Mes No Valido!!!");
			val2=d.value="";
			d.focus();
		}
		
		ano=val2.substring(4,8);
		dia=val2.substring(0,2);
		if(ini=="02"){
			if((dia=="30")||(dia=="31")||(dia=="29")){
			if(ano.length==4){
				if ( ( ano % 100 != 0) && ((ano % 4 == 0) || (ano % 400 == 0))) {
					val2="29";
					val2=val2+ini;
				}else{
					val2="28";
					val2=val2+ini;
				}
				val2=val2+ano;
			}
		  }
		}
		///////////////////////////////////////////////////
		//Validar fecha mayor que la fecha actual
			
		if(ano.length==4){
			if (ano==annio) {
				if(ini==mes){
					if((dia==dias)||(dia<dias)){
						alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
					val2='';
					}
				}else{
					if(ini<mes){
						alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
						val2='';
					}
				}
			}
			if (ano<annio) {
				alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
				val2='';
			}
		}
		
		///////////////////////////////////////////////////
		
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
	 		val2 = val2.substring(pat[s])
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
function recargar(){
	var t=setTimeout("ver()",500);
}
