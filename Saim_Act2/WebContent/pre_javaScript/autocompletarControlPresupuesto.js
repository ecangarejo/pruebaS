var peticion = null;
var elementoSeleccionado = -1;
var sugerencias = null;
var cacheSugerencias = {};
var vectorNombreCentrosCostos = new Array();
var vectorNombreCuentas = new Array();

/*función utilizada para realizar la petición asincrona AJAX*/
function inicializar_xhr() {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}


/*función para autocompletar cunado se digite el codigo del presupuesto*/
function autocompleta() {
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
                    var texto = document.getElementById("txtCodigoCuenta").value;
                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
                    if(tecla == 8 && texto == "") {
                        borraLista();
                        return;
                    }
                    if(cacheSugerencias[texto] == null) {
                        peticion = inicializar_xhr();
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
       document.getElementById("txtCodigoCuenta").value = sugerencias[elementoSeleccionado];
        borraLista();
        document.getElementById("txtCodigoCuenta").disabled = true;
        //activarBotones();
        //activarOpcionesDeBusqueda();
        //document.getElementById("bttnBuscar").disabled = false;
        //document.getElementById("bttnCancelar").disabled=;
        //buscarNombreCuenta();
        //activaBotonAdicionarFila();
    }
}

function activarOpcionesDeBusqueda(){
//alert("entro");
    document.getElementById("txtRdBttnBusquedaCuentas").disabled = false;
    document.getElementById("txtRdBttnBusquedaCentros").disabled = false;
    document.getElementById("txtRdBttnBusquedaCentroCostosEspecifico").disabled = false;
    document.getElementById("txtRdBttnBusquedaCuentaEspecifica").disabled = false;
    document.getElementById("bttnBuscar").disabled = false;
}

function activarBotones(){
var rdBttnCentroCostoEspecifico = document.getElementById("txtRdBttnBusquedaCentroCostosEspecifico");
var rdBttnCuentaEspecifica = document.getElementById("txtRdBttnBusquedaCuentaEspecifica");
    if(rdBttnCentroCostoEspecifico.checked){
        document.getElementById("cboCentrosDeCostos").disabled = false;
        document.getElementById("bttnBuscar").disabled = false;
    }else{
        if(rdBttnCuentaEspecifica.checked){
            document.getElementById("cboCentrosDeCostos").disabled = false;
            document.getElementById("bttnBuscar").disabled = false;
        }
    }

}

