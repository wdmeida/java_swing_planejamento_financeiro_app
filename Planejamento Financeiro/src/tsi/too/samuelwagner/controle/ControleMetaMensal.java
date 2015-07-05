package tsi.too.samuelwagner.controle;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import tsi.too.samuelwagner.arquivo.ArquivoMetaMensal;
import tsi.too.samuelwagner.enumeracoes.CaminhoArquivo;
import tsi.too.samuelwagner.enumeracoes.RotuloJanelaMetaMensal;
import tsi.too.samuelwagner.gui.IgCadastrarMetaMensal;
import tsi.too.samuelwagner.tipo.Categoria;
import tsi.too.samuelwagner.tipo.MetaMensal;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;
import tsi.too.samuelwagner.validacoes.Validador;

/**A classe <code>ControleMetaMensal</code> implementa os métodos necessarios para utilizar as operações no <code>ArquivoMetaMensal</code>.
 *  @author Samuel Gonçalves
 * @author Wagner Almeida
 *
 */
public class ControleMetaMensal {
	private ArquivoMetaMensal arquivoMetaMensal;
	private static IgCadastrarMetaMensal igCadastrarMetaMensal; 
	
	/**
	 * Construtor default da classe <code>ControleMetaMensal</code>. Inicializa
	 * o arquivoMetaMensal <code>ArquivoMetaMensal</code>.
	 */
	public ControleMetaMensal() {
		arquivoMetaMensal = new ArquivoMetaMensal();
	}
	
