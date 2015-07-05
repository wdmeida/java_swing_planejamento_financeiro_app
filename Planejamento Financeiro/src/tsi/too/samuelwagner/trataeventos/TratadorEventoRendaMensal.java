package tsi.too.samuelwagner.trataeventos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;

import tsi.too.samuelwagner.enumeracoes.RotuloJanelaRendaERendaMensal;
import tsi.too.samuelwagner.enumeracoes.TituloJanela;
import tsi.too.samuelwagner.gui.IgCadastraRendaMensal;
import tsi.too.samuelwagner.gui.IgPlanejamentoFinanceiro;
import tsi.too.samuelwagner.operacoes.GerenciamentoDeFinanca;
import tsi.too.samuelwagner.operacoes.OperacoesDoIgPlanejamentoFinanceiro;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;
import tsi.too.samuelwagner.validacoes.Validador;

/**
 * A classe <code>TratadorEventoRendaERendaMensal</code> implementa os métodos para tratar os eventos
 * da lasse <code>IgCadastraRendaMensal</code>.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 *
 */
public class TratadorEventoRendaMensal implements ActionListener{
	
	private TituloJanela tituloJanela;
	private IgCadastraRendaMensal igCadastraRendaMensal;
	private IgPlanejamentoFinanceiro igPlanejamentoFinanceiro;
	
	/**Contrutor da Classe <code>TratadorEventoRendaMensal</code> que recebe como parametro:
	 * @param tituloJanela <code>Enum</code> recebe a referencia do titulo da Janela.
	 * @param igCadastraRendaMensal <code>IgCadastraRendaMensal</code> recebe a referencia do Janela.
	 * @param igPlanejamentoFinanceiro <code>IgPlanejamentoFinanceiro</code>
	 */
	public TratadorEventoRendaMensal(TituloJanela tituloJanela, IgCadastraRendaMensal igCadastraRendaMensal, IgPlanejamentoFinanceiro igPlanejamentoFinanceiro) {
		this.tituloJanela = tituloJanela;
		this.igCadastraRendaMensal = igCadastraRendaMensal;
		this.igPlanejamentoFinanceiro = igPlanejamentoFinanceiro;
	}

	/** Método por receber o Evento gerado no <code>IgCadastraRendaMensal</code> .
	 * @param event <code>ActionEvent</code>.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == igCadastraRendaMensal.getAdicionarButton())
					cadastraRendaMensal();
		else
			if(event.getSource() == igCadastraRendaMensal.getCancelaButton())
				igCadastraRendaMensal.dispose();
	}
	
	/**
	 * Método responsavel por validar os dados e chamar a Classe <code>ControlePlanejamentoFinanceiro</code>
	 */
	private void cadastraRendaMensal() {
		
		if(Validador.validaNumeroReal(FuncaoAuxiliar.converteVirgulaEmPonto(igCadastraRendaMensal.getAreaValorField().getText()))){
			GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleRendaMensal().gravarRendaMensal(
					igCadastraRendaMensal.getTipoRendaBox().getItemAt(igCadastraRendaMensal.getTipoRendaBox().getSelectedIndex()),
					obtemCodigoRendaEscolhida(igCadastraRendaMensal.getTipoRendaBox().getSelectedIndex()),
					igCadastraRendaMensal.getjCalendar().getCalendar(), 
					Double.parseDouble(FuncaoAuxiliar.converteVirgulaEmPonto(igCadastraRendaMensal.getAreaValorField().getText())));
			FuncaoAuxiliar.exibirMensagem(igCadastraRendaMensal,tituloJanela.getTitulo() + RotuloJanelaRendaERendaMensal.SALVO.getDescricao(), tituloJanela.getTitulo());
			OperacoesDoIgPlanejamentoFinanceiro.atualizaTabelaRenda(igPlanejamentoFinanceiro);
			igCadastraRendaMensal.dispose();
		}else{
			FuncaoAuxiliar.exibirMensagemErro(igCadastraRendaMensal, RotuloJanelaRendaERendaMensal.VAZIO_RENDA_MENSAL.getDescricao(), tituloJanela.getTitulo());
			igCadastraRendaMensal.getAreaValorField().setBorder(new LineBorder(Color.RED));
		}
	}
	
	/** Esse método retorna um indice da renda escolhida.
	 * @param indice <code>int</code> da Renda escolhida.
	 * @return um <code>int</code>.
	 */
	private int obtemCodigoRendaEscolhida(int indice) {
		return GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleRenda().geraCodigo();
	}
}
