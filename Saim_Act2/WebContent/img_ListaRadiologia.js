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

/////////////////////////////////////////////////////////////////////////////////


function Radio() {
	
	//alert("entro a funcion radio :P");
	ajax=getXMLObject();
	var contenido=document.getElementById('examenes');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControlListaRadiologia",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val); //Posting txtname to Servlet
		}	     
	}
}





function ExamPorModalidad(){
	 var NomSubArea=document.getElementById("cmbServicio").value;
	 if(NomSubArea=="Seleccione"){
		 alert("Seleccione El tipo del examen.");
	 }
	 if(NomSubArea!="Seleccione"){
		 ajax=getXMLObject();
			ajax.open("POST","ControlListaRadiologia",true); //getname will be the servlet name
			ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('examenes').innerHTML = ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=ExamPorModalidad"+"&modalidad="+NomSubArea); //Posting txtname to Servlet
	 }
}



function fecha_gru(){
	  var time1 = new Date()
	  var ano = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  var dia = time1.getDate()

	  var temp1 = "" + ((ano < 10) ? "0" : "") + ano
	  temp1 += ((mes < 10) ? ":0" : ":") + mes
	  temp1 += ((dia < 10) ? ":0" : ":") + dia
	 document.getElementById('txtfecha').value = temp1
	 id = setTimeout("fecha()",1000)
	  
	 
	  }





function hora_gru(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.getElementById('txthora').value = temp;
	  id = setTimeout("hora()",1000)
	  }



function validar(h){
		if (h.txtnumdoc.value.length==0){
			window.alert('Debe Digitar la Cedula');
			h.nom.focus();
			return false;
		}
	h.submit();
	}


function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}

////////////////////////////////////////////////////////////////////////
function Mostrar() {	
	if (ajax.readyState == 4) {		
		if(ajax.status == 200) {		
			document.getElementById('cambio').innerHTML = ajax.responseText
	     }
	     else {
	        alert("Error during AJAX call. Please try again,getCheckedRadio");
	     }
	   }
}

////////////////////////////////////////////////////////////////////////
function buscar(){
	//alert("funcion buscar");
	ajax=objetoAjax();
        	DivValor=document.getElementById('examenes')	 	 
        	    ajax.open("POST","ControlListaRadiologia",true); //getname will be the servlet name
        	    ajax.onreadystatechange  =function(){
        			if (ajax.readyState == 4) {
        				PatronNormal=ajax.responseText;
        				//alert(PatronNormal)        				
        	  			DivValor.innerHTML=PatronNormal;
        	  	   	}  	
        		}
        	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
        	    ajax.send("valor=9"); //Posting txtname to Servlet
        		//  }
        	//window.location.href="ControlExamenImag?tipo="+tipo+"&ced="+cedula+"",true;
       // }
	
}
////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////
function omitir(codigo){
	var NomUsu=document.getElementById('txtNomUsu').value;
	var CodUsu=document.getElementById('txtCodUsu').value;
	if(xmlhttp) { 		 	  
		xmlhttp.open("POST","ControlPacientesPendientes?valor=3&codigo="+codigo+"&NomUsu="+NomUsu+"&CodUsu="+CodUsu,true); //getname will be the servlet name
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send(""); //Posting txtname to Servlet
	} 
	window.location.reload();

}



function Revisado(codigo){
	var NomUsu=document.getElementById('txtNomUsu').value;
	var CodUsu=document.getElementById('txtCodUsu').value;
	if(xmlhttp) { 		 	  
		xmlhttp.open("POST","ControlPacientesPendientes?valor=33&codigo="+codigo+"&NomUsu="+NomUsu+"&CodUsu="+CodUsu,true); //getname will be the servlet name
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send(""); //Posting txtname to Servlet
	} 
	window.location.reload();

}
