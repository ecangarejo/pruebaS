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



function abrirVentana() {

	ajax = getXMLObject();
	var contenido1 = document.getElementById('Opcion');
	ajax.open("POST", "ControlPrincipioActivo", true);

	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido1.innerHTML = ajax.responseText
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=30A"); // Posting txtname to Servlet
}


function crear(){
	var nombre=document.getElementById('nombre').value;
	var fk=document.getElementById('fk').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlPrincipioActivo",true);
	ajax.onreadystatechange = function(){
	if(ajax.readyState == 4){
	alert(ajax.responseText);
	window.location.reload();
	}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1&nombre="+nombre+"&fk="+fk);

										
									}

								
							
						
										
				
			
		
	


