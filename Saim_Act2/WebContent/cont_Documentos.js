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

function Periodo(ccme) {//Seleccionar el Periodo
	//alert("Periodo: "+ccme)
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			document.getElementById('AC').focus();
				
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1&ccme="+ccme); 
}


function vpc(ccme) {//Consulta si el periodo se gener� y si no esta bloqueado
//	alert("vpc: "+ccme);
	var pc = document.getElementById("MC").value;
	var ac = document.getElementById("AC").value;
	ajax = getXMLObject();
	//var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			var detDatos = ajax.responseText.split("|");
			var p=detDatos[0];
			var b=detDatos[1];
			if(p=="00"){
				alert("El periodo no ha sido se ha generado!!!");
				document.getElementById('AC').value="";
				document.getElementById('MC').value="";
				document.getElementById('AC').focus();
			}else{
				if(b=="1"){
					if(ccme==1){alert("El periodo esta Bloqueado... Solo estara activo para consultas!!!");
					vpc2(pc,ac,p,b,ccme);
					}
					if(ccme==2){alert("El periodo esta Bloqueado... No se puede acceder a Modificar ni a Eliminar Documentos!!!");
					document.getElementById('AC').value="";
					document.getElementById('MC').value="";
					document.getElementById('AC').focus();
					}
					
				}
				if(b=="0"){vpc2(pc,ac,p,b,ccme);}
			}
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2&pc="+pc+"&ac="+ac); 
}


function vpc2(pc,ac,p,b,ccme) {//Radios para Crear o Consultar Documentos    
//alert(pc+"-"+ac+"-"+p+"-"+b+"-"+ccme);
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	if(ccme==1){ajax.send("valor=3&pc="+pc+"&ac="+ac+"&p="+p+"&b="+b+"&ccme="+ccme); }
	if(ccme==2){ajax.send("valor=3m&pc="+pc+"&ac="+ac+"&p="+p+"&b="+b+"&ccme="+ccme); }

}

function RadioDoc(v,pc,ac,p,b,ccme) {//Se Crea o se Consulta el Documento	
	if(v=="4"){
		alert("Periodo BLOQUEADO!!!! No se pueden Crear Documentos ");
	}else{
		var xxx="";
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			if((v==1)||(v==2)||(v==3)){
				var xx=ajax.responseText;
				//alert(xx); 
				xxx=xx.replace(/CJ/g,"\u00f1"); 
				//alert(xxx);
			}else{
				xxx=ajax.responseText;
			}
			
			
			contenido.innerHTML = xxx;//ajax.responseText;
				
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4&v="+v+"&pc="+pc+"&ac="+ac+"&p="+p+"&b="+b+"&ccme="+ccme); 
	}
}


function TipoDoc(va) {//Se unifica el tipo de documento con el nombre
	if(va=="0"){
		var cc = document.getElementById("tipoDocC").value;
		var lc = document.getElementById("tipoDocC").length;
		var ln=lc-1;
		var indice;
		for(i=0;i<=ln;i++){
			if(cc==document.getElementById("tipoDocN").options[i].value){
			indice=i;
			}
		}
		document.getElementById("tipoDocN").text=document.getElementById("tipoDocN").options[indice].text;
		document.getElementById("tipoDocN").value=cc;
	}
	if(va=="1"){
		 cc = document.getElementById("tipoDocN").value;
		 lc = document.getElementById("tipoDocN").length;
		 ln=lc-1;
		 indice;
		document.getElementById("tipoDocC").text=cc;
		document.getElementById("tipoDocC").value=cc;
	}
	numdoc(cc);
}

function fpcont() {//Se verifica la plantilla y se montan los otros campos
	var pd = document.getElementById("Plantilla").value;
	ajax = getXMLObject();
	var contenido = document.getElementById('pcont');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;	
			document.getElementById('divtercero').innerHTML="";
			document.getElementById('Centrosc').innerHTML="";
			document.getElementById('SCentrosc').innerHTML="";
			document.getElementById('VBase').innerHTML="";
			
			if(pd!=0){div3();}
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5&pd="+pd); 

}




function vsucu(va) {//Se unifica el tipo de documento con el nombre
	if(va=="0"){
		var cc = document.getElementById("sucuc").value;
		var lc = document.getElementById("sucuc").length;
		var ln=lc-1;
		var indice;
		for(i=0;i<=ln;i++){
			if(cc==document.getElementById("sucun").options[i].value){
			indice=i;
			}
		}
		document.getElementById("sucun").text=document.getElementById("sucun").options[indice].text;
		document.getElementById("sucun").value=cc;
	}
	if(va=="1"){
		 cc = document.getElementById("sucun").value;
		 lc = document.getElementById("sucun").length;
		 ln=lc-1;
		 indice;
		document.getElementById("sucuc").text=cc;
		document.getElementById("sucuc").value=cc;
	}
	vcentros(cc,"0");

}

function vcentros(su,cos) {//Se verifica la sucursal y se cargan los centros de costo y tambien sirve para verificar el centro de costo y cargar los sucentros
	ajax = getXMLObject();
	if(cos=="0"){
	var contenido = document.getElementById('Centrosc');
	}
	if(cos=="1"){
	contenido = document.getElementById('SCentrosc');
	}
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;	
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=6&su="+su+"&cos="+cos); 

}


function vccosto(va) {//Se unifica el centro de costo con el nombre
	if(va=="0"){
		var cc = document.getElementById("ccostoc").value;
		var lc = document.getElementById("ccostoc").length;
		var ln=lc-1;
		var indice;
		for(i=0;i<=ln;i++){
			if(cc==document.getElementById("ccoston").options[i].value){
			indice=i;
			}
		}
		document.getElementById("ccoston").text=document.getElementById("ccoston").options[indice].text;
		document.getElementById("ccoston").value=cc;
	}
	if(va=="1"){
		 cc = document.getElementById("ccoston").value;
		 lc = document.getElementById("ccoston").length;
		 ln=lc-1;
		 indice;
		document.getElementById("ccostoc").text=cc;
		document.getElementById("ccostoc").value=cc;
	}
	vcentros(cc,"1");
}


function vter(va) {//Se unifica el centro de costo con el nombre
	if(va=="0"){
		var cc = document.getElementById("tercero").value;
		var lc = document.getElementById("tercero").length;
		var ln=lc-1;
		var indice;
		for(i=0;i<=ln;i++){
			if(cc==document.getElementById("terceron").options[i].value){
			indice=i;
			}
		}
		document.getElementById("terceron").text=document.getElementById("terceron").options[indice].text;
		document.getElementById("terceron").value=cc;
	}
	if(va=="1"){
		 cc = document.getElementById("terceron").value;
		 lc = document.getElementById("terceron").length;
		 ln=lc-1;
		 indice;
		document.getElementById("tercero").text=cc;
		document.getElementById("tercero").value=cc;
	}
}

function vbanco(va) {//Se unifica el tipo de documento con el nombre
	if(va=="0"){
		var cc = document.getElementById("ban").value;
		var lc = document.getElementById("ban").length;
		var ln=lc-1;
		var indice;
		for(i=0;i<=ln;i++){
			if(cc==document.getElementById("bann").options[i].value){
			indice=i;
			}
		}
		document.getElementById("bann").text=document.getElementById("bann").options[indice].text;
		document.getElementById("bann").value=cc;
	}
	if(va=="1"){
		 cc = document.getElementById("bann").value;
		 lc = document.getElementById("bann").length;
		 ln=lc-1;
		 indice;
		document.getElementById("ban").text=cc;
		document.getElementById("ban").value=cc;
	}
}

function vsccosto(va) {//Se unifica el centro de costo con el nombre
	if(va=="0"){
		var cc = document.getElementById("sccostoc").value;
		var lc = document.getElementById("sccostoc").length;
		var ln=lc-1;
		var indice;
		for(i=0;i<=ln;i++){
			if(cc==document.getElementById("sccoston").options[i].value){
			indice=i;
			}
		}
		document.getElementById("sccoston").text=document.getElementById("sccoston").options[indice].text;
		document.getElementById("sccoston").value=cc;
	}
	if(va=="1"){
		 cc = document.getElementById("sccoston").value;
		 lc = document.getElementById("sccoston").length;
		 ln=lc-1;
		 indice;
		document.getElementById("sccostoc").text=cc;
		document.getElementById("sccostoc").value=cc;
	}
	VBases();
}

function VBases() {//Se carga el valor base de la plantilla
	ajax = getXMLObject();
	var contenido = document.getElementById('VBase');
	if(contenido!=null){
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;		
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=7"); 
	}
}


function div3() {//Se verifica la plantilla y se montan los otros campos
	//var pd = document.getElementById("Plantilla").value;
	ajax = getXMLObject();
	var contenido = document.getElementById('divtercero');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;		
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=8"); 

}

function numdoc(cc){//
	if(cc!="Seleccione"){
	//var pd = document.getElementById("Plantilla").value;
	ajax = getXMLObject();
	//var contenido = document.getElementById('pcont');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("numdoc").value ="";
			val = ajax.responseText.split("|");
			var cons="";
	
			if(val[1].length==1){cons=("00000000"+val[1]);}
			if(val[1].length==2){cons=("0000000"+val[1]);}
			if(val[1].length==3){cons=("000000"+val[1]);}
			if(val[1].length==4){cons=("00000"+val[1]);}
			if(val[1].length==5){cons=("0000"+val[1]);}
			if(val[1].length==6){cons=("000"+val[1]);}
			if(val[1].length==7){cons=("00"+val[1]);}
			if(val[1].length==8){cons=("0"+val[1]);}
			if(val[1].length==9){cons=val[1];}
			if(val[1].length>9){alert("Consecutivo supera el registro!!!");}else{
			document.getElementById("numdoc").value =(val[0]+""+cons);
			}//contenido.innerHTML = ajax.responseText;	
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=9&cc="+cc); 
	}
}


