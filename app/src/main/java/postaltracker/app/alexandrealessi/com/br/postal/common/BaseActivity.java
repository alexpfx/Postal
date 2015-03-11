package postaltracker.app.alexandrealessi.com.br.postal.common;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.List;

import dagger.ObjectGraph;
import postaltracker.app.alexandrealessi.com.br.postal.App;

/**
 * Created by alexandre on 10/03/15.
 */
public abstract class BaseActivity extends ActionBarActivity {
    private ObjectGraph activityGraph;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        activityGraph = ((App) getApplication()).createScopedGraph(getModules().toArray());
        activityGraph.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityGraph = null;
    }

    protected abstract List<Object> getModules();

    public void inject(Object object) {
        activityGraph.inject(object);
    }
}
