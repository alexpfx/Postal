package br.com.alexandrealessi.postal.model;

import android.os.AsyncTask;
import br.com.alexandrealessi.postal.utils.SroDTO;
import br.com.alexandrealessi.postal.utils.SroUtils;
import br.com.alexpfx.api.postal.SroInvalidoException;
import br.com.alexpfx.api.postal.dao.AgenciaIdeiaRepository;
import br.com.alexpfx.api.postal.dao.InfraException;
import br.com.alexpfx.api.postal.dao.SroRepository;
import br.com.alexpfx.api.postal.dao.SroRetornoInfo;

import java.util.List;


/**
 * Created by alex on 25/02/2015.
 */
public class SroInteractorImpl implements SroInteractor {
    private SroRepository sroRepository;


    public SroInteractorImpl() {
        this.sroRepository = new AgenciaIdeiaRepository();
    }

    @Override
    public void avaliarSro(String sroString, ResultadoAvaliacaoSroCallback resultadoCallback) {
        try {
            SroDTO sroDTO = SroUtils.getSroDTOFromCodeString(sroString);
            resultadoCallback.onCodigoSroValido(sroDTO);
        } catch (SroInvalidoException e) {
            resultadoCallback.onCodigoSroInvalido(sroString);
        }
    }

    @Override
    public void consultarCorreiosSro(final SroDTO sro, final ConsultarCorreiosSroCallback callback) {
        new AsyncTask<SroDTO, Void, List<SroRetornoInfo>>() {
            @Override
            protected List<SroRetornoInfo> doInBackground(SroDTO... params) {
                try {
                    List<SroRetornoInfo> sroRetornoInfos = SroUtils.consultarSro(params[0].toString());
                    return sroRetornoInfos;
                } catch (InfraException e) {
                    return null;
                }

            }

            @Override
            protected void onPostExecute(List<SroRetornoInfo> sroRetornoInfos) {
                if (sroRetornoInfos == null || sroRetornoInfos.isEmpty()) {
                    callback.naoEncontrado(sro);
                } else {
                    callback.receive(sro, sroRetornoInfos);
                }
            }
        }.execute(sro);
    }


}
