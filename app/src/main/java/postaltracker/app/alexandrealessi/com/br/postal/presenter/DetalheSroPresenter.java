package postaltracker.app.alexandrealessi.com.br.postal.presenter;

import postaltracker.app.alexandrealessi.com.br.postal.common.BasePresenter;
import postaltracker.app.alexandrealessi.com.br.postal.view.SroDetalheView;

/**
 * Created by alex on 24/02/2015.
 */
public interface DetalheSroPresenter extends BasePresenter<SroDetalheView>{
    public void verificarValidadeSro(String sro);
}
