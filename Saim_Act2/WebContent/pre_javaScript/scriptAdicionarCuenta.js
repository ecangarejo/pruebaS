function comprobarValores(){
var valorCodigoReferencia = document.getElementById("txtCodigoCuenta").value;
var valorNombre = document.getElementById("txtNombreCuenta").value;
    if(valorCodigoReferencia !=="" && valorNombre !== ""){
        document.getElementById("bttnAdicionar").disabled = false;
        //document.getElementById("bttnCancela").disabled = false;
    }else{
        document.getElementById("bttnAdicionar").disabled = true;
        //document.getElementById("bttnCancela").disabled = true;
    }
}

function bloquearBoton(){
    document.getElementById("bttnAdicionar").disabled = true;
}
window.onload=function(){
    document.getElementById("txtCodigoCuenta").onkeyup=comprobarValores;
    document.getElementById("txtNombreCuenta").onkeyup=comprobarValores;
    document.getElementById("bttnCancelar").onclick =bloquearBoton;
}