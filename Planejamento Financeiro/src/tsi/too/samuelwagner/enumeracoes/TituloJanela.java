package tsi.too.samuelwagner.enumeracoes;

/**Esta <code>Enum</code> possui os titulos utilizados nas interfaces do programa.
 * @author Samuel
 * @author Wagner
 *
 */
public enum TituloJanela {
	RENDA("Renda"),
	RENDA_MENSAL("Renda Mensal"),
	CATEGORIA("Categoria"),
	DESPESA("Despesa"),
	META_MENSAL("Meta Mensal"),
	PLANEJAMENTO_MENSAL("Planejamento Mensal"),
	FORMA_PAGAMENTO("Forma Pagamento"),
	IG_GRAFICO("Balanço Mensal"),
	IG_GRAFICO_DETALHE("Balanço da Forma de Pagamento"),
	IG_GRAFICO_META("Grafico Meta Mensal"),
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
