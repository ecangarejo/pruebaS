// JavaScript Document

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/** *********************************************** */
function getXMLObject() // XML OBJECT
{
	var xmlHttp = false;
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP"); // For Old Microsoft
		// Browsers
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); // For Microsoft
			// IE 6.0+
		} catch (e2) {
			xmlHttp = false; // No Browser accepts the XMLHTTP Object then
			// false
		}
	}
	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest(); // For Mozilla, Opera Browsers
	}
	return xmlHttp; // Mandatory Statement returning the ajax object created
}

var xmlhttp = new getXMLObject(); // xmlhttp holds the ajax object

/****************************************************/
var myImage;
var arrayNombresRadiosAntecedentes;
var arrayNombresRadiosExamenClinico;
var GeneralidadesDientesDATA;
var matrizgeneralidadesdisponibles = new Array();
var informeviejo=1;
var nombregeneralidades = new Array();
var arrayHistoriasAnteriores = new Array();
var arraynombretecnicoDientes = new Array();
var opcion="2";

//funcion para saber si se encuentra el paciente registrado en el sistema

function cargarData(){
	ajax = getXMLObject();
	ajax.open("POST", "modificacionRegistro", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			
			//alert(ajax.responseText);
			var datos = ajax.responseText.split("|");
		
			
			if (datos.length > 0) {
				GeneralidadesDientesDATA =  new Array();
     			aparecerElemento("odontograma");
			    myImage=new Image();
				myImage.onload = function(){
			
					var indice = 1;
					var sw1 = 0; // cada ocho veces
					var sw2 = 0; // 
					for ( var i = 0; i < 52; i++) {
						GeneralidadesDientesDATA[i] ="sin_cambios";
						if (sw1 == 8) {
							indice++;
							sw1 = 0;
						}
						if (i == 32) {
							indice = 5;
						}
						if (sw2 == 5) {
							indice++;
							sw2 = 0;
						}
						switch (indice) {
						case 1:
							ArreglonombresDientes.push('canvas_dsi' + (sw1 + 1));
							break;
						case 2:
							ArreglonombresDientes.push('canvas_dsd' + (sw1 + 1));
							break;
						case 3:
							ArreglonombresDientes.push('canvas_dii' + (sw1 + 1));
							break;
						case 4:
							ArreglonombresDientes.push('canvas_did' + (sw1 + 1));
							break;
						case 5:
							ArreglonombresDientes.push('canvas_dcsi' + (sw2 + 1));
							break;
						case 6:
							ArreglonombresDientes.push('canvas_dcsd' + (sw2 + 1));
							break;
						case 7:
							ArreglonombresDientes.push('canvas_dcid' + (sw2 + 1));
							break;
						default:
							ArreglonombresDientes.push('canvas_dcii' + (sw2 + 1));
							break;
						}
						if (i < 32) {
							sw1++;
						} else {
							sw2++;
						}
					}	
	   				indice = 0;
	                    // alert("cargar"+indice);
						while (indice < ArreglonombresDientes.length) {
							document.getElementById(ArreglonombresDientes[indice]).width = 30;
					    	document.getElementById(ArreglonombresDientes[indice]).height = 30;
						//	document.getElementById(ArreglonombresDientes[indice]).getContext('2d').drawImage(img,	0,0,document.getElementById(ArreglonombresDientes[indice]).width,document.getElementById(ArreglonombresDientes[indice]).height);
					    	document.getElementById(ArreglonombresDientes[indice]).getContext('2d').drawImage(myImage,	0,0,document.getElementById(ArreglonombresDientes[indice]).width,document.getElementById(ArreglonombresDientes[indice]).height);
						//	refrescarDiente(ArreglonombresDientes[indice]);
							//	document.getElementById(ArreglonombresDientes[indice]).getContext('2d').drawImage(this,	0,0,img.width,img.height);
							indice = indice + 1;
						}
						cargarnombresradios();
						var j = 0;
						//alert("xxx dta recibida "+datos.length);
						while (j < datos.length ){
								 
								 switch(j){
								 
								 case 0:
									 var datadienteshistoriaanterior = datos[0].split("@");
									 var cadenapordiente="";
									 var generalidadesdiente = "";
									 var generalidadobtenida=1;
									 var direccionobtenida=1;
									 for(var z = 0 ; z < datadienteshistoriaanterior.length ; z++){
										 cadenapordiente = datadienteshistoriaanterior[z].split("-");
										 for(var a = 0 ; a < cadenapordiente.length ; a++ ){
											 
											 generalidadesdiente = cadenapordiente[a].split(",");
											 generalidadobtenida = parseInt(generalidadesdiente[0])-2;
											 direccionobtenida = parseInt(generalidadesdiente[1])-1;
											 
											/* if (generalidadobtenida ==-1){
												 GeneralidadesDientesDATA[z] = 'normal';
											 }
											 else{
												 seleccionOpcion(direccionobtenida, generalidadobtenida, ArreglonombresDientes[z]);
											 }*/
											
											 
										 if (generalidadobtenida >-1){
											 seleccionOpcion(direccionobtenida, generalidadobtenida, ArreglonombresDientes[z]);
										 }
											 
											 
										 }
									 }
								informeviejo=0;

									 break;
									 
									 
								break;
								 case 1:
									 var dataexamenclinicoprevio = datos[1].split("-");
									 
										for(var m = 0; m < dataexamenclinicoprevio.length; m++){
											
											reiniciarRadiosButtons(arrayNombresRadiosExamenClinico[m], dataexamenclinicoprevio[m]);
										}
									 
									 break;
								 case 2:
									    var dataantecedentesprevios = datos[2].split("-");
										 
										for(var l = 0; l < dataantecedentesprevios.length; l++){
											reiniciarRadiosButtons(arrayNombresRadiosAntecedentes[l], dataantecedentesprevios[l]);
										}
									 
									 
									 break;
								 
								 case 3:
									 
									 var datahistoriaodontologica = datos[3].split("&");
									 
									 for(var x = 0; x < datahistoriaodontologica.length; x++){
									 switch(x){
									 case 0:
										 reemplazarinfotextbox("motivo_consulta", datahistoriaodontologica[0]);
									 break;
									 case 1:
										 reemplazarinfotextbox("plan_tratamiento", datahistoriaodontologica[1]);
									 break;
									 case 2:
										 reemplazarinfotextbox("observaciones", datahistoriaodontologica[2]);
									 break;
    								 case 3 :
										 reemplazarinfotextbox("evolucion", datahistoriaodontologica[3]);
									 break;
									 }
									 }

									 break;
									 
								 case 4:
									 if(datos[4]!='0'){
										 
										 var evolucionesAnteriores = datos[4].split('*');
										 var codhtml = "<table width=100% border= '1'> ";
										// alert(evolucionesAnteriores.length +"----");
                                      for (var t =0 ; t < evolucionesAnteriores.length ; t++){
                                     	// alert(evolucionesAnteriores[t]);
                                     	 var cad = evolucionesAnteriores[t];
                                     	 var fechainfo=cad.split("@");
                               			 codhtml += 
                               			    "<tr>" +
									 		"<td colspan='1' width='20%' >"+ 
									 		"<div align='center'>"+
									 		fechainfo[0]+
									 		"</div>"+
									 		"</td>" +
									 		"<td colspan='8'width='80%'>"+ 
									 		"<div align='center'>"+
									 		"<textarea readonly class='textarea'  id='historia"+(t+1)+"' name='historia"+(t+1)+"' cols='100' rows='3' >"+fechainfo[1]+"</textarea>"+
									 		"</div>"+
									 		"</td>" +
									 		"</tr>";
										 }
										 
                                      codhtml += "</table>";
										 document.getElementById("evoluciones_previas").innerHTML = codhtml;
										 
										 
									 }
						             
									 
									 break;
									 
									 
								 case 5:
									 
									 if(datos[5]=="true"){
										 
										 var generalidadesdisponiblesporhistoria = datos[6].split(";");
										 
										 for(var u = 0; u < generalidadesdisponiblesporhistoria.length;u++ ){
										
										//	 alert("yyyyy "+ generalidadesdisponiblesporhistoria[u].substring(1, generalidadesdisponiblesporhistoria[u].length-1)); 
                                        	 matrizgeneralidadesdisponibles.push(generalidadesdisponiblesporhistoria[u].substring(1, generalidadesdisponiblesporhistoria[u].length-1)); 
										 }
										 
										 
										 
									 }
								 break;
								 
								 case 6:
									// alert("rrrrrr "+datos[7]);
									 nombregeneralidades = datos[7].split("@");
									 
									 break;
									 
								 case 7:
									 
									 if((datos[8].length != 0)){
									//	alert("uuuu "+datos[8]);
										 arrayHistoriasAnteriores = datos[8].split(",");
										 
									 }
									 
									 break;
								 case 8:
									 
									 arraynombretecnicoDientes = datos[9].split("@");
									 
									 
									 break;
								 }
								 
								 
								 
								 
								 
								j++; 
							 }
						
						
						if(arrayHistoriasAnteriores.length == 0){
							
							opcion ="2";
							
						}else{
							
							 if (confirm("Desea usted modificar solo este odontograma sin afectar a los demas o modificar el actual y cambiar los posteriores? presione aceptar para modificar solo este odontograma, presione cancelar para modificar este y los posteriores")){
								 
								 opcion ="2";
							 }
							 else{
								 opcion ="3";
							 }
							
							
						}
						
						
						
				};
				
				myImage.src = "Imagenes/Diente_Modelo.png";
				//alert("cargo!!!! todo!!!!!");
			} else {
				
			
				alert("no hay data cargada");
			}

		}
	};
