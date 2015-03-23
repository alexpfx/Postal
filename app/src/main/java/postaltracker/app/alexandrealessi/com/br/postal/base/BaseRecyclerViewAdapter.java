package postaltracker.app.alexandrealessi.com.br.postal.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by alexandre on 22/03/15.
 */
public class BaseRecyclerViewAdapter <VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter <VH> {



    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
