package postaltracker.app.alexandrealessi.com.br.postal.model.domain;

import com.orm.SugarRecord;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 09/03/15.
 */
public class Pacote extends SugarRecord<Pacote>{
    private String sro;


    public Pacote() {
    }

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
        if (itemsAcao.size() > 0){
            return itemsAcao.get(0);
        }
        return ItemAcao.NULL;
    }

    @Override
    public String toString() {
        return sro;
    }
}
