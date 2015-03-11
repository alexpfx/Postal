package postaltracker.app.alexandrealessi.com.br.postal;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandre on 10/03/15.
 */
@Module(
        injects = {
                App.class
        },
        library = true
)
public class AppModule {
    private final App app;


    public AppModule(App app) {
        this.app = app;

    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }
}
