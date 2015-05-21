
//------------------------------------------------------------------------
	function getXMLObject() {
	var xmlhttp;
	if(typeof(XMLHttpRequest) != 'undefined'){
    	try{
      		xmlhttp = new XMLHttpRequest();
    	}catch(e){ }
  	}
  	else{
    	try{
      		xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
    	}catch(e){
      		xmlhttp = new ActiveXObject('Msxml2.XMLHTTP');
    	}
  	}
  	return xmlhttp;
}
	
 
	
var xmlhttp = new getXMLObject();

function img_paciente(){
	getXMLObject();	 
	  if(xmlhttp) {
		 
		 // var tipo = document.form3.tipodoc.selectedIndex;
		  //var tipodocu=document.form3.tipodoc.options[tipo].text;
		  var tipodocu=document.getElementById("tipodoc").value;
		  var ced= document.getElementById("txtnumdocu").value;
		  var apellidos = document.getElementById("txtapellidos").value;
		  var nombre=document.getElementById("txtnombres").value; 
		  var fecha=document.getElementById("txtfechanaci").value;
		  var tele=document.getElementById("txtele").value;
		  var dire=document.getElementById("txtdireccion").value;
		  var ema=document.getElementById("txtemail").value;
		  //var gene=document.form3.cbgenero.selectedIndex;
		  var genero=document.getElementById("cbgenero").value;
		  var op=document.getElementById("op").value;
		  var CodUsu=document.getElementById("CodUsu").value;
		  var fechaIngreso=document.getElementById("txtfecha").value;
		  var horaIngreso=document.getElementById("txthora").value;
		  var eps=document.getElementById("txteps").value;		  
		  if(ced==""){
			  alert("Digite El Numero De Documento!!!");
		  }
		  if(tipodocu=="SELECCIONE..."){
			  alert("Seleccione El Tipo De Documento!!!");
		  }
		  if(apellidos==""){
			  alert("Digite Los Apellidos!!!");
		  }
		  if(nombre==""){
			  alert("Digite El Nombre!!!");
		  }
		  if(tele==""){
			  alert("Digite El Numero Telefonico!!!");
		  }
		  if(genero=="SELECCIONE..."){
			  alert("Seleccione El Genero!!!");
		  }
		  if(eps==""){
			  alert("Digite El Nombre De La Entidad!!!");
		  }
		  if(fecha=="00/00/0000"){
			  alert("Fecha De Nacimiento Invalido\nPruebe Con Una Fecha Valida!!!")
		  }
		  
		  if((ced!="")&&(tipodocu!="SELECCIONE...")&&(apellidos!="")&&(nombre!="")&&(tele!="")&&(genero!="SELECCIONE...")&&(eps!="")&&(fecha!="00/00/0000")){		  
			   xmlhttp.open("POST","ControlExamenImag?valor=1&ced="+ced+"&tipo="+tipodocu+"&ape="+apellidos+"&nom="+nombre+"&fecha="+fecha+"&telefono="+tele+"&dire="+dire+"&email="+ema+"&gene="+genero+"&eps="+eps+"&CodUsu="+CodUsu+"+&fechaIngreso="+fechaIngreso+"&horaIngreso="+horaIngreso,true); 
			   xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			   xmlhttp.send(""); //Posting txtname to Servlet
			   nombrecomp=nombre+" "+apellidos;
			   alert("Ingreso Exitoso...!");
			   if(op==1){
				   window.location.href="img_buscarEspecifico.jsp?numero="+ced+"&TipoDoc="+tipodocu,true;
			   }
			   if(op==2){
			   window.location.href="img_asignar.jsp?numero="+ced+"&TipoDoc="+tipodocu,true;
			   }
		  }	
	  }
	   var x;
}
function img_grupo() {
	  if(xmlhttp) { 	
		  var cedula=document.getElementById('txtnumdoc').value;
	    xmlhttp.open("POST","ControlExamenImag?valor=2&cedula="+cedula,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = Comprobarimg_grupo;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
	   var x;
	}

function Comprobarimg_grupo() {
	var x;
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			document.getElementById('examenes').innerHTML = xmlhttp.responseText;
	     }
	     else {
	        alert("Error during AJAX call. Please try again");
	     }
	   }
	}


var patron = new Array(2,2,4);
var patron2 = new Array(1,3,3,3,3)
function masca(d,sep,pat,nums){
	


if(d.valant != d.value){
	val = d.value
	largo = val.length
	ini=val.substring(0,2);
	if(ini>31){
		alert("Dia No Valido!!!");
		val=d.value="";
		d.focus();
		
	}
	val = val.split(sep);
	

	
	val2 = ''
	for(r=0;r<val.length;r++){
		val2 += val[r]
			
	}
	ini=val2.substring(2,4);
	//alert(ini);
	if(ini>12){
		alert("Mes No Valido!!!");
		val2=d.value="";
		d.focus();
		
	}
	
	if(nums){
		for(z=0;z<val2.length;z++){
			if(isNaN(val2.charAt(z))){
				letra = new RegExp(val2.charAt(z),"g")
				val2 = val2.replace(letra,"")
			}
		}
	}
	val = ''
	val3 = new Array()
	for(s=0; s<pat.length; s++){
    
		val3[s] = val2.substring(0,pat[s])
		val2 = val2.substring(pat[s])
	}
	for(q=0;q<val3.length; q++){
		if(q ==0){
			val = val3[q]
		}
		else{
			if(val3[q] != ""){
				val += sep + val3[q]
				}
		}
	}
	d.value = val
	d.valant = val
	}
}