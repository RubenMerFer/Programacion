package proyectoFinal2;

/**
 * El nombre de la interfaz
 * @author Black24Mamba*/
public interface JuegoInterfaz {

	/**
	 * Para iniciar el juego*/
	public void iniciar();
	
	/**
	 * Para jugar*/
	public void jugar();
	
	/**
	 * Aquí se almacenará una lista de todos los jugadores humanos y el Nº total de preguntas correctas*/
	public void ranking();
	
	/**
	 * Aquí se almacenará el historial de partidas jugadas*/
	public void historico();
	
	/**
	 * Para gestionar a los jugadores*/
	public void gestionJugadores();
	
	/**
	 * Para finalizar el juego*/
	public void finalizar();
}