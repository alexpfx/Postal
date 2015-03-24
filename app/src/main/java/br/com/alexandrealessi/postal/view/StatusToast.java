package br.com.alexandrealessi.postal.view;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import postaltracker.app.alexandrealessi.com.br.postal.R;

/**
 * Created by alexandre on 03/03/15.
 */
public class StatusToast {
    private static final int DEFAULT_DURATION = Toast.LENGTH_SHORT;
    private final Context context;
    private int GRAVITY = Gravity.TOP | Gravity.END;
    private int X = 0;
    private int Y = 0;

    private Toast toast;

    private StatusToast(Context context) {
        this.context = context;
        toast = Toast.makeText(context,"", DEFAULT_DURATION);
        toast.setGravity(GRAVITY, X, Y);
        toast.getView().setBackgroundColor(context.getResources().getColor(R.color.md_yellow_500));
    }

    public static StatusToast make (Context context){
        return new StatusToast(context);
    }



    public StatusToast info(String message) {
        toast.setText(message);
        toast.getView().setBackgroundColor(context.getResources().getColor(R.color.md_yellow_500));
        toast.show();
        return this;
    }

    public StatusToast error(String message) {
        toast.setText(message);
        toast.getView().setBackgroundColor(context.getResources().getColor(R.color.md_deep_orange_500));
        toast.show();
        return this;
    }

    public StatusToast success(String message) {
        toast.setText(message);
        toast.getView().setBackgroundColor(context.getResources().getColor(R.color.md_green_500));
        toast.show();
        return this;
    }

}
