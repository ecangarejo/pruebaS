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


///////////////////////////////////////////////////////////////////////////////////////

function Rips(){

var contenidos=document.getElementById('form1');	
ajax=getXMLObject();
ajax.open("POST","ControlRips",true); //getname will be the servlet name
ajax.onreadystatechange  = function(){
if (ajax.readyState == 4) {	
contenidos.innerHTML = ajax.responseText	
//alert(ajax.responseText);
//document.getElementById("ResultadosBusqueda").innerHTML=ajax.responseText;
}
}
ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
ajax.send("valor=1A"); //Posting txtname to Servle	
}


function Ruta(){
//	alert(document.getElementById('fichero').value);
	var filename = "c:\\ZZ";

	 var fso = new ActiveXObject("Scripting.FileSystemObject");
	 var i = -1;

	 if (fso.FolderExists(filename))
	 {
		 alert("La carpeta ya existe");
	 }else{
		 var newFolderName = fso.CreateFolder(filename);
		 var newpath = fso.CreateTextFile("c:\\ZZ\\zzzzz.txt"); 
		 var arch = fso.CreateTextFile("c:\\ZZ\\yyyy.txt"); 
		 arch.WriteLine("Esta linea se escribe dentro de mi nuevo archivo.");
		 arch.WriteLine("Otraaaaa.");
	
	 }
	 
	 
	// return i;


	//WScript.echo(GetLineCount(Filename));
	
	//try {
//	var fso = new ActiveXObject("Scripting.FileSystemObject");
	// } catch (e) {
	//	 alert("Esta aaaa");
	//   }
//		   var newpath = fso.BuildPath("c:\\tmp", "kuku.txt");
//	alert(newpath);
//	 newpath = fso.CopyFile("C:\\test.txt", "C:\\yehuda\\test.txt");
	
	//Set fs = CreateObject("Scripting.FileSystemObject") ;
/*	var object = new  getXMLObject(); 
	nction createFile(){
	//	var object = new ActiveXObject("Scripting.FileSystemObject");
		var file = object.CreateTextFile("C:\\Hello.txt", false);
		file.WriteLine('Hello World');
		file.WriteLine('Hope is a thing with feathers, that perches on the soul.'); 
		file.Close();
		}
	*/
	
	
/*
	   
	var contenidos=document.getElementById('ruta');	
	ajax=getXMLObject();
	ajax.open("POST","ControlRips",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			contenidos.innerHTML = ajax.responseText	
			//alert(ajax.responseText);
			//document.getElementById("ResultadosBusqueda").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2"); //Posting txtname to Servle	*/
}

