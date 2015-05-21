function getXMLObject()  //XML OBJECT
{
   var xmlHttp = false;
   try {
     xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
   }
   catch (e) {
     try {
       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
     }
     catch (e2) {
       xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
     }
   }
   if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
     xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
   }
   return xmlHttp;  // Mandatory Statement returning the ajax object created
}
var xmlhttp = new getXMLObject(); //xmlhttp holds the ajax object
//////////////////////////////////////////////////////////////////////////////////////////////////////////

function Radio() {
	ajax=getXMLObject();
	var contenido=document.getElementById('Contenido');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControlProgramas",true); //getname will be the servlet name
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////

function ConsultarP(){
	
	var contenido=document.getElementById('Contenido');	
	var mbase=document.getElementById("mbase").value;
	var comp=document.getElementById("comp").value;
	var cref=document.getElementById("cref").value;
	var cups=document.getElementById("cups").value;
	var desc=document.getElementById("desc").value;
	var espe=document.getElementById("espe").value;
	var serv=document.getElementById("serv").value;
	var subc=document.getElementById("subc").value;
	var rips=document.getElementById("rips").value;
	var prn="";if(document.getElementById("rn").checked){prn="1";}else{prn="0";}
	var paq="";if(document.getElementById("aq").checked){paq="1";}else{paq="0";}
	var pme="";if(document.getElementById("med").checked){pme="1";}else{pme="0";}
	
	
	//var prn=document.getElementById("rn").value;
	//alert("prn: "+prn);
	document.getElementById('contenido').innerHTML='<p class="style6"><div align="center" >Cargando Resultados...</p>';//<img src="Imagenes/cargando.gif">';
	
	//alert("mbase "+mbase+" comp "+comp+" cref "+cref+" cups "+cups+" desc "+desc+" espe "+espe+" serv "+serv+" subc "+subc+" rips "+rips);
	
	if(mbase=="Seleccione" && comp=="Seleccione" && cref=="" && cups=="" && desc=="" && espe=="Seleccione" && serv=="Seleccione" && subc=="Seleccione" && rips=="Seleccione" && prn=="0" && paq=="0" && pme=="0"){alert("Debe Ingresar al menos un Criterio de Busqueda");}else{
    
	ajax=getXMLObject();
	ajax.open("POST","ControlProgramas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=3&mbase="+mbase+"&comp="+comp+"&cref="+cref+"&cups="+cups+"&desc="+desc+"&espe="+espe+"&serv="+serv+"&subc="+subc+"&rips="+rips+"&prn="+prn+"&paq="+paq+"&pme="+pme);
	
  }
}


function checkAll() {
	 var i;
  var nodoCheck = document.getElementsByTagName("input");
  var varCheck = document.getElementById("all").checked;
  for (i=0; i<nodoCheck.length; i++){
      if (nodoCheck[i].type == "checkbox" && nodoCheck[i].name != "all" && nodoCheck[i].disabled == false) {
          nodoCheck[i].checked = varCheck;
      }
  } 
}

function checkAllca() {
	 var i;
 var nodoCheck = document.getElementsByTagName("input");
 var varCheck = document.getElementById("all").checked;
 for (i=0; i<nodoCheck.length; i++){
     if (nodoCheck[i].type == "checkbox" && nodoCheck[i].name != "all" && nodoCheck[i].name == "chkvalidar" && nodoCheck[i].disabled == false) {
         nodoCheck[i].checked = varCheck;
     }
 } 
}

function ModificarP(){
	
 var c=document.getElementById('yo').value;
 //alert("contador "+c);
 var d="";
 var valida=0;
 if(c!=0){
	if(c!=1){ 
	for(var i=0; i<=c-1; i++){	
      if (document.form1.ca[i].checked) {
       	 d=d+document.form1.ca[i].value; 
       	 d=d+"|";
       	 valida=valida+1;
     }     	  
    }
	}else{
		if(document.form1.ca.checked){
			 d=document.form1.ca.value;
			 valida=valida+1;
		}
	}
	//alert("validar"+valida);
	//alert("ddddddd"+d);
	if(valida!=0){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlProgramas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			Contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=4&modifica="+d+"&valida="+valida);
	
  }else{
  alert("Debe seleccionar almenos un Programa a modificar");
  }
 }
}


function ModificarPP(){
	
	 var c=document.getElementById('yo').value;
	 //alert("contador "+c);
	 var d="";
	 var valida=0;
	 if(c!=0){
		if(c!=1){ 
		for(var i=0; i<=c-1; i++){	
	      if (document.form1.ca[i].checked) {
	       	 d=d+document.form1.ca[i].value; 
	       	 d=d+"|";
	       	 valida=valida+1;
	     }     	  
	    }
		}else{
			if(document.form1.ca.checked){
				 d=document.form1.ca.value;
				 valida=valida+1;
			}
		}
		//alert("validar"+valida);
		//alert("ddddddd"+d);
		if(valida!=0){
		
		ajax=getXMLObject();
		ajax.open("POST","ControlProgramas",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				document.getElementById("Contenido").innerHTML = ajax.responseText	
			}
		}
		ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
		ajax.send("valor=16&modifica="+d+"&valida="+valida);
		
	  }else{
	  alert("Debe seleccionar almenos un Programa a modificar");
	  }
	 }
	}


