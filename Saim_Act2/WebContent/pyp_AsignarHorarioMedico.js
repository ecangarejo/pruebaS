function getXMLObject()  //XML OBJECT
{
   var xmlHttp = false;
   try {
     xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");  // For Old Microsoft Browsers
   }
   catch (e) {
     try {
       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");  // For Microsoft IE 6.0+
     }
     catch (e2) {
       xmlHttp = false;   // No Browser accepts the XMLHTTP Object then false
     }
   }
   if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
     xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
   }
   return xmlHttp;  // Mandatory Statement returning the ajax object created
}
var xmlhttp = new getXMLObject(); //xmlhttp holds the ajax object
//////////////////////////////////////////////////////////////////////////////////////////////////////////
function CHKLunes(){	
	var fecha="";
	var cF=document.getElementById("txtContadorFecha").value;
	var oscar=cF;
	var diasSemana = new Array("Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sabado");
	var dias;
	if(cF!=1){
		for(var i=0; i<=cF-1; i++){
			if(form1.chkHorarioMedico[i]){
						fecha=form1.chkHorarioMedico[i].value;
						var f=new Date(fecha);
						dias=diasSemana[f.getDay()];						
						if(form1.checkboxL.checked){
							if(dias=="Lunes"){
								form1.chkHorarioMedico[i].checked=true;
							}
						}else{
							if(dias=="Lunes"){
								form1.chkHorarioMedico[i].checked=false;
							}
						}
					}
			}//fin del si de los dias seleccionados
			oscar--;
		}//fin del for de las fechas	
	}

function CHKMartes(){	
	var fecha="";
	var cF=document.getElementById("txtContadorFecha").value;
	var oscar=cF;
	var diasSemana = new Array("Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sabado");
	var dias;
	if(cF!=1){
		for(var i=0; i<=cF-1; i++){
			if(form1.chkHorarioMedico[i]){
						fecha=form1.chkHorarioMedico[i].value;
						var f=new Date(fecha);
						dias=diasSemana[f.getDay()];						
						if(form1.checkboxM.checked){
							if(dias=="Martes"){
								form1.chkHorarioMedico[i].checked=true;
							}
						}else{
							if(dias=="Martes"){
								form1.chkHorarioMedico[i].checked=false;
							}
						}
					}
			}//fin del si de los dias seleccionados
			oscar--;
		}//fin del for de las fechas	
	}

function CHKMiercoles(){	
	var fecha="";
	var cF=document.getElementById("txtContadorFecha").value;
	var oscar=cF;
	var diasSemana = new Array("Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado");
	var dias;
	if(cF!=1){
		for(var i=0; i<=cF-1; i++){
			if(form1.chkHorarioMedico[i]){
						fecha=form1.chkHorarioMedico[i].value;
						var f=new Date(fecha);
						dias=diasSemana[f.getDay()];						
						if(form1.checkboxMI.checked){
							if(dias=="Miercoles"){
								form1.chkHorarioMedico[i].checked=true;
							}
						}else{
							if(dias=="Miercoles"){
								form1.chkHorarioMedico[i].checked=false;
							}
						}
					}
			}//fin del si de los dias seleccionados
			oscar--;
		}//fin del for de las fechas	
	}

function CHKJueves(){	
	var fecha="";
	var cF=document.getElementById("txtContadorFecha").value;
	var oscar=cF;
	var diasSemana = new Array("Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sabado");
	var dias;
	if(cF!=1){
		for(var i=0; i<=cF-1; i++){
			if(form1.chkHorarioMedico[i]){
						fecha=form1.chkHorarioMedico[i].value;
						var f=new Date(fecha);
						dias=diasSemana[f.getDay()];						
						if(form1.checkboxJ.checked){
							if(dias=="Jueves"){
								form1.chkHorarioMedico[i].checked=true;
							}
						}else{
							if(dias=="Jueves"){
								form1.chkHorarioMedico[i].checked=false;
							}
						}
					}
			}//fin del si de los dias seleccionados
			oscar--;
		}//fin del for de las fechas	
	}

