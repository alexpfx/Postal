package postaltracker.app.alexandrealessi.com.br.postal.view;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;

import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import postaltracker.app.alexandrealessi.com.br.postal.R;
import postaltracker.app.alexandrealessi.com.br.postal.common.AbstractPresenter;
import postaltracker.app.alexandrealessi.com.br.postal.model.PacotesInteractorImpl;
import postaltracker.app.alexandrealessi.com.br.postal.model.domain.ItemAcao;
import postaltracker.app.alexandrealessi.com.br.postal.model.domain.Pacote;
import postaltracker.app.alexandrealessi.com.br.postal.presenter.ListaPacotesPresenter;
import postaltracker.app.alexandrealessi.com.br.postal.presenter.ListaPacotesPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaPacotesFragment extends Fragment implements ListaPacotesView {

    @InjectView(R.id.rcvPacotes)
    RecyclerView rcvListaPacotes;

    @InjectView(R.id.tvFiltro)
    TextView tvFiltro;


    private ListPacotesAdapter adapter;

    private ListaPacotesPresenter listaPacotesPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveDdInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_pacotes, container, false);
        ButterKnife.inject(this, view);
        configurarPresenter();
        rcvListaPacotes.setHasFixedSize(false);


        LinearLayoutManager lm = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvListaPacotes.setLayoutManager(lm);

        adapter = new ListPacotesAdapter(new ArrayList<ListPacotesAdapter.Item>(){
            {
                add (new ListPacotesAdapter.Item("saiu para entrega", new Date (), "DM 123456789 BR", "Palhoca",new ArrayList<String>(){
                    {
                        add("livros");
                        add("submarino");
                    }
                }));
                add (new ListPacotesAdapter.Item("entregue", new Date (), "DM 123456789 BR", "Palhoca", new ArrayList<String>(){
                    {
                        add("tenis");
                        add("by tennis");
                    }
                }));
                add (new ListPacotesAdapter.Item("aguardando", new Date (), "DM 484945454 BR", "Sao Miguel do Oeste", new ArrayList<String>(){
                    {
                        add("submarino");
                        add("celular");
                    }
                }));
                add (new ListPacotesAdapter.Item("aguardando", new Date (), "DM 484945454 BR", "Sao Miguel do Oeste", new ArrayList<String>(){
                    {
                        add("submarino");
                        add("celular");
                    }
                }));

                add (new ListPacotesAdapter.Item("aguardando", new Date (), "DM 484945454 BR", "Sao Miguel do Oeste", new ArrayList<String>(){
                    {
                        add("submarino");
                        add("celular");
                    }
                }));

            }
        });
        rcvListaPacotes.setAdapter(adapter);
        return view;
    }

    private void configurarPresenter() {
        listaPacotesPresenter = new ListaPacotesPresenterImpl();
        listaPacotesPresenter.setPacotesInteractor(new PacotesInteractorImpl());
        ((AbstractPresenter) listaPacotesPresenter).init(this);
    }


    @Override
    public void mostrarListaPacotes(Pacote[] pacotes) {
        adapter.getItems().clear();
        for (Pacote pacote : pacotes) {
            ItemAcao itemAcao = pacote.obterItemAcaoRecente();
            String acao = itemAcao.getAcao().getDescricao();
            Date dataAcao = itemAcao.getData();
            adapter.getItems().add(new ListPacotesAdapter.Item(acao, dataAcao, pacote.getSro(), itemAcao.getLocal().getDescricao(), pacote.getTags()));
        }
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btnLimparFiltro)
    public void btnLimparFiltrosClick() {
        tvFiltro.setText("");
    }

    @OnClick(R.id.btnRefresh)
    public void btnRefreshClick (){
        listaPacotesPresenter.requestListaPacotes();
    }

    @OnClick(R.id.btnOpenCadastroPacotes)
    public void btnOpenCadastroPacotesClick () {
    }

}



