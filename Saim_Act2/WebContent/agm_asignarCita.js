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
/******************************************************************************************************/
//funci de la fecha de nacimiento
var patron = new Array(2,2,4);
var patron2 = new Array(1,3,3,3,3)
function masca(d,sep,pat,nums){
	


if(d.valant != d.value){
	val = d.value
	largo = val.length
	ini=val.substring(0,2);
	if(ini>31){
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
	//alert(ini);
	
	if(ini>12){
		alert("Mes No Valido!!!");
		val2=d.value="";
		d.focus();
		
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
/**********************************************ORDEN DE SERVICIO****************************************/
function BuscarPacienteO(){		
		var TipoDocumento=document.getElementById("cmbTipoDoc").value;
		var NumeroDocumento=document.getElementById("txtNumDocumento").value;
		var resultado=document.getElementById('resultado');
		if(TipoDocumento=="Seleccione"){alert("Seleccione El Tipo De Documento.")}
		if(NumeroDocumento==""){alert("Digite El Numero De Documento.")}
		if((TipoDocumento!="Seleccione")&&(NumeroDocumento!="")){
		ajax=getXMLObject();
		ajax.open("POST","ControlAsignarCita",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var validar=ajax.responseText;				
				resultado.innerHTML = validar;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1O&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento);		
	}	
}

function CargarEntidades(){
	var Entidad=document.getElementById("txtEntidad").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById("autoentidad").innerHTML = ajax.responseText;			
			var conen=document.getElementById("txtContadorEnt").value;
			if(conen=="1"){
				var CodEnt=document.getElementById("txtCodEnt").value;
				var NomEntidad=document.getElementById("txtNomEnt").value;
				var CodManual=document.getElementById("txtCodMT").value;
				
				var NumeroNit=document.getElementById("txtNumeroNit").value;
				var DireccionEnt=document.getElementById("txtDireccionEnt").value;
				var TelefonoEnt=document.getElementById("txtTelefonoEnt").value;
				
				AsignarEntidad(CodEnt,NomEntidad,CodManual,NumeroNit,DireccionEnt,TelefonoEnt);
			}
		}
	}	
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=BENT&Entidad="+Entidad);
	
}

function AsignarEntidad(CodEnt,NomEntidad,CodManual,NumeroNit,DireccionEnt,TelefonoEnt){
	//alert("CodDx="+CodDx+"Cie10="+Cie10+"NomDx="+NomDx);
	document.getElementById("txtCodEnt").value=CodEnt;
	document.getElementById("txtEntidad").value=NomEntidad;
	document.getElementById("txtCodMT").value=CodManual;
	document.getElementById("txtCodMaTr").value=CodManual;
	document.getElementById("txtCodEntidadPrestadora").value=CodEnt;
	
	document.getElementById("txtDireEntiPrestadora").value=DireccionEnt;
	document.getElementById("txtTelEntiPrestadora").value=TelefonoEnt;
	document.getElementById("txtNitEntidadPrestadora").value=NumeroNit;
	
	document.getElementById("autoentidad").innerHTML ='';
	
}



function CargarProgramas(){
	var CodEnt=document.getElementById("txtCodEntidadPrestadora").value;
	var Programa=document.getElementById("txtServicio").value;
	var CodManual=document.getElementById("txtCodMaTr").value;
	if(CodManual==""){
		alert("Seleccione una entidad prestadora");
		document.getElementById("txtEntidad").focus();
	}else{
	//alert("Programa="+Programa+" CodManual="+CodManual);
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById("autoservi").innerHTML = ajax.responseText;			
			var conen=document.getElementById("txtContadorPro").value;
			if(conen=="1"){
				var CodPrograma=document.getElementById("txtCodPrograma").value;
				var NomPrograma=document.getElementById("txtNomPrograma").value;
				var CodCups=document.getElementById("txtCodCups").value;
				var CodReferencia=document.getElementById("txtCodReferencia").value;
				var valor=document.getElementById("txtvalor").value;
				AsignarPrograma(CodPrograma,NomPrograma,CodCups,CodReferencia,valor);
			}
		}
	}	
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=BPRO&Programa="+Programa+"&CodManual="+CodManual+"&CodEnt="+CodEnt);
	}
	
}

function AsignarPrograma(CodPrograma,NomPrograma,CodCups,CodReferencia,valor){
	//alert("CodPrograma="+CodPrograma+"NomPrograma="+NomPrograma+"CodCups="+CodCups+" CodReferencia "+CodReferencia+" valor"+valor);
	document.getElementById("txtCodPrograma").value=CodPrograma;
	document.getElementById("txtCodCups").value=CodCups;
	document.getElementById("txtCodReferencia").value=CodReferencia;
	document.getElementById("txtCodCups1").value=CodCups;
	document.getElementById("txtCodReferencia1").value=CodReferencia;
	document.getElementById("txtvalor").value=valor;
	document.getElementById("txtCodigoPrograma").value=CodPrograma;
	document.getElementById("txtValorPrograma").value=valor;
	
	document.getElementById("txtServicio").value=NomPrograma;
	document.getElementById("autoservi").innerHTML ='';
	
}

