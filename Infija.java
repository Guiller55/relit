/**
 * Código utilizado para el curso de Estructuras de Datos.
 *
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo tal cual a estudiantes actuales o potenciales.
 */
package ed.aplicaciones.calculadora;

import java.text.ParseException;
import java.util.Stack;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 * Clase para evalúar expresiones en notación infija.
 *
 * @author blackzafiro
 */
public class Infija {

	/**
	 * Devuelve la precedencia de cada operador. Entre mayor es la precedencia,
	 * más pronto deverá ejecutarse la operación.
	 *
	 * @operador Símbolo que representa a las operaciones: +,-,*,/ y '('.
	 * @throws UnsupportedOperationException para cualquier otro símbolo.
	 */
	private static int precedencia(char operador) {
		switch(operador){
			case '+':
			case '-':
				return 1;
			case '*':
			case '/':
			case '%':
				return 2;
			default:
				throw new UnsupportedOperationException();
		}
	}

	/**
	 * Pasa las operaciones indicadas en notación infija a notación sufija o
	 * postfija.
	 *
	 * @param tokens Arreglo con símbolos de operaciones (incluyendo paréntesis)
	 *        y números (según la definición aceptada por
	 *        <code>Double.parseDouble(token)</code> en orden infijo.
	 * @return Arreglo con símbolos de operaciones (sin incluir paréntesis) y
	 *         números en orden postfijo.
	 * @throws ParseException si la expresión contiene símbolos inválidos
	 *         o el número de operadores y operandos no coincide.
	 */
	public static String[] infijaASufija(String[] tokens) throws ParseException {
		return null;
	}

	/**
	 * Recibe la secuencia de símbolos de una expresión matemática en notación
	 * infija y calcula el resultado de evaluarla.
	 *
	 * @param tokens Lista de símbolos: operadores, paréntesis y números.
	 * @return resultado de la operación.
	 * @throws ParseException si la expresión contiene símbolos inválidos
	 *         o el número de operadores y operandos no coincide.
	 */
	public static double evalúaInfija(String[] tokens) throws ParseException {
		String[] suf = infijaASufija(tokens);
		System.out.println("Sufija: " + Arrays.toString(suf));
		return Fija.evalúaPostfija(suf);
	}

	/**
	 * Interfaz de texto para la calculadora.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String sentence;
		String method = "infija";
		AnalizadorMatemático analizador = new AnalizadorMatemático();
		String[] tokens;

		System.out.println("Calculadora en modo notación " + method);
		while (true) {
			sentence = scanner.nextLine();
			switch (sentence) {
				case "exit":
					return;
				case "infija":
				case "prefija":
				case "postfija":
					System.out.println("Cambiando a notación " + sentence);
					method = sentence;
					continue;
				default:
					break;
			}
			tokens = analizador.extraeTokens(sentence);
			System.out.println("Tokens: " + Arrays.toString(tokens));
			double resultado;
			try {
				switch (method) {
					case "infija":
						resultado = evalúaInfija(tokens);
						break;
					case "prefija":
						resultado = Fija.evalúaPrefija(tokens);
						break;
					case "postfija":
						resultado = Fija.evalúaPostfija(tokens);
						break;
					default:
						System.out.println("Método inválido <" + method
							+ "> seleccione alguno de:\n"
							+ "\tinfija\n"
							+ "\tprefija\n"
							+ "\tpostfija\n");
						continue;
				}
				System.out.println("= " + resultado);
			} catch(ParseException pe) {
				System.out.println("Expresión inválida");
			}
		}
	}
}
