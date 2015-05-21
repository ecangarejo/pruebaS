//<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" >

function fecha(){
	  var time1 = new Date()
	  var acos = time1.getFullYear();
	  var mes = time1.getMonth();
	  mes=mes+1;
	  
	  var dia = time1.getDate();

	  var temp1 = "" + ((acos < 10) ? "0" : "") + acos;
	  temp1 += ((mes < 10) ? ":0" : ":") + mes;
	  temp1 += ((dia < 10) ? ":0" : ":") + dia;
	 // document.forms['form1'].txtfechaent.value = temp1
	  document.getElementById("txtfechaent").value =temp1;
	 id = setTimeout("fecha()",1000);
	  
	 
	  }
	
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

function CargarCamas(){
	var CodSubarea=document.getElementById("cbeps").value;
	ajax=getXMLObject();	
	ajax.open("POST","ControlPreingreso",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			document.getElementById("CargarCamas").innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("va=CCA&CodSubarea="+CodSubarea); //Posting txtname to Servlet
}

	function Guardar_Texto() {
		 
			   var exa=document.getElementById('txtexa').value;
			   var ced=document.getElementById('txtced').value;
			   var tipo=document.getElementById('txttipo').value;
			   var des=document.getElementById('textarea').value;
			   var fecha=document.getElementById('txtfecha').value;
			   var hora=document.getElementById('txthora').value;
			   var valor=document.getElementById('txtvalor').value;
			   var usu=document.getElementById('txtusu').value;
              
			// alert("usuario"+usu);
			 
			// alert("resultado "+des);
			  
			  if(des==""){
				  alert("Digite el Resultado del Examen");
			  }else{
				  /*for(i=0;i<des.length;i++){
					  des=des.replace('+','@');
			          
			        }*/
				  des=encodeURIComponent(document.getElementById("textarea").value);
				  ajax=getXMLObject();
				   ajax.open("POST","IngExamen",true); //getname will be the servlet name
				   
					   ajax.onreadystatechange=function() {
							if (ajax.readyState==4) {
								if(ajax.status == 200) {
								//mostrar resultados en esta capa
									alert(ajax.responseText);
						            window.close();
								}
							}
						}
				    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("z="+0+"&valor="+valor+"&examen="+exa+"&ced="+ced+"&tipo="+tipo+"&des="+des+"&fecha="+fecha+"&hora="+hora+"&usu="+usu+""); //Posting txtname to Servlet
			  }
			
		}
	
	function Guardar_Rango() {
			
			  var exa=document.form1.txtexa.value;
			  var ced=document.form1.txtced.value;
			  var tipo=document.form1.txttipo.value;
			  var des=document.form1.textarea.value;
			  var fecha=document.form1.txtfecha.value;
			  var hora=document.form1.txthora.value;
			  var valor=document.form1.txtvalor.value;
			  var usu=document.form1.txtusu.value;

			  if(des==""){
				  alert("Digite el Resultado del Examen");
			  }else{
				  /*for(i=0;i<des.length;i++){
					  des=des.replace('+','@');
			          
			        }*/
				  des=encodeURIComponent(document.getElementById("textarea").value);
				  ajax=getXMLObject();
				   ajax.open("POST","IngExamen",true); //getname will be the servlet name
				   
					   ajax.onreadystatechange=function() {
							if (ajax.readyState==4) {
								if(ajax.status == 200) {
								//mostrar resultados en esta capa
									alert(ajax.responseText);
						            window.close();
								}
							}
						}
				    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("z="+1+"&valor="+valor+"&examen="+exa+"&ced="+ced+"&tipo="+tipo+"&des="+des+"&fecha="+fecha+"&hora="+hora+"&usu="+usu+""); //Posting txtname to Servlet
			  }
		}
	   
	function yosi() {
		getXMLObject();
		 
		  if(xmlhttp) { 
			  var exa = document.form1.cbexamen.selectedIndex;
			  var nomexa=document.form1.cbexamen.options[exa].text;
			
			  xmlhttp.open("POST","ControlFormato?examen="+nomexa+"",true); //getname will be the servlet name
			  xmlhttp.onreadystatechange  = TipoExamen;
			  xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			  xmlhttp.send("");
				  
			  }
		   
		   var x;
		}
	
	function ocultar() {
		getXMLObject();
		 
		  if(xmlhttp) { 
			  var txtipo = document.form1.cblabarea.selectedIndex;
			  var nombretipo=document.form1.cblabarea.options[txtipo].text;
			
			  
			  xmlhttp.open("POST","lab_Ingrupo?area="+nombretipo+"",true); //getname will be the servlet name
			  xmlhttp.onreadystatechange  = area;
			  xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			  xmlhttp.send("");
			  
			  indice_marcado=0;
			  document.form1.miradio[indice_marcado].checked = false
			  
			  indice_marcado1=1;
			  document.form1.miradio[indice_marcado1].checked = false
			  div = document.getElementById("examen");

				 div.style.display = "none";
				 
				 div1 = document.getElementById("exa");

				 div1.style.display = "none";
				  
				 div2 = document.getElementById("de");

				 div2.style.display = "none";
				 
				 div2 = document.getElementById("dios");

				 div2.style.display = "none";
			  }
		   
		   var x;
		}
	
function area() {
		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
				
				document.form1.area.value = xmlhttp.responseText
		     	
		    		
			}
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	
	function ventana_grupo() {
		getXMLObject();
		 
		  if(xmlhttp) { 
			  var exa = document.form1.cbsubare.selectedIndex;
			  var nomexa=document.form1.cbsubare.options[exa].text;
			  if(nomexa=="SELECCIONE..."){
				  div2 = document.getElementById("dios");

				  div2.style.display = "none";
				  alert("Elija Grupo..!");
				  
			  }else{
			 var sexo=document.form1.txtsexo.value;
			 var edad=document.form1.txtedad.value;
			 
			  xmlhttp.open("POST","ControlFormato1?examen="+nomexa+"&sexo="+sexo+"&edad="+edad+"",true); //getname will be the servlet name
			  xmlhttp.onreadystatechange  = Dios;
			  xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			  xmlhttp.send(""); //Posting txtname to Servlet
			  
			 div = document.getElementById("examen");

			 div.style.display = "none";
			 
			 div1 = document.getElementById("exa");

			 div1.style.display = "none";
			 
			 div1 = document.getElementById("dios");

			 div1.style.display = "";
			 				 
			  } 
				  
			  }
		   
		   var x;
		}
	
		
	function tu() {
		getXMLObject();
		 
		  if(xmlhttp) { 
			 
			  var txtipo = document.forms['form1'].cblabarea.selectedIndex;
			  var nombretipo=document.forms['form1'].cblabarea.options[txtipo].text;
			  indice_marcado=0;
			 
			  if(nombretipo=="SELECCIONE..."){
				  alert("Seleccione Area...!");
				  document.forms['form1'].miradio[indice_marcado].checked = false 
			  }else{
				
				  
				  div = document.getElementById("de");

					 div.style.display = "none";
					 
					 div = document.getElementById("dios");

					 div.style.display = "none";
				  
				  xmlhttp.open("POST","ControlExamen?nom="+nombretipo+"",true); //getname will be the servlet name
				  xmlhttp.onreadystatechange  = Examen;
				  xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				  xmlhttp.send(""); //Posting txtname to Servlet
				 
				
					 div = document.getElementById("examen");

					 div.style.display = "";
					 
					 div1 = document.getElementById("exa");

					 div1.style.display = "";
					 
				  
			  }
		   }
		   var x;
		}
	
	function lista(h) {
		getXMLObject();
		 
		  if(xmlhttp) { 
			
			  
			  xmlhttp.open("POST","lab_ExaBuscar?z="+1+"",true); //getname will be the servlet name
			  xmlhttp.onreadystatechange  = Examen_Ge;
			  xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			  xmlhttp.send("");
			  div = document.getElementById("examen");

				 div.style.display = "";
				 div1 = document.getElementById("de");

				 div1.style.display = "none";
				 
				 div1 = document.getElementById("exa");

				 div1.style.display = "none";
		
		   }
		   var x;
		}
	
	
	function grupo(h) {
		getXMLObject();
		 
		  if(xmlhttp) { 
			  
			     div = document.getElementById("examen");

				 div.style.display = "none";
				 
				 div1 = document.getElementById("exa");

				 div1.style.display = "none";
			 
			  var txtipo = h.cblabarea.selectedIndex;
			  var nombretipo=h.cblabarea.options[txtipo].text;
			  indice_marcado=1;
			 
			  if(nombretipo=="SELECCIONE..."){
				  alert("Seleccione Area...!");
				  h.miradio[indice_marcado].checked = false 
			  }else{
			   
		     xmlhttp.open("POST","lab_subarea?nom="+nombretipo+"",true); //getname will be the servlet name
			 xmlhttp.onreadystatechange  = LabSubarea;
			 xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			 xmlhttp.send(""); //Posting txtname to Servlet
				  
			 div = document.getElementById("de");

			 div.style.display = "";
			 div = document.getElementById("dios");

			 div.style.display = "none";
			 
			  }
		   }
		   var x;
		}
	
	function ajaxFunction() {
		getXMLObject();
		 
		  if(xmlhttp) { 	 
		  // var txtname = h.txtnombre.value;//document.getElementById("txtnombre");
		    xmlhttp.open("POST","ControlPreingreso?va=1",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarPeticion;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
		}
	function ajaxConfirmar(h) {
		 
		  if(xmlhttp) { 	 
		  var txtipo = h.cbafiliacion.selectedIndex;
		  var nombretipo=h.cbafiliacion.options[txtipo].text;//document.getElementById("txtnombre");
		    xmlhttp.open("POST","ControlSeleccion?tipo="+nombretipo,true); //getname will be the servlet name
		    //xmlhttp.onreadystatechange  = ComprobarPeticion;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
		}
	
	function lab_area() {
		 
		  if(xmlhttp) { 	 
		    xmlhttp.open("POST","Controlab_area",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = Comprobarlab_area;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
		}
	
	function lab_unidad() {
		 
		  if(xmlhttp) { 	 
		
			  var ced=document.getElementById('txtced').value;
			  var docu=document.getElementById('txttipo').value;
			  var examen=document.getElementById('txtexa').value;
			  var valor=document.getElementById('txtvalor').value;
			  var area=document.getElementById('txtarea').value;
			  var edad=document.getElementById('txtedad').value;
			  var gene=document.getElementById('txtgene').value;
		
		    xmlhttp.open("POST","lab_unidad?area="+area+"&valor="+valor+"&examen="+examen+"&ced="+ced+"&docu="+docu+"&edad="+edad+"&gene="+gene+"",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = Comprobarlab_unidad;
		   
		    xmlhttp.send(""); 
		   }
		   var x;
		}
	
	
	function ajaxrango(h){
		 
		  if(xmlhttp) { 	 
			  var uni = h.select3.selectedIndex;
			  var nomuni=h.select3.options[uni].text;
		 // alert(nomuni);
		    xmlhttp.open("POST","lab_rango?nomuni="+nomuni+"",true); //getname will be the servlet name
		   xmlhttp.onreadystatechange  = Rango;
		   xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
		}
	
	function cambio(){
		 
		  if(xmlhttp) { 	 
			 var uni = document.form1.select3.selectedIndex;
			 var nomuni=document.form1.select3.options[uni].text;
		 
		   xmlhttp.open("POST","lab_generar?",true); //getname will be the servlet name
		 
		   }
		   var x;
		}
	
	// Carga lo Paises
	function ajaxNacionalidad() {
		// alert("1.js - ajaxNacionalidad");
		  if(xmlhttp) { 	 
		  // var txtname = h.txtnombre.value;//document.getElementById("txtnombre");
		    xmlhttp.open("POST","ControlPais?yo=1",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarPais; // 
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
	
		}
	
	// Carga lo Paises // copia necesaria
	function ajaxNacionalidad1() {
		// alert("1.js - ajaxNacionalidad");
		  if(xmlhttp) { 	 
		  // var txtname = h.txtnombre.value;//document.getElementById("txtnombre");
		    xmlhttp.open("POST","ControlPais?yo=1",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarPais1; // 
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
	
		}
	
	function ajaxOcupacion() {
		// alert("loco entro");
		  if(xmlhttp) { 	 
		  // var txtname = h.txtnombre.value;//document.getElementById("txtnombre");
		    xmlhttp.open("POST","ControlMuni?ocu=1",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarOcupacion;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
	
		}
	function ajaxEps() {
		//alert("llego");
		 var nomeps,cod;
		 cod=h.cbpacientidad.selectedIndex;
		// alert(cod);
		 nomeps=h.cbpacientidad.options[cod].text;
		// alert(nomeps);
		  if(xmlhttp) { 	 
		  // var txtname = h.txtnombre.value;//document.getElementById("txtnombre");
		    xmlhttp.open("POST","ControlPaciente?yosi=1",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarPaciente;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
		}
	function ajaxEps1(h) {
		//alert("llego");
		 var nomeps,cod;
		 cod=h.cbpacientidad.selectedIndex;
		// alert(cod);
		 nomeps=h.cbpacientidad.options[cod].text;
		 //alert(nomeps);
		  if(xmlhttp) { 	 
		  // var txtname = h.txtnombre.value;//document.getElementById("txtnombre");
		    xmlhttp.open("POST","ControlPaciente?yosi=1&nomep="+nomeps+"",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarPaciente;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
		}
	function ajaxCodigoConv(h) {
		 
		  if(xmlhttp) { 
			  var cod=h.cbpacientidad.selectedIndex;
				var nomeps=h.cbpacientidad.options[cod].text;
				//alert(nomeps);
		  // var txtname = h.txtnombre.value;//document.getElementById("txtnombre");
		    xmlhttp.open("POST","ControlPaciente?yosi=2&nomep="+nomeps,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarCodigoConv;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
		}
	function CodigoMun(h) {
		  if(xmlhttp) { 
			var codigo=h.cbmun.selectedIndex;
			
			var nombremun=h.cbmun.options[codigo].text;
			var codidoDepa=h.cbdep.selectedIndex;
			var nombreDepa=h.cbdep.options[codidoDepa].text;
					 
			xmlhttp.open("POST","ControlMunicipio?nom="+nombremun+"&nomdepa="+nombreDepa,true); //getname will be the servlet name
		   xmlhttp.onreadystatechange  = ComprobarCodMun;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
		}
	function CodigoMunCopia(h) {
		  if(xmlhttp) { 
			var codigo=h.cbmun.selectedIndex;
			
			var nombremun=h.cbmun.options[codigo].text;
			var codidoDepa=h.cbdep.selectedIndex;
			var nombreDepa=h.cbdep.options[codidoDepa].text;
					 
			xmlhttp.open("POST","ControlMunicipio?nom="+nombremun+"&nomdepa="+nombreDepa,true); //getname will be the servlet name
		   xmlhttp.onreadystatechange  = ComprobarCodMunCopia;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }
		   var x;
		}
	
	

	function ajax(h) {
		  if(xmlhttp) { 
			  var x=h.cbeps.selectedIndex;
			  x=h.cbeps.options[x].text;
		    xmlhttp.open("POST","ControlPreingreso?va=2&x="+x,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = Comprobar;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }
		}
	function ajaxDep(h) {
		 //alert("Entramos al otro");
		  if(xmlhttp) { 
			  var x=h.cbnacionalidad.selectedIndex;
			 // alert("el indice es "+x);	 
			 
		  // var txtname = h.txtnombre.value;//document.getElementById("txtnombre");
		    xmlhttp.open("POST","ControlPais?yo=2&x="+x,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarDep;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }
		}
	// Copia departamento
	
	function ajaxDepCopia(h) {
		 //alert("Entramos al otro");
		  if(xmlhttp) { 
			  var x=h.cbnacionalidad.selectedIndex;
			 // alert("el indice es "+x);	 
			 
		  // var txtname = h.txtnombre.value;//document.getElementById("txtnombre");
		    xmlhttp.open("POST","ControlPais?yo=2&x="+x,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarDepCopia;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }
		}
	function ajaxCambiarOcu(h) {
		 //alert("Entramos al otro");
		  if(xmlhttp) { 
			  var y=h.cbocupacion.selectedIndex;
			  var x=h.cbocupacion.options[y].text;
			 //alert("el indice es "+x);	 
			 
		  // var txtname = h.txtnombre.value;//document.getElementById("txtnombre");
		    xmlhttp.open("POST","ControlMuni?ocu=2&x="+x,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarCambOcu;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }
		}
	function ajaxx(h) {
		  if(xmlhttp) { 
			  var xx=h.cbeps.selectedIndex;
			  xx=h.cbeps.options[xx].text;
			  var y=h.ciu.selectedIndex;
			  y=h.ciu.options[y].text;
		    xmlhttp.open("POST","ControlPreingreso?va=3&y="+y+"&xx="+xx,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = Comprobarx;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }
		}
	function ajaxxMun(h) {
		 //alert("Entramos al otro");
		  if(xmlhttp) { 
			  //var xx=h.cbnacionalidad.selectedIndex;
			  var dep=h.cbdep.selectedIndex;
			  dep=h.cbdep.options[dep].text;
			 // alert("el indice es "+x);	 
			 
		  // var txtname = h.txtnombre.value;//document.getElementById("txtnombre");
		    xmlhttp.open("POST","ControlPais?yo=3&de="+dep+"",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarxMun;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }
		}
	function ajaxxMunCopia(h) {
		 //alert("Entramos al otro");
		  if(xmlhttp) { 
			  //var xx=h.cbnacionalidad.selectedIndex;
			  var dep=h.cbdep.selectedIndex;
			  dep=h.cbdep.options[dep].text;
			 // alert("el indice es "+x);	 
			 
		  // var txtname = h.txtnombre.value;//document.getElementById("txtnombre");
		    xmlhttp.open("POST","ControlPais?yo=3&de="+dep+"",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarxMunCopia;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }
		}

	function Examen() {
		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
				
				  document.getElementById("examen").innerHTML = xmlhttp.responseText


		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		
		 
		}
function Examen_Ge() {
		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
				
				
				  document.getElementById('examen').innerHTML = xmlhttp.responseText

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		
		 
		}
	

	function LabSubarea() {
		var event='';

		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
				
				document.getElementById('de').innerHTML = xmlhttp.responseText; 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		
		 
		}
	
function Dios() {
		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
				
		     	
				document.getElementById("dios").innerHTML = xmlhttp.responseText
		     	
		    		
		    	 // document.form1.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	
	function Rango() {
		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
				
		     	
				formato.innerHTML = xmlhttp.responseText;
		     		
		    	 // document.form1.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		
		 
	}
	
	function TipoExamen() {
		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
				
		     	
				document.getElementById("tipo").innerHTML = xmlhttp.responseText
		     	
		    		
		    	 // document.form1.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		
		 
		}
	
	
	function Comprobarx() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");
		     	//alert(z+"cesaaaaaa");
		     	form1.cam.length=y;
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			     form1.cam.options[x+1].text=z[x];
			    }
		    		
		    	  //document.form1.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	
	function ComprobarxMun() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");
		     	//alert(z+"cesaaaaaa");
		     	form2.cbmun.length=y;
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1){ 
			     form2.cbmun.options[x+1].text=z[x];
			    }
		    		
		    	  //document.form2.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	function ComprobarxMunCopia() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");
		     	//alert(z+"cesaaaaaa");
		     	form1.cbmun.length=y;
		        var h,ss;
		        form1.cbmun.options[0].text="SELECCIONE..."
		     	for(x=0; x<y-1; x=x+1){ 
			     form1.cbmun.options[x+1].text=z[x];
			    }
		    		
		    	  //document.form2.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	
	function Comprobar() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");
		     	//alert(z+"cesaaaaaa");
		     	form1.ciu.length=y;
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			     form1.ciu.options[x+1].text=z[x];
			    }
		    		
		    	  //document.form1.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	function ComprobarDep() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");
		     	//alert(z+"cesaaaaaa");
		     	form2.cbdep.length=y;
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			     form2.cbdep.options[x+1].text=z[x];
			    }
		    		
		    	  //document.form2.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	
	function ComprobarDepCopia() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");
		    
		     	form1.cbdep.length=y;
		        var h,ss;
		        form1.cbdep.options[0].text="SELECCIONE..."
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			     form1.cbdep.options[x+1].text=z[x];
			    }
		    		
		    	  //document.form2.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	function ComprobarPeticion() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	
		     	var a=xmlhttp.responseText;
		     	//alert("lo que trae "+a)
		     	var y=a.split("_").length;
		     	var z=a.split("_");
		   
		     	
		     	form1.cbeps.length=y;
		        var h,ss;
		     	for(x=0; x<y; x=x+1){ 
			     form1.cbeps.options[x+1].text=z[x];
			    }
		    		
		    	  //document.form1.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	// respuesta de servlet..... llena el combobox con los datos 
	function ComprobarPais() {
		 
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {	
		     	var c=xmlhttp.responseText;		     	
		     	var d=c.split("_").length;
		     	var f=c.split("_");
		     	document.getElementById("cbnacionalidad").length=d;
		     	//alert("Comprobar Pais valor d ="+d);
		        var h,ss;
		     
		     	for(x=0; x<d; x=x+1)
		     	{		     		 
            		document.getElementById("cbnacionalidad").options[x+1].text=f[x];
            	    ajaxOcupacion();
			    }
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	
	// respuesta de servlet..... llena el combobox con los datos // Copia por problemas de id de formulario
	function ComprobarPais1() {
		 
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {	
		     	var c=xmlhttp.responseText;		     	
		     	var d=c.split("_").length;
		     	var f=c.split("_");
		     	document.getElementById("cbnacionalidad").length=d;
		     	//alert("Comprobar Pais valor d ="+d);
		        var h,ss;
		     	for(x=0; x<d; x=x+1)
		     	{		     		 
		     		document.getElementById("cbnacionalidad").options[x+1].text=f[x];
			    
			    }
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	
	function ComprobarOcupacion() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
	
		     	var c=xmlhttp.responseText;
		     	//alert("valor de c "+c);
		     	var d=c.split("_").length;
		     	var f=c.split("_");
		     	//alert(z+"cesaaaaaa");
		     	//alert(d);
		     	form2.cbocupacion.length=d;
		        var h,ss;
		     	for(x=0; x<d; x=x+1)
		     	{
		     		 
			     form2.cbocupacion.options[x+1].text=f[x];
			     ajaxEps( document.forms['form2']);
			    }
		     	
		    		
		    	  //document.form2.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	// copia del comprobar ocupacion
	/*function ComprobarOcupacion1() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
	
		     	var c=xmlhttp.responseText;
		     	//alert("valor de c "+c);
		     	var d=c.split("_").length;
		     	var f=c.split("_");
		     	//alert(z+"cesaaaaaa");
		     	//alert(d);
		     	form1.cbocupacion.length=d;
		        var h,ss;
		     	for(x=0; x<d; x=x+1)
		     	{
		     		 
			     form1.cbocupacion.options[x+1].text=f[x];
			     ajaxEps1( document.forms['form1']);
			    }
		     	
		    		
		    	  //document.form2.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}*/
	
	function ComprobarPaciente() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
	
		     	var c=xmlhttp.responseText;
		     	
		     	var d=c.split("_").length;
		     	var f=c.split("_");
		     	
		     	form2.cbpacientidad.length=d;
		        var h,ss;
		     	for(x=0; x<d; x=x+1)
		     	{
		     		 
			     form2.cbpacientidad.options[x+1].text=f[x];
			    
			    }
		    		
		    	  //document.form2.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	
	function ComprobarCodigoConv() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
	
		     	var c=xmlhttp.responseText;
		     	
			     form2.txtcodigoconv.value=c;
		    	//  document.form2.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	
	function Comprobarlab_area() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
	
		     	var c=xmlhttp.responseText;
		     	//alert(c);
		     	var d=c.split("_").length;
		     	var f=c.split("_");
		     	
		     	document.getElementById('cblabarea').length=d;
		        var h,ss;
		     	for(x=0; x<d; x=x+1)
		     	{
		     		document.getElementById('cblabarea').options[x+1].text=f[x];
			    }
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	
	function Comprobarlab_unidad() {
		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {	
				document.getElementById('uni').innerHTML = xmlhttp.responseText;	
			} else {
		        //alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	
	function ComprobarCambOcu() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
	
		     	var c=xmlhttp.responseText;
		     	
			     //form2.txtocupacion.value=c;
		     	document.getElementById('txtocupacion').Value=c;
		    	  //document.form2.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	
	function ComprobarCodMun() {

		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	var c=xmlhttp.responseText;
		     	
			     form2.txtcodmun.value=c;
		    	 // document.form2.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	
	// hay una copia porque hay problemas en los id del formulario 
	function ComprobarCodMunCopia() {

		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	var c=xmlhttp.responseText;
		     	
		     	//asigna el codigo de el municipio a un campo de texto
			     form1.txtcodmun.value=c;
		    	 // document.form2.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}
	
	function envio(h) {
	  /*  var x=h.cam.selectedIndex;
	    x=h.cam.options[x].text;*/
		var x=document.getElementById("cam").value;
	    if((x=="Seleccione")){
	 	   alert("Seleccione Una Cama...!");
	    }else{
	    	window.opener.document.form1.textfield13.value=x;
	    	window.close();
	     }
	 	
		}
	
	function contacto(Cod) {
		
	    var x=document.getElementById("txtnombre").value;
	   // alert(x);
	    window.opener.document.form1.textfield14.value=x;
	    window.opener.document.form1.txtCodAcomp.value=Cod;
	    }

	