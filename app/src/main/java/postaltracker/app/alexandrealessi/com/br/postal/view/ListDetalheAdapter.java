package postaltracker.app.alexandrealessi.com.br.postal.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import postaltracker.app.alexandrealessi.com.br.postal.R;

/**
 * Created by alexandre on 22/02/15.
 */
public class ListDetalheAdapter extends RecyclerView.Adapter <ListDetalheAdapter.ViewHolder>{
    private Context context;
    private List<ViewModel> modelItemList;

    public ListDetalheAdapter(Context context, List<ViewModel> modelItemList) {
        this.context = context;
        this.modelItemList = modelItemList;
    }






    public void add (ViewModel model){
        this.modelItemList.add(model);
    }
    public void remove (ViewModel model){
        this.modelItemList.remove(model);
    }
    public void clear (){
        this.modelItemList.clear();
    }

    public void setModelItemList(List<ViewModel> modelItemList) {
        this.modelItemList = modelItemList;
    }

    @Override
    public ListDetalheAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detalhe_item, parent, false);
        final ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,""+modelItemList.get(vh.getPosition()), Toast.LENGTH_SHORT).show();
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ViewModel item = modelItemList.get(position);
        viewHolder.txtInfo.setText(item.info);
        viewHolder.txtStatus.setText(item.status);
        viewHolder.txtDetalhe.setText(item.detalhe);
    }

    @Override
    public int getItemCount() {
        return modelItemList.size();
    }

    public static class ViewModel{
        private String info;
        private String detalhe;
        private String status;

        private ViewModel(String info, String status, String detalhe) {
            this.info = info;
            this.detalhe = detalhe;
            this.status = status;
        }

        public static ViewModel create (String info, String status, String detalhe){
            ViewModel vm = new ViewModel(info, detalhe, status);
            return vm;
        }

        public ViewModel addTo (List<ViewModel> list){
            list.add(this);
            return this;
        }


        public String getInfo() {
            return info;
        }

        public String getDetalhe() {
            return detalhe;
        }

        public String getStatus() {
            return status;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtInfo;
        private TextView txtDetalhe;
        private TextView txtStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            txtInfo = (TextView) itemView.findViewById(R.id.txtInfo);
            txtDetalhe = (TextView) itemView.findViewById(R.id.txtDetalhe);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
        }


    }



}
