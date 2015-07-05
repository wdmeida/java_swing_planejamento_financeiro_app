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

/**A classe <code>ControleDespesa</code> implementa os métodos necessarios para utilizar as operações no <code>ArquivoDespesa</code>.
 * @author Samuel Gonçalves
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
	 * Grava um objeto <code>Despesa</code> no arquivo binário.
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
	 * Pesquisa um objeto <code>Despesa</code> salvo no arquivo binário.
	 * 
	 * @param descricao <code>String</code> referente a despesa procurada.
	 * @return indice <code>int</code> com a posição ou -1 caso não seja localizado o objeto.
	 */
	/*
	 * O método abre o arquivo, pesquisa todos os objetos salvos, comparando ao parâmetro recebido. Caso não localize 
	 * retorna 1, caso encontre, retorna a posição.
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
	 * Obtém um objeto <code>Despesa</code> atráves do indice passado por referência.
	 * 
	 * @param indice <code>int</code> com a posição do arquivo.
	 * @return metaMensal com um objeto <code>Despesa</code> salvo na posição indicada por parâmetro. 
	 */
	/*
	 * O método recebe o indice para uma posição no arquivo e verifica se esta posição é válida, caso seja retorna o objeto
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
	 * Gera o código da despesa a ser cadastrada.
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
	 * @param indice <code>int</code> com a posição a ser excluída.
	 * @return <code>true</code> caso a exclusão seja realizada e <code>false</code> caso não seja possível excluir.
	 */
	/*
	 * O método recebe o indice e verifica se este se refere a uma posição válida, caso se refira exclui o objeto
	 * no arquivo na posição solicitada.
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
		
		//Pesquisa a forma de pagamento no arquivo para obter o código.
		int indice = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleFormaPagamento().pesquisaFormaPagamento(formaPagamento);
		if(indice != -1)
			pagamento = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleFormaPagamento().obtemFormaPagamento(indice);
		despesa.setCodigoPagamento(pagamento.getCodigo());
		
		//Pesquisa a categoria no arquivo para obter o código.
		indice = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleCategoria().pesquisaCategoria(nomeCategoria);
		if(indice != -1)
			categoria = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleCategoria().obtemCategoria(indice);
		
		//Atribui o código da categoria a despesas.
		despesa.setCodigoCategoria(categoria.getCodigo());
		
		//Gera um código para a despesa.
		despesa.setCodigo(gerarCodigoDespesa());
	
		//Grava a categoria no arquivo.
		gravarDespesa(despesa);
		
		//Grava o planejamento mensal para a despesa cadastrada.
		GerenciamentoDeFinanca.getGerenciamentoFincanca().getControlePlanejamentoMensal().gravarPlanejamentoMensal(despesa.getCodigo(), despesa.getDataPagamento());
	}//cadastrarDespesa
	
	/**
	 * Pesquisa as despesas de acordo com o mês e ano passados por parâmentro.
	 * @param codigoCategoria <code>int</code> com o código da categoria da despesa.
	 * @param dataDespesa <code>Calendar</code> com o mês ano a ser pesquisado.
	 * @return despesas <code>Despesa[]</code> com as despesas encontradas ou <code>null</code> caso não encontre nenhuma.
	 */
	public Despesa[] pesquisarDespesas(int codigoCategoria, Calendar dataDespesa){
		List<Despesa> despesas = new ArrayList<Despesa>();
		
		//Obtém a data em formato mes/ano.
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
	 * Pesquisa as despesas de acordo com o mês e ano passados por parâmentro.
	 * @param dataDespesa <code>Calendar</code> com o mês ano a ser pesquisado.
	 * @return despesas <code>Despesa[]</code> com as despesas encontradas ou <code>null</code> caso não encontre nenhuma.
	 */
	public Despesa[] pesquisarDespesas(Calendar dataDespesa){
		List<Despesa> despesas = new ArrayList<Despesa>();
		
		//Obtém a data em formato mes/ano.
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
	 * Retorna o número de Despesas cadastradas.
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
	 * Obtém uma despesa através do seu código.
	 * @param codigoDespesa <code>int</code> com o código da despesa.
	 * @return despesas <code>Despesa</code> com a despesa que possui o código passado por parâmentro.
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
