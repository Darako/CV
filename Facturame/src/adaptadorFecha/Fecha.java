package adaptadorFecha;

/*
 * Interface para manejar fechas en formato de España (ES): DD/MM/AAAA. 
 */
/**
 * 
 * @author Jorge Gonz�lez Rodr�guez y Javier Ruiz Rodr�guez
 *
 */
public interface Fecha {

    /**
     * Devuelve una cadena con la fecha en formato de España (DD/MM/AAAA).
     * @return Fecha en formato de España (DD/MM/AAAA)
     */
    @Override
    public String toString();

    /**
     * Devuelve el año.
     * @return Entero que representa el año.
     */
    public int getAnio();

    /**
     * Estable el anio�o.
     * @param anio�o Entero que representa el año.
     */
    public void setAnio(int anio);

    /**
     * Devuelve el dia.
     * @return Entero que representa el dia.
     */
    public int getDia();

    /**
     * Establece el día.
     * @param dia Entero que representa el día.
     */
    public void setDia(int dia);

    /**
     * Devuelve el mes.
     * @return Entero que representa el mes.
     */
    public int getMes();

    /**
     * Establece el mes.
     * @param mes Entero que representa el mes.
     */
    public void setMes(int mes);
}
