package proyectoFinal2;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

public class JuegoDePreguntas implements JuegoInterfaz{
	
	//Variables
	private Scanner entrada;
	private int opcion;
	boolean opcionAceptada= false;
	boolean salir= false;
	private int numJugadores= 0;
	private int numRondas= 0;
	private boolean totalJugAceptados= false;
	private boolean totalRondAceptadas= false;
	private String cpu1= "CPU1";
	private String cpu2= "CPU2";
	private String cpu3= "CPU3";
	private String cpu4= "CPU4";
	boolean esCorrecta= false;
	private ArrayList<String> listaJugadoresHumanosDelSistema= new ArrayList<>(); //Lista de jugadores humanos del sistema
	Jugador[] jugadores= null; //Jugadores (entre 1 y 4) que jugarán una partida
	
	//Constructor
	public JuegoDePreguntas() {
		entrada= new Scanner(System.in);
	}//Fin constructor
	
	//Implementamos de los métodos de la interfaz
	public void iniciar(){
		do{
			do{
				try {
					System.out.println("\n¿ERES MÁS LISTO QUE UNA COMPUTADORA?");
					System.out.println("MENÚ PRINCIPAL");
					System.out.println("1. JUGAR PARTIDA");
					System.out.println("2. RANKING");
					System.out.println("3. HISTÓRICO");
					System.out.println("4. JUGADORES");
					System.out.println("5. SALIR");
					System.out.print("INGRESE UNA OPCIÓN: ");
					opcion=entrada.nextInt();
					entrada.nextLine(); //Limpiamos buffer
					
					if(opcion >= 1 && opcion <= 5) {
						opcionAceptada= true;
					}else {
						System.out.println("OPCIÓN NO VÁLIDA, VUELVE A INTENTARLO.");
						opcionAceptada= false;
					}
				}catch(InputMismatchException e) {
					System.err.println("EXCEPCIÓN 'InputMismatchException'");
					System.out.println("VALOR PARA ELEGIR OPCIÓN INCORRECTO");
					entrada.nextLine(); //Limpiamos buffer
				}
			}while(!opcionAceptada);
			
			switch (opcion) {
			case 1:
				jugar();
				break;
			case 2:
				ranking();
				break;
			case 3:
				historico();
				break;
			case 4:
				gestionJugadores();
				break;
			case 5:
				finalizar();
				salir= true;
				break;
			}//Fin switch
		}while(!salir);
	}//Fin método
	
