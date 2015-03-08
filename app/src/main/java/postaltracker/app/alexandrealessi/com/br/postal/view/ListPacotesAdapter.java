package postaltracker.app.alexandrealessi.com.br.postal.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import postaltracker.app.alexandrealessi.com.br.postal.R;

/**
 * Created by alexandre on 06/03/15.
 */
public class ListPacotesAdapter  extends RecyclerView.Adapter<ListPacotesAdapter.ViewHolder> {

    private DateFormat dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM, Locale.getDefault());
    private List<Item> items;


    public ListPacotesAdapter(List<Item> items) {
        this.items = items;
    }

    public interface ItemClickListener{
        public void itemClicked (Item item);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_pacote, parent, false);
        return ViewHolder.newInstance(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.setSro(item.sro);
        holder.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "teste", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static final class Item {
        private String acao, sro;
        private boolean atualizado;

        public Item(String acao, String sro) {
            this.acao = acao;
            this.sro = sro;
        }

    }

     static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtSro;
        private ImageButton btnLocation;
        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }


        private  ViewHolder(View itemView, TextView txtSro, ImageButton btnLocation) {
            super(itemView);
            this.txtSro = txtSro;
            this.btnLocation = btnLocation;
        }

        private static ViewHolder newInstance (View v){
            TextView txtAcao, txtSro;
            txtSro = (TextView) v.findViewById(R.id.txtSro);
            ImageButton btnLocationButton = (ImageButton) v.findViewById(R.id.btnLocation);
           return new ViewHolder(v, txtSro, btnLocationButton);
        }

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void setSro(String txtSro) {
            this.txtSro.setText(txtSro);
        }

        public void setBtnLocation(ImageButton btnLocation) {
            this.btnLocation = btnLocation;
        }
    }



}
