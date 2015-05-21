String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}
String.prototype.ltrim = function() {
	return this.replace(/^\s+/,"");
}
String.prototype.rtrim = function() {
	return this.replace(/\s+$/,"");
}


function OnlyChr(e,ChrValidos){
	//d: decimales
	//af: alfanumericos
	
	var keynum;
	var keychar;
	var numcheck;

	keynum = e.keyCode;
	keychar = String.fromCharCode(keynum);
	
	if(ChrValidos=="af"){
		numcheck = /[\w\-]/;
	}
	if(ChrValidos=="d"){
		numcheck = /[\w\.]/;
	}
	
	return numcheck.test(keychar)
}

function getXMLObject(){
	
	var xmlhttp;
	if (typeof (XMLHttpRequest) != 'undefined') {
		try {
			xmlhttp = new XMLHttpRequest();
		} catch (e) {
		}
	} else {
		try {
			xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
		} catch (e) {
			xmlhttp = new ActiveXObject('Msxml2.XMLHTTP');
		}
	}
	return xmlhttp;
}


var fondo = false; //para ventana composicion medicamento
var mensaje = false;

var mensaje1 = false; //para ventana concentracion medicamento

function activarATC(object,codMed){
	var seleccion = object.value;
	if(seleccion=='1'){
		document.getElementById('tipoMedicamento').innerHTML="<p class='desc' >Tipo medicamento:</p><select id='tipoMed' style='float:left;margin-left:3px;' onchange='activarATC(this)'><option>Seleccione...</option><option value='0'>No Pos</option><option value='1'>Pos</option></select>" +
        "<p style='float:left;margin-left:25px;margin-right:20px;'>ATC</p><input type='text' id='ATC' onkeypress='return OnlyChr(event,&quot;af&quot;)'/>";
		document.getElementById("tipoMed").options[2].selected=true;	
	}else{
		document.getElementById('tipoMedicamento').innerHTML="<div class='lDet' id='tipoMedicamento'><p class='desc'>Tipo medicamento:</p><select id='tipoMed'  onchange='activarATC(this)'><option>Seleccione...</option><option value='0'>No Pos</option><option value='1'>Pos</option></select></div>";
		if(seleccion=='0'){
			document.getElementById("tipoMed").options[1].selected=true;
		}else{
			if(seleccion==""){
				document.getElementById("tipoMed").options[0].selected=true;
			}
		}
		
	}
}


function limpiarATC_CUM(ATC_CUM,identificador){
	var codLimpia = '';
	if(identificador=='ATC'){
		codLimpia = ATC_CUM.replace(/\s/g,'');
	}
	if(identificador=='CUM'){
		codLimpia = ATC_CUM.replace(/[^\w\-]/g,'');
	}
	return codLimpia;
}


