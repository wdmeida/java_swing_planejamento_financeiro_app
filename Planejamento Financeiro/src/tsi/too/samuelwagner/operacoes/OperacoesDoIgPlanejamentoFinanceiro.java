package tsi.too.samuelwagner.operacoes;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import tsi.too.samuelwagner.gui.IgPlanejamentoFinanceiro;
import tsi.too.samuelwagner.tipo.Despesa;
import tsi.too.samuelwagner.tipo.RendaMensal;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;

/**A classe <code>OperacoesDoIgPlanejamentoFinanceiro</code> é responsável pelas operações da classe
 * <code>IgPlanejamentoFinanceiro</code>.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */

public class OperacoesDoIgPlanejamentoFinanceiro {
	
	/**
	 * Carrega os nomes das categorias salvas em disco para o comboBox.
	 * @return um vetor de <code>String</code> com os nomes das categorias.
	 */
	public static String[] carregarValoresComboBoxMeta(){
		String[] nomesCategorias = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleCategoria().obterNomesCategorias();
		if(nomesCategorias != null){
			Arrays.sort(nomesCategorias);
			return nomesCategorias;
		}
		return null;
	}//carregarValoresComboBox()
	
	/**Gera os dados referente a tabela Renda.
	 * @param mesAno <code>String</code> data da renda procurada.
	 * @return uma Matriz de <code>String</code>.
	 */
	public static String[][] preencheTabelaRenda(String mesAno) {
		List<String> descricao = new LinkedList<>(),
						   data = new LinkedList<>(),
						   valor = new LinkedList<>();
		int indice, numerosDeRendaMensais = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleRendaMensal().numerosDeRendas();
		for( indice = 0; indice < numerosDeRendaMensais; indice++){
			RendaMensal rendaMensal = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleRendaMensal().obtemRendaMensal(indice);
			if(mesAno.equalsIgnoreCase(FuncaoAuxiliar.obtemMesAno(FuncaoAuxiliar.coverteDataParaString(rendaMensal.getData(),true)))){
				descricao.add(rendaMensal.getDescricao());
				data.add(FuncaoAuxiliar.coverteDataParaString(rendaMensal.getData(), true));
				valor.add(String.format("R$ %1.2f", rendaMensal.getValor()));
			}
		}
		String[] retorno = new String[0];
		return preencheTabela(descricao.toArray(retorno), data.toArray(retorno), valor.toArray(retorno));
	}
	
	/**
	 * Obtém os dados das 10 despesas mais recentes cadastradas e exibe um resumo para o usuário.
	 * @return um <code>String[][]</code> contendo o conteudo das linhas e colunas de informações para preencher a tabela.
	 */
	public static String[][] resumoDespesas(){		
		List<String> descricao = new LinkedList<>(),
					 data = new LinkedList<>(),
					 valor = new LinkedList<>();
		int indice, numeroDespesas = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleDespesa().quantidadeDespesas();
		int contador = 0; //Cria um contador pra controlar e pegar apenas as últimas 10 despesas.
		
		for(indice = numeroDespesas - 1; indice >= 0; indice--){
			Despesa despesa = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleDespesa().obtemDespesa(indice);
			descricao.add(despesa.getDescricao());
			data.add(FuncaoAuxiliar.coverteDataParaString(despesa.getDataPagamento(), true));
			valor.add(String.format("R$ %1.2f", despesa.getValorDespesa()));
			contador++;
			if(contador == 20) break;
		}//for
		
		String[] retorno = new String[0];
		return preencheTabela(descricao.toArray(retorno), data.toArray(retorno), valor.toArray(retorno));
	}//resumoDespesas()

	/**Gera uma Matriz para preencher a Tabela.
	 * 
	 * @param descricao vetor de <code>String</code> contendo as descrições.
	 * @param data vetor de <code>String</code> contendo as datas.
	 * @param valor vetor de <code>String</code> contendo os valores.
	 * @return uma Matriz de <code>String</code>.
	 */
	public static String[][] preencheTabela(String[] descricao, String[] data, String[] valor) {
		String[][] dados;
		dados = new String[descricao.length][3];
		
		for(int indice = 0; indice < descricao.length;indice++){
			dados[indice][0] = descricao[indice];
			dados[indice][1] = data[indice];
			dados[indice][2] = valor[indice];
		}
		return dados;
	}
	