	//OPCIÓN 1
	public void jugar() {
		do {
			try {
				//Información para iniciar la partida
//				System.out.print("\nINGRESE EL NÚMERO DE JUGADORES (1-4): ");
				/**DEFENSA EJERCICIO 3.- **/
				System.out.print("\nINGRESE EL NÚMERO DE JUGADORES (2-6): ");
				/**DEFENSA EJERCICIO 3.- **/
				numJugadores= entrada.nextInt();
				System.out.print("INGRESE EL NÚMERO DE RONDAS (3, 5, 10 Ó 20): ");
				numRondas= entrada.nextInt();
				entrada.nextLine(); //Limpiamos buffer
				
//				if((numJugadores >= 1 && numJugadores <= 4) && (numRondas == 3 || numRondas == 5 || numRondas == 10 || numRondas == 20)) {
					/**DEFENSA EJERCICIO 3.- **/
				if((numJugadores >= 2 && numJugadores <= 6) && (numRondas == 3 || numRondas == 5 || numRondas == 10 || numRondas == 20)) {
					/**DEFENSA EJERCICIO 3.- **/
					totalJugAceptados= true;
					totalRondAceptadas= true;
					jugadores= new Jugador[numJugadores];
				}else {
					System.out.println("ERROR, PUSISTE MAL EL Nº DE JUGADORES Y/O EL Nº DE RONDAS");
					totalJugAceptados= false;
					totalRondAceptadas= false;
				}
			}catch(InputMismatchException e) {
				System.err.println("EXCEPCIÓN 'InputMismatchException'");
				System.out.println("VALOR/ES PARA ELEGIR EL Nº DE JUGADORES Y/O EL Nº DE RONDAS INCORRECTO/S");
				entrada.nextLine(); //Limpiamos buffer
			}
		}while(!totalJugAceptados && !totalRondAceptadas);
		
		//Crear jugadores y asignarles un nombre
		boolean jugadorHumanoEnSistema= false; //Variable para verificar que cada jugador esté en el sistema
		ArrayList<String> nombresAsignados= new ArrayList<>(); //Lista para guardar los nombres que ya fueron seleccionados
		
		for(int i=0; i<numJugadores;i++) {
			do {
				mostrarJugadores(); //Mostramos los jugadores registrados en el sistema
				System.out.print("\nESCRIBE EL NOMBRE DEL JUGADOR " + (i+1) + ": ");
				String nombre= entrada.next();
				entrada.nextLine(); //Limpiar buffer para que no recoga el espacio en blanco al elegir una opción de respuesta
				
				if(nombre.equalsIgnoreCase(cpu1)|| nombre.equalsIgnoreCase(cpu2) || nombre.equalsIgnoreCase(cpu3) || nombre.equalsIgnoreCase(cpu4)) {
					if(nombresAsignados.contains(nombre)) {//Verificamos que el nombre está en la lista
						System.out.println("ESTA CPU YA FUE ELEGIDA. ELIGE OTRA");
						jugadorHumanoEnSistema= false;
					}else {
						jugadores[i]= new Jugador(nombre, 0, true);
						System.out.println("ESTE JUGADOR ES UNA CPU Y JUGARÁ AUTOMÁTICAMENTE");
						nombresAsignados.add(nombre);
						jugadorHumanoEnSistema= true; //Aunque sea una cpu debe ser true para salir del do-while
					}			
				}else {
					if(listaJugadoresHumanosDelSistema.contains(nombre)) {//Verificamos que el nombre está en la lista
						if(nombresAsignados.contains(nombre)) {//Verificamos si el nombre ya fue elegido o no por otro jugador
							System.out.println("ESTE NOMBRE YA FUE ELEGIDO. ELIGE OTRO");
							jugadorHumanoEnSistema= false; //Aunque si lo esté pero forzamos al jugador a que ponga otro nombre
						}else {
							jugadores[i]= new Jugador(nombre, 0, false);
							nombresAsignados.add(nombre);
							jugadorHumanoEnSistema= true;
						}
					}else {
						jugadorHumanoEnSistema= false;
						System.out.println("EL JUGADOR NO ESTÁ EN EL SISTEMA. AÑADELO O ELIGE OTRO");
						System.out.println("¿QUIERES AÑADIRLO? PON 'SI' O 'NO'");
						String respuesta= entrada.nextLine();
						
						if(respuesta.equalsIgnoreCase("SI")) {
							añadirJugador();
						}else if(respuesta.equalsIgnoreCase("NO")) {
							System.out.println("OK, ELIGE OTRO JUGADOR");
						}else {
							System.out.println("ERA DECIR 'SI' O 'NO', IMAGINO QUE PONDRÁS OTRO JUGADOR ;)");
						}
					}
				}
			}while(!jugadorHumanoEnSistema);
		}//Fin for
		
		//Comenzamos la partida
		//Hay que ordenar los jugadores aleatoriamente
		List<Jugador> listaJugadores= Arrays.asList(jugadores); //Lista temporal con los elementos del array
		Collections.shuffle(listaJugadores); //Mezclamos a los jugadores
		jugadores= listaJugadores.toArray(jugadores); //Actualizamos el array con los elementos de la lista temporal
		
		Ronda ronda= new Ronda(jugadores, numRondas);
		String respuestaJugador= "";
		String respuestaCorrecta= "";
		
		for(int i= 0; i < numRondas; i++) {
			System.out.println("\nRonda " + (i + 1));
			System.out.println("--------");
			
			for(Jugador jugador: jugadores) {
				System.out.println("\n***Turno de " + jugador.getNombre() + "***");
			    ronda.Pregunta(); //Mostramos la pregunta y obtenemos la respuesta de los jugadores
			    
			    //JUGADORES CPU
			    if(jugador.getNombre().equalsIgnoreCase(cpu1) || jugador.getNombre().equalsIgnoreCase(cpu2) ||
			    		jugador.getNombre().equalsIgnoreCase(cpu3) || jugador.getNombre().equalsIgnoreCase(cpu4)) {
			    	if(ronda.tipoPregunta == 0) {//Mates
			    		respuestaCorrecta= ronda.mostrarRespuestaCorrecta(); //Mostramos la respuesta correcta
			    		System.out.println("MI RESPUESTA ES " + respuestaCorrecta);
			        	System.out.println("SOY BUENÍSIMO EN MATEMÁTICAS. SIEMPRE ACIERTO ;)");
			        }else if(ronda.tipoPregunta == 1) {//Letras
			        	respuestaCorrecta= ronda.mostrarRespuestaCorrecta(); //Mostramos la respuesta correcta
			        	System.out.println("LA RESPUESTA CORRECTA ERA " + respuestaCorrecta);
			        	System.out.println("SOY MUY MALO EN LETRAS. SIEMPRE FALLO :(");
			        }else if(ronda.tipoPregunta == 2) {//Ingles
			        	System.out.println("NO SOY BUENO EN INGLÉS, PERO CONFÍO EN MI SUERTE ^_^");
			        	Random random= new Random();
			        	int opcionAlAzar= random.nextInt(4);
			        	respuestaJugador= "";
			        	
			        	//Verificamos a qué letra le corresponde el número
			        	if(opcionAlAzar == 0) {
			        		respuestaJugador= "A";
			        	}else if(opcionAlAzar == 1) {
			        		respuestaJugador= "B";
			        	}else if(opcionAlAzar == 2) {
			        		respuestaJugador= "C";
			        	}else if(opcionAlAzar == 3) {
			        		respuestaJugador= "D";
			        	}
			        	//Validamos la respuesta
			        	System.out.println("VOY A ELEGIR LA OPCIÓN " + respuestaJugador + ")");
			        	esCorrecta= ronda.validarRespuesta(respuestaJugador);
			        }
			        /**DEFENSA EJERCICIO 1.- **/
			        else if(ronda.tipoPregunta == 3) {//La nueva opción PUNTO-PREGUNTA
			        	System.out.println("TOMA YA, TENGO UN PUNTO EXTRA :-}");
			        }
			        /**DEFENSA EJERCICIO 1.- **/
			    }
			    //JUGADORES HUMANOS
			    else {
			    	//Dependiendo del tipo de pregunta se muestra un determinado mensaje
			    	if(ronda.tipoPregunta == 0 || ronda.tipoPregunta == 1) {
			    		System.out.print("PON TU RESPUESTA: ");
			    		respuestaJugador= entrada.nextLine();
					    
					    //Validar y procesar la respuesta
					    esCorrecta= ronda.validarRespuesta(respuestaJugador);
			    	}else if(ronda.tipoPregunta == 2) {
			    		System.out.print("ELIGE UNA OPCIÓN COMO RESPUESTA (A, B, C Ó D): ");
			    		respuestaJugador= entrada.nextLine();
					    
					    //Validar y procesar la respuesta
					    esCorrecta= ronda.validarRespuesta(respuestaJugador);
			    	}
			    	/**DEFENSA EJERCICIO 1.- **/
			    	else if(ronda.tipoPregunta == 3) {
			    		esCorrecta= true;
			    	}
			    	/**DEFENSA EJERCICIO 1.- **/
				    				   
			        if(esCorrecta) {
			        	if(ronda.tipoPregunta != 3) {
			        		System.out.println("RESPUESTA CORRECTA");	
			        	}else {
			        		System.out.println("TIENES UN PUNTO DIRECTAMENTE"); /**DEFENSA EJERCICIO 1.- **/
			        	}
				    }else if (!esCorrecta){
				    	respuestaCorrecta= ronda.mostrarRespuestaCorrecta(); //Mostramos la respuesta correcta
				        System.out.println("RESPUESTA INCORRECTA. LA CORRECTA ERA " + respuestaCorrecta);
				    }
			    }
			    
			    //Actualizamos puntuaciones
				//JUGADORES CPU
		        if(jugador.getNombre().equalsIgnoreCase(cpu1) || jugador.getNombre().equalsIgnoreCase(cpu2)
		        		||jugador.getNombre().equalsIgnoreCase(cpu3) ||jugador.getNombre().equalsIgnoreCase(cpu4)) {
		            if(ronda.tipoPregunta == 0) {
			            jugador.setPuntuacion(jugador.getPuntuacion() + 1);
		            }else if(ronda.tipoPregunta == 1) {
		            	jugador.setPuntuacion(jugador.getPuntuacion());
		            }else if(ronda.tipoPregunta == 2) {
		            	if(esCorrecta) {
		            		System.out.println("ACERTÉ :}");
		            		jugador.setPuntuacion(jugador.getPuntuacion() + 1);
		            	}else {
		            		respuestaCorrecta= ronda.mostrarRespuestaCorrecta(); //Mostramos la respuesta correcta
		            		System.out.println("LA RESPUESTA CORRECTA ERA " + respuestaCorrecta + ". FALLÉ :[");
		            		jugador.setPuntuacion(jugador.getPuntuacion());
		            	}
		            }
		            /**DEFENSA EJERCICIO 1.- **/
		            else if(ronda.tipoPregunta == 3) {//La nueva opción PUNTO-PREGUNTA
		            	jugador.setPuntuacion(jugador.getPuntuacion() + 1);
		            }
		            /**DEFENSA EJERCICIO 1.- **/
		        }
		        //JUGADORES HUMANOS
		        else {
		            if(esCorrecta) {
		            	jugador.setPuntuacion(jugador.getPuntuacion() + 1);
		            }else if(!esCorrecta){
		            	jugador.setPuntuacion(jugador.getPuntuacion());
		            }
		        }
			}//Fin 2º for
			System.out.println("\n****RESULTADOS****");
			
			//Mostrar puntuaciones tras cada ronda
			for(int j= 0; j<jugadores.length; j++) {
				Jugador jugador= jugadores[j];
				System.out.println(jugador.getNombre() + ": " + jugador.getPuntuacion() + (jugador.getPuntuacion() != 1 ? " puntos" : " punto"));
			}//Fin 3º for
			System.out.println("FIN DE LA RONDA");
	        System.out.println("--------");
		}//Fin 1º for
		
		//Mostrar puntuaciones finales y el ganador
		System.out.println("\nFIN DE LA PARTIDA, MOSTRAMOS PUNTUACIONES FINALES Y AL GANADOR");
		int maxPuntuacion= 0;
		
		for(Jugador jugador: jugadores) {
			System.out.println(jugador.getNombre() + ": " + jugador.getPuntuacion() + (jugador.getPuntuacion() != 1 ? " puntos" : " punto"));
			if(jugador.getPuntuacion() > maxPuntuacion) {
				maxPuntuacion= jugador.getPuntuacion(); //Actualizamos la puntuación máxima
			}
		}//Fin for
		ArrayList<String> ganadores= new ArrayList<>(); //Lista para añadir los jugadores con la máxima puntuación
		
		for(Jugador jugador: jugadores) {
			if(jugador.getPuntuacion() == maxPuntuacion) {
				ganadores.add(jugador.getNombre());
			}
		}//Fin for
		
		if(ganadores.size() == 1) {//1 ganador
			System.out.print("-> GANÓ " + ganadores.get(0));
		}else {//Varios ganadores, empate
			System.out.print("-> EMPATE ENTRE ");
			for(int i=0; i<ganadores.size(); i++) {
				System.out.print(ganadores.get(i));
				if(i < ganadores.size()-1) {//Si no es el último se añade una coma
					System.out.print(", ");
				}
			}//Fin for
		}
		System.out.println(" CON " + maxPuntuacion + (maxPuntuacion != 1 ? " puntos" : " punto"));
		
		/**DEFENSA EJERCICIO 2.- **/
		System.out.println("\nINTENTAMOS ACTUALIZAR 'ganadores.txt'");
		almacenarGanadoresEnFichero(ganadores, maxPuntuacion);
		/**DEFENSA EJERCICIO 2.- **/
		
		//Registramos la partida en el histórico
		listaJugadores= Arrays.asList(jugadores); //Volvemos a usar la lista temporal
		listaJugadores.sort((jugador1, jugador2) -> Integer.compare(jugador2.getPuntuacion(), jugador1.getPuntuacion())); //Ordenamos los jugadores por puntuación máxima
		jugadores= listaJugadores.toArray(jugadores); //Actualizamos el array con los elementos de la lista temporal
		System.out.println("\nINTENTAMOS REGISTRAR LA PARTIDA EN EL HISTÓRICO");
		registrarPartidaEnHistorico(jugadores);
		
		//Actualizamos el ranking de jugadores humanos
		System.out.println("\nINTENTAMOS ACTUALIZAR EL RANKING DE JUGADORES HUMANOS");
		actualizarRanking(jugadores);
	}//Fin método
	
