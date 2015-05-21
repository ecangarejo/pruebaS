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
////////////////////////////////////////////////////////////////////////////
function AsignarFormato(){
	var CodFormato=document.getElementById("txtCodFormato").value;
	var CodPaciente=document.getElementById("txtCodPac").value;
	var CodUsuario=document.getElementById("txtCodigoUsuario").value;
	var CodOrden=document.getElementById("txtCodOrden").value;
	var Hora=document.getElementById("txtHora").value;
	var Fecha=document.getElementById("txtFecha").value;
	var vacio=" ";
	var vacio2="";
	if(CodPaciente==""){
		alert("No Se Ha Seleccionado Ningun Paciente!!!");
	}		
	if(CodPaciente!=""){
		ajax=getXMLObject();
		ajax.open("POST","Pat_ControlListarPacientes",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if(ajax.readyState == 4){
				alert(ajax.responseText);
				window.location.href("pat_AsignarEstudio.jsp?TipoDocumento="+vacio+"&Identificacion="+vacio+"&CodOrden="+vacio2);
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&CodFormato="+CodFormato+"&CodPaciente="+CodPaciente+"&CodUsuario="+CodUsuario+"&Hora="+Hora+"&Fecha="+Fecha+"&CodOrden="+CodOrden); //Posting txtname to Servlet
	}	
}
function VerFormatosLlenar(CodFormato,dia,mes,anio,horas,minutos,segundos,CodPaciente,CodAsignacion,Tipo){
	var Fecha=anio+"-"+mes+"-"+dia;
	var Hora=horas+":"+minutos+":"+segundos;
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlListarPacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if(ajax.readyState == 4){
			document.getElementById('Pacientes').innerHTML = ajax.responseText;	
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3&CodAsignacion="+CodAsignacion+"&HoraFormato="+Hora+"&FechaFormato="+Fecha+"&CodPaciente="+CodPaciente+"&CodFormato="+CodFormato+"&Tipo="+Tipo); //Posting txtname to Servlet
		   //var x;
}

function CargarPendientes(){
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlListarPacientes");
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById('Pacientes').innerHTML = ajax.responseText;			
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	ajax.send("valor=2");
}


function IngresarEstudioBR(){
	var CodAsignacion = document.getElementById("txtCodAsignacion").value;
	var Protocolo = document.getElementById("txtProtocolo").value;
	var TipoEspecimen = encodeURIComponent(document.getElementById("txtTipoespecimen").value);
	var Medico = document.getElementById("txtMedicoTratante").value;
	var FechaRecibo = document.getElementById("txtFechaRecibo").value;
	var FechaEntrega = document.getElementById("txtFechaEntrega").value;
	var DiagnosticoClinico = encodeURIComponent(document.getElementById("txtDiagClinico").value);
	
	var DesMacro= encodeURIComponent(document.getElementById("txtDesMacro").value);
	var DescMicro1= encodeURIComponent(document.getElementById("txtDescMicro1").value);
	var DescMicro2= encodeURIComponent(document.getElementById("txtDescMicro2").value);
	var ObserLleno= encodeURIComponent(document.getElementById("txtObserLleno").value);
	
	var RE1= document.getElementById("RE1").value;
	var RE2= document.getElementById("RE2").value;
	var RE3= document.getElementById("RE3").value;
	var RE4= document.getElementById("RE4").value;
	var RE5= document.getElementById("RE5").value;
	var RE6= document.getElementById("RE6").value;
	var RE7= document.getElementById("RE7").value;
	var RE8= document.getElementById("RE8").value;
	var RE9= document.getElementById("RE9").value;
	var RE10= document.getElementById("RE10").value;
	
	var IT1= encodeURIComponent(document.getElementById("IT1").value);
	var IT2= encodeURIComponent(document.getElementById("IT2").value);
	var IT3= encodeURIComponent(document.getElementById("IT3").value);
	var IT4= encodeURIComponent(document.getElementById("IT4").value);
	var IT5= encodeURIComponent(document.getElementById("IT5").value);
	var IT6= encodeURIComponent(document.getElementById("IT6").value);
	var IT7= encodeURIComponent(document.getElementById("IT7").value);
	var IT8= encodeURIComponent(document.getElementById("IT8").value);
	var IT9= encodeURIComponent(document.getElementById("IT9").value);
	var IT10= encodeURIComponent(document.getElementById("IT10").value);
	
	var PTR1= encodeURIComponent(document.getElementById("PTR1").value);
	var PTR2= encodeURIComponent(document.getElementById("PTR2").value);
	var PTR3= encodeURIComponent(document.getElementById("PTR3").value);
	var PTR4= encodeURIComponent(document.getElementById("PTR4").value);
	var PTR5= encodeURIComponent(document.getElementById("PTR5").value);
	var PTR6= encodeURIComponent(document.getElementById("PTR6").value);
	var PTR7= encodeURIComponent(document.getElementById("PTR7").value);
	var PTR8= encodeURIComponent(document.getElementById("PTR8").value);
	var PTR9= encodeURIComponent(document.getElementById("PTR9").value);
	var PTR10= encodeURIComponent(document.getElementById("PTR10").value);
	
	var Diagnostico=encodeURIComponent(document.getElementById("txtDiagnostico").value);
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlCrearPaciente");
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);	
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5BR&CodAsignacion="+CodAsignacion+"&Protocolo="+Protocolo+
			"&TipoEspecimen="+TipoEspecimen+"&Medico="+Medico+
			"&FechaRecibo="+FechaRecibo+"&FechaEntrega="+FechaEntrega+
			"&DiagnosticoClinico="+DiagnosticoClinico+
			"&DesMacro="+DesMacro+"&DescMicro1="+DescMicro1+"&DescMicro2="+DescMicro2+"&ObserLleno="+ObserLleno+
			"&RE1="+RE1+"&RE2="+RE2+"&RE3="+RE3+"&RE4="+RE4+"&RE5="+RE5+"&RE6="+RE6+"&RE7="+RE7+"&RE8="+RE8+"&RE9="+RE9+"&RE10="+RE10+
			"&IT1="+IT1+"&IT2="+IT2+"&IT3="+IT3+"&IT4="+IT4+"&IT5="+IT5+"&IT6="+IT6+"&IT7="+IT7+"&IT8="+IT8+"&IT9="+IT9+"&IT10="+IT10+
			"&PTR1="+PTR1+"&PTR2="+PTR2+"&PTR3="+PTR3+"&PTR4="+PTR4+"&PTR5="+PTR5+"&PTR6="+PTR6+"&PTR7="+PTR7+"&PTR8="+PTR8+"&PTR9="+PTR9+"&PTR10="+PTR10+
			"&Diagnostico="+Diagnostico);
	
}


function IngresarEstudio(){
	var CodAsignacion = document.getElementById("txtCodAsignacion").value;
	var Protocolo = document.getElementById("txtProtocolo").value;
	var TipoEspecimen = document.getElementById("txtTipoespecimen").value;
	var Medico = document.getElementById("txtMedicoTratante").value;
	var FechaRecibo = document.getElementById("txtFechaRecibo").value;
	var FechaEntrega = document.getElementById("txtFechaEntrega").value;
	var DiagnosticoClinico = document.getElementById("txtDiagClinico").value;
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlCrearPaciente");
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);	
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5&CodAsignacion="+CodAsignacion+"&Protocolo="+Protocolo+"&TipoEspecimen="+TipoEspecimen+"&Medico="+Medico+"&FechaRecibo="+FechaRecibo+"&FechaEntrega="+FechaEntrega+"&DiagnosticoClinico="+DiagnosticoClinico);
	
}


