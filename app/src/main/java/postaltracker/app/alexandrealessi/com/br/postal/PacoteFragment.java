package postaltracker.app.alexandrealessi.com.br.postal;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static postaltracker.app.alexandrealessi.com.br.postal.ListDetalheAdapter.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class PacoteFragment extends Fragment {
    private ShowPacoteDetalhadoPresenter detalhePresenter;

    public PacoteFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pacote, container, false);

        RecyclerView listDetalhe = (RecyclerView) v.findViewById(R.id.listDetalhe);
        listDetalhe.setHasFixedSize(false);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        listDetalhe.setLayoutManager(manager);

        ListDetalheAdapter adapter = new ListDetalheAdapter(this.getActivity().getApplicationContext(),createFakeList ());
        listDetalhe.setAdapter(adapter);
        return v;
    }

    private List<ViewModel> createFakeList() {
        List<ViewModel> list = new ArrayList<>();
        ViewModel.create("21/11/2014 17:48 Palhoca / SC", "Objeto entregue ao destinatário ", "").addTo(list);
        ViewModel.create("21/11/2014 10:26 Palhoca / SC", "Objeto saiu para entrega ao destinatário ", "").addTo(list);
        ViewModel.create("21/11/2014 06:39 Sao Jose / SC", "Objeto encaminhado ", "de Unidade Operacional em Sao Jose / SC para Unidade de Distribuição em Palhoca / SC ").addTo(list);
        ViewModel.create("20/11/2014 17:57 Florianopolis / SC", "Objeto encaminhado ", "de Agência dos Correios em Florianopolis / SC para Unidade Operacional em Sao Jose / SC ").addTo(list);
        ViewModel.create("20/11/2014 16:20 Florianopolis / SC", "Objeto postado", "").addTo(list);
        return list;
    }


}
