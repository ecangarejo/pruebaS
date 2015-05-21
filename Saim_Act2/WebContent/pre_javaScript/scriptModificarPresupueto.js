var peticion = null;
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
    var inicio = document.getElementById("cboPresupuesto");
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

/*función para crear el query para buscar presupuestos*/
function crearQuery(){
    return "txtAccion="+encodeURIComponent(34)+
    "&nocache=" + Math.random();
}



/*funciín para formatear respuesta del servidor para llenar Combox de presupuestos en ejecución*/     
function mostrarPresupuestosEnEjeucion() {
        if (peticion.readyState == 4) {
                if (peticion.status == 200) {
                        var lista = document.getElementById("cboPresupuesto");
                        var documento_xml = peticion.responseXML;
                        var centrosDeCostos = documento_xml.getElementsByTagName("centroDeCostos")[0];
                        var losCentrosDeCostos = centrosDeCostos.getElementsByTagName("centroDeCosto");
                        lista.options[0] = new Option("-seleccionar presupuesto -");
                        // Método 1: Crear elementos Option() y añadirlos a la lista
                        //vectorPresupuestos = new Array();
                        //vectorPresupuestadoPresupuesto = new Array();
                        //vectorEjecutadoPresupuesto = new Array();
                        //vectorSaldoPresupuesto = new Array();
                                for(i=0; i<losCentrosDeCostos.length; i++) {
                                        var codigo = losCentrosDeCostos[i].getElementsByTagName("codigo")[0].firstChild.nodeValue;
                                        var nombre = losCentrosDeCostos[i].getElementsByTagName("nombre")[0].firstChild.nodeValue;
                                        //vectorPresupuestos[codigo] = nombre;
  //                                      alert("valor ="+codigo+"nombre ="+nombre);
                                        lista.options[i+1] = new Option(nombre, codigo);
                                        //vectorPresupuestadoPresupuesto[codigo] = losCentrosDeCostos[i].getElementsByTagName("valorPresupuestado")[0].firstChild.nodeValue;
    //                                    alert("codigo ="+codigo+"valor Presupuestado ="+vectorPresupuestadoPresupuesto[codigo]);
                                        //vectorEjecutadoPresupuesto[codigo] = losCentrosDeCostos[i].getElementsByTagName("valorEjecutado")[0].firstChild.nodeValue;
                                        //vectorSaldoPresupuesto[codigo] = losCentrosDeCostos[i].getElementsByTagName("saldo")[0].firstChild.nodeValue;

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
document.getElementById("cboPresupuesto").disabled = true;
var cboPresupuesto = document.getElementById("cboPresupuesto");
//var  = document.getElementById("txtCodigoCuenta").value;
var valorPresupuesto = parseInt(cboPresupuesto.options[cboPresupuesto.selectedIndex].value);
    //var idPpto = document.getElementById("cboPresupuestosEjecucion").value;
         //alert("valor id ="+idPpto);
        peticion = inicializa_xhr();
        var inicio = document.getElementById("cboCentroCostos");
	inicio.options[0] = new Option("- cargando... -");
	if(peticion) {
		peticion.onreadystatechange = llenarControlCombox;
                var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_centro_costo_servlet?txtAccion=7&idPresupuesto="+valorPresupuesto+"&nocache="+Math.random();
                peticion.open("GET",cad, true);
        	//peticion.open("GET","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_centro_costo_servlet?txtAccion=7&idPresupuesto="+valorPresupuesto+"&nocache="+Math.random(), true);
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
                        document.getElementById("cboCentroCostos").disabled = false;
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
var cboPresupuesto = document.getElementById("cboPresupuesto");
//var  = document.getElementById("txtCodigoCuenta").value;
var valorPresupuesto = parseInt(cboPresupuesto.options[cboPresupuesto.selectedIndex].value);
var cboCentroCostos = document.getElementById("cboCentroCostos");
document.getElementById("cboCentroCostos").disabled = true;

//var  = document.getElementById("txtCodigoCuenta").value;
var valorCentroCostos = parseInt(cboCentroCostos.options[cboCentroCostos.selectedIndex].value);
    //var idPpto = document.getElementById("cboPresupuestosEjecucion").value;
         //alert("valor id ="+idPpto);
        peticion = inicializa_xhr();
        var inicio = document.getElementById("cboSeleccionarCuentas");
	inicio.options[0] = new Option("- cargando... -");
	if(peticion) {
		peticion.onreadystatechange = llenarControlComboxCuentas;
        	peticion.open("GET","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_cuenta_servlet?txtAccion=10&idPresupuesto="+valorPresupuesto+"&idCentroCostos="+valorCentroCostos+"&nocache="+Math.random(), true);
	        peticion.send(null);
	}
}


/*funcion que procesa la respuesta del servidor para la peticion de cuentas
del centro de costo seleccionado*/
function llenarControlComboxCuentas(){
        if (peticion.readyState == 4) {
                if (peticion.status == 200) {
                        var lista = document.getElementById("cboSeleccionarCuentas");
                        var documento_xml = peticion.responseXML;
                        var centrosDeCostos = documento_xml.getElementsByTagName("centroDeCostos")[0];
                        var losCentrosDeCostos = centrosDeCostos.getElementsByTagName("centroDeCosto");
                        lista.options[0] = new Option("- selecciona una Cuenta -");
                        /*vectorValoresPresupuesto = new Array();
                        vectorCuentas = new Array();
                        vectorValoresEjecutado = new Array();
                        vectorSaldo = new Array();*/
                        for(i=0; i<losCentrosDeCostos.length; i++) {
                             var codigo = losCentrosDeCostos[i].getElementsByTagName("codigo")[0].firstChild.nodeValue;
                             var nombre = losCentrosDeCostos[i].getElementsByTagName("nombre")[0].firstChild.nodeValue;
                             lista.options[i+1] = new Option(nombre, codigo);
                                        var valPpto =losCentrosDeCostos[i].getElementsByTagName("valorPresupuestado")[0].firstChild.nodeValue;
                                        var valEjctd =losCentrosDeCostos[i].getElementsByTagName("valorEjecutado")[0].firstChild.nodeValue;
                                        var valSal =losCentrosDeCostos[i].getElementsByTagName("saldo")[0].firstChild.nodeValue;
                                        //vectorCuentas[codigo] = nombre;
                                        //alert("vectorCuentas="+vectorCuentas[codigo]);
                                        //vectorValoresPresupuesto[codigo] = valPpto;
                                        //alert("vectorValoresPresupuesto[codigo]"+vectorValoresPresupuesto[codigo])
                                        //vectorValoresEjecutado[codigo] = valEjctd;
                                        //alert("vectorValoresEjecutado[codigo]"+vectorValoresEjecutado[codigo]);
                                        //vectorSaldo[codigo] = valSal;
                                        //alert("vectorSaldo[codigo]"+vectorSaldo[codigo]);
                        }
                        document.getElementById("cboSeleccionarCuentas").disabled = false;
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

/*función para buscar detalles del presupuesto*/
function buscarDetallesPresupuesto(){
    peticion = inicializa_xhr();
    var inicio = document.getElementById("cboPresupuesto");
    inicio.options[0] = new Option("- cargando... -");
    if(peticion) {
        peticion.onreadystatechange = mostrarPresupuestosAModificar;
        var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_ejecucion_servlet";
        peticion.open("POST",cad, true);
        //peticion.open("POST","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_ejecucion_servlet", true);
        peticion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        var query = crearQueryBuscarDetalles();
        //alert(query);
        peticion.send(query);
    }
}



function crearQueryBuscarDetalles(){
var cboPresupuesto =document.getElementById("cboPresupuesto");
var cboCentroCosto =document.getElementById("cboCentroCostos");
var cboCuentas=document.getElementById("cboSeleccionarCuentas") ;

var valorCboPresupuesto = parseInt(cboPresupuesto.options[cboPresupuesto.selectedIndex].value);
var valorCboCentroCosto = parseInt(cboCentroCosto.options[cboCentroCosto.selectedIndex].value);
var valorCuentas = parseInt(cboCuentas.options[cboCuentas.selectedIndex].value);


        return "txtAccion="+encodeURIComponent(3)+
        "&idPpto="+encodeURIComponent(valorCboPresupuesto)+
        "&idCta="+encodeURIComponent(valorCboCentroCosto)+
        "&idCCtos="+encodeURIComponent(valorCuentas)+
    "&nocache=" + Math.random();
}


function llamarValidarMesesModificar(){
    if(validarMeseModificar()==1){
        alert("por favor verifique los datos ingresados");
    }else{
        //alert("actualizar presupuesto");
        actualizarPresupuesto();
        recargarPagina();
    }
}
function validarMeseModificar(){
var sw=0;
try{
var mesEnero = parseFloat(document.getElementById("txtModificarEnero").value);
var mesFebrero = parseFloat(document.getElementById("txtModificarFebrero").value);
var mesMarzo = parseFloat(document.getElementById("txtModificarMarzo").value);
var mesAbril = parseFloat(document.getElementById("txtModificarAbril").value);
var mesMayo = parseFloat(document.getElementById("txtModificarMayo").value);
var mesJunio = parseFloat(document.getElementById("txtModificarJunio").value);
var mesJulio = parseFloat(document.getElementById("txtModificarJulio").value);
var mesAgosto = parseFloat(document.getElementById("txtModificarAgosto").value);
var mesSeptiembre = parseFloat(document.getElementById("txtModificarSeptiembre").value);
var mesOctubre = parseFloat(document.getElementById("txtModificarOctubre").value);
var mesNoviembre = parseFloat(document.getElementById("txtModificarNoviembre").value);
var mesDiciembre = parseFloat(document.getElementById("txtModificarDiciembre").value);
//.value
if(isNaN(mesEnero)){
    document.getElementById("txtModificarEnero").style.borderColor='red';
    sw=1;
}else{
    document.getElementById("txtModificarEnero").style.borderColor='';
}
if(isNaN(mesFebrero)){
document.getElementById("txtModificarFebrero").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarFebrero").style.borderColor='';
}

if(isNaN(mesMarzo)){
document.getElementById("txtModificarMarzo").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarMarzo").style.borderColor='';
}
if(isNaN(mesAbril)){
document.getElementById("txtModificarAbril").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarAbril").style.borderColor='';
}

if(isNaN(mesMayo)){
document.getElementById("txtModificarMayo").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarMayo").style.borderColor='';
}

if(isNaN(mesJunio)){
document.getElementById("txtModificarJunio").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarJunio").style.borderColor='';
}

if(isNaN(mesJulio)){
document.getElementById("txtModificarJulio").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarJulio").style.borderColor='';
}

if(isNaN(mesAgosto)){
document.getElementById("txtModificarAgosto").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarAgosto").style.borderColor='';
}
if(isNaN(mesSeptiembre)){
document.getElementById("txtModificarSeptiembre").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarSeptiembre").style.borderColor='';
}

if(isNaN(mesOctubre)){
document.getElementById("txtModificarOctubre").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarOctubre").style.borderColor='';
}

if(isNaN(mesNoviembre)){
document.getElementById("txtModificarNoviembre").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarNoviembre").style.borderColor='';
}

if(isNaN(mesDiciembre)){
document.getElementById("txtModificarDiciembre").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarDiciembre").style.borderColor='';
}

return sw;
}catch(excepcion){
    alert(excepcion);
}
}

/*funciín para formatear respuesta del servidor para llenar Combox de presupuestos en ejecución*/     
function mostrarPresupuestosAModificar() {
        if (peticion.readyState == 4) {
                if (peticion.status == 200) {
                        //var lista = document.getElementById("cboPresupuestosEjecucion");
                        var documento_xml = peticion.responseXML;
                        var detalles = documento_xml.getElementsByTagName("detalles")[0];
                        var detalleMensual = detalles.getElementsByTagName("detalleMensual")[0];
                        var valorMensual = detalles.getElementsByTagName("valorPresupuestoMes");
                        document.getElementById("txtModificarEnero").value= parseFloat(valorMensual[0].firstChild.nodeValue);
                        document.getElementById("txtModificarFebrero").value= parseFloat(valorMensual[1].firstChild.nodeValue);
                        document.getElementById("txtModificarMarzo").value= parseFloat(valorMensual[2].firstChild.nodeValue);
                        document.getElementById("txtModificarAbril").value= parseFloat(valorMensual[3].firstChild.nodeValue);
                        document.getElementById("txtModificarMayo").value= parseFloat(valorMensual[4].firstChild.nodeValue);
                        document.getElementById("txtModificarJunio").value= parseFloat(valorMensual[5].firstChild.nodeValue);
                        document.getElementById("txtModificarJulio").value= parseFloat(valorMensual[6].firstChild.nodeValue);
                        document.getElementById("txtModificarAgosto").value= parseFloat(valorMensual[7].firstChild.nodeValue);
                        document.getElementById("txtModificarSeptiembre").value= parseFloat(valorMensual[8].firstChild.nodeValue);
                        document.getElementById("txtModificarOctubre").value= parseFloat(valorMensual[9].firstChild.nodeValue);
                        document.getElementById("txtModificarNoviembre").value= parseFloat(valorMensual[10].firstChild.nodeValue);
                        document.getElementById("txtModificarDiciembre").value= parseFloat(valorMensual[11].firstChild.nodeValue);
                        bloquearControles();
                        

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
       
       
       function bloquearControles(){
       
       
        document.getElementById("cboPresupuesto").disabled =true;
        document.getElementById("cboCentroCostos").disabled =true;
        document.getElementById("cboSeleccionarCuentas").disabled =true;
        document.getElementById("bttnMostrarDetalles").disabled =true;
        document.getElementById("txtModificarEnero").disabled =false;
        document.getElementById("txtModificarFebrero").disabled =false;
        document.getElementById("txtModificarMarzo").disabled =false;
        document.getElementById("txtModificarAbril").disabled =false;
        document.getElementById("txtModificarMayo").disabled =false;
        document.getElementById("txtModificarJunio").disabled =false;
        document.getElementById("txtModificarJulio").disabled =false;
        document.getElementById("txtModificarAgosto").disabled =false;
        document.getElementById("txtModificarSeptiembre").disabled =false;
        document.getElementById("txtModificarOctubre").disabled =false;
        document.getElementById("txtModificarNoviembre").disabled =false;
        document.getElementById("txtModificarDiciembre").disabled =false;
        document.getElementById("bttnActualizar").disabled =false;
        document.getElementById("bttnCancelarModificar").disabled =false;
        //document.getElementById("").disabled =true;
        //document.getElementById("").disabled =true;
       } 
       
       function actualizarPresupuesto(){
                peticion = inicializa_xhr();
                if(peticion) {
                    peticion.onreadystatechange = mostrarRespuesta;
                    peticion.open("POST","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_ejecucion_servlet", true);
                    peticion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    var query = crearQueryActualizarDatos();
                    //alert(query);
                    peticion.send(query);
                }
       }
       
       function crearQueryActualizarDatos(){
        var cboPresupuesto =document.getElementById("cboPresupuesto");
        var cboCentroCosto =document.getElementById("cboCentroCostos");
        var cboCuentas=document.getElementById("cboSeleccionarCuentas") ;
        
        var valorCboPresupuesto = parseInt(cboPresupuesto.options[cboPresupuesto.selectedIndex].value);
        var valorCboCentroCosto = parseInt(cboCentroCosto.options[cboCentroCosto.selectedIndex].value);
        var valorCuentas = parseInt(cboCuentas.options[cboCuentas.selectedIndex].value);
        var valorMesEne = parseFloat(document.getElementById("txtModificarEnero").value);
        var valorMesFeb = parseFloat(document.getElementById("txtModificarFebrero").value);
        var valorMesMar = parseFloat(document.getElementById("txtModificarMarzo").value);
        var valorMesAbr = parseFloat(document.getElementById("txtModificarAbril").value);
        var valorMesMay = parseFloat(document.getElementById("txtModificarMayo").value);
        var valorMesJun = parseFloat(document.getElementById("txtModificarJunio").value);
        var valorMesJul = parseFloat(document.getElementById("txtModificarJulio").value);
        var valorMesAgo = parseFloat(document.getElementById("txtModificarAgosto").value);
        var valorMesSep = parseFloat(document.getElementById("txtModificarSeptiembre").value);
        var valorMesOct = parseFloat(document.getElementById("txtModificarOctubre").value);
        var valorMesNov = parseFloat(document.getElementById("txtModificarNoviembre").value);
        var valorMesDic = parseFloat(document.getElementById("txtModificarDiciembre").value);
        return "txtAccion="+encodeURIComponent(4)+
        "&idPpto="+encodeURIComponent(valorCboPresupuesto)+
        "&idCta="+encodeURIComponent(valorCuentas)+
        "&idCCtos="+encodeURIComponent(valorCboCentroCosto)+
        "&enero="+encodeURIComponent(valorMesEne)+
        "&febrero="+encodeURIComponent(valorMesFeb)+
        "&marzo="+encodeURIComponent(valorMesMar)+
        "&abril="+encodeURIComponent(valorMesAbr)+
        "&mayo="+encodeURIComponent(valorMesMay)+
        "&junio="+encodeURIComponent(valorMesJun)+
        "&julio="+encodeURIComponent(valorMesJul)+
        "&agosto="+encodeURIComponent(valorMesAgo)+
        "&septiembre="+encodeURIComponent(valorMesSep)+
        "&octubre="+encodeURIComponent(valorMesOct)+
        "&noviembre="+encodeURIComponent(valorMesNov)+
        "&diciembre="+encodeURIComponent(valorMesDic)+
        "&nocache=" + Math.random();
       }
       
       
       function mostrarRespuesta(){
                if (peticion.readyState == 4) {
			if (peticion.status == 200) {
                            try {
                            var texto = peticion.responseText;
                            
                            if(texto==1){
                            alert("presupuesto Modificado");
                            }else{
                            alert("no se pudo modificar el presupuesto")
                            }
                            }catch(excepcion) {
                                alert(excepcion);
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

function recargarPagina(){
    location.reload(true);
}

function desbloquearControles(){
document.getElementById("bttnMostrarDetalles").disabled = false;
document.getElementById("cboSeleccionarCuentas").disabled = true;
}
        
	window.onload = function() {
         llenarComboxPresupuestosEnEjecucion();
         document.getElementById("cboPresupuesto").onchange = llenarComboxCentroDeCostos;
         document.getElementById("cboCentroCostos").onchange = peticionComboxCuentas;
            document.getElementById("bttnMostrarDetalles").onclick = buscarDetallesPresupuesto;
            document.getElementById("bttnActualizar").onclick =llamarValidarMesesModificar;
            document.getElementById("bttnCancelarModificar").onclick=recargarPagina;
            document.getElementById("cboSeleccionarCuentas").onchange=desbloquearControles;
            /*document.getElementById("").onclick=limpiarDatos;
            document.getElementById("").onclick=buscarEnTablaDetallesPresupuestos;
            document.getElementById("").onclick=limpiarDatosModificar;
            document.getElementById("").onclick = modificarDatos;
            document.getElementById("").onclick = enviarXML;*/
            //document.getElementById("bttnAprobarPresupuesto").onclick=iniciarAprobacion;
	}