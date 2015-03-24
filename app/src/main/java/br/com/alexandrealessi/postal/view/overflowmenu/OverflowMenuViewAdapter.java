package br.com.alexandrealessi.postal.view.overflowmenu;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.alexandrealessi.postal.BusProvider;
import br.com.alexandrealessi.postal.R;
import br.com.alexandrealessi.postal.view.overflowmenu.event.OverflowMenuItemClickEvent;

/**
 * Created by alexandre on 21/02/15.
 */
public class OverflowMenuViewAdapter extends RecyclerView.Adapter<OverflowMenuViewAdapter.ViewHolder> {

    private Context context;
    private List<ViewModel> modelItemList;

    public OverflowMenuViewAdapter(Context context, List<ViewModel> modelItemList) {
        this.modelItemList = modelItemList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_overflow_menu, parent, false);
        final ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        ViewModel itemModel = modelItemList.get(position);
        viewHolder.txtLabel.setText(itemModel.label);
        viewHolder.imgIcon.setImageResource(itemModel.srcIcon);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusProvider.getInstance().post(new OverflowMenuItemClickEvent(modelItemList.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelItemList.size();
    }


    public static class ViewModel {
        private int srcIcon;
        private String label;
        private Class <? extends Fragment> fragmentClass;

       public static ViewModel create(int srcIcon, String label, Class<? extends Fragment> fragmentClass) {
            ViewModel v = new ViewModel();
            v.srcIcon = srcIcon;
            v.label = label;
            v.fragmentClass = fragmentClass;
            return v;
        }

        public ViewModel andAddTo(List<ViewModel> list) {
            list.add(this);
            return this;
        }

        public int getSrcIcon() {
            return srcIcon;
        }

        public String getLabel() {
            return label;
        }

        public Class<? extends Fragment> getFragmentClass() {
            return fragmentClass;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgIcon;
        private TextView txtLabel;

        public ViewHolder(View itemView) {
            super(itemView);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
            txtLabel = (TextView) itemView.findViewById(R.id.txtLabel);
        }
    }
}