function ventComposicion(codMed,tipoMed){
	
	fondo = document.createElement('div');
	mensaje = document.createElement('div');
	mensaje.setAttribute('id','venFarma');
	fondo.setAttribute('id','fondo');
	
	document.getElementsByTagName('body')[0].appendChild(fondo);
	document.getElementsByTagName('body')[0].appendChild(mensaje);
	window.scroll (0,0) ; 
	var contenidoMensaje = "";
	document.body.style.overflow="hidden";
	contenidoMensaje += "<div id='contenidoFarma' > " +
			            "<div class='titulo'><p>COMPOSICION</p></div> " +
			            "<div class='lDet' id='composicion'><p class='desc'>Compuesto:</p> ";
	if(tipoMed!=""){
		contenidoMensaje += "<input id='compuesto' type='text' style='width:180px;'/><input id='codComp' type='hidden'/> ";
	}else{
		contenidoMensaje += "<select id='prinActivo' style='width:350px;'></select>  ";
	}
	contenidoMensaje += "</div><div class='lDet'><p class='desc'>Forma Farmaceutica:</p><select id='selff'><option>Seleccione...</option></select></div>" +
			            "<div class='lDet' id='tipoMedicamento'><p class='desc'>Tipo medicamento:</p><select id='tipoMed'  onchange='activarATC(this,"+codMed+")'><option>Seleccione...</option><option value='0'>No Pos</option><option value='1'>Pos</option></select></div>" +
			            "<div class='lDet'><p class='desc'>Medicamento de control:</p><select id='medControl'><option>Seleccione...</option><option value='1'>Si</option><option value='0'>No</option></select></div>" +
			            "<div class='lDet'><p class='desc'>Concentracion:</p><input type='text' class='medidas' id='txtConcentracion' onkeypress='return OnlyChr(event,&quot;d&quot;)'/> " +
	            		"<p class='desc2'>Ud de concentracion</p><select id='ud'><option>Seleccione...</option></select></div> " +
	            		"<div class='lDet'><p class='desc'>Cod. CUM:</p><input id='cum' type='text' onkeypress='return OnlyChr(event,&quot;af&quot;)'/></div>" +
	            		"<div class='btn'><input id ='btn1' type='button' value='Agregar' onclick='crearConcentracion("+codMed+",&quot;"+tipoMed+"&quot;)'/></div> " +
	            		"<div class='encDetComp'><p class='comp'>Forma farmaceutica</p><p class='conc'>Concentracion</p><p class='del'></p></div> " +
	            		"<div  id='allComp' class='allComp'> " +
	            		"</div> " +
	            		"<div class='btn'><input id ='btn1' type='button' value='Finalizar' onclick='finalizarCreacion(&quot;"+codMed+"&quot;)'/></div> " +
	            		"</div>";
	mensaje.innerHTML = contenidoMensaje;
	if(tipoMed!=""){
		if(tipoMed.length>0){
			var dettipoMed = tipoMed.split("|");
			document.getElementById('compuesto').disabled=true;
			document.getElementById('compuesto').value=dettipoMed[1];
			document.getElementById('codComp').value=dettipoMed[0];
		}
	}else{
		//cargar principios activos
	}
	cargarConcentracion(document.getElementById('allComp'), codMed,1,document.getElementById('prinActivo'));
}
var compMedi = "";
var compMedCom = "";
function ventComposicionComp(codMed,nombreMedicamento,compuesto){
	compMedi = compuesto;
	fondo = document.createElement('div');
	mensaje = document.createElement('div');
	mensaje.setAttribute('id','venFarma');
	fondo.setAttribute('id','fondo');
	
	document.getElementsByTagName('body')[0].appendChild(fondo);
	document.getElementsByTagName('body')[0].appendChild(mensaje);
	window.scroll (0,0) ; 
	document.body.style.overflow="hidden";
	var contenido = "";
	contenido += "<div id='contenidoFarma' > " +
			            "<div class='titulo'><p>COMPOSICION ("+nombreMedicamento+")</p></div> " +
			            "<div class='lDet'><p class='desc'>Compuesto:</p><select id='prinActivo' style='width:350px;'></select></div> " +
			            "<div class='lDet'><p class='desc'>Forma Farmaceutica:</p><select id='selff'><option>Seleccione...</option></select></div>" +
			            "<div class='lDet' id='tipoMedicamento'><p class='desc'>Tipo medicamento:</p><select id='tipoMed'  onchange='activarATC(this,"+codMed+")'><option>Seleccione...</option><option value='0'>No Pos</option><option value='1'>Pos</option></select></div>" +
			            "<div class='lDet'><p class='desc'>Medicamento de control:</p><select id='medControl'><option>Seleccione...</option><option value='1'>Si</option><option value='0'>No</option></select></div>" +
			            "<div class='lDet'><p class='desc'>Concentracion:</p><input type='text' class='medidas' id='txtConcentracion' onkeypress='return OnlyChr(event,&quot;d&quot;)'/> " +
	            		"<p class='desc2'>Ud de concentracion</p><select id='ud'><option>Seleccione...</option></select></div> " +
	            		"<div class='lDet' id='composicion'><p class='desc'>Cod. CUM:</p><input id='cum' type='text' onkeypress='return OnlyChr(event,&quot;af&quot;)'/></div>" +
	            		"<div class='btn' id='botones'><input id ='btn2' type='button' value='Agregar' onclick='crearConcentracionComp(&quot;"+codMed+"&quot;,&quot;"+compMedi+"&quot;)'/> ";
	if(compMedi==""){
		contenido += "<input id ='btn3' type='button' value='Nueva Forma' onclick='nuevaFF(codMed)'/>";
	}
	            				
	contenido += "</div><div class='encDetComp'><p class='comp'>Forma farmaceutica</p><p class='conc'>Concentracion</p><p class='del'></p></div> " +
	            		"<div  id='allComp' class='allComp'> " +
	            		"</div> " +
	            		"<div class='btn'><input id ='btn1' type='button' value='Aceptar' onclick='finalizarCreacion(&quot;"+codMed+"&quot;)'/></div> " +
	            		"</div>";
	mensaje.innerHTML=contenido;
	cargarConcentracionComp(document.getElementById('allComp'),codMed,1,compMedi,document.getElementById('prinActivo'));
}