function CHKViernes(){	
	var fecha="";
	var cF=document.getElementById("txtContadorFecha").value;
	var oscar=cF;
	var diasSemana = new Array("Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sabado");
	var dias;
	if(cF!=1){
		for(var i=0; i<=cF-1; i++){
			if(form1.chkHorarioMedico[i]){
						fecha=form1.chkHorarioMedico[i].value;
						var f=new Date(fecha);
						dias=diasSemana[f.getDay()];						
						if(form1.checkboxV.checked){
							if(dias=="Viernes"){
								form1.chkHorarioMedico[i].checked=true;
							}
						}else{
							if(dias=="Viernes"){
								form1.chkHorarioMedico[i].checked=false;
							}
						}
					}
			}//fin del si de los dias seleccionados
			oscar--;
		}//fin del for de las fechas	
	}

function CHKSabado(){	
	var fecha="";
	var cF=document.getElementById("txtContadorFecha").value;
	var oscar=cF;
	var diasSemana = new Array("Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sabado");
	var dias;
	if(cF!=1){
		for(var i=0; i<=cF-1; i++){
			if(form1.chkHorarioMedico[i]){
						fecha=form1.chkHorarioMedico[i].value;
						var f=new Date(fecha);
						dias=diasSemana[f.getDay()];						
						if(form1.checkboxS.checked){
							if(dias=="Sabado"){
								form1.chkHorarioMedico[i].checked=true;
							}
						}else{
							if(dias=="Sabado"){
								form1.chkHorarioMedico[i].checked=false;
							}
						}
					}
			}//fin del si de los dias seleccionados
			oscar--;
		}//fin del for de las fechas	
	}
	

function CHKAM(){
	var hora="";
	var cH=document.getElementById("txtContadorHora").value;
	var oscar=cH;
	var Jorna;
	if(cH!=1){
		for(var k=0;k<=cH-1;k++){
			if(form1.ChkHora[k]){
				hora=form1.ChkHora[k].value;
				var y=hora.split(":").length;
				var z=hora.split(":");		     	
				for(var x=0; x<y-1; x=x+1){
					Jorna=z[2];					
				}
				
				if(form1.checkboxAM.checked){
					if(Jorna=="AM"){
						form1.ChkHora[k].checked=true;
					}
				}else{
					if(Jorna=="AM"){
						form1.ChkHora[k].checked=false;
					}
				}
				//prueba(fecha,hora);
			}
		}//fin del for de las horas					
		oscar--;
	}
}

function CHKMD(){
	var hora="";
	var cH=document.getElementById("txtContadorHora").value;
	var oscar=cH;
	var Jorna;
	if(cH!=1){
		for(var k=0;k<=cH-1;k++){
			if(form1.ChkHora[k]){
				hora=form1.ChkHora[k].value;
				var y=hora.split(":").length;
				var z=hora.split(":");		     	
				for(var x=0; x<y-1; x=x+1){
					Jorna=z[2];					
				}
				
				if(form1.checkboxMD.checked){
					if(Jorna=="MD"){
						form1.ChkHora[k].checked=true;
					}
				}else{
					if(Jorna=="MD"){
						form1.ChkHora[k].checked=false;
					}
				}
				//prueba(fecha,hora);
			}
		}//fin del for de las horas					
		oscar--;
	}
}

function CHKPM(){
	var hora="";
	var cH=document.getElementById("txtContadorHora").value;
	var oscar=cH;
	var Jorna;
	if(cH!=1){
		for(var k=0;k<=cH-1;k++){
			if(form1.ChkHora[k]){
				hora=form1.ChkHora[k].value;
				var y=hora.split(":").length;
				var z=hora.split(":");		     	
				for(var x=0; x<y-1; x=x+1){
					Jorna=z[2];					
				}
				
				if(form1.checkboxPM.checked){
					if(Jorna=="PM"){
						form1.ChkHora[k].checked=true;
					}
				}else{
					if(Jorna=="PM"){
						form1.ChkHora[k].checked=false;
					}
				}
				//prueba(fecha,hora);
			}
		}//fin del for de las horas					
		oscar--;
	}
}

