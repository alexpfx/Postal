package postaltracker.app.alexandrealessi.com.br.postal.common;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by alexandre on 10/03/15.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("theActivity: "+getActivity());
        ((BaseActivity) getActivity()).inject(this);

    }
}