function ventPresentacion(codMed,codConc,codFF,esCompuesto){
	
	mensaje1 = document.createElement('div');
	mensaje1.setAttribute('id','venFarma1');
	
	document.getElementsByTagName('body')[0].appendChild(mensaje1);

	mensaje1.innerHTML = "<div id='contenidoFarma'> " +
			            "<div class='titulo'><p>PRESENTACION</p></div> " +
			            "<div class='lDet'><p class='desc'>Presentacion:</p><select id='pres'><option>Seleccione...</option></select></div> " +
			            "<div class='lDet'><p class='desc'>Cantidad:</p><input type='text' class='medidas' id='txtcantConc' onkeypress='return OnlyChr(event,&quot;d&quot;)'/> " +
	            		"<p class='desc2'>Ud de medida</p><select id='udpres'><option>Seleccione...</option></select></div> " +
	            		"<div class='lDet' style='display:none;' id='cumPresentacion'><p class='desc'>Cod. CUM:</p><input id='cumP' type='text' onkeypress='return OnlyChr(event,&quot;af&quot;)'/></div>" +
	            		"<div class='btn'><input id ='btn1' type='button' value='Agregar' onclick='crearPresentacion(&quot;"+codMed+"&quot;,&quot;"+codConc+"&quot;,&quot;"+codFF+"&quot;,&quot;"+esCompuesto+"&quot;)'/></div> " +
	            		"<div class='encDetComp'><p class='comp'>Presentacion</p><p class='conc'>Medida</p><p class='del'></p></div> " +
	            		"<div  id='allComp2' class='allComp2'> " +
	            		"</div> " +
	            		"<div class='btn'><input id ='btn1' type='button' value='Aceptar' onclick='cerrar()'/></div> " +
	            		"</div>";
	cargarPresentacionMed(document.getElementById('allComp2'),codMed,codConc);
	
}

function cerrar(){
	document.getElementsByTagName('body')[0].removeChild(mensaje1);
	mensaje1=false;
}

function cargarFormaFarma(select,compuesto,objetoPrinActivo){
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearMedicamentos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var resp = ajax.responseText;
			resp = resp.substring(0,resp.length-1);
			var dresp = resp.split(";");
			for(var i=0;i<dresp.length;i++){
				var detalles = dresp[i].split("|");
				var options = document.createElement('option');
				options.value = detalles[0];
				options.text = detalles[1];
				select.add(options);
			}
			cargarUnidades(document.getElementById('ud'),compuesto,objetoPrinActivo);
		}
	  }
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ff");
}

function cargarPresentacion(select){
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearMedicamentos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var resp = ajax.responseText;
			resp = resp.substring(0,resp.length-1);
			var dresp = resp.split(";");
			for(var i=0;i<dresp.length;i++){
				var detalles = dresp[i].split("|");
				var options = document.createElement('option');
				options.value = detalles[0];
				options.text = detalles[1];
				select.add(options);
			}
			cargarUDPresentacion(document.getElementById('udpres'));
		}
	  }
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=pre");
}