//alert("T:"+recopilarinfotextbox("txtcodTratamiento"));
//alert("H:"+recopilarinfotextbox("txtcodHistoria"));
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1&CodTratamiento=" + recopilarinfotextbox("txtcodTratamiento")
			+ "&CodHistoria=" + recopilarinfotextbox("txtcodHistoria")); // Posting txtname
	// to Servlet

	
}

/****************************************************/
var ArreglonombresDientes = new Array();// nombre de los canvas (todos)
var ArreglonombreDibujos = new Array();// nombre canvas dibujados
var ArregloDibujos = new Array();// parametros dibujados de los canvas (misma
// posicion)
var opcionseleccionada = -1;



function validargeneralidadAsignada(codDienteSeleccionado){
	var arrayaux = new Array();
	var arrayaux2 = new Array(); 
	if (matrizgeneralidadesdisponibles.length > 0){
		for(var s=0;s<matrizgeneralidadesdisponibles.length;s++){
			arrayaux = matrizgeneralidadesdisponibles[s].split(",");
			if(arrayaux[codDienteSeleccionado].length==1){
				if(arrayaux[codDienteSeleccionado]>1){
					return s+","+codDienteSeleccionado+","+arrayaux[codDienteSeleccionado];
				}
				
			}
			else{
				arrayaux2 = arrayaux[codDienteSeleccionado].split("@");
				var o = 0;
				
				while (o < arrayaux2.length ){
					if(arrayaux2[o]>1){
						return s+","+codDienteSeleccionado+","+arrayaux[codDienteSeleccionado];
					}
					
					o++;
				}
				
				
			}
			
			
			
			
			
		}
		return "no_hay";
		
	}else{
		return "no_hay";
	}
}



function seleccion(opcion) { // seleccion de la generalidad del diente

	switch (opcion) {
	case 1://seleccion caries
		document.getElementById('boton_caries').className = 'caries_boton_seleccionado';
		document.getElementById('boton_no_erupcionado').className = 'no_erupcionado_boton_normal';
		document.getElementById('boton_radicina').className = 'radicina_boton_normal';
		document.getElementById('boton_sellante_ausente').className = 'sellante_ausente_boton_normal';
		document.getElementById('boton_ausencia_normal').className = 'ausencia_boton_normal';
		document.getElementById('boton_desmineralizacion').className = 'desmineralizacion_boton_normal';
		document.getElementById('boton_obturacion').className = 'obturacion_boton_normal';
		document.getElementById('boton_restaurar').className = 'restaurar_diente_boton_normal';
		opcionseleccionada = 0;

		break;

	case 2://no erupcionado
		document.getElementById('boton_caries').className = 'caries_boton_normal';
		document.getElementById('boton_no_erupcionado').className = 'no_erupcionado_boton_seleccionado';
		document.getElementById('boton_radicina').className = 'radicina_boton_normal';
		document.getElementById('boton_sellante_ausente').className = 'sellante_ausente_boton_normal';
		document.getElementById('boton_ausencia_normal').className = 'ausencia_boton_normal';
		document.getElementById('boton_desmineralizacion').className = 'desmineralizacion_boton_normal';
		document.getElementById('boton_obturacion').className = 'obturacion_boton_normal';
		document.getElementById('boton_restaurar').className = 'restaurar_diente_boton_normal';
		opcionseleccionada = 1;
		break;

	case 3:// radicina
		document.getElementById('boton_caries').className = 'caries_boton_normal';
		document.getElementById('boton_no_erupcionado').className = 'no_erupcionado_boton_normal';
		document.getElementById('boton_radicina').className = 'radicina_boton_seleccionado';
		document.getElementById('boton_sellante_ausente').className = 'sellante_ausente_boton_normal';
		document.getElementById('boton_ausencia_normal').className = 'ausencia_boton_normal';
		document.getElementById('boton_desmineralizacion').className = 'desmineralizacion_boton_normal';
		document.getElementById('boton_obturacion').className = 'obturacion_boton_normal';
		document.getElementById('boton_restaurar').className = 'restaurar_diente_boton_normal';
		opcionseleccionada = 2;
		break;

	case 4://sellante ausente
		document.getElementById('boton_caries').className = 'caries_boton_normal';
		document.getElementById('boton_no_erupcionado').className = 'no_erupcionado_boton_normal';
		document.getElementById('boton_radicina').className = 'radicina_boton_normal';
		document.getElementById('boton_sellante_ausente').className = 'sellante_ausente_boton_seleccionado';
		document.getElementById('boton_ausencia_normal').className = 'ausencia_boton_normal';
		document.getElementById('boton_desmineralizacion').className = 'desmineralizacion_boton_normal';
		document.getElementById('boton_obturacion').className = 'obturacion_boton_normal';
		document.getElementById('boton_restaurar').className = 'restaurar_diente_boton_normal';
		opcionseleccionada = 3;

		break;

	case 5: // diente ausente
		document.getElementById('boton_caries').className = 'caries_boton_normal';
		document.getElementById('boton_no_erupcionado').className = 'no_erupcionado_boton_normal';
		document.getElementById('boton_radicina').className = 'radicina_boton_normal';
		document.getElementById('boton_sellante_ausente').className = 'sellante_ausente_boton_normal';
		document.getElementById('boton_ausencia_normal').className = 'ausencia_boton_seleccionado';
		document.getElementById('boton_desmineralizacion').className = 'desmineralizacion_boton_normal';
		document.getElementById('boton_obturacion').className = 'obturacion_boton_normal';
		document.getElementById('boton_restaurar').className = 'restaurar_diente_boton_normal';
		opcionseleccionada = 4;
		break;

	case 6: // desmineralizacion
		document.getElementById('boton_caries').className = 'caries_boton_normal';
		document.getElementById('boton_no_erupcionado').className = 'no_erupcionado_boton_normal';
		document.getElementById('boton_radicina').className = 'radicina_boton_normal';
		document.getElementById('boton_sellante_ausente').className = 'sellante_ausente_boton_normal';
		document.getElementById('boton_ausencia_normal').className = 'ausencia_boton_normal';
		document.getElementById('boton_desmineralizacion').className = 'desmineralizacion_boton_seleccionado';
		document.getElementById('boton_obturacion').className = 'obturacion_boton_normal';
		document.getElementById('boton_restaurar').className = 'restaurar_diente_boton_normal';
		opcionseleccionada = 5;
		break;

	case 7://obturacion
		document.getElementById('boton_caries').className = 'caries_boton_normal';
		document.getElementById('boton_no_erupcionado').className = 'no_erupcionado_boton_normal';
		document.getElementById('boton_radicina').className = 'radicina_boton_normal';
		document.getElementById('boton_sellante_ausente').className = 'sellante_ausente_boton_normal';
		document.getElementById('boton_ausencia_normal').className = 'ausencia_boton_normal';
		document.getElementById('boton_desmineralizacion').className = 'desmineralizacion_boton_normal';
		document.getElementById('boton_obturacion').className = 'obturacion_boton_seleccionado';
		document.getElementById('boton_restaurar').className = 'restaurar_diente_boton_normal';
		opcionseleccionada = 6;
		break;
	default:// restaurar diente
		document.getElementById('boton_caries').className = 'caries_boton_normal';
		document.getElementById('boton_no_erupcionado').className = 'no_erupcionado_boton_normal';
		document.getElementById('boton_radicina').className = 'radicina_boton_normal';
		document.getElementById('boton_sellante_ausente').className = 'sellante_ausente_boton_normal';
		document.getElementById('boton_ausencia_normal').className = 'ausencia_boton_normal';
		document.getElementById('boton_desmineralizacion').className = 'desmineralizacion_boton_normal';
		document.getElementById('boton_obturacion').className = 'obturacion_boton_normal';
		document.getElementById('boton_restaurar').className = 'restaurar_diente_boton_seleccionado';
		opcionseleccionada = 7;
		break;
	}

}

