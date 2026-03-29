package proyectoFinal2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Ingles {
	
	//Variables
	private int indice;
	private ArrayList<String> opcionesDesordenadas;
	private String respuestaCorrecta;
	
	//Getter de la respuesta correcta
	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	//Método para obtener TODAS las preguntas del fichero 'ingles.txt'
	private ArrayList<String[]> obtenerPreguntasEnIngles() {
		ArrayList<String[]> preguntas= new ArrayList<>();
		
		try {
			File f= new File("src/proyectoFinal2/ingles.txt");
			Scanner entrada= new Scanner(f);
			
			while(entrada.hasNext()) {
				String linea= entrada.nextLine();
				String[] opciones= new String[5];
				opciones[0]= linea; //La opción 0 es la pregunta
				
				//Almacenamos las posibles respuestas
				for(int i=1; i<5; i++) {
					opciones[i]= entrada.nextLine();
				}//Fin for
				preguntas.add(opciones);
			}//Fin while
			entrada.close(); //Cerramos fichero
		}catch(FileNotFoundException e) {
			System.err.println("EXCEPCIÓN 'FileNotFoundException'");
			System.out.println("NO SE ENCONTRÓ EL FICHERO 'ingles.txt'");
		}catch(IOException e) {
			System.err.println("EXCEPCIÓN 'IOException'");
			System.out.println("ERROR AL LEER EL FICHERO");
		}
		return preguntas;
	}//Fin método
	
	//Método para generar la pregunta aleatoria
	public String generaPregunta() {
		ArrayList<String[]> preguntas= obtenerPreguntasEnIngles();
		Random random= new Random();
		this.indice= random.nextInt(preguntas.size());
		String preguntaActual[]= preguntas.get(this.indice); //Obtenemos la pregunta
		this.respuestaCorrecta= preguntaActual[1]; //Guardamos la respuesta correcta
		
		StringBuilder pregunta= new StringBuilder();
		pregunta.append("PREGUNTA DE INGLÉS: ").append(preguntaActual[0]).append("\n");
		opcionesDesordenadas= new ArrayList<>(Arrays.asList(preguntaActual).subList(1, 5)); //Guardamos las opciones en la lista
		Collections.shuffle(opcionesDesordenadas); //Mezclamos aleatoriamente las opciones de respuesta
        
        char letra='A';
        for (int i=0; i < opcionesDesordenadas.size(); i++) {
        	String opcion= opcionesDesordenadas.get(i);
            pregunta.append((char) (letra + i)).append(") ").append(opcion).append("\n");
        }//Fin for
        return pregunta.toString();
	}//Fin método
	
	//Método para validar la respuesta
	public boolean validarRespuesta(String respuesta) {
		//Convertir la respuesta proporcionada a letra
		char respuestaLetra= respuesta.toUpperCase().charAt(0);
		
		//Verificamos que la respuesta esté dentro de las opciones válidas (A, B, C Ó D)
		if(respuestaLetra < 'A' || respuestaLetra > 'D') {
			return false;
		}
		
		//Obtenemos el índice seleccionado (0= A, 1= B, 2= C, 3= D)
		int seleccion= respuestaLetra - 'A';
		
		//Comparamos el contenido de la respuesta seleccionada con la correcta
		return opcionesDesordenadas.get(seleccion).equalsIgnoreCase(respuestaCorrecta);
	}//Fin método
}