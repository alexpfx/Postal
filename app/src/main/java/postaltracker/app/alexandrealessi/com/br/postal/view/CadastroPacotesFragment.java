package postaltracker.app.alexandrealessi.com.br.postal.view;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import postaltracker.app.alexandrealessi.com.br.postal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroPacotesFragment extends Fragment {


    public CadastroPacotesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_cadastro_pacotes, container, false);
        ButterKnife.inject(this, view);
        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_cadastro_pacotes, menu);
    }
}
