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
public class ListPacotesAdapter extends RecyclerView.Adapter<ListPacotesAdapter.ViewHolder> {

    private DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.SHORT, Locale.getDefault());
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public ListPacotesAdapter(List<Item> items) {
        this.items = items;
    }

    public interface ItemClickListener {
        public void itemClicked(Item item);
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
        holder.txtSro.setText(item.sro);
        holder.txtDataAcao.setText(dateFormat.format(item.dataAcao).concat(": "));
        holder.txtAcao.setText(item.acao);
        StringBuilder sb = new StringBuilder();
        List<String> tags = item.tags;
        if (tags != null) {
            for (String tag : tags) {
                sb.append(tag).append(" ");
            }
        }
        holder.txtTags.setText(sb.toString());

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

    static final class Item {
        private final Date dataAcao;
        private final List<String> tags;
        private String acao, sro;

        public Item(String acao, Date dataAcao, String sro, List<String> tags) {
            this.acao = acao;
            this.sro = sro;
            this.dataAcao = dataAcao;
            this.tags = tags;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtSro, txtDataAcao, txtAcao, txtTags;
        private final ImageButton btnLocation, btnExpand, btnDirections;

        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }


        private ViewHolder(View itemView, TextView txtSro, TextView txtAcao, TextView txtDataAcao, TextView txtTags, ImageButton btnLocation, ImageButton btnDirections, ImageButton btnExpand) {
            super(itemView);
            this.txtSro = txtSro;
            this.btnLocation = btnLocation;
            this.txtAcao = txtAcao;
            this.txtDataAcao = txtDataAcao;
            this.btnDirections = btnDirections;
            this.btnExpand = btnExpand;
            this.txtTags = txtTags;

        }

        private static ViewHolder newInstance(View v) {
            TextView txtAcao, txtSro, txtDataAcao, txtTags;
            txtSro = (TextView) v.findViewById(R.id.txtSro);
            ImageButton btnLocation = (ImageButton) v.findViewById(R.id.btnExpand);
            ImageButton btnExpand = (ImageButton) v.findViewById(R.id.btnExpand);
            ImageButton btnDirections = (ImageButton) v.findViewById(R.id.btnExpand);
            txtAcao = (TextView) v.findViewById(R.id.txtAcao);
            txtDataAcao = (TextView) v.findViewById(R.id.txtDataAcao);
            txtTags = (TextView) v.findViewById(R.id.txtTags);
            return new ViewHolder(v, txtSro, txtAcao, txtDataAcao, txtTags, btnLocation, btnDirections, btnExpand);
        }

    }


}
