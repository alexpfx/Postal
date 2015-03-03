package postaltracker.app.alexandrealessi.com.br.postal.view;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.alexpfx.api.postal.Sro;
import br.com.alexpfx.api.postal.SroFactory;
import br.com.alexpfx.api.postal.SroInvalidoException;
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
    private ListDetalheAdapter detalheListAdapter;
    private StatusToast toaster;

    private EditText edtCode;
    private AutoCompleteTextView edtTipoServico;
    private AutoCompleteTextView edtPais;
    private ImageButton btnScanQrCode;
    private IntentIntegrator scanIntegrator;
    private View toastPosition;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getActivity().getApplicationContext();
        detalhePresenter = new DetalheSroPresenterImpl(this);
        toaster = StatusToast.make(context);


        View v = inflater.inflate(R.layout.fragment_pacote, container, false);
        configurarRecycleViews(v);
        configurarTextViews(v);
        configurarEditTexts(v);
        configurarQRCodeScanner();
        configurarBotoes(v);
        toastPosition = v.findViewById(R.id.layToastPosition);
        return v;
    }

    private void configurarTextViews(View v) {


    }

    private void configurarBotoes(View v) {

        btnScanQrCode = (ImageButton) v.findViewById(R.id.btnScanQrCode);
        btnScanQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanIntegrator.setPrompt("Aponte a camera para o QRCode do objeto de rastreamento");
//                scanIntegrator.setCaptureLayout(R.layout.custom_capture_layout);
                Map<String, ?> moreExtras = scanIntegrator.getMoreExtras();

                scanIntegrator.initiateScan();

            }
        });
    }

    private void configurarQRCodeScanner() {
        scanIntegrator = IntentIntegrator.forSupportFragment(this);


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
        return new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, criarArrayTipoServico());
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
        RecyclerView listDetalhe = (RecyclerView) v.findViewById(R.id.listDetalhe);
        listDetalhe.setHasFixedSize(false);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        listDetalhe.setLayoutManager(manager);
        detalheListAdapter = new ListDetalheAdapter(this.getActivity().getApplicationContext(), new ArrayList<ViewModel>());
        listDetalhe.setAdapter(detalheListAdapter);
        listDetalhe.addItemDecoration(new ListDetalheDividersItemDecoration());

    }

    private void configurarEditTexts(View v) {
        edtTipoServico = (AutoCompleteTextView) v.findViewById(R.id.edtTipoServico);
        edtPais = (AutoCompleteTextView) v.findViewById(R.id.edtPais);
        atribuirListAdapter(edtTipoServico, criarStringArrayAdapter(criarArrayTipoServico()));
        atribuirListAdapter(edtPais, criarStringArrayAdapter(criarArrayCodigoPais()));
        edtCode = (EditText) v.findViewById(R.id.edtCode);
        configurarTextWatcher(edtTipoServico, edtCode, edtPais);

    }


    private List<String> criarArrayTipoServico() {
        List<String> tipos = new ArrayList<String>();
        for (TipoSro tipo : TipoSro.values()) {
            tipos.add(tipo.getCodigo());
        }
        return tipos;
    }

    //TODO: pegar lista possivel de paises.
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
        toaster.error("SRO Inválido");

    }

    @Override
    public void mostrarQueEhValido() {
        toaster.info("Sro Válido. Buscando...");
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
        toaster.success("Detalhes encontrados ");
        esconderTeclado();
    }

    private void esconderTeclado() {
        InputMethodManager m = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        m.hideSoftInputFromWindow(edtCode.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    @Override
    public void mostrarDetalhesNaoEncontrados(Sro sro) {

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
            mostrarQueEhInvalido();
            return;
        }
        detalhePresenter.verificarValidadeSro(getQroCode());
    }

    public synchronized String getQroCode() {
        return edtTipoServico.getText().toString() + edtCode.getText().toString() + edtPais.getText().toString();
    }
}
