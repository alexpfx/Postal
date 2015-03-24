package br.com.alexandrealessi.postal.model;

import android.os.AsyncTask;

import java.util.List;

import br.com.alexpfx.api.postal.Sro;
import br.com.alexpfx.api.postal.SroFactory;
import br.com.alexpfx.api.postal.SroInvalidoException;
import br.com.alexpfx.api.postal.dao.AgenciaIdeiaRepository;
import br.com.alexpfx.api.postal.dao.InfraException;
import br.com.alexpfx.api.postal.dao.SroRepository;
import br.com.alexpfx.api.postal.dao.SroRetornoInfo;


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
            Sro objetoSro = new SroFactory().criar(sroString);
            if (objetoSro.isValid()) {
                resultadoCallback.onCodigoSroValido(objetoSro);
            } else {
                resultadoCallback.onCodigoSroInvalido(sroString);
            }
        } catch (SroInvalidoException e) {
            resultadoCallback.onCodigoSroInvalido(sroString);
        }

    }

    @Override
    public void consultarCorreiosSro(final Sro sro, final ConsultarCorreiosSroCallback callback) {
        new AsyncTask<Sro, Void, List<SroRetornoInfo>>() {
            @Override
            protected List<SroRetornoInfo> doInBackground(Sro... params) {
                try {
                    List<SroRetornoInfo> sroRetornoInfos = sroRepository.consultarSro(params[0]);
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
