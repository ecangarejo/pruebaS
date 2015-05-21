package seguridad_bean;

public class CrearPermisos {
	
	public CrearPermisos(){}
	
	String NombreMenu="";
	String UrlMenu="";	
	///////////////////////////////
	String NombreOpcionesMenu="";
	String CodigoMenuFk="";	
	///////////////////////////////
	String NombreOpcionDisponible="";
	String UrlOpcionesMenu="";
	String CodigoOpMenuFk="";
	
	
	
	public String getNombreMenu(){return NombreMenu;}	
	public void setNombreMenu(String NombreMenu){this.NombreMenu = NombreMenu;}	
	public String getUrlMenu(){return UrlMenu;}	
	public void setUrlMenu(String UrlMenu){this.UrlMenu = UrlMenu;}
	
	public String getNombreOpcionesMenu(){return NombreOpcionesMenu;}	
	public void setNombreOpcionesMenu(String NombreOpcionesMenu){this.NombreOpcionesMenu = NombreOpcionesMenu;}
	public String getCodigoMenuFk(){return CodigoMenuFk;}	
	public void setCodigoMenuFk(String CodigoMenuFk){this.CodigoMenuFk = CodigoMenuFk;}
	
	public String getNombreOpcionDisponible(){return NombreOpcionDisponible;}	
	public void setNombreOpcionDisponible(String NombreOpcionDisponible){this.NombreOpcionDisponible = NombreOpcionDisponible;}
	public String getUrlOpcionesMenu(){return UrlOpcionesMenu;}	
	public void setUrlOpcionesMenu(String UrlOpcionesMenu){this.UrlOpcionesMenu = UrlOpcionesMenu;}
	public String getCodigoOpMenuFk(){return CodigoOpMenuFk;}	
	public void setCodigoOpMenuFk(String CodigoOpMenuFk){this.CodigoOpMenuFk = CodigoOpMenuFk;}

}
