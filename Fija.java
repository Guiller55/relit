/*
 * Código utilizado para el curso de Estructuras de Datos.
 *
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o potenciales.
 */
package ed.aplicaciones.calculadora;

import java.util.Stack;
import java.util.Scanner;
import java.text.ParseException;

/**
 * Clase para evalúar expresiones en notaciones prefija y postfija.
 *
 * @author blackzafiro
 */
public class Fija {

	/**
	 * Evalúa la operación indicada por <code>operador</code>. Por ejemplo: si
	 * operador es '*' devuelve operador1 * operador2.
	 *
	 * @param operador character con el operador correspondiente a la operación
	 * que se desea realizar.
	 * @param operando1 primer operando.
	 * @param operando2 segundo operando.
	 * @return el resultado de aplicar la operación a los operandos.
	 */
	private static double evalúa(char operador, double operando1, double operando2) {
		double resultado = 0;
		switch(operador){
			case '+':
				resultado = operando1 + operando2;
				break;
			case '-':
				resultado = operando1 - operando2;
				break;
			case '*':
				resultado = operando1 * operando2;
				break;
			case '/':
				resultado = operando1 / operando2;
				break;
			case '%':
				resultado = operando1 % operando2;
				break;
		}
		return resultado;
	}

	private static boolean esNumero(String simbolo) {
        try {
            // intentamos convertir el símbolo a un número
            Double.parseDouble(simbolo);
        } catch (Exception e) {
            // si no se puede convertir, no es un número
            return false;
        }
        return true;
    }

	/**
	 * Recibe la secuencia de símbolos de una expresión matemática en notación
	 * prefija y calcula el resultado de evaluarla.
	 *
	 * @param tokens Lista de símbolos: operadores y números.
	 * @return resultado de la operación.
	 * @throws ParseException si la expresión contiene símbolos inválidos
	 *         o el número de operadores y operandos no coincide.
	 */
	public static double evalúaPrefija(String[] tokens) throws ParseException {
		Stack<Double> pila = new Stack<>();
		for(int i = tokens.length - 1; i >= 0; i--){
			String token = tokens[i];

			if(esNumero(token)){
				pila.push(Double.parseDouble(token));
			} else {
				char simbolo = token.charAt(0);
				double operando2 = pila.pop();
				double operando1 = pila.pop();
				pila.push(evalúa(simbolo, operando1, operando2));
			}
		}
		return pila.pop();
	}

	/**
	 * Recibe la secuencia de símbolos de una expresión matemática en notación
	 * postfija y calcula el resultado de evaluarla.
	 *
	 * @param tokens Lista de símbolos: operadores y números.
	 * @return resultado de la operación.
	 * @throws ParseException si la expresión contiene símbolos inválidos
	 *         o el número de operadores y operandos no coincide.
	 */
	public static double evalúaPostfija(String[] tokens) throws ParseException {
		Stack<Double> pila = new Stack<>();
		for(int i = 0; i < tokens.length; i++){
			String token = tokens[i];

			if(esNumero(token)){
				pila.push(Double.parseDouble(token));
			} else {
				char simbolo = token.charAt(0);
				double operando1 = pila.pop();
				double operando2 = pila.pop();
				pila.push(evalúa(simbolo, operando1, operando2));
			}
		}
		return pila.pop();
	}

	/**
	 * Interfaz de texto para la calculadora.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String sentence;
		String method = "prefija";
		AnalizadorMatemático analizador = new AnalizadorMatemático();
		String[] tokens;
		
		System.out.println("Calculadora en modo notación " + method);
		while (true) {
			sentence = scanner.nextLine();
			switch (sentence) {
				case "exit":
					return;
				case "prefija":
				case "postfija":
					System.out.println("Cambiando a notación " + sentence);
					method = sentence;
					continue;
				default:
					break;
			}
			tokens = analizador.extraeTokens(sentence);
			try {
				if (method.equals("postfija")) {
					System.out.println("= " + evalúaPostfija(tokens));
				} else {
					System.out.println("= " + evalúaPrefija(tokens));
				}
			} catch(ParseException pe) {
				System.out.println("Expresión inválida");
			}
		}
	}
}
