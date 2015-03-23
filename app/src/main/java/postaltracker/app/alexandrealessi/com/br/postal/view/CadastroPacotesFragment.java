package postaltracker.app.alexandrealessi.com.br.postal.view;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return inflater.inflate(R.layout.fragment_cadastro_pacotes, container, false);
    }


}
