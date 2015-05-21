var peticion = null;
var mesEjecucion = null;
var tmpValorTotal = null;
var valorTotalEjecutado = new Array();
var vectorPresupuestos = new Array();
var vectorPresupuestadoPresupuesto = new Array();
var vectorEjecutadoPresupuesto = new Array();
var vectorSaldoPresupuesto = new Array();
var vectorCuentas = new Array();
var vectorValoresPresupuesto = new Array();
var vectorValoresEjecutado = new Array();
var vectorSaldo = new Array();

var vectorFechaInicioEjecucion = new Array();
var vectorFechaFinEjecucion = new Array();

/*función para prepara peticiones por medio de AJAX*/
function inicializa_xhr() {
        if (window.XMLHttpRequest) {
                return new XMLHttpRequest();
        } else if (window.ActiveXObject) {
                return new ActiveXObject("Microsoft.XMLHTTP");
        }
}

/*función utilizada para llenar el combox de los presupuestos que se encuentran en ejecución*/	
function llenarComboxPresupuestosEnEjecucion(){
    peticion = inicializa_xhr();
    var inicio = document.getElementById("cboPresupuestosEjecucion");
    inicio.options[0] = new Option("- cargando... -");
    if(peticion) {
        peticion.onreadystatechange = mostrarPresupuestosEnEjeucion;
        var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_presupuesto_servlet";
        peticion.open("POST",cad, true);
        //peticion.open("POST","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_presupuesto_servlet", true);
        peticion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        var query = crearQuery();
        peticion.send(query);
    }
}

/*funcion para crear los parametros que se enviaran al servidor*/
function crearQuery(){
    return "txtAccion="+encodeURIComponent(28)+
    "&nocache=" + Math.random();
}

