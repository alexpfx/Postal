package postaltracker.app.alexandrealessi.com.br.postal.view;


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

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.alexpfx.api.postal.SRO;
import postaltracker.app.alexandrealessi.com.br.postal.R;
import postaltracker.app.alexandrealessi.com.br.postal.model.SroRetornoInfo;
import postaltracker.app.alexandrealessi.com.br.postal.presenter.DetalheSroPresenter;
import postaltracker.app.alexandrealessi.com.br.postal.presenter.DetalheSroPresenterImpl;

import static postaltracker.app.alexandrealessi.com.br.postal.view.ListDetalheAdapter.ViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class PacoteFragment extends Fragment implements SroDetalheView {
    private DetalheSroPresenter detalhePresenter;
    private TextView txtSroStatusInfo;
    private EditText edtCode;
    private ListDetalheAdapter detalheListAdapter;

    public PacoteFragment() {


    }


     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        detalhePresenter = new DetalheSroPresenterImpl(this);
        View v = inflater.inflate(R.layout.fragment_pacote, container, false);

        RecyclerView listDetalhe = (RecyclerView) v.findViewById(R.id.listDetalhe);
        listDetalhe.setHasFixedSize(false);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        listDetalhe.setLayoutManager(manager);

        detalheListAdapter = new ListDetalheAdapter(this.getActivity().getApplicationContext(),createFakeList ());
        listDetalhe.setAdapter(detalheListAdapter);

        txtSroStatusInfo = (TextView) v.findViewById(R.id.txtSroStatusInfo);
        edtCode = (EditText) v.findViewById(R.id.edtCode);
        edtCode.addTextChangedListener(edtCodeTextWatcher);
        return v;
    }

    private TextWatcher edtCodeTextWatcher = new TextWatcherAdapter() {
        @Override
        public void afterTextChanged(Editable s) {
            String code = StringUtils.deleteWhitespace(s.toString());
            if (code.length() != 13){
                  mostrarQueEhInvalido();
                  return;
            }
            detalhePresenter.verificarValidadeSro(code);
        }
    } ;




    @Override
    public void mostrarQueEhInvalido() {
        txtSroStatusInfo.setText("Digite um SRO Válido.");
    }

    @Override
    public void mostrarQueEhValido() {
        txtSroStatusInfo.setText("SRO Válido. Buscando informações...");
    }

    @Override
    public void mostrarDetalhesRecebidos(List<SroRetornoInfo> listaInfos) {
        List<ViewModel> lista = new ArrayList<>();
        for (SroRetornoInfo rinfo:listaInfos){
            String info = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(rinfo.getData()).concat(" ").concat(rinfo.getLocal());
            String status = rinfo.getAcao();
            String detalhe = rinfo.getDetalhes();
            ViewModel.create(info, status, detalhe).addTo(lista);
        }
        detalheListAdapter.setModelItemList(lista);
    }

    @Override
    public void mostrarDetalhesNaoEncontrados(SRO sro) {
        txtSroStatusInfo.setText("Nao encontrados detalhes para o pacote ".concat(sro.getNumero().toString()).concat(" no sistema de rastreamento dos correios. "));
    }

    //TODO remover
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
