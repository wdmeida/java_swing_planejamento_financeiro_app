package tsi.too.samuelwagner.arquivo;

import java.io.IOException;

import tsi.too.samuelwagner.tipo.Renda;
import br.samuelwgner.arquivobinario.ArquivoBinario;

/**
 * Define as fun��es respons�veis por salvar e recuperar um objeto <code>Renda</code> persistido em um arquivo no disco.
 * 
 * @author Samuel e Wagner
 */
public class ArquivoRenda extends ArquivoBinario {

	/**
	 * O m�todo readObject retorna o objeto em que o ponteiro do arquivo est� posicionado. O m�todo dispara a seguinte exce��o:
	 * 
	 * @exception IOException caso n�o seja poss�vel recuperar o objeto do arquivo.
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
	 * O m�todo record size, define o tamanho do objeto a ser persistido. O tamanho do objeto � definido da seguinte
	 * forma:
	 * 
	 * <code>String</code> com a descri��o de no m�ximo 30 bytes (60 bytes UNICODE, pois este necessita de 2 bytes por caracter)
	 * <code>int</code> com o c�digo de 4 bytes.
	 * 
	 * Totalizando 64 bytes.
	 */
	@Override
	public int recordSize() {
		return 64;
	}//recordSize()

	/**
	 * O m�todo writeObject persiste o objeto recebido por par�metro em arquivo. Recebe o seguinte par�metro:
	 * @param obj <code>Object</code> com o objeto a ser persistido.
	 * O m�todo dispara as seguintes exce��es.
	 * @exception IOException caso n�o seja poss�vel persistir o objeto no arquivo.
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
