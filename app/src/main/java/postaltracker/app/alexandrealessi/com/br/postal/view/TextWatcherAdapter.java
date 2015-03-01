package postaltracker.app.alexandrealessi.com.br.postal.view;

import android.text.Editable;
import android.text.TextWatcher;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by alex on 25/02/2015.
 */
public abstract class TextWatcherAdapter implements TextWatcher{


    private Editable [] edts;

    protected TextWatcherAdapter(Editable ... edts) {
        this.edts = edts;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    private StringBuilder sb = new StringBuilder();
    protected String getConcatText (Editable thisEditable){
        sb.setLength(0);
        for (Editable edt:edts){
            if (thisEditable.hashCode() == edt.hashCode()){
                sb.append(thisEditable.toString());
            }else{
                sb.append(edt.toString());
            }
        }
        return sb.toString();
    }
}
