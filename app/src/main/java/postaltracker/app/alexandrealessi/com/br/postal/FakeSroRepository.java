package postaltracker.app.alexandrealessi.com.br.postal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.alexpfx.api.postal.SRO;
import br.com.alexpfx.api.postal.SROFactory;

/**
 * Created by alex on 26/02/2015.
 */
public class FakeSroRepository implements SroRepository {
    private Map<SRO, List<SroRetornoInfo>> map;


    public FakeSroRepository() {
        this.map = new HashMap<>();
        map.put(new SROFactory().criar("DM180464317BR"), criarLista());
        map.put(new SROFactory().criar("DM180638790BR"), criarLista());
    }

    private List<SroRetornoInfo> criarLista() {
        List<SroRetornoInfo> lista = new ArrayList<>();
        lista.add(new SroRetornoInfo(new Date(), "CTE CAMPINAS/GCCAP - Valinhos/SP", "postado", "-"));
        lista.add(new SroRetornoInfo(new Date(), "CTE CAMPINAS/GCCAP - Valinhos/SP", "encaminhado", "Encaminhado para CTE CAMPINAS - Valinhos/SP"));
        return lista;
    }

    @Override
    public List<SroRetornoInfo> send(SRO sro) throws InfraException {
        List<SroRetornoInfo> retorno = map.get(sro);
        return retorno;
   }
}
