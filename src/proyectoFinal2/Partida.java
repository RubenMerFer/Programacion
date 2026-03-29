package proyectoFinal2;

public abstract class Partida {

	//Variables
	private Jugador[] jugadores;
	private int numRondas;
	
	//Constructor
	public Partida(Jugador[] jugadores, int numRondas) {
		this.jugadores = jugadores;
		this.numRondas = numRondas;
	}//Fin constructor
	
	//Getters y Setters
	public Jugador[] getJugadores() {
		return jugadores;
	}

	public void setJugadores(Jugador[] jugadores) {
		this.jugadores = jugadores;
	}

	public int getNumRondas() {
		return numRondas;
	}

	public void setNumRondas(int numRondas) {
		this.numRondas = numRondas;
	}
	
	//Método abstracto
	abstract public String Pregunta();
}