function AsignarOrden(){
	var CodigoPrograma=document.getElementById("txtCodigoPrograma").value;
	var CodCups=document.getElementById("txtCodCups1").value;
	var CodReferencia=document.getElementById("txtCodReferencia1").value;
	var CodEnt=document.getElementById("txtCodEntidadPrestadora").value;
	var CodPac=document.getElementById("txtCodPac").value;
	var Servicio=document.getElementById("txtServicio").value;
	var ValorPrograma=document.getElementById("txtValorPrograma").value;
	var NumOrden=document.getElementById("txtNumOrden").value;
	var Observacion=document.getElementById("txtObservacion").value;
	var Cantidad=document.getElementById("txtCantidad").value;
	var CodOrden=document.getElementById("txtCodOrden").value;
	var Codusuario=document.getElementById("txtCodusuario").value;
	//var ConsecutivoOrden=document.getElementById("txtConsecutivoOrden").value;
	
	var NomEntidad=document.getElementById("txtEntidad").value;
	var DireEntiPrestadora=document.getElementById("txtDireEntiPrestadora").value;
	var TelEntiPrestadora=document.getElementById("txtTelEntiPrestadora").value;
	var NitEntidadPrestadora=document.getElementById("txtNitEntidadPrestadora").value;
	var TipoOrden=document.getElementById("cmbTipoOrden").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	
	if(CodEnt==""){
		alert("Seleccione Entidad Prestadora.");
		document.getElementById("txtEntidad").focus();
	}else{
		if(CodigoPrograma==""){
			alert("Seleccione servicio.");
			document.getElementById("txtServicio").focus();
		}else{
			if(TipoOrden=="Seleccione"){
				alert("Seleccione Tipo de Orden.");
				document.getElementById("cmbTipoOrden").focus();
			}else{
				ajax.onreadystatechange = function(){
					if(ajax.readyState == 4){
						document.getElementById("DetalleOrden").innerHTML=ajax.responseText;
						document.getElementById("txtCodCups1").value='';
						document.getElementById("txtCodReferencia1").value='';
						document.getElementById("txtServicio").value='';
						document.getElementById("txtValorPrograma").value='';
						document.getElementById("txtCodigoPrograma").value='';
						document.getElementById("txtEntidad").disabled=true;
						document.getElementById("txtObservacion").disabled=true;
						document.getElementById("txtNumOrden").disabled=true;
					}
				}	
				ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=AOS&CodigoPrograma="+CodigoPrograma+"&CodCups="+CodCups+"&CodReferencia="+CodReferencia+
						"&CodEnt="+CodEnt+"&CodPac="+CodPac+"&Servicio="+encodeURIComponent(Servicio)+"&ValorPrograma="+ValorPrograma+
						"&NumOrden="+NumOrden+"&Observacion="+Observacion+"&Cantidad="+Cantidad+"&CodOrden="+CodOrden+
						"&Codusuario="+Codusuario+"&ConsecutivoOrden=0&NomEntidad="+NomEntidad+"&DireEntiPrestadora="+DireEntiPrestadora+
						"&TelEntiPrestadora="+TelEntiPrestadora+"&NitEntidadPrestadora="+NitEntidadPrestadora+"&servicio="+TipoOrden);
			}
		}
	}
}

function FinalizarOS(){
	var CodOrden=document.getElementById("txtCodOrden").value;
	var CodMaTrEmpServ=document.getElementById("txtCodMaTr").value;
	var CodMaEntiPac=document.getElementById("txtCodMaEntiPac").value;
	var CodPac=document.getElementById("txtCodPac").value;
	var NumOrden=document.getElementById("txtNumOrden").value;
	var Codusuario=document.getElementById("txtCodusuario").value;
	var NomUsu=document.getElementById("txtNomUsu").value;
	var opcion=confirm("¿Desea Finalizar la Orden?");
	if(opcion){
		if(CodOrden=="0"){
			alert("No Se ha Relacionado Ningun Programa a la Orden de Servcio.");
		}else{
			ajax=getXMLObject();
			ajax.open("POST","ControlAsignarCita",true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					//document.getElementById("ReporteOS").innerHTML=ajax.responseText;
					alert("Orden Generada Exitosamente.");
					ReporteOrdenServicio(CodOrden)
					window.location.reload();
				}
			}	
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=FOS&CodOrden="+CodOrden+"&CodMaTrEmpServ="+CodMaTrEmpServ+
					"&CodMaEntiPac="+CodMaEntiPac+"&CodPac="+CodPac+"&NumOrden="+NumOrden+"&Codusuario="+Codusuario+"&NomUsu="+NomUsu);
		}
	}else{}
}

