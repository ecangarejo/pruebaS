package cont_bean;

public class Facturas {
	/*******************************DETALLE FACTURA********************************************/
	String cod_fact_fk="";
	String cantidad="";
	String fecha="";
	String num_soporte="";
	String usuario="";
	
	
	public String getCod_fact_fk() {
		return cod_fact_fk;
	}
	public void setCod_fact_fk(String cod_fact_fk) {
		this.cod_fact_fk = cod_fact_fk;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getNum_soporte() {
		return num_soporte;
	}
	public void setNum_soporte(String num_soporte) {
		this.num_soporte = num_soporte;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/******************************FACTURA*****************************************************/
	String cod_cuenta_fk="";
	String numero_factura="";
	String precio_factura="";
	String fecha_factura="";
	String fecha_insercion="";
	String hora_insercion="";
	String usuario_insercion="";
	String tipo="";
	String estado="";
	String observacion="";
	String iva="";
	String ret_ica="";
	
	public String getCod_cuenta_fk() {
		return cod_cuenta_fk;
	}
	public void setCod_cuenta_fk(String cod_cuenta_fk) {
		this.cod_cuenta_fk = cod_cuenta_fk;
	}
	public String getNumero_factura() {
		return numero_factura;
	}
	public void setNumero_factura(String numero_factura) {
		this.numero_factura = numero_factura;
	}
	public String getPrecio_factura() {
		return precio_factura;
	}
	public void setPrecio_factura(String precio_factura) {
		this.precio_factura = precio_factura;
	}
	public String getFecha_factura() {
		return fecha_factura;
	}
	public void setFecha_factura(String fecha_factura) {
		this.fecha_factura = fecha_factura;
	}
	public String getFecha_insercion() {
		return fecha_insercion;
	}
	public void setFecha_insercion(String fecha_insercion) {
		this.fecha_insercion = fecha_insercion;
	}
	public String getHora_insercion() {
		return hora_insercion;
	}
	public void setHora_insercion(String hora_insercion) {
		this.hora_insercion = hora_insercion;
	}
	public String getUsuario_insercion() {
		return usuario_insercion;
	}
	public void setUsuario_insercion(String usuario_insercion) {
		this.usuario_insercion = usuario_insercion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getIva() {
		return iva;
	}
	public void setIva(String iva) {
		this.iva = iva;
	}
	public String getRet_ica() {
		return ret_ica;
	}
	public void setRet_ica(String ret_ica) {
		this.ret_ica = ret_ica;
	}
}
