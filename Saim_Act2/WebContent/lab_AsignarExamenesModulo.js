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
//////////////////////////////////////////////////////////////////////////////////////////////////

function fecha_gru(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  var dia = time1.getDate()
	  var temp1 = "" + ((año < 10) ? "0" : "") + año
	  temp1 += ((mes < 10) ? "-0" : "-") + mes
	  temp1 += ((dia < 10) ? "-0" : "-") + dia
	 document.getElementById('fechagru').value = temp1
	 id = setTimeout("fecha()",1000)
	  }
function hora_gru(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.getElementById('horagru').value = temp;
	  id = setTimeout("hora()",1000)
	  }
function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}
function validarcom(tecla, e)
	{ 
		opc = false;
		var s=document.getElementById('txtnumdoc').value;
		var n=0;
		var pos=0;
		for(i=0;i<s.length;i++){
		   valor = parseInt(s.charAt(i));
			if(isNaN(valor)){
				n++;
			}
		}
		if(n>0)
		{
			window.alert('El campo  solo acepta valores Numericos');
			document.getElementById('txtnumdoc').value="";
			document.getElementById('txtnumdoc').focus();
			return false;
		}
		tecla =   e.keyCode;  e.which;
	if(tecla == 13)
      {
		var gen=document.getElementById('cbafiliacion').selectedIndex;
		tipo=document.getElementById('cbafiliacion').options[gen].text;
	        cedula=document.getElementById('txtnumdoc').value;
	        if(cedula==""){
		        alert("Digite La Identificacion.");
	        }else{
	       		ajax=getXMLObject();
	     	    ajax.open("POST","ControlAsignarExamenesModulo",true); //getname will be the servlet name
	     	    ajax.onreadystatechange=function() {
	     			if (ajax.readyState==4) {
	     				var lleno=ajax.responseText;
	     				if(lleno!=""){
	     					document.getElementById('formulario').innerHTML = lleno;
	     				}
	     				if(lleno==""){
	     					window.location.href("adm_IngresarDemografico.jsp");
	     				}
	     			}
	     		}
	     	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	     		ajax.send("valor=1&TipoDocumento="+tipo+"&NumeroDocumento="+cedula); //Posting txtname to Servlet
	        }	
		}
	}
function VerExamenes(){	
	DivValor=document.getElementById('Examenes');
	var CodArea=document.getElementById("cmbAreaExamen").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarExamenesModulo",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			Contenido=ajax.responseText;
			DivValor.innerHTML=Contenido;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2&CodArea="+CodArea); //Posting txtname to Servle	
}
function EnviarLaboratorios(CodEstudio,TipoEstu){
	var CodigoPac=document.getElementById("txtCodPaciente").value;
	var HoraPeticion=document.getElementById("horagru").value;
	var FechaPeticion=document.getElementById("fechagru").value;
	var NombreMedico=document.getElementById("txtNombreMedico").value;
	var TipoPyP=document.getElementById("cmbPyP").value;
	if(NombreMedico==""){
		alert("Escriba El Nombre Del Medico.");
	}
	if(TipoPyP=="Seleccione"){
		alert("Seleccione El Tipo De PyP.");
	}
	if((NombreMedico!="")&&(TipoPyP!="Seleccione")){
	ajax=getXMLObject();
	ajax.open("POST","ControlFormatosPestanas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=9&CodEstudio="+CodEstudio+"&TipoEstu="+TipoEstu+"&CodigoPac="+CodigoPac+"&HoraPeticion="+HoraPeticion+"&FechaPeticion="+FechaPeticion+"&NombreMedico="+NombreMedico+"&TipoPyP="+TipoPyP);
	}
}
function GuardarLaboratorios(){	
	var total=document.getElementById('yo').value;
	var cont=total;	
	if(total!=1){
		for(var h=0; h<=total-1; h++){			
			if(document.form1.ca[h].checked){
				CodEstudio=document.form1.ca[h].value;
				TipoEstu=document.form1.txtTipo[h].value;
				EnviarLaboratorios(CodEstudio,TipoEstu);
			}
			cont--;
		}
	}
	//
	if(total==1){
		//alert(total)
		if(document.form1.ca.checked){
			CodEstudio=document.form1.ca.value;
			TipoEstu=document.form1.txtTipo.value;
			EnviarLaboratorios(CodEstudio,TipoEstu);
		}
	}
}

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