function BuscarFA(){
	var eps=document.getElementById("cmbE").value;
	var fi=document.getElementById("FI").value;
	var ff=document.getElementById("FF").value;
	//alert("Buscando: "+eps+" - "+fi+" - "+ff);
	
	var contenidos=document.getElementById('DFD');
		
	if((fi=="")||(ff=="")){
		alert("Debe seleccionar un rango de busqueda");
	}else{
	
	ajax=getXMLObject();
	ajax.open("POST","ControlRips",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			contenidos.innerHTML = ajax.responseText	
			//alert(ajax.responseText);
			//document.getElementById("ResultadosBusqueda").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3A&fi="+fi+"&eps="+eps+"&ff="+ff); //Posting txtname to Servle	
}
}


function MoverS(){
	
	var fd=document.getElementById("FD");
	var fs=document.getElementById("FS");
	
	var c=0;
	var x=0;
	//alert(fd.length);
	if(fs.length==0){
		
	for(var i=0; i<fd.length; i++){
		
		//alert(fs.length+" - "+c+" - "+x);
		
		if(fd[i].selected){
			x++;
		}
		
		if((fd[i].selected)&&(c==0)){

			var options=document.createElement('option');
			options.value=fd[i].value;
			options.text=fd[i].text;
			document.getElementById("FS").add(options);
			fd[i]=null;
			i--;
			c++;
		}
		
		
		
	}
	
	}else{
		alert("Ya hay una factura seleccionada para generar RIPS");
	}
	//alert("x: "+x);

	if(x>1){alert("Solo se permite generar rips de una factura a la vez");}

}


function MoverD(){
	var fd=document.getElementById("FD");
	var fs=document.getElementById("FS");
	
	for(var i=0; i<fs.length; i++){
		if(fs[i].selected){
			var options=document.createElement('option');
			options.value=fs[i].value;
			options.text=fs[i].text;
			document.getElementById("FD").add(options);
			fs[i]=null;
			i--;
		}
	}
	
	
}



/*function Exportar(){
	//alert("de");
	var rut=document.getElementById("ruta").value;
	var nom=document.getElementById("txtNombre").value;
	var fi=document.getElementById("FI").value;
	var ff=document.getElementById("FF").value;
	var fs=document.getElementById("FS");
	var CodEnt=document.getElementById("cmbE").value;
	//alert(rut+" - "+nom);
	//alert(fs.length);
	
	if(rut==""){alert("Debe seleccionar la unidad de destino");}
	else{
		if(nom==""){alert("Debe seleccionar el Nombre del archivo destino");}
		else{
			if(fs.length==0){alert("Seleccione las Facturas de las cuales desea Generar los RIPS");}
			else{
				var datos="";
				var fsl=fs.length;
				for(var i=0; i<fsl; i++){
				datos=datos+fs.options[i].text;
				datos=datos+"|";
				}
				//alert("Facturas:  "+datos);
				//var contenidos=document.getElementById('ruta');	
				ajax=getXMLObject();
				ajax.open("POST","ControlRips",true); //getname will be the servlet name
				ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {	
						//contenidos.innerHTML = ajax.responseText	
						//alert(ajax.responseText);
						//document.getElementById("ResultadosBusqueda").innerHTML=ajax.responseText;
					}
				}
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt); //Posting txtname to Servle
			}
		}
	}	
		
	
}*/

function ExportarA(){
	
	var rut=document.getElementById("ruta").value;
	var nom=document.getElementById("txtNombre").value;
	var fi=document.getElementById("FI").value;
	var ff=document.getElementById("FF").value;
	var fs=document.getElementById("FS");
	var CodEnt=document.getElementById("cmbE").value;
	
	if(rut==""){alert("Debe seleccionar la unidad de destino");}
	else{
		if(nom==""){alert("Debe seleccionar el Nombre del archivo destino");}
		else{
			if(fs.length==0){alert("Seleccione las Facturas de las cuales desea Generar los RIPS");}
			else{
				var datos=fs.options[0].text;
				var fsl=fs.length;
					
				var fso = new ActiveXObject("Scripting.FileSystemObject");
				var filename = rut+":\\RIPS";
				if (fso.FolderExists(filename))
				 {
					 alert("La carpeta RIPS ya existe");
				 }else{
					 var newFolderName = fso.CreateFolder(filename);
						// document.getElementById('DFD').innerHTML="cesar";//'<p>Cargando Lista de Facturas... No Cierre La Ventana...</p><img src="Imagenes/ani.gif">';
					 document.getElementById('form1').innerHTML='<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Uno.gif"> 1%';
							
					 
					 /*** Archivo de Consultas***/	
				 ajax=getXMLObject();
				 var ctac=0;
						ajax.open("POST","ControlRips",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								var valida=ajax.responseText;
								if (valida!=""){
								 var arch = fso.CreateTextFile(rut+":\\RIPS\\AC"+nom+".txt"); 
								 arch.WriteLine(ajax.responseText);
								 arch.Close();	
								 str = valida.split('\r\n');
								 ctac=str.length;
								}
				
								AF(rut,nom,fi,ff,fsl,datos,CodEnt,ctac);
								}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					 	ajax.send("valor=5A&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&ARA=1"); //Posting txtname to Servle
				 
					 	
				 
				 }//fin de la creacion del folder
				
			}
		}
	}
	
}



function AF(rut,nom,fi,ff,fsl,datos,CodEnt,ctac){
	//alert("esto es ctac en af: "+ctac);		
	var fsoaf = new ActiveXObject("Scripting.FileSystemObject");
					   /*** Archivo de Descripcion Agrupada***/
					    
					    /*** Archivo de Transacciones***/
	 document.getElementById('form1').innerHTML='<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Diez.gif"> 10%';
		
						var ctaf=0;
						ajax=getXMLObject();
	 					ajax.open("POST","ControlRips",true); //getname will be the servlet name
					    ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								// var newFolderName = fso.CreateFolder(filename);
								var valida=ajax.responseText;
								if (valida!=""){
								 var archaf = fsoaf.CreateTextFile(rut+":\\RIPS\\AF"+nom+".txt"); 
								 archaf.WriteLine(ajax.responseText);
								 archaf.Close();
								 str = valida.split('\r\n');
								 ctaf=str.length;
								}
								
								//var cantidad = valida.match(/\n+/g); 
								//var ctaf = cantidad?cantidad.length:0;
								
								
								
								 AM(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf);
							 									
								}
						}					    
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=3"); //Posting txtname to Servle			

}//fin de la creacion del folder
	

function AM(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf){
	 document.getElementById('form1').innerHTML='<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Veinte.gif"> 20%';
		
	var fso = new ActiveXObject("Scripting.FileSystemObject");
					     /*** Archivo de Medicamentos***/
						ajax=getXMLObject();
						 var ctam=0;
						ajax.open("POST","ControlRips",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								var valida=ajax.responseText;
								if (valida!=""){
								 var arch = fso.CreateTextFile(rut+":\\RIPS\\AM"+nom+".txt"); 
								 arch.WriteLine(ajax.responseText);
								// alert(ajax.responseText);
								 arch.Close();
								 str = valida.split('\r\n');
								 ctam=str.length;
								}
								
								//var cantidad = valida.match(/\n+/g); 
								//var ctam = cantidad?cantidad.length:0;
								
								
								 US(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam);	
								 	
								}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=4"); //Posting txtname to Servle

}//fin de la creacion del folder
	

function US(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam){
	/*** Archivo de Usuarios***/
	 document.getElementById('form1').innerHTML='<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Treinta.gif"> 30%';
		
	var fso = new ActiveXObject("Scripting.FileSystemObject");
						/*** Archivo de Otros Servicios***/	
						ajax=getXMLObject();
						var ctus=0;
						ajax.open("POST","ControlRips",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								var valida=ajax.responseText;
								if (valida!=""){
								 var arch = fso.CreateTextFile(rut+":\\RIPS\\US"+nom+".txt"); 
								 arch.WriteLine(ajax.responseText);
								 arch.Close();
								// alert(ajax.responseText);
								 str = valida.split('\r\n');
								 ctus=str.length;
								}
								
								//var cantidad = valida.match(/\n+/g); 
								//var ctus = cantidad?cantidad.length:0;
								
								
								
								 AT(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus);	
								// alert("RIPS Generados Exitosamente!!!");
								// AM(rut,nom,fi,ff,fsl,datos,CodEnt);
								
								}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=10"); //Posting txtname to Servle
			
}//fin de la creacion del folder



