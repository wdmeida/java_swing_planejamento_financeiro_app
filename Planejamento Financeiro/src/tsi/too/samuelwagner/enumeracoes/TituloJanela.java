package tsi.too.samuelwagner.enumeracoes;

/**Esta <code>Enum</code> possui os titulos utilizados nas interfaces do programa.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 *
 */
public enum TituloJanela {
	/**
	 * T�tulo da janela de renda.
	 */
	RENDA("Renda"),
	
	/**
	 * T�tulo da janela de renda mensal.
	 */
	RENDA_MENSAL("Renda Mensal"),
	
	/**
	 * T�tulo da janela de categoria.
	 */
	CATEGORIA("Categoria"),
	
	/**
	 * T�tulo da janela de despesa.
	 */
	DESPESA("Despesa"),
	
	/**
	 * T�tulo da janela de meta mensal.
	 */
	META_MENSAL("Meta Mensal"),
	
	/**
	 * T�tulo da janela de planejamento mensal.
	 */
	PLANEJAMENTO_MENSAL("Planejamento Mensal"),
	
	/**
	 * T�tulo da janela de forma de pagamento.
	 */
	FORMA_PAGAMENTO("Forma Pagamento"),
	
	/**
	 * T�tulo da janela de balan�o mensal.
	 */
	IG_GRAFICO("Balan�o Mensal"),
	
	/**
	 * T�tulo da janela de balan�o da forma de pagamento.
	 */
	IG_GRAFICO_DETALHE("Balan�o da Forma de Pagamento"),
	
	/**
	 * T�tulo da janela de gr�fico da meta mensal.
	 */
	IG_GRAFICO_META("Grafico Meta Mensal"),
	
	/**
	 * T�tulo da janela de gr�fico por categoria.
	 */
	IG_GRAFICO_CATEGORIA("Grafico Categoria");
	
	private String titulo;

	/**
	 * Contrutor defaul da enumera��o.
	 * @param titulo <code>String</code> com o valor da enumera��o.
	 */
	TituloJanela(String titulo) {
		setTitulo(titulo);
	}
	
	/**
	 * Retorna o titulo da <code>Enum</code>.
	 * @return um <code>String</code> com valor da enumera��o
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Atribui um valor a enumera��o.
	 * @param titulo <code>String</code>
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	
}
