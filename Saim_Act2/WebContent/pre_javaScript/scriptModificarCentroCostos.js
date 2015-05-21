var elementoSeleccionado = -1;
var sugerencias = null;
var cacheSugerencias = {};
var peticion = null;

/*función para inicializar la petición mediante ajax*/
function inicializa_xhr() {
    if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}
/*función utilizada por la caja de texto txtCodigoCuenta
para llamar utilizando ajax al servidor buscado los posibles valores
de la cuenta digitada*/
function autocompleta() {
//alert("entro en autocompleta");
    var elEvento = arguments[0] || window.event;
    var tecla = elEvento.keyCode;
    //alert(tecla);
        if(tecla == 40) { // Flecha Abajo
        //alert("presiono abajo");
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
                var texto = document.getElementById("txtCodigoCentroCostos").value;
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
                                //if(peticion.responseText){
                                sugerencias = eval('('+peticion.responseText+')');
                                //alert(sugerencias.length);
                                if(sugerencias.length == 0) {
                                sinResultados();
                                //alert("exitos");
                                }
                                else {
                                    //sugerencias.texto;
                                    cacheSugerencias[texto] = sugerencias;
                                    actualizaSugerencias();
                                }
                           /* }else{
                                sinResultados();
                            }*/
                            }
                        }
                };
//peticion.open('POST', ' http://127.0.0.1:8988/PruebasAjax-PruebasAJAX-context-root/servletautocompletar?nocache='+Math.random(), true);
 //peticion.open('POST', 'http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_cuenta_servlet', true);
 //var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_centro_costo_servlet";
 //alert(cad);
 //peticion.open('POST',cad, true);
//peticion.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//var querys = "txtAccion="+encodeURIComponent(8)+"&txtCodigoReferencia="+encodeURIComponent(texto)+"&nocache="+Math.random();
//peticion.send(querys);
                var txtAccion="8";  
                peticion.open("POST","pre_centro_costo_servlet?txtAccion="+txtAccion+"&txtCodigoReferencia="+texto+"",true);                                 
                
               // peticion.send(querys);
                peticion.send("");                
                
}
    else {
        sugerencias = cacheSugerencias[texto];
        actualizaSugerencias();
    }
}
}

/*función llamada paar mostrar en el div sugerencias los posibles valores
que puede tomar el codigo de la cuenta*/
function muestraSugerencias() {
    var zonaSugerencias = document.getElementById("sugerencias");
        zonaSugerencias.innerHTML = sugerencias.formateaLista();
        zonaSugerencias.style.display = 'block';
}

/*prototipo de el objeto div para formatear los diferentes
valores que salgan en el div sugerencias*/
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

/*función de un evento cuando se selecciona un 
dato de la lista de sugerencias que salen en el 
div sugerencias cuando se está ingresando el codigo de la cuenta*/
function seleccionaElemento() {
//alert(sugerencias[elementoSeleccionado]);
    if(sugerencias[elementoSeleccionado]) {
       document.getElementById("txtCodigoCentroCostos").value = sugerencias[elementoSeleccionado];
        borraLista();
        buscarNombreCentroCostos();
        activarControles();
        document.getElementById("txtCodigoCentroCostos").disabled = true;
    }
}


function activarControles(){
    document.getElementById("txtCodigoCentroCostos").disabled=true;
    document.getElementById("rdBttnCambiarCodigoCentroCosto").disabled = false;
    document.getElementById("txtNombreCentroCostos").disabled=false;
    document.getElementById("rdBttnEstadoCentroCostosActivo").disabled=false;
    document.getElementById("rdBttnEstadoCentroCostosInactivo").disabled=false;
    document.getElementById("bttnActualizarCentroDeCostos").disabled=false;
    document.getElementById("bttnCancelar").disabled=false;
    
    
}
/*función llamada para quitar el div cuando se selecciona 
un dato del la lista de sugerencia*/
function borraLista() {
        document.getElementById("sugerencias").innerHTML = "";
        document.getElementById("sugerencias").style.display = "none";
        //document.getElementById("bttnAdicionarFila").disabled = true;
        //limpiarTodosLosCampos();
}

/*funcion que se activa cuando 
los valores introducidos en la caja de texto no concuerdan
con la lista de sugerencias o más bien no hay
*/
function sinResultados() {
    document.getElementById("sugerencias").innerHTML = "no exite centro de Costos que empiece con este texto,verifique su información";
    document.getElementById("sugerencias").style.display = "block";
    //document.getElementById("bttnAdicionarFila").disabled = true;
}

/*función utilizada paa actualizar las sugerencias de acuerdo el texto vaya cambiando*/
function actualizaSugerencias() {
elementoSeleccionado = -1;
muestraSugerencias();
}

