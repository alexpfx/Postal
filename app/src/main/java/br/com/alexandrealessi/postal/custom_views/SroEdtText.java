package br.com.alexandrealessi.postal.custom_views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.alexandrealessi.postal.R;
import butterknife.InjectView;
import butterknife.OnClick;
import com.gc.materialdesign.views.ButtonIcon;
import com.google.zxing.integration.android.IntentIntegrator;


/**
 * Created by alexandre on 23/03/15.
 */
public class SroEdtText extends LinearLayout {




    private final List<SroEdtTextWatcher> textChangeListeners = new ArrayList<>();

    private EditText edtNumber;

    private AutoCompleteTextView edtServiceType;

    private AutoCompleteTextView edtCountry;

    private ButtonIcon btnOpenQrCodeReader;

    private IntentIntegrator scanIntegrator;

    public SroEdtText(Context context) {
        super(context);
        init(context);

    }

    private void init(Context context) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_sro_edttext, this);
        edtNumber = (EditText) view.findViewById(R.id.edtSroNumber);
        edtNumber.addTextChangedListener(textWatcher);
        edtServiceType = (AutoCompleteTextView) view.findViewById(R.id.edtSroServiceType);
        edtServiceType.addTextChangedListener(textWatcher);
        edtCountry = (AutoCompleteTextView) view.findViewById(R.id.edtSroCountry);
        edtCountry.addTextChangedListener(textWatcher);


    }

    private void configurarQRCodeScanner() {
        scanIntegrator = new IntentIntegrator((Activity) getContext());
    }
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            for (SroEdtTextWatcher tw : textChangeListeners) {
                tw.sroChanged(getServiceType(), getNumber(), getCountry(), getSro());
            }
        }
    };


    public SroEdtText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SroEdtText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SroEdtText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public String getSro() {
        return getServiceType().toString() + getNumber().toString() + getCountry().toString();
    }

    public Editable getServiceType() {
        return edtServiceType.getText();
    }

    public Editable getCountry() {
        return edtCountry.getText();

    }

    public Editable getNumber() {
        return edtNumber.getText();
    }

    public void clear() {
        edtServiceType.setText("");
        edtNumber.setText("");
        edtCountry.setText("");
    }

    public interface SroEdtTextWatcher {
        public void sroChanged(Editable serviceType, Editable number, Editable country, String newSro);
    }

    public void addTextChangelistener(SroEdtTextWatcher textWatcher) {
        textChangeListeners.add(textWatcher);
    }

    public void removeTextChangeListener(SroEdtTextWatcher textWatcher) {
        textChangeListeners.remove(textWatcher);
    }

    @OnClick
    public void onBtnOpenQrCodeReader (){
        scanIntegrator.setPrompt("Aponte a camera para o QRCode do objeto de rastreamento");
        scanIntegrator.getMoreExtras();
        scanIntegrator.initiateScan();
    }


}
