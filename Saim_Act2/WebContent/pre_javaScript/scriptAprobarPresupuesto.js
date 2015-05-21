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
        
        /*función utilizada por el boton buscar presupuesto
        para inciar busqueda de todos los detalles del presupuesto*/
        function iniciarBusqueda(){
		peticion = inicializa_xhr();
			if(peticion) {
				peticion.onreadystatechange = mostrarResultado;
                                //var valor = parseInt(document.getElementById("txtAccion"));
                                //alert(valor.value);
				//peticion.open("POST","http://127.0.0.1:8988/PruebasAjax-PruebasAJAX-context-root/servlet3", true);
                                var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_presupuesto_servlet";
                                peticion.open("POST",cad, true);                                
                                //peticion.open("POST","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_presupuesto_servlet", true);
                                peticion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				//peticion.send("txtAccion=" + encodeURIComponent(valor));
                                var query = crearQuery();
                                peticion.send(query);
			}
			//document.getElementById("cboCentrosDeCostos").onchange = muestraValor;
        }

/*función utilizada para construir el query que va al servidor*/
    function crearQuery(){
            var codigo = document.getElementById("txtCodigoRefernciaPresupuestoBuscar").value;
            var tipoBusquedaTodos = document.getElementById("rdBttnTipoBusquedaTodos");
            var tipoBusquedaCentros = document.getElementById("rdBttnTipoBusquedaCentros");
//            alert(tipoBusquedaTodos.checked);
  //          alert(tipoBusquedaCentros.checked);
            if(tipoBusquedaTodos.checked){
            var tituloBusqueda =document.getElementById("tblEncabezadoBusqueda");
            tituloBusqueda.rows[0].cells[0].innerHTML = "REPORTE DEL PRESUPUESTO CENTROS DE COSTOS Y CUENTAS";
            
            
                return "txtAccion="+encodeURIComponent(22)+
                "&txtCodigoRefernciaPresupuestoBuscar="+encodeURIComponent(codigo)+
                "&nocache=" + Math.random();
            }else{
                var tituloBusqueda =document.getElementById("tblEncabezadoBusqueda");
                tituloBusqueda.rows[0].cells[0].innerHTML = "REPORTE DEL PRESUPUESTO CONSOLIDADO POR CUENTAS";
                return "txtAccion="+encodeURIComponent(29)+
                "&txtCodigoRefernciaPresupuestoBuscar="+encodeURIComponent(codigo)+
                "&nocache=" + Math.random();
            }
    }
	
        /*función que formatea la respuesta del servidor*/
	function mostrarResultado() {
		if (peticion.readyState == 4) {
			if (peticion.status == 200) {
                        try {
                        	var documento_xml = peticion.responseXML;
                                var presupuesto = documento_xml.getElementsByTagName("presupuesto")[0];
                                var tablaEncabezadoPresupuesto = document.getElementById("encabezadoPresupuesto");
                                var codigoRef = presupuesto.getElementsByTagName("codigoReferencia")[0];
                                
                                //document.getElementById("txtCodigoReferenciaPresupuesto").value = codigoRef.firstChild.nodeValue;
                                tablaEncabezadoPresupuesto.rows[1].cells[0].innerHTML = codigoRef.firstChild.nodeValue;
                                
                                var fechaInicio = presupuesto.getElementsByTagName("fechaInicio")[0];
                                //document.getElementById("txtFechaInicioEjecucion").value = fechaInicio.firstChild.nodeValue;
                                tablaEncabezadoPresupuesto.rows[1].cells[1].innerHTML = fechaInicio.firstChild.nodeValue;
                                
                                var fechaFin =presupuesto.getElementsByTagName("fechaFin")[0];
                                //document.getElementById("txtFechaFinEjecucion").value = fechaFin.firstChild.nodeValue;
                                tablaEncabezadoPresupuesto.rows[1].cells[2].innerHTML = fechaFin.firstChild.nodeValue;
                                
                                var fechaLimite =presupuesto.getElementsByTagName("fechaLimiteAprobacion")[0];
                                //document.getElementById("txtFechaLimiteAprobacion").value = fechaLimite.firstChild.nodeValue;
                                tablaEncabezadoPresupuesto.rows[1].cells[3].innerHTML = fechaLimite.firstChild.nodeValue;
                                
                                var valorPresupuestado = presupuesto.getElementsByTagName("valPresupuestado")[0];
                                //document.getElementById("txtValorPresupuestoGlobal").value = valorPresupuestado.firstChild.nodeValue;
                                tablaEncabezadoPresupuesto.rows[1].cells[4].innerHTML = parseFloat(valorPresupuestado.firstChild.nodeValue);
                                
                                var detalles = presupuesto.getElementsByTagName("detalles");
                                var detallesMensual = presupuesto.getElementsByTagName("detalleMensual");
                                var meses = presupuesto.getElementsByTagName("numeroMes");
                                var nombreCentroCostos = presupuesto.getElementsByTagName("nombreCentroCosto");
                                //alert("numero de centros de costos="+nombreCentroCostos.length);
                                var valorMes = presupuesto.getElementsByTagName("valorPresupuestoMes");
                                var tabla = document.getElementById('detallesPresupuesto'); 
                                var estado = presupuesto.getElementsByTagName("estado")[0];
                                var contadorInicioDetalles =0;
                                var contadorFinDetalles =12;
                                for(var i=0;i<detalles.length;i++){
                                   var codigoCuenta = detalles[i];
                                   var nomCentroCosto = nombreCentroCostos[i];
                                   //alert(nomCentroCosto.firstChild.nodeValue);
                                   var detalleMensual = detallesMensual[i];
                                    tabla.tBodies[0].insertRow(i);
                                    var cabecera = document.createElement("th");
                                    cabecera.setAttribute('scope', 'row');
                                    cabecera.innerHTML = codigoCuenta.childNodes[0].firstChild.nodeValue;
                                    
                                    tabla.tBodies[0].rows[i].appendChild(cabecera);
                                    tabla.tBodies[0].rows[i].insertCell(1);
                                    tabla.tBodies[0].rows[i].cells[1].innerHTML = codigoCuenta.childNodes[1].firstChild.nodeValue;
                                    tabla.tBodies[0].rows[i].insertCell(2);
                                    tabla.tBodies[0].rows[i].cells[2].innerHTML = nomCentroCosto.firstChild.nodeValue;
                                   //alert("valor que va en el nombre centro de costos="+nomCentroCosto.childNodes[0].firstChild.nodeValue);
                                    var contadorCeldaTabla =3;
                                    for(var j =contadorInicioDetalles;j<contadorFinDetalles;j++){
                                        tabla.tBodies[0].rows[i].insertCell(contadorCeldaTabla);
                                        tabla.tBodies[0].rows[i].cells[contadorCeldaTabla].innerHTML =parseFloat(valorMes[j].firstChild.nodeValue);
                                        contadorCeldaTabla++;
                                    }
                                    var contadorFinDetallesTmp = contadorFinDetalles;
                                    contadorFinDetalles = contadorFinDetalles +12;
                                    contadorInicioDetalles=contadorFinDetallesTmp;
                                }
                                //alert("200");
                                bloquearControles();
                                if(estado.firstChild.nodeValue==2){
                                    document.getElementById("bttnAprobarPresupuesto").disabled=true;
                                    document.getElementById("bttnIniciarEjecucion").disabled = false;
                                    //document.getElementById("txtEstadoPresupuesto").value = "Aprobado";
                                    tablaEncabezadoPresupuesto.rows[3].cells[0].innerHTML = "Aprobado";
                                }else{
                                    if(estado.firstChild.nodeValue==1){
                                    //document.getElementById("txtEstadoPresupuesto").value = "No aprobado";
                                    document.getElementById("bttnIniciarEjecucion").disabled = true;
                                    tablaEncabezadoPresupuesto.rows[3].cells[0].innerHTML = "No Aprobado";
                                    }else{
                                        if(estado.firstChild.nodeValue==3){
                                      //  document.getElementById("txtEstadoPresupuesto").value = "En ejecución";                                        
                                        document.getElementById("bttnAprobarPresupuesto").disabled=true;
                                        document.getElementById("bttnTerminarEjecucion").disabled = false;
                                        tablaEncabezadoPresupuesto.rows[3].cells[0].innerHTML = "En Ejecución";
                                        }else{
                                            if(estado.firstChild.nodeValue==4){
                                        //        document.getElementById("txtEstadoPresupuesto").value = "Ejecuctado";                                        
                                                document.getElementById("bttnAprobarPresupuesto").disabled=true;
                                                document.getElementById("bttnTerminarEjecucion").disabled = true;                                            
                                                tablaEncabezadoPresupuesto.rows[3].cells[0].innerHTML = "Ejecutado";
                                            }
                                        }
                                    }
                                }
                        } catch(excepcion) {
                            alert(excepcion);
                        }
			}else{
				if(peticion.status == 500){
						alert("error 500");
				}else{
					if(peticion.status == 404){
						alert("error 404");
					}
				}
			}
		}
	}

