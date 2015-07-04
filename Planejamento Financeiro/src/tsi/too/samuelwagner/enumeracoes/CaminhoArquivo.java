package tsi.too.samuelwagner.enumeracoes;

import java.io.File;

/** Esta <code>Enum</code> possui os caminhos utilizados na para a gravação dos dados em arquivo.
 * @author Samuel
 *@author Wagner
 */
public enum CaminhoArquivo {
	RENDA("arquivos" + File.separator + "Renda.dat"),
	RENDA_MENSAL("arquivos" + File.separator + "RendaMensal.dat"),
	META_MENSAL("arquivos" + File.separator + "MetaMensal.dat"),
	CATEGORIA("arquivos" + File.separator + "Categoria.dat"),
	DESPESA("arquivos" + File.separator + "Despesa.dat"),
	FORMA_PAGAMENTO("arquivos" + File.separator + "FormaPagamento.dat"),
	PLANEJAMENTO_MENSAL("arquivos" + File.separator + "PlanejamentoMensal.dat");
	
	private String caminho;

	/**
	 * Contrutor defaul da enumeração.
	 * @param rotulo <code>String</code> com o valor da enumeração.
	 */
	CaminhoArquivo(String caminho) {
		setCaminho(caminho);
	}
	

	/**
	 * Retorna um <code>String</code> com o valor da enumeração.
	 * @return um <code>String</code>.
	 */
	public String getCaminho() {
		return caminho;
	}

	/**
	 * Atribui um valor a enumeração.
	 * @param descricao <code>String</code>
	 */
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
	
}
