package tsi.too.samuelwagner.controle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tsi.too.samuelwagner.arquivo.ArquivoDespesa;
import tsi.too.samuelwagner.enumeracoes.CaminhoArquivo;
import tsi.too.samuelwagner.operacoes.GerenciamentoDeFinanca;
import tsi.too.samuelwagner.tipo.Categoria;
import tsi.too.samuelwagner.tipo.Despesa;
import tsi.too.samuelwagner.tipo.FormaPagamento;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;

/**A classe <code>ControleDespesa</code> implementa os m�todos necessarios para utilizar as opera��es no <code>ArquivoDespesa</code>.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 *
 */
public class ControleDespesa {
	private ArquivoDespesa arquivoDespesa;
	
	/**
	 * Construtor default da classe <code>ControleDespesa</code>. Inicializa o <code>ArquivoDespesa</code>.
	 */
	public ControleDespesa() {
		arquivoDespesa = new ArquivoDespesa();
	}
	
	/**
	 * Grava um objeto <code>Despesa</code> no arquivo bin�rio.
	 * 
	 * @param despesa <code>Despesa</code> com a despesa a ser cadastrada.
	 */
	public void gravarDespesa(Despesa despesa){
		try {
			arquivoDespesa.openFile(CaminhoArquivo.DESPESA.getCaminho());
			arquivoDespesa.setFilePointer(arquivoDespesa.recordQuantity());
			arquivoDespesa.writeObject(despesa);
			arquivoDespesa.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//gravarDespesa
	
	/**
	 * Pesquisa um objeto <code>Despesa</code> salvo no arquivo bin�rio.
	 * 
	 * @param descricao <code>String</code> referente a despesa procurada.
	 * @return indice <code>int</code> com a posi��o ou -1 caso n�o seja localizado o objeto.
	 */
	/*
	 * O m�todo abre o arquivo, pesquisa todos os objetos salvos, comparando ao par�metro recebido. Caso n�o localize 
	 * retorna 1, caso encontre, retorna a posi��o.
	 */
	public int pesquisarDespesa(String descricao) {
		try {
			arquivoDespesa.openFile(CaminhoArquivo.DESPESA.getCaminho());
			for(int indice = 0; indice < arquivoDespesa.recordQuantity();indice++){
				arquivoDespesa.setFilePointer(indice);
				if(FuncaoAuxiliar.comparaString(arquivoDespesa.readObject().getDescricao(),descricao)){
					arquivoDespesa.closeFile();
					return indice;
				}
			}
			arquivoDespesa.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}//pesquisaDespesa() 
	
	/**
	 * Obt�m um objeto <code>Despesa</code> atr�ves do indice passado por refer�ncia.
	 * 
	 * @param indice <code>int</code> com a posi��o do arquivo.
	 * @return metaMensal com um objeto <code>Despesa</code> salvo na posi��o indicada por par�metro. 
	 */
	/*
	 * O m�todo recebe o indice para uma posi��o no arquivo e verifica se esta posi��o � v�lida, caso seja retorna o objeto
	 * requisitado.
	 */
	public Despesa obtemDespesa(int indice) {
		Despesa despesa = null;
		try {
			arquivoDespesa.openFile(CaminhoArquivo.DESPESA.getCaminho());
			if(indice < 0 || indice > arquivoDespesa.recordQuantity())  {arquivoDespesa.closeFile(); return null;}
			arquivoDespesa.setFilePointer(indice);
			despesa = arquivoDespesa.readObject();
			arquivoDespesa.closeFile();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return despesa;
	}//obtemDespesa()
	
	/**
	 * Gera o c�digo da despesa a ser cadastrada.
	 * @return
	 */
	private int gerarCodigoDespesa() {
		int codigo = 1;
		Despesa despesa;
		try {
			arquivoDespesa.openFile(CaminhoArquivo.DESPESA.getCaminho());
			if(arquivoDespesa.recordQuantity() == 0)  {arquivoDespesa.closeFile(); return codigo;}
			for(int indice = 0; indice < arquivoDespesa.recordQuantity(); indice++){
				arquivoDespesa.setFilePointer(indice);
				despesa = arquivoDespesa.readObject();
				if(despesa.getCodigo() > codigo)
					codigo = despesa.getCodigo();
			}
			arquivoDespesa.closeFile();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return codigo + 1;
	}//gerarCodigoDespesa()
	
	/**
	 * Exclui um objeto <code>Despesa</code> cadastrado no arquivo.
	 * 
	 * @param indice <code>int</code> com a posi��o a ser exclu�da.
	 * @return <code>true</code> caso a exclus�o seja realizada e <code>false</code> caso n�o seja poss�vel excluir.
	 */
	/*
	 * O m�todo recebe o indice e verifica se este se refere a uma posi��o v�lida, caso se refira exclui o objeto
	 * no arquivo na posi��o solicitada.
	 */
	public boolean excluirDespesa(int indice){
			try {
				arquivoDespesa.openFile(CaminhoArquivo.DESPESA.getCaminho());
				if(indice < 0 || indice > arquivoDespesa.recordQuantity())  {arquivoDespesa.closeFile(); return false;}
				arquivoDespesa.excludeFileRecord(indice);
				arquivoDespesa.closeFile();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
	}//excluiiDespesa()
	
	/**
	 * Retorna a quantidade de despesas cadastradas.
	 * @return tamanho <code>int</code>
	 */
	public int quantidadeDespesas(){
		int tamanho = 0;
		try {
			arquivoDespesa.openFile(CaminhoArquivo.DESPESA.getCaminho());
			tamanho = (int)arquivoDespesa.recordQuantity();
			arquivoDespesa.closeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tamanho;
	}//quantidadeDespesas
	
	/**
	 * Cadastra a despesa no arquivo.
	 * @param despesa <code>Despesa</code> com o objeto a ser salvo.
	 * @param formaPagamento <code>String</code> com o nome da forma de pagamento utilizada.
	 * @param nomeCategoria <code>String</code> com o nome da categoria da despesa.
	 */
	public void cadastrarDespesa(Despesa despesa, String formaPagamento, String nomeCategoria) {
		FormaPagamento pagamento = new FormaPagamento();
		Categoria categoria = new Categoria();
		
		//Pesquisa a forma de pagamento no arquivo para obter o c�digo.
		int indice = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleFormaPagamento().pesquisaFormaPagamento(formaPagamento);
		if(indice != -1)
			pagamento = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleFormaPagamento().obtemFormaPagamento(indice);
		despesa.setCodigoPagamento(pagamento.getCodigo());
		
		//Pesquisa a categoria no arquivo para obter o c�digo.
		indice = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleCategoria().pesquisaCategoria(nomeCategoria);
		if(indice != -1)
			categoria = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleCategoria().obtemCategoria(indice);
		
		//Atribui o c�digo da categoria a despesas.
		despesa.setCodigoCategoria(categoria.getCodigo());
		
		//Gera um c�digo para a despesa.
		despesa.setCodigo(gerarCodigoDespesa());
	
		//Grava a categoria no arquivo.
		gravarDespesa(despesa);
		
		//Grava o planejamento mensal para a despesa cadastrada.
		GerenciamentoDeFinanca.getGerenciamentoFincanca().getControlePlanejamentoMensal().gravarPlanejamentoMensal(despesa.getCodigo(), despesa.getDataPagamento());
	}//cadastrarDespesa
	
	/**
	 * Pesquisa as despesas de acordo com o m�s e ano passados por par�mentro.
	 * @param codigoCategoria <code>int</code> com o c�digo da categoria da despesa.
	 * @param dataDespesa <code>Calendar</code> com o m�s ano a ser pesquisado.
	 * @return despesas <code>Despesa[]</code> com as despesas encontradas ou <code>null</code> caso n�o encontre nenhuma.
	 */
	public Despesa[] pesquisarDespesas(int codigoCategoria, Calendar dataDespesa){
		List<Despesa> despesas = new ArrayList<Despesa>();
		
		//Obt�m a data em formato mes/ano.
		String data = FuncaoAuxiliar.coverteDataParaString(dataDespesa, false);
		try {
			arquivoDespesa.openFile(CaminhoArquivo.DESPESA.getCaminho());
			if(arquivoDespesa.recordQuantity() == 0) {arquivoDespesa.closeFile(); return null;}
			for(int i = 0; i < arquivoDespesa.recordQuantity(); i++){
				arquivoDespesa.setFilePointer(i);
				Despesa despesa = arquivoDespesa.readObject();
				if(codigoCategoria == despesa.getCodigoCategoria() && data.equalsIgnoreCase(FuncaoAuxiliar.coverteDataParaString(despesa.getDataDespesa(),false)))
					despesas.add(despesa);
				
			}
			arquivoDespesa.closeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(despesas.size() > 0) return despesas.toArray(new Despesa[0]); return null;
	}//pesquisarDespesas
	
	/**
	 * Pesquisa as despesas de acordo com o m�s e ano passados por par�mentro.
	 * @param dataDespesa <code>Calendar</code> com o m�s ano a ser pesquisado.
	 * @return despesas <code>Despesa[]</code> com as despesas encontradas ou <code>null</code> caso n�o encontre nenhuma.
	 */
	public Despesa[] pesquisarDespesas(Calendar dataDespesa){
		List<Despesa> despesas = new ArrayList<Despesa>();
		
		//Obt�m a data em formato mes/ano.
		String data = FuncaoAuxiliar.coverteDataParaString(dataDespesa, false);
		try {
			arquivoDespesa.openFile(CaminhoArquivo.DESPESA.getCaminho());
			if(arquivoDespesa.recordQuantity() == 0) {arquivoDespesa.closeFile(); return null;}
			for(int i = 0; i < arquivoDespesa.recordQuantity(); i++){
				arquivoDespesa.setFilePointer(i);
				Despesa despesa = arquivoDespesa.readObject();
				if(data.equalsIgnoreCase(FuncaoAuxiliar.coverteDataParaString(despesa.getDataDespesa(),false)))
					despesas.add(despesa);
				
			}
			arquivoDespesa.closeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(despesas.size() > 0) return despesas.toArray(new Despesa[0]); return null;
	}//pesquisarDespesas
	
	/**
	 * Retorna o n�mero de Despesas cadastradas.
	 * @return um numeroDespesa <code>int</code>
	 */
	public int numerosDeDespesas() {
		int numeroDespesa;
		try {
			arquivoDespesa.openFile(CaminhoArquivo.DESPESA.getCaminho());
			numeroDespesa = (int) arquivoDespesa.recordQuantity();
			arquivoDespesa.closeFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		return numeroDespesa;
	}
	
	/**
	 * Obt�m uma despesa atrav�s do seu c�digo.
	 * @param codigoDespesa <code>int</code> com o c�digo da despesa.
	 * @return despesas <code>Despesa</code> com a despesa que possui o c�digo passado por par�mentro.
	 */
	public Despesa pesquisarDespesas(int codigoDespesa){
		Despesa despesa = new Despesa();
		try {
			
			arquivoDespesa.openFile(CaminhoArquivo.DESPESA.getCaminho());
			if(arquivoDespesa.recordQuantity() == 0) {arquivoDespesa.closeFile(); return null;}
			for(int i = 0; i < arquivoDespesa.recordQuantity(); i++){
				arquivoDespesa.setFilePointer(i);
				despesa = arquivoDespesa.readObject();
				if(despesa.getCodigo() == codigoDespesa)
					break;
			}
			arquivoDespesa.closeFile();			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return despesa;
	}//pesquisarDespesas
	
}//class ControleDespesa