function ActualizarResultHistorias(Resul,CodResul){
	var Codi=encodeURIComponent(Resul)
	ajax=getXMLObject();	
		ajax.open("POST","Pat_ControlListarPacientes",true); //getname will be the servlet name
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=4&Resul="+Codi+"&CodResul="+CodResul); //Posting txtname to Servlet    
}

function ActualizarResultados(){
	var c=document.getElementById('txtTotal').value;
	var oscar=c;
	var a;
	if(c!=1){
		for(var i=0; i<=c-1; i++){
			CodResul=document.form1.txtCodResultado[i].value;
			Resul=document.form1.txtRespuesta[i].value;
			//Resul=encodeURIComponent(Resul);
			for(a=0;a<Resul.length;a++){
				//Resul=Resul.replace('Ñ','@');
				//Resul=Resul.replace('ñ','@');				
			}			
			ActualizarResultHistorias(Resul,CodResul);
	      oscar--; 
	     }	
	 }else{	
		 Resul=document.getElementById("txtRespuesta").value;
		 for(e=0;e<Resul.length;e++){
				//Resul=Resul.replace('Ñ','@');
				//Resul=Resul.replace('ñ','@');				
			}
		 CodResul=document.getElementById("txtCodResultado").value;
		 ActualizarResultHistorias(Resul,CodResul);
	 }
}

