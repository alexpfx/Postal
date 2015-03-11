package postaltracker.app.alexandrealessi.com.br.postal;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;

/**
 * Created by alexandre on 10/03/15.
 */
public class App extends Application{
    private ObjectGraph objectGraph;

    public static App get(Context context){
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildObjectGraphAndInject ();
    }

    public ObjectGraph createScopedGraph (Object... modules){
        return objectGraph.plus(modules);
    }

    private void buildObjectGraphAndInject() {
        objectGraph = ObjectGraph.create(Modules.list(this));
        inject (this);
    }

    private void inject(Object object) {
        objectGraph.inject(object);
    }

    public ObjectGraph getApplicationGraph (){
        return objectGraph;
    }

}
