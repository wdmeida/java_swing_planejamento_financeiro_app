package tsi.too.samuelwagner.arquivo;

import java.io.IOException;
import java.util.Calendar;

import tsi.too.samuelwagner.tipo.Despesa;
import br.samuelwgner.arquivobinario.ArquivoBinario;

/**
 * Define as fun��es respons�veis por salvar e recuperar um objeto de um arquivo em disco.
 * 
 * @author Samuel e Wagner
 */
public class ArquivoDespesa extends ArquivoBinario {
	
	/**
	 * O m�todo readObject retorna o objeto em que o ponteiro do arquivo est� posicionado. O m�todo dispara a seguinte exce��o:
	 * 
	 * @exception IOException caso n�o seja poss�vel recuperar o objeto do arquivo.
	 * 
	 * @return um <code>Despesa</code> com os dados lidos do arquivo.
	 */
	@Override
	public Despesa readObject() throws IOException {
		Despesa despesa = new Despesa();
		
		despesa.setCodigo(arquivoBinario.readInt());
		
		despesa.setCodigoCategoria(arquivoBinario.readInt());
		
		despesa.setCodigoPagamento(arquivoBinario.readInt());
		
		Calendar calDes = Calendar.getInstance();
		calDes.setTimeInMillis(arquivoBinario.readLong());
		despesa.setDataDespesa(calDes);
		
		calDes.setTimeInMillis(arquivoBinario.readLong());
		despesa.setDataPagamento(calDes);
		
		despesa.setDescricao(readString(40, true));
		
		despesa.setValorDespesa(arquivoBinario.readDouble());
		
		despesa.setNumeroParcelas(arquivoBinario.readInt());
		
		despesa.setNumeroCheque(readString(20, true));
		
		return despesa;
	}//readObject()
	
	/**
	 * O m�todo record size, define o tamanho do objeto a ser persistido. O tamanho do objeto � definido da seguinte
	 * forma:
	 * 
	 * <code>int</code> com o tamanho de 4 bytes (16 bytes, pois s�o 4 valores <code>int</code> de 4 bytes cada 1).
	 * <code>Calendar</code> com o tamanho  de 8 bytes, pois ser� salva a data em milissegundos <code>long</code> 
	 * 						 (16 bytes s�o 2 objetos calendar).
	 * <code>double</code> com o c�digo de 8 bytes.
	 * <code>String</code> com a descri��o da despesa de 40 bytes (80 bytes UNICODE pois este utiliza 2 bytes por caracter).
	 * <code>String</code> com a descri��o da despesa de 20 bytes (40 bytes UNICODE pois este utiliza 2 bytes por caracter).
	 * Totalizando 160 bytes.
	 */
	@Override
	public int recordSize() {
		return 160;
	}//recordSize()

	/**
	 * O m�todo writeObject persiste o objeto recebido por par�metro em arquivo. Recebe o seguinte par�metro:
	 * @param obj <code>Object</code> com o objeto a ser persistido.
	 * O m�todo dispara as seguintes exce��es.
	 * @exception IOException caso n�o seja poss�vel persistir o objeto no arquivo.
	 * @exception ClassCastException caso o objeto seja de um tipo diferente do que pode ser persistido no arquivo. 
	 */
	@Override
	public void writeObject(Object obj) throws IOException, ClassCastException{
		Despesa despesa;
		
		if(obj instanceof Despesa)
			despesa = (Despesa)obj;
		else
			throw new ClassCastException();
		
		arquivoBinario.writeInt(despesa.getCodigo());
		
		arquivoBinario.writeInt(despesa.getCodigoCategoria());
		
		arquivoBinario.writeInt(despesa.getCodigoPagamento());
		
		arquivoBinario.writeLong(despesa.getDataDespesa().getTimeInMillis());
		
		arquivoBinario.writeLong(despesa.getDataPagamento().getTimeInMillis());
		
		arquivoBinario.writeChars(setStringLength(despesa.getDescricao(), 40));
		
		arquivoBinario.writeDouble(despesa.getValorDespesa());
		
		arquivoBinario.writeInt(despesa.getNumeroParcelas());
		
		arquivoBinario.writeChars(setStringLength(despesa.getNumeroCheque(), 20));
	}//writeObject()
}//class Despesa
