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
//////////////////////////////////////////////////////////////////////////////////////////////////////////
function Radio() {	
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","PYP_ControlParametros",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val); //Posting txtname to Servlet
		}	     
	}
}
function checkAll() {
	 var i;
  var nodoCheck = document.getElementsByTagName("input");
  var varCheck = document.getElementById("chkTodos").checked;
  for (i=0; i<nodoCheck.length; i++){
      if (nodoCheck[i].type == "checkbox" && nodoCheck[i].name != "all" && nodoCheck[i].disabled == false) {
          nodoCheck[i].checked = varCheck;
      }
  } 
}

function OmitirParametro(CodigoHorario){
	ajax=getXMLObject();
	ajax.open("POST","PYP_ControlParametros",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			alert(ajax.responseText);
			window.location.reload();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3&CodigoParametro="+CodigoHorario); //Posting txtname to Servlet*/
	
}
function GuardarActualizacionParametros(CodigoParametro){	
	var horaIni=document.getElementById("cmbHoraInicio").value;
	var minutosIni=document.getElementById("cmbMinutoInicio").value;
	var jornadaIni=document.getElementById("cmbJornadaInicio").value;
	
	var horaFin=document.getElementById("cmbHoraFin").value;
	var minutosFin=document.getElementById("cmbMinutoFin").value;
	var jornadaFin=document.getElementById("cmbJornadaFin").value;
	
	if((jornadaIni=="AM")&&(jornadaFin=="PM")){
		GuardarActualizacion(CodigoParametro);
	}
	
	if((jornadaIni=="PM")&&(jornadaFin=="AM")){
		alert("Horario Tiene Que ir De Jornada AM a Jornada PM");
	}
	
	if((jornadaIni=="AM")&&(jornadaFin=="AM")){
		if(horaIni>horaFin){
			alert("La Hora Inicio No Puede Ser Mayor a la Hora Final.");
		}
		if(horaIni<horaFin){
			GuardarActualizacion(CodigoParametro);
		}
		if((horaIni==horaFin)&&(minutosIni==minutosFin)){
			alert("Las Horas No Pueden Ser Iguales.");
		}
		
		if((horaIni==horaFin)&&(minutosIni<minutosFin)){
			GuardarActualizacion(CodigoParametro);
		}
	}
	if((jornadaIni=="PM")&&(jornadaFin=="PM")){
		if(horaIni>horaFin){
			alert("La Hora Inicio No Puede Ser Mayor a la Hora Final.");
		}
		if(horaIni<horaFin){
			GuardarActualizacion(CodigoParametro);
		}
		if((horaIni==horaFin)&&(minutosIni==minutosFin)){
			alert("Las Horas No Pueden Ser Iguales.");
		}
		if((horaIni==horaFin)&&(minutosIni<minutosFin)){
			GuardarActualizacion(CodigoParametro);
		}
	}
}


function GuardarActualizacion(CodigoParametro){
	
	var horaIni=document.getElementById("cmbHoraInicio").value;
	var minutosIni=document.getElementById("cmbMinutoInicio").value;
	var jornadaIni=document.getElementById("cmbJornadaInicio").value;
	
	var horaFin=document.getElementById("cmbHoraFin").value;
	var minutosFin=document.getElementById("cmbMinutoFin").value;
	var jornadaFin=document.getElementById("cmbJornadaFin").value;
	
	if(jornadaFin=="PM"){
		horaFin=parseInt(horaFin)+12;
		if(horaFin==24){
			horaFin=0;
		}

	}
	
	var HoraInicial=horaIni+":"+minutosIni+":"+jornadaIni;
	var HoraFinal=horaFin+":"+minutosFin+":"+jornadaFin;
	
	var MinCita=document.getElementById("txtDuracionConsulta").value; 
	
	if(MinCita!=""){
		ajax=getXMLObject();
		ajax.open("POST","PYP_ControlParametros",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				alert(ajax.responseText);
				window.location.reload();
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2&HoraInicial="+HoraInicial+"&HoraFinal="+HoraFinal+"&tiempoCita="+MinCita+"&CodigoParametro="+CodigoParametro); //Posting txtname to Servlet*/
		}
	
}

function ActualizarParametros(){
	var c=document.getElementById('txtContador').value;
	var oscar=c;
	if(c!=1){		
		for(var i=0 ; i<=c-1 ; i++){
			CodigoParametro=document.form1.txtCodigoParametro[i].value;
			//GuardarActualizacion(CodigoParametro);
			GuardarActualizacionParametros(CodigoParametro)
			oscar--;
		}
	}
	if(c==1){
		CodigoParametro=document.form1.txtCodigoParametro.value;
		//GuardarActualizacion(CodigoParametro);
		GuardarActualizacionParametros(CodigoParametro)
	}
}

function GuardarAsignacionDiasHora(Dia){	
	var horaIni=document.getElementById("cmbHoraIni").value;
	var minutosIni=document.getElementById("cmbMinIni").value;
	var jornadaIni=document.getElementById("cmbJorIni").value;
	
	var horaFin=document.getElementById("cmnHoraFin").value;
	var minutosFin=document.getElementById("cmbMinFin").value;
	var jornadaFin=document.getElementById("cmbJorFin").value;
	
	if((jornadaIni=="AM")&&(jornadaFin=="PM")){
		guarda(Dia);
	}
	
	if((jornadaIni=="PM")&&(jornadaFin=="AM")){
		alert("Horario Tiene Que ir De Jornada AM a Jornada PM");
	}
	
	if((jornadaIni=="AM")&&(jornadaFin=="AM")){
		if(horaIni>horaFin){
			alert("La Hora Inicio No Puede Ser Mayor a la Hora Final.");
		}
		if(horaIni<horaFin){
			guarda(Dia);
		}
		if((horaIni==horaFin)&&(minutosIni==minutosFin)){
			alert("Las Horas No Pueden Ser Iguales.");
		}
		
		if((horaIni==horaFin)&&(minutosIni<minutosFin)){
			guarda(Dia);
		}
	}
	if((jornadaIni=="PM")&&(jornadaFin=="PM")){
		if(horaIni>horaFin){
			alert("La Hora Inicio No Puede Ser Mayor a la Hora Final.");
		}
		if(horaIni<horaFin){
			guarda(Dia);
		}
		if((horaIni==horaFin)&&(minutosIni==minutosFin)){
			alert("Las Horas No Pueden Ser Iguales.");
		}
		if((horaIni==horaFin)&&(minutosIni<minutosFin)){
			guarda(Dia);
		}
	}
}

function guarda(Dia){
	var horaIni=document.getElementById("cmbHoraIni").value;
	var minutosIni=document.getElementById("cmbMinIni").value;
	var jornadaIni=document.getElementById("cmbJorIni").value;
	
	var horaFin=document.getElementById("cmnHoraFin").value;
	var minutosFin=document.getElementById("cmbMinFin").value;
	var jornadaFin=document.getElementById("cmbJorFin").value;

	var MinCita=document.getElementById("txtMinuConsulta").value;
	var TCita="";

	if(jornadaFin=="PM"){
		horaFin=parseInt(horaFin)+12;
		if(horaFin==24){
			horaFin=0;
		}

	}
	
	var HoraInicial=horaIni+":"+minutosIni+":"+jornadaIni;
	var HoraFinal=horaFin+":"+minutosFin+":"+jornadaFin;	

	if(MinCita==""){
		alert("Digite la Duracion de la Cita");
	}
	if(MinCita!=""){
	ajax=getXMLObject();
	ajax.open("POST","PYP_ControlParametros",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			alert(ajax.responseText);
			window.location.reload();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1&Dia="+Dia+"&HoraInicial="+HoraInicial+"&HoraFinal="+HoraFinal+"&tiempoCita="+MinCita); //Posting txtname to Servlet*/
	}
	
}

function GuardarHorasDias(){
	var c=7;//7 igual a los dias de la semana.
	var oscar=c;
	 if(c!=1){
			for(var i=0; i<=c-1; i++){	
		      if (form1.dia[i].checked) {
		    	  Dia=form1.dia[i].value;
		       	GuardarAsignacionDiasHora(Dia);
		      }
		      oscar--; 
		     }	
		 } 
		 else{
			 if(form1.dia.checked){
				 Dia=form1.dia.value;
				 GuardarAsignacionDiasHora(Dia);
			}
		 }
}