function cargarUDPresentacion(select){
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearMedicamentos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var resp = ajax.responseText;
			resp = resp.substring(0,resp.length-1);
			var dresp = resp.split(";");
			for(var i=0;i<dresp.length;i++){
				var detalles = dresp[i].split("|");
				var options = document.createElement('option');
				options.value = detalles[0];
				options.text = detalles[1];
				select.add(options);
			}
		}
	  }
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=udpre");
}

function cargarConcentracion(div,codMed,primeraVez,selectOpPA){
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearMedicamentos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var resp = ajax.responseText;
			if(resp!=""){
				resp = resp.substring(0,resp.length-1);
				var dresp = resp.split(";");
				var lineas = "";
				for(var i=0;i<dresp.length;i++){
					var detalles = dresp[i].split("|");
					lineas += "<div class='lComp'><p class='comp'>"+detalles[0]+"</p><p class='conc'>"+detalles[1]+"</p><p class='del'><a href='#' onclick='ventPresentacion(&quot;"+codMed+"&quot;,&quot;"+detalles[2]+"&quot;,&quot;"+detalles[3]+"&quot;,&quot;no&quot;)'>Crear presentacion</a></p></div>";
				}
				div.innerHTML=lineas;
			}
			if(primeraVez==1){
				cargarFormaFarma(document.getElementById('selff'),"",selectOpPA);
			}
		}
	  }
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=conc&codMed="+codMed);
}

function cargarConcentracionComp(div,codMed,primeraVez,compuesto,objetoPrinActivo){

	ajax=getXMLObject();
	ajax.open("POST","ControlCrearMedicamentos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var resp = ajax.responseText;
			if(resp!=""){
				resp = resp.substring(0,resp.length-1);
				var dresp = resp.split(";");
				var lineas = "";
				for(var i=0;i<dresp.length;i++){
					var detalles = dresp[i].split("|");
					lineas += "<div class='lComp'><p class='comp'>"+detalles[1]+"</p><p class='conc'>"+detalles[2]+"</p><p class='del'><a href='#' onclick='ventPresentacion(&quot;"+detalles[4]+"&quot;,&quot;"+detalles[3]+"&quot;,&quot;"+detalles[0]+"&quot;,&quot;si&quot;)'>Crear presentacion</a></p></div>";
				}
				div.innerHTML=lineas;
			}
			if(primeraVez==1){
				cargarFormaFarma(document.getElementById('selff'),compuesto,objetoPrinActivo);
			}else{
				cargarPrinActivo(compuesto,objetoPrinActivo);
			}
		}
	  }
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=concComp&codMed="+codMed);
}

function cargarPrinActivo(compuesto,select){

	if(compuesto==undefined){
		compuesto="";
	}
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearMedicamentos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var resp = ajax.responseText;
			resp = resp.substring(0,resp.length-1);
			var dresp = resp.split(";");
			if(select!=null){
				for(var i=0;i<dresp.length;i++){
					var detalles = dresp[i].split("|");
					var options = document.createElement('option');
					options.value = detalles[0];
					options.text = detalles[1];
					select.add(options);
				}
			}
			
		}
	  }
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=prinactivo&compuestos="+compuesto);
}

function cargarUnidades(select,compuesto,objetoPrinActivo){
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearMedicamentos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var resp = ajax.responseText;
			resp = resp.substring(0,resp.length-1);
			var dresp = resp.split(";");
			for(var i=0;i<dresp.length;i++){
				var detalles = dresp[i].split("|");
				var options = document.createElement('option');
				options.value = detalles[0];
				options.text = detalles[1];
				select.add(options);
			}
			cargarPrinActivo(compuesto,objetoPrinActivo);
		}
	  }
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ud");
}

