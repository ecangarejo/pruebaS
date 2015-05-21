

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


	function Cedula_General() {
		getXMLObject();
		 
		  if(xmlhttp) {
			
			//  alert("Listo...!");
			  
			xmlhttp.open("POST","lab_General?z="+1+"",true); //getname will be the servlet name
			xmlhttp.onreadystatechange  = Busqueda;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send(""); //Posting txtname to Servlet
				 
			 div = document.getElementById('resultado');
				
				div.style.display='none';
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

	