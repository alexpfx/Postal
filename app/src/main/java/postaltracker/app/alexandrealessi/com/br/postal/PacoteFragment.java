package postaltracker.app.alexandrealessi.com.br.postal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static postaltracker.app.alexandrealessi.com.br.postal.ListDetalheAdapter.ViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class PacoteFragment extends Fragment implements DetalheSroView{
    private ShowPacoteDetalhadoPresenter detalhePresenter;
    private TextView txtSroStatusInfo;
    private EditText edtCode;
    public PacoteFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        detalhePresenter = new ShowPacoteDetalhadoPresenetarImpl(this);
        View v = inflater.inflate(R.layout.fragment_pacote, container, false);

        RecyclerView listDetalhe = (RecyclerView) v.findViewById(R.id.listDetalhe);
        listDetalhe.setHasFixedSize(false);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        listDetalhe.setLayoutManager(manager);

        ListDetalheAdapter adapter = new ListDetalheAdapter(this.getActivity().getApplicationContext(),createFakeList ());
        listDetalhe.setAdapter(adapter);

        txtSroStatusInfo = (TextView) v.findViewById(R.id.txtSroStatusInfo);
        edtCode = (EditText) v.findViewById(R.id.edtCode);
        edtCode.addTextChangedListener(edtCodeTextWatcher);
        return v;
    }

    private TextWatcher edtCodeTextWatcher = new TextWatcherAdapter() {
        @Override
        public void afterTextChanged(Editable s) {
            System.out.println(s);
            if (s.length() < 13){
                  return;
            }
            detalhePresenter.buscarSroValido(s.toString());
        }
    } ;


    private List<ViewModel> createFakeList() {
        List<ViewModel> list = new ArrayList<>();
        ViewModel.create("21/11/2014 17:48 Palhoca / SC", "Objeto entregue ao destinatário ", "").addTo(list);
        ViewModel.create("21/11/2014 10:26 Palhoca / SC", "Objeto saiu para entrega ao destinatário ", "").addTo(list);
        ViewModel.create("21/11/2014 06:39 Sao Jose / SC", "Objeto encaminhado ", "de Unidade Operacional em Sao Jose / SC para Unidade de Distribuição em Palhoca / SC ").addTo(list);
        ViewModel.create("20/11/2014 17:57 Florianopolis / SC", "Objeto encaminhado ", "de Agência dos Correios em Florianopolis / SC para Unidade Operacional em Sao Jose / SC ").addTo(list);
        ViewModel.create("20/11/2014 16:20 Florianopolis / SC", "Objeto postado", "").addTo(list);
        return list;
    }


    @Override
    public void mostrarQueEhInvalido() {
        txtSroStatusInfo.setText("Digite um SRO Válido.");
    }

    @Override
    public void mostrarQueEhValido() {
        txtSroStatusInfo.setText("SRO Válido. Buscando informações...");
    }
}
