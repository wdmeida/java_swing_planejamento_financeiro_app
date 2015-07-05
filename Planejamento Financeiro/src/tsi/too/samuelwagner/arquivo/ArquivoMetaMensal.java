package tsi.too.samuelwagner.arquivo;

import java.io.IOException;
import java.util.Calendar;

import tsi.too.samuelwagner.tipo.MetaMensal;
import br.samuelwgner.arquivobinario.ArquivoBinario;

/**
 * Define as fun��es respons�veis por salvar e recuperar um objeto de um arquivo em disco.
 *  @author Samuel Gon�alves
 * @author Wagner Almeida
 */
public class ArquivoMetaMensal extends ArquivoBinario {
	
	/**
	 * O m�todo readObject retorna o objeto em que o ponteiro do arquivo est� posicionado. O m�todo dispara a seguinte exce��o:
	 * 
	 * @exception IOException caso n�o seja poss�vel recuperar o objeto do arquivo.
	 * 
	 * @return um <code>MetaMensal</code> com os dados lidos do arquivo.
	 */
	@Override
	public MetaMensal readObject() throws IOException {
		MetaMensal metaMensal = new MetaMensal();
		Calendar cal = Calendar.getInstance();
		
		cal.setTimeInMillis(arquivoBinario.readLong());
		
		metaMensal.setMesAnoMeta(cal);
		
		metaMensal.setCodigo(arquivoBinario.readInt());
		
		metaMensal.setValor(arquivoBinario.readDouble());
		
		metaMensal.setPorcentagem(arquivoBinario.readInt());
		return metaMensal;
	}
	
	/**
	 * O m�todo record size, define o tamanho do objeto a ser persistido. O tamanho do objeto � definido da seguinte
	 * forma:
	 * 
	 * <code>Calendar</code> com o tamanho  de 8 bytes (Pois se salva um valor long).
	 * <code>double</code> com o c�digo de 8 bytes.
	 * <code>int</code> com o c�digo da categoria de 4 bytes.
	 *  <code>int</code> com a porcentagem da meta 4 bytes.
	 * Totalizando 24 bytes.
	 */
	@Override
	public int recordSize() {
		return 24;
	}

	/**
	 * O m�todo writeObject persiste o objeto recebido por par�metro em arquivo. Recebe o seguinte par�metro:
	 * @param obj <code>Object</code> com o objeto a ser persistido.
	 * O m�todo dispara as seguintes exce��es.
	 * @exception IOException caso n�o seja poss�vel persistir o objeto no arquivo.
	 * @exception ClassCastException caso o objeto seja de um tipo diferente do que pode ser persistido no arquivo. 
	 */
	@Override
	public void writeObject(Object obj) throws IOException, ClassCastException {
		MetaMensal metaMensal;
		
		if(obj instanceof MetaMensal)
			metaMensal = (MetaMensal)obj;
		else
			throw new ClassCastException();
		
		arquivoBinario.writeLong(metaMensal.getMesAnoMeta().getTimeInMillis());
		
		arquivoBinario.writeInt(metaMensal.getCodigo());
		
		arquivoBinario.writeDouble(metaMensal.getValor());
		
		arquivoBinario.writeInt(metaMensal.getPorcentagem());
	}
}
