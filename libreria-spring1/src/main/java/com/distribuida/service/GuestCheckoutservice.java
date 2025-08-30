package com.distribuida.service;

import com.distribuida.model.Factura;

public interface GuestCheckoutservice {
    Factura checkoutByToken(String token);

}