function CrearDoc(pc,ac){//Se Inserta el nuevo Documento
	
	var txtCodusuario = document.getElementById("txtCodusuario").value;
	
	var td = document.getElementById("tipoDocC").value;
	var fd = document.getElementById("fd").value;
	var nd = document.getElementById("numdoc").value;
	var dd = document.getElementById("detdoc").value;
	var pd = document.getElementById("Plantilla").value;
	
	if(td=="Seleccione"){ alert("Debe seleccionar el tipo de Documento");}
	 else{
		 vfd();//valida la fecha
	  if(fd==""){ alert("Debe seleccionar la fecha del Documento");}
	   else{
		if(nd==""){ alert("Debe Digitar el numero del Documento");}
		 else{
		  if(dd==""){ alert("Debe Diligenciar el campo Detalle");}
		   else{
		   
			   /////////////Revisa si tiene plantilla////////////
			   var sw=0;
			   if(pd!=0){
				 var ter = document.getElementById("tercero").value;
			   	  if(ter=="0"){ alert("Debe seleccionar el Tercero");}
			   	   else{
			   		var teri = document.getElementById("tercero").selectedIndex;
			   		var tern = document.getElementById("tercero").options[teri].text;	
			   		
				    var sd = document.getElementById("sucuc").value;
				    if(sd=="0"){ alert("Debe seleccionar la sucursal");}
				    else{
				    	var sdi = document.getElementById("sucuc").selectedIndex;
				   		var sdn = document.getElementById("sucuc").options[sdi].text;	
				    	
					 var ccd = document.getElementById("ccostoc").value;
					 if(ccd=="0"){ alert("Debe seleccionar el Centro de Costo");}
					  else{
						  
						  var ccdi = document.getElementById("ccostoc").selectedIndex;
					   	  var ccdn = document.getElementById("ccostoc").options[ccdi].text;
					   		
						 var scd = document.getElementById("sccostoc").value;
						 if(scd=="0"){ alert("Debe seleccionar el Subcentro de Costo");}
						 else{
							 var scdi = document.getElementById("sccostoc").selectedIndex;
						   	  var scdn = document.getElementById("sccostoc").options[scdi].text;
							 
						    var bd = document.getElementById("base").value;
						    if(bd==""){ alert("Debe digitar el valor base de la operacion");}
						     else{
						       sw=1;
						     }
						 }
					  }
				    } 
			   	}
			   }else{sw=1;}
			   
			   
			   if(sw==1){

				   ajax = getXMLObject();
					var contenido = document.getElementById('Detalle');
					ajax.open("POST", "ControlDocumentos", true); // getname will be the
					// servlet name
					ajax.onreadystatechange = function() {
						if (ajax.readyState == 4) {
							
							contenido.innerHTML = ajax.responseText;
							var docu = document.getElementById('docuh').value;
							 if(pd==0){
								Totales(0,0,docu,"-1",ac,pc);
							 }else{
								 var ddt = document.getElementById("ddt").value;
								 var dct = document.getElementById("dct").value;
								 var lipla = document.getElementById("lipla").value;
								 Totales(ddt,dct,docu,lipla,ac,pc);
							 }
						}
					}
					ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=10&td="+td+"&pc="+pc+"&ac="+ac+"&nd="+nd+"&fd="+fd+"&dd="+dd+"&pd="+pd+"&ter="+ter+"&sd="+sd+"&ccd="+ccd+"&scd="+scd+"&bd="+bd+"&tern="+tern+"&sdn="+sdn+"&ccdn="+ccdn+"&scdn="+scdn+"&user="+txtCodusuario); 
					//ajax.send("valor=10");//debe borrarse
					document.getElementById('tdboton').innerHTML="";
					document.getElementById("fd").disabled = true;
					document.getElementById("numdoc").disabled = true;
					if(pd!=0){
					document.getElementById("base").disabled = true;
					}
					document.getElementById("detdoc").disabled = true;
					document.getElementById('enca').disabled = true;
					
				
			   }
			   
		   }
		 }
	   }
	 }
	
}

function limpiacta(idiv) {//Limpia el div de cta si no la selecciona
	// document.getElementById("dcta"+idiv).innerHTML = "";
     //document.getElementById("dcta"+idiv).style.display = "none";
}

function limpiater(idiv) {//Limpia el div de cta si no la selecciona
	// document.getElementById("dter"+idiv).innerHTML = "";
  //   document.getElementById("dter"+idiv).style.display = "none";
}

function lcco(e) {//Generar nueva linea del detalle contable
	//alert(l);
	var su = document.getElementById("suc"+e).value;
	ajax = getXMLObject();
	var contenido = document.getElementById('ncco'+e);
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
			//alert(ajax.responseText);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=12&su="+su+"&id="+e); 

}

function lsco(e) {//Generar nueva linea del detalle contable
	//alert(l);
	var su = document.getElementById("cco"+e).value;
	ajax = getXMLObject();
	var contenido = document.getElementById('nsco'+e);
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
			//alert(ajax.responseText);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=13&su="+su+"&id="+e); 

}


function Nuevalinea(x,doc,ac,pc) {//Generar nueva linea del detalle contable
	
	var Datos="";	
	var sw2=0;
	var sw=0;
	var debito=0;
	var credito=0;
	for(e=0;e<=x;e++){
		f=(e+1);
	var cta = document.getElementById("cta"+e).value;
	var ctah = document.getElementById("ctah"+e).value;
	var suc = document.getElementById("suc"+e).value;
	var suci = document.getElementById("suc"+e).selectedIndex;
	var suct = document.getElementById("suc"+e).options[suci].text;	
	var cco = document.getElementById("cco"+e).value;
	var ccoi = document.getElementById("cco"+e).selectedIndex;
	var ccot = document.getElementById("cco"+e).options[ccoi].text;	
	var scc = document.getElementById("scc"+e).value;
	var scci = document.getElementById("scc"+e).selectedIndex;
	var scct = document.getElementById("scc"+e).options[scci].text;	
	var ter = document.getElementById("ter"+e).value;
	var terh = document.getElementById("terh"+e).value;
	var des = document.getElementById("des"+e).value;
	var bas = document.getElementById("bas"+e).value;
	var ref = document.getElementById("ref"+e).value;
	var deb = document.getElementById("deb"+e).value;
	var cre = document.getElementById("cre"+e).value;
	var dif = document.getElementById("dif"+e).value;
	
	if(ref==""){ref="N/A"}
	
	if(ctah==""){alert("Debe seleccionar la Cuenta de la fila "+f);sw2=1;}
	else{
	 if(suc=="0"){alert("Debe seleccionar la Sucursal de la fila "+f);sw2=1;}
	 else{
		 if(cco=="0"){alert("Debe seleccionar el Centro de Costo de la fila "+f);sw2=1;}
		 else{
			 if(terh==""){alert("Debe seleccionar el Tercero de la fila "+f);sw2=1;}
				 else{
					 if(des==""){alert("Debe Digitar la Descripcion de la fila "+f);sw2=1;}
					 else{
						 if(bas==""){alert("Debe Digitar el valor Base de la fila "+f);sw2=1;}
						 else{
							 if(deb==""){alert("Debe Digitar el valor del Debito de la fila "+f);sw2=1;}
							 else{
								 if(cre==""){alert("Debe Digitar el valor del Credito de la fila "+f);sw2=1;}
								 else{
									
									 debito=debito+parseInt(deb);
									 credito=credito+parseInt(cre);
									 
									 Datos=Datos+cta;Datos=Datos+"|"
									 Datos=Datos+ctah;Datos=Datos+"|"
									 Datos=Datos+suc;Datos=Datos+"|"
									 Datos=Datos+suct;Datos=Datos+"|"
									 Datos=Datos+cco;Datos=Datos+"|"
									 Datos=Datos+ccot;Datos=Datos+"|"
									 Datos=Datos+scc;Datos=Datos+"|"
									 Datos=Datos+scct;Datos=Datos+"|"
									 Datos=Datos+ter;Datos=Datos+"|"
									 Datos=Datos+terh;Datos=Datos+"|"
									 Datos=Datos+des;Datos=Datos+"|"
									 Datos=Datos+bas;Datos=Datos+"|"
									 Datos=Datos+ref;Datos=Datos+"|"
									 Datos=Datos+deb;Datos=Datos+"|"
									 Datos=Datos+cre;Datos=Datos+"|"
									 Datos=Datos+dif;Datos=Datos+"|"
									 
									sw=1;
									// alert(Datos);
									// alert(Ndatos);
									 
								 	}
							 	}
						 	}
					 	}
				 	}
				}
			}
		}
		
	}
	
	if((sw==1)&&(sw2==0)){
	 var Ndatos=encodeURIComponent(Datos)
	ajax = getXMLObject();
	var contenido = document.getElementById('linea');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
			document.getElementById("cta"+(x+1)).focus();
			document.getElementById("cta"+(x+1)).focus();
			Totales(debito,credito,doc,x,ac,pc);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=11&l="+x+"&Ndatos="+Ndatos+"&docu="+doc+"&ac="+ac+"&pc="+pc); 
	}

}


