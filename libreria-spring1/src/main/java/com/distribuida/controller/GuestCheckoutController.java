package com.distribuida.controller;

import com.distribuida.model.Factura;

import com.distribuida.service.GuestCheckoutservice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guest/checkout")
public class GuestCheckoutController {
    private final GuestCheckoutservice guestCheckoutService;
    public GuestCheckoutController ( GuestCheckoutservice service) {
        this.guestCheckoutService = service;
    }
    @PostMapping
    public ResponseEntity<Factura> checkout(@RequestParam String token) {
        return ResponseEntity.ok(guestCheckoutService.checkoutByToken (token));
    }

}