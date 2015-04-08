package br.com.alexandrealessi.postal.model.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandre on 09/03/15.
 */
public class Pacote {
    String sro;
    List<Evento> itemsAcao = new ArrayList<>();
    List<String> tags = new ArrayList<>();

    public Pacote() {
    }



    public String getSro() {
        return sro;
    }

    public void setSro(String sro) {
        this.sro = sro;
    }

    public List<Evento> getItemsAcao() {
        return itemsAcao;
    }

    public void setItemsAcao(List<Evento> itemsAcao) {
        this.itemsAcao = itemsAcao;
    }


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Evento obterItemAcaoRecente() {
        if (itemsAcao.size() > 0){
            return itemsAcao.get(0);
        }
        return Evento.NULL;
    }

    @Override
    public String toString() {
        return sro;
    }
}