function IngresarPacientePatologia(){
	var TipoDocumento=document.getElementById("tipodoc").value;
	var Identificacion=document.getElementById("txtnumdocu").value;
	var Apellidos=document.getElementById("txtapellidos").value;
	var Nombre=document.getElementById("txtnombres").value;
	var FechaNacimiento=document.getElementById("txtfechanaci").value;
	var Telefono=document.getElementById("txtele").value;
	var Direccion=document.getElementById("txtdireccion").value;
	var Eps=document.getElementById("txteps").value;
	var Email=document.getElementById("txtemail").value;
	var Genero=document.getElementById("cbgenero").value;
	
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlCrearPaciente",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;			
			alert(validar);
			window.location.href("pat_AsignarEstudio.jsp?TipoDocumento="+TipoDocumento+"&Identificacion="+Identificacion);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1&TipoDocumento="+TipoDocumento+"&Identificacion="+Identificacion+"&Apellidos="+Apellidos+"&Nombre="+Nombre+"&FechaNacimiento="+FechaNacimiento+"&Telefono="+Telefono+"&Direccion="+Direccion+"&Direccion="+Direccion+"&Email="+Email+"&Eps="+Eps+"&Genero="+Genero);


}

///////////////////////////////////////////////////////////////////////////
function Radio() {	
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","Pat_ControlCrearPaciente",true); //getname will be the servlet name
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

function CrearArea(){
	var NombreArea=document.getElementById("txtNomArea").value;
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlCrearPaciente",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;			
			alert(validar);
			CargarArea();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2.1&NombreArea="+NombreArea);

}
function CargarArea(){
	
	var contenido=document.getElementById('Opcion');
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlCrearPaciente",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
			
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2");

}


function CrearEstudio(){
	var CodArea_fk=document.getElementById("cmbArea").value;
	var NombreEstudio=document.getElementById("txtNomEstudio").value;
	if(CodArea_fk=="Seleccione"){
		alert("Seleccione Una Opcion Valida");
	}else{
		if(NombreEstudio==""){
			alert("El Campo Nombre del Estudio No Puede Ir Vacio");
		}
	}
	if((CodArea_fk!="Seleccione")&&(NombreEstudio!="")){
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlCrearPaciente",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;			
			alert(validar);
			CargarEstudioPorArea();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3.1&CodArea_fk="+CodArea_fk+"&NombreEstudio="+NombreEstudio);
	}
}
function CargarEstudioPorArea(){
	var relacion=document.getElementById('relacion');
	var CodArea_fk=document.getElementById("cmbArea").value;
	
	if(CodArea_fk=="Seleccione"){
		alert("Seleccione Una Opcion Valida");
	}
	if(CodArea_fk!="Seleccione"){
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlCrearPaciente",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			relacion.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3.2&CodArea_fk="+CodArea_fk);
	}
}

function BuscarPaciente(){
	var formulario=document.getElementById('formulario');
	var TipoDocumento=document.getElementById("cbafiliacion").value;
	var Identificacion=document.getElementById("txtnumdoc").value;
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlCrearPaciente",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var form=ajax.responseText;			
			if(form==""){
				window.location.href="pat_CrearPaciente.jsp?Identificacion="+Identificacion+"&TipoDocumento="+TipoDocumento,true;
			}
			if(form!=""){
				formulario.innerHTML = form;
			}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4&TipoDocumento="+TipoDocumento+"&Identificacion="+Identificacion);
}


function BuscarPacienteAprobar(){
	var formulario=document.getElementById('formulario');
	//var TipoDocumento=document.getElementById("cbafiliacion").value;
	//var Identificacion=document.getElementById("txtnumdoc").value;
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlCrearPaciente",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//var form=;				
				formulario.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4.22");
}

function BuscarPacienteActu(){
	var formulario=document.getElementById('formulario');
	var TipoDocumento=document.getElementById("cbafiliacion").value;
	var Identificacion=document.getElementById("txtnumdoc").value;
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlCrearPaciente",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var form=ajax.responseText;			
			if(form==""){
				window.location.href="pat_CrearPaciente.jsp?Identificacion="+Identificacion+"&TipoDocumento="+TipoDocumento,true;
			}
			if(form!=""){
				formulario.innerHTML = form;
			}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4.2&TipoDocumento="+TipoDocumento+"&Identificacion="+Identificacion);
}

