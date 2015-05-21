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

	/*función para autocompletar cunado se digite el codigo del presupuesto*/
	function autocompletaPacacientes() { 
		//alert()
		//Picture1.MousePointer = 99
	    var elEvento = arguments[0] || window.event;
	   
	    var tecla = elEvento.keyCode;
	    
	    
	   
	               
	                    var texto = document.getElementById("txtNomPaciente").value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
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
	                                                    sinResultados();
	                                                }else {
	                                                    cacheSugerencias[texto] = sugerencias;
	                                                    actualizaSugerencias();
	                                                }
	                                            }
	                                        }
	                                    };
	                                var txtAccion="26";  
	                                peticion.open("GET","ControlCrearFormato?txtAccion="+txtAccion+"&codigo="+texto,true);                                 
	     	                        peticion.send("");
	                    } else {
	                        sugerencias = cacheSugerencias[texto];
	                        actualizaSugerencias();
	                    }
	                
	}
	function sinResultados() {
	    document.getElementById("sugerencias").innerHTML = "No existe registro";
	    document.getElementById("sugerencias").style.display = "block";
	}

	function actualizaSugerencias() {
		elementoSeleccionado = -1;
		muestraSugerencias();
		
		  
		}

	/*función que muestra las sugerencias de codigos de referencia de presupuestos*/
	function muestraSugerencias() {
	    var zonaSugerencias = document.getElementById("sugerencias");
	        zonaSugerencias.innerHTML = sugerencias.formateaLista();
	        zonaSugerencias.style.display = 'block';
// div = document.getElementById('resultado');
			
		//	div.style.display='none';
	       
	       
	}

	/*prototypo de la lista quesale en el div de sugerencias*/
	Array.prototype.formateaLista = function() {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";
	    
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\">'+this[i]+'<\/li>';
	            }else {
	                codigoHtml += '<li onclick="confirmar(this.innerHTML)">'+this[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
       div = document.getElementById('sugerencias');
		div.style.display='none';
	    return codigoHtml;
	     
	};

	
	function borraLista() {
		Pacientes();
	}

function confirmar(cf){
		 var CodPac="";
		 var CodAdm="";
		
	     	var y=cf.split("|").length;
	     	var z=cf.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     	CodPac=z[1];
	     	CodAdm=z[2];
		    }
	       //alert("CodPac "+CodPac);
	      /*
	       * se direcciona a los laboratorios enseguida
	     	window.location.href="ord_AsignarLaboratorio.jsp?CodPac="+CodPac;*/
	     	window.location.href="hic_pestanas.jsp?CodPac="+CodPac+"&CodAdm="+CodAdm;
	    }
	
	