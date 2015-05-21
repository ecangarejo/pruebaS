var peticion = null;
var elementoSeleccionado = -1;
var sugerencias1 = null;
var cacheSugerencias = {};
/*funci�n utilizada para realizar la petici�n asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	/*funci�n para autocompletar*/
	function autocompleta_nombre() {
		
		//alert();
		
	    var elEvento = arguments[0] || window.event;
	   
	    var tecla = elEvento.keyCode;
	    
	                if(tecla == 13) { // ENTER o Intro
	                   
	                    seleccionaElemento_nombre();
	                }else {
	                    var texto = document.getElementById("txtnomexam").value;
	                    // Si es la tecla de borrado y el texto es vac�o, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                        borraLista();
	                        return;
	                    }
	                    if(cacheSugerencias[texto] == null) {
	                        peticion = inicializa_xhr();
	                        peticion.onreadystatechange = function() {
	                                    if(peticion.readyState == 4) {
	                                        if(peticion.status == 200) {
	                                                sugerencias1 = eval('('+peticion.responseText+')');
	                                                if(sugerencias1.length == 0) {
	                                                	sinResultados_nombre();
	                                                }else {
	                                                    cacheSugerencias[texto] = sugerencias1;
	                                                    actualizaSugerencias_nombre();
	                                                }
	                                            }
	                                        }
	                                    };
	                              //  var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/ControlBuscarUsu";
	                                //peticion.open("POST",cad, true); 
	                                var txtAccion="2";  
	                                peticion.open("GET","ControlCitasExamen?z="+txtAccion+"&nombre="+texto+"",true);                                 
	                        //peticion.open('POST', 'http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_presupuesto_servlet', true);
	                       // peticion.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	                        //var querys = "txtAccion="+encodeURIComponent(26)+"&txtCodigoReferencia="+encodeURIComponent(texto)+"&nocache="+Math.random();
	                       // peticion.send(querys);
	     	                       peticion.send("");
	                    } else {
	                        sugerencias1 = cacheSugerencias[texto];
	                        actualizaSugerencias_nombre();
	                    }
	                }
	}
	function sinResultados_nombre() {
	    document.getElementById("sugerencias1").innerHTML = "No existe examen";
	    document.getElementById("sugerencias1").style.display = "block";
	    //document.getElementById("bttnAdicionarFila").disabled = true;
	}

	function actualizaSugerencias_nombre() {
		elementoSeleccionado = -1;
		muestraSugerencias_nombre();
		
		  
		}

	/*funci�n que muestra las sugerencias de codigos de referencia de presupuestos*/
	function muestraSugerencias_nombre() {
	    var zonaSugerencias = document.getElementById("sugerencias1");
	        zonaSugerencias.innerHTML = sugerencias1.formateaLista_nombre();
	        zonaSugerencias.style.display = 'block';

	       
	       
	}

	/*prototypo de la lista quesale en el div de sugerencias*/
	Array.prototype.formateaLista_nombre = function() {
	    var codigoHtml = "";
	    codigoHtml = "<ul  class = \"scrollbox\" size='80' >";
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\">'+this[i]+'<\/li>';
	            }else {
	                codigoHtml += '<li onclick="clicksugerencia_nombre(this.innerHTML);">'+this[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";

	    return codigoHtml;
	   
	};

	/*funci�n en el cual se le asigna a la caja de texto la opci�n selccionada de las sugerencias*/
	function seleccionaElemento_nombre() {
	
	
	
	    if(sugerencias1[elementoSeleccionado]) {
		    var kitty=sugerencias1[elementoSeleccionado];
             
            
		   var resul=kitty;
		   var ced;
	     	/*var y=kitty.split("|").length;
	     	var z=kitty.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		    resul=z[0];
		    //ced=z[1];
		    // form1.cbArea.value();
		    }
	     	alert(resul);*/
	    	
	       document.getElementById("txtnomexam").value = resul;
	      
	       
	        borraLista_nombre();
	       
	    }
	}
	function borraLista_nombre() {
        document.getElementById("sugerencias1").innerHTML = "";
        document.getElementById("sugerencias1").style.display = "none";
     
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
 	var y=choice.split("|").length;
 	var z=choice.split("|");		     	
 	for(x=0; x<y-1; x=x+1)
 	{ 
    resul=z[0];
    ced=z[1];
    // form1.cbArea.value();
    }
    

 	document.getElementById("txtnomexam").value = resul; 
 	document.getElementById("txtcodexamen").value=ced
//document.getElementById("cedula").value = ced; 

borraLista_nombre();
document.getElementById("txtnomexam").focus();



}

