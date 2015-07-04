package tsi.too.samuelwagner.tipo;

/**
 * Est� classe FormaPagamento guarda a forma como foi efetuado o pagamento da despesa.
 * Possui os seguintes par�metros: <code>int</code>codigo e <code>String</code>descricao herdados
 * da superclasse e <code>String</code> tipoPagamento.
 * @author Samuel e Wagner
 */
public class FormaPagamento extends Identificacao{
	/**
	 * Contrutor default
	 */
	public FormaPagamento(){}

	/**
	 * Contrutor sobrecarregado que recebe os seguintes par�metros:
	 * @param codigo <code>int</code>.
	 * @param descricao <code>String</code>.
	 */
	public FormaPagamento(int codigo, String descricao) {
		super(codigo, descricao);
	}
	
	/**
	 * Retornar uma refer�ncia de uma String contendo as informa��es do tipo de pagamento.
	 * @return <code>String</code>
	 */
	@Override
	public String toString() {
		return super.toString();
	}
	
}//class FormaPagamento
