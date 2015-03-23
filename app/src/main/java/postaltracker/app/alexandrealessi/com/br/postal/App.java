package postaltracker.app.alexandrealessi.com.br.postal;

import android.app.Application;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by alexandre on 22/03/15.
 */
public class App extends Application {
    public static Bus BUS = new Bus(ThreadEnforcer.MAIN);


}
