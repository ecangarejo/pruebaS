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


function DescOC(){
	//Se dirige al controlador para mostrarme las opciones de descargue
	ajax=getXMLObject();
	var cont=document.getElementById("desord");
	ajax.open("POST","ControlDescOC",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			cont.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=VerDescOC"); 
}

function Tdescargue(){
	ajax=getXMLObject();
	//alert();
	var tipo=document.getElementById("tid").value;
	var va="";
	var cont=document.getElementById("desord");
	if(tipo==0){
		alert("NO HA SELECCIONA UNA OPCION VALIDA ");
	}else{
		if(tipo==1){
			va="DManual";
		}else{
			if(tipo==2){
			 va="DPrecargado";
			}
		}
		ajax.open("POST","ControlDescOC",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				cont.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor="+va+""); 
			
	}	
}

function BtnBuscar(){
	ajax=getXMLObject();
	var codprove=document.getElementById("codprove").value;
	var Nofact=document.getElementById("nofactura").value;
	var Nofactura=encodeURIComponent(Nofact);
	var rep=document.getElementById("Resp").value;
	var resp=encodeURIComponent(rep);
	var cont=document.getElementById("desord");
	
	if((codprove=="")||(Nofact=="")||(rep=="")){
		alert("No ha digitado todos los campos ");
	}else{
		ajax.open("POST","ControlDescOC",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				var datos=ajax.responseText;
				//alert(datos);
			    var elem=datos.split("_");
			    var verif=elem[0];
			    //alert("valor de verif"+verif);
			    if(verif==1){
			    	alert("Esta factura "+Nofactura+" fue descargada en la orden de Compra No. "+elem[1]);
			    	window.location.reload();
			    	
			    }else{
			    	cont.innerHTML=datos;	
			    }
			    
				
			}
		}
		//alert("valor=Tipos&codprove="+codprove+"&nofact="+Nofactura+"&resp="+resp);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Tipos&codprove="+codprove+"&nofact="+Nofactura+"&resp="+resp);
	}
}

function btnmanual(tip, codprove,e){
	
	var cprod=document.getElementById("cprod").value;
	var civa=document.getElementById("civa").value;
	var cref=document.getElementById("codref").value;
	var codref=encodeURIComponent(cref);
	var nfact=document.getElementById("nofact").value;
	var nofact=encodeURIComponent(nfact);
	var descrip=document.getElementById("descrip").value;
	var ndescrip=encodeURIComponent(descrip);
	var precio=document.getElementById("valor").value;
	var desc=document.getElementById("desc").value;
	var viva=document.getElementById("viva").value;
	var total=document.getElementById("total").value;
	var user=document.getElementById("user").value;
	var verif=document.getElementById("verif").value;
	var rep=document.getElementById("resp").value;
	var resp=encodeURIComponent(rep);
	var cont=document.getElementById("desord");
	var codigo="";
	if(e!=1){
	   codigo=document.getElementById("codigo").value;
	}
	if((cprod=="")||(precio=="")||(desc=="")){
		alert("No ha digitado todos los campos ");
	}else{
		ajax=getXMLObject();
		ajax.open("POST","ControlDescOC",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				cont.innerHTML=ajax.responseText;
			}
		}
		//alert("valor=GCargue&tip="+tip+"&codprove="+codprove+"&e="+e+"&cprod="+cprod+"&civa="+civa+"&codref="+codref+"&nofact="+nofact+"&ndescrip="+ndescrip+"&precio="+precio+"&desc="+desc+"&viva="+viva+"&total="+total+"&user="+user+"&codigo="+codigo+"&verif="+verif+"&resp="+resp);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=GCargue&tip="+tip+"&codprove="+codprove+"&e="+e+"&cprod="+cprod+"&civa="+civa+"&codref="+codref+"&nofact="+nofact+"&ndescrip="+ndescrip+"&precio="+precio+"&desc="+desc+"&viva="+viva+"&total="+total+"&user="+user+"&codigo="+codigo+"&verif="+verif+"&resp="+resp); 
	}
	
}


function RElim(i,coddet){
	//alert("entrando a RElim");
	var codigo = document.getElementById("codigo").value; //codigo de orden de compra provisional
	//alert(codigo);
	var user=document.getElementById("user").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlDescOC",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			document.getElementById("detcar").innerHTML="";
			//alert(ajax.responseText);
			document.getElementById("detcar").innerHTML=ajax.responseText;
		}
	}
	//alert("valor=GCargue&tip="+tip+"&codprove="+codprove+"&e="+e+"&cprod="+cprod+"&civa="+civa+"&codref="+codref+"&nofact="+nofact+"&ndescrip="+ndescrip+"&precio="+precio+"&desc="+desc+"&viva="+viva+"&total="+total+"&user="+user+"&codigo="+codigo+"&verif="+verif+"&resp="+resp);
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ElimCargue&coddet="+coddet+"&user="+user+"&codigo="+codigo);
}


function Terminar(codigo){
	//alert("Entrando a terminar");
	var user=document.getElementById("user").value;
	var res=document.getElementById("resp").value;
	var resp=encodeURIComponent(res);
	var nofac=document.getElementById("nofact").value;
	var nofact=encodeURIComponent(nofac);
	var codsuc=document.getElementById("codsuc").value;
	var codsubc=document.getElementById("codsubc").value;
	var codcc=document.getElementById("codcc").value;
	var codsucycc=document.getElementById("codsucycc").value;
	var codcentrosubc=document.getElementById("codcentrosubc").value;
	var ob=document.getElementById("obs").value;
	var obs=encodeURIComponent(ob);
	
	if((codsuc=="")||(codsubc=="")||(codcc=="")||(codsucycc=="")||(codcentrosubc=="")||(resp=="")||(nofact=="")){
		alert("No ha seleccionado todos los datos")
	}else{
		ajax=getXMLObject();
		ajax.open("POST","ControlDescOC",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				alert("Descargado exitosamente ! ");
				var datos=ajax.responseText;
				window.location.reload();
				pagina="cont_ReporteDocumento.jsp?CodDocumento="+datos;			
			   	var opciones="toolbar=no, location=no, directories=no, status=yes, menubar=yes,";
			 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
			   	window.open(pagina,"NUEVA",opciones);
				
				
				 
				
				
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Terminar&user="+user+"&codigo="+codigo+"&resp="+resp+"&nofact="+nofact+"&codcsuc="+codsuc+"&codsubc="+codsubc+"&codcc="+codcc+"&codsucycc="+codsucycc+"&codcentrosubc="+codcentrosubc+"&obs="+obs);
	}
}


function AutoProveOrden(){
	ajax=getXMLObject();
	var texto=document.getElementById("prove").value;
	var cont=document.getElementById("vprov");
	ajax.open("POST","ControlDescOC",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			cont.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=BusquedaProve&texto="+texto);
	
}

function SelProv(codprov){
	//alert("entrando");
	var cont=document.getElementById("vprov");
	document.getElementById("vprov").innerHTML="";
	ajax=getXMLObject();
	ajax.open("POST","ControlDescOC",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var cadena=ajax.responseText;
			//alert(cadena);
			var elem=cadena.split('_');
			var cpv=elem[0];
			var nomp=elem[1];
			document.getElementById("prove").value=nomp;
			document.getElementById("codprove").value=cpv;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Prov&cod="+codprov);	
}

function tip(){
	ajax=getXMLObject();
	var tip=document.getElementById("tip").value;
	var codprove=document.getElementById("cprove").value;
	var nofact=document.getElementById("nfact").value;
	var Nofactura=encodeURIComponent(nofact);
	var rep=document.getElementById("resp").value;
	var resp=encodeURIComponent(rep);
	var cont=document.getElementById("desord");
	var user=document.getElementById("user");
	var opc=confirm("¿Desea seleccionar este tipo de producto ?");
	if(opc){
		ajax=getXMLObject();
		ajax.open("POST","ControlDescOC",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				//alert(ajax.responseText);
				cont.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=IManual&tip="+tip+"&codprove="+codprove+"&Nofactura="+Nofactura+"&user="+user+"&resp="+resp);
		
		
	}else{}
}

function AutoProdu(tip,i,codprove){
	ajax=getXMLObject();
	var texto="";
	var cont="";
	if(i==1){
	     texto=document.getElementById("codref").value;
	     cont=document.getElementById("vcref");
	     document.getElementById("vprod").innerHTML="";
	     document.getElementById("descrip").value="";
	}else{
		if(i==2){
		 texto=document.getElementById("descrip").value;
		 cont=document.getElementById("vprod");
		 document.getElementById("vcref").innerHTML="";
		 document.getElementById("codref").value="";
		}
	}

	ajax.open("POST","ControlDescOC",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			cont.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=VProd&tip="+tip+"&i="+i+"&texto="+texto+"&codprove="+codprove);
}


function SelProd(codprod,i,t){
	/*Si t=1  quiere decir que escogi un producto si t=2 escogi una cuenta */
	var cref=document.getElementById("cref"+i).value;
	var codref=encodeURIComponent(cref);
	var np=document.getElementById("nom"+i).value;
	var nprod=encodeURIComponent(np);
	var valor=document.getElementById("valor"+i).value;
	var vaiva=document.getElementById("vaiva"+i).value;
	var civa=document.getElementById("civa"+i).value;
	
	document.getElementById("codref").value=codref;
	document.getElementById("descrip").value=np;
	document.getElementById("valor").value=valor;
	document.getElementById("viva").value=vaiva;
	document.getElementById("cprod").value=codprod;
	document.getElementById("civa").value=civa;
	//alert("valor de t"+t);
	document.getElementById("verif").value=t;
	
	document.getElementById("vprod").innerHTML="";
	document.getElementById("vcref").innerHTML="";
	
	var descuento=document.getElementById("desc").value;
	var vdesc=((valor*descuento)/100);
	var valoriva=(((valor-vdesc)*vaiva)/100);
	var vtotal=((valor-vdesc)+valoriva);
	document.getElementById("total").value=vtotal;
	
}

function checknum(t){
	var cant=0;
	var desc=0;
	var vdesc=0;
	var viva=0;
	var valoriva=0;
	var vtotal=0;
	if(t==1){//campo de valor
		cant=document.getElementById("valor").value;
		if((cant==0)||((cant>0)&&(cant<9999999999999999999999))){
			 desc=document.getElementById("desc").value;
			 viva=document.getElementById("viva").value;
			 vdesc=((cant*desc)/100);
			 valoriva=(((cant-desc)*viva)/100);
			 vtotal=((cant-desc)+valoriva);
			 document.getElementById("total").value=vtotal;
			
		}else{
			alert("El valor a ingresar debe ser numero ");
			document.getElementById("valor").value=0;
		}
		
	}else{
		if(t==2){//campo de descuento 
			desc=document.getElementById("desc").value;
			
			if((desc==0)||((desc>0)&&(desc<9999999999999999999999))){
				cant=document.getElementById("valor").value;
				 viva=document.getElementById("viva").value;
				 vdesc=((cant*desc)/100);
				 valoriva=(((cant-desc)*viva)/100);
				 vtotal=((cant-desc)+valoriva);
				 document.getElementById("total").value=vtotal;
				
			}else{
				alert("El valor a ingresar debe ser numero ");
				document.getElementById("desc").value=0;
			}
			
		}else{
			if(t==3){//campo del iva
				viva=document.getElementById("viva").value;
				if((viva==0)||((viva>0)&&(viva<9999999999999999999999))){
					desc=document.getElementById("desc").value;
					cant=document.getElementById("valor").value;
						vdesc=((cant*desc)/100);
					 valoriva=(((cant-desc)*viva)/100);
					 vtotal=((cant-desc)+valoriva);
					 document.getElementById("total").value=vtotal;
					
				}else{
					alert("El valor a ingresar debe ser numero ");
					document.getElementById("viva").value=0;
				}
			}
		}
	}
}

function BuscarIva(){
	ajax=getXMLObject();
	var texto=document.getElementById("viva").value;
	var vistiva=document.getElementById("veriva");
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			vistiva.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Biva&texto="+texto);
	
}


function Biv(cod, valor){
	//alert();
	document.getElementById("viva").value=valor;
	document.getElementById("veriva").innerHTML="";
	document.getElementById("civa").value=cod;
	checknum(3);
	//document.getElementById("Oa").focus();
}

function suc(){
	ajax=getXMLObject();
	var suc=document.getElementById("suc").value;
	var conte=document.getElementById("centrocosto");
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			conte.innerHTML=ajax.responseText;
			document.getElementById("codsuc").value=suc;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Vistcentrocosto&suc="+suc);
}


function ccosto(suc){
	ajax=getXMLObject();
	var ccosto=document.getElementById("ccosto").value;
	var visual=document.getElementById("succosto");
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		 visual.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Vistsubcosto&ccosto="+ccosto+"&suc="+suc);
}

function ccysc(codcosto,suc){
	ajax=getXMLObject();
	var subcc=document.getElementById("subccosto").value;
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			 var codigos=ajax.responseText;
			   var elem=codigos.split('_');
			   var codcentrosubc=elem[0];
			   var codsucycc=elem[1];
		 document.getElementById("codcentrosubc").value=codcentrosubc;
		 document.getElementById("codsucycc").value=codsucycc;
		 document.getElementById("codsuc").value=suc;
		 document.getElementById("codcc").value=codcosto;
		 document.getElementById("codsubc").value=subcc;
		 //alert(ajax.responseText);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ccysc&codcosto="+codcosto+"&subcc="+subcc+"&suc="+suc);
}



function dia(){
	var codoc=document.getElementById("codoc").value;
	//alert(codoc);
	var d=document.getElementById("dia").value;
	if((d>-1)&&(d<32)){
		if(d.length==2){
			document.getElementById("mes").focus();
		}
		
	}else{
		alert("El campo solo acepta datos numericos. ")
		document.getElementById("dia").value="";
	}
}

function Ano(tipo){
	
	//alert(codoc);
	var a=document.getElementById("ano").value;
	
	if((a>0)&&(a<9999)){
		if(a.length==4){
			if(tipo==1){
			
			}else{
				if(tipo==2){
					var codoc=document.getElementById("codoc").value;
					if(codoc==""){
						alert("No ha digitado la orden de compra a descargar");
					}else{
						OCPredescargado(codoc);
					}
				}
				
			}
		}
	}else{
		alert("El campo solo acepta datos numericos. ");
		document.getElementById("ano").value="";
	}
}

function OCPredescargado(codoc){
	ajax=getXMLObject();
	var cont=document.getElementById("desord");
	var dia=document.getElementById("dia").value;
	var mes=document.getElementById("mes").value;
	var ano=document.getElementById("ano").value;
	var dp=document.getElementById("dp").value;
	var nofact=document.getElementById("fact").value;
	
	if((dia=="")||(mes=="")||(ano=="")||(codoc=="")||(dp=="")||(nofact=="")){
		alert("No ha digitado todos los campos ! ");
	}else{
		var fecha=ano+"-"+mes+"-"+dia;
		ajax.open("POST","ControlDescOC",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				cont.innerHTML=ajax.responseText;
			}
		}
		//alert("valor=OCP&codoc="+codoc+"&fecha="+fecha+"&dp="+dp+"&nofact="+nofact); 
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=OCP&codoc="+codoc+"&fecha="+fecha+"&dp="+dp+"&nofact="+nofact); 
	
	}
}

function BuscOrdenA(){
	//muestra en el div las orden de compra aprobadas que son las que se pueden descargar 
	ajax=getXMLObject();
	var texto=document.getElementById("numoc").value;
	//alert("texto "+texto);
	if(texto==""){
		document.getElementById("numoc").valueL="";
		document.getElementById("ord").innerHTML="";
	}else{
		var cont=document.getElementById("ord");
		ajax.open("POST","ControlDescOC",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				//alert(ajax.responseText);
				cont.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=DivOC&texto="+texto); 
	}
}

function Selord(codord, i){
	ajax=getXMLObject();
	
	var conse=document.getElementById("consec"+i).value;
	var nombre=document.getElementById("nomprov"+i).value;
	document.getElementById("ord").innerHTML="";
	document.getElementById("numoc").value=conse+" - "+nombre;
	document.getElementById("codoc").value=codord;
	
	var dia=document.getElementById("dia").value;
	var mes=document.getElementById("mes").value;
	var ano=document.getElementById("ano").value;
	var fact=document.getElementById("fact").value;
	var dp=document.getElementById("dp").value;
	if(fact==""){
		document.getElementById("fact").focus();
	}else{
		if(dp==""){
			document.getElementById("dp").focus();
		}else{
			if(dia==""){
				document.getElementById("dia").focus();
			}else{
				if(mes=="0"){
				document.getElementById("mes").focus();
				}else{
					if(ano==""){
						document.getElementById("ano").focus();
					}else{
						OCPredescargado(codord);
					}
				}
			}
		}
	}//fin de validacion de vacio
	
}

function Auna(){
	//alert("texto Auna "+texto);
	ajax=getXMLObject();
	var texto=document.getElementById("numoc").value;
	var cod="";
	if(texto==""){
		document.getElementById("numoc").value="";
		document.getElementById("ord").innerHTML=" ";
	}else{
			ajax.open("POST","ControlDescOC",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					var datos=ajax.responseText;
					//alert(datos);
					var elem=datos.split("_");
					var vali=elem[0];
					if(vali==1){
						cod=elem[2];
						var consec=elem[1];
						var nombre=elem[3];
						document.getElementById("numoc").value=consec+" - "+nombre;
						document.getElementById("codoc").value=cod;
						document.getElementById("ord").innerHTML="   ";
						var dia=document.getElementById("dia").value;
						var mes=document.getElementById("mes").value;
						var ano=document.getElementById("ano").value;
						var fact=document.getElementById("fact").value;
						var dp=document.getElementById("dp").value;
						if(fact==""){
							document.getElementById("fact").focus();
						}else{
							if(dp==""){
								document.getElementById("dp").focus();
							}else{
								if(dia==""){
									document.getElementById("dia").focus();
								}else{
									if(mes=="0"){
									document.getElementById("mes").focus();
									}else{
										if(ano==""){
											document.getElementById("ano").focus();
										}else{
											OCPredescargado(cod);
										}
									}
								}
							}
						}
						
					}else{
						BuscOrdenA();
					}
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=Auna&texto="+texto);
	}
}

function Verifact(){
	ajax=getXMLObject();
	var fact=document.getElementById("fact").value;
	var codoc=document.getElementById("codoc").value;
	//alert(codoc);
	document.getElementById("codoc").value=codoc;
	ajax.open("POST","ControlDescOC",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			var verif=ajax.responseText;
			//alert(verif);
			if(verif==1){
				alert("Esta Factura ya fue descargada !");
				document.getElementById("fact").value="";
				document.getElementById("fact").focus();
			}else{
				document.getElementById("dp").focus();
			}
		}
	}
	//alert("valor=Verifact&fact="+fact+"&codoc="+codoc); 
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Verifact&fact="+fact+"&codoc="+codoc); 
	
}

function num1(){
	ajax=getXMLObject();
	
	var n=document.getElementById("dp").value;
	
	if((n>0)&&(n<9999)){
		if(n.length==3){
			document.getElementById("dia").focus();
		}
	}else{
		alert("El campo solo acepta valores numericos ");
	}
	
}

function Omitir(i){
	var o=document.getElementById("act"+i).innerHTML;
	if(o=="OMITIR"){
		document.getElementById("cantrec"+i).value="OMITIDO";
		document.getElementById("cantrec"+i).disabled=true;
		document.getElementById("act"+i).innerHTML="HABILITAR";
	}else{
		if(o=="HABILITAR"){
			document.getElementById("cantrec"+i).disabled=false;
			document.getElementById("cantrec"+i).value="0";
			document.getElementById("act"+i).innerHTML="OMITIR";
		}
	}
	//alert(o);
}

function valcant(i,cantsol,cantdesc){
	var cantrec=document.getElementById("cantrec"+i).value;
	var rest=cantsol-cantdesc;
	if((cantrec>0)&&(cantrec<9999999999999999999)){
		if(cantrec>rest){
			alert("La cantidad recibida no puede ser mayor que la cantidad solicitada ");
		}
	}else{
		alert("Este campo solo acepta datos numericos ! ");
	}
}


function TestBodega(i,codoc,dp,tp){
	//alert("TestBodega");
	var cont=document.getElementById("desord");
	var prod="";
	var productos="";
	var cant="";
	var cantidades="";
	var BodFarmacia="2"; //Bodega de Recepcion
	var BodProducto="";
	var nofact=document.getElementById("nofact").value;
	var nofactura=encodeURIComponent(nofact);
	var fecha=document.getElementById("fecha").value;
	//alert("valor de i "+i);
	var vali=""
	var coddet="";
	var coddets="";
	var validos=0;
	for(j=1;j<i;j++){
		prod=document.getElementById("codprod"+j).value;
		productos=prod+"_"+productos;
		cant=document.getElementById("cantrec"+j).value;
		if(i==2){
			if(cant=="OMITIDO"){
				vali=1;//Esto quiere decir que la orden solo tiene un solo registro y no va a ser descagada
			}
		}
		
		if(cant=="OMITIDO"){
			validos=validos;//esto quiere decire que la orden ha emitido todos sus registros
		}else{
			validos=validos+1;
		}
		cantidades=cant+"_"+cantidades;
		coddet=document.getElementById("coddet"+j).value;
		coddets=coddet+"_"+coddets;
	}
	var opc="";
	var esto="";
	var descrip="";
	if(vali==1){
		
		
			var user=document.getElementById("user").value;
			ajax3=getXMLObject();
			ajax3.open("POST","ControlDescOC",true); //getname will be the servlet name
			ajax3.onreadystatechange  = function(){
				if (ajax3.readyState == 4) {
					var dat=ajax3.responseText;
					if(dat==1){
						esto=4;
						descrip="ORDEN CAMBIO A ESTADO EJECUTADO ";
						opc=confirm("Esta orden pasara a un estado de EJECUTADO y es irreversible este cambio ¿Esta seguro?");
					}else{
						if(dat==0){
							esto=5;
							
							opc=confirm("Esta orden pasara a un estado de NO DESCARGADO y es irreversible este cambio ¿Esta seguro?");
						}
					}
					
					if(opc){
						var textoobs="";
						if(dat==0){
						textoobs=prompt("Digite la razon por la cual la orden no sera descargada: ");
						descrip="ORDEN CAMBIO A ESTADO NO DESCARGADO("+textoobs+") ";
						}
						ajax4=getXMLObject();
						ajax4.open("POST","ControlDescOC",true); //getname will be the servlet name
						ajax4.onreadystatechange  = function(){
							if (ajax4.readyState == 4) {
								alert(ajax4.responseText);
								window.location.reload();
							}
						}
						;
						ajax4.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
						ajax4.send("valor=CambEstO&textoobs="+textoobs+"&descrip="+descrip+"&codoc="+codoc+"&user="+user+"&esto="+esto);
					
					}else{}
				}
			}
			ajax3.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax3.send("valor=VerifOrden&codoc="+codoc+"&user="+user);
			
		
	}else{
		if(validos==0){
			var user=document.getElementById("user").value;
			ajax3=getXMLObject();
			ajax3.open("POST","ControlDescOC",true); //getname will be the servlet name
			ajax3.onreadystatechange  = function(){
				if (ajax3.readyState == 4) {
					var dat=ajax3.responseText;
					if(dat==1){
						esto=4;
						descrip="ORDEN CAMBIO A ESTADO EJECUTADO ";
						opc=confirm("Esta orden pasara a un estado de EJECUTADO y es irreversible este cambio ¿Esta seguro?");
					}else{
						if(dat==0){
							esto=5;
							opc=confirm("Esta orden pasara a un estado de NO DESCARGADO y es irreversible este cambio ¿Esta seguro?");
						}
					}
					
					if(opc){
						var textoobs="";
						if(dat==0){
						textoobs=prompt("Digite la razon por la cual la orden no sera descargada: ");
						descrip="ORDEN CAMBIO A ESTADO NO DESCARGADO("+textoobs+") ";
						}
						ajax4=getXMLObject();
						ajax4.open("POST","ControlDescOC",true); //getname will be the servlet name
						ajax4.onreadystatechange  = function(){
							if (ajax4.readyState == 4) {
								alert(ajax4.responseText);
								window.location.reload();
							}
						}
						;
						ajax4.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
						ajax4.send("valor=CambEstO&textoobs="+textoobs+"&descrip="+descrip+"&codoc="+codoc+"&user="+user+"&esto="+esto);
					
					}else{}
				}
			}
			ajax3.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax3.send("valor=VerifOrden&codoc="+codoc+"&user="+user);
		}else{
			if(tp>0){
				ajax.open("POST","ControlDescOC",true); //getname will be the servlet name
				ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {
						cont.innerHTML=ajax.responseText;
					}
				}
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=TestBodega&productos="+productos+"&cantidades="+cantidades+"&i="+i+"&codoc="+codoc+"&fecha="+fecha+"&dp="+dp+"&tp="+tp+"&BodFarmacia="+BodFarmacia+"&nofactura="+nofactura+"&coddets="+coddets); 
			}else{
				ajax1=getXMLObject();
				ajax1.open("POST","ControlDescOC",true); //getname will be the servlet name
				ajax1.onreadystatechange  = function(){
					if (ajax1.readyState == 4) {
				/*lo de abajo se hace  para conseguir la fecha de vencimiento, el lote y invima del medicamento */
						cont.innerHTML=ajax1.responseText;
					}
				};
				//alert("ajax1 "+fecha);
				ajax1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax1.send("valor=1&productos="+productos+"&cantidades="+cantidades+"&i="+i+"&codoc="+codoc+"&fecha="+fecha+"&dp="+dp+"&tp="+tp+"&BodFarmacia="+BodFarmacia+"&nofactura="+nofactura+"&coddets="+coddets+"&Bproducto=1&fechasp=1&lotesp=1"); 
				//GuardarPresdescargado(i,codoc,fecha,dp,productos,cantidades,BodFarmacia,BodProducto,tp,nofactura,coddets);
			}
		}
	}
}

function SelBodI(i,codoc,dp,tp,s,BodFarmacia,verif){
	//alert("entrando");
	var cont=document.getElementById("desord");
	var BProducto=document.getElementById("BodInv").value;
	var productos=document.getElementById("productos").value;
	var cantidades=document.getElementById("cantidades").value;
	var nofact=document.getElementById("nofactura").value;
	var fecha=document.getElementById("fecha").value;
	var nofactura=encodeURIComponent(nofact);
	var coddets=document.getElementById("coddets").value;
	/*alert(BProducto);
	alert(" prod "+productos);
	alert(" nofact "+nofact);
	alert(" nofactura "+nofactura);
	alert(" coddets "+coddets);*/
	//alert(s);
	var lotp="";
	var lotesp="";
	var fep="";
	var fechasp="";
	var vali=0;
	for(k=1;k<s;k++){
		/*lotp=document.getElementById("lot1").value;
		alert(lotp);*/
		lotp=document.getElementById("lot"+k).value;
		lotesp=lotp+"_"+lotesp;
		fep=document.getElementById("fvenc"+k).value;
		fechasp=fep+"_"+fechasp;
		var rrr=document.getElementById("vercant"+k).value
		var omi=0;
		if((lotp=="")||(fep=="")){
			if(lotp==""){
				if(rrr=="OMITIDO"){
					
				}else{
					alert("No ha digitado el lote de la fila No."+k);
					vali=1;
				}
			}else{
				if(rrr=="OMITIDO"){
					
				}else{
				alert("No ha digita la fecha vencimiento de la fila No."+k)
				vali=1;
				}
			}
		}
		
		if(rrr=="OMITIDO"){
			omi=omi;
		}else{
			omi=omi+1;
		}
	}
	
	if(BProducto=="0"){
			alert("NO HA SELECCIONADO UNA BODEGA VALIDA ");
	}else{
		if(vali!=1){
				if(omi==0){
					alert("No es necesario que seleccione una Bodega. Pulse el boton Continuar ");
				}else{
						var opc=confirm("Desea seleccionar esta Bodega ?");
						if(opc){
							//alert("s "+s);
							//alert("i "+i);
							if((verif==1)||(verif==3)){
							ajax.open("POST","ControlDescOC",true); //getname will be the servlet name
							ajax.onreadystatechange  = function(){
								if (ajax.readyState == 4) {
									cont.innerHTML=ajax.responseText;
								}
							}
							/*lo de abajo se hace  para conseguir la fecha de vencimiento, el lote y invima del medicamento */
							//alert("ajax /// "+fecha);
							//alert(" // Bproducto "+BProducto);
							ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
							ajax.send("valor=1&productos="+productos+"&cantidades="+cantidades+"&i="+i+"&codoc="+codoc+"&fecha="+fecha+"&dp="+dp+"&tp="+tp+"&BodFarmacia="+BodFarmacia+"&nofactura="+nofactura+"&coddets="+coddets+"&Bproducto="+BProducto+"&fechasp="+fechasp+"&lotesp="+lotesp); 
							}else{
								if(verif==2){
									//Cuando la orden no tiene medicamentos tambien cargados. Se va a guardar enseguida. 
									GuardarPresdescargado(i,codoc,fecha,dp,productos,cantidades,BodFarmacia,BProducto,tp,nofactura,coddets,fechasp,lotesp,"","","");
								}
							}
						}else{}
				}
		}
	}
	
}


function ContinuarDesc(i,codoc,dp,tp,s,BodFarmacia,verif){
	//alert("entrando ContinuarDesc - valor de verif"+verif);
	var cont=document.getElementById("desord");
	var BProducto=document.getElementById("BodInv").value;
	var productos=document.getElementById("productos").value;
	var cantidades=document.getElementById("cantidades").value;
	var nofact=document.getElementById("nofactura").value;
	var fecha=document.getElementById("fecha").value;
	var nofactura=encodeURIComponent(nofact);
	var coddets=document.getElementById("coddets").value;
	/*alert(BProducto);
	alert(" prod "+productos);
	alert(" nofact "+nofact);
	alert(" nofactura "+nofactura);
	alert(" coddets "+coddets);*/
	//alert(s);
	var lotp="";
	var lotesp="";
	var fep="";
	var fechasp="";
	var vali=0;
	for(k=1;k<s;k++){
		/*lotp=document.getElementById("lot1").value;
		alert(lotp);*/
		lotp=document.getElementById("lot"+k).value;
		lotesp=lotp+"_"+lotesp;
		fep=document.getElementById("fvenc"+k).value;
		fechasp=fep+"_"+fechasp;
		var rrr=document.getElementById("vercant"+k).value
		var omi=0;
		if((lotp=="")||(fep=="")){
			if(lotp==""){
				if(rrr=="OMITIDO"){
					
				}else{
					alert("No ha digitado el lote de la fila No."+k);
					vali=1;
				}
			}else{
				if(rrr=="OMITIDO"){
					
				}else{
				alert("No ha digita la fecha vencimiento de la fila No."+k)
				vali=1;
				}
			}
		}
		
		if(rrr=="OMITIDO"){
			omi=omi;
		}else{
			omi=omi+1;
		}
	}
	
	if(omi==0){			
		if(verif==2){//solo productos
			//alert("entrando a verif 2 ");
				//Cuando la orden no tiene medicamentos tambien cargados. Se va a guardar enseguida. 
				GuardarPresdescargado(i,codoc,fecha,dp,productos,cantidades,BodFarmacia,BProducto,tp,nofactura,coddets,fechasp,lotesp,"","","");
		}else{
			if(verif==3){//productos y medicamentos
			 
				ajax.open("POST","ControlDescOC",true); //getname will be the servlet name
				ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {
						cont.innerHTML=ajax.responseText;
					}
				}
				/*lo de abajo se hace  para conseguir la fecha de vencimiento, el lote y invima del medicamento */
				//alert("ajax "+fecha);
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=1&productos="+productos+"&cantidades="+cantidades+"&i="+i+"&codoc="+codoc+"&fecha="+fecha+"&dp="+dp+"&tp="+tp+"&BodFarmacia="+BodFarmacia+"&nofactura="+nofactura+"&coddets="+coddets+"&Bproducto="+BProducto+"&fechasp="+fechasp+"&lotesp="+lotesp); 
				
			}
		}
	}else{
		alert("Debe seleccionar una Bodega");
	}
	
	
}










function GuardarPresdescargado(i,codoc,fecha,dp,productos,cantidades,BodFarmacia,BodProducto,tp,nofactura,coddets,fechasp,lotesp,fechas,lotes,invimas){
	/*Esta funcion me va a llevar  los datos para guardar en la plantilla de contabilidad
	* en farc_inventario, si es un medicamento o dispositivo, de lo contario se guardara en inv_producto
	* y se va a crear una lista de  entradas pendientes por act de recepcion. 
	* 
	*/
	alert("Entrando a guardarPresdescargado");
	var user=document.getElementById("user").value;
	ajax.open("POST","ControlDescOC",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			var verif=ajax.responseText;
			var elem=verif.split("_");
			var tip=elem[0];//verif
			var consecFarm=elem[1];
			var consecInv=elem[2];
			alert("valor de verif"+verif);
			if((tip==1)||(tip==2)||(tip==3)){
				alert("Orden Descargada ! ");
				if(tip==1){ //solo medicamentos y dispositivos
					ajax1=getXMLObject();
					ajax1.open("POST","ControlDescOC",true);
					ajax1.onreadystatechange  = function(){
						if (ajax1.readyState == 4) {
							window.location.reload();
							pagina="farc_ReporteEntradas.jsp?movi="+ajax1.responseText;			
						   	var opciones="toolbar=no, location=no, directories=no, status=yes, menubar=yes,";
						 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
						   	window.open(pagina,"NUEVA",opciones);
						}
					}
					ajax1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					ajax1.send("valor=MovFarmacia&consecFarm="+consecFarm);
				
				}else{
					if(tip==2){//otros productos
							ajax1=getXMLObject();
							ajax1.open("POST","ControlDescOC",true);
							ajax1.onreadystatechange  = function(){
								if (ajax1.readyState == 4) {
									window.location.reload();
									pagina="inv_ReporteEntradas.jsp?movi="+ajax1.resposeText;			
								   	var opciones="toolbar=no, location=no, directories=no, status=yes, menubar=yes,";
								 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
								   	window.open(pagina,"NUEVA",opciones);
								}
							}
							ajax1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
							ajax1.send("valor=MovProducto&concecInv="+consecInv);
						
					}else{
						if(tip==3){//med, disp y otros productos 
							ajax1=getXMLObject();
							ajax1.open("POST","ControlDescOC",true);
							ajax1.onreadystatechange  = function(){
								if (ajax1.readyState == 4) {
									alert("Orden Descargada!!")
									window.location.reload();
									pagina="farc_ReporteEntradas.jsp?movi="+ajax1.resposeText;			
								   	var opciones="toolbar=no, location=no, directories=no, status=yes, menubar=yes,";
								 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
								   	window.open(pagina,"NUEVA1",opciones);
								}
							}
							ajax1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
							ajax1.send("valor=MovFarmacia&consecFarm="+consecFarm);
						
							
							ajax2=getXMLObject();
							ajax2.open("POST","ControlDescOC",true);
							ajax2.onreadystatechange  = function(){
								if (ajax2.readyState == 4) {
									pagina="inv_ReporteEntradas.jsp?movi="+ajax2.resposeText;			
								   	var opciones="toolbar=no, location=no, directories=no, status=yes, menubar=yes,";
								 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
								   	window.open(pagina,"NUEVA2",opciones);
								}
							}
							ajax2.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
							ajax2.send("valor=MovProducto&concecInv="+consecInv);
						}
					}
				}
				
			}
			
		}
	}
	alert("valor=GpreOC&i="+i+"&codoc="+codoc+"&fecha="+fecha+"&dp="+dp+"&productos="+productos+"&cantidades="+cantidades+"&BodFarmacia="+BodFarmacia+"&BodProducto="+BodProducto+"&tp="+tp+"&nofactura="+nofactura+"&user="+user+"&coddets="+coddets+"&fechasp="+fechasp+"&lotesp="+lotesp+"&fechas="+fechas+"&lotes="+lotes+"&invimas="+invimas);
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=GpreOC&i="+i+"&codoc="+codoc+"&fecha="+fecha+"&dp="+dp+"&productos="+productos+"&cantidades="+cantidades+"&BodFarmacia="+BodFarmacia+"&BodProducto="+BodProducto+"&tp="+tp+"&nofactura="+nofactura+"&user="+user+"&coddets="+coddets+"&fechasp="+fechasp+"&lotesp="+lotesp+"&fechas="+fechas+"&lotes="+lotes+"&invimas="+invimas);
}

function verifinv(j,ii,h){
	/** verificacion del codigo invima 
	 * j Es el numero de veces que recorre el para en el controlador inicializado en cero
	 *  ii es el numero de productos -1 
	 *  h  es el identificador para capturar las cajas de texto inicializado en 1 
	 */
	var inv="";
	var invimas="";
	var lot="";
	var lotes="";
	var fech="";
	var fechas=""
	var vali=0;
	for(k=1;k<h+1;k++){
		inv=document.getElementById("invima"+k).value;
		invimas=inv+"_"+invimas;
		lot=document.getElementById("lot"+k).value;
		lotes=lot+"_"+lotes;
		fech=document.getElementById("fvenc"+k).value;
		//alert(fech);
		fechas=fech+"_"+fechas;
		if((inv=="")||(lot=="")||(fechas=="")){
			if(inv==""){
				alert("No ha digitado el codigo Invima de la Fila "+k);
				k=ii-1;
				vali=1;
			}else{
				if(lot==""){
					alert("No ha digitado el lote de la Fila "+k);
					vali=1;
				}else{
					alert("No ha digitado la fecha de vencimiento de la fila "+k);
					vali=1;
				}
			}
		}
	
	}
	//var invima=document.getElementById("invima"+);
	var rest=ii-h;
	var restt=h-j;
	//alert("valor de rest"+rest);
	//alert("valor de restt"+restt);
	if(rest==1){
		if(restt==1){
			if(vali==1){
				alert("No ha seleccionado todo los datos ");
			}else{
				var productos=document.getElementById("productos").value;
				var cantidades=document.getElementById("cantidades").value;
				var nofactura=document.getElementById("nofactura").value;
				var coddets=document.getElementById("coddets").value;
				var i=document.getElementById("i").value;
				var codoc=document.getElementById("codoc").value;
				var fecha=document.getElementById("fecha").value; //fecha descargue 
				var dp=document.getElementById("dp").value;
				var tp=document.getElementById("tp").value;
				var BodFarmacia=document.getElementById("BodFarmacia").value;
				var BodProducto=document.getElementById("BProducto").value;
				var fechasp=document.getElementById("fechasp").value;
				var lotesp=document.getElementById("lotesp").value;
				alert("Entrando vv BPRODUCTO"+BodProducto+" fecha"+fecha);
				GuardarPresdescargado(i,codoc,fecha,dp,productos,cantidades,BodFarmacia,BodProducto,tp,nofactura,coddets,fechasp,lotesp,fechas,lotes,invimas);
				
			}
			
		}
	}else{
		if(vali==1){
			alert("no ha seleccionado todos los datos ");
		}else{	
			var vista=document.getElementById("boton");
			 productos=document.getElementById("productos").value;
			 cantidades=document.getElementById("cantidades").value;
			nofactura=document.getElementById("nofactura").value;
			coddets=document.getElementById("coddets").value;
			i=document.getElementById("i").value;
			codoc=document.getElementById("codoc").value;
			fecha=document.getElementById("fecha").value; //fecha descargue 
			 dp=document.getElementById("dp").value;
			 tp=document.getElementById("tp").value;
			 BodFarmacia=document.getElementById("BodFarmacia").value;
			 BodProducto=document.getElementById("BProducto").value;
			 fechasp=document.getElementById("fechasp").value;
			 lotesp=document.getElementById("lotesp").value;
			 var user=document.getElementById("user").value;
			alert("#"+fecha);
			alert("# BodProducto"+BodProducto+" fechasp"+fechasp);
			 Visor(i,codoc,fecha,dp,productos,cantidades,BodFarmacia,BodProducto,tp,nofactura,coddets,fechasp,lotesp,fechas,lotes,invimas,user,h);
			
		}
	}
}


function Visor(i,codoc,fecha,dp,productos,cantidades,BodFarmacia,BodProducto,tp,nofactura,coddets,fechasp,lotesp,fechas,lotes,invimas,user,h){
	 
	 var vista=document.getElementById("boton");
	// vista.innerHTML="PROBANDO";
	alert("BodProducto"+BodProducto);
	alert("valor=Oclic&i="+i+"&codoc="+codoc+"&fecha="+fecha+"&dp="+dp+"&productos="+productos+"&cantidades="+cantidades+"&BodFarmacia="+BodFarmacia+"&BodProducto="+BodProducto+"&tp="+tp+"&nofactura="+nofactura+"&user="+user+"&coddets="+coddets+"&fechasp="+fechasp+"&lotesp="+lotesp+"&fechas="+fechas+"&lotes="+lotes+"&invimas="+invimas+"&h="+h);
	ajax=getXMLObject();
	 ajax.open("POST","ControlDescOC",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				vista.innerHTML=ajax.responseText;
			}
		};
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Oclic&i="+i+"&codoc="+codoc+"&fecha="+fecha+"&dp="+dp+"&productos="+productos+"&cantidades="+cantidades+"&BodFarmacia="+BodFarmacia+"&BodProducto="+BodProducto+"&tp="+tp+"&nofactura="+nofactura+"&user="+user+"&coddets="+coddets+"&fechasp="+fechasp+"&lotesp="+lotesp+"&fechas="+fechas+"&lotes="+lotes+"&invimas="+invimas+"&h="+h);
}

function pp(){
	/*Artimaña...*/
	alert("entrando");
	var productos=document.getElementById("productos").value;
	var cantidades=document.getElementById("cantidades").value;
	var nofactura=document.getElementById("nofactura").value;
	var coddets=document.getElementById("coddets").value;
	var i=document.getElementById("i").value;
	var codoc=document.getElementById("codoc").value;
	var fecha=document.getElementById("fecha").value; //fecha descargue 
	var dp=document.getElementById("dp").value;
	var  tp=document.getElementById("tp").value;
	var BodFarmacia=document.getElementById("BodFarmacia").value;
	var  BodProducto=document.getElementById("BProducto").value;
	 var fechasp=document.getElementById("fechasp").value;
	 var lotesp=document.getElementById("lotesp").value;
	 var user=document.getElementById("user").value;
	 var fechas=document.getElementById("fechas").value;
	 var lotes=document.getElementById("lotes").value;
	 var invimas=document.getElementById("invimas").value;
	 var h=document.getElementById("h").value;
	
	 var validacion=0;
	 alert("valor de h"+h);
	 for(k=1;k<h;k++){
		 alert("valor de h dentro del para "+h)
			inv=document.getElementById("invima"+k).value;
			lot=document.getElementById("lot"+k).value;
			fech=document.getElementById("fvenc"+k).value;
			 var rrr=document.getElementById("vercantt"+k).value
				if((lot=="")){
						if(rrr=="OMITIDO"){
							
						}else{
							alert("No ha digitado el lote de la fila No."+k);
							validacion=1;
						}
				}else{
					if(inv==""){
						if(rrr=="OMITIDO"){
							
						}else{
							alert("No ha digitado el codigo invima de la fila No."+k);
							validacion=1;
						}
					}else{
						if(fech==""){
							if(rrr=="OMITIDO"){
							}else{
							alert("No ha digita la fecha vencimiento de la fila No."+k)
							validacion=1;
							}
						}
					}
						
				}
		alert("valor de k dentro del para "+k);
	 }//fin para 
	
	 if(validacion==1){
		 alert("No puede continuar hasta que todos los datos esten ingresados");
	 }else{
	 GuardarPresdescargado(i,codoc,fecha,dp,productos,cantidades,BodFarmacia,BodProducto,tp,nofactura,coddets,fechasp,lotesp,fechas,lotes,invimas);
	 }
}

var fechafilter=/^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
function checkfecha(c){
	var e=document.getElementById("fvenc"+c).value;
	var returnval=fechafilter.test(e)
	if (returnval==false){
		alert("La fecha es Invalida");
		document.getElementById("fvenc"+c).value = "";
		document.getElementById("fvenc"+c).focus();	
	}
return returnval
}


var patron = new Array(2,2,4);
function masca(d,pat,nums,dias,mes,annio){
var sep="/";
if(d.valant != d.value){
	val = d.value
	largo = val.length
	ini=val.substring(0,2);
	if((ini>31)||(ini=="00")){
		alert("Dia No Valido!!!");
		val=d.value="";
		d.focus();
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
		alert("Mes No Valido!!!");
		val2=d.value="";
		d.focus();
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
	///////////////////////////////////////////////////
	//Validar fecha mayor que la fecha actual
		
	if(ano.length==4){
		if (ano==annio) {
			if(ini==mes){
				if((dia==dias)){
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
		
		//document.getElementById("concepto").focus();
	}
	
	///////////////////////////////////////////////////
	
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



