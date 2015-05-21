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
function HorariosMedicosCex(){
	ajax=getXMLObject();
	var Formulario=document.getElementById("Formulario");
	ajax.open("POST","PYP_ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			Formulario.innerHTML = ajax.responseText
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CHMCEX"); //Posting txtname to Servlet

}
function CargarFormulario(){
	ajax=getXMLObject();
	var Formulario=document.getElementById("Formulario");
	ajax.open("POST","PYP_ControlAsignarCita",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			Formulario.innerHTML = ajax.responseText
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=6"); //Posting txtname to Servlet
}

function Generar(){
	ajax=getXMLObject();
	var CodigoMedico=document.getElementById("cmbMedico").value;
	//var rbFecha = document.getElementsByName("rbFecha");
	var Formulario=document.getElementById("Formulario");
	var Dia=document.getElementById("cmbDia").value;
	var Mes=document.getElementById("cmbMes").value;
	var Anio=document.getElementById("txtAnio").value;
	var fecha=Anio+"-"+Mes+"-"+Dia;
	//for (var x = 0; x < rbFecha.length; x ++) {
		//if (rbFecha[x].checked) {fecha=rbFecha[x].value;}
	//}
	//if(fecha=="N"){alert("Seleccione Una Fecha.");}
	if(CodigoMedico=="Seleccione"){
		ajax.open("POST","PYP_ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Formulario.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=7.1&fecha="+fecha); //Posting txtname to Servlet
	}	
	if((CodigoMedico!="Seleccione")){
		ajax.open("POST","PYP_ControlAsignarCita",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				Formulario.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=7&CodigoMedico="+CodigoMedico+"&fecha="+fecha); //Posting txtname to Servlet
	}
}

function Imprimir() {
	var fecha=document.getElementById("txtFecha").value;
	var CodigoMedico=document.getElementById("txtCodigoMedico").value;
	pagina="pyp_ReportesCitas.jsp?CodigoMedico="+CodigoMedico+"&fecha="+fecha;
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina,"NUEVA",opciones);
}