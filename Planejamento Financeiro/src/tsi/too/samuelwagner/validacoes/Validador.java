package tsi.too.samuelwagner.validacoes;

/** A classe <code>Validador</code> é reponsavel por implementar os métodos que validão as entradas
 * do usuário no programa.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class Validador {
	
	/**
	 * Verifica se a <code>String</code> passada e um número inteiro positivo.
	 * @param numero <code>String</code> que será usada para a validação.
	 * @return Retorna true <code>boolean</code> para número inteiro e false <code>boolean</code>
	 * para outro tipo de caracter.
	 */
	public static boolean validaNumeroInteiro(String numero) {
		try {
			if(Integer.parseInt(numero) <= 0)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Verifica se a <code>String</code> passada e um número inteiro positivo e menor ou igual a 100.
	 * @param numero <code>String</code> que será usada para a validação.
	 * @return Retorna true <code>boolean</code> para número inteiro e false <code>boolean</code>
	 * para outro tipo de caracter.
	 */
	public static boolean validaPorcentagem(String numero) {
		try {
			if(Integer.parseInt(numero) <= 0 || Integer.parseInt(numero) > 100) 
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Verifica se a <code>String</code> passada e um número real positivo.
	 * @param numero <code>String</code> que será usada para a validação.
	 * @return Retorna true <code>boolean</code> para número real e false <code>boolean</code>
	 * para outro tipo de caracter.
	 */
	public static boolean validaNumeroReal(String numero) {
		try {
			if(Double.parseDouble(numero) <= 0 || numero.indexOf("e") != -1) 
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Verifica se a <code>String</code> passada esta vazia.
	 * @param texto <code>String</code> que será usada para a validação.
	 * @return Retorna true <code>boolean</code> para <code>String</code> vazia false <code>boolean</code>
	 * caso contrario.
	 */
	public static boolean validaCampoVazio(String texto) {
		if(texto.equals(""))
			return true;
		return false;
	}
}
