package tsi.too.samuelwagner.enumeracoes;

/**Esta <code>Enum</code> possui os titulos utilizados nas interfaces do programa.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 *
 */
public enum TituloJanela {
	/**
	 * Título da janela de renda.
	 */
	RENDA("Renda"),
	
	/**
	 * Título da janela de renda mensal.
	 */
	RENDA_MENSAL("Renda Mensal"),
	
	/**
	 * Título da janela de categoria.
	 */
	CATEGORIA("Categoria"),
	
	/**
	 * Título da janela de despesa.
	 */
	DESPESA("Despesa"),
	
	/**
	 * Título da janela de meta mensal.
	 */
	META_MENSAL("Meta Mensal"),
	
	/**
	 * Título da janela de planejamento mensal.
	 */
	PLANEJAMENTO_MENSAL("Planejamento Mensal"),
	
	/**
	 * Título da janela de forma de pagamento.
	 */
	FORMA_PAGAMENTO("Forma Pagamento"),
	
	/**
	 * Título da janela de balanço mensal.
	 */
	IG_GRAFICO("Balanço Mensal"),
	
	/**
	 * Título da janela de balanço da forma de pagamento.
	 */
	IG_GRAFICO_DETALHE("Balanço da Forma de Pagamento"),
	
	/**
	 * Título da janela de gráfico da meta mensal.
	 */
	IG_GRAFICO_META("Grafico Meta Mensal"),
	
	/**
	 * Título da janela de gráfico por categoria.
	 */
	IG_GRAFICO_CATEGORIA("Grafico Categoria");
	
	private String titulo;

	/**
	 * Contrutor defaul da enumeração.
	 * @param titulo <code>String</code> com o valor da enumeração.
	 */
	TituloJanela(String titulo) {
		setTitulo(titulo);
	}
	
	/**
	 * Retorna o titulo da <code>Enum</code>.
	 * @return um <code>String</code> com valor da enumeração
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Atribui um valor a enumeração.
	 * @param titulo <code>String</code>
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	
}