function VerAprobPato(CodAsignacion,dia,mes,anio,horas,minutos,segundos,Tipo,TipoDoc,NumDoc){	
	FechaPato=anio+"-"+mes+"-"+dia;
	HoraPato=horas+":"+minutos+":"+segundos;
	var formulario=document.getElementById('formulario');

	//alert("FechaPato "+FechaPato+" HoraPato "+HoraPato+" CodAsignacion "+CodAsignacion);
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlCrearPaciente",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			formulario.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4.33&CodAsignacion="+CodAsignacion+"&Tipo="+Tipo+"&TipoDocumento="+TipoDoc+"&Identificacion="+NumDoc);
}

function VerActuPato(CodAsignacion,dia,mes,anio,horas,minutos,segundos){	
	FechaPato=anio+"-"+mes+"-"+dia;
	HoraPato=horas+":"+minutos+":"+segundos;
	var formulario=document.getElementById('formulario');

	//alert("FechaPato "+FechaPato+" HoraPato "+HoraPato+" CodAsignacion "+CodAsignacion);
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlCrearPaciente",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			formulario.innerHTML = ajax.responseText;	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4.3&CodAsignacion="+CodAsignacion);
}

function VerFormato(){
	var Formato=document.getElementById('Formato');
	var CodigoEstudio=document.getElementById("cmbEstudios").value;
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlCrearPaciente",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			Formato.innerHTML = ajax.responseText;	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4.1&CodigoEstudio="+CodigoEstudio);
}



