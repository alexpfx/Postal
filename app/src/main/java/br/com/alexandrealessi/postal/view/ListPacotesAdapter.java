package br.com.alexandrealessi.postal.view;

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

import br.com.alexandrealessi.postal.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

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
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_pacote_n, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.tvSro.setText(item.sro);
        holder.tvDataAcao.setText(dateFormat.format(item.dataAcao).concat(": "));
        holder.tvAcao.setText(item.acao);
        holder.tvLocal.setText(item.local);

        StringBuilder sb = new StringBuilder();
        List<String> tags = item.tags;
        if (tags != null) {
            for (String tag : tags) {
                sb.append(tag).append(" ");
            }
        }
        holder.txtTags.setText(sb.toString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static final class Item {
        private final Date dataAcao;
        private final List<String> tags;
        private String acao, sro, local;

        public Item(String acao, Date dataAcao, String sro, String local, List<String> tags) {
            this.acao = acao;
            this.sro = sro;
            this.dataAcao = dataAcao;
            this.tags = tags;
            this.local = local;

        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tvSro)
        TextView tvSro;
        @InjectView(R.id.tvDataAcao)
        TextView tvDataAcao;
        @InjectView(R.id.tvAcao)
        TextView tvAcao;
        @InjectView(R.id.tvTags)
        TextView txtTags;

        @InjectView(R.id.tvLocal)
        TextView tvLocal;

        @InjectView(R.id.btnExpand)
        ImageButton btnExpand;


        @OnClick(R.id.btnExpand)
        public void btnExpandClick() {
            System.out.println("click");
        }


        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        private ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

}
