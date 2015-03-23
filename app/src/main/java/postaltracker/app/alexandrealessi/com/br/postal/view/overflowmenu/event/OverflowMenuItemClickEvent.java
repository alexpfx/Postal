package postaltracker.app.alexandrealessi.com.br.postal.view.overflowmenu.event;

import android.app.Fragment;

import postaltracker.app.alexandrealessi.com.br.postal.view.overflowmenu.OverflowMenuViewAdapter;

/**
 * Created by alexandre on 23/03/15.
 */
public class OverflowMenuItemClickEvent {


    private final OverflowMenuViewAdapter.ViewModel viewModel;

    public OverflowMenuItemClickEvent(OverflowMenuViewAdapter.ViewModel viewModel) {

        this.viewModel = viewModel;
    }


    public OverflowMenuViewAdapter.ViewModel getViewModel() {
        return viewModel;
    }
}