/*función de bloqueo de controles de la pagina jsp*/        
        function bloquearControles(){
            document.getElementById("txtCodigoRefernciaPresupuestoBuscar").disabled=true;
            document.getElementById("bttnBuscarPresupuesto").disabled=true;
            document.getElementById("bttnCancelar").disabled=false;
            document.getElementById("bttnAprobarPresupuesto").disabled=false;
            document.getElementById("bttnImprimirReporte").disabled = false;

        }

/*función utilizada para limpiar todos los datos de la pagina*/
function limpiarDatos(){
    /*document.getElementById("txtCodigoReferenciaPresupuesto").value="0";
    document.getElementById("txtFechaInicioEjecucion").value="yyyy-mm-dd";
    document.getElementById("txtFechaFinEjecucion").value="yyyy-mm-dd";
    document.getElementById("txtFechaLimiteAprobacion").value="yyyy-mm-dd";
    document.getElementById("txtValorPresupuestoGlobal").value="0";
    document.getElementById("txtEstadoPresupuesto").value="";*/
    location.reload(true);
    /*limpiarDatosTabla();
    desbloquearControles();
    document.getElementById("bttnImprimirReporte").disabled=true;
    document.getElmentById*/
}

/*limpia los datos que tiene la tabla con una busqueda ya realizada*/
function limpiarDatosTabla(){
var tabla = document.getElementById('detallesPresupuesto'); 
//alert("numero de Filas = "+tabla.tBodies[0].rows.length);
var numeroFilas = tabla.tBodies[0].rows.length;
    for(var i=(numeroFilas-1);i>=0;i--){
        tabla.tBodies[0].deleteRow(i);
    }
}

