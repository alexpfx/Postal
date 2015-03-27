package br.com.alexandrealessi.postal.view;


import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import br.com.alexandrealessi.postal.R;
import br.com.alexandrealessi.postal.TextWatcherAdapter;
import br.com.alexandrealessi.postal.custom_views.SroEdtText;
import br.com.alexpfx.api.postal.NenhumSroValidoException;
import br.com.alexpfx.api.postal.Sro;
import br.com.alexpfx.api.postal.SroFactory;
import br.com.alexpfx.api.postal.SroInvalidoException;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroPacotesFragment extends Fragment {

    private static final int SRO_LENGTH = 13;
    private MenuItem actionPaste;
    private MenuItem actionAccept;
    private MenuItem actionCancel;

    @InjectView(R.id.edtSro)
    SroEdtText edtSro;

    @InjectView(R.id.edtSroList)
    EditText edtSroList;

    public CadastroPacotesFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_cadastro_pacotes, container, false);
        ButterKnife.inject(this, view);

        setupEdtSro();
        return view;

    }

    private void setupEdtSro() {
        edtSro
                .addTextChangelistener(new SroEdtText.SroEdtTextWatcher() {
                    @Override
                    public void sroChanged(Editable serviceType, Editable number, Editable country, String newSro) {
//                        showHideCancelMenuItem(newSro);
                        showHideAcceptButton(newSro);
                    }
                });
        edtSroList.addTextChangedListener(new TextWatcherAdapter(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //showHideAcceptButton(edtSro);
            }
        });
    }

    /*
    TODO: Comentado pois este método se torna complexo tratando a alteração dos dois Inputs. Colocar botão individual ou deixar o botão sempre visivel.
    Botão accept deve estar escondido por default.
    Deve ser mostrado quando:
        - Há um sro válido digitado no primeiro input.
        - Há pelo menos um sro válido no segundo input.
    Deve ser/permanecer escondido quando:
        - Não há SROs válidos em nenhum dos inputs.
     */
    private void showHideAcceptButton(String newSro) {
        List<Sro> listSro;
        Sro sro;
        if (newSro.length() == SRO_LENGTH) {
            try {
                sro = new SroFactory().criar(newSro);
            } catch (SroInvalidoException e) {

            }

        } else if (newSro.length() > SRO_LENGTH) {
            try {
                listSro = new SroFactory().criarListaDescartarInvalidos(newSro);
            } catch (NenhumSroValidoException e) {

            }
        }
    }

    /*
    //TODO: Comentado pois este método se torna complexo tratando a alteração dos dois Inputs. Colocar botão individual ou deixar o botão sempre visivel.
    private void showHideCancelMenuItem(String newSro) {
        actionCancel.setVisible(!newSro.isEmpty());
    }
*/


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_cadastro_pacotes, menu);
        actionPaste = menu.findItem(R.id.action_paste);
        actionAccept = menu.findItem(R.id.action_accept);
        actionCancel = menu.findItem(R.id.action_cancel);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_qrread:
                openQrCodeReader();
                return true;
            case R.id.action_paste:
                pasteFromClipboard();
                return true;
            case R.id.action_cancel:
                clearEditTexts();
                return true;
            case R.id.action_accept:
                addValidSroToList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Acionado a partir do botão accept.
     * Adiciona os Sros válidos contido em qualquer um dos inputs.
     */
    private void addValidSroToList() {


    }

    /**
     * Limpa os campos de input. Chamado quando o item de menu 'cancelar' é acionado.
     */
    private void clearEditTexts() {
        edtSro.clear();
        edtSroList.setText("");
    }

    /**
     * Colar no input correspondente. Se tamanho do texto no clipoard == 13, na primeira. Se maior na segunda. Caso contrário não colar e mostrar mensagem avisando que
     * o dado é inválido.
     */
    private void pasteFromClipboard() {

    }

    /**
     * Procedimento para abrir Intent que le um Qr Code.
     */
    private void openQrCodeReader() {
    }
}
