package tsi.too.samuelwagner.controle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tsi.too.samuelwagner.arquivo.ArquivoPlanejamentoMensal;
import tsi.too.samuelwagner.enumeracoes.CaminhoArquivo;
import tsi.too.samuelwagner.tipo.PlanejamentoMensal;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;

/**A classe <code>ControlePlanejamentoMensal</code> implementa os m�todos necessarios para utilizar as opera��es no <code>ArquivoPlanejamentoMensal</code>.
 * 
 * @author Samuel
 * @author Wagner
 */
public class ControlePlanejamentoMensal implements Controle{
	private ArquivoPlanejamentoMensal arquivoPlanejamentoMensal;
	
	/**
	 * Construtor default da classe <code>ControlePlanejamentoMensal</code>. Inicializa o arquivoPlanejamentoMensal
	 *  <code>ArquivoPlanejamentoMensal</code>.
	 */
	public ControlePlanejamentoMensal() {
		arquivoPlanejamentoMensal = new ArquivoPlanejamentoMensal();
	}
	
	/**Grava um objeto <code>PlanejamentoMensal</code> no arquivo bin�rio.
	 * 
	 * @param codigo <code>int</code> da despesa para o planejamento mensal.
	 * @param data <code>Calendar</code> contendo m�s e ano do planejamento.
	 */
	/*
	 * O m�todo abre o arquivo para grava��o, posiciona o ponteiro na ultima posi��o v�lida e grava o objeto.
	 * Em seguida o arquivo � fechado.
	 */
	public void gravarPlanejamentoMensal(int codigo, Calendar data){
		try {
			arquivoPlanejamentoMensal.openFile(CaminhoArquivo.PLANEJAMENTO_MENSAL.getCaminho());
			arquivoPlanejamentoMensal.setFilePointer(arquivoPlanejamentoMensal.recordQuantity());
			arquivoPlanejamentoMensal.writeObject(new PlanejamentoMensal(codigo,data));
			arquivoPlanejamentoMensal.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//gravarPlanejamento()
	
	/**
	 * Pesquisa um objeto <code>Planejamento Mensal</code> salvo no arquivo bin�rio.
	 * 
	 * @param codigo <code>int</code> referente a despesa procurada.
	 * @return indice <code>int</code> com a posi��o ou -1 caso n�o seja localizado o objeto.
	 */
	/*
	 * O m�todo abre o arquivo, pesquisa todos os objetos salvos, comparando ao par�metro recebido. Caso n�o localize 
	 * retorna 1, caso encontre, retorna a posi��o.
	 */
	public int pesquisaPlanejamentoMensal(int codigo) {
		try {
			arquivoPlanejamentoMensal.openFile(CaminhoArquivo.PLANEJAMENTO_MENSAL.getCaminho());
			for(int indice = 0; indice < arquivoPlanejamentoMensal.recordQuantity();indice++){
				arquivoPlanejamentoMensal.setFilePointer(indice);
				if(arquivoPlanejamentoMensal.readObject().getCodigoDespesa() == codigo){
					arquivoPlanejamentoMensal.closeFile();
					return indice;
				}
			}
			arquivoPlanejamentoMensal.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}//pesquisaPlanejamentoMensal() 
	
	/**
	 * Obt�m um objeto <code>PlanejamentoMensal</code> atr�ves do indice passado por refer�ncia.
	 * 
	 * @param indice <code>int</code> com a posi��o do arquivo.
	 * @return planejamentoMensal com um objeto <code>PlanejamentoMensal</code> salvo na posi��o indicada por par�metro. 
	 */
	/*
	 * O m�todo recebe o indice para uma posi��o no arquivo e verifica se esta posi��o � v�lida, caso seja retorna o objeto
	 * requisitado.
	 */
	public PlanejamentoMensal obtemPlanejamentoMensal(int indice) {
		PlanejamentoMensal planejamentoMensal = null;
		try {
			arquivoPlanejamentoMensal.openFile(CaminhoArquivo.PLANEJAMENTO_MENSAL.getCaminho());
			if(indice < 0 || indice > arquivoPlanejamentoMensal.recordQuantity())  {arquivoPlanejamentoMensal.closeFile(); return null;}
			arquivoPlanejamentoMensal.setFilePointer(indice);
			planejamentoMensal = arquivoPlanejamentoMensal.readObject();
			arquivoPlanejamentoMensal.closeFile();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return planejamentoMensal;
	}//obtemPlanejamentoMensal()
	
	/**
	 * Exclui um objeto <code>PlanejamentoMensal</code> cadastrado no arquivo.
	 * 
	 * @param indice <code>int</code> com a posi��o a ser exclu�da.
	 * @return <code>true</code> caso a exclus�o seja realizada e <code>false</code> caso n�o seja poss�vel excluir.
	 */
	/*
	 * O m�todo recebe o indice e verifica se este se refere a uma posi��o v�lida, caso se refira exclui o objeto
	 * no arquivo na posi��o solicitada.
	 */
	public boolean excluiPlanejamentoMensal(int indice){
			try {
				arquivoPlanejamentoMensal.openFile(CaminhoArquivo.PLANEJAMENTO_MENSAL.getCaminho());
				if(indice < 0 || indice > arquivoPlanejamentoMensal.recordQuantity()) {arquivoPlanejamentoMensal.closeFile(); return false;}
				arquivoPlanejamentoMensal.excludeFileRecord(indice);
				arquivoPlanejamentoMensal.closeFile();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
	}//excluiPlanejamentoMensal()
	
	/**
	 * Obt�m um <code>Integer[]</code> com todas as despesas cadastradas para o m�s passado por via par�metro.
	 * @param mesAno <code>String</code> com o m�s e ano do planejamento solicitado.
	 * @return <code>Integer[]</code>
	 */
	public Integer[] obterCodigosPlanejamentoMensal(Calendar mesAno) {
		List<Integer> codigos = new ArrayList<Integer>();
		
		try {
			arquivoPlanejamentoMensal.openFile(CaminhoArquivo.PLANEJAMENTO_MENSAL.getCaminho());
			for(int cont = 0; cont < arquivoPlanejamentoMensal.recordQuantity(); cont++){
				arquivoPlanejamentoMensal.setFilePointer(cont);
				PlanejamentoMensal planejamento = arquivoPlanejamentoMensal.readObject();
				if(FuncaoAuxiliar.coverteDataParaString(planejamento.getMesAnoPlanejamento(), false).equalsIgnoreCase(FuncaoAuxiliar.coverteDataParaString(mesAno, false)))
					codigos.add(planejamento.getCodigoDespesa());
			}
			arquivoPlanejamentoMensal.closeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(codigos.size() == 0) return null;
		
		return codigos.toArray(new Integer[0]);
	}//obterCodigosPlanejamentoMensal()
}//class ControlePlanejamentoMensal