	/**Grava um objeto <code>MetaMensal</code> no arquivo binário.
	 * 
	 * @param codigo <code>int</code> da categoria da meta mensal definida.
	 * @param data <code>Calendar</code> com o mês e o ano da meta mensal definida.
	 * @param valor <code>double</code> da meta mensal definida.
	 * @param porcentagem <code>int</code> da meta mensal definida.
	 */
	/*
	 * O método abre o arquivo para gravação, posiciona o ponteiro na ultima posição válida e grava o objeto.
	 * Em seguida o arquivo é fechado.
	 */
	public void gravarMetaMensal(int codigo, Calendar data, double valor, int porcentagem){
		try {
			arquivoMetaMensal.openFile(CaminhoArquivo.META_MENSAL.getCaminho());
			arquivoMetaMensal.setFilePointer(arquivoMetaMensal.recordQuantity());
			arquivoMetaMensal.writeObject(new MetaMensal(codigo,data,valor,porcentagem));
			arquivoMetaMensal.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//gravarMetaMensal()
	
	/**
	 * Pesquisa um objeto <code>Categoria</code> salvo no arquivo binário.
	 * 
	 * @param codigo <code>int</code> referente a despesa procurada.
	 * @return indice <code>int</code> com a posição ou -1 caso não seja localizado o objeto.
	 */
	/*
	 * O método abre o arquivo, pesquisa todos os objetos salvos, comparando ao parâmetro recebido. Caso não localize 
	 * retorna 1, caso encontre, retorna a posição.
	 */
	public int pesquisaMetaMensal(int codigo) {
		try {
			arquivoMetaMensal.openFile(CaminhoArquivo.META_MENSAL.getCaminho());
			for(int indice = 0; indice < arquivoMetaMensal.recordQuantity();indice++){
				arquivoMetaMensal.setFilePointer(indice);
				if(arquivoMetaMensal.readObject().getCodigo() == codigo){
					arquivoMetaMensal.closeFile();
					return indice;
				}
			}
			arquivoMetaMensal.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	} //pesquisaMetaMensal()
	
	/**
	 * Obtém um objeto <code>MetaMensal</code> atráves do indice passado por referência.
	 * 
	 * @param indice <code>int</code> com a posição do arquivo.
	 * @return metaMensal com um objeto <code>MetaMensal</code> salvo na posição indicada por parâmetro. 
	 */
	/*
	 * O método recebe o indice para uma posição no arquivo e verifica se esta posição é válida, caso seja retorna o objeto
	 * requisitado.
	 */
	public MetaMensal obtemMetaMensal(int indice) {
		MetaMensal metaMensal = null;
		try {
			arquivoMetaMensal.openFile(CaminhoArquivo.META_MENSAL.getCaminho());
			if(indice < 0 || indice > arquivoMetaMensal.recordQuantity())  {arquivoMetaMensal.closeFile(); return null;}
			arquivoMetaMensal.setFilePointer(indice);
			metaMensal = arquivoMetaMensal.readObject();
			arquivoMetaMensal.closeFile();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return metaMensal;
	}//obtemMetaMensal()
	
	/**
	 * Exclui um objeto <code>MetaMensal</code> cadastrado no arquivo.
	 * 
	 * @param indice <code>int</code> com a posição a ser excluída.
	 * @return <code>true</code> caso a exclusão seja realizada e <code>false</code> caso não seja possível excluir.
	 */
	/*
	 * O método recebe o indice e verifica se este se refere a uma posição válida, caso se refira exclui o objeto
	 * no arquivo na posição solicitada.
	 */
	public boolean excluiMetaMensal(int indice){
			try {
				arquivoMetaMensal.openFile(CaminhoArquivo.META_MENSAL.getCaminho());
				if(indice < 0 || indice > arquivoMetaMensal.recordQuantity())  {arquivoMetaMensal.closeFile(); return false;}
				arquivoMetaMensal.excludeFileRecord(indice);
				arquivoMetaMensal.closeFile();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
	}//excluiMetaMensal()
	
	/**
	 * Cadastra a meta mensal caso ela não esteja cadastrada no sistema.
	 * @param nomeCategoria <code>String</code> com o nome da categoria.
	 * @param mesAno <code>Calendar</code> com o mês e ano da meta.
	 * @param valor <code>String</code> com o valor da meta.
	 * @param porcentagem <code>int</code> com a porcentagem da meta.
	 * @return um <code>boolean</code> informando se a meta foi cadastrada com sucesso. 
	 */
	public boolean cadastrarMetaMensal(String nomeCategoria, Calendar mesAno, String valor, int porcentagem) {
		ControleCategoria categoria = new ControleCategoria();
		int indice = categoria.pesquisaCategoria(nomeCategoria);
	
		Categoria auxiliar = categoria.obtemCategoria(indice);
		if(!Validador.validaNumeroReal(FuncaoAuxiliar.converteVirgulaEmPonto(valor))){
			showMessageDialog(igCadastrarMetaMensal,RotuloJanelaMetaMensal.VAZIO_INVALIDO.getDescricao(),RotuloJanelaMetaMensal.TITULO.getDescricao(),ERROR_MESSAGE);
			return false;
		}else{
			try {
				arquivoMetaMensal.openFile(CaminhoArquivo.META_MENSAL.getCaminho());
				for(int posicao = 0; posicao < arquivoMetaMensal.recordQuantity(); posicao++){
					MetaMensal meta = arquivoMetaMensal.readObject();
					if(meta.getCodigo() == auxiliar.getCodigo())
						if(FuncaoAuxiliar.coverteDataParaString(meta.getMesAnoMeta(), false).equalsIgnoreCase(FuncaoAuxiliar.coverteDataParaString(mesAno, false))){
							showMessageDialog(igCadastrarMetaMensal, RotuloJanelaMetaMensal.REPETIDO.getDescricao(), RotuloJanelaMetaMensal.TITULO.getDescricao(), ERROR_MESSAGE);
							arquivoMetaMensal.closeFile(); return false;
						}
				}//for()
				arquivoMetaMensal.closeFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			gravarMetaMensal(auxiliar.getCodigo(), mesAno, Double.parseDouble(FuncaoAuxiliar.converteVirgulaEmPonto(valor)),porcentagem);
			showMessageDialog(igCadastrarMetaMensal, RotuloJanelaMetaMensal.SALVO.getDescricao(), RotuloJanelaMetaMensal.TITULO.getDescricao(), INFORMATION_MESSAGE);
			return true;
		}
	}//cadastrarMetaMensal
	
	/**
	 * Pesquisa uma meta pelo código da categoria e pelo mês/Ano da mesma.
	 * @param codigo <code>int</code> da categoria.
	 * @param data <code>Calendar</code> com o mês/ano da meta.
	 * @return meta <code>MetaMensal</code> se encontrado..
	 */
	public MetaMensal pesquisarMetaMensal(int codigo, Calendar data) {
		try {
			arquivoMetaMensal.openFile(CaminhoArquivo.META_MENSAL.getCaminho());
			for(int posicao = 0; posicao < arquivoMetaMensal.recordQuantity(); posicao++){
				MetaMensal meta = arquivoMetaMensal.readObject();
				if(meta.getCodigo() == codigo)
					if(FuncaoAuxiliar.coverteDataParaString(meta.getMesAnoMeta(), false).equalsIgnoreCase(FuncaoAuxiliar.coverteDataParaString(data, false))){
						return meta;
					}
			}//for()
			arquivoMetaMensal.closeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** informa o número de metas cadastradas.
	 * @return um <code>int</code> contendo o número da meta.
	 */
	public int retornaNumeroDeMetas(){
		try {
			arquivoMetaMensal.openFile(CaminhoArquivo.META_MENSAL.getCaminho());
			int codigo = (int) arquivoMetaMensal.recordQuantity();
			arquivoMetaMensal.closeFile();
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
}//class ControleMetaMensal
