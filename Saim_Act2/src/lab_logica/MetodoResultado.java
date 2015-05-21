/**
 * logica: MetodoResultado se encuentran las consultas,inserciones y actualizaciones para  
 * para el ingreso y consulta de resultados.
*/
package lab_logica;

import hic_bean.CrearPacLab;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;

import lab_bean.Rango;
import lab_bean.Resultado;
import lab_bean.Texto;

public class MetodoResultado {
	
	public void EliminarIgual(String validar){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from ord_pac_lab where codigo='"+validar+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoResultado>>EliminarIgual:_ "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void RelacionPacienteLaboratorios(String CodEstudio,String TipoEstudio,String CodPaciente,String hora,String fecha,String estado,String usuario,String TipoPyP){
		/**
		 * creamos la relacion de los formatos con la admision y el paciente 
		 * lleva como parametro el codigo del formato,codigo de la admision,codigo del paciente
		 */
		CrearPacLab cpl = new CrearPacLab();
		cpl.setCodigoEstudio(CodEstudio);
		cpl.setCodigoPac(CodPaciente);
		cpl.setTipoEstudio(TipoEstudio);
		cpl.setHora(hora);
		cpl.setFecha(fecha);
		cpl.setEstado(estado);
		cpl.setusuario(usuario);
		cpl.setTipoPyP(TipoPyP);
	
	 		
	   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into ord_pac_lab(codigo_pac_fk,codigo_estudio,tipo_estudio,hora,fecha,estado,usuario,tipopyp)values(?,?,?,?,?,?,?,?)");
			    ps.setString(1,cpl.getCodigoPac());
			    ps.setString(2,cpl.getCodigoEstudio());
			    ps.setString(3,cpl.getTipoEstudio());
			    ps.setString(4,cpl.getHora());
			    ps.setString(5,cpl.getFecha());
			    ps.setString(6,cpl.getEstado());
			    ps.setString(7,cpl.getusuario());
			    ps.setString(8,cpl.getTipoPyP());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN METODO:_ RelacionPacienteLaboratorios "+ex);
			}

		}
	
	
	
	
	 public void insertar(String codpa, String codexa,String fecha,String hora,String usu,String des,String ced,String codord_fk){
			try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();			    
			    Resultado re = new Resultado();				 
			    re.setPaciente(codpa);				 
			    re.setExamen(codexa);				 
			    re.setFecha(fecha);				 
			    re.setHora(hora);				 
			    re.setIngreso(usu);
			    re.setResultado(des);	
			    re.setcedula(ced);
			    re.setcodord_fk(codord_fk);
			    ps=con.conn.prepareStatement("insert into lab_resultado(codpac_fk,codexamen_fk,fecha,hora,inserccion,resultado,cedpac_fk,codord_fk) values(?,?,?,?,?,?,?,?)");				
				ps.setString(1,re.getPaciente());
				ps.setString(2,re.getExamen());
				ps.setString(3,re.getFecha());
				ps.setString(4,re.getHora());
				ps.setString(5,re.getIngreso());
				ps.setString(6,re.getResultado());
				ps.setString(7, re.getcedula());
				ps.setString(8, re.getcodord_fk());
				ps.executeUpdate();
				ps.close();
				con.cerrar();				
				 
			}catch(Exception ex){
				//ex.notify();
				System.out.print("ERROR En MetodoResultado>>insertar "+ex);
			}	
		}
	 
	 public java.sql.ResultSet ResulGrupo(String tipo,String ced,String subarea){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	     
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lres.fecha,lres.hora,lsub.nombre,lex.nombre,lres.resultado,lpac.pac_codigo_paciente,lsub.codigo from adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_subarea lsub where lsub.nombre='"+subarea+"' and lex.codigosubarea_fk=lsub.codigo and lres.codexamen_fk=lex.codigo and lpac.tipo_documento='"+tipo+"' and lpac.numero_documento="+ced+" and lpac.pac_codigo_paciente=lres.codpac_fk group by lres.hora,lres.fecha order by lres.fecha asc ");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoResultado>>ResulGrupo "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet BuscarIgual(String hora,String fecha){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	     
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select codigo,tipo_estudio from ord_pac_lab where fecha='"+fecha+"' and hora='"+hora+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoResultado>>BuscarIgual "+ex);
	        }
	      
	        return rs;
	        
	    }

	 
	 public java.sql.ResultSet Buscaedad(String tipo,String ced){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	     
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select (year(curdate())-year(fecha_nacimiento))-(right(curdate(),5) < right(fecha_nacimiento, 5)) as edad,genero from adm_paciente where numero_documento='"+ced+"' and tipo_documento='"+tipo+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoResultado>>Buscaedad "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet BuscEdad(String exa){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	     
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_rango.edadInicial,lab_rango.edadFinal from lab_rango,lab_examen,lab_tipo_rango where lab_examen.nombre='"+exa+"' and lab_examen.codigo=lab_tipo_rango.codexamen_fk and lab_tipo_rango.codigo=lab_rango.codtiporango_fk");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoResultado>>BuscEdad "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet BuscarTipo(String exa,String area){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	     
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_tipo_rango.tiporango,lab_tipo_rango.codigo from lab_tipo_rango, lab_examen where lab_examen.codigo=lab_tipo_rango.codexamen_fk and lab_examen.nombre='"+exa+"' and lab_examen.codigoarea_fk="+area+" ");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error en MetodoResultado>>BuscarTipo "+ex);
	        }
	      
	        return rs;
	        
	    }
	 public java.sql.ResultSet Resulatdo3(String codtipo,String nom,String area, String edadini,String  edadfi){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	     
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_rango.valorinicial,lab_rango.valorfinal,lab_unidad.nombre,lab_comentario.observacion,lab_genero.especificacion from lab_unidad,lab_rango,lab_comentario,lab_genero,lab_examen,lab_area,lab_tipo_rango WHERE lab_examen.nombre='"+nom+"' and  lab_tipo_rango.codexamen_fk=lab_examen.codigo and lab_tipo_rango.codigo=lab_rango.codtiporango_fk and lab_rango.codcomentario_fk=lab_comentario.codigo and lab_rango.codgenero_fk=lab_genero.codigo and lab_rango.codunidad_fk=lab_unidad.codigo and lab_examen.codigosubarea_fk=0 and lab_area.codigo="+area+" and lab_area.codigo=lab_examen.codigoarea_fk and lab_tipo_rango.codigo="+codtipo+" and lab_rango.edadInicial="+edadini+" and lab_rango.edadfinal="+edadfi+"");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error en MetodoResultado>>Resulatdo3 "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	
	 
	 public java.sql.ResultSet Resultados(String codtipo,String nom,String  area,String  gene){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	     
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_rango.valorinicial,lab_rango.valorfinal,lab_unidad.nombre,lab_comentario.observacion,lab_genero.especificacion from lab_unidad,lab_rango,lab_comentario,lab_genero,lab_examen,lab_area,lab_tipo_rango WHERE lab_examen.nombre='"+nom+"' and  lab_tipo_rango.codexamen_fk=lab_examen.codigo and lab_tipo_rango.codigo=lab_rango.codtiporango_fk and lab_rango.codcomentario_fk=lab_comentario.codigo and lab_rango.codgenero_fk=lab_genero.codigo and lab_rango.codunidad_fk=lab_unidad.codigo and lab_examen.codigosubarea_fk=0 and lab_area.codigo="+area+" and lab_area.codigo=lab_examen.codigoarea_fk and lab_genero.especificacion='"+gene+"' and lab_tipo_rango.codigo="+codtipo+" and lab_examen.codigosubarea_fk=0 and  lab_examen.codigoarea_fk>0");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error en MetodoResultado>>Resultados "+ex);
	        }
	        return rs;
	        
	    }
	 
	 
	 public java.sql.ResultSet Resultados1(String codtipo,String nom,String area){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_rango.valorinicial,lab_rango.valorfinal,lab_unidad.nombre,lab_comentario.observacion,lab_genero.especificacion from lab_unidad,lab_rango,lab_comentario,lab_genero,lab_examen,lab_area,lab_tipo_rango WHERE lab_examen.nombre='"+nom+"' and  lab_tipo_rango.codexamen_fk=lab_examen.codigo and lab_tipo_rango.codigo=lab_rango.codtiporango_fk and lab_rango.codcomentario_fk=lab_comentario.codigo and lab_rango.codgenero_fk=lab_genero.codigo and lab_rango.codunidad_fk=lab_unidad.codigo and lab_examen.codigosubarea_fk=0 and lab_area.codigo="+area+" and lab_area.codigo=lab_examen.codigoarea_fk and lab_tipo_rango.codigo="+codtipo+" ");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error en MetodoResultado>>Resultados1 "+ex);
	        }
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet Resultados2(String codtipo,String nom,String area,String edad){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_rango.valorinicial,lab_rango.valorfinal,lab_unidad.nombre,lab_comentario.observacion,lab_genero.especificacion from lab_unidad,lab_rango,lab_comentario,lab_genero,lab_examen,lab_area,lab_tipo_rango WHERE lab_examen.nombre='"+nom+"' and  lab_tipo_rango.codexamen_fk=lab_examen.codigo and lab_tipo_rango.codigo=lab_rango.codtiporango_fk and lab_rango.codcomentario_fk=lab_comentario.codigo and lab_rango.codgenero_fk=lab_genero.codigo and lab_rango.codunidad_fk=lab_unidad.codigo and lab_examen.codigosubarea_fk=0 and lab_area.codigo="+area+" and lab_area.codigo=lab_examen.codigoarea_fk and lab_tipo_rango.codigo="+codtipo+" ");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error en MetodoResultado>>Resultados2 "+ex);
	        }
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet ResultadosGrupo(String nom){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_rango.valorinicial,lab_rango.valorfinal,lab_unidad.nombre,lab_comentario.observacion,lab_genero.especificacion from lab_unidad,lab_rango,lab_comentario,lab_genero,lab_examen WHERE lab_examen.nombre='"+nom+"' and lab_examen.codigo=lab_rango.codexamen_fk and lab_rango.codcomentario_fk=lab_comentario.codigo and lab_rango.codgenero_fk=lab_genero.codigo and lab_rango.codunidad_fk=lab_unidad.codigo and lab_examen.codigoarea_fk=0");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error en MetodoResultado>>ResultadosGrupo "+ex);
	        }
	        return rs;
	    }
	 
	 public java.sql.ResultSet CodArea(String nom){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_area.codigo from lab_area where lab_area.nombre='"+nom+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error en MetodoResultado>>CodArea "+ex);
	        }
	        return rs;
	    }
	 
	 public java.sql.ResultSet CodResult(String codexa,String hora,String fecha,String codpac){
	        java.sql.ResultSet rs=null;
	        Statement st = null;	     
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_resultado.codigo from lab_resultado where lab_resultado.codexamen_fk="+codexa+" and lab_resultado.hora='"+hora+"' and lab_resultado.fecha='"+fecha+"' and lab_resultado.codpac_fk="+codpac+"");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error en MetodoResultado>>CodResult "+ex);
	        }
	        return rs;
	    }
}