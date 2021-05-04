package com.example.service;

import java.util.List;

public interface FinanceService {

    Object getStockPriceInSingleMode(String stockProvider, String stockIndex);

    Object getStockInBatchMode(String stockProvider, List<String> stockIndexList);

    Object checkUsernameAndPassword(String username, String password);
}