function AnularMovimiento(codigo){
	var CodOrden=document.getElementById("txtCodOrden").value;
	//var ConsecutivoOrden=document.getElementById("txtConsecutivoOrden").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById("DetalleOrden").innerHTML=ajax.responseText;
		}
	}	
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ODOS&codigo="+codigo+"&CodOrden="+CodOrden);
	
}
function CargarFormularioDI(){
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById("cardarDI").innerHTML=ajax.responseText;
		}
	}	
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CCADI");
}

function BuscarDetalleIngresos(){
	
	var txtFechaInicial=document.getElementById("txtFechaInicial").value;
	var txtFechaFinal=document.getElementById("txtFechaFinal").value;
	var CodEnt=document.getElementById("CodEnt").value;
	var CodUsu=document.getElementById("CodUsu").value;
	var NomEnt = document.form1.CodEnt.selectedIndex;
	var NombreEntidad=document.form1.CodEnt.options[NomEnt].text;
	
	var NomUsu = document.form1.CodUsu.selectedIndex;
	var NombreUsuario=document.form1.CodUsu.options[NomUsu].text;
	
	var TipoBusqueda="";
	if((txtFechaInicial=="")||(txtFechaFinal=="")){
		alert("Es necesario Digitar un rango de fechas para generar el reporte.");
	}else{
		if((CodEnt=="Seleccione")&&(CodUsu=="Seleccione")){
			//alert("Reporte Generado Solo Por Fechas");
			TipoBusqueda="FE";
		}
		
		if((CodEnt!="Seleccione")&&(CodUsu=="Seleccione")){
			//alert("Reporte Generado por Fechas y entidad");
			TipoBusqueda="FEEN";
		}
		
		if((CodEnt=="Seleccione")&&(CodUsu!="Seleccione")){
			//alert("Reporte Generado por fechas y usuario");
			TipoBusqueda="FEUS";
		}
		
		if((CodEnt!="Seleccione")&&(CodUsu!="Seleccione")){
			//alert("Reporte Generado por fechas, entidad y usuarios");
			TipoBusqueda="FEENUS";
		}
	}
	document.getElementById('cardarDI').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			document.getElementById("cardarDI").innerHTML=ajax.responseText;
		}
	}	
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=BDDI&txtFechaInicial="+txtFechaInicial+"&txtFechaFinal="+txtFechaFinal+"&CodEnt="+CodEnt+"&CodUsu="+CodUsu+"&TipoBusqueda="+TipoBusqueda+"&NombreEntidad="+NombreEntidad+"&NombreUsuario="+NombreUsuario);
}

function TipoConsulta(){	
	var radiobutton = document.getElementsByName("radiobutton");
	var TipoBus="";
	for (var x = 0; x < radiobutton.length; x ++) {
		if (radiobutton[x].checked) {
			TipoBus=radiobutton[x].id;
		}
	}	
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById("resultado").innerHTML=ajax.responseText;
		}
	}	
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=TCOS&TipoBus="+TipoBus);
}


function BuscarOrdenServ(){
 	//document.getElementById('resultado').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
	var radiobutton = document.getElementsByName("radiobutton");
	var TipoBus="";
	for (var x = 0; x < radiobutton.length; x ++) {
		if (radiobutton[x].checked) {
			TipoBus=radiobutton[x].id;
		}
	}
	
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);


	
	if(TipoBus=="CDOC"){
		var NumDoc=document.getElementById("txtNumDocumento").value;
		var TipoDoc=document.getElementById("cmbTipoDoc").value;
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				document.getElementById("resultado").innerHTML=ajax.responseText;
			}
		}	
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=RECOS&NumDoc="+NumDoc+"&TipoDoc="+TipoDoc+"&TipoBus="+TipoBus);
	}
	
	if(TipoBus=="CNOS"){
		var NumOrden=document.getElementById("txtNumOrdenServicio").value;
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				document.getElementById("resultado").innerHTML=ajax.responseText;
			}
		}	
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=RECOS&NumOrden="+NumOrden+"&TipoBus="+TipoBus);
	}
	
	if(TipoBus=="CNOM"){
		var NombrePac=document.getElementById("txtNombrePac").value;
		var PrimerApellido=document.getElementById("txtPrimerApellido").value;
		var SegundoApellido=document.getElementById("txtSegundoApellido").value;
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				document.getElementById("resultado").innerHTML=ajax.responseText;
			}
		}	
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=RECOS&NombrePac="+NombrePac+"&PrimerApellido="+PrimerApellido+"&SegundoApellido="+SegundoApellido+"&TipoBus="+TipoBus);
	}
	
	if(TipoBus=="CFEH"){
		var txtFechaInicial=document.getElementById("txtFechaInicial").value;
		var txtFechaFinal=document.getElementById("txtFechaFinal").value;
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				document.getElementById("resultado").innerHTML=ajax.responseText;
			}
		}	
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=RECOS&txtFechaInicial="+txtFechaInicial+"&txtFechaFinal="+txtFechaFinal+"&TipoBus="+TipoBus);
	}
	
}

