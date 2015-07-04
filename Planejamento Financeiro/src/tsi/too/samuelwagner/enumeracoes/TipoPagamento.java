package tsi.too.samuelwagner.enumeracoes;
/**
 * Está classe terá todos os tipos de pagamento que será utilizado para o trabalho.
 * @author Samuel
 * @author Wagner 
 */
public enum TipoPagamento {
	
	CARTAO("Cartão de Crédito"),
	A_VISTA("À Vista"),
	PARCELADO("Parcelado"),
	FINANCIAMENTO("Financiamento"),
	CREDIARIO("Crediário"),
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