function AprobarPatologia1(){
	var CodAsignacion=document.getElementById("txtCodAsignacion").value;
	var CodDatosComplementarios=document.getElementById("txtCodDatosComplementarios").value;
	var Protocolo=document.getElementById("txtProtocolo").value;
	var Tipoespecimen=encodeURIComponent(document.getElementById("txtTipoespecimen").value);
	var MedicoTratante=document.getElementById("txtMedicoTratante").value;
	var FechaRecibo=document.getElementById("txtFechaRecibo").value;
	var FechaEntrega=document.getElementById("txtFechaEntrega").value;
	var DiagClinico=encodeURIComponent(document.getElementById("txtDiagClinico").value);
	var CodUsu=document.getElementById("txtUsuario").value;
	
	var DesMacro= encodeURIComponent(document.getElementById("txtDesMacro").value);
	var DescMicro1= encodeURIComponent(document.getElementById("txtDescMicro1").value);
	var DescMicro2= encodeURIComponent(document.getElementById("txtDescMicro2").value);
	var ObserLleno= encodeURIComponent(document.getElementById("txtObserLleno").value);
	
	var RE1= document.getElementById("RE1").value;
	var RE2= document.getElementById("RE2").value;
	var RE3= document.getElementById("RE3").value;
	var RE4= document.getElementById("RE4").value;
	var RE5= document.getElementById("RE5").value;
	var RE6= document.getElementById("RE6").value;
	var RE7= document.getElementById("RE7").value;
	var RE8= document.getElementById("RE8").value;
	var RE9= document.getElementById("RE9").value;
	var RE10= document.getElementById("RE10").value;
	
	var IT1= encodeURIComponent(document.getElementById("IT1").value);
	var IT2= encodeURIComponent(document.getElementById("IT2").value);
	var IT3= encodeURIComponent(document.getElementById("IT3").value);
	var IT4= encodeURIComponent(document.getElementById("IT4").value);
	var IT5= encodeURIComponent(document.getElementById("IT5").value);
	var IT6= encodeURIComponent(document.getElementById("IT6").value);
	var IT7= encodeURIComponent(document.getElementById("IT7").value);
	var IT8= encodeURIComponent(document.getElementById("IT8").value);
	var IT9= encodeURIComponent(document.getElementById("IT9").value);
	var IT10= encodeURIComponent(document.getElementById("IT10").value);
	
	var PTR1= encodeURIComponent(document.getElementById("PTR1").value);
	var PTR2= encodeURIComponent(document.getElementById("PTR2").value);
	var PTR3= encodeURIComponent(document.getElementById("PTR3").value);
	var PTR4= encodeURIComponent(document.getElementById("PTR4").value);
	var PTR5= encodeURIComponent(document.getElementById("PTR5").value);
	var PTR6= encodeURIComponent(document.getElementById("PTR6").value);
	var PTR7= encodeURIComponent(document.getElementById("PTR7").value);
	var PTR8= encodeURIComponent(document.getElementById("PTR8").value);
	var PTR9= encodeURIComponent(document.getElementById("PTR9").value);
	var PTR10= encodeURIComponent(document.getElementById("PTR10").value);
	
	var Diagnostico=encodeURIComponent(document.getElementById("txtDiagnostico").value);
	var CodBiopsia=document.getElementById("txtCodBiopsia").value;
	/*var txtProtocolo=document.getElementById("txtProtocolo").value;
	var txtProtocolo=document.getElementById("txtProtocolo").value;
	var txtProtocolo=document.getElementById("txtProtocolo").value;*/
	
	
	//alert("Resul "+Codi+" CodResul "+CodResul+" CodAsignacion "+CodAsignacion+" CodDatosComplementarios "+CodDatosComplementarios);
	  ajax=getXMLObject();	
		ajax.open("POST","Pat_ControlListarPacientes",true); //getname will be the servlet name
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				alert("Aprobacion Exitosa.");
				window.location.reload();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=4.111&CodAsignacion="+CodAsignacion
				+"&CodDatosComplementarios="+CodDatosComplementarios+"&Protocolo="+Protocolo
				+"&Tipoespecimen="+Tipoespecimen+"&MedicoTratante="+MedicoTratante
				+"&FechaRecibo="+FechaRecibo+"&FechaEntrega="+FechaEntrega
				+"&DiagClinico="+DiagClinico+
				"&DesMacro="+DesMacro+"&DescMicro1="+DescMicro1+"&DescMicro2="+DescMicro2+"&ObserLleno="+ObserLleno+
				"&RE1="+RE1+"&RE2="+RE2+"&RE3="+RE3+"&RE4="+RE4+"&RE5="+RE5+"&RE6="+RE6+"&RE7="+RE7+"&RE8="+RE8+"&RE9="+RE9+"&RE10="+RE10+
				"&IT1="+IT1+"&IT2="+IT2+"&IT3="+IT3+"&IT4="+IT4+"&IT5="+IT5+"&IT6="+IT6+"&IT7="+IT7+"&IT8="+IT8+"&IT9="+IT9+"&IT10="+IT10+
				"&PTR1="+PTR1+"&PTR2="+PTR2+"&PTR3="+PTR3+"&PTR4="+PTR4+"&PTR5="+PTR5+"&PTR6="+PTR6+"&PTR7="+PTR7+"&PTR8="+PTR8+"&PTR9="+PTR9+"&PTR10="+PTR10+
				"&Diagnostico="+Diagnostico+"&CodBiopsia="+CodBiopsia+"&CodUsu="+CodUsu); //Posting txtname to Servlet    
}



function AprobarResultHistorias0(Resul,CodResul){
	var CodAsignacion=document.getElementById("txtCodAsignacion").value;
	var CodDatosComplementarios=document.getElementById("txtCodDatosComplementarios").value;
	var Protocolo=document.getElementById("txtProtocolo").value;
	var Tipoespecimen=document.getElementById("txtTipoespecimen").value;
	var MedicoTratante=document.getElementById("txtMedicoTratante").value;
	var FechaRecibo=document.getElementById("txtFechaRecibo").value;
	var FechaEntrega=document.getElementById("txtFechaEntrega").value;
	var DiagClinico=document.getElementById("txtDiagClinico").value;
	/*var txtProtocolo=document.getElementById("txtProtocolo").value;
	var txtProtocolo=document.getElementById("txtProtocolo").value;
	var txtProtocolo=document.getElementById("txtProtocolo").value;*/
	var CodUsu=document.getElementById("txtUsuario").value;
	
	var Codi=encodeURIComponent(Resul);
	var Tipoespecimen1=encodeURIComponent(Tipoespecimen);
	var DiagClinico1=encodeURIComponent(DiagClinico);
	
	//alert("Resul "+Codi+" CodResul "+CodResul+" CodAsignacion "+CodAsignacion+" CodDatosComplementarios "+CodDatosComplementarios);
	  ajax=getXMLObject();	
		ajax.open("POST","Pat_ControlListarPacientes",true); //getname will be the servlet name
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				//alert("Aprobacion Exitosa.");
				window.location.reload();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=4.11&Resul="+Codi+"&CodResul="+CodResul+"&CodAsignacion="+CodAsignacion
				+"&CodDatosComplementarios="+CodDatosComplementarios+"&Protocolo="+Protocolo
				+"&Tipoespecimen="+Tipoespecimen1+"&MedicoTratante="+MedicoTratante
				+"&FechaRecibo="+FechaRecibo+"&FechaEntrega="+FechaEntrega
				+"&DiagClinico="+DiagClinico1+"&CodUsu="+CodUsu); //Posting txtname to Servlet    
}

