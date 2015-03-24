package br.com.alexandrealessi.postal.custom_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;

import br.com.alexandrealessi.postal.R;


/**
 * Created by alexandre on 23/03/15.
 */
public class SroEdtText extends LinearLayout {

    EditText edtNumber;

    AutoCompleteTextView edtServiceType;

    AutoCompleteTextView edtCountry;

    public SroEdtText(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_sro_edttext, this);
        edtNumber = (EditText) view.findViewById(R.id.edtSroNumber);
        edtServiceType = (AutoCompleteTextView) view.findViewById(R.id.edtSroServiceType);
        edtCountry = (AutoCompleteTextView) view.findViewById(R.id.edtSroCountry);
    }

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
}
