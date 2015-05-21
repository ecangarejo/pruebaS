var peticion = null;
var cad = "";
var arregloCentrosDeCostosNombre = new Array();
var arregloCentrosDeCostosCodigo = new Array();
var arregloTabla = new Array();
var arreglo = new Array();
var arregloParaGuardar = new Array();
var READY_STATE_COMPLETE=4;
var arregloValorPresupuestadoCuenta = new Array();
var elementoSeleccionado = -1;
var sugerencias = null;
var cacheSugerencias = {};

/*funcion utilizada por el boton Adicionar fila del jsp
para comprobar si se encuentra una fila adicionada con las mismas caracteristicas*/
function buscarEnTablaDetallePresupuesto(){
   var codigoRefCentroCosto = document.getElementById("cboCentrosDeCostos").value;
//   alert("codigoCentro Costo ="+codigoRefCentroCosto);
   var codigoRefCuenta = document.getElementById("txtCodigoCuenta").value;
  // alert("txtCodigoCuenta="+codigoRefCuenta);
   var tabla = document.getElementById("detallesPresupuesto");
   var numeroDeFilasCuentas =tabla.tBodies[0].rows.length;
   //alert("numero de Filas Cuentas="+numeroDeFilasCuentas);
   var sw=1;
   if(numeroDeFilasCuentas!==0){
    for(var j=0;j<numeroDeFilasCuentas;j++){
     //   alert("dato tabla pos="+j+" = "+tabla.tBodies[0].rows[j].cells[1].innerHTML+" == "+codigoRefCuenta);
        if(tabla.tBodies[0].rows[j].cells[0].innerHTML == codigoRefCuenta && tabla.tBodies[0].rows[j].cells[2].innerHTML == codigoRefCentroCosto){
       //     alert("encontrado en la tabla");
            sw=2;
        }
        
    }
   }
 
   if(sw==1){
        //alert("no esta en el arreglo");
        if(validarMeseAdicionar()==0){
        insertarFilaDetalle();
        limpiarTodosLosCampos();
        }
   }else{
        alert("no se puede agregar esa fila a la tabla, compruebe que no se encuentra ya adicionada");
   }
}


/*funcion para validar datos en las cajas de texto de los meses*/
function validarMeseAdicionar(){
var sw=0;
try{
var mesEnero = parseFloat(document.getElementById("txtEnero").value);
var mesFebrero = parseFloat(document.getElementById("txtFebrero").value);
var mesMarzo = parseFloat(document.getElementById("txtMarzo").value);
var mesAbril = parseFloat(document.getElementById("txtAbril").value);
var mesMayo = parseFloat(document.getElementById("txtMayo").value);
var mesJunio = parseFloat(document.getElementById("txtJunio").value);
var mesJulio = parseFloat(document.getElementById("txtJulio").value);
var mesAgosto = parseFloat(document.getElementById("txtAgosto").value);
var mesSeptiembre = parseFloat(document.getElementById("txtSeptiembre").value);
var mesOctubre = parseFloat(document.getElementById("txtOctubre").value);
var mesNoviembre = parseFloat(document.getElementById("txtNoviembre").value);
var mesDiciembre = parseFloat(document.getElementById("txtDiciembre").value);
//.value
//alert(typeof mesEnero);
if(isNaN(mesEnero) || mesEnero<0){
    document.getElementById("txtEnero").style.borderColor='red';
    sw=1;
}else{
    document.getElementById("txtEnero").style.borderColor='';
}
if(isNaN(mesFebrero) || mesFebrero<0){
document.getElementById("txtFebrero").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtFebrero").style.borderColor='';
}

if(isNaN(mesMarzo) || mesMarzo<0){
document.getElementById("txtMarzo").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtMarzo").style.borderColor='';
}
if(isNaN(mesAbril) || mesAbril<0){
document.getElementById("txtAbril").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtAbril").style.borderColor='';
}

if(isNaN(mesMayo) || mesMayo<0){
document.getElementById("txtMayo").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtMayo").style.borderColor='';
}

if(isNaN(mesJunio) || mesJunio<0){
document.getElementById("txtJunio").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtJunio").style.borderColor='';
}

if(isNaN(mesJulio) || mesJulio<0){
document.getElementById("txtJulio").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtJulio").style.borderColor='';
}

if(isNaN(mesAgosto) || mesAgosto<0){
document.getElementById("txtAgosto").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtAgosto").style.borderColor='';
}
if(isNaN(mesSeptiembre) || mesSeptiembre<0){
document.getElementById("txtSeptiembre").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtSeptiembre").style.borderColor='';
}

if(isNaN(mesOctubre) || mesOctubre<0){
document.getElementById("txtOctubre").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtOctubre").style.borderColor='';
}

if(isNaN(mesNoviembre) || mesNoviembre<0){
document.getElementById("txtNoviembre").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtNoviembre").style.borderColor='';
}

if(isNaN(mesDiciembre) || mesDiciembre<0){
document.getElementById("txtDiciembre").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtDiciembre").style.borderColor='';
}

return sw;
}catch(excepcion){
    alert(excepcion);
}
}


/*funcion para validar datos en las cajas de texto de los meses*/
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
if(isNaN(mesEnero) || mesEnero<0){
    document.getElementById("txtModificarEnero").style.borderColor='red';
    sw=1;
}else{
    document.getElementById("txtModificarEnero").style.borderColor='';
}
if(isNaN(mesFebrero) || mesFebrero<0){
document.getElementById("txtModificarFebrero").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarFebrero").style.borderColor='';
}

if(isNaN(mesMarzo) || mesMarzo<0){
document.getElementById("txtModificarMarzo").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarMarzo").style.borderColor='';
}
if(isNaN(mesAbril) || mesAbril<0){
document.getElementById("txtModificarAbril").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarAbril").style.borderColor='';
}

if(isNaN(mesMayo) || mesMayo<0){
document.getElementById("txtModificarMayo").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarMayo").style.borderColor='';
}

if(isNaN(mesJunio) || mesJunio<0){
document.getElementById("txtModificarJunio").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarJunio").style.borderColor='';
}

if(isNaN(mesJulio) || mesJunio<0){
document.getElementById("txtModificarJulio").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarJulio").style.borderColor='';
}

if(isNaN(mesAgosto) || mesAgosto<0){
document.getElementById("txtModificarAgosto").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarAgosto").style.borderColor='';
}
if(isNaN(mesSeptiembre) || mesSeptiembre<0){
document.getElementById("txtModificarSeptiembre").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarSeptiembre").style.borderColor='';
}

if(isNaN(mesOctubre) || mesOctubre<0){
document.getElementById("txtModificarOctubre").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarOctubre").style.borderColor='';
}

