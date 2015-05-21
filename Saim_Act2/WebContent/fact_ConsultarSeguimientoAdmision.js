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
///////////////////////////////////////////////////////////////////////////////////////
function Rips(){
	ajax=getXMLObject();
	ajax.open("POST","ControlSeguimientoAdmision",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			//alert(ajax.responseText);
			//document.getElementById("ResultadosBusqueda").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2"); //Posting txtname to Servle	
}
function AutoCompletaPaciente(){
	var NomPac=document.getElementById("txtNomPaciente").value;
	
	//alert(NomPac)
	ajax=getXMLObject();
	ajax.open("POST","ControlSeguimientoAdmision",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			document.getElementById("ResultadosBusqueda").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1.1&NomPac="+NomPac); //Posting txtname to Servle	
}

function CargarAutoCompletaPaciente(){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlSeguimientoAdmision",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			document.getElementById("ResultadosBusqueda").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1"); //Posting txtname to Servle	
}


function VerSeguimiento(){
	
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
			window.location.href="hic_SeleccionarPacientes.jsp?pacientes="+cadena;			
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
           	window.location.href="hic_SeleccionarPacientes.jsp?pacientes="+cadena;
		}
	}
	
}