
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
function ComprobarFormulario() {
	//var x;
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			document.getElementById('formulario').innerHTML = xmlhttp.responseText;
	     }
	     else {
	        alert("Error during AJAX call ComprobarFormulario. Please try again");
	     }
	   }
	}
function Formulario(){
	 if(xmlhttp) { 	
		    xmlhttp.open("POST","ControlPacientesLaboratorios?valor=0",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarFormulario;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
}
//////////////////////////////////////////////////////////////////////////
function RealizarExamenes(CodPac){
	//
	//alert(CodPac);
	window.location.href="lab_RealizarPendiente.jsp?CodPac="+CodPac; 
}
/////////////////////////////////////////////////////////////////////////
function ComprobarPendientePaci(){
	 if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
				document.getElementById('examenes').innerHTML = xmlhttp.responseText;
		     }
		     else {
		        alert("Error during AJAX call ComprobarFormulario. Please try again");
		     }
		   }
}

function LlenarLaboraPendientePaci(CodPac){
	if(xmlhttp) { 	
	    xmlhttp.open("POST","ControlPacientesLaboratorios?valor=1&CodPac="+CodPac,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarPendientePaci;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
	   var x;
}

///////////////////////////////////////////////////////////////////////////
function ComprobarFormato(){
	 if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
				document.getElementById('formato').innerHTML = xmlhttp.responseText;
		     }
		     else {
		        alert("Error during AJAX call ComprobarFormulario. Please try again");
		     }
		   }
}
function Realizar(CodEstudio,TipoExamen,CodAsignacion,Genero,Edad){
	if(Genero=="1"){
		Genero="Femenino";
	}
	if(Genero=="2"){
		Genero="Masculino";
	}
	if(xmlhttp) { 	
	    xmlhttp.open("POST","ControlPacientesLaboratorios?valor=2&CodEstudio="+CodEstudio+"&TipoExamen="+TipoExamen+"&CodAsignacion="+CodAsignacion+"&edad="+Edad+"&sexo="+Genero,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarFormato;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
	
}
function omitir(CodAsignacion){
	if(xmlhttp) { 	
	    xmlhttp.open("POST","ControlPacientesLaboratorios?valor=6&CodAsignacion="+CodAsignacion,true); //getname will be the servlet name
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
window.location.reload();
}
////////////////////////////////////////////////////////////////////////////
function ExamenRango(){
	var CodPac=document.getElementById("txtCodPac").value;
	var CodExa=document.getElementById("codexa").value;
	var Fecha=document.getElementById("txtFecha").value;
	var Hora=document.getElementById("txtHora").value;
	var Resultado=document.getElementById("exagrupo").value ;
	var Usuario=document.getElementById("txtUsuario").value;
	var cedula=document.getElementById("txtnumdoc").value;
	var CodAsignacion=document.getElementById("CodAsignacion").value;
	var Resul=encodeURIComponent(document.getElementById("exagrupo").value);
if(xmlhttp) { 	
	    xmlhttp.open("POST","ControlPacientesLaboratorios?valor=3&CodPac="+CodPac+"&CodExa="+CodExa+"&Fecha="+Fecha+"&Hora="+Hora+"&Usuario="+Usuario+"&Resultado="+Resul+"&CodAsignacion="+CodAsignacion+"&cedula="+cedula,true); //getname will be the servlet name
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
window.location.reload(); 
}
///////////////////////////////////////////////////////////////////////////
function ExamenTexto(){
	var CodPac=document.getElementById("txtCodPac").value;
	var CodExa=document.getElementById("codexa").value;
	var Fecha=document.getElementById("txtFecha").value;
	var Hora=document.getElementById("txtHora").value;
	var Resultado=document.getElementById("exagrupo").value ;
	var Usuario=document.getElementById("txtUsuario").value;
	var CodAsignacion=document.getElementById("CodAsignacion").value;
	var cedula=document.getElementById("txtnumdoc").value;
	var Resul=encodeURIComponent(document.getElementById("exagrupo").value);
	if(xmlhttp) { 	
	    xmlhttp.open("POST","ControlPacientesLaboratorios?valor=4&CodPac="+CodPac+"&CodExa="+CodExa+"&Fecha="+Fecha+"&Hora="+Hora+"&Usuario="+Usuario+"&Resultado="+Resul+"&CodAsignacion="+CodAsignacion+"&cedula="+cedula,true); //getname will be the servlet name
	    //xmlhttp.onreadystatechange  = ComprobarFormato;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
	window.location.reload(); 
}

//////////////////////////////////////////////////////////////////////////
function GuardarGrupo(Resultado,CodExa,CodTipoExa){
	//alert();
	var Fecha=document.getElementById("txtFecha").value;
	var Hora=document.getElementById("txtHora").value;
	var Usuario=document.getElementById("txtUsuario").value;
	var CodAsignacion=document.getElementById("CodAsignacion").value;
	var CodPac=document.getElementById("txtCodPac").value;
	var cedula=document.getElementById("txtnumdoc").value;
	var Resul=encodeURIComponent(Resultado);
	getXMLObject();
	 var xmlhttp = new getXMLObject();
	if(xmlhttp) { 
	    xmlhttp.open("POST","ControlPacientesLaboratorios?valor=5&Resultado="+Resul+"&CodExa="+CodExa+"&CodTipoExa="+CodTipoExa+"&Hora="+Hora+"&Fecha="+Fecha+"&Usuario="+Usuario+"&CodAsignacion="+CodAsignacion+"&CodPac="+CodPac+"&cedula="+cedula,true); //getname will be the servlet name
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet	
	}
	window.location.reload(); 
	//alert("Resultado "+Resultado+" CodExamen "+CodExamen+" CodTipoExa "+CodTipoExa);
}
//////////////////////////////////////////////////////////////////////////

function ExamenGrupo(){
	var c=document.getElementById("txtContador").value;
	var oscar=c;
	 if(c!=1){
			for(var i=0; i<=c-1; i++){	
		       	Resultado=form1.exagrupo[i].value;
		        CodExamen=form1.codexa[i].value;
		        CodTipoExa=form1.codtipoexa[i].value;
		        GuardarGrupo(Resultado,CodExamen,CodTipoExa);
		        oscar--; 
		     }	
		 }
	 else{
		 Resultado=form1.exagrupo[i].value;
	     CodExamen=form1.codexa[i].value;
	     CodTipoExa=form1.codtipoexa[i].value;
	     GuardarGrupo(Resultado,CodExamen,CodTipoExa);
	}
}

function ReportesDia(){
	var Fecha=document.getElementById("txtFecha").value;
	var formulario=document.getElementById("formulario");
	ajax=getXMLObject();
	ajax.open("POST","ControlPacientesLaboratorios",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			formulario.innerHTML=ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=7&Fecha="+Fecha); //Posting txtname to Servlet*/

}