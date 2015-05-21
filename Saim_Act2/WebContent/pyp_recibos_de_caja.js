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
/*function soloNumero(evt){
	var keyPressed = (evt.which) ? evt.which : event.keyCode;
	//alert(keyPressed);
	//patron = /\d/;//48 - 57 , 96 - 105
	//return patron.test(String.fromCharCode(keyPressed));
	//alert(!(keyPressed > 31 && (keyPressed < 48 || keyPressed > 57)));
	return !(keyPressed > 31 && (keyPressed < 48 || keyPressed > 57));
}*/
/************************************ACTUALIZAR RECIBO DE CAJA*****************************************************/
function BuscarReciboCaja(){
	var NumReciboCaja=document.getElementById("txtNumReciboCaja").value;
	 ajax=getXMLObject();
		ajax.open("POST","PYP_ControlEspecialidades",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if(ajax.readyState == 4){
				document.getElementById("contenido").innerHTML = ajax.responseText;
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=ARC&NumReciboCaja="+NumReciboCaja);
}
/*****************************FIN DE ACTUALIZAR RECIBO DE CAJA*****************************************************/
function CargarEgresos(){
	// var Concepto=document.getElementById("cmbConcepto").value;	
	 ajax=getXMLObject();
		ajax.open("POST","PYP_ControlEspecialidades",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if(ajax.readyState == 4){
				document.getElementById("contenido").innerHTML = ajax.responseText;
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=eg1");
	
}

function GuardarEgresos(){
	
	var FechaLetra=document.getElementById("txtFechaLetra").value;//
	var Concepto=document.getElementById("txtConcepto").value;//
	var Cantidad=document.getElementById("txtAbono").value;//
	var Observacion=document.getElementById("txtObservacion").value;//
	var ValorLetra=document.getElementById("txtValorLetra").value;//
	var Nombre=document.getElementById("txtNombre").value;//
	var Identificacion=document.getElementById("txtIdentificacion").value;//
	var NumCheque=document.getElementById("txtNumCons").value;
	var TipoPago="";
	var radioButtons = document.getElementsByName("radiobutton");
	//alert(radioButtons);
	for (var x = 0; x < radioButtons.length; x ++){
		if (radioButtons[x].checked){
			TipoPago=radioButtons[x].id;
			}
		}
	
	//alert(TipoPago)
	//TipoPago=TipoPago+" "+NumCons;
	/*if(TipoPago=="Cheque"){
		
	}*/
	
	var Codusuario=document.getElementById("txtCodusuario").value;
	 ajax=getXMLObject();
		ajax.open("POST","PYP_ControlEspecialidades",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if(ajax.readyState == 4){
				window.location.href("pyp_recibos_egreso.jsp");
				document.location.href = document.location.href;
				//alert();
				//document.getElementById("contenido").innerHTML = ajax.responseText;
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=eg2&Concepto="+Concepto+"&Cantidad="+Cantidad+"&Observacion="+Observacion+
				"&Codusuario="+Codusuario+"&FechaLetra="+FechaLetra+"&ValorLetra="+ValorLetra+
				"&TipoPago="+TipoPago+"&Nombre="+Nombre+"&Identificacion="+Identificacion+
				"&NumCheque="+NumCheque);
}
/*****************************************************************************************/
/*****************************************************************************************/
function VerOtro(){
	 var Concepto=document.getElementById("cmbConcepto").value;	
	 ajax=getXMLObject();
		ajax.open("POST","PYP_ControlEspecialidades",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if(ajax.readyState == 4){
				document.getElementById("Otros").innerHTML = ajax.responseText;
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=rc3.1&Concepto="+Concepto);
}
/////////////////////////////////////////////////////////////////////////////
function BuscarPaciente(){
	var TipoDocumento=document.getElementById("cmbTipoDoc").value;
	var NumeroDocumento=document.getElementById("txtNumDocumento").value;
	//var TotalPagar=document.getElementById("txtTotalPagar").value;
	var contenido=document.getElementById('contenido');
	var Codusuario=document.getElementById("txtCodusuario").value;
	if(TipoDocumento=="Seleccione"){alert("Seleccione El Tipo De Documento.")}
	if(NumeroDocumento==""){alert("Digite El Numero De Documento.")}
	//alert("TipoDocumento "+TipoDocumento+" NumeroDocumento "+NumeroDocumento);
	if((TipoDocumento!="Seleccione")&&(NumeroDocumento!="")){
	ajax=getXMLObject();
	ajax.open("POST","PYP_ControlEspecialidades",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			 var valor=ajax.responseText;	
			//alert(valor)
			 contenido.innerHTML = valor;
			 
			// document.getElementById("cmbTipoDoc").disabled=true;
			// document.getElementById("txtNumDocumento").disabled=true;
			// document.getElementById("txtTotalPagar").disabled=true;
			// NumeroDocumento=;
			// TotalPagar=;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=rc1&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento+"&Codusuario="+Codusuario);
	//"&TotalPagar="+TotalPagar+
}
}
function NuevoRc(){
	document.location.href = document.location.href;
}


function ActualizarReciboCaja(){
	var Abono=document.getElementById("txtAbono").value
	var ValorOrden=document.getElementById("txtValorOrden").value;	
	var CuotaModeradora=document.getElementById("txtCuotaModeradora").value;
	var NumOrden=document.getElementById("txtNumOrden").value;
	var TotalPagar=document.getElementById("txtTotalPagar").value;	
    var ValorLetra=document.getElementById("txtValorLetra").value ;  
    var concepto=document.getElementById('cmbConcepto').value;
    var tpago=document.getElementById('tpago').value;
   
    var CodReciboCajaPrincipal=document.getElementById("txtCodReciboCajaPrincipal").value;
    var CodReciboCaja=document.getElementById("txtCodReciboCaja").value;
    var ValorCheCons=document.getElementById("txtValorCheCons").value;
    var ValoraRecibir=0;
    if((Abono!="")&&(ValorOrden=="")){
		ValorOrden="0";
		ValoraRecibir=Abono;
	}
	if((ValorOrden!="")&&(Abono=="")){
		Abono=ValorOrden;
		ValoraRecibir=ValorOrden;
	}
	
	
				ajax=getXMLObject();
					ajax.open("POST","PYP_ControlEspecialidades",true); //getname will be the servlet name
					ajax.onreadystatechange  = function(){
						if(ajax.readyState == 4){
							window.location.href("pyp_recibos_de_caja_actualizar.jsp");
							document.location.href = document.location.href;
							//CodDetRecCaj = ajax.responseText;
							//ImprimirReciboCaja(CodDetRecCaj)
						}
					};
					ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=AcRC&Abono="+Abono+"&ValorLetra="+ValorLetra+
							"&CuotaModeradora="+CuotaModeradora+"&ValorOrden="+ValorOrden+"&NumOrden="+NumOrden+
							"&TotalPagar="+TotalPagar+"&ValoraRecibir="+ValoraRecibir+"&CodReciboCajaPrincipal="+CodReciboCajaPrincipal+
							"&CodReciboCaja="+CodReciboCaja+"&ValorCheCons="+ValorCheCons+"&concepto="+concepto+"&tpago="+tpago);
				}


function GuardarReciboCaja(){
	var TipoPago="";
	var Abono=document.getElementById("txtAbono").value
	var ValorOrden=document.getElementById("txtValorOrden").value="0";	
	var ValorRecibidoPleno=document.getElementById("txtValorRecibidoPleno").value;
	var ValoraRecibirPleno=document.getElementById("txtValoraRecibirPleno").value;
	var ValorPendientePleno=document.getElementById("txtValorPendientePleno").value;;	
	var ValoraRecibir=document.getElementById("txtValoraRecibir").value;
	var ValorRecibido=document.getElementById("txtValorRecibido").value;
	var ValorPendiente=document.getElementById("txtValorPendiente").value;;
	var ValCmb=document.getElementById("cmbConcepto").value;
	var Concepto=document.getElementById("cmbConcepto").selectedIndex;
	Conceptos=document.getElementById("cmbConcepto").options[Concepto].text;
	var CodCita=document.getElementById("txtCodCita").value;	
	var FechaLetra=document.getElementById("txtFechaLetra").value;
	var CodReciboCaja=document.getElementById("txtCodReciboCaja").value;
    var ValorLetra=document.getElementById("txtValorLetra").value ;  	
	var Otro=document.getElementById("txtOtro").value
	var NumCons=document.getElementById("txtNumCons").value;
	var CodPac=document.getElementById("txtCodPac").value;
	var CuotaModeradora=document.getElementById("txtCuotaModeradora").value;
	var Codusuario=document.getElementById("txtCodusuario").value;	
	var NumOrden=document.getElementById("txtNumOrden").value="-";
	var TotalPagar=document.getElementById("txtTotalPagar").value;
	var Observacion=document.getElementById("txtObservacion").value;
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++){if (radioButtons[x].checked){TipoPago=radioButtons[x].id;}}
	TipoPago=TipoPago+" "+NumCons;	
	var cheqconsig="";
	//alert(TipoPago);
	document.getElementById("txtNumOrden").value="-";
	if((Abono=="")&&(ValorOrden=="")){
		alert("Debe Digitar El Valor del Servicio\ny/o El Valor de la Orden");
		document.getElementById("txtAbono").focus();
			
	}else{
		if(TipoPago==" "){
			alert("Seleccione Tipo de Pago ");
		}else{
			if(ValCmb=="Seleccione"){
				alert("Seleccione el Tipo de Concepto");
			}else{				
				if((ValorOrden!="")&&(NumOrden=="")){
					
					alert("Escriba el Numero de la Orden.");
					document.getElementById("txtNumOrden").focus();
					//ValoraRecibir=ValorOrden;
				}else{
					if(CuotaModeradora==""){
						CuotaModeradora=0;
					}					
					if((Abono!="")&&(ValorOrden=="")){
						ValorOrden="0";
						ValoraRecibir=Abono;
					}
					if((ValorOrden!="")&&(Abono=="")){
						Abono=ValorOrden;
						ValoraRecibir=ValorOrden;
					}
					if(TipoPago!="Efectivo "){
						//alert("Sin efectivo ")
						cheqconsig=Abono;
						Abono="0"
					}else{
						//alert("EFEctivo --- ");
					}
					Concepto=Conceptos+" "+Otro;

				ajax=getXMLObject();
					ajax.open("POST","PYP_ControlEspecialidades",true); //getname will be the servlet name
					ajax.onreadystatechange  = function(){
						if(ajax.readyState == 4){
							window.location.href("pyp_recibos_de_caja.jsp");
							document.location.href = document.location.href;
							CodDetRecCaj = ajax.responseText;
							ImprimirReciboCaja(CodDetRecCaj)
						}
					};
					ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=rc2&Abono="+Abono+"&ValoraRecibir="+ValoraRecibir+"&ValorRecibido="+ValorRecibido+
							"&ValorPendiente="+ValorPendiente+"&FechaLetra="+FechaLetra+"&CodReciboCaja="+CodReciboCaja+
							"&ValorLetra="+ValorLetra+"&Codusuario="+Codusuario+"&TipoPago="+TipoPago+"&Concepto="+Concepto+
							"&ValorPendientePleno="+ValorPendientePleno+"&ValorRecibidoPleno="+ValorRecibidoPleno+
							"&ValoraRecibirPleno="+ValoraRecibirPleno+"&CodCita="+CodCita+"&CodPac="+CodPac+
							"&CuotaModeradora="+CuotaModeradora+"&ValorOrden="+ValorOrden+"&NumOrden="+NumOrden+
							"&TotalPagar="+TotalPagar+"&cheqconsig="+cheqconsig+"&Observacion="+Observacion);
				}
			}
		}		
	}
}

function DatoTiPa(){
	var TipoPago="";
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++){if (radioButtons[x].checked){TipoPago=radioButtons[x].id;}}
	
	
	var subrecibos=document.getElementById("TipoPago");
	ajax=getXMLObject();
	ajax.open("POST","PYP_ControlEspecialidades",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText)
 			subrecibos.innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=rc3.2&TipoPago="+TipoPago);
}

function VerRecibos(CodRecCaj){
	//alert("VerRecibos("+CodPac+")");
	var subrecibos=document.getElementById("subrecibos");
	ajax=getXMLObject();
	ajax.open("POST","PYP_ControlEspecialidades",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if(ajax.readyState == 4){
 			subrecibos.innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=rc3&CodRecCaj="+CodRecCaj);
}

function ImprimirReciboCaja(CodDetRecCaj){
	//alert("ImprimirReciboCaja("+CodDetRecCaj+")")
	pagina="pyp_ReciboCaja.jsp?CodDetRecCaj="+CodDetRecCaj;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function ImprimirReciboEgreso(CodEgr){
	//alert("ImprimirReciboCaja("+CodDetRecCaj+")")
	pagina="pyp_ReciboEgreso.jsp?CodEgr="+CodEgr;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function FinalAtencionCE(){
	var opcion=confirm("Desea Finalizar Esta Atencion?");
	var CodHorarioMedico=document.getElementById("CodHorarioMedico").value;
		if(opcion){
			ajax=getXMLObject();
			ajax.open("POST","PYP_ControlAsignarCita",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if(ajax.readyState == 4){
					alert(ajax.responseText);
					window.location.href("pyp_ListaMedico.jsp");
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=28&CodHorarioMedico="+CodHorarioMedico); //Posting txtname to Servlet
		}
		else{
		}
	}

function Letras3(){
	var Abono=document.getElementById("txtAbono").value;
	var contenido=document.getElementById("ValorLetras");

	if(Abono==""){
		contenido.innerHTML ="";
	}else{
		//alert("else "+Abono);
		ajax=getXMLObject();
		ajax.open("POST","PYP_ControlEspecialidades",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if(ajax.readyState == 4){
				//alert();
				//contenido.innerHTML = ajax.responseText;
				document.getElementById("txtValorLetra").value=ajax.responseText;
				
				//window.location.href("agm_ListaMedico.jsp");
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=let1&Abono="+Abono); //Posting txtname to Servlet
	}
}
function Letras(){
	var Abono=document.getElementById("txtAbono").value;
	var contenido=document.getElementById("ValorLetras");

	if(Abono==""){
		contenido.innerHTML ="";
	}else{
		//alert("else "+Abono);
		ajax=getXMLObject();
		ajax.open("POST","PYP_ControlEspecialidades",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if(ajax.readyState == 4){
				//alert();
				contenido.innerHTML = ajax.responseText;
				document.getElementById("txtValorOrden").value="0";
				
				
				//window.location.href("agm_ListaMedico.jsp");
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=let&Abono="+Abono); //Posting txtname to Servlet
	}
}
function Letras1(){
	var Abono=document.getElementById("txtValorOrden").value;
	var contenido=document.getElementById("ValorLetras");

	if(Abono==""){
		contenido.innerHTML ="";
	}else{
		//alert("else "+Abono);
		ajax=getXMLObject();
		ajax.open("POST","PYP_ControlEspecialidades",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if(ajax.readyState == 4){
				//alert();
				contenido.innerHTML = ajax.responseText;
				document.getElementById("txtAbono").value="0";;
				//window.location.href("agm_ListaMedico.jsp");
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=let&Abono="+Abono); //Posting txtname to Servlet
	}
}

/**********************************************************************************************/

/**********************************************************************************************/