function abrirVentana(){
	var composicion = document.getElementById("comp").value;
	var comp = document.getElementById("comp").text;
	var nomMedi = document.getElementById("nomMedi").value.toUpperCase();
	var clas = document.getElementById("clas").text;
	var clasificacion = document.getElementById("clas").value;
	
	if(comp!="Seleccione..."&&nomMedi!=""&&clas!="Seleccione..."){
		
					if(composicion=="0"){
						if(clasificacion=="0"){
							ajax1=getXMLObject();
							ajax1.open("POST","ControlCrearMedicamentos",true);
							ajax1.onreadystatechange = function(){
								if(ajax1.readyState == 4){
									var resp2 = ajax1.responseText;
									if(resp2!=""){
										ajax=getXMLObject();
										ajax.open("POST","ControlCrearMedicamentos",true);
										ajax.onreadystatechange = function(){
											if(ajax.readyState == 4){
												ventComposicion(ajax.responseText, resp2);									
											}
										}
										ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
										ajax.send("valor=1&composicion="+composicion+"&nomMedi="+nomMedi+"&clasificacion="+clasificacion);
									}else{
										alert("El medicamento no coincide con ningun principio activo");
									}
								}
							}
					
							ajax1.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
							ajax1.send("valor=pa&nomMedi="+nomMedi);
						}
						if(clasificacion=="1"){
							ajax6=getXMLObject();
							ajax6.open("POST","ControlCrearMedicamentos",true);
							ajax6.onreadystatechange = function(){
								if(ajax6.readyState == 4){
									resp = ajax6.responseText;
									if(resp==""||resp=="nuevo"){
										ajax=getXMLObject();
										ajax.open("POST","ControlCrearMedicamentos",true);
										ajax.onreadystatechange = function(){
											if(ajax.readyState == 4){
												codMed = ajax.responseText;
												ajax2=getXMLObject();
												ajax2.open("POST","ControlCrearMedicamentos",true);
												ajax2.onreadystatechange = function(){
													if(ajax2.readyState == 4){
														
														ventComposicion(codMed,ajax2.responseText);
													}
												}
												ajax2.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
												ajax2.send("valor=pamc&codMed="+codMed+"&opcion=1");
											}
										}
										ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
										ajax.send("valor=1&composicion="+composicion+"&nomMedi="+nomMedi+"&clasificacion="+clasificacion);
									}else{
										
										resp2 = resp.split("|");
										alert("Este descripcion ya se encuentra creado como un \nmedicamento "+resp2[0]+" de composicion "+resp2[1]);
									}
								}
									
								}
								
								ajax6.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
								ajax6.send("valor=MED&nomMedi="+nomMedi+"&clas="+clasificacion+"&comp="+composicion);
						}
					}
					if(composicion=="1"){	
						if(clasificacion=="0"){
							
							nomMedi = nomMedi.replace(/[+]/gi,"_");
							var detNomMedi = nomMedi.split("_");
							
							ajax1=getXMLObject();
							ajax1.open("POST","ControlCrearMedicamentos",true);
							ajax1.onreadystatechange = function(){
								if(ajax1.readyState == 4){
									var resp2 = ajax1.responseText;
									
									if(resp2!=""){
										if(detNomMedi.length==1){
											if(confirm("El medicamento compuesto presenta solo un principio activo. \nDesea crearlo como medicamento simple?")){
												ajax2=getXMLObject();
												ajax2.open("POST","ControlCrearMedicamentos",true);
												ajax2.onreadystatechange = function(){
													if(ajax2.readyState == 4){
														ventComposicion(ajax2.responseText, resp2);									
													}
												}
												ajax2.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
												ajax2.send("valor=1&composicion=0"+"&nomMedi="+nomMedi+"&clasificacion="+clasificacion);
											}    
										}else{
											if(detNomMedi.length>1){
												
												nomMedi = detNomMedi[0].trim()+"+"+detNomMedi[1].trim();
												nomMed = nomMedi;
												nomMedi= encodeURIComponent(nomMedi);
												ajax3=getXMLObject();
												ajax3.open("POST","ControlCrearMedicamentos",true);
												ajax3.onreadystatechange = function(){
													if(ajax3.readyState == 4){
														//alert(ajax3.responseText+", "+nomMed+", "+resp2);
														ventComposicionComp(ajax3.responseText,nomMed,resp2);						
													}
												}
												ajax3.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
												ajax3.send("valor=1&composicion="+composicion+"&nomMedi="+nomMedi+"&clasificacion="+clasificacion);
											}
										}
										
									}else{
										alert("El medicamento no coincide con ningun principio activo");
									}
								}
							}
							
							ajax1.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
							ajax1.send("valor=pac&nomMedi="+nomMedi);
						}
						if(clasificacion=="1"){
							ajax6=getXMLObject();
							ajax6.open("POST","ControlCrearMedicamentos",true);
							ajax6.onreadystatechange = function(){
								if(ajax6.readyState == 4){
									resp = ajax6.responseText;
									if(resp==""||resp=="nuevo"){
										ajax=getXMLObject();
										ajax.open("POST","ControlCrearMedicamentos",true);
										ajax.onreadystatechange = function(){
											if(ajax.readyState == 4){
												codMed = ajax.responseText;
												ajax3=getXMLObject();
												ajax3.open("POST","ControlCrearMedicamentos",true);
												ajax3.onreadystatechange = function(){
													if(ajax3.readyState == 4){
														ventComposicionComp(codMed,nomMedi,ajax3.responseText);
													}
												}
												ajax3.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
												ajax3.send("valor=pamc&codMed="+codMed+"&opcion=2");
											}
										}
										ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
										ajax.send("valor=1&composicion="+composicion+"&nomMedi="+nomMedi+"&clasificacion="+clasificacion);
									}else{
										
										resp2 = resp.split("|");
										alert("Este descripcion ya se encuentra creado como un \nmedicamento "+resp2[0]+" de composicion "+resp2[1]);
									}
								}
									
								}
								
								ajax6.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
								ajax6.send("valor=MED&nomMedi="+nomMedi+"&clas="+clasificacion+"&comp="+composicion);
						}
					}
			
			
	}else{
		alert("Debe llenar todos los campos");
	}
}

