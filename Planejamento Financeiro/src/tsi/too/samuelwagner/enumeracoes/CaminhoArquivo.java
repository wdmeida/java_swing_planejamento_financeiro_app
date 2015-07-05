package tsi.too.samuelwagner.enumeracoes;

import java.io.File;

/** Esta <code>Enum</code> possui os caminhos utilizados na para a gravação dos dados em arquivo.
 * @author Samuel Gonçalves
 *@author Wagner Almeida
 */
public enum CaminhoArquivo {
	/**
	 * Caminho do arquivo que salva a Renda do usuário.
	 */
	RENDA("arquivos" + File.separator + "Renda.dat"),
	
	/**
	 * Caminho do arquivo que salva a Renda Mensal do usuário.
	 */
	RENDA_MENSAL("arquivos" + File.separator + "RendaMensal.dat"),
	
	/**
	 * Caminho do arquivo que salva a Meta Mensal do usuário.
	 */
	META_MENSAL("arquivos" + File.separator + "MetaMensal.dat"),
	
	/**
	 * Caminho do arquivo que salva a Categoria do usuário.
	 */
	CATEGORIA("arquivos" + File.separator + "Categoria.dat"),
	
	/**
	 * Caminho do arquivo que salva a Despesa do usuário.
	 */
	DESPESA("arquivos" + File.separator + "Despesa.dat"),
	
	/**
	 * Caminho do arquivo que salva a Forma de Pagamento utilizada pelo usuário.
	 */
	FORMA_PAGAMENTO("arquivos" + File.separator + "FormaPagamento.dat"),
	
	/**
	 * Caminho do arquivo que salva a Planejamento Mensal a que a despesa do usuário faz parte.
	 */
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
	 * Retorna um <code>String</code> com o caminho do arquivo salvo na enumeração.
	 * @return um <code>String</code>.
	 */
	public String getCaminho() {
		return caminho;
	}

	/**
	 * Atribui o caminho do arquivo.
	 * @param caminho <code>String</code>
	 */
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
}
