package pat_bean;

public class CrearPaciente {
/**
 * para crear al paciente
 * *****************************************************************************************************
 */
	String TipoDocumento="";
	String Identificacion="";
	String Nombre="";
	String Apellidos="";
	String FechaNacimiento="";
	String Genero="";
	String Eps="";
	String Telefono="";
	String Direccion="";
	String Email="";
	
	public String getTipoDocumento() {return TipoDocumento;}
	public void setTipoDocumento(String TipoDocumento) {this.TipoDocumento = TipoDocumento;	}
	
	public String getIdentificacion() {return Identificacion;}
	public void setIdentificacion(String Identificacion) {this.Identificacion = Identificacion;	}
	
	public String getNombre() {return Nombre;}
	public void setNombre(String Nombre) {this.Nombre = Nombre;	}
	
	public String getApellidos() {return Apellidos;}
	public void setApellidos(String Apellidos) {this.Apellidos = Apellidos;	}
	
	public String getFechaNacimiento() {return FechaNacimiento;}
	public void setFechaNacimiento(String FechaNacimiento) {this.FechaNacimiento = FechaNacimiento;	}
	
	public String getGenero() {return Genero;}
	public void setGenero(String Genero) {this.Genero = Genero;	}
	
	public String getEps() {return Eps;}
	public void setEps(String Eps) {this.Eps = Eps;	}
	
	public String getTelefono() {return Telefono;}
	public void setTelefono(String Telefono) {this.Telefono = Telefono;	}
	
	public String getDireccion() {return Direccion;}
	public void setDireccion(String Direccion) {this.Direccion = Direccion;	}
	
	public String getEmail() {return Email;}
	public void setEmail(String Email) {this.Email = Email;	}	
	/**
	 * **************************************************************************************
	 * para crear el area y el estudio.
	 */
	String NombreArea="";
	String NombreEstudio="";
	String CodArea_fk="";
	
	public String getNombreArea() {return NombreArea;}
	public void setNombreArea(String NombreArea) {this.NombreArea = NombreArea;	}
	
	public String getNombreEstudio() {return NombreEstudio;}
	public void setNombreEstudio(String NombreEstudio) {this.NombreEstudio = NombreEstudio;	}
	
	public String getCodArea_fk() {return CodArea_fk;}
	public void setCodArea_fk(String CodArea_fk) {this.CodArea_fk = CodArea_fk;	}
	
}
