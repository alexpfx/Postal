package br.com.alexandrealessi.postal.view.overflowmenu.event;

import br.com.alexandrealessi.postal.view.overflowmenu.OverflowMenuViewAdapter;

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
