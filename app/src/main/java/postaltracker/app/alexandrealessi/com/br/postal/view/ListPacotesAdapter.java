package postaltracker.app.alexandrealessi.com.br.postal.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

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
        View v = LayoutInflater.from(context).inflate(R.layout.sro_item, parent, false);
        return ViewHolder.newInstance(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.setDetalhes(item.detalhe);
        holder.setDataInsercao(dateFormat.format(item.dataInsercao));
        holder.setDataPostagem(dateFormat.format(item.dataPostagem));
        holder.setSro(item.sro);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static final class Item {
        private Date dataPostagem, dataInsercao;
        private String detalhe, sro;

        public Item(Date dataPostagem, Date dataInsercao, String detalhe, String sro) {
            this.dataPostagem = dataPostagem;
            this.dataInsercao = dataInsercao;
            this.detalhe = detalhe;
            this.sro = sro;
        }

    }

     static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtDataPostagem, txtDataInsercao, txtDetalhes, txtSro;
        private ImageButton btnLocationButton;
        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }


        private  ViewHolder(View itemView, TextView txtDataPostagem, TextView txtDataInsercao, TextView txtDetalhes, TextView txtSro, ImageButton btnLocationButton) {
            super(itemView);
            this.txtDataPostagem = txtDataPostagem;
            this.txtDataInsercao = txtDataInsercao;
            this.txtDetalhes = txtDetalhes;
            this.txtSro = txtSro;
            this.btnLocationButton = btnLocationButton;
        }

        private static ViewHolder newInstance (View v){
            TextView txtDataPostagem, txtDataInsercao, txtDetalhes, txtSro;
            txtDataPostagem = (TextView) v.findViewById(R.id.txtDataPostagem);
            txtDataInsercao = (TextView) v.findViewById(R.id.txtDataInsercao);
            txtDetalhes = (TextView) v.findViewById(R.id.txtDataPostagem);
            txtSro = (TextView) v.findViewById(R.id.txtSro);
            ImageButton btnLocationButton = (ImageButton) v.findViewById(R.id.btnLocationButton);
           return new ViewHolder(v, txtDataPostagem, txtDataInsercao, txtDetalhes,txtSro, btnLocationButton);
        }

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void setDataPostagem(String txtDataPostagem) {
            this.txtDataPostagem.setText(txtDataPostagem);
        }

        public void setDataInsercao(String txtDataInsercao) {
            this.txtDataInsercao.setText(txtDataInsercao);
        }

        public void setDetalhes(String txtDetalhes) {
            this.txtDetalhes.setText(txtDetalhes);
        }

        public void setSro(String txtSro) {
            this.txtSro.setText(txtSro);
        }

        public void setBtnLocationButton(ImageButton btnLocationButton) {
            this.btnLocationButton = btnLocationButton;
        }
    }



}