function crearConcentracion(codMed,pa){
	if(pa!=""||document.getElementById("prinActivo")==null){
		var compuesto = document.getElementById("codComp").value;
	}else{
		compuesto = document.getElementById("prinActivo").value;
	}
	var selff = document.getElementById("selff").value;
	var tipoMed = document.getElementById("tipoMed").value;
	var concentracion = document.getElementById("txtConcentracion").value;
	var udconcen = document.getElementById("ud").value;
	var medControl = document.getElementById("medControl").value;
	var cum = document.getElementById("cum").value;
	var ATC = "default";
	if(document.getElementById("ATC")!=null){
		ATC = document.getElementById("ATC").value;
	}
	
	if(compuesto!=""&&concentracion!=""&&selff!=""&&tipoMed!=""&&udconcen!=""&&medControl!=""&&cum!=""&&ATC!=""){
		
		cum = limpiarATC_CUM(cum,'CUM');
		ATC = limpiarATC_CUM(ATC,'ATC');
		
		ajax4=getXMLObject();
		ajax4.open("POST","ControlCrearMedicamentos",true);
		ajax4.onreadystatechange = function(){
			if(ajax4.readyState == 4){
				if(ajax4.responseText==""){
					ajax=getXMLObject();
					ajax.open("POST","ControlCrearMedicamentos",true);
					ajax.onreadystatechange = function(){
						if(ajax.readyState == 4){
							if(ajax.responseText==""){
								cargarConcentracion(document.getElementById('allComp'), codMed,0);
							}else{
								alert("El medicamento ya presenta una forma farmaceutica con la concentracion")
							}
							if(document.getElementById("prinActivo")!=null&&pa==""){
								var indice = document.getElementById("prinActivo").selectedIndex;
								document.getElementById('composicion').innerHTML="<p class='desc'>Compuesto:</p><input id='compuesto' disabled=true type='text' value='"+document.getElementById("prinActivo").options[indice].text+"'/><input id='codComp' type='hidden' value='"+document.getElementById("prinActivo").value+"'/>";
							}
							limpiarCargueConcentracion();
						}
					  }
					ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=2&codMed="+codMed+"&compuesto="+compuesto+"&selff="+selff+"&tipoMed="+tipoMed+"&concentracion="+concentracion+"&udconcen="+udconcen+"&medControl="+medControl+"&cum="+cum+"&pa="+pa+"&atc="+ATC);
				}else{
					document.getElementById("cum").value="";
					alert("El codigo CUM digitado ya pertenece a otro medicamento");
					document.getElementById("cum").focus();
				}
			}
			
		  }
		ajax4.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax4.send("valor=cum&CUM="+cum);
	}else{
		alert("Debe llenar todos los campos");
	}
}

