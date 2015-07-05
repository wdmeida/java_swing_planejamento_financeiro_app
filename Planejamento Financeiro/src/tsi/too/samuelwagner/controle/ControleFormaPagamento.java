package tsi.too.samuelwagner.controle;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tsi.too.samuelwagner.arquivo.ArquivoFormaPagamento;
import tsi.too.samuelwagner.enumeracoes.CaminhoArquivo;
import tsi.too.samuelwagner.gui.IgCadastrarDespesas;
import tsi.too.samuelwagner.tipo.Compara;
import tsi.too.samuelwagner.tipo.FormaPagamento;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;
import tsi.too.samuelwagner.validacoes.Validador;

/**A classe <code>ControleFormaPagamento</code> implementa os métodos necessarios para utilizar as operações no <code>ArquivoFormaPagamento</code>.
 *  @author Samuel Gonçalves
 * @author Wagner Almeida
 *
 */
public class ControleFormaPagamento {
	private ArquivoFormaPagamento arquivoFormaPagamento;
	
	/**
	 * Construtor default da classe <code>ControleFormaPagamento</code>. Inicializa
	 * o arquivoFormaPagamento <code>ArquivoFormaPagamento</code>.
	 */
	public ControleFormaPagamento() {
		arquivoFormaPagamento = new ArquivoFormaPagamento();
	}
	
