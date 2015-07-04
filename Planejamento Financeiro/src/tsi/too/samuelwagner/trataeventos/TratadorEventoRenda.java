package tsi.too.samuelwagner.trataeventos;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;

import tsi.too.samuelwagner.enumeracoes.RotuloJanelaRendaERendaMensal;
import tsi.too.samuelwagner.enumeracoes.TituloJanela;
import tsi.too.samuelwagner.gui.IgCadastrarRenda;
import tsi.too.samuelwagner.operacoes.GerenciamentoDeFinanca;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;
import tsi.too.samuelwagner.validacoes.Validador;

/**A classe <code>TratadorEventoRenda</code> responsável por tratar os eventos da classe <code>IgCadastrarRenda</code>.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 *
 */
public class TratadorEventoRenda implements ActionListener {

	private IgCadastrarRenda igCadastrarRenda;
	private TituloJanela tituloJanela;
	
	
	/**Contrutor da Classe <code>TratadorEventoRenda</code> que recebe como parametro:
	 * @param igCadastrarRenda <code>IgCadastraRenda</code> recebe a referencia do Janela.
	 * @param tituloJanela <code>TituloJanela</code> recebe a referencia do titulo da Janela.
	 */
	public TratadorEventoRenda(IgCadastrarRenda igCadastrarRenda, TituloJanela tituloJanela) {
		this.igCadastrarRenda = igCadastrarRenda;
		this.tituloJanela = tituloJanela;
	}

	/** Método por receber o Evento gerado no <code>IgCadastraRenda</code> .
	 * @param event <code>ActionEvent</code>.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == igCadastrarRenda.getAdicionarButton())
			cadastraRenda();
		else
			if(event.getSource() == igCadastrarRenda.getCancelaButton())
				igCadastrarRenda.dispose();
	}
	
	/**
	 * Método responsavel por validar os dados e chamar a Classe <code>ControlePlanejamentoFinanceiro</code>
	 */
	private void cadastraRenda(){
		if(verificaSeRendaCadastrada()) return;
		
		if(!Validador.validaCampoVazio(igCadastrarRenda.getAreaDescricao().getText())){
			GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleRenda().gravarRenda(igCadastrarRenda.getAreaDescricao().getText(),
					GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleRenda().geraCodigo());
			FuncaoAuxiliar.exibirMensagem(igCadastrarRenda,tituloJanela.getTitulo() + RotuloJanelaRendaERendaMensal.SALVO.getDescricao(), tituloJanela.getTitulo());
			igCadastrarRenda.dispose();
		}else{
			FuncaoAuxiliar.exibirMensagemErro(igCadastrarRenda, RotuloJanelaRendaERendaMensal.VAZIO_RENDA_MENSAL.getDescricao(), tituloJanela.getTitulo());
			igCadastrarRenda.getAreaDescricao().setBorder(new LineBorder(Color.RED));
		}
		
	}
	
	/** Esse Método verifica se a renda fornecida já consta no sistema.
	 * @return um <code>boolean</code>.
	 */
	private boolean verificaSeRendaCadastrada(){
		int indice = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleRenda().pesquisaRenda(
				igCadastrarRenda.getAreaDescricao().getText());
		if(indice == -1) return false;
		
		showMessageDialog(igCadastrarRenda, TituloJanela.RENDA.getTitulo() +
				RotuloJanelaRendaERendaMensal.REPETIDO.getDescricao(), tituloJanela.getTitulo(), ERROR_MESSAGE);
		return true;
	}
}