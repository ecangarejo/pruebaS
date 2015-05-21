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
function ComprobarAsignarFormato() {

	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			var resultado=xmlhttp.responseText;

			document.getElementById('FormatosPaciente').innerHTML = resultado;
	     }
	     else {
	        alert("Error during AJAX call ComprobarAsignarFormato. Please try again");
	     }
	   }
	   VerLaboraHistoria();
	}

function AsignarFormato(){
	var CodFormato=document.getElementById("txtCodFormato").value;
	var CodAdmision=document.getElementById("txtCodAdm").value;
	var CodPaciente=document.getElementById("txtCodPac").value;
	var CodUsuario=document.getElementById("txtCodUsu").value;
	var Hora=document.getElementById("txtHora").value;
	var Fecha=document.getElementById("txtFecha").value;
	
	if((CodPaciente=="")&&(CodAdmision=="")){
		alert("No Se Ha Seleccionado Ningun Paciente!!!");
	}		
	if((CodPaciente!="")&&(CodAdmision!="")){
		if(xmlhttp) { 	
			//alert()
			xmlhttp.open("POST","Pat_ControlListarPacientes?valor=1&CodFormato="+CodFormato+"&CodAdmision="+CodAdmision+"&CodPaciente="+CodPaciente+"&CodUsuario="+CodUsuario+"&Hora="+Hora+"&Fecha="+Fecha,true); //getname will be the servlet name
			xmlhttp.onreadystatechange  = ComprobarAsignarFormato;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
		}
	}
}	
////////////////////////////////////////////////////////////////////////////
function ComprobarImagenologia(){
	if (xmlhttp.readyState == 4) {		
		if(xmlhttp.status == 200) {
			var resultado=xmlhttp.responseText;
			document.getElementById('ImagenologiaPaciente').innerHTML = resultado;
	     }
	     else {
	        alert("Error during AJAX call ComprobarImagenologia. Please try again");
	     }
	   }
}

function VerImagenologiaHistoria(){
	var CodPac=document.getElementById("CodPac").value;
	if(xmlhttp) { 	
	    xmlhttp.open("POST","ControlVerFormatos?valor=4&CodPac="+CodPac,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarImagenologia;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
}
////////////////////////////////////////////////////////////////////////////
function ComprobarLaboratorios(){
	if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			var resultado=xmlhttp.responseText;
			//alert(resultado)
			document.getElementById('LaboratoriosPaciente').innerHTML = resultado;
	     }
	     else {
	        alert("Error during AJAX call ComprobarLaboratorios. Please try again");
	     }
	   }
	VerImagenologiaHistoria();
}

function VerLaboraHistoria(){
	var CodPac=document.getElementById("CodPac").value;
	if(xmlhttp) { 	
	    xmlhttp.open("POST","ControlVerFormatos?valor=3&CodPac="+CodPac,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarLaboratorios;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
}
////////////////////////////////////////////////////////////////////////////
function VerAreas(CodFormato,dia,mes,anio,horas,minutos,segundos){
	var Fecha=anio+"-"+mes+"-"+dia;
	var Hora=horas+":"+minutos+":"+segundos;
	 if(xmlhttp) { 	
		    xmlhttp.open("POST","ControlVerFormatos?valor=0&CodFormato="+CodFormato+"&HoraFormato="+Hora+"&FechaFormato="+Fecha,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarAreas;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   //var x;
}
function ComprobarAreas() {
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			document.getElementById('areas').innerHTML = xmlhttp.responseText;
			var div =document.getElementById("formulario");
			//div.style.display="none"
			div.style.display='none';


	     }
	     else {
	        alert("Error during AJAX call ComprobarAreas. Please try again");
	     }
	   }
	}

////////////////////////////////////////////////////////////////////////////
function ComprobarPreguntas() {
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			div = document.getElementById('formulario');

			div.style.display = '';

			//alert(xmlhttp.responseText);
			document.getElementById('formulario').innerHTML = xmlhttp.responseText;
			
	     }
	     else {
	        alert("Error during AJAX call ComprobarPreguntas. Please try again");
	     }
		//mostarpreguntas();
	   }
	}
function mostarpreguntas(CodArea){
	var CodAdm=document.getElementById("CodAdm").value;
	var CodPac=document.getElementById("CodPac").value;
	var FechaFormato=document.getElementById("FechaFormato").value;
	var HoraFormato=document.getElementById("HoraFormato").value;
	var CodUsuario=document.getElementById("txtCodUsu").value;
	if(xmlhttp) { 	
	    xmlhttp.open("POST","ControlVerFormatos?valor=1&CodArea="+CodArea+"&CodAdm="+CodAdm+"&CodPac="+CodPac+"&FechaFormato="+FechaFormato+"&HoraFormato="+HoraFormato+"&CodUsuario="+CodUsuario,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarPreguntas;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlhttp.send(""); //Posting txtname to Servlet
	}
	
}
////////////////////////////////////////////////////////////////////////////
function ComprobarPaciente() {
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			var texto=xmlhttp.responseText;
			var y=texto.split("|").length;
	     	var z=texto.split("|");	     	
	     	document.getElementById('txtNomPac').value=z[0];
	     	document.getElementById('txtSexo').value=z[1];
	     	document.getElementById('txtTipoDoc').value=z[2];
	     	document.getElementById('txtNumDoc').value=z[3];
	     	document.getElementById('txtEdad').value=z[4];	     	
	     }
	     else {
	        alert("Error during AJAX call ComprobarPaciente. Please try again");
	     }
	   }
	   AsignarFormato()
	}

