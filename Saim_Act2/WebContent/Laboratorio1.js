

//------------------------------------------------------------------------
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
/*************************************************************************/	
	function GuardarGrupo2(a,codexa,codtipoexa,cont){
		
		var cod=document.getElementById("txtCodPac").value;
		var CodigoSubarea=document.getElementById("CodigoSubarea").value;
		var cedula=document.getElementById("txtnumdoc").value;	
		var fecha=document.getElementById("fechagru").value;
		var hora=document.getElementById("horagru").value;
		var usu=document.getElementById("txtusu").value;
		//var CodAsignacion=document.getElementById("CodAsignacion").value;
		var Resul=encodeURIComponent(a);
		ajax=getXMLObject();
		ajax.open("POST","INSERTAR_GRUPO",true); //getname will be the servlet name
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				alert("Ingreso Exitoso.");
				window.location.reload();
			}
		}
		//alert("CodigoSubarea="+CodigoSubarea+"&resultado="+Resul+"&cod="+cod+"&fecha="+fecha+"&hora="+hora+"&exa="+codexa+"&tipo="+codtipoexa+"&usu="+usu+"&cedula="+cedula+"&cont="+cont)
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("CodigoSubarea="+CodigoSubarea+"&resultado="+Resul+"&cod="+cod+"&fecha="+fecha+"&hora="+hora+"&exa="+codexa+"&tipo="+codtipoexa+"&usu="+usu+"&cedula="+cedula+"&cont="+cont); //Posting txtname to Servlet    
	}
	function insertar_grupo2(f){
		var cont=0;
		var oscar=f;
		var a="";
		if(f!=1){
			for(var i=0 ; i<=f-1 ; i++){
				a=document.form1.exagrupo[i].value;
				/*for(p=0;p<a.length;p++){
					a=a.replace('+','@');
				}*/
				cont=cont+1;
				var codexa=document.form1.codexa[i].value;
				var codtipoexa=document.form1.codtipoexa[i].value;
				GuardarGrupo2(a,codexa,codtipoexa,cont);
				
				oscar--;
			}
		}
	}
	
function GuardarGrupo(a,codexa,codtipoexa,cont){
		
		var cod=document.getElementById("txtCodPac").value;
		var cedula=document.getElementById("txtnumdoc").value;	
		var fecha=document.getElementById("fechagru").value;
		var hora=document.getElementById("horagru").value;
		var usu=document.getElementById("txtusu").value;
		var CodAsignacion=document.getElementById("CodAsignacion").value;
		var Resul=encodeURIComponent(a);
		ajax=getXMLObject();
		ajax.open("POST","INSERTAR_GRUPO",true); //getname will be the servlet name
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				//alert("Ingreso Exitoso.");
				window.location.reload();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("resultado="+Resul+"&cod="+cod+"&fecha="+fecha+"&hora="+hora+"&exa="+codexa+"&tipo="+codtipoexa+"&usu="+usu+"&cedula="+cedula+"&cont="+cont+"&CodAsignacion="+CodAsignacion); //Posting txtname to Servlet    
	}
	
	function insertar_grupo(f){
		
		var cont=0;
		var oscar=f;
		var a="";
		if(f!=1){
			for(var i=0 ; i<=f-1 ; i++){
				a=document.form1.exagrupo[i].value;
				/*for(p=0;p<a.length;p++){
					a=a.replace('+','@');
				}*/
				
				//cont=cont+1;
				var codexa=document.form1.codexa[i].value;
				var codtipoexa=document.form1.codtipoexa[i].value;
				GuardarGrupo(a,codexa,codtipoexa,cont);
				
				oscar--;
			}
		}
	}
