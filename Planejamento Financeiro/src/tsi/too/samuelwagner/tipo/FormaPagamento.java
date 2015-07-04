package tsi.too.samuelwagner.tipo;

/**
 * Está classe FormaPagamento guarda a forma como foi efetuado o pagamento da despesa.
 * Possui os seguintes parâmetros: <code>int</code>codigo e <code>String</code>descricao herdados
 * da superclasse e <code>String</code> tipoPagamento.
 * @author Samuel e Wagner
 */
public class FormaPagamento extends Identificacao{
	/**
	 * Contrutor default
	 */
	public FormaPagamento(){}

	/**
	 * Contrutor sobrecarregado que recebe os seguintes parâmetros:
	 * @param codigo <code>int</code>.
	 * @param descricao <code>String</code>.
	 */
	public FormaPagamento(int codigo, String descricao) {
		super(codigo, descricao);
	}
	
	/**
	 * Retornar uma referência de uma String contendo as informações do tipo de pagamento.
	 * @return <code>String</code>
	 */
	@Override
	public String toString() {
		return super.toString();
	}
	
}//class FormaPagamento
