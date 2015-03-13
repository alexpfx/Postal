package postaltracker.app.alexandrealessi.com.br.postal.common;

/**
 * Created by alexandre on 12/03/15.
 */
public abstract class AbstractPresenter <T>   {
    private T view;

    public void init(T view) {

        this.view = view;
    }

    public T getView() {
        return view;
    }



}