function cargar() { //carga personalizada de todos los dientes del odontograma de acuerdo al nombre del canvas
	var indice = 1;
	var sw1 = 0;
	var sw2 = 0; 
	for ( var i = 0; i < 52; i++) {
		if (sw1 == 8) {
			indice++;
			sw1 = 0;

		}
		if (i == 32) {
			indice = 5;
		}

		if (sw2 == 5) {
			indice++;
			sw2 = 0;
		}

		switch (indice) {
		case 1:
			ArreglonombresDientes.push('canvas_dsi' + (sw1 + 1));
			break;
		case 2:
			ArreglonombresDientes.push('canvas_dsd' + (sw1 + 1));
			break;
		case 3:
			ArreglonombresDientes.push('canvas_dii' + (sw1 + 1));
			break;
		case 4:
			ArreglonombresDientes.push('canvas_did' + (sw1 + 1));
			break;
		case 5:
			ArreglonombresDientes.push('canvas_dcsi' + (sw2 + 1));
			break;
		case 6:
			ArreglonombresDientes.push('canvas_dcsd' + (sw2 + 1));
			break;
		case 7:
			ArreglonombresDientes.push('canvas_dcii' + (sw2 + 1));
			break;
		default:

			ArreglonombresDientes.push('canvas_dcid' + (sw2 + 1));
			break;
		}
		if (i < 32) {
			sw1++;
		} else {
			sw2++;
		}
	}
	indice = 0;

	var img = new Image();
	//img.height="30";
	//img.width="30";
	img.src = 'Imagenes/Diente_Modelo.png';
	img.onload = function() {
		while (indice < ArreglonombresDientes.length) {
		//	document.getElementById(ArreglonombresDientes[indice]).width = 30;
		//	document.getElementById(ArreglonombresDientes[indice]).height = 30;
			document.getElementById(ArreglonombresDientes[indice]).getContext('2d').drawImage(img,	0,0,document.getElementById(ArreglonombresDientes[indice]).width,document.getElementById(ArreglonombresDientes[indice]).height);
			document.getElementById(ArreglonombresDientes[indice]).getContext('2d').drawImage(img,	0,0,document.getElementById(ArreglonombresDientes[indice]).width,document.getElementById(ArreglonombresDientes[indice]).height);
		//	refrescarDiente(ArreglonombresDientes[indice]);
			//	document.getElementById(ArreglonombresDientes[indice]).getContext('2d').drawImage(this,	0,0,img.width,img.height);
			indice = indice + 1;
		}
	};
}

function obtenerIndexNombreArray(nombreCanvas) { // obtener indice del arreglo de nombres de los canvas de los dientes
	for ( var i = 0; i < ArreglonombresDientes.length; i++) {
		if (ArreglonombresDientes[i] == nombreCanvas) {
			return i;
		}
	}
	return -1;
}

function localizacionCanvas(e, nombreCanvas) { // posicion donde se ubico el mouse en un canvas de un diente cualquiera

	var curleft = 0, curtop = 0;
	var x;
    var y;
	var xTotal =0;
    var yTotal =0;
    var canvas = document.getElementById(nombreCanvas);
    
    if (canvas.offsetParent) {
        do {
            curleft += canvas.offsetLeft;
            curtop += canvas.offsetTop;
        } while (canvas = canvas.offsetParent);
        
     	      
        if (e.pageX || e.pageY)
        {
            x = e.pageX;
            y = e.pageY;
        }
        else {
            x = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
            y = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;
        }
        
        xTotal= x - curleft;
        yTotal= y - curtop;
        
        var sector = sectorSeleccionadoCanvas(xTotal, yTotal);
        //alert(sector);
        if (opcionseleccionada > -1) {
        
        	/*var respuestaSeleccion = validargeneralidadAsignada(obtenerIndexNombreArray(nombreCanvas));
        	        	if(respuestaSeleccion != 'no_hay'){
        		
        		var cad = respuestaSeleccion.split(",");
        		var cadenamensaje = "el diente no."+arraynombretecnicoDientes[cad[1]] +" de la historia odontologica no."+arrayHistoriasAnteriores[cad[0]] + " presenta: ";
        	    var cademensajeaux = "";
        		
        		if(cad.length ==1){
        			cademensajeaux = nombregeneralidades[cad[2]]+",";
        			
        		}else{
        		//	alert(cad[2]);
        		var cad2 = cad[2].split("@");
        	//	alert(cad2[0]);
        		var t = 0;
        		while ( t < cad2.length){
        			
        			
        			cademensajeaux += nombregeneralidades[parseInt(cad2[t])-1] +",";
        			t++;
        			
        		}
        			
        		}
        		cadenamensaje = cadenamensaje + cademensajeaux +" " +"alrededor del mismo, desea reemplazar una nueva generalidad y sobreescribir este y los demas odontograma posteriores?";
        		var retVal = confirm(cadenamensaje);
       		if( retVal == true ){
        			   seleccionOpcion(sector, opcionseleccionada, nombreCanvas);
        		   }
        	
        	else{
        		 seleccionOpcion(sector, opcionseleccionada, nombreCanvas);
        	}
        } else {*/
        	seleccionOpcion(sector, opcionseleccionada, nombreCanvas);
    		
    //	}
        
    }else {
    	alert("seleccione un opcion entre las herramientas");
	}
 
    }
	
}

