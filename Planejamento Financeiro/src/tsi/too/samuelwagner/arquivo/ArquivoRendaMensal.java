package tsi.too.samuelwagner.arquivo;

import java.io.IOException;
import java.util.Calendar;

import tsi.too.samuelwagner.tipo.RendaMensal;
import br.samuelwgner.arquivobinario.ArquivoBinario;

/**
 * Define as fun��es respons�veis por salvar e recuperar um objeto <code>RendaMensal</code> persistido em um arquivo no disco.
 *  @author Samuel Gon�alves
 * @author Wagner Almeida
 */

public class ArquivoRendaMensal extends ArquivoBinario {

	/**
	 * O m�todo readObject retorna o objeto em que o ponteiro do arquivo est� posicionado. O m�todo dispara a seguinte exce��o:
	 * 
	 * @exception IOException <code>IOException</code> caso n�o seja poss�vel recuperar o objeto do arquivo.
	 * 
	 * @return um <code>RendaMensal</code> com os dados lidos do arquivo.
	 */
	@Override
	public RendaMensal readObject() throws IOException {
		RendaMensal rendaMensal = new RendaMensal();
		
		rendaMensal.setDescricao(readString(40, true));
		
		rendaMensal.setCodigo(arquivoBinario.readInt());
		
		Calendar data = Calendar.getInstance();
		data.setTimeInMillis(arquivoBinario.readLong());
		rendaMensal.setData(data);
		
		rendaMensal.setValor(arquivoBinario.readDouble());
		
		return rendaMensal;
	}//readObject()

	/**
	 * O m�todo record size, define o tamanho do objeto a ser persistido. O tamanho do objeto � definido da seguinte
	 * forma:
	 * 
	 * <code>String</code> com o c�digo de 80 bytes.
	 * <code>int</code> com o c�digo de 4 bytes.
	 * <code>Calendar</code> com a data, em milisegundos. Calendar sera persistido como um tipo <code>long</code> 
	 * para que possa ser salvo em arquivo. Tipo <code>long</code> possui 8 bytes.
	 * <code>double</code> com o valor da renda de 8 bytes.
	 * 
	 * Totalizando 100 bytes.
	 */
	@Override
	public int recordSize() {
		return 100;
	}//recordSize()

	/**
	 * O m�todo writeObject persiste o objeto recebido por par�metro em arquivo. Recebe o seguinte par�metro:
	 * @param obj <code>Object</code> com o objeto a ser persistido.
	 * O m�todo dispara as seguintes exce��es.
	 * @exception IOException caso n�o seja poss�vel persistir o objeto no arquivo.
	 * @exception ClassCastException caso o objeto seja de um tipo diferente do que pode ser persistido no arquivo. 
	 */
	@Override
	public void writeObject(Object obj) throws IOException, ClassCastException {
		RendaMensal rendaMensal;
		
		if(obj instanceof RendaMensal)
			rendaMensal = (RendaMensal) obj;
		else
			throw new ClassCastException();
		
		arquivoBinario.writeChars(setStringLength(rendaMensal.getDescricao(), 40));
		
		arquivoBinario.writeInt(rendaMensal.getCodigo());
		
		arquivoBinario.writeLong(rendaMensal.getData().getTimeInMillis());
		
		arquivoBinario.writeDouble(rendaMensal.getValor());
	}//writeObject()

}//class ArquivoRendaMensal
