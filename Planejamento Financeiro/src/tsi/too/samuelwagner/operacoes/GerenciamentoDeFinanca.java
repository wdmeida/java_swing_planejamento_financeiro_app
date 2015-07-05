package tsi.too.samuelwagner.operacoes;

import tsi.too.samuelwagner.controle.ControleCategoria;
import tsi.too.samuelwagner.controle.ControleDespesa;
import tsi.too.samuelwagner.controle.ControleFormaPagamento;
import tsi.too.samuelwagner.controle.ControleMetaMensal;
import tsi.too.samuelwagner.controle.ControlePlanejamentoMensal;
import tsi.too.samuelwagner.controle.ControleRenda;
import tsi.too.samuelwagner.controle.ControleRendaMensal;

/**
 * A classe <code>GerenciamentoDeFinanca</code> controla todas as operações do aplicativo Planejamento Financeiro.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class GerenciamentoDeFinanca {
	private static GerenciamentoDeFinanca gerenciamentoFinancas;
	private ControleCategoria controleCategoria;
	private ControleFormaPagamento controleFormaPagamento;
	private ControleMetaMensal controleMetaMensal;
	private ControlePlanejamentoMensal controlePlanejamentoMensal;
	private ControleRendaMensal controleRendaMensal;
	private ControleDespesa controleDespesa;
	private ControleRenda controleRenda;
	
	/**
	 * Construtor default. Instância os objetos responsáveis pelas operações dos objetos em disco. É definido como privado 
	 * para que apenas seja instânciado uma única vez para evitar perda de referências.
	 */
	private GerenciamentoDeFinanca(){
		controleCategoria = new ControleCategoria();
		controleFormaPagamento = new ControleFormaPagamento();
		controleMetaMensal = new ControleMetaMensal();
		controlePlanejamentoMensal = new ControlePlanejamentoMensal();
		controleRendaMensal = new ControleRendaMensal();
		controleDespesa = new ControleDespesa();
		controleRenda = new ControleRenda();
	}
	
	/**Cria a referências da Classe <code>GerenciamentoDeFinanca</code>.
	 * @return um <code>GerenciamentoDeFinanca</code>.
	 */
	public static GerenciamentoDeFinanca getGerenciamentoFincanca(){
		if(gerenciamentoFinancas == null) gerenciamentoFinancas = new GerenciamentoDeFinanca();
		return gerenciamentoFinancas;
	}
	
	/**Retorna a referência da Classe <code>ControleCategoria</code>
	 * @return um a referências de <code>ControleCategoria</code>
	 */
	public ControleCategoria getControleCategoria() {
		return controleCategoria;
	}

	/**Retorna a referência da Classe <code>ControleFormaPagamento</code>
	 * @return um a referências de <code>ControleFormaPagamento</code>
	 */
	public ControleFormaPagamento getControleFormaPagamento() {
		return controleFormaPagamento;
	}

	/**Retorna a referência da Classe <code>ControleMetaMensal</code>
	 * @return um a referências de <code>ControleMetaMensal</code>
	 */
	public ControleMetaMensal getControleMetaMensal() {
		return controleMetaMensal;
	}

	/**Retorna a referência da Classe <code>ControlePlanejamentoMensal</code>
	 * @return um a referências de <code>ControlePlanejamentoMensal</code>
	 */
	public ControlePlanejamentoMensal getControlePlanejamentoMensal() {
		return controlePlanejamentoMensal;
	}

	/**Retorna a referência da Classe <code>ControleRendaMensal</code>
	 * @return um a referências de <code>ControleRendaMensal</code>
	 */
	public ControleRendaMensal getControleRendaMensal() {
		return controleRendaMensal;
	}

	/**Retorna a referência da Classe <code>ControleDespesa</code>
	 * @return um a referências de <code>ControleDespesa</code>
	 */
	public ControleDespesa getControleDespesa() {
		return controleDespesa;
	}

	/**Retorna a referência da Classe <code>ControleRenda</code>
	 * @return um a referências de <code>ControleRenda</code>
	 */
	public ControleRenda getControleRenda() {
		return controleRenda;
	}
}//class GerenciamentoDeFinancas
