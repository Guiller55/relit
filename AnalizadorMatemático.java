/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed.aplicaciones.calculadora;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author blackzafiro
 */
public class AnalizadorMatemático {
	
	private final String DELIMS = "\\s+|(?<=\\()|(?=\\))";
	
	public AnalizadorMatemático() {}
	
	public String[] extraeTokens(String línea) {
		String[] tokens = línea.split(DELIMS);
		List<String> toks = new LinkedList<>();
		for(String token : tokens) {
			if(!token.equals("")) toks.add(token);
		}
		tokens = toks.toArray(new String[toks.size()]);
		return tokens;
	}
	
}
