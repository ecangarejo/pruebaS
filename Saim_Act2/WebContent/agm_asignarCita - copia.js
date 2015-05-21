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
function BuscarRecibo(){
	var NumRecibo=document.getElementById("txtNumRecibo").value;
	var TipoRec=document.getElementById("cmbTipoRec").value;
	//var radiobutton = document.getElementsByName("radiobutton");
	var Conte=document.getElementById("BuscRec");
	/*var TipoBus="";
	for (var x = 0; x < radiobutton.length; x ++) {
		if (radiobutton[x].checked) {
			TipoBus=radiobutton[x].id;
		}
	}	*/
	
	//alert("valor=BR1&TipoRec="+TipoRec+"&NumRecibo="+NumRecibo);
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){				
			Conte.innerHTML = ajax.responseText;	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=BR1&TipoRec="+TipoRec+"&NumRecibo="+NumRecibo);

	
	//alert("TipoRec "+TipoRec+ " TipoBus "+TipoBus);
}


function buscarRecibosIngFech(){
	var diaIni=document.getElementById('cmbdiaIni').value;
	var mesIni=document.getElementById('cmbmesIni').value;
	var anioIni=document.getElementById('txtanioIni').value;
	var Fecha=anioIni+"-"+mesIni+"-"+diaIni;
	 alert("Ingreso "+Fecha);
	 
}


/*****************************************************************************************/
/*****************************************************************************************/