	//OPCIÓN 2
	public void ranking() {
		System.out.println("\n---RANKING DE JUGADORES Y SUS PUNTUACIONES---");
		try {
			File f= new File("src/proyectoFinal2/ranking.txt");
			Scanner entrada= new Scanner(f);
			
			if(!entrada.hasNext()) {
				System.out.println("LO SIENTO, EL RANKING ESTÁ VACÍO");
			}
			
			while(entrada.hasNext()) {
				String linea= entrada.nextLine();
				System.out.println(linea);
			}//Fin while
			entrada.close(); //Cerramos fichero
		}catch(FileNotFoundException e) {
			System.err.println("EXCEPCIÓN 'FileNotFoundException'");
			System.out.println("NO SE ENCONTRÓ EL FICHERO 'ranking.txt'");
		}catch(IOException e) {
			System.err.println("EXCEPCIÓN 'IOException'");
			System.out.println("ERROR AL LEER EL FICHERO");
		}
	}//Fin método
	/*******************************Métodos que usamos para la parte del ranking*******************************/
	private void actualizarRanking(Jugador[] jugadores) {
		Jugador jugadorLeido= null;
		ArrayList<Jugador> listaJugadoresLeidos= new ArrayList<>();
		
		try {
			File f= new File("src/proyectoFinal2/ranking.txt");
			Scanner entrada= new Scanner(f);
			
			//Leemos primero el fichero para ver si hay algún jugador o no
			while(entrada.hasNext()) {
				String linea= entrada.nextLine();
				String[] datosJugador= linea.split(" ");
				String nombreJug= datosJugador[0];
				int puntuacionJug= Integer.parseInt(datosJugador[1]);
				
				jugadorLeido= new Jugador(nombreJug, puntuacionJug);
				listaJugadoresLeidos.add(jugadorLeido); //Añadimos el jugador a la lista
			}//Fin while
			entrada.close(); //Cerramos fichero
			FileWriter writer= new FileWriter(f);
			boolean hayJugadoresHumanos= false;
			boolean hayCpus= false;
			
			for(int i=0; i<jugadores.length; i++) {
				Jugador jugador= jugadores[i];
				
				if(!(jugador.getNombre().equalsIgnoreCase(cpu1) || jugador.getNombre().equalsIgnoreCase(cpu2) ||
						jugador.getNombre().equalsIgnoreCase(cpu3) || jugador.getNombre().equalsIgnoreCase(cpu4))) {//Sólo humanos
					boolean encontrado= false; //Para verificar si algún jugador ya existe en listaJugadoresLeidos
					hayJugadoresHumanos= true; //Con que haya un solo jugador humano esto será true
					
					for(int j=0; j<listaJugadoresLeidos.size(); j++) {
						jugadorLeido= listaJugadoresLeidos.get(j);
						
						if(jugadorLeido.getNombre().equalsIgnoreCase(jugador.getNombre())) {
							//El jugador ya estaba en la lista pero su puntuación se debe actualizar tras la partida
							jugadorLeido.setPuntuacion(jugadorLeido.getPuntuacion() + jugador.getPuntuacion());
							encontrado= true;
							break;
						}
					}//Fin for
					
					if(!encontrado) {
						listaJugadoresLeidos.add(jugador); //Añadimos jugadores que no estuvieran previamente en listaJugadoresLeidos
					}
				}else {
					hayCpus= true; //Con que haya una sola cpu esto será true
				}
			}//Fin for
			
			//Tras actualizar la lista de jugadores leidos la ordenamos por la puntuación más alta
			listaJugadoresLeidos.sort((jugador1, jugador2) -> Integer.compare(jugador2.getPuntuacion(), jugador1.getPuntuacion()));
			
			//Actualizamos el fichero
			for(Jugador jugador: listaJugadoresLeidos) {
				writer.write(jugador.getNombre() + " " + jugador.getPuntuacion() + "\n");
			}//Fin for
			
			if(hayJugadoresHumanos) {
				System.out.println("RANKING ACTUALIZADO CON ÉXITO");	
			}else if(!hayJugadoresHumanos && hayCpus){
				System.out.println("EL RANKING SE MANTIENE INTACTO PORQUE SÓLO JUGARON CPUS");
			}		
			writer.close(); //Cerramos writer
		}catch(IOException e) {
			System.err.println("EXCEPCIÓN 'IOException'");
			System.out.println("ERROR AL INTENTAR ACTUALIZAR EL RANKING");
		}
	}//Fin método
	/*******************************Hasta aquí los Métodos que usamos para la parte del ranking*******************************/
	//OPCIÓN 3
	public void historico() {
		System.out.println("\n---HISTORIAL DE PARTIDAS CON JUGADORES (Y CPUs) Y SUS PUNTUACIONES---");
		try {
			File f= new File("src/proyectoFinal2/historico.txt");
			Scanner entrada= new Scanner(f);
			
			if(!entrada.hasNext()) {
				System.out.println("LO SIENTO, NO HAY PARTIDAS REGISTRADAS");
			}
			
			while(entrada.hasNext()) {
				String linea= entrada.nextLine();
				System.out.println(linea);
			}//Fin while
			entrada.close(); //Cerramos fichero
		}catch(FileNotFoundException e) {
			System.err.println("EXCEPCIÓN 'FileNotFoundException'");
			System.out.println("NO SE ENCONTRÓ EL FICHERO 'historico.txt'");
		}catch(IOException e) {
			System.err.println("EXCEPCIÓN 'IOException'");
			System.out.println("ERROR AL LEER EL FICHERO");
		}
	}//Fin método
	/*******************************Métodos que usamos para la parte del histórico*******************************/
	private void registrarPartidaEnHistorico(Jugador[] jugadores) {
		try {
			File f= new File("src/proyectoFinal2/historico.txt");
			FileWriter writer= new FileWriter(f, true);
			
			for(int i=0; i<jugadores.length; i++) {
				Jugador jugador= jugadores[i];
				writer.write(jugador.getNombre() + " " + jugador.getPuntuacion());
				if(i < jugadores.length-1) {//Si no es el último se añade ';'
					writer.write("; ");
				}
			}//Fin for
			writer.write("\n");
			System.out.println("SE AÑADIÓ LA PARTIDA AL HISTÓRICO CON ÉXITO");
			writer.close(); //Cerramos writer
		}catch(IOException e) {
			System.err.println("EXCEPCIÓN 'IOException'");
			System.out.println("ERROR AL INTENTAR AÑADIR LA PARTIDA EN EL HISTÓRICO");
		}
	}//Fin método
	/*******************************Hasta aquí los Métodos que usamos para la parte del histórico*******************************/
	//OPCIÓN 4
	public void gestionJugadores(){
		int opcion= 0;
		boolean salirGestionJugadores= false;
		boolean opcionAceptada= false;
		
		do {
			do {
				try {
					System.out.println("\nGESTIÓN DE JUGADORES");
					System.out.println("1. VER JUGADORES");
					System.out.println("2. AÑADIR JUGADOR");
					System.out.println("3. ELIMINAR JUGADOR");
					System.out.println("4. VOLVER AL MENÚ PRINCIPAL");
					System.out.print("INGRESE UNA OPCIÓN: ");
					opcion=entrada.nextInt();
					entrada.nextLine(); //Limpiamos buffer
					
					if(opcion >= 1 && opcion <= 4) {
						opcionAceptada= true;
					}else {
						System.out.println("OPCIÓN NO VÁLIDA, VUELVE A INTENTARLO.");
						opcionAceptada= false;
					}
				}catch(InputMismatchException e) {
					System.err.println("EXCEPCIÓN 'InputMismatchException'");
					System.out.println("VALOR PARA ELEGIR OPCIÓN INCORRECTO");
					entrada.nextLine(); //Limpiamos buffer
				}
			}while(!opcionAceptada);
			
			switch (opcion) {
			case 1:
				mostrarJugadores();
				break;
			case 2:
				añadirJugador();
				break;
			case 3:
				eliminarJugador();
				break;
			case 4:
				System.out.println("VOLVIENDO AL MENÚ PRINCIPAL...");
				salirGestionJugadores= true;
				break;
			}//Fin switch
		}while(!salirGestionJugadores);
	}//Fin método
	/*******************************Métodos que usamos en gestionJugadores()*******************************/
	private void mostrarJugadores(){
		try {
			File f= new File("src/proyectoFinal2/jugadores.txt");
			Scanner entrada= new Scanner(f);
			System.out.println("\n---JUGADORES ACTUALES EN EL SISTEMA---");
			
			if(!entrada.hasNext()) {
				System.out.println("LO SIENTO, NO HAY JUGADORES REGISTRADOS EN EL SISTEMA");
			}else {
				while(entrada.hasNext()) {
					String linea= entrada.nextLine();
					System.out.println("JUGADOR REGISTRADO: " + linea);
					
					if(!listaJugadoresHumanosDelSistema.contains(linea)) {
						//El jugador leído del fichero no estaba en la lista, se añade
						listaJugadoresHumanosDelSistema.add(linea);
					}
				}//Fin while
			}
			entrada.close(); //Cerramos fichero
		}catch(FileNotFoundException e) {
			System.err.println("EXCEPCIÓN 'FileNotFoundException'");
			System.out.println("NO SE ENCONTRÓ EL FICHERO 'jugadores.txt'");
		}catch(IOException e) {
			System.err.println("EXCEPCIÓN 'IOException'");
			System.out.println("ERROR AL LEER EL FICHERO");
		}
	}//Fin método
	
