package tsi.too.samuelwagner.tipo;

import java.util.Comparator;
/**
 * A classe <code>Compara</code> implementa os m�todo respons�vel realizar a compara��o na ordena��o <code>String</code>
 *
 * @author Samuel e Wagner
 * @see Comparator
 *
 */
public class Compara implements Comparator<String> {
	/**
	 * O m�todo compare compara duas <code>String</code> para saberem se s�o iguais, menores ou maiores.
	 * 
	 * @param str1 <code>String</code> a ser comparada.
	 * @param str2 <code>String</code> a ser comparada.
	 */
	@Override
	public int compare(String str1, String str2) {
		return str1.compareToIgnoreCase(str2);
	}//compare
	
}//class Compara