function BuscarOrdenServicioAnular(){
	var NumOrden=document.getElementById("txtNumeroOrden").value;	
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById("resultado").innerHTML=ajax.responseText;
		}
	}	
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=BOSA&NumOrden="+NumOrden);
}

function AnularOrdenServicio(CodOrden){
	//var Observacion=document.getElementById("txtObservacion").value;
	var ObservacionAnula=document.getElementById("txtObserAnula").value;
	var Codusuario=document.getElementById("txtCodusuario").value;
	if(ObservacionAnula==""){
		alert("Escriba el motivo de anulacion.");
		document.getElementById("txtObserAnula").focus();
	}else{
		var AgruObserva="MOTIVO ANULACION:"+ObservacionAnula
		ajax=getXMLObject();
		ajax.open("POST","ControlAsignarCita",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				alert("Orden Anulada Satisfactoriamente.");
				window.location.reload();
				//document.getElementById("resultado").innerHTML=ajax.responseText;
			}
		}	
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=AOSA&CodOrden="+CodOrden+"&AgruObserva="+AgruObserva+"&Codusuario="+Codusuario);
	}
}

function ReporteOrdenServicio(CodOrden){	
	pagina="fact_ReporteOrdenServicio.jsp?CodOrden="+CodOrden;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

/*******************************************FIN DE ORDEN DE SERVICIO************************************/
function ImprimirReciboCaja(CodDetRecCaj){
	//alert("ImprimirReciboCaja("+CodDetRecCaj+")")
	pagina="agm_ReciboCaja.jsp?CodDetRecCaj="+CodDetRecCaj;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function ImprimirReciboEgreso(CodEgr){
	//alert("ImprimirReciboCaja("+CodDetRecCaj+")")
	pagina="agm_ReciboEgreso.jsp?CodEgr="+CodEgr;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function BuscarRecibos(){
	//var NumRecibo=document.getElementById("txtNumRecibo").value;
	var Dia=document.getElementById("cmbdia").value
	var Mes=document.getElementById("cmbmes").value;
	var Anio=document.getElementById("txtanio").value;
	
	var Fecha=Anio+"-"+Mes+"-"+Dia;
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
	ajax.send("valor=BR1&TipoRec="+TipoRec+"&Fecha="+Fecha);

	
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

function CargarSubcentroCosto(){
	var CodCCosto=document.getElementById("cmbCentCost").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){				
			document.getElementById("CenSubCost").innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CsC&CodCCosto="+CodCCosto);	
}


function CentrosCostos(){
	var CodSuc=document.getElementById("cmbSucur").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){				
			document.getElementById("CenCost").innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CCC&CodSuc="+CodSuc);	
}
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


function BuscarPacienteAnular(){
	
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
				alert("Opcion Valida Solo para anular,para crear paciente trate de asignarle una cita.");
				//window.location.href("adm_IngresarDemografico.jsp");
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

function DevolverAtencion(CodHorarioMedico){
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;
			alert(validar);
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=DA&CodHorarioMedico="+CodHorarioMedico);
	
}
function BuscarPacienteDevolverAtencion(){
	
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
				alert("Opcion Valida Solo para anular,para crear paciente trate de asignarle una cita.");
				//window.location.href("adm_IngresarDemografico.jsp");
			}else{
			resultado.innerHTML = validar;
			Coincidencias.innerHTML="";
			}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1DA&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento);
	
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
				alert("Opcion Valida Solo para anular,para crear paciente trate de asignarle una cita.");
				//window.location.href("adm_IngresarDemografico.jsp");
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

function VerHorasDisponibles(){

	var CodMed=document.getElementById("cmbEspecialista").value;
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var FechaPcita=anioIni+"-"+mesIni+"-"+diaIni;
	 if(diaIni=="Dia"){
		 alert("Seleccione el dia.");
	 }else{
		 if(mesIni=="Mes"){
			 alert("Seleccione el mes.");
		 }else{
			 if(anioIni==""){
				 alert("Digite el año.");
			 }else{
				 if(CodMed=="Seleccione"){
					alert("Seleccione el medico."); 
				 }else{
					 ajax=getXMLObject();
					 ajax.open("POST","ControlAsignarCita",true);
					 ajax.onreadystatechange = function(){
						 if(ajax.readyState == 4){
							 var validar=ajax.responseText;
							 //alert(validar);
							 document.getElementById("CamHoraMed").innerHTML = validar;
						 }
					 }
					 ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
					 ajax.send("valor=HorDisp&CodMed="+CodMed+"&FechaPcita="+FechaPcita);
				 }
			 }
		 }
	 }
}



function BuscarProgCex(){
	var Programa=document.getElementById("txtCodProgCEX").value;
	var ManualTarifario=document.getElementById("CodManualTarifario").value;
	var CodEnt=document.getElementById("CodEnt").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById("CodProCex").innerHTML = ajax.responseText;			
			var cong=document.getElementById("txtContadorP").value;
			//alert(cong);
			if(cong=="1"){
				var txtCodPro=document.getElementById("txtCodProP").value;
				var txtCodRefPro=document.getElementById("txtCodRefPro").value;
				var txtNomPro=document.getElementById("txtNomPro").value;
				var txtValorPro=document.getElementById("txtValorProP").value;
				AsignarProgCex(txtCodPro,txtCodRefPro,txtNomPro,txtValorPro);
			}
		}
	}	
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=BPDX&Programa="+Programa+"&ManualTarifario="+ManualTarifario+"&CodEnt="+CodEnt);
}

function AsignarProgCex(CodPro,CodRefPro,NomPro,ValorPro){
	//alert("CodPro="+CodPro+" CodRefPro="+CodRefPro+" NomPro="+NomPro+" ValorPro="+ValorPro)
	document.getElementById("txtCodPro").value=CodPro;
	document.getElementById("txtCodProgCEX").value=CodRefPro;
	document.getElementById("txtValorPro").value=ValorPro;
	document.getElementById("CodProCex").innerHTML =NomPro;
}

function BuscarDxCex(){
	var Diagnostico=document.getElementById("txtDiagCEX").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById("DxCex").innerHTML = ajax.responseText;			
			var cong=document.getElementById("txtContadorACG").value;
			if(cong=="1"){
				var CodDx=document.getElementById("txtCodDxA").value;
				var Cie10=document.getElementById("txtCie10").value;
				var NomDx=document.getElementById("txtNomDx").value;
				AsignarDxCex(CodDx,Cie10,NomDx);
			}
		}
	}	
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=BDX&Diagnostico="+Diagnostico);
}