function Totales(d,c,a,l,ac,pc) {//Se verifica la plantilla y se montan los otros campos
	ajax = getXMLObject();
	var contenido = document.getElementById('cabecera');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;		
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=14&vdebito="+d+"&vcredito="+c+"&docu="+a+"&ls="+l+"&ac="+ac+"&pc="+pc); 

}



function GuardarDetalle(doc,x,vd,vc,ac,pc) {//Se verifica la plantilla y se montan los otros campos
	swcheq=0;
	if(vd==vc){
		var Datos="";	
		var sw2=0;
		var sw=0;
		var debito=0;
		var credito=0;
		for(e=0;e<=x;e++){
			f=(e+1);
		var cta = document.getElementById("cta"+e).value;
		var ctah = document.getElementById("ctah"+e).value;
		var suc = document.getElementById("suc"+e).value;
		var suci = document.getElementById("suc"+e).selectedIndex;
		var suct = document.getElementById("suc"+e).options[suci].text;	
		var cco = document.getElementById("cco"+e).value;
		var ccoi = document.getElementById("cco"+e).selectedIndex;
		var ccot = document.getElementById("cco"+e).options[ccoi].text;	
		var scc = document.getElementById("scc"+e).value;
		var scci = document.getElementById("scc"+e).selectedIndex;
		var scct = document.getElementById("scc"+e).options[scci].text;	
		var ter = document.getElementById("ter"+e).value;
		var terh = document.getElementById("terh"+e).value;
		var des = document.getElementById("des"+e).value;
		var bas = document.getElementById("bas"+e).value;
		var ref = document.getElementById("ref"+e).value;
		var deb = document.getElementById("deb"+e).value;
		var cre = document.getElementById("cre"+e).value;
		var dif = document.getElementById("dif"+e).value;
		
		if(ref==""){ref="N/A"}
		
		if(ctah==""){alert("Debe seleccionar la Cuenta de la fila "+f);sw2=1;}
		else{
		 if(suc=="0"){alert("Debe seleccionar la Sucursal de la fila "+f);sw2=1;}
		 else{
			 if(cco=="0"){alert("Debe seleccionar el Centro de Costo de la fila "+f);sw2=1;}
			 else{
				 if(terh==""){alert("Debe seleccionar el Tercero de la fila "+f);sw2=1;}
					 else{
						 if(des==""){alert("Debe Digitar la Descripcion de la fila "+f);sw2=1;}
						 else{
							 if(bas==""){alert("Debe Digitar el valor Base de la fila "+f);sw2=1;}
							 else{
								 if(deb==""){alert("Debe Digitar el valor del Debito de la fila "+f);sw2=1;}
								 else{
									 if(cre==""){alert("Debe Digitar el valor del Credito de la fila "+f);sw2=1;}
									 else{
										
										 debito=debito+parseInt(deb);
										 credito=credito+parseInt(cre);
										 
										 Datos=Datos+cta;Datos=Datos+"|"
										 Datos=Datos+ctah;Datos=Datos+"|"
										 Datos=Datos+suc;Datos=Datos+"|"
										 Datos=Datos+suct;Datos=Datos+"|"
										 Datos=Datos+cco;Datos=Datos+"|"
										 Datos=Datos+ccot;Datos=Datos+"|"
										 Datos=Datos+scc;Datos=Datos+"|"
										 Datos=Datos+scct;Datos=Datos+"|"
										 Datos=Datos+ter;Datos=Datos+"|"
										 Datos=Datos+terh;Datos=Datos+"|"
										 Datos=Datos+des;Datos=Datos+"|"
										 Datos=Datos+bas;Datos=Datos+"|"
										 Datos=Datos+ref;Datos=Datos+"|"
										 Datos=Datos+deb;Datos=Datos+"|"
										 Datos=Datos+cre;Datos=Datos+"|"
										 Datos=Datos+dif;Datos=Datos+"|"
										 
										sw=1;
										// alert(Datos);
										// alert(Ndatos);
										 ctacheq=cta.substring(0,4);
										 if((ctacheq=="1110")||(ctacheq=="1120")){
											 swcheq=1;
										 }
										 
									 	}
								 	}
							 	}
						 	}
					 	}
					}
				}
			}
			
		}
		
		if((sw==1)&&(sw2==0)){
		 var Ndatos=encodeURIComponent(Datos)
		ajax = getXMLObject();
		var contenido = document.getElementById('linea');
		ajax.open("POST", "ControlDocumentos", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				
				
				var detDatos = ajax.responseText.split("|");
				var tdche=detDatos[0];
				var ndche=detDatos[1];
				var fdche=detDatos[2];
				var vdche=detDatos[3];
				
				
			//	alert("tdche: "+tdche+" swcheq:"+swcheq+" vdche:"+vdche);
				
				if((tdche=="310")&&(swcheq==1)){
					
					ConsultarCheque2(ndche,fdche,vdche);
					alert("Documeno Creado Exitosamente!!!");
					
				}else{
					alert("Documeno Creado Exitosamente!!!!");
					if(tdche=="140"){	
						ajax1 = getXMLObject();
						ajax1.open("POST", "ControlDocumentos", true); // getname will be the
						ajax1.onreadystatechange = function() {
							if (ajax1.readyState == 4) {
								var datoss=ajax1.responseText;
								//alert("datos"+datoss);
								var elem=datoss.split("_");
								var letra=elem[0];
								var consecegre=elem[1];
								 pagina1="cont_ReporteEgreso.jsp?doc="+consecegre+"&l="+letra+"&f="+fdche;	
								  var opciones1="toolbar=no, location=no, directories=no, status=no, menubar=no,";
								  opciones1 =opciones1+"scrollbars=si, resizable=yes, width=1000, height=600, top=80,  left=120";
								  window.open(pagina1,"NUEVA2",opciones1);
							}
						}
						ajax1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
						ajax1.send("valor=26y&v="+vdche+"&doc="+doc+"&tip=0");
						
						ImprimirDocumento(doc);
						
					}else{
						ImprimirDocumento(doc);
					}
					
				}
								
				Periodo();
				
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=15&l="+x+"&Ndatos="+Ndatos+"&docu="+doc+"&vdebito="+vd+"&vcredito="+vc+"&ac="+ac+"&pc="+pc); 
		}
	}else{
		alert("No puede Guardar un documento con diferencias entre el debito y el credito");
	}
}/////fin guardar detalle


function EliminarDetalle(doc){
	ajax = getXMLObject();
	var contenido = document.getElementById('linea');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			alert(ajax.responseText);
			Periodo();
			//ReporteDocumento(doc);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=16&docu="+doc); 
}



function ConsultarDoc(y,aco,pco){
	var doc = document.getElementById("numdc").value;
	var ac = document.getElementById("anodc").value;
	var pc="";
	if(y==1){
	pc = document.getElementById("perdc").value;
	}
	if(y==2){
	pc = document.getElementById("tipoDocC").value;
	if(pc=="Seleccione"){pc="";}
	}
	
	if((doc=="") && (ac=="") && (pc=="")){alert("Debe seleccionar un criterio de busqueda!!!");}
	else{
	ajax = getXMLObject();
	var contenido = document.getElementById('consul');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			var xx=ajax.responseText;
 
			xxx=xx.replace(/CJ/g,"\u00f1");
			contenido.innerHTML = xxx;

		}
	}
	//alert(ac+"-"+pc);
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	if(y=="1"){
	ajax.send("valor=17&docu="+doc+"&ac="+ac+"&pc="+pc); 
	}
	if(y=="2"){
	ajax.send("valor=18&docu="+doc+"&ac="+aco+"&pc="+pco+"&aco="+ac+"&pco="+pc); 
	}
	if(y=="3"){
	ajax.send("valor=20&docu="+doc+"&ac="+aco+"&pc="+pco); 
	}
	}
}