function CrearP(){
	
	var contenido=document.getElementById('Contenido');	
	var mbase=document.getElementById("mbase").value;
	var comp=document.getElementById("comp").value;
	var cref=document.getElementById("cref").value;
	var cups=document.getElementById("cups").value;
	var desc=document.getElementById("desc").value;
	var espe=document.getElementById("espe").value;
	var serv=document.getElementById("serv").value;
	var subc=document.getElementById("subc").value;
	var rips=document.getElementById("rips").value;
	//var prn=document.getElementById("rn").value;
	var prn="";if(document.getElementById("rnn").checked){prn="1";}else{prn="0";}
	var paq="";if(document.getElementById("aqn").checked){paq="1";}else{paq="0";}
	var pme="";if(document.getElementById("medn").checked){pme="1";}else{pme="0";}
	
	desc=encodeURIComponent(desc);
	
	var user=document.getElementById("txtCodusuario").value;
	
	//alert("prniiii "+prn+" paq: "+paq+" pme: "+pme);
	//alert("mbase "+mbase+" comp "+comp+" cref "+cref+" cups "+cups+" desc "+desc+" espe "+espe+" serv "+serv+" subc "+subc+" rips "+rips);
	
	if(mbase=="Seleccione" || comp=="Seleccione" || cref=="" || cups=="" || desc=="" || espe=="Seleccione" || serv=="Seleccione" || subc=="Seleccione" || rips=="Seleccione" ){alert("Debe digitar todos los campos del programa a crear");}else{
    
	ajax=getXMLObject();
	ajax.open("POST","ControlProgramas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert("Ingreso Exitoso!!!");
			contenido.innerHTML = ajax.responseText	
			Radio();
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=5&mbase="+mbase+"&comp="+comp+"&cref="+cref+"&cups="+cups+"&desc="+desc+"&espe="+espe+"&serv="+serv+"&subc="+subc+"&rips="+rips+"&user="+user+"&prn="+prn+"&paq="+paq+"&pme="+pme);
	
  }
}



function CrearP2(v){
	
	var contenido=document.getElementById('Contenido');	
	var mbase=document.getElementById("mbase"+v).value;
	var comp=document.getElementById("comp"+v).value;
	var cref=document.getElementById("cref"+v).value;
	var cups=document.getElementById("cups"+v).value;
	var desc=document.getElementById("desc"+v).value;
	var espe=document.getElementById("espe"+v).value;
	var serv=document.getElementById("serv"+v).value;
	var subc=document.getElementById("subc"+v).value;
	var rips=document.getElementById("rips"+v).value;
	//var prn=document.getElementById("rn"+v).value;
	var prn="";if(document.getElementById("rnn"+v).checked){prn="1";}else{prn="0";}
	var paq="";if(document.getElementById("aqn"+v).checked){paq="1";}else{paq="0";}
	var pme="";if(document.getElementById("medn"+v).checked){pme="1";}else{pme="0";}
	
	var user=document.getElementById("txtCodusuario").value;
	
	//alert("prniiii "+prn+" paq: "+paq+" pme: "+pme);
	
	desc=encodeURIComponent(desc);
	
	//alert("mbase "+mbase+" comp "+comp+" cref "+cref+" cups "+cups+" desc "+desc+" espe "+espe+" serv "+serv+" subc "+subc+" rips "+rips);
	
	if(mbase=="Seleccione" || comp=="Seleccione" || cref=="" || cups=="" || desc=="" || espe=="Seleccione" || serv=="Seleccione" || subc=="Seleccione" || rips=="Seleccione" || prn=="Seleccione"){alert("Debe digitar todos los campos del programa a crear");}else{
    
	ajax=getXMLObject();
	ajax.open("POST","ControlProgramas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert("Ingreso Exitoso!!!");
			contenido.innerHTML = ajax.responseText	
			Radio();
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=5&mbase="+mbase+"&comp="+comp+"&cref="+cref+"&cups="+cups+"&desc="+desc+"&espe="+espe+"&serv="+serv+"&subc="+subc+"&rips="+rips+"&user="+user+"&prn="+prn+"&paq="+paq+"&pme="+pme);
	
  }
}


function ModificarPExistente(v,y){
	
	var contenido=document.getElementById('ripss');	
	//alert(v+" - "+y);
	if(y==1){
	var mbase=document.getElementById("mbase"+v).value;
	//alert(mbase); 
	}
	if(y==2){
	var comp=document.getElementById("comp"+v).value;
	//alert(comp);
	}
	if(y==3){
	var cref=document.getElementById("cref"+v).value;
	//alert(cref);
	}
	if(y==4){
	var cups=document.getElementById("cups"+v).value;
	//alert(cups);
	}
	if(y==5){
	var desc=document.getElementById("desc"+v).value;
	//alert(desc);
	}
	if(y==6){
	var espe=document.getElementById("espe"+v).value;
	//alert(espe);
	}
	if(y==7){
	var serv=document.getElementById("serv"+v).value;
	//alert(serv);
	}
	if(y==8){
	var subc=document.getElementById("subc"+v).value;
	//alert(subc);
	}
	if(y==9){
	var rips=document.getElementById("rips"+v).value;
	//alert(rips);
	}
	if(y==10){
	var prn="";if(document.getElementById("rn"+v).checked){prn="1";}else{prn="0";}		
	//var prn=document.getElementById("rn"+v).value;
	//alert(prn);
	}
	if(y==11){
		var paq="";if(document.getElementById("aq"+v).checked){paq="1";}else{paq="0";}		
		//var prn=document.getElementById("rn"+v).value;
		//alert(prn);
		}
	if(y==12){
		var pme="";if(document.getElementById("med"+v).checked){pme="1";}else{pme="0";}		
		//var prn=document.getElementById("rn"+v).value;
		//alert(prn);
		}
	//alert("mbase "+mbase+" comp "+comp+" cref "+cref+" cups "+cups+" desc "+desc+" espe "+espe+" serv "+serv+" subc "+subc+" rips "+rips);
	
	ajax=getXMLObject();
	ajax.open("POST","ControlProgramas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert("Act Exitoso!!!");
			//contenido.innerHTML = ajax.responseText	
			//Radio();
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	
	//ajax.send("valor=6&mbase="+mbase+"&comp="+comp+"&cref="+cref+"&cups="+cups+"&desc="+desc+"&espe="+espe+"&serv="+serv+"&subc="+subc+"&rips="+rips);
	
	if(y==1){
		ajax.send("valor=6&mbase="+mbase+"&y="+y+"&v="+v);
	}
	if(y==2){
		ajax.send("valor=6&comp="+comp+"&y="+y+"&v="+v);
	}
	if(y==3){
		ajax.send("valor=6&cref="+cref+"&y="+y+"&v="+v);
	}
	if(y==4){
		ajax.send("valor=6&cups="+cups+"&y="+y+"&v="+v);
	}
	if(y==5){
		desc=encodeURIComponent(desc);
		ajax.send("valor=6&desc="+desc+"&y="+y+"&v="+v);
	}
	if(y==6){
		ajax.send("valor=6&espe="+espe+"&y="+y+"&v="+v);
	}
	if(y==7){
		ajax.send("valor=6&serv="+serv+"&y="+y+"&v="+v);
	}
	if(y==8){
		ajax.send("valor=6&subc="+subc+"&y="+y+"&v="+v);
	}
	if(y==9){
		ajax.send("valor=6&rips="+rips+"&y="+y+"&v="+v);
	}
	if(y==10){
		ajax.send("valor=6&prn="+prn+"&y="+y+"&v="+v);
	}
	if(y==11){
		ajax.send("valor=6&paq="+paq+"&y="+y+"&v="+v);
	}
	if(y==12){
		ajax.send("valor=6&pme="+pme+"&y="+y+"&v="+v);
	}
}




/////////////////////////////////




function ConsultarPq(){
	
	var contenido=document.getElementById('Contenido');	
	var mbase=document.getElementById("mbase").value;
	var comp=document.getElementById("comp").value;
	var cref=document.getElementById("cref").value;
	var desc=document.getElementById("desc").value;
	var subc=document.getElementById("subc").value;

	
	//alert("mbase "+mbase+" comp "+comp+" cref "+cref+" cups "+cups+" desc "+desc+" espe "+espe+" serv "+serv+" subc "+subc+" rips "+rips);
	
	if(mbase=="Seleccione" && comp=="Seleccione" && cref=="" && desc=="" && subc=="Seleccione"){alert("Debe Ingresar al menos un Criterio de Busqueda");}else{
    
	ajax=getXMLObject();
	ajax.open("POST","ControlProgramas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=7&mbase="+mbase+"&comp="+comp+"&cref="+cref+"&desc="+desc+"&subc="+subc);
	
  }
}



function ModificarPq(){
	
	 var c=document.getElementById('yoq').value;
	// alert("contador "+c);
	 var d="";
	 var valida=0;
	 if(c!=0){
		if(c!=1){ 
		for(var i=0; i<=c-1; i++){	
	      if (document.form1.ca[i].checked) {
	       	 d=d+document.form1.ca[i].value; 
	       	 d=d+"|";
	       	 valida=valida+1;
	     }     	  
	    }
		}else{
			if(document.form1.ca.checked){
				 d=document.form1.ca.value;
				 valida=valida+1;
			}
		}
	//	alert("validar"+valida);
	//	alert("ddddddd"+d);
		if(valida!=0){
		
		ajax=getXMLObject();
		ajax.open("POST","ControlProgramas",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				Contenido.innerHTML = ajax.responseText	
			}
		}
		ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
		ajax.send("valor=8&modifica="+d+"&valida="+valida);
		
	  }else{
	  alert("Debe seleccionar almenos un Programa a modificar");
	  }
	 }
	}



function ModificarPqExistente(v,y){
	
	var contenido=document.getElementById('ripss');	
	//alert(v+" - "+y);
	if(y==1){
	var mbase=document.getElementById("mbase"+v).value;
	//alert(mbase); 
	}
	if(y==2){
	var comp=document.getElementById("comp"+v).value;
	//alert(comp);
	}
	if(y==3){
	var cref=document.getElementById("cref"+v).value;
	//alert(cref);
	}
	if(y==4){
	var desc=document.getElementById("desc"+v).value;
	//alert(desc);
	}
	if(y==5){
	var subc=document.getElementById("subc"+v).value;
	//alert(subc);
	}
	

	//alert("mbase "+mbase+" comp "+comp+" cref "+cref+" cups "+cups+" desc "+desc+" espe "+espe+" serv "+serv+" subc "+subc+" rips "+rips);
	
	ajax=getXMLObject();
	ajax.open("POST","ControlProgramas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert("Act Exitoso!!!");
			//contenido.innerHTML = ajax.responseText	
			//Radio();
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	
	//ajax.send("valor=6&mbase="+mbase+"&comp="+comp+"&cref="+cref+"&cups="+cups+"&desc="+desc+"&espe="+espe+"&serv="+serv+"&subc="+subc+"&rips="+rips);
	
	if(y==1){
		ajax.send("valor=9&mbase="+mbase+"&y="+y+"&v="+v);
	}
	if(y==2){
		ajax.send("valor=9&comp="+comp+"&y="+y+"&v="+v);
	}
	if(y==3){
		ajax.send("valor=9&cref="+cref+"&y="+y+"&v="+v);
	}
	if(y==4){
		ajax.send("valor=9&desc="+desc+"&y="+y+"&v="+v);
	}
	if(y==5){
		ajax.send("valor=9&subc="+subc+"&y="+y+"&v="+v);
	}
	
}


function CrearPq(){
	
	var contenido=document.getElementById('Contenido');	
	var mbase=document.getElementById("mbase").value;
	var comp=document.getElementById("comp").value;
	var cref=document.getElementById("cref").value;
	var desc=document.getElementById("desc").value;
	var subc=document.getElementById("subc").value;
	
	//alert("prn "+prn);
	//alert("mbase "+mbase+" comp "+comp+" cref "+cref+" cups "+cups+" desc "+desc+" espe "+espe+" serv "+serv+" subc "+subc+" rips "+rips);
	
	if(mbase=="Seleccione" || comp=="Seleccione" || cref=="" || desc=="" || subc=="Seleccione" ){alert("Debe digitar todos los campos del Servicio a crear");}else{
    
	ajax=getXMLObject();
	ajax.open("POST","ControlProgramas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert("Ingreso Exitoso!!!");
			contenido.innerHTML = ajax.responseText	
			Radio();
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=10&mbase="+mbase+"&comp="+comp+"&cref="+cref+"&desc="+desc+"&subc="+subc);
	
  }
}


function CrearPq2(v){
	//alert(v);
	var contenido=document.getElementById('Contenido');	
	var mbase=document.getElementById("mbasen"+v).value;
	var comp=document.getElementById("compn"+v).value;
	var cref=document.getElementById("crefn"+v).value;
	var desc=document.getElementById("descn"+v).value;
	var subc=document.getElementById("subcn"+v).value;

	
	//alert("mbase "+mbase+" comp "+comp+" cref "+cref+" cups "+cups+" desc "+desc+" espe "+espe+" serv "+serv+" subc "+subc+" rips "+rips);
	
	if(mbase=="Seleccione" || comp=="Seleccione" || cref=="" || desc=="" || subc=="Seleccione" ){alert("Debe digitar todos los campos del Servicio a crear");}else{
    
	ajax=getXMLObject();
	ajax.open("POST","ControlProgramas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert("Ingreso Exitoso!!!");
			contenido.innerHTML = ajax.responseText	
			Radio();
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=10&mbase="+mbase+"&comp="+comp+"&cref="+cref+"&desc="+desc+"&subc="+subc);
	
  }
}



function pq(v,x){
	//alert("x: "+x);
	var table = document.getElementById("tpq");   
	var rows = table.getElementsByTagName("tr");   
	for(i = 1; i < rows.length; i++){ 
		rows[i].className = "blanco"; 
	}
	rows[v+1].className = "gris"; 
	
	var contenido=document.getElementById('prog');	
	ajax=getXMLObject();
	ajax.open("POST","ControlProgramas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert("Ingreso Exitoso!!!");
			contenido.innerHTML = ajax.responseText	
			//Radio();
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=11&prog="+x);
}


function pq2(v,x){
	//alert("v: "+v+" x: "+x);
	var mbase=document.getElementById("mbase"+x).value;  
	var table = document.getElementById("tapq");   
	var rows = table.getElementsByTagName("tr");   
	for(i = 1; i < rows.length; i++){ 
		rows[i].className = "blanco"; 
	}
	rows[v].className = "azul"; 
	
	var contenido=document.getElementById('progs');	
	ajax=getXMLObject();
	ajax.open("POST","ControlProgramas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert("Ingreso Exitoso!!!");
			contenido.innerHTML = ajax.responseText	
			//Radio();
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=12&prog="+x+"&mbase="+mbase+"&v="+v);
}

function cesarj(){
	//alert("cesar");
	cachesugerencias1 = {};
}

function Asignarpq(prog,v){
	//alert("pasando v: "+v);
	var contenido=document.getElementById('Contenido');	
	var codp=document.getElementById("desch0").value;
   
	ajax=getXMLObject();
	ajax.open("POST","ControlProgramas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert("Asignación Exitosa!!!");
			pq2(v,prog)
			//contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=13&prog="+prog+"&codp="+codp);
}


function Eliminarpq(pp,prog,v){
	//alert("programapaquete: "+pp);
	var contenido=document.getElementById('Contenido');	
	//var codp=document.getElementById("desch0").value;
   
	ajax=getXMLObject();
	ajax.open("POST","ControlProgramas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert("Eliminación Exitosa!!!");
			pq2(v,prog)
			//contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=14&prog="+prog+"&pp="+pp);
}
//////////////////////////
