package pojo;

public class Subordinado {

	private String dniJefe;
	private String dniSubordinado;

	public Subordinado(String dniSubordinado, String dniJefe) {
		this.dniJefe = dniJefe;
		this.dniSubordinado = dniSubordinado;
	}

	public Subordinado() {
	}

	public String getDniJefe() {
		return dniJefe;
	}

	public void setDniJefe(String dniJefe) {
		this.dniJefe = dniJefe;
	}

	public String getDniSubordinado() {
		return dniSubordinado;
	}

	public void setDniSubordinado(String dniSubordinado) {
		this.dniSubordinado = dniSubordinado;
	}

}