function guardarArreglo(nombreCanvas, abr_dibujo, shapes, color, direccion) { //funcion de guardar la generalidad, el nombre del canvas, coordenadas  y tipo de dibujo
	/*for (i = 0, j = ArreglonombreDibujos.length; i < j; i++) {
		if (ArreglonombreDibujos[i] == nombreCanvas + abr_dibujo) {
			ArreglonombreDibujos.splice(i, 1);
			ArregloDibujos.splice(i, 1);
			i--;
		}
	}*/

	borrarElementos(nombreCanvas, direccion);
	for ( var i = 0; i < shapes.length; i++) {
//		alert(nombreCanvas + abr_dibujo);
		ArreglonombreDibujos.push(nombreCanvas + abr_dibujo);
		var dibujo = color + "," + shapes[i];
		ArregloDibujos.push(dibujo);
	}

}

function comprobar_radiciva_adyacentes(canvas) { // comprobacion de la existencia de radicivas adyacentes para poder pintar su contorno
var array = new Array();
array[0]=0;
array[1]=0;
array[2]=0;
array[3]=0;
array[4]=0;
for ( var i = 0; i < ArreglonombreDibujos.length; i++) {
		if (ArreglonombreDibujos[i].search(canvas) != -1) {
			if (ArreglonombreDibujos[i].search("_radicina") != -1) {
				//alert(ArreglonombreDibujos[i]);
				if (ArreglonombreDibujos[i].search("_izquierdo") != -1) {
					array[0]=1;
				}
				else if (ArreglonombreDibujos[i].search("_superior") != -1) {
					array[1]=1;
				}
				else if (ArreglonombreDibujos[i].search("_derecho") != -1) {
					array[2]=1;
				}
				
				else if (ArreglonombreDibujos[i].search("_inferior") != -1) {
					array[3]=1;
				}
				else{
					array[4]=1;
				}

			}
		}
     }
	return array;

}

function borrarRacidiva(elemento_para_eliminar, coords){//borrar radicivas del diente
    	for (i = 0, j = ArreglonombreDibujos.length; i < j; i++) {
			if (ArreglonombreDibujos[i] == elemento_para_eliminar) {
				if(ArregloDibujos[i]==coords){
					ArreglonombreDibujos.splice(i, 1);
					ArregloDibujos.splice(i, 1);
					i--;
				}
			}
		}
}

function borrarElementos(canvas, direccion){ // borrar elementos del diente determinado a excepcion de la radiciva
	

	for (var i = 0; i < ArreglonombreDibujos.length; i++ ) {
		array = ArreglonombreDibujos[i].split('_');
		//alert(array[0]+"_"+array[1]+" == "+canvas);
		//alert(array.toString());
		if (array[0]+"_"+array[1]==canvas) {
    	//alert(""+direccion);
			var opcionDibujada="_"+array[3]; //opcion dibujada escojida
			//			alert(array[3]);
			if("_"+array[2]==direccion){//si son caries o obturacion o radicivas
				ArreglonombreDibujos[i]="";
				ArregloDibujos[i]="";
		    }
            else if (opcionDibujada =='_dienteausente'){
    			
    			ArreglonombreDibujos[i]="";
				ArregloDibujos[i]="";
    		}
			
    		else if (opcionDibujada =='_noerupcionado'){
    			
    			ArreglonombreDibujos[i]="";
				ArregloDibujos[i]="";
    		}
           else if (opcionDibujada =='_sellanteausente'){
    			
    			ArreglonombreDibujos[i]="";
				ArregloDibujos[i]="";
    		}
			
           else if (opcionDibujada =='_desmineralizacion'){
   			
   			ArreglonombreDibujos[i]="";
			ArregloDibujos[i]="";
   		}
			
    		
		}
	}
	ArreglonombreDibujos = cleanArray(ArreglonombreDibujos);
	ArregloDibujos = cleanArray(ArregloDibujos);
}