/*desbloque los controles que esten bloqueados para inicar una nueva busqueda*/
function desbloquearControles(){
document.getElementById("txtCodigoRefernciaPresupuestoBuscar").disabled=false;
document.getElementById("bttnBuscarPresupuesto").disabled=false;
document.getElementById("bttnCancelar").disabled=true;
document.getElementById("bttnAprobarPresupuesto").disabled=true;
document.getElementById("bttnIniciarEjecucion").disabled = true;
}


/*función que realiza la petición asincrona al servidor para realizar la aprobación del presupuesto
*/
function iniciarAprobacion(){
		peticion = inicializa_xhr();
			if(peticion) {
				peticion.onreadystatechange = mostrarRespuesta;
                                //var valor = parseInt(document.getElementById("txtAccion"));
                                //alert(valor.value);
				//peticion.open("POST","http://127.0.0.1:8988/PruebasAjax-PruebasAJAX-context-root/servlet3", true);
                                var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_presupuesto_servlet";
                                peticion.open("POST",cad, true);
                                //peticion.open("POST","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_presupuesto_servlet", true);
                                peticion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				//peticion.send("txtAccion=" + encodeURIComponent(valor));
                                var query = crearQueryAprobacion();
                                peticion.send(query);
			}
			//document.getElementById("cboCentrosDeCostos").onchange = muestraValor;
}

/*query enviado al servidor
aqui van los parametros que el servidor recibe*/
function crearQueryAprobacion(){
var codigo = document.getElementById("txtCodigoRefernciaPresupuestoBuscar").value;
return "txtAccion="+encodeURIComponent(23)+
"&txtCodigoRefernciaPresupuestoBuscar="+encodeURIComponent(codigo)+
"&nocache=" + Math.random();
}


/*función que muestra la respueta de la transacción pedida al servidor*/
function mostrarRespuesta(){
                if (peticion.readyState == 4) {
			if (peticion.status == 200) {
                            try {
                            var texto = peticion.responseText;
                                //alert(texto)
                            if(texto==1){
                                alert("transacción realizada, Presupuesto Aprobado");
                                limpiarDatos();
                            }else{
                                alert("Transacción no realizada, Presupuesto no aprobado");
                            }
                            }catch(excepcion) {
                                alert("Error Transacción no realizada"+excepcion);
                            }
                        } else{
				if(peticion.status == 500){
						alert("error 500");
				}else{
					if(peticion.status == 404){
						alert("error 404");
					}
				}
			}
                }
}

/*función de petición al servidor para iniciar la ejecución de un presupuesto*/
function iniciarEjecucion(){
		peticion = inicializa_xhr();
			if(peticion) {
				peticion.onreadystatechange = mostrarRespuestaEjecucion;
                                //var valor = parseInt(document.getElementById("txtAccion"));
                                //alert(valor.value);
				//peticion.open("POST","http://127.0.0.1:8988/PruebasAjax-PruebasAJAX-context-root/servlet3", true);
                                var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_presupuesto_servlet";
                                peticion.open("POST",cad, true);                                
                                //peticion.open("POST","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_presupuesto_servlet", true);
                                peticion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				//peticion.send("txtAccion=" + encodeURIComponent(valor));
                                var query = crearQueryEjecucion();

                                peticion.send(query);
			}
			//document.getElementById("cboCentrosDeCostos").onchange = muestraValor;
}


