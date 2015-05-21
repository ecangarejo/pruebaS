var peticion = null;
var elementoSeleccionado = -1;
var sugerencias = null;
var cacheSugerencias = {};
/*funci�n utilizada para realizar la petici�n asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	/*funci�n para autocompletar cunado se digite el codigo del presupuesto*/
	function autocompleta_nombre() {
		
		//alert("llegooo");
		
		//Picture1.MousePointer = 99
	    var elEvento = arguments[0] || window.event;
	   
	    var tecla = elEvento.keyCode;
	    
	    
	       /* if(tecla == 40) { // Flecha Abajo
		       
	               if(elementoSeleccionado+1 < sugerencias.length) {
	                    elementoSeleccionado++;
	                }
	            muestraSugerencias_nombre();
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	                muestraSugerencias_nombre();
	            }else*/
	                if(tecla == 13) { // ENTER o Intro
	                   
	                    seleccionaElemento_nombre();
	                	//clicksugerencia(choice);
	                }else {
	                    var texto = document.getElementById("nombre").value;
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
	                                var txtAccion="27";  
	                                peticion.open("POST","ControlBuscarPacientes?txtAccion="+txtAccion+"&nombre="+texto+"",true);                                 
	                        //peticion.open('POST', 'http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_presupuesto_servlet', true);
	                       // peticion.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	                        //var querys = "txtAccion="+encodeURIComponent(26)+"&txtCodigoReferencia="+encodeURIComponent(texto)+"&nocache="+Math.random();
	                       // peticion.send(querys);
	     	                       peticion.send("");
	                    } else {
	                        sugerencias = cacheSugerencias[texto];
	                        actualizaSugerencias_nombre();
	                    }
	                }
	}
	function sinResultados_nombre() {
	    document.getElementById("sugerencias").innerHTML = "No existe usuario";
	    document.getElementById("sugerencias").style.display = "block";
	    //document.getElementById("bttnAdicionarFila").disabled = true;
	}

	function actualizaSugerencias_nombre() {
		elementoSeleccionado = -1;
		muestraSugerencias_nombre();
		
		  
		}

	/*funci�n que muestra las sugerencias de codigos de referencia de presupuestos*/
	function muestraSugerencias_nombre() {
	    var zonaSugerencias = document.getElementById("sugerencias");
	        zonaSugerencias.innerHTML = sugerencias.formateaLista_nombre();
	        zonaSugerencias.style.display = 'block';
 div = document.getElementById('resultado');
			
			div.style.display='none';
	       
	       
	}

	/*prototypo de la lista quesale en el div de sugerencias*/
	Array.prototype.formateaLista_nombre = function() {
	    var codigoHtml = "";
	    codigoHtml = "<ul class =\"scrollbox\"\"seleccionado\" size='80'>";
	        for(var i=0; i<this.length; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\">'+this[i]+'<\/li>';  
	            }else {
	                codigoHtml += '<li onclick="clicksugerencia_nombre(this.innerHTML);">'+this[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
 div = document.getElementById('resultado');
		
		div.style.display='none';
	    return codigoHtml;
	   
	};

	/*funci�n en el cual se le asigna a la caja de texto la opci�n selccionada de las sugerencias*/
	function seleccionaElemento_nombre() {
	
	
	
	    if(sugerencias[elementoSeleccionado]) {
		    var kitty=sugerencias[elementoSeleccionado];
             
            
		   var resul;
		   var ced;
	     	var y=kitty.split("|").length;
	     	var z=kitty.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		    resul=z[0];
		    ced=z[1];
		    // form1.cbArea.value();
		    }
	    	
	       document.getElementById("nombre").value = resul;
	       document.getElementById("cedula").value = ced;
	        borraLista_nombre();
	       
	    }
	}
	function borraLista_nombre() {
        document.getElementById("sugerencias").innerHTML = "";
        document.getElementById("sugerencias").style.display = "none";
     
}


	
	function yosi_nombre() {
		getXMLObject();
		 
		  if(xmlhttp) {
			
			
			  
			  var tipo,cedula,nombre;
				
			  document.getElementById('resultado').innerHTML='<p>Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
			  div = document.getElementById('resultado');
				
				div.style.display="";
			  
		        cedula=document.getElementById('cedula').value;
		        //nombre=document.getElementById('nombre').value;
		        tipo=document.getElementById('tipodoce').value;
		        //alert(tipo);
			xmlhttp.open("POST","lab_BusPac?z="+2+"&ced="+cedula+"&ti="+tipo+"",true); //getname will be the servlet name
			
			xmlhttp.onreadystatechange  = Resultado_nombre;
		   xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xmlhttp.send(""); //Posting txtname to Servlet
		
				 
		  }
		   var x;
		}
	
	function yosi_nombre1() {
		getXMLObject();
		 
		  if(xmlhttp) {
			
			
			  
			  var tipo,cedula,nombre;
				
			  document.getElementById('resultado').innerHTML='<p>Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
			  div = document.getElementById('resultado');
				
				div.style.display="";
			  
		        cedula=document.getElementById('cedula').value;
		        //nombre=document.getElementById('nombre').value;
		        tipo=document.getElementById('tipodoce').value;
		        //alert(tipo);
			xmlhttp.open("POST","lab_BusPac?z="+13+"&ced="+cedula+"&ti="+tipo+"",true); //getname will be the servlet name
			
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
	
	function validarcom_nombre1(h,tecla, e){			
		tecla =   e.keyCode;  e.which;
	if(tecla == 13){ 
	    var tipo,cedula;
		//tipo="";
	    cedula=document.getElementById('cedula').value;
        //nombre=document.getElementById('nombre').value;
        tipo=document.getElementById('tipodoce').value;
	   
	        cedula=h.cedula.value;
	        if(cedula==""||tipo==""){
		   
	        }else{
	        	
	        	yosi_nombre1();                               
	        		
	        	
	        }
		}
	}
function validarcom_nombre(h,tecla, e){			
		tecla =   e.keyCode;  e.which;
	if(tecla == 13){ 
	    var tipo,cedula;
	    cedula=document.getElementById('cedula').value;
        //nombre=document.getElementById('nombre').value;
        tipo=document.getElementById('tipodoce').value;
	   
	        cedula=h.cedula.value;
	        if(cedula==""||tipo==""){
		   
	        }else{
	        	
	        	yosi_nombre();                               
	        		
	        	
	        }
		}
	}
function clicksugerencia_nombre(choice){
	
	
    var resul;
    var ced;
    var ti;
 	var y=choice.split("|").length;
 	var z=choice.split("|");		     	
 	for(x=0; x<y-1; x=x+1)
 	{ 
    resul=z[0];
    ced=z[1];
    ti=z[2]
    }
		

 	document.getElementById("nombre").value = resul; 
document.getElementById("cedula").value = ced; 
document.getElementById("tipodoce").value = ti;

borraLista_nombre();
document.getElementById("nombre").focus();



}