function logica_radiciva(array,shapes,sector,nombreCanvas, abreviacion){// funcion de inserccion de la radiciva por diente segun el sector seleccionado
	var racidiva_eliminar="";

	switch(sector){
	case 1://sector izquierdo
	
		if(array[0]==0){
			borrarElementos(nombreCanvas, "_izquierdo");
			ArreglonombreDibujos.push(nombreCanvas+abreviacion);
			ArregloDibujos.push('blue,'+ shapes[0].toString());
			
			if (array[1]==0){ //superior
				ArreglonombreDibujos.push(nombreCanvas+abreviacion); // 1
				ArregloDibujos.push("red,1,0,11,10,10,11,0,1");
			}
			else{
		     	racidiva_eliminar = nombreCanvas +"_superior_radicina";
				borrarRacidiva(racidiva_eliminar,"red,1,0,11,10,10,11,0,1");
			}
		
			if(array[3]==0){ //inferior
				ArreglonombreDibujos.push(nombreCanvas+abreviacion); // 2
				ArregloDibujos.push("red,2,28,1,27,10,18,11,19");
		
			}
			else{
			 	racidiva_eliminar = nombreCanvas +"_inferior_radicina";
				borrarRacidiva(racidiva_eliminar,"red,2,28,1,27,10,18,11,19");
			}
			
			if(array[4]==0){//central
				
				ArreglonombreDibujos.push(nombreCanvas+abreviacion); // 3
				ArregloDibujos.push("red,11,9,9,9,9,20,11,20");
			
			}else{
				racidiva_eliminar = nombreCanvas +"_central_radicina";
				borrarRacidiva(racidiva_eliminar,"red,11,9,9,9,9,20,11,20");
			}
			ArreglonombreDibujos.push(nombreCanvas+abreviacion);
			ArregloDibujos.push("red,0,1,2,1,2,29,0,29");// 4 fijo
			
			
			
		}
		
		
		
	break;
	
	case 2://sector superior
		if (array[1]==0){
			borrarElementos(nombreCanvas, "_superior");
			ArreglonombreDibujos.push(nombreCanvas+abreviacion);
			ArregloDibujos.push('blue,'+ shapes[0].toString());
			if (array[0]==0){ //izquierdo
				
				ArreglonombreDibujos.push(nombreCanvas+abreviacion); // 1
				ArregloDibujos.push("red,1,0,11,10,10,11,0,1");
			}
			else{
		     	racidiva_eliminar = nombreCanvas +"_izquierdo_radicina";
				borrarRacidiva(racidiva_eliminar,"red,1,0,11,10,10,11,0,1");
			}
		
			if(array[2]==0){ //derecho
				ArreglonombreDibujos.push(nombreCanvas+abreviacion); // 2
				ArregloDibujos.push("red,27,1,28,2,19,11,18,10");
				
			}
			else{
			 	racidiva_eliminar = nombreCanvas +"_derecho_radicina";
				borrarRacidiva(racidiva_eliminar,"red,27,1,28,2,19,11,18,10");
			}
			
			if(array[4]==0){ //central
				ArreglonombreDibujos.push(nombreCanvas+abreviacion); // 3
				ArregloDibujos.push("red,9,9,9,11,20,11,20,9");
			
			}else{
				racidiva_eliminar = nombreCanvas +"_central_radicina";
				borrarRacidiva(racidiva_eliminar,"red,9,9,9,11,20,11,20,9");
			}
			ArreglonombreDibujos.push(nombreCanvas+abreviacion);
			ArregloDibujos.push("red,1,3,1,1,28,1,28,3");// 4 fijo
			
		}
        
		break;
case 3://sector derecho
		if(array[2]==0){
			borrarElementos(nombreCanvas, "_derecho");
			ArreglonombreDibujos.push(nombreCanvas+abreviacion);
			ArregloDibujos.push('blue,'+ shapes[0].toString());
			if (array[1]==0){//superior
				ArreglonombreDibujos.push(nombreCanvas+abreviacion); // 1
				ArregloDibujos.push("red,27,1,28,2,19,11,18,10");
			}
			else{
		     	racidiva_eliminar = nombreCanvas +"_superior_radicina";
				borrarRacidiva(racidiva_eliminar,"red,27,1,28,2,19,11,18,10");
			}
		
			if(array[3]==0){//inferior
				ArreglonombreDibujos.push(nombreCanvas+abreviacion); // 2
				ArregloDibujos.push("red,27,28,28,27,19,18,18,19");
		
			}
			else{
			 	racidiva_eliminar = nombreCanvas +"_inferior_radicina";
				borrarRacidiva(racidiva_eliminar,"red,27,28,28,27,19,18,18,19");
			}
			
			if(array[4]==0){//central
				ArreglonombreDibujos.push(nombreCanvas+abreviacion); // 3
				ArregloDibujos.push("red,20,9,18,9,18,20,20,20");
			
			}else{
				racidiva_eliminar = nombreCanvas +"_central_radicina";
				borrarRacidiva(racidiva_eliminar,"red,20,9,18,9,18,20,20,20");
			}
			ArreglonombreDibujos.push(nombreCanvas+abreviacion);
			ArregloDibujos.push("red,27,1,29,1,29,29,27,29");// 4 fijo
		}
        
		break;
	
		
case 4: //sector inferior
	if(array[3]==0){
		borrarElementos(nombreCanvas, "_inferior");
		ArreglonombreDibujos.push(nombreCanvas+abreviacion);
		ArregloDibujos.push('blue,'+ shapes[0].toString());
		 if (array[0]==0){//izquierdo
		    	
				ArreglonombreDibujos.push(nombreCanvas+abreviacion); // 1
				ArregloDibujos.push("red,2,28,1,27,10,18,11,19");
			}
			else{
		     	racidiva_eliminar = nombreCanvas +"_izquierdo_radicina";
				borrarRacidiva(racidiva_eliminar,"red,2,28,1,27,10,18,11,19");
			}

			if(array[2]==0){//derecho
				
				
				ArreglonombreDibujos.push(nombreCanvas+abreviacion); // 2
				ArregloDibujos.push("red,27,28,28,27,19,18,18,19");

			}
			else{
			 	racidiva_eliminar = nombreCanvas +"_derecho_radicina";
				borrarRacidiva(racidiva_eliminar,"red,27,28,28,27,19,18,18,19");
			}
			
			if(array[4]==0){//central
				
				ArreglonombreDibujos.push(nombreCanvas+abreviacion); // 3
				ArregloDibujos.push("red,9,18,9,20,20,20,20,18");
			
			}else{
				racidiva_eliminar = nombreCanvas +"_central_radicina";
				borrarRacidiva(racidiva_eliminar,"red,9,18,9,20,20,20,20,18");
			}
			ArreglonombreDibujos.push(nombreCanvas+abreviacion);
			ArregloDibujos.push("red,1,29,1,27,28,27,28,29");// 4 fijo
	}
   
	break;
	
	
	default: //sector central
	 if(array[4]==0){
		    borrarElementos(nombreCanvas, "_central");
			ArreglonombreDibujos.push(nombreCanvas+abreviacion);
			ArregloDibujos.push('blue,'+ shapes[0].toString());
		 if (array[0]==0){ //izquierdo
				ArreglonombreDibujos.push(nombreCanvas+abreviacion); // 1
				ArregloDibujos.push("red,11,9,9,9,9,20,11,20");
			}
			else{
		     	racidiva_eliminar = nombreCanvas +"_izquierdo_radicina";
				borrarRacidiva(racidiva_eliminar,"red,11,9,9,9,9,20,11,20");
			}

			if(array[1]==0){ //superior
				ArreglonombreDibujos.push(nombreCanvas+abreviacion); // 2
				ArregloDibujos.push("red,9,9,9,11,20,11,20,9");

			}
			else{
			 	racidiva_eliminar = nombreCanvas +"_superior_radicina";
				borrarRacidiva(racidiva_eliminar,"red,9,9,9,11,20,11,20,9");
			}
			
			if(array[2]==0){//derecho
				ArreglonombreDibujos.push(nombreCanvas+abreviacion); // 3
				ArregloDibujos.push("red,20,9,18,9,18,20,20,20");
			
			}else{
				racidiva_eliminar = nombreCanvas +"_derecho_radicina";
				borrarRacidiva(racidiva_eliminar,"red,20,9,18,9,18,20,20,20");
			}
			
			if(array[3]==0){//inferior
				ArreglonombreDibujos.push(nombreCanvas+abreviacion); // 3
				ArregloDibujos.push("red,9,18,9,20,20,20,20,18");
			
			}else{
				racidiva_eliminar = nombreCanvas +"_inferior_radicina";
				borrarRacidiva(racidiva_eliminar,"red,9,18,9,20,20,20,20,18");
			}
	 }
	   
		break;
}
	refrescarDiente(nombreCanvas);
	dibujar(nombreCanvas);
	
}

