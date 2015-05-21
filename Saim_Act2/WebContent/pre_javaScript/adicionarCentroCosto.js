function comprobarValores(){
var valorCodigoReferencia = document.getElementById("txtCodigoReferencia").value;
var valorNombre = document.getElementById("txtNombreCentroCosto").value;
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
    document.getElementById("txtCodigoReferencia").onkeyup=comprobarValores;
    document.getElementById("txtNombreCentroCosto").onkeyup=comprobarValores;
    document.getElementById("bttnCancela").onclick =bloquearBoton;
}