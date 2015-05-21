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


function abrirVentana() {

	ajax = getXMLObject();
	var contenido1 = document.getElementById('Opcion');
	ajax.open("POST", "ControlCrearArticulo2", true);

	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido1.innerHTML = ajax.responseText
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=30A"); // Posting txtname to Servlet
}


function medoins(){
	var contenido1=document.getElementById('opcion');
	var  cmbGrupo=document.getElementById('cmbGrupo').value;

	
	if( cmbGrupo=="2"){
	 document.getElementById('txtConcentracion').value="";
	 document.getElementById('txtConcentracion').disabled=true;
	 document.getElementById('cmbFFarmaceutica').value="Seleccione";
	 document.getElementById('cmbFFarmaceutica').disabled=true;
	 document.getElementById('cmbUnidad').value="Seleccione";
	 document.getElementById('cmbUnidad').disabled=true;
	 document.getElementById('cmbClasificacion').value="Seleccione...";
	 document.getElementById('cmbClasificacion').disabled=true;
	 document.getElementById('cmbControl').value="";
	 document.getElementById('cmbControl').disabled=true;
	 //GuardarArticuloPrecio(cmbGrupo);
	 
	}else{
		 document.getElementById('txtConcentracion').disabled=false;
		 document.getElementById('cmbFFarmaceutica').disabled=false;
		 document.getElementById('cmbUnidad').disabled=false;
		 document.getElementById('cmbClasificacion').disabled=false;
		 document.getElementById('cmbControl').disabled=false;
		 //GuardarArticuloPrecio(cmbGrupo);
	}
	
	
}


function activarATC(object){
	var seleccion = object.value;
	if(seleccion=="Pos"){
		document.getElementById('tipoMedicamento').innerHTML="<p class='desc' >Tipo medicamento:</p><select id='cmbClasificacion' name='cmbClasificacion' style='float:left;margin-left:3px;' onchange='activarATC(this)'><option>Seleccione...</option><option  value='No Pos'>No Pos</option><option value='Pos'>Pos</option></select>" +
        "<p class ='desc'> ATC </p><input type='text' id='txtCodigoATC' name='txtCodigoATC'onkeypress='return OnlyChr(event,&quot;af&quot;)'/>";
		document.getElementById("cmbClasificacion").options[2].selected=true;	
	}else{
		document.getElementById('tipoMedicamento').innerHTML="<div class='lDet' id='tipoMedicamento'><p class='desc'>Tipo medicamento:</p><select id='cmbClasificacion'  name='cmbClasificacion' onchange='activarATC(this)'><option>Seleccione...</option><option value='No Pos'>No Pos</option><option value='Pos'>Pos</option></select></div>";
		if(seleccion=="No Pos"){
			document.getElementById('tipoMedicamento').innerHTML="<p class='desc' >Tipo medicamento:</p><select id='cmbClasificacion' name='cmbClasificacion' style='float:left;margin-left:3px;' onchange='activarATC(this)'><option>Seleccione...</option><option  value='No Pos'>No Pos</option><option value='Pos'>Pos</option></select>" +
			"<p class ='desc'> </p><input type='text' style='visibility:hidden' id='txtCodigoATC' name='txtCodigoATC' value=' '/>";
			document.getElementById("cmbClasificacion").options[1].selected=true;
		}else{
			if(seleccion==""){
				document.getElementById("cmbClasificacion").options[0].selected=true;
			}
		}
		
	}
}

