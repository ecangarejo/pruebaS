package farc_controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.text.*;
import java.util.StringTokenizer;  //para dividir por token |

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import lab_logica.MetodolabPa;

import farc_metodo.MetodoCrearArticulo;
import farc_metodo.MetodoEntradas;


/**
 * Servlet implementation class ControlEntradas
 */
public class ControlEntradas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/xml; charset=ISO-8859-1");
		response.setContentType("text/html;charset=UTF-8");
		MetodoEntradas me = new MetodoEntradas();
		
		String va = request.getParameter("valor");
		String datos2 ="";
		String Ndatos ="";
		String texto=request.getParameter("texto");
		String cont = request.getParameter("cont");
		String nmov = request.getParameter("nmov");
		String tmov = request.getParameter("tmov");
		String canul = request.getParameter("canul");
		String R = request.getParameter("R");
		String sw = request.getParameter("sw");
		String fechas = request.getParameter("fechas");
		String documento = request.getParameter("documento");
		//String M2[][] = request.getParameter("m2"); //= request.getParameter("m2");
			
		PrintWriter out=response.getWriter();

		java.util.Date fechaS = new Date();
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		int m=Integer.parseInt(mes)+1;
		mes=String.valueOf(m);
		int d=Integer.parseInt(dia);
		if(d<10){dia="0"+d;}
		if(m<10){mes="0"+m;}
		
		if(cont==null){cont="0";}
		int conta = Integer.parseInt(cont);
		if (conta>9){conta=9;}
		
		datos2 = request.getParameter("datos2");
		if(datos2==null){datos2="";}
		if(conta==1){datos2="";}
		
		Ndatos = request.getParameter("Ndatos");
		if(Ndatos==null){Ndatos="";}
		//if(conta==1){datos2="";}
		
	
		String M[][] = new String[11][21];
		String MI[][] = new String[11][21];
		String V[] = new String[250];
		
		String fecha=dia+"/"+mes+"/"+annio;
		//****************************************
		//Este es el llamado para la mascara de fechas
		//onKeyup='masca(this,patron,true)'
			
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		ResultSet rs7=null;

		/////ANTIGUO MODULO DE ENTRADAS
	
		/*   
		
		if(va.equals("Entradas")){	
			String user=request.getParameter("user");
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Entrada de Articulos</div></td></tr>");
			out.print("<tr><td width='12%'>Bodega</td><td><label><select name='cmbBodega' id='cmbBodega'><option value='Seleccione'>Seleccione</option>");
			rs=me.ObtenerBodegas(user);
			try {
				while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td>");		
			
			out.print("<td width='13%'>Proveedor</td><td width='45%'><label><select name='cmbProveedor' id='cmbProveedor'><option value='Seleccione'>Seleccione</option>");
			rs1=me.ObtenerProveedor();
			try {
				while(rs1.next()){
				out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("</select></label></td></tr>");	
			//if(fecha==null){
			out.print("<tr><td>Numero de Factura o Remision</td><td><label><input name='txtFactura' type='text' id='txtFactura' size='40'/><td>Fecha</td><td width='45%'><label><input name='txtFecha' type='text' id='txtFecha' value='"+fecha+"' readonly='' size='40' /></label></td></tr>");
			out.print("<tr><td>Concepto</td><td><label><input name='txtConcepto' type='text' id='txtConcepto' size='40'/></td>");
			
		
			out.print("<td width='13%'>Tipo de Movimiento</td><td width='45%'><label><select name='cmbTipoM' id='cmbTipoM'  ><option value='Seleccione'>Seleccione</option>");
			rs3=me.listarMovimientose();
			try {
				while(rs3.next()){
				out.print("<option value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
				//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td></tr>");
	
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Generar Movimientos</div></td></tr>");
					
			out.print("<table width='100%' border='1' class='style6'><tr><td width='28%'><div align='center'>Articulo</div></td><td width='11%'><div align='center'>Lote</div></td><td width='11%'><div align='center'>Codigo Invima</div></td><td width='9%'><div align='center'>Vence</div></td><td width='8%'><div align='center'>Cantidad</div></td><td width='4%'><div align='center'>IVA </div></td><td width='13%'><div align='center'>Vlr Unitario</div></td><td width='16%'><div align='center'>Vlr Total </div></td></tr>");
			
			out.print("<td><input name='txtTipoMe0' type='text' id='txtTipoMe0' onkeyup='autocompletaArticulo(0,0)' size='39' />");//identificador Div+identificador campo
			out.print("<input name='txtTipoMeH0' type='hidden' id='txtTipoMeH0'/></td>");//identificador Div+identificador campo
			out.print("<td><label><input name='txtLote0' type='text' id='txtLote0' onFocus='checkart(0)' onkeyup='checkcara(0)' size='11'/></td>");
			out.print("<td><label><input name='txtInvima0' type='text' id='txtInvima0' onkeyup='checkcaras(0)' size='11'/></td>");
			out.print("<td><label><input name='txtVence0' type='text' id='txtVence0' onKeyup='masca(this,patron,true,"+dia+","+mes+","+annio+")' onBlur='checkfecha(0)' size='8'/></td>");
			out.print("<td><label><input name='txtCantidad0' type='text' id='txtCantidad0' onkeyup='checknum(0)'  onBlur='Repetir(0)'size='6'/></td>");
			out.print("<td><label><input name='txtIva0' type='text' id='txtIva0'  onkeyup='autocompletaIva(0,0)' onBlur='Repetir(0)' size='1'/>"); //
			out.print("<input name='txtIvaH0' type='hidden' id='txtIvaH0'/></td>");//identificador Div+identificador campo
			out.print("<td><label><input name='txtVunitario0' type='text' id='txtVunitario0' onFocus='checkiva(0)' onkeyup='checknum2(0)' onBlur='Repetir(0),Repetir2("+cont+",0,this.form)' size='15'/></td>");
			out.print("<td><label><input name='txtTotal0' type='text' id='txtTotal0' readonly='' onBlur='Repetir2("+cont+",0,this.form)' size='19'/></td>");//value='"+vt+"'
			
			out.print("<tr><td><div id='sugerencias10'></div></td><td></td><td></td><td></td><td></td><td><div id='sugerencias20'></div></td></tr>");
			
			
			out.print("</table>"); 
				out.print("<div id='repetir'><div id='boton'  align='center'><input type='button' name='btnD' id='btnD' value='    Ingresar    ' onClick='Repetir2N("+cont+",0,this.form)'></div></div>");
				//out.print("<table width='100%' class='style6'><tr><td colspan='2'><div id='boton'><input type='button' name='btnD' id='btnD' value='Dxxxxxxxxxxxxxx' onClick='Repetir2N("+cont+",0,this.form)'></td></div></td></tr>);
				
			//out.print("<tr><td colspan='4'>&nbsp;</td></tr>");
			
			//rs = me.ObtenerMovimiento(TipoM);
		}

		
		if(va.equals("EntradasRepetidas")){		
			//out.print("contador "+conta);
			
						
			String a0 = request.getParameter("a0");
			char s ;
			char s2='@';
			String ini="";
			String fin="";
		//	String s2 = s.substring(0,3) + "A" + s.substring(3);
			for(int q=0; q<a0.length();q++){
				s=a0.charAt(q);
				if(s==s2){//out.println("encontrado en "+q+" es "+s);
				ini=ini+"%";}else{
				ini=ini+s;
				}
			}

			
	
			String a2 = request.getParameter("a2");
			String a3 = request.getParameter("a3");
			String a1 = request.getParameter("a1");
			String a4 = request.getParameter("a4");
			String a5 = request.getParameter("a5");
			String a00 = request.getParameter("a00");
			String a6 = request.getParameter("a6");
			String a7 = request.getParameter("a7");
			String a8 = request.getParameter("a8");
			
			datos2=datos2+ini;datos2=datos2+"|";
			datos2=datos2+a2;datos2=datos2+"|";
			datos2=datos2+a3;datos2=datos2+"|";
			datos2=datos2+a1;datos2=datos2+"|";
			datos2=datos2+a4;datos2=datos2+"|";
			datos2=datos2+a5;datos2=datos2+"|";
			datos2=datos2+a00;datos2=datos2+"|";
			datos2=datos2+a6;datos2=datos2+"|";
			datos2=datos2+a7;datos2=datos2+"|";
			datos2=datos2+a8;datos2=datos2+"|";
			
			//out.print("Dtossss  "+datos2);
			
			int i1=0;
			//V[0] =   
			//i1++;
			  
			
			//llena los datos que provienen de repetir
			   StringTokenizer elementos;  
			   elementos = new StringTokenizer(Ndatos,"|"); 
			   
			   while(elementos.hasMoreTokens()){ 
				   V[i1] = elementos.nextToken();   
				   i1++;
			   }
			   
			  /* for(int z=0; z<i1; z++){
				   out.print(" V["+z+"]="+V[z]+"\n"); 
			   }*/
			/*   
			   int in=0;
			   for(int z=0; z<conta; z++){
				   for(int zz=0; zz<=9; zz++){
					   MI[z][zz]=V[in];
					   in++;
				   }
			   }
			   
			out.print("<table width='100%' border='1' class='style6'>");
			int ind=0;
			int cj=0;
			for(int i=0;i<conta;i++){
				//out.print("esta es i: "+i);
				int j=i+1;
				//out.print("JJJJJ"+j);
				for(int z=0; z<=9; z++){
				ind=z+(j*10);
				M[i][z]=V[ind];
				//out.print("fila "+(i)+" col"+z+" : "+M[i][z]);
				}
				
				
				String sug1="sugerencias1"+j;
				String sug2="sugerencias2"+j;
				String art="txtTipoMe"+j;
				String artH="txtTipoMeH"+j;
				String lot="txtLote"+j;
				String inv="txtInvima"+j;
				String vence="txtVence"+j;
				String ca="txtCantidad"+j;
				String iva="txtIva"+j;
				String ivaH="txtIvaH"+j;
				String vau="txtVunitario"+j;
				String vat="txtTotal"+j;
				String txtM="txtM"+j;
			//	out.print("txtM0 "+datos2+"  txtMJ: "+j);
			//	out.print("conta es igual a "+conta);
				
				if((conta>1)&&(i<(conta-1))){
				
				out.print("<td width='28%'><input name='"+art+"' type='text' id='"+art+"'  value='"+M[i][0]+"' onkeyup='autocompletaArticulo("+j+","+j+")' size='39' />");
				out.print("<input name='"+artH+"' type='hidden' id='"+artH+"' value='"+M[i][1]+"'/></td>");//identificador Div+identificador campo
				out.print("<td width='11%'><label><input name='"+lot+"' type='text' id='"+lot+"' value='"+M[i][2]+"' onFocus='checkart("+j+")' onkeyup='checkcara("+j+")' size='11'/></td>");
				out.print("<td width='11%'><label><input name='"+inv+"' type='text' id='"+inv+"' value='"+M[i][3]+"' onkeyup='checkcaras("+j+")' size='11'/></td>");
				out.print("<td width='9%'><label><input name='"+vence+"' type='text' id='"+vence+"' value='"+M[i][4]+"' onKeyup='masca(this,patron,true,"+dia+","+mes+","+annio+")' onBlur='checkfecha("+j+")' size='8'/></td>");
				out.print("<td width='8%'><label><input name='"+ca+"' type='text' id='"+ca+"' value='"+M[i][5]+"' onkeyup='checknum("+j+")' onBlur='Repetir("+j+")' size='6'/></td>");
				out.print("<td width='4%'><label><input name='"+iva+"' type='text' id='"+iva+"' value='"+M[i][6]+"' onkeyup='autocompletaIva("+j+","+j+")' onBlur='Repetir("+j+")' size='1'/>");
				out.print("<input name='"+ivaH+"' type='hidden' id='"+ivaH+"' value='"+M[i][7]+"'/></td>");//identificador Div+identificador campo
				out.print("<td width='13%'><label><input name='"+vau+"' type='text' id='"+vau+"' value='"+M[i][8]+"' onFocus='checkiva("+j+")'  onkeyup='checknum2("+j+")' onBlur='Repetir("+j+"),Repetir2("+conta+","+j+",this.form)' size='15'/></td>");
				out.print("<td width='16%'><label><input name='"+vat+"' type='text' '"+vat+"' readonly='' value='"+M[i][9]+"' onBlur='Repetir2("+conta+","+j+",this.form)' size='19'/></td>");
				out.print("<input name='"+txtM+"' type='hidden' id='"+txtM+"' value='"+Ndatos+"' /></td>");//identificador Div+identificador campo
				
				out.print("<tr><td><div id='"+sug1+"'></div></td><td></td><td></td><td></td><td></td><td><div id='"+sug2+"'></div></td></tr>");				
				
				}
				
				if((i==(conta-1)&&(i!=8))){
					
					out.print("<td width='28%'><input name='"+art+"' type='text' id='"+art+"' onkeyup='autocompletaArticulo("+j+","+j+")' size='39'/>");
					out.print("<input name='"+artH+"' type='hidden' id='"+artH+"'  /></td>");//identificador Div+identificador campo
					out.print("<td width='11%'><label><input name='"+lot+"' type='text' id='"+lot+"' onFocus='checkart("+j+")' onkeyup='checkcara("+j+")' size='11'/></td>");
					out.print("<td width='11%'><label><input name='"+inv+"' type='text' id='"+inv+"' onkeyup='checkcaras("+j+")' size='11'/></td>");
					out.print("<td width='9%'><label><input name='"+vence+"' type='text' id='"+vence+"' onKeyup='masca(this,patron,true,"+dia+","+mes+","+annio+")' onBlur='checkfecha("+j+")' size='8'/></td>");
					out.print("<td width='8%'><label><input name='"+ca+"' type='text' id='"+ca+"' onkeyup='checknum("+j+")' onBlur='Repetir("+j+")'  size='6'/></td>");
					out.print("<td width='4%'><label><input name='"+iva+"' type='text' id='"+iva+"' onkeyup='autocompletaIva("+j+","+j+")' onBlur='Repetir("+j+")' size='1'/>");
					out.print("<input name='"+ivaH+"' type='hidden' id='"+ivaH+"'/></td>");//identificador Div+identificador campo
					out.print("<td width='13%'><label><input name='"+vau+"' type='text' id='"+vau+"' onFocus='checkiva("+j+")'  onkeyup='checknum2("+j+")' onBlur='Repetir("+j+"),Repetir2("+conta+","+j+",this.form)' size='15'/></td>");
					out.print("<td width='16%'><label><input name='"+vat+"' type='text' '"+vat+"' readonly='' onBlur='Repetir2("+conta+","+j+",this.form)' size='19'/></td>");
					out.print("<input name='"+txtM+"' type='hidden' id='"+txtM+"' value='"+Ndatos+"' /></td>");//identificador Div+identificador campo
					
					out.print("<tr><td><div id='"+sug1+"'></div></td><td></td><td></td><td></td><td></td><td><div id='"+sug2+"'></div></td></tr>");				
				}
				if(i==8){
					for(int ss=0; ss<=9; ss++){ if (M[i][ss]==null){M[i][ss]="";}}
					out.print("<td width='28%'><input name='"+art+"' type='text' id='"+art+"' value='"+M[i][0]+"' onkeyup='autocompletaArticulo("+j+","+j+")' size='39'/>");
					out.print("<input name='"+artH+"' type='hidden' id='"+artH+"' value='"+M[i][1]+"' /></td>");//identificador Div+identificador campo
					out.print("<td width='11%'><label><input name='"+lot+"' type='text' id='"+lot+"' value='"+M[i][2]+"' onFocus='checkart("+j+")' onkeyup='checkcara("+j+")' size='11'/></td>");
					out.print("<td width='11%'><label><input name='"+inv+"' type='text' id='"+inv+"' value='"+M[i][3]+"' onkeyup='checkcaras("+j+")' size='11'/></td>");
					out.print("<td width='9%'><label><input name='"+vence+"' type='text' id='"+vence+"' value='"+M[i][4]+"' onKeyup='masca(this,patron,true,"+dia+","+mes+","+annio+")' onBlur='checkfecha("+j+")' size='8'/></td>");
					out.print("<td width='8%'><label><input name='"+ca+"' type='text' id='"+ca+"' value='"+M[i][5]+"' onkeyup='checknum("+j+")' onBlur='Repetir("+j+")' size='6'/></td>");
					out.print("<td width='4%'><label><input name='"+iva+"' type='text' id='"+iva+"' value='"+M[i][6]+"' onkeyup='autocompletaIva("+j+","+j+")' onBlur='Repetir("+j+")' size='1'/>");
					out.print("<input name='"+ivaH+"' type='hidden' id='"+ivaH+"' value='"+M[i][7]+"' /></td>");//identificador Div+identificador campo
					out.print("<td width='13%'><label><input name='"+vau+"' type='text' id='"+vau+"' value='"+M[i][8]+"' onFocus='checkiva("+j+")'  onkeyup='checknum2("+j+"),Repetir2("+conta+","+j+",this.form)' onBlur='Repetir("+j+")' size='15'/></td>");
					out.print("<td width='16%'><label><input name='"+vat+"' type='text' '"+vat+"' readonly='' value='"+M[i][9]+"' onBlur='Repetir2("+conta+","+j+",this.form)' size='19'/></td>");
					out.print("<input name='"+txtM+"' type='hidden' id='"+txtM+"' value='"+Ndatos+"' /></td>");//identificador Div+identificador campo
					
					out.print("<tr><td><div id='"+sug1+"'></div></td><td></td><td></td><td></td><td></td><td><div id='"+sug2+"'></div></td></tr>");				
				}
			
			
				cj=j;
			}
			
			out.print("<tr><td colspan='8'><div id='boton'  align='center'><input type='button' name='btnD' id='btnD' value='    Ingresar    ' onClick='Repetir2N("+cont+","+cj+",this.form)'></td></div></td></tr>");
			
			
		}

		
		

		
		
		
		if(va.equals("Ingreso")){	
		
		Calendar calendario = Calendar.getInstance();
	//	Calendar calendario = new GregorianCalendar();
		int hora, minutos, segundos;
		hora =calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		String hra= hora+":"+minutos+":"+segundos;
		
		String ing = request.getParameter("ing");
		String nE = request.getParameter("nE");
		String bod = request.getParameter("bodega");
		String provee = request.getParameter("proveedor");
		String fact = request.getParameter("factura");
		String conc = request.getParameter("concepto");
		String mov = request.getParameter("movimiento");
		String user = request.getParameter("user");
		String fech = request.getParameter("fechas");
		String dias=fech.substring(0, 2);
		String meses=fech.substring(3, 5);
		String anos=fech.substring(6, 10);
		String fec=anos+"-"+meses+"-"+dias;
		//substring = string.substring(1, 2)
		
		//out.print("nE :  "+nE);
		//out.print("ing :  "+ing);
		//1: 18	//2: 29	//3: 40	//4: 51	//5: 62	//6: 73	//7: 84	//8: 95	//9: 106		//10:117
		StringTokenizer elementos;  
		elementos = new StringTokenizer(ing,"|"); 
		int i2=0;
		while(elementos.hasMoreTokens()){ 
		  V[i2] = elementos.nextToken();  
		  //out.print(V[i2]);
		  i2++;
		}
		//out.print("I2longitud: "+i2);
		int fin=i2/10;
		int r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13;
		//out.print("vector: "+fin);
		if(i2>0){
			//esta seccion ingresa el movimiento
			int cant=0;	
			for(int z3=0; z3<fin; z3++){
				if(fin==0){	
				cant=cant+Integer.parseInt(V[z3+5]);
				}else{
				cant=cant+Integer.parseInt(V[z3*10+5]);
				}
			}
			String cants=String.valueOf(cant);
			
			
			rs1=me.ObtenerConsecutivo("1");
			String cons="";
			int cn=0;
			try {
				if(rs1.next()){
				cons=rs1.getString(1)+""+rs1.getString(2);
				cn=Integer.parseInt(rs1.getString(2))+1;
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			String cns=String.valueOf(cn);
			me.ActualizaConsecutivo("1",cns);
			
			me.CrearMovimientos(cants, mov, fec, hra, user, fact, conc, cons);
		
			
			//////
			//rescatar el ultimo movimiento
			rs1=me.ObtenerUMovimiento(fec, hra);
			String movi="";
			try {
				while(rs1.next()){
				movi=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			/////
		 for(int z2=0; z2<fin; z2++){
			if(fin==0){
			String ven=V[z2+4];	
			String diass=ven.substring(0, 2);
			String mesess=ven.substring(3, 5);
			String anoss=ven.substring(6, 10);
			String venc=anoss+"-"+mesess+"-"+diass;
			
			me.CrearEntradas(movi, V[z2+1], venc, V[z2+2], V[z2+5], V[z2+8], V[z2+9], V[z2+7], bod, fec, hra, provee, V[z2+3]);
			
			
			///obtener ultimo inv
			 rs2=me.ObtenerUEntradas(fec,hra);
				String in="";
				try {
					while(rs2.next()){
					in=rs2.getString(1);
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			////crear detalle mov
			
		   me.CrearDetalle(movi,in,bod,V[z2+5]);	
			
			
			
			}else{
				String ven=V[z2*10+4];	
				String diass=ven.substring(0, 2);
				String mesess=ven.substring(3, 5);
				String anoss=ven.substring(6, 10);
				String venc=anoss+"-"+mesess+"-"+diass;
		
				
				
	  me.CrearEntradas(movi, V[z2*10+1], venc, V[z2*10+2], V[z2*10+5], V[z2*10+8], V[z2*10+9], V[z2*10+7], bod, fec, hra, provee, V[z2*10+3]);
			
				
				
				///obtener ultimo inv
			 rs2=me.ObtenerUEntradas(fec, hra);
				String in="";
				try {
					while(rs2.next()){
					in=rs2.getString(1);
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			////crear detalle mov
			
			  me.CrearDetalle(movi,in,bod,V[z2*10+5]);	
			
			}
		  }
		 ////////////////
		  
		 
		out.print(movi);
		
		  
		  //response.sendRedirect("farc_ReporteEntradas.jsp?movi="+movi);
		 //out.print("Ingreso Exitoso.");
		}
		}
		
		*/

		////FIN MODULO ANTIGUO////

