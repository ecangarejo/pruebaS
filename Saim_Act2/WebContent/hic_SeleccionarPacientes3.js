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

function fecha(){
	  var time1 = new Date()
	  var anio = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  var dia = time1.getDate()
	  var temp1 = "" + ((anio < 10) ? "0" : "") + anio
	  temp1 += ((mes < 10) ? "-0" : "-") + mes
	  temp1 += ((dia < 10) ? "-0" : "-") + dia
	  document.getElementById('txtFecha').value = temp1
	  id = setTimeout("fecha()",1000)
	  }

function hora(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.getElementById('txtHora').value = temp;
	  id = setTimeout("hora()",1000)
	  }


function cargar(){
	var pacientes=document.getElementById('pacientes').value;
	   ajax=getXMLObject();
	   ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('FormatoMultiple').innerHTML = ajax.responseText;
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&pacientes="+pacientes); //Posting txtname to Servlet
}

function cambio(CodPac){
	var NomDiv=CodPac;	
	var usuario=document.getElementById("txtCodusuario").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {
			document.getElementById("contenido").innerHTML = ajax.responseText;
			document.getElementById("CodPac").value=CodPac
			//document.getElementById("CedPac").value=cedula
		}
	}
		
 	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2&CodPac="+CodPac+"&usuario="+usuario); //Posting txtname to Servlet*/
}
/***********************INICIO DE LA EPICRISIS********************/
/*function MostrarEpicrisis(){
	var DivValor=document.getElementById('HistoriaPaciente');
	var CodAdm=document.getElementById("CodAdm").value;
	var CodPac=document.getElementById("CodPac").value;
	   ajax=getXMLObject();
	   ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					DivValor.innerHTML = ajax.responseText;
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=NOACTIVO&CodAdm="+CodAdm);	
}
*/



/**************************FIN DE LA EPICRISIS********************/
/***********************MostrarInsumosHospitalarios***************/
/*function MostrarInsumosHospitalarios(){
	DivValor=document.getElementById('HistoriaPaciente');
	var usuario=document.getElementById("txtCodusuario").value;
	var CodAdm=document.getElementById("CodAdm").value;
	ajax=getXMLObject();
	    ajax.open("POST","ControlFormatoHic",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		Contenido=ajax.responseText;
				DivValor.innerHTML=Contenido;
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=NOACTIVO&CodAdm="+CodAdm+"&usuario="+usuario); //Posting txtname to Servlet
}
/*******************fin MostrarInsumosHospitalarios***************/

/***********************MEDICAMENTOS******************************/
/*function MostrarMedicamentos(){
	DivValor=document.getElementById('HistoriaPaciente');
	ajax=getXMLObject();
	    ajax.open("POST","ControlFormatoHic",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		Contenido=ajax.responseText;
				DivValor.innerHTML=Contenido;
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=NOACTIVO"); //Posting txtname to Servlet
}*/

function RadioHisMedi(){
	ajax=getXMLObject();
	var CodAdm=document.getElementById("CodAdm").value;
	var contenido=document.getElementById('CambioMedicamentos');
	var radioButtons = document.getElementsByName("rbMedicamentos");
	var CodigoPaciente=document.getElementById("CodPac").value;
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControlFormatoHic",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val+"&CodPac="+CodigoPaciente+"&CodAdm="+CodAdm); //Posting txtname to Servlet
		}	     
	}
}
/*******************FIN MEDICAMENTOS******************************/
/*****************************FORMULACION*************************/
/*function Formulacion(){
	
		
	}*/

function RadioFor() {
	ajax=getXMLObject();
	var CodAdm=document.getElementById("CodAdm").value;
	var usuario=document.getElementById("txtCodusuario").value;
	//var contenido=document.getElementById('ContenidoFormulacion');
	var contenido=document.getElementById('opciones');
	var radioButtons = document.getElementsByName("radiobutton");
	var CodigoPaciente=document.getElementById("CodPac").value;
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val+"&CodPac="+CodigoPaciente+"&codAdm="+CodAdm+"&usuario="+usuario); //Posting txtname to Servlet
		}	     
	}
}

