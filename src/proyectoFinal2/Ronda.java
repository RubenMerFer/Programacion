package proyectoFinal2;

import java.util.Random;

public class Ronda extends Partida {

	//Variables
	private Ingles ingles;
	private Mates mates;
	private Letras letras;
	int tipoPregunta= -1;
	
	//Constructor
	public Ronda(Jugador[] jugadores, int numRondas) {
		super(jugadores, numRondas);
		this.ingles= new Ingles();
		this.mates= new Mates();
		this.letras= new Letras();
	}//Fin constructor

	//Método para generar una pregunta
	public String Pregunta() {
		Random random= new Random();
//		this.tipoPregunta= random.nextInt(3); //Generamos un número aleatorio entre 0 y 2 para definir si la pregunta es de mates, letras o inglés
		/**DEFENSA EJERCICIO 1.- **/
		this.tipoPregunta= random.nextInt(4); //Generamos un número aleatorio entre 0 y 3 para definir el tipo de pregunta AL AÑADIRSE LA NUEVA OPCIÓN "PUNTO-PREGUNTA"
		/**DEFENSA EJERCICIO 1.- **/
		
		String pregunta= "";
		
		if(tipoPregunta == 0) {//Mates
			pregunta= mates.generaPregunta();
			System.out.println("PREGUNTA DE MATES: " + pregunta + " = ¿?");
		}else if(tipoPregunta == 1) {//Letras
			pregunta= letras.generaPregunta();
			System.out.println("PREGUNTA DE LETRAS: " + pregunta);
		}else if(tipoPregunta == 2) {//Inglés
			pregunta= ingles.generaPregunta();
			System.out.println(pregunta);
		}
		/**DEFENSA EJERCICIO 1.- **/
		else {//La nueva opción PUNTO-PREGUNTA
			System.out.println("OPCIÓN 'PUNTO-PREGUNTA': NO HAY NINGUNA PREGUNTA QUE RESPONDER");
		}
		/**DEFENSA EJERCICIO 1.- **/
		return pregunta;
	}//Fin método

	//Método para validar la respuesta
	public boolean validarRespuesta(String respuesta) {
		boolean esCorrecta= false;
		
		if(tipoPregunta == 0) {//Mates
            esCorrecta= mates.validarRespuesta(respuesta);
            if(esCorrecta) {
            	return true;
            }
        }else if(tipoPregunta == 1) {//Letras
        	esCorrecta= letras.validarRespuesta(respuesta);
            if(esCorrecta) {
            	return true;
            }
        }else if(tipoPregunta == 2) {//Inglés
        	esCorrecta= ingles.validarRespuesta(respuesta);
            if(esCorrecta) {
            	return true;
            }
        }
		return esCorrecta;
	}//Fin método
	
	//Método para mostrar la respuesta correcta en caso de fallar el jugador
	public String mostrarRespuestaCorrecta() {
		String respuesta= "";
		
		if(tipoPregunta == 0) {//Mates
			respuesta= String.valueOf(mates.getResultadoCorrecto());
		}else if(tipoPregunta == 1) {//Letras
			respuesta= letras.getRespuestaCorrecta();
		}else if(tipoPregunta == 2) {//Inglés
			respuesta= ingles.getRespuestaCorrecta();
		}
		return respuesta;
	}//Fin método
}