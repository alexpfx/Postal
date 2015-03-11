package postaltracker.app.alexandrealessi.com.br.postal;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import postaltracker.app.alexandrealessi.com.br.postal.presenter.DetalheSroPresenter;
import postaltracker.app.alexandrealessi.com.br.postal.presenter.DetalheSroPresenterImpl;
import postaltracker.app.alexandrealessi.com.br.postal.view.ListaDetalhesFragment;
import postaltracker.app.alexandrealessi.com.br.postal.view.ListaPacotesFragment;

/**
 * Created by alexandre on 10/03/15.
 */
@Module(
        addsTo = AppModule.class,
        injects = {MainActivity.class, ListaDetalhesFragment.class, ListaPacotesFragment.class},
        complete = false,
        library = true
)
public class MainModule {
    private MainActivity activity;

    public MainModule(MainActivity activity) {
        this.activity = activity;
    }


    @Provides
    @Singleton
    public DetalheSroPresenter providerDetalheSroPresenter (){
        return new DetalheSroPresenterImpl();
    }
}
