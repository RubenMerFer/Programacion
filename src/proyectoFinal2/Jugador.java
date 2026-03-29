package proyectoFinal2;

public class Jugador {

	//Variables
	private String nombre;
	private int puntuacion;
	private boolean cpu;
	
	//Constructor
	public Jugador(String nombre, int puntuacion, boolean cpu) {
		this.nombre = nombre;
		this.puntuacion = puntuacion;
		this.cpu = cpu;
	}//Fin constructor
	
	//Constructor con el nombre y la puntuación como parámetros para el ranking
	public Jugador(String nombre, int puntuacion) {
		this.nombre = nombre;
		this.puntuacion = puntuacion;
	}//Fin constructor

	//Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public boolean getEsCpu() {
		return cpu;
	}

	public void setCpu(boolean cpu) {
		this.cpu = cpu;
	}
}