	private void añadirJugador(){
		try {
			File f= new File("src/proyectoFinal2/jugadores.txt");
			Scanner entradaFichero= new Scanner(f);
			
			//Verificar si hay jugadores existentes en el fichero
			if(entradaFichero.hasNext()) {
				System.out.println("\n---JUGADORES ACTUALES---");
				while(entradaFichero.hasNext()) {
					String jugadorExistente= entradaFichero.nextLine();
					System.out.println(jugadorExistente);
					
					if(!listaJugadoresHumanosDelSistema.contains(jugadorExistente)) {
						//El jugador leído del fichero no estaba en la lista, se añade
						listaJugadoresHumanosDelSistema.add(jugadorExistente);
					}
				}//Fin while
				System.out.println();
			}
			entradaFichero.close(); //Cerramos el fichero antes de volver a abrirlo
			
			//Verificar si el nombre del nuevo jugador ya existe
			boolean nombreExistente= false;
			boolean esCpu= false;
			Scanner entrada= new Scanner(System.in);
			
			System.out.print("INGRESE EL NOMBRE DEL NUEVO JUGADOR: ");
			String nombre= entrada.next();
			entradaFichero= new Scanner(f); //Abrimos de nuevo el fichero para leer los jugadores existentes
			
			while(entradaFichero.hasNext()) {
				String jugadorExistente= entradaFichero.nextLine();
				if (jugadorExistente.equalsIgnoreCase(nombre)) {
					nombreExistente= true;
					break;
				}
			}//Fin while
			entradaFichero.close(); //Cerramos fichero
			
			if(nombreExistente) {
				System.out.println("ERROR, EL NOMBRE DE ESTE JUGADOR YA EXISTE");
			}else if(nombre.equalsIgnoreCase(cpu1)|| nombre.equalsIgnoreCase(cpu2) || nombre.equalsIgnoreCase(cpu3) || nombre.equalsIgnoreCase(cpu4)) {
				esCpu= true;
				System.out.println("ERROR, NO SE PUEDEN AÑADIR CPUs");
			}else if(!nombreExistente && !esCpu) {
				listaJugadoresHumanosDelSistema.add(nombre); //Añadimos el nuevo jugador a la lista
				System.out.println("JUGADOR AGREGADO EXITOSAMENTE");
				
				//Escribimos el nuevo jugador al fichero
				FileWriter writer= new FileWriter(f);
				
				for(int i=0; i<listaJugadoresHumanosDelSistema.size(); i++) {
					nombre= listaJugadoresHumanosDelSistema.get(i);
					writer.write(nombre + "\n");
				}//Fin for
				writer.close(); //Cerramos writer
			}
		}catch(FileNotFoundException e) {
			System.err.println("EXCEPCIÓN 'FileNotFoundException'");
			System.out.println("NO SE ENCONTRÓ EL FICHERO 'jugadores.txt'");
		}catch(IOException e) {
			System.err.println("EXCEPCIÓN 'IOException'");
			System.out.println("ERROR AL INTENTAR AÑADIR AL JUGADOR");
		}
	}//Fin método

