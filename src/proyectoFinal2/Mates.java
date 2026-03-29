package proyectoFinal2;

import java.util.Random;

public class Mates {
	
	//Variables
	private String preguntaMates;
	private int resultadoCorrecto;

	//Getter del resultado correcto
	public int getResultadoCorrecto() {
		return resultadoCorrecto;
	}
	
	//Método para generar el operador aleatorio
	private String generarOperadorAleatorio() {
		Random random= new Random();
		String [] operadores= {"+", "-", "*"};
		int indiceOperadores= random.nextInt(operadores.length);
		String operador= " " + operadores[indiceOperadores] + " ";
		return operador;
	}//Fin método
	
	//Método para generar el número aleatorio
	private int generarNumeroAleatorio() {
		Random random= new Random();
		int numeroAleatorio= random.nextInt(2, 13);
		return numeroAleatorio;
	}//Fin método
	
	//Método para generar la pregunta aleatoria
	public String generaPregunta() {
		Random random= new Random();
		int numOperandos= random.nextInt(4, 9);
		
		StringBuilder pregunta= new StringBuilder();
		
		//Generamos la expresión matemática
		for(int i=0; i<numOperandos; i++) {
			if(i > 0) {
				pregunta.append(generarOperadorAleatorio());
			}
			pregunta.append(generarNumeroAleatorio());
		}//Fin for
		this.preguntaMates= pregunta.toString(); //Guardamos la pregunta
		this.resultadoCorrecto= resolverOperacion(); //Guardamos el resultado
		return pregunta.toString();
	}//Fin método

	//Método para validar la respuesta
	public boolean validarRespuesta(String respuesta) {
		//Convertir la respuesta proporcionada a entero
		int respuestaInt;
		try {
			respuestaInt= Integer.parseInt(respuesta);
		}catch (NumberFormatException e) {
			System.err.println("EXCEPCIÓN 'NumberFormatException'");
		    System.out.println("LA RESPUESTA NO ES UN NÚMERO VÁLIDO");
		    return false;
		}
		//Comparar la respuesta con el resultado calculado
		return respuestaInt == this.resultadoCorrecto;
	}//Fin método

	//Método para resolver la operación
	private int resolverOperacion(){
		//Obtener los operandos y operadores de la pregunta generada previamente
		String[] elementos= preguntaMates.split("\\s+");
		int resultado= Integer.parseInt(elementos[0]); //El primer elemento es un número
		
		for(int i=1; i<elementos.length; i+=2) {
		    String operador= elementos[i];
		    int operando= Integer.parseInt(elementos[i + 1]);
		    
		    //Realizamos la operación según el operador
		    switch (operador) {
		    case "+":
		    	resultado += operando;
		        break;
		    case "-":
		        resultado -= operando;
		        break;
		    case "*":
		        resultado *= operando;
		        break;
		    }//Fin switch
		}//Fin for
		return resultado;
	}//Fin método
}