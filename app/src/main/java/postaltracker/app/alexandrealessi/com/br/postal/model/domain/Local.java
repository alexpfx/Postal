package postaltracker.app.alexandrealessi.com.br.postal.model.domain;

import com.orm.SugarRecord;

/**
 * Created by alexandre on 09/03/15.
 */
public class Local extends SugarRecord<Local>{
    private String descricao;

    public Local() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