function BuscarPaciente(CodPac,CodAdm){
	if(xmlhttp) {
	    xmlhttp.open("POST","ControlVerFormatos?valor=2&CodPac="+CodPac,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarPaciente;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
}
///////////////////////////////////////////////////////////////////////////
function GenerarOrden(){
	var CodPac=document.getElementById("CodPac").value;
	window.location.href="ord_AsignarLaboratorio.jsp?CodPac="+CodPac;
	//window.location.href="hic_OrdenServicio.jsp?CodPac="+CodPac;	
//alert()	
}
////////////////////////////////////////////////////
function Abrir(ano,mes,dia,hora,minuto,segundo,codpa,codexa,edad,codgenero) {
	
	var fecha=ano+"-"+mes+"-"+dia
	var horas=hora+":"+minuto+":"+segundo

			
   pagina="Lab_Reporte_Grupo.jsp?hora="+horas+"&fecha="+fecha+"&subarea="+codexa+"&codpac="+codpa+"&edad="+edad+"&codge="+codgenero+""
			
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   window.open(pagina,"NUEVA",opciones);
}
///////////////////////////////////////////////////////////////////////////
function Abrir_ventana (ano,mes,dia,hora,minuto,segundo,tipo,exa,codpa,codres,valorini,valorfinal) {
		
	fecha=ano+"-"+mes+"-"+dia
	horas=hora+":"+minuto+":"+segundo
		
	if(tipo=="2"){
    pagina="Lab_Reporte_Rango.jsp?hora="+horas+"&fecha="+fecha+"&exa="+exa+"&codpac="+codpa+"&codre="+codres+"&valorini="+valorini+"&valorfi="+valorfinal+""
			
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   window.open(pagina,"NUEVA",opciones);
	}else{
		  pagina="Lab_Reporte_Texto.jsp?hora="+horas+"&fecha="+fecha+"&exa="+exa+"&codpac="+codpa+"&codre="+codres+""
		
          opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
          opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
          window.open(pagina,"NUEVA",opciones);
	}
	
}
///////////////////////////////////////////////////////////////////
function mostarexamenes (codIce,usuario) {		
	pagina="img_ReporteImg.jsp?codIce="+codIce+"&usuario="+usuario;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);		
}

/*function GuardarResultados(){
	var c=document.getElementById('Contador').value;
	var oscar=c;
	if(c!=1){
		for(var i=0; i<=c-1; i++){
			Resul=document.form1.txtRespuesta[i].value;
			CodPre=document.form1.txtCodPregunta[i].value;
			codArea_Fk=document.form1.txtCodAreaPre[i].value;
			GuardarResultHistorias(Resul,CodPre,codArea_Fk);
	      oscar--; 
	     }	
	 }else{	
		 Resul=document.form1.txtRespuesta.value;
		 CodPre=document.form1.txtCodPregunta.value;
		 codArea_Fk=document.form1.txtCodAreaPre.value;
		 GuardarResultHistorias(Resul,CodPre,codArea_Fk);
	 }	
}

function GuardarResultHistorias(Resul,CodPre,codArea_Fk){
	var CodPac=document.getElementById("CodPac").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var codUsu_fk=document.getElementById("txtCodUsu").value;
	/////////////////////////////////////////////////////////////////
	  getXMLObject();
		  var xmlhttp = new getXMLObject();
	  if(xmlhttp) { 
		var hora=document.getElementById("txtHora").value;
		var fecha=document.getElementById("txtFecha").value;	
		xmlhttp.open("POST","ControlIngresarResultados?valor=0&codPreg_fk="+CodPre+"&codPa_fk="+CodPac+"&codAdm_fk="+CodAdm+"&resultados="+Resul+"&hora="+hora+"&fecha="+fecha+"&estado=0&codUsu_fk="+codUsu_fk+"&codArea_Fk="+codArea_Fk,true); //getname will be the servlet name
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlhttp.send(""); //Posting txtname to Servlet    
	}	
	/////////////////////////////////////////////////////////////////	
}*/
/*function ActualizarResultHistorias(Resul,CodResul){
	getXMLObject();
	var xmlhttp = new getXMLObject();
	if(xmlhttp) { 
		//alert("Resul "+Resul+" CodResul "+CodResul)
		//var hora=document.getElementById("txtHora").value;
		//var fecha=document.getElementById("txtFecha").value;
		var hora=document.getElementById("HoraFormato").value;
		var fecha=document.getElementById("FechaFormato").value;
		xmlhttp.open("POST","ControlIngresarResultados?valor=1&Resul="+Resul+"&CodResul="+CodResul,true); //getname will be the servlet name
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xmlhttp.send(""); //Posting txtname to Servlet    
	}
}
function ActualizarResultados(){
	var c=document.getElementById('txtTotal').value;
	var oscar=c;
	if(c!=1){
		for(var i=0; i<=c-1; i++){
			Resul=document.form1.txtRespuesta[i].value;
			CodResul=document.form1.txtCodResultado[i].value;
			ActualizarResultHistorias(Resul,CodResul);
	      oscar--; 
	     }	
	 }else{	
		 Resul=document.form1.txtRespuesta.value;
		 CodResul=document.form1.txtCodResultado.value;
		 ActualizarResultHistorias(Resul,CodResul);
	 }
}
*/