function crearPresentacion(codMed,codConc,codFF,esCompuesto){
	//alert(codConc);
	var tipoPre = document.getElementById("pres").value;
	var udTipoPres = document.getElementById("udpres").value;
	var cantPres = document.getElementById("txtcantConc").value;
	var usuario = document.getElementById("txtusu").value;
	
	var CUM = "";
	if(document.getElementById('cumPresentacion').style.display=='inline'){
		CUM = document.getElementById('cumP').value;
	}
	
	if(tipoPre!=""&&udTipoPres!=""&&cantPres!=""){
		document.getElementById('btn1').disabled = true;
		ajax=getXMLObject();
		ajax.open("POST","ControlCrearMedicamentos",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				cargarPresentacionMed(document.getElementById('allComp2'),codMed,codConc);
				document.getElementById('btn1').disabled = false;
			}
		  }
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&codMed="+codMed+"&codConc="+codConc+"&tipoPre="+tipoPre+"&udTipoPres="+udTipoPres+"&cantPres="+cantPres+"&codFF="+codFF+"&usuario="+usuario+"&esCompuesto="+esCompuesto+"&cum="+CUM);
	}else{
		alert("Debe llenar todos los campos");
	}
}

function cargarPresentacionMed(div,codMed,codConc){
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearMedicamentos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var resp = ajax.responseText;
			if(resp!=""){
				resp = resp.substring(0,resp.length-1);
				var dresp0 = resp.split("%");
				if(dresp0[0]=="CPP"||dresp0[0]=="cpp"){
					document.getElementById('cumPresentacion').style.display='inline';
					document.getElementById('allComp2').className = 'allComp22';
				}
				if(dresp0[1]!=null){
					var dresp = dresp0[1].split(";");
					var lineas = "";
					for(var i=0;i<dresp.length;i++){
						var detalles = dresp[i].split("|");
						lineas += "<div class='lComp'><p class='comp'>"+detalles[0]+"</p><p class='conc'>"+detalles[1]+"</p></div>";
					}
					div.innerHTML=lineas;
				}
			}
			cargarPresentacion(document.getElementById('pres'));
		}
	  }
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=presentacion&codMed="+codMed+"&codConc="+codConc);
}

function limpiarCargueConcentracion(){
	document.getElementById("selff").options[0].selected=true;
	document.getElementById("tipoMed").options[0].selected=true;
	document.getElementById("txtConcentracion").value="";
	document.getElementById("ud").options[0].selected=true;
	document.getElementById("medControl").options[0].selected=true;
	document.getElementById("cum").value="";
	document.getElementById('tipoMedicamento').innerHTML="<div class='lDet' id='tipoMedicamento'><p class='desc'>Tipo medicamento:</p><select id='tipoMed'  onchange='activarATC(this)'><option>Seleccione...</option><option value='0'>No Pos</option><option value='1'>Pos</option></select></div>";
}

