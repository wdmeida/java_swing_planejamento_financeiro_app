package tsi.too.samuelwagner.arquivo;

import java.io.IOException;

import tsi.too.samuelwagner.tipo.Renda;
import br.samuelwgner.arquivobinario.ArquivoBinario;

/**
 * Define as funções responsáveis por salvar e recuperar um objeto <code>Renda</code> persistido em um arquivo no disco.
 * 
 * @author Samuel e Wagner
 */
public class ArquivoRenda extends ArquivoBinario {

	/**
	 * O método readObject retorna o objeto em que o ponteiro do arquivo está posicionado. O método dispara a seguinte exceção:
	 * 
	 * @exception IOException caso não seja possível recuperar o objeto do arquivo.
	 * 
	 * @return um <code>Renda</code> com os dados lidos do arquivo.
	 */
	@Override
	public Renda readObject() throws IOException {
		Renda renda = new Renda();
		
		renda.setCodigo(arquivoBinario.readInt());
		renda.setDescricao(readString(30, true));
		
		return renda;
	}//readObject()

	/**
	 * O método record size, define o tamanho do objeto a ser persistido. O tamanho do objeto é definido da seguinte
	 * forma:
	 * 
	 * <code>String</code> com a descrição de no máximo 30 bytes (60 bytes UNICODE, pois este necessita de 2 bytes por caracter)
	 * <code>int</code> com o código de 4 bytes.
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
	public void writeObject(Object obj) throws IOException {
		Renda renda;
		
		if(obj instanceof Renda)
			renda = (Renda) obj;
		else
			throw new ClassCastException();
		
		arquivoBinario.writeInt(renda.getCodigo());
		
		arquivoBinario.writeChars(setStringLength(renda.getDescricao(), 30));

	}//writeObject()

}//class ArquivoRenda
