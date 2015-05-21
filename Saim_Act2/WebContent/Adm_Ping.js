var peticion = null;
var elementoSeleccionado = -1;
var sugerencias = null;
var cacheSugerencias = {};
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	/*función para autocompletar*/
	function autocompleta_nombre() {
		
		//alert();
		
	    var elEvento = arguments[0] || window.event;
	   
	   // var tecla = elEvento.keyCode;
	    
	               // if(tecla == 13) { // ENTER o Intro
	                   
	                    //seleccionaElemento_nombre();
	               // }else {
	                    var texto = document.getElementById("cbeps").value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(texto == "") {
	                    	borraLista_nombre();
	                 //       return;
	                    }
	                    if(cacheSugerencias[texto] == null) {
	                        peticion = inicializa_xhr();
	                        peticion.onreadystatechange = function() {
	                                    if(peticion.readyState == 4) {
	                                        if(peticion.status == 200) {
	                                                sugerencias = eval('('+peticion.responseText+')');
	                                                if(sugerencias.length == 0) {
	                                                	sinResultados_nombre();
	                                                }else {
	                                                    cacheSugerencias[texto] = sugerencias;
	                                                    actualizaSugerencias_nombre();
	                                                }
	                                            }
	                                        }
	                                    };
	                              //  var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/ControlBuscarUsu";
	                                //peticion.open("POST",cad, true); 
	                                var txtAccion="2";  
	                                peticion.open("GET","ControlAutoCompletaEps?z="+txtAccion+"&nombre="+texto+"",true);                                 
	                        //peticion.open('POST', 'http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_presupuesto_servlet', true);
	                       // peticion.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	                        //var querys = "txtAccion="+encodeURIComponent(26)+"&txtCodigoReferencia="+encodeURIComponent(texto)+"&nocache="+Math.random();
	                       // peticion.send(querys);
	     	                       peticion.send("");
	                    } else {
	                        sugerencias = cacheSugerencias[texto];
	                        actualizaSugerencias_nombre();
	                    }
	                //}
	}
	function sinResultados_nombre() {
	    document.getElementById("sugerencias").innerHTML = "No existe examen";
	    document.getElementById("sugerencias").style.display = "block";
	    //document.getElementById("bttnAdicionarFila").disabled = true;
	}

	function actualizaSugerencias_nombre() {
		elementoSeleccionado = -1;
		muestraSugerencias_nombre();
		
		  
		}

	/*función que muestra las sugerencias de codigos de referencia de presupuestos*/
	function muestraSugerencias_nombre() {
	    var zonaSugerencias = document.getElementById("sugerencias");
	        zonaSugerencias.innerHTML = sugerencias.formateaLista_nombre();
	        zonaSugerencias.style.display = 'block';

	       
	       
	}

	/*prototypo de la lista quesale en el div de sugerencias*/
	Array.prototype.formateaLista_nombre = function() {
	    var codigoHtml = "";
	    codigoHtml = "<ul  class = \"scrollbox\" size='80' >";
	        for(var i=0; i<this.length; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\">'+this[i]+'<\/li>';
	            }else {
	                codigoHtml += '<li onclick="clicksugerencia_nombre(this.innerHTML);">'+this[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";

	    return codigoHtml;
	   
	};

	/*función en el cual se le asigna a la caja de texto la opción selccionada de las sugerencias*/
	function seleccionaElemento_nombre() {
	
	
	
	    if(sugerencias[elementoSeleccionado]) {
		    var kitty=sugerencias[elementoSeleccionado];
             
            
		   var resul=kitty;
		   var ced;
	     	/*var y=kitty.split("/").length;
	     	var z=kitty.split("/");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		    resul=z[0];
		    //ced=z[1];
		    // form1.cbArea.value();
		    }
	     	alert(resul);*/
	    	
	       document.getElementById("cbeps").value = resul;
	      
	       
	        borraLista_nombre();
	       
	    }
	}
	function borraLista_nombre() {
        document.getElementById("sugerencias").innerHTML = "";
        document.getElementById("sugerencias").style.display = "none";
     
}


	
	/*	function yosi_nombre() {
		getXMLObject();
		 
		  if(xmlhttp) {
			
			
			
			  var tipo,cedula;
				tipo="CC";
				
		        cedula=document.getElementById('cedula').value;
			xmlhttp.open("POST","lab_BusPac?z="+2+"&ced="+cedula+"&ti="+tipo+"",true); //getname will be the servlet name
			
			xmlhttp.onreadystatechange  = Resultado_nombre;
		   xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xmlhttp.send(""); //Posting txtname to Servlet
		
				 
		  }
		   var x;
		}
		function Resultado_nombre() {
		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
				
				document.getElementById('resultado').innerHTML = xmlhttp.responseText
				div = document.getElementById('resultado');
				
				div.style.display="";
				 r = document.getElementById('sugerencias');
					r.style.display='none';
		     	 
		     }
		     else {
		       // alert("Error during AJAX call. Please try again");
		     }
		   }
		
		 
		}
function validarcom_nombre(h,tecla, e){	
	
		
		tecla =   e.keyCode;  e.which;
	if(tecla == 13){ 
	    var tipo,cedula;
		tipo="CC";
	   
	        cedula=h.cedula.value;
	        if(cedula==""||tipo==""){
		   
	        }else{
	        	
	        	yosi_nombre();                               
	        		
	        	
	        }
		}
	}
	*/
function clicksugerencia_nombre(choice){
	
	
    var resul;
    var ced="";
    resul=choice;
 	var y=choice.split("_").length;
 	var z=choice.split("_");		     	
 	for(x=0; x<y-1; x=x+1)
 	{ 
    resul=z[0];
    ced=z[1];
    // form1.cbArea.value();
    }
    

 	document.getElementById("cbeps").value = resul; 
 	document.getElementById("txtcodentidad").value=ced
//document.getElementById("cedula").value = ced; 

borraLista_nombre();
document.getElementById("cbeps").focus();
}
/**************************************************************************************/
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

function BuscarPaciente(){
	var TipoDocumento = document.getElementById("cbtipodoc").value;
	var NumeroDocumento= document.getElementById("txtnumdocu").value;
	var DivResul = document.getElementById("cargaAmbu");
	if(TipoDocumento=="Seleccione"){alert("Seleccione El Tipo De Documento.")}
	if(NumeroDocumento==""){alert("Digite El Numero De Documento.")}
	
	if((TipoDocumento!="Seleccione")&&(NumeroDocumento!="")){
	ajax=getXMLObject();
	ajax.open("POST","ControlPing",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;
			DivResul.innerHTML = validar;
			/*if(validar=="1"){
				window.location.href="adm_IngresarDemografico.jsp";
			}else{
				DivResul.innerHTML = validar;
			//Coincidencias.innerHTML="";
			}*/
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("va=p1&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento);
}
	
}

function GuardarDemograficoCERP(){
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
	var TipoDoc=document.getElementById("cbtipodoc").value;
	var NumDocumento=document.getElementById("txtnumdocu").value;
	//var TipoDocumento = document.getElementById("cbtipodoc").value;
	//var NumeroDocumento= document.getElementById("txtnumdocu").value;
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
													//BuscarPacienteR();
													BuscarPaciente();
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

function GuardarAmbulatorio(){
	
	var TipoDocumento = document.getElementById("cbtipodoc").value;
	var NumeroDocumento= document.getElementById("txtnumdocu").value;
	var CodPac = document.getElementById("txtCodPac").value;
	var CodUsu=document.getElementById("CodUsu").value;
	/*if(Servicio=="Seleccione"){
		alert("Seleccione Unidad de Servicio");
		document.getElementById("cmbServicio").focus();
	}else{*/
	ajax=getXMLObject();
	ajax.open("POST","ControlPing",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;			
			alert(validar);			
			window.location.reload();
			document.getElementById("txtnumdocu").focus();
			/*if(validar=="1"){
				window.location.href("adm_IngresarDemografico.jsp");
			}else{
				DivResul.innerHTML = validar;
			//Coincidencias.innerHTML="";
			}*/
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("va=p2&CodPac="+CodPac+"&CodUsu="+CodUsu+"&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento);
	//}
}
