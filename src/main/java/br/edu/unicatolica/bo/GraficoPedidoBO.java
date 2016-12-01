package br.edu.unicatolica.bo;

import br.edu.unicatolica.dao.PedidoDAO;
import br.edu.unicatolica.entity.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author Danrley
 */
public class GraficoPedidoBO implements Serializable {

    private static GraficoPedidoBO instance;

    private GraficoPedidoBO() {

    }

    public static GraficoPedidoBO getInstance() {
        if (instance == null) {
            instance = new GraficoPedidoBO();
        }
        return instance;
    }

    public Map<Date, BigDecimal> valoresTotaisPorData(Integer numeroDeDias, Usuario vendedor) {
        Calendar dataInicial = Calendar.getInstance();
        dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
        dataInicial.add(Calendar.DAY_OF_MONTH, numeroDeDias * -1);

        Map<Date, BigDecimal> resultado = criarMapaVazio(numeroDeDias, dataInicial);

        resultado = PedidoDAO.getInstance().consultaGrafico(resultado, vendedor, dataInicial);
        return resultado;
    }

    private Map<Date, BigDecimal> criarMapaVazio(Integer numeroDeDias, Calendar dataInicial) {
        numeroDeDias -= 1;
        dataInicial = (Calendar) dataInicial.clone();
        Map<Date, BigDecimal> mapaInicial = new TreeMap<>();

        for (int i = 0; i < numeroDeDias; i++) {
            mapaInicial.put(dataInicial.getTime(), BigDecimal.ZERO);
            dataInicial.add(Calendar.DAY_OF_MONTH, 1);
        }

        return mapaInicial;
    }
}
