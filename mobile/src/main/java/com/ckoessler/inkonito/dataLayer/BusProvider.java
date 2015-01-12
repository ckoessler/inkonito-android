package com.ckoessler.inkonito.dataLayer;

import com.squareup.otto.Bus;

/**
 * Created by chris on 12/15/14.
 */
public class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

    private BusProvider(){

    }
}
