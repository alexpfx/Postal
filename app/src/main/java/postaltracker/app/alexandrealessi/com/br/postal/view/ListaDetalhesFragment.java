package postaltracker.app.alexandrealessi.com.br.postal.view;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.alexpfx.api.postal.Sro;
import br.com.alexpfx.api.postal.SroFactory;
import br.com.alexpfx.api.postal.SroInvalidoException;
import br.com.alexpfx.api.postal.TipoSro;
import br.com.alexpfx.api.postal.dao.SroRetornoInfo;
import butterknife.ButterKnife;
import butterknife.InjectView;
import postaltracker.app.alexandrealessi.com.br.postal.R;
import postaltracker.app.alexandrealessi.com.br.postal.common.AbstractPresenter;
import postaltracker.app.alexandrealessi.com.br.postal.presenter.DetalheSroPresenter;
import postaltracker.app.alexandrealessi.com.br.postal.presenter.DetalheSroPresenterImpl;

import static postaltracker.app.alexandrealessi.com.br.postal.view.ListDetalheAdapter.ViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaDetalhesFragment extends Fragment implements SroDetalheView {

    private static final String tag = ListaDetalhesFragment.class.getSimpleName();

    DetalheSroPresenter detalhePresenter;

    private ListDetalheAdapter detalheListAdapter;

    private StatusToast toaster;

    @InjectView(R.id.edtCode)
    EditText edtCode;

    @InjectView(R.id.edtTipoServico)
    AutoCompleteTextView edtTipoServico;

    @InjectView(R.id.edtPais)
    AutoCompleteTextView edtPais;

    @InjectView(R.id.btnScanQrCode)
    ImageButton btnScanQrCode;

    @InjectView(R.id.listDetalhe)
    RecyclerView recyclerView;

    private IntentIntegrator scanIntegrator;

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity().getApplicationContext();
        toaster = StatusToast.make(context);
        View view = inflater.inflate(R.layout.fragment_lista_detalhes, container, false);
        ButterKnife.inject(this, view);
        detalhePresenter = new DetalheSroPresenterImpl();
        ((AbstractPresenter)detalhePresenter).init(this);

        configurarRecycleViews(view);
        configurarEditTexts(view);
        configurarQRCodeScanner();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AbstractPresenter)detalhePresenter).init(this);
    }

    public void onBtnScanClick() {
        scanIntegrator.setPrompt("Aponte a camera para o QRCode do objeto de rastreamento");

        scanIntegrator.getMoreExtras();
        scanIntegrator.initiateScan();
    }


    private void configurarQRCodeScanner() {
        scanIntegrator = IntentIntegrator.forFragment(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String contents = scanResult.getContents();
            if (contents != null) {
                Sro sro;
                try {
                    Log.d(tag, contents);
                    sro = new SroFactory().criar(contents);
                    if (sro.isValid()) {
                        mostrarSroScaneado(sro);
                    } else {
                        mostrarQueEhInvalido();
                    }
                } catch (SroInvalidoException e) {
                    mostrarQueEhInvalido();
                }
            }

        }

    }

    private ArrayAdapter<String> criarStringArrayAdapter(List<String> arrayList) {
        return new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, criarArrayTipoServico());
    }

    private void atribuirListAdapter(AutoCompleteTextView editText, ArrayAdapter<String> arrayAdapter) {
        editText.setAdapter(arrayAdapter);
    }


    /**
     * Edits devem ser passados em ordem.
     *
     * @param edts
     */
    public void configurarTextWatcher(EditText... edts) {

        for (EditText edt : edts) {
            edt.addTextChangedListener(new TextWatcherAdapter(this));
        }


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void configurarRecycleViews(View v) {
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(manager);
        detalheListAdapter = new ListDetalheAdapter(this.getActivity().getApplicationContext(), new ArrayList<ViewModel>());
        recyclerView.setAdapter(detalheListAdapter);
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), s));

    }

    private void configurarEditTexts(View v) {
        atribuirListAdapter(edtTipoServico, criarStringArrayAdapter(criarArrayTipoServico()));
        atribuirListAdapter(edtPais, criarStringArrayAdapter(criarArrayCodigoPais()));
        configurarTextWatcher(edtTipoServico, edtCode, edtPais);
    }


    private List<String> criarArrayTipoServico() {
        List<String> tipos = new ArrayList<>();
        for (TipoSro tipo : TipoSro.values()) {
            tipos.add(tipo.getCodigo());
        }
        return tipos;
    }

    //TODO: pegar lista possivel de paises.
    private List<String> criarArrayCodigoPais() {
        List<String> tipos = new ArrayList<>();
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
        toaster.error("SRO Incorreto.");

    }

    @Override
    public void mostrarQueEhValido() {

        toaster.info("Sro Válido, buscando informações...");
    }

    @Override
    public void mostrarDetalhesRecebidos(Sro sro, List<SroRetornoInfo> listaInfos) {
        ArrayList<ViewModel> lista = new ArrayList<>();
        for (SroRetornoInfo rinfo : listaInfos) {
            String info = SimpleDateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(rinfo.getData()).concat(" ").concat(rinfo.getLocal());
            String status = rinfo.getAcao();
            String detalhe = rinfo.getDetalhes();
            ViewModel.create(info, status, detalhe).addTo(lista);
        }
        detalheListAdapter.setModelItemList(lista);
        detalheListAdapter.notifyDataSetChanged();
        toaster.success("código de rastreio encontrado: " + sro);
        esconderTeclado();
    }

    private void esconderTeclado() {
        InputMethodManager m = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        m.hideSoftInputFromWindow(edtCode.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    @Override
    public void mostrarDetalhesNaoEncontrados(Sro sro) {
        toaster.info("não foram encontradas informações para o pacote " + sro);
        esconderTeclado();
    }

    @Override
    public void mostrarSroScaneado(Sro sro) {
        this.edtTipoServico.setText(sro.getCodigoServico().getCodigo());
        this.edtPais.setText(sro.getPaisOrigem());
        this.edtCode.setText(sro.getNumero().toString() + sro.getDigitoVerificador().toString());
    }

    @Override
    public void onQrCodeChange() {
        Log.d(tag, getQroCode());
        if (getQroCode().length() != 13) {
            if (detalheListAdapter.getItemCount() > 0) {
                detalheListAdapter.clear();
                detalheListAdapter.notifyDataSetChanged();
            }
            return;
        }
        detalhePresenter.verificarValidadeSro(getQroCode());
    }

    public synchronized String getQroCode() {
        return edtTipoServico.getText().toString() + edtCode.getText().toString() + edtPais.getText().toString();
    }
}