function AT(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus){
	 document.getElementById('form1').innerHTML='<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Cuarenta.gif"> 40%';
		
	var fso = new ActiveXObject("Scripting.FileSystemObject");
						/*** Archivo de Otros Servicios***/	
						ajax=getXMLObject();
						var ctat=0;
						ajax.open("POST","ControlRips",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								var valida=ajax.responseText;
								if (valida!=""){
								 var arch = fso.CreateTextFile(rut+":\\RIPS\\AT"+nom+".txt"); 
								 arch.WriteLine(ajax.responseText);
								 arch.Close();
								//  alert(ajax.responseText);
								  str = valida.split('\r\n');
								  ctat=str.length;
								}
							/*	alert("en at: "+valida);
								var cantidad = valida.match(/\n+/g); 
								alert("cantidad at: "+cantidad);
								var ctat = cantidad?cantidad.length:0;
								alert("ctat en at: "+ctat);
								*/
								
								 						
								 AU(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat);
								}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=8"); //Posting txtname to Servle

}//fin de la creacion del folder



function AU(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat){
	 document.getElementById('form1').innerHTML='<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Cincuenta.gif"> 50%';
		
					/*** Archivo de Urgencias***/
	var fso = new ActiveXObject("Scripting.FileSystemObject");
						/*** Archivo de Otros Servicios***/	
						ajax=getXMLObject();
						var ctau=0;
						ajax.open("POST","ControlRips",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								var valida=ajax.responseText;
								if (valida!=""){
								 var arch = fso.CreateTextFile(rut+":\\RIPS\\AU"+nom+".txt"); 
								 arch.WriteLine(ajax.responseText);
								 arch.Close();
								 str = valida.split('\r\n');
								 ctau=str.length;
								}
								//var cantidad = valida.match(/\n+/g); 
								//var ctau = cantidad?cantidad.length:0;
								
								
								//  alert(ajax.responseText);
								 AP(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau);
								
								}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=9"); //Posting txtname to Servle

}//fin de la creacion del folder



function AP(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau){
	 document.getElementById('form1').innerHTML='<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Sesenta.gif"> 60%';
		
	var fso = new ActiveXObject("Scripting.FileSystemObject");
						/*** Archivo de Procedimientos***/
						ajax=getXMLObject();
						var ctap=0;
						ajax.open("POST","ControlRips",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								
								var valida=ajax.responseText;
								if (valida!=""){
								 var arch = fso.CreateTextFile(rut+":\\RIPS\\AP"+nom+".txt"); 
								 arch.WriteLine(ajax.responseText);
								 arch.Close();
								 str = valida.split('\r\n');
								 ctap=str.length;
								}
								//var cantidad = valida.match(/\n+/g); 
								//var ctap = cantidad?cantidad.length:0;
								
								 
	  
								 RN(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau,ctap);
								 
								}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=7"); //Posting txtname to Servle

}//fin de la creacion del folder



function RN(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau,ctap){
	 document.getElementById('form1').innerHTML='<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Setenta.gif"> 70%';
		
	var fso = new ActiveXObject("Scripting.FileSystemObject");
						/*** Archivo de Recien Nacidos***/
						ajax=getXMLObject();
						var ctrn=0;
						ajax.open("POST","ControlRips",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								var valida=ajax.responseText;
								if (valida!=""){
								 var arch = fso.CreateTextFile(rut+":\\RIPS\\RN"+nom+".txt"); 
								 arch.WriteLine(ajax.responseText);
								 arch.Close();
								 str = valida.split('\r\n');
								 ctrn=str.length;
								}
								//var cantidad = valida.match(/\n+/g); 
								//var ctrn = cantidad?cantidad.length:0;
								
								
								 AH(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau,ctap,ctrn);	
								
								}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=6"); //Posting txtname to Servle

}//fin de la creacion del folder




function AH(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau,ctap,ctrn){
	 document.getElementById('form1').innerHTML='<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Ochenta.gif"> 80%';
		
	var fso = new ActiveXObject("Scripting.FileSystemObject");
						/*** Archivo de Hospitalizacion***/
						ajax=getXMLObject();
						var ctah=0;
						ajax.open("POST","ControlRips",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								var valida=ajax.responseText;
								if (valida!=""){
								 var arch = fso.CreateTextFile(rut+":\\RIPS\\AH"+nom+".txt"); 
								 arch.WriteLine(ajax.responseText);
								 arch.Close();
								 str = valida.split('\r\n');
								 ctah=str.length;
								}
								//var cantidad = valida.match(/\n+/g); 
								//var ctah = cantidad?cantidad.length:0;
								
								 //alert("Rips Exportados Satisfactoriamente!!!");
								 CT(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau,ctap,ctrn,ctah);
								 	
								}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=5"); //Posting txtname to Servle

}//fin de la creacion del folder