function CHKAME(){
	var hora="";
	var cH=document.getElementById("txtCon").value;
	var oscar=cH;
	var Jorna;
	if(cH!=1){
		for(var k=0;k<=cH-1;k++){
			if(form1.ChkHora[k]){
				hora=form1.ChkHora[k].value;
				var y=hora.split(":").length;
				var z=hora.split(":");		     	
				for(var x=0; x<y-1; x=x+1){
					Jorna=z[2];					
				}
				
				if(form1.checkboxAM.checked){
					if(Jorna=="AM"){
						form1.ChkHora[k].checked=true;
					}
				}else{
					if(Jorna=="AM"){
						form1.ChkHora[k].checked=false;
					}
				}
				//prueba(fecha,hora);
			}
		}//fin del for de las horas					
		oscar--;
	}
}

function CHKMDE(){
	var hora="";
	var cH=document.getElementById("txtCon").value;
	var oscar=cH;
	var Jorna;
	if(cH!=1){
		for(var k=0;k<=cH-1;k++){
			if(form1.ChkHora[k]){
				hora=form1.ChkHora[k].value;
				var y=hora.split(":").length;
				var z=hora.split(":");		     	
				for(var x=0; x<y-1; x=x+1){
					Jorna=z[2];					
				}
				
				if(form1.checkboxMD.checked){
					if(Jorna=="MD"){
						form1.ChkHora[k].checked=true;
					}
				}else{
					if(Jorna=="MD"){
						form1.ChkHora[k].checked=false;
					}
				}
				//prueba(fecha,hora);
			}
		}//fin del for de las horas					
		oscar--;
	}
}

function CHKPME(){
	var hora="";
	var cH=document.getElementById("txtCon").value;
	var oscar=cH;
	var Jorna;
	if(cH!=1){
		for(var k=0;k<=cH-1;k++){
			if(form1.ChkHora[k]){
				hora=form1.ChkHora[k].value;
				var y=hora.split(":").length;
				var z=hora.split(":");		     	
				for(var x=0; x<y-1; x=x+1){
					Jorna=z[2];					
				}
				
				if(form1.checkboxPM.checked){
					if(Jorna=="PM"){
						form1.ChkHora[k].checked=true;
					}
				}else{
					if(Jorna=="PM"){
						form1.ChkHora[k].checked=false;
					}
				}
				//prueba(fecha,hora);
			}
		}//fin del for de las horas					
		oscar--;
	}
}

