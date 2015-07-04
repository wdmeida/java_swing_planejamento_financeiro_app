package tsi.too.samuelwagner.tipo;
/**
 * A classe Renda herda os atributos da Classe Abstrata Identificacao
 * 
 * @author Samuel e Wagner
 */
public class Renda extends Identificacao {
	
	/**
	 * Contrutor default.
	 */
	public Renda() {
		super();
	}

	/**
	 * Contrutor sobrecarregado que recebe os seguintes parametros:
	 * @param codigo <code>int</code> de indentificação da renda.
	 * @param descricao <code>String</code> da renda.
	 */
	public Renda(int codigo, String descricao) {
		super(codigo, descricao);
	}
	
	/**
	 * Retorna uma referência em String do código e da descrição
	 * @return <code>String</code> referente aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}//class Renda
