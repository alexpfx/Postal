package br.com.alexandrealessi.postal.custom_views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
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

    EditText edtCode;

    AutoCompleteTextView edtTipoServico;

    AutoCompleteTextView edtPais;

    public SroEdtText(Context context) {
        super(context);
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_sro_edttext, this);

    }

    public SroEdtText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SroEdtText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SroEdtText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