function fecha(anio,mes,dia) {

	date=anio+"-"+mes+"-"+dia;

	var fecha=document.getElementById("txtFechaSeleccion").value=date;
	var CodigoPaciente=document.getElementById("txtCodigoPaciente").value;
	var CodigoEspecialidad=document.getElementById("cmbEspecialidad").value;
	var CodigoMedico=document.getElementById("cmbMedico").value;
	var HorasCitas=document.getElementById("HorasCitas");
	if(fecha==""){alert("Seleccione La Fecha De La Cita")}
	
	if(CodigoPaciente==""){alert("No Hay Paciente Seleccionado.")}
	
	if(CodigoEspecialidad=="Seleccione"){alert("Seleccione Una Especialidad.")}
	
	if(CodigoMedico=="Seleccione"){alert("Seleccione Un Medico.")}
	
	
	if((fecha!="")&&(CodigoPaciente!="")&&(CodigoEspecialidad!="Seleccione")&&(CodigoMedico!="Seleccione")){

		ajax=getXMLObject();
		ajax.open("POST","ControlAsignarCita",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){				
				HorasCitas.innerHTML = ajax.responseText	
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&fecha="+fecha+"&CodigoPaciente="+CodigoPaciente+"&CodigoEspecialidad="+CodigoEspecialidad+"&CodigoMedico="+CodigoMedico);
	}
}

function buscarCoincidencias(){
	ajax=getXMLObject();
	var hora=document.getElementById("cmbHora").value;
	var CodigoMedico=document.getElementById("cmbMedico").value;
	var CodigoEspecialidad=document.getElementById("cmbEspecialidad").value;
	var CodigoPaciente=document.getElementById("txtCodigoPaciente").value;
	var rbFecha = document.getElementsByName("rbFecha");
	var Coincidencias=document.getElementById("HorasCitas");
	var fecha="N";
	for (var x = 0; x < rbFecha.length; x ++) {
		if (rbFecha[x].checked) {
			fecha=rbFecha[x].value; 
		}
	}
	if((hora=="Seleccione")&&(CodigoMedico=="Seleccione")&&(CodigoEspecialidad=="Seleccione")&&(fecha=="N")){
		alert("Seleccione Parametros de Busqueda.");
	}
	/**********************4 variables********************************/
	if((hora!="Seleccione")&&(CodigoMedico!="Seleccione")&&(CodigoEspecialidad!="Seleccione")&&(fecha!="N")){
		//alert("Consulta Medico-Especialidad-hora-fecha");
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Coincidencias.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&op=mehf&fecha="+fecha+"&hora="+hora+"&CodigoMedico="+CodigoMedico+"&CodigoEspecialidad="+CodigoEspecialidad+"&CodigoPaciente="+CodigoPaciente); //Posting txtname to Servlet
		
	}
	/********************3 variables*********************************/
	if((hora!="Seleccione")&&(CodigoMedico=="Seleccione")&&(CodigoEspecialidad!="Seleccione")&&(fecha!="N")){
		//alert("Consulta Especialidad-hora-fecha");
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Coincidencias.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&op=ehf&fecha="+fecha+"&hora="+hora+"&CodigoEspecialidad="+CodigoEspecialidad+"&CodigoPaciente="+CodigoPaciente); //Posting txtname to Servlet
	
	}
	if((hora!="Seleccione")&&(CodigoMedico!="Seleccione")&&(CodigoEspecialidad=="Seleccione")&&(fecha!="N")){
		//alert("Consulta Medico-hora-fecha");
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Coincidencias.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&op=mhf&fecha="+fecha+"&hora="+hora+"&CodigoMedico="+CodigoMedico+"&CodigoPaciente="+CodigoPaciente); //Posting txtname to Servlet
	
	}
	if((hora=="Seleccione")&&(CodigoMedico!="Seleccione")&&(CodigoEspecialidad!="Seleccione")&&(fecha!="N")){
		//alert("Consulta Medico-Especialidad-fecha");
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Coincidencias.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&op=mef&fecha="+fecha+"&CodigoMedico="+CodigoMedico+"&CodigoEspecialidad="+CodigoEspecialidad+"&CodigoPaciente="+CodigoPaciente); //Posting txtname to Servlet
	
	}
	if((hora!="Seleccione")&&(CodigoMedico!="Seleccione")&&(CodigoEspecialidad!="Seleccione")&&(fecha=="N")){
		//alert("Consulta Medico-Especialidad-hora");
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Coincidencias.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&op=meh&hora="+hora+"&CodigoMedico="+CodigoMedico+"&CodigoEspecialidad="+CodigoEspecialidad+"&CodigoPaciente="+CodigoPaciente); //Posting txtname to Servlet
	
	}
	/******************2 variables******************************/
	if((hora!="Seleccione")&&(CodigoMedico=="Seleccione")&&(CodigoEspecialidad=="Seleccione")&&(fecha!="N")){
		//alert("Consulta hora-fecha");
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Coincidencias.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&op=hf&fecha="+fecha+"&hora="+hora+"&CodigoPaciente="+CodigoPaciente); //Posting txtname to Servlet
	
	}
	if((hora=="Seleccione")&&(CodigoMedico=="Seleccione")&&(CodigoEspecialidad!="Seleccione")&&(fecha!="N")){
		//alert("Consulta Especialidad-fecha");
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Coincidencias.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&op=ef&fecha="+fecha+"&CodigoEspecialidad="+CodigoEspecialidad+"&CodigoPaciente="+CodigoPaciente); //Posting txtname to Servlet
	
	}
	if((hora!="Seleccione")&&(CodigoMedico=="Seleccione")&&(CodigoEspecialidad!="Seleccione")&&(fecha=="N")){
		//alert("Consulta Especialidad-hora");
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Coincidencias.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&op=eh&hora="+hora+"&CodigoEspecialidad="+CodigoEspecialidad+"&CodigoPaciente="+CodigoPaciente); //Posting txtname to Servlet
	
	}
	if((hora=="Seleccione")&&(CodigoMedico!="Seleccione")&&(CodigoEspecialidad=="Seleccione")&&(fecha!="N")){
		//alert("Consulta Medico-fecha");
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Coincidencias.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&op=mf&fecha="+fecha+"&CodigoMedico="+CodigoMedico+"&CodigoPaciente="+CodigoPaciente); //Posting txtname to Servlet
	
	}
	if((hora!="Seleccione")&&(CodigoMedico!="Seleccione")&&(CodigoEspecialidad=="Seleccione")&&(fecha=="N")){
		//alert("Consulta Medico-hora");
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Coincidencias.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&op=mh&hora="+hora+"&CodigoMedico="+CodigoMedico+"&CodigoPaciente="+CodigoPaciente); //Posting txtname to Servlet
	
	}
	if((hora=="Seleccione")&&(CodigoMedico!="Seleccione")&&(CodigoEspecialidad!="Seleccione")&&(fecha=="N")){
		//alert("Consulta Medico-Especialidad");
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Coincidencias.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&op=me&CodigoMedico="+CodigoMedico+"&CodigoEspecialidad="+CodigoEspecialidad+"&CodigoPaciente="+CodigoPaciente); //Posting txtname to Servlet
	
	}
	/********************1 variable****************************/
	if((hora=="Seleccione")&&(CodigoMedico=="Seleccione")&&(CodigoEspecialidad=="Seleccione")&&(fecha!="N")){
		//alert("Consulta fecha");
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Coincidencias.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&op=f&fecha="+fecha+"&CodigoPaciente="+CodigoPaciente); //Posting txtname to Servlet
	
	}
	if((hora!="Seleccione")&&(CodigoMedico=="Seleccione")&&(CodigoEspecialidad=="Seleccione")&&(fecha=="N")){
		//alert("Consulta hora");
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Coincidencias.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&op=h&hora="+hora+"&CodigoPaciente="+CodigoPaciente); //Posting txtname to Servlet
	
	}
	if((hora=="Seleccione")&&(CodigoMedico=="Seleccione")&&(CodigoEspecialidad!="Seleccione")&&(fecha=="N")){
		//alert("Consulta Especialidad");
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Coincidencias.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&op=e&CodigoEspecialidad="+CodigoEspecialidad+"&CodigoPaciente="+CodigoPaciente); //Posting txtname to Servlet
	
	}
	if((hora=="Seleccione")&&(CodigoMedico!="Seleccione")&&(CodigoEspecialidad=="Seleccione")&&(fecha=="N")){
		//alert("Consulta Medico");
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Coincidencias.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&op=m&CodigoMedico="+CodigoMedico+"&CodigoPaciente="+CodigoPaciente); //Posting txtname to Servlet
	
	}
}

function BuscarPaciente(){
	var TipoDocumento=document.getElementById("cmbTipoDoc").value;
	var NumeroDocumento=document.getElementById("txtNumDocumento").value;
	var resultado=document.getElementById('resultado');
	var Coincidencias=document.getElementById("HorasCitas");
	if(TipoDocumento=="Seleccione"){alert("Seleccione El Tipo De Documento.")}
	if(NumeroDocumento==""){alert("Digite El Numero De Documento.")}
	
	if((TipoDocumento!="Seleccione")&&(NumeroDocumento!="")){
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;
			if(validar=="1"){
				window.location.href("adm_IngresarDemografico.jsp");
			}else{
			resultado.innerHTML = validar;
			Coincidencias.innerHTML="";
			}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento);
}
}

