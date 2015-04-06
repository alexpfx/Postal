package br.com.alexandrealessi.postal.utils;

import br.com.alexpfx.api.postal.Sro;
import br.com.alexpfx.api.postal.SroFactory;
import br.com.alexpfx.api.postal.SroFactoryInterface;
import br.com.alexpfx.api.postal.SroInvalidoException;
import br.com.alexpfx.api.postal.dao.AgenciaIdeiaRepository;
import br.com.alexpfx.api.postal.dao.SroRepository;
import br.com.alexpfx.api.postal.dao.SroRetornoInfo;

import java.util.List;

/**
 * Created by alexandre on 05/04/15.
 */
public class SroUtils {

    /**
     * Verificar se Sro e completamente valido (atraves do calculo do digito verificador).
     *
     * @param sro
     * @return
     */
    public static boolean isValidSro(String sro) {
        SroFactoryInterface factory = new SroFactory();
        try {
            Sro criado = factory.criar(sro);
            return criado.isValid();
        } catch (SroInvalidoException e) {
        }
        return false;
    }

    public static SroDTO getSroDTOFromCodeString (String sroCodeString) throws IllegalArgumentException{
       SroFactoryInterface factory = new SroFactory();
        try {
            Sro criado = factory.criar(sroCodeString);
            if (!criado.isValid()){
                throw getIllegalArgumentExceptionInvalidSro(sroCodeString);
            }
            return SroDTO.create(criado.getCodigoServico().getCodigo(), String.valueOf(criado.getNumero()), criado.getPaisOrigem());
        } catch (SroInvalidoException e) {
            throw getIllegalArgumentExceptionInvalidSro(sroCodeString);
        }
    }

    private static IllegalArgumentException getIllegalArgumentExceptionInvalidSro(String sroCodeString) {
        return new IllegalArgumentException("Sro invalido: "+sroCodeString);
    }



    //TODO: eliminar dependencia de SroRetornoInfo
    public static List<SroRetornoInfo> consultarSro (String sro){
        SroRepository repository = new AgenciaIdeiaRepository();
        return repository.consultarSro(sro);
    }



}