/*función llamada para quitar el div cuando se selecciona 
un dato del la lista de sugerencia*/
function borraLista() {
        document.getElementById("sugerencias").innerHTML = "";
        document.getElementById("sugerencias").style.display = "none";
        document.getElementById("bttnBuscar").disabled = true;
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

/*función utilizada para cambiar el encabezado cada vez que se escoja un periodo o trimestre*/
function cambiarEncabezadoTabla(){
        var tabla = document.getElementById('tablaControl');
    var numColumnas = tabla.rows[0].cells.length;
    //alert(numColumnas+"valor combox="+document.getElementById("cboPeriodoAVisualizar").value);
    //alert(tabla.rows[0].cells[2].innerHTML);
    if(document.getElementById("cboPeriodoAVisualizar").value==1){
    cambiarEncabezadoPrimerTrimestre();
    }else{
        if(document.getElementById("cboPeriodoAVisualizar").value==2){
        cambiarEncabezadoSegundoTrimestre();
        }else{
            if(document.getElementById("cboPeriodoAVisualizar").value==3){
            cambiarEncabezadoTercerTrimestre()
            }else{
                 if(document.getElementById("cboPeriodoAVisualizar").value==4){
                    cambiarEncabezadoCuartoTrimestre()
                    }else{
                    
                    }
            }
            //alert("nada");
        }
    }
}

/*función utilizada si el trimestre es el primero*/
function cambiarEncabezadoPrimerTrimestre(){
        var tabla = document.getElementById('tablaControl');
        tabla.caption.innerHTML = "Primer Trimestre";
        tabla.rows[0].cells[3].innerHTML = "Presupuesto Enero";
        tabla.rows[0].cells[4].innerHTML = "Ejecutado Enero";
        tabla.rows[0].cells[5].innerHTML = "Saldo Enero";
        tabla.rows[0].cells[6].innerHTML = "Presupuesto Febrero";
        tabla.rows[0].cells[7].innerHTML = "Ejecutado Febrero";
        tabla.rows[0].cells[8].innerHTML = "Saldo Febrero";
        tabla.rows[0].cells[9].innerHTML = "Presupuesto Marzo";
        tabla.rows[0].cells[10].innerHTML = "Ejecutado Marzo";
        tabla.rows[0].cells[11].innerHTML = "Saldo Marzo";
        
}

/*función utilizada si el trimestre es el segundo*/
function cambiarEncabezadoSegundoTrimestre(){
        var tabla = document.getElementById('tablaControl');
        tabla.caption.innerHTML = "Segundo Trimestre";
        tabla.rows[0].cells[3].innerHTML = "Presupuesto Abril";
        tabla.rows[0].cells[4].innerHTML = "Ejecutado Abril";
        tabla.rows[0].cells[5].innerHTML = "Saldo Abril";
        tabla.rows[0].cells[6].innerHTML = "Presupuesto Mayo";
        tabla.rows[0].cells[7].innerHTML = "Ejecutado Mayo";
        tabla.rows[0].cells[8].innerHTML = "Saldo Mayo";
        tabla.rows[0].cells[9].innerHTML = "Presupuesto Junio";
        tabla.rows[0].cells[10].innerHTML = "Ejecutado Junio";
        tabla.rows[0].cells[11].innerHTML = "Saldo Junio";
        
}

/*función utilizada si el trimestre es el tercero*/
function cambiarEncabezadoTercerTrimestre(){
        var tabla = document.getElementById('tablaControl');
        tabla.caption.innerHTML = "Tercer Trimestre";
        tabla.rows[0].cells[3].innerHTML = "Presupuesto Julio";
        tabla.rows[0].cells[4].innerHTML = "Ejecutado Julio";
        tabla.rows[0].cells[5].innerHTML = "Saldo Julio";
        tabla.rows[0].cells[6].innerHTML = "Presupuesto Agosto";
        tabla.rows[0].cells[7].innerHTML = "Ejecutado Agosto";
        tabla.rows[0].cells[8].innerHTML = "Saldo Agosto";
        tabla.rows[0].cells[9].innerHTML = "Presupuesto Septiembre";
        tabla.rows[0].cells[10].innerHTML = "Ejecutado Septiembre";
        tabla.rows[0].cells[11].innerHTML = "Saldo Septiembre";
        
}

/*función utilizada si el trimestre es el cuarto*/
function cambiarEncabezadoCuartoTrimestre(){
        var tabla = document.getElementById('tablaControl');
        tabla.caption.innerHTML = "Cuarto Trimestre";
        tabla.rows[0].cells[3].innerHTML = "Presupuesto Octubre";
        tabla.rows[0].cells[4].innerHTML = "Ejecutado Octubre";
        tabla.rows[0].cells[5].innerHTML = "Saldo Octubre";
        tabla.rows[0].cells[6].innerHTML = "Presupuesto Noviembre";
        tabla.rows[0].cells[7].innerHTML = "Ejecutado Noviembre";
        tabla.rows[0].cells[8].innerHTML = "Saldo Noviembre";
        tabla.rows[0].cells[9].innerHTML = "Presupuesto Diciembre";
        tabla.rows[0].cells[10].innerHTML = "Ejecutado Diciembre";
        tabla.rows[0].cells[11].innerHTML = "Saldo Diciembre";
}

/*prepara para la petición asincrona al servidor mediante AJAx*/
function iniciarBusqueda(){
//alert("entro en iniciar busqueda");
 var cboAnual = document.getElementById("radioBttnAnual");
		peticion = inicializar_xhr();
			if(peticion) {
                            if(cboAnual.checked){
                            	peticion.onreadystatechange =mostrarResultadoBusqueda2;
                            }else{
                        	peticion.onreadystatechange = mostrarResultadoBusqueda;    
                            }
			
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


function bloquearControles(){
    document.getElementById("txtRdBttnBusquedaCuentas").disabled =true;
    document.getElementById("txtRdBttnBusquedaCentros").disabled =true;
    document.getElementById("txtRdBttnBusquedaCentroCostosEspecifico").disabled =true;
    document.getElementById("txtRdBttnBusquedaCuentaEspecifica").disabled =true;
    document.getElementById("cboCentrosDeCostos").disabled =true;
    document.getElementById("cboCuentasCentrosCostos").disabled =true;
    document.getElementById("bttnBuscar").disabled =true;
    document.getElementById("bttnImprimirReporte").disabled = false;
    
    
}
/*función que formatea la respuesta del servidor*/
	function mostrarResultadoBusqueda() {
		if (peticion.readyState == 4) {
			if (peticion.status == 200) {
                        try {
                        	var documento_xml = peticion.responseXML;
                                var presupuesto = documento_xml.getElementsByTagName("presupuesto")[0];
                                var codigoRef = presupuesto.getElementsByTagName("codigoReferencia")[0];
                                
                                //document.getElementById("txtCodigoReferenciaPresupuesto").value = codigoRef.firstChild.nodeValue;
                                var tablaencabezadoPresupuesto = document.getElementById("encabezadoPresupuesto");
                                //alert(tablaencabezadoPresupuesto.rows[2].cells[1].firstChild.nodeValue);
                                tablaencabezadoPresupuesto.rows[1].cells[0].innerHTML = codigoRef.firstChild.nodeValue;
                                
                                var fechaInicio = presupuesto.getElementsByTagName("fechaInicio")[0];
                                //document.getElementById("txtFechaInicioEjecucion").value = fechaInicio.firstChild.nodeValue;
                                tablaencabezadoPresupuesto.rows[1].cells[1].innerHTML = fechaInicio.firstChild.nodeValue;
                                
                                var fechaFin =presupuesto.getElementsByTagName("fechaFin")[0];
                                //document.getElementById("txtFechaFinEjecucion").value = fechaFin.firstChild.nodeValue;
                                tablaencabezadoPresupuesto.rows[1].cells[2].innerHTML = fechaFin.firstChild.nodeValue;
                                
                                var fechaLimite =presupuesto.getElementsByTagName("fechaLimiteAprobacion")[0];
                                //document.getElementById("txtFechaLimiteAprobacion").value = fechaLimite.firstChild.nodeValue;
                                tablaencabezadoPresupuesto.rows[1].cells[3].innerHTML = fechaLimite.firstChild.nodeValue;
                                
                                var detalles = presupuesto.getElementsByTagName("detalles");
                                var detallesMensual = presupuesto.getElementsByTagName("detalleMensual");
                                var meses = presupuesto.getElementsByTagName("numeroMes");
                                var nombreCentroCostos = presupuesto.getElementsByTagName("nombreCentroCosto");
                                //alert("numero de centros de costos="+nombreCentroCostos.length);
                                
                                
                                var valorPresupuestado = presupuesto.getElementsByTagName("valPresupuestado")[0];
                                //document.getElementById("txtValorPresupuestoGlobal").value = valorPresupuestado.firstChild.nodeValue;
                                tablaencabezadoPresupuesto.rows[1].cells[4].innerHTML = parseFloat(valorPresupuestado.firstChild.nodeValue);
                                
                                var valorEjecutado = presupuesto.getElementsByTagName("valEjecutado")[0];
                                //alert(valorEjecutado);
                                //document.getElementById("txtValorPresupuestoGlobal").value = valorPresupuestado.firstChild.nodeValue;
                                tablaencabezadoPresupuesto.rows[3].cells[1].innerHTML = parseFloat(valorEjecutado.firstChild.nodeValue);
                                
                                var valorPresupuestoMes = presupuesto.getElementsByTagName("valorPresupuestoMes");
                                var valorEjecutadoMes = presupuesto.getElementsByTagName("valorEjecutadoMes");
                                var saldoMes = presupuesto.getElementsByTagName("saldoMes");
                                var tabla = document.getElementById('tablaControl'); 
                                var estado = presupuesto.getElementsByTagName("estado")[0];
                                
                                var contadorInicioDetalles =0;
                                var contadorFinDetalles =3;
                                //alert("numero de detalles="+detalles.length);
                                for(var i=0;i<detalles.length;i++){
                                //alert("i="+i);
                                   var codigoCuenta = detalles[i];
                                   var nomCentroCosto = nombreCentroCostos[i];
                                   var detalleMensual = detallesMensual[i];
                                    tabla.tBodies[0].insertRow(i);
                                    var cabecera = document.createElement("th");
                                    cabecera.setAttribute('scope', 'row');
                                    cabecera.setAttribute('align','right');
                                    cabecera.innerHTML = codigoCuenta.childNodes[0].firstChild.nodeValue;
                                    
                                    tabla.tBodies[0].rows[i].appendChild(cabecera);
                                    tabla.tBodies[0].rows[i].insertCell(1);
                                    tabla.tBodies[0].rows[i].cells[1].innerHTML = codigoCuenta.childNodes[1].firstChild.nodeValue;
                                    tabla.tBodies[0].rows[i].insertCell(2);
                                    tabla.tBodies[0].rows[i].cells[2].innerHTML = nomCentroCosto.firstChild.nodeValue;
                                    var contadorCeldaTabla =3;
                                    //var sw=1;
                                    for(var j =contadorInicioDetalles;j<contadorFinDetalles;j++){
                                    //alert("j="+j);
                                        tabla.tBodies[0].rows[i].insertCell(contadorCeldaTabla);
                                        tabla.tBodies[0].rows[i].insertCell(contadorCeldaTabla+1);
                                        tabla.tBodies[0].rows[i].insertCell(contadorCeldaTabla+2);
                                        tabla.tBodies[0].rows[i].cells[contadorCeldaTabla].innerHTML =parseFloat(valorPresupuestoMes[j].firstChild.nodeValue);
                                        tabla.tBodies[0].rows[i].cells[contadorCeldaTabla+1].innerHTML =parseFloat(valorEjecutadoMes[j].firstChild.nodeValue);
                                        tabla.tBodies[0].rows[i].cells[contadorCeldaTabla+2].innerHTML =parseFloat(saldoMes[j].firstChild.nodeValue);
                                        contadorCeldaTabla=contadorCeldaTabla+3;
                                    }
                                    var contadorFinDetallesTmp = contadorFinDetalles;
                                    contadorFinDetalles = contadorFinDetalles +3;
                                    contadorInicioDetalles=contadorFinDetallesTmp;
                                }
                                
                                bloquearControles();
                                mostrarTituloReporte();
                                
                                if(estado.firstChild.nodeValue==2){
                                    //document.getElementById("bttnAprobarPresupuesto").disabled=true;
                                    //document.getElementById("bttnIniciarEjecucion").disabled = false;
                                    //document.getElementById("txtEstadoPresupuesto").value = "Aprobado";
                                    tablaencabezadoPresupuesto.rows[3].cells[0].innerHTML = "Aprobado";
                                }else{
                                    if(estado.firstChild.nodeValue==1){
                                    //document.getElementById("txtEstadoPresupuesto").value = "No aprobado";
                                    tablaencabezadoPresupuesto.rows[3].cells[0].innerHTML = "No aprobado";
                                    //document.getElementById("bttnIniciarEjecucion").disabled = true;
                                    }else{
                                        if(estado.firstChild.nodeValue==3){
                                        //document.getElementById("txtEstadoPresupuesto").value = "En ejecución";                                        
                                        tablaencabezadoPresupuesto.rows[3].cells[0].innerHTML = "En ejecución";
//                                        document.getElementById("bttnAprobarPresupuesto").disabled=true;
                                        
                                        }else{
                                            if(estado.firstChild.nodeValue==4){
                                            tablaencabezadoPresupuesto.rows[3].cells[0].innerHTML = "Ejecutado";
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
        
        
        /*función que formatea la respuesta del servidor*/
	function mostrarResultadoBusqueda2() {
		if (peticion.readyState == 4) {
			if (peticion.status == 200) {
                        try {
                        	var documento_xml = peticion.responseXML;
                                var presupuesto = documento_xml.getElementsByTagName("presupuesto")[0];
                                var codigoRef = presupuesto.getElementsByTagName("codigoReferencia")[0];
                                
                                //document.getElementById("txtCodigoReferenciaPresupuesto").value = codigoRef.firstChild.nodeValue;
                                var tablaencabezadoPresupuesto = document.getElementById("encabezadoPresupuestoAnual");
                                //alert(tablaencabezadoPresupuesto.rows[2].cells[1].firstChild.nodeValue);
                                tablaencabezadoPresupuesto.rows[1].cells[0].innerHTML = codigoRef.firstChild.nodeValue;
                                
                                var fechaInicio = presupuesto.getElementsByTagName("fechaInicio")[0];
                                //document.getElementById("txtFechaInicioEjecucion").value = fechaInicio.firstChild.nodeValue;
                                tablaencabezadoPresupuesto.rows[1].cells[1].innerHTML = fechaInicio.firstChild.nodeValue;
                                
                                var fechaFin =presupuesto.getElementsByTagName("fechaFin")[0];
                                //document.getElementById("txtFechaFinEjecucion").value = fechaFin.firstChild.nodeValue;
                                tablaencabezadoPresupuesto.rows[1].cells[2].innerHTML = fechaFin.firstChild.nodeValue;
                                
                                var fechaLimite =presupuesto.getElementsByTagName("fechaLimiteAprobacion")[0];
                                //document.getElementById("txtFechaLimiteAprobacion").value = fechaLimite.firstChild.nodeValue;
                                tablaencabezadoPresupuesto.rows[1].cells[3].innerHTML = fechaLimite.firstChild.nodeValue;
                                
                                var detalles = presupuesto.getElementsByTagName("detalles");
                                var detallesMensual = presupuesto.getElementsByTagName("detalleMensual");
                                var meses = presupuesto.getElementsByTagName("numeroMes");
                                var nombreCentroCostos = presupuesto.getElementsByTagName("nombreCentroCosto");
                                //alert("numero de centros de costos="+nombreCentroCostos.length);
                                
                                
                                var valorPresupuestado = presupuesto.getElementsByTagName("valPresupuestado")[0];
                                //document.getElementById("txtValorPresupuestoGlobal").value = valorPresupuestado.firstChild.nodeValue;
                                tablaencabezadoPresupuesto.rows[1].cells[4].innerHTML = parseFloat(valorPresupuestado.firstChild.nodeValue);
                                
                                var valorEjecutado = presupuesto.getElementsByTagName("valEjecutado")[0];
                                //alert(valorEjecutado);
                                //document.getElementById("txtValorPresupuestoGlobal").value = valorPresupuestado.firstChild.nodeValue;
                                tablaencabezadoPresupuesto.rows[3].cells[1].innerHTML = parseFloat(valorEjecutado.firstChild.nodeValue);
                                
                                var valorPresupuestoMes = presupuesto.getElementsByTagName("valorPresupuestoMes");
                                var valorEjecutadoMes = presupuesto.getElementsByTagName("valorEjecutadoMes");
                                var saldoMes = presupuesto.getElementsByTagName("saldoMes");
                                var tabla = document.getElementById('tablaControlAnual'); 
                                var estado = presupuesto.getElementsByTagName("estado")[0];
                                
                                var contadorInicioDetalles =0;
                                var contadorFinDetalles =12;
                                //alert("numero de detalles="+detalles.length);
                                for(var i=0;i<detalles.length;i++){
                                //alert("i="+i);
                                   var codigoCuenta = detalles[i];
                                   var nomCentroCosto = nombreCentroCostos[i];
                                   var detalleMensual = detallesMensual[i];
                                    tabla.tBodies[0].insertRow(i);
                                    var cabecera = document.createElement("th");
                                    cabecera.setAttribute('scope', 'row');
                                    cabecera.setAttribute('align','right');
                                    cabecera.innerHTML = codigoCuenta.childNodes[0].firstChild.nodeValue;
                                    
                                    tabla.tBodies[0].rows[i].appendChild(cabecera);
                                    tabla.tBodies[0].rows[i].insertCell(1);
                                    tabla.tBodies[0].rows[i].cells[1].innerHTML = codigoCuenta.childNodes[1].firstChild.nodeValue;
                                    tabla.tBodies[0].rows[i].insertCell(2);
                                    tabla.tBodies[0].rows[i].cells[2].innerHTML = nomCentroCosto.firstChild.nodeValue;
                                    var contadorCeldaTabla =3;
                                    //var sw=1;
                                    for(var j =contadorInicioDetalles;j<contadorFinDetalles;j++){
                                    //alert("j="+j);
                                        tabla.tBodies[0].rows[i].insertCell(contadorCeldaTabla);
                                        tabla.tBodies[0].rows[i].insertCell(contadorCeldaTabla+1);
                                        tabla.tBodies[0].rows[i].insertCell(contadorCeldaTabla+2);
                                        tabla.tBodies[0].rows[i].cells[contadorCeldaTabla].innerHTML =parseFloat(valorPresupuestoMes[j].firstChild.nodeValue);
                                        tabla.tBodies[0].rows[i].cells[contadorCeldaTabla+1].innerHTML =parseFloat(valorEjecutadoMes[j].firstChild.nodeValue);
                                        tabla.tBodies[0].rows[i].cells[contadorCeldaTabla+2].innerHTML =parseFloat(saldoMes[j].firstChild.nodeValue);
                                        contadorCeldaTabla=contadorCeldaTabla+3;
                                    }
                                    var contadorFinDetallesTmp = contadorFinDetalles;
                                    contadorFinDetalles = contadorFinDetalles +12;
                                    contadorInicioDetalles=contadorFinDetallesTmp;
                                }
                                
                                bloquearControles();
                                mostrarTituloReporte();
                                
                                if(estado.firstChild.nodeValue==2){
                                    //document.getElementById("bttnAprobarPresupuesto").disabled=true;
                                    //document.getElementById("bttnIniciarEjecucion").disabled = false;
                                    //document.getElementById("txtEstadoPresupuesto").value = "Aprobado";
                                    tablaencabezadoPresupuesto.rows[3].cells[0].innerHTML = "Aprobado";
                                }else{
                                    if(estado.firstChild.nodeValue==1){
                                    //document.getElementById("txtEstadoPresupuesto").value = "No aprobado";
                                    tablaencabezadoPresupuesto.rows[3].cells[0].innerHTML = "No aprobado";
                                    //document.getElementById("bttnIniciarEjecucion").disabled = true;
                                    }else{
                                        if(estado.firstChild.nodeValue==3){
                                        //document.getElementById("txtEstadoPresupuesto").value = "En ejecución";                                        
                                        tablaencabezadoPresupuesto.rows[3].cells[0].innerHTML = "En ejecución";
//                                        document.getElementById("bttnAprobarPresupuesto").disabled=true;
                                        
                                        }else{
                                            if(estado.firstChild.nodeValue==4){
                                            tablaencabezadoPresupuesto.rows[3].cells[0].innerHTML = "Ejecutado";
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
        
        /*funcion para mostrar el titulo del reporte*/
        function mostrarTituloReporte(){
            var tablaTituloReporte = document.getElementById("tblTituloBusqueda");
            var tablaTituloReporte2 = document.getElementById("tblTituloBusqueda2");
            var cboConsolidadoCuentas = document.getElementById("txtRdBttnBusquedaCuentas");
            var cboListaCentrosCostos = document.getElementById("txtRdBttnBusquedaCentros");
            var cboCentroCostosEspecifico = document.getElementById("txtRdBttnBusquedaCentroCostosEspecifico");
            var cboCentroCostoyCuenta = document.getElementById("txtRdBttnBusquedaCuentaEspecifica");
             var cboAnual = document.getElementById("radioBttnAnual");
            if(cboConsolidadoCuentas.checked){
            tablaTituloReporte.rows[0].cells[0].innerHTML="REPORTE DEL PRESUPUESTO "+document.getElementById("txtCodigoCuenta").value.toUpperCase()+" POR CUENTAS";
            }else{
                if(cboListaCentrosCostos.checked){
                tablaTituloReporte.rows[0].cells[0].innerHTML="REPORTE DEL PRESUPUESTO "+document.getElementById("txtCodigoCuenta").value.toUpperCase()+" POR CUENTAS Y CENTROS DE COSTOS";
                }else{
                    if(cboCentroCostosEspecifico.checked){
                        var cbo=document.getElementById("cboCentrosDeCostos");
                        var valorCctos = parseInt(cbo.options[cbo.selectedIndex].value);
                        tablaTituloReporte.rows[0].cells[0].innerHTML="REPORTE DEL PRESUPUESTO "+document.getElementById("txtCodigoCuenta").value.toUpperCase()+" DEL CENTRO DE COSTOS "+vectorNombreCentrosCostos[valorCctos];
                    }else{
                        if(cboCentroCostoyCuenta.checked){
                            var cbo=document.getElementById("cboCentrosDeCostos");
                            var cbo2 = document.getElementById("cboCuentasCentrosCostos");
                            var valorCctos = parseInt(cbo.options[cbo.selectedIndex].value);
                            var valorCtas = parseInt(cbo2.options[cbo2.selectedIndex].value);
                            tablaTituloReporte.rows[0].cells[0].innerHTML="REPORTE DEL PRESUPUESTO "+document.getElementById("txtCodigoCuenta").value.toUpperCase()+" DEL CENTRO DE COSTOS "+vectorNombreCentrosCostos[valorCctos]+" Y LA CUENTA "+vectorNombreCuentas[valorCtas];
                            
                        }else{
                            if(cboAnual.checked){
                                tablaTituloReporte.rows[0].cells[0].innerHTML="REPORTE DEL PRESUPUESTO ANUAL POR CUENTAS Y CENTROS DE COSTOS";
                            }
                        }
                    }
                }
            }

        }
        
        /*funcion utilizada para preparar el query que se enviara al servidor para ser procesado*/
        function crearQuery(){
            var codigo = document.getElementById("txtCodigoCuenta").value;
            var trimestre = document.getElementById("cboPeriodoAVisualizar").value;
            var cboConsolidadoCuentas = document.getElementById("txtRdBttnBusquedaCuentas");
            var cboListaCentrosCostos = document.getElementById("txtRdBttnBusquedaCentros");
            var cboCentroCostosEspecifico = document.getElementById("txtRdBttnBusquedaCentroCostosEspecifico");
            var cboCentroCostoyCuenta = document.getElementById("txtRdBttnBusquedaCuentaEspecifica");
            var cboAnual = document.getElementById("radioBttnAnual");
            if(cboConsolidadoCuentas.checked && !cboAnual.checked){
            return "txtAccion="+encodeURIComponent(30)+
            "&txtCodigoRefernciaPresupuestoBuscar="+encodeURIComponent(codigo)+
            "&txtTimestre="+encodeURIComponent(trimestre)+
            "&nocache=" + Math.random();
            }else{
                if(cboListaCentrosCostos.checked && !cboAnual.checked){
                return "txtAccion="+encodeURIComponent(27)+
                "&txtCodigoRefernciaPresupuestoBuscar="+encodeURIComponent(codigo)+
                "&txtTimestre="+encodeURIComponent(trimestre)+
                "&nocache=" + Math.random();
                }else{
                    if(cboCentroCostosEspecifico.checked && !cboAnual.checked){
                    var cbo=document.getElementById("cboCentrosDeCostos");
                    var valorCBO = parseInt(cbo.options[cbo.selectedIndex].value);
                        return "txtAccion="+encodeURIComponent(31)+
                        "&txtCodigoRefernciaPresupuestoBuscar="+encodeURIComponent(codigo)+
                        "&cboCentrosDeCostos="+encodeURIComponent(valorCBO)+
                        "&txtTimestre="+encodeURIComponent(trimestre)+
                        "&nocache=" + Math.random();
                    }else{
                        if(cboCentroCostoyCuenta.checked && !cboAnual.checked){
                            var cbo=document.getElementById("cboCentrosDeCostos");
                            var cbo2 = document.getElementById("cboCuentasCentrosCostos");
                            var valorCBO = parseInt(cbo.options[cbo.selectedIndex].value);
                            var valorCBO2 =parseInt(cbo2.options[cbo2.selectedIndex].value);
                            return "txtAccion="+encodeURIComponent(32)+
                            "&txtCodigoRefernciaPresupuestoBuscar="+encodeURIComponent(codigo)+
                            "&cboCtas="+encodeURIComponent(valorCBO2)+
                            "&cboCentrosDeCostos="+encodeURIComponent(valorCBO)+
                            "&txtTimestre="+encodeURIComponent(trimestre)+
                            "&nocache=" + Math.random();    
                        }else{
                            if(cboAnual.checked && cboListaCentrosCostos.checked){
                                return "txtAccion="+encodeURIComponent(36)+
                                "&txtCodigoRefernciaPresupuestoBuscar="+encodeURIComponent(codigo)+
                                "&txtTimestre="+encodeURIComponent(trimestre)+
                                "&nocache=" + Math.random();
                            }else{
                                if(cboAnual.checked && cboConsolidadoCuentas.checked){
                                return "txtAccion="+encodeURIComponent(37)+
                                "&txtCodigoRefernciaPresupuestoBuscar="+encodeURIComponent(codigo)+
                                "&nocache=" + Math.random();
                                }else{
                                    if(cboAnual.checked && cboCentroCostosEspecifico.checked){
                                        var cbo=document.getElementById("cboCentrosDeCostos");
                                        var valorCBO = parseInt(cbo.options[cbo.selectedIndex].value);
                                        return "txtAccion="+encodeURIComponent(38)+
                                        "&txtCodigoRefernciaPresupuestoBuscar="+encodeURIComponent(codigo)+
                                        "&cboCentrosDeCostos="+encodeURIComponent(valorCBO)+
                                        "&nocache=" + Math.random();
                                    }else{
                                        if(cboAnual.checked && cboCentroCostoyCuenta.checked){
                                            var cbo=document.getElementById("cboCentrosDeCostos");
                                            var cbo2 = document.getElementById("cboCuentasCentrosCostos");
                                            var valorCBO = parseInt(cbo.options[cbo.selectedIndex].value);
                                            var valorCBO2 =parseInt(cbo2.options[cbo2.selectedIndex].value);
                                            return "txtAccion="+encodeURIComponent(39)+
                                            "&txtCodigoRefernciaPresupuestoBuscar="+encodeURIComponent(codigo)+
                                            "&cboCtas="+encodeURIComponent(valorCBO2)+
                                            "&cboCentrosDeCostos="+encodeURIComponent(valorCBO)+
                                            "&nocache=" + Math.random();   
                                        
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        /*fnción utilizada para bloquear controles de la pagina jsp*/
        
        /*función utilizada para limpiar los datos de la pagina jsp*/
        function limpiarDatos(){
        location.reload(true);
            //document.getElementById("txtCodigoCuenta").value="";
            /*var tablaencabezadoPresupuesto = document.getElementById("encabezadoPresupuesto");
            tablaencabezadoPresupuesto.rows[1].cells[0].innerHTML ="";
            tablaencabezadoPresupuesto.rows[1].cells[1].innerHTML ="";
            tablaencabezadoPresupuesto.rows[1].cells[2].innerHTML ="";
            tablaencabezadoPresupuesto.rows[1].cells[3].innerHTML ="";
            tablaencabezadoPresupuesto.rows[1].cells[4].innerHTML ="";
            tablaencabezadoPresupuesto.rows[3].cells[0].innerHTML ="";*/
            /*document.getElementById("txtFechaInicioEjecucion").value="yyyy-mm-dd";
            document.getElementById("txtFechaFinEjecucion").value="yyyy-mm-dd";
            document.getElementById("txtFechaLimiteAprobacion").value="yyyy-mm-dd";
            document.getElementById("txtValorPresupuestoGlobal").value="0";
            document.getElementById("txtEstadoPresupuesto").value="";*/
            /*limpiarDatosTabla();
            limpiarCombox();
            desbloquearControles();*/
        }

/*función utilizada para limpiar los datos que contenga la tabla*/
        function limpiarDatosTabla(){
            var tabla = document.getElementById('tablaControl'); 
            //alert("numero de Filas = "+tabla.tBodies[0].rows.length);
            var numeroFilas = tabla.tBodies[0].rows.length;
                for(var i=(numeroFilas-1);i>=0;i--){
                    tabla.tBodies[0].deleteRow(i);
                }
        }

/*funciçon para desbloquear los controles de la tabla*/
        function desbloquearControles(){
            document.getElementById("txtCodigoCuenta").disabled=false;
            document.getElementById("cboPeriodoAVisualizar").disabled=false;
            //document.getElementById("bttnCancelar").disabled=true;
            document.getElementById("txtRdBttnBusquedaCuentas").disabled=true;
            document.getElementById("txtRdBttnBusquedaCentros").disabled=true;
            document.getElementById("txtRdBttnBusquedaCentroCostosEspecifico").disabled=true;
            document.getElementById("txtRdBttnBusquedaCuentaEspecifica").disabled=true;
            document.getElementById("txtRdBttnBusquedaCentroCostosEspecifico").checked = false;
            document.getElementById("txtRdBttnBusquedaCuentaEspecifica").checked = false;
            document.getElementById("txtRdBttnBusquedaCuentas").checked = true;
            document.getElementById("cboCentrosDeCostos").disabled = true;
            document.getElementById("cboCuentasCentrosCostos").disabled = true;
            document.getElementById("bttnBuscar").disabled=true;
            document.getElementById("bttnImprimirReporte").disabled = true;
        }

/*funciçon utilizada para por el control radio button cuando se seleccione*/
function llenarCombox(){
//alert("entro");
//activarBotones();
ocultarTablaAnual();
    var idPpto = document.getElementById("txtCodigoCuenta").value;
    document.getElementById("bttnBuscar").disabled = true;
    document.getElementById("cboCuentasCentrosCostos").disabled = true;
    

         if(idPpto!==""){
        peticion = inicializar_xhr();
                		var inicio = document.getElementById("cboCentrosDeCostos");
				inicio.options[0] = new Option("- cargando... -");
			if(peticion) {
				peticion.onreadystatechange = llenarControlCombox;
                                //var valor = parseInt(document.getElementById("txtAccion"));
                                //alert(valor.value);
                                var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_centro_costo_servlet?txtAccion=5&idPresupuesto="+idPpto+"&nocache="+Math.random();
                                peticion.open("GET",cad, true);
				//peticion.open("GET","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_centro_costo_servlet?txtAccion=5&idPresupuesto="+idPpto+"&nocache="+Math.random(), true);
				//peticion.send("txtAccion=" + encodeURIComponent(valor));
                                peticion.send(null);
			}
        }else{
            alert("por favor escoja un presupuesto");
        }
			//document.getElementById("cboCentrosDeCostos").onchange = muestraValor;
}


function activarComboxDepende(){
//alert("entro");
    var radioCentroCosto = document.getElementById("txtRdBttnBusquedaCentroCostosEspecifico");
    var radioCuenta = document.getElementById("txtRdBttnBusquedaCuentaEspecifica");
    if(radioCentroCosto.checked){
        document.getElementById("bttnBuscar").disabled = false;
        //document.getElementById("bttnBuscar").disabled = false;
        //document.getElementById("cboCuentasCentrosCostos").disabled = false;
    }else{
        if(radioCuenta.checked){
            verValor();
            document.getElementById("cboCuentasCentrosCostos").disabled =false ;
            document.getElementById("bttnBuscar").disabled = true;
        }
    }
}

function llenarControlCombox() {

        if (peticion.readyState == 4) {
                if (peticion.status == 200) {
                //alert("200");
                        var lista = document.getElementById("cboCentrosDeCostos");
                        var documento_xml = peticion.responseXML;
//				alert(documento_xml.getElementById(""));
                        var centrosDeCostos = documento_xml.getElementsByTagName("centroDeCostos")[0];
                        var losCentrosDeCostos = centrosDeCostos.getElementsByTagName("centroDeCosto");
                        lista.options[0] = new Option("- selecciona un centro de costos -");
                        vectorNombreCentrosCostos=new Array();
                        // Método 1: Crear elementos Option() y añadirlos a la lista
                                for(i=0; i<losCentrosDeCostos.length; i++) {
                                        var codigo = losCentrosDeCostos[i].getElementsByTagName("codigo")[0].firstChild.nodeValue;
                                        var nombre = losCentrosDeCostos[i].getElementsByTagName("nombre")[0].firstChild.nodeValue;
                                        lista.options[i+1] = new Option(nombre, codigo);
                                        vectorNombreCentrosCostos[codigo]=nombre;
                                }
                              document.getElementById("cboCentrosDeCostos").disabled = false;
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

function limpiarCombox(){
                    var lista2 = document.getElementById("cboCentrosDeCostos");
                    var listaCuentas = document.getElementById("cboCuentasCentrosCostos");
                    document.getElementById("cboCentrosDeCostos").disabled=false;
                var numeroOpciones = lista2.getElementsByTagName("option");
                var numeroOpcionesCuentas = listaCuentas.getElementsByTagName("option");
                //alert("numero de Opciones = " +numeroOpciones.length);
                //listaCuentas.removeChild(numeroOpcionesCuentas);
                for(j=0;j<numeroOpcionesCuentas.length;j++){
                     listaCuentas.options[j] = new Option();
                }
                
                    for(i=0;i<numeroOpciones.length;i++){
                       // alert("i= " +i);
                        lista2.options[i] = new Option();
                    }
                    
                
           
}
        
        /*funciçon utilizada para ver el valor de la opciçon seleccionada en el combox de centros de costos
        y enseguida llenar el combox de cuentas*/
        function verValor(){
            var cboCentroCostosEspecifico = document.getElementById("txtRdBttnBusquedaCentroCostosEspecifico");
            var cboCentroCostoyCuenta = document.getElementById("txtRdBttnBusquedaCuentaEspecifica");
                if(cboCentroCostoyCuenta.checked){
                    var cbo=document.getElementById("cboCentrosDeCostos");
        //                alert(parseInt(cbo.options[cbo.selectedIndex].value));
                        var valorPresupuesto = document.getElementById("txtCodigoCuenta").value;
                        var valorCctos = parseInt(cbo.options[cbo.selectedIndex].value);
          //              alert("valor presupuesto="+valorPresupuesto+" valorCctos = "+valorCctos);
                                peticion = inicializar_xhr();
                        		var inicio = document.getElementById("cboCuentasCentrosCostos");
                    			inicio.options[0] = new Option("- cargando... -");
                                        if(peticion) {
                                                peticion.onreadystatechange = mostrarCuentasCombox;
                                                var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_cuenta_servlet?txtAccion=8&idPresupuesto="+valorPresupuesto+"&idCctos="+valorCctos+"&nocache="+Math.random();
                                                peticion.open("GET",cad, true);
                                		//peticion.open("GET","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_cuenta_servlet?txtAccion=8&idPresupuesto="+valorPresupuesto+"&idCctos="+valorCctos+"&nocache="+Math.random(), true);
				                peticion.send(null);
                                        }
                }
        }
        
/*funciçon utilizada para formatear la respuesta del servidor cuando se solicite
las cuentas que pertenecen a un centro de costo*/
function mostrarCuentasCombox(){
    if (peticion.readyState == 4) {
		if (peticion.status == 200) {
			var lista = document.getElementById("cboCuentasCentrosCostos");
			var documento_xml = peticion.responseXML;
			var centrosDeCostos = documento_xml.getElementsByTagName("centroDeCostos")[0];
			var losCentrosDeCostos = centrosDeCostos.getElementsByTagName("centroDeCosto");
			lista.options[0] = new Option("- selecciona una cuenta -");
                        vectorNombreCuentas = new Array();
        			for(i=0; i<losCentrosDeCostos.length; i++) {
					var codigo = losCentrosDeCostos[i].getElementsByTagName("codigo")[0].firstChild.nodeValue;
					var nombre = losCentrosDeCostos[i].getElementsByTagName("nombre")[0].firstChild.nodeValue;
					lista.options[i+1] = new Option(nombre, codigo);
                                        vectorNombreCuentas[codigo] = nombre;
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

function desactivarControles(){
//alert("entro en evento");
    document.getElementById("cboCentrosDeCostos").disabled =true;
    document.getElementById("cboCuentasCentrosCostos").disabled =true;
    var presupuesto = document.getElementById("txtCodigoCuenta").value;
    ocultarTablaAnual();

}

function activarBotonBuscar(){
document.getElementById("bttnBuscar").disabled = false;
document.getElementById("cboCuentasCentrosCostos").disabled = true;
document.getElementById("cboCentrosDeCostos").disabled = true;
    document.getElementById("txtRdBttnBusquedaCuentas").disabled =true;
    document.getElementById("txtRdBttnBusquedaCentros").disabled =true;
    document.getElementById("txtRdBttnBusquedaCentroCostosEspecifico").disabled =true;
    document.getElementById("txtRdBttnBusquedaCuentaEspecifica").disabled =true;
}

function imprimirReporte(){
var valorCboReporteAnual=document.getElementById("radioBttnAnual");
//alert(valorCboReporteAnual.checked);
if(valorCboReporteAnual.checked){
    imprSelec("reporteanual");
}else{
    imprSelec("reporte");
}
}

function imprSelec(nombre){
  var ficha = document.getElementById(nombre);
  
var ventimp = window.open("","xxx");
  ventimp.document.write(ficha.innerHTML);
  ventimp.document.close();
  ventimp.print();
  ventimp.close();
}
      function mostrarTablaAnual(){
      var valorRadioButton =document.getElementById("radioBttnAnual");
        if(valorRadioButton.checked){
        document.getElementById("reporteAnual").style.display = "block";
        document.getElementById("reporte").style.display = "none";
        desactivarRadioButtonBusqueda();
        document.getElementById("cboPeriodoAVisualizar").disabled=true;
        }
      }  
      
    function ocultarTablaAnual(){
      var valorRadioButton =document.getElementById("radioBttnAnual");
      var valorRadioButtonTrimestral =document.getElementById("txtRdBttnBusquedaTrimestral");
        if(!valorRadioButton.checked && valorRadioButtonTrimestral.checked){
        document.getElementById("reporteAnual").style.display = "none";
        document.getElementById("reporte").style.display = "block";
        desactivarRadioButtonBusqueda();
        document.getElementById("cboPeriodoAVisualizar").disabled=false;
        
        }
      } 
      
      function desactivarRadioButtonBusqueda(){
      if(document.getElementById("txtCodigoCuenta").value!==""){
        document.getElementById("bttnBuscar").disabled = false;
      }
        //
        //document.getElementById("cboCuentasCentrosCostos").disabled = false;
        //document.getElementById("cboCentrosDeCostos").disabled = false;
        document.getElementById("txtRdBttnBusquedaCuentas").disabled =false;
        document.getElementById("txtRdBttnBusquedaCentros").disabled =false;
        document.getElementById("txtRdBttnBusquedaCentroCostosEspecifico").disabled =false;
        document.getElementById("txtRdBttnBusquedaCuentaEspecifica").disabled =false;
      }
	window.onload = function() {
                                 document.getElementById("txtCodigoCuenta").onkeyup = autocompleta;
                                 document.getElementById("cboPeriodoAVisualizar").onchange =cambiarEncabezadoTabla;
                                 document.getElementById("bttnBuscar").onclick=iniciarBusqueda;
                                 document.getElementById("bttnCancelar").onclick = limpiarDatos;
                                 document.getElementById("txtRdBttnBusquedaCentroCostosEspecifico").onclick=llenarCombox;
                                 document.getElementById("txtRdBttnBusquedaCuentaEspecifica").onclick=llenarCombox;
                                 document.getElementById("cboCentrosDeCostos").onchange = activarComboxDepende;
                                 document.getElementById("txtRdBttnBusquedaCuentas").onclick=desactivarControles;
                                 document.getElementById("txtRdBttnBusquedaCentros").onclick=desactivarControles;
                                 document.getElementById("cboCuentasCentrosCostos").onchange=activarBotonBuscar;
                                 document.getElementById("bttnImprimirReporte").onclick = imprimirReporte;
                                 document.getElementById("radioBttnAnual").onclick = mostrarTablaAnual;
                                 document.getElementById("txtRdBttnBusquedaTrimestral").onclick = ocultarTablaAnual;
                                 document.getElementById("reporteAnual").style.display = "none";
             
	}
