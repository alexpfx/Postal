package br.com.alexandrealessi.postal.view;


import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.com.alexandrealessi.postal.R;
import br.com.alexandrealessi.postal.custom_views.SroEdtText;
import br.com.alexpfx.api.postal.NenhumSroValidoException;
import br.com.alexpfx.api.postal.Sro;
import br.com.alexpfx.api.postal.SroFactory;
import br.com.alexpfx.api.postal.SroInvalidoException;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroPacotesFragment extends Fragment {

    private static final int SRO_LENGTH = 13;
    private MenuItem actionPaste;
    private MenuItem actionAccept;
    private MenuItem actionCancel;

    @InjectView(R.id.edtSro)
    SroEdtText edtSro;

    public CadastroPacotesFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_cadastro_pacotes, container, false);
        ButterKnife.inject(this, view);

        setupEdtSro();
        return view;

    }

    private void setupEdtSro() {
        edtSro
                .addTextChangelistener(new SroEdtText.SroEdtTextWatcher() {
                    @Override
                    public void sroChanged(Editable serviceType, Editable number, Editable country, String newSro) {
                        showHideCancelMenuItem (newSro);
                        sendOrNotForValidation (newSro);
                    }
                });
    }

    private void sendOrNotForValidation(String newSro) {
        if (newSro.length() == SRO_LENGTH){
            try {
                Sro sro = new SroFactory().criar(newSro);
            } catch (SroInvalidoException e) {
                // mostrar que é invalido.
            }
        } else if (newSro.length() > SRO_LENGTH){
            try {
                List<Sro> listSro = new SroFactory().criarListaDescartarInvalidos(newSro);
            } catch (NenhumSroValidoException e) {
                // mostrar q nenhum eh valido
            }
        }

    }

    private void showHideCancelMenuItem(String newSro) {
        actionCancel.setVisible(!newSro.isEmpty());
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_cadastro_pacotes, menu);
        actionPaste = menu.findItem(R.id.action_paste);
        actionAccept = menu.findItem(R.id.action_accept);
        actionCancel = menu.findItem(R.id.action_cancel);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_qrread:
                openQrCodeReader();
                return true;
            case R.id.action_paste:
                pasteFromClipboard();
                return true;
            case R.id.action_cancel:
                clearEditTexts();
                return true;
            case R.id.action_accept:
                addValidSroToList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addValidSroToList() {


    }

    private void clearEditTexts() {

    }

    private void pasteFromClipboard() {

    }

    private void openQrCodeReader() {
    }
}
