package br.com.alexandrealessi.postal.utils;

/**
 * Created by alexandre on 05/04/15.
 */
public class SroDTO {
    private String serviceType;
    private String codeNumber;
    private String country;


    private SroDTO(String serviceType, String codeNumber, String country) {
        this.serviceType = serviceType;
        this.codeNumber = codeNumber;
        this.country = country;
    }


    public String getServiceType() {
        return serviceType;
    }

    public String getCodeNumber() {
        return codeNumber;
    }

    public String getCountry() {
        return country;
    }

    public static SroDTO create(String serviceType, String code, String country) {
        return new SroDTO(serviceType, code, country);
    }

    @Override
    public String toString() {
        return serviceType + codeNumber + country;
    }
}
