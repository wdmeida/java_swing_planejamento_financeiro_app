package tsi.too.samuelwagner.arquivo;

import java.io.IOException;
import java.util.Calendar;

import tsi.too.samuelwagner.tipo.PlanejamentoMensal;
import br.samuelwgner.arquivobinario.ArquivoBinario;

/**
 * Define as fun��es respons�veis por salvar e recuperar um objeto <code>PlanejamentoMensal</code> persistido em um arquivo no disco.
 * 
 * @author Samuel e Wagner
 */
public class ArquivoPlanejamentoMensal extends ArquivoBinario {

	/**
	 * O m�todo readObject retorna o objeto em que o ponteiro do arquivo est� posicionado. O m�todo dispara a seguinte exce��o:
	 * 
	 * @exception IOException caso n�o seja poss�vel recuperar o objeto do arquivo.
	 * 
	 * @return um <code>FormaPagamento</code> com os dados lidos do arquivo.
	 */
	@Override
	public PlanejamentoMensal readObject() throws IOException {
		PlanejamentoMensal planejamento = new PlanejamentoMensal();
		
		planejamento.setCodigoDespesa(arquivoBinario.readInt());
		
		Calendar data = Calendar.getInstance();
		data.setTimeInMillis(arquivoBinario.readLong());
		planejamento.setMesAnoPlanejamento(data);
		
		return planejamento;
	}//readObject()
	
	/**
	 * O m�todo record size, define o tamanho do objeto a ser persistido. O tamanho do objeto � definido da seguinte
	 * forma:
	 * 
	 * <code>int</code> com o c�digo de 4 bytes.
	 * <code>Calendar</code> com a data, em milisegundos. Calendar sera persistido como um tipo <code>long</code> 
	 * para que possa ser salvo em arquivo. Tipo <code>long</code> possui 8 bytes.
	 * 
	 * Totalizando 12 bytes.
	 */
	@Override
	public int recordSize() {
		return 12;
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
		PlanejamentoMensal planejamento;
		
		if(obj instanceof PlanejamentoMensal)
			planejamento = (PlanejamentoMensal) obj;
		else
			throw new ClassCastException();

		arquivoBinario.writeInt(planejamento.getCodigoDespesa());
		
		arquivoBinario.writeLong(planejamento.getMesAnoPlanejamento().getTimeInMillis());
	}//writeObject()

}//class ArquivoPlanejamentoMensal
