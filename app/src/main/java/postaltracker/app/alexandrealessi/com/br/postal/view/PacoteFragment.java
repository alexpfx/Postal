package postaltracker.app.alexandrealessi.com.br.postal.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.alexpfx.api.postal.Sro;
import br.com.alexpfx.api.postal.TipoSro;
import br.com.alexpfx.api.postal.dao.SroRetornoInfo;
import postaltracker.app.alexandrealessi.com.br.postal.R;
import postaltracker.app.alexandrealessi.com.br.postal.presenter.DetalheSroPresenter;
import postaltracker.app.alexandrealessi.com.br.postal.presenter.DetalheSroPresenterImpl;

import static postaltracker.app.alexandrealessi.com.br.postal.view.ListDetalheAdapter.ViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class PacoteFragment extends Fragment implements SroDetalheView {
    private static final String tag = PacoteFragment.class.getSimpleName();
    private DetalheSroPresenter detalhePresenter;
    private TextView txtSroStatusInfo;
    private ListDetalheAdapter detalheListAdapter;

    private EditText edtCode;
    private AutoCompleteTextView edtTipoServico;
    private AutoCompleteTextView edtPais;

    public PacoteFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        detalhePresenter = new DetalheSroPresenterImpl(this);
        View v = inflater.inflate(R.layout.fragment_pacote, container, false);

        RecyclerView listDetalhe = (RecyclerView) v.findViewById(R.id.listDetalhe);
        listDetalhe.setHasFixedSize(false);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        listDetalhe.setLayoutManager(manager);

        detalheListAdapter = new ListDetalheAdapter(this.getActivity().getApplicationContext(), new ArrayList<ViewModel>());
        listDetalhe.setAdapter(detalheListAdapter);
        listDetalhe.addItemDecoration(new ListDetalheDividersItemDecoration());


        txtSroStatusInfo = (TextView) v.findViewById(R.id.txtSroStatusInfo);
        edtCode = (EditText) v.findViewById(R.id.edtCode);


        ArrayAdapter<String> adapterEdtTipoServico = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, criarArrayTipoServico());
        ArrayAdapter<String> adapterEdtPais = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, criarArrayCodigoPais());

        edtTipoServico = (AutoCompleteTextView) v.findViewById(R.id.edtTipoServico);
        edtTipoServico.setAdapter(adapterEdtTipoServico);
        edtPais = (AutoCompleteTextView) v.findViewById(R.id.edtPais);
        edtPais.setAdapter(adapterEdtPais);


        TextWatcher validadorSroTextWatcher = new TextWatcherAdapter(edtTipoServico.getText(), edtCode.getText(), edtPais.getText()) {
            @Override
            public void afterTextChanged(Editable s) {
                String code = StringUtils.deleteWhitespace(getConcatText(s));
                Log.d(tag,code);
                if (code.length() != 13) {
                    mostrarQueEhInvalido();
                    return;
                }
                detalhePresenter.verificarValidadeSro(code);
            }
        };


        edtCode.addTextChangedListener(validadorSroTextWatcher);
        edtTipoServico.addTextChangedListener(validadorSroTextWatcher);
        edtPais.addTextChangedListener(validadorSroTextWatcher);


        return v;
    }

    private List<String> criarArrayTipoServico() {
        List<String> tipos = new ArrayList<String>();
        for (TipoSro tipo : TipoSro.values()) {
            tipos.add(tipo.getCodigo());
        }
        return tipos;
    }

    private List<String> criarArrayCodigoPais() {
        List<String> tipos = new ArrayList<String>();
        tipos.add("BR");
        tipos.add("CN");
        tipos.add("US");
        tipos.add("UR");
        tipos.add("CZ");
        tipos.add("AR");
        return tipos;
    }


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
        for (SroRetornoInfo rinfo : listaInfos) {
            String info = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(rinfo.getData()).concat(" ").concat(rinfo.getLocal());
            String status = rinfo.getAcao();
            String detalhe = rinfo.getDetalhes();
            ViewModel.create(info, status, detalhe).addTo(lista);
        }
        detalheListAdapter.setModelItemList(lista);
        detalheListAdapter.notifyDataSetChanged();
        txtSroStatusInfo.setText("Detalhes para o pacote encontrados");
        InputMethodManager m = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        m.hideSoftInputFromWindow(txtSroStatusInfo.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);

    }

    @Override
    public void mostrarDetalhesNaoEncontrados(Sro sro) {
        txtSroStatusInfo.setText("Nao encontrados detalhes para o pacote ".concat(sro.toString()).concat(" no sistema de rastreamento dos correios. "));
    }


}
