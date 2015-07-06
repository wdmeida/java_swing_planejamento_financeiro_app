package tsi.too.samuelwagner.enumeracoes;
/**
 * Esta <code>Enum</code> possui os rótulos utilizados na interface de ajuda da janela <code>IgAjuda</code>.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 *
 */
public enum RotuloAjuda {
	/**
	 * Ajuda cadastrar despesa.
	 */
	CADASTRO_DESPESA("Para cadastrar uma despesa, basta você ir até a aba Despesas na janela principal, e clicar no botão Cadastrar."
			+ " Você também pode cadastrar através do painel de acesso rápido (tela inicial), clicando em Despesa no painel superiro,"
			+ " ou então utilizando de atalho Ctrl + D do teclado."),
	
	/**
	 * Ajuda visualizar despesa.		
	 */
	VISUALIZAR_DESPESA("Para visualizar uma despesa, basta você ir até a aba Despesas na janela principal, e clicar no botão Detalhes."
			+ " Você também pode visualizar através do painel de acesso rápido (tela inical), clicando em Despesas no painel inferior, "
			+ "ou então utilizar as teclas de atalho Ctrl + E do teclado."),
	/**
	 * Ajuda visualizar meta.		
	 */
	VISUALIZAR_META("Para visualizar uma meta, basta você ir até a aba Metas na janela principal, e selecionar o mês que "
			+ "deseja ver sua meta (caso esta esteja cadastrada). Você também pode visualizar a meta através de um gráfico na própria aba de Metas "
			+ "clicando no botão Visão Gráfica."),
	/**
	 * Ajuda cadastrar meta.		
	 */
	CADASTRAR_META("Para cadastrar uma meta, basta você ir até a aba Metas na janela principal, e clicar no botão Metas."
			+ " Você também pode cadastrar uma meta através do botão Metas no painel de acesso rápido (tela inicial), "
			+ " ou então utilizar as teclas de atalho Ctrl + M do teclado."),
	/**
	 * Ajuda cadastrar renda.		
	 */
	CADASTRAR_RENDA("Para cadastrar uma renda, basta você ir até a aba Retas na janela principal, e clicar no botão Cadastrar."
			+ " Você também pode cadastrar uma renda através do botão Rendas no painel de acesso rápido (tela inicial), "
			+ " ou então utilizar as teclas de atalho Ctrl + R do teclado."),
	/**
	 * Ajuda visualizar gráficos.		
	 */
	VISUALIZAR_GRAFICO("Existem quatro gráficos específicos que podem ser gerados. São eles: Gráfico de Metas, Categorias, "
			+ "Formas de pagamento e Balanço mensal. Eles podem ser acessados através da aba de Balanço Mensal, onde existe um"
			+ " botão relativo a cada gráfico e na aba de Meta onde existe um botão relativo ao Visão Gráfica deste. Eles também podem"
			+ "ser acessados através do painel principal, na aba de acesso rápido."),
	/**
	 * Ajuda salvar gráfico.		
	 */
	SALVAR_GRAFICO("Para salvar um gráfico, basta apenas clicar com o botão direito sobre este e escolher a opção salvar.");

	private String descricao;
	
	/**
	 * Contrutor defaul da enumeração.
	 * @param rotulo <code>String</code> com o valor da enumeração.
	 */
	private RotuloAjuda(String rotulo){
		this.descricao = rotulo;
	}
	
	/**
	 * Retorna um <code>String</code> com o valor da enumeração.
	 * @return um <code>String</code>.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Atribui um valor a enumeração.
	 * @param descricao <code>String</code>
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