/*petición asincrona del nombre de la cuenta con el codigo
seleccionado de la lista de sugerencias*/
function buscarNombreCentroCostos(){
peticion = inicializa_xhr();
                    peticion.onreadystatechange = function() {
                        if(peticion.readyState == 4) {
                            if(peticion.status == 200) {
                                //if(peticion.responseText){
                                var documento_xml = peticion.responseXML;
//				alert(documento_xml.getElementById(""));
				//var centrosDeCostos = documento_xml.getElementsByTagName("centroDeCostos")[0];
				var cuenta = documento_xml.getElementsByTagName("centroCostos");
                                var codigo = cuenta[0].getElementsByTagName("idCentroCostos")[0].firstChild.nodeValue;
				var nombre = cuenta[0].getElementsByTagName("breCentroCostos")[0].firstChild.nodeValue;
                                var estado  = cuenta[0].getElementsByTagName("estado")[0].firstChild.nodeValue;
				document.getElementById("txtNombreCentroCostos").value = nombre;
                                //alert(estado);
                                if(estado=="A"){
                                    document.getElementById("rdBttnEstadoCentroCostosActivo").checked = true;
                                }else{
                                    if(estado="I"){
                                    document.getElementById("rdBttnEstadoCentroCostosInactivo").checked = true;
                                    }
                                }
                                //document.getElementById("txtIdCuenta").value = codigo;
                                //alert(sugerencias.length);

                           /* }else{
                                sinResultados();
                            }*/
                            }
                        }
                };
//peticion.open('POST', ' http://127.0.0.1:8988/PruebasAjax-PruebasAJAX-context-root/servletautocompletar?nocache='+Math.random(), true);
                              //  var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_centro_costo_servlet";
                              //  peticion.open("POST",cad, true);
 //peticion.open('POST', 'http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_cuenta_servlet', true);
//peticion.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//var querys = "txtAccion="+encodeURIComponent(9)+"&txtCodigoReferencia="+encodeURIComponent(document.getElementById("txtCodigoCentroCostos").value)+"&nocache="+Math.random();
//peticion.send(querys);

var txtAccion="9";  
var valor=document.getElementById("txtCodigoCentroCostos").value;
peticion.open("POST","pre_centro_costo_servlet?txtAccion="+txtAccion+"&txtCodigoReferencia="+valor+"",true);                                 

// peticion.send(querys);
peticion.send("");
}

function recargarPagina(){
location.reload(true);
}

function actualizarDatos(){
    peticion = inicializa_xhr();
    if(peticion){
            peticion.onreadystatechange =comprobarTransaccion;
           // var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_centro_costo_servlet";
            peticion.open("POST","pre_centro_costo_servlet", true);
             //peticion.open("POST","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_detalle_ejecucion_servlet",true);
            peticion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            var query_string = crearQueryActualizar();
            //alert(query_string);
	    peticion.send(query_string);

           
    }
}

function crearQueryActualizar(){
var codigoCentroCostoViejo = document.getElementById("txtCodigoCentroCostos").value;
var codigoCentroCostoNuevo = document.getElementById("txtCodigoRefNuevo").value;
var nombreCentroCosto = document.getElementById("txtNombreCentroCostos").value;
//alert(nombreCuenta);
var valorEstadoActivo = document.getElementById("rdBttnEstadoCentroCostosActivo").checked;
var valorEstadoInactivo =document.getElementById("rdBttnEstadoCentroCostosInactivo").checked;
var valorRadioButtonCambiarCodigo = document.getElementById("rdBttnCambiarCodigoCentroCosto").checked;
var estado;
//alert(valorEstadoActivo);
//alert(valorEstadoInactivo);
if(valorEstadoActivo){
estado="A";
}else{
if(valorEstadoInactivo){
estado="I";
}
}
//alert("valor codigo nuevo"+codigoCuentaNuevo+"estado ="+estado);
if(valorRadioButtonCambiarCodigo && codigoCentroCostoNuevo!==""){
return "txtAccion="+encodeURIComponent(2)+
    "&txtCodigoCentroCostoViejo="+encodeURIComponent(codigoCentroCostoViejo)+
    "&txtCodigoCentroCostoNuevo="+encodeURIComponent(codigoCentroCostoNuevo)+
    "&txtNombreCentroCosto="+encodeURIComponent(nombreCentroCosto)+
    "&cboEstado="+encodeURIComponent(estado)+
    "&nocache=" + Math.random();
}else{
    return "txtAccion="+encodeURIComponent(2)+
    "&txtCodigoCentroCostoViejo="+encodeURIComponent(codigoCentroCostoViejo)+
    "&txtCodigoCentroCostoNuevo="+encodeURIComponent(codigoCentroCostoViejo)+
    "&txtNombreCentroCosto="+encodeURIComponent(nombreCentroCosto)+
    "&cboEstado="+encodeURIComponent(estado)+
    "&nocache=" + Math.random();
    }
}


function comprobarTransaccion() {
	if (peticion.readyState == 4) {
		if (peticion.status == 200) {
                        var respuesta = peticion.responseText;
                        //alert(respuesta);
                        if(respuesta == 1){
                            alert("Transacción completada");
                            recargarPagina();
                        }else{
                            alert("transacción no realizada");
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

function activarTxtCodigoCentroCostoNuevo(){
var valorRadioButton = document.getElementById("rdBttnCambiarCodigoCentroCosto").checked;
//alert("valor "+valorRadioButton);
if(valorRadioButton){
document.getElementById("txtCodigoRefNuevo").disabled = false;
}else{
document.getElementById("txtCodigoRefNuevo").disabled =true;
}

}
        /*función que se cargar al iniciar la ejecucion de la pagina*/
	window.onload = function() {
                                 document.getElementById("txtCodigoCentroCostos").onkeyup = autocompleta;
                                 document.getElementById("rdBttnCambiarCodigoCentroCosto").onclick = activarTxtCodigoCentroCostoNuevo;
                                 document.getElementById("bttnActualizarCentroDeCostos").onclick=actualizarDatos;
                                 document.getElementById("bttnCancelar").onclick= recargarPagina;
                                 /*document.getElementById("bttnActualizarCuenta").onclick = actualizarDatos;
                                 document.getElementById("rdBttnCambiarCodigoCuenta").onclick=activarTxtCodigoCuentaNuevo;*/
	}