function EliminarHorasDiasSeleccionada(){
	var FechaSelEliminar=document.getElementById("FechaSeleccionada").value;
	var CodigoMedico=document.getElementById('cmbMedico').value;
	var Man="0";var Med="0";var Tar="0";
	var AM=document.getElementById("checkboxAM");
	if(AM.checked){Man="AM";}
	var MD=document.getElementById("checkboxMD");
	if(MD.checked){Med="MD";}
	var PM=document.getElementById("checkboxPM");
	if(PM.checked){Tar="PM";}
	
	var x = confirm("Desea Eliminar los registro?");
	if (x) {
		ajax=getXMLObject();
		ajax.open("POST","PYP_ControlParametros",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				//VerDetalleHorario(FechaMes,CodigoMedico);
				CargarVerDiaSeleccionado();
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=EDHM2&Man="+Man+"&Med="+Med+"&Tar="+Tar+"&FechaSelEliminar="+FechaSelEliminar+"&CodigoMedico="+CodigoMedico);
	}else{}
}



function EliminarHora(CodHorMed,CodigoMedico,FechaMes){
	if(CodHorMed=="na"){
		alert("No se puede anular registro, tiene una cita asignada.");
	}else{
		if(CodHorMed=="at"){
			alert("No se puede anular registro, el paciente esta siendo atendido.");
		}else{
			if(CodHorMed=="fa"){
				alert("No se puede anular registro, la atencion esta finalizada.");
			}else{
				if(CodHorMed=="ff"){
					alert("No se puede anular registro, fecha anterior a la actual.");
				}else{
					var x = confirm("Desea Eliminar el registro?");
					if (x) {
						ajax=getXMLObject();
						ajax.open("POST","PYP_ControlParametros",true);
						ajax.onreadystatechange = function(){
							if(ajax.readyState == 4){
								VerDetalleHorario(FechaMes,CodigoMedico);
							}
						};
						ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
						ajax.send("valor=EDC&CodHorMed="+CodHorMed);
					}else{}
				}
			}
		}
	}
}

function EliminarDCS(){
	ajax=getXMLObject();
	var CodigoMedico=document.getElementById('cmbMedico').value;
	if(CodigoMedico=="Seleccione"){
		alert("Seleccione Un Medico.");
	}
	if(CodigoMedico!="Seleccione"){
		ajax.open("POST","PYP_ControlParametros",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				document.getElementById("Formulario").innerHTML = ajax.responseText;
				document.getElementById("barra").innerHTML = ''	;
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=EDCS&CodigoMedico="+CodigoMedico);
	}

}
function EliminarDS(){
	ajax=getXMLObject();
	var CodigoMedico=document.getElementById('cmbMedico').value;
	if(CodigoMedico=="Seleccione"){
		alert("Seleccione Un Medico.");
	}
	if(CodigoMedico!="Seleccione"){
		ajax.open("POST","PYP_ControlParametros",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				document.getElementById("Formulario").innerHTML = ajax.responseText;
				document.getElementById("barra").innerHTML = ''	;
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=EDS&CodigoMedico="+CodigoMedico);
	}

}

function BorrarDiv(){
	document.getElementById("Formulario").innerHTML = '';
	document.getElementById("barra").innerHTML = ''	;
}

function EliminarDia(CodigoMedico,Dia,Jornada){
	//alert("CodigoMedico "+CodigoMedico+" Dia "+Dia+" Jornada "+Jornada);
	var x = confirm("Solo se eliminaran los registros que no tienen movimiento. Desea Continuar?");
	if (x) {
	ajax=getXMLObject();
	ajax.open("POST","PYP_ControlParametros",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(" Eliminado satisfactoriamente.");
			EliminarDS();
			//document.getElementById("Formulario").innerHTML = ajax.responseText;
			//document.getElementById("barra").innerHTML = ''	;
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=EDSOK&CodigoMedico="+CodigoMedico+"&Dia="+Dia+"&Jornada="+Jornada);
	}else{
		
	}
}
function CargaAgendaMedico(){
	ajax=getXMLObject();
	var CodigoMedico=document.getElementById('cmbMedico').value;
	if(CodigoMedico=="Seleccione"){
		alert("Seleccione Un Medico.");
	}
	if(CodigoMedico!="Seleccione"){
		ajax.open("POST","PYP_ControlParametros",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				document.getElementById("Formulario").innerHTML = ajax.responseText;
				document.getElementById("barra").innerHTML = ''	;
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VHM&CodigoMedico="+CodigoMedico);
	}
}

function CargarVerDiaSeleccionado(){
	var DiaSeleccionado=document.getElementById("FechaSeleccionada").value;
	var CodigoMedico=document.getElementById('cmbMedico').value;
	var FechaClick=document.getElementById("FechaClick").value;
	ajax=getXMLObject();
	ajax.open("POST","PYP_ControlParametros",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById("barra").innerHTML = ajax.responseText;
			document.getElementById("FechaSeleccionada").value=DiaSeleccionado;
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=VDSHM&CodigoMedico="+CodigoMedico+"&FechaSel="+DiaSeleccionado+"&FechaClick="+FechaClick);

}

function VerDiaSeleccionado(){
	var DiaSeleccionado=document.getElementById("cmbFechaEl").value;
	var CodigoMedico=document.getElementById('cmbMedico').value;
	var FechaClick=document.getElementById("FechaClick").value;
	ajax=getXMLObject();
	ajax.open("POST","PYP_ControlParametros",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById("barra").innerHTML = ajax.responseText;
			document.getElementById("FechaSeleccionada").value=DiaSeleccionado;
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=VDSHM&CodigoMedico="+CodigoMedico+"&FechaSel="+DiaSeleccionado+"&FechaClick="+FechaClick);

}


function VerDetalleHorario(AnMe,CodigoMedico){
	var FechaSeleccionada=document.getElementById("FechaSeleccionada").value;
	ajax=getXMLObject();
	ajax.open("POST","PYP_ControlParametros",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById("FechaClick").value=AnMe;
			document.getElementById("barra").innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=VDHM&CodigoMedico="+CodigoMedico+"&FechaMes="+AnMe);
	
}


function GuardarHorarioNV(){
	var DiaI=document.getElementById("cmbDia").value;
	var MesI=document.getElementById("cmbMes").value;
	var AnioI=document.getElementById("cmbAnio").value;
	var FechaI=AnioI+"-"+MesI+"-"+DiaI;
	
	var DiaF=document.getElementById("cmbDiaF").value;
	var MesF=document.getElementById("cmbMesF").value;
	var AnioF=document.getElementById("cmbAnioF").value;
	var FechaF=AnioF+"-"+MesF+"-"+DiaF;
	
	var HorI=document.getElementById("cmbHoraI").value;
	var MinuI=document.getElementById("cmbMinutosI").value;
	var JornI=document.getElementById("cmbJornadaI").value;
	var HoraI=HorI+":"+MinuI+":"+JornI;
	
	var HorF=document.getElementById("cmbHoraF").value;
	var MinuF=document.getElementById("cmbMinutosF").value;
	var JornF=document.getElementById("cmbJornadaF").value;
	var HoraF=HorF+":"+MinuF+":"+JornF; 
	
	var lunes=document.getElementById("checkboxL");
	var martes=document.getElementById("checkboxM");
	var miercoles=document.getElementById("checkboxMI");
	var jueves=document.getElementById("checkboxJ");
	var viernes=document.getElementById("checkboxV");
	var sabado=document.getElementById("checkboxS");
	var domingo=document.getElementById("checkboxD");
	
	var e=document.getElementById("chkMedCex");
	var lu="";var mar="";var mier="";var jue="";var vier="";var sab="";var dom="";
	
	if(lunes.checked)    {lu="Monday";}else{lu="0";}
	if(martes.checked)   {mar="Tuesday";}else{mar="0";}
	if(miercoles.checked){mier="Wednesday";}else{mier="0";}
	if(jueves.checked)   {jue="Thursday";}else{jue="0";}
	if(viernes.checked)  {vier="Friday";}else{vier="0";}
	if(sabado.checked)   {sab="Saturday";}else{sab="0";}
	if(domingo.checked)  {dom="Sunday";}else{dom="0";}
	
	alert("FechaI="+FechaI+" FechaF="+FechaF+" HoraI="+HoraI+" HoraF="+HoraF);
	alert("lu="+lu+" mar="+mar+" mier="+mier+" jue="+jue+" vier="+vier+" sab="+sab+" dom="+dom);
	
	
}


function horario(){
	ajax=getXMLObject();
	var contenido=document.getElementById('Horario');
	var CodigoMedico=document.getElementById('cmbMedico').value;
	if(CodigoMedico=="Seleccione"){
		alert("Seleccione Un Medico.");
	}
	if(CodigoMedico!="Seleccione"){
		ajax.open("POST","PYP_ControlParametros",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				contenido.innerHTML = ajax.responseText;	
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=asignar&CodigoMedico="+CodigoMedico);
	}
}
function SgtMes(){
	//alert("SgtMes");
	ajax=getXMLObject();
	var contenido=document.getElementById('Horario');
	var CodigoMedico=document.getElementById('cmbMedico').value;
	if(CodigoMedico=="Seleccione"){
		alert("Seleccione Un Medico.");
	}
	if(CodigoMedico!="Seleccione"){
		ajax.open("POST","PYP_ControlParametros",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				contenido.innerHTML = ajax.responseText;	
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=sgt&mon=1");
	}
	
	
}
function fecha(){
	  var time1 = new Date();
	  var ano = time1.getFullYear();
	  var mes = time1.getMonth();
	  mes=mes+1;
	  var dia = time1.getDate();
	  var temp1 = "" + ((ano < 10) ? "0" : "") + ano;
	  temp1 += ((mes < 10) ? "-0" : "-") + mes;
	  temp1 += ((dia < 10) ? "-0" : "-") + dia;
	  document.getElementById('txtFecha').value = temp1;
	  id = setTimeout("fecha()",1000);
	  }

function CargarFormulario(){
	//var Formulario=document.getElementById('Formulario');
	ajax=getXMLObject();
	ajax.open("POST","PYP_ControlParametros",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			document.getElementById('Formulario').innerHTML = ajax.responseText;	
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=horario");
	
}
/*function prueba (FechaMedico,HorasMedico) {
	ajax=getXMLObject();
	var CodigoMedico=document.getElementById('cmbMedico').value;
	ajax.open("POST","ControlParametros",true);
	document.getElementById('barra').innerHTML='<p><img src="Imagenes/ani.gif"></p>';
	ajax.onreadystatechange=function() {
		if (ajax.readyState == 4) {
			// Fin
			document.getElementById('barra').innerHTML='Completado.';
			var resu = ajax.responseText;
			alert(resu);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	ajax.send("valor=guardar&CodigoMedico="+CodigoMedico+"&HorasMedico="+HorasMedico+"&FechaMedico="+FechaMedico);
}
*/
function GuardarHorario(FechaMedico,HorasMedico){
	//alert("valor=guardar&HorasMedico="+HorasMedico+"&FechaMedico="+FechaMedico);
	var CodigoMedico=document.getElementById('cmbMedico').value;
	document.getElementById('barra').innerHTML='<p>Ingresando Datos...No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	//document.getElementById('barra').innerHTML='<p>Ingresando Datos...No Cierre La Ventana..</p>';
	ajax=getXMLObject();
	ajax.open("POST","PYP_ControlParametros",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById('barra').innerHTML='Completado.';
			var resu = ajax.responseText;
			document.getElementById('barra').innerHTML='<p>Datos Ingresados Satisfactoriamente.</p>';
			//alert(resu);
			//window.location.reload();
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=guardar&CodigoMedico="+CodigoMedico+"&HorasMedico="+HorasMedico+"&FechaMedico="+FechaMedico);
}

function Chkdato(){	
	
	var fecha="";
	var hora="";
	var cF=document.getElementById("txtContadorFecha").value;
	var cH=document.getElementById("txtContadorHora").value;
	//alert("cF="+cF+" cH="+cH);
	var oscar=cF;
	if(cF!=1){
		for(var i=0; i<=cF-1; i++){
			if(form1.chkHorarioMedico[i].checked){
				for(var k=0;k<=cH-1;k++){
					if(form1.ChkHora[k].checked){
						fecha=form1.chkHorarioMedico[i].value;
						hora=form1.ChkHora[k].value;
						GuardarHorario(fecha,hora);		
						//prueba(fecha,hora);
					}
				}//fin del for de las horas					
			}//fin del si de los dias seleccionados
			oscar--;
		}//fin del for de las fechas	
	} 
}

function checkAll() {
	 var i;
 var nodoCheck = document.getElementsByTagName("input");
 var varCheck = document.getElementById("chkTodos").checked;
 for (i=0; i<nodoCheck.length; i++){
     if (nodoCheck[i].type == "checkbox" && nodoCheck[i].name != "all" && nodoCheck[i].disabled == false) {
         nodoCheck[i].checked = varCheck;
     }
 } 
}