function BuscarMedicos(){
	var medico=document.getElementById('medico');
	var CodigoEspecialidad=document.getElementById("cmbEspecialidad").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){			
			medico.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2&CodigoEspecialidad="+CodigoEspecialidad);
}

function UnClick(hora,minuto,jornada,codigoHorarioMedico,Anio,Mes,Dia,CodigoEspecialidad,CodigoMedico){
	var fecha=Anio+"-"+Mes+"-"+Dia;
	if(jornada=="0"){
		jornada="AM"
	}
	if(jornada=="1"){
		jornada="PM"
	}
	var Hora=hora+":"+minuto+":"+jornada;
	
	var NombreMes="";
	if(Mes=="1"){NombreMes="Enero"}
	if(Mes=="2"){NombreMes="Febrero"}
	if(Mes=="3"){NombreMes="Marzo"}
	if(Mes=="4"){NombreMes="Abril"}
	if(Mes=="5"){NombreMes="Mayo"}
	if(Mes=="6"){NombreMes="Junio"}
	if(Mes=="7"){NombreMes="Julio"}
	if(Mes=="8"){NombreMes="Agosto"}
	if(Mes=="9"){NombreMes="Septiembre"}
	if(Mes=="10"){NombreMes="Octubre"}
	if(Mes=="11"){NombreMes="Noviembre"}
	if(Mes=="12"){NombreMes="Diciembre"}
	var opcion=confirm("Desea Asignar Una Cita El Dia\n"+Dia+" de "+NombreMes+" del "+Anio+" A las: "+Hora+" ?");
	if(opcion){
	var CodigoPaciente=document.getElementById("txtCodigoPaciente").value;
	var NombreCompleto=document.getElementById("NombreCompleto").value;
	var UsuarioInsercion=document.getElementById("txtCodusuario").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){			
			var valor = ajax.responseText;
			if(valor=="1"){
				alert("Cita Asignada Satisfactoriamente.");
				window.location.reload();
			}else{
				alert(valor);
			}
			
		}
	
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4&CodigoEspecialidad="+CodigoEspecialidad+"&codigoHorarioMedico="+codigoHorarioMedico+"&CodigoPaciente="+CodigoPaciente+"&CodigoMedico="+CodigoMedico+"&NombrePaciente="+NombreCompleto+"&UsuarioInsercion="+UsuarioInsercion+"&Fecha="+fecha);
}//fin del si opcion
	else{
		
	}
}


