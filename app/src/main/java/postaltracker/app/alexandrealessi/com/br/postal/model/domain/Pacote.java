package postaltracker.app.alexandrealessi.com.br.postal.model.domain;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 09/03/15.
 */
public class Pacote extends SugarRecord<Pacote>{
    private String sro;

    private List<ItemAcao> itemsAcao = new ArrayList<>();
    public List<String> tags;

    public String getSro() {
        return sro;
    }

    public void setSro(String sro) {
        this.sro = sro;
    }

    public List<ItemAcao> getItemsAcao() {
        return itemsAcao;
    }

    public void setItemsAcao(List<ItemAcao> itemsAcao) {
        this.itemsAcao = itemsAcao;
    }


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public ItemAcao obterItemAcaoRecente (){
        return itemsAcao.get(0);
    }


}
