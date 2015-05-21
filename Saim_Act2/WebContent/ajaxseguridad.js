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
/////////////////////////////////////////////////////////////////////////////////
function CargarMenuInicio(CodUsuario ,pend,p){
	ajax=getXMLObject();
	var contenido=document.getElementById('menu');	
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText;
			if(pend>2){
				if(p==0){
				CargarAlertas(CodUsuario, pend);
				}else{
					if(pend>15){
					
						CargarAlertas(CodUsuario, pend);
					}
				}
				}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Menus&CodigoUsuario="+CodUsuario);
}


function Val24h(CodUsuario){
	ajax=getXMLObject();
	//alert("VAL24H");
	var contenido=document.getElementById('Pacientes');
	document.getElementById('Pacientes').innerHTML='<p>	Buscando Pacientes... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML=ajax.responseText;
			//alert(ajax.responseText);
		}
	}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Val24h&CodigoUsuario="+CodUsuario);
}

function VerMultiplesP(CodigoUsuario){
	
	var total=document.getElementById('txtCont').value;
	var cont=total;	
	var cadena ="";
	var k=0;
	var revision=0;
	if(total!=1){
		for(var h=0; h<=total-1; h++){			
			if(document.form1.chkPac[h].checked){
				CodPac=document.form1.chkPac[h].value;
				cadena = cadena+document.form1.chkPac[h].value;
	           	cadena = cadena +"|";	
	           	k=k+1;
			}
			cont--;			
		}
		if(k<=3){
			registro(CodigoUsuario,cadena);	
			//alert("verificando cadena"+cadena);
			window.location.href="hic_SeleccionarPacientes.jsp?pacientes="+cadena;			
		}else{
			alert("Solo Puede Visualizar 3 Pacientes Al Tiempo.");
		}
		if(k==0){
			alert("No Ha Seleccionado Ningun Paciente");
			//window.location.href="hic_BuscarPacientes.jsp";
		}
	}
	else{
		if(document.form1.chkPac.checked){
			cadena = cadena+document.form1.chkPac.value;
			//registro(cadena,CodigoUsuario);	
			//alert("verificando cadena22"+cadena);
           	cadena = cadena +"|";
           //registro(cadena,CodigoUsuario);	
           	//window.location.href="hic_SeleccionarPacientes.jsp?pacientes="+cadena;
		}
	}
	
}

function registro(CodigoUsuario,cadena){
	ajax=getXMLObject();
	//alert("registro"+cadena);
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);
		}
	}
	
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=registro&CodigoUsuario="+CodigoUsuario+"&cadena="+cadena);
}

function CargarAlertas(CodUsuario, pend){	
	var co=0;
	//alert(pend);
	//alert(CodUsuario);
	ajax=getXMLObject();
	//var CodUsuario=document.getElementById("txtusuario").value;
	//var contenido=document.getElementById('menu');
	//alert("cargar alertas");
	//alert(pend);
	//alert(CodUsuario);
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText
			
			co=ajax.responseText;
				if(co==1){
				window.location.href="menu.jsp?co="+co;
				}else{
					alert(ajax.responseText);
					MostrarForPend(CodUsuario, pend);
				}
			
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Alerta&CodigoUsuario="+CodUsuario);
}

function MostrarForPend(CodUsuario, pend){
	
	window.location.href="datos.jsp?CodUsuario="+CodUsuario+"&pend="+pend;

}

function onloaddatosl(CodUsuario, pend){
	
	ajax=getXMLObject();
	//var CodUsuario=document.getElementById("txtusuario").value;
	var contenido=document.getElementById('Listaformatos');	
	//alert("onloaddatosl");
	//alert(pend);
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML=ajax.responseText;
			//alert(ajax.responseText);
		}
	
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Alerta2&CodigoUsuario="+CodUsuario+"&pend="+pend);
}