function seleccionOpcion(sector, opcion, nombreCanvas) { // funcion para la inserccion de las generalidades por diente hacia el dibujo del mismo
	var shapes = new Array();
	var color = "";
	var abr_dibujo = "";
	switch (sector) {
	case 1: // sector 1
		shapes.push("1,2,10,11,10,19,1,28");
		abr_dibujo = "_izquierdo";
		break;
	case 2: // sector 2

	
		shapes.push("2,1,11,10,19,10,28,1");
		abr_dibujo = "_superior";
		break;
	case 3: // sector 3

	
		shapes.push("29,28,20,19,20,11,29,2");
		abr_dibujo = "_derecho";
		break;
	case 4: // sector 4
	
		shapes.push("28,29,19,20,11,20,2,29");
		abr_dibujo = "_inferior";
		break;

	default: // sector 5

		shapes.push("19,11,11,11,11,19,19,19");
		abr_dibujo = "_central";
		break;
	}

	switch (opcion) {
	case 0:// caries
		
		//borrarElementos(nombreCanvas, abr_dibujo);
		abr_dibujo += "_caries";
		color = "red";
		
		break;
	case 1: // no erupcionado
		shapes = new Array();
		color = "#FDC200";
		abr_dibujo += "_noerupcionado";
	//	alert("/////"+nombreCanvas+abr_dibujo);
		shapes.push("1,13,29,13,29,16,1,16");
		reiniciardibujo(nombreCanvas);
		break;
	case 2:// radicina ???
		abr_dibujo += "_radicina";
		logica_radiciva(comprobar_radiciva_adyacentes(nombreCanvas),shapes,sector,nombreCanvas,abr_dibujo);
		break;

	case 3:
		color = "red";
		shapes = new Array(); // sellantes ausentes
		abr_dibujo += "_sellanteausente";
		shapes.push("25,24,25,26,4,26,4,24");
		shapes.push("24,3,24,5,3,5,3,3");
		shapes.push("4,3,3,5,23,25,26,25");
		reiniciardibujo(nombreCanvas);
		break;
	case 4:
		color = "red";
		shapes = new Array(); // diente ausente
		abr_dibujo += "_dienteausente";
		shapes.push("28,27,2,1,1,1,0,2,26,28,29,30");
		shapes.push("31,0,1,30,0,30,0,28,28,0,30,0");
		reiniciardibujo(nombreCanvas);
		break;

	case 5:
		shapes = new Array(); // desmineralizacion
		color = "red";
		abr_dibujo += "_desmineralizacion";
		shapes.push("19,29,16,29,16,1,19,1");
		shapes.push("10,1,13,1,13,29,10,29");
		reiniciardibujo(nombreCanvas);
		break;
	case 6:
		color = "blue";
		abr_dibujo += "_obturacion";
		break;
	case 7: // restaurar diente
		reiniciardibujo(nombreCanvas);
		refrescarDiente(nombreCanvas);
		break;
	}
	if ((opcion < 7)&&(opcion != 2)) {
		array = abr_dibujo.split("_");
		guardarArreglo(nombreCanvas, abr_dibujo, shapes, color,"_"+array[1]);
		refrescarDiente(nombreCanvas);
		dibujar(nombreCanvas);
	}

	visualizarPintadoDiente(nombreCanvas);

}

function refrescarDiente(nombreCanvas) { // restaurar dibujo del diente
	var canvas = document.getElementById(nombreCanvas);
	canvas.width = canvas.width;
	if (canvas.getContext) {
		var ctx = canvas.getContext('2d');
		var img = new Image();
		img.src = 'Imagenes/Diente_Modelo.png';
		canvas.width = 30;
		canvas.height = 30;
		ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
	} else {
		alert('You need Safari or Firefox 1.5+ to see this demo.');
	}
}

function reiniciardibujo(nombrecanvas) {// reiniciar los arreglos de los dibujos realizados en el odontograma 

	for ( var i = 0; i < ArreglonombreDibujos.length; i++) {
		var nombre = ArreglonombreDibujos[i].split("_");
		var nombre_parametrizado = nombre[0] + "_" + nombre[1];
		if (nombre_parametrizado == nombrecanvas) {
			ArreglonombreDibujos[i] = "";
			ArregloDibujos[i] = "";
		}
	}
	ArreglonombreDibujos = cleanArray(ArreglonombreDibujos);
	ArregloDibujos = cleanArray(ArregloDibujos);
}

function cleanArray(actual) { // limpiar espacios vacios en un arreglo
	var newArray = new Array();
	for ( var i = 0, j = actual.length; i < j; i++) {
		if (actual[i]) {
			newArray.push(actual[i]);
		}
	}
	return newArray;
}

function areasApintar(nombrecanvas) { //areas del diente que se tienen que pintar
	var array = new Array();
	for ( var i = 0; i < ArreglonombreDibujos.length; i++) {
		var nombre = ArreglonombreDibujos[i].split("_");
		var nombre_parametrizado = nombre[0] + "_" + nombre[1];
		if (nombre_parametrizado == nombrecanvas) {
			array.push(i);
		}
	}
	return array;
}

function dibujar(nombrecanvas) {// dibujar la generalidad sobre el dibujo del diente en la coordenada seleccionada
	var ctx = document.getElementById(nombrecanvas).getContext('2d');
	var arraypintar = areasApintar(nombrecanvas);
	// alert(arraypintar);

	for ( var i = 0; i < arraypintar.length; i++) {
		// var arreglo = ArregloDibujos[arraypintar[i]];
		// var cadena = arreglo.toString();
		// if (cadena != null) {
		// var arreglo2 = cadena.split(",");

		var arreglo2 = ArregloDibujos[arraypintar[i]].toString().split(",");
		//alert(arreglo2[0]);
		ctx.fillStyle = arreglo2[0];
		ctx.beginPath();
		ctx.moveTo(arreglo2[1], arreglo2[2]);
		for ( var item = 3; item < arreglo2.length - 1; item += 2) {
			ctx.lineTo(arreglo2[item], arreglo2[item + 1]);
		}
		ctx.closePath();
		ctx.fill();
		// }

	}
}


function eliminateDuplicates(arr) {// eliminar valores duplicados de un arreglo
	 var i,
	     len=arr.length,
	     out=[],
	     obj={};

	 for (i=0;i<len;i++) {
	    obj[arr[i]]=0;
	 }
	 for (i in obj) {
	    out.push(i);
	 }
	 return out;
}

function visualizarPintadoDiente(nombreCanvas){// funcion para la obtencion de las generalidades descritas en el odontograma para su envio a la base de datos
	var ArregloEspecificoPorDiente = new Array();
    var Arreglosinrepetidos = new Array();
	for (var i =0 ; i < ArreglonombreDibujos.length ; i++ ){
       	if(ArreglonombreDibujos[i].search(nombreCanvas)!= -1){
       		ArregloEspecificoPorDiente.push(ArreglonombreDibujos[i]);
       	}
	}
	
	Arreglosinrepetidos = eliminateDuplicates(ArregloEspecificoPorDiente);
	
	/*var cadenaGeneralidadespordiente="";
	for (var i =0 ; i < ArregloEspecificoPorDiente.length ; i++ ){
		cadenaGeneralidadespordiente +=  ArregloEspecificoPorDiente[i]+"-";

	}*/

	
	var cadenaGeneralidadespordiente="";
	for (var i =0 ; i < Arreglosinrepetidos.length ; i++ ){
		cadenaGeneralidadespordiente += Arreglosinrepetidos[i]+"-";

	}
	if(cadenaGeneralidadespordiente == ""){
		cadenaGeneralidadespordiente= "normal-";
	}
	
	
	var indicediente = obtenerIndexNombreArray(nombreCanvas);
	

if(informeviejo==0){
	GeneralidadesDientesDATA[indicediente] = cadenaGeneralidadespordiente.substring(0, cadenaGeneralidadespordiente.length-1);
}
		
		
		//alert(GeneralidadesDientesDATA[indicediente]);

	//alert(nombreCanvas+"  ["+cadenaGeneralidadespordiente.substring(0, cadenaGeneralidadespordiente.length-1)+"]");
	
	
}