function finalizarCreacion(codMed){
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearMedicamentos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var resp = ajax.responseText;
			if(resp==""){
				window.location.reload();
			}else{
				alert("No estan creadas todas las presentaciones a las diferentes forma farmaceuticas");
			}
		}
	  }
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=fin&codMed="+codMed);
}
var comp="";
var codFFm = "";//global para medicamento compuesto
function crearConcentracionComp(codMed,compuestos){
	var nuevaFF = false;
	select = document.getElementById('prinActivo');
	divAll = document.getElementById('allComp');
	var compuesto = document.getElementById("prinActivo").value;
	var selff = document.getElementById("selff").value;
	var tipoMed = document.getElementById("tipoMed").value;
	var concentracion = document.getElementById("txtConcentracion").value;
	var udconcen = document.getElementById("ud").value;
	var medControl = document.getElementById("medControl").value;
	var cum = document.getElementById("cum").value; 
	var ATC = "default";
	if(document.getElementById("ATC")!=null){
		ATC = document.getElementById("ATC").value;
	}

	if(compuesto!=""&&concentracion!=""&&selff!=""&&tipoMed!=""&&udconcen!=""&&medControl!=""&&cum!=""&&ATC!=""){
		cum = limpiarATC_CUM(cum,'CUM');
		ATC = limpiarATC_CUM(ATC,'ATC');
		ajax4=getXMLObject();
		ajax4.open("POST","ControlCrearMedicamentos",true);
		ajax4.onreadystatechange = function(){
			if(ajax4.readyState == 4){
				var optSelComp = document.getElementById("prinActivo").childNodes;
				var cantCompuestos = compuestos.split("|");
				if(optSelComp.length==cantCompuestos.length-1||compMedCom==""){
					nuevaFF = true;
				}		
				comp = "";
				
				if(ajax4.responseText==""||(document.getElementById("cum").disabled==true)){
					if(optSelComp.length>1){
						for (var i=0;i<optSelComp.length;i++){
							if(optSelComp[i].value!=compuesto){
								comp+=optSelComp[i].value+"|";
								document.getElementById("selff").disabled=true;
								document.getElementById("tipoMed").disabled=true;
								document.getElementById("medControl").disabled=true;
								if(document.getElementById("ATC")!=null){
									document.getElementById("ATC").disabled=true;
								}
								document.getElementById("cum").disabled=true;
								document.getElementById("txtConcentracion").value="";
								document.getElementById("ud").options[0].selected=true;
							}
						}
					}else{
						if(optSelComp.length==1){
							comp=compuestos;
							limpiarCargueConcentracion();
							document.getElementById("selff").disabled=false;
							document.getElementById("tipoMed").disabled=false;
							document.getElementById("medControl").disabled=false;
							document.getElementById("cum").disabled=false;
						}
					}
					
					
					document.getElementById("prinActivo").length=0;
					
					ajax=getXMLObject();
					ajax.open("POST","ControlCrearMedicamentos",true);
					ajax.onreadystatechange = function(){
						if(ajax.readyState == 4){
							
							codFFm = ajax.responseText;
							if(ajax.responseText==""){
								cargarConcentracionComp(divAll, codMed,2,comp, select);
								compMedCom += compuesto+"|";
							}else{
								
								alert("El medicamento ya presenta una forma farmaceutica con la concentracion")
								
								
							}
											
						}
						
					  }
					ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=4&codMed="+codMed+"&compuesto="+compuesto+"&selff="+selff+"&tipoMed="+tipoMed+"&concentracion="+concentracion+"&udconcen="+udconcen+"&codFFm="+codFFm+"&medControl="+medControl+"&nuevaFF="+nuevaFF+"&cum="+cum+"&atc="+ATC);
				}else{
					document.getElementById("cum").value="";
					alert("El codigo CUM digitado ya pertenece a otro medicamento");
					document.getElementById("cum").focus();
				}
			}
			
		  }
		ajax4.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax4.send("valor=cum&CUM="+cum);
	}else{
		alert("Debe llenar todos los campos");
	}
}

function nuevaFF(codMed){
	select = document.getElementById('prinActivo');
	divAll = document.getElementById('allComp');
	compMedi = compMedCom;
	document.getElementById("prinActivo").length=0;
	cargarConcentracionComp(divAll, codMed,2,compMedi,select);
	document.getElementById('botones').innerHTML ="<input id ='btn2' type='button' value='Agregar' onclick='crearConcentracionComp(&quot;"+codMed+"&quot;,&quot;"+compMedi+"&quot;)'/> ";
	limpiarCargueConcentracion();
	document.getElementById("selff").disabled=false;
	document.getElementById("tipoMed").disabled=false;
	document.getElementById("medControl").disabled=false;
	document.getElementById("cum").disabled=false;
	
}
