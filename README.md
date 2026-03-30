# Proyecto final de Programación - 1º DAM
El proyecto incluye generación aleatoria de preguntas de mates, letras e inglés, validación de respuestas, soporte para jugadores humanos y CPU, actualización de ranking, registro del histórico de partidas y gestión de jugadores registrados. También se integran las modificaciones de la defensa de un examen correspondientes al bloque de ficheros: punto-pregunta, guardado de ganadores con fecha y hora y partidas de 2 a 6 jugadores. La única parte no implementada en esta versión es el bloque de base de datos del examen.

## Funcionalidades
- Menú principal por consola con acceso a las distintas opciones del programa.
- Inicio de partidas con un número configurable de jugadores y rondas.
- Soporte para jugadores humanos y jugadores CPU.
- Mezcla aleatoria del orden de participación de los jugadores al comenzar cada partida.
- Generación aleatoria de distintos tipos de preguntas:
  - Preguntas de mates.
  - Preguntas de letras.
  - Preguntas de inglés con respuestas desordenadas aleatoriamente.
  - Punto-pregunta, que otorga un punto directo sin responder nada.
- Validación de respuestas introducidas por teclado.
- Comportamiento automático para las CPU según el tipo de pregunta:
  - En mates siempre aciertan.
  - En letras siempre fallan.
  - En inglés responden aleatoriamente.
  - En punto-pregunta reciben el punto automáticamente.
- Visualización de resultados al final de cada ronda.
- Cálculo de puntuaciones finales y determinación del ganador o de posibles empates.
- Registro del histórico de partidas en fichero.
- Actualización del ranking de jugadores humanos en fichero.
- Almacenamiento del ganador o ganadores en fichero con fecha y hora.
- Gestión de jugadores registrados:
  - Ver jugadores.
  - Añadir jugador.
  - Eliminar jugador.

## Tecnologías utilizadas
- Java
- Eclipse como entorno de desarrollo

## Flujo de la aplicación
1. Al iniciar el programa se muestra el menú principal.
2. El usuario puede elegir entre jugar una partida, consultar el ranking, ver el histórico, gestionar jugadores o salir.
3. Si se elige jugar, se solicita:
   - Número de jugadores (entre 2 y 6).
   - Número de rondas (3, 5, 10 o 20).
4. Después se seleccionan los jugadores que participarán en la partida, pudiendo ser humanos registrados o CPUs.
5. El sistema mezcla aleatoriamente el orden de los jugadores.
6. En cada ronda, cada jugador recibe un tipo de pregunta aleatorio.
7. Se valida la respuesta y se actualiza la puntuación correspondiente.
8. Al finalizar cada ronda se muestran las puntuaciones parciales.
9. Al terminar la partida se muestran las puntuaciones finales y el ganador o ganadores.
10. Finalmente:
    - Se registra la partida en el histórico.
    - Se actualiza el ranking.
    - Se almacenan los ganadores en su fichero correspondiente.

## Estructura general del proyecto
- `ProgramaPrincipalJuego.java`: contiene el método `main` y arranca la aplicación.
- `JuegoDePreguntas.java`: clase principal con la lógica general del menú, partidas, ranking, histórico y gestión de jugadores.
- `Jugador.java`: representa a cada jugador, humano o CPU.
- `JuegoInterfaz.java`: interfaz con los métodos principales del juego.
- `Partida.java`: clase abstracta base para la estructura de la partida.
- `Ronda.java`: gestiona la generación del tipo de pregunta y la validación de respuestas.
- `Mates.java`: generación y validación de preguntas matemáticas.
- `Letras.java`: generación y validación de preguntas de palabras ocultas.
- `Ingles.java`: lectura y validación de preguntas tipo test en inglés.

## Ficheros utilizados
- `jugadores.txt`: almacena los jugadores humanos registrados.
- `ranking.txt`: almacena el ranking acumulado de jugadores humanos.
- `historico.txt`: guarda el histórico de partidas jugadas.
- `ganadores.txt`: registra el ganador o ganadores de cada partida junto con fecha y hora.
- `diccionario.txt`: contiene las palabras utilizadas en las preguntas de letras.
- `ingles.txt`: contiene las preguntas y respuestas utilizadas en las preguntas de inglés.

## Mejoras añadidas respecto al proyecto base
Se incorporan varias mejoras correspondientes a la defensa del proyecto:
- Nueva modalidad **punto-pregunta**.
- Registro del ganador o ganadores en el fichero `ganadores.txt`.
- Ampliación del número de jugadores permitidos de 2 a 6.

## Parte no implementada
La única parte no desarrollada en esta versión es el bloque de base de datos de la defensa, que consistía en almacenar estadísticas sobre la frecuencia de aparición de los distintos tipos de preguntas y mostrarlas en el menú principal.

## Autor
RubenMerFer