/*funciónm de creación de la cadena enviada al servidor*/
function crearQueryEjecucion(){
    var codigo = document.getElementById("txtCodigoRefernciaPresupuestoBuscar").value;
    return "txtAccion="+encodeURIComponent(25)+
    "&txtCodigoRefernciaPresupuestoBuscar="+encodeURIComponent(codigo)+
    "&nocache=" + Math.random();
}
/*función de mostrar la respuesta a la peticion de iniciar la ejecución de un presupuesto*/
function mostrarRespuestaEjecucion(){

                if (peticion.readyState == 4) {
			if (peticion.status == 200) {
                            try {
                            var texto = peticion.responseText;
                                //alert(texto)
                            if(texto==1){
                                alert("Transacción realizada,Presupuesto en ejecución");
                                limpiarDatos();
                            }else{
                                alert("Transacción no realizada, Presupuesto NO está en ejecución")
                            }
                            }catch(excepcion) {
                                alert("Transacción no realizada,"+excepcion);
                            }
                        } else{
				if(peticion.status == 500){
						alert("error 500");
				}else{
					if(peticion.status == 404){
						alert("error 404");
					}
				}
			}
}
}



/*función de petición al servidor para iniciar la ejecución de un presupuesto*/
function terminarEjecucion(){
		peticion = inicializa_xhr();
			if(peticion) {
				peticion.onreadystatechange = mostrarRespuestaTerminarEjecucion;
                                //var valor = parseInt(document.getElementById("txtAccion"));
                                //alert(valor.value);
				//peticion.open("POST","http://127.0.0.1:8988/PruebasAjax-PruebasAJAX-context-root/servlet3", true);
                                var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_presupuesto_servlet";
                                peticion.open("POST",cad, true);                                
                                //peticion.open("POST","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_presupuesto_servlet", true);
                                peticion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				//peticion.send("txtAccion=" + encodeURIComponent(valor));
                                var query = crearQueryTerminarEjecucion();

                                peticion.send(query);
			}
			//document.getElementById("cboCentrosDeCostos").onchange = muestraValor;
}


/*funciónm de creación de la cadena enviada al servidor*/
function crearQueryTerminarEjecucion(){
    var codigo = document.getElementById("txtCodigoRefernciaPresupuestoBuscar").value;
    return "txtAccion="+encodeURIComponent(35)+
    "&txtCodigoRefernciaPresupuestoBuscar="+encodeURIComponent(codigo)+
    "&nocache=" + Math.random();
}
/*función de mostrar la respuesta a la peticion de iniciar la ejecución de un presupuesto*/
function mostrarRespuestaTerminarEjecucion(){

                if (peticion.readyState == 4) {
			if (peticion.status == 200) {
                            try {
                            var texto = peticion.responseText;
                                //alert(texto)
                            if(texto==1){
                                alert("Transacción realizada,Presupuesto en ejecución");
                                limpiarDatos();
                                document.getElementById("bttnTerminarEjecucion").disabled=true;
                            }else{
                                alert("Transacción no realizada, Presupuesto NO está en ejecución")
                            }
                            }catch(excepcion) {
                                alert("Transacción no realizada,"+excepcion);
                            }
                        } else{
				if(peticion.status == 500){
						alert("error 500");
				}else{
					if(peticion.status == 404){
						alert("error 404");
					}
				}
			}
}
}


/*función para autocompletar cunado se digite el codigo del presupuesto*/
function autocompleta() {
	alert("listo");
    var elEvento = arguments[0] || window.event;
    var tecla = elEvento.keyCode;
        if(tecla == 40) { // Flecha Abajo
               if(elementoSeleccionado+1 < sugerencias.length) {
                    elementoSeleccionado++;
                }
            muestraSugerencias();
        }else 
            if(tecla == 38) { // Flecha Arriba
                //alert("presiono arriba");
                    if(elementoSeleccionado > 0) {
                        elementoSeleccionado--;
                    }
                muestraSugerencias();
            }else
                if(tecla == 13) { // ENTER o Intro
                    //alert("presiono enter");
                    seleccionaElemento();
                }else {
                    var texto = document.getElementById("txtCodigoRefernciaPresupuestoBuscar").value;
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
                                var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_presupuesto_servlet";
                                peticion.open("POST",cad, true);                                    
                        //peticion.open('POST', 'http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_presupuesto_servlet', true);
                        peticion.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                        var querys = "txtAccion="+encodeURIComponent(26)+"&txtCodigoReferencia="+encodeURIComponent(texto)+"&nocache="+Math.random();
                        peticion.send(querys);
                    } else {
                        sugerencias = cacheSugerencias[texto];
                        actualizaSugerencias();
                    }
                }
}

