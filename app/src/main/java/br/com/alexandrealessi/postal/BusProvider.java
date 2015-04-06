package br.com.alexandrealessi.postal;

import com.squareup.otto.Bus;

/**
 * Created by alexandre on 23/03/15.
 */
public class BusProvider {
    private static Bus bus = new Bus();


    public static Bus getInstance (){
        return bus;
    }

    private BusProvider (){

    }

}
