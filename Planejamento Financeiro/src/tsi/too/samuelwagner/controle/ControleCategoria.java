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

/**A classe <code>ControleCategoria</code> implementa os métodos necessarios para utilizar as operações no <code>ArquivoCategoria</code>.
 * @author Samuel Gonçalves
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
	
	/**Grava um objeto <code>Categoria</code> no arquivo binário.
	 * @param descricao <code>String</code> da categoria.
	 * @param codigo <code>int</code> da categoria
	 */
	/*
	 * O método abre o arquivo para gravação, posiciona o ponteiro na ultima posição válida e grava o objeto.
	 * Em seguida o arquivo é fechado.
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
	 * Pesquisa um objeto <code>Categoria</code> salvo no arquivo binário.
	 * 
	 * @param descricao <code>String</code> referente a despesa procurada.
	 * @return indice <code>int</code> com a posição ou -1 caso não seja localizado o objeto.
	 */
	/*
	 * O método abre o arquivo, pesquisa todos os objetos salvos, comparando ao parâmetro recebido. Caso não localize 
	 * retorna 1, caso encontre, retorna a posição.
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
	 * Pesquisa o nome de uma categoria salva no arquivo binário.
	 * 
	 * @param codigo <code>int</code> da categoria.
	 * @return um <code>String</code> com o nome da categoria.
	 */
	/*
	 * O método abre o arquivo, pesquisa todos os objetos salvos, comparando ao parâmetro recebido. Caso não localize 
	 * retorna 1, caso encontre, retorna a posição.
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
	 * Obtém um objeto <code>Categoria</code> atráves do indice passado por referência.
	 * 
	 * @param indice <code>int</code> com a posição do arquivo.
	 * @return categoria com um objeto <code>Categoria</code> salvo na posição indicada por parâmetro. 
	 */
	/*
	 * O método recebe o indice para uma posição no arquivo e verifica se esta posição é válida, caso seja retorna o objeto
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
	 * @param indice <code>int</code> com a posição a ser excluída.
	 * @return <code>true</code> caso a exclusão seja realizada e <code>false</code> caso não seja possível excluir.
	 */
	/*
	 * O método recebe o indice e verifica se este se refere a uma posição válida, caso se refira exclui o objeto
	 * no arquivo na posição solicitada.
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
	 * Retorna o próximo código disponível.
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
	 * @return boolean <code>false</code> caso não seja possível cadastrar e <code>true</code> caso o cadastro seja realizado com 
	 * sucesso.
	 */
	public boolean cadastrarCategoria(String nome) {
		//Verifica se o campo está vazio.
		if(Validador.validaCampoVazio(nome)){
			showMessageDialog(cadastrarCategoria, RotuloJanelaCategoria.VAZIO.getDescricao(), RotuloJanelaCategoria.TITULO.getDescricao(), ERROR_MESSAGE);
			return false;
		}
		else
		{
			//Pesquisa na categoria para saber se a categoria não está cadastrada.
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
	 * Obtém uma referência de um array de <code>String[]</code> de todas as categorias cadastradas.
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
	 * Atribui uma referência de um objeto <code>IgCadastrarCategoria</code> para acessar os valores dos campos.
	 * @param cadastrarCategoria <code>IgCadastrarCategoria</code> com a referência da janela de cadastro.
	 */
	public void setCadastrarCategoria(IgCadastrarCategoria cadastrarCategoria) {
		this.cadastrarCategoria = cadastrarCategoria;
	}//setCadastrarCategoria()
}//class ControleCategoria