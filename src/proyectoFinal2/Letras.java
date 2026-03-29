package proyectoFinal2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Letras {
	
	//Variables
	private String respuestaCorrecta;
	ArrayList<String> palabras= new ArrayList<>();
	
	//Getter de la respuesta correcta
	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	//Método para obtener las palabras de 'diccionario.txt'
	private ArrayList<String> obternerPalabrasDelDiccionario(){
		ArrayList<String> palabrasDiccionario= new ArrayList<>();

		try {
			File f= new File("src/proyectoFinal2/diccionario.txt");
			Scanner entrada= new Scanner(f);
			
			while(entrada.hasNext()) {
				String linea= entrada.nextLine();
				palabrasDiccionario.add(linea); //Añadimos la palabra a la lista
			}//Fin while
			entrada.close(); //Cerramos fichero
		}catch(FileNotFoundException e) {
			System.err.println("EXCEPCIÓN 'FileNotFoundException'");
			System.out.println("NO SE ENCONTRÓ EL FICHERO 'diccionario.txt'");
		}catch(IOException e) {
			System.err.println("EXCEPCIÓN 'IOException'");
			System.out.println("ERROR AL LEER EL FICHERO");
		}
		return palabrasDiccionario;
	}//Fin método

	//Método para generar la pregunta
	public String generaPregunta() {
		palabras= obternerPalabrasDelDiccionario();
		Random random= new Random();
		int indice= random.nextInt(palabras.size());
		String palabra= palabras.get(indice); //Obtenemos la palabra
		this.respuestaCorrecta= palabra; //Guardamos la palabra
		int longitud= palabra.length();
		int letrasOcultas= longitud / 3;
		
		//Elegimos al azar las letras que se ocultarán
		ArrayList<Integer> numerosAleatorios= new ArrayList<>(); //Para guardar los números al azar y evitar duplicados
		int aleatorio= random.nextInt(longitud);
		numerosAleatorios.add(aleatorio); //Añadimos el número a la lista
		
		for(int i=0; i<longitud - 1; i++) {
			do {
				aleatorio= random.nextInt(longitud);
			}while(numerosAleatorios.contains(aleatorio));
			numerosAleatorios.add(aleatorio); //Se añade a la lista otro número aleatorio que no estuviera previamente
		}//Fin for
		
		StringBuilder pregunta= new StringBuilder();
		for(int i=0; i< longitud; i++) {
			if(numerosAleatorios.get(i) % 3 == 0 && letrasOcultas > 0) {
				pregunta.append("*");
				letrasOcultas--;
			}else {
				pregunta.append(palabra.charAt(i));
			}
		}//Fin for
		return pregunta.toString();
	}//Fin método
	
	//Método para validar la respuesta
	public boolean validarRespuesta(String respuesta) {
		int longitud= respuestaCorrecta.length();
		
		//Comparamos longitudes
		if(respuesta.length() != longitud) {
			return false;
		}
		
		//Comparamos respuestas
		if(respuesta.equalsIgnoreCase(respuestaCorrecta)) {
			return true; //Respuesta correcta
		}else {
			//Respuesta incorrecta, o no si el jugador acertó pero no puso la tilde
			String respuestaConTilde= buscarPalabraConTilde(respuesta.toLowerCase(), palabras);
			
			if(respuestaConTilde != null) {
				//Si encontramos una palabra similar con tilde, la comparamos con la respuesta correcta
				if(respuestaConTilde.equalsIgnoreCase(respuestaCorrecta)) {
					return true; //Al final respuesta correcta
				}
			}
		}
		return false;
	}//Fin método
	
	//Método para buscar una palabra similiar a la respuesta del jugador con tilde
	private String buscarPalabraConTilde(String respuestaJugador, ArrayList<String> listaPalabras) {
		for(String palabra: listaPalabras) {
			//Verificamos si tienen la misma longitud
			if(respuestaJugador.length() == palabra.length()) {
				String palabraConTilde= reemplazarVocalConTilde(respuestaJugador, palabra);
				if(listaPalabras.contains(palabraConTilde)) {
					return palabraConTilde;
				}
			}
		}//Fin for
		return null;
	}//Fin método
	
	//Método para reemplazar la vocal sin tilde por la correspondiente tilde en una palabra
	private String reemplazarVocalConTilde(String respuestaJugador, String palabra) {
		for(int i=0; i<respuestaJugador.length(); i++) {
			char caracterPalabraJugador= respuestaJugador.charAt(i);
			char caracterPalabraCorrecta= palabra.charAt(i);
			
			if(caracterPalabraJugador != caracterPalabraCorrecta) {
				switch(caracterPalabraJugador) {
				case 'a':
					return respuestaJugador.substring(0, i) + "á" + respuestaJugador.substring(i+1);
				case 'e':
					return respuestaJugador.substring(0, i) + "é" + respuestaJugador.substring(i+1);
				case 'i':
					return respuestaJugador.substring(0, i) + "í" + respuestaJugador.substring(i+1);
				case 'o':
					return respuestaJugador.substring(0, i) + "ó" + respuestaJugador.substring(i+1);
				case 'u':
					return respuestaJugador.substring(0, i) + "ú" + respuestaJugador.substring(i+1);
				}//Fin switch
			}
		}//Fin for
		return null;
	}//Fin método
}