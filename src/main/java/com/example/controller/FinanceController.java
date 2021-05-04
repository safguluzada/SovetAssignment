package com.example.controller;


import io.micronaut.http.HttpResponse;

import java.util.List;

public interface FinanceController {

    HttpResponse getStockPriceSingleMode(String stockProvider, String stockIndex,String username, String password);

    HttpResponse getStockPriceBatchMode(String stockProvider, List<String> stockIndex,String username, String password);
}
