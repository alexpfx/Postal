package br.com.alexandrealessi.postal.custom_views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import br.com.alexandrealessi.postal.R;
import br.com.alexandrealessi.postal.utils.SroDTO;
import br.com.alexandrealessi.postal.utils.SroUtils;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.gc.materialdesign.views.ButtonIcon;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by alexandre on 23/03/15.
 */
public class SroEdtText extends LinearLayout {

    public static final String tag = SroEdtText.class.getName();
    private final List<SroEdtTextWatcher> textChangeListeners = new ArrayList<>();

    private EditText edtCodeNumber;

    private AutoCompleteTextView edtServiceType;

    private AutoCompleteTextView edtCountry;

    @InjectView(R.id.btnOpenQrCodeReader)
    ButtonIcon btnOpenQrCodeReader;

    private IntentIntegrator scanIntegrator;

    public SroEdtText(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_sro_edttext, this);
        edtCodeNumber = (EditText) view.findViewById(R.id.edtSroNumber);
        edtCodeNumber.addTextChangedListener(textWatcher);
        edtServiceType = (AutoCompleteTextView) view.findViewById(R.id.edtSroServiceType);
        edtServiceType.addTextChangedListener(textWatcher);
        edtCountry = (AutoCompleteTextView) view.findViewById(R.id.edtSroCountry);
        edtCountry.addTextChangedListener(textWatcher);
        setupQRCodeScanner();
        ButterKnife.inject(this, view);
    }

    private void setupQRCodeScanner() {
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
                tw.sroChanged(getServiceType(), getNumber(), getCountry(), getSroCode());
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

    public String getSroCode() {
        return getServiceType().toString() + getNumber().toString() + getCountry().toString();
    }

    public Editable getServiceType() {
        return edtServiceType.getText();
    }

    public Editable getCountry() {
        return edtCountry.getText();
    }

    public Editable getNumber() {
        return edtCodeNumber.getText();
    }

    public void clear() {
        edtServiceType.setText("");
        edtCodeNumber.setText("");
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

    @OnClick(R.id.btnOpenQrCodeReader)
    public void onBtnOpenQrCodeReader() {
        scanIntegrator.setPrompt("Ler QRCode");
        scanIntegrator.getMoreExtras();
        scanIntegrator.initiateScan();
    }

    @OnClick(R.id.btnPaste)
    public void onBtnPasteClick() {
        //TODO: habilitar botao paste somente quando houver conteudo valido na area de transferencia para nao precisar fazer todas estas validacoes.
        ClipboardManager clipBoardManager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData primaryClip = clipBoardManager.getPrimaryClip();
        if (primaryClip == null) {
            Log.d(tag, "nada na area de trasnferencia");
            return;
        }
        ClipData.Item itemAt = primaryClip.getItemAt(0);
        if (itemAt == null) {
            Log.d(tag, "sem itens na area de transferencia");
            return;
        }
        CharSequence text = itemAt.getText();
        if (text == null) {
            Log.d(tag, "não contém texto");
            return;
        }

        try {
            SroDTO sroDTO = SroUtils.getSroDTOFromCodeString(String.valueOf(text));
            edtServiceType.setText(sroDTO.getServiceType());
            edtCodeNumber.setText(sroDTO.getCodeNumber());
            edtCountry.setText(sroDTO.getCountry());
        } catch (IllegalArgumentException e) {
            Log.d(tag, e.getMessage());
        }
    }

}
