package tsi.too.samuelwagner.controle;

import java.io.FileNotFoundException;
import java.io.IOException;

import tsi.too.samuelwagner.arquivo.ArquivoRenda;
import tsi.too.samuelwagner.enumeracoes.CaminhoArquivo;
import tsi.too.samuelwagner.tipo.Renda;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;

/**A classe <code>ControleRenda</code> implementa os métodos necessarios para utilizar as operações no <code>ArquivoRenda</code>.
 *  @author Samuel Gonçalves
 * @author Wagner Almeida
 *
 */
public class ControleRenda {
	private ArquivoRenda arquivoRenda;
	
	/**
	 * Construtor default da classe <code>ControleRenda</code>. Inicializa o arquivoRenda <code>arquivoRenda</code>.
	 */
	public ControleRenda() {
		arquivoRenda = new ArquivoRenda();
	}
	
	/**Grava um objeto <code>Renda</code> no arquivo binário.
	 * 
	 * @param descricao <code>String</code> contendo a descrição da renda.
	 * @param codigo <code>int</code> representando o código da renda cadastrada.
	 */
	/*
	 * O método abre o arquivo para gravação, posiciona o ponteiro na ultima posição válida e grava o objeto.
	 * Em seguida o arquivo é fechado.
	 */
	public void gravarRenda(String descricao, int codigo){
		try {
			arquivoRenda.openFile(CaminhoArquivo.RENDA.getCaminho());
			arquivoRenda.setFilePointer(arquivoRenda.recordQuantity());
			arquivoRenda.writeObject(new Renda(codigo, descricao));
			arquivoRenda.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//gravarRenda()
	
	/**
	 * Pesquisa um objeto <code>Renda</code> salvo no arquivo binário.
	 * 
	 * @param descricao <code>String</code> referente a despesa procurada.
	 * @return indice <code>int</code> com a posição ou -1 caso não seja localizado o objeto.
	 */
	/*
	 * O método abre o arquivo, pesquisa todos os objetos salvos, comparando ao parâmetro recebido. Caso não localize 
	 * retorna 1, caso encontre, retorna a posição.
	 */
	public int pesquisaRenda(String descricao) {
		try {
			arquivoRenda.openFile(CaminhoArquivo.RENDA.getCaminho());
			for(int indice = 0; indice < arquivoRenda.recordQuantity();indice++){
				arquivoRenda.setFilePointer(indice);
				if(FuncaoAuxiliar.comparaString(arquivoRenda.readObject().getDescricao(),descricao)){
					arquivoRenda.closeFile();
					return indice;
				}
			}
			arquivoRenda.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}//pesquisaRenda() 
	
	/**
	 * Obtém um objeto <code>Renda</code> atráves do indice passado por referência.
	 * 
	 * @param indice <code>int</code> com a posição do arquivo.
	 * @return renda com um objeto <code>Renda</code> salvo na posição indicada por parâmetro. 
	 */
	/*
	 * O método recebe o indice para uma posição no arquivo e verifica se esta posição é válida, caso seja retorna o objeto
	 * requisitado.
	 */
	public Renda obtemRenda(int indice) {
		Renda renda = null;
		try {
			arquivoRenda.openFile(CaminhoArquivo.RENDA.getCaminho());
			if(indice < 0 || indice > arquivoRenda.recordQuantity())  {arquivoRenda.closeFile(); return null;}
			arquivoRenda.setFilePointer(indice);
			renda = arquivoRenda.readObject();
			arquivoRenda.closeFile();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return renda;
	}//obtemRenda()
	
	/**
	 * Exclui um objeto <code>Renda</code> cadastrado no arquivo.
	 * 
	 * @param indice <code>int</code> com a posição a ser excluída.
	 * @return <code>true</code> caso a exclusão seja realizada e <code>false</code> caso não seja possível excluir.
	 */
	/*
	 * O método recebe o indice e verifica se este se refere a uma posição válida, caso se refira exclui o objeto
	 * no arquivo na posição solicitada.
	 */
	public boolean excluiRenda(int indice){
			try {
				arquivoRenda.openFile(CaminhoArquivo.RENDA.getCaminho());
				if(indice < 0 || indice > arquivoRenda.recordQuantity())  {arquivoRenda.closeFile(); return false;}
				arquivoRenda.excludeFileRecord(indice);
				arquivoRenda.closeFile();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
	}//excluirRenda()
	
	/** Gera o codigo da Renda de acordo com os códigos já criados.
	 * @return um <code>int</code> contendo o codigo.
	 */
	public int geraCodigo() {
		int codigo = 1;
		try {
			arquivoRenda.openFile(CaminhoArquivo.RENDA.getCaminho());
			for(int indice = 0; indice < arquivoRenda.recordQuantity();indice++){
				arquivoRenda.setFilePointer(indice);
				Renda renda = arquivoRenda.readObject();
				if(renda.getCodigo() >= codigo)
					codigo = renda.getCodigo() + 1;
			}
			arquivoRenda.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return codigo;
	}
	
	/** informa o número de rendas cadastradas.
	 * @return um <code>int</code> contendo o número de rendas.
	 */
	public int retornaNumeroDeRendas(){
		try {
			arquivoRenda.openFile(CaminhoArquivo.RENDA.getCaminho());
			int codigo = (int) arquivoRenda.recordQuantity();
			arquivoRenda.closeFile();
			return codigo;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}//class ControleRenda
