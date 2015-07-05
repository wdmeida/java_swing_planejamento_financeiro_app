package tsi.too.samuelwagner.arquivo;

import java.io.IOException;

import tsi.too.samuelwagner.tipo.FormaPagamento;
import br.samuelwgner.arquivobinario.ArquivoBinario;

/**
 * Define as funções responsáveis por salvar e recuperar um objeto <code>FormaPagemento</code> persistido em um arquivo no disco.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class ArquivoFormaPagamento extends ArquivoBinario {

	/**
	 * O método readObject retorna o objeto em que o ponteiro do arquivo está posicionado. O método dispara a seguinte exceção:
	 * 
	 * @exception IOException caso não seja possível recuperar o objeto do arquivo.
	 * 
	 * @return um <code>FormaPagamento</code> com os dados lidos do arquivo.
	 */
	@Override
	public FormaPagamento readObject() throws IOException {
		FormaPagamento formaPagamento = new FormaPagamento();
		
		formaPagamento.setCodigo(arquivoBinario.readInt());
		
		formaPagamento.setDescricao(readString(30, true));
		
		return formaPagamento;
	}//readObject()
	
	/**
	 * O método record size, define o tamanho do objeto a ser persistido. O tamanho do objeto é definido da seguinte
	 * forma:
	 * 
	 * <code>int</code> com o código de 4 bytes.
	 * <code>String</code> com a descrição de 30 bytes (60 bytes UNICODE, pois este necessita de 2 bytes para cada caracter).
	 * 
	 * Totalizando 64 bytes.
	 */
	@Override
	public int recordSize() {
		return 64;
	}//recordSize()

	/**
	 * O método writeObject persiste o objeto recebido por parâmetro em arquivo. Recebe o seguinte parâmetro:
	 * @param obj <code>Object</code> com o objeto a ser persistido.
	 * O método dispara as seguintes exceções.
	 * @exception IOException caso não seja possível persistir o objeto no arquivo.
	 * @exception ClassCastException caso o objeto seja de um tipo diferente do que pode ser persistido no arquivo. 
	 */
	@Override
	public void writeObject(Object obj) throws IOException, ClassCastException{
		FormaPagamento formaPagamento;
		
		if(obj instanceof FormaPagamento)
			formaPagamento = (FormaPagamento) obj;
		else
			throw new ClassCastException();
		
		arquivoBinario.writeInt(formaPagamento.getCodigo());
		
		arquivoBinario.writeChars(setStringLength(formaPagamento.getDescricao(), 30));

	}//writeObject()

}//class ArquivoFormaPagamento
