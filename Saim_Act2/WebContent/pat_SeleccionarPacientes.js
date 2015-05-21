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


/*************************** FORMATOS         **************************/

function ActualizarResultados(){
	var c=document.getElementById('txtTotal').value;
	var oscar=c;
	var a;
	if(c!=1){
		for(var i=0; i<=c-1; i++){
			CodResul=document.form1.txtCodResultado[i].value;
			Resul=document.form1.txtRespuesta[i].value;
			for(a=0;a<Resul.length;a++){
				Resul=Resul.replace('Ñ','@');
				Resul=Resul.replace('ñ','@');				
			}			
			ActualizarResultHistorias(Resul,CodResul);
	      oscar--; 
	     }	
	 }else{	
		 Resul=document.getElementById("txtRespuesta").value;
		 for(e=0;e<Resul.length;e++){
				Resul=Resul.replace('Ñ','@');
				Resul=Resul.replace('ñ','@');				
			}
		 CodResul=document.getElementById("txtCodResultado").value;
		 ActualizarResultHistorias(Resul,CodResul);
	 }
}

function AsignarFormato(){
	var CodFormato=document.getElementById("txtCodFormato").value;
	var CodAdmision=document.getElementById("CodAdm").value;
	var CodPaciente=document.getElementById("CodPac").value;
	var CodUsuario=document.getElementById("txtCodusuario").value;
	var Hora=document.getElementById("txtHora").value;
	var Fecha=document.getElementById("txtFecha").value;
	
	if((CodPaciente=="")&&(CodAdmision=="")){
		alert("No Se Ha Seleccionado Ningun Paciente!!!");
	}		
	if((CodPaciente!="")&&(CodAdmision!="")){
		ajax=getXMLObject();
		ajax.open("POST","Pat_ControlListarPacientes",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if(ajax.readyState == 4){
				document.getElementById('FormatosPaciente').innerHTML = ajax.responseText;
				///CargarFormatos();
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		ajax.send("valor=1&CodFormato="+CodFormato+"&CodAdmision="+CodAdmision+"&CodPaciente="+CodPaciente+"&CodUsuario="+CodUsuario+"&Hora="+Hora+"&Fecha="+Fecha); //Posting txtname to Servlet
	}
	
}

function OmitirFormato(CodigoFormatoPaciente){
	
	var opcion=confirm("Desea Eliminar Este Formato?");
	
	if(opcion){
		ajax=getXMLObject();
		ajax.open("POST","Pat_ControlListarPacientes",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if(ajax.readyState == 4){
				document.getElementById('FormatosPaciente').innerHTML = ajax.responseText;
				//CargarFormatos();
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		ajax.send("valor=8&CodigoFormatoPaciente="+CodigoFormatoPaciente); //Posting txtname to Servlet
	}
	else{
	}	
	/*	*/
}

/*************************** FIN FORMATOS ******************************/