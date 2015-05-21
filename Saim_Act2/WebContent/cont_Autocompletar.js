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
// ////////////////////////////////////////////////////////////////////////////////////////////////////////
/*function Causa() {//Consulta si el periodo se generó y si no esta bloqueado
	
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			//document.getElementById('AC').focus();
			
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=50"); 
	
}*/
/*var item=1;
function newfile(){
	//alert("newfile");	
	var table = document.getElementById("asiento");
	var rowCount = table.rows.length;
	//alert(rowCount);
    var row = table.insertRow(rowCount-2);

    var cell0 = row.insertCell(0);
    var element0 = document.createElement('label'); 
    element0.innerHTML =item;
    cell0.appendChild(element0);
      
    var cell1 = row.insertCell(1);
    var element1 = document.createElement("input");
    element1.type = "text";
    element1.setAttribute("id", "cta"+item);
    element1.style.width='100%';
    element1.value=document.getElementById('cta0').value;
    document.getElementById('cta0').value="";
    cell1.appendChild(element1);

    var cell2 = row.insertCell(2);
    var element2 = document.createElement("input");
    element2.type = "text";
    element2.setAttribute("id", "ter"+item);
    element2.style.width='100%';
    element2.value=document.getElementById('ter0').value;
    document.getElementById('ter0').value="";
    cell2.appendChild(element2);
    

    var cell3 = row.insertCell(3);
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.setAttribute("id", "doc"+item);
    element3.style.width='100%';
    element3.value=document.getElementById('doc0').value;
    document.getElementById('doc0').value="";
    cell3.appendChild(element3);

    var cell4 = row.insertCell(4);
    var element4 = document.createElement("input");
    element4.type = "text";
    element4.setAttribute("id", "deb"+item);
    element4.style.width='100%';
    element4.value=document.getElementById('deb0').value;
    document.getElementById('deb0').value="";
    cell4.appendChild(element4);
    
    var cell5 = row.insertCell(5);
    var element5 = document.createElement("input");
    element5.type = "text";
    element5.setAttribute("id", "cre"+item);
    element5.style.width='100%';
    element5.value=document.getElementById('cre0').value;
    document.getElementById('cre0').value="";
    // if (element5.attachEvent) { element5.attachEvent ("onblur", function () {newfile()}); }
    // if (element5.addEventListener) { element5.addEventListener ("blur", function () {newfile()},false);}
    cell5.appendChild(element5);
    
    item++;
    ocument.getElementById('cta0').focus;
}
*/
var xx=null;
var art=null;
var artl=null;
var Seleccionado = -1;
//var listactas=null;
function Completa(cta,d,evt,controlador,opc,iam,nopc,xx) {//Consulta sla cuenta para causarla
	//alert("cta: "+cta+" d: "+d+" evt: "+evt+" controlador: "+controlador+" opc: "+opc+" iam: "+iam+" nopc: "+nopc+" xx :"+xx);
	var ncta=cta.value;
	
	if(ncta!=""){
	
	ajax = getXMLObject();
	
	var tecla=evt.keyCode;
	var ctatxt=cta.value;
	//alert(ncta+" - "+controlador+" - "+opc);
	
	 if(tecla == 40) { // Flecha Abajo
     	//alert("presiono abajo");
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
		  
		  if(iam==1){ mostrar[x]=z[1]; }
		  if(iam==2){ mostrar[x]=z[1]+"       &nbsp;&nbsp;&nbsp;         "+z[2]; }
		  if(iam==3){ mostrar[x]=z[1]+"       &nbsp;&nbsp;&nbsp;         "+z[2]+"       &nbsp;&nbsp;&nbsp;         "+z[3]; }
		  if(iam==4){ mostrar[x]=z[1]+"       &nbsp;&nbsp;&nbsp;         "+z[2]+"       &nbsp;&nbsp;&nbsp;         "+z[3]+"       &nbsp;&nbsp;&nbsp;         "+z[4]; }
		 
		}
		
     var codigoHtml = "<ul>";	
       for(var i=0; i<artl; i++) {
    	  // alert(mostrar[i]+" ceasr "+art[i]);
            if(i == Seleccionado) {
                codigoHtml += '<li class= \"seleccionado\">'+mostrar[i]+'</li>';
            }else {
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

/*function ConfirmarDatos(e,d,cta){
	//alert(e+" - "+d+" - "+cta);
	var z=e.split("|");		     	
	 codigo=z[0];
	 numero=z[1];
	 nombre=z[2];
	 document.getElementById(cta).value = numero;
	 document.getElementById(cta).title=nombre;
	 document.getElementById(d).innerHTML = "";
     document.getElementById(d).style.display = "none";
     cta.focus;
}
*/






