package farc_controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import farc_metodo.MetodoCrearArticulo;
import farc_metodo.MetodoCrearMedicamento;
import farc_metodo.MetodoCrearProveedor;

public class ControlCrearMedicamentos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String va = request.getParameter("valor");
		PrintWriter out=response.getWriter();
		ResultSet rs;
		MetodoCrearArticulo mca = new MetodoCrearArticulo();
		MetodoCrearMedicamento mcm = new MetodoCrearMedicamento();
		
		if(va.equals("ff")){
			rs = mca.ObtenerFormaFarmaceutica();
			try {
				while(rs.next()){
				out.print(rs.getString(1)+"|"+rs.getString(2)+";");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("pre")){
			rs = mcm.obtenerPrecentacion();
			try {
				while(rs.next()){
				out.print(rs.getString(1)+"|"+rs.getString(2)+";");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("udpre")){
			rs = mcm.obtenerUDPrecentacion();
			try {
				while(rs.next()){
				out.print(rs.getString(1)+"|"+rs.getString(2)+";");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("pa")){
			String nomMed = request.getParameter("nomMedi");
			rs = mcm.obtenerPrincipioActivo(nomMed);
			try {
				while(rs.next()){
				out.print(rs.getString(1)+"|"+rs.getString(2));
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("pac")){
			String nomMed = request.getParameter("nomMedi");

			String[] detNomMed = nomMed.split("_");
			int coincidencias = 0;
			String prinActivos ="";
			for (int i=0;i<detNomMed.length;i++){
				rs = mcm.obtenerPrincipioActivo(detNomMed[i].trim());
				try {
					while(rs.next()){
						coincidencias++;
						if(detNomMed.length>1){
							prinActivos+=rs.getString(1)+"|";
						}else{
							if(detNomMed.length==1){
								prinActivos=rs.getString(1)+"|"+rs.getString(2);
							}
						}
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			}
			if(detNomMed.length==coincidencias){
				out.print(prinActivos);
				
			}
		}
		
		if(va.equals("ud")){
			rs = mca.ObtenerUnidad();
			try {
				while(rs.next()){
				out.print(rs.getString(1)+"|"+rs.getString(2)+";");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("prinactivo")){
			String nomMed = request.getParameter("compuestos");
			
			String sql = "";
			if(nomMed!=""){
				nomMed = nomMed.substring(0, nomMed.length()-1);
				nomMed = nomMed.replace('|', '_');
				String[] detNomMed = nomMed.split("_");
				for(int i=0;i<detNomMed.length;i++){
					//System.out.println(detNomMed[i]);
					if(i==0){
						sql += " WHERE fsg.cod_sustGenerica = "+detNomMed[i];
					}
					if(i>0){
						sql += " OR fsg.cod_sustGenerica = "+detNomMed[i]+" ";
					}
				}
			}
			
			
			rs = mcm.PrincipioActivo(sql);
			try {
				while(rs.next()){
				out.print(rs.getString(1)+"|"+rs.getString(2)+";");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		
		
		if(va.equals("conc")){
			String codMed = request.getParameter("codMed");
			rs = mcm.obtenerConcentracion(codMed);
			try {
				while(rs.next()){
				out.print(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+";");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("concComp")){
			String codMed = request.getParameter("codMed");
			rs = mcm.obtenerConcentracionComp(codMed);
			try {
				while(rs.next()){
				out.print(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+";");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("presentacion")){
			String codMed = request.getParameter("codMed");
			String codConc = request.getParameter("codConc");
			rs = mcm.verificarTipoCUMMed(codConc, codMed);
			String cpp = "";
			try {
				while(rs.next()){
					cpp = rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print(cpp+"%");
			
			rs = mcm.obtenerPresentaciom(codMed, codConc);
			try {
				while(rs.next()){
				out.print(rs.getString(1)+"|"+rs.getString(2)+";");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			
		}
		
		if(va.equals("1")){
			String composicion = request.getParameter("composicion");
			String nomMedi = request.getParameter("nomMedi");
			String clasificacion = request.getParameter("clasificacion");
			
			rs = mcm.verificarMedicamento(nomMedi,clasificacion,composicion);
			String codMedic = "";
			try {
				while(rs.next()){
					codMedic = rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(codMedic!=""){
				out.print(codMedic);
			}else{
				mcm.crearMedicamento(nomMedi, clasificacion, composicion);
				rs = mcm.verificarMedicamento(nomMedi,clasificacion,composicion);
				codMedic = "";
				try {
					while(rs.next()){
						codMedic = rs.getString(1);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print(codMedic);
				
			}
			
		}
		
				
		if(va.equals("2")){
			String codMed = request.getParameter("codMed");
			String compuesto = request.getParameter("compuesto");
			String selff = request.getParameter("selff");
			String tipoMed = request.getParameter("tipoMed");
			String concentracion = request.getParameter("concentracion");
			String udconcen = request.getParameter("udconcen");
			String medControl = request.getParameter("medControl");
			String cum = request.getParameter("cum");
			String atc = request.getParameter("atc");
			
			if(atc.equals("default")){
				atc = "";
			}
			
			rs = mcm.verificarFFConcMed(codMed, compuesto, selff, tipoMed, concentracion, udconcen);
			String exFFCon = "";
			try {
				while(rs.next()){
					exFFCon = rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			if(exFFCon==""){
				mcm.crearFFMed(selff, tipoMed, codMed,medControl,cum,atc);
				rs = mcm.obtenerCodFFMed(selff, tipoMed, codMed);
				String codFF = "";
				try {
					while(rs.next()){
						codFF = rs.getString(1);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				mcm.crearConcentracion(compuesto, codFF, concentracion, udconcen);
			}else{
				out.print("existe");
			}
		}
		
		if(va.equals("3")){		
			String codMed = request.getParameter("codMed");
			String codConc = request.getParameter("codConc");
			String tipoPre = request.getParameter("tipoPre");
			String udTipoPres = request.getParameter("udTipoPres");
			String cantPres = request.getParameter("cantPres");
			String codFF = request.getParameter("codFF");
			String esCompuesto = request.getParameter("esCompuesto");
			String usuario = request.getParameter("usuario");
			String cum = request.getParameter("cum");

			mcm.crearPresentacion(codMed, codConc, tipoPre, udTipoPres, cantPres,codFF);
			
			Calendar calendario = Calendar.getInstance();
			int hra, minutos, segundos;
			hra =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hora= hra+":"+minutos+":"+segundos;
			
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
			String fecha=annio+"-"+mes+"-"+dia;
			
			
			
			rs=mcm.obtenerCodPresentacion(codMed, codConc);
			
			String codPreMed = "";
			
			try {
				while(rs.next()){
					codPreMed = rs.getString(1);				
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			
			if(esCompuesto.equals("no")){
				
				rs=mcm.obtenerInfoNuevoMed(codMed,codFF);
				String codMedicamento = "";
				String codMedicamentoAux = "";
				String nombre = "";
				String concentracion = "";
				String descripcion = "";
				String nomgenerico = "";

				String clasificacion = "";
				String tipo = "";
				String codforma_fk = "";
				String control = "";
				
				try {
					while(rs.next()){
						codMedicamento = rs.getString(1);
						nombre = rs.getString(2);
						concentracion = rs.getString(3);
						descripcion = rs.getString(4);
						nomgenerico = rs.getString(5);

						clasificacion = rs.getString(7);
						tipo = rs.getString(8);
						codforma_fk = rs.getString(9);
						control = rs.getString(10);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				if (codMedicamento.equals("")){
					String codNP = "";
					rs = mcm.obtenerConseNP();
					try {
						while(rs.next()){
							codNP = rs.getString(1);
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					if(codNP.equals("")){
						codMedicamento = "NP00001";
					}else{
						codNP = codNP.substring(2,codNP.length());
						int consecutivo = Integer.parseInt(codNP);
						consecutivo++;
						String consec = String.valueOf(consecutivo);
						String Ceros="";
						for (int i=0;i<(codNP.length()-consec.length());i++){
							Ceros += "0";
						}
						codMedicamento = "NP"+Ceros+consec;
					}
				}
				mcm.crearMedicamentoTabMED(codMedicamento, nombre, concentracion, descripcion, "1", nomgenerico, clasificacion, tipo, codforma_fk, control, "1", codMed,codFF,codPreMed);
				
				String descPrograma="";
				rs = mcm.obtenerInfoNuevoMedParaPrograma(codMed,codFF);
				try {
					while(rs.next()){
						codMedicamento = rs.getString(1);
						descPrograma = rs.getString(2);
						codMedicamentoAux=rs.getString(3);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				if(codMedicamento.equals("cpp")||codMedicamento.equals("CPP")){
					codMedicamento=codMedicamentoAux;
				}				
								
				mcm.crearProgramaMedicamento(3,"2",codMedicamento, codMedicamento, descPrograma, 86, 12,4,9,fecha, hora, usuario);
				
			}
			
			if(esCompuesto.equals("si")){
				rs = mcm.obtenerInfoNuevoMedComp(codMed,codFF);
				String codATC = "";
				String nombre = "";
				String concentracion = "";
				String nomgenerico = "";
				
				String clasificacion = "";
				String tipo = "";
				String codforma_fk = "";
				String control = "";
				
				try {
					while(rs.next()){
						codATC = rs.getString(1);
						nombre = rs.getString(2);
						concentracion = rs.getString(3);
						nomgenerico = rs.getString(4);
						
						clasificacion = rs.getString(6);
						tipo = rs.getString(7);
						codforma_fk = rs.getString(8);
						control = rs.getString(9);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				if (codATC.equals("")){
					String codNP = "";
					rs = mcm.obtenerConseNP();
					try {
						while(rs.next()){
							codNP = rs.getString(1);
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					if(codNP.equals("")){
						codATC = "NP00001";
					}else{
						codNP = codNP.substring(2,codNP.length());
						int consecutivo = Integer.parseInt(codNP);
						consecutivo++;
						String consec = String.valueOf(consecutivo);
						String Ceros="";
						for (int i=0;i<(codNP.length()-consec.length());i++){
							Ceros += "0";
						}
						codATC = "NP"+Ceros+consec;
					}
				}
				
				mcm.crearMedicamentoTabMED(codATC, nombre, concentracion, "", "1", nomgenerico, clasificacion, tipo, codforma_fk, control, "1",codMed,codFF,codPreMed);
				String codMedicamento = "";
				String codMedicamentoAux = "";
				String descPrograma="";
				rs = mcm.obtenerInfoNuevoMedCompParaPrograma(codMed, codFF);
				try {
					while(rs.next()){
						codMedicamento = rs.getString(1); 
						descPrograma = rs.getString(2);
						codMedicamentoAux = rs.getString(3);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				if(codMedicamento.equals("cpp")||codMedicamento.equals("CPP")){
					codMedicamento = codMedicamentoAux;
				}				
				mcm.crearProgramaMedicamento(3,"2",codMedicamento, codMedicamento, descPrograma, 86, 12,4,9,fecha, hora, usuario);
			}
			
			String codNuevoMed = "";
			rs = mcm.obtenerNuevoMed();
			try {
				while(rs.next()){
					codNuevoMed = rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			String codNuevoProg = "";
			String nomprog="";
			String codrefprog="";
			rs = mcm.obtenerNuevoProg();
			try {
				while(rs.next()){
					codNuevoProg = rs.getString(1);
					codrefprog=rs.getString(2);
					nomprog=rs.getString(3);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			mcm.crearProgMed(codNuevoProg, codNuevoMed);


			////para producto////
			mcm.CrearProducto(codrefprog,nomprog,"1",usuario,fecha,hora);
			String cproducto="";
			rs=mcm.ObtenerProducto(codrefprog,nomprog,"1",usuario,fecha,hora);
			try {
				if(rs.next()){
					cproducto=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mcm.CrearOrdProgMed(cproducto, codNuevoProg, codNuevoMed);
			mca.CrearAsignacionGrupo(cproducto,"1",usuario,fecha,hora);
			///fin para producto ////

		}
		
		if(va.equals("fin")){		
			
			String codMed = request.getParameter("codMed");
			rs = mcm.verificarPresMedicamentos(codMed);
			String sinPresentacion = "";
			try {
				while(rs.next()){
					sinPresentacion = rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print(sinPresentacion);
		}
		
		if(va.equals("pamc")){		
			
			String codMed = request.getParameter("codMed");
			String opcion = request.getParameter("opcion");
			
			rs = mcm.obtenerPAMedComercial(codMed);
			String pa = "";
			try {
				while(rs.next()){
					if(opcion.equals("1")){
						pa = rs.getString(1)+"|"+rs.getString(2);
					}
					if(opcion.equals("2")){
						pa += rs.getString(1)+"|";
					}
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print(pa);
		}
		
		if(va.equals("4")){
			String codMed = request.getParameter("codMed");
			String compuesto = request.getParameter("compuesto");
			String selff = request.getParameter("selff");
			String tipoMed = request.getParameter("tipoMed");
			String concentracion = request.getParameter("concentracion");
			String udconcen = request.getParameter("udconcen");
			String codFFm = request.getParameter("codFFm");
			String medControl = request.getParameter("medControl");
			String cum = request.getParameter("cum");
			String atc = request.getParameter("atc");
			
			if(atc.equals("default")){
				atc = "";
			}
			
			String nuevaFF = request.getParameter("nuevaFF");
			
			boolean nuevoFF = Boolean.parseBoolean(nuevaFF);
			
			if(nuevoFF){
				mcm.crearFFMed(selff, tipoMed, codMed,medControl,cum,atc);
			}
			
			rs = mcm.obtenerCodFFMed(selff, tipoMed, codMed);
			try {
				while(rs.next()){
					codFFm = rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			mcm.crearConcentracion(compuesto, codFFm, concentracion, udconcen);
			
			
		}
		
		if(va.equals("cum")){		
			
			String cum = request.getParameter("CUM");
			if(!cum.equals("CPP")&&!cum.equals("cpp")){
				rs = mcm.verificarCUM(cum);
				String codCUM = "";
				try {
					while(rs.next()){
						codCUM = rs.getString(1);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print(codCUM);
			}else{
				out.print("");
			}			
		}
		
		if(va.equals("MED")){		
			String nomMedi = request.getParameter("nomMedi");
			String clas = request.getParameter("clas");
			String composicion = request.getParameter("comp");
			
			
			
			rs = mcm.verificarMED(nomMedi);
			
			String tipo = "";
			String comp = "";
			try {
				while(rs.next()){
					tipo = rs.getString(1);
					comp = rs.getString(2);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.print("Error "+e);
				e.printStackTrace();
			}
			
		
			if(tipo.equals("")&&comp.equals("")){
				out.print("nuevo");
			}else{
				if(clas.equals(tipo)&&composicion.equals(comp)){
					out.print("");
					
				}else{
					if(tipo.equals("0")){
						tipo="generico";
					}
					if(tipo.equals("1")){
						tipo="comercial";
					}
					if(comp.equals("0")){
						comp="simple";
					}
					if(comp.equals("1")){
						comp="compuesto";
					}
					out.print(tipo+"|"+comp);
					
				}
			}
			
		}
		
		
		if(va.equals("sugestPA")){
			String texto = request.getParameter("texto");
			try {
				rs =mcm.listarPA(texto);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(1)+"|a|b|c|d|e'";
	            	cadena = cadena +",";	
				}
				cadena = cadena+"]";
				out.write(cadena);
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}		
		
		
		
  }
}