if(va.equals("Entradas")){	
	String user=request.getParameter("user");
	out.print("<table width='100%' class='style6'><tr><td colspan='8'><div align='center' class='style11' id='cabecera2'>Acta de Recepcion / Entrada de Articulos </div></td></tr>");
	out.print("<tr><td width='12%'>Bodega</td><td><label><select name='cmbBodega' id='cmbBodega'><option value='Seleccione'>Seleccione</option>");
	rs=me.ObtenerBodegas(user);
	try {
		while(rs.next()){
			out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
		}
		rs.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
		}
	out.print("</select></label></td>");		
		
	out.print("<td width='13%'> Proveedor</td><td width='45%'><label><select name='cmbProveedor' id='cmbProveedor'><option value='Seleccione'>Seleccione</option>");
	rs1=me.ObtenerProveedor();
	try {
		while(rs1.next()){
			out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
		}
		rs1.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
		
	out.print("</select></label></td>");	
	out.print("<td width='13%'>Tipo de Movimiento</td><td width='45%'><label><select name='cmbTipoM' id='cmbTipoM'  ><option value='Seleccione'>Seleccione</option>");
	rs3=me.listarMovimientose();
	try {
		while(rs3.next()){
			out.print("<option value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
		}
		rs3.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	out.print("</select></label></td></tr>");
	//if(fecha==null){
	out.print("<tr><td>Numero de Factura :</td><td><label><input name='txtFactura' type='text' id='txtFactura' size='30'/><td>Numero de Orden :</td><td><label><input name='txtOrden' type='text' id='txtOrden' size='30'/></td><td>Fecha</td><td><label><input name='txtFecha' type='text' id='txtFecha' value='"+fecha+"' readonly='' size='10' /></label> Hora </td><td><label><input name='txtHora' type='text' id='txtHora' onKeyup='checkhora()'  size='10' /></label></td></tr>");
	out.print("<tr><td>Recibe : </td><td><label><input name='txtRecibe' type='text' id='txtRecibe' size='30'/></td></td><td>Entrego :</td><td width='45%'><label><input name='txtEntrego' type='text' id='txtEntrego'  size='30' /></label></td><td>Verifico : </td><td><label><input name='txtVerifico' type='text' id='txtVerifico' size='30'/></td></tr>");
		
	//out.print("<tr><td>Tipo de Muestreo</td><td><label><input name='txtOrden' type='text' id='txtOrden' size='40'/></td></td><td>Procedimiento de Muestreo</td><td width='45%'><label><input name='txtProcMu' type='text' id='txtProcMu' value='ALEATORIO'  readonly='' size='20' /></label></td></tr>");
	out.print("<tr><td>Concepto</td><td><label><input name='txtConcepto' type='text' id='txtConcepto' size='40'/></td><td>Observacion :</td><td width='45%'><label><input name='txtObservacion' type='text' id='txtObservacion'  size='45' /></label></td></tr>");
		
		
	out.print("<tr><td width='25%'>Cumplimiento en Tiempo de entrega </td><td><label><select name='cmbTiemE' type='text' id='cmbTiemE'><option value='Seleccione'>Seleccion</option>" +
			"<option value='SI'>SI</option>" +
			"<option value='NO'>NO</option></select></td></td><td>Tiempo </td><td width='45%'><label><input name='txtTiempo' type='text' id='txtTiempo'  size='10' /></label></td></tr>");
	
	out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Generar Movimientos</div></td></tr>");
				
	out.print("<table width='100%' border='1' class='style2'><tr><td width='10%'><div align='center'>Articulo</div></td><td width='12%'><div align='center'>Laboratorio</div></td><td width='4%'><div align='center'>Lote</div></td><td width='4%'><div align='center'>Codigo Invima</div></td><td width='4%'><div align='center'>Vence</div></td><td width='4%'><div align='center'>Cant. Solicita</div></td><td width='4%'><div align='center'>Cant. Recibe</div></td><td width='5%'><div align='center'>EMBA</div></td><td width='5%'><div align='center'>EMPA</div></td><td width='4%'><div align='center'>Envase</div></td><td width='2%'><div align='center'>T.ºC</div></td><td width='12%'><div align='center'>Env. Primario</div></td><td width='12%'><div align='center'>Emp. Sec</div></td><td width='2%'><div align='center'>Cumple</div></td><td width='2%'><div align='center'>Dona</div></td><td width='2%'><div align='center'>IVA</div></td><td width='5%'><div align='center'>Vlr Unitario</div></td><td width='7%'><div align='center'>Vlr Total </div></td></tr>");
	out.print("<tr><td><input name='txtTipoMe0' type='text' id='txtTipoMe0' onkeyup='autocompletaArticulo(0,0)'  size='20'  />");//size='39'
	out.print("<input name='txtTipoMeH0' type='hidden' id='txtTipoMeH0'/></td>");//identificador Div+identificador campo
		
	out.print("<td width='12%'><label><select name='cmbLab0' id='cmbLab0'   style='width:100%' style='font-size:9px;' ><option value='Seleccione'>Seleccione</option>");
	rs3=me.listarLaboratorios();
	try { 
		while(rs3.next()){
			out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
		}
		rs3.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	out.print("</select></label></td>");
	out.print("<td width='4%'><label><input name='txtLote0' type='text' id='txtLote0' onFocus='checkart(0)' onkeyup='checkcara(0)'  size='5' /></td>");//
	out.print("<td width='4%'><label><input name='txtInvima0' type='text' id='txtInvima0' onkeyup='checkcaras(0)'  size='5'/></td>");//size='11'
	out.print("<td width='4%'><label><input name='txtVence0' type='text' id='txtVence0' onKeyup='masca(this,patron,true,"+dia+","+mes+","+annio+")' onBlur='checkfecha(0),compare_dates(0,this,"+dia+","+mes+","+annio+")' size='6' /></td>");//size='8'
	out.print("<td width='4%'><label><input name='txtCantSol0' type='text' id='txtCantSol0' onkeyup='checknum(0)' size='3' /></td>");//size='6'
	out.print("<td width='4%'><label><input name='txtCantidad0' type='text' id='txtCantidad0' onkeyup='checknum(0)'  onBlur='Repetir(0)'   size='3'  /></td>");//size='6'
	out.print("<td width='5%'><label><select name='cmbEMB0' id='cmbEMB0'  style='width:100%' style='font-size:9px;' >");
	rs3=me.listarEmbalaje();
	try {
		while(rs3.next()){
			out.print("<option title='"+rs3.getString(3)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
		}
		rs3.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	out.print("</select></label></td>");
	out.print("<td width='5%'><label><select name='cmbEMP0' id='cmbEMP0'  style='width:100%' style='font-size:9px;' >");
	rs3=me.listarEmpaque();
	try {
		while(rs3.next()){
			out.print("<option title='"+rs3.getString(3)+"'  value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
		}
		rs3.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	out.print("</select></label></td>");
	out.print("<td width='4%' ><label><select name='cmbEMV0' id='cmbEMV0' style='width:100%' style='font-size:9px;' >");
	rs3=me.listarEnvase();
	try {
		while(rs3.next()){
			out.print("<option title='"+rs3.getString(3)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
		}
		rs3.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	out.print("</select></label></td>");
	out.print("<td width='2%'><label><input name='txtT0' type='text' id='txtT0' value='N/A'  size='2' /></td>"); //size='4'
	out.print("<td width='12%'><label><select name='cmbENVP0' id='cmbENVP0' style='width:100%' style='font-size:9px;' >");
	rs3=me.listarEnvasePrimario();
	try {
		while(rs3.next()){
			out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
		}
		rs3.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	out.print("</select></label></td>");
	out.print("<td width='12%' ><label><select name='cmbEMB0' id='cmbEMPS0' style='width:100%' style='font-size:9px;' >");
	rs3=me.listarEmpaqueSecundario();
	try {
		while(rs3.next()){
			out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
		}
		rs3.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	out.print("</select></label></td>");
	out.print("<td align='center' width='2%'><input type='checkbox' name='checkbox' id='cumple0'  size='2' ></td>");
	out.print("<td align='center' width='2%'><input type='checkbox' name='checkbox' id='dona0'   size='2' ></td>");
	out.print("<td width='2%'><label><input name='txtIva0' type='text' id='txtIva0'  onkeyup='autocompletaIva(0,0)' onBlur='Repetir(0)' style='width:100%'/>"); //size='4'
	out.print("<input name='txtIvaH0' type='hidden' id='txtIvaH0'/></td>");
	out.print("<td width='5%'><label><input name='txtVunitario0' type='text' id='txtVunitario0' onFocus='checkiva(0)' onkeyup='checknum2(0)' onBlur='Repetir(0),Repetir2("+cont+",0,this.form)' size='6'  /></td>");//size='15'
	out.print("<td width='7%'><label><input name='txtTotal0' type='text' id='txtTotal0' readonly='' onBlur='Repetir2("+cont+",0,this.form)'  size='8'  /></td>");//value='"+vt+"' size='19'
	out.print("<tr><td><div id='sugerencias10'></div></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><div id='sugerencias20'></div></td></tr>");
	out.print("</table>"); 
	out.print("<div id='repetir'><div id='boton'  align='center'><input type='button' name='btnD' id='btnD' value='    Ingresar    ' onClick='Repetir2N("+cont+",0,this.form)'></div></div>");
					
}
	
	
	
	
	if(va.equals("EntradasRepetidas")){		
		System.out.print("contador "+conta);
		
					
		String a0 = request.getParameter("a0");
		char s ;
		char s2='@';
		String ini="";
		String fin="";
	//	String s2 = s.substring(0,3) + "A" + s.substring(3);
		for(int q=0; q<a0.length();q++){
			s=a0.charAt(q);
			if(s==s2){//out.println("encontrado en "+q+" es "+s);
			ini=ini+"%";}else{
			ini=ini+s;
			}
		}

		

		String a2 = request.getParameter("a2");
		String a3 = request.getParameter("a3");
		String a1 = request.getParameter("a1");
		String a4 = request.getParameter("a4");
		String a5 = request.getParameter("a5");
		String a00 = request.getParameter("a00");
		String a6 = request.getParameter("a6");
		String a7 = request.getParameter("a7");
		String a8 = request.getParameter("a8");
		
		String r0ar = request.getParameter("r0ar");
		String r1ar = request.getParameter("r1ar");
		String r2ar = request.getParameter("r2ar");
		String r3ar = request.getParameter("r3ar");
		String r4ar = request.getParameter("r4ar");
		String r5ar = request.getParameter("r5ar");
		String r6ar = request.getParameter("r6ar");
		String r7ar = request.getParameter("r7ar");
		String r8ar = request.getParameter("r8ar");
		String r9ar = request.getParameter("r9ar");
		
		
		datos2=datos2+ini;datos2=datos2+"|";
		datos2=datos2+a2;datos2=datos2+"|";
		datos2=datos2+a3;datos2=datos2+"|";
		datos2=datos2+a1;datos2=datos2+"|";
		datos2=datos2+a4;datos2=datos2+"|";
		datos2=datos2+a5;datos2=datos2+"|";
		datos2=datos2+a00;datos2=datos2+"|";
		datos2=datos2+a6;datos2=datos2+"|";
		datos2=datos2+a7;datos2=datos2+"|";
		datos2=datos2+a8;datos2=datos2+"|";
		
		datos2=datos2+r0ar;datos2=datos2+"|";
		datos2=datos2+r1ar;datos2=datos2+"|";
		datos2=datos2+r2ar;datos2=datos2+"|";
		datos2=datos2+r3ar;datos2=datos2+"|";
		datos2=datos2+r4ar;datos2=datos2+"|";
		datos2=datos2+r5ar;datos2=datos2+"|";
		datos2=datos2+r6ar;datos2=datos2+"|";
		datos2=datos2+r7ar;datos2=datos2+"|";
		datos2=datos2+r8ar;datos2=datos2+"|";
		datos2=datos2+r9ar;datos2=datos2+"|";
		
		//out.print("Dtossss  "+datos2);
		
		int i1=0;
		//V[0] =   
		//i1++;
		  
		
		//llena los datos que provienen de repetir
		   StringTokenizer elementos;  
		   elementos = new StringTokenizer(Ndatos,"|"); 
		   System.out.println("Ndatos: "+Ndatos);
		   while(elementos.hasMoreTokens()){ 
			   V[i1] = elementos.nextToken(); 
			   System.out.println("V["+i1+"]: "+V[i1]);
			   i1++;
		   }
		   
		  /* for(int z=0; z<i1; z++){
			   out.print(" V["+z+"]="+V[z]+"\n"); 
		   }*/
		   
		   int in=0;
		   for(int z=0; z<conta; z++){
			   for(int zz=0; zz<=19; zz++){
				   MI[z][zz]=V[in];
				   System.out.println("M["+z+"]["+zz+"]: "+ MI[z][zz]);
				   in++;
			   }
		   }
		   
	//	out.print("<table width='100%' border='1' class='style6'>");
		int ind=0;
		int cj=0;
		for(int i=0;i<conta;i++){
			// System.out.println("esta es i: "+i);
			int j=i+1;
			//out.print("JJJJJ"+j);
			for(int z=0; z<=19; z++){
			ind=z+(j*20);// YO CREO QUE CON EL ACTA DE RECEPCION DEBE CAMBIAR A 20 j*20
			M[i][z]=V[ind];
			 System.out.println("fila "+(i)+" col "+z+" : "+M[i][z]);
			}
			
			
			String sug1="sugerencias1"+j;
			String sug2="sugerencias2"+j;
			String art="txtTipoMe"+j;
			String artH="txtTipoMeH"+j;
			String lot="txtLote"+j;
			String inv="txtInvima"+j;
			String vence="txtVence"+j;
			String ca="txtCantidad"+j;
			String iva="txtIva"+j;
			String ivaH="txtIvaH"+j;
			String vau="txtVunitario"+j;
			String vat="txtTotal"+j;
			String txtM="txtM"+j;
			
											
			String vp0ar="cmbLab"+j;//ar
			String vp1ar="txtCantSol"+j;//ar
			String vp2ar="cmbEMB"+j;//ar
			String vp3ar="cmbEMP"+j;//ar
			String vp4ar="cmbEMV"+j;//ar
			String vp5ar="txtT"+j;//ar
			String vp6ar="cmbENVP"+j;//ar
			String vp7ar="cmbEMPS"+j;//ar
			String vp8ar="cumple"+j;//ar
			String vp9ar="dona"+j;//ar
			
			
			
			out.print("<table width='100%' border='1' class='style2'>");
			out.print("<tr style='color:white' ><td width='10%'><div align='center'>Articulo</div></td><td width='12%'><div align='center'>Laboratorio</div></td><td width='4%'><div align='center'>Lote</div></td><td width='4%'><div align='center'>Codigo Invima</div></td><td width='4%'><div align='center'>Vence</div></td><td width='4%'><div align='center'> Solicita</div></td><td width='4%'><div align='center'> Recibe</div></td><td width='5%'><div align='center'>EMBA</div></td><td width='5%'><div align='center'>EMPA</div></td><td width='4%'><div align='center'>Envase</div></td><td width='2%'><div align='center'>T.ºC</div></td><td width='12%'><div align='center'>Primario</div></td><td width='12%'><div align='center'>Emp. </div></td><td width='2%'><div align='center'>Cumple</div></td><td width='2%'><div align='center'>Dona</div></td><td width='2%'><div align='center'>IVA</div></td><td width='5%'><div align='center'>Vlr Unitario</div></td><td width='7%'><div align='center'>Vlr Total </div></td></tr>");
		//	out.print("txtM0 "+datos2+"  txtMJ: "+j);
			System.out.println("i es igual a "+i);
			 System.out.println("conta es igual a "+conta);
			
			// out.print("<tr><td width='14%'><div align='center'></div></td><td width='12%'><div align='center'></div></td><td width='5%'><div align='center'></div></td><td width='5%'><div align='center'></div></td><td width='6%'><div align='center'></div></td><td width='4%'><div align='center'></div></td><td width='4%'><div align='center'></div></td><td width='5%'><div align='center'></div></td><td width='5%'><div align='center'></div></td><td width='4%'><div align='center'></div></td><td width='2%'><div align='center'></div></td><td width='8%'><div align='center'></div></td><td width='8%'><div align='center'></div></td><td width='2%'><div align='center'></div></td><td width='2%'><div align='center'></div></td><td width='2%'><div align='center'></div></td><td width='5%'><div align='center'></div></td><td width='7%'><div align='center'></div></td></tr>");
			 if((conta>1)&&(i<(conta-1))){/**  AQUI if((conta>1)&&(i<(conta-1))){   **/
				 System.out.println("Entro aqui if((conta>1)&&(i<(conta-1))){");
		/*	out.print("<td width='28%'><input name='"+art+"' type='text' id='"+art+"'  value='"+M[i][0]+"' onkeyup='autocompletaArticulo("+j+","+j+")' size='39' />");
			out.print("<input name='"+artH+"' type='hiden' id='"+artH+"' value='"+M[i][1]+"'/></td>");//identificador Div+identificador campo
			out.print("<td width='11%'><label><input name='"+lot+"' type='text' id='"+lot+"' value='"+M[i][2]+"' onFocus='checkart("+j+")' onkeyup='checkcara("+j+")' size='11'/></td>");
			out.print("<td width='11%'><label><input name='"+inv+"' type='text' id='"+inv+"' value='"+M[i][3]+"' onkeyup='checkcaras("+j+")' size='11'/></td>");
			out.print("<td width='9%'><label><input name='"+vence+"' type='text' id='"+vence+"' value='"+M[i][4]+"' onKeyup='masca(this,patron,true,"+dia+","+mes+","+annio+")' onBlur='checkfecha("+j+")' size='8'/></td>");
			out.print("<td width='8%'><label><input name='"+ca+"' type='text' id='"+ca+"' value='"+M[i][5]+"' onkeyup='checknum("+j+")' onBlur='Repetir("+j+")' size='6'/></td>");
			out.print("<td width='4%'><label><input name='"+iva+"' type='text' id='"+iva+"' value='"+M[i][6]+"' onkeyup='autocompletaIva("+j+","+j+")' onBlur='Repetir("+j+")' size='1'/>");
			out.print("<input name='"+ivaH+"' type='hiden' id='"+ivaH+"' value='"+M[i][7]+"'/></td>");//identificador Div+identificador campo
			out.print("<td width='13%'><label><input name='"+vau+"' type='text' id='"+vau+"' value='"+M[i][8]+"' onFocus='checkiva("+j+")'  onkeyup='checknum2("+j+")' onBlur='Repetir("+j+"),Repetir2("+conta+","+j+",this.form)' size='15'/></td>");
			out.print("<td width='16%'><label><input name='"+vat+"' type='text' '"+vat+"' readonly='' value='"+M[i][9]+"' onBlur='Repetir2("+conta+","+j+",this.form)' size='19'/></td>");
			out.print("<input name='"+txtM+"' type='hiden' id='"+txtM+"' value='"+Ndatos+"' /></td>");//identificador Div+identificador campo
			
			out.print("<tr><td><div id='"+sug1+"'></div></td><td></td><td></td><td></td><td></td><td><div id='"+sug2+"'></div></td></tr>");				
			*/
			
					
				 //cuando se da enter muestra este 2
				 out.print("<tr><td width='10%'><input name='txtTipoMe0' type='text' id='"+art+"'  value='"+M[i][0]+"'  onkeyup='autocompletaArticulo("+j+","+j+")' size='20' />");//size='39'
					out.print("<input name='txtTipoMeH0' type='hidden' id='"+artH+"'  value='"+M[i][1]+"' /></td>");//identificador Div+identificador campo
					out.print("<td width='12%'><label><select name='cmbLab0' id='"+vp0ar+"'   style='width:100%' style='font-size:9px;'>");
					rs3=me.listarLaboratorios(M[i][10]);
					try { 
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					rs3=me.listarLaboratoriosno(M[i][10]);
					try { 
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='4%'><label><input name='txtLote0' type='text' id='"+lot+"'  value='"+M[i][2]+"' onFocus='checkart("+j+")' onkeyup='checkcara("+j+")'  size='5' /></td>");//
					out.print("<td width='4%'><label><input name='txtInvima0' type='text' id='"+inv+"' value='"+M[i][3]+"' onkeyup='checkcaras("+j+")'  size='5' /></td>");//size='11'
					out.print("<td width='4%'><label><input name='txtVence0' type='text' id='"+vence+"'  value='"+M[i][4]+"'  onKeyup='masca(this,patron,true,"+dia+","+mes+","+annio+")' onBlur='checkfecha("+j+"),compare_dates("+j+",this,"+dia+","+mes+","+annio+")' size='6' /></td>");//size='8'
					out.print("<td width='4%'><label><input name='txtCantSol0' type='text' id='"+vp1ar+"' value='"+M[i][11]+"' onkeyup='checknum(0)'   size='3'/></td>");//size='6'
					out.print("<td width='4%'><label><input name='txtCantidad0' type='text' id='"+ca+"'  value='"+M[i][5]+"' onkeyup='checknum("+j+")'  onBlur='Repetir("+j+")'  size='3' /></td>");//size='6'
					out.print("<td width='5%'><label><select name='cmbEMB0' id='"+vp2ar+"'  style='width:100%' style='font-size:9px;' >");
					rs3=me.listarEmbalaje(M[i][12]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(3)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					rs3=me.listarEmbalajeno(M[i][12]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(3)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='5%' ><label><select name='cmbEMP0' id='"+vp3ar+"'  style='width:100%' style='font-size:9px;' >");
					rs3=me.listarEmpaque(M[i][13]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(3)+"'  value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					rs3=me.listarEmpaqueno(M[i][13]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(3)+"'  value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='4%' ><label><select name='cmbEMV0' id='"+vp4ar+"' style='width:100%' style='font-size:9px;' >");
					rs3=me.listarEnvase(M[i][14]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(3)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					rs3=me.listarEnvaseno(M[i][14]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(3)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='2%'><label><input name='txtT0' type='text' id='"+vp5ar+"'  value='"+M[i][15]+"'  size='2' /></td>"); //size='4'
					out.print("<td width='12%' ><label><select name='cmbENVP0' id='"+vp6ar+"' style='width:100%' style='font-size:9px;' >");
					rs3=me.listarEnvasePrimario(M[i][16]);
					try { 
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					rs3=me.listarEnvasePrimariono(M[i][16]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='12%' ><label><select name='cmbEMB0' id='"+vp7ar+"' style='width:100%'  style='font-size:9px;' >");
					rs3=me.listarEmpaqueSecundario(M[i][17]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					rs3=me.listarEmpaqueSecundariono(M[i][17]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					if(M[i][18].equals("0")){
					out.print("<td align='center' width='2%'><input type='checkbox' name='checkbox' id='"+vp8ar+"' size='2'/></td>");
					}else{
					out.print("<td align='center' width='2%'><input type='checkbox' name='checkbox' id='"+vp8ar+"' checked='true' size='2' /></td>");
					}
					if(M[i][19].equals("0")){
					out.print("<td align='center' width='2%'><input type='checkbox' name='checkbox' id='"+vp9ar+"'  size='2'  /></td>");
					}else{
					out.print("<td align='center' width='2%'><input type='checkbox' name='checkbox' id='"+vp9ar+"' checked='checked'  size='2'  /></td>");
					}
					
					out.print("<td width='2%'><label><input name='txtIva0' type='text' id='"+iva+"'   value='"+M[i][6]+"'  onkeyup='autocompletaIva("+j+","+j+")' onBlur='Repetir("+j+")'  style='width:100%' />"); //size='4'
					out.print("<input name='txtIvaH0' type='hidden' id='"+ivaH+"'  value='"+M[i][7]+"' /></td>");
					out.print("<td width='5%'><label><input name='txtVunitario0' type='text' id='"+vau+"'  value='"+M[i][8]+"'  onFocus='checkiva("+j+")' onkeyup='checknum2("+j+")' onBlur='Repetir("+j+"),Repetir2("+cont+","+j+",this.form)' size='6'  /></td>");//size='15'
					out.print("<td width='7%'><label><input name='txtTotal0' type='text' id='"+vat+"'  value='"+M[i][9]+"'  readonly='' onBlur='Repetir2("+cont+","+j+",this.form)'  size='8'  /></td></tr>");//value='"+vt+"' size='19'
					out.print("<input name='"+txtM+"' type='hidden' id='"+txtM+"' value='"+Ndatos+"' />");//identificador Div+identificador campo
								
					out.print("<tr><td><div id='"+sug1+"'></div></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><div id='"+sug2+"'></div></td></tr>");
								
					//out.print("</table>"); 
				 
			}/** FINNNNNNNN AQUI if((conta>1)&&(i<(conta-1))){   **/
			
			
			
			
			
			
		
			if((i==(conta-1)&&(i!=8))){/**  AQUI if((i==(conta-1)&&(i!=8))){  **/ //ESTA ES LA LINEA NUEVA DE CREACION
				 System.out.println("Entro aqui if if((i==(conta-1)&&(i!=8))){");
			/*	out.print("<td width='28%'><input name='"+art+"' type='text' id='"+art+"' onkeyup='autocompletaArticulo("+j+","+j+")' size='39'/>");
				out.print("<input name='"+artH+"' type='hiden' id='"+artH+"'  /></td>");//identificador Div+identificador campo
				out.print("<td width='11%'><label><input name='"+lot+"' type='text' id='"+lot+"' onFocus='checkart("+j+")' onkeyup='checkcara("+j+")' size='11'/></td>");
				out.print("<td width='11%'><label><input name='"+inv+"' type='text' id='"+inv+"' onkeyup='checkcaras("+j+")' size='11'/></td>");
				out.print("<td width='9%'><label><input name='"+vence+"' type='text' id='"+vence+"' onKeyup='masca(this,patron,true,"+dia+","+mes+","+annio+")' onBlur='checkfecha("+j+")' size='8'/></td>");
				out.print("<td width='8%'><label><input name='"+ca+"' type='text' id='"+ca+"' onkeyup='checknum("+j+")' onBlur='Repetir("+j+")'  size='6'/></td>");
				out.print("<td width='4%'><label><input name='"+iva+"' type='text' id='"+iva+"' onkeyup='autocompletaIva("+j+","+j+")' onBlur='Repetir("+j+")' size='1'/>");
				out.print("<input name='"+ivaH+"' type='hiden' id='"+ivaH+"'/></td>");//identificador Div+identificador campo
				out.print("<td width='13%'><label><input name='"+vau+"' type='text' id='"+vau+"' onFocus='checkiva("+j+")'  onkeyup='checknum2("+j+")' onBlur='Repetir("+j+"),Repetir2("+conta+","+j+",this.form)' size='15'/></td>");
				out.print("<td width='16%'><label><input name='"+vat+"' type='text' '"+vat+"' readonly='' onBlur='Repetir2("+conta+","+j+",this.form)' size='19'/></td>");
				out.print("<input name='"+txtM+"' type='hiden' id='"+txtM+"' value='"+Ndatos+"' /></td>");//identificador Div+identificador campo
				
				out.print("<tr><td><div id='"+sug1+"'></div></td><td></td><td></td><td></td><td></td><td><div id='"+sug2+"'></div></td></tr>");	
			*/	
				///repetida 3
				 out.print("<tr><td><input name='txtTipoMe0' type='text' id='"+art+"' onkeyup='autocompletaArticulo("+j+","+j+")'  size='20'  />");//size='39'
					out.print("<input name='txtTipoMeH0' type='hidden' id='"+artH+"'/></td>");//identificador Div+identificador campo
					out.print("<td width='12%'><label><select name='cmbLab0' id='"+vp0ar+"' style='width:100%' style='font-size:9px;' ><option value='Seleccione'>Seleccione</option>");
					rs3=me.listarLaboratorios();
					try { 
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='4%'><label><input name='txtLote0' type='text' id='"+lot+"' onFocus='checkart("+j+")' onkeyup='checkcara("+j+")'  size='5' /></td>");//
					out.print("<td width='4%'><label><input name='txtInvima0' type='text' id='"+inv+"' onkeyup='checkcaras("+j+")'  size='5' /></td>");//size='11'
					out.print("<td width='4%'><label><input name='txtVence0' type='text' id='"+vence+"' onKeyup='masca(this,patron,true,"+dia+","+mes+","+annio+")' onBlur='checkfecha("+j+")' size='6' /></td>");//size='8'
					out.print("<td width='4%'><label><input name='txtCantSol0' type='text' id='"+vp1ar+"' onkeyup='checknum(0)'  size='3' /></td>");//size='6'
					out.print("<td width='4%'><label><input name='txtCantidad0' type='text' id='"+ca+"' onkeyup='checknum("+j+")'  onBlur='Repetir("+j+")'  size='3' /></td>");//size='6'
					out.print("<td width='5%' ><label><select name='cmbEMB0' id='"+vp2ar+"'  style='width:100%' style='font-size:9px;' >");
					rs3=me.listarEmbalaje();
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(3)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='5%' ><label><select name='cmbEMP0' id='"+vp3ar+"'  style='width:100%' style='font-size:9px;' >");
					rs3=me.listarEmpaque();
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(3)+"'  value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='4%' ><label><select name='cmbEMV0' id='"+vp4ar+"' style='width:100%'  style='font-size:9px;' >");
					rs3=me.listarEnvase();
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(3)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='2%'><label><input name='txtT0' type='text' id='"+vp5ar+"'  value='N/A'  size='2' /></td>"); //size='4'
					out.print("<td width='12%' ><label><select name='cmbENVP0' id='"+vp6ar+"' style='width:100%'  style='font-size:9px;' >");
					rs3=me.listarEnvasePrimario();
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='12%'><label><select name='cmbEMB0' id='"+vp7ar+"' style='width:100%' style='font-size:9px;' >");
					rs3=me.listarEmpaqueSecundario();
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td align='center' width='2%'><input type='checkbox' name='checkbox' id='"+vp8ar+"' size='2' ></td>");
					out.print("<td align='center' width='2%'><input type='checkbox' name='checkbox' id='"+vp9ar+"'  size='2' ></td>");
					out.print("<td width='2%'><label><input name='txtIva0' type='text' id='"+iva+"'  onkeyup='autocompletaIva("+j+","+j+")' onBlur='Repetir("+j+")'  style='width:100%' />"); //size='4'
					out.print("<input name='txtIvaH0' type='hidden' id='"+ivaH+"'/></td>");
					out.print("<td width='5%'><label><input name='txtVunitario0' type='text' id='"+vau+"' onFocus='checkiva("+j+")' onkeyup='checknum2("+j+")' onBlur='Repetir("+j+"),Repetir2("+cont+","+j+",this.form)' size='6'  /></td>");//size='15'
					out.print("<td width='7%'><label><input name='txtTotal0' type='text' id='"+vat+"' readonly='' onBlur='Repetir2("+cont+","+j+",this.form)'  size='8'  /></td></tr>");//value='"+vt+"' size='19'
					out.print("<input name='"+txtM+"' type='hidden' id='"+txtM+"' value='"+Ndatos+"' />");//identificador Div+identificador campo
					out.print("<tr><td><div id='"+sug1+"'></div></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><div id='"+sug2+"'></div></td></tr>");
								
					//out.print("</table>"); 
						
			}/** FINNNNNNN AQUI if((i==(conta-1)&&(i!=8))){  **/ //FIN DE LA LINEA NUEVA DE CRACION
			
			if(i==8){
				 System.out.println("Entro aqui if if(i==8){");
			/*	for(int ss=0; ss<=9; ss++){ if (M[i][ss]==null){M[i][ss]="";}}
				out.print("<td width='28%'><input name='"+art+"' type='text' id='"+art+"' value='"+M[i][0]+"' onkeyup='autocompletaArticulo("+j+","+j+")' size='39'/>");
				out.print("<input name='"+artH+"' type='hiden' id='"+artH+"' value='"+M[i][1]+"' /></td>");//identificador Div+identificador campo
				out.print("<td width='11%'><label><input name='"+lot+"' type='text' id='"+lot+"' value='"+M[i][2]+"' onFocus='checkart("+j+")' onkeyup='checkcara("+j+")' size='11'/></td>");
				out.print("<td width='11%'><label><input name='"+inv+"' type='text' id='"+inv+"' value='"+M[i][3]+"' onkeyup='checkcaras("+j+")' size='11' /></td>");
				out.print("<td width='9%'><label><input name='"+vence+"' type='text' id='"+vence+"' value='"+M[i][4]+"' onKeyup='masca(this,patron,true,"+dia+","+mes+","+annio+")' onBlur='checkfecha("+j+")' size='8'/></td>");
				out.print("<td width='8%'><label><input name='"+ca+"' type='text' id='"+ca+"' value='"+M[i][5]+"' onkeyup='checknum("+j+")' onBlur='Repetir("+j+")' size='6'/></td>");
				out.print("<td width='4%'><label><input name='"+iva+"' type='text' id='"+iva+"' value='"+M[i][6]+"' onkeyup='autocompletaIva("+j+","+j+")' onBlur='Repetir("+j+")' size='1'/>");
				out.print("<input name='"+ivaH+"' type='hiden' id='"+ivaH+"' value='"+M[i][7]+"' /></td>");//identificador Div+identificador campo
				out.print("<td width='13%'><label><input name='"+vau+"' type='text' id='"+vau+"' value='"+M[i][8]+"' onFocus='checkiva("+j+")'  onkeyup='checknum2("+j+"),Repetir2("+conta+","+j+",this.form)' onBlur='Repetir("+j+")' size='15'/></td>");
				out.print("<td width='16%'><label><input name='"+vat+"' type='text' '"+vat+"' readonly='' value='"+M[i][9]+"' onBlur='Repetir2("+conta+","+j+",this.form)' size='19'/></td>");
				out.print("<input name='"+txtM+"' type='hiden' id='"+txtM+"' value='"+Ndatos+"' />");//identificador Div+identificador campo
				
				out.print("<tr><td><div id='"+sug1+"'></div></td><td></td><td></td><td></td><td></td><td><div id='"+sug2+"'></div></td></tr>");				
			*/
			
				 ///cuarta 
				 out.print("<tr><td width='10%'><input name='txtTipoMe0' type='text' id='"+art+"'  value='"+M[i][0]+"'  onkeyup='autocompletaArticulo("+j+","+j+")'  size='20'  />");//size='39'
					out.print("<input name='txtTipoMeH0' type='hidden' id='"+artH+"'  value='"+M[i][1]+"' /></td>");//identificador Div+identificador campo
					out.print("<td width='12%'><label><select name='cmbLab0' id='"+vp0ar+"'   style='width:100%' style='font-size:9px;'>");
					rs3=me.listarLaboratorios(M[i][10]);
					try { 
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					rs3=me.listarLaboratoriosno(M[i][10]);
					try { 
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='4%'><label><input name='txtLote0' type='text' id='"+lot+"'  value='"+M[i][2]+"' onFocus='checkart("+j+")' onkeyup='checkcara("+j+")'  size='5' /></td>");//
					out.print("<td width='4%'><label><input name='txtInvima0' type='text' id='"+inv+"' value='"+M[i][3]+"' onkeyup='checkcaras("+j+")'  size='5' /></td>");//size='11'
					out.print("<td width='4%'><label><input name='txtVence0' type='text' id='"+vence+"'  value='"+M[i][4]+"'  onKeyup='masca(this,patron,true,"+dia+","+mes+","+annio+")' onBlur='checkfecha("+j+"),compare_dates("+j+",this,"+dia+","+mes+","+annio+")'  size='6' /></td>");//size='8'
					out.print("<td width='4%'><label><input name='txtCantSol0' type='text' id='"+vp1ar+"' value='"+M[i][11]+"' onkeyup='checknum(0)'   size='3'/></td>");//size='6'
					out.print("<td width='4%'><label><input name='txtCantidad0' type='text' id='"+ca+"'  value='"+M[i][5]+"' onkeyup='checknum("+j+")'  onBlur='Repetir("+j+")' size='3' /></td>");//size='6'
					out.print("<td width='5%' ><label><select name='cmbEMB0' id='"+vp2ar+"'  style='width:100%' style='font-size:9px;' >");
					rs3=me.listarEmbalaje(M[i][12]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(3)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					rs3=me.listarEmbalajeno(M[i][12]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(3)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='5%' ><label><select name='cmbEMP0' id='"+vp3ar+"'  style='width:100%' style='font-size:9px;'>");
					rs3=me.listarEmpaque(M[i][13]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(3)+"'  value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					rs3=me.listarEmpaqueno(M[i][13]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(3)+"'  value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='4%' ><label><select name='cmbEMV0' id='"+vp4ar+"' style='width:100%' style='font-size:9px;' >");
					rs3=me.listarEnvase(M[i][14]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(3)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					rs3=me.listarEnvaseno(M[i][14]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(3)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='2%'><label><input name='txtT0' type='text' id='"+vp5ar+"'  value='"+M[i][15]+"'  size='2' /></td>"); //size='4'
					out.print("<td width='12%' ><label><select name='cmbENVP0' id='"+vp6ar+"' style='width:100%' style='font-size:9px;' >");
					rs3=me.listarEnvasePrimario(M[i][16]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					rs3=me.listarEnvasePrimariono(M[i][16]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					out.print("<td width='12%' ><label><select name='cmbEMB0' id='"+vp7ar+"' style='width:100%' style='font-size:9px;' >");
					rs3=me.listarEmpaqueSecundario(M[i][17]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					rs3=me.listarEmpaqueSecundariono(M[i][17]);
					try {
						while(rs3.next()){
						out.print("<option title='"+rs3.getString(2)+"' value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					if(M[i][18].equals("0")){
					out.print("<td width='2%' align='center'><input type='checkbox' name='checkbox' id='"+vp8ar+"' size='2'/></td>");
					}else{
					out.print("<td width='2%' align='center'><input type='checkbox' name='checkbox' id='"+vp8ar+"' checked='true' size='2' /></td>");
					}
					if(M[i][19].equals("0")){
					out.print("<td width='2%' align='center'><input type='checkbox' name='checkbox' id='"+vp9ar+"'  size='2' /></td>");
					}else{
					out.print("<td width='2%' align='center'><input type='checkbox' name='checkbox' id='"+vp9ar+"' checked='checked'  size='2'  /></td>");
					}
					
					out.print("<td width='2%'><label><input name='txtIva0' type='text' id='"+iva+"'   value='"+M[i][6]+"'  onkeyup='autocompletaIva("+j+","+j+")' onBlur='Repetir("+j+")'  style='width:100%' />"); //size='4'
					out.print("<input name='txtIvaH0' type='hidden' id='"+ivaH+"'  value='"+M[i][7]+"' /></td>");
					out.print("<td width='5%'><label><input name='txtVunitario0' type='text' id='"+vau+"'  value='"+M[i][8]+"'  onFocus='checkiva("+j+")' onkeyup='checknum2("+j+")' onBlur='Repetir("+j+")' size='6' /></td>");//size='15'
					out.print("<td width='7%'><label><input name='txtTotal0' type='text' id='"+vat+"'  value='"+M[i][9]+"'  readonly=''  size='8'  /></td></tr>");//value='"+vt+"' size='19'
					out.print("<input name='"+txtM+"' type='hidden' id='"+txtM+"' value='"+Ndatos+"' />");//identificador Div+identificador campo
								
					out.print("<tr><td><div id='"+sug1+"'></div></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><div id='"+sug2+"'></div></td></tr>");
								
			
			
			
			}
		
			cj=j;
		}
		
		out.print("<tr><td colspan='18'><div id='boton'  align='center'><input type='button' name='btnD' id='btnD' value='    Ingresar    ' onClick='Repetir2N("+cont+","+cj+",this.form)'></td></div></td></tr>");
		
		out.print("</table>");
	}
		
		
		if(va.equals("Ingreso")){	
		
		Calendar calendario = Calendar.getInstance();
	//	Calendar calendario = new GregorianCalendar();
		int hora, minutos, segundos;
		hora =calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		String hra= hora+":"+minutos+":"+segundos;
		
		String ing = request.getParameter("ing");
		String nE = request.getParameter("nE");
		String bod = request.getParameter("bodega");
		String provee = request.getParameter("proveedor");
		String fact = request.getParameter("factura");
		String conc = request.getParameter("concepto");
		String mov = request.getParameter("movimiento");
		String user = request.getParameter("user");
		String fech = request.getParameter("fechas");
		String dias=fech.substring(0, 2);
		String meses=fech.substring(3, 5);
		String anos=fech.substring(6, 10);
		String fec=anos+"-"+meses+"-"+dias;
		
		
		String orden = request.getParameter("orden");
		String horan = request.getParameter("hora");
		String recibe = request.getParameter("recibe");
		String entrega = request.getParameter("entrega");
		String verifica = request.getParameter("verifica");
		String observacion = request.getParameter("observacion");
		String tentrega = request.getParameter("tentrega");
		String tiempo = request.getParameter("tiempo");
		
		horan=horan+":00";
		
		
		//substring = string.substring(1, 2)
		
		//out.print("nE :  "+nE);
		//out.print("ing :  "+ing);
		//1: 18	//2: 29	//3: 40	//4: 51	//5: 62	//6: 73	//7: 84	//8: 95	//9: 106		//10:117
		StringTokenizer elementos;  
		elementos = new StringTokenizer(ing,"|"); 
		int i2=0;
		while(elementos.hasMoreTokens()){ 
		  V[i2] = elementos.nextToken();  
		  //out.print(V[i2]);
		  i2++;
		}
		//out.print("I2longitud: "+i2);
		int fin=i2/20;											/***DEBE SER ENTRE 20 y hacer el insertar en la tabla y listo***/
		int r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13;
		//out.print("vector: "+fin);
		if(i2>0){
			//esta seccion ingresa el movimiento
			int cant=0;	
			for(int z3=0; z3<fin; z3++){
				if(fin==0){	
				cant=cant+Integer.parseInt(V[z3+5]);
				}else{
				cant=cant+Integer.parseInt(V[z3*20+5]);
				}
			}
			String cants=String.valueOf(cant);
			
			
			rs1=me.ObtenerConsecutivo("1");
			String cons="";
			int cn=0;
			try {
				if(rs1.next()){
				cons=rs1.getString(1)+""+rs1.getString(2);
				cn=Integer.parseInt(rs1.getString(2))+1;
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			String cns=String.valueOf(cn);
			me.ActualizaConsecutivo("1",cns);
			
			me.CrearMovimientos(cants, mov, fec, hra, user, fact, conc, cons);
		
			
			//////
			//rescatar el ultimo movimiento
			rs1=me.ObtenerUMovimiento(fec, hra);
			String movi="";
			try {
				while(rs1.next()){
				movi=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			/////
		 for(int z2=0; z2<fin; z2++){
			if(fin==0){
			String ven=V[z2+4];	
			String diass=ven.substring(0, 2);
			String mesess=ven.substring(3, 5);
			String anoss=ven.substring(6, 10);
			String venc=anoss+"-"+mesess+"-"+diass;
			
			me.CrearEntradas(movi, V[z2+1], venc, V[z2+2], V[z2+5], V[z2+8], V[z2+9], V[z2+7], bod, fec, hra, provee, V[z2+3]);
			
			
			///obtener ultimo inv
			 rs2=me.ObtenerUEntradas(fec,hra);
				String in="";
				try {
					while(rs2.next()){
					in=rs2.getString(1);
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			////crear detalle mov
				
				int cp=(Integer.parseInt(V[z2+11])-Integer.parseInt(V[z2+5]));
				String cps=String.valueOf(cp);
				
				double cm=(Integer.parseInt(V[z2+5])*0.1)+1;
				String cms=String.valueOf(cm);
				
		   me.CrearActa(in,orden,fact,V[z2+11],V[z2+5],cps,V[z2+12],V[z2+13],V[z2+14],V[z2+15],V[z2+16],V[z2+17],recibe,entrega,"10%+1","ALEATORIO",cms,V[z2+18],tentrega,verifica,observacion,V[z2+19],fec,horan,tiempo,V[z2*20+10]);

		   me.CrearDetalle(movi,in,bod,V[z2+5]);	
			
			
			}else{
				String ven=V[z2*20+4];	
				String diass=ven.substring(0, 2);
				String mesess=ven.substring(3, 5);
				String anoss=ven.substring(6, 10);
				String venc=anoss+"-"+mesess+"-"+diass;
		
				
				
				me.CrearEntradas(movi, V[z2*20+1], venc, V[z2*20+2], V[z2*20+5], V[z2*20+8], V[z2*20+9], V[z2*20+7], bod, fec, hra, provee, V[z2*20+3]);
			
				
				
				///obtener ultimo inv
			 rs2=me.ObtenerUEntradas(fec, hra);
				String in="";
				try {
					while(rs2.next()){
					in=rs2.getString(1);
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			////crear detalle mov
				int cp=(Integer.parseInt(V[z2*20+11])-Integer.parseInt(V[z2*20+5]));
				String cps=String.valueOf(cp);
				
				double cm=(Integer.parseInt(V[z2*20+5])*0.1)+1;
				String cms=String.valueOf(cm);
				
				System.out.println("valor de z2 "+z2);
				 me.CrearActa(in,orden,fact,V[z2*20+11],V[z2*20+5],cps,V[z2*20+12],V[z2*20+13],V[z2*20+14],V[z2*20+15],V[z2*20+16],V[z2*20+17],recibe,entrega,"10%+1","ALEATORIO",cms,V[z2*20+18],tentrega,verifica,observacion,V[z2*20+19],fec,horan,tiempo,V[z2*20+10]);

			  me.CrearDetalle(movi,in,bod,V[z2*20+5]);	
			
			}
		  }
		 ////////////////
		  
		 
		out.print(movi);
		
		  
		  //response.sendRedirect("farc_ReporteEntradas.jsp?movi="+movi);
		 //out.print("Ingreso Exitoso.");
		}
		}
				



			
		if(va.equals("autoart")){
			try {
				rs =me.listarArticulos(texto);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+"|"+rs.getString(1)+"|"+rs.getString(7)+"'";
                	cadena = cadena +",";	
				}
				cadena = cadena+"]";
				response.getWriter().write(cadena);
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
			
		if(va.equals("autoiva")){
			try {
				rs =me.listarIvas(texto);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(2)+"|"+rs.getString(1)+"'";
                	cadena = cadena +",";	
				}
				cadena = cadena+"]";
				response.getWriter().write(cadena);
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("AnularE")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Anulación de Entradas</div></td></tr>");
			out.print("<tr><td>Movimiento Número</td><td><label><input name='txtMov' type='text' id='txtMov' size='40'/><td>Fecha</td><td><label><input name='txtVence0' type='text' id='txtVence0' onKeyup='mascas(this,patron,true,"+dia+","+mes+","+annio+")' onBlur='checkfecha(0)' size='35' /> dd/mm/aaaa</label></td></tr>");
			out.print("<tr><td>No. Doc. Soporte</td><td><label><input name='txtDoc' type='text' id='txtDoc' size='40'/><td>Realizado por</td><td><label><input name='txtR' type='text' id='txtR' size='35' /></label></td></tr>");
			out.print("<tr><td width='13%'>Tipo de Movimiento</td><td width='45%'><label><select name='cmbTipoM' id='cmbTipoM'  ><option value='Seleccione'>Seleccione</option>");
			rs3=me.listarMovimientose();
			try {
				while(rs3.next()){
				out.print("<option value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
				//out.print("a"+rs3.getString(1)+" b"+rs3.getString(2)); 
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td></tr>");
			out.print("<tr></tr><tr><td colspan='4'><div align='center'><label><input type='button' name='btnConsultarE' id='btnConsultarE' value='     CONSULTAR     ' onClick='ConsultarEA()'></label></div></td></tr></table>");
			out.print("<tr><td><div id='mova'></div></td></tr>");

			
		}
		////////////////////////////////////
		
		if(va.equals("AnularE2")){	
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Resultados de la Busqueda</div></td></tr>");
			String sql="select m.codigo,m.fecha,m.nsoporte,t.descripcion,m.concepto,u.usuario from farc_movimientos m,seg_usuario u, farc_tipomovimiento t where m.cod_usuarioFk=u.usu_codigo and m.tipo_mvtoFk=t.codigo and t.cod_movFk='1'";
			if(!nmov.equals("")){
				sql=sql+" and m.codigo='"+nmov+"'";
			}
			if(!fechas.equals("")){
				sql=sql+" and m.fecha='"+fechas+"'";
			}
			if(!documento.equals("")){
				sql=sql+" and m.nsoporte='"+documento+"'";
			}
			if(!R.equals("")){
				sql=sql+" and u.usuario='"+R+"'";
			}
			if(!tmov.equals("Seleccione")){
				sql=sql+" and m.tipo_mvtoFk='"+tmov+"'";
			}
			//out.print("nmov "+nmov+" fechas "+fechas+" documento "+documento+" R "+R+" tmov "+tmov);
			//out.print(sql);
			out.print("<table width='100%' border='1' class='style6'><tr><td width='7%'><div align='center'>Mov. No</div></td><td width='9%'><div align='center'>Fecha</div></td><td width='12%'><div align='center'>Doc. Soporte</div></td><td width='23%'><div align='center'>Movimiento</div></td><td width=30%'><div align='center'>Concepto</div></td><td width='15%'><div align='center'>Realizado por</div></td><td width='4%'><div align='center'>Ver</div></td></tr>");
			rs =me.ObtenerMoviA(sql);
			String color="'#FF6699'";
			try {
				while(rs.next()){
					out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='white'  ><td onclick='anular("+rs.getString(1)+")'>"+rs.getString(1)+"</td>");
					out.print("<td onclick='anular("+rs.getString(1)+")' >"+rs.getString(2)+"</td>");//onMouseOver='
					out.print("<td onclick='anular("+rs.getString(1)+")'>"+rs.getString(3)+"</td>");
					out.print("<td onclick='anular("+rs.getString(1)+")'>"+rs.getString(4)+"</td>");
					out.print("<td onclick='anular("+rs.getString(1)+")'>"+rs.getString(5)+"</td>");
					out.print("<td onclick='anular("+rs.getString(1)+")'>"+rs.getString(6)+"</td>");
					out.print("<td><a  href='#'onclick='veranular("+rs.getString(1)+")'>VER</a></td>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("AnularE3")){	
			sw="0";
			rs1 =me.ObtenerInvAnular(nmov);
			try {
				while(rs1.next()){
					///////////////////////
					rs2 =me.ObtenerMovAnular(nmov,rs1.getString(1));
					try {
						while(rs2.next()){
							sw="1";							
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					////////disp/////////////
					
					rs3 =me.ObtenerDispAnular(rs1.getString(1));
					try {
						while(rs3.next()){
							sw="1";							
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					////////////////////////
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("<tr><td><label><input name='txtSw' type='hidden' id='txtSw' size='133%' value='"+sw+"'/></tr>");
			
		}
		//////////////////////////////////////
		if(va.equals("AnularE4")){	
			
			if(sw.equals("0")){
				out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Entrada Seleccionada</div></td></tr>");
				String sql="select m.codigo,m.fecha,m.nsoporte,t.descripcion,m.concepto,u.usuario,m.tipo_mvtoFk from farc_movimientos m,seg_usuario u, farc_tipomovimiento t where m.cod_usuarioFk=u.usu_codigo and m.tipo_mvtoFk=t.codigo and t.cod_movFk='1' and m.codigo='"+nmov+"'";
				
				out.print("<table width='100%' border='1' class='style6'><tr><td width='7%'><div align='center'>Mov. No</div></td><td width='9%'><div align='center'>Fecha</div></td><td width='12%'><div align='center'>Doc. Soporte</div></td><td width='23%'><div align='center'>Movimiento</div></td><td width=30%'><div align='center'>Concepto</div></td><td width='15%'><div align='center'>Realizado por</div></td><td width='4%'><div align='center'>Ver</div></td></tr>");
				rs =me.ObtenerMoviA(sql);
				String tipomov="";
				try {
					while(rs.next()){
						out.print("<tr><td>"+rs.getString(1)+"</td>");
						out.print("<td>"+rs.getString(2)+"</td>");
						out.print("<td>"+rs.getString(3)+"</td>");
						out.print("<td>"+rs.getString(4)+"</td>");
						out.print("<td>"+rs.getString(5)+"</td>");
						out.print("<td>"+rs.getString(6)+"</td>");
						tipomov=rs.getString(7);
						out.print("<td><a  href='#'onclick='veranular("+rs.getString(1)+")'>VER</a></td>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print("</table><table width='100%' border='1' class='style6'>");
				out.print("<tr><td width='15%'>Concepto de Anulación</td><td td width='85%'><label><input name='txtCanul' type='text' id='txtCanul' size='133%'/></tr>");
				out.print("</table><table width='100%' border='0' class='style6'>");
				out.print("<tr></tr><tr><td colspan='4'><div align='center'><label><input type='button' name='btnAnularE' id='btnAnularE' value='     ANULAR     ' onClick='AnularEA("+nmov+","+tipomov+")'></label></div></td></tr></table>");
			}
			///hacer el if del sw
		}
		/////////////////////////////////////////
		
		if(va.equals("AnularE5")){	
		Calendar calendario = Calendar.getInstance();
		//	Calendar calendario = new GregorianCalendar();
			int hora, minutos, segundos;
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= hora+":"+minutos+":"+segundos;
			String user = request.getParameter("user");
			
			String dias=fecha.substring(0, 2);
			String meses=fecha.substring(3, 5);
			String anos=fecha.substring(6, 10);
			String fec=anos+"-"+meses+"-"+dias;
			
			//Creamos el movimiento de anulacion
			me.CrearAnulacion(fec,hra,user,canul,tmov,nmov);
			//Consultamos el codigo de la anulacion
			rs=me.ObtenerAnulacion(fec, hra);
			String coda="";
			try {
				if(rs.next()){
				 coda=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//Creamos los detalles de movimientos de la anulacion
			rs1=me.ObtenerDetalleAnulacion(nmov);
			try {
				while(rs1.next()){
					me.CrearDetalleAnulacion(coda,rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10));
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			me.Anulardmov(nmov);
			me.Anularinv(nmov);
			me.Anularmov(nmov);
		
		}
		////////////////////////



if(va.equals("1h")){
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Pacientes Hospitalizados </div></td></tr>");
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='50%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisión</div></i></td></tr>");  //
			
			out.print("<tr><td><input type=text id='desc0' onkeyup='autocompletaPacientesh(0,0,0)' style='width:100%' >"); 
			out.print("<input name='desch0' type='hidden' id='desch0'/></td>");
			out.print("<td><input type=text id='eps0'  readonly='readonly' style='width:100%' >"); 
			out.print("<input name='epsh0' type='hidden' id='epsh0'/></td>");
			out.print("<td><input type=text id='adm0'  readonly='readonly' style='width:100%' ></tr>"); 
			//out.print("<td><input id='iraFact' type=button value='Facturar' disabled='true' ></td></tr>"); 
		
			out.print("<tr><td><div id='sugerencias0' ></div></td></tr></table>");
			out.print("</table>");
			out.print("<tr><td><div id='resul2' ></div></td></tr></table>");
			out.print("<tr><td><div id='resul' ></div></td></tr></table>");
			out.print("<tr><td><div id='resulcrea' ></div></td></tr></table>");
		}
		
		if(va.equals("2h")){
			String fe="";
			Double imc=0.0;
			String para="";
			rs2 =me.listarPerfil(cont);
			try {
				if(rs2.next()){
					if(rs2.getString(5)==null){fe="Activo";}else{fe=rs2.getString(5);}
					//Math.rint(numero*1000)/1000;
					imc=Math.rint((Integer.parseInt(rs2.getString(13))/(Double.parseDouble(rs2.getString(14))*Double.parseDouble(rs2.getString(14))))*1000)/1000;
					if(imc<18.5){para="Desnutricion";}
					if((imc>=18.5)&&(imc<24.9)){para="Normopeso";}
					if((imc>=25)&&(imc<29.9)){para="Sobrepeso";}
					if((imc>=30)&&(imc<34.9)){para="Obesidad Grado I";}
					if((imc>=35)&&(imc<39.9)){para="Obesidad Grado II";}
					if(imc>=40){para="Obesidad Grado III";}
					out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='10%' ><i><div align='center'>F.Ingreso</div></i></td><td width='10%' ><i><div align='center'>F.Egreso</div></i></td><td width='40%'><i><div align='center'>Servicio/Cama</div></i></td><td width='5%' ><i><div align='center'>Sexo</div></i></td><td width='5%'>Edad</td><td width='5%'>Peso(Kg)</td><td width='5%'>Talla</td><td width='5%'>IMC</td><td width='15%'>Parametro IMC</td></tr>");  //
					out.print("<tr><td>"+rs2.getString(4)+"</td><td>"+fe+"</td><td>"+rs2.getString(6)+" - "+rs2.getString(7)+" - "+rs2.getString(8)+"</td><td>"+rs2.getString(11)+"</td><td>"+rs2.getString(12)+"</td><td>"+rs2.getString(13)+"</td><td>"+rs2.getString(14)+"</td><td>"+imc+"</td><td>"+para+"</td></tr>"); 
					out.print("<table width='100%' border='1' class='style6' ><tr><td width='50%' ><i><div align='lefth'>Diagnosticos: <input type=text id='eps0'  readonly='readonly' style='width:580px' value='"+rs2.getString(9)+"'></div></i></td><td width='50%' ><i><div align='lefth'>Alergias:<input type=text id='eps0'  readonly='readonly' style='width:605px'  value='"+rs2.getString(10)+"' ></div></i></td></tr></table>");  //
					
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		
if(va.equals("3h")){
			
			rs2 =me.listarPerfil(cont);
			try {
				if(rs2.next()){
					out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Detalle del Perfil farmacoterapeutico</div></td></tr>");
					out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='2%' rowspan='2'><i><div align='center'>EST.</div></i></td><td width='13%' rowspan='2'><i><div align='center'>NOMBRE GENERICO</div></i></td><td width='10%' rowspan='2'><i><div align='center'>FF/CONCENTRACION</div></i></td><td width='7%' rowspan='2'><i><div align='center'>VIA DE ADMON.</div></i></td><td width='7%' rowspan='2'><i><div align='center'>FRECUENCIA.</div></i></td><td width='5%' rowspan='2'><i><div align='center'>DIA TTO. <BR>FECHA</div></i></td>");  //
				
					//out.print("<tr BGCOLOR='#D3D3D3'><td><div id='tthoo' >julio</div></td></tr>"); 
				
					//////Detalle del perfil encabezados/////////////
					String VM[] = new String[100];
					String VVIA[] = new String[100];
					String VFRE[] = new String[100];
					
					String VD[] = new String[100];
					String VD13[] = new String[100];
					String VF[] = new String[100];
					String VF2[] = new String[100];
					int indf=0;
					int indm=0;
				//	int contador=0;
					
					
					rs5 = me.listarDetallePerfil(rs2.getString(1));
					try {
						while(rs5.next()){
							int sw1=0;
							int swm1=0;
							if(indf==0){
								VF[indf]=rs5.getString(8).substring(5);	
								VF2[indf]=rs5.getString(8).substring(8);
								indf++;
							}else{
							for(int i=0;i<indf;i++){
								//System.out.println("rs5.getString(8).substring(5): "+rs5.getString(8).substring(5)+" VF["+i+"]: "+VF[i]);
								if(rs5.getString(8).substring(5).equals(VF[i])){
									sw1=1;
								}	
							}
							if(sw1==0){
								VF[indf]=rs5.getString(8).substring(5);	
								VF2[indf]=rs5.getString(8).substring(8);	
								indf++;
								//System.out.println("sumamos la fecha en: "+rs5.getString(8).substring(5));
							}
						}
						
												
							if(indm==0){
								VM[indm]=rs5.getString(3);
								VVIA[indm] = rs5.getString(5);
								VFRE[indm] = rs5.getString(6);
								indm++;
							}else{
							for(int i=0;i<indm;i++){
								if((rs5.getString(3).equals(VM[i]))&&(rs5.getString(5).equals(VVIA[i]))&&(rs5.getString(6).equals(VFRE[i])))          {
									swm1=1;
								}	
							}
							if(swm1==0){
								VM[indm]=rs5.getString(3);	
								VVIA[indm] = rs5.getString(5);
								VFRE[indm] = rs5.getString(6);
								indm++;
							}
							}
							
						}
						rs5.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}



						
					//////////////////SI NO TIENE NADA DISPENSADO O DEVUELTO///////////////////
					if(indm==0){
						String xVM[] = new String[100];
						String xVVIA[] = new String[100];
						String xVFRE[] = new String[100];
						
						//String VD[] = new String[100];
						//String VD13[] = new String[100];
						String xVF[] = new String[100];
						String xVF2[] = new String[100];
						int xindf=0;
						int xindm=0;
						
						
						
						rs5 = me.listarDetallePerfil3(rs2.getString(1));
						try {
							while(rs5.next()){
								int xsw1=0;
								int xswm1=0;
								if(xindf==0){
									xVF[xindf]=rs5.getString(8).substring(5);	
									xVF2[xindf]=rs5.getString(8).substring(8);
									xindf++;
								}else{
								for(int i=0;i<xindf;i++){
									//System.out.println("rs5.getString(8).substring(5): "+rs5.getString(8).substring(5)+" VF["+i+"]: "+VF[i]);
									if(rs5.getString(8).substring(5).equals(xVF[i])){
										xsw1=1;
									}	
								}
								if(xsw1==0){
									xVF[xindf]=rs5.getString(8).substring(5);	
									xVF2[xindf]=rs5.getString(8).substring(8);	
									xindf++;
									//System.out.println("sumamos la fecha en: "+rs5.getString(8).substring(5));
								}
							}
							
													
								if(xindm==0){
									xVM[xindm]=rs5.getString(3);
									xVVIA[xindm] = rs5.getString(5);
									xVFRE[xindm] = rs5.getString(6);
									xindm++;
								}else{
								for(int i=0;i<xindm;i++){
									if((rs5.getString(3).equals(xVM[i]))&&(rs5.getString(5).equals(xVVIA[i]))&&(rs5.getString(6).equals(xVFRE[i])))          {
										xswm1=1;
									}	
								}
								if(xswm1==0){
									xVM[xindm]=rs5.getString(3);	
									xVVIA[xindm] = rs5.getString(5);
									xVFRE[xindm] = rs5.getString(6);
									xindm++;
								}
								}
								
							}
							rs5.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						
						out.print("<td  width='60%'></td></tr><tr><td></td></tr>");

						for(int y=0; y<xindm; y++){
						rs4 =me.listarMedicamentosPerfil(xVM[y]);
						try {
							if(rs4.next()){
								out.print("<tr><td  rowspan='1'><div align='center'><select id='cmbE'><option value='A'>A</option></div></td><td  rowspan='1'>"+rs4.getString(2)+"</td><td rowspan='1'>"+rs4.getString(3)+" "+rs4.getString(4)+" "+rs4.getString(5)+"</td><td rowspan='1'>"+xVVIA[y]+"</td><td rowspan='1'>"+xVFRE[y]+"</td>"); 
							}
							rs4.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
							
						//out.print("<td >Dispensada</td>");
						}
						
						
						//out.print("<td BGCOLOR='#D3D3D3' >NO HAN DISPENSADO NADA JAJAJAJ</td>");
						System.out.println("<td BGCOLOR='#D3D3D3' >NO HAN DISPENSADO NADA JAJAJAJ</td>");
					}
					
					//////////////////FINNNN SI NO TIENE NADA DISPENSADO O DEVUELTO///////////////////
					
					

					
					String td=String.valueOf(60-(indf*2));
					if((indf*2)>=60){
						td="0";
					}
										
					//System.out.println("td: "+td);
					for(int i=1;i<=indf;i++){
						out.print("<td width='2%'>"+i+"</td>");
						//System.out.println("i: "+i);
					}
					out.print("<td width='"+td+"%'></td>");
					out.print("</tr><tr BGCOLOR='#D3D3D3'>");
					for(int i=0;i<indf;i++){
						out.print("<td title='mes-dia: "+VF[i]+"'><div id='tthoo' >"+VF2[i]+"</div></td>"); 
					}
					out.print("<td></td></tr>");
					//////Detalle del perfil/////////////
					
					int validador=0;
					int indd=0;
					for(int j=0;j<indm;j++){//////////////////////////////////////////////PRINCIPALL////////////////////////////
					//	System.out.println("VF["+i+"]: "+VF[i]);
					int swmq=0;
					
					for(int y=0;y<indd;y++){
						//System.out.println(" VD13["+y+"]: "+VD13[y]);
						VD13[y]=null;
						indd=0;
					}
					String ve="";
					String ee="";
					String ee1="";
					String ee2="";
					String ee3="";
					
					int NI=0;
					int T=0;
					int TD=0;
					rs3 = me.listarDetallePerfil(rs2.getString(1),VM[j],VVIA[j],VFRE[j]);
					try {
						while(rs3.next()){
							///////////Medicamentos en el perfil///////////
							ve=rs3.getString(1);
							ee=rs3.getString(11);
							if(ee.equals("0")){ee1="A";ee2="C";ee3="S";}
							if(ee.equals("1")){ee1="C";ee2="A";ee3="S";}
							if(ee.equals("2")){ee1="S";ee2="A";ee3="C";}
							
							if(swmq==0){
							rs4 =me.listarMedicamentosPerfil(rs3.getString(3));
							try {
								if(rs4.next()){
									
									
									if(ee1.equals("A")){
									out.print("<tr><td  rowspan='2'><div align='center'><select id='cmbE"+j+"' onchange='estaopac("+j+","+cont+")'><option value='"+ve+"'>"+ee1+"</option><option value='"+ve+"'>"+ee2+"</option><option value='"+ve+"'>"+ee3+"</option></div></td><td  rowspan='2'>"+rs4.getString(2)+"</td><td rowspan='2'>"+rs4.getString(3)+" "+rs4.getString(4)+" "+rs4.getString(5)+"</td><td rowspan='2'>"+rs3.getString(5)+"</td><td rowspan='2'>"+rs3.getString(7)+" "+rs3.getString(6)+"</td>"); 
									}
									if(ee1.equals("C")){
									out.print("<tr><td  rowspan='2'><div align='center'><select id='cmbE"+j+"' onchange='estaopac("+j+","+cont+")'><option value='"+ve+"'>"+ee1+"</option><option value='"+ve+"'>"+ee2+"</option><option value='"+ve+"'>"+ee3+"</option></div></td><td  rowspan='2'><font color=grey>"+rs4.getString(2)+"</font></td><td rowspan='2'><font color=grey>"+rs4.getString(3)+" "+rs4.getString(4)+" "+rs4.getString(5)+"</font></td><td rowspan='2'>"+rs3.getString(5)+"</td><td rowspan='2'>"+rs3.getString(7)+" "+rs3.getString(6)+"</td>"); 
									}
									if(ee1.equals("S")){
										out.print("<tr><td  rowspan='2'><div align='center'><select id='cmbE"+j+"' onchange='estaopac("+j+","+cont+")'><option value='"+ve+"'>"+ee1+"</option><option value='"+ve+"'>"+ee2+"</option><option value='"+ve+"'>"+ee3+"</option></div></td><td  rowspan='2'><font color=red>"+rs4.getString(2)+"</font></td><td rowspan='2'><font color=red>"+rs4.getString(3)+" "+rs4.getString(4)+" "+rs4.getString(5)+"</font></td><td rowspan='2'>"+rs3.getString(5)+"</td><td rowspan='2'>"+rs3.getString(7)+" "+rs3.getString(6)+"</td>"); 
									}
								}
								rs4.getStatement().getConnection().close();
							} catch (SQLException e) {
								out.print("Error "+e);
								e.printStackTrace();
							}
								
							out.print("<td >Dispensada</td>");
							}//fin swmq
								
							//System.out.println("NNNNNIIIIII "+NI);
							
							int SW=0;
							while((SW==0)&&(NI<=indf)){					
								//System.out.println("2222222222NNNNNIIIIII "+NI);
							//for(int i=0;i<indf;i++){
								System.out.println("xxxxxrs5.getString(8).substring(5): "+rs3.getString(14).substring(5)+" VF["+NI+"]: "+VF[NI]);
								
								if(rs3.getString(14).substring(5).equals(VF[NI])){
									out.print("<td title='"+rs3.getString(9)+" "+rs3.getString(15)+" "+rs3.getString(16)+"'>"+rs3.getString(12)+"</td>");
									VD[indd]=rs3.getString(13);
									VD13[indd]=rs3.getString(14).substring(5);
									indd++;
									NI++;
									SW=1;
									T=T+Integer.parseInt(rs3.getString(12));
									System.out.println("FECHAREAL "+NI);
								}else{
									NI++;
									out.print("<td BGCOLOR='#D3D3D3' ></td>");
									System.out.println("FECHANUEVA "+NI);
								}
							//}//fin for i
							}//fin while sw==0
								///////////Fin Medicamentos en el perfil///////////
							swmq++;	
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					if(NI<indf){
						for(int x=NI; x<indf; x++){
							out.print("<td BGCOLOR='#D3D3D3' ></td>");
						}
					}
					
					out.print("<td>T: "+T+"</td>");
					
					out.print("</tr><tr>");//out.print("<td>Total:</td></tr><tr>");
										
					out.print("<td  >Devuelta</td>");
					for(int i=0;i<indf;i++){
						int swd=0;
						for(int z=0;z<indd;z++){
						//System.out.println("VD13: "+VD13[z]+" VF["+i+"]: "+VF[i]);
						if(VD13[z].equals(VF[i])){
							out.print("<td>"+VD[z]+"</td>");
							swd=1;
							TD=TD+Integer.parseInt(VD[z]);
						}
						}
						if(swd==0){out.print("<td BGCOLOR='#D3D3D3' ></td>");}
					}
					
					out.print("<td>T: "+TD+"</td>");
					
				 }//fin del if de los codigos de medicamentos del vector m
				}else{
					out.print("Este Paciente no tiene activo ningun Perfil Farmacoterapeutico");
				}
				
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		

if(va.equals("4h")){
	String inf="";
	rs2 =me.listarPerfil(cont);
	try {
		if(rs2.next()){
			inf=rs2.getString(15);
			if(inf==null){inf="";}
			//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='20%' ><i><div align='center'>Medico Tratante</div></i></td><td width='10%' ><i><div align='center'>Servicio/Cama</div></i></td><td width='5%'><i><div align='center'>Edad</div></i></td><td width='5%' ><i><div align='center'>Sexo</div></i></td><td width='20%'>peso Kg</td><td width='20%'>Talla</td><td width='20%'>IMC</td></tr>");  //
		//	out.print("<tr><td>AROCA</td><td>PAB. MAR UN-301</td><td>xxxxxs</td><td><div id='ttpo' ></div></td></tr>");
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td>INTERVENCION FARMACEUTICA:<input type='text'  id='observacion' style='width:100%' value='"+inf+"'></td><td width='5%' ><input type='button' id='btnG' value='GUARDAR' onClick='Intervencion("+cont+")'></td></tr></table>");
		}
		rs2.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
}


if(va.equals("5h")){
	String v=request.getParameter("v");
	String x=request.getParameter("x");
	
	String cp="";
	String cm="";
	String vi="";
	String fr="";
	
	rs2 =me.listarDetallePerfil2(v);
	try {
		if(rs2.next()){
			cp=rs2.getString(1);	
			cm=rs2.getString(2);	
			vi=rs2.getString(3);	
			fr=rs2.getString(4);	
		}
		rs2.getStatement().getConnection().close();
	} catch (SQLException e) {
		out.print("Error "+e);
		e.printStackTrace();
	}
	
	me.ActualizarEstadoDPerfil(x,cp,cm,vi,fr);
}


if(va.equals("6h")){
	String v=request.getParameter("v");
	me.ActualizarObservacionPerfil(v,cont);
	out.println("Actualizacion exitosa!!!");
}


		
		if(va.equals("autoinvh")){
			//System.out.println("entramos al inv d control entradas");
			try {
				//System.out.print("ESSSTOO."+xx);
				rs =me.listarPacientesh(texto);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6)+"|"+rs.getString(7)+"|"+rs.getString(1)+"'";
	            	cadena = cadena +",";	
				}
				cadena = cadena+"]";
				//System.out.print("ESSSTOO."+cadena);
				response.getWriter().write(cadena);
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
		
		
		
		
		if(va.equals("1x")){
			out.print("<table width='100%' class='style6' border='0'><tr><td colspan='2' ><div align='center' class='style11' id='cabecera2'>ORDEN DE PRODUCCION DE MEDICAMENTOS</div></td></tr>");
			rs2 =me.listarOrdenP();
			int swop=0;
			try {
				if(rs2.next()){
					out.print("<tr></tr><tr><td width='20%'>No. ORDEN DE PRODUCCION: "+rs2.getString(1)+"</td width='20%'><td>FECHA: "+rs2.getString(2)+"</tr><tr></tr>");
					out.print("<input name='epsh0' type='hidden' id='opp' value="+rs2.getString(1)+" >");
					swop=1;
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			out.print("<input name='epsh0' type='hidden' id='op' value="+swop+" >");
			
			if(swop==0){
				out.print("<table width='100%' border='0'><tr><tr><td><div align='center'>NO HAY ORDEN DE PRODUCCION ACTIVA</div></td></tr></table>");
			}
			
			out.print("<tr><td colspan='2' ><div id='detalle'></div></td></tr>");
			out.print("<tr><td colspan='2' ><div id='aprobar'></div></td></tr></table>");
		}





		
	if(va.equals("2x")){
			String VM[] = new String[250];
			Double VV[] = new Double[250];
			int ivm=0;
			int ivv=0;
			//out.print("<table width='100%' class='style6' border='0'><tr><td colspan='2' ><div align='center' class='style11' id='cabecera2'>ORDEN DE PRODUCCION DE MEDICAMENTOS</div></td></tr>");
			rs2 =me.listarOrdenP();
			try {
				if(rs2.next()){
					//out.print("<tr></tr><tr><td width='20%'>No. ORDEN DE PRODUCCION: "+rs2.getString(1)+"</td width='20%'><td>FECHA: "+rs2.getString(2)+"</tr><tr></tr>");
					////////////////////////////////////DETALLE DE LA ORDEN/////////////////////
					
					out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='12%'><i><div align='center'>Grupo Farmacologico</div></i></td><td width='2%'><i><div align='center'>No Mx</div></i></td><td width='7%'><i><div align='center'>Fecha</div></i></td><td width='4%'><i><div align='center'>No. Orden Medica</div></i></td><td width='14%'><i><div align='center'>Nombre del Paciente</div></i></td><td width='7%'><i><div align='center'>No de Identificacion</div></i></td><td width='15%'><i><div align='center'>Servicio/Habitacion</div></i></td><td width='12%'><i><div align='center'>Medicamento</div></i></td><td width='3%'><i><div align='center'>[ ]mg</div></i></td><td width='3%'><i><div align='center'>Diluyente</div></i></td><td width='2%'><i><div align='center'>V.R (ml)</div></i></td><td width='3%'><i><div align='center'>Dosis(mg)</div></i></td><td width='2%'><i><div align='center'>F(h)</div></i></td><td width='3%'><i><div align='center'>No Unidades</div></i></td><td width='3%'><i><div align='center'>Vol Dosis(ml)</div></i></td><td width='3%'><i><div align='center'>No de dosis a adecuar</div></i></td><td width='4%'><i><div align='center'>Volumen final de dosis (ml)</div></i></td><td  width='1%'></td><td>Revisado</td></tr>");  //
					rs3 =me.listarDetalleOrdenP(rs2.getString(1));
					
					try {
						while(rs3.next()){
							int swm=0;
							//System.out.println("cesar_:"+rs3.getString(1));
							////////////////SE CONSULTA A LA TABLA ADM_PACIENTES///////////////////////
							String pac="";
							String ipac="";
							rs5=me.listarPacientes(rs3.getString(6));
							try {
								if(rs5.next()){
									ipac=rs5.getString(1)+" "+rs5.getString(2);
									pac=rs5.getString(3)+" "+rs5.getString(4)+" "+rs5.getString(5);
								}
								rs5.getStatement().getConnection().close();
							} catch (SQLException e) {
								out.print("Error "+e);
								e.printStackTrace();
							}
							////////////////FIN ADM_PACIENTES///////////////////////
										
							
							////////////////SE CONSULTA A LA TABLA FARC_MEDORDENPRODUCCION///////////////////////
							 
							////////////////SE CONSULTA A LA TABLA MEDICAMENTOS///////////////////////
										String gf="";
										rs5=me.listarGrupoFarmacologico(rs3.getString(22));
										try {
											if(rs5.next()){
												gf=rs5.getString(1);
											}
											rs5.getStatement().getConnection().close();
										} catch (SQLException e) {
											out.print("Error "+e);
											e.printStackTrace();
										}
										////////////////FIN MEDICAMENTOS///////////////////////
										
										////////////////SE CONSULTA A LA TABLA MEDICAMENTOS///////////////////////
										String med="";
										rs5=me.listarMedicamento(rs3.getString(17));
										try {
											if(rs5.next()){
												med=rs5.getString(1);
											}
											rs5.getStatement().getConnection().close();
										} catch (SQLException e) {
											out.print("Error "+e);
											e.printStackTrace();
										}
										
										
										////////////////FIN MEDICAMENTOS///////////////////////
										
										
									int ndaa=24/Integer.parseInt(rs3.getString(10));
									//System.out.println("ndaa:"+ndaa);
									//float caad=((Integer.parseInt(rs3.getString(9))*ndaa)/Integer.parseInt(rs4.getString(3)));
									
									double x1=Double.parseDouble(rs3.getString(9))*ndaa;
									//System.out.println("x1:"+x1);
									//System.out.println("x1111x:"+x1);
									
									double x2=x1/Double.parseDouble(rs3.getString(18));
									System.out.println("x2:"+x2);
									double	x10 =0;
									if(x2 > 0){
										x10 = Math.floor(x2 * Math.pow(10,3))/Math.pow(10,3);
										System.out.println("x10>0:"+x10);	
									}else{
										x10 = Math.ceil(x2 * Math.pow(10,3))/Math.pow(10,3);
										System.out.println("x10<=0:"+x10);	
									}
									
									//long mult=(long)Math.pow(10,2);
									//double x10=(Math.round(x2*mult))/(double)mult;
									
									//System.out.println("x2222x:"+x2);
									//double x10=Math.rint(x2*100)/100;
									double x = Math.ceil(x2);
									//System.out.println("xxxxxxxxx:"+x);
									
									double vdd=((Double.parseDouble(rs3.getString(9))*Double.parseDouble(rs3.getString(20)))/Double.parseDouble(rs3.getString(18)));
										/*System.out.println(vdd);
										System.out.println("Corregido: "+Math.ceil(vdd));
										System.out.println("redondeado: "+Math.rint(vdd*100)/100);
									*/vdd=Math.rint(vdd*100)/100;
										
									out.print("<tr><td>"+gf+"</td>");
									out.print("<td>"+rs3.getString(21)+"</td>");
									out.print("<td>"+rs2.getString(2)+"</td>");
									out.print("<td>"+rs3.getString(4)+"</td>");
									out.print("<td>"+pac+"</td>");
									out.print("<td>"+ipac+"</td>");
									out.print("<td>"+rs3.getString(7)+" "+rs3.getString(8)+"</td>");
									out.print("<td>"+med+"</td>");
									out.print("<td>"+rs3.getString(18)+"</td>");
									out.print("<td>"+rs3.getString(19)+"</td>");
									out.print("<td>"+rs3.getString(20)+"</td>");
									//out.print("<td>"+rs3.getString(9)+"</td>");
									out.print("<td><label><input type='text' id='d"+rs3.getString(1)+"'  value='"+rs3.getString(9)+"' onBlur='CDosis("+rs3.getString(1)+")' style='width:100%' /></td>");//size='6'
									out.print("<td>"+rs3.getString(10)+"</td>");
									out.print("<td>"+x10+"</td>");
									out.print("<td>"+vdd+"</td>");
									out.print("<td>"+ndaa+"</td>");
																	
									out.print("<td><select id='cmbVF"+rs3.getString(1)+"' onChange='volumen("+rs3.getString(1)+","+vdd+")'>"); 
									int cc=0;
									String vfdd2="";
									String pfdd2="";

									rs7=me.BuscarVolumenAsignado(rs3.getString(1));
									String descripvol="";
									String valorvol="";
									while(rs7.next()){
										descripvol=rs7.getString(2);
										valorvol=rs7.getString(1);
									}
									if((descripvol!=null)&&(valorvol!=null)){
									out.print("<option title='"+descripvol+"'  value='"+valorvol+"'>"+valorvol+"");
									}
									rs7.getStatement().getConnection().close();

									rs5=me.listarConstantesVolumen(rs3.getString(3));
									try {
										while(rs5.next()){
											//med=rs5.getString(1);
											double vfdd=Integer.parseInt(rs5.getString(2))+vdd;
											out.print("<option title='"+rs5.getString(3)+"' value='"+rs5.getString(1)+"'>"+vfdd+"</option>");
											if(cc==0){vfdd2=String.valueOf(vfdd); pfdd2=rs5.getString(3);}
											cc++;
										}
										rs5.getStatement().getConnection().close();
									} catch (SQLException e) {
										out.print("Error "+e);
										e.printStackTrace();
									}
									out.print("</select></td>");
									
									out.print("<td><img src='Imagenes/Eliminar.JPG' onClick='EliminarMed("+rs3.getString(1)+",&quot;"+med+"&quot;,&quot;"+pac+"&quot;)'></td>");
									
									rs6=me.BuscarEstado(rs3.getString(1));
									String est="";
									while(rs6.next()){
										est=rs6.getString(1);
									}
									
									if(est.equals("3")){
										out.print("<td align='center'><font color='green'><b>OK</b></font></td></tr>");
									}else{
										out.print("<td align='center'><input type='radio' name='radiobutton' onClick='CambEst("+rs3.getString(1)+")'/></td></tr>");
									}
									rs6.getStatement().getConnection().close();

									if(rs3.getString(12)==null){
										me.ActualizarDetalle(vfdd2,pfdd2,rs3.getString(1));
										
										/*double ind=(double)((ndaa+1)/2.0);
										int idn=(int)(Math.ceil(ind));
										
										for(int i=0; i<idn; i++){
											me.CrearDetalleIndicador(rs2.getString(1),rs3.getString(1));
										}	*/
									
									}
									
									//GENERA EL TOTAL DE UNIDADES X MEDICAMENTO
								/*	if(ivm==0){
										VM[ivm]=med;	
										VV[ivv]=x10;
										ivm++;
										ivv++;
									}else{
										for(int i=0;i<ivm;i++){
											if(med.equals(VM[i])){
												VV[i]=VV[i]+x10;
												swm=1;
											}	
										}
										if(swm==0){
											VM[ivm]=med;	
											VV[ivv]=x10;
											ivm++;
											ivv++;
										}
									}
										*/							
									
						  ////////////////////////FIN DE CONSULTA FARC_MEDORDENPRODUCCION/////////////////////////
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					/////////////////////////////////////FIN DETALLE DE LA ORDEN///////////////////
					
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			/*for(int i=0;i<ivm;i++){
				System.out.println("Medicameto: "+VM[i]+" Valor: "+VV[i]);
			}*/
			
		}






		
		
		if(va.equals("3x")){
			//out.print("<table width='100%' class='style6' border='0'><tr><td colspan='2' ><div align='center' class='style11' id='cabecera2'>ORDEN DE PRODUCCION DE MEDICAMENTOS</div></td></tr>");
			String opp = request.getParameter("opp");
			out.print("<table width='100%' class='style6' border='0'><tr><td><div align='center'>QF Responsable de la produccion: <input type='text' id='responsable' size='100px'></div></td><td><div align='center'>Auxiliar de produccion: <input type='text' id='auxiliar'  size='200px'></div></td><td><div align='center'><input type='button' id='btnG' value=' VERIFICAR ORDEN' onClick='VerificarO("+opp+")'></td></div></tr></table>");
		}			
		
		
			if(va.equals("4x")){
			String opp = request.getParameter("opp");
			String resp = request.getParameter("resp");
			String aux = request.getParameter("aux");
			String user = request.getParameter("user");
			
			Calendar calendario = Calendar.getInstance();
			//	Calendar calendario = new GregorianCalendar();
				int hora, minutos, segundos;
				hora =calendario.get(Calendar.HOUR_OF_DAY);
				minutos = calendario.get(Calendar.MINUTE);
				segundos = calendario.get(Calendar.SECOND);
				String hra= hora+":"+minutos+":"+segundos;
			
			me.ActualizarPerfil(resp,aux,hra,user,opp);
			
			
			rs3 =me.listarDetalleOrdenP(opp);
			try {
				while(rs3.next()){
					int ndaa=24/Integer.parseInt(rs3.getString(10));
					double ind=(double)((ndaa)/2.0);
					
					int idn=(int)(Math.ceil(ind));
					
					for(int i=0; i<idn; i++){
						me.CrearDetalleIndicador(opp,rs3.getString(1));
					}	
				}
			rs3.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		
		}
		
		
		if(va.equals("5x")){
			
			String cmbVF = request.getParameter("cmbVF");
			String codd = request.getParameter("codd");
			String vdd = request.getParameter("vdd");
			float vddn=0;
			String med="";
			rs5=me.listarConstantesVolumenxcodigo(cmbVF);
			try {
				if(rs5.next()){
					vddn=Float.parseFloat(rs5.getString(1))+Float.parseFloat(vdd);
					me.ActualizarDetalle(String.valueOf(vddn),rs5.getString(2),codd);
				}
				rs5.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
				
		}
	//	
		if(va.equals("6x")){
			
			String coddetalle = request.getParameter("coddetalle");
			String can = request.getParameter("can");
			String user = request.getParameter("user");
			
			me.EliminarDetalle(can,user,coddetalle);
		}

 if(va.equals("7x")){
		 
			String codd = request.getParameter("codd");
			String vdd = request.getParameter("vdd");
			
			me.ActualizarDosis(vdd,codd);
		}





 if(va.equals("8x")){
		 
			String codd = request.getParameter("codd");
			//String vdd = request.getParameter("vdd");
			String cmbVF = request.getParameter("cmbVF");
			
			me.ActualizarVf(cmbVF,codd);
			
		}
		
	 if(va.equals("9x")){
		 
			String codd = request.getParameter("codd");
			//String vdd = request.getParameter("vdd");
			//String cmbVF = request.getParameter("cmbVF");
			
			me.ActualizarEsta(codd);
			
		}












	}
}