function sectorSeleccionadoCanvas(x, y) {// numero del sector seleccionado en el diente 
	var sw1 = 0; // sector superior izquierdo del diente
	var sw3 = 0; // sector superior derecho del diente
	var sw7 = 0; // sector inferior izquierdo del diente
	var sw9 = 0; // sector inferior derecho del diente
	var i = 0;
	var j = 0;
	if (y < 10) {
		if (x < 10) {
			for (i = 0; i < 10; i++) {
				for (j = i; j < 10; j++) {
					if ((x == i) && (y == j)) {
						sw1 = 1;
					}
				}
			}
			if (sw1 == 0) {
				return 2;
			} else {
				return 1;

			}
		} else if (x < 20) {
			return 2;
		} else {
			var valor = 10;
			for (i = 20; i <= 30; i++) {
				for (j = valor; j >= 0; j--) {
					if ((x == i) && (y == j)) {
						sw3 = 1;
					}
				}
				valor--;
			}

			if (sw3 == 0) {
				return 3;
			} else {
				return 2;
			}
		}
	} else if (y < 20) {
		if (x < 10) {
			return 1;
		} else if (x < 20) {
			return 5;
		} else {
			return 3;
		}
	} else {

		if (x < 10) {
			var valor = 30;
			for (i = 0; i < 10; i++) {
				for (j = valor; j >= 20; j--) {
					if ((x == i) && (y == j)) {
						sw7 = 1;
					}
				}
				valor--;
			}
			if (sw7 == 0) {
				return 4;
			} else {
				return 1;
			}
		} else if (x < 20) {
			return 4;

		} else {
			for (i = 20; i <= 30; i++) {
				for (j = i; j <= 30; j++) {
					if ((x == i) && (y == j)) {
						sw9 = 1;
					}
				}
			}
			if (sw9 == 0) {
				return 3;
			} else {
				return 4;
			}
		}
	}
}


