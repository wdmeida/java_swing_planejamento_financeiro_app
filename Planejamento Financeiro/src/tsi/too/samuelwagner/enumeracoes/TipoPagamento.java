package tsi.too.samuelwagner.enumeracoes;
/**
 * Est� classe ter� todos os tipos de pagamento que ser� utilizado para o trabalho.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 */
public enum TipoPagamento {
	
	/**
	 * Forma de pagamento cart�o de cr�dito.
	 */
	CARTAO("Cart�o de Cr�dito"),
	
	/**
	 * Forma de pagamento � vista.
	 */
	A_VISTA("� Vista"),
	
	/**
	 * Forma de pagamento parcelado.
	 */
	PARCELADO("Parcelado"),
	
	/**
	 * Forma de pagamento financiamento.
	 */
	FINANCIAMENTO("Financiamento"),
	
	/**
	 * Forma de pagamento credi�rio.
	 */
	CREDIARIO("Credi�rio"),
	
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