if(isNaN(mesNoviembre) || mesNoviembre<0){
document.getElementById("txtModificarNoviembre").style.borderColor='red';
sw=1;
}else{
document.getElementById("txtModificarNoviembre").style.borderColor='';
}

if(isNaN(mesDiciembre) || mesDiciembre<0){
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


/*metodo llamado si una fila se va a adicionar a la tabla*/
function insertarFilaDetalle(){
var tabla = document.getElementById("detallesPresupuesto");
var numeroCuenta = document.getElementById("txtCodigoCuenta").value;
var nombreCuenta = document.getElementById("txtNombreCuenta").value;
var nombreCentroCosto = arregloCentrosDeCostosNombre[document.getElementById("cboCentrosDeCostos").value];
//alert(arregloCentrosDeCostosCodigo[nombreCentroCosto]);
var mesEnero = document.getElementById("txtEnero").value;
var mesFebrero = document.getElementById("txtFebrero").value;
var mesMarzo = document.getElementById("txtMarzo").value;
var mesAbril = document.getElementById("txtAbril").value;
var mesMayo = document.getElementById("txtMayo").value;
var mesJunio = document.getElementById("txtJunio").value;
var mesJulio = document.getElementById("txtJulio").value;
var mesAgosto = document.getElementById("txtAgosto").value;
var mesSeptiembre = document.getElementById("txtSeptiembre").value;
var mesOctubre = document.getElementById("txtOctubre").value;
var mesNoviembre = document.getElementById("txtNoviembre").value;
var mesDiciembre = document.getElementById("txtDiciembre").value;
var numFilasCuerpo = tabla.tBodies[0].rows.length;
var numero = numFilasCuerpo+1;

tabla.tBodies[0].insertRow(numFilasCuerpo);
var cabecera = document.createElement("th");
cabecera.setAttribute('scope', 'row');
cabecera.setAttribute('id',document.getElementById("nombre_Cuenta").value);
arregloParaGuardar.push(document.getElementById("txtCodigoCuenta").value);
cabecera.innerHTML = numeroCuenta;
arregloTabla.push(numeroCuenta);
tabla.tBodies[0].rows[numFilasCuerpo].appendChild(cabecera);
var codigoOriginal = document.getElementById(document.getElementById("nombre_Cuenta").value);
tabla.tBodies[0].rows[numFilasCuerpo].insertCell(1);
tabla.tBodies[0].rows[numFilasCuerpo].cells[1].innerHTML = nombreCuenta;
tabla.tBodies[0].rows[numFilasCuerpo].insertCell(2);
tabla.tBodies[0].rows[numFilasCuerpo].cells[2].innerHTML = nombreCentroCosto;
tabla.tBodies[0].rows[numFilasCuerpo].insertCell(3);
tabla.tBodies[0].rows[numFilasCuerpo].cells[3].innerHTML =mesEnero ;
tabla.tBodies[0].rows[numFilasCuerpo].insertCell(4);
tabla.tBodies[0].rows[numFilasCuerpo].cells[4].innerHTML = mesFebrero ;
tabla.tBodies[0].rows[numFilasCuerpo].insertCell(5);
tabla.tBodies[0].rows[numFilasCuerpo].cells[5].innerHTML = mesMarzo ;
tabla.tBodies[0].rows[numFilasCuerpo].insertCell(6);
tabla.tBodies[0].rows[numFilasCuerpo].cells[6].innerHTML = mesAbril ;
tabla.tBodies[0].rows[numFilasCuerpo].insertCell(7);
tabla.tBodies[0].rows[numFilasCuerpo].cells[7].innerHTML = mesMayo ;
tabla.tBodies[0].rows[numFilasCuerpo].insertCell(8);
tabla.tBodies[0].rows[numFilasCuerpo].cells[8].innerHTML = mesJunio ;
tabla.tBodies[0].rows[numFilasCuerpo].insertCell(9);
tabla.tBodies[0].rows[numFilasCuerpo].cells[9].innerHTML = mesJulio ;
tabla.tBodies[0].rows[numFilasCuerpo].insertCell(10);
tabla.tBodies[0].rows[numFilasCuerpo].cells[10].innerHTML = mesAgosto ;
tabla.tBodies[0].rows[numFilasCuerpo].insertCell(11);
tabla.tBodies[0].rows[numFilasCuerpo].cells[11].innerHTML = mesSeptiembre;
tabla.tBodies[0].rows[numFilasCuerpo].insertCell(12);
tabla.tBodies[0].rows[numFilasCuerpo].cells[12].innerHTML = mesOctubre;
tabla.tBodies[0].rows[numFilasCuerpo].insertCell(13);
tabla.tBodies[0].rows[numFilasCuerpo].cells[13].innerHTML = mesNoviembre;
tabla.tBodies[0].rows[numFilasCuerpo].insertCell(14);
tabla.tBodies[0].rows[numFilasCuerpo].cells[14].innerHTML = mesDiciembre;
document.getElementById("bttnGuardarPresupuesto").disabled = false;
document.getElementById("bttnAdicionarFila").disabled = true;
calcularValorPresupuestoGlobal();
calcularValorPresupuestoCuenta();
}

/*metodos llamado para calcular el valor presupuestado de la cuenta
despues de adicionar o quitar una fila a la tabla
*/
function calcularValorPresupuestoCuenta(){
    arreglo = new Array();
    var valorPresupuestoCuenta = 0;
    var tabla = document.getElementById('detallesPresupuesto');
    var numeroDeFilasCuentas =tabla.tBodies[0].rows.length;
    if(numeroDeFilasCuentas!==0){
            for(i=0;i<numeroDeFilasCuentas;i++){
            valorPresupuestoCuenta = 0;
            valorPresupuestoCuenta = valorPresupuestoCuenta + parseFloat(tabla.tBodies[0].rows[i].cells[3].innerHTML);
            valorPresupuestoCuenta = valorPresupuestoCuenta + parseFloat(tabla.tBodies[0].rows[i].cells[4].innerHTML);
            valorPresupuestoCuenta = valorPresupuestoCuenta + parseFloat(tabla.tBodies[0].rows[i].cells[5].innerHTML);
            valorPresupuestoCuenta = valorPresupuestoCuenta + parseFloat(tabla.tBodies[0].rows[i].cells[6].innerHTML);
            valorPresupuestoCuenta = valorPresupuestoCuenta + parseFloat(tabla.tBodies[0].rows[i].cells[7].innerHTML);
            valorPresupuestoCuenta = valorPresupuestoCuenta + parseFloat(tabla.tBodies[0].rows[i].cells[8].innerHTML);
            valorPresupuestoCuenta = valorPresupuestoCuenta + parseFloat(tabla.tBodies[0].rows[i].cells[9].innerHTML);
            valorPresupuestoCuenta = valorPresupuestoCuenta + parseFloat(tabla.tBodies[0].rows[i].cells[10].innerHTML);
            valorPresupuestoCuenta = valorPresupuestoCuenta + parseFloat(tabla.tBodies[0].rows[i].cells[11].innerHTML);
            valorPresupuestoCuenta = valorPresupuestoCuenta + parseFloat(tabla.tBodies[0].rows[i].cells[12].innerHTML);
            valorPresupuestoCuenta = valorPresupuestoCuenta + parseFloat(tabla.tBodies[0].rows[i].cells[13].innerHTML);
            valorPresupuestoCuenta = valorPresupuestoCuenta + parseFloat(tabla.tBodies[0].rows[i].cells[14].innerHTML);
            arreglo.push(valorPresupuestoCuenta);
        }
        
    }
}

/*funcion llamado para calcualr el valor del presupuesto global 
al momento de adicionar o quitar una fila a la tabla*/
function calcularValorPresupuestoGlobal(){
    var valorPresupuestoGlobal = 0 ;
    var tabla = document.getElementById('detallesPresupuesto');
    var numeroDeFilasCuentas =tabla.tBodies[0].rows.length;
    if(numeroDeFilasCuentas!==0){
            for(i=0;i<numeroDeFilasCuentas;i++){
            valorPresupuestoGlobal = valorPresupuestoGlobal + parseFloat(tabla.tBodies[0].rows[i].cells[3].innerHTML);
            valorPresupuestoGlobal = valorPresupuestoGlobal + parseFloat(tabla.tBodies[0].rows[i].cells[4].innerHTML);
            valorPresupuestoGlobal = valorPresupuestoGlobal + parseFloat(tabla.tBodies[0].rows[i].cells[5].innerHTML);
            valorPresupuestoGlobal = valorPresupuestoGlobal + parseFloat(tabla.tBodies[0].rows[i].cells[6].innerHTML);
            valorPresupuestoGlobal = valorPresupuestoGlobal + parseFloat(tabla.tBodies[0].rows[i].cells[7].innerHTML);
            valorPresupuestoGlobal = valorPresupuestoGlobal + parseFloat(tabla.tBodies[0].rows[i].cells[8].innerHTML);
            valorPresupuestoGlobal = valorPresupuestoGlobal + parseFloat(tabla.tBodies[0].rows[i].cells[9].innerHTML);
            valorPresupuestoGlobal = valorPresupuestoGlobal + parseFloat(tabla.tBodies[0].rows[i].cells[10].innerHTML);
            valorPresupuestoGlobal = valorPresupuestoGlobal + parseFloat(tabla.tBodies[0].rows[i].cells[11].innerHTML);
            valorPresupuestoGlobal = valorPresupuestoGlobal + parseFloat(tabla.tBodies[0].rows[i].cells[12].innerHTML);
            valorPresupuestoGlobal = valorPresupuestoGlobal + parseFloat(tabla.tBodies[0].rows[i].cells[13].innerHTML);
            valorPresupuestoGlobal = valorPresupuestoGlobal + parseFloat(tabla.tBodies[0].rows[i].cells[14].innerHTML);
        }
        var txtValorPresupuestoGlobal = document.getElementById("txtValorPresupuestoGlobal");
        txtValorPresupuestoGlobal.setAttribute("value", valorPresupuestoGlobal);
    }else{
        var txtValorPresupuestoGlobal = document.getElementById("txtValorPresupuestoGlobal");
        txtValorPresupuestoGlobal.setAttribute("value", 0);
        }
}

/*funcion utilizada para limpiar todos los campos de la tabla de operacion de
adicionar fila a la tabla
*/
function limpiarTodosLosCampos(){
    var codigoCuenta = document.getElementById("txtCodigoCuenta");
    document.getElementById("txtCodigoCuenta").disabled = false;
    var nomCuenta=document.getElementById("txtNombreCuenta");
    var mesEnero =document.getElementById("txtEnero");
    document.getElementById("txtEnero").disabled = true ;
    var mesFebrero= document.getElementById("txtFebrero");
    document.getElementById("txtFebrero").disabled =true ;
    var mesMarzo= document.getElementById("txtMarzo");
    document.getElementById("txtMarzo").disabled = true ;
    var mesAbril=document.getElementById("txtAbril");
    document.getElementById("txtAbril").disabled = true;
    var mesMayo =document.getElementById("txtMayo");
    document.getElementById("txtMayo").disabled = true;
    var mesJunio=document.getElementById("txtJunio");
    document.getElementById("txtJunio").disabled = true ;
    var mesJulio=document.getElementById("txtJulio");
    document.getElementById("txtJulio").disabled = true;
    var mesAgosto=document.getElementById("txtAgosto");
    document.getElementById("txtAgosto").disabled = true ;
    var mesSeptiembre=document.getElementById("txtSeptiembre");
    document.getElementById("txtSeptiembre").disabled = true ;
    var mesOctubre=document.getElementById("txtOctubre");
    document.getElementById("txtOctubre").disabled =true ;
    var mesNoviembre=document.getElementById("txtNoviembre");
    document.getElementById("txtNoviembre").disabled = true ;
    var mesDiciembre=document.getElementById("txtDiciembre");
    document.getElementById("txtDiciembre").disabled = true ;
    document.getElementById("bttnCancelar").disabled = true;
    codigoCuenta.setAttribute("value", "");
    nomCuenta.setAttribute("value", "");
    mesEnero.setAttribute("value", 0);
    mesFebrero.setAttribute("value", 0);
    mesMarzo.setAttribute("value", 0);
    mesAbril.setAttribute("value", 0);
    mesMayo.setAttribute("value", 0);
    mesJunio.setAttribute("value", 0);
    mesJulio.setAttribute("value", 0);
    mesAgosto.setAttribute("value", 0);
    mesSeptiembre.setAttribute("value", 0);
    mesOctubre.setAttribute("value", 0);
    mesNoviembre.setAttribute("value", 0);
    mesDiciembre.setAttribute("value", 0);
}

/*funcion utilizada por el boton guardar presupuesto
*/
function enviarXML(){
var detallesXML = armarDetallesXML();
var codigoReferencia = document.getElementById("txtCodigoReferenciaPresupuesto").value;
var fechaInicio = document.getElementById("txtFechaInicioEjecucion").value;
var fechaFin = document.getElementById("txtFechaFinEjecucion").value;
var fechaLimite = document.getElementById("txtFechaLimiteAprobacion").value;
var valorGlobalPresupuestado = document.getElementById("txtValorPresupuestoGlobal").value;
var operacion = document.getElementById("txtOperacion").value;
var xml = "<presupuesto codigoReferencia='"+codigoReferencia+"' fechaInicio='"+fechaInicio+"' fechaFin='"+fechaFin+"' fechaLimite='"+fechaLimite+"' valorPresupuestado='"+valorGlobalPresupuestado+"' operacion='"+operacion+"'>"+detallesXML+"</presupuesto>";
var cadenaServidor = "txtAccion=" +encodeURIComponent(20)+
"&cadenaXML=" +encodeURIComponent(xml);
peticion = inicializa_xhr();
    if(peticion){
            peticion.onreadystatechange =comprobarTransaccion;
            var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_presupuesto_servlet";
            peticion.open("POST",cad, true);
            //peticion.open("POST","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_presupuesto_servlet",true);
            peticion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            var query_string = cadenaServidor;
	    peticion.send(query_string);

           
    }
}


/*función para armar todos los detalles que se van a adicionar
y crear la parte de detalles XML
*/
function armarDetallesXML(){
    var tabla = document.getElementById('detallesPresupuesto');
    var numeroDeFilasCuentas =tabla.tBodies[0].rows.length;
    //alert("numero de Filas Cuentas = "+numeroDeFilasCuentas);
    var cadenaCuenta="";
    if(numeroDeFilasCuentas!==0){
        for(i=0;i<numeroDeFilasCuentas;i++){
        //alert(tabla.tBodies[0].rows[i].cells[2].innerHTML);
            cadenaCuenta = cadenaCuenta + "<detalles codRefCCtos='"+arregloCentrosDeCostosCodigo[tabla.tBodies[0].rows[i].cells[2].innerHTML]+"' codigoCuenta='"+tabla.tBodies[0].rows[i].cells[0].innerHTML+"' valorPresupuestoCuenta='"+arreglo[i]+"'>";
            cadenaCuenta = cadenaCuenta + "<detalleMensual numeroMes='1' valor='"+tabla.tBodies[0].rows[i].cells[3].innerHTML+"'></detalleMensual>";
            cadenaCuenta = cadenaCuenta + "<detalleMensual numeroMes='2' valor='"+tabla.tBodies[0].rows[i].cells[4].innerHTML+"'></detalleMensual>";
            cadenaCuenta = cadenaCuenta + "<detalleMensual numeroMes='3' valor='"+tabla.tBodies[0].rows[i].cells[5].innerHTML+"'></detalleMensual>";
            cadenaCuenta = cadenaCuenta + "<detalleMensual numeroMes='4' valor='"+tabla.tBodies[0].rows[i].cells[6].innerHTML+"'></detalleMensual>";
            cadenaCuenta = cadenaCuenta + "<detalleMensual numeroMes='5' valor='"+tabla.tBodies[0].rows[i].cells[7].innerHTML+"'></detalleMensual>";
            cadenaCuenta = cadenaCuenta + "<detalleMensual numeroMes='6' valor='"+tabla.tBodies[0].rows[i].cells[8].innerHTML+"'></detalleMensual>";
            cadenaCuenta = cadenaCuenta + "<detalleMensual numeroMes='7' valor='"+tabla.tBodies[0].rows[i].cells[9].innerHTML+"'></detalleMensual>";
            cadenaCuenta = cadenaCuenta + "<detalleMensual numeroMes='8' valor='"+tabla.tBodies[0].rows[i].cells[10].innerHTML+"'></detalleMensual>";
            cadenaCuenta = cadenaCuenta + "<detalleMensual numeroMes='9' valor='"+tabla.tBodies[0].rows[i].cells[11].innerHTML+"'></detalleMensual>";
            cadenaCuenta = cadenaCuenta + "<detalleMensual numeroMes='10' valor='"+tabla.tBodies[0].rows[i].cells[12].innerHTML+"'></detalleMensual>";
            cadenaCuenta = cadenaCuenta + "<detalleMensual numeroMes='11' valor='"+tabla.tBodies[0].rows[i].cells[13].innerHTML+"'></detalleMensual>";
            cadenaCuenta = cadenaCuenta + "<detalleMensual numeroMes='12' valor='"+tabla.tBodies[0].rows[i].cells[14].innerHTML+"'></detalleMensual>";
            cadenaCuenta = cadenaCuenta +"</detalles>";
        }
    }
    return cadenaCuenta;
}


/*funcion utilizada para comprobar el estado de la transacción
por medio de la respuesta del servidor
*/
function comprobarTransaccion() {
	if (peticion.readyState == 4) {
		if (peticion.status == 200) {
                        var respuesta = peticion.responseText;
                        if(respuesta == 1){
                            alert("Transacción completada");
                            location.reload(true);
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
                var texto = document.getElementById("txtCodigoCuenta").value;
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
 var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_cuenta_servlet";
 //alert(cad);
 peticion.open('POST',cad, true);
peticion.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
var querys = "txtAccion="+encodeURIComponent(4)+"&txtCodigoReferencia="+encodeURIComponent(texto)+"&nocache="+Math.random();
peticion.send(querys);
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
       document.getElementById("txtCodigoCuenta").value = sugerencias[elementoSeleccionado];
        borraLista();
        buscarNombreCuenta();
        activaBotonAdicionarFila();
    }
}

/*función llamada para quitar el div cuando se selecciona 
un dato del la lista de sugerencia*/
function borraLista() {
        document.getElementById("sugerencias").innerHTML = "";
        document.getElementById("sugerencias").style.display = "none";
        document.getElementById("bttnAdicionarFila").disabled = true;
        //limpiarTodosLosCampos();
}

/*petición asincrona del nombre de la cuenta con el codigo
seleccionado de la lista de sugerencias*/
function buscarNombreCuenta(){
peticion = inicializa_xhr();
                    peticion.onreadystatechange = function() {
                        if(peticion.readyState == 4) {
                            if(peticion.status == 200) {
                                //if(peticion.responseText){
                                var documento_xml = peticion.responseXML;
//				alert(documento_xml.getElementById(""));
				//var centrosDeCostos = documento_xml.getElementsByTagName("centroDeCostos")[0];
				var cuenta = documento_xml.getElementsByTagName("cuenta");
                                var codigo = cuenta[0].getElementsByTagName("idCuenta")[0].firstChild.nodeValue;
				var nombre = cuenta[0].getElementsByTagName("breCuenta")[0].firstChild.nodeValue;
				document.getElementById("txtNombreCuenta").value = nombre;
                                document.getElementById("txtIdCuenta").value = codigo;
                                //alert(sugerencias.length);

                           /* }else{
                                sinResultados();
                            }*/
                            }
                        }
                };
//peticion.open('POST', ' http://127.0.0.1:8988/PruebasAjax-PruebasAJAX-context-root/servletautocompletar?nocache='+Math.random(), true);
                                var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_cuenta_servlet";
                                peticion.open("POST",cad, true);
 //peticion.open('POST', 'http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_cuenta_servlet', true);
peticion.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
var querys = "txtAccion="+encodeURIComponent(5)+"&txtCodigoReferencia="+encodeURIComponent(document.getElementById("txtCodigoCuenta").value)+"&nocache="+Math.random();
peticion.send(querys);
}


/*funcion que activa las casillas de los meses 
y el boton adicionar fila a la tabla*/
function activaBotonAdicionarFila(){
    //limpiarTodosLosCampos();
    //document.getElementById("txtEnero").focus = true;
    document.getElementById("bttnCancelar").disabled = false;
    document.getElementById("bttnAdicionarFila").disabled = false;
    document.getElementById("detallesPresupuesto").disabled =false;
    document.getElementById("txtCodigoCuenta").disabled = false;
    document.getElementById("txtNombreCuenta").disabled = false;
    document.getElementById("txtEnero").disabled = false;
    document.getElementById("txtFebrero").disabled = false;
    document.getElementById("txtMarzo").disabled = false;
    document.getElementById("txtAbril").disabled = false;
    document.getElementById("txtMayo").disabled = false;
    document.getElementById("txtJunio").disabled = false;
    document.getElementById("txtJulio").disabled = false;
    document.getElementById("txtAgosto").disabled = false;
    document.getElementById("txtSeptiembre").disabled = false;
    document.getElementById("txtOctubre").disabled = false;
    document.getElementById("txtNoviembre").disabled = false;
    document.getElementById("txtDiciembre").disabled = false;
    document.getElementById("txtCodigoCuenta").disabled = false;
}

/*funcion que se activa cuando 
los valores introducidos en la caja de texto no concuerdan
con la lista de sugerencias o más bien no hay
*/
function sinResultados() {
    document.getElementById("sugerencias").innerHTML = "no exite cuenta que empiece con este texto,verifique su información";
    document.getElementById("sugerencias").style.display = "block";
    document.getElementById("bttnAdicionarFila").disabled = true;
}

/*función utilizada paa actualizar las sugerencias de acuerdo el texto vaya cambiando*/
function actualizaSugerencias() {
elementoSeleccionado = -1;
muestraSugerencias();
}

/*función utilizada por el boton BuscarDetalles 
para buscar en la tabla la fila adicionada las caracteristicas definidas
codcentrodecostos y codigo cuenta
*/
function buscarEnTablaDetallesPresupuestosModificar(){

   var codigoRefCentroCosto = document.getElementById("cboCentrosDeCostosModificar").value;
   //alert("codigoCentro Costo ="+codigoRefCentroCosto);
     var codReferencia = document.getElementById("txtModificarCodigoCuenta").value;
    var tabla = document.getElementById('detallesPresupuesto');
    var numeroDeFilasCuentas =tabla.tBodies[0].rows.length;
    var cadenaCuenta ;
        var posicionEncontrado;
        if(numeroDeFilasCuentas!==0){
            for(i=0;i<numeroDeFilasCuentas;i++){
                //alert(tabla.tBodies[0].rows[i].cells[0].innerHTML);
                if(codReferencia == tabla.tBodies[0].rows[i].cells[0].innerHTML && tabla.tBodies[0].rows[i].cells[2].innerHTML == arregloCentrosDeCostosNombre[codigoRefCentroCosto]){
                    posicionEncontrado = i;
                    //alert("encontrado en la Tabla");
                    i=numeroDeFilasCuentas;
                    document.getElementById("txtModificarCodigoCuenta").disabled = true;
                    
                }else{
                     posicionEncontrado=null;
                }
                
                //alert(tabla.tBodies[0].rows[0].cells[1].innerHTML);
                //alert(tabla.tBodies[0].rows[i].cells[2].innerHTML);
            }   
        
        if(posicionEncontrado!==null){
            document.getElementById("txtModificarNombreCuenta").value=tabla.tBodies[0].rows[posicionEncontrado].cells[1].innerHTML;
            document.getElementById("txtModificarEnero").value=tabla.tBodies[0].rows[posicionEncontrado].cells[3].innerHTML;
            document.getElementById("txtModificarFebrero").value=tabla.tBodies[0].rows[posicionEncontrado].cells[4].innerHTML;
            document.getElementById("txtModificarMarzo").value=tabla.tBodies[0].rows[posicionEncontrado].cells[5].innerHTML;
            document.getElementById("txtModificarAbril").value=tabla.tBodies[0].rows[posicionEncontrado].cells[6].innerHTML;
            document.getElementById("txtModificarMayo").value=tabla.tBodies[0].rows[posicionEncontrado].cells[7].innerHTML;
            document.getElementById("txtModificarJunio").value=tabla.tBodies[0].rows[posicionEncontrado].cells[8].innerHTML;
            document.getElementById("txtModificarJulio").value=tabla.tBodies[0].rows[posicionEncontrado].cells[9].innerHTML;
            document.getElementById("txtModificarAgosto").value=tabla.tBodies[0].rows[posicionEncontrado].cells[10].innerHTML;
            document.getElementById("txtModificarSeptiembre").value=tabla.tBodies[0].rows[posicionEncontrado].cells[11].innerHTML;
            document.getElementById("txtModificarOctubre").value=tabla.tBodies[0].rows[posicionEncontrado].cells[12].innerHTML;
            document.getElementById("txtModificarNoviembre").value=tabla.tBodies[0].rows[posicionEncontrado].cells[13].innerHTML;
            document.getElementById("txtModificarDiciembre").value=tabla.tBodies[0].rows[posicionEncontrado].cells[14].innerHTML;
            document.getElementById("bttnCancelarModificar").disabled=false;
            document.getElementById("bttnActualizar").disabled=true;
            //document.getElementById("").value=
            activarCajasDeTextoModificar();
        }else{
            alert("Cuenta no encontrada en la tabla, verifique su información");
        }
    
    }else{
        alert("no hay filas adicionadas a la tabla");
    }
}

/*función utilizada para activar las cajas de texto de la tabla de operaciones
modificar*/
function activarCajasDeTextoModificar(){
        //document.getElementById("txtModificarNombreCuenta").disabled = true;
        document.getElementById("txtModificarEnero").disabled = false;
        document.getElementById("txtModificarFebrero").disabled = false;
        document.getElementById("txtModificarMarzo").disabled = false;
        document.getElementById("txtModificarAbril").disabled = false;
        document.getElementById("txtModificarMayo").disabled = false;
        document.getElementById("txtModificarJunio").disabled = false;
        document.getElementById("txtModificarJulio").disabled = false;
        document.getElementById("txtModificarAgosto").disabled = false;
        document.getElementById("txtModificarSeptiembre").disabled = false;
        document.getElementById("txtModificarOctubre").disabled = false;
        document.getElementById("txtModificarNoviembre").disabled = false;
        document.getElementById("txtModificarDiciembre").disabled = false;
        document.getElementById("bttnActualizar").disabled = false;
        document.getElementById("bttnQuitar").disabled = false
}

/*función utilizada por el boton actualizar datos
para actualizar los datos que estan adicionados a la tabla*/
function modificarDatos(){
    var codReferencia = document.getElementById("txtModificarCodigoCuenta").value;
    var tabla = document.getElementById('detallesPresupuesto');
    var codigoRefCentroCosto = document.getElementById("cboCentrosDeCostosModificar").value;
   //alert("codigoCentro Costo ="+codigoRefCentroCosto);
    var numeroDeFilasCuentas =tabla.tBodies[0].rows.length;
    var cadenaCuenta ;
        var posicionEncontrado;
        if(numeroDeFilasCuentas!==0){
            for(i=0;i<numeroDeFilasCuentas;i++){
                //alert(tabla.tBodies[0].rows[i].cells[0].innerHTML);
                if(codReferencia == tabla.tBodies[0].rows[i].cells[0].innerHTML &&  tabla.tBodies[0].rows[i].cells[2].innerHTML == arregloCentrosDeCostosNombre[codigoRefCentroCosto] ){
                    posicionEncontrado = i;
     //               alert("encontrado en la Tabla");
                    i=numeroDeFilasCuentas;
                    document.getElementById("txtModificarCodigoCuenta").disabled = true;
                    
                }else{
                posicionEncontrado=null;
                }
                
                //alert(tabla.tBodies[0].rows[0].cells[1].innerHTML);
                //alert(tabla.tBodies[0].rows[i].cells[2].innerHTML);
        }
       // alert(posicionEncontrado);
        if(posicionEncontrado!==null){
            //tabla.tBodies[0].rows[posicionEncontrado].cells[1].innerHTML=document.getElementById("txtModificarNombreCuenta").value;
            if(validarMeseModificar()==0){
            tabla.tBodies[0].rows[posicionEncontrado].cells[3].innerHTML=document.getElementById("txtModificarEnero").value;
            tabla.tBodies[0].rows[posicionEncontrado].cells[4].innerHTML=document.getElementById("txtModificarFebrero").value;
            tabla.tBodies[0].rows[posicionEncontrado].cells[5].innerHTML=document.getElementById("txtModificarMarzo").value;
            tabla.tBodies[0].rows[posicionEncontrado].cells[6].innerHTML=document.getElementById("txtModificarAbril").value;
            tabla.tBodies[0].rows[posicionEncontrado].cells[7].innerHTML=document.getElementById("txtModificarMayo").value;
            tabla.tBodies[0].rows[posicionEncontrado].cells[8].innerHTML=document.getElementById("txtModificarJunio").value;
            tabla.tBodies[0].rows[posicionEncontrado].cells[9].innerHTML=document.getElementById("txtModificarJulio").value;
            tabla.tBodies[0].rows[posicionEncontrado].cells[10].innerHTML=document.getElementById("txtModificarAgosto").value;
            tabla.tBodies[0].rows[posicionEncontrado].cells[11].innerHTML=document.getElementById("txtModificarSeptiembre").value;
            tabla.tBodies[0].rows[posicionEncontrado].cells[12].innerHTML=document.getElementById("txtModificarOctubre").value;
            tabla.tBodies[0].rows[posicionEncontrado].cells[13].innerHTML=document.getElementById("txtModificarNoviembre").value;
            tabla.tBodies[0].rows[posicionEncontrado].cells[14].innerHTML=document.getElementById("txtModificarDiciembre").value;
            //document.getElementById("").value=
            limpiarCajasDeTextoModificar();
            calcularValorPresupuestoGlobal();
            calcularValorPresupuestoCuenta();
            alert("datos actualizados correctamente");
            }else{
            alert("verificar los datos que se desean actualizar");
            }
            
            
        }else{
            alert("cuenta no encontrada en la tabla, verifique sus datos");
        }
    
    }
}

/*función utilizada para limpiar las cajas de texto
de la tabla de operaciones modificar datos*/
function limpiarCajasDeTextoModificar(){
        document.getElementById("txtModificarCodigoCuenta").value="";
        document.getElementById("txtModificarCodigoCuenta").disabled = false;
        document.getElementById("txtModificarNombreCuenta").value="";
        document.getElementById("txtModificarEnero").value="0";
        document.getElementById("txtModificarEnero").disabled = true;
        document.getElementById("txtModificarFebrero").value="0";
        document.getElementById("txtModificarFebrero").disabled = true;
        document.getElementById("txtModificarMarzo").value="0";
        document.getElementById("txtModificarMarzo").disabled = true;
        document.getElementById("txtModificarAbril").value="0";
        document.getElementById("txtModificarAbril").disabled = true;
        document.getElementById("txtModificarMayo").value="0";
        document.getElementById("txtModificarMayo").disabled = true;
        document.getElementById("txtModificarJunio").value="0";
        document.getElementById("txtModificarJunio").disabled = true;
        document.getElementById("txtModificarJulio").value="0";
        document.getElementById("txtModificarJulio").disabled = true;
        document.getElementById("txtModificarAgosto").value="0";
        document.getElementById("txtModificarAgosto").disabled = true;
        document.getElementById("txtModificarSeptiembre").value="0";
        document.getElementById("txtModificarSeptiembre").disabled = true;
        document.getElementById("txtModificarOctubre").value="0";
        document.getElementById("txtModificarOctubre").disabled = true;
        document.getElementById("txtModificarNoviembre").value="0";
        document.getElementById("txtModificarNoviembre").disabled = true;
        document.getElementById("txtModificarDiciembre").value="0";
        document.getElementById("txtModificarDiciembre").disabled = true;
        document.getElementById("bttnCancelarModificar").disabled=true;
        document.getElementById("bttnActualizar").disabled=true;
        document.getElementById("bttnQuitar").disabled=true;
}

/*función utilizada para eliminar una fila de la tabla*/
function eliminarFilaModificar(){
   var codigoRefCentroCosto = document.getElementById("cboCentrosDeCostosModificar").value;
   //alert("codigoCentro Costo ="+codigoRefCentroCosto);
    var codReferencia = document.getElementById("txtModificarCodigoCuenta").value;
    var tabla = document.getElementById('detallesPresupuesto');
    var numeroDeFilasCuentas =tabla.tBodies[0].rows.length;
    var cadenaCuenta ;
        var posicionEncontrado;
        if(numeroDeFilasCuentas!==0){
            for(i=0;i<numeroDeFilasCuentas;i++){
     //           alert(tabla.tBodies[0].rows[i].cells[0].innerHTML);
                if(codReferencia == tabla.tBodies[0].rows[i].cells[0].innerHTML &&  tabla.tBodies[0].rows[i].cells[2].innerHTML == arregloCentrosDeCostosNombre[codigoRefCentroCosto]){
                    posicionEncontrado = i;
       //             alert("encontrado en la Tabla");
                    i=numeroDeFilasCuentas;
                    document.getElementById("txtModificarCodigoCuenta").disabled = true;
                    
                }else{
                    posicionEncontrado=null;
                }
                
                //alert(tabla.tBodies[0].rows[0].cells[1].innerHTML);
                //alert(tabla.tBodies[0].rows[i].cells[2].innerHTML);
        }
        //alert(posicionEncontrado);
        if(posicionEncontrado!==null){
            //tabla.tBodies[0].rows[posicionEncontrado].cells[1].innerHTML=document.getElementById("txtModificarNombreCuenta").value;
            tabla.tBodies[0].deleteRow(posicionEncontrado);
            //limpiarDatos();
            limpiarCajasDeTextoModificar();
            calcularValorPresupuestoGlobal();
            calcularValorPresupuestoCuenta();
        }else{
        alert("Cuenta no encontrada en la tabla, verifique su información");
        }
    
    }
}


/*función utilizada por el boton cancelar para limpiar todos los campos y la tabla */
function limpiarTodosLosCamposBotonCancelarAdicion(){
    var codigoCuenta = document.getElementById("txtCodigoCuenta");
    document.getElementById("txtCodigoCuenta").disabled = false;
    var nomCuenta=document.getElementById("txtNombreCuenta");
    var mesEnero =document.getElementById("txtEnero");
    document.getElementById("txtEnero").disabled = true ;
    var mesFebrero= document.getElementById("txtFebrero");
    document.getElementById("txtFebrero").disabled = true ;
    var mesMarzo= document.getElementById("txtMarzo");
    document.getElementById("txtMarzo").disabled = true ;
    var mesAbril=document.getElementById("txtAbril");
    document.getElementById("txtAbril").disabled = true ;
    var mesMayo =document.getElementById("txtMayo");
    document.getElementById("txtMayo").disabled = true ;
    var mesJunio=document.getElementById("txtJunio");
    document.getElementById("txtJunio").disabled = true ;
    var mesJulio=document.getElementById("txtJulio");
    document.getElementById("txtJulio").disabled = true ;
    var mesAgosto=document.getElementById("txtAgosto");
    document.getElementById("txtAgosto").disabled = true ;
    var mesSeptiembre=document.getElementById("txtSeptiembre");
    document.getElementById("txtSeptiembre").disabled = true ;
    var mesOctubre=document.getElementById("txtOctubre");
    document.getElementById("txtOctubre").disabled = true ;
    var mesNoviembre=document.getElementById("txtNoviembre");
    document.getElementById("txtNoviembre").disabled = true ;
    var mesDiciembre=document.getElementById("txtDiciembre");
    document.getElementById("txtDiciembre").disabled = true ;
    codigoCuenta.setAttribute("value", "");
    nomCuenta.setAttribute("value", "");
    mesEnero.setAttribute("value", 0);
    mesFebrero.setAttribute("value", 0);
    mesMarzo.setAttribute("value", 0);
    mesAbril.setAttribute("value", 0);
    mesMayo.setAttribute("value", 0);
    mesJunio.setAttribute("value", 0);
    mesJulio.setAttribute("value", 0);
    mesAgosto.setAttribute("value", 0);
    mesSeptiembre.setAttribute("value", 0);
    mesOctubre.setAttribute("value", 0);
    mesNoviembre.setAttribute("value", 0);
    mesDiciembre.setAttribute("value", 0);
}




/*función utilizada para hacer una petición asincrona al servidor
pidiendo los diferentes centros de costos que existen*/
function cargarCboCentroCostos(){
		peticion = inicializa_xhr();
                		var inicio = document.getElementById("cboCentrosDeCostos");
				inicio.options[0] = new Option("- cargando... -");
			if(peticion) {
				peticion.onreadystatechange = muestraCentroDeCosto;
                                //var valor = parseInt(document.getElementById("txtAccion"));
                                //alert(valor.value);
                                var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/pre_centro_costo_servlet?txtAccion=4&nocache="+Math.random();
                                //peticion.open("GET",cad, true);
                                peticion.open("GET","pre_centro_costo_servlet?txtAccion=4", true);
				//peticion.open("GET","http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_centro_costo_servlet?txtAccion=4&nocache="+Math.random(), true);
                                //peticion.open("GET","http://"+location.host+"/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_centro_costo_servlet?txtAccion=4&nocache="+Math.random(), true);
                                
				//peticion.send("txtAccion=" + encodeURIComponent(valor));
                                peticion.send(null);
			}
}


	function muestraCentroDeCosto() {
		if (peticion.readyState == 4) {
			if (peticion.status == 200) {
			//alert("200");
                        arregloCentrosDeCostos = new Array();
                        arregloCentrosDeCostosCodigo = new Array();
				var lista = document.getElementById("cboCentrosDeCostos");
                                var lista2 = document.getElementById("cboCentrosDeCostosModificar");
				var documento_xml = peticion.responseXML;
//				alert(documento_xml.getElementById(""));
				var centrosDeCostos = documento_xml.getElementsByTagName("centroDeCostos")[0];
				var losCentrosDeCostos = centrosDeCostos.getElementsByTagName("centroDeCosto");
				lista.options[0] = new Option("- selecciona un centro de costos -");
                                lista2.options[0] = new Option("- selecciona un centro de costos -");
				// Método 1: Crear elementos Option() y añadirlos a la lista
					for(i=0; i<losCentrosDeCostos.length; i++) {
						var codigo = losCentrosDeCostos[i].getElementsByTagName("codigo")[0].firstChild.nodeValue;
						var nombre = losCentrosDeCostos[i].getElementsByTagName("nombre")[0].firstChild.nodeValue;
						lista.options[i+1] = new Option(nombre, codigo);
                                                lista2.options[i+1] = new Option(nombre, codigo);
                                                arregloCentrosDeCostosNombre[codigo] = nombre;
                                                arregloCentrosDeCostosCodigo[nombre] = codigo;
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

function validarCampos(){
//alert("");
    var cadenaMostrar="";
    var codRefPresupuesto = document.getElementById("txtCodigoReferenciaPresupuesto").value;
    var validacionFechas = validarFechas();
    var validacionCodigoPresupuesto = validaCodRefPresupuesto();
    if(validacionFechas==1) {
    quitarResataFechas();
    }else{
        cadenaMostrar=  cadenaMostrar+"VERIFIQUE LA COHERENCIA DE LAS FECHAS";
        //alert("VERIFIQUE LA COHERENCIA DE LAS FECHAS");
        resaltaTxtFechas();
    }
    if(validacionCodigoPresupuesto==1){
            
    }else{
        cadenaMostrar=  cadenaMostrar+"\nDIGITE UN CODIGO DE REFERENCIA PARA EL NUEVO PRESUPUESTO";
        //alert("Digite un codigo de referencia para el presupuesto nuevo");
    }
    
    if(cadenaMostrar==""){
    enviarXML();
        //alert("guardarPresupuesto");
    }else{
        alert(cadenaMostrar);
    }
}




function validaCodRefPresupuesto(){
    var sw=0;
    var codRefPresupuesto = document.getElementById("txtCodigoReferenciaPresupuesto");
    var valorCodRefPresupuesto = codRefPresupuesto.value;
    if(valorCodRefPresupuesto=="" ){
        codRefPresupuesto.style.borderColor='red';
    }else{
        sw=1;
        codRefPresupuesto.style.borderColor='';
    }
return sw;
}

function resaltaTxtFechas(){
    var fechaInicioEjecucion = document.getElementById("txtFechaInicioEjecucion");
    var fechaFinEjecucion = document.getElementById("txtFechaFinEjecucion");
    var fechaLimiteAprobacion = document.getElementById("txtFechaLimiteAprobacion");
    fechaInicioEjecucion.style.borderColor='red';
    fechaFinEjecucion.style.borderColor='red';
    fechaLimiteAprobacion.style.borderColor='red';
}

function quitarResataFechas(){
    var fechaInicioEjecucion = document.getElementById("txtFechaInicioEjecucion");
    var fechaFinEjecucion = document.getElementById("txtFechaFinEjecucion");
    var fechaLimiteAprobacion = document.getElementById("txtFechaLimiteAprobacion");
    fechaInicioEjecucion.style.borderColor='';
    fechaFinEjecucion.style.borderColor='';
    fechaLimiteAprobacion.style.borderColor='';
}
        
function validarFechas(){
	alert("listo..");
var sw=0;
var fechaInicioEjecucion = document.getElementById("txtFechaInicioEjecucion").value;
var fechaFinEjecucion = document.getElementById("txtFechaFinEjecucion").value;
var fechaLimiteAprobacion = document.getElementById("txtFechaLimiteAprobacion").value;
annoInicioEjecucion=fechaInicioEjecucion.substring(0,4);
annoFinEjecucion = fechaFinEjecucion.substring(0,4);
annoLimiteAprobacion = fechaLimiteAprobacion.substring(0,4);
mesInicioEjecucion=fechaInicioEjecucion.substring(5,7);
mesFinEjecucion = fechaFinEjecucion.substring(5,7);
mesLimiteAprobacion = fechaLimiteAprobacion.substring(5,7);
diaInicioEjecucion=fechaInicioEjecucion.substring(8,10);
diaFinEjecucion = fechaFinEjecucion.substring(8,10);
diaLimiteAprobacion = fechaLimiteAprobacion.substring(8,10);

if(annoInicioEjecucion>annoFinEjecucion || annoLimiteAprobacion>annoFinEjecucion){
    alert("VERIFICAR, que los años de las fechas sean coherentes, es decir año fecha inicio <= año fecha fin y que año fecha limite se encuente entre el año de la fecha de inicio y fecha fin");
}else{

    if(annoInicioEjecucion == annoFinEjecucion && annoInicioEjecucion == annoLimiteAprobacion){
    //alert("entro en años uçiguales"+mesInicioEjecucion +"<="+ mesFinEjecucion +"&&"+ mesLimiteAprobacion +"<="+ mesInicioEjecucion);
        if(mesInicioEjecucion <= mesFinEjecucion && mesLimiteAprobacion <= mesInicioEjecucion){
        
        if(mesInicioEjecucion == mesFinEjecucion){
      //  alert("entro en segundo si"+diaInicioEjecucion +"<"+ diaFinEjecucion +"&&"+ diaLimiteAprobacion +"<="+ diaInicioEjecucion);
            if(diaInicioEjecucion < diaFinEjecucion && diaLimiteAprobacion <= diaInicioEjecucion){
                sw=1;
                //alert("aceptar presupuesto");
            }
        }else{
            if(diaLimiteAprobacion <= diaInicioEjecucion){
                sw=1;
               alert("aceptar presupuesto");
            }
        }
        }
}else{
        if(annoInicioEjecucion <= annoFinEjecucion && annoLimiteAprobacion <= annoFinEjecucion){
                    if(annoInicioEjecucion > annoLimiteAprobacion){
                    sw=1;
                    //alert("aceptar Presupuesto");
                    }else{
                        if(annoInicioEjecucion == annoLimiteAprobacion){
                            if(mesInicioEjecucion > mesLimiteAprobacion){
                                sw=1;
                                //alert("aceptar presupuesto");
                            }else{
                                if(mesInicioEjecucion == mesLimiteAprobacion){
                                    if(diaInicioEjecucion > diaLimiteAprobacion){
                                        sw=1;
                                        //alert("aceptar presupuesto");
                                    }else{
                                        if(diaInicioEjecucion == diaLimiteAprobacion){
                                            sw=1;
                                            //alert("aceptar presupuesto");
                                        }
                                    }
                                }
                            }
                        }
                    }
  
        }
    }
}
return sw;
}

function activarTxtCodigoCuentaAdicionar(){
document.getElementById("txtCodigoCuenta").disabled =false;

}

function activarTxtCodigoCuentaModificar(){

document.getElementById("txtModificarCodigoCuenta").disabled =false;
}

        
        /*función que se cargar al iniciar la ejecucion de la pagina*/
	window.onload = function() {

                                 cargarCboCentroCostos();
                                 document.getElementById("bttnAdicionarFila").onclick = buscarEnTablaDetallePresupuesto;
                                 document.getElementById("bttnGuardarPresupuesto").onclick = validarCampos;
                                 document.getElementById("txtCodigoCuenta").onkeyup = autocompleta;
                                 document.getElementById("bttnBuscarDetalles").onclick=buscarEnTablaDetallesPresupuestosModificar;
                                 document.getElementById("bttnActualizar").onclick=modificarDatos;
                                 document.getElementById("bttnQuitar").onclick=eliminarFilaModificar;
                                 document.getElementById("bttnCancelar").onclick=limpiarTodosLosCamposBotonCancelarAdicion;
                                 document.getElementById("cboCentrosDeCostos").onchange = activarTxtCodigoCuentaAdicionar;
                                 document.getElementById("cboCentrosDeCostosModificar").onchange = activarTxtCodigoCuentaModificar;
                                 document.getElementById("bttnCancelarModificar").onclick = limpiarCajasDeTextoModificar;
                                 
                                //document.getElementById("txtCodigoCuenta").focus();
	}