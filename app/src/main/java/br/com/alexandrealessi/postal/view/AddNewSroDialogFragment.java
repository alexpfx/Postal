package br.com.alexandrealessi.postal.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import br.com.alexandrealessi.postal.R;
import br.com.alexandrealessi.postal.custom_views.SroEdtText;
import br.com.alexpfx.api.postal.Sro;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by alexandre on 30/03/15.
 */
public class AddNewSroDialogFragment extends DialogFragment {
    private String tag = AddNewSroDialogFragment.class.getName();
    private NewSroListener listener;

    @InjectView(R.id.edtSro)
    SroEdtText sroEdtText;

    public static interface NewSroListener {
        void onNewSroAdd (Sro sro);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_cadastro_pacotes, null);
        ButterKnife.inject(this, view);
        builder.setView(view)
                .setPositiveButton(R.string.accept, acceptClick)
                .setNegativeButton(R.string.cancel, null);

        return builder.create();
    }
    public DialogInterface.OnClickListener acceptClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (listener != null){


            }

        }
    };

}
