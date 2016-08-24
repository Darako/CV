package pojo;

public class Camion extends Vehiculo {

	private double longCaja;
	private double alturaCaja;
	private double anchoCaja;
	private double pesoMaxCaja;
	private double volumenCaja;
	private boolean trampilla;

	public Camion(String nBastidor, String matricula, String combustible, int nPasajeros, int potenciaCV,
			int potenciaKWh, int kmTotales, int peso, double largo, double ancho, double longCaja, double anchoCaja,
			double pesoMaxCaja, double volumenCaja, boolean trampilla, String descripcion, double alturaCaja,
			double galibo) {
		super(nBastidor, matricula, combustible, nPasajeros, potenciaCV, potenciaKWh, kmTotales, peso, largo, ancho,
				galibo, descripcion);
		this.longCaja = longCaja;
		this.alturaCaja = alturaCaja;
		this.anchoCaja = anchoCaja;
		this.pesoMaxCaja = pesoMaxCaja;
		this.volumenCaja = volumenCaja;
		this.trampilla = trampilla;
	}

	public Camion() {
	}

	public double getLongCaja() {
		return longCaja;
	}

	public void setLongCaja(double longCaja) {
		this.longCaja = longCaja;
	}

	public double getAlturaCaja() {
		return alturaCaja;
	}

	public void setAlturaCaja(double alturaCaja) {
		this.alturaCaja = alturaCaja;
	}

	public double getAnchoCaja() {
		return anchoCaja;
	}

	public void setAnchoCaja(double anchoCaja) {
		this.anchoCaja = anchoCaja;
	}

	public double getPesoMaxCaja() {
		return pesoMaxCaja;
	}

	public void setPesoMaxCaja(double pesoMaxCaja) {
		this.pesoMaxCaja = pesoMaxCaja;
	}

	public double getVolumenCaja() {
		return volumenCaja;
	}

	public void setVolumenCaja(double volumenCaja) {
		this.volumenCaja = volumenCaja;
	}

	public boolean isTrampilla() {
		return trampilla;
	}

	public void setTrampilla(boolean trampilla) {
		this.trampilla = trampilla;
	}

}
