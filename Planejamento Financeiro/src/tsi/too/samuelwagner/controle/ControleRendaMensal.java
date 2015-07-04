package tsi.too.samuelwagner.controle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import tsi.too.samuelwagner.arquivo.ArquivoRendaMensal;
import tsi.too.samuelwagner.enumeracoes.CaminhoArquivo;
import tsi.too.samuelwagner.tipo.RendaMensal;

/**A classe <code>ControleRendaMensal</code> implementa os métodos necessarios para utilizar as operações no <code>ArquivoRendaMensal</code>.
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
	
	/**Grava um objeto <code>RendaMensal</code> no arquivo binário.
	 * @param descricao <code>String</code> da renda.
	 * @param codigo <code>int</code> da renda mensal.
	 * @param data <code>Calendar</code> em que renda mensal foi recebida.
	 * @param valor <code>double</code> da renda.
	 */
	/*
	 * O método abre o arquivo para gravação, posiciona o ponteiro na ultima posição válida e grava o objeto.
	 * Em seguida o arquivo é fechado.
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
	 * Pesquisa um objeto <code>RendaMensal</code> salvo no arquivo binário.
	 * 
	 * @param codigo <code>int</code> referente a despesa procurada.
	 * @return indice <code>int</code> com a posição ou -1 caso não seja localizado o objeto.
	 */
	/*
	 * O método abre o arquivo, pesquisa todos os objetos salvos, comparando ao parâmetro recebido. Caso não localize 
	 * retorna 1, caso encontre, retorna a posição.
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
	 * Obtém um objeto <code>RendaMensal</code> atráves do indice passado por referência.
	 * 
	 * @param indice <code>int</code> com a posição do arquivo.
	 * @return rendaMensal com um objeto <code>RendaMensal</code> salvo na posição indicada por parâmetro. 
	 */
	/*
	 * O método recebe o indice para uma posição no arquivo e verifica se esta posição é válida, caso seja retorna o objeto
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
	 * @param indice <code>int</code> com a posição a ser excluída.
	 * @return <code>true</code> caso a exclusão seja realizada e <code>false</code> caso não seja possível excluir.
	 */
	/*
	 * O método recebe o indice e verifica se este se refere a uma posição válida, caso se refira exclui o objeto
	 * no arquivo na posição solicitada.
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
	 * Retorna o número de rendas cadastradas.
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
