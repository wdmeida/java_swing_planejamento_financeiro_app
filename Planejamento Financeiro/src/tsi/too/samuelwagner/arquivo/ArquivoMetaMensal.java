package tsi.too.samuelwagner.arquivo;

import java.io.IOException;
import java.util.Calendar;

import tsi.too.samuelwagner.tipo.MetaMensal;
import br.samuelwgner.arquivobinario.ArquivoBinario;

/**
 * Define as funções responsáveis por salvar e recuperar um objeto de um arquivo em disco.
 *  @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class ArquivoMetaMensal extends ArquivoBinario {
	
	/**
	 * O método readObject retorna o objeto em que o ponteiro do arquivo está posicionado. O método dispara a seguinte exceção:
	 * 
	 * @exception IOException caso não seja possível recuperar o objeto do arquivo.
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
	 * O método record size, define o tamanho do objeto a ser persistido. O tamanho do objeto é definido da seguinte
	 * forma:
	 * 
	 * <code>Calendar</code> com o tamanho  de 8 bytes (Pois se salva um valor long).
	 * <code>double</code> com o código de 8 bytes.
	 * <code>int</code> com o código da categoria de 4 bytes.
	 *  <code>int</code> com a porcentagem da meta 4 bytes.
	 * Totalizando 24 bytes.
	 */
	@Override
	public int recordSize() {
		return 24;
	}

	/**
	 * O método writeObject persiste o objeto recebido por parâmetro em arquivo. Recebe o seguinte parâmetro:
	 * @param obj <code>Object</code> com o objeto a ser persistido.
	 * O método dispara as seguintes exceções.
	 * @exception IOException caso não seja possível persistir o objeto no arquivo.
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
