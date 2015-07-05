package tsi.too.samuelwagner.controle;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tsi.too.samuelwagner.arquivo.ArquivoCategoria;
import tsi.too.samuelwagner.enumeracoes.CaminhoArquivo;
import tsi.too.samuelwagner.enumeracoes.RotuloJanelaCategoria;
import tsi.too.samuelwagner.gui.IgCadastrarCategoria;
import tsi.too.samuelwagner.tipo.Categoria;
import tsi.too.samuelwagner.tipo.Compara;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;
import tsi.too.samuelwagner.validacoes.Validador;

/**A classe <code>ControleCategoria</code> implementa os m�todos necessarios para utilizar as opera��es no <code>ArquivoCategoria</code>.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 *
 */
public class ControleCategoria  {
	private ArquivoCategoria arquivoCategoria;
	private IgCadastrarCategoria cadastrarCategoria;
	
	/**
	 * Construtor default da classe <code>ControleCategoria</code>. Inicializa
	 * o arquivoCategoria <code>ArquivoCategoria</code>.
	 */
	public ControleCategoria() {
		arquivoCategoria = new ArquivoCategoria();
	}
	
	/**Grava um objeto <code>Categoria</code> no arquivo bin�rio.
	 * @param descricao <code>String</code> da categoria.
	 * @param codigo <code>int</code> da categoria
	 */
	/*
	 * O m�todo abre o arquivo para grava��o, posiciona o ponteiro na ultima posi��o v�lida e grava o objeto.
	 * Em seguida o arquivo � fechado.
	 */
	public void gravarCategoria(String descricao,int codigo){
		try {
			arquivoCategoria.openFile(CaminhoArquivo.CATEGORIA.getCaminho());
			arquivoCategoria.setFilePointer(arquivoCategoria.recordQuantity());
			arquivoCategoria.writeObject(new Categoria(codigo,descricao));
			arquivoCategoria.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//gravarCategoria()
	
	/**
	 * Pesquisa um objeto <code>Categoria</code> salvo no arquivo bin�rio.
	 * 
	 * @param descricao <code>String</code> referente a despesa procurada.
	 * @return indice <code>int</code> com a posi��o ou -1 caso n�o seja localizado o objeto.
	 */
	/*
	 * O m�todo abre o arquivo, pesquisa todos os objetos salvos, comparando ao par�metro recebido. Caso n�o localize 
	 * retorna 1, caso encontre, retorna a posi��o.
	 */
	public int pesquisaCategoria(String descricao) {
		try {
			arquivoCategoria.openFile(CaminhoArquivo.CATEGORIA.getCaminho());
			for(int indice = 0; indice < arquivoCategoria.recordQuantity();indice++){
				arquivoCategoria.setFilePointer(indice);
				if(FuncaoAuxiliar.comparaString(arquivoCategoria.readObject().getDescricao(),descricao)){
					arquivoCategoria.closeFile();
					return indice;
				}
			}
			arquivoCategoria.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}//pesquisaCategoria() 
	
	/**
	 * Pesquisa o nome de uma categoria salva no arquivo bin�rio.
	 * 
	 * @param codigo <code>int</code> da categoria.
	 * @return um <code>String</code> com o nome da categoria.
	 */
	/*
	 * O m�todo abre o arquivo, pesquisa todos os objetos salvos, comparando ao par�metro recebido. Caso n�o localize 
	 * retorna 1, caso encontre, retorna a posi��o.
	 */
	public String pesquisaCategoria(int codigo) {
		try {
			arquivoCategoria.openFile(CaminhoArquivo.CATEGORIA.getCaminho());
			for(int indice = 0; indice < arquivoCategoria.recordQuantity();indice++){
				arquivoCategoria.setFilePointer(indice);
				Categoria categoria = arquivoCategoria.readObject();
				if(categoria.getCodigo() == codigo){
					arquivoCategoria.closeFile();
					return categoria.getDescricao();
				}
			}
			arquivoCategoria.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}//pesquisaCategoria() 
	
	/**
	 * Obt�m um objeto <code>Categoria</code> atr�ves do indice passado por refer�ncia.
	 * 
	 * @param indice <code>int</code> com a posi��o do arquivo.
	 * @return categoria com um objeto <code>Categoria</code> salvo na posi��o indicada por par�metro. 
	 */
	/*
	 * O m�todo recebe o indice para uma posi��o no arquivo e verifica se esta posi��o � v�lida, caso seja retorna o objeto
	 * requisitado.
	 */
	public Categoria obtemCategoria(int indice) {
		Categoria categoria = null;
		try {
			arquivoCategoria.openFile(CaminhoArquivo.CATEGORIA.getCaminho());
			if(indice < 0 || indice > arquivoCategoria.recordQuantity()) {arquivoCategoria.closeFile(); return null;}
			arquivoCategoria.setFilePointer(indice);
			categoria = arquivoCategoria.readObject();
			arquivoCategoria.closeFile();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return categoria;
	}//obtemCategoria()
	
	/**
	 * Exclui um objeto <code>Categoria</code> cadastrado no arquivo.
	 * 
	 * @param indice <code>int</code> com a posi��o a ser exclu�da.
	 * @return <code>true</code> caso a exclus�o seja realizada e <code>false</code> caso n�o seja poss�vel excluir.
	 */
	/*
	 * O m�todo recebe o indice e verifica se este se refere a uma posi��o v�lida, caso se refira exclui o objeto
	 * no arquivo na posi��o solicitada.
	 */
	public boolean excluiCategoria(int indice){
			try {
				arquivoCategoria.openFile(CaminhoArquivo.CATEGORIA.getCaminho());
				if(indice < 0 || indice > arquivoCategoria.recordQuantity())  {arquivoCategoria.closeFile(); return false;}
				arquivoCategoria.excludeFileRecord(indice);
				arquivoCategoria.closeFile();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
	}//excluiCategoria()
	
	/**
	 * Retorna o pr�ximo c�digo dispon�vel.
	 * 
	 * @return codigo <code>int</code>
	 */
	public int gerarCodigo() {
		int codigo = 1;
		try {
			arquivoCategoria.openFile(CaminhoArquivo.CATEGORIA.getCaminho());
			if(arquivoCategoria.recordQuantity() == 0)  {arquivoCategoria.closeFile(); return codigo;}
			for(int indice = 0; indice < arquivoCategoria.recordQuantity(); indice++){
				arquivoCategoria.setFilePointer(indice);
				Categoria categoria = arquivoCategoria.readObject();
				if(codigo < categoria.getCodigo())
					codigo = categoria.getCodigo();
			}
			arquivoCategoria.closeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return codigo + 1;
	}//gerarCodigo()
	
	/**
	 * Cadastrar uma nova categoria no arquivo.
	 * @param nome <code>String</code> da categoria a ser salva.
	 * @return boolean <code>false</code> caso n�o seja poss�vel cadastrar e <code>true</code> caso o cadastro seja realizado com 
	 * sucesso.
	 */
	public boolean cadastrarCategoria(String nome) {
		//Verifica se o campo est� vazio.
		if(Validador.validaCampoVazio(nome)){
			showMessageDialog(cadastrarCategoria, RotuloJanelaCategoria.VAZIO.getDescricao(), RotuloJanelaCategoria.TITULO.getDescricao(), ERROR_MESSAGE);
			return false;
		}
		else
		{
			//Pesquisa na categoria para saber se a categoria n�o est� cadastrada.
			int indice = pesquisaCategoria(nome);
			if(indice != -1){
				showMessageDialog(cadastrarCategoria, RotuloJanelaCategoria.CADASTRADAS.getDescricao(), RotuloJanelaCategoria.TITULO.getDescricao(), ERROR_MESSAGE);
				return false;
			}
			else
			{
				//Cadastra os dados no arquivo.
				gravarCategoria(nome, gerarCodigo());
				showMessageDialog(cadastrarCategoria, RotuloJanelaCategoria.SALVO.getDescricao(), RotuloJanelaCategoria.TITULO.getDescricao(), INFORMATION_MESSAGE);
				return true;
			}
		}
	}//cadastrarCategoria()
	
	/**
	 * Obt�m uma refer�ncia de um array de <code>String[]</code> de todas as categorias cadastradas.
	 * @return nomesCategorias <code>String[]</code>
	 */
	public String[] obterNomesCategorias(){
		List<String> nomesCategorias = new ArrayList<String>();
		try {
			arquivoCategoria.openFile(CaminhoArquivo.CATEGORIA.getCaminho());
			if(arquivoCategoria.recordQuantity() == 0)  {arquivoCategoria.closeFile(); return null;}
			for(int indice = 0; indice < arquivoCategoria.recordQuantity(); indice++){
				arquivoCategoria.setFilePointer(indice);
				Categoria categoria = arquivoCategoria.readObject();
				nomesCategorias.add(categoria.getDescricao());
			}
			arquivoCategoria.closeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//ordena o nome das categorias.
		Collections.sort(nomesCategorias,new Compara());
		
		return nomesCategorias.toArray(new String[0]);
	}//obterNomesCategorias()
	
	/**
	 * Atribui uma refer�ncia de um objeto <code>IgCadastrarCategoria</code> para acessar os valores dos campos.
	 * @param cadastrarCategoria <code>IgCadastrarCategoria</code> com a refer�ncia da janela de cadastro.
	 */
	public void setCadastrarCategoria(IgCadastrarCategoria cadastrarCategoria) {
		this.cadastrarCategoria = cadastrarCategoria;
	}//setCadastrarCategoria()
}//class ControleCategoria