	private void eliminarJugador(){
		try {
			File f= new File("src/proyectoFinal2/jugadores.txt");
			Scanner entradaFichero= new Scanner(f);
	        
			//Verificar si hay jugadores existentes en el fichero
			if(entradaFichero.hasNext()) {
			    System.out.println("\n---JUGADORES ACTUALES---");
			    
			    while(entradaFichero.hasNext()) {
			        String jugadorExistente = entradaFichero.nextLine();
			        System.out.println(jugadorExistente);
			        
			        if(!listaJugadoresHumanosDelSistema.contains(jugadorExistente)) {
						//El jugador leído del fichero no estaba en la lista, se añade
						listaJugadoresHumanosDelSistema.add(jugadorExistente);
					}
			    }//Fin while
			    System.out.println();
			}else {
				System.out.println("LO SIENTO, NO HAY JUGADORES EN EL SISTEMA");
				entradaFichero.close(); //Cerramos fichero
				return; //Salimos del método
			}
			entradaFichero.close(); //Cerramos el fichero antes de volver a abrirlo

			//Solicitar al usuario que ingrese el nombre del jugador que quiera eliminar
			boolean esCpu= false;
			boolean encontrado= false;
			Scanner entrada= new Scanner(System.in);

			System.out.print("PON EL NOMBRE DEL JUGADOR QUE QUIERAS ELIMINAR: ");
			String nombre= entrada.next();
			entradaFichero= new Scanner(f); //Abrimos de nuevo el fichero para leer los jugadores existentes

			//Recorrer los jugadores y copiarlos en el fichero temporal sin el nombre del jugador a eliminar
		    while(entradaFichero.hasNext()) {
		        String jugadorExistente= entradaFichero.nextLine();
		        
		        if(jugadorExistente.equalsIgnoreCase(nombre)) {
		        	encontrado= true; //El jugador se encontró en el fichero
		        	break;
		        }
		    }//Fin while
		    
		    if(nombre.equalsIgnoreCase(cpu1) || nombre.equalsIgnoreCase(cpu2) || nombre.equalsIgnoreCase(cpu3) || nombre.equalsIgnoreCase(cpu4)) {
		        esCpu= true;
		        System.out.println("ERROR, NO SE PUEDEN PONER CPUs");
		    }else if(!encontrado) {
		        System.out.println("EL JUGADOR NO EXISTE, NO SE PUEDE ELIMINAR");
		    }else if(encontrado && !esCpu) {
		        
		        //Recorremos la lista antigua para añadir los jugadores no eliminados
		        for(int i=0; i<listaJugadoresHumanosDelSistema.size(); i++) {
		        	String nombreJugador= listaJugadoresHumanosDelSistema.get(i);
		        	
		        	if(nombreJugador.equalsIgnoreCase(nombre)) {
		        		listaJugadoresHumanosDelSistema.remove(i); //Eliminamos el jugador de la lista
		        	}
		        }//Fin for
		        
		       	//Escribimos los jugadores en el fichero
		        FileWriter writer = new FileWriter(f);
		        
		        for(int i=0; i<listaJugadoresHumanosDelSistema.size(); i++) {
		        	String nombreJugador= listaJugadoresHumanosDelSistema.get(i);
		        	writer.write(nombreJugador + "\n");
		        }//Fin for
		        System.out.println("JUGADOR ELIMINADO EXITOSAMENTE");
		        
		       	//Cerramos fichero y writer
		        entradaFichero.close();
		        writer.close();
		        
		        //Actualizamos ranking
		        System.out.println("\nACTUALIZAMOS EL RANKING TRAS ELIMINAR EL JUGADOR");
		        f= new File("src/proyectoFinal2/ranking.txt");
		        entradaFichero= new Scanner(f); //Abrimos fichero 'ranking.txt'
		        
		        Jugador jugadorLeido= null;
				ArrayList<Jugador> listaJugadoresLeidos= new ArrayList<>();
				
				//Leemos primero el fichero para ver si hay algún jugador o no
				while(entradaFichero.hasNext()) {
					String linea= entradaFichero.nextLine();
					String[] datosJugador= linea.split(" ");
					String nombreJug= datosJugador[0];
					int puntuacionJug= Integer.parseInt(datosJugador[1]);
					
					jugadorLeido= new Jugador(nombreJug, puntuacionJug);
					listaJugadoresLeidos.add(jugadorLeido); //Añadimos el jugador a la lista
				}//Fin while
				entradaFichero.close(); //Cerramos fichero
		        writer= new FileWriter(f); //Abrimos de nuevo el writer
		        
		        for(int i=0; i<listaJugadoresLeidos.size(); i++) {
		        	Jugador jugador= listaJugadoresLeidos.get(i);
		        	
		        	if(jugador.getNombre().equalsIgnoreCase(nombre)) {
		        		listaJugadoresLeidos.remove(i);
		        		break; //Salimos del for tras eliminar el jugador de la lista
		        	}
		        }//Fin for

		        //Aseguramos que el orden por puntuación más alta siga intacto
		        listaJugadoresLeidos.sort((jugador1, jugador2) -> Integer.compare(jugador2.getPuntuacion(), jugador1.getPuntuacion()));
				
				//Actualizamos el fichero
				for(Jugador jugador: listaJugadoresLeidos) {
					writer.write(jugador.getNombre() + " " + jugador.getPuntuacion() + "\n");
				}//Fin for
				System.out.println("RANKING ACTUALIZADO CON ÉXITO");
				writer.close(); //Cerramos writer
		    }
		}catch(IOException e) {
			System.err.println("EXCEPCIÓN 'IOException'");
			System.out.println("ERROR AL INTENTAR ELIMINAR AL JUGADOR");
		}
	}//Fin método
	/*******************************Hasta aquí los Métodos que usamos en gestionJugadores()*******************************/
	public void finalizar() {
		System.out.println("HASTA LA PRÓXIMA");
	}//Fin método
	
