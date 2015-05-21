

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

function reforma() {
	getXMLObject();	 
	  if(xmlhttp) {
	var examenlab=document.getElementById('nombre').value;
	//alert("llego.."+examen);
	if(examenlab==""){
		alert("Seleccione Examen...");
	
	
	   
	}else{
		 var txtAccion="3";
		 xmlhttp.open("POST","lab_ExaBuscar?z="+txtAccion+"&nombre="+examenlab+"",true); 
		    xmlhttp.onreadystatechange  = restaurar;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send(""); //Posting txtname to Servlet*/
			
	}
	  }
	}
function restaurar() {

	if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			var kitty=xmlhttp.responseText;
			//alert("respuesta"+kitty);
			var resul;
			var area;
	     	var y=kitty.split("/").length;
	     	var z=kitty.split("/");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		    resul=z[0];
		    area=z[1];
		    // form1.cbArea.value();
		    }
	    	
			//alert("tipo jajaja"+resul);
			document.getElementById('tipo2').value =resul ;
			document.getElementById('area').value =area ;
	     }
	
	}
	Abrir_ventana_yosi('yo','Ayuda','width=2000,height=500');
	 
}

function BuscarExamencomp(cf,k){
	
	getXMLObject();	 
	  if(xmlhttp) {
		  
		  
		  var ced="";
		  var tipodocu="";
		  
		   ced= document.form1.cedula.value;
		  var tip = document.form1.cbtipo.selectedIndex;
		   tipodocu=document.form1.cbtipo.options[tip].text; 
		   
		 		 // alert(cf+" cantidad escojidos"+k);
			   xmlhttp.open("POST","lab_BusPac?z="+9+"&ced="+ced+"&tipo="+tipodocu+"&exa="+cf+"&cantidad="+k+"",true); 
			    xmlhttp.onreadystatechange  = Tabla;
			    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				xmlhttp.send(""); //Posting txtname to Servlet*/	 
	  }
	   var x;
	
}

function Cedulacomp (){
	
	 getXMLObject();	 
	  if(xmlhttp) {
		  
		xmlhttp.open("POST","lab_BusPac?z="+8+"",true); 
	    xmlhttp.onreadystatechange  = Muestra;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send(""); //Posting txtname to Servlet*/
			 
	  }
	   var x;	
	 
}
	
	function ReportesGrupo(){
		  getXMLObject();	 
		  if(xmlhttp) {
			  var cedula="";var subarea="";
			  var nomuni="";
			  var uni = document.form1.tipo.selectedIndex;
			  nomuni=document.form1.tipo.options[uni].text;
			  var area = document.form1.cbarea.selectedIndex;
			  labarea=document.form1.cbarea.options[area].text;
			  var sub = document.form1.subare.selectedIndex;
			  subarea=document.form1.subare.options[sub].text;  
			  cedula=document.form1.cedulapac.value;
			  
			  if((subarea=="SELECCIONE...")||(cedula=="")||(nomuni=="")){
				  alert("Los campos no pueden ir vacios!!!");
			  }else{		  
		       xmlhttp.open("POST","FormatoGrupo?tipo="+nomuni+"&cedula="+cedula+"&subarea="+subarea+"&area="+labarea+"",true); 
		       xmlhttp.onreadystatechange  = ReporteGrupo;
		      xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send(""); //Posting txtname to Servlet
			  }		 
		  }
		   var x;	
	}
	
function Muestra() {
		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
				
				document.getElementById('busquedacomp').innerHTML = xmlhttp.responseText
				
		     	 
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		
		 
		}
function Tabla() {
	
	if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			//alert(xmlhttp);
			//alert(xmlhttp.responseText);
			document.getElementById('mostrarcomp').innerHTML = xmlhttp.responseText
			
	     	 
	     }
	     else {
	        alert("Error during AJAX call. Please try again");
	     }
	   }
	
	 
	}
	
function ReporteGrupo() {
		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
				
				document.getElementById('Reporte').innerHTML = xmlhttp.responseText
				
		     	 
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		
		 
		}
  function	buscarGrupo(){
	  
	  getXMLObject();
		 
	  if(xmlhttp) {
		  
		  var uni = document.form1.cbarea.selectedIndex;
		  var nomuni=document.form1.cbarea.options[uni].text;
		
		xmlhttp.open("POST","lab_BusPac?z="+7+"&area="+nomuni+"",true); //getname will be the servlet name
		xmlhttp.onreadystatechange  = Grupo;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send(""); //Posting txtname to Servlet
			 
	  }
	   var x;
	}
	
	function Cedula() {
		getXMLObject();
		 
		  if(xmlhttp) {
			
			xmlhttp.open("POST","lab_BusPac?z="+1+"",true); //getname will be the servlet name
			xmlhttp.onreadystatechange  = Busqueda;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send(""); //Posting txtname to Servlet
				 
			 div = document.getElementById('resultado');
				
				div.style.display='none';
		  }
		   var x;
		}
	function Cedula_Grupo() {
		getXMLObject();
		 
		  if(xmlhttp) {
		
			xmlhttp.open("POST","lab_BusPac?z="+4+"",true); //getname will be the servlet name
			xmlhttp.onreadystatechange  = busGrupo;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send(""); //Posting txtname to Servlet
				 
		  }
		   var x;
		}
	function Ced_Grupo() {
		getXMLObject();
		 
		  if(xmlhttp) {
		
			xmlhttp.open("POST","lab_BusPac?z="+6+"",true); //getname will be the servlet name
			xmlhttp.onreadystatechange  = busGrupo;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send(""); //Posting txtname to Servlet
				 
		  }
		   var x;
		}
	function Nombre() {
		getXMLObject();
		 
		  if(xmlhttp) {
			
			xmlhttp.open("POST","lab_BusPac?z="+3+"",true); //getname will be the servlet name
			xmlhttp.onreadystatechange  = Busqueda;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send(""); //Posting txtname to Servlet
				 
			 div = document.getElementById('resultado');
				
				div.style.display='none';
		  }
		   var x;
		}
	function Nombre1() {
		getXMLObject();
		 
		  if(xmlhttp) {
			
			xmlhttp.open("POST","lab_BusPac?z="+113+"",true); //getname will be the servlet name
			xmlhttp.onreadystatechange  = Busqueda;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send(""); //Posting txtname to Servlet
				 
			 div = document.getElementById('resultado');
				
				div.style.display='none';
		  }
		   var x;
		}
	   
function Grupo() {
		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
				
				document.getElementById('busGru').innerHTML = xmlhttp.responseText
				
		     	 
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
}
		
		 
	
	function Busqueda() {
		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
				
				document.getElementById('busqueda').innerHTML = xmlhttp.responseText
				
		     	 
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		
		 
		}
function busGrupo() {
		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
			document.getElementById('bus').innerHTML = xmlhttp.responseText;
		    }else {
		        alert("Error during AJAX call. Please try again");
		     }
		   } 
		}
	
	