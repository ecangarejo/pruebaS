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
/////////////////////////////////////////////////////////////////////////////////////////////////

function focus(h){
	h.focus();
}

function fecha_ingreso(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  
	  var dia = time1.getDate()

	  var temp1 = "" + ((dia < 10) ? "0" : "") + dia
	  temp1 += ((mes < 10) ? "/0" : "/") + mes
	  temp1 += ((año < 10) ? "/0" : "/") + año
	  form1.txtFecha.value = temp1
	 id = setTimeout("fecha_ingreso()",1000)
	  
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

function llenar(){
		ajaxFunctionTraslado();
		}

/////////////////////////////////////////////////////////////////////////////////////////////////
	function ajaxFunctionTraslado() {
		  if(xmlhttp) { 	 
		    xmlhttp.open("POST","ControlTrasladoLlenar?va=1",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarPeticionTraslado;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		}	
////////////////////////////////////////////////////////////////////////////////////////////////
function ComprobarPeticionTraslado() {
		   if (xmlhttp.readyState == 4) {	   
			if(xmlhttp.status == 200) {
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");		     	
		     	form1.cmbarea.length=y;
		     	form1.cmbareatr.length=y;
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			     form1.cmbarea.options[x+1].text=z[x];
			     form1.cmbareatr.options[x+1].text=z[x];
			    }		    		
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
			
		   }
		   
		}
/////////////////////////////////////////////////////////////////////////////////////////////////
function ajaxtraslado(h) {
		  if(xmlhttp) { 
			  var x=h.cmbarea.selectedIndex;
			  x=h.cmbarea.options[x].text;
			xmlhttp.open("POST","ControlTrasladoLlenar?x="+x+"&va=2",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarSubareaTraslado;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }
		}
///////////////////////////////////////////////////////////////////////////////////////////////
function ComprobarSubareaTraslado() {
		
		   if (xmlhttp.readyState == 4) {
			   if(xmlhttp.status == 200) {
			   	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");
		       	form1.cmbsubarea.length=y;
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			     form1.cmbsubarea.options[x+1].text=z[x];			     
			    }
		     	form1.cmbsubarea.selectedIndex=0;
		    		

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
///////////////////////////////////////////////////////////////////////////////////////////////
	function ajaxtrasladocama(h) {
		  if(xmlhttp) { 
			  var xx=h.cmbarea.selectedIndex;
			  xx=h.cmbarea.options[xx].text;
			  var y=h.cmbsubarea.selectedIndex;
			  y=h.cmbsubarea.options[y].text;
		    xmlhttp.open("POST","ControlTrasladoLlenar",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange=function() {
				if (xmlhttp.readyState==4) {
					document.getElementById('cama').innerHTML = xmlhttp.responseText;
				}
			}
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send("va=3&y="+y); //Posting txtname to Servlet
			  }
		}
/////////////////////////////////////////////////////////////////////////////////////////////////
	function Comprobarxtraslado() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");
		     	form1.cmbcama.length=y;
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			     form1.cmbcama.options[x+1].text=z[x];
			   
			    }
		     	form1.cmbcama.selectedIndex=0;
		     	 	
		    	  
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		   
		}
//////////////////////////////////////////////////////////////////////////////////////
function VerificarPaciente(){
		if(xmlhttp) { 
			//var s=h.cmbcama.selectedIndex;
		//	var cama=h.cmbcama.options[s].text;
			var CodCama=document.getElementById("cmbcama").value;
			//alert(CodCama);
		    xmlhttp.open("POST","ControlTrasladoLlenar?va=4&cama="+CodCama,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = CoVP;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }
	}//fin de verificar index

	function CoVP() {
		   if (xmlhttp.readyState == 4) {
			   if(xmlhttp.status == 200) {
			   	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");
			     form1.txtnombretr.value=z[0];
			     form1.txtapellidotr.value=z[1];
			     form1.txtcedulatr.value=z[2];
			    // form1.txtcodigoCamVieja.value=z[3];
			     form1.txtcodigoAdmin.value=z[3];
			   }  else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		   
		}


//////////////////////////////////////ACTUALIZAR COMBOS////////////////////////////////////

function ajaxFunctionTrasladoActualizar() {
		  if(xmlhttp) { 	 
		    xmlhttp.open("POST","ControlTrasladoLlenar?va=5",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarPeticionTrasladoActualizar;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		}	
////////////////////////////////////////////////////////////////////////////////////////////////
function ComprobarPeticionTrasladoActualizar() {
		   if (xmlhttp.readyState == 4) {	   
			if(xmlhttp.status == 200) {
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");		     	
		     	form1.cmbareatr.length=y;
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			     form1.cmbareatr.options[x+1].text=z[x];
			    }
		     	
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
			
		   }
		   
		}

/////////////////////////////////////////////////////////////////////////////////////////////////
function ajaxtrasladoActualizar(h) {
		  if(xmlhttp) { 
			  var xa=h.cmbareatr.selectedIndex;
			  xa=h.cmbareatr.options[xa].text;
			xmlhttp.open("POST","ControlTrasladoLlenar?xa="+xa+"&va=6",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarSubareaTrasladoActualizar;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }
		}
///////////////////////////////////////////////////////////////////////////////////////////////
function ComprobarSubareaTrasladoActualizar() {
		
		   if (xmlhttp.readyState == 4) {
			   if(xmlhttp.status == 200) {
			   	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");
		       	form1.cmbsubareatr.length=y;
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			     form1.cmbsubareatr.options[x+1].text=z[x];
			    }
		     	form1.cmbsubareatr.selectedIndex=0;
		    		

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
///////////////////////////////////////////////////////////////////////////////////////////////
function ajaxtrasladocamaActualizar(h) {
	  if(xmlhttp) { 
		 
		  var ya=h.cmbsubareatr.selectedIndex;
		  ya=h.cmbsubareatr.options[ya].text;
		 // alert(ya);
	    xmlhttp.open("POST","ControlTrasladoLlenar",true); //getname will be the servlet name
	    xmlhttp.onreadystatechange=function() {
			if (xmlhttp.readyState==4) {
				document.getElementById('camatr').innerHTML = xmlhttp.responseText;
				//alert(xmlhttp.responseText);
			}
		}
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send("va=7&ya="+ya); //Posting txtname to Servlet
		  }
	  
	}
/////////////////////////////////////////////////////////////////////////////////////////////////
/*function ComprobarxtrasladoActualizar() {
	
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
	     	
	     	var a=xmlhttp.responseText;
	     	var y=a.split("_").length;
	     	var z=a.split("_");
	     	form1.cmbcamatr.length=y;
	        var h,ss;
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		     form1.cmbcamatr.options[x+1].text=z[x];
		    }
	     	form1.cmbcamatr.selectedIndex=0;
	    		

	     }
	     else {
	        alert("Error during AJAX call. Please try again");
	     }
	   }
	}*/


		/*function ajaxcodigoAdmision(h) {
			  if(xmlhttp) { 
				  var Cam=h.cmbcamatr.selectedIndex;
				  var CamaNueva=null;
				  CamaNueva=h.cmbcamatr.options[Cam].text;
				  var cedula=h.txtcedulatr.value;		  
					xmlhttp.open("POST","ControlTrasladoLlenar?va=0&cedula="+cedula+"&CamaNueva="+CamaNueva,true);
				    xmlhttp.onreadystatechange  = ComprobarCodigoAdmision;
				    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				    xmlhttp.send(""); //Posting txtname to Servlet
				  }
		  
		}*/
		
		function ComprobarCodigoAdmision() {

	 if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			 var a=xmlhttp.responseText;
			 
			  	var y=a.split("_").length;
			  	var x=a.split("_");
			  	 form1.txtcodigoAdmin.value=x[0];
			  	form1.txtcodigoCamNueva.value=x[1];

	   }
	   else {
	      alert("Error during AJAX call. Please try again");
	   }
	 }
	}

function TrasladarPaciente(){
	//var s=h.cmbcamatr.selectedIndex;
	//var camaOc=h.cmbcamatr.options[s].text;
	//var ss=h.cmbcama.selectedIndex;
	//var camaAc=h.cmbcama.options[ss].text;
	//var cedula=h.txtcedulatr.value;
	//var codigoNueva=h.txtcodigoCamNueva.value;
	var codigoNueva=document.getElementById("cmbcamatr").value;
	var codigoVieja=document.getElementById("cmbcama").value;
	//var codigoVieja=h.txtcodigoCamVieja.value;
	var codigoAd=document.getElementById("txtcodigoAdmin").value;
	var fecha=document.getElementById("txtFecha").value;
	var hora=document.getElementById("txtHora").value;
	var usuario=document.getElementById("txtUsuario").value;
	if(codigoNueva==""){
		alert("Falta Seleccionar La Cama Destino!!!")
	}else{
		if(codigoVieja==""){
			alert("Falta Seleccionar La Ubicacion Del Paciente!!!")
		}else{
			if(codigoAd==""){
				alert("No Se Ha Seleccionado Ningun paciente!!!")
			}
		}
	}
	
	if((codigoNueva!="")&&(codigoVieja!="")&&(codigoAd!="")){		

	//window.location.href="ControlTrasladoLlenar?usuario="+usuario+"&hora="+hora+"&camaOc="+camaOc+"&camaAc="+camaAc+"&codigoNueva="+codigoNueva+"&codigoVieja="+codigoVieja+"&codigo="+codigoAd+"&fecha="+fecha;
	//alert("Traslado Exitoso!!!")
	
	  ajax=getXMLObject();
	   ajax.open("POST","ControlTrasladoLlenar",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					//document.getElementById('FormatoMultiple').innerHTML = ajax.responseText;
					var val=ajax.responseText;
					if(val==""){
						alert("Traslado Exitoso!");
						window.location.href="Adm_traslado.jsp";
					}else{
						alert(val);
					}
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("va=tr&usuario="+usuario+"&hora="+hora+"&codigoNueva="+codigoNueva+"&codigoVieja="+codigoVieja+"&codigo="+codigoAd+"&fecha="+fecha); //Posting txtname to Servlet
		
	}	
 
}//fin de trasladar paciente


