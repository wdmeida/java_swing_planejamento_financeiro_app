package tsi.too.samuelwagner.controle;

import java.io.FileNotFoundException;
import java.io.IOException;

import tsi.too.samuelwagner.arquivo.ArquivoRenda;
import tsi.too.samuelwagner.enumeracoes.CaminhoArquivo;
import tsi.too.samuelwagner.tipo.Renda;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;

/**A classe <code>ControleRenda</code> implementa os m�todos necessarios para utilizar as opera��es no <code>ArquivoRenda</code>.
 *  @author Samuel Gon�alves
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
	
	/**Grava um objeto <code>Renda</code> no arquivo bin�rio.
	 * 
	 * @param descricao <code>String</code> contendo a descri��o da renda.
	 * @param codigo <code>int</code> representando o c�digo da renda cadastrada.
	 */
	/*
	 * O m�todo abre o arquivo para grava��o, posiciona o ponteiro na ultima posi��o v�lida e grava o objeto.
	 * Em seguida o arquivo � fechado.
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
	 * Pesquisa um objeto <code>Renda</code> salvo no arquivo bin�rio.
	 * 
	 * @param descricao <code>String</code> referente a despesa procurada.
	 * @return indice <code>int</code> com a posi��o ou -1 caso n�o seja localizado o objeto.
	 */
	/*
	 * O m�todo abre o arquivo, pesquisa todos os objetos salvos, comparando ao par�metro recebido. Caso n�o localize 
	 * retorna 1, caso encontre, retorna a posi��o.
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
	 * Obt�m um objeto <code>Renda</code> atr�ves do indice passado por refer�ncia.
	 * 
	 * @param indice <code>int</code> com a posi��o do arquivo.
	 * @return renda com um objeto <code>Renda</code> salvo na posi��o indicada por par�metro. 
	 */
	/*
	 * O m�todo recebe o indice para uma posi��o no arquivo e verifica se esta posi��o � v�lida, caso seja retorna o objeto
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
	 * @param indice <code>int</code> com a posi��o a ser exclu�da.
	 * @return <code>true</code> caso a exclus�o seja realizada e <code>false</code> caso n�o seja poss�vel excluir.
	 */
	/*
	 * O m�todo recebe o indice e verifica se este se refere a uma posi��o v�lida, caso se refira exclui o objeto
	 * no arquivo na posi��o solicitada.
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
	
	/** Gera o codigo da Renda de acordo com os c�digos j� criados.
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
	
	/** informa o n�mero de rendas cadastradas.
	 * @return um <code>int</code> contendo o n�mero de rendas.
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