function AsignarDxCex(CodDx,Cie10,NomDx){
	//alert("CodDx="+CodDx+"Cie10="+Cie10+"NomDx="+NomDx);
	document.getElementById("txtCodDx").value=CodDx;
	document.getElementById("txtDiagCEX").value=Cie10;
	document.getElementById("DxCex").innerHTML=NomDx;
	
}



function BuscarPacienteR(){
	var TipoDocumento=document.getElementById("cmbTipoDoc").value;
	var NumeroDocumento=document.getElementById("txtNumDocumento").value;
	var resultado=document.getElementById('resultado');
	if(TipoDocumento=="Seleccione"){alert("Seleccione El Tipo De Documento.")}
	if(NumeroDocumento==""){alert("Digite El Numero De Documento.")}
	
	if((TipoDocumento!="Seleccione")&&(NumeroDocumento!="")){
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;
		//	alert(validar);
			/*if(validar=="1"){
				//window.location.href("adm_IngresarDemografico.jsp");
			}else{
			resultado.innerHTML = validar;
			Coincidencias.innerHTML="";*/
			resultado.innerHTML = validar;
			}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=R1&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento);
}

function MostrasCitasDisponiblesMedico(){
	var CodMedico=document.getElementById("cmbMedico").value;
	//alert("Fecha"+Fecha+" CodMedico"+CodMedico);
	if(CodMedico=="Seleccione"){
		alert("Seleccione Medico");
	}else{
		ajax=getXMLObject();
		ajax.open("POST","ControlAsignarCita",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){			
				document.getElementById("DetallesCita").innerHTML = ajax.responseText	
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=CCMT&CodMedico="+CodMedico);
	}
}

function CargarMunicipio(){
	var CodDep=document.getElementById("cbdep").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			
			document.getElementById("Municipio").innerHTML = ajax.responseText;
			}
		}	
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=mun&CodDep="+CodDep);

}

