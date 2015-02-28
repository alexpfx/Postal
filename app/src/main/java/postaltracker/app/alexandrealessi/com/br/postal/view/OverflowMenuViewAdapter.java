package postaltracker.app.alexandrealessi.com.br.postal.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import postaltracker.app.alexandrealessi.com.br.postal.R;

/**
 * Created by alexandre on 21/02/15.
 */
public class OverflowMenuViewAdapter extends RecyclerView.Adapter<OverflowMenuViewAdapter.ViewHolder> {

    private Context context;
    private List<ViewModel> modelItemList;
    private final OnOverflowMenuItemClickListener clickListener;

    public OverflowMenuViewAdapter(Context context, List<ViewModel> modelItemList, OnOverflowMenuItemClickListener clickListener) {
        this.clickListener = clickListener;
        this.modelItemList = modelItemList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.overflow_menu_item, parent, false);
        final ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onOverflowMenuItemClick(modelItemList.get(vh.getPosition()));
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ViewModel itemModel = modelItemList.get(position);
        viewHolder.txtLabel.setText(itemModel.label);
        viewHolder.imgIcon.setImageResource(itemModel.srcIcon);
    }

    @Override
    public int getItemCount() {
        return modelItemList.size();
    }


    public static interface OnOverflowMenuItemClickListener {
        void onOverflowMenuItemClick(ViewModel viewModel);
    }

    public static class ViewModel {
        private int srcIcon;
        private String label;

        public static ViewModel create(int srcIcon, String label) {
            ViewModel v = new ViewModel();
            v.srcIcon = srcIcon;
            v.label = label;
            return v;
        }

        public ViewModel andAddTo(List<ViewModel> list) {
            list.add(this);
            return this;
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

