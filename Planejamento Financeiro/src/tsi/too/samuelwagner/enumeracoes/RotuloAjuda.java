package tsi.too.samuelwagner.enumeracoes;
/**
 * Esta <code>Enum</code> possui os r�tulos utilizados na interface de ajuda da janela <code>IgAjuda</code>.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 *
 */
public enum RotuloAjuda {
	/**
	 * Ajuda cadastrar despesa.
	 */
	CADASTRO_DESPESA("Para cadastrar uma despesa, basta voc� ir at� a aba Despesas na janela principal, e clicar no bot�o Cadastrar."
			+ " Voc� tamb�m pode cadastrar atrav�s do painel de acesso r�pido (tela inicial), clicando em Despesa no painel superiro,"
			+ " ou ent�o utilizando de atalho Ctrl + D do teclado."),
	
	/**
	 * Ajuda visualizar despesa.		
	 */
	VISUALIZAR_DESPESA("Para visualizar uma despesa, basta voc� ir at� a aba Despesas na janela principal, e clicar no bot�o Detalhes."
			+ " Voc� tamb�m pode visualizar atrav�s do painel de acesso r�pido (tela inical), clicando em Despesas no painel inferior, "
			+ "ou ent�o utilizar as teclas de atalho Ctrl + E do teclado."),
	/**
	 * Ajuda visualizar meta.		
	 */
	VISUALIZAR_META("Para visualizar uma meta, basta voc� ir at� a aba Metas na janela principal, e selecionar o m�s que "
			+ "deseja ver sua meta (caso esta esteja cadastrada). Voc� tamb�m pode visualizar a meta atrav�s de um gr�fico na pr�pria aba de Metas "
			+ "clicando no bot�o Vis�o Gr�fica."),
	/**
	 * Ajuda cadastrar meta.		
	 */
	CADASTRAR_META("Para cadastrar uma meta, basta voc� ir at� a aba Metas na janela principal, e clicar no bot�o Metas."
			+ " Voc� tamb�m pode cadastrar uma meta atrav�s do bot�o Metas no painel de acesso r�pido (tela inicial), "
			+ " ou ent�o utilizar as teclas de atalho Ctrl + M do teclado."),
	/**
	 * Ajuda cadastrar renda.		
	 */
	CADASTRAR_RENDA("Para cadastrar uma renda, basta voc� ir at� a aba Retas na janela principal, e clicar no bot�o Cadastrar."
			+ " Voc� tamb�m pode cadastrar uma renda atrav�s do bot�o Rendas no painel de acesso r�pido (tela inicial), "
			+ " ou ent�o utilizar as teclas de atalho Ctrl + R do teclado."),
	/**
	 * Ajuda visualizar gr�ficos.		
	 */
	VISUALIZAR_GRAFICO("Existem quatro gr�ficos espec�ficos que podem ser gerados. S�o eles: Gr�fico de Metas, Categorias, "
			+ "Formas de pagamento e Balan�o mensal. Eles podem ser acessados atrav�s da aba de Balan�o Mensal, onde existe um"
			+ " bot�o relativo a cada gr�fico e na aba de Meta onde existe um bot�o relativo ao Vis�o Gr�fica deste. Eles tamb�m podem"
			+ "ser acessados atrav�s do painel principal, na aba de acesso r�pido."),
	/**
	 * Ajuda salvar gr�fico.		
	 */
	SALVAR_GRAFICO("Para salvar um gr�fico, basta apenas clicar com o bot�o direito sobre este e escolher a op��o salvar.");

	private String descricao;
	
	/**
	 * Contrutor defaul da enumera��o.
	 * @param rotulo <code>String</code> com o valor da enumera��o.
	 */
	private RotuloAjuda(String rotulo){
		this.descricao = rotulo;
	}
	
	/**
	 * Retorna um <code>String</code> com o valor da enumera��o.
	 * @return um <code>String</code>.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Atribui um valor a enumera��o.
	 * @param descricao <code>String</code>
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