	/**Grava um objeto <code>FormaPagamento</code> no arquivo binário.
	 * @param descricao <code>String</code> da forma de pagamento.
	 * @param codigo <code>int</code> da forma de pagamento
	 */
	/*
	 * O método abre o arquivo para gravação, posiciona o ponteiro na ultima posição válida e grava o objeto.
	 * Em seguida o arquivo é fechado.
	 */
	public void gravarFormaPagamento(String descricao, int codigo){
		try {
			arquivoFormaPagamento.openFile(CaminhoArquivo.FORMA_PAGAMENTO.getCaminho());
			arquivoFormaPagamento.setFilePointer(arquivoFormaPagamento.recordQuantity());
			arquivoFormaPagamento.writeObject(new FormaPagamento(codigo, descricao));
			arquivoFormaPagamento.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//gravarFormaPagamento()
	
	/**
	 * Pesquisa um objeto <code>FormaPagamento</code> salvo no arquivo binário.
	 * 
	 * @param descricao <code>String</code> referente a despesa procurada.
	 * @return indice <code>int</code> com a posição ou -1 caso não seja localizado o objeto.
	 */
	/*
	 * O método abre o arquivo, pesquisa todos os objetos salvos, comparando ao parâmetro recebido. Caso não localize 
	 * retorna 1, caso encontre, retorna a posição.
	 */
	public int pesquisaFormaPagamento(String descricao) {
		try {
			arquivoFormaPagamento.openFile(CaminhoArquivo.FORMA_PAGAMENTO.getCaminho());
			for(int indice = 0; indice < arquivoFormaPagamento.recordQuantity();indice++){
				arquivoFormaPagamento.setFilePointer(indice);
				if(FuncaoAuxiliar.comparaString(arquivoFormaPagamento.readObject().getDescricao(),descricao)){
					arquivoFormaPagamento.closeFile();
					return indice;
				}
			}
			arquivoFormaPagamento.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}//pesquisaFormaPagamento()
	
	/**
	 * Pesquisa a forma de pagamento salva no arquivo binário.
	 * 
	 * @param codigo <code>int</code> da forma de pagamento.
	 * @return um <code>String</code> com o nome da forma de pagamento.
	 */
	/*
	 * O método abre o arquivo, pesquisa todos os objetos salvos, comparando ao parâmetro recebido. Caso não localize 
	 * retorna 1, caso encontre, retorna a posição.
	 */
	public String pesquisaFormaPagamento(int codigo) {
		try {
			arquivoFormaPagamento.openFile(CaminhoArquivo.FORMA_PAGAMENTO.getCaminho());
			for(int indice = 0; indice < arquivoFormaPagamento.recordQuantity();indice++){
				arquivoFormaPagamento.setFilePointer(indice);
				FormaPagamento formaPagemento = arquivoFormaPagamento.readObject();
				if(formaPagemento.getCodigo() == codigo){
					arquivoFormaPagamento.closeFile();
					return formaPagemento.getDescricao();
				}
			}
			arquivoFormaPagamento.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}//pesquisaCategoria() 
	
	/**
	 * Obtém um objeto <code>FormaPagamento</code> atráves do indice passado por referência.
	 * 
	 * @param indice <code>int</code> com a posição do arquivo.
	 * @return formaPagamento com um objeto <code>FormaPagamento</code> salvo na posição indicada por parâmetro. 
	 */
	/*
	 * O método recebe o indice para uma posição no arquivo e verifica se esta posição é válida, caso seja retorna o objeto
	 * requisitado.
	 */
	public FormaPagamento obtemFormaPagamento(int indice) {
		FormaPagamento formaPagamento = null;
		try {
			arquivoFormaPagamento.openFile(CaminhoArquivo.FORMA_PAGAMENTO.getCaminho());
			if(indice < 0 || indice > arquivoFormaPagamento.recordQuantity()) {arquivoFormaPagamento.closeFile(); return null;}
			arquivoFormaPagamento.setFilePointer(indice);
			formaPagamento = arquivoFormaPagamento.readObject();
			arquivoFormaPagamento.closeFile();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return formaPagamento;
	}//obtemFormaPagamento()
	
	/**
	 * Exclui um objeto <code>FormaPagamento</code> cadastrado no arquivo.
	 * 
	 * @param indice <code>int</code> com a posição a ser excluída.
	 * @return <code>true</code> caso a exclusão seja realizada e <code>false</code> caso não seja possível excluir.
	 */
	/*
	 * O método recebe o indice e verifica se este se refere a uma posição válida, caso se refira exclui o objeto
	 * no arquivo na posição solicitada.
	 */
	public boolean excluiFormaPagamento(int indice){
			try {
				arquivoFormaPagamento.openFile(CaminhoArquivo.FORMA_PAGAMENTO.getCaminho());
				if(indice < 0 || indice > arquivoFormaPagamento.recordQuantity()) {arquivoFormaPagamento.closeFile(); return false;}
				arquivoFormaPagamento.excludeFileRecord(indice);
				arquivoFormaPagamento.closeFile();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
	}//excluiFormaPagamento()
	
	/** Gera o codigo da Renda de acordo com os códigos já criados.
	 * @return um <code>int</code> contendo o codigo.
	 */
	public int gerarCodigo() {
		int codigo = 1;
		try {
			arquivoFormaPagamento.openFile(CaminhoArquivo.FORMA_PAGAMENTO.getCaminho());
			if(arquivoFormaPagamento.recordQuantity() == 0) {arquivoFormaPagamento.closeFile(); return codigo;}
			for(int indice = 0; indice < arquivoFormaPagamento.recordQuantity();indice++){
				arquivoFormaPagamento.setFilePointer(indice);
				FormaPagamento formaPagamento = arquivoFormaPagamento.readObject();
				if(formaPagamento.getCodigo() > codigo)
					codigo = formaPagamento.getCodigo();
			}
			arquivoFormaPagamento.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return codigo + 1;
	}//geraCodigo()
	
	/**
	 * Obtém uma representação um array de <code>String</code> das formas de pagamento cadastradas.
	 * @return nomes <code>String[]</code>
	 */
	public String[] obterFormasPagamento(){
		List<String> nomes = new ArrayList<String>();
		try {
			arquivoFormaPagamento.openFile(CaminhoArquivo.FORMA_PAGAMENTO.getCaminho());
			if(arquivoFormaPagamento.recordQuantity() == 0) {arquivoFormaPagamento.closeFile(); return null;}
			for(int indice = 0; indice < arquivoFormaPagamento.recordQuantity(); indice++){
				arquivoFormaPagamento.setFilePointer(indice);
				FormaPagamento formaPagamento = arquivoFormaPagamento.readObject();
				nomes.add(formaPagamento.getDescricao());
			}//for()
			arquivoFormaPagamento.closeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Ordena o array.
		Collections.sort(nomes, new Compara());
		
		//Retorna uma representação em String.
		return nomes.toArray(new String[0]);
	}//obterFormaPagamento()
	
	/** Cadastra a forma de Pagamento no arquivo.
	 * @param cadastrarDespesa <code>IgCadastrarDespesa</code>.
	 * @param formaPagamento <code>String</code>.
	 * @return um <code>boolean</code> informando se foi possivel cadastra.
	 */
	public boolean cadastrarFormaPagamento(IgCadastrarDespesas cadastrarDespesa, String formaPagamento) {
		if(Validador.validaCampoVazio(formaPagamento)){
			showMessageDialog(cadastrarDespesa, "A forma de pagamento não pode ser vazia.", "Cadastrar Forma de Pagamento", ERROR_MESSAGE);
			return false;
		}
		else
			if(pesquisaFormaPagamento(formaPagamento) != -1){
				showMessageDialog(cadastrarDespesa, "A forma de pagamento já cadastrada no sistema.", "Cadastrar Forma de Pagamento", ERROR_MESSAGE);
				return false;
			}
			else
				gravarFormaPagamento(formaPagamento, gerarCodigo());
		return true;
	}//cadastrarFormaPagamento()
}//class FormaPagamento
