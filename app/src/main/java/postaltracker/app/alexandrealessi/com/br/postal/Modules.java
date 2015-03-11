package postaltracker.app.alexandrealessi.com.br.postal;

/**
 * Created by alexandre on 10/03/15.
 */
public class Modules {

    public Modules() {
    }
    static Object[] list (App app){
        return new Object[] {new AppModule (app)};
    }
}