function DosClick(hora,minuto,jornada,codigoHorarioMedico,Anio,Mes,Dia){
	var fecha=Anio+"-"+Mes+"-"+Dia;
	if(jornada=="0"){
		jornada="AM"
	}
	if(jornada=="1"){
		jornada="PM"
	}
	var Hora=hora+":"+minuto+":"+jornada;
	
	var NombreMes="";
	if(Mes=="1"){NombreMes="Enero"}
	if(Mes=="2"){NombreMes="Febrero"}
	if(Mes=="3"){NombreMes="Marzo"}
	if(Mes=="4"){NombreMes="Abril"}
	if(Mes=="5"){NombreMes="Mayo"}
	if(Mes=="6"){NombreMes="Junio"}
	if(Mes=="7"){NombreMes="Julio"}
	if(Mes=="8"){NombreMes="Agosto"}
	if(Mes=="9"){NombreMes="Septiembre"}
	if(Mes=="10"){NombreMes="Octubre"}
	if(Mes=="11"){NombreMes="Noviembre"}
	if(Mes=="12"){NombreMes="Diciembre"}
	var CodigoPaciente=document.getElementById("txtCodigoPaciente").value;
	
	var opcion=confirm("Desea Cancelar La Cita Del Dia\n"+Dia+" de "+NombreMes+" del "+Anio+" A las: "+Hora+" ?");
	if(opcion){
	ajax=getXMLObject();
	var valor=null;
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			valor = ajax.responseText;
			if(valor=="1"){
				alert("Cita Cancelada Satisfactoriamente.");
				window.location.reload();
			}else{
				alert(valor);
			}
		}
		//alert("Cita Cancelada Con Exito.")
		//window.location.reload();
	}
	//alert("codigoHorarioMedico "+codigoHorarioMedico+" CodigoPaciente "+CodigoPaciente+" CodigoEspecialidad "+CodigoEspecialidad+" CodigoMedico "+CodigoMedico);

	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5&codigoHorarioMedico="+codigoHorarioMedico+"&CodigoPaciente="+CodigoPaciente);
	}else{
		
	}
}
