package pojo;

public class Empresa {

	private String nif;
	private String nEmpresa;
	private String direccion;
	private int nTelefono;
	private String email;

	public Empresa(String nif, String nEmpresa, String direccion, int nTelefono, String email) {
		this.nif = nif;
		this.nEmpresa = nEmpresa;
		this.direccion = direccion;
		this.nTelefono = nTelefono;
		this.email = email;
	}

	public Empresa() {
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getEmpresa() {
		return nEmpresa;
	}

	public void setEmpresa(String nEmpresa) {
		this.nEmpresa = nEmpresa;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getnTelefono() {
		return nTelefono;
	}

	public void setnTelefono(int nTelefono) {
		this.nTelefono = nTelefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