function ActualizarDataOdontologia(){// envio de datos obtendos del modulo mediante ajax
	
	var observacionesDATA = "";
	var evolucionDATA="";
	var plantratamientoDATA="";
	var motivoDATA="";
    var arrayAntecedentesDATA = new Array();
    var arrayExamenClinicoDATA = new Array();
	


	
	
	
	
	
  //  if(opcion =="3" ){
    	var dientesamodificar = new Array();
    	var generalidadesdientesamodificar = new Array();
    	for(var i=0; i< GeneralidadesDientesDATA.length;i++){
    		
    		if(GeneralidadesDientesDATA[i]!='sin_cambios'){
    			dientesamodificar.push(arraynombretecnicoDientes[i] );
    			generalidadesdientesamodificar.push(GeneralidadesDientesDATA[i]);
    	//		alert(GeneralidadesDientesDATA);
    			
    			//cambios += "Diente No."+ arraynombretecnicoDientes[i] +" ["+GeneralidadesDientesDATA[i]+"]"+"\n";
    			
    		}
    		
    		
    		
    		
    	}
    	pagina="odont_visor_cambios_odontograma.jsp?dientesamodificar="+dientesamodificar+"&generalidadesamodificar="+generalidadesdientesamodificar;			
   	   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
   	 	opciones =opciones+"scrollbars=si, resizable=yes, width=400, height=400, top=85, left=140";
   	    var ventanaemergente= window.open(pagina,"Cambios_Realizados",opciones);

    	
    	
   
   	   	
   	 if(confirm('Desea Guardar los Cambios Realizados')){
   		ventanaemergente.close() ;
   	// revicion de campos vacios
   	    
   		if(tamanoinfotextbox('evolucion')>0){
   			evolucionDATA = recopilarinfotextbox('evolucion');
   		if(tamanoinfotextbox('motivo_consulta')>0){
   			motivoDATA=recopilarinfotextbox('motivo_consulta');
   			
   		if(tamanoinfotextbox('plan_tratamiento')>0){
   			plantratamientoDATA = recopilarinfotextbox('plan_tratamiento');
   			if(tamanoinfotextbox('observaciones')>0){
   				observacionesDATA=recopilarinfotextbox('observaciones');	
   			}else{
   				observacionesDATA = "ninguna";
   			}
   		    for (var x = 0; x< arrayNombresRadiosAntecedentes.length ; x++ ){
   	        	arrayAntecedentesDATA.push(recopilarinfoRadios(arrayNombresRadiosAntecedentes[x]));
   	        }
   	        for (var y = 0; y< arrayNombresRadiosExamenClinico.length ; y++ ){
   	           	arrayExamenClinicoDATA.push(recopilarinfoRadios(arrayNombresRadiosExamenClinico[y]));
   	        }
   	        var ImagenesOdontogramaDATA = "";
   	       // var imagen1 = ObtenerCanvasDecodificado64(ArreglonombresDientes[0]);
   	        for (var z=0 ; z < ArreglonombresDientes.length ; z++){
   	        	ImagenesOdontogramaDATA +=ObtenerCanvasDecodificado64(ArreglonombresDientes[z])+"@";
   	        }

   	        ImagenesOdontogramaDATA = ImagenesOdontogramaDATA.substring(0, ImagenesOdontogramaDATA.length-1);
   	    	desactivarelementoFormulario('actualizar');
   	        ajax = getXMLObject();
   			ajax.open("POST", "modificacionRegistro", true);
   			ajax.onreadystatechange = function() {
   				if (ajax.readyState == 4) {
   					var respuesta = ajax.responseText.split("|");
   			        if  (respuesta[0]=='1'){
   							alert(respuesta[1]);
   							mostrarInformeOdontologia(respuesta[2],respuesta[3]);
   			        	
   			        }else{
   			        	alert(respuesta[1]);
   			        }
   			        
   					   var minavegador = new Browser();
   					    if (minavegador.name == 'firefox'){
   					    	 window.open('','_parent','');
   					    }
   					    window.close();
   				}

   			};
   		    ajax.setRequestHeader('Content-Type',
   					'application/x-www-form-urlencoded;charset=utf-8');
   		  
   				ajax.send(
   		     	// Datos Odontograma
   				//"codigopaciente="+recopilarinfotextbox('txtUsuario')+	
   				//"&codigoodontologo="+recopilarinfotextbox('txtUsuario')+
   				"observaciones="+ encodeURIComponent(observacionesDATA)+
   				"&evolucion="+encodeURIComponent(evolucionDATA)+
   				"&motivo="+encodeURIComponent(motivoDATA)+
   	     		"&plandetratamiento="+ encodeURIComponent(plantratamientoDATA)+
   				"&arrayAntecedentes="+arrayAntecedentesDATA+
   			    "&arrayExamenClinico="+arrayExamenClinicoDATA+
   			    "&arrayImagenesDientes="+encodeURIComponent(ImagenesOdontogramaDATA)+
   	            "&arraynombresdientes="+ArreglonombresDientes+
   				"&generalidades="+GeneralidadesDientesDATA+
   				"&CodHistoria="+ recopilarinfotextbox("txtcodHistoria")+
   				"&CodTratamiento="+recopilarinfotextbox("txtcodTratamiento")+
   				"&valor="+opcion
   				
   				);
   		
   		}else{
   			
   			
   		}
   		
   		}else{
   			alert('por favor llene el campo motivo de la consulta');
   		}
   		}else{
   			alert('por favor llene el campo evolucion');
   		}
   		
   		
   	 }
   	 else{
   		 
   		ventanaemergente.close() ;
   	 }
   		
   	   	
	
	
}

 function mostrarInformeOdontologia(codTratamiento, codHistoria){//funcion para desplegar el informe generado
	    pagina="odont_reporteOdontologia.jsp?CodTratamientoOdontologico="+codTratamiento+"&CodHistoriaOdontologica="+codHistoria;			
	   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85, left=140";
	   	window.open(pagina,"Informe_Odontologia",opciones);
	 
	 
 }
 
 


  function recopilarinfoRadios(grupoRadios) { 
	  var Cadena = "";
	  for (var i = 0; ele = document.getElementsByName(grupoRadios)[i]; i++) 
		  if (ele.checked == true) { 
			  Cadena = ele.value; 
	      } 
	 return Cadena;
 }

  function reiniciarRadiosButtons(grupoRadios, valor){ // reiniciar todos los radio buttons con su 

	  var radList = document.getElementsByName(grupoRadios);
	  for (var i = 0; i < radList.length; i++) {
	  if(radList[i].value == valor){
		  radList[i].checked = true;  
      }
	  }
		 
  }
  

  function cargarnombresradios(){//cargar nombres de cada radio button de la vista
	  
	   arrayNombresRadiosAntecedentes = new Array();
	   arrayNombresRadiosAntecedentes.push("Alergias");   
	   arrayNombresRadiosAntecedentes.push("Hemorragias");
	   arrayNombresRadiosAntecedentes.push("Enfermedades_Respiratorias");
	   arrayNombresRadiosAntecedentes.push("Cardiopatias");
	   arrayNombresRadiosAntecedentes.push("Fiebre_Reumatica");
	   arrayNombresRadiosAntecedentes.push("Enfermedades_Renales");
	   arrayNombresRadiosAntecedentes.push("Hepatitis");
	   arrayNombresRadiosAntecedentes.push("Transtornos_Gastricos");
	   arrayNombresRadiosAntecedentes.push("Tension_Arterial");
	   arrayNombresRadiosAntecedentes.push("Diabetes");
	   arrayNombresRadiosAntecedentes.push("Ingesta_Medicamentos");
	   arrayNombresRadiosAntecedentes.push("Cirugias");
	   arrayNombresRadiosAntecedentes.push("Aparatologia");
	   arrayNombresRadiosAntecedentes.push("HIV");
	   arrayNombresRadiosAntecedentes.push("Extracciones_Dentales");
	   arrayNombresRadiosAntecedentes.push("Enfermedades_Orales");
	   arrayNombresRadiosAntecedentes.push("Antecedentes_Familiares");
	   arrayNombresRadiosAntecedentes.push("Otros");
	
	
	   arrayNombresRadiosExamenClinico = new Array();
	   arrayNombresRadiosExamenClinico.push("Lengua");
	   arrayNombresRadiosExamenClinico.push("Carrillos");
	   arrayNombresRadiosExamenClinico.push("Abrasion");
	   arrayNombresRadiosExamenClinico.push("Atricion");
	   arrayNombresRadiosExamenClinico.push("Hipoplasias");
	   arrayNombresRadiosExamenClinico.push("Gingivitis_Marginal");
	   arrayNombresRadiosExamenClinico.push("Gingivitis_Difusa");
	   arrayNombresRadiosExamenClinico.push("Micrognasia");
	   arrayNombresRadiosExamenClinico.push("Macrognasia");
	   arrayNombresRadiosExamenClinico.push("Desviacion");
	   arrayNombresRadiosExamenClinico.push("Mal_Posicion");
	   arrayNombresRadiosExamenClinico.push("ATM");
	   arrayNombresRadiosExamenClinico.push("Habitos_Orales");
	   arrayNombresRadiosExamenClinico.push("Paladar_Blando");
	   arrayNombresRadiosExamenClinico.push("Piso_De_Boca");
	   arrayNombresRadiosExamenClinico.push("Supernumerarios");
	   arrayNombresRadiosExamenClinico.push("Hipodoncia");
	   arrayNombresRadiosExamenClinico.push("Fracturas");
	   arrayNombresRadiosExamenClinico.push("Periodontitis");
	   arrayNombresRadiosExamenClinico.push("Retracciones");
	   arrayNombresRadiosExamenClinico.push("Torus");
	   arrayNombresRadiosExamenClinico.push("Sobremordida_Vertical");
	   arrayNombresRadiosExamenClinico.push("Sobremordida_Horizontal");
	   arrayNombresRadiosExamenClinico.push("Otros2");
  
	  
	  
  }
  
  function aparecerElemento(id) {
		document.getElementById(id).style.display = "inline";
  }

  function desaparecerElemento(id) {
		 document.getElementById(id).style.display = "none";
  }

  function recopilarinfotextbox(idText) {
		return document.getElementById(idText).value;
  }

  function tamanoinfotextbox(idText) {
		return document.getElementById(idText).value.length;
  }
  
  function reemplazarinfotextbox(idText, valor) {
		document.getElementById(idText).value = valor;
  }
  
  function ObtenerCanvasDecodificado64(nombreCanvas) {// funcion para decoficar los dibujos del odontograma 
		var minavegador =  new Browser();
	    var propiedad = true; 
	 //   alert('entra '+minavegador.name +' '+minavegador.version);
	    if (minavegador.name =='iexplorer'){
			if(minavegador.version =='6'){
				propiedad = false;
			}
	        if(minavegador.version =='7'){
	        	propiedad= false;
			}
	        if(minavegador.version =='8'){
	           	propiedad= false;
	        }
		}
	
	if (propiedad== true){
		var canvas = document.getElementById(nombreCanvas);
	    if (canvas.getContext) {
	    	  var dataURL = canvas.toDataURL("image/jpg");
	    	// var dataURL = canvas.toDataURL();
	        // dataURL = dataURL.replace(/^data:image\/(png|jpeg);base64,/, "") ;    
   		     dataURL = dataURL.replace(/^data:image\/(png|jpeg);base64,/, "") ;    
    	     return dataURL;            // Get the data as an image.
	    }
	}
		else{
			//alert("tu navegador no puede guardar la imagen "+nombreCanvas+" por favor actualice su navegador");
			return "";
		}

	}

  
  function limpiarFormulariosOdontologia(){//funcion limpiar formularios
	  reemplazarinfotextbox("evolucion", "");
	  reemplazarinfotextbox("motivo_consulta", "");
	  reemplazarinfotextbox("observaciones", "");
	  reemplazarinfotextbox("plan_tratamiento", "");
	  reemplazarinfotextbox("txtnumdoc", "");
	  activarelementoFormulario("cbafiliacion");
      activarelementoFormulario("txtnumdoc");
      activarelementoFormulario("btnBuscarPac");
  	  activarelementoFormulario('enviar');
	  activarelementoFormulario('anular');
	  activarelementoFormulario("terminartratamiento");
	  for(var x = 0 ; x < arrayNombresRadiosAntecedentes.length ; x++){
		  reiniciarRadiosButtons(arrayNombresRadiosAntecedentes[x], "0");
	  }
      for(var y = 0 ; y< arrayNombresRadiosExamenClinico.length ; y++){
    	  reiniciarRadiosButtons(arrayNombresRadiosExamenClinico[y], '0');
	  }

  }
  function activarelementoFormulario(idCombo) {
	
		document.getElementById(idCombo).disabled = false;
	}

	function desactivarelementoFormulario(idCombo) {

		document.getElementById(idCombo).disabled = true;
	}

/*  function anularDataOdontologia(){ // funcion anular informe odontologia actual

			if (confirm("Desea Anular el informe Actual?")) {
				// Respuesta afirmativa...
			
			    var minavegador = new Browser();
			    if (minavegador.name == 'firefox'){
			    	limpiarFormulariosOdontologia();
			    }
				window.location.reload();
				document.getElementById('txtnumdoc').focus();
			}
  }

function terminarTratamiento(){
	
	estadoTratamiento = 0;
	desactivarelementoFormulario("terminartratamiento");
}*/
