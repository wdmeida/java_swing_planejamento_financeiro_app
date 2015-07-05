package tsi.too.samuelwagner.enumeracoes;

import java.io.File;

/** Esta <code>Enum</code> possui os caminhos utilizados na para a grava��o dos dados em arquivo.
 * @author Samuel Gon�alves
 *@author Wagner Almeida
 */
public enum CaminhoArquivo {
	/**
	 * Caminho do arquivo que salva a Renda do usu�rio.
	 */
	RENDA("arquivos" + File.separator + "Renda.dat"),
	
	/**
	 * Caminho do arquivo que salva a Renda Mensal do usu�rio.
	 */
	RENDA_MENSAL("arquivos" + File.separator + "RendaMensal.dat"),
	
	/**
	 * Caminho do arquivo que salva a Meta Mensal do usu�rio.
	 */
	META_MENSAL("arquivos" + File.separator + "MetaMensal.dat"),
	
	/**
	 * Caminho do arquivo que salva a Categoria do usu�rio.
	 */
	CATEGORIA("arquivos" + File.separator + "Categoria.dat"),
	
	/**
	 * Caminho do arquivo que salva a Despesa do usu�rio.
	 */
	DESPESA("arquivos" + File.separator + "Despesa.dat"),
	
	/**
	 * Caminho do arquivo que salva a Forma de Pagamento utilizada pelo usu�rio.
	 */
	FORMA_PAGAMENTO("arquivos" + File.separator + "FormaPagamento.dat"),
	
	/**
	 * Caminho do arquivo que salva a Planejamento Mensal a que a despesa do usu�rio faz parte.
	 */
	PLANEJAMENTO_MENSAL("arquivos" + File.separator + "PlanejamentoMensal.dat");
	
	private String caminho;

	/**
	 * Contrutor defaul da enumera��o.
	 * @param rotulo <code>String</code> com o valor da enumera��o.
	 */
	CaminhoArquivo(String caminho) {
		setCaminho(caminho);
	}
	

	/**
	 * Retorna um <code>String</code> com o caminho do arquivo salvo na enumera��o.
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