/*funciín para formatear respuesta del servidor para llenar Combox de presupuestos en ejecución*/     
function mostrarPresupuestosEnEjeucion() {
        if (peticion.readyState == 4) {
                if (peticion.status == 200) {
                        var lista = document.getElementById("cboPresupuestosEjecucion");
                        var documento_xml = peticion.responseXML;
                        var centrosDeCostos = documento_xml.getElementsByTagName("centroDeCostos")[0];
                        var losCentrosDeCostos = centrosDeCostos.getElementsByTagName("centroDeCosto");
                        lista.options[0] = new Option("-seleccionar presupuesto -");
                        // Método 1: Crear elementos Option() y añadirlos a la lista
                        vectorPresupuestos = new Array();
                        vectorPresupuestadoPresupuesto = new Array();
                        vectorEjecutadoPresupuesto = new Array();
                        vectorSaldoPresupuesto = new Array();
                        vectorFechaInicioEjecucion = new Array();
                        vectorFechaFinEjecucion = new Array();
                                for(i=0; i<losCentrosDeCostos.length; i++) {
                                        var codigo = losCentrosDeCostos[i].getElementsByTagName("codigo")[0].firstChild.nodeValue;
                                        var nombre = losCentrosDeCostos[i].getElementsByTagName("nombre")[0].firstChild.nodeValue;
                                        vectorPresupuestos[codigo] = nombre;
  //                                      alert("valor ="+codigo+"nombre ="+nombre);
                                        lista.options[i+1] = new Option(nombre, codigo);
                                        vectorPresupuestadoPresupuesto[codigo] = losCentrosDeCostos[i].getElementsByTagName("valorPresupuestado")[0].firstChild.nodeValue;
    //                                    alert("codigo ="+codigo+"valor Presupuestado ="+vectorPresupuestadoPresupuesto[codigo]);
                                        vectorEjecutadoPresupuesto[codigo] = losCentrosDeCostos[i].getElementsByTagName("valorEjecutado")[0].firstChild.nodeValue;
                                        vectorSaldoPresupuesto[codigo] = losCentrosDeCostos[i].getElementsByTagName("saldo")[0].firstChild.nodeValue;
                                        vectorFechaInicioEjecucion[codigo]=losCentrosDeCostos[i].getElementsByTagName("fechaInicio")[0].firstChild.nodeValue;
                                        vectorFechaFinEjecucion[codigo]=losCentrosDeCostos[i].getElementsByTagName("fechaFin")[0].firstChild.nodeValue;
                                        

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

/*funcion utilizada para hacer la peticion al servidor 
sobre los centros de costos disponibles para un presupuesto escogido 
en el combox de presupuestos*/
function llenarComboxCentroDeCostos(){
//alert("entro");
//buscarValores();

document.getElementById("cboPresupuestosEjecucion").disabled=true;
document.getElementById("cboCentroCostos").disabled=false;
var cboPresupuesto = document.getElementById("cboPresupuestosEjecucion");
//var  = document.getElementById("txtCodigoCuenta").value;
var valorPresupuesto = parseInt(cboPresupuesto.options[cboPresupuesto.selectedIndex].value);
document.getElementById("txtFechaInicio").value=vectorFechaInicioEjecucion[valorPresupuesto];
document.getElementById("txtFechaFin").value=vectorFechaFinEjecucion[valorPresupuesto];
//validarFechaInicio();
//validarFechas();
//alert(valorPresupuesto);
    //var idPpto = document.getElementById("cboPresupuestosEjecucion").value;
         //alert("valor id ="+idPpto);
        peticion = inicializa_xhr();
        var inicio = document.getElementById("cboCentroCostos");
	inicio.options[0] = new Option("- cargando... -");
	if(peticion) {
		peticion.onreadystatechange = llenarControlCombox;
                var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_centro_costo_servlet?txtAccion=6&idPresupuesto="+valorPresupuesto+"&nocache="+Math.random();
                peticion.open("GET",cad, true);
        	//peticion.open("GET","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_centro_costo_servlet?txtAccion=6&idPresupuesto="+valorPresupuesto+"&nocache="+Math.random(), true);
	        peticion.send(null);
	}
}


/*función que formatea el resultado enviado por el servidor de la peticion llenarComboxCentroDeCostos()*/
function llenarControlCombox() {
        if (peticion.readyState == 4) {
                if (peticion.status == 200) {
                        var lista = document.getElementById("cboCentroCostos");
                        var documento_xml = peticion.responseXML;
                        var centrosDeCostos = documento_xml.getElementsByTagName("centroDeCostos")[0];
                        var losCentrosDeCostos = centrosDeCostos.getElementsByTagName("centroDeCosto");
                        lista.options[0] = new Option("- selecciona un centro de costos -");
                        for(i=0; i<losCentrosDeCostos.length; i++) {
                             var codigo = losCentrosDeCostos[i].getElementsByTagName("codigo")[0].firstChild.nodeValue;
                             var nombre = losCentrosDeCostos[i].getElementsByTagName("nombre")[0].firstChild.nodeValue;
                             lista.options[i+1] = new Option(nombre, codigo);
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

/*funcion para hacer peticion asincrona al servidor 
buscando las cuentas disponibles para el centro de costos seleccionado*/
function peticionComboxCuentas(){
document.getElementById("cboCentroCostos").disabled=true;
var cboPresupuesto = document.getElementById("cboPresupuestosEjecucion");
document.getElementById("cboCuentasPresupuesto").disabled=false;
//var  = document.getElementById("txtCodigoCuenta").value;
var valorPresupuesto = parseInt(cboPresupuesto.options[cboPresupuesto.selectedIndex].value);
var cboCentroCostos = document.getElementById("cboCentroCostos");

//var  = document.getElementById("txtCodigoCuenta").value;
var valorCentroCostos = parseInt(cboCentroCostos.options[cboCentroCostos.selectedIndex].value);
    //var idPpto = document.getElementById("cboPresupuestosEjecucion").value;
         //alert("valor id ="+idPpto);
        peticion = inicializa_xhr();
        var inicio = document.getElementById("cboCuentasPresupuesto");
	inicio.options[0] = new Option("- cargando... -");
	if(peticion) {
		peticion.onreadystatechange = llenarControlComboxCuentas;
                var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_cuenta_servlet?txtAccion=9&idPresupuesto="+valorPresupuesto+"&idCentroCostos="+valorCentroCostos+"&nocache="+Math.random();
                peticion.open("GET",cad, true);                
        	//peticion.open("GET","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_cuenta_servlet?txtAccion=9&idPresupuesto="+valorPresupuesto+"&idCentroCostos="+valorCentroCostos+"&nocache="+Math.random(), true);
	        peticion.send(null);
	}
}

/*funcion que procesa la respuesta del servidor para la peticion de cuentas
del centro de costo seleccionado*/
function llenarControlComboxCuentas(){
        if (peticion.readyState == 4) {
                if (peticion.status == 200) {
                        var lista = document.getElementById("cboCuentasPresupuesto");
                        var documento_xml = peticion.responseXML;
                        var centrosDeCostos = documento_xml.getElementsByTagName("centroDeCostos")[0];
                        var losCentrosDeCostos = centrosDeCostos.getElementsByTagName("centroDeCosto");
                        lista.options[0] = new Option("- selecciona una cuenta -");
                        vectorValoresPresupuesto = new Array();
                        vectorCuentas = new Array();
                        vectorValoresEjecutado = new Array();
                        vectorSaldo = new Array();
                        for(i=0; i<losCentrosDeCostos.length; i++) {
                             var codigo = losCentrosDeCostos[i].getElementsByTagName("codigo")[0].firstChild.nodeValue;
                             var nombre = losCentrosDeCostos[i].getElementsByTagName("nombre")[0].firstChild.nodeValue;
                             lista.options[i+1] = new Option(nombre, codigo);
                                        var valPpto =losCentrosDeCostos[i].getElementsByTagName("valorPresupuestado")[0].firstChild.nodeValue;
                                        var valEjctd =losCentrosDeCostos[i].getElementsByTagName("valorEjecutado")[0].firstChild.nodeValue;
                                        var valSal =losCentrosDeCostos[i].getElementsByTagName("saldo")[0].firstChild.nodeValue;
                                        vectorCuentas[codigo] = nombre;
                                        //alert("vectorCuentas="+vectorCuentas[codigo]);
                                        vectorValoresPresupuesto[codigo] = valPpto;
                                        //alert("vectorValoresPresupuesto[codigo]"+vectorValoresPresupuesto[codigo])
                                        vectorValoresEjecutado[codigo] = valEjctd;
                                        //alert("vectorValoresEjecutado[codigo]"+vectorValoresEjecutado[codigo]);
                                        vectorSaldo[codigo] = valSal;
                                        //alert("vectorSaldo[codigo]"+vectorSaldo[codigo]);
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
   
   
        
function muestraSubCentrosDeCostos() {
        if (peticion.readyState == 4) {
                if (peticion.status == 200) {
                //alert("200");
                        var lista = document.getElementById("cboCuentasPresupuesto");
                        var documento_xml = peticion.responseXML;
//				alert(documento_xml.getElementById(""));
                        var centrosDeCostos = documento_xml.getElementsByTagName("subcentroDeCostos")[0];
                        var losCentrosDeCostos = centrosDeCostos.getElementsByTagName("subcentroDeCosto");
                        lista.options[0] = new Option("- selecciona una cuenta -");
                        // Método 1: Crear elementos Option() y añadirlos a la lista
                        vectorValoresPresupuesto = new Array();
                        vectorCuentas = new Array();
                        vectorValoresEjecutado = new Array();
                        vectorSaldo = new Array();
                                for(i=0; i<losCentrosDeCostos.length; i++) {
                                        var codigo = losCentrosDeCostos[i].getElementsByTagName("codigo")[0].firstChild.nodeValue;
                                        var nombre = losCentrosDeCostos[i].getElementsByTagName("nombre")[0].firstChild.nodeValue;
                                        var valPpto =losCentrosDeCostos[i].getElementsByTagName("valorPresupuestado")[0].firstChild.nodeValue;
                                        var valEjctd =losCentrosDeCostos[i].getElementsByTagName("valorEjecutado")[0].firstChild.nodeValue;
                                        var valSal =losCentrosDeCostos[i].getElementsByTagName("saldo")[0].firstChild.nodeValue;
                                        lista.options[i+1] = new Option(nombre, codigo);
      //                                  alert("valor ="+codigo+"nombre ="+nombre);
                                        vectorCuentas[codigo] = nombre;
                                        vectorValoresPresupuesto[codigo] = valPpto;
                                        vectorValoresEjecutado[codigo] = valEjctd;
                                        vectorSaldo[codigo] = valSal;
        //                                alert(losCentrosDeCostos[i].getElementsByTagName("valorPresupuestado")[0].firstChild.nodeValue);
                                        
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



function buscarSubCentroCostos(){
//alert("entro");
            buscarValoresPresupuesto();
                var lista2 = document.getElementById("cboCuentasPresupuesto");
                var numeroOpciones = lista2.getElementsByTagName("option");
                //alert("numero de Opciones = " +numeroOpciones.length);
                
                for(i=0;i<numeroOpciones.length;i++){
                    //alert("i= " +i);
                    lista2.options[i] = new Option();
                }
                /*var centroCostoEscogido = document.getElementById("cboCentrosDeCostos");
                var numeroCentroCosto = parseInt(centroCostoEscogido.options[centroCostoEscogido.selectedIndex].value);
                alert(numeroCentroCosto);*/
                /*var inicio = document.getElementById("cboSubcentroCostos");
                alert(inicio.getElementsByTagName("options").length) ;*/
                
                /*alert(inicio.getElementsByTagName("options").length) ;*/
                
                /*var numeroDeOpciones =inicio.getElementsByTagName("option");
                alert(numeroDeOpciones.length);
                for(i=0; i<numeroDeOpciones.length; i++){
                
                }*/
                var inicio = document.getElementById("cboCuentasPresupuesto");
                var cboCentro = document.getElementById("cboPresupuestosEjecucion");
		var codigoCentroCosto = parseInt(cboCentro.options[cboCentro.selectedIndex].value);
		//alert("ultimo ="+codigoCentroCosto);
                peticion = inicializa_xhr();
                inicio.options[0] = new Option("- cargando... -");
                if(peticion) {
                                peticion.onreadystatechange = muestraSubCentrosDeCostos;
                                //var valor = parseInt(document.getElementById("txtAccion"));
                                //alert(valor.value);
                                var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_cuenta_servlet";
                                peticion.open("POST",cad, true);
				//peticion.open("POST","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_cuenta_servlet", true);
                                peticion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                var query = crearQueryCuenta();
                                //alert("query="+ query) ;
				//peticion.send("txtAccion=" + encodeURIComponent(valor));
                                peticion.send(query);
                }
//inicio.disabled = false;

}

function mostrarValor(){
    var cctos = document.getElementById("cboCentrosDeCostos");
    var scctos = document.getElementById("cboSubcentroCostos");
    var codigoCentroCosto = parseInt(cctos.options[cctos.selectedIndex].value);
    var c = parseInt(scctos.options[scctos.selectedIndex].value);
    alert("codigo centro costo = "+codigoCentroCosto+" codigo scctos = "+c);
    var codCuenta = document.getElementById("txtCodigoCuenta");
    var nomCuenta = document.getElementById("txtNombreCuenta");
    var estadoCuenta = document.getElementById("cboEstado");
    codCuenta.disabled = false;
    nomCuenta.disabled = false;
    estadoCuenta.disabled = false;
    //txtCodigoCuenta,txtNombreCuenta,cboEstado,bttnGuardar,bttnResetear
}

function activarBotones(){
    var codCuenta = document.getElementById("txtCodigoCuenta").value;
    var nomCuenta = document.getElementById("txtNombreCuenta").value;
    alert(document.getElementById("txtCodigoCuenta").value);
        if(codCuenta!= null && codCuenta!=NaN && codCuenta!="" && nomCuenta!=null && nomCuenta !=NaN && nomCuenta != "" ){
                var accionBotonAdicionar =document.getElementById("bttnGuardar");
                accionBotonAdicionar.disabled = false;
            
        }else{
        var accionBotonAdicionar =document.getElementById("bttnGuardar");
                accionBotonAdicionar.disabled = true;
        }
        
        
}



function crearQueryCuenta(){
var codigo = document.getElementById("cboPresupuestosEjecucion").value;
return "txtAccion="+encodeURIComponent(7)+
"&txtCodPresupuesto=" + codigo+
"&nocache=" + Math.random();
}

function buscarValores(){
    //alert("hola");
    document.getElementById("cboCuentasPresupuesto").disabled=true;
    document.getElementById("bttnCargarEjecucion").disabled = false;
    var cuenta = document.getElementById("cboCuentasPresupuesto").value;
    document.getElementById("txtValorPresupuestado").value = parseFloat(vectorValoresPresupuesto[cuenta]);
    document.getElementById("txtValorEjecutado").value = parseFloat(vectorValoresEjecutado[cuenta]);
    document.getElementById("txtSaldo").value = parseFloat(vectorSaldo[cuenta]);
    document.getElementById("txtValorEjecucion").disabled = false;
    //document.getElementById("bttnAdicionarFila").disabled = false;
    //alert("nombre y valor del combox cuentas"+vectorCuentas[cuenta]+"y"+cuenta);
}

function buscarValoresPresupuesto(){
var codigo = document.getElementById("cboPresupuestosEjecucion").value;
alert(codigo);
alert(vectorPresupuestadoPresupuesto[codigo]);
//document.getElementById("txtValorPresupuestoGlobal").value= vectorPresupuestadoPresupuesto[codigo];
//document.getElementById("txtValorEjecutadoGlobal").value=vectorEjecutadoPresupuesto[codigo];
}

function armarDetallesXML(){
    valorTotalEjecutado = new Array();
    tmpValorTotal = 0;
    var tabla = document.getElementById('tablaEjecuciones');
    var numeroDeFilasCuentas =tabla.tBodies[0].rows.length;
    alert("numero de Filas Cuentas = "+numeroDeFilasCuentas);
    var cadenaCuenta="";
    if(numeroDeFilasCuentas!==0){
        for(i=0;i<numeroDeFilasCuentas;i++){
            var codCuenta = tabla.tBodies[0].rows[i].cells[1].innerHTML;
            var valorACargar = tabla.tBodies[0].rows[i].cells[4].innerHTML;
            var numMes = mesEjecucion;
            //alert("<detalles codigoCuenta='"+codCuenta+"' valorACargar='"+valorACargar+"' numeroMes='"+numMes+"'></detalles>");
            cadenaCuenta = cadenaCuenta +"<detalles codigoCuenta='"+codCuenta+"' valorACargar='"+valorACargar+"' numeroMes='"+numMes+"'></detalles>";
            tmpValorTotal= tmpValorTotal+parseFloat(valorACargar);
        }
    }
    return cadenaCuenta;
}

function enviarXML(){


var detallesXML = armarDetallesXML();
var codigoCboPresupuesto = document.getElementById("cboPresupuestosEjecucion").value;
//alert(vectorPresupuestos[codigoCboPresupuesto]+" valor total ejecutado="+tmpValorTotal);

alert(detallesXML);
var xml = "<presupuesto codigoReferencia='"+vectorPresupuestos[codigoCboPresupuesto]+"' valorTotalEjecutado='"+tmpValorTotal+"'>"+detallesXML+"</presupuesto>";
alert("cadenaFinalXML"+xml);
var cadenaServidor = "txtAccion=" +encodeURIComponent(1)+
"&cadenaXML=" +encodeURIComponent(xml);
peticion = inicializa_xhr();
    if(peticion){
            peticion.onreadystatechange =probarConexion;
            var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_detalle_ejecucion_servlet";
            peticion.open("POST",cad, true);
             //peticion.open("POST","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_detalle_ejecucion_servlet",true);
            peticion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            var query_string = cadenaServidor;

	    peticion.send(query_string);

           
    }
/*var fechaInicio = document.getElementById("txtFechaInicioEjecucion").value;
var fechaFin = document.getElementById("txtFechaFinEjecucion").value;
var fechaLimite = document.getElementById("txtFechaLimiteAprobacion").value;
var prioridad = document.getElementById("cboPrioridad").value;
var valorGlobalPresupuestado = document.getElementById("txtValorPresupuestoGlobal").value;
var operacion = document.getElementById("txtOperacion").value;

var xml = "<presupuesto codigoReferencia='"+codigoReferencia+"' fechaInicio='"+fechaInicio+"' fechaFin='"+fechaFin+"' fechaLimite='"+fechaLimite+"' prioridad='"+prioridad+"' valorPresupuestado='"+valorGlobalPresupuestado+"' operacion='"+operacion+"'>"+detallesXML+"</presupuesto>";
var cadenaServidor = "txtAccion=" +encodeURIComponent(20)+
"&cadenaXML=" +encodeURIComponent(xml);


peticion = inicializa_xhr();
    if(peticion){
            peticion.onreadystatechange =probarConexion;

             peticion.open("POST","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_presupuesto_servlet",true);
            peticion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            var query_string = cadenaServidor;

	    peticion.send(query_string);

           
    }*/
}

function validarValorACargar(){
    var val = document.getElementById("txtValorEjecucion").value;
    //alert(val);
    if(typeof val == "undefined" || isNaN(val)){
        if(val<0){
        document.getElementById("txtValorEjecucion").style.borderColor='red';
        alert("verfique el valor digitado no se admiten valores negativos");
        }
    }else{
        if(validarFechas()==1 && val>0){
            guardarEjecucion();
        }else{
            if(validarFechas()==1 && val<0){
                document.getElementById("txtValorEjecucion").style.borderColor='red';
                alert("verfique el valor digitado no se admiten valores negativos");
            }
        }
    //crearQueryEjecucion();
    }
    
}


function guardarEjecucion(){
    peticion = inicializa_xhr();
    if(peticion){
            peticion.onreadystatechange =comprobarTransaccion;
            var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_detalle_ejecucion_servlet";
            peticion.open("POST",cad, true);
             //peticion.open("POST","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_detalle_ejecucion_servlet",true);
            peticion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            var query_string = crearQueryEjecucion();

	    peticion.send(query_string);

           
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

function crearQueryEjecucion(){
var fecha = document.getElementById("txtFecha");
var presupuesto = document.getElementById("cboPresupuestosEjecucion");
var centroCosto = document.getElementById("cboCentroCostos");
var cuenta = document.getElementById("cboCuentasPresupuesto");
var Ejecucion = parseFloat(document.getElementById("txtValorEjecucion").value);

var valorFecha = fecha.value;
var valorCodigoPresupuesto=parseInt(presupuesto.options[presupuesto.selectedIndex].value);
var valorCodigoCentroCosto=parseInt(centroCosto.options[centroCosto.selectedIndex].value);
var valorCodigoCuenta=parseInt(cuenta.options[cuenta.selectedIndex].value);

//parseInt(cboCentro.options[cboCentro.selectedIndex].value)


return "txtAccion="+encodeURIComponent(2)+
"&txtFecha=" +encodeURIComponent(valorFecha)+
"&cboPresupuestosEjecucion="+encodeURIComponent(valorCodigoPresupuesto)+
"&cboCentroCostos="+encodeURIComponent(valorCodigoCentroCosto)+
"&cboCuentasPresupuesto="+encodeURIComponent(valorCodigoCuenta)+
"&txtValorEjecucion="+encodeURIComponent(Ejecucion)+
"&nocache=" + Math.random();
}

function recargarPagina(){
    location.reload(true);
}

function validarFechas(){
    if(validarFechaInicio()==1 && validarFechaFin()==1){
    //alert("cargar Ejecución");
    document.getElementById("txtFecha").style.borderColor='';
    //.style.border
    return 1;
    }else{
        document.getElementById("txtFecha").style.borderColor='red';
        alert("para cargar la ejecución la fecha de la ejecución debe estar entre la fecha de inicio y fecha de fin del presupuesto");
        return 2;
    }
    /*alert(validarFechaInicio());
    alert(validarFechaFin());*/
}

function validarFechaInicio(){
var respuesta;
    var fechaInicioEjecucion = document.getElementById("txtFechaInicio").value;
    //var fechaFinEjecucion = document.getElementById("txtFechaFin").value;
    var fechaEjecucion = document.getElementById("txtFecha").value;
    
    annoInicioEjecucion=fechaInicioEjecucion.substring(0,4);
    mesInicioEjecucion=fechaInicioEjecucion.substring(5,7);
    diaInicioEjecucion=fechaInicioEjecucion.substring(8,10);
    annoEjecucion=fechaEjecucion.substring(0,4);
    mesEjecucion=fechaEjecucion.substring(5,7);
    diaEjecucion=fechaEjecucion.substring(8,10);
    //alert("año inicio ejecucion ="+annoInicioEjecucion+" mes Inicio Ejecucion="+mesInicioEjecucion+"dia Inicio Ejecucion="+diaInicioEjecucion);
    //alert("año ejecucion="+annoEjecucion+" mesEjecucion="+mesEjecucion+" diaEjecucion="+diaEjecucion)
    if(annoInicioEjecucion==annoEjecucion){
    //alert("años iguales");
        if(mesInicioEjecucion==mesEjecucion){
      //  alert("meses Iguales");
            if(diaInicioEjecucion==diaEjecucion) {
            //aceptar
        //    alert("dias Iguales");
              respuesta = 1;  
            }else{
                if(diaInicioEjecucion<diaEjecucion){
          //      alert("diaInicio<diaEjecucion");
                //aceptar
                respuesta = 1;  
                }else{
            //    alert("diaInicio>diaEjecucion");
                //no aceptar
                respuesta = 2;  
                }
            }
        }else{
            if(mesInicioEjecucion<mesEjecucion){
            //aceptar
                respuesta = 1;  
            }else{
            respuesta = 2;  
            }
        }
    }else{
        if(annoInicioEjecucion<annoEjecucion){
        //aceptar
        respuesta = 1;  
        }else{
            if(annoInicioEjecucion>annoEjecucion){
            //no aceptar
            respuesta = 2;  
            }
        }
    }
    return respuesta;
    
    
}

function validarFechaFin(){
var respuesta;
    var fechaFinEjecucion = document.getElementById("txtFechaFin").value;
    //var fechaFinEjecucion = document.getElementById("txtFechaFin").value;
    var fechaEjecucion = document.getElementById("txtFecha").value;
    
    annoFinEjecucion=fechaFinEjecucion.substring(0,4);
    mesFinEjecucion=fechaFinEjecucion.substring(5,7);
    diaFinEjecucion=fechaFinEjecucion.substring(8,10);
    annoEjecucion=fechaEjecucion.substring(0,4);
    mesEjecucion=fechaEjecucion.substring(5,7);
    diaEjecucion=fechaEjecucion.substring(8,10);
    //alert("año inicio ejecucion ="+annoInicioEjecucion+" mes Inicio Ejecucion="+mesInicioEjecucion+"dia Inicio Ejecucion="+diaInicioEjecucion);
    //alert("año ejecucion="+annoEjecucion+" mesEjecucion="+mesEjecucion+" diaEjecucion="+diaEjecucion)
    if(annoFinEjecucion==annoEjecucion){
    //alert("años iguales");
        if(mesFinEjecucion==mesEjecucion){
      //  alert("meses Iguales");
            if(diaFinEjecucion==diaEjecucion) {
            //aceptar
        //    alert("dias Iguales");
              respuesta = 1;  
            }else{
                if(diaFinEjecucion>diaEjecucion){
          //      alert("diaInicio<diaEjecucion");
                //aceptar
                respuesta = 1;  
                }else{
            //    alert("diaInicio>diaEjecucion");
                //no aceptar
                respuesta = 2;  
                }
            }
        }else{
            if(mesFinEjecucion>mesEjecucion){
            //aceptar
                respuesta = 1;  
            }else{
            respuesta = 2;  
            }
        }
    }else{
        if(annoFinEjecucion>annoEjecucion){
        //aceptar
        respuesta = 1;  
        }else{
            if(annoFinEjecucion<annoEjecucion){
            //no aceptar
            respuesta = 2;  
            }
        }
    }
    return respuesta;
}
	window.onload = function() {
		llenarComboxPresupuestosEnEjecucion();
                        var fecha = new Date();
                        var anno = fecha.getFullYear();
                        var mes = fecha.getMonth()+1;
                        mesEjecucion = mes;
                        var dia = fecha.getDate();
                        var fechaCompleta = anno+"-"+mes+"-"+dia;
                        document.getElementById("txtFecha").value = fechaCompleta;
			document.getElementById("cboPresupuestosEjecucion").onchange = llenarComboxCentroDeCostos;
                        //document.getElementById("cboPresupuestosEjecucion").onchange = validarFechaInicio;
                        
                        document.getElementById("cboCuentasPresupuesto").onchange = buscarValores;
                        document.getElementById("bttnCargarEjecucion").onclick = validarValorACargar;
                        //document.getElementById("bttnGuardarPresupuesto").onclick =enviarXML;
                        //document.getElementById("bttnBuscarDetalles").onclick = buscarEnTablaEjecucionesModificar;
                        //document.getElementById("bttnActualizar").onclick = modificarDatos;
                        //document.getElementById("bttnQuitar").onclick =eliminarFilaModificar;
                        document.getElementById("cboCentroCostos").onchange = peticionComboxCuentas;
                        document.getElementById("bttnCancelarEjecucion").onclick = recargarPagina;
	}