/*función que muestra las sugerencias de codigos de referencia de presupuestos*/
function muestraSugerencias() {
    var zonaSugerencias = document.getElementById("sugerencias");
        zonaSugerencias.innerHTML = sugerencias.formateaLista();
        zonaSugerencias.style.display = 'block';
}

/*prototypo de la lista quesale en el div de sugerencias*/
Array.prototype.formateaLista = function() {
    var codigoHtml = "";
    codigoHtml = "<ul>";
        for(var i=0; i<this.length; i++) {
            if(i == elementoSeleccionado) {
                codigoHtml += "<li class= \"seleccionado\">"+this[i]+"<\/li>";
            }else {
                codigoHtml += "<li>"+this[i]+"<\/li>";
            }
        }
    codigoHtml += "<\/ul>";
    return codigoHtml;
};

/*función en el cual se le asigna a la caja de texto la opción selccionada de las sugerencias*/
function seleccionaElemento() {
//alert(sugerencias[elementoSeleccionado]);
    if(sugerencias[elementoSeleccionado]) {
       document.getElementById("txtCodigoRefernciaPresupuestoBuscar").value = sugerencias[elementoSeleccionado];
        borraLista();
        document.getElementById("txtCodigoRefernciaPresupuestoBuscar").disabled = true;
        document.getElementById("bttnBuscarPresupuesto").disabled = false;
        //activarBotones();

        //document.getElementById("bttnBuscar").disabled = false;
        //document.getElementById("bttnCancelar").disabled=;
        //buscarNombreCuenta();
        //activaBotonAdicionarFila();
    }
}



function activarBotones(){
var rdBttnCentroCostoEspecifico = document.getElementById("txtRdBttnBusquedaCentroCostosEspecifico");
var rdBttnCuentaEspecifica = document.getElementById("txtRdBttnBusquedaCuentaEspecifica");
    if(rdBttnCentroCostoEspecifico.checked){
        document.getElementById("cboCentrosDeCostos").disabled = false;
    }else{
        if(rdBttnCuentaEspecifica.checked){
            document.getElementById("cboCentrosDeCostos").disabled = false;
        }
    }

}

/*función llamada para quitar el div cuando se selecciona 
un dato del la lista de sugerencia*/
function borraLista() {
        document.getElementById("sugerencias").innerHTML = "";
        document.getElementById("sugerencias").style.display = "none";
        //document.getElementById("bttnBuscarPresupuesto").disabled = true;
        //document.getElementById("bttnAdicionarFila").disabled = true;
        //limpiarTodosLosCampos();
}

/*funcion que se activa cuando 
los valores introducidos en la caja de texto no concuerdan
con la lista de sugerencias o más bien no hay
*/
function sinResultados() {
    document.getElementById("sugerencias").innerHTML = "No existe presupuesto que empiecen con ese texto";
    document.getElementById("sugerencias").style.display = "block";
    //document.getElementById("bttnAdicionarFila").disabled = true;
}

/*función utilizada paa actualizar las sugerencias de acuerdo el texto vaya cambiando*/
function actualizaSugerencias() {
elementoSeleccionado = -1;
muestraSugerencias();
}

function imprimirReporte(){
imprSelec("reporte");
}

function imprSelec(nombre){
  var ficha = document.getElementById(nombre);
  
var ventimp = window.open("","xxx");
  ventimp.document.write(ficha.innerHTML);
  ventimp.document.close();
  ventimp.print();
  ventimp.close();
}



	window.onload = function() {
            document.getElementById("txtCodigoRefernciaPresupuestoBuscar").onkeyup = autocompleta;
            document.getElementById("bttnBuscarPresupuesto").onclick = iniciarBusqueda;
            document.getElementById("bttnCancelar").onclick=limpiarDatos;
            document.getElementById("bttnAprobarPresupuesto").onclick=iniciarAprobacion;
            document.getElementById("bttnIniciarEjecucion").onclick=iniciarEjecucion;
            document.getElementById("bttnTerminarEjecucion").onclick = terminarEjecucion;
            document.getElementById("bttnImprimirReporte").onclick = imprimirReporte;
	}