function getXMLObject() // XML OBJECT
{
	var xmlHttp = false;
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP") // For Old Microsoft
		// Browsers
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP") // For Microsoft
			// IE 6.0+
		} catch (e2) {
			xmlHttp = false // No Browser accepts the XMLHTTP Object then false
		}
	}
	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest(); // For Mozilla, Opera Browsers
	}
	return xmlHttp; // Mandatory Statement returning the ajax object created
}
var xmlhttp = new getXMLObject(); // xmlhttp holds the ajax object

var xx=null;
var art=null;
var artl=null;
var Seleccionado = -1;

function Completa(cta,d,evt,controlador,opc,iam,nopc,xx) {//Consulta sla cuenta para causarla
	
	var ncta=cta.value;
	
	if(ncta!=""){
	
	ajax = getXMLObject();
	
	var tecla=evt.keyCode;
	var ctatxt=cta.value;
	
	
	 if(tecla == 40) { // Flecha Abajo
     	
       if(Seleccionado+1 < artl) { Seleccionado++; }
       mostrarctas(d,cta,iam,nopc,evt);
     }else{ 
       if(tecla == 38) { // Flecha Arriba
         //alert("presiono arriba");
         if(Seleccionado > 0) {  Seleccionado--;}
         mostrarctas(d,cta,iam,nopc,evt);
           }else{
             if(tecla == 13) { // ENTER o Intro
            	 ConfirmarDatos(art[Seleccionado],d.id,cta.id,nopc,evt);//ConfirmarDatos
            	 return;
                // seleccionaElementoArticulo(articulos1, idiv, itex);
             }else {
             	//  texto = document.getElementById("desc"+itex).value;
                 // Si es la tecla de borrado y el texto es vacío, ocultar la lista
                 if((tecla == 8) && (ctatxt == "")) {
                	// document.getElementById("dcta").innerHTML = "";
                    // document.getElementById("dcta").style.display = "none";
                	 d.innerHTML = "";
                     d.style.display = "none";
                     return;
                 }else{
                 //////////ajax////////////
                	 ajax.open("POST",controlador, true); // getname will be the
                		ajax.onreadystatechange = function() {
                			if (ajax.readyState == 4) {
                			
                				 art = eval('('+ajax.responseText+')');
                				 artl=art.length;
                	                
                	             var navegador = navigator.userAgent;
                	             if (navigator.userAgent.indexOf('MSIE') !=-1) {//alert('está usando Internet Explorer ...');
                	            	 artl=artl-1;
                	             } /*else if (navigator.userAgent.indexOf('Firefox') !=-1) {//alert('está usando Firefox ...');
                	             } else if (navigator.userAgent.indexOf('Chrome') !=-1) {// alert('está usando Google Chrome ...');
                	             } else if (navigator.userAgent.indexOf('Opera') !=-1) {// alert('está usando Opera ...');
                	             } */
                	             Seleccionado = -1;
                	             
                	             if(artl==0){
                	            	 //document.getElementById("dcta").innerHTML = "No existe registro";
                                     //document.getElementById("dcta").style.display = "block";
                	            	 d.innerHTML = "No existe registro";
                                     d.style.display = "block";
                	             }else{
                	             mostrarctas(d,cta,iam,nopc,evt);            
                	             }
                	             // alert(artl);
                	            
                				
                			}
                		}
                		
                		if(xx!=undefined){
                			xx=document.getElementById(xx).value; 
                		}
                		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
                		//alert("valor="+opc+"&texto="+ncta+"&xx="+xx); 
                		ajax.send("valor="+opc+"&texto="+ncta+"&xx="+xx); 
                		
                		//var ee="epsh0";
                        //=document.getElementById(ee).value;  
                       	
                		///////fin ajax//////////
                		
                 }//fin else tecla borrar
               }//fin else selecciona elemento
              }//fin else flecha arriba
             }//fin else flecha abajo
	
		
	}else{
	d.innerHTML = "";
	d.style.display = "none";
	}
		

	
}


function mostrarctas(d,cta,iam,nopc,evt){
	
	var mostrar={};
    var unoauno=null;
		for(x=0; x<artl; x=x+1){
		  unoauno=art[x];
		  z=unoauno.split("|");
		  
		  if(iam==1){ mostrar[x]=z[1];}
		  //if(iam==2){ mostrar[x]=z[1]+"       &nbsp;&nbsp;&nbsp;         "+z[2]; }
		  //if(iam==2){ mostrar[x]=z[1]+"       &nbsp;&nbsp;&nbsp;         "+z[2]+"       &nbsp;&nbsp;&nbsp;         "+z[3]; }
		  //if(iam==2){ mostrar[x]=z[1]+"       &nbsp;&nbsp;&nbsp;         "+z[2]+"       &nbsp;&nbsp;&nbsp;         "+z[3]+"       &nbsp;&nbsp;&nbsp;         "+z[4]; }
		  //if(iam==5){ mostrar[x]=z[1]+"       &nbsp;&nbsp;&nbsp;         "+z[2]+"       &nbsp;&nbsp;&nbsp;         "+z[3]+"       &nbsp;&nbsp;&nbsp;         "+z[4]+"       &nbsp;&nbsp;&nbsp;         "+z[5]; }
		  if(iam==2){ mostrar[x]=z[1]+"       &nbsp;&nbsp;&nbsp;         "+z[2]+"       &nbsp;&nbsp;&nbsp;         "+z[3]+"       &nbsp;&nbsp;&nbsp;         "+z[4]+"       &nbsp;&nbsp;&nbsp;         "+z[5]; }
		}
		
     var codigoHtml = "<ul>";	
       for(var i=0; i<artl; i++) {
    	  // alert(mostrar[i]+" ceasr "+art[i]);
            if(i == Seleccionado) {
                codigoHtml += '<li class= \"seleccionado\">'+mostrar[i]+'</li>';
            }else {
            	//codigoHtml += "<li onclick='ConfirmarDatos(&quot;"+art[i]+"&quot;,&quot;"+d.id+"&quot;,&quot;"+cta.id+"&quot;,&quot;"+s.id+"&quot;,&quot;"+fd.id+"&quot;,&quot;"+md.id+"&quot;,"+nopc+",&quot;"+evt+"&quot;)'>"+mostrar[i]+"</li>";
            	codigoHtml += "<li onclick='ConfirmarDatos(&quot;"+art[i]+"&quot;,&quot;"+d.id+"&quot;,&quot;"+cta.id+"&quot;,"+nopc+",&quot;"+evt+"&quot;)'>"+mostrar[i]+"</li>";
            }
        }
     codigoHtml += "</ul>";
   //  alert(codigoHtml);
   // document.getElementById("dcta").innerHTML=codigoHtml;
   // document.getElementById("dcta").style.display = 'block';	
     d.innerHTML=codigoHtml;
     d.style.display = 'block';	
  
}








