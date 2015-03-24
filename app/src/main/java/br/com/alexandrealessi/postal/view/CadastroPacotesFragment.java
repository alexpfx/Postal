package br.com.alexandrealessi.postal.view;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import br.com.alexandrealessi.postal.R;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroPacotesFragment extends Fragment {

    private MenuItem actionPaste;
    private MenuItem actionAccept;
    private MenuItem actionCancel;

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
        actionPaste = menu.findItem(R.id.action_paste);
        actionAccept= menu.findItem(R.id.action_accept);
        actionCancel= menu.findItem(R.id.action_cancel);
        inflater.inflate(R.menu.menu_cadastro_pacotes, menu);
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