function GuardarArticuloPrecio(cmbGrupo){
	
	var txtNombreArticulo=document.getElementById('txtNombreArticulo').value;
	var cmbGrupo=document.getElementById('cmbGrupo').value;
	var cmbClasificacion=document.getElementById('cmbClasificacion').value;
	var txtCodusuario=document.getElementById("txtCodusuario").value;
	
	if(cmbGrupo==1){
		
		//var txtNombreArticulo=document.getElementById('txtNombreArticulo').value;
		var txtCodigoATC=document.getElementById('txtCodigoATC').value;
		//var cmbGrupo=document.getElementById('cmbGrupo').value;
		var txtConcentracion=document.getElementById('txtConcentracion').value;
		var cmbUnidad=document.getElementById('cmbUnidad').value;
		var cmbFFarmaceutica=document.getElementById('cmbFFarmaceutica').value;
		var cmbControl=document.getElementById('cmbControl').value;
		//var cmbClasificacion=document.getElementById('cmbClasificacion').value;
		var cmbTipo=document.getElementById('cmbTipo').value;

		//var txtCodusuario=document.getElementById("txtCodusuario").value;
	
	if(txtNombreArticulo==""){
			alert("Diligencie en nombre del articulo.");
		}else{
				if(cmbGrupo=="Seleccione"){
					alert("Seleccione el Grupo");
				}else{
					if(txtConcentracion==""){
						alert("Diligencie la concentracion");
					}else{
						if(cmbUnidad=="Seleccione"){
							alert("Seleccione la Unidad");
						}else{
							if(cmbFFarmaceutica=="Seleccione"){
								alert("Seleccione Forma Farmaceutica");
							}else{
								if(cmbControl=="Seleccione"){
									alert("Seleccione control");
								}else{
									if(cmbClasificacion=="Seleccione"){
										alert("Seleccione Clasificacion");
									}else{
										if(cmbTipo==""){
											alert("Seleccione Tipo");
										}else{
												var uni = document.form1.cmbUnidad.selectedIndex;
												var nomuni=document.form1.cmbUnidad.options[uni].text;
												
												var FF = document.form1.cmbFFarmaceutica.selectedIndex;
												var nomFF=document.form1.cmbFFarmaceutica.options[FF].text;
												
												var NombreCompletoArticulo=txtNombreArticulo+" "+txtConcentracion+" "+nomuni+" "+nomFF;
												
												if(txtCodigoATC=="")
												ajax=getXMLObject();
												ajax.open("POST","ControlCrearArticulo2",true);
												ajax.onreadystatechange = function(){
													if(ajax.readyState == 4){
															alert(ajax.responseText);
															window.location.reload();
													}
												}
												ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
												ajax.send("valor=CAV&txtNombreArticulo="+encodeURIComponent(NombreCompletoArticulo)+
														"&txtCodigoATC="+txtCodigoATC+"&cmbGrupo="+cmbGrupo+"&txtConcentracion="+encodeURIComponent(txtConcentracion)+
														"&cmbUnidad="+cmbUnidad+"&cmbFFarmaceutica="+cmbFFarmaceutica+
														"&cmbControl="+cmbControl+"&cmbClasificacion="+cmbClasificacion+
														"&cmbTipo="+cmbTipo+"&txtCodusuario="+txtCodusuario);

											}
										}
									}
								}
							}
						}					
					}
				}
	}
	else{
		var NombreCompletoArticulo=txtNombreArticulo;
		txtCodigoATC="";
		txtConcentracion="";
		cmbUnidad="1";
		cmbFFarmaceutica="1";
		var cmbControl="No";
		cmbTipo="";


		ajax=getXMLObject();
		ajax.open("POST","ControlCrearArticulo2",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				alert(ajax.responseText);
				window.location.reload();
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		
		//ajax.send("valor=1&codigoArticulo="+codigoArticulo+"&nombre="+nombre+"&concentracion="+concentracion+"&cod_formaFK="+cod_formaFK+"&cod_unidadFK="+cod_unidadFK+"&tipoArticulo="+tipoArticulo+"&nombreGenerico="+nombreGenerico+"&tipo="+tipo+"&grupo="+grupo+"&control="+control+"&observacion="+observacion+"&user="+user);
		ajax.send("valor=CAV&txtNombreArticulo="+encodeURIComponent(NombreCompletoArticulo)+
				"&txtCodigoATC="+txtCodigoATC+"&cmbGrupo="+cmbGrupo+"&txtConcentracion="+encodeURIComponent(txtConcentracion)+
				"&cmbUnidad="+cmbUnidad+"&cmbFFarmaceutica="+cmbFFarmaceutica+
				"&cmbControl="+cmbControl+"&cmbClasificacion="+cmbClasificacion+
				"&cmbTipo="+cmbTipo+"&txtCodusuario="+txtCodusuario);
}
	
		}