function AprobarPatologia0(){
	//alert();
	//var CodResultado=document.getElementById("txtCodResultado").value;
	//var Respuesta=document.getElementById("txtRespuesta").value;
	var c=document.getElementById("txtTotal").value;
	
	var oscar=c;
	var a;
	if(c!=1){
		for(var i=0; i<=c-1; i++){
			CodResul=document.form1.txtCodResultado[i].value;
			Resul=document.form1.txtRespuesta[i].value;
			//Resul=encodeURIComponent(Resul);
			for(a=0;a<Resul.length;a++){
				//Resul=Resul.replace('Ñ','@');
				//Resul=Resul.replace('ñ','@');				
			}			
			AprobarResultHistorias0(Resul,CodResul);
	      oscar--; 
	     }	
	 }else{	
		 Resul=document.getElementById("txtRespuesta").value;
		 for(e=0;e<Resul.length;e++){
				//Resul=Resul.replace('Ñ','@');
				//Resul=Resul.replace('ñ','@');				
			}
		 CodResul=document.getElementById("txtCodResultado").value;
		 AprobarResultHistorias0(Resul,CodResul);
	 }
}







function ActualizarResultHistorias1(Resul,CodResul){
	var CodAsignacion=document.getElementById("txtCodAsignacion").value;
	var CodDatosComplementarios=document.getElementById("txtCodDatosComplementarios").value;
	var Protocolo=document.getElementById("txtProtocolo").value;
	var Tipoespecimen=document.getElementById("txtTipoespecimen").value;
	var MedicoTratante=document.getElementById("txtMedicoTratante").value;
	var FechaRecibo=document.getElementById("txtFechaRecibo").value;
	var FechaEntrega=document.getElementById("txtFechaEntrega").value;
	var DiagClinico=document.getElementById("txtDiagClinico").value;
	/*var txtProtocolo=document.getElementById("txtProtocolo").value;
	var txtProtocolo=document.getElementById("txtProtocolo").value;
	var txtProtocolo=document.getElementById("txtProtocolo").value;*/
	
	var Codi=encodeURIComponent(Resul)
	
	//alert("Resul "+Codi+" CodResul "+CodResul+" CodAsignacion "+CodAsignacion+" CodDatosComplementarios "+CodDatosComplementarios);
	  ajax=getXMLObject();	
		ajax.open("POST","Pat_ControlListarPacientes",true); //getname will be the servlet name
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=4.1&Resul="+Codi+"&CodResul="+CodResul+"&CodAsignacion="+CodAsignacion
				+"&CodDatosComplementarios="+CodDatosComplementarios+"&Protocolo="+Protocolo
				+"&Tipoespecimen="+Tipoespecimen+"&MedicoTratante="+MedicoTratante
				+"&FechaRecibo="+FechaRecibo+"&FechaEntrega="+FechaEntrega
				+"&DiagClinico="+DiagClinico); //Posting txtname to Servlet    
}

