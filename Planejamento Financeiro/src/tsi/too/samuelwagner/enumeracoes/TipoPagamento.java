package tsi.too.samuelwagner.enumeracoes;
/**
 * Está classe terá todos os tipos de pagamento que será utilizado para o trabalho.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public enum TipoPagamento {
	
	/**
	 * Forma de pagamento cartão de crédito.
	 */
	CARTAO("Cartão de Crédito"),
	
	/**
	 * Forma de pagamento à vista.
	 */
	A_VISTA("À Vista"),
	
	/**
	 * Forma de pagamento parcelado.
	 */
	PARCELADO("Parcelado"),
	
	/**
	 * Forma de pagamento financiamento.
	 */
	FINANCIAMENTO("Financiamento"),
	
	/**
	 * Forma de pagamento crediário.
	 */
	CREDIARIO("Crediário"),
	
	/**
	 * Forma de pagamento cheque.
	 */
	CHEQUE("Cheque");
	
	private String tipoPagamento;
	
	/**
	 * Construtor sobrecarregado.
	 * @param tipoPagamento do tipo <code>String</code>.
	 */
	TipoPagamento(String tipoPagamento) {
		setTipoPagamento(tipoPagamento);
	}
	
	/**
	 * Retorna o tipo do pagamento armanzenado na <code>String</code>.
	 * @return uma <code>String</code>.
	 */
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	
	/**
	 * Define o valor da variavel.
	 * @param tipoPagamento do tipo <code>String</code>.
	 */
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
}