function GuardarDemograficoCER(){
	var papellido=document.getElementById("txtpapellido").value;
	var sapellido=document.getElementById("txtsapellido").value;
	var nombre=document.getElementById("txtnombre").value;
	var fechanaci=document.getElementById("txtfechanaci").value;
	var CodMuni=document.getElementById("cbmun").value;
	var Genero=document.getElementById("cmbGenero").value;
	var Telefono=document.getElementById("txtcelular").value;
	
	var Telefono_Fijo=document.getElementById("txtTelFijo").value;
	var Telefono_Oficina=document.getElementById("txtTelOfi").value;
	var EstadoCivil=document.getElementById("cmbEstadoCivil").value;
	var TipoAfiliacion=document.getElementById("cmbTipoAfiliacion").value;
	var NivelCotizante=document.getElementById("cmbNivelCotizante").value;
	
	var Direccion=document.getElementById("txtdire").value;
	var Entidad=document.getElementById("txtcodentidad").value;
	var TipoDoc=document.getElementById("cmbTipoDoc").value;
	var NumDocumento=document.getElementById("txtNumDocumento").value;
	var Email=document.getElementById("txtEmail").value;
	if(papellido==""){
		alert("Escriba el Primer Apellido");
		document.getElementById("txtpapellido").focus();
	}else{
		if(nombre==""){
			alert("Escriba el Nombre");
			document.getElementById("txtnombre").focus();
		}else{
			if(fechanaci==""){
				alert("Escriba la Fecha de Nacimiento");
				document.getElementById("txtfechanaci").focus();				
			}else{
				if(CodMuni=="Seleccione"){
					alert("Seleccione el Municipio");
					document.getElementById("cbmun").focus();
				}else{
					if(Genero=="Seleccione"){
						alert("Seleccione el Genero");
						document.getElementById("cmbGenero").focus();
					}else{
						if(Telefono==""){
							alert("Escriba el Telefono");
							document.getElementById("txtcelular").focus();
						}else{
							if(Entidad==""){
								alert("Seleccione la Entidad");
								document.getElementById("cbeps").focus();
							}else{
								if(Email==""){
									alert("Escriba Email");
									document.getElementById("txtEmail").focus();
								}else{	
									if(EstadoCivil=="Seleccione"){
										alert("Seleccione Estado Civil");
										document.getElementById("cmbEstadoCivil").focus();
									}else{
										if((TipoAfiliacion=="Seleccione")||(NivelCotizante=="Seleccione")){
											alert("Falta seleccionar tipo de afiliacion y/o nivel cotizante.");
									
										}else{
											ajax=getXMLObject();
											ajax.open("POST","ControlAsignarCita",true);
											ajax.onreadystatechange = function(){
												if(ajax.readyState == 4){
													BuscarPacienteR();
												}
											}	
											ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
											//ajax.send("valor=mun&CodDep="+CodDep);
											ajax.send("valor=ddr&papellido="+papellido+"&sapellido="+sapellido+"&nombre="+nombre+
													"&fechanaci="+fechanaci+"&CodMuni="+CodMuni+"&Genero="+Genero+"&Telefono="+Telefono+
													"&Entidad="+Entidad+"&TipoDoc="+TipoDoc+"&NumDocumento="+NumDocumento+"&Direccion="+Direccion+"&Email="+Email+
													"&Telefono_Fijo="+Telefono_Fijo+"&Telefono_Oficina="+Telefono_Oficina+"&EstadoCivil="+EstadoCivil+"&TipoAfiliacion="+TipoAfiliacion+"&NivelCotizante="+NivelCotizante);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}

function IngresarProximaCita(){
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var FechaPcita=anioIni+"-"+mesIni+"-"+diaIni;
	 var HoraPcita=document.getElementById("cmbhora").value;
	 var CodUsu=document.getElementById("cmbEspecialista").value;
	 var CodPac=document.getElementById("CodPac").value;
	 var NombreCompleto=document.getElementById("txtNombreCompleto").value;
	 if(diaIni=="Dia"){
		 
	 }else{
	 ajax=getXMLObject();
		ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				var v=ajax.responseText;
				if(v=="1"){
					alert("Cita Asignada Exitosamente.");
					window.location.reload();
				}else{
					alert(ajax.responseText);
				}
				//window.location.reload();
				//contenido.innerHTML = ajax.responseText
				//InmunoterapiaCE();
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=ProCitR&FechaPcita="+FechaPcita+"&HoraPcita="+HoraPcita+"&CodUsu="+CodUsu+"&CodPac="+CodPac+"&NombreCompleto="+NombreCompleto);
	
}
}

function AsignarCita(CodHorario){
	

	//alert(CodHorario);
	///////////////////////////////////////////////
	var CodProg=document.getElementById("txtCodPro").value;
	var CodDx=document.getElementById("txtCodDx").value;
	var ValorPro=document.getElementById("txtValorPro").value;
	var NumAutori=document.getElementById("txtNumAutor").value;
	var Copago=document.getElementById("txtCopagoCex").value;
	var Moderacion=document.getElementById("txtModeracionCex").value;
	var CodCsC=document.getElementById("cmbSubCentCost").value;
	var CodSucur=document.getElementById("cmbSucur").value;
	var CodEnt=document.getElementById("CodEnt").value;
	var NitEnti=document.getElementById("NitEnti").value;
	///////////////////////////////////////////////
	var TipoCita=document.getElementById("cmbTipoCita"+CodHorario).value;
	var Observacion=document.getElementById("txtObservacion"+CodHorario).value;
	var CodPac=document.getElementById("CodPac").value;
	var FechaPcita=document.getElementById("txtFechaSel").value;
	//var CodUsu=document.getElementById("txtCodUsuMed").value;
	var CodUsu=document.getElementById("txtCodusuario").value;
	var NombreCompleto=document.getElementById("txtNombreCompleto").value;
	var CodMedico=document.getElementById("cmbMedico").value;
	var CodEspMed=document.getElementById("txtCodEspMed").value;
	//alert("Antes copago="+Copago+"Moderacion="+Moderacion);
	if(Copago==""){
		//alert("copago vacio");
		Copago="0";
		}
	
	if(Moderacion==""){
		//alert("moderacion vacia");
		Moderacion="0";
		}
	
	//alert("Despues copago="+Copago+" Moderacion="+Moderacion);
//	alert("valor=GNCEX&FechaPcita="+FechaPcita+"&CodUsu="+CodUsu+"&CodPac="+CodPac+"&NombreCompleto="+NombreCompleto+"&TipoCita="+TipoCita+"&Observacion="+Observacion+"&CodMedico="+CodMedico+"&CodHorario="+CodHorario+"&CodEspMed="+CodEspMed+
	//		"&CodProg="+CodProg+"&CodDx="+CodDx+"&ValorPro="+ValorPro+"&NumAutori="+NumAutori+"&Copago="+Copago+"&Moderacion="+Moderacion+"&CodCsC="+CodCsC+"&CodSucur="+CodSucur+"&CodEnt="+CodEnt+"&NitEnti="+NitEnti);

	if(TipoCita=="Seleccione"){
		alert("Seleccione el tipo de cita.");
		document.getElementById("cmbTipoCita"+CodHorario).focus();
	}else{
		if(CodProg==""){
			alert("Digite el programa.");
		}else{
			if(CodDx==""){
				alert("Digite el diagnostico.");
			}else{
				if(NumAutori==""){
					alert("Digite numero de autorizacion.");
					document.getElementById("txtNumAutor").focus();
				}else{
					if(CodCsC=="Seleccione"){
						alert("Seleccione el Subcentro de costo.");
						document.getElementById("cmbSubCentCost").focus();
					}else{
						var opcion=confirm("Desea Asignar Esta Cita ?");
						//alert("valor=GNCEX&FechaPcita="+FechaPcita+"&CodUsu="+CodUsu+"&CodPac="+CodPac+"&NombreCompleto="+NombreCompleto+"&TipoCita="+TipoCita+"&Observacion="+Observacion+"&CodMedico="+CodMedico+"&CodHorario="+CodHorario+"&CodEspMed="+CodEspMed);
						if(opcion){
							ajax=getXMLObject();
							ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
							ajax.onreadystatechange  = function(){
								if (ajax.readyState == 4) {
									var v=ajax.responseText;
									if(v=="1"){
										alert("Cita Asignada Exitosamente.");
										window.location.reload();
										ReporteRecordatorioCita(CodHorario);
									}else{
										alert(ajax.responseText);
									}
								}
							};
							ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
							//alert("valor=GNCEX&FechaPcita="+FechaPcita+"&CodUsu="+CodUsu+"&CodPac="+CodPac+"&NombreCompleto="+NombreCompleto+"&TipoCita="+TipoCita+"&Observacion="+Observacion+"&CodMedico="+CodMedico+"&CodHorario="+CodHorario+"&CodEspMed="+CodEspMed+
							//				"&CodProg="+CodProg+"&CodDx="+CodDx+"&ValorPro="+ValorPro+"&NumAutori="+NumAutori+"&Copago="+Copago+"&Moderacion="+Moderacion+"&CodCsC="+CodCsC+"&CodSucur="+CodSucur+"&CodEnt="+CodEnt+"&NitEnti="+NitEnti);
							ajax.send("valor=GNCEX&FechaPcita="+FechaPcita+"&CodUsu="+CodUsu+"&CodPac="+CodPac+"&NombreCompleto="+NombreCompleto+"&TipoCita="+TipoCita+"&Observacion="+Observacion+"&CodMedico="+CodMedico+"&CodHorario="+CodHorario+"&CodEspMed="+CodEspMed+
									"&CodProg="+CodProg+"&CodDx="+CodDx+"&ValorPro="+ValorPro+"&NumAutori="+NumAutori+"&Copago="+Copago+"&Moderacion="+Moderacion+"&CodCsC="+CodCsC+"&CodSucur="+CodSucur+"&CodEnt="+CodEnt+"&NitEnti="+NitEnti);
						}else{}
					}
				}
			}
		}
	}	
}

function ReporteRecordatorioCita(CodHoraMedico) {		
	pagina="agm_ReporteRecordatorioCita.jsp?CodHoraMedico="+CodHoraMedico;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);		
}

function CargarCitasMedico(Fecha){
	var CodMedico=document.getElementById("cmbMedico").value;
	//alert("Fecha"+Fecha+" CodMedico"+CodMedico);
	if(CodMedico=="Seleccione"){
		alert("Seleccione Medico");
	}else{
		ajax=getXMLObject();
		ajax.open("POST","ControlAsignarCita",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){			
				document.getElementById("DetallesCita").innerHTML = ajax.responseText	
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=CCM&Fecha="+Fecha+"&CodMedico="+CodMedico);
	}
}
function CargarCalendario(){
	var mes=document.getElementById('cmbmesIni').value;
	var anio=document.getElementById("txtanioIni").value;
	var CodMedico=document.getElementById("cmbMedico").value;
	if(CodMedico=="Seleccione"){
		alert("Seleccione Medico");
	}else{
		ajax=getXMLObject();
		ajax.open("POST","ControlAsignarCita",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){			
				document.getElementById("CalenMes").innerHTML = ajax.responseText	
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=CargarMes&mes="+mes+"&anio="+anio+"&CodMedico="+CodMedico);
	}
}
function BuscarMedicos(){
	var medico=document.getElementById('medico');
	var CodigoEspecialida=document.getElementById("cmbEspecialidad").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){			
			medico.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2&CodigoEspecialidad="+CodigoEspecialida);
}

function UnClick(hora,minuto,jornada,codigoHorarioMedico,Anio,Mes,Dia,CodigoEspecialidad,CodigoMedico){
	var fecha=Anio+"-"+Mes+"-"+Dia;
	if(jornada=="0"){
		jornada="AM"
	}
	if(jornada=="1"){
		jornada="PM"
	}
	if(jornada=="2"){
		jornada="MD"
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
	ajax.send("valor=4&CodigoEspecialidad="+CodigoEspecialidad+"&codigoHorarioMedico="+codigoHorarioMedico+"&CodigoPaciente="+CodigoPaciente+"&CodigoMedico="+CodigoMedico+"&NombrePaciente="+NombreCompleto+"&UsuarioInsercion="+UsuarioInsercion+"&Fecha="+fecha+"&Hora="+Hora);
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
	if(jornada=="2"){
		jornada="MD"
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
	var UsuarioInsercion=document.getElementById("txtCodusuario").value;
	
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
	ajax.send("valor=5&codigoHorarioMedico="+codigoHorarioMedico+"&CodigoPaciente="+CodigoPaciente+"&UsuarioInsercion="+UsuarioInsercion);
	}else{
		
	}
}


function AnularCita(CodHorMedico){
	var UsuarioInsercion=document.getElementById("txtCodusuario").value;
	
	var opcion=confirm("Desea Cancelar La Cita ?");
	if(opcion){
		var MotivoAnula=document.getElementById("txtMotivoAnula"+CodHorMedico).value;
		var FechaHoraCita=document.getElementById("FechaHoraCita").value;
		var MedicoCita=document.getElementById("MedicoCita").value;
		var NombrePacAnul=document.getElementById("NombrePacAnul").value;
		var DocumenPac=document.getElementById("DocumenPac").value;
		
		if(MotivoAnula==""){
			alert("Escriba el motivo de la anulacion.");
			document.getElementById("txtMotivoAnula"+CodHorMedico).focus();
		}else{
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
			ajax.send("valor=5A&codigoHorarioMedico="+CodHorMedico+"&UsuarioInsercion="+UsuarioInsercion+"&MotivoAnula="+MotivoAnula+
					"&FechaHoraCita="+FechaHoraCita+"&MedicoCita="+MedicoCita+"&NombrePacAnul="+NombrePacAnul+"&DocumenPac="+DocumenPac);
			//alert("valor=5A&codigoHorarioMedico="+CodHorMedico+"&UsuarioInsercion="+UsuarioInsercion+"&MotivoAnula="+MotivoAnula);
		}
	
	}else{
		
	}
	
}
