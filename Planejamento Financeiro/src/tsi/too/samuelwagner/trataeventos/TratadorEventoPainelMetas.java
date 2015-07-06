package tsi.too.samuelwagner.trataeventos;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.border.LineBorder;

import tsi.too.samuelwagner.controle.ControleCategoria;
import tsi.too.samuelwagner.controle.ControleMetaMensal;
import tsi.too.samuelwagner.enumeracoes.RotuloJanelaMetaMensal;
import tsi.too.samuelwagner.gui.IgPlanejamentoFinanceiro;
import tsi.too.samuelwagner.operacoes.GerenciamentoDeFinanca;
import tsi.too.samuelwagner.tipo.Categoria;
import tsi.too.samuelwagner.tipo.Despesa;
import tsi.too.samuelwagner.tipo.MetaMensal;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;

/**
 * A classe <code>TratadorEventoPainelMetas</code> registra os m�todos respons�veis por tratar os eventos do painel de metas.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 */

public class TratadorEventoPainelMetas implements ActionListener{
	private IgPlanejamentoFinanceiro planejamentoFinanceiro;

	/**
	 * Construtor default da classe <code>TratadorEventoPainelMetas</code>. 
	 * @param planejamentoFinanceiro <code>TratadorEventoPainelMetas</code> com a refer�ncia da classe principal para
	 * poder acessar os componentes gr�ficos.
	 */
	public TratadorEventoPainelMetas(IgPlanejamentoFinanceiro planejamentoFinanceiro) {
		this.planejamentoFinanceiro = planejamentoFinanceiro;
	}//TratadorEventoPainelMetas()
	
	/**
	 * Registra os m�todos que trataram os eventos do painel de metas.
	 * @param event <code>ActionEvent</code>.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(planejamentoFinanceiro.getSelecionarCategoriaComboBox()))	
			carregarInformacoesMetaPainel();
	}//actionPerformed
	
	/**
	 * Carrega as informa��es no painel de metas de acordo com a categoria cadastrada. Exibe o valor da meta definida pelo usu�rio e 
	 * os gastos realizados pela determinada meta no corrente m�s.
	 */
	private void carregarInformacoesMetaPainel() {
		//Obt�m o nome da categoria.
		String nomeCategoria = planejamentoFinanceiro.getSelecionarCategoriaComboBox().getItemAt(planejamentoFinanceiro.getSelecionarCategoriaComboBox().getSelectedIndex());
		
		//Pesquisa a categoria para obter o c�digo.
		int indice = new ControleCategoria().pesquisaCategoria(nomeCategoria);
		
		//Obtem a categoria.
		Categoria categoria = new ControleCategoria().obtemCategoria(indice);
		Calendar data = Calendar.getInstance();
		
		//Recupera o objeto meta para configurar o valor no painel.
		MetaMensal meta = new ControleMetaMensal().pesquisarMetaMensal(categoria.getCodigo(), data);
		
		//Verifica se a meta foi obtida.
		if(meta != null){
			planejamentoFinanceiro.getMetaTextField().setText(String.format("R$ %1.2f", meta.getValor()));
			//Define o nome da categoria ao painel.
			planejamentoFinanceiro.getMetaCategoriaTextField().setText(nomeCategoria);
			//Define a porcentagem da meta.
			planejamentoFinanceiro.getAlertaTextField().setText(Integer.toString(meta.getPorcentagem()) + " %");
			
			Despesa []despesas = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleDespesa().pesquisarDespesas(categoria.getCodigo(), data);
			if(despesas != null){
				double total = somaDespesas(despesas);		
				planejamentoFinanceiro.getGastoTextField().setText(String.format("R$ %1.2f",total));
				planejamentoFinanceiro.getGastoTextField().setBorder(new LineBorder(Color.GRAY));
				
				planejamentoFinanceiro.getSelecionarCategoriaComboBox().transferFocus();
				verificarSituacaoMeta(total, nomeCategoria, meta);
			}
			else
			{
				planejamentoFinanceiro.getGastoTextField().setText("");
				planejamentoFinanceiro.getSelecionarCategoriaComboBox().transferFocus();
				showMessageDialog(planejamentoFinanceiro, RotuloJanelaMetaMensal.DESPESA_VAZIA.getDescricao(), RotuloJanelaMetaMensal.TITULO_META.getDescricao(), INFORMATION_MESSAGE);
				return;
			}
		}
		else
		{
			planejamentoFinanceiro.getGastoTextField().setText("");
			planejamentoFinanceiro.getMetaTextField().setText("");
			planejamentoFinanceiro.getMetaCategoriaTextField().setText("");
			planejamentoFinanceiro.getSelecionarCategoriaComboBox().transferFocus();
			showMessageDialog(planejamentoFinanceiro, RotuloJanelaMetaMensal.META_VAZIA.getDescricao(), RotuloJanelaMetaMensal.TITULO_META.getDescricao(), ERROR_MESSAGE);
			return;
		}
	}//carregarInformacoesMetaPainel()
	
	/**
	 * Verifica as situa��es das despesas em rela��o as metas, caso o usu�rio tenha extrapolado a meta, exibe
	 * um aviso na tela.
	 * @param total <code>double</code>.
	 * @param categoria <code>String</code>.
	 * @param meta <code>MetaMensal</code>.
	 */
	public void verificarSituacaoMeta(double total, String categoria, MetaMensal meta) {
		//Obt�m o valor da porcentagem do m�todo
		double metaMensal = FuncaoAuxiliar.valorSinalEmDinheiro(meta.getValor(), meta.getPorcentagem());
		
		if(metaMensal <= total){
			if(total < meta.getValor()){
				FuncaoAuxiliar.exibirMensagemErro(planejamentoFinanceiro, "Voc� extrapolou a porcentagem definida para sua meta:\n"
						+ "Categoria: " + categoria + "\nMeta: " + meta.getPorcentagem() + " %.", "Meta categoria");
				planejamentoFinanceiro.getGastoTextField().setBorder(new LineBorder(Color.YELLOW));
			}
			else{
				FuncaoAuxiliar.exibirMensagemErro(planejamentoFinanceiro, "Voc� extrapolou sua meta de or�amento na seguinte categoria:\n "
						+ categoria, "Meta categoria");
				planejamentoFinanceiro.getGastoTextField().setBorder(new LineBorder(Color.RED));
			}
				
		}
	}//verificarSituacaoMeta()
	
	/**
	 * Obt�m a soma das despesas de determinada categoria passadas por par�metro.
	 * @param despesas <code>Despesa[]</code> com um array com as despesas cadastradas.
	 * @return total <code>double</code> com o total das despesas cadastradas.
	 */
	public static double somaDespesas(Despesa[] despesas){
		double total = 0;
		
		for(Despesa desp : despesas)
			total += desp.getValorDespesa();
		return total;
	}//somaDespesas()
}//class TratadorEventoPainelMetas
