<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<br/>
<br/>
<script>
document.write("<center>PRUEBA DE UN CALENDARIO DINAMICO</center>");
</script>

<br/>
<br/>


<body>

<script type="text/javascript">

//Declaracion de variables globales
var diasMes = new Array(); /*Array con los meses del año*/
diasMes[0] = 31;
diasMes[1] = 28;
diasMes[2] = 31;
diasMes[3] = 30;
diasMes[4] = 31;
diasMes[5] = 30;
diasMes[6] = 31;
diasMes[7] = 31;
diasMes[8] = 30;
diasMes[9] = 31;
diasMes[10] = 30;
diasMes[11] = 31;

//Declaración de funciones globales
function Calendario()
{
/*Declaración de variables locales de la función*/
var meses = "EneFebMarAbrMayJunJulAgoSepOctNovDic"; //Variable que almacena el nombre parcial de los meses del año.
var hoy = new Date(); //Función predefinida que extrae el día actual para un variable.
var dia;
var anio; //Variable que almacena el año en curso.
var nDias; //Variable que almacena el número de días del mes extraido del Array diasMes
var columna; //Variable auxiliar de número de columnas.
var diaInicial; //Variable que guarda el día de la semana en la que empieza el mes.

/*Código de la función*/

anio=hoy.getYear();

if (navigator.appName == "Netscape")
{
anio += 1900; //En Netscape, para calcular el año actual se debe calcular desde 1900.
}

dia=hoy.getDate(); //Qué día del mes es hoy??
//alert("dia "+dia);
/*¿EL AÑO ACTUAL ES BISIESTO?*/
if(((anio%4==0) && (anio%100!=0))||(anio%400==0)) /*Compruebo que el año es bisiesto*/
{
diasMes[1] = 29; //y si lo es varío el número de días del mes de febrero en el array
}

/*Establecemos el número de días del mes en el que estamos*/
nDias = diasMes[hoy.getMonth()];
alert("nDias "+nDias);
/*Busca el primer día del mes*/
primerDia = hoy; //Hacemos un nuevo objeto basado en hoy, que es un objeto de fecha.
primerDia.setDate(1); //Establecemos el objeto de fecha en el día 1 de este mes.

/*Calcula qué día de la semana es el día 1 del mes*/
diaInicial = primerDia.getDay();

/*Lo imprimimos en la pantalla*/

//Cabecera de la tabla de calendario
document.writeln("<center>");
document.write("<table border>");
document.write("<tr><th colspan=7 align=center>");
document.write(meses.substring(hoy.getMonth()*3,(hoy.getMonth()+1)*3)); //Selecciona el fragmento donde está la abreviatura del mes
//en curso: establece primero el margen derecho de la forma que
//multiplica el valor de los meses por 3 y el izquierdo
//seleccionando la abreviatura siguiente por 3
document.write(". "); //Escribe el punto de separación
document.write(anio); //Escribe el año en la cabecera del calendario.

//Cuerpo del calendario
document.write("<tr><th align=center>Dom"); //fila cabecera con los días de la semana: domingo
document.write("<th align=center>Lun"); //Lunes
document.write("<th align=center>Mar"); //Martes
document.write("<th align=center>Mie"); //Miercoles
document.write("<th align=center>Jue"); //Jueves
document.write("<th align=center>Vie"); //Viernes
document.write("<th align=center>Sab"); //Sábado

//Escribe los espacios en blanco necesarios para cuadrar el día de la semana en la primera fila
document.write("<tr>"); //Generamos la nueva fila
columna=0; //Variable auxiliar en el bucle de inscripción de días en el calendario

for(i=0;i<diaInicial;i++) /*Bucle para la reserva de espacios de los días en el calendario*/
{
document.write("<td align=center><font size+=4>.");
columna++;
document.write("</font>");
}

for(i=1;i<=nDias;i++) /*Bucle para la inclusión del número de día en el calendario*/
{
document.write("<td align=center><input name='chkHorarioMedico' type='checkbox' id='chkHorarioMedico'/>");

if(i==dia) //Si el día a escribir es el día de hoy
{
document.write("<font color=\"#ff0000\" size+=7><b>"); //Resaltamos el día de hoy
}
document.write(i);

if(i==dia) //Si el día a escribir es el día de hoy
{
document.write("</b></font>"); //Cerramos la etiqueta de edición de las propiedades de las fuentes
}

columna++; //Pasamos a la columna siguiente.

if (columna==7) //Si ya tenemos siete columnas
{
document.write("<tr>"); //Abrimos una nueva fila
columna=0; //Reseteamos la variables de columnas.
}
}

document.write("</table>"); //Cerramos la tabla.
document.writeln("</center>");
}

/*CODIGO DEL SCRIPT*/

Calendario(); //Llamada a la función para generar el calendario.
document.write("<BR>");

</script>

<noscript>
<center>EL SCRIPT NO ES VALIDO PARA ESTE NAVEGADOR O NO ES COMPATIBLE CON SCRIPTS.</center>
</noscript>

</body>
</html>