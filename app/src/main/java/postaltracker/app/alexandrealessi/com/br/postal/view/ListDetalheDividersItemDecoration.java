package postaltracker.app.alexandrealessi.com.br.postal.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by alexandre on 28/02/15.
 */
public class ListDetalheDividersItemDecoration extends RecyclerView.ItemDecoration {
    private int space = 1;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(space, 0, space, space);
        if (parent.getChildPosition(view) == 0){
            outRect.top = space;
        }


    }
}
