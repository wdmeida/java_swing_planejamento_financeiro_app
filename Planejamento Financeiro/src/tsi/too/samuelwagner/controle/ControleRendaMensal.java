package tsi.too.samuelwagner.controle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import tsi.too.samuelwagner.arquivo.ArquivoRendaMensal;
import tsi.too.samuelwagner.enumeracoes.CaminhoArquivo;
import tsi.too.samuelwagner.tipo.RendaMensal;

/**A classe <code>ControleRendaMensal</code> implementa os m�todos necessarios para utilizar as opera��es no <code>ArquivoRendaMensal</code>.
 * 
 * @author Samuel
 * @author Wagner
 */
public class ControleRendaMensal implements Controle{
	private ArquivoRendaMensal arquivoRendaMensal;
	
	/**
	 * Construtor default da classe <code>ControleRendaMensal</code>. Inicializa o <code>ArquivoRendaMensal</code>.
	 */
	public ControleRendaMensal() {
		arquivoRendaMensal = new ArquivoRendaMensal();
	}
	
	/**Grava um objeto <code>RendaMensal</code> no arquivo bin�rio.
	 * @param descricao <code>String</code> da renda.
	 * @param codigo <code>int</code> da renda mensal.
	 * @param data <code>Calendar</code> em que renda mensal foi recebida.
	 * @param valor <code>double</code> da renda.
	 */
	/*
	 * O m�todo abre o arquivo para grava��o, posiciona o ponteiro na ultima posi��o v�lida e grava o objeto.
	 * Em seguida o arquivo � fechado.
	 */
	public void gravarRendaMensal(String descricao,int codigo,Calendar data, double valor){
		try {
			arquivoRendaMensal.openFile(CaminhoArquivo.RENDA_MENSAL.getCaminho());
			arquivoRendaMensal.setFilePointer(arquivoRendaMensal.recordQuantity());
			arquivoRendaMensal.writeObject(new RendaMensal(descricao,codigo,data,valor));
			arquivoRendaMensal.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//gravarRendaMensal()
	
	/**
	 * Pesquisa um objeto <code>RendaMensal</code> salvo no arquivo bin�rio.
	 * 
	 * @param codigo <code>int</code> referente a despesa procurada.
	 * @return indice <code>int</code> com a posi��o ou -1 caso n�o seja localizado o objeto.
	 */
	/*
	 * O m�todo abre o arquivo, pesquisa todos os objetos salvos, comparando ao par�metro recebido. Caso n�o localize 
	 * retorna 1, caso encontre, retorna a posi��o.
	 */
	public int pesquisaRendaMensal(int codigo) {
		try {
			arquivoRendaMensal.openFile(CaminhoArquivo.RENDA_MENSAL.getCaminho());
			for(int indice = 0; indice < arquivoRendaMensal.recordQuantity();indice++){
				arquivoRendaMensal.setFilePointer(indice);
				if(arquivoRendaMensal.readObject().getCodigo() == codigo){
					arquivoRendaMensal.closeFile();
					return indice;
				}
			}
			arquivoRendaMensal.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}//pesquisaRendaMensal() 
	
	/**
	 * Obt�m um objeto <code>RendaMensal</code> atr�ves do indice passado por refer�ncia.
	 * 
	 * @param indice <code>int</code> com a posi��o do arquivo.
	 * @return rendaMensal com um objeto <code>RendaMensal</code> salvo na posi��o indicada por par�metro. 
	 */
	/*
	 * O m�todo recebe o indice para uma posi��o no arquivo e verifica se esta posi��o � v�lida, caso seja retorna o objeto
	 * requisitado.
	 */
	public RendaMensal obtemRendaMensal(int indice) {
		RendaMensal rendaMensal = null;
		try {
			arquivoRendaMensal.openFile(CaminhoArquivo.RENDA_MENSAL.getCaminho());
			if(indice < 0 || indice > arquivoRendaMensal.recordQuantity())  {arquivoRendaMensal.closeFile(); return null;}
			arquivoRendaMensal.setFilePointer(indice);
			rendaMensal = arquivoRendaMensal.readObject();
			arquivoRendaMensal.closeFile();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rendaMensal;
	}//obtemRendaMensal()
	
	/**
	 * Exclui um objeto <code>RendaMensal</code> cadastrado no arquivo.
	 * 
	 * @param indice <code>int</code> com a posi��o a ser exclu�da.
	 * @return <code>true</code> caso a exclus�o seja realizada e <code>false</code> caso n�o seja poss�vel excluir.
	 */
	/*
	 * O m�todo recebe o indice e verifica se este se refere a uma posi��o v�lida, caso se refira exclui o objeto
	 * no arquivo na posi��o solicitada.
	 */
	public boolean excluiRendaMensal(int indice){
			try {
				arquivoRendaMensal.openFile(CaminhoArquivo.RENDA_MENSAL.getCaminho());
				if(indice < 0 || indice > arquivoRendaMensal.recordQuantity())  {arquivoRendaMensal.closeFile(); return false;}
				arquivoRendaMensal.excludeFileRecord(indice);
				arquivoRendaMensal.closeFile();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
	}//excluiRendaMensal()
	
	/**
	 * Retorna o n�mero de rendas cadastradas.
	 * @return numeroRenda <code>int</code>
	 */
	public int numerosDeRendas() {
		int numeroRenda;
		try {
			arquivoRendaMensal.openFile(CaminhoArquivo.RENDA_MENSAL.getCaminho());
			numeroRenda = (int) arquivoRendaMensal.recordQuantity();
			arquivoRendaMensal.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return numeroRenda;
	}
	
}//class ControleRendaMensal
