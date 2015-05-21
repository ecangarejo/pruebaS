function objetoAjax(){
	var xmlhttp=false;
	try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
		   xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (E) {
			xmlhttp = false;
  		}
	}

	if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
		xmlhttp = new XMLHttpRequest();
	}
	return xmlhttp;
}
/////////////////////////////////////////////////////////////////////////////////
function ActualizarResultHistorias(Resul,CodResul){
		ajax=objetoAjax();	
		var hora=document.getElementById("HoraFormato").value;
		var fecha=document.getElementById("FechaFormato").value;
		ajax.open("POST","ControlIngresarResultados",true); //getname will be the servlet name
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&Resul="+Resul+"&CodResul="+CodResul); //Posting txtname to Servlet    
}
function ActualizarResultados(){
	var c=document.getElementById('txtTotal').value;
	var oscar=c;
	if(c!=1){
		for(var i=0; i<=c-1; i++){
			Resul=document.form1.txtRespuesta[i].value;
			CodResul=document.form1.txtCodResultado[i].value;
			ActualizarResultHistorias(Resul,CodResul);
	      oscar--; 
	     }	
	 }else{	
		 Resul=document.getElementById("txtRespuesta").value;
		 CodResul=document.getElementById("txtCodResultado").value;
		 ActualizarResultHistorias(Resul,CodResul);
	 }
}