function CalcularCantidad(){
	var CantidadDosis=document.getElementById("cmbCantidad").value;
	var Lapso=document.getElementById("cmbLapso").value;
	var Administracion=document.getElementById("cmbViaAdmi").value;
	if(CantidadDosis=="Seleccione"){
		document.getElementById("txtCantidad").value="";
		//document.getElementById("txtObservacion").value="";
	}
	else{
		if(Lapso=="Seleccione"){
			document.getElementById("txtCantidad").value="";
			//document.getElementById("txtObservacion").value="";
		}
		else{
			if(Administracion=="Seleccione"){
				//document.getElementById("txtObservacion").value="";
			}
		}
	}
	if((CantidadDosis!="Seleccione")&&(Lapso!="Seleccione")){
		var Tiempo= 24 / Lapso;
		var CantidadTotal=CantidadDosis * Tiempo;
		document.getElementById("txtCantidad").value=CantidadTotal;
	}
	
	if((CantidadDosis!="Seleccione")&&(Lapso!="Seleccione")&&(Administracion!="Seleccione")){
		var Dosificacion="Dar "+CantidadDosis+" Cada "+Lapso+" Horas. "+Administracion;
		//document.getElementById("txtObservacion").value=Dosificacion;
	}
}
function ActualizaDetalleOrden(){
	var CodFormulacion_fk=document.getElementById("txtCodFormulacion").value;
	var DetOrden=document.getElementById("txtDetOrden").value;	
	if(CodFormulacion_fk!=""){
		ajax=getXMLObject();
		var DetOrden1=encodeURIComponent(DetOrden);
		ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
		ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		ajax.send("valor=13&DetOrden="+DetOrden1+"&codFormulacion_fk="+CodFormulacion_fk); //Posting txtname to Servlet
	}
}
function AsignarMediControl(){
	/************FORMULACION*******************/
	var CodPac=document.getElementById("CodPac").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var CodCama=document.getElementById("txtCodCama").value;
	var CodArea=document.getElementById("txtCodArea").value;
	var CodSubarea=document.getElementById("txtCodSubarea").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	/////////////////////////////////////////
	/*******DETALLE FORMULACION****************/
	var CodFormulacion_fk=document.getElementById("txtCodFormulacion").value;
	var CodigoMed=document.getElementById("txtCodigoMed").value;
	var Cantidad=document.getElementById("txtCantidad").value;
	///////////////////////////////////////
	var CantidadDosis=document.getElementById("cmbCantidad").value;
	var Lapso=document.getElementById("cmbLapso").value;
	var Administracion=document.getElementById("cmbViaAdmi").value;
	///////////////////////////////////////
	var ConteForm=document.getElementById("ConteForm");
	var DetOrden=encodeURIComponent(document.getElementById("txtDetOrden").value);
	var Dosificacion="";
	///////////////////////////////////////
	if(CantidadDosis=="Seleccione"){
		alert("Seleccione Cantidad De La Dosis.");
	}else{
		if(Lapso=="Seleccione"){
			alert("Seleccione Lapso De Tiempo.");
		}else{
			if(Administracion=="Seleccione"){
				alert("Seleccione Tipo De Administracion.");
			}
		}
	}
	///////////////////////////////////////
	var Observacion=document.getElementById("txtObservacion").value;
	if((CantidadDosis!="Seleccione")&&(Lapso!="Seleccione")&&(Administracion!="Seleccione")){
		Dosificacion="Dar "+CantidadDosis+" Cada "+Lapso+" Horas. "+Administracion;
		//document.getElementById("txtObservacion").value=Dosificacion;
	}
	var opcion=confirm("Desea Formular Este Medicamento De Control?");
		
	if(opcion){
		ajax=getXMLObject();
		ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
		if((Cantidad!="")&&(Administracion!="Seleccione")){
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					var codFormulacion_fk=ajax.responseText;
					ReporteFormulacion(codFormulacion_fk);
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=12&codPac="+CodPac+"&codAdm="+CodAdm+"&usuario="+usuario+"&codCama="+CodCama+"&codArea="+CodArea+"&codSubarea="+CodSubarea+"&hora="+hora+"&fecha="+fecha+"&codFormulacion_fk="+CodFormulacion_fk+"&codigoMed="+CodigoMed+"&cantidad="+Cantidad+"&observacion="+Observacion+"&dosificacion="+Dosificacion+"&DetOrden="+DetOrden); //Posting txtname to Servlet
		}
	}else{}
}
function ReporteFormulacion(CodFormulacion){
				
    pagina="hic_Formulacion.jsp?CodFormulacion="+CodFormulacion;			
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
    OrdenServicio();
	
}
function AsignarMedi(){
	/************FORMULACION*******************/
	var CodPac=document.getElementById("CodPac").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var CodCama=document.getElementById("txtCodCama").value;
	var CodArea=document.getElementById("txtCodArea").value;
	var CodSubarea=document.getElementById("txtCodSubarea").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	//falta una observacion de formulacion.
	/*******DETALLE FORMULACION****************/
	var CodFormulacion_fk=document.getElementById("txtCodFormulacion").value;
	var CodigoMed=document.getElementById("txtCodigoMed").value;
	var Cantidad=document.getElementById("txtCantidad").value;
	///////////////////////////////////////
	var CantidadDosis=document.getElementById("cmbCantidad").value;
	var Lapso=document.getElementById("cmbLapso").value;
	var Administracion=document.getElementById("cmbViaAdmi").value;
	///////////////////////////////////////
	var ConteForm=document.getElementById("ConteForm");
	var DetOrden=encodeURIComponent(document.getElementById("txtDetOrden").value);
	var Dosificacion="";
	///////////////////////////////////////
	if(CantidadDosis=="Seleccione"){
		alert("Seleccione Cantidad De La Dosis.");
	}else{
		if(Lapso=="Seleccione"){
			alert("Seleccione Lapso De Tiempo.");
		}else{
			if(Administracion=="Seleccione"){
				alert("Seleccione Tipo De Administracion.");
			}
		}
	}
	///////////////////////////////////////
	var Observacion=document.getElementById("txtObservacion").value;
	if((CantidadDosis!="Seleccione")&&(Lapso!="Seleccione")&&(Administracion!="Seleccione")){
		Dosificacion="Dar "+CantidadDosis+" Cada "+Lapso+" Horas. "+Administracion;
		//document.getElementById("txtObservacion").value=Dosificacion;
	}
	if((CantidadDosis=="")&&(Lapso=="")&&(Administracion=="")){
		Dosificacion="";
	}
	//////////////////////////////////////////////////////////////
	ajax=getXMLObject();
	ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	/////////////////////////////////////////////////////////////
	if(CodFormulacion_fk==""){
		// si es primer medicamento.
		if((Cantidad!="")&&(Administracion!="Seleccione")){
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				document.getElementById("txtCodigoMed").value="";
				document.getElementById("txtMedicamento").value="";
				document.getElementById("txtMedicamento").focus();
				ConteForm.innerHTML=ajax.responseText;
			}
		}
		//alert("CodFormulacion= vacio "+"valor=9&codPac="+CodPac+"&codAdm="+CodAdm+"&usuario="+usuario+"&codCama="+CodCama+"&codArea="+CodArea+"&codSubarea="+CodSubarea+"&hora="+hora+"&fecha="+fecha+"&codFormulacion_fk="+CodFormulacion_fk+"&codigoMed="+CodigoMed+"&cantidad="+Cantidad+"&observacion="+Observacion+"&dosificacion="+Dosificacion+"&DetOrden="+DetOrden);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=9&codPac="+CodPac+"&codAdm="+CodAdm+"&usuario="+usuario+"&codCama="+CodCama+"&codArea="+CodArea+"&codSubarea="+CodSubarea+"&hora="+hora+"&fecha="+fecha+"&codFormulacion_fk="+CodFormulacion_fk+"&codigoMed="+CodigoMed+"&cantidad="+Cantidad+"&observacion="+Observacion+"&dosificacion="+Dosificacion+"&DetOrden="+DetOrden); //Posting txtname to Servlet
		}
	}
	
	if(CodFormulacion_fk!=""){
		// de segundo medicamento en adelante.
		if((Cantidad!="")&&(Administracion!="Seleccione")){
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				document.getElementById("txtCodigoMed").value="";
				document.getElementById("txtMedicamento").value="";
				document.getElementById("txtMedicamento").focus();
				ConteForm.innerHTML=ajax.responseText;
			}
		}
		//alert("CodFormulacion= lleno "+"valor=9.1&codFormulacion_fk="+CodFormulacion_fk+"&codigoMed="+CodigoMed+"&cantidad="+Cantidad+"&observacion="+Observacion+"&dosificacion="+Dosificacion+"&DetOrden="+DetOrden);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=9.1&codFormulacion_fk="+CodFormulacion_fk+"&codigoMed="+CodigoMed+"&cantidad="+Cantidad+"&observacion="+Observacion+"&dosificacion="+Dosificacion+"&DetOrden="+DetOrden+"&CodAdm="+CodAdm); //Posting txtname to Servlet
		}
	}
	
}
function BuscarTipoMedi(){
	
	var Tipo=document.getElementById("txtTipo").value;
	ajax=getXMLObject();
	Contenido=document.getElementById('DetAdministra');
	var CodMedicamento=document.getElementById("txtCodigoMed").value;
	var CodFormulacion_fk=document.getElementById("txtCodFormulacion").value;
	if(Tipo=="0"){		
		ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				//alert(ajax.responseText);
				Contenido.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=8.1&CodMedicamento="+CodMedicamento+"&codFormulacion_fk="+CodFormulacion_fk); //Posting txtname to Servlet
	}
	
	if(Tipo=="1"){
		ajax.open("POST","ControlFormatoHic",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				//alert(ajax.responseText);
				Contenido.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=CamInsu"); //Posting txtname to Servlet
	
	}
}

function OmitirDetalleFormulacion(codDetFormulacion){
	var CodFormulacion_fk=document.getElementById("txtCodFormulacion").value;
	var ConteForm=document.getElementById("ConteForm");	
	ajax=getXMLObject();	
	ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			document.getElementById("txtCodigoMed").value="";
			document.getElementById("txtMedicamento").value="";
			document.getElementById("txtMedicamento").focus();
			ConteForm.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=11&codDetFormulacion_fk="+codDetFormulacion+"&codFormulacion_fk="+CodFormulacion_fk); //Posting txtname to Servlet
}

function FinalFormulacion(){
	var opcion=confirm("Desea Finalizar La Formulacion?");	
	if(opcion){
		var codFormulacion_fk=document.getElementById("txtCodFormulacion").value; 
		ajax=getXMLObject();
		ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				var validar=ajax.responseText;
				if(validar=="1"){
					ReporteFormulacion(codFormulacion_fk);
					OrdenServicio();
				}else{
					alert("Error En Actualizacion de Datos.")
				}
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=10&codFormulacion_fk="+codFormulacion_fk); //Posting txtname to Servlet
	}else{
		
	}
}
/*****************************FIN FORMULACION*************************/
/*****************************ATENCIONES ANTERIORES*************************/
function MostrarAtenciones(){
	var CodPac=document.getElementById("CodPac").value;
	DivValor=document.getElementById('HistoriaPaciente');
	ajax=getXMLObject();
	    ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		Contenido=ajax.responseText;
				DivValor.innerHTML=Contenido;
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=NOACTIVO&CodPac="+CodPac); //Posting txtname to Servlet
}


/*************************FIN ATENCIONES ANTERIORES*************************/
/*****************************DIAGNOSTICO FINAL*****************************/
function ClasiDiagnostico(){
	var CodAdmision=document.getElementById("CodAdm").value;
	DivValor=document.getElementById('HistoriaPaciente');
	ajax=getXMLObject();
	    ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		Contenido=ajax.responseText;
				DivValor.innerHTML=Contenido;
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=NOACTIVO&CodAdmision="+CodAdmision); //Posting txtname to Servlet		
	}

function AsignarDiagRel1(){
	ajax=getXMLObject();
	var CodDiagnosticoRela1=document.getElementById("txtCodDiagnosticoRela1").value;
	var TipoDiagRel1=document.getElementById("txtTipoDiagRel1").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var CodPac=document.getElementById("CodPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var usuario=document.getElementById("txtCodusuario").value;
	if(CodDiagnosticoRela1==""){
		alert("Seleccione El Diagnostico Relacionado 1.");
	}
	if(CodDiagnosticoRela1!=""){
	ajax.open("POST","ControlIngresarResultados",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		var val="";
		val=ajax.responseText;	
		if(val==""){
			
		}
		if(val!=""){
			alert(val);
		}
		ClasiDiagnostico();
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3&CodDiagnosticoRela1="+CodDiagnosticoRela1+"&TipoDiagRel1="+TipoDiagRel1+"&CodAdm="+CodAdm+"&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario); //Posting txtname to Servlet
	}
}

function AsignarDiagRel2 (){
	ajax=getXMLObject();
	var CodDiagnosticoRela2=document.getElementById("txtCodDiagnosticoRela2").value;
	var TipoDiagRel2=document.getElementById("txtTipoDiagRel2").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var CodPac=document.getElementById("CodPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var usuario=document.getElementById("txtCodusuario").value;
	if(CodDiagnosticoRela2==""){
		alert("Seleccione El Diagnostico Relacionado 2.");
	}
	if(CodDiagnosticoRela2!=""){
	ajax.open("POST","ControlIngresarResultados",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		var val="";
		val=ajax.responseText;	
		if(val==""){
			
		}
		if(val!=""){
			alert(val);
		}
		ClasiDiagnostico();
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4&CodDiagnosticoRela2="+CodDiagnosticoRela2+"&TipoDiagRel2="+TipoDiagRel2+"&CodAdm="+CodAdm+"&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario); //Posting txtname to Servlet
	}
}

function AsignarDiagEg (){
	ajax=getXMLObject();
	var CodDiagnosticoEgreso=document.getElementById("txtCodDiagnosticoEgreso").value;
	var TipoDiagEG=document.getElementById("txtTipoDiagEG").value;	
	var CodAdm=document.getElementById("CodAdm").value;
	var CodPac=document.getElementById("CodPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var usuario=document.getElementById("txtCodusuario").value;
	if(CodDiagnosticoEgreso==""){
		alert("Seleccione El Diagnostico de Egreso.");
	}
	if(CodDiagnosticoEgreso!=""){
	ajax.open("POST","ControlIngresarResultados",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		var val="";
		val=ajax.responseText;	
		if(val==""){
			
		}
		if(val!=""){
			alert(val);
		}
		ClasiDiagnostico();
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2&CodDiagnosticoEgreso="+CodDiagnosticoEgreso+"&TipoDiagEG="+TipoDiagEG+"&CodAdm="+CodAdm+"&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario); //Posting txtname to Servlet
	}
}


function IngresarDiagnosticoInicial(){
	
	
	ajax=getXMLObject();	
	var CodResul=document.getElementById("txtCodResultado").value;
	var Resul=document.getElementById("txtNomDiagnos").value;
	var CodPac=document.getElementById("CodPac").value;	
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var CodDiagnostico=document.getElementById("txtCodDiagnostico").value;
	var Resul1=document.getElementById("txtResult1").value; 
	
		Resul=Resul1+"-->"+Resul;
		//alert("Resul "+Resul+" Resul1 "+Resul1);
	if(CodDiagnostico==""){alert("Este Codigo no Existe En El CIE10.");}
	if(CodDiagnostico!=""){
		ajax.open("POST","ControlIngresarResultados",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			//alert(ajax.responseText);
			document.getElementById("DiagnosticoIniciales").innerHTML=ajax.responseText;
			Resul1=document.getElementById("txtResult1").value=Resul+"-"+CodDiagnostico;
			document.getElementById("txtCodDiagnostico").value="";
			document.getElementById("txtNomDiagnos").value="";
			
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&Resul="+Resul+"&CodResul="+CodResul+"&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&CodAdm="+CodAdm+"&CodDiagnostico="+CodDiagnostico); //Posting txtname to Servlet
	}
}

function IngresarDiagnosticoInicialH(){
	ajax=getXMLObject();	
	//var CodResul=document.getElementById("txtCodResultado").value;
	var Resul=document.getElementById("txtNomDiagnos").value;
	var CodPac=document.getElementById("CodPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var CodDiagnostico=document.getElementById("txtCodDiagnostico").value;
	var Resul1=document.getElementById("txtResult1").value; 
	
		Resul=Resul1+"-->"+Resul;
		//alert("Resul "+Resul+" Resul1 "+Resul1);
	if(CodDiagnostico==""){alert("Este Codigo no Existe En El CIE10.");}
	if(CodDiagnostico!=""){
		ajax.open("POST","ControlIngresarResultados",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			//alert(ajax.responseText);
		//	document.getElementById("DI").innerHTML=ajax.responseText;
			/*Resul1=document.getElementById("txtResult1").value=Resul+"-"+CodDiagnostico;
			document.getElementById("txtCodDiagnostico").value="";
			document.getElementById("txtNomDiagnos").value="";*/
			ClasiDiagnostico();
			
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&Resul="+Resul+"&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&CodAdm="+CodAdm+"&CodDiagnostico="+CodDiagnostico); //Posting txtname to Servlet
	}
}

function IngresarDiagnosticoSolo(){

	ajax=getXMLObject();	
	var CodResul=document.getElementById("txtCodResultado5").value;
	var Resul=document.getElementById("txtNomDiagnos").value;
	var CodPac=document.getElementById("CodPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var CodDiagnostico=document.getElementById("txtCodDiagnostico").value;
	var Resul1=document.getElementById("txtResult1").value; 
	
		Resul=Resul1+"-->"+Resul;
		//alert("Resul "+Resul+" Resul1 "+Resul1);
	if(CodDiagnostico==""){alert("Este Codigo no Existe En El CIE10.");}
	if(CodDiagnostico!=""){
		ajax.open("POST","ControlIngresarResultados",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			//alert(ajax.responseText);
			document.getElementById("DiagnosticoIniciales").innerHTML=ajax.responseText;
			Resul1=document.getElementById("txtResult1").value=Resul+"-"+CodDiagnostico;
			document.getElementById("txtCodDiagnostico").value="";
			document.getElementById("txtNomDiagnos").value="";
			
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1.1&Resul="+Resul+"&CodResul="+CodResul+"&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&CodAdm="+CodAdm+"&CodDiagnostico="+CodDiagnostico); //Posting txtname to Servlet
	}
}
function IngresarDestino(){
	ajax=getXMLObject();
	var valorSalida="";
	var valorDestino="";
	var rbDestino = document.getElementsByName("rbDestino");
	for (var x = 0; x < rbDestino.length; x ++) {
		if (rbDestino[x].checked) {
			valorDestino=rbDestino[x].id;
		}
	}
	var rbSalida = document.getElementsByName("rbSalida");
	for (var y = 0; y < rbSalida.length; y ++) {
		if (rbSalida[y].checked) {
			valorSalida=rbSalida[y].id;
		}
	}
	var CodPac=document.getElementById("CodPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var CodAdm=document.getElementById("CodAdm").value;
	
	var fin_cons=document.getElementById("cmbfinalidadcons").value;
	var cau_ext=document.getElementById("cmbcausaexterna").value;
	var tip_diag=document.getElementById("cmbtipdiag").value;
	 
	//alert("fin_cons="+fin_cons+" cau_ext="+cau_ext+" tip_diag="+tip_diag);
	
	if(valorDestino==""){alert("Seleccione El Destino Del Paciente.")
		}else{
			if(valorSalida==""){alert("Seleccione El Estado de Salida Del Paciente.")
				}
		}
		 
	 if(CodPac==""){alert("Falta El Codigo Del Paciente.");}
	 if(hora==""){alert("Falta La Hora.");}
	 if(fecha==""){alert("Falta La Fecha.");}
	 if(usuario==""){alert("Falta El Usuario.");}
	 if(CodAdm==""){alert("Falta La Admision");}
	 
	 if((valorDestino!="")&&(valorSalida!="")&&(CodPac!="")&&(hora!="")&&(fecha!="")&&(usuario!="")&&(CodAdm!="")){
		 ajax.open("POST","ControlIngresarResultados",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				var val="";
				val=ajax.responseText;
				if(val!=""){
					alert(val);
				}else{
					alert("Alta Existosa.");
					window.location.href="hic_BuscarPacientes.jsp";
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=5&tip_diag="+tip_diag+"&cau_ext="+cau_ext+"&fin_cons="+fin_cons+"&DestinoPaciente="+valorDestino+"&EstadoSalida="+valorSalida+"&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&CodAdm="+CodAdm); //Posting txtname to Servlet
			
			
	 }
}
/************************FIN DIAGNOSTICO FINAL******************************/
/***********************************LABORATORIO ***************************/
function MenuLaboratorio(){
	DivValor=document.getElementById('HistoriaPaciente');
	ajax=getXMLObject();
	    ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		Contenido=ajax.responseText;
				DivValor.innerHTML=Contenido;
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=NOACTIVO"); //Posting txtname to Servlet
		
	}
function Radio() {
	ajax=getXMLObject();
	var contenido=document.getElementById('ContenidoLaboratorio');
	var radioButtons = document.getElementsByName("radiobutton");
	var CodigoPaciente=document.getElementById("CodPac").value;
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val+"&CodPac="+CodigoPaciente); //Posting txtname to Servlet
		}	     
	}
}
function historial(CodExamen,CodPac){
	ajax=getXMLObject();
	var DivValor=document.getElementById("HistorialLaboratorios");
	 ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		Contenido=ajax.responseText;
				DivValor.innerHTML=Contenido;
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    ajax.send("valor=3&CodPac="+CodPac+"&CodExamen="+CodExamen); //Posting txtname to Servlet
}
function mostrarHistorial(){
    var c=document.getElementById('txtContador').value;
    var CodPac=document.getElementById("CodPac").value;
	var oscar=c;
	if(c!=1){
		for(var i=0; i<=c-1; i++){	
			if (document.form1.chkExamenes[i].checked) {
				CodExamen=document.form1.chkExamenes[i].value;       	 
				historial(CodExamen,CodPac);
			}     	  
			oscar--; 
		}	
	} 
	else{
		if(document.form1.chkExamenes.checked){
			CodExamen=document.form1.chkExamenes.value;
			historial(CodExamen,CodPac);
		}
	}
}
/*************************** FIN DE LABORATORIO***************************/
 /***************************IMAGENOLOGIA***************************/
 function MostrarImagenologia(){
		DivValor=document.getElementById('HistoriaPaciente');
		CodigoPaciente=document.getElementById("CodPac").value;
		ajax=getXMLObject();
		    ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
		    ajax.onreadystatechange  = function(){
		    	if (ajax.readyState == 4) {				
		    		Contenido=ajax.responseText;
					DivValor.innerHTML=Contenido;
		  	   	}
			  }
		  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=NOACTIVO&CodPac="+CodigoPaciente); //Posting txtname to Servlet
		}
 
 /***************************FIN DE IMAGENOLOGIA***************************/
/***************************ORDEN DE SERVICIO***************************/
function OrdenServicio(){
	DivValor=document.getElementById('HistoriaPaciente');
	var CodAdm=document.getElementById("CodAdm").value;
	ajax=getXMLObject();
	    ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		Contenido=ajax.responseText;
	    		//alert(Contenido)
				DivValor.innerHTML=Contenido;
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=5&CodAdmision="+CodAdm); //Posting txtname to Servlet
	//alert("Orden de Servicio");
	}

/********************************REPORTES DE CONSULTAS********************************/
function ReportLabora(CodOrdenLab){	
	pagina="hic_OrdenLaboratorio.jsp?CodOrdenLab="+CodOrdenLab;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);   	
}

function ReportImage(CodOrden){
		
	pagina="hic_OrdenImagenologia.jsp?CodOrden="+CodOrden;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);   	
}


function ReportMedica(CodFormulacion){
	
    pagina="hic_Formulacion.jsp?CodFormulacion="+CodFormulacion;			
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
	
}
/*************************************************************************************/

/*function Radio2() {	
	ajax=getXMLObject();
	var contenido=document.getElementById('opciones');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
	         ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
	         ajax.onreadystatechange  = function(){
	        	 if (ajax.readyState == 4) {
	        		 contenido.innerHTML = ajax.responseText	
	        	 }
	         };
	         ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	         ajax.send("valor="+val); //Posting txtname to Servlet
		  }	     
    }
}*/

/*function EnviarLaboratorios(CodEstudio,TipoEstu,contador){
	//alert(CodEstudio)
	//ajax=getXMLObject();
	var xmlhttp = new getXMLObject();
	var xmlhttp4 = new getXMLObject();
	var xmlhttp5 = new getXMLObject();
	//ajax1=getXMLObject();
	var CodigoPac=document.getElementById("CodPac").value;
	var HoraPeticion=document.getElementById("txtHora").value;
	var FechaPeticion=document.getElementById("txtFecha").value;
	var usuario=document.getElementById("txtCodusuario").value; 
	var CodAdm=document.getElementById("CodAdm").value;
	var CodOrdenLab=document.getElementById("txtCodOrdenLab").value;
	var ajax= new getXMLObject();	
	ajax.open("POST","ControlFormatosPestanas",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){	
				var xmlhttp1 = new getXMLObject();
				var xmlhttp2 = new getXMLObject();
				var xmlhttp3 = new getXMLObject();
				var CodOrden=ajax.responseText;
				document.getElementById("txtCodOrdenLab").value=CodOrden;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		ajax.send("valor=9&CodEstudio="+CodEstudio+"&TipoEstu="+TipoEstu+"&CodigoPac="+CodigoPac+"&HoraPeticion="+HoraPeticion+"&FechaPeticion="+FechaPeticion+"&usuario="+usuario+"&CodAdmision="+CodAdm+"&TipoPyP=Urgencia&CodOrdenLab="+CodOrdenLab+"&contador="+contador);
}*/

function OrdenMedicaGeneral(){
	var DetOrden=encodeURIComponent(document.getElementById("txtDetOrden").value);
	var CodigoPac=document.getElementById("CodPac").value;
	var HoraFormato=document.getElementById("txtHora").value;
	var FechaFormato=document.getElementById("txtFecha").value;
	var usuario=document.getElementById("txtCodusuario").value; 
	var CodAdm=document.getElementById("CodAdm").value;
	var ajax= new getXMLObject();	
	ajax.open("POST","ControlVerFormatos",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){	
				var valor=ajax.responseText;
				ReporteOrdenGeneral(valor);
				//OrdenServicio();
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=71&DetOrden="+DetOrden+"&codPac="+CodigoPac+"&HoraFormato="+HoraFormato+"&FechaFormato="+FechaFormato+"&usuario="+usuario+"&codAdm="+CodAdm);
}

function ReporteOrdenGeneral(CodOrden){

	pagina="hic_OrdenGeneral.jsp?CodOrden="+CodOrden;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
   	
   	OrdenServicio();
}

function OmitirDetOrdenLab(CodDetOrdLab){
	var CodOrdenLab=document.getElementById("txtCodOrdenLab").value;
	var ajax= new getXMLObject();	
	ajax.open("POST","ControlFormatosPestanas",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){	
				var valor=ajax.responseText;
				if(valor==""){
					CodOrdenLab="";
					VerExamenes();	
				}
				if(valor!=""){
					CodOrdenLab=valor;
					VerExamenes();	
				}							
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=98&CodDetOrdLab="+CodDetOrdLab+"&CodOrdenLab="+CodOrdenLab);

}



function ActuDetOrdenLab(){
	var CodOrdenLab=document.getElementById("txtCodOrdenLab").value;
	var DetOrdenLab=document.getElementById("txtDetOrdenLab").value;
	if(CodOrdenLab==""){}
	var ajax= new getXMLObject();	
	ajax.open("POST","ControlFormatosPestanas",true);
	if(CodOrdenLab!=""){
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=89&CodOrdenLab="+CodOrdenLab+"&DetOrdenLab="+DetOrdenLab);
	}
}
function FinalizarLaboratorios(){
	var CodOrden=document.getElementById("txtCodOrdenLab").value;
	var ajax= new getXMLObject();	
	ajax.open("POST","ControlFormatosPestanas",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){			
				ReportesOrdenLaboratorios(CodOrden);
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=FinOrden&CodOrden="+CodOrden);
	
}
function ReportesOrdenLaboratorios(CodOrdenLab){
	//var CodOrdenLab=document.getElementById("txtCodOrdenLab").value;
	
	pagina="hic_OrdenLaboratorio.jsp?CodOrdenLab="+CodOrdenLab;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
   	
   	OrdenServicio();
}

function AsignarOrdenLab(){
	var cmbExamenes=document.getElementById("cmbExamenes").value;
	var DetOrdenLab=encodeURIComponent(document.getElementById("txtDetOrdenLab").value);
	var CodigoPac=document.getElementById("CodPac").value;
	var HoraPeticion=document.getElementById("txtHora").value;
	var FechaPeticion=document.getElementById("txtFecha").value;
	var usuario=document.getElementById("txtCodusuario").value; 
	var CodAdm=document.getElementById("CodAdm").value;
	var CodOrdenLab=document.getElementById("txtCodOrdenLab").value;
	var DivValor=document.getElementById('DetOrdLab');
	var ObservacionExamen=document.getElementById("txtObservacionExamen").value;
	var CodEstudio=null;
	var TipoEstu=null;
	if(cmbExamenes=="Seleccione"){
		alert("Seleccione Examen A Asignar.");
	}
	if(cmbExamenes!="Seleccione"){
		var y=cmbExamenes.split("|").length;
		var z=cmbExamenes.split("|");		     	
		for(x=0; x<y-1; x=x+1)
		{
			CodEstudio=z[0];
			TipoEstu=z[1];
  	  }
		var ajax= new getXMLObject();	
		ajax.open("POST","ControlFormatosPestanas",true);
		if(CodOrdenLab==""){
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){			
					var valor=ajax.responseText;
					DivValor.innerHTML=valor;
					document.getElementById("txtObservacionExamen").value="";
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=99&CodEstudio="+CodEstudio+"&TipoEstu="+TipoEstu+"&CodigoPac="+CodigoPac+"&HoraPeticion="+HoraPeticion+"&FechaPeticion="+FechaPeticion+"&usuario="+usuario+"&CodAdmision="+CodAdm+"&TipoPyP=Urgencia&ObservacionExamen="+ObservacionExamen+"&DetOrdenLab="+DetOrdenLab);
		}
		
		if(CodOrdenLab!=""){
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){			
					var valor=ajax.responseText;
					DivValor.innerHTML=valor;
					document.getElementById("txtObservacionExamen").value="";
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=999&CodEstudio="+CodEstudio+"&TipoEstu="+TipoEstu+"&CodigoPac="+CodigoPac+"&HoraPeticion="+HoraPeticion+"&FechaPeticion="+FechaPeticion+"&usuario="+usuario+"&CodAdmision="+CodAdm+"&TipoPyP=Urgencia&CodOrdenLab="+CodOrdenLab+"&ObservacionExamen="+ObservacionExamen);
		}
	}
	
	
}

/*function GuardarLaboratorios(){	
	var total=document.getElementById('yo').value;
	var contador=0;
	var cont=total;	
	if(total!=1){
		for(var h=0; h<=total-1; h++){
			contador=contador+1;
			if(document.form1.ca[h].checked){
				CodEstudio=document.form1.ca[h].value;
				TipoEstu=document.form1.txtTipo[h].value;
				EnviarLaboratorios(CodEstudio,TipoEstu,contador);
			}			
			cont--;
		}
	}
	else{
		if(document.form1.ca.checked){
			contador=contador+1;
			CodEstudio=document.form1.ca.value;
			TipoEstu=document.form1.txtTipo.value;
			EnviarLaboratorios(CodEstudio,TipoEstu,contador);
		}
	}
}*/

function checkAll() {
	 var i;
 var nodoCheck = document.getElementsByTagName("input");
 var varCheck = document.getElementById("all").checked;
 for (i=0; i<nodoCheck.length; i++){
     if (nodoCheck[i].type == "checkbox" && nodoCheck[i].name != "all" && nodoCheck[i].disabled == false) {
         nodoCheck[i].checked = varCheck;
     }
 } 
}
function VerExamenes(){
	DivValor=document.getElementById('examenes');
	var CodArea=document.getElementById("cmbgrupos").value;
	var CodOrdenLab=document.getElementById("txtCodOrdenLab").value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
	
	if(CodOrdenLab==""){
		//alert("CodOrdenLab Vacio "+CodOrdenLab);
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {				
				Contenido=ajax.responseText;
				DivValor.innerHTML=Contenido;

			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=8&CodArea="+CodArea); //Posting txtname to Servle	*/
	}
	
	if(CodOrdenLab!=""){
		//alert("CodOrdenLab Lleno "+CodOrdenLab);
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {				
				Contenido=ajax.responseText;
				DivValor.innerHTML=Contenido;

			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=81&CodArea="+CodArea+"&CodOrdenLab="+CodOrdenLab); //Posting txtname to Servle	*/
	}
	
}

function enviar(){
	
	var usuario=document.getElementById("txtCodusuario").value;  
	var codPaciente=document.getElementById("CodPac").value;	
	var codExamen=document.getElementById("txtcodexamen").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;	
	var datosClinico=encodeURIComponent(document.getElementById('txtdatosClinicos').value);
	var CedPac=document.getElementById("CedPac").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var CodOrdenImg=document.getElementById("txtCodOrdenImg").value;
	var DetOrden=encodeURIComponent(document.getElementById("txtDetOrden").value);
	var Div=document.getElementById("DetOrdImg");
	var portatil="";
	if(document.form1.chkportatil.checked){
		 portatil=document.form1.chkportatil.value;
		}
	
	if(codPaciente==""){
		alert("No se Ha Seleccionado Ningun Paciente");
	}else{
		if(codExamen==""){
			alert("No se Ha Seleccionado Ningun Examen");
		}else{
			if(hora==""){
				alert("No hay Hora Seleccionado");
			}else{
				if(fecha==""){
					alert("No hay Fecha Seleccionado");
				}else{
					if(datosClinico==""){
						alert("Los Datos Clinicos No Pueden Ir Vacios");
					}
				}
			}
		}
	}
	
	var e;
    ajax=getXMLObject();
	ajax.open("POST","ControlCitasExamen",true); //getname will be the servlet name	
    if(CodOrdenImg==""){
    	if((codPaciente!="")&&(codExamen!="")&&(hora!="")&&(fecha!="")&&(datosClinico!="")){
    		ajax.onreadystatechange  = function(){
    			if (ajax.readyState == 4) {
    				Contenido=ajax.responseText;
    				Div.innerHTML=Contenido;
    				document.getElementById("txtcodexamen").value="";
    				var NomExamen=document.getElementById("txtnomexam").value="";
    				datosClinico=document.getElementById('txtdatosClinicos').value="";
    				document.form1.chkportatil.checked=false;
    				document.getElementById("txtnomexam").focus();
    				document.getElementById("txtnomexam").focus();
    			}
    		}
    		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
    		ajax.send("valor=3&codigoExa_fk="+codExamen+"&codigoPac_fk="+codPaciente+"&estado=-1&usuario="+usuario+"&hora="+hora+"&fecha="+fecha+"&datosClinico="+datosClinico+"&portatil="+portatil+"&cedula="+CedPac+"&CodAdm="+CodAdm+"&observacion="+DetOrden); //Posting txtname to Servlet		
    	}
    }
	
	
	 if(CodOrdenImg!=""){

			if((codPaciente!="")&&(codExamen!="")&&(hora!="")&&(fecha!="")&&(datosClinico!="")){
	    		ajax.onreadystatechange  = function(){
	    			if (ajax.readyState == 4) {
	    				Contenido=ajax.responseText;
	    				Div.innerHTML=Contenido;
	    				document.getElementById("txtcodexamen").value="";
	    				var NomExamen=document.getElementById("txtnomexam").value="";
	    				datosClinico=document.getElementById('txtdatosClinicos').value="";
	    				document.form1.chkportatil.checked=false;
	    				document.getElementById("txtnomexam").focus();
	    				document.getElementById("txtnomexam").focus();
	 	   			}
	    		}
	 	   		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	 	   		ajax.send("valor=33&codigoExa_fk="+codExamen+"&codigoPac_fk="+codPaciente+"&estado=-1&usuario="+usuario+"&hora="+hora+"&fecha="+fecha+"&datosClinico="+datosClinico+"&portatil="+portatil+"&cedula="+CedPac+"&CodAdm="+CodAdm+"&CodOrdenImg="+CodOrdenImg+"&observacion="+DetOrden); //Posting txtname to Servlet		

			}
	 }
		
}

function ReportesOrdenImagenologia(CodOrden){
	
	
	pagina="hic_OrdenImagenologia.jsp?CodOrden="+CodOrden;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
   	
   	OrdenServicio();
}

function FinalizarOrdenImg(){
	var CodOrden=document.getElementById("txtCodOrdenImg").value;
	var ajax= new getXMLObject();	
	ajax.open("POST","ControlFormatosPestanas",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){			
				ReportesOrdenImagenologia(CodOrden);
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=FinOrden&CodOrden="+CodOrden);
}

function OmitirDetOrdenImg(CodDetOrdImg){
	var Div=document.getElementById("DetOrdImg");
	var CodOrdenImg=document.getElementById("txtCodOrdenImg").value;
	var ajax= new getXMLObject();	
	ajax.open("POST","ControlCitasExamen",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){	
				Contenido=ajax.responseText;
				Div.innerHTML=Contenido;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=334&CodDetOrdImg="+CodDetOrdImg+"&CodOrdenImg="+CodOrdenImg);
}
    
   
	
/*************************** FIN ORDEN DE SERVICIO***************************/
/*************************** ANTECEDENTES***************************/
function ImprimirAntecedentes(Genero){
	var CodPac=document.getElementById("CodPac").value;
	if(Genero=="1"){
		//genero Femenino
		paginaf="hic_ImprimirAntecedenteFemenino.jsp?CodPac="+CodPac;			
	    var opcionesf="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	    opcionesf =opcionesf+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	    window.open(paginaf,"NUEVA",opcionesf);
	}
	if(Genero=="2"){
		//genero Masculino
		pagina="hic_ImprimirAntecedentes.jsp?CodPac="+CodPac;			
	    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	    window.open(pagina,"NUEVA",opciones);
	}
	
	
}
function Antecedentes(){
	DivValor=document.getElementById('HistoriaPaciente');
	var CedPac=document.getElementById("CedPac").value;
	var CodPac=document.getElementById("CodPac").value;
	ajax=getXMLObject();
	    ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		Contenido=ajax.responseText;
				DivValor.innerHTML=Contenido;
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=NOACTIVO&CedPac="+CedPac+"&CodPac="+CodPac); //Posting txtname to Servlet
	//alert("Orden de Servicio");
}

function RadioAntecedentes() {	
	ajax=getXMLObject();
	var contenido=document.getElementById('Antecedente');
	var radioButtons = document.getElementsByName("radiobutton");
	var CedPac=document.getElementById("CedPac").value;
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
	         ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	         ajax.onreadystatechange  = function(){
	        	 if (ajax.readyState == 4) {
	        		 contenido.innerHTML = ajax.responseText	
	        	 }
	         };
	         ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	         ajax.send("valor="+val+"&CedPac="+CedPac); //Posting txtname to Servlet
		  }	     
    }
}
/****************************GINECOBSTETRA****************************/
function IngresarGineco(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtCodusuario").value;
	var CedPac=document.getElementById("CedPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var CodPac=document.getElementById("CodPac").value;
	//var Estudio=document.getElementById("txtEstudioGineco").value;
	//var observacion=document.getElementById("txtObservacionGineco").value;
	 var gestas=document.getElementById("txtGestas").value;
	 var partos=document.getElementById("txtPartos").value;
	 var abortos=document.getElementById("txtAbortos").value;
	 var cesareas=document.getElementById("txtCesareas").value;
	 var espontaneos=document.getElementById("txtEspontaneos").value;
	 var menarquia=document.getElementById("txtMenarquia").value;
	 var fum=document.getElementById("txtFum").value;
	 var furs=document.getElementById("txtFurs").value;
	 var ivs=document.getElementById("txtIvs").value;
	 var its=document.getElementById("txtIts").value;
	 var compasexual=document.getElementById("txtComSexu").value;
	
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CedPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}
	if(CodPac==""){
		alert("El Diagnostico No Puede Ir Vacio.");
	}
	
	if((usuario!="null")&&(CedPac!="")&&(hora!="")&&(fecha!="")&&(CodPac!="")){
		ajax=getXMLObject();
		ajax.open("POST","ControlMultiplePacientes",true);
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				CargarGinecobstetra();
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=IngGine&CedPac="+CedPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&CodPac="+CodPac+"&gestas="+gestas+"&partos="+partos+"&abortos="+abortos+"&cesareas="+cesareas+"&espontaneos="+espontaneos+"&menarquia="+menarquia+"&fum="+fum+"&furs="+furs+"&ivs="+ivs+"&its="+its+"&compasexual="+compasexual); //Posting txtname to Servlet
	}
}

function CargarGinecobstetra(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("CedPac").value;	

	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=11&CedPac="+CedPac); //Posting txtname to Servlet
}

function ActualizarGineco(){
	ajax=getXMLObject();
	var CodigoGineco=document.getElementById("txtCodGineco").value;
	var gestas=document.getElementById("txtGestas").value;
	var partos=document.getElementById("txtPartos").value;
	var abortos=document.getElementById("txtAbortos").value;
	var cesareas=document.getElementById("txtCesareas").value;
	var espontaneos=document.getElementById("txtEspontaneos").value;
	var menarquia=document.getElementById("txtMenarquia").value;
	var fum=document.getElementById("txtFum").value;
	var furs=document.getElementById("txtFurs").value;
	var ivs=document.getElementById("txtIvs").value;
	var its=document.getElementById("txtIts").value;
	var compasexual=document.getElementById("txtComSexu").value;
	 
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			CargarGinecobstetra();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ActuGineco&CodigoGineco="+CodigoGineco+"&gestas="+gestas+"&partos="+partos+"&abortos="+abortos+"&cesareas="+cesareas+"&espontaneos="+espontaneos+"&menarquia="+menarquia+"&fum="+fum+"&furs="+furs+"&ivs="+ivs+"&its="+its+"&compasexual="+compasexual); //Posting txtname to Servlet
}
/***************************FIN GINECOBSTETRA*************************/
/****************************PERSONALES*******************************/
function DiagnosticoPaciente(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtCodusuario").value;
	var CedPac=document.getElementById("CedPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var CodDiagnostico=document.getElementById("txtCodDiagnostico").value;
	var observacion=document.getElementById("txtObservacioDiagnostico").value;
	//var CodAdm=document.getElementById("CodAdm").value;
	var CodPac=document.getElementById("CodPac").value;
	
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CedPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}
	if(CodDiagnostico==""){
		alert("El Diagnostico No Puede Ir Vacio.");
	}
	if((usuario!="null")&&(CedPac!="")&&(hora!="")&&(fecha!="")&&(CodDiagnostico!="")){
		if(xmlhttp){
			xmlhttp.open("POST","ControladorMedico",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					//alert(xmlhttp.responseText);
					CargarDiagnostico();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=6&CedPac="+CedPac+"&CodDiagnostico="+CodDiagnostico+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&observacion="+observacion+"&CodPac="+CodPac); //Posting txtname to Servlet "&CodAdm="+CodAdm+
		}
		
	}
}
function CargarDiagnostico(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("CedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5&CedPac="+CedPac); //Posting txtname to Servlet
}

function Observacion (CodDiag) {		
	pagina="hic_ObserDiagnostico.jsp?CodDiag="+CodDiag;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=400, height=200, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);		
}

function omitirPersonal(CodigoAntPersonal){
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			CargarDiagnostico();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=omitirAnte&CodigoAntPersonal="+CodigoAntPersonal); //Posting txtname to Servlet
}

/****************************FIN PERSONALES*******************************/
/****************************ALERGICOS*******************************/
/*
function CargarAlergias(){
	
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("CedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=7&CedPac="+CedPac); //Posting txtname to Servlet
}

function omitirAlergico(CodigoAler){
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			CargarAlergias();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=omitirAler&CodigoAler="+CodigoAler); //Posting txtname to Servlet
}
function IngresaAlergia(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtCodusuario").value;
	var CodPac=document.getElementById("CodPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var Alergia=document.getElementById("txtAlergia").value;
	var Reaccion=document.getElementById("txtReaccion").value;
	var CedPac=document.getElementById("CedPac").value;
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CodPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}	
	if(Alergia==""){
		alert("No Hay Ingresado La Alergia.");
	}
	if(Reaccion==""){
		alert("No Hay Ingresado La Reaccion.");
	}
	if((usuario!="null")&&(CodPac!="")&&(hora!="")&&(fecha!="")&&(Alergia!="")&&(Reaccion!="")){
		if(xmlhttp){
			xmlhttp.open("POST","ControlMultiplePacientes",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarAlergias();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngreAler&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&Alergia="+Alergia+"&Reaccion="+Reaccion+"&cedula="+CedPac); //Posting txtname to Servlet
		}
		
	}
}*/
/****************************FIN ALERGICOS*******************************/
/****************************FAMILIARES*******************************/
/*
function IngresaAntFamiliares(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtCodusuario").value;
	var CodPac=document.getElementById("CodPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var Diagnostico=document.getElementById("txtCodDiagnostico").value;
	var ObserFami=document.getElementById("txtObseFami").value;
	var familiar=document.getElementById("cmbFamilia").value;
	var CedPac=document.getElementById("CedPac").value;
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CodPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}	
	if(familiar=="Seleccion"){
		alert("No Ha Seleccionado Ningun Familiar.");
	}
	if((usuario!="null")&&(CodPac!="")&&(hora!="")&&(fecha!="")&&(familiar!="Seleccion")){
		if(xmlhttp){
			xmlhttp.open("POST","ControlMultiplePacientes",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarAnteFamiliar();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngreFami&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&Diagnostico="+Diagnostico+"&ObserFami="+ObserFami+"&familiar="+familiar+"&cedula="+CedPac); //Posting txtname to Servlet
		}
		
	}
}

function CargarAnteFamiliar(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("CedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=9&CedPac="+CedPac); //Posting txtname to Servlet
}

function omitirAnteFamiliar(CodigoAntFamiliar){
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			CargarAnteFamiliar();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=omitirAnFan&CodigoAntFamiliar="+CodigoAntFamiliar); //Posting txtname to Servlet
}

function MostrarObserFami(CodAntFami){
	pagina="hic_ObserAnteFami.jsp?CodAntFami="+CodAntFami;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=400, height=200, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}*/
/****************************FIN FAMILIARES*******************************/
/*************************** TOXICOLOGIA ***************************/
/*
function IngresarToxico(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtCodusuario").value;
	var CodPac=document.getElementById("CodPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var Toxicologia=document.getElementById("cmbToxicologia").value;
	var ObservacionTx=document.getElementById("txtObservacionTx").value;
	var CedPac=document.getElementById("CedPac").value;
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CodPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}	
	if(Toxicologia=="Seleccione"){
		alert("No Hay Seleccionado La Toxicologia.");
	}
	if(ObservacionTx==""){
		alert("No Hay Ingresado Ninguna Observacion.");
	}
	if((usuario!="null")&&(CodPac!="")&&(hora!="")&&(fecha!="")&&(Toxicologia!="")&&(ObservacionTx!="")){
		if(xmlhttp){
			xmlhttp.open("POST","ControlMultiplePacientes",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarToxicologia();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngTox&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&tipoTx="+Toxicologia+"&ObservacionTx="+ObservacionTx+"&cedula="+CedPac); //Posting txtname to Servlet
		}
		
	}
}
 
function CargarToxicologia(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("CedPac").value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=8&CedPac="+CedPac); //Posting txtname to Servlet
}

function omitirToxico(CodigoTx){
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			CargarToxicologia();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=omitirToxico&CodigoTx="+CodigoTx); //Posting txtname to Servlet
}
*/

/***************************FIN TOXICOLOGIA ************************/

/*************************** TRANSFUCIONES ***************************/
/*
function RadioTrans() {	
	ajax=getXMLObject();
	var contenido=document.getElementById('OpcTransf');
	var radioButtons = document.getElementsByName("radioTrans");
	var CodPac=document.getElementById("CodPac").value;
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
	         ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	         ajax.onreadystatechange  = function(){
	        	 if (ajax.readyState == 4) {
	        		 contenido.innerHTML = ajax.responseText	
	        	 }
	         };
	         ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	         ajax.send("valor="+val+"&CodPac="+CodPac); //Posting txtname to Servlet
		  }	     
    }
}

function GuardarSiTrans(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtCodusuario").value;
	var CodPac=document.getElementById("CodPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var CedPac=document.getElementById("CedPac").value;
	var transfucion="Si";
	var ObservacionTr=document.getElementById("txtObservacionTr").value;
	var Cantidad=document.getElementById("txtCantidad").value;
	var FechaTransfucion=document.getElementById("txtFechaTransfucion").value;
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CodPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}	
	if(Cantidad==""){
		alert("No Hay Ingresado La Cantidad.");
	}
	if(FechaTransfucion==""){
		alert("No Hay Ingresado La Fecha De Transfucion.");
	}
	if((usuario!="null")&&(CodPac!="")&&(hora!="")&&(fecha!="")&&(Cantidad!="")&&(FechaTransfucion!="")){
		if(xmlhttp){
			xmlhttp.open("POST","ControlMultiplePacientes",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarTransfucion();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngTran&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&Cantidad="+Cantidad+"&FechaTransfucion="+FechaTransfucion+"&transfucion="+transfucion+"&ObservacionTr="+ObservacionTr+"&cedula="+CedPac); //Posting txtname to Servlet
		}
		
	}
}

function CargarTransfucion(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("CedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	ajax.send("valor=10&CedPac="+CedPac); //Posting txtname to Servlet
}

function omitirTranfucion(CodigoTranfucion){
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			CargarTransfucion();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=omitirTranfucion&CodigoTranfucion="+CodigoTranfucion); //Posting txtname to Servlet
}

function GuardarNoTrans(){
	//alert()
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtCodusuario").value;
	var CodPac=document.getElementById("CodPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var CedPac=document.getElementById("CedPac").value;
	var transfucion="No";
	var ObservacionTr="No Se Le Han Hecho Transfuciones De Sanguineas";
	var Cantidad="Ninguna";
	var FechaTransfucion="No Refiere";
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CodPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}		
	if((usuario!="null")&&(CodPac!="")&&(hora!="")&&(fecha!="")){
		if(xmlhttp){
			xmlhttp.open("POST","ControlMultiplePacientes",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarTransfucion();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngTran&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&Cantidad="+Cantidad+"&FechaTransfucion="+FechaTransfucion+"&transfucion="+transfucion+"&ObservacionTr="+ObservacionTr+"&cedula="+CedPac); //Posting txtname to Servlet
		}
		
	}
}
*/
/***************************FIN TRANSFUCIONES ************************/

/***************************QUIRURJICOS************************/
/*
function IngresarProcedimiento(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtCodusuario").value;
	var CodPac=document.getElementById("CodPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var CedPac=document.getElementById("CedPac").value;
	var txtFechaCx=document.getElementById("txtFechaCx").value;
	var txtObservacionCx=document.getElementById("txtObservacionCx").value;
	var txtCodigoQx=document.getElementById("txtCodigoQx").value;
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CodPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}	
	if(txtFechaCx==""){
		alert("No Hay Ingresado La fecha de la Cirujia.");
	}
	if(txtObservacionCx==""){
		alert("No Hay Ingresado Ninguna Observacion.");
	}
	if(txtCodigoQx==""){
		alert("No Hay Seleccionado Ningun Procedimiento.");
	}
	if((usuario!="null")&&(CodPac!="")&&(hora!="")&&(fecha!="")&&(txtFechaCx!="")&&(txtObservacionCx!="")&&(txtCodigoQx!="")){
		if(xmlhttp){
			xmlhttp.open("POST","ControlMultiplePacientes",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarProcedimiento();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngrQx&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&fechaQx="+txtFechaCx+"&observacion="+txtObservacionCx+"&codigoQx="+txtCodigoQx+"&cedula="+CedPac); //Posting txtname to Servlet
		}
		
	}
}

function CargarProcedimiento(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("CedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=6&CedPac="+CedPac); //Posting txtname to Servlet
}

function omitirQuirurjico(CodigoQx){
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			CargarProcedimiento();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=omitirQx&CodigoQx="+CodigoQx); //Posting txtname to Servlet
}*/


/***************************FIN QUIRURJICOS ************************/
/***************************MEDICAMENTOS************************/
/*
function IngresarMd(){
	var xmlhttp = new getXMLObject();
	var usuario=document.getElementById("txtCodusuario").value;
	var CodPac=document.getElementById("CodPac").value;
	var hora=document.getElementById("txtHora").value;
	var fecha=document.getElementById("txtFecha").value;
	var CedPac=document.getElementById("CedPac").value;
	var txtObservacionMd=document.getElementById("txtObservacionMd").value;
	var txtCodigoMed=document.getElementById("txtCodigoMed").value;
	if(usuario=="null"){
		alert("No Hay Usuario Activo Para Hacer Esta Asignacion.");
	}	
	if(CodPac==""){
		alert("No Hay Ningun Paciente Seleccionado.");
	}
	if(hora==""){
		alert("No Hay Hora Seleccionada.");
	}
	if(fecha==""){
		alert("No Hay Fecha Seleccionada.");
	}	
	
	if(txtObservacionMd==""){
		alert("No Hay Ingresado Ninguna Observacion.");
	}
	if(txtCodigoMed==""){
		alert("No Hay Seleccionado Ningun Procedimiento.");
	}
	if((usuario!="null")&&(CodPac!="")&&(hora!="")&&(fecha!="")&&(txtObservacionMd!="")&&(txtCodigoMed!="")){
		if(xmlhttp){
			xmlhttp.open("POST","ControlMultiplePacientes",true);
			xmlhttp.onreadystatechange  = function(){
				if (xmlhttp.readyState == 4) {
					CargarMedicamentos();
		    	}
			};
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("valor=IngMed&CodPac="+CodPac+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&observacion="+txtObservacionMd+"&codigoMed="+txtCodigoMed+"&cedula="+CedPac); //Posting txtname to Servlet
		}
		
	}
}
 
function CargarMedicamentos(){
	var contenido=document.getElementById('Antecedente');
	var CedPac=document.getElementById("CedPac").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=12&CedPac="+CedPac); //Posting txtname to Servlet
}
function omitirAntMedicamento(CodigoMedicamento){
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			CargarMedicamentos();
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=omitirAntMedica&CodigoMedicamento="+CodigoMedicamento); //Posting txtname to Servlet	
}*/

/*************************** FIN MEDICAMENTOS **************************/
/*************************** FIN ANTECEDENTES **************************/
/*************************** FORMATOS         **************************/
function Formatos(){
	DivValor=document.getElementById('HistoriaPaciente');
	var CodigoUsuario=document.getElementById('txtCodusuario').value;
	ajax=getXMLObject();
	    ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		Contenido=ajax.responseText;
				DivValor.innerHTML=Contenido;
				
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=ACTIVO&CodigoUsuario="+CodigoUsuario); //Posting txtname to Servlet
}


/*************************** FIN FORMATOS ******************************/
/***********************ABRIR LOS PDF**************************/
 function Abrir(ano,mes,dia,hora,minuto,segundo,codpa,codexa,codgenero) {	
	var fecha=ano+"-"+mes+"-"+dia
	var horas=hora+":"+minuto+":"+segundo			
    pagina="Lab_Reporte_Grupo.jsp?hora="+horas+"&fecha="+fecha+"&subarea="+codexa+"&codpac="+codpa+"&codge="+codgenero+"";			
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
}

function Abrir_ventana (ano,mes,dia,hora,minuto,segundo,tipo,exa,codpa,codres,valorini,valorfinal) {

	fecha=ano+"-"+mes+"-"+dia
	horas=hora+":"+minuto+":"+segundo		
	if(tipo=="2"){
    pagina="Lab_Reporte_Rango.jsp?hora="+horas+"&fecha="+fecha+"&exa="+exa+"&codpac="+codpa+"&codre="+codres+"&valorini="+valorini+"&valorfi="+valorfinal+"";			
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
	}else{
		  pagina="Lab_Reporte_Texto.jsp?hora="+horas+"&fecha="+fecha+"&exa="+exa+"&codpac="+codpa+"&codre="+codres+"";
          var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
          opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
          window.open(pagina,"NUEVA",opciones);
	}	
}

function mostarexamenes (codIce,usuario) {		
	pagina="img_ReporteImg.jsp?codIce="+codIce+"&usuario="+usuario;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);		
}
/***********************FIN DE ABRIR LOS PDF*******************/

/******************************HIC_BUSCARPACIENTES.JSP EMPIEZA******************/
function AutoCompletaPaciente(){
	var NomPac=document.getElementById("txtNomPaciente").value;
	var radioButtons = document.getElementsByName("rbUnidad");
	var CodUnidad=null;
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			CodUnidad=radioButtons[x].value;
		}
	}
	if(CodUnidad==null){
		alert("Seleccione Unidad.");
	}
	if(CodUnidad!=null){
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			document.getElementById("ResultadosBusqueda").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3&NomPac="+NomPac+"&CodUnidad="+CodUnidad); //Posting txtname to Servle
	}
}

/*function CargarAutoCompletaPaciente(){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			document.getElementById("ResultadosBusqueda").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	ajax.send("valor=3.1"); //Posting txtname to Servle	
}*/


function VerUnidad(){
	ajax=getXMLObject();
	var radioButtons = document.getElementsByName("rbUnidad");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var CodUnidad=radioButtons[x].value;    
	         ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	         ajax.onreadystatechange  = function(){
	        	 if (ajax.readyState == 4) {
	        		 document.getElementById("ResultadosBusqueda").innerHTML=ajax.responseText;
	        	 }
	         };
	         ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	         ajax.send("valor=3.1&CodUnidad="+CodUnidad); //Posting txtname to Servle	
		}	     
	}
}

function VerMultiples(){
	
	var total=document.getElementById('txtCont').value;
	var cont=total;	
	var cadena ="";
	var k=0;
	if(total!=1){
		for(var h=0; h<=total-1; h++){			
			if(document.form1.chkPac[h].checked){
				CodPac=document.form1.chkPac[h].value;
				cadena = cadena+document.form1.chkPac[h].value;
	           	cadena = cadena +"|";	
	           	k=k+1;
			}
			cont--;			
		}
		if(k<=3){
			window.location.href="hic_SeleccionarPacientes2.jsp?pacientes="+cadena;			
		}else{
			alert("Solo Puede Visualizar 3 Pacientes Al Tiempo.");
		}
		if(k==0){
			alert("No Ha Seleccionado Ningun Paciente");
			window.location.href="hic_BuscarPacientes.jsp";
		}
	}
	else{
		if(document.form1.chkPac.checked){
			cadena = cadena+document.form1.chkPac.value;
           	cadena = cadena +"|";
           	window.location.href="hic_SeleccionarPacientes2.jsp?pacientes="+cadena; 
		}
	}
	
}

/*****************************FIN HIC_BUSCARPACIENTES.JSP ************************/