	/**Método preenche comboBox da tabela Renda.
	 * @return um vetor de <code>String</code>.
	 */
	public static String[] preencheMesAnteriorRenda(){
		//Obtem o numero de rendas mensais cadastradas.
		int numeroRendas = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleRendaMensal().numerosDeRendas();
		Map<String,String> mesesHash = new HashMap<>();
		String mes;
		for(int indice = 0; indice < numeroRendas;indice++){
			mes = FuncaoAuxiliar.obtemMesAno(FuncaoAuxiliar.coverteDataParaString(GerenciamentoDeFinanca.
					getGerenciamentoFincanca().getControleRendaMensal().obtemRendaMensal(indice).getData(),true));
				mesesHash.put(mes,mes);
		}
			
		return preencherMesAnterior(mesesHash);
	}
	
	/**Método preenche comboBox do grafico Meta Mesal.
	 * @return um vetor de <code>String</code>.
	 */
	public static String[] preencheMesAnteriorMeta(){
		//Obtem o numero de rendas mensais cadastradas.
		int numeroMetas = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleMetaMensal().retornaNumeroDeMetas();
		Map<String,String> mesesHash = new HashMap<>();
		String mes;
		for(int indice = 0; indice < numeroMetas;indice++){
			mes = FuncaoAuxiliar.obtemMesAno(FuncaoAuxiliar.coverteDataParaString(GerenciamentoDeFinanca.
					getGerenciamentoFincanca().getControleMetaMensal().obtemMetaMensal(indice).getMesAnoMeta(),true));
				mesesHash.put(mes,mes);
		}
		return preencherMesAnterior(mesesHash);
	}
	
	/**Método preenche comboBox do grafico Despesa.
	 * @return um vetor de <code>String</code>.
	 */
	public static String[] preencheMesAnteriorDespesa(){
		//Obtem o numero de rendas mensais cadastradas.
		int numeroDespesa = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleDespesa().numerosDeDespesas();
		Map<String,String> mesesHash = new HashMap<>();
		String mes;
		for(int indice = 0; indice < numeroDespesa;indice++){
			mes = FuncaoAuxiliar.obtemMesAno(FuncaoAuxiliar.coverteDataParaString(GerenciamentoDeFinanca.
					getGerenciamentoFincanca().getControleDespesa().obtemDespesa(indice).getDataDespesa(),true));
				mesesHash.put(mes,mes);
		}
		return preencherMesAnterior(mesesHash);
	}
	
	/**Gera um array de String com um map passado.
	 * @param mesesHash <code>Map</code> com o meses.
	 * @return um vetor de <code>String</code>.
	 */
	private static String[] preencherMesAnterior(Map<String,String> mesesHash){
		Collection<String> aux = mesesHash.values();
		String[] meses = aux.toArray(new String[0]);
			
		aux.clear();
		mesesHash.clear();
		
		Arrays.sort(meses);
		
		return meses;
	}
	
	/**
	 * Atualiza os dados na tabela de renda.
	 * @param igPlanejamentoFinanceiro <code>IgPlanejamentoFinanceiro</code>.
	 */
	public static void atualizaTabelaRenda(IgPlanejamentoFinanceiro igPlanejamentoFinanceiro) {
		igPlanejamentoFinanceiro.getMesRendaComboBox().setModel(new DefaultComboBoxModel<String>(preencheMesAnteriorRenda()));
		
		igPlanejamentoFinanceiro.getRendaTable().setModel(new DefaultTableModel(
				OperacoesDoIgPlanejamentoFinanceiro.preencheTabelaRenda((String)igPlanejamentoFinanceiro.getMesRendaComboBox().getSelectedItem())
				, new String[] {"Descrição","Data","Valor"}));
	}
	
	/**
	 * Atualiza os dados na tabela de despesa.
	 * @param igPlanejamentoFinanceiro <code>IgPlanejamentoFinanceiro</code>.
	 */
	public static void atualizaTabelaDespesa(IgPlanejamentoFinanceiro igPlanejamentoFinanceiro) {
		
		igPlanejamentoFinanceiro.getDespesasRecentesTable().setModel(new DefaultTableModel(
				OperacoesDoIgPlanejamentoFinanceiro.resumoDespesas(), new String[] {"Descrição","Data","Valor"}));
	}
}