package com.example.service.impl;


import com.example.service.FinanceService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Singleton
public class FinanceServiceImpl implements FinanceService {

    private static Logger logger = LoggerFactory.getLogger(FinanceServiceImpl.class);

    public Object getStockPriceInSingleMode(String stockProvider, String stockIndex) {
        if (!stockProvider.equals("YAHOO")) {
            logger.error("Provider is wrong");
            return "StockProvider is wrong";
        } else {
            try {
                logger.info("GetStockPriceInSingleMode service is accessed and passed if statement");
                Stock stock = YahooFinance.get(stockIndex);
                BigDecimal price = stock.getQuote().getPrice();
                List<String> stringList = new ArrayList<>();
                stringList.add(stockIndex + ": " + price);
                return new Gson().toJson(stringList);
            } catch (Exception e) {
                logger.error("Exception occured while fethcing data from YAhoo API : " + e.getMessage());
                return "Yahoo Api error";
            }

        }

    }

    public Object getStockInBatchMode(String stockProvider, List<String> stockIndexList) {
        if (!stockProvider.equals("YAHOO")) {
            logger.error("Provider is wrong");
            return "StockProvider is wrong";
        } else {
            try {

                String[] strings = new String[stockIndexList.size()];
                stockIndexList.toArray(strings);
                logger.info("GetStockPriceInBatchMode service is accessed and passed if statement");
                Map<String, Stock> stocks = YahooFinance.get(strings);
                List<String> stockList = new ArrayList<>();
                stocks.forEach((k, v) -> stockList.add(v.getSymbol() + ": " + v.getQuote().getPrice()));
                return new Gson().toJson(stockList);

            } catch (Exception e) {
                logger.error("Exception occured while fethcing data from YAhoo API : " + e.getMessage());
                return "Yahoo Api error";
            }
        }
    }

    @Override
    public Object checkUsernameAndPassword(String username, String password) {
        if (username.equals("Sovet") && password.equals("Sovet1999"))
            return 200;
        else
            return 401;
    }


}
