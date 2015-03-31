package br.com.alexandrealessi.postal.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import br.com.alexandrealessi.postal.R;

/**
 * Created by alexandre on 30/03/15.
 */
public class AddNewSroDialogFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.fragment_cadastro_pacotes, null)).setPositiveButton(R.string.accept, null).setNegativeButton(R.string.cancel, null);

        return builder.create();
    }
}