//SOLO NOS FALTA CREAR EL CT Y CORONAMOS
function CT(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau,ctap,ctrn,ctah){
	 document.getElementById('form1').innerHTML='<p>Generando Archivos RIPS... No Cierre La Ventana...</p><img src="Imagenes/Noventa.gif"> 99%';
		
//	alert(ctac+"-"+ctaf+"-"+ctam+"-"+ctus+"-"+ctat+"-"+ctau+"-"+ctap+"-"+ctrn+"-"+ctah);
							/*** Archivo de Control***/
	var fso = new ActiveXObject("Scripting.FileSystemObject");
	ajax=getXMLObject();
	ajax.open("POST","ControlRips",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		
		if (ajax.readyState == 4) {	
			 var arch = fso.CreateTextFile(rut+":\\RIPS\\CT"+nom+".txt"); 
			 if(ctac!="0"){
			 arch.WriteLine(ajax.responseText+"AC"+nom+","+ctac);
			 }
			 if(ctaf!="0"){
			 arch.WriteLine(ajax.responseText+"AF"+nom+","+ctaf);
			 }
			 if(ctah!="0"){
			 arch.WriteLine(ajax.responseText+"AH"+nom+","+ctah);
			 }
			 if(ctam!="0"){
			 arch.WriteLine(ajax.responseText+"AM"+nom+","+ctam);
			 }
			 if(ctap!="0"){
			 arch.WriteLine(ajax.responseText+"AP"+nom+","+ctap);
			 }
			 if(ctat!="0"){
			 arch.WriteLine(ajax.responseText+"AT"+nom+","+ctat);
			 }
			 if(ctau!="0"){
			 arch.WriteLine(ajax.responseText+"AU"+nom+","+ctau);
			 }
			 if(ctrn!="0"){
			 arch.WriteLine(ajax.responseText+"RN"+nom+","+ctrn);
			 }
			 if(ctus!="0"){
			 arch.WriteLine(ajax.responseText+"US"+nom+","+ctus);
			 }			 
			 arch.Close();

			 alert("Rips Exportados Satisfactoriamente!!!");
			 Rips();
			 //CT(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau,ctap,ctrn,ctah);
			 	
			}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=11"); //Posting txtname to Servle

}//fin de la creacion del folder






function max() {
	var nom=document.getElementById("txtNombre").value;
	largo = nom.length;
	if (largo>6) {
		//alert("El nombre debe ser maximo de seis (6) caracteres!!!");
		var y = nom.substr(0,6);
		document.getElementById("txtNombre").value = y;
	}
}



//funci de la fecha 
var patron = new Array(2,2,4);
function masca(d,pat,nums,dias,mes,annio){
var sep="/";
if(d.valant != d.value){
	val = d.value
	largo = val.length
	ini=val.substring(0,2);
	if((ini>31)||(ini=="00")){
		//alert("Dia No Valido!!!");
		val=d.value="01";
		//d.focus();
	}
	val = val.split(sep);

	val2 = ''
	for(r=0;r<val.length;r++){
		val2 += val[r]
	}
	ini=val2.substring(2,4);
	if((ini=="04")||(ini=="06")||(ini=="09")||(ini=="11")){
		x=val2.substring(0,2);
		if(x=="31"){
			val2="30";
			val2=val2+ini;
		}
	}
	if((ini>12)||(ini=="00")){
		//alert("Mes No Valido!!!");
		mescj=val2.substring(0,2);
		val2=mescj+"01";
		//d.focus();
	}
	
	ano=val2.substring(4,8);
	dia=val2.substring(0,2);
	
	
	
	if(ini=="02"){
		if((dia=="30")||(dia=="31")||(dia=="29")){
		if(ano.length==4){
			if ( ( ano % 100 != 0) && ((ano % 4 == 0) || (ano % 400 == 0))) {
				val2="29";
				val2=val2+ini;
			}else{
				val2="28";
				val2=val2+ini;
			}
			val2=val2+ano;
		}
	  }
	}
/*	///////////////////////////////////////////////////
	//Validar fecha mayor que la fecha actual
		
	if(ano.length==4){
		if (ano==annio) {
			if(ini==mes){
				if((dia==dias)||(dia<dias)){
					alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
				val2='';
				}
			}else{
				if(ini<mes){
					alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
					val2='';
				}
			}
		}
		if (ano<annio) {
			alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
			val2='';
		}
	}
	
	///////////////////////////////////////////////////
*/	

	
	if(nums){
		for(z=0;z<val2.length;z++){
			if(isNaN(val2.charAt(z))){
				letra = new RegExp(val2.charAt(z),"g")
				val2 = val2.replace(letra,"")
				
			}
		}
	}
		
	val = ''
	val3 = new Array()
	for(s=0; s<pat.length; s++){
 		val3[s] = val2.substring(0,pat[s])
 		val2 = val2.substring(pat[s])
	}
	
	for(q=0;q<val3.length; q++){
		if(q ==0){
			val = val3[q]         
		}
		else{
			if(val3[q] != ""){
				val += sep + val3[q]
			}
		}
	}
	d.value = val
	d.valant = val
	}
}



























































/*function getXMLObject() // XML OBJECT
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


///////////////////////////////////////////////////////////////////////////////////////

function Rips(){

var contenidos=document.getElementById('form1');	
ajax=getXMLObject();
ajax.open("POST","ControlRips",true); //getname will be the servlet name
ajax.onreadystatechange  = function(){
if (ajax.readyState == 4) {	
contenidos.innerHTML = ajax.responseText	
//alert(ajax.responseText);
//document.getElementById("ResultadosBusqueda").innerHTML=ajax.responseText;
}
}
ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
ajax.send("valor=1"); //Posting txtname to Servle	
}


function Ruta(){
//	alert(document.getElementById('fichero').value);
	var filename = "c:\\ZZ";

	 var fso = new ActiveXObject("Scripting.FileSystemObject");
	 var i = -1;

	 if (fso.FolderExists(filename))
	 {
		 alert("La carpeta ya existe");
	 }else{
		 var newFolderName = fso.CreateFolder(filename);
		 var newpath = fso.CreateTextFile("c:\\ZZ\\zzzzz.txt"); 
		 var arch = fso.CreateTextFile("c:\\ZZ\\yyyy.txt"); 
		 arch.WriteLine("Esta linea se escribe dentro de mi nuevo archivo.");
		 arch.WriteLine("Otraaaaa.");
	
	 }
	 
	 
	// return i;


	//WScript.echo(GetLineCount(Filename));
	
	//try {
//	var fso = new ActiveXObject("Scripting.FileSystemObject");
	// } catch (e) {
	//	 alert("Esta aaaa");
	//   }
//		   var newpath = fso.BuildPath("c:\\tmp", "kuku.txt");
//	alert(newpath);
//	 newpath = fso.CopyFile("C:\\test.txt", "C:\\yehuda\\test.txt");
	
	//Set fs = CreateObject("Scripting.FileSystemObject") ;
/*	var object = new  getXMLObject(); 
	nction createFile(){
	//	var object = new ActiveXObject("Scripting.FileSystemObject");
		var file = object.CreateTextFile("C:\\Hello.txt", false);
		file.WriteLine('Hello World');
		file.WriteLine('Hope is a thing with feathers, that perches on the soul.'); 
		file.Close();
		}
	*/
	
	
/*
	   
	var contenidos=document.getElementById('ruta');	
	ajax=getXMLObject();
	ajax.open("POST","ControlRips",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			contenidos.innerHTML = ajax.responseText	
			//alert(ajax.responseText);
			//document.getElementById("ResultadosBusqueda").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2"); //Posting txtname to Servle	*//*
}

function BuscarF(){
	var eps=document.getElementById("cmbE").value;
	var fi=document.getElementById("FI").value;
	var ff=document.getElementById("FF").value;
	//alert("Buscando: "+eps+" - "+fi+" - "+ff);
	
	var contenidos=document.getElementById('DFD');
		
	if((fi=="")||(ff=="")){
		alert("Debe seleccionar un rango de busqueda");
	}else{
	
	ajax=getXMLObject();
	ajax.open("POST","ControlRips",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			contenidos.innerHTML = ajax.responseText	
			//alert(ajax.responseText);
			//document.getElementById("ResultadosBusqueda").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3&fi="+fi+"&eps="+eps+"&ff="+ff); //Posting txtname to Servle	
}
}


function MoverS(){
	
	var fd=document.getElementById("FD");
	var fs=document.getElementById("FS");
	
	for(var i=0; i<fd.length; i++){
		if(fd[i].selected){
			var options=document.createElement('option');
			options.value=fd[i].value;
			options.text=fd[i].text;
			document.getElementById("FS").add(options);
			fd[i]=null;
			i--;
		}
	}
}


function MoverD(){
	var fd=document.getElementById("FD");
	var fs=document.getElementById("FS");
	
	for(var i=0; i<fs.length; i++){
		if(fs[i].selected){
			var options=document.createElement('option');
			options.value=fs[i].value;
			options.text=fs[i].text;
			document.getElementById("FD").add(options);
			fs[i]=null;
			i--;
		}
	}
	
	
}



/*function Exportar(){
	//alert("de");
	var rut=document.getElementById("ruta").value;
	var nom=document.getElementById("txtNombre").value;
	var fi=document.getElementById("FI").value;
	var ff=document.getElementById("FF").value;
	var fs=document.getElementById("FS");
	var CodEnt=document.getElementById("cmbE").value;
	//alert(rut+" - "+nom);
	//alert(fs.length);
	
	if(rut==""){alert("Debe seleccionar la unidad de destino");}
	else{
		if(nom==""){alert("Debe seleccionar el Nombre del archivo destino");}
		else{
			if(fs.length==0){alert("Seleccione las Facturas de las cuales desea Generar los RIPS");}
			else{
				var datos="";
				var fsl=fs.length;
				for(var i=0; i<fsl; i++){
				datos=datos+fs.options[i].text;
				datos=datos+"|";
				}
				//alert("Facturas:  "+datos);
				//var contenidos=document.getElementById('ruta');	
				ajax=getXMLObject();
				ajax.open("POST","ControlRips",true); //getname will be the servlet name
				ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {	
						//contenidos.innerHTML = ajax.responseText	
						//alert(ajax.responseText);
						//document.getElementById("ResultadosBusqueda").innerHTML=ajax.responseText;
					}
				}
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt); //Posting txtname to Servle
			}
		}
	}	
		
	
}*//*

function Exportar(){
	
	var rut=document.getElementById("ruta").value;
	var nom=document.getElementById("txtNombre").value;
	var fi=document.getElementById("FI").value;
	var ff=document.getElementById("FF").value;
	var fs=document.getElementById("FS");
	var CodEnt=document.getElementById("cmbE").value;
	
	if(rut==""){alert("Debe seleccionar la unidad de destino");}
	else{
		if(nom==""){alert("Debe seleccionar el Nombre del archivo destino");}
		else{
			if(fs.length==0){alert("Seleccione las Facturas de las cuales desea Generar los RIPS");}
			else{
				var datos="";
				var fsl=fs.length;
				for(var i=0; i<fsl; i++){
				datos=datos+fs.options[i].text;
				datos=datos+"|";
				}				
				
			//	window.location.href="fact_RipsPrueba.jsp?rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt;
			//	ajax.send("valor=5&); //Posting txtname to Servle
				
				var fso = new ActiveXObject("Scripting.FileSystemObject");
				var filename = rut+":\\RIPS";
				if (fso.FolderExists(filename))
				 {
					 alert("La carpeta RIPS ya existe");
				 }else{
					 var newFolderName = fso.CreateFolder(filename);
					 /*** Archivo de Consultas***/	/*
				 ajax=getXMLObject();
				 var ctac=0;
				 
				 document.getElementById('resultadosf').innerHTML='<p>Cargando Lista de Facturas... No Cierre La Ventana...</p><img src="Imagenes/ani.gif">';
					
					 
				 
						ajax.open("POST","ControlRips",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								var valida=ajax.responseText;
								if (valida!=""){
								 var arch = fso.CreateTextFile(rut+":\\RIPS\\AC"+nom+".txt"); 
								 arch.WriteLine(ajax.responseText);
								 arch.Close();	
								 str = valida.split('\r\n');
								 ctac=str.length;
								}

							/*	/////////////pruebaexcel/////
								var ExcelApp = new ActiveXObject("Excel.Application");
								var ExcelSheet = new ActiveXObject("Excel.Sheet");
								ExcelSheet.Application.Visible = true;
								ExcelSheet.ActiveSheet.Cells(1,1).Value = "Columna A Fila 1";
								ExcelSheet.ActiveSheet.Cells(2,1).Value = "Columna A Fila 2";
								ExcelSheet.ActiveSheet.Cells(3,1).Value = "Columna A Fila 3";
								ExcelSheet.ActiveSheet.Cells(4,1).Value = "Columna A Fila 4";
								ExcelSheet.ActiveSheet.Cells(1,2).Value = "Columna B Fila 1";
								ExcelSheet.ActiveSheet.Cells(2,2).Value = "Columna B Fila 2";
								ExcelSheet.ActiveSheet.Cells(3,2).Value = "Columna B Fila 3";
								ExcelSheet.ActiveSheet.Cells(4,2).Value = "Columna B Fila 4";
								ExcelSheet.SaveAs(rut+":\\RIPS\\CESAR.XLS");
								ExcelSheet.Application.Quit();
							 */
								/////////////
								
								// alert("ESTA ES LA CANTIDAD DE LINEAS"+str.length);//if(str.length>4){			
								    	
								    	
							/*	
							//	var cj=	ajax.responseText.length;						
							//	alert(valida);
								
							//	var cantidad = valida.match(/\n+/g); 
							//	alert(cantidad);
							//	var ctac = cantidad?cantidad.length:0;
							//	var nctac="fallo";//=cantidad.length;
							//		alert("esto es ctac: "+ctac+" pero la nueva y mas firme es "+nctac+" pero la ultima cj dice la verdad "+cj);	
													
								AF(rut,nom,fi,ff,fsl,datos,CodEnt,ctac);
								}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					 	ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=1"); //Posting txtname to Servle
				 
					 	
				 
				 }//fin de la creacion del folder
				
			}
		}
	}
	
}



function AF(rut,nom,fi,ff,fsl,datos,CodEnt,ctac){
	//alert("esto es ctac en af: "+ctac);		
	var fsoaf = new ActiveXObject("Scripting.FileSystemObject");
					   /*** Archivo de Descripcion Agrupada***/
					    
					    /*** Archivo de Transacciones***//*
						var ctaf=0;
						ajax=getXMLObject();
	 					ajax.open("POST","ControlRips",true); //getname will be the servlet name
					    ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								// var newFolderName = fso.CreateFolder(filename);
								var valida=ajax.responseText;
								if (valida!=""){
								 var archaf = fsoaf.CreateTextFile(rut+":\\RIPS\\AF"+nom+".txt"); 
								 archaf.WriteLine(ajax.responseText);
								 archaf.Close();
								 str = valida.split('\r\n');
								 ctaf=str.length;
								}
								
								//var cantidad = valida.match(/\n+/g); 
								//var ctaf = cantidad?cantidad.length:0;
								
								
								
								 AM(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf);
							 									
								}
						}					    
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=3"); //Posting txtname to Servle			

}//fin de la creacion del folder
	

function AM(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf){
	
	var fso = new ActiveXObject("Scripting.FileSystemObject");
					     /*** Archivo de Medicamentos***//*
						ajax=getXMLObject();
						 var ctam=0;
						ajax.open("POST","ControlRips",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								var valida=ajax.responseText;
								if (valida!=""){
								 var arch = fso.CreateTextFile(rut+":\\RIPS\\AM"+nom+".txt"); 
								 arch.WriteLine(ajax.responseText);
								// alert(ajax.responseText);
								 arch.Close();
								 str = valida.split('\r\n');
								 ctam=str.length;
								}
								
								//var cantidad = valida.match(/\n+/g); 
								//var ctam = cantidad?cantidad.length:0;
								
								
								 US(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam);	
								 	
								}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=4"); //Posting txtname to Servle

}//fin de la creacion del folder
	

function US(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam){
	/*** Archivo de Usuarios***//*
	var fso = new ActiveXObject("Scripting.FileSystemObject");
						/*** Archivo de Otros Servicios***//*	
						ajax=getXMLObject();
						var ctus=0;
						ajax.open("POST","ControlRips",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								var valida=ajax.responseText;
								if (valida!=""){
								 var arch = fso.CreateTextFile(rut+":\\RIPS\\US"+nom+".txt"); 
								 arch.WriteLine(ajax.responseText);
								 arch.Close();
								// alert(ajax.responseText);
								 str = valida.split('\r\n');
								 ctus=str.length;
								}
								
								//var cantidad = valida.match(/\n+/g); 
								//var ctus = cantidad?cantidad.length:0;
								
								
								
								 AT(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus);	
								// alert("RIPS Generados Exitosamente!!!");
								// AM(rut,nom,fi,ff,fsl,datos,CodEnt);
								
								}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=10"); //Posting txtname to Servle
			
}//fin de la creacion del folder



function AT(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus){
	
	var fso = new ActiveXObject("Scripting.FileSystemObject");
						/*** Archivo de Otros Servicios***/	/*
						ajax=getXMLObject();
						var ctat=0;
						ajax.open("POST","ControlRips",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								var valida=ajax.responseText;
								if (valida!=""){
								 var arch = fso.CreateTextFile(rut+":\\RIPS\\AT"+nom+".txt"); 
								 arch.WriteLine(ajax.responseText);
								 arch.Close();
								//  alert(ajax.responseText);
								  str = valida.split('\r\n');
								  ctat=str.length;
								}
							//	alert("en at: "+valida);
								//var cantidad = valida.match(/\n+/g); 
								//alert("cantidad at: "+cantidad);
							//	var ctat = cantidad?cantidad.length:0;
								//alert("ctat en at: "+ctat);
								
								
								 						
								 AU(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat);
								}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=8"); //Posting txtname to Servle

}//fin de la creacion del folder



function AU(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat){
					/*** Archivo de Urgencias***//*
	var fso = new ActiveXObject("Scripting.FileSystemObject");
						/*** Archivo de Otros Servicios***//*	
						ajax=getXMLObject();
						var ctau=0;
						ajax.open("POST","ControlRips",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								var valida=ajax.responseText;
								if (valida!=""){
								 var arch = fso.CreateTextFile(rut+":\\RIPS\\AU"+nom+".txt"); 
								 arch.WriteLine(ajax.responseText);
								 arch.Close();
								 str = valida.split('\r\n');
								 ctau=str.length;
								}
								//var cantidad = valida.match(/\n+/g); 
								//var ctau = cantidad?cantidad.length:0;
								
								
								//  alert(ajax.responseText);
								 AP(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau);
								
								}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=9"); //Posting txtname to Servle

}//fin de la creacion del folder



function AP(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau){
	
	var fso = new ActiveXObject("Scripting.FileSystemObject");
						/*** Archivo de Procedimientos***//*
						ajax=getXMLObject();
						var ctap=0;
						ajax.open("POST","ControlRips",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								
								var valida=ajax.responseText;
								if (valida!=""){
								 var arch = fso.CreateTextFile(rut+":\\RIPS\\AP"+nom+".txt"); 
								 arch.WriteLine(ajax.responseText);
								 arch.Close();
								 str = valida.split('\r\n');
								 ctap=str.length;
								}
								//var cantidad = valida.match(/\n+/g); 
								//var ctap = cantidad?cantidad.length:0;
								
								 
	  
								 RN(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau,ctap);
								 
								}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=7"); //Posting txtname to Servle

}//fin de la creacion del folder



function RN(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau,ctap){
	
	var fso = new ActiveXObject("Scripting.FileSystemObject");
						/*** Archivo de Recien Nacidos***//*
						ajax=getXMLObject();
						var ctrn=0;
						ajax.open("POST","ControlRips",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								var valida=ajax.responseText;
								if (valida!=""){
								 var arch = fso.CreateTextFile(rut+":\\RIPS\\RN"+nom+".txt"); 
								 arch.WriteLine(ajax.responseText);
								 arch.Close();
								 str = valida.split('\r\n');
								 ctrn=str.length;
								}
								//var cantidad = valida.match(/\n+/g); 
								//var ctrn = cantidad?cantidad.length:0;
								
								
								 AH(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau,ctap,ctrn);	
								
								}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=6"); //Posting txtname to Servle

}//fin de la creacion del folder




function AH(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau,ctap,ctrn){
	
	var fso = new ActiveXObject("Scripting.FileSystemObject");
						/*** Archivo de Hospitalizacion***//*
						ajax=getXMLObject();
						var ctah=0;
						ajax.open("POST","ControlRips",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {	
								var valida=ajax.responseText;
								if (valida!=""){
								 var arch = fso.CreateTextFile(rut+":\\RIPS\\AH"+nom+".txt"); 
								 arch.WriteLine(ajax.responseText);
								 arch.Close();
								 str = valida.split('\r\n');
								 ctah=str.length;
								}
								//var cantidad = valida.match(/\n+/g); 
								//var ctah = cantidad?cantidad.length:0;
								
								 //alert("Rips Exportados Satisfactoriamente!!!");
								 CT(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau,ctap,ctrn,ctah);
								 	
								}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=5"); //Posting txtname to Servle

}//fin de la creacion del folder




//SOLO NOS FALTA CREAR EL CT Y CORONAMOS
function CT(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau,ctap,ctrn,ctah){
//	alert(ctac+"-"+ctaf+"-"+ctam+"-"+ctus+"-"+ctat+"-"+ctau+"-"+ctap+"-"+ctrn+"-"+ctah);
							/*** Archivo de Control***//*
	var fso = new ActiveXObject("Scripting.FileSystemObject");
	ajax=getXMLObject();
	ajax.open("POST","ControlRips",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		
		if (ajax.readyState == 4) {	
			 var arch = fso.CreateTextFile(rut+":\\RIPS\\CT"+nom+".txt"); 
			 if(ctac!="0"){
			 arch.WriteLine(ajax.responseText+"AC"+nom+","+ctac);
			 }
			 if(ctaf!="0"){
			 arch.WriteLine(ajax.responseText+"AF"+nom+","+ctaf);
			 }
			 if(ctah!="0"){
			 arch.WriteLine(ajax.responseText+"AH"+nom+","+ctah);
			 }
			 if(ctam!="0"){
			 arch.WriteLine(ajax.responseText+"AM"+nom+","+ctam);
			 }
			 if(ctap!="0"){
			 arch.WriteLine(ajax.responseText+"AP"+nom+","+ctap);
			 }
			 if(ctat!="0"){
			 arch.WriteLine(ajax.responseText+"AT"+nom+","+ctat);
			 }
			 if(ctau!="0"){
			 arch.WriteLine(ajax.responseText+"AU"+nom+","+ctau);
			 }
			 if(ctrn!="0"){
			 arch.WriteLine(ajax.responseText+"RN"+nom+","+ctrn);
			 }
			 if(ctus!="0"){
			 arch.WriteLine(ajax.responseText+"US"+nom+","+ctus);
			 }			 
			 arch.Close();

			 alert("Rips Exportados Satisfactoriamente!!!");
			 //CT(rut,nom,fi,ff,fsl,datos,CodEnt,ctac,ctaf,ctam,ctus,ctat,ctau,ctap,ctrn,ctah);
			 	
			}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=5&rut="+rut+"&nom="+nom+"&fi="+fi+"&ff="+ff+"&fsl="+fsl+"&datos="+datos+"&CodEnt="+CodEnt+"&AR=11"); //Posting txtname to Servle

}//fin de la creacion del folder






function max() {
	var nom=document.getElementById("txtNombre").value;
	largo = nom.length;
	if (largo>6) {
		//alert("El nombre debe ser maximo de seis (6) caracteres!!!");
		var y = nom.substr(0,6);
		document.getElementById("txtNombre").value = y;
	}
}



//funci de la fecha 
var patron = new Array(2,2,4);
function masca(d,pat,nums,dias,mes,annio){
var sep="/";
if(d.valant != d.value){
	val = d.value
	largo = val.length
	ini=val.substring(0,2);
	if((ini>31)||(ini=="00")){
		//alert("Dia No Valido!!!");
		val=d.value="01";
		//d.focus();
	}
	val = val.split(sep);

	val2 = ''
	for(r=0;r<val.length;r++){
		val2 += val[r]
	}
	ini=val2.substring(2,4);
	if((ini=="04")||(ini=="06")||(ini=="09")||(ini=="11")){
		x=val2.substring(0,2);
		if(x=="31"){
			val2="30";
			val2=val2+ini;
		}
	}
	if((ini>12)||(ini=="00")){
		//alert("Mes No Valido!!!");
		mescj=val2.substring(0,2);
		val2=mescj+"01";
		//d.focus();
	}
	
	ano=val2.substring(4,8);
	dia=val2.substring(0,2);
	
	
	
	if(ini=="02"){
		if((dia=="30")||(dia=="31")||(dia=="29")){
		if(ano.length==4){
			if ( ( ano % 100 != 0) && ((ano % 4 == 0) || (ano % 400 == 0))) {
				val2="29";
				val2=val2+ini;
			}else{
				val2="28";
				val2=val2+ini;
			}
			val2=val2+ano;
		}
	  }
	}
/*	///////////////////////////////////////////////////
	//Validar fecha mayor que la fecha actual
		
	if(ano.length==4){
		if (ano==annio) {
			if(ini==mes){
				if((dia==dias)||(dia<dias)){
					alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
				val2='';
				}
			}else{
				if(ini<mes){
					alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
					val2='';
				}
			}
		}
		if (ano<annio) {
			alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
			val2='';
		}
	}
	
	///////////////////////////////////////////////////
*/	
/*
	
	if(nums){
		for(z=0;z<val2.length;z++){
			if(isNaN(val2.charAt(z))){
				letra = new RegExp(val2.charAt(z),"g")
				val2 = val2.replace(letra,"")
				
			}
		}
	}
		
	val = ''
	val3 = new Array()
	for(s=0; s<pat.length; s++){
 		val3[s] = val2.substring(0,pat[s])
 		val2 = val2.substring(pat[s])
	}
	
	for(q=0;q<val3.length; q++){
		if(q ==0){
			val = val3[q]         
		}
		else{
			if(val3[q] != ""){
				val += sep + val3[q]
			}
		}
	}
	d.value = val
	d.valant = val
	}
}
*/