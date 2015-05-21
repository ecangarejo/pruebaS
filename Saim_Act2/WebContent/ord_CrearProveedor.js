
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

function Proveedor(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlProve",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Proveedor");

}

function MProveedor(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlProve",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=MVerprove");
}

function CargarProve(iden){
	var contenido=document.getElementById('contenido');
	document.getElementById('contenido').innerHTML='<p>Cargando Listado de Proveedores.... </p><img src="Imagenes/ani.gif">';
	ajax=getXMLObject();
	ajax.open("POST","ControlProve",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=MProveedor&iden="+iden);
}

function autocompletaBanco(){
	var NomBanco=document.getElementById("txtBanco").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlProve",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			document.getElementById("banco").innerHTML=ajax.responseText;
			var Contador=document.getElementById("txtCont").value;
			if(Contador=="1"){
				var CodBanco=document.getElementById("txtCodBanco").value;
				var NombrBanco=document.getElementById("txtNombBanco").value;
				SeleccionarBanco(CodBanco,NombrBanco)
				//NomBanco=document.getElementById("txtNombBanco").value;
			}
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=abc&NomBanco="+NomBanco); //Posting txtname to Servle

}


function validanit(){

	var contenido=document.getElementById('contenido');

	var sig=document.getElementById('txtNit').value;
	
	var tc=document.getElementById('tc').value;
	var tc1=document.getElementById('tc').selectedIndex;
	var tc2=document.getElementById('tc')[tc1].text;

	if(tc2=="NI"){
		
		
		if ((sig>0)||(sig<999999999999)) {
			if(sig.length==14)sig="0"+sig;
			if(sig.length==13)sig="00"+sig;
			if(sig.length==12)sig="000"+sig;
			if(sig.length==11)sig="0000"+sig;
			if(sig.length==10)sig="00000"+sig;
			if(sig.length==9)sig="000000"+sig;
			if(sig.length==8)sig="0000000"+sig;
			if(sig.length==7)sig="00000000"+sig;
			if(sig.length==6)sig="000000000"+sig;
			if(sig.length==5)sig="0000000000"+sig;
			if(sig.length==4)sig="00000000000"+sig;
			if(sig.length==3)sig="000000000000"+sig;
			if(sig.length==2)sig="0000000000000"+sig;
			if(sig.length==1)sig="00000000000000"+sig;
			if(sig.length<1){
				alert("El numero de documento es incorrecto");
			}else{
				
				var a=sig.substring(0,1);
				var b=sig.substring(1,2);
				var c=sig.substring(2,3);
				var d=sig.substring(3,4);
				var e=sig.substring(4,5);
				var f=sig.substring(5,6);
				var g=sig.substring(6,7);
				var h=sig.substring(7,8);
				var i=sig.substring(8,9);
				var j=sig.substring(9,10);
				var k=sig.substring(10,11);
				var l=sig.substring(11,12);
				var m=sig.substring(12,13);
				var n=sig.substring(13,14);
				var o=sig.substring(14,15);
				
			 var suma=a*71+b*67+c*59+d*53+e*47+f*43+g*41+h*37+i*29+j*23+k*19+l*17+m*13+n*7+o*3;
			 var r=suma%11;
			 var dv=0;
			 if(r==0){
			  dv=0;
			 }else{
			  if(r==1){
			  dv=1;
			  }else{
			   dv=11-r;
			  }
			 }
			document.getElementById("dv").value=dv;
				
			
			
			ajax=getXMLObject();
			ajax.open("POST","ControlNomina",true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					//contenido.innerHTML = ajax.responseText	
					//OpcionN();
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			//ajax.send("valor=5.1&usu="+usu+"&nom="+nom+"&sig="+sig);
			}	
		
			
			
		
		}else{
			alert("Los numeros de documentos NIT solo aceptan valores númericos!!!");
			document.getElementById('txtNit').value = "";
			document.getElementById('txtNit').focus();
			document.getElementById('txtNit').focus();
	
		}//solo numeros
	}//tc2=ni
}





function SeleccionarBanco(CodBanco,NombrBanco){
	document.getElementById("txtBanco").value=NombrBanco;
	document.getElementById("txtCodBanco").value=CodBanco;
	document.getElementById("banco").style.display = "none";
}


function CrearProveedor(){
	var tc1=document.getElementById('tc').selectedIndex;
	var tc=document.getElementById('tc')[tc1].text;	//tipo de identificacion
	var nit=document.getElementById('txtNit').value;
	var dv=document.getElementById("dv").value; // digito de verificacion 
	var pais=document.getElementById('cmbPais').value;
	var ciudad=document.getElementById('cmbDpto').value;
	var muni=document.getElementById('cmbMuni').value;
	var telefono=document.getElementById('txtTelefono').value;
	var fax=document.getElementById('txtFax').value;
	var email=document.getElementById('txtEmail').value;
	var contacto=document.getElementById('txtContacto').value;
	var telcontacto=document.getElementById("telconta").value;
	var codbanco=document.getElementById("txtCodBanco").value;
	var nbanco=document.getElementById("txtBanco").value;
	var nombrebanco=encodeURIComponent(nbanco);
	var diaspla=document.getElementById("diap").value;
	var estado=document.getElementById("estad").value;
	var clase=document.getElementById('cmbClase').value;
	var razon=document.getElementById('txtRazon').value;
	var direccion=document.getElementById('txtDireccion').value;
	var observacion=document.getElementById('txtDescripcion').value;
	var formapago=document.getElementById("fp").value;
	var user=document.getElementById("txtCodusuario").value;
	/*var cta="";
	if(formapago==3){
		alert("entrando !");
		cta=document.getElementById("txtFormaPago").value;
		
	}*/
	var aecon=document.getElementById("txtActEco").value;
	//alert(" ! "+aecon);
	var rfte=document.getElementById("codfte").value;
	var ica=document.getElementById("codica").value;
	//alert(cta);
	var ctaD=document.getElementById("txtFormaPago").value;
	if(razon==""){
		alert("Debe Digitar la Razon Social.");
	}else{
	 if(nit==""){
		alert("Digite el NIT del Proveedor.");
	 }else{
	   if(telefono==""){
		alert("Debe Digitar El telefono del Proveedor.");
      }else{
    	  if(pais=="Seleccione"){
    		alert("Debe Escoger la Nacionalidad del Proveedor.");
	   }else{
		   if(ciudad=="Seleccione"){
				alert("Debe Escoger la Ciudad.");
	    }else{
	    	if(muni=="Seleccione"){
	    		alert("Debe Escoger El Municipio.");
	     }else{
	      if(contacto==""){
	    	alert("Debe Digitar El Nombre del Contacto.");
	      }else{
	       if(clase=="Seleccione"){
		    alert("Debe Escoger la Clase Contribuyente.");
	       }else{
	    	 if((formapago==3)&&(cta="")){
	    		 alert("No ha digitado un cuenta asociada a la transferencia de pago ");
	    	 }else{
	    		 if(rfte==""){
	    		 	 alert("Debe digitar el valor de la Retefuente ");
	    		 }else{
	    		     if(ica==""){
	    		      alert("Debe digitar el valor del ICA ");
	    		     }else{
	    		    	 if(diaspla==""){
	    		    		 alert("No ha digitado los dias de plazo ");
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
	}
	//alert("valor=3&nit="+nit+"&pais="+pais+"&ciudad="+ciudad+"&muni="+muni+"&telefono="+telefono+"&fax="+fax+"&email="+email+"&contacto="+contacto+"&clase="+clase+"&razon="+razon+"&direccion="+direccion+"&observacion="+observacion+"&formapago="+formapago+"&aecon="+aecon+"&rfte="+rfte+"&ica="+ica+"&ctad="+ctaD+"&user="+user);
	if((nit!="")&&(razon!="")&&(telefono!="")&&(contacto!="")&&(pais!="Seleccione")&&(ciudad!="Seleccione")&&(muni!="Seleccione")&&(clase!="Seleccione")&&(rfte!="")&&(ica!="")&&(aecon!="")&&(diaspla!="")&&(ica!="")){
	ajax=getXMLObject();
	ajax.open("POST","ControlProve",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3&nit="+nit+"&pais="+pais+"&ciudad="+ciudad+"&muni="+muni+"&telefono="+telefono+"&fax="+fax+"&email="+email+"&contacto="+contacto+"&clase="+clase+"&razon="+razon+"&direccion="+direccion+"&observacion="+observacion+"&formapago="+formapago+"&aecon="+aecon+"&rfte="+rfte+"&ica="+ica+"&ctad="+ctaD+"&user="+user+"&tc="+tc+"&diaspla="+diaspla+"&nombrebanco="+nombrebanco+"&codbanco="+codbanco+"&telcontacto="+telcontacto+"&dv="+dv+"&estado="+estado);
 }
}



function Dpto(){
	
	var contenido=document.getElementById('dpto');
	var pais=document.getElementById("cmbPais").value;
	
		ajax=getXMLObject();
		    ajax.open("POST","ControlProve",true); //getname will be the servlet name
		     ajax.onreadystatechange  = function(){
		    	 if(ajax.readyState == 4){
		    		var aviso=ajax.responseText;		
		    		contenido.innerHTML = aviso;
					    		
		    	}		    	
		    }
		    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=1&pais="+pais); //Posting txtname to Servlet
	
}

function Muni(){
	
	var contenido=document.getElementById('Muni');
	var ciudad=document.getElementById("cmbDpto").value;
	
		ajax=getXMLObject();
		    ajax.open("POST","ControlProve",true); //getname will be the servlet name
		     ajax.onreadystatechange  = function(){
		    	 if(ajax.readyState == 4){
		    		var aviso=ajax.responseText;		
		    		contenido.innerHTML = aviso;
					    		
		    	}		    	
		    }
		    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=2&ciudad="+ciudad); //Posting txtname to Servlet
	
}


function Actualizarp(ni){

	var contenido=document.getElementById('contenido');
	//var ciudad=document.getElementById("cmbDpto").value;
	//alert("El nit es "+ni);
		ajax=getXMLObject();
		    ajax.open("POST","ControlProve",true); //getname will be the servlet name
		     ajax.onreadystatechange  = function(){
		    	 if(ajax.readyState == 4){
		    		var aviso=ajax.responseText;		
		    		contenido.innerHTML = aviso;   		
		    	}		    	
		    }
		    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=Actualizarp&ni="+ni); //Posting txtname to Servlet
	
}


function ModificarProveedor(codpp){
	var tc1=document.getElementById('tc').selectedIndex;
	var tc=document.getElementById('tc')[tc1].text;	//tipo de identificacion
	var nit=document.getElementById('txtNit').value;
	var dv=document.getElementById("dv").value; // digito de verificacion 
	var pais=document.getElementById('cmbPais').value;
	var ciudad=document.getElementById('cmbDpto').value;
	var muni=document.getElementById('cmbMuni').value;
	var telefono=document.getElementById('txtTelefono').value;
	var fax=document.getElementById('txtFax').value;
	var email=document.getElementById('txtEmail').value;
	var contacto=document.getElementById('txtContacto').value;
	var telcontacto=document.getElementById("telconta").value;
	var codbanco=document.getElementById("txtCodBanco").value;
	var nbanco=document.getElementById("txtBanco").value;
	var nombrebanco=encodeURIComponent(nbanco);
	var diaspla=document.getElementById("diap").value;
	var estado=document.getElementById("estad").value;
	var clase=document.getElementById('cmbClase').value;
	var razon=document.getElementById('txtRazon').value;
	var direccion=document.getElementById('txtDireccion').value;
	var observacion=document.getElementById('txtDescripcion').value;
	var codterc=document.getElementById("codterc").value;
	var codfp=document.getElementById("fp").value;
	var codact=document.getElementById("codaecon").value;
	
	//alert("nit: "+nit+" codpp:"+codpp);
	
	if(razon==""){
		alert("Debe Digitar la Razon Social.");
	}else{
	 if(nit==""){
		alert("Digite el NIT del Proveedor.");
	 }else{
	   if(telefono==""){
		alert("Debe Digitar El telefono del Proveedor.");
      }else{
    	  if(pais=="Seleccione"){
    		alert("Debe Escoger la Nacionalidad del Proveedor.");
	   }else{
		   if(ciudad=="Seleccione"){
				alert("Debe Escoger la Ciudad.");
	    }else{
	    	if(muni=="Seleccione"){
	    		alert("Debe Escoger El Municipio.");
	     }else{
	      if(contacto==""){
	    	alert("Debe Digitar El Nombre del Contacto.");
	      }else{
	       if(clase=="Seleccione"){
		    alert("Debe Escoger la Clase Contribuyente.");
	       }else{
	    	   if(codact==""){
	    		   alert("Debe Seleccionar una Actividad Economica.");
	    	   }else{
	    		   if(codfp==""){
	    			   alert("Debe Seleccionar una forma de Pago");
	    		   }else{
	    			   if(diaspla==""){
	    		    		 alert("No ha digitado los dias de plazo ");
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
	var codrfte="";
	var codica="";
	var valrfte="";
	var valica="";
	if(codact!=""){
		codrfte=document.getElementById("codfte").value;
		codica=document.getElementById("codica").value;
		valrfte=document.getElementById("fte").value;
		valica=document.getElementById("ica").value;
	}
	var codctapago="";
	//alert("codfp"+codfp);
	//alert("codprove "+codpp+" codterc "+codterc);
	if(codfp=="3"){
		codctapago=document.getElementById("cta").value;
	//alert(codctapago);
	}
	
	if((nit!="")&&(razon!="")&&(telefono!="")&&(contacto!="")&&(pais!="Seleccione")&&(ciudad!="Seleccione")&&(muni!="Seleccione")&&(clase!="Seleccione")&&(codact!="")&&(codfp!="")){
	 
		if((codact!="")&&((codrfte=="")||(codica==""))){
			alert("Debe ingresar el valor de los impuestos ");
		}else{
			if((codfp=="3")&&(codctapago=="")){
				alert("Su forma de pago es Transferencia, debe digitar una Cta ");
			}else{
				var user=document.getElementById("txtCodusuario").value;
			ajax=getXMLObject();
			ajax.open("POST","ControlProve",true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					alert(ajax.responseText);
					window.location.reload();
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=MP&nit="+nit+"&pais="+pais+"&ciudad="+ciudad+"&muni="+muni+"&telefono="+telefono+"&fax="+fax+"&email="+email+"&contacto="+contacto+"&clase="+clase+"&razon="+razon+"&direccion="+direccion+"&observacion="+observacion+"&codpp="+codpp+"&codact="+codact+"&codfp="+codfp+"&codica="+codica+"&codrfte="+codrfte+"&valrfte="+valrfte+"&valica="+valica+"&codctapago="+codctapago+"&codterc="+codterc+"&user="+user+"&ciudad="+ciudad+"&tc="+tc+"&diaspla="+diaspla+"&nombrebanco="+nombrebanco+"&codbanco="+codbanco+"&telcontacto="+telcontacto+"&dv="+dv+"&estado="+estado);
			
			}
		}
	}
}

function FormaPago(){
	ajax=getXMLObject();
	var fp=document.getElementById("fp").value;
	var contenido=document.getElementById("vfp");
	ajax.open("POST","ControlProve",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=FormaPago&fp="+fp);
}


function autoAecon(){
	
	
	ajax=getXMLObject();
	
	var texto=document.getElementById("aecon").value;
	var contenido=document.getElementById("veacon");
	ajax.open("POST","ControlProve",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=AutoAeco&texto="+texto);
}


function Aeco(codAeco){
	ajax=getXMLObject();
	var ctafp=document.getElementById("txtFormaPago").value;
	document.getElementById("veacon").innerHTML="";
	
	ajax.open("POST","ControlProve",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			
			var cadena=ajax.responseText;
			//alert(cadena);
			var elem=cadena.split('_');
	
			var caeco=elem[0];
			var cod_ciiu=elem[1];
			var nact=elem[2];
			var vcre=elem[3];
			
			var actividad="<table width='100%'><tr class='rep' align='center' bgcolor='#BADBFB' ><td width='14%'><b>Codigo CIIU</b></td><td width='50%'><b>Nombre de la Actividad</b></td><td width='9%'><b>Retefuente</b></td><td width='9%'><b>ICA</b></td><td width='9%'><b>CRE</b></td></tr>" +
					"<tr class='rep' ><td width='14%'>"+cod_ciiu+"</td><td width='50%'>"+nact+"</td><td width='9%'><input type='text'id='fte' onkeyup='Retefuente("+codAeco+")' size='4'/></td><td width='9%'><input tye='text' id='ica'  onkeyup='valica("+codAeco+")' size='4'/></td><td width='9%'>"+vcre+"</td></tr>"+
					"<tr><td colspan='2' width='64%'><input type='hidden' id='codfte' size='8'/><input type='hidden' id='codica'  size='8' /></td><td ><div id='vistafte'></div></td><td ><div id='vistaica'></div></td><td></td></tr></table>";
			//alert("valor de acteco"+codAeco);
			//alert("valor de caeco "+caeco);
			document.getElementById("veacon").innerHTML=actividad;
			document.getElementById("codaecon").value=codAeco;
			document.getElementById("aecon").value=nact;
			document.getElementById("txtActEco").value=caeco;
			document.getElementById("txtFormaPago").value=ctafp;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	//alert("valor=visualaeco&codaeco="+codAeco+"&ctafp="+ctafp);
	ajax.send("valor=visualaeco&codaeco="+codAeco+"&ctafp="+ctafp);
}


function Retefuente(codAeco){
	ajax=getXMLObject();
	var texto=document.getElementById("fte").value;
	var ctafp=document.getElementById("txtFormaPago").value;
	var cont=document.getElementById("vistafte");
	if((texto==0)||((texto>0)&&(texto<9.999999999999999999999999999999999999999999))){
		ajax.open("POST","ControlProve",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				cont.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Autorefte&texto="+texto);
		
		
	}else{
	   alert("Este campo solo admite digitos numericos ");
	   document.getElementById("fte").value="";
	}
	document.getElementById("txtActEco").value=codAeco;
	document.getElementById("txtFormaPago").value=ctafp;
	//alert("valor=Retefuente&codaeco="+codAeco+"&ctafp="+ctafp);
}

function Arfte(cod, valor){
	ajax=getXMLObject();
//	alert(valor);
	var ctafp=document.getElementById("txtFormaPago").value;
	var codAeco=document.getElementById("txtActEco").value;
	document.getElementById("fte").value=valor;
	document.getElementById("codfte").value=cod;
	document.getElementById("vistafte").innerHTML="";
	document.getElementById("ica").focus();
	document.getElementById("txtFormaPago").value=ctafp;
	//alert("valor=Arfte&codaeco="+codAeco+"&ctafp="+ctafp);
}

function Aica(cod, valor){
	ajax=getXMLObject();
	var ctafp=document.getElementById("txtFormaPago").value;
	var codAeco=document.getElementById("txtActEco").value;
	document.getElementById("ica").value=valor;
	document.getElementById("codica").value=cod;
	document.getElementById("vistaica").innerHTML="";
	document.getElementById("txtDescripcion").focus();
	document.getElementById("txtFormaPago").value=ctafp;
	document.getElementById("txtActEco").value=codAeco;
	//alert("valor=Aica&codaeco="+codAeco+"&ctafp="+ctafp);
}

function valica(codAeco){
	var texto=document.getElementById("ica").value;
	var ctafp=document.getElementById("txtFormaPago").value;
	var cont=document.getElementById("vistaica");
	if((texto==0)||((texto>0)&&(texto<9.999999999999999999999999999999999999999999))){
		ajax.open("POST","ControlProve",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				cont.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Autoica&texto="+texto);
		
		
	}else{
	   alert("Este campo solo admite digitos numericos ");
	   document.getElementById("fte").value="";
	}
	document.getElementById("txtActEco").value=codAeco;
	document.getElementById("txtFormaPago").value=ctafp;
	//alert("valor=valica&codaeco="+codAeco+"&ctafp="+ctafp);
	
}
function TempFormaPago(){
	ajax=getXMLObject();
	var ctafp=document.getElementById("cta").value;
	document.getElementById("txtFormaPago").value=ctafp;	
	
}


