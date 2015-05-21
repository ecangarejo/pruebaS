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
///////////////////////////////////////////////////////////////////////////////////////

function Aprob(){
	ajax=getXMLObject();
	var cont=document.getElementById("aprob");
	ajax.open("POST","ControlAprobar",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			cont.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ListaPendxA");
}

function VerO(codigo){
	document.getElementById("vistaprob").innerHTML="";
	pagina="ord_VerDetalleOrden.jsp?codigo="+codigo;			
   	var opciones="toolbar=no, location=no, directories=no, status=yes, menubar=yes,";
 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);	
}

function AprobO(codigo){
	ajax=getXMLObject();
	var cont=document.getElementById("vistaprob");
	var user=document.getElementById("user").value;
	ajax.open("POST","ControlAprobar",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			cont.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Aprob1&codigo="+codigo+"&user="+user);
}

function GAprob(codigo){
	ajax=getXMLObject();
	ajax.open("POST","ControlAprobar",true);
	var opc=confirm("¿Desea aprobar la Orden de Compra seleccionada?");
	if(opc){
		var user=document.getElementById("user").value;
		var ob=document.getElementById("oap").value;
		var obs=encodeURIComponent(ob);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var datos=ajax.responseText;
				if(datos==0){
					alert("Aprobacion Exitosa!")
				}else{
					alert("Ups ha courrido un error !");
				}
				window.location.reload();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Gaprob&codigo="+codigo+"&user="+user+"&obs="+obs);
	}else{}
	
}

function RechO(codigo){
	ajax=getXMLObject();
	var cont=document.getElementById("vistaprob");
	var user=document.getElementById("user").value;
	ajax.open("POST","ControlAprobar",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			cont.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Rech1&codigo="+codigo+"&user="+user);
	
}

function GRech(codigo){
	ajax=getXMLObject();
	ajax.open("POST","ControlAprobar",true);
	var opc=confirm("¿Desea Rechazar la Orden de Compra seleccionada?");
	if(opc){
		var user=document.getElementById("user").value;
		var ob=document.getElementById("orec").value;
		var obs=encodeURIComponent(ob);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var datos=ajax.responseText;
				if(datos==0){
					alert("Rechazo Exitoso!")
				}else{
					alert("Ups ha courrido un error !");
				}
				window.location.reload();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Grech&codigo="+codigo+"&user="+user+"&obs="+obs);
	}else{}
}

function ModiO(codigo){
	//alert();
	var cont=document.getElementById("aprob");
	ajax=getXMLObject();
	ajax.open("POST","ControlAprobar",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			cont.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ModOC&codigo="+codigo);
}


function AdProd(codoc,codprove){
	/*Adicionar un registro en la orden ya emitida */
	var opc=confirm("¿Desea agregar este item a su orden de compra ?");
	if(opc){
		var cont=document.getElementById("aprob");
		var user=document.getElementById("user").value;
		var codprod=document.getElementById("codprodu").value;
		var codre=document.getElementById("codref").value;
		var codref=encodeURIComponent(codre);
		var nombr=document.getElementById("descrip").value;
		var nombre=encodeURIComponent(nombr);
		var cant=document.getElementById("cant").value;
		var punit=document.getElementById("punit").value;
		var subtotal=document.getElementById("subtotal").value;
		var iva=document.getElementById("iva").value;
		var ptotal=document.getElementById("ptotal").value;
		var codiiva=document.getElementById("codiiva").value;
		var codtarifa=document.getElementById("codtarifa").value;
		var desc=document.getElementById("desc").value;
		ajax=getXMLObject();
		if((user=="")||(codprod=="")||(nombre=="")||(cant=="")||(punit=="")||(subtotal=="")||(iva=="")||(ptotal=="")||(codiiva=="")||(codtarifa=="")||(desc=="")){
			alert("Deben estar llenos todos los campos ");
		}else{
			ajax.open("POST","ControlAprobar",true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					alert("Adicion Exitosa");
					ModiO(codoc);
				}
			}
			alert("valor=Adprod&codigo="+codoc+"&user="+user+"&codprove="+codprove+"&codprod="+codprod+"&codref="+codref+"&nombre="+nombre+"&cant="+cant+"&punit="+punit+"&subtotal="+subtotal+"&iva="+iva+"&ptotal="+ptotal+"&codiiva="+codiiva+"&codtarifa="+codtarifa+"&desc="+desc);
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=Adprod&codigo="+codoc+"&user="+user+"&codprove="+codprove+"&codprod="+codprod+"&codref="+codref+"&nombre="+nombre+"&cant="+cant+"&punit="+punit+"&subtotal="+subtotal+"&iva="+iva+"&ptotal="+ptotal+"&codiiva="+codiiva+"&codtarifa="+codtarifa+"&desc="+desc);
		}
	}else{}
	
}

function ElimO(codigo,coddet){
	var user=document.getElementById("user").value;
	var con="";
	var opc=confirm("Esta seguro de eliminar este producto de su orden de compra ? ");
	if(opc){
		var concept=prompt("Digite una observacion","[OBSERVACION]");
		
		if(concept==""){
			 alert("Observacion no puede ir vacia");
			if(concept=="[OBSERVACION]"){
				alert("Digite una observacion valida");
			}
		}else{
			con=concept;
			ajax=getXMLObject();
			ajax.open("POST","ControlAprobar",true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					alert("Eliminacion Exitosa !");
					ModiO(codigo);
				}
			}
			alert("valor=ElimO&codigo="+codigo+"&coddet="+coddet+"&user="+user+"&concept="+con);
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=ElimO&codigo="+codigo+"&coddet="+coddet+"&user="+user+"&concept="+con);
		}
	}else{}
}
function VerDetalleO(codigo){
	cont=document.getElementById("verdetalle");
	ajax=getXMLObject();
	ajax.open("POST","ControlAprobar",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			cont.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=VDetO&codigo="+codigo);
}
