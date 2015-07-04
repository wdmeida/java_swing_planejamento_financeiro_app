package tsi.too.samuelwagner.arquivo;

import java.io.IOException;
import java.util.Calendar;

import tsi.too.samuelwagner.tipo.Despesa;
import br.samuelwgner.arquivobinario.ArquivoBinario;

/**
 * Define as funções responsáveis por salvar e recuperar um objeto de um arquivo em disco.
 * 
 * @author Samuel e Wagner
 */
public class ArquivoDespesa extends ArquivoBinario {
	
	/**
	 * O método readObject retorna o objeto em que o ponteiro do arquivo está posicionado. O método dispara a seguinte exceção:
	 * 
	 * @exception IOException caso não seja possível recuperar o objeto do arquivo.
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
	 * O método record size, define o tamanho do objeto a ser persistido. O tamanho do objeto é definido da seguinte
	 * forma:
	 * 
	 * <code>int</code> com o tamanho de 4 bytes (16 bytes, pois são 4 valores <code>int</code> de 4 bytes cada 1).
	 * <code>Calendar</code> com o tamanho  de 8 bytes, pois será salva a data em milissegundos <code>long</code> 
	 * 						 (16 bytes são 2 objetos calendar).
	 * <code>double</code> com o código de 8 bytes.
	 * <code>String</code> com a descrição da despesa de 40 bytes (80 bytes UNICODE pois este utiliza 2 bytes por caracter).
	 * <code>String</code> com a descrição da despesa de 20 bytes (40 bytes UNICODE pois este utiliza 2 bytes por caracter).
	 * Totalizando 160 bytes.
	 */
	@Override
	public int recordSize() {
		return 160;
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
