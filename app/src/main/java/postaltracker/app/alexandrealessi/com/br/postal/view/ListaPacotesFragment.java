package postaltracker.app.alexandrealessi.com.br.postal.view;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import postaltracker.app.alexandrealessi.com.br.postal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaPacotesFragment extends Fragment {


    private RecyclerView recyclerView;
    private ListPacotesAdapter adapter;

    public ListaPacotesFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_lista_pacotes, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.listPacotes);
        recyclerView.addItemDecoration(new ListDetalheDividersItemDecoration());

        LinearLayoutManager lm = new LinearLayoutManager(this.getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);
        adapter = new ListPacotesAdapter(createFakeItems());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return v;
    }

    private List<ListPacotesAdapter.Item> createFakeItems() {
        return new ArrayList<ListPacotesAdapter.Item>(){
            {
                add(new ListPacotesAdapter.Item(new Date(),new Date(), "detalhe", "DM 123456789 BR"));
                add(new ListPacotesAdapter.Item(new Date(),new Date(), "palhoca", "XP 446797914 CN"));
                add(new ListPacotesAdapter.Item(new Date(),new Date(), "floripa", "DR 149740790 BR"));
            }
        };


    }


}