	/**DEFENSA EJERCICIO 2.- */
	//Método para almacenar el ganador o ganadores de cada partida en 'ganador.txt'
	private void almacenarGanadoresEnFichero(ArrayList<String> ganadores, int maxPuntuacion) {
		DateTimeFormatter formato= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); //Definimos este formato para la fecha y la hora
		LocalDateTime fecha= LocalDateTime.now(); //Fecha actual
		String fechaTexto= fecha.format(formato);
		
		String nombre= "";
		
		try {
			File f= new File("src/proyectoFinal2/ganadores.txt");
			FileWriter writer= new FileWriter(f, true);
			
			if(ganadores.size() == 1) {//1 ganador
				nombre= ganadores.get(0);
				writer.write(nombre + " " + maxPuntuacion + " " + fechaTexto);
			}else {//Varios ganadores
				for(int i=0; i<ganadores.size(); i++) {
					nombre= ganadores.get(i);
					writer.write(nombre + " " + maxPuntuacion + " " + fechaTexto);
					
					if(i < ganadores.size() - 1) {//Si no es el último ponemos al final ';'
						writer.write("; ");
					}
				}//Fin for
			}
			writer.write("\n");
			System.out.println("SE ACTUALIZÓ 'ganadores.txt' CORRECTAMENTE");
			writer.close(); //Cerramos writer
		}catch(IOException e) {
			System.err.println("EXCEPCIÓN 'IOException'");
			System.out.println("ERROR AL INTENTAR ALMACENAR EL GANADOR O GANADORES");
		}
	}//Fin método
}