function ActualizarPatologia(){
	//alert();
	//var CodResultado=document.getElementById("txtCodResultado").value;
	//var Respuesta=document.getElementById("txtRespuesta").value;
	var c=document.getElementById("txtTotal").value;
	
	var oscar=c;
	var a;
	if(c!=1){
		for(var i=0; i<=c-1; i++){
			CodResul=document.form1.txtCodResultado[i].value;
			Resul=document.form1.txtRespuesta[i].value;
			//Resul=encodeURIComponent(Resul);
			for(a=0;a<Resul.length;a++){
				//Resul=Resul.replace('Ñ','@');
				//Resul=Resul.replace('ñ','@');				
			}			
			ActualizarResultHistorias1(Resul,CodResul);
	      oscar--; 
	     }	
	 }else{	
		 Resul=document.getElementById("txtRespuesta").value;
		 for(e=0;e<Resul.length;e++){
				//Resul=Resul.replace('Ñ','@');
				//Resul=Resul.replace('ñ','@');				
			}
		 CodResul=document.getElementById("txtCodResultado").value;
		 ActualizarResultHistorias1(Resul,CodResul);
	 }
}
/*function IngresarEstudioSimple(){
	var CodigoEstudio=document.getElementById("cmbEstudios").value;
	var CodPac=document.getElementById("txtCodPac").value;
	var Protocolo=document.getElementById("txtProtocolo").value;
	var TipoEspecimen=document.getElementById("txtTipoEspecimen").value;
	var DiagnosticoClinico=document.getElementById("txtDiagClinico").value;
	var Medico=document.getElementById("txtMedicoTratante").value;
	var FechaRecibo=document.getElementById("txtFechaRecibo").value;
	var FechaEntrega=document.getElementById("txtFechaEntrega").value;
	var DescMacro=document.getElementById("txtDescMacro").value;
	var DescMicro=document.getElementById("txtDescMicro").value;
	var ConcDiagn=document.getElementById("txtConcDiag").value;
	var FechaSistema=document.getElementById("txtfecha").value;
	var HoraSistema=document.getElementById("txthora").value;
	var CodUsuario=document.getElementById("txtCodigoUsuario").value;
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlCrearPaciente",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	ajax.send("valor=5&CodigoEstudio="+CodigoEstudio+"&CodPac="+CodPac+"&Protocolo="+Protocolo+"&TipoEspecimen="+TipoEspecimen+"&DiagnosticoClinico="+DiagnosticoClinico+"&Medico="+Medico+"&FechaRecibo="+FechaRecibo+"&FechaEntrega="+FechaEntrega+"&DescMacro="+DescMacro+"&DescMicro="+DescMicro+"&ConcDiagn="+ConcDiagn+"&FechaSistema="+FechaSistema+"&HoraSistema="+HoraSistema+"&CodUsuario="+CodUsuario);
	
}*/

function buscar(){
	var TipoDocumento=document.getElementById("cbafiliacion").value;
	var Identificacion=document.getElementById("txtnumdoc").value;
	var contenido=document.getElementById("Resultados");
	ajax=getXMLObject();
	ajax.open("POST","Pat_ControlCrearPaciente",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=6.7&TipoDocumento="+TipoDocumento+"&Identificacion="+Identificacion);
	
	
}
/*
function mostarEstudio(CodigoFormato,TipoFormato){
	if(TipoFormato=="1"){
		pagina="pat_ReporteSimple.jsp?CodRepor="+CodigoFormato;			
	   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	   	window.open(pagina,"NUEVA",opciones);
	}
	if(TipoFormato==""){
		
	}
}*/
function mostarReporteEstudio(CodigoAsignacion,Tipo){
	if(Tipo=="0"){
	pagina="pat_ReporteSimple.jsp?CodigoAsignacion="+CodigoAsignacion;		
	}
	if(Tipo=="1"){
		pagina="pat_ReporteBiopsiaRenal.jsp?CodigoAsignacion="+CodigoAsignacion;	
	}
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}
////////////////////////////////////////////////////////////////////////////
var patron = new Array(2,2,4);
var patron2 = new Array(1,3,3,3,3)
function masca(d,sep,pat,nums){
if(d.valant != d.value){
	val = d.value;
	largo = val.length;
	ini=val.substring(0,2);
	if(ini>31){	alert("Dia No Valido.");val=d.value="";d.focus();}
	val = val.split(sep);
	val2 = ''
	for(r=0;r<val.length;r++){val2 += val[r]}
	ini=val2.substring(2,4);
	if(ini>12){alert("Mes No Valido.");val2=d.value="";d.focus();}
	if(nums){for(z=0;z<val2.length;z++){if(isNaN(val2.charAt(z))){letra = new RegExp(val2.charAt(z),"g")
	val2 = val2.replace(letra,"")}}}
	
	val = ''
	val3 = new Array()
	for(s=0; s<pat.length; s++){    
		val3[s] = val2.substring(0,pat[s])
		val2 = val2.substring(pat[s])
	}
	for(q=0;q<val3.length; q++){
		if(q ==0){val = val3[q]	}
		else{if(val3[q] != ""){	val += sep + val3[q]}}
	}
	d.value = val
	d.valant = val
	}
}