function ValRadioButtom(CodUsuario , CodPac , CodAdm, CodFor,  Hora, Fecha,CodForPac, pend){

	ajax=getXMLObject();
	var RBF = document.getElementsByName("accion");
	var Valor=null;
	var Cont=0;
	//alert("valbutton");
	//alert(CodUsuario);
	//alert(pend);
	for (var i=0; i < RBF.length; i ++){
		
		if(RBF[i].checked){
			Valor=RBF[i].value;}
		if(Valor==null)
		{  Cont=Cont+1;
		}
	}
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		if(Valor==1){
			window.location.href=("hic_preliminarhc.jsp?usuario="+CodUsuario+"&CodFormato="+CodFor+"&fecha="+Fecha+"&hora="+Hora+"&CodPac="+CodPac+"&CodAdmision="+CodAdm);
		}
		else{
			if(Valor==2){ 
				
					var CodAdmision=CodAdm;
					var CodFormato=CodFor;
					var usuario=CodUsuario;
					GuardarFormato(CodAdmision,CodFormato,CodPac,usuario,pend,CodForPac,Fecha,Hora);
				
			}else{
				if(Valor==3){
					ClearFor(CodForPac,CodUsuario, pend);
				}
			}
		}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Datos&CodigoUsuario="+CodUsuario);

}


function GuardarFormato(CodAdmision,CodFormato,CodPac,usuario,pend,CodForPac,Fecha,Hora){
	var opcion=confirm("Desea Guardar Este Formato?");
	if(opcion){
	/*var FechaFormato=dia+"-"+mes+"-"+anio;
	var HoraFormato=horas+":"+minutos+":"+segundos;
	var CodUsuario=document.getElementById("txtCodusuario").value;*/
	ajax=getXMLObject();	
	ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;
			if(validar!=""){
				alert(validar);
			}
			else{
				window.location.href=("hic_BuscarPacientes.jsp");
			}
			//document.getElementById('').innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=12&CodigoFormatoPaciente="+CodForPac+"&FechaFormato="+Fecha+"&HoraFormato="+Hora+"&CodAdmision="+CodAdmision+"&CodUsuario="+usuario+"&CodFormato="+CodFormato+"&CodUsuario2="+usuario); //Posting txtname to Servlet
	}else{}
}



function guardarf(CodAdmision,CodFormato, CodPac, usuario,pend,CodForPac){
	ajax=getXMLObject();
	var opcion=confirm("Desea Guardar Este Formato?");
	if(opcion){
	ajax.open("POST","PermisosPagina",true); 
	ajax.onreadystatechange  = function(){
		if(ajax.readyState == 4){
			alert("Formato Guardado Con Exito");
			//alert(pend);
			//alert(usuario);
			if(pend==0){
			window.location.href=("hic_BuscarPacientes.jsp");
			}else{
				CargarAlertas(usuario, pend);
			}
		}
	}
	
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
	ajax.send("valor=SaveFor&CodAdmision="+CodAdmision+"&CodFormato="+CodFormato+"&CodPac="+CodPac+"&usuario="+usuario+"&CodForPac="+CodForPac); //Posting txtname to Servlet  
	}else{}
	}



function ClearFor(CodForPac,CodUsuario, pend){
	ajax=getXMLObject();
	var opcion=confirm("Desea eliminar Formato?");
	if(opcion){
	
		ajax.open("POST","PermisosPagina",true);
		ajax.onreadystatechange=function(){
			if(ajax.readyState == 4){
					alert("Formato Eliminado Con Exito");
						window.location.href=("hic_BuscarPacientes.jsp");
						
			}
		}
	
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=EliminarFor&CodForPac="+CodForPac);
		
	}else{}
}

function ValButtonMen(CodUsuario, CodRemi, CodMen){

	ajax=getXMLObject();
	var RBF = document.getElementsByName("Act");
	var Valor=null;
	var Cont=0;
	for (var i=0; i < RBF.length; i ++){
		
		if(RBF[i].checked){
			Valor=RBF[i].value;}
		if(Valor==null)
		{  Cont=Cont+1;
		}
	}
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		 if(Valor==1){
			 AbrirMensaje(CodUsuario, CodRemi,CodMen);}
		 else{
			if(Valor==2){
			EliminarMen(CodUsuario, CodRemi,CodMen);}
		}
	   }
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Datos&CodigoUsuario="+CodUsuario);

}

function ValButtonChat(CodUsuario){
	ajax=getXMLObject();
	//alert("valor=ListUsu&CodigoUsuario="+CodUsuario);
	var contenido=document.getElementById('oculto');
	var val=2;
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ListUsu&CodigoUsuario="+CodUsuario+"&val="+val);
}

function prueba(CodUsuario){
	ajax=getXMLObject()
	//alert("entrando a prueba");
	var RBF = document.getElementsByName("chat");
	var Valor=0;
	var Cont=0;
	for (var i=0; i < RBF.length; i ++){
		
		if(RBF[i].checked){
			Valor=RBF[i].value;}
	}
	//alert(RBF);
	//alert(Valor);
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			if(Valor==0){
				AbrirChat(CodUsuario);
			}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Datos&CodigoUsuario="+CodUsuario);
	
}

function AbrirChat(CodUsuario){
	
	ajax=getXMLObject();
	//alert("valor=OpenChat&CodUsuario="+CodUsuario);
	var contenido=document.getElementById('ChatNuevo');	
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		//	alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OpenChat&CodUsuario="+CodUsuario);
}




function HistorialChat(CodUsuario){
	ajax=getXMLObject();
	var contenido=document.getElementById('ChatNuevo');
	var conte2=document.getElementById('Listausuarios');
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange=function(){
	if(ajax.readyState == 4){
		contenido.innerHTML=ajax.responseText;
		conte2.innerHTML="";
	}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Hchat&CodUsuario="+CodUsuario);
}

function buscarchatrec(ano, codusuario){
	
	ajax=getXMLObject();
	var anos=document.getElementById('ano');
	if(anos.selectedIndex == -1){
		return;
		}
	valor =anos.Value;
	txt = anos.options[anos.selectedIndex].text;
	tanos=txt;
	var dia=document.getElementById('dia');
	if(dia.selectedIndex == -1){
		return;
		}
	valor =dia.Value;
	txt = dia.options[dia.selectedIndex].text;
	tdia=txt;
	var mes=document.getElementById('mes');
	if(mes.selectedIndex == -1){
		return;
		}
	valor =mes.Value;
	txt = mes.options[mes.selectedIndex].text;
	tmes=txt;
	var tmeses=0;
	if((tmes=="---")||(tdia=="---")||(tanos=="--")){
		alert("No ha seleccionado todos los Datos ");
	}else{
		if(tmes=="Enero"){
			tmeses=1;
		}else{
			if(tmes=="Febrero"){
				tmeses=2;
			}else{
				if(tmes=="Marzo"){
					tmeses=3;
					}else{
						if(tmes=="Abril"){
						 tmeses=4;
						}else{
							if(tmes=="Mayo"){
								 tmeses=5;
								}else{
									if(tmes=="Junio"){
										 tmeses=6;
										}else{
											if(tmes=="Julio"){
												 tmeses=7;
												}else{
													if(tmes=="Agosto"){
														 tmeses=8;
														}else{
															if(tmes=="Septiembre"){
																 tmeses=9;
																}else{
																	if(tmes=="Octubre"){
																		 tmeses=10;
																		}else{
																			if(tmes=="Noviembre"){
																				 tmeses=11;
																				}else{
																					if(tmes=="Diciembre"){
																						 tmeses=12;
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
			}
		}
	

		
				
	//alert(tanos);
	//alert(tdia);
	//alert(tmes);
	var contenido=document.getElementById('Historial');
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.reposenText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OpenChatH&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&codusuario="+codusuario);
}

function OpenHistChat(CodRemiChat, codusuario, tanos, tdia, tmeses){
	ajax=getXMLObject();
	var contenido=document.getElementById('VistaHist');
	var conte2=document.getElementById('Listausuarios');
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML=ajax.responseText;
			conte2.innerHTML="";
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=VistaChatHist&CodRemiChat="+CodRemiChat+"&codusuario="+codusuario+"&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia);
}

function MostrarListMen(CodUsuario)
{		ajax=getXMLObject();
	var contenido=document.getElementById('ListaMensajes');	
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
	if(ajax.readyState == 4){
		contenido.innerHTML=ajax.responseText;
	}
}
ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
ajax.send("valor=ListMen&CodigoUsuario="+CodUsuario);
}

function EliminarMen(CodUsuario, CodRemi, CodMen){
	ajax=getXMLObject();
	
	var opcion=confirm("Desea Eliminar Este Mensaje?");
	if(opcion){
	ajax.open("POST","PermisosPagina",true); 
	//alert("valor=ElimMens&CodUsuario="+CodUsuario+"&CodRemi="+CodRemi+"&CodMen="+CodMen);
	ajax.onreadystatechange  = function(){
		if(ajax.readyState == 4){
			alert("Mensaje Eliminado Con Exito");
			window.location.href=("mensajes.jsp");
		}
	}
	
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
	ajax.send("valor=ElimMens&CodUsuario="+CodUsuario+"&CodRemi="+CodRemi+"&CodMen="+CodMen);  
	}else{}
	}

function NuevoMen(CodUsuario)
{
	ajax=getXMLObject();
	var contenido=document.getElementById('ListaMensajes');
	var cont2=document.getElementById('Listausuarios');
	var cont3=document.getElementById('VistaMensajes');
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
	if(ajax.readyState == 4){
		contenido.innerHTML=ajax.responseText;
		cont2.innerHTML="";
		cont3.innerHTML="";
	}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=NuevoMens&CodigoUsuario="+CodUsuario);
}

function GuardarResp(){
	ajax=getXMLObject();
	var codremi=document.getElementById("codremi").value;
	var asunto=document.getElementById("txtasunto").value;
	var contmen=document.getElementById("contmen").value;
	var codu=document.getElementById("coduser").value;
	var opcion=confirm("Desea Enviar Este Mensaje?");
	if(opcion){
		if((asunto=="")||(codremi=="")||(contmen=="")){
				alert("No estan llenos todos los campos");
		}else{
			//alert("valor=EnviaMen&asunto="+asunto+"&contmen="+contmen+"&codu="+codu+"&remi="+codremi);
			ajax.open("POST","PermisosPagina",true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					alert("Mensaje Enviado !");
					window.location.href=("mensajes.jsp");
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=EnviaMen&asunto="+asunto+"&contmen="+contmen+"&codu="+codu+"&codremi="+codremi);	
		}	
}else{}
}

function respondermen(CodRemi,CodMen,CodUsuario,asunto,contenido,fecha,hora){
	ajax=getXMLObject();
	//alert("valor=ResMen&CodUsuario="+CodUsuario+"&CodRemi="+CodRemi+"&CodMen="+CodMen+"=&asunto="+asunto+"&contenido="+contenido+"&fecha="+fecha+"&hora="+hora);
	var conte=document.getElementById('ListaMensajes');
	var conte2=document.getElementById('VistaMensajes');
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			conte.innerHTML=ajax.responseText;
			conte2.innerHTML="";
			
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ResMen&CodUsuario="+CodUsuario+"&CodRemi="+CodRemi+"&CodMen="+CodMen+"&contenido="+contenido+"&asunto="+asunto+"&fecha="+fecha+"&hora="+hora);
}
function maxiLong(texto,maxlong){
	
	var tecla, in_value, out_value;

	if (texto.value.length > maxlong) {
	in_value = texto.value;
	out_value = in_value.substring(0,maxlong);
	texto.value = out_value;
	return false;
	}
	return true;
}
function MostrarListUsu(CodUsuario,val)
{		ajax=getXMLObject();
		
	var contenido=document.getElementById('Listausuarios');
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
	if(ajax.readyState == 4){
		contenido.innerHTML=ajax.responseText;
		
	}

}
ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
ajax.send("valor=ListUsu&CodigoUsuario="+CodUsuario+"&val="+val);
}

function AutocompletarRemi(){
	var NomRemi=document.getElementById("txtRemi").value;
	var lista=document.getElementById('ResultadoRemi');
	ajax=getXMLObject();
	ajax.open("POST","PermisosPagina",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			lista.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=AutoRemi&NomRemi="+NomRemi);
	
}
var co=0;
function LlenarRemi(CodRemi, Cont){
	 var Remi=document.getElementById("txtRemi");
	 var codremi=document.getElementById("txtcodremi")
	 var lista=document.getElementById('ResultadoRemi');
	 
	 var EnvRemi=null;
	 ajax=getXMLObject();
	 ajax.open("POST","PermisosPagina",true); 
	 ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {	
				Remi.value=ajax.responseText;
				co=co+1;
				codremi.value=CodRemi;
				EnvRemi=CodRemi;
			}
			if(EnvRemi!=null){
				lista.innerHTML="";
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=LlenaRemi&CodRemi="+CodRemi);	
}
 var cerrar=0;
 var act=null;
function chat(codremi, CodUsuario){
	ajax=getXMLObject();
	clearInterval(act);
	var contenido=document.getElementById('VistaChat');
	var conten=document.getElementById('ChatNuevo');
	var cont2=document.getElementById('Listausuarios');
	ajax.open("POST","PermisosPagina",true);
	
	ajax.onreadystatechange = function(){
	if(ajax.readyState == 4){
		contenido.innerHTML=ajax.responseText;
		conten.innerHTML="";
		cont2.innerHTML="";
		
		//window.setInterval(Actualizar,10000);
		 act = setTimeout("chat("+codremi+","+CodUsuario+")",10000);
		
		//window.clearInterval("Actualizar("+codremi+","+CodUsuario+")");
		var i=2;
		for(i=0;i>=2;i++){
		if(i==2){
			clearInterval(act);
			alert(act);
			alert(codremi);
			alert(CodUsuario);
			}
		}
	}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=chat&codremi="+codremi+"&CodUsuario="+CodUsuario);
	
}



function LimpiarChat(codremi,CodUsuario){
	ajax=getXMLObject();
	//var contenido=document.getElementById('VistaChat');
	var opcion=confirm("Esta seguro de borrar su conversacion?");
	if(opcion){
		ajax.open("POST","PermisosPagina",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				//alert("valor=LimpiarChat&CodUsuario="+CodUsuario+"&codremi="+codremi); 
				clearInterval(act);
				chat(codremi,CodUsuario);
				//contenido.innerHTML=ajax.responseText;
				
			}
		}
		ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
		ajax.send("valor=LimpChat&codremi="+codremi+"&CodUsuario="+CodUsuario);  	
	}else{}
}

function EnviarDialogo(CodUsuario, codremi)
{	ajax=getXMLObject();
	var mensaje=document.getElementById("txtmensaje").value;
	var contenido=document.getElementById('VistaChat');
	//var contenido=document.getElementById('Dialogo');
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML=ajax.responseText;	}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=GuardarDial&codremi="+codremi+"&CodUsuario="+CodUsuario+"&mensaje="+mensaje);
}


function cerrardialogo(codremi, CodUsuario,cond){
	ajax=getXMLObject();
	var contenido=document.getElementById('VistaChat');
	var conten=document.getElementById('ChatNuevo');
	var opcion=confirm("Desea Cerrar el Chat?");
	if(opcion){
		ajax.open("POST","PermisosPagina",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
			contenido.innerHTML=ajax.responseText;
			conten.innerHTML=ajax.responseText;
			 clearInterval(act);			
			}
			}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=cerrarchat=&CodUsuario="+CodUsuario+"&cond="+cond);
		}
}

function GuardarMen(){
	ajax=getXMLObject();
	var codremi=document.getElementById("txtcodremi").value;
	var asunto=document.getElementById("txtasunto").value;
	var contmen=document.getElementById("contmen").value;
	var codu=document.getElementById("coduser").value;
	var opcion=confirm("Desea Enviar Este Mensaje?");
	if(opcion){
		if((asunto=="")||(codremi=="")||(contmen=="")){
				alert("No estan llenos todos los campos");
		}else{
			//alert("valor=EnviaMen&asunto="+asunto+"&contmen="+contmen+"&codu="+codu+"&remi="+codremi);
			ajax.open("POST","PermisosPagina",true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					alert("Mensaje Enviado !");
					window.location.href=("mensajes.jsp");
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=EnviaMen&asunto="+asunto+"&contmen="+contmen+"&codu="+codu+"&codremi="+codremi);	
		}	
}
}

function AbrirMensaje(CodUsuario, CodRemi, CodMen){
	ajax=getXMLObject();
	var contenido=document.getElementById('VistaMensajes');	
	ajax.open("POST","PermisosPagina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OpenMen&CodUsuario="+CodUsuario+"&CodRemi="+CodRemi+"&CodMen="+CodMen);
}

		
		
function opciones(h){
	xmlhttp.open("POST","ControlMenu?codusu="+h+"",true); //getname will be the servlet name
    xmlhttp.onreadystatechange  = Menu;
    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
    xmlhttp.send(""); //Posting txtname to Servlet
}

function tf(){
	pagina="acerca.html";			
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones =opciones+"scrollbars=si, resizable=no, width=480, height=520, top=85,  left=140";
	window.open(pagina,"NUEVA",opciones);		
}
		
	
function seguridad_login(){	
	if(xmlhttp) {
		var usuario=document.getElementById("txtusuario").value;
		var contrasena=document.getElementById("contrasena").value
		if(usuario==""){
			alert("Digite Usuario.");
		}else{
			if(contrasena==""){
				alert("Digite Contraseña.");
			}
		}
		if((usuario!="")&&(contrasena!="")){
		//var usuario=document.login.user.value;
		//var contrasena=document.login.contrasena.value;
		xmlhttp.open("POST","Seguridad_login?accion="+1+"&id="+contrasena+"&usu="+usuario+"",true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = Seguridad;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); 	
		}
	}
	var x;
}
 
function ajaxmodulo() {
	if(xmlhttp) { 	 
		xmlhttp.open("POST","ControlModulo?va=1",true); //getname will be the servlet name
		xmlhttp.onreadystatechange  = Comprobar;
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send(""); //Posting txtname to Servlet
	}
	var x;
}

var cont=0;
function mostrardiv() {
	div = document.getElementById('principal');
	div.style.display = "";
}

function cambiarmodulo(h) {
	mostrardiv();
	cont=cont+1;
	if(xmlhttp) {
		var cod1=h.cbarea.selectedIndex;
		var area1=h.cbarea.options[cod1].text;
		var cod=h.disponible.selectedIndex;
		var nomarea=h.disponible.options[cod].text;
	    xmlhttp.open("POST","Modulos2?va="+2+"&area="+nomarea+"&area1="+area1+"",true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = Comprobararea;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); 
	}
	var x;
	     
}

function mostrar_tabla(h) {
	if(xmlhttp) {
		var cod=h.txtCodigoRefernciaPresupuestoBuscar.value;
		if(cod==""){
			alert("Digite Cedula del Usuario...!");
		}else{
			div = document.getElementById('yosi');
			e = document.getElementById('Eliminar');
			i = document.getElementById('Insertar');
			c = document.getElementById('Cambiar');
			div.style.display = "";
			e.style.display = "";
			i.style.display = "";
			c.style.display = "";

			xmlhttp.open("POST","Busquedad?ced="+cod+"",true); //getname will be the servlet name
			xmlhttp.onreadystatechange  = comprobarbusqueda;
			xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			xmlhttp.send("");
		}
	    
	}
	var x;
	     
}


function cerrar() {
	div = document.getElementById('principal');
	div.style.display='none';
	}

function cambiar(h) {
	cerrar();
	cont=cont+1;
	if(xmlhttp) {
		var cod=h.cbarea.selectedIndex;
		var nomarea=h.cbarea.options[cod].text;			
	    xmlhttp.open("POST","ControlModulo?va="+2+"&area="+nomarea+"",true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = Comprobararea2;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlhttp.send(""); 
	}
	var x;
}

function Comprobar() {
	if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			var a=xmlhttp.responseText;
			var y=a.split("_").length;
			var z=a.split("_");
			form1.cbarea.length=y;
			var h,ss;
			for(x=0; x<y-1; x=x+1)
			{ 
				form1.cbarea.options[x].text=z[x];
			}
		}
		else {
			alert("Error during AJAX call. Please try again");
		}
	}
}
function Comprobararea2() {
	if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			form1.disponible.length=0;
			var a=xmlhttp.responseText;
			var y=a.split("_").length;
			var z=a.split("_");
			form1.disponible.length=y;
			var h,ss;
			for(x=0; x<y-1; x=x+1)
			{ 
				form1.disponible.options[x].text=z[x];
			}
		}
		else {
			alert("Error during AJAX call. Please try again");
		}
	}
}

function Comprobararea() {	
	if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {	     	
			document.getElementById('yosimar').innerHTML = xmlhttp.responseText
		}
		else {
			alert("Error during AJAX call. Please try again");
		}
	}
}

function Seguridad() {
	if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {	  
			var val=xmlhttp.responseText;
			document.getElementById('seguridad').innerHTML = val;
			//var yo=document.getElementById('seguridad').innerHTML;
			if(val=="menu"){
				//se va directo a menu principal
				window.location.href="menu.jsp";
			}
			if(val=="hccx"){
				//muestra los dos entornos
				window.location.href="MenuAuxiliar.jsp";
			}
			if(val=="hosadm"){
				//muestra entorno de hospitalizacion y administracion
				window.location.href="hic_BuscarPacientes.jsp";
			}
			if(val=="cexadm"){
				//muestra entorno de consulta externa y administracion
				window.location.href="agm_ListaMedico.jsp";
			}
			 
		}
		else {
			alert("Error during AJAX call. Please try again");
		}
	} 
}

function comprobarbusqueda() {	
	if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {	     	
			document.getElementById('yosi').innerHTML = xmlhttp.responseText
		}
		else {
			alert("Error during AJAX call. Please try again");
		}
	}
}
function Menu() {
	if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			var a=xmlhttp.responseText;
		}
		else {
			alert("Error during AJAX call. Please try again");
		}
	}
}	

function ingresar(){
	var cedula=form1.txtCodigoRefernciaPresupuestoBuscar.value;
	window.location.href="permisos.jsp?ced="+cedula+"";
}

function salir_p(){	
	window.location.href="Seg_login.jsp";
}