function ModificarDocumento(doc,ac,pc) {//Se verifica la plantilla y se montan los otros campos
		ajax = getXMLObject();
		var contenido = document.getElementById('consul');
		ajax.open("POST", "ControlDocumentos", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
			//	alert("llegamos"+doc+"-"+ac+"+"+pc);
				var xx=ajax.responseText;
				xxx=xx.replace(/CJ/g,"\u00f1"); 
				contenido.innerHTML = xxx;
				//Periodo();
				//ImprimirDocumento(doc);
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=19&docu="+doc+"&ac="+ac+"&pc="+pc); 
}


function EliminarDocumento(doc,ac,pc) {//Se verifica la plantilla y se montan los otros campos
	
	if (confirm("Desea Eliminar este Documento?")) {
	ajax = getXMLObject();
	var contenido = document.getElementById('consul');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			alert(ajax.responseText);
			Periodo();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=22&docu="+doc+"&ac="+ac+"&pc="+pc); 
 }
}


function EliminarLinea(doc,ac,pc,le, en) {//Se verifica la plantilla y se montan los otros campos
	if(en==1){
	var x = document.getElementById("totale").value;
    }
	else{ x = le-1;}
	
	//alert("Eliminamos la linea "+le);
	//alert("Total de lineas "+x);
			
		///////////////////////////////////
			
			var Datos="";	
			var sw2=0;
			var sw=0;
			var debito=0;
			var credito=0;
			for(e=0;e<=x;e++){
				f=(e+1);
		//		alert("e:"+e);
			var cta = document.getElementById("cta"+e).value;
			var ctah = document.getElementById("ctah"+e).value;
			var suc = document.getElementById("suc"+e).value;
			var suci = document.getElementById("suc"+e).selectedIndex;
			var suct = document.getElementById("suc"+e).options[suci].text;	
			var cco = document.getElementById("cco"+e).value;
			var ccoi = document.getElementById("cco"+e).selectedIndex;
			var ccot = document.getElementById("cco"+e).options[ccoi].text;	
			var scc = document.getElementById("scc"+e).value;
			var scci = document.getElementById("scc"+e).selectedIndex;
			var scct = document.getElementById("scc"+e).options[scci].text;	
			var ter = document.getElementById("ter"+e).value;
			var terh = document.getElementById("terh"+e).value;
			var des = document.getElementById("des"+e).value;
			var bas = document.getElementById("bas"+e).value;
			var ref = document.getElementById("ref"+e).value;
			var deb = document.getElementById("deb"+e).value;
			var cre = document.getElementById("cre"+e).value;
			var dif = document.getElementById("dif"+e).value;
				
			var cdh = document.getElementById("cdh"+e).value;
			
			var ideen=0;
			
	//		alert("si en=1 en: "+en+" si e=le-1  e: "+e+" y le: "+le)
			
			if(en==1){
			if(e==(le-1)){ideen="2";}else{
				ideen = document.getElementById("ideen"+e).value;
			}
			}else{
				ideen = document.getElementById("ideen"+e).value;	
			}
	//		alert("1:consulta, 2:eliminar, 4:nuevo"+ideen);
			
			if(ref==""){ref="N/A"}
			
			if(ctah==""){alert("Debe seleccionar la Cuenta de la fila "+f);sw2=1;}
			else{
			 if(suc=="0"){alert("Debe seleccionar la Sucursal de la fila "+f);sw2=1;}
			 else{
				 if(cco=="0"){alert("Debe seleccionar el Centro de Costo de la fila "+f);sw2=1;}
				 else{
					 if(terh==""){alert("Debe seleccionar el Tercero de la fila "+f);sw2=1;}
						 else{
							 if(des==""){alert("Debe Digitar la Descripcion de la fila "+f);sw2=1;}
							 else{
								 if(bas==""){alert("Debe Digitar el valor Base de la fila "+f);sw2=1;}
								 else{
									 if(deb==""){alert("Debe Digitar el valor del Debito de la fila "+f);sw2=1;}
									 else{
										 if(cre==""){alert("Debe Digitar el valor del Credito de la fila "+f);sw2=1;}
										 else{
											
											 debito=debito+parseInt(deb);
											 credito=credito+parseInt(cre);
											 
											
											 Datos=Datos+cta;Datos=Datos+"|"
											 Datos=Datos+ctah;Datos=Datos+"|"
											 Datos=Datos+suc;Datos=Datos+"|"
											 Datos=Datos+suct;Datos=Datos+"|"
											 Datos=Datos+cco;Datos=Datos+"|"
											 Datos=Datos+ccot;Datos=Datos+"|"
											 Datos=Datos+scc;Datos=Datos+"|"
											 Datos=Datos+scct;Datos=Datos+"|"
											 Datos=Datos+ter;Datos=Datos+"|"
											 Datos=Datos+terh;Datos=Datos+"|"
											 Datos=Datos+des;Datos=Datos+"|"
											 Datos=Datos+bas;Datos=Datos+"|"
											 Datos=Datos+ref;Datos=Datos+"|"
											 Datos=Datos+deb;Datos=Datos+"|"
											 Datos=Datos+cre;Datos=Datos+"|"
											 Datos=Datos+dif;Datos=Datos+"|"
											 Datos=Datos+ideen;Datos=Datos+"|"			
											 Datos=Datos+cdh;Datos=Datos+"|"			
											 
											sw=1;
											// alert(Datos);
											// alert(Ndatos);
											 
										 	}
									 	}
								 	}
							 	}
						 	}
						}
					}
				}
				
			}
			
			if((sw==1)&&(sw2==0)){
			 var Ndatos=encodeURIComponent(Datos)
			ajax = getXMLObject();
			var contenido = document.getElementById('consul');
			ajax.open("POST", "ControlDocumentos", true); // getname will be the
			ajax.onreadystatechange = function() {
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText;
					//document.getElementById("cta"+(x+1)).focus();
					//document.getElementById("cta"+(x+1)).focus();
					//Periodo();
					//ImprimirDocumento(doc);
					
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=19a&l="+x+"&Ndatos="+Ndatos+"&docu="+doc+"&ac="+ac+"&pc="+pc+"&le="+le); 
			}

		}







function ImprimirDocumento(CodDocumento){
	
	pagina="cont_ReporteDocumento.jsp?CodDocumento="+CodDocumento;	
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=600, top=85,  left=140";
   window.open(pagina,"NUEVA",opciones);
	
	
}



function ImprimirDocumento2(CodDocumento){
	
	pagina="cont_ReporteDocumento.jsp?CodDocumento="+CodDocumento;	
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=600, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);	
    
    ajax1 = getXMLObject();
	ajax1.open("POST", "ControlDocumentos", true); // getname will be the
	ajax1.onreadystatechange = function() {
		if (ajax1.readyState == 4) {
			var datoss=ajax1.responseText;
			//alert("datos"+datoss);
			var elem=datoss.split("_");
			var letra=elem[0];
			var consecegre=elem[1];
			var fdche=elem[2];
			var tipo=elem[3];
			if(tipo==140){
			 pagina1="cont_ReporteEgreso.jsp?doc="+consecegre+"&l="+letra+"&f="+fdche;	
			  var opciones1="toolbar=no, location=no, directories=no, status=no, menubar=no,";
			  opciones1 =opciones1+"scrollbars=si, resizable=yes, width=1000, height=600, top=80,  left=120";
			  window.open(pagina1,"NUEVA2",opciones1);
			}
		}
	}
	ajax1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax1.send("valor=26y&tip=1&doc="+CodDocumento);
    
    
}



function ModificarDetalle(doc,x,ac,pc) {//Se verifica la plantilla y se montan los otros campos
	
	//alert("doc: "+doc+" x: "+x+" ac: "+ac+" pc: "+pc);
	
	
	
		var Datos="";	
		var sw2=0;
		var sw=0;
		var debito=0;
		var credito=0;
		
		for(e=0;e<=x;e++){
		//	alert("e: "+e);
			f=(e+1);
			
		var cdh = document.getElementById("cdh"+e).value;
		var cta = document.getElementById("cta"+e).value; //alert("cta: "+e+" : "+cta);
		var ctah = document.getElementById("ctah"+e).value;
		var suc = document.getElementById("suc"+e).value;
		var suci = document.getElementById("suc"+e).selectedIndex;
		var suct = document.getElementById("suc"+e).options[suci].text;	
		var cco = document.getElementById("cco"+e).value;
		var ccoi = document.getElementById("cco"+e).selectedIndex;
		var ccot = document.getElementById("cco"+e).options[ccoi].text;	
		var scc = document.getElementById("scc"+e).value;
		var scci = document.getElementById("scc"+e).selectedIndex;
		var scct = document.getElementById("scc"+e).options[scci].text;	
		var ter = document.getElementById("ter"+e).value;
		var terh = document.getElementById("terh"+e).value;
		var des = document.getElementById("des"+e).value;
		var bas = document.getElementById("bas"+e).value;
		var ref = document.getElementById("ref"+e).value;
		var deb = document.getElementById("deb"+e).value;
		var cre = document.getElementById("cre"+e).value;
		var dif = document.getElementById("dif"+e).value;
		var ideen = document.getElementById("ideen"+e).value;
		
		if(ref==""){ref="N/A"}
		
		if(ctah==""){alert("Debe seleccionar la Cuenta de la fila "+f);sw2=1;}
		else{
		 if(suc=="0"){alert("Debe seleccionar la Sucursal de la fila "+f);sw2=1;}
		 else{
			 if(cco=="0"){alert("Debe seleccionar el Centro de Costo de la fila "+f);sw2=1;}
			 else{
				 if(terh==""){alert("Debe seleccionar el Tercero de la fila "+f);sw2=1;}
					 else{
						 if(des==""){alert("Debe Digitar la Descripcion de la fila "+f);sw2=1;}
						 else{
							 if(bas==""){alert("Debe Digitar el valor Base de la fila "+f);sw2=1;}
							 else{
								 if(deb==""){alert("Debe Digitar el valor del Debito de la fila "+f);sw2=1;}
								 else{
									 if(cre==""){alert("Debe Digitar el valor del Credito de la fila "+f);sw2=1;}
									 else{
										
										 debito=debito+parseInt(deb);
										 credito=credito+parseInt(cre);
								//		 alert(debito+" <-- Debito ; Credito -->"+credito);
										 
										 Datos=Datos+cta;Datos=Datos+"|"
										 Datos=Datos+ctah;Datos=Datos+"|"
										 Datos=Datos+suc;Datos=Datos+"|"
										 Datos=Datos+suct;Datos=Datos+"|"
										 Datos=Datos+cco;Datos=Datos+"|"
										 Datos=Datos+ccot;Datos=Datos+"|"
										 Datos=Datos+scc;Datos=Datos+"|"
										 Datos=Datos+scct;Datos=Datos+"|"
										 Datos=Datos+ter;Datos=Datos+"|"
										 Datos=Datos+terh;Datos=Datos+"|"
										 Datos=Datos+des;Datos=Datos+"|"
										 Datos=Datos+bas;Datos=Datos+"|"
										 Datos=Datos+ref;Datos=Datos+"|"
										 Datos=Datos+deb;Datos=Datos+"|"
										 Datos=Datos+cre;Datos=Datos+"|"
										 Datos=Datos+dif;Datos=Datos+"|"
										 Datos=Datos+ideen;Datos=Datos+"|"			
										 Datos=Datos+cdh;Datos=Datos+"|"			
										 
										 
										sw=1;
										// alert(Datos);
										// alert(Ndatos);
										 
									 	}
								 	}
							 	}
						 	}
					 	}
					}
				}
			}
			
		}

		if(debito==credito){

		if((sw==1)&&(sw2==0)){
		 var Ndatos=encodeURIComponent(Datos)
		ajax = getXMLObject();
		var contenido = document.getElementById('linea');
		ajax.open("POST", "ControlDocumentos", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert(ajax.responseText);
				//Periodo();
				RadioDoc(2,pc,ac,0,0,2);
				ImprimirDocumento(doc);
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=21&l="+x+"&Ndatos="+Ndatos+"&docu="+doc+"&vdebito="+debito+"&vcredito="+credito+"&ac="+ac+"&pc="+pc); 
		}

	}else{
		alert("No puede Guardar un documento con diferencias entre el debito y el credito");
	}
	
	

}/////fin ModificarDetalle


function TipoDocM(va) {//Se unifica el tipo de documento con el nombre
	if(va=="0"){
		var cc = document.getElementById("tipoDocC").value;
		var lc = document.getElementById("tipoDocC").length;
		var ln=lc-1;
		var indice;
		for(i=0;i<=ln;i++){
			if(cc==document.getElementById("tipoDocN").options[i].value){
			indice=i;
			}
		}
		document.getElementById("tipoDocN").text=document.getElementById("tipoDocN").options[indice].text;
		document.getElementById("tipoDocN").value=cc;
	}
	if(va=="1"){
		 cc = document.getElementById("tipoDocN").value;
		 lc = document.getElementById("tipoDocN").length;
		 ln=lc-1;
		 indice;
		document.getElementById("tipoDocC").text=cc;
		document.getElementById("tipoDocC").value=cc;
	}
}










//////////Funcion Validar Fechas/////////////

var patronp = new Array(2);
var patrona = new Array(4);

function periodocontable(d, pat, nums, op,ccme) {
	
	val = d.value;
	largo = val.length;
	
	if (nums) {
		for (z = 0; z < val.length; z++) {
			if (isNaN(val.charAt(z))) {
				letra = new RegExp(val.charAt(z), "g");
				d.value = val.replace(letra, "");
				val = d.value;
				largo = val.length;
			}
		}
	}
	
	if (d.valant != d.value) {
	if(op==0){
		if(largo>2){
			d.value=val.substring(0, 2);
		}
		
		ini = val.substring(0, 2);
		if ((ini > 13) || (ini == "00")) {
			d.value = "01";
		}
		
	/*	if(largo==2){
			document.getElementById('AC').value="";
			document.getElementById('AC').focus();
		}	*/	
	}else{
		if(largo==4){
			document.getElementById('MC').value="";
			document.getElementById('MC').focus();
			//d.value=val.substring(0, 4);
		}	
	}
	
	}
	
	var elEvento =  window.event;  // arguments[0] ||
    var tecla = elEvento.keyCode;
    if(tecla == 13) { 
    	if((document.getElementById('AC').value!="")&(document.getElementById('MC').value!="")){
    	vpc(ccme);
    	}else{alert("Debe seleccionar tanto el A\u00f1o como el Periodo");
    		if(document.getElementById('MC').value==""){document.getElementById('MC').focus();}
    		if(document.getElementById('AC').value==""){document.getElementById('AC').focus();}
    	}
    }
}


///////////Funcion Validar Fecha///////////
var fechafilter = /^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
function vfd() {

	var e = document.getElementById("fd").value;
	var returnval = fechafilter.test(e)
	if (returnval == false) {
		alert("La fecha del documento es Invalida");
		document.getElementById("fd").value = "";
		document.getElementById('fd').focus();
		}
	return returnval
}


/////funcion formato de fecha//////////
var patron = new Array(2, 2, 4);
function masca(d, pat, nums, dias, mes, annio, pc, ac) {
	var sep = "/";
	if (d.valant != d.value) {
		val = d.value
		largo = val.length
		ini = val.substring(0, 2);
		if ((ini > 31) || (ini == "00")) {
			// alert("Dia No Valido!!!");
			val = d.value = "01";
			// d.focus();
		}
		val = val.split(sep);

		val2 = ''
		for (r = 0; r < val.length; r++) {
			val2 += val[r]
		}
		ini = val2.substring(2, 4);
		if ((ini == "04") || (ini == "06") || (ini == "09") || (ini == "11")) {
			x = val2.substring(0, 2);
			if (x == "31") {
				val2 = "30";
				val2 = val2 + ini;
			}
		}
		if ((ini > 12) || (ini == "00")) {
			// alert("Mes No Valido!!!");
			mescj = val2.substring(0, 2);
			val2 = mescj + "01";
			// d.focus();
		}

		ano = val2.substring(4, 8);
		dia = val2.substring(0, 2);
		
		if(ini.length==2){
			if(ini!=pc){
				alert("Mes fuera del periodo");
				mescj = val2.substring(0, 2);
				val2 = mescj;
			}
		}
		
		if(ano.length==4){ 
		if(ano!=ac){
			alert("A\u00f1o fuera del periodo");
			mescj = val2.substring(0, 4);
			val2 = mescj;
		}
		}

		if (ini == "02") {
			if ((dia == "30") || (dia == "31") || (dia == "29")) {
				if (ano.length == 4) {
					if ((ano % 100 != 0)
							&& ((ano % 4 == 0) || (ano % 400 == 0))) {
						val2 = "29";
						val2 = val2 + ini;
					} else {
						val2 = "28";
						val2 = val2 + ini;
					}
					val2 = val2 + ano;
				}
			}
		}

		/*
		 * /////////////////////////////////////////////////// //Validar fecha
		 * mayor que la fecha actual
		 * 
		 * if(ano.length==4){ if (ano==annio) { if(ini==mes){
		 * if((dia==dias)||(dia<dias)){ alert("La fecha de Vencimiento no puede
		 * ser menor o igual a la fecha Actual"); val2=''; } }else{ if(ini<mes){
		 * alert("La fecha de Vencimiento no puede ser menor o igual a la fecha
		 * Actual"); val2=''; } } } if (ano<annio) { alert("La fecha de
		 * Vencimiento no puede ser menor o igual a la fecha Actual"); val2=''; } }
		 * 
		 * ///////////////////////////////////////////////////
		 */
		if (nums) {
			for (z = 0; z < val2.length; z++) {
				if (isNaN(val2.charAt(z))) {
					letra = new RegExp(val2.charAt(z), "g")
					val2 = val2.replace(letra, "")

				}
			}
		}

		val = ''
		val3 = new Array()
		for (s = 0; s < pat.length; s++) {
			val3[s] = val2.substring(0, pat[s])
			val2 = val2.substring(pat[s])
		}

		for (q = 0; q < val3.length; q++) {
			if (q == 0) {
				val = val3[q]
			} else {
				if (val3[q] != "") {
					val += sep + val3[q]
				}
			}
		}
		d.value = val
		d.valant = val
	}
	var elEvento =  window.event;  // arguments[0] ||
    var tecla = elEvento.keyCode;
    if(tecla == 13) { 
    		//document.getElementById("ref0").focus();
    		
    }
	
}

var emailfilterno = /^[0-9]*$/;
function vnb(c,t) {
	var cc="";
	if(t==1){cc = "bas" + c;}
	if(t==2){cc = "deb" + c;}
	if(t==3){cc = "cre" + c;}
	if(t==4){cc = "base";}
	if(t==5){cc = "valor0";}
	
	var e = document.getElementById(cc).value;
	var returnval = emailfilterno.test(e)

	if(e.length>1){
	var x = e.substr(0, 1);
	if (x == 0) {
		var y = e.substr(1);
		document.getElementById(cc).value = y;
	}
	}

	if (returnval == false) {
		var l = e.length;
		var xx = e.substr(0, l-1);
		//alert("l: "+l+"x: "+x);
		
		//alert("Este campo acepta solo valores n�mericos!!!");
		document.getElementById(cc).value = xx;
		document.getElementById(cc).focus();
	}
	return returnval
}


function exluye(c,t) {
	if(t==1){cc = "deb" + c; dd="cre" + c;}
	if(t==2){cc = "cre" + c; dd="deb" + c;}
	
	var e = document.getElementById(cc).value;
	var f = document.getElementById(dd).value;
	if(e>0){
		document.getElementById(dd).value="0";
	}
}


function format(input)
{
var num = input.value.replace(/\./g,'');
if(!isNaN(num)){
num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1.');
num = num.split('').reverse().join('').replace(/^[\.]/,'');
input.value = num;
}

else{ alert('Solo se permiten numeros');
input.value = input.value.replace(/[^\d\.]*/g,'');
}

if(input.value<1){
var l = num.length;
input.value = num.substr(0, l-1);
}
}



function Cheque() {//Consulta si el periodo se gener� y si no esta bloqueado
//	alert("vpc: "+ccme);
	ajax = getXMLObject();
	//var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			var detDatos = ajax.responseText.split("|");
			var p=detDatos[0];
			var b=detDatos[1];
			if(p=="00"){
				alert("El periodo no ha sido se ha generado comuniquese con el departamento de Contabilidad!!!");
				window.location.href="Contabilidad.jsp";
			}else{
				if(b=="1"){
					alert("El periodo esta Bloqueado... No se pueden generar egresos por cheque comuniquese con el departamento de Contabilidad!!!");
					window.location.href="Contabilidad.jsp";
				}
				if(b=="0"){Cheque2();}
			}
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=23p"); 
}

function Cheque2() {//Seleccionar el Periodo
	
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			//document.getElementById('AC').focus();
			numdoc(140);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=23"); 
}

function CCheque() {//Seleccionar el Periodo
	
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			//document.getElementById('AC').focus();
			//numdoc(140);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=23C"); 
}
//////////////////////NUMEROS A LETRAS////////////



///////////////////////fin letras////////////////
function GuardarCheque() {//Seleccionar el Periodo
	var txtCodusuario = document.getElementById("txtCodusuario").value;
	var fd = document.getElementById("fd").value;
	var vdebito = document.getElementById("valor0").value;
	var dd = document.getElementById("detalle").value;
	var suci = document.getElementById("sucuc").selectedIndex;
	var suct = document.getElementById("sucuc").options[suci].text;	
	var sucv = document.getElementById("sucuc").options[suci].value;	
	var centi = document.getElementById("ccostoc").selectedIndex;
	var centt = document.getElementById("ccostoc").options[centi].text;	
	var centv = document.getElementById("ccostoc").options[centi].value;	
	var subci = document.getElementById("sccostoc").selectedIndex;
	var subct = document.getElementById("sccostoc").options[subci].text;	
	var subcv = document.getElementById("sccostoc").options[subci].value;	
	var bani = document.getElementById("ban").selectedIndex;
	var bant = document.getElementById("ban").options[bani].text;	
	var banv = document.getElementById("ban").options[bani].value;	
	var teri = document.getElementById("tercero").selectedIndex;
	var tert = document.getElementById("tercero").options[teri].text;	
	var terv = document.getElementById("tercero").options[teri].value;	
	var cheq = document.getElementById("cheq").value;
	var conci = document.getElementById("concepto").selectedIndex;
	var conct = document.getElementById("concepto").options[conci].text;	
	var concv = document.getElementById("concepto").options[conci].value;	
	
//alert("Sucursal "+suct+" - "+sucv+" Centrocosto "+centt+" - "+centv+" SubCentrocosto "+subct+" - "+subcv+" Banco "+bant+" - "+banv+" tercero "+tert+" - "+terv);
	//alert(vdebito);
	lvd=vdebito.length;
	for(i=0;i<lvd;i++){
	vdebito=vdebito.replace(".","");
	}
	//alert(vdebito);
	//var cco = document.getElementById("cco"+e).value;
	
	if(suct=="Seleccione"){alert("Debe seleccionar la Sucursal");
	}else{
		if(centt=="Seleccione"){alert("Debe seleccionar El Centro de Costo");
		}else{
			if(subct=="Seleccione"){alert("Debe seleccionar El SubCentro de Costo");
			}else{
				if(bant=="Seleccione"){alert("Debe seleccionar El Banco");
				}else{
					if(tert=="Seleccione"){alert("Debe seleccionar El Tercero");
					}else{
						if(vdebito=="0"){alert("Debe Digitar el valor del cheque");
						}else{
							if(cheq==""){alert("Debe Digitar el numero del cheque");
							}else{
								if(conct=="Seleccione"){alert("Debe seleccionar El Concepto de Egreso");
								}else{
									if(dd==""){alert("Debe Digitar el detalle o alguna observaci�n del cheque");
									}else{
										//var l=letras(vdebito);
										//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
									//	alert(l+" - "+fd);
										
										document.getElementById('gcheque').innerHTML="";
										document.getElementById('Cargando').innerHTML='<p>Cargando Resultados...</p><img src="Imagenes/load.gif">';

										//alert("Camino al controlador");
										ajax = getXMLObject();
										var contenido = document.getElementById('Opcion');
										ajax.open("POST", "ControlDocumentos", true); // getname will be the
										// servlet name
										ajax.onreadystatechange = function() {
											if (ajax.readyState == 4) {
												
												var cons=ajax.responseText;
												alert("Documento Generado Exitosamente!!!");
												document.getElementById('Cargando').innerHTML='<table width="100%"  border="0" ><tr><td colspan="3"><div align="center" id="gcheque"><input type="button"  value="  Nuevo  " onClick="Cheque2()" ></div></td></tr></table>';
												ConsultarCheque2(cons,fd,vdebito);
												//ImprimirCheque(cons,l,fd);
																			//	contenido.innerHTML = ajax.responseText
																			//document.getElementById('AC').focus();
																			//	numdoc(140);
											}
										}
										ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
										ajax.send("valor=24&dd="+dd+"&vdebito="+vdebito+"&user="+txtCodusuario+"&sd="+sucv+"&ccd="+centv+"&scd="+subcv+"&bd="+banv+"&ter="+terv+"&cheque="+cheq+"&conc="+concv);
										
									}//detalle
								}//concepto
							}//No cheque
						}//valor
					}//tercero
				}//Banco
			}//subcentro
		}//centro
	}//sucursal
}//fin guardar cheque


function ConsultarCheque(){
	var docu = document.getElementById("numdoc").value;
	var teri = document.getElementById("tercero").selectedIndex;
	var tert = document.getElementById("tercero").options[teri].text;	
	var terv = document.getElementById("tercero").options[teri].value;	
	var cheq = document.getElementById("cheq").value;
	//alert("Consultar: "+numdoc+" - "+terv+" - "+cheq);
	
	ajax = getXMLObject();
	var contenido = document.getElementById('Cargando');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			//document.getElementById('AC').focus();
			//numdoc(140);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=25&docu="+docu+"&ter="+terv+"&cheque="+cheq); 
	
}

function ConsultarCheque2(doc,f,v){
//alert("ConsultarCheque2: "+doc+" - "+f+" - "+v);
	ajax = getXMLObject();
	//var contenido = document.getElementById('Cargando');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			var l = ajax.responseText;
			//alert("Letras: "+l);
			ImprimirCheque(doc,l,f);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=26&v="+v); 
	
}


function ImprimirCheque(doc,l,f){
	
	pagina="cont_ReporteCheque.jsp?doc="+doc+"&l="+l+"&f="+f;	
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=600, top=85,  left=140";
   window.open(pagina,"NUEVA",opciones);
	
	pagina1="cont_ReporteEgreso.jsp?doc="+doc+"&l="+l+"&f="+f;	
   var opciones1="toolbar=no, location=no, directories=no, status=no, menubar=no,";
   opciones1 =opciones1+"scrollbars=si, resizable=yes, width=1000, height=600, top=80,  left=120";
   window.open(pagina1,"NUEVA2",opciones1);

}


function PeriodoSaldos(ccme) {//Seleccionar el Periodo
	//alert("Periodo: "+ccme)
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			document.getElementById('AC').focus();
				
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=30&ccme="+ccme); 
}



function periodocontablen(d, pat, nums, op) {
	
	val = d.value;
	largo = val.length;
	
	if (nums) {
		for (z = 0; z < val.length; z++) {
			if (isNaN(val.charAt(z))) {
				letra = new RegExp(val.charAt(z), "g");
				d.value = val.replace(letra, "");
				val = d.value;
				largo = val.length;
			}
		}
	}
	
	if (d.valant != d.value) {
	if(op==0){
		if(largo>2){
			d.value=val.substring(0, 2);
		}
		
		ini = val.substring(0, 2);
		if ((ini > 13) || (ini == "00")) {
			d.value = "01";
		}
		
	/*	if(largo==2){
			document.getElementById('AC').value="";
			document.getElementById('AC').focus();
		}	*/	
	}else{
		if(largo==4){
			document.getElementById('MC').value="";
			document.getElementById('MC').focus();
			//d.value=val.substring(0, 4);
		}	
	}
	
	}
	
	var elEvento =  window.event;  // arguments[0] ||
    var tecla = elEvento.keyCode;
    if(tecla == 13) { 
    	if((document.getElementById('AC').value!="")&(document.getElementById('MC').value!="")){
    	PS1(document.getElementById('AC').value,document.getElementById('MC').value);
    	}else{alert("Debe seleccionar tanto el A\u00f1o como el Periodo");
    		if(document.getElementById('MC').value==""){document.getElementById('MC').focus();}
    		if(document.getElementById('AC').value==""){document.getElementById('AC').focus();}
    	}
    }
}

function PS1(ac,pc) {//Seleccionar el Periodo
	//alert("Periodo: "+ccme)
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
		//	document.getElementById('AC').focus();
				
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=31&ac="+ac+"&pc="+pc); 
}

function PS2(ac,pc) {//Seleccionar el Periodo
	//alert("ac: "+ac+" pc: "+pc);
	if (confirm("Confirma Actualizacion automatica de saldos?")) {
	document.getElementById('Opcion').innerHTML='<div align="center"><p>Acualizando Saldos...</p><img src="Imagenes/load.gif"></div>';

	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
		//	document.getElementById('AC').focus();
				
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=32&ac="+ac+"&pc="+pc); 
	}
}


function Causa() {//Consulta si el periodo se gener� y si no esta bloqueado
	
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
	
}

function referencia(x){
	//alert(x.value);
	document.getElementById('doc0').value=x.value;
}

var item=1;
var switem=0;
function newfile(p){
	if((p!=1)&&(switem==0)){
		item=p;
		switem=1;
	}
//alert(item);
	var ttd=0;
	var ttc=0;
	var ttdc=0;
	for(var i=0;i<item;i++){
		ttd=ttd+parseInt(document.getElementById('deb'+i).value);
		ttc=ttc+parseInt(document.getElementById('cre'+i).value);
	}
	ttdc=ttd-ttc;
	
	document.getElementById('ttd').value=ttd;
	document.getElementById('ttc').value=ttc;
	document.getElementById('ttdi').value=ttdc;
	
	if((ttd!=0)&&(ttdc==0)){document.getElementById("botoncausa").disabled=false;}
	else{document.getElementById("botoncausa").disabled=true;}
	
	var table = document.getElementById("asiento");
	var rowCount = table.rows.length;
	
	//alert("rowCount: "+rowCount);
	
    var row = table.insertRow(rowCount-2);
    
    row.setAttribute("id", "tr"+item);
    row.setAttribute("name", "tri");

    var cell0 = row.insertCell(0);
    var element0 = document.createElement("label");
    element0.setAttribute("id", "it"+item);
    element0.value=item;
   // element0.innerHTML =item;
    document.getElementById('lit').innerHTML=item+1;
   // if (element0.attachEvent) {element0.attachEvent ("onkeyup", function () {Eliminalinea(0)});}
   // if (element0.addEventListener) {element0.addEventListener("onkeyup", Eliminalinea(1), false);}

    element0.onclick = function() {Eliminalinea(this,item)};
    element0.innerHTML = item;
    cell0.appendChild(element0);
    

    var cell1 = row.insertCell(1);//Campo de cuenta
    var element1 = document.createElement("input");
    element1.type = "text";
    element1.setAttribute("id", "cta"+item);
    element1.setAttribute("name", "cta");
    element1.style.width='100%';
    element1.value=document.getElementById('cta0').value;
    element1.title=document.getElementById('cta0').title;
    document.getElementById('cta0').value="";
    document.getElementById('cta0').title="";
    //var x="dcta"+item;
   //  element1.onkeyup = function() {Divdectaytercero(this,51,2,1,0,"")};// Onkeyup='Completa(this,dcta,event,&quot;ControlDocumentos&quot;,&quot;51&quot;,2,1)'    
    element1.onkeyup = function(event) {
    	Divdectaytercero(this,51,2,1,0,event);
    }
 
    cell1.appendChild(element1);
         
    // var cell1 = row.insertCell(1);//Campo de cuenta hidden
    var element01 = document.createElement("input");
    element01.type = "text";
    element01.setAttribute("id", "hcta"+item);
    element01.setAttribute("name", "hcta");
   // element01.setAttribute("hidden", true);   
    element01.style.display= "none";
    element01.style.width='50%';
    element01.value=document.getElementById('hcta0').value;
    document.getElementById('hcta0').value="";
    cell1.appendChild(element01);
    
    
    var cell2 = row.insertCell(2);//Campo tercero
    var element2 = document.createElement("input");
    element2.type = "text";
    element2.setAttribute("id", "ter"+item);
    element2.setAttribute("name", "ter");
    element2.style.width='100%';
    element2.value=document.getElementById('ter0').value;
    element2.title=document.getElementById('ter0').title;
    document.getElementById('ter0').value="";
    document.getElementById('ter0').title="";
     //element2.onkeyup = function() {Divdectaytercero(this,51,2,2,1)};// Onkeyup='Completa(this,dcta,event,&quot;ControlDocumentos&quot;,&quot;51&quot;,2,1)'    
    element2.onkeyup = function(event) {
    	Divdectaytercero(this,52,2,2,1,event);
    }
    
    cell2.appendChild(element2);

   // var cell2 = row.insertCell(2);//Campo tercero hidden
    var element02 = document.createElement("input");
    element02.type = "text";
    element02.setAttribute("id", "hter"+item);
    element02.setAttribute("name", "hter");
    element02.style.display= "none";
  //  element02.setAttribute("hidden", "hidden"); 
   // element02.setAttribute("style","display:none;");
   // element02.style.type = 'hidden';
    element02.style.width='100%';
    element02.value=document.getElementById('hter0').value;
    document.getElementById('hter0').value="";
    cell2.appendChild(element02);
    
    
    var cell3 = row.insertCell(3);
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.setAttribute("id", "doc"+item);
    element3.setAttribute("name", "doc");
    element3.style.width='100%';
    element3.title = "doc"+item;
    element3.value=document.getElementById('doc0').value;
    //document.getElementById('doc0').value="";
    cell3.appendChild(element3);

    var vsd=0;
    var vsc=0;
    if(ttdc<0){vsd=(ttdc*-1); vsc=0;}else{vsd=0; vsc=ttdc;}
    
    var cell4 = row.insertCell(4);
    var element4 = document.createElement("input");
    element4.type = "text";
    element4.setAttribute("id", "deb"+item);
    element4.setAttribute("name", "deb");
    element4.style.width='100%';
    element4.value=document.getElementById('deb0').value;
    element4.title=element4.id;
    document.getElementById('deb0').value=vsd;
    element4.onkeyup = function() {Recalcula()};
    cell4.appendChild(element4);
    
    var cell5 = row.insertCell(5);
    var element5 = document.createElement("input");
    element5.type = "text";
    element5.setAttribute("id", "cre"+item);
    element5.setAttribute("name", "cre");
    element5.style.width='97%';
    element5.value=document.getElementById('cre0').value;
    element5.onkeyup = function() {Recalcula()};
    document.getElementById('cre0').value=vsc;
    // if (element5.attachEvent) { element5.attachEvent ("onblur", function () {newfile()}); }
    // if (element5.addEventListener) { element5.addEventListener ("blur", function () {newfile()},false);}
    cell5.appendChild(element5);
    
    
    ///////////creamos la fila de los div donde se autocompletaran ctas y terceros////////////
    //out.print("<td></td><td><div id='dcta'></div></td><td><div id='dter'></div></td><td></td><td></td><td></td>");
    var row2 = table.insertRow(rowCount-1);

    row2.setAttribute("id", "tr"+item);
    row2.setAttribute("name", "tri");
    
    var cell0v2 = row2.insertCell(0);
    
    var cell1v2 = row2.insertCell(1);
    var element0v2 = document.createElement("div");
    element0v2.setAttribute("id", "dcta"+item);
    element0v2.className="dlista";
    //document.getElementsByTagName('ul').className="dlista2";
    //document.getElementsByTagName('li').className="dlista3";
    
    //alert(element0v2.className);

    cell1v2.appendChild(element0v2);
   
    var cell2v2 = row2.insertCell(2);
    var element1v2 = document.createElement("div");
    element1v2.setAttribute("id", "dter"+item);
    element1v2.className="dlista";
    
    cell2v2.appendChild(element1v2);
    
  //  alert("cosa: "+cosa);
   // var nodo = document.getElementById("div");
    // Igualamos su atributo className a un string que contiene el nombre
    // de las clases CSS a aplicar separadas por espacios.
   // element0v2.className = "clase1 clase2";
 
 
    /*var newContent = document.createTextNode("Aqui va el div de ctasss!"); 
    
    var estilos={
    	'border':'1px solid black',
    	'margin-left':'0.8px',
    	'display':'none', 
    	'height':'336px', 
    	'overflow':'scroll' 	
    };
    	
    
    aplicarestilos(element0v2,estilos);
    
    
    
    element0v2.appendChild(newContent);
 */
    
    
    
    
    
    //var sheet = document.createElement('style')
   // sheet.innerHTML = "div {border:1px solid black; margin-left:0.8px; display:none; height:336px; overflow : scroll ;}";
   // element0v2.appendChild(sheet);
    
    /*#dcta0  li:hover{Cursor : pointer; background-color: #cccccc; }
    #dcta0 { border:1px solid black; margin-left:0.8px; display:none; height:336px; overflow : scroll ;}
    #dcta0 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
    #dcta0 ul li {padding: .2em; border-bottom: 1px solid silver;}
    */
    
    
    item++;
    document.getElementById('cta0').focus();

}

function Divdectaytercero(t,x,y,z,w,elEvento){

	var d=t.id;
	var dn=d.substring(3);
	
	//alert(t+" - "+x+" - "+y+" - "+z+" - "+w+" - "+elEvento);
	 var event = elEvento || window.event;
	 var code = (event.keyCode) ? event.keyCode : event.which;
	 var tecla=event.keyCode;
	
	
	//alert(d+" - "+dn+" - "+document.getElementById('dcta'+dn).value);
	
	if(w==0){
		di=document.getElementById('dcta'+dn);
		
		//d=document.getElementById('dcta'+dn);
		Completa(t,di,event,'ControlDocumentos',x,y,z);
		//alert("pruebaaaa "+d+" - "+d.id+" - "+d.value);
	}
	if(w==1){
		di=document.getElementById('dter'+dn);
		
		//d=document.getElementById('dter'+dn)
		Completa(t,di,event,'ControlDocumentos',x,y,z);
	}
}


function Eliminalinea(x, y){
	x=x.value;
	//alert(x+" - "+y);
	
	var max = document.getElementsByTagName('label').length;
	//alert("y: "+y+" tag: "+max);
	
	var tr = document.getElementById("tr"+x);
	tr.parentNode.removeChild(tr);
	/*
	var a = document.getElementById("it"+x);
	var b = document.getElementById("cta"+x);
	var c = document.getElementById("ter"+x);
	var d = document.getElementById("doc"+x);
	var e = document.getElementById("deb"+x);
	var f = document.getElementById("cre"+x);
	
	a.parentNode.removeChild(a);
	b.parentNode.removeChild(b);
	c.parentNode.removeChild(c);
	d.parentNode.removeChild(d);
	e.parentNode.removeChild(e);
	f.parentNode.removeChild(f);
	*/
	
	var ci=1;
	for(var i=1; i<y;i++){
		//alert("i: "+i+" max: "+max+" ci: "+ci);
		if(document.getElementById('it'+i)){document.getElementById('it'+i).innerHTML=ci;ci++;
		//alert("entra if: "+ci+" document.getElementById('it'+i): "+document.getElementById('it'+i).value);
		}
		document.getElementById('lit').innerHTML=max;
		
	}
	
	
}



function Recalcula(){
	
	var max = document.getElementsByTagName('label').length;
	 
	var tdeb=0;
	var tcre=0;
	for(var i=0;i<=max;i++){
		// tdeb=parseInt(tdeb)+parseInt(document.getElementsByName('deb')[i].value);
		tdeb=parseInt(tdeb)+parseInt(document.getElementById('deb'+i).value);
		 //alert(tdeb);
		// tcre=parseInt(tcre)+parseInt(document.getElementsByName('cre')[i].value);
		 tcre=parseInt(tcre)+parseInt(document.getElementById('cre'+i).value);
	}
	
	document.getElementById('ttd').value=tdeb;
	document.getElementById('ttc').value=tcre;
		
	document.getElementById('ttdi').value=parseInt(tdeb)-parseInt(tcre);
	if((parseInt(tdeb)-parseInt(tcre))!=0){
		document.getElementById("botoncausa").disabled=true;
	}else{
		document.getElementById("botoncausa").disabled=false;
	}
	
}



function ConfirmarDatos(e,d,cta,nopc,evt){//Seleccionar datos del autocompletar
	
	var z=e.split("|");		     	
	var h="h"+cta;
	//alert("ConfirmarDatos "+e+" - "+d+" - "+cta+" - "+nopc+" - "+evt+" - "+h);
	if(nopc=="1"){//Buscar cuentas
		 codigo=z[0];
		 numero=z[1];
		 nombre=z[2];
		 document.getElementById(cta).value = numero;
		 document.getElementById(cta).title=nombre;
		 document.getElementById(h).value=codigo;
		 document.getElementById(d).innerHTML = "";
	     document.getElementById(d).style.display = "none";
	     //cta.focus;
	}//fin nopc 1

	
	if(nopc=="2"){//Buscar terceros
		 codigo=z[0];
		 numero=z[1];
		 nombre=z[2];
		 document.getElementById(cta).value = numero;
		 document.getElementById(cta).title=nombre;
		 document.getElementById(h).value=codigo;
		 document.getElementById(d).innerHTML = "";
	     document.getElementById(d).style.display = "none";
	}	
	
	//tambien hay que crear los dos campos text al lado de cta y ter que sean hidden para guardar los cod de cuentas
	//xq en los que estan vamos a mostrar son los numeros y nombres de terceros
}




function Causar() {
	var sw=0;
	var max = document.getElementsByTagName('label').length;
	//alert(max);
	//var deb=document.getElementsByName('deb').length;
	var datos="";	
	
	for(var i=1;i<=max;i++){
	
		//var r1=document.getElementsByName('cta')[i].value;
		//var r2=document.getElementsByName('ter')[i].value;
		//var r3=document.getElementsByName('doc')[i].value;
		//var r4=document.getElementsByName('deb')[i].value;
		//var r5=document.getElementsByName('cre')[i].value;
		
		var r1=document.getElementById('hcta'+i).value;
		var r2=document.getElementById('hter'+i).value;
		var r3=document.getElementById('doc'+i).value;
		var r4=document.getElementById('deb'+i).value;
		var r5=document.getElementById('cre'+i).value;
		
		if(r1==""){
			alert("La cuenta de la fila "+(i)+" no puede ir vacia"); sw=1;
		}else{
			if(r2==""){
				alert("El tercero de la fila "+(i)+" no puede ir vacia"); sw=1;
			}else{
				if(parseInt(document.getElementById('deb'+i).value)+parseInt(document.getElementById('cre'+i).value)==0){
					alert("La fila "+(i)+" no posee valores ni debitos ni creditos"); sw=1;
				}else{
					 r1=r1.replace(",",".");
					 r2=r2.replace(",",".");
					 r3=r3.replace(",",".");
					 r4=r4.replace(",",".");
					 r5=r5.replace(",",".");
					 
					 var f=r1+","+r2+","+r3+","+r4+","+r5;
					 datos=datos+f+"|";
				}
			}
		}

	}
	
	
	/*armo la variable elementos como tokenizer y la inserto ala db como matriz*/
	
	//cont_documentos (anio,periodo,tipo_documento_fk,numero_documento,fecha,detalle,valor_debito,valor_credito,cod_plantilla_fk,valor_base,cod_usaurio,fecha_creacion,hora_creacion)
	//cont_detalle_documentos(cod_documento_fk,cod_cuenta_fk,cod_sucursal_costo_fk,cod_centro_subcentro_fk,cod_terceros_fk,descripcion,valor_base,documento_referencia,valor_debito,valor_credito,cod_diferido_fk,origen)

	/*
	//cont_documentos
	anio
	periodo
	tipo_documento_fk
	numero_documento
	fecha
	detalle
	valor_debito
	valor_credito
	cod_plantilla_fk
	valor_base
	cod_usaurio
	fecha_creacion
	hora_creacion
	
	
	//cont_detalle_documentos
	cod_documento_fk
	cod_cuenta_fk
	cod_sucursal_costo_fk
	cod_centro_subcentro_fk
	cod_terceros_fk
	descripcion
	//valor_base
	//documento_referencia
	//valor_debito
	//valor_credito
	//cod_diferido_fk
	//origen
	
	*/
	
	
	
	
	//falta hacer el autocompletar de cuentas  terceros 
	if(sw==0){
		
		//alert("datos: "+datos);
		var NumOrden=document.getElementById('Norden').value;
		var vdebito=document.getElementById('ttc').value;
		var vcredito=document.getElementById('ttd').value;
		var dd=document.getElementById('desccausa').value;
		
		var sc=document.getElementById('ccostoc').value;
		var cs=document.getElementById('sccostoc').value;
		
		
		
		var user = document.getElementById("txtCodusuario").value;
		
		
		ajax = getXMLObject();
		var contenido = document.getElementById('Opcion');
		ajax.open("POST", "ControlDocumentos", true); // getname will be the
		// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				//contenido.innerHTML = ajax.responseText
				//document.getElementById('AC').focus();
				ImprimirDocumento(ajax.responseText);
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=53&Ndatos="+datos+"&vdebito="+vdebito+"&vcredito="+vcredito+"&dd="+dd+"&user="+user+"&sc="+sc+"&cs="+cs+"&NumOrden="+NumOrden); 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Seleccionar el Periodo
	
	/*alert("cta1"+document.getElementById('cta1').value);
	alert("cta2"+document.getElementById('cta2').value);
	alert("cta3"+document.getElementById('cta3').value);
	alert("cta4"+document.getElementById('cta4').value);
	*/
	
	/*capa=document.getElementById('cre4');
	alert(capa.value);
	//capa.removeEventListener("blur",newfile(),true)
	if (capa.removeEventListener) {    // all browsers except IE before version 9
		capa.removeEventListener ("blur", newfile(), false);
            }else{
	if (capa.detachEvent) {        // IE before version 9
		capa.detachEvent ('onblur', newfile());
    }}
	  
           
       */
             
	/*
	capa=document.getElementById('cre1');
	capa.detachEvent ('onblur', newfile());*/
	//onBlur='newfile();'
	
	
	
/*

	var addEvent, removeEvent;
	
	if ( window.addEventListener ) {
		addEvent = function(obj, type, fn ) {
			obj.addEventListener(type, fn, false );
		}
		removeEvent = function(obj, type, fn ) {
			obj.removeEventListener(type, fn, false );
		}
	} else if (document.attachEvent) {
		addEvent = function(obj, type, fn ) {
			var eProp = type + fn;
			obj['e'+eProp] = fn;
			obj[eProp] = function(){obj['e'+eProp]( window.event );}
			obj.attachEvent( 'on'+type, obj[eProp] );
		}
		removeEvent = function(obj, type, fn ) {
			var eProp = type + fn;
			obj.detachEvent( 'on'+type, obj[eProp] );
			obj[eProp] = null;
			obj["e"+eProp] = null;
		}
	}
		
	*/
	
	

	
}

function orden(){
	var tercero=document.getElementById('tercero').value;
	ajax = getXMLObject();
	var contenido = document.getElementById('NoOrden');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=54&ter="+tercero); 
}

function detorden(){
	//alert("orden");
	var ter="";
	var NumOrden=document.getElementById('Norden').value;
	var ter=document.getElementById('tercero').value;
	
	var combo = document.getElementById("tercero");
	var selected = combo.options[combo.selectedIndex].text;
	
	var factu=document.getElementById('factu').value;
	
	if(ter == ""){
		var ter=document.getElementById('terceron').value;
	}else{
		var ter=document.getElementById('tercero').value;
	}
	
	
	ajax = getXMLObject();
	var contenido = document.getElementById('asientosc');
	ajax.open("POST", "ControlDocumentos", true); // getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=55&NumOrden="+NumOrden+"&ter="+ter+"&factu="+factu+"&tern="+selected); 
}
