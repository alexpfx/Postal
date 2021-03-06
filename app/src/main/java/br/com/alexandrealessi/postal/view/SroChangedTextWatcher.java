package br.com.alexandrealessi.postal.view;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by alex on 25/02/2015.
 */
public class SroChangedTextWatcher implements TextWatcher {
    private SroDetalheView listener;


    public SroChangedTextWatcher(SroDetalheView listener) {
        this.listener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        listener.onQrCodeChange();
    }
}
