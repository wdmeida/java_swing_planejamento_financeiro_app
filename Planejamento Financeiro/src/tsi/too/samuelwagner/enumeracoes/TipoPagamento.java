package tsi.too.samuelwagner.enumeracoes;
/**
 * Est� classe ter� todos os tipos de pagamento que ser� utilizado para o trabalho.
 * @author Samuel
 * @author Wagner 
 */
public enum TipoPagamento {
	
	CARTAO("Cart�o de Cr�dito"),
	A_VISTA("� Vista"),
	PARCELADO("Parcelado"),
	FINANCIAMENTO("Financiamento"),
	CREDIARIO("Credi�rio"),
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
