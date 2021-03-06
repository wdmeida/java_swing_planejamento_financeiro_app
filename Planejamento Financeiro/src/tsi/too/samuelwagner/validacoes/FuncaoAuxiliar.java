package tsi.too.samuelwagner.validacoes;

import static javax.swing.JOptionPane.*;

import java.awt.Component;
import java.text.Collator;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.DefaultComboBoxModel;

import tsi.too.samuelwagner.operacoes.GerenciamentoDeFinanca;
/**
 * A classe <code>FuncaoAuxiliar</code> possui os m�todos <code>static</code> que s�o utilizados por todas 
 * as outras classes do aplicativo Planejamento Financeiro.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 */
public class FuncaoAuxiliar {
	private static String nomeMeses[] = {"Janeiro","Fevereiro","Mar�o","Abril","Maio","Junho","Julho",
			"Agosto","Setembro","Outubro","Novembro","Desembro"};
	
	/** Converte um <code>Calendar</code> para <code>String</code>.
	 * @param data <code>Calendar</code>.
	 * @param completa <code>boolean</code> com os valores <code>true</code> caso queira a data completa ou <code>false</code> 
	 * 					caso queira apenas m�s e ano.
	 * @return um <code>String</code> formatada.
	 */
	public static String coverteDataParaString(Calendar data, boolean completa) {
		if(completa)
			return String.format("%02d/%02d/%04d", data.get(Calendar.DAY_OF_MONTH),
												   data.get(Calendar.MONTH)+1,
												   data.get(Calendar.YEAR));
		else
			return String.format("%02d/%04d", data.get(Calendar.MONTH)+1,
					   						  data.get(Calendar.YEAR));

	}//converteDataParaString()
	
	/**Obt�m m�s e ano de uma data.
	 * @param diaMesAno <code>String</code> contendo o dia mes e ano;
	 * @return um <code>String</code> com o M�s e o Ano.
	 */
	public static String obtemMesAno(String diaMesAno){
		StringTokenizer tokens = new StringTokenizer(diaMesAno, "/");
		tokens.nextToken();
		String mes = tokens.nextToken(),
			   ano = tokens.nextToken();
		return nomeMeses[Integer.parseInt(mes)-1] +" "+ano;
	}//obtemMesAno
	
	/**
	 * Converte uma data em <code>String</code> para <code>Calendar</code>.
	 * @param data <code>String</code> com a data a ser convertida.
	 * @return um <code>Calendar</code> com a data ja convertida.
	 */
	public static Calendar converteDataParaCalendar(String data) {
		Calendar dataPesquisa = Calendar.getInstance();
		String aux[] = data.split(" ");
		
		int mes;
		//Obt�m o indice da data.
		for(mes = 0; mes < nomeMeses.length; mes++) if(aux[0].equalsIgnoreCase(nomeMeses[mes])) break;		
		dataPesquisa.set(Integer.parseInt(aux[1]), mes, Calendar.DAY_OF_MONTH);
		 
		return dataPesquisa;
	}
	
	/**Exibe uma mensagem ao usu�rio.
	 * @param componente <code>Component</code> localiza��o de onde a tela vai se posicionar.
	 * @param Mensagem <code>String</code> a mensagem que vai ser exibida.
	 * @param titulo <code>String</code> titulo da caixa de dialogo.
	 */
	public static void exibirMensagem(Component componente, String Mensagem, String titulo) {
		showMessageDialog(componente, Mensagem, titulo, INFORMATION_MESSAGE);
	}//exibirMensagem
	
	/**Exibe uma mensagem de erro ao usu�rio.
	 * @param componente <code>Component</code> localiza��o de onde a tela vai se posicionar.
	 * @param Mensagem <code>String</code> a mensagem que vai ser exibida.
	 * @param titulo <code>String</code> titulo da caixa de dialogo.
	 */
	public static void exibirMensagemErro(Component componente, String Mensagem, String titulo) {
		showMessageDialog(componente, Mensagem, titulo, ERROR_MESSAGE);
	}//exibirMensagemErro
	
	/**
	 * Compara duas String e verifica se s�o iguais. Caso seja retorna <code>true</code> se n�o retorna
	 * <code>false</code>. Ignora acentua��es.
	 * @param textoA <code>String</code> a ser comparada.
	 * @param textoB <code>String</code> a ser comparada.
	 * @return um <code>true</code> caso sejam iguais ou um <code>false</code> caso sejam diferentes.
	 */
	public static boolean comparaString(String textoA, String textoB) {
		 Collator collator = Collator.getInstance (new Locale("pt", "BR"));  
		 collator.setStrength(Collator.PRIMARY); // importante!  
		 return (collator.compare(textoA,textoB))== 0 ? true : false;
	}//comparaString()
	
	/**
	 * Recebe o valor da meta do usu�rio e a porcentagem e retorna o valor em reais.
	 * @param meta <code>double</code> com a meta de gastos do usu�rio.
	 * @param porcentagem <code>int</code> com a porcentagem definida pelo usu�rio.
	 * @return um <code>double</code> com o valor da meta.
	 */
	public static double valorSinalEmDinheiro(double meta, int porcentagem) {
		return meta * porcentagem / 100;
	}//valorSinalEmDinheiro
	
	/**Obt�m os meses sem repeti��o para preencherem a o comboBox de Despesas.
	 * @return um vetor de <code>String</code>.
	 */
	public static DefaultComboBoxModel<String> obterMesesComboBox(){
		//Obtem o numero de rendas mensais cadastradas.
		int numeroDespesas = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleDespesa().quantidadeDespesas();
		Map<String,String> mesesHash = new HashMap<>();
		String mes;
		for(int indice = 0; indice < numeroDespesas;indice++){
			mes = FuncaoAuxiliar.obtemMesAno(FuncaoAuxiliar.coverteDataParaString(GerenciamentoDeFinanca.
					getGerenciamentoFincanca().getControleDespesa().obtemDespesa(indice).getDataDespesa(),true));
				mesesHash.put(mes,mes);
		}
			
		Collection<String> aux = mesesHash.values();
		String[] meses = aux.toArray(new String[0]);
		
		aux.clear();
		mesesHash.clear();
		
		Arrays.sort(meses);
		
		DefaultComboBoxModel<String> periodos = new DefaultComboBoxModel<String>(meses);
		if(numeroDespesas == 0)
			periodos.insertElementAt("", 0);
		return periodos;
	}//Meses Despesa
	
	/**Transforma uma valor real que cont�m virgula para um valor real com ponto.
	 * @param valor <code>String</code> contedo o valor a ser transformado.
	 * @return um <code>String</code> com o novo valor.
	 */
	public static String converteVirgulaEmPonto(String valor){
		
		if(valor.indexOf(".") == -1 && valor.indexOf(",") == -1) return valor;
		
		StringTokenizer tokens = new StringTokenizer(valor, ",.");
		if(tokens.countTokens() >= 3) return "";
		
		String novoValor = tokens.nextToken() + "." + tokens.nextToken();
		while(tokens.hasMoreTokens())
			novoValor += tokens.nextToken();
		
		return novoValor;
	}
}//class FuncaoAuxiliar
