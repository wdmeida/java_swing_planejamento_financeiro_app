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
 * A classe <code>FuncaoAuxiliar</code>
 * @author Samuel
 * @author Wagner
 */
public class FuncaoAuxiliar {
	private static String nomeMeses[] = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho",
			"Agosto","Setembro","Outubro","Novembro","Desembro"};
	
	/** Converte um <code>Calendar</code> para <code>String</code>.
	 * @param data <code>Calendar</code>.
	 * @param completa <code>boolean</code>.
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
	
	/**Obtém mês e ano de uma data.
	 * @param diaMesAno <code>String</code> contendo o dia mes e ano;
	 * @return um <code>String</code> com o Mês e o Ano.
	 */
	public static String obtemMesAno(String diaMesAno){
		StringTokenizer tokens = new StringTokenizer(diaMesAno, "/");
		tokens.nextToken();
		String mes = tokens.nextToken(),
			   ano = tokens.nextToken();
		return nomeMeses[Integer.parseInt(mes)-1] +" "+ano;
	}//obtemMesAno
	
	/**
	 * Converte a uma data em <code>String</code> para <code>Calendar</code>.
	 * @param data <code>String</code> com a data a ser convertida.
	 * @return um <code>Calendar</code> com a data ja convertida.
	 */
	public static Calendar converteDataParaCalendar(String data) {
		Calendar dataPesquisa = Calendar.getInstance();
		String aux[] = data.split(" ");
		
		int mes;
		//Obtém o indice da data.
		for(mes = 0; mes < nomeMeses.length; mes++) if(aux[0].equalsIgnoreCase(nomeMeses[mes])) break;		
		dataPesquisa.set(Integer.parseInt(aux[1]), mes, Calendar.DAY_OF_MONTH);
		
		return dataPesquisa;
	}
	
	/**
	 * @param componente
	 * @param Mensagem
	 * @param titulo
	 */
	public static void exibirMensagem(Component componente, String Mensagem, String titulo) {
		showMessageDialog(componente, Mensagem, titulo, INFORMATION_MESSAGE);
	}//exibirMensagem
	
	/**
	 * @param componente
	 * @param Mensagem
	 * @param titulo
	 */
	public static void exibirMensagemErro(Component componente, String Mensagem, String titulo) {
		showMessageDialog(componente, Mensagem, titulo, ERROR_MESSAGE);
	}//exibirMensagemErro
	
	/**
	 * Compara duas String e verifica se são iguais. Caso seja retorna true. Ignora acentuações.
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
	 * Recebe o valor da meta do usuário e a porcentagem e retorna o valor em reais da porcentagem
	 * relativa a meta.
	 * @param meta <code>double</code> com a meta de gastos do usuário.
	 * @param porcentagem <code>int</code> com a porcentagem definida pelo usuário.
	 * @return um <code>double</code> com o valor da meta.
	 */
	public static double valorSinalEmDinheiro(double meta, int porcentagem) {
		return meta * porcentagem / 100;
	}//valorSinalEmDinheiro
	
	/**Obtém os meses sem repetição para preencherem a o comboBox de Despesas.
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
		return periodos;
	}//Meses Despesa
}//class FuncaoAuxiliar
