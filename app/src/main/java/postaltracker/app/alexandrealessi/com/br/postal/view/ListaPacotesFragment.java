package postaltracker.app.alexandrealessi.com.br.postal.view;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import postaltracker.app.alexandrealessi.com.br.postal.R;
import postaltracker.app.alexandrealessi.com.br.postal.model.domain.ItemAcao;
import postaltracker.app.alexandrealessi.com.br.postal.model.domain.Pacote;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaPacotesFragment extends Fragment implements ListaPacotesView {

    @InjectView(R.id.listPacotes)
    RecyclerView recyclerView;

    private ListPacotesAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_pacotes, container, false);
        ButterKnife.inject(this, view);

        recyclerView.setHasFixedSize(false);

        LinearLayoutManager lm = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);
        adapter = new ListPacotesAdapter(createFakeItems());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private Random rand = new Random();

    private List<ListPacotesAdapter.Item> createFakeItems() {
        return new ArrayList<ListPacotesAdapter.Item>() {
            {
                add(new ListPacotesAdapter.Item("Entrega não efetuada por motivos operacionais", new Date(rand.nextLong()), "DM 543496494 BR", new ArrayList<String>() {
                    {
                        add("Siciliano");
                        add("Livro");
                        add("Presente");
                    }
                }));

            }
        };
    }


    @Override
    public void mostrarListaPacotes(List<Pacote> pacotes) {
        adapter.getItems().clear();
        for (Pacote pacote : pacotes) {
            ItemAcao itemAcao = pacote.obterItemAcaoRecente();
            String acao = itemAcao.getAcao().getDescricao();
            Date dataAcao = itemAcao.getData();
            adapter.getItems().add(new ListPacotesAdapter.Item(acao, dataAcao, pacote.getSro(), pacote.getTags()));
        }
    }

}//81


