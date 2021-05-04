package com.example.controller.impl;

import com.example.controller.FinanceController;
import com.example.service.FinanceService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

@Controller("/finance")
public class FinanceControllerImpl implements FinanceController {

    private static Logger logger = LoggerFactory.getLogger(FinanceControllerImpl.class);

    @Inject
    private FinanceService financeService;

    @Override
    @Get("/singleMode")
    public HttpResponse<?> getStockPriceSingleMode(@QueryValue String stockProvider, @QueryValue String stockIndex,@QueryValue String username,@QueryValue String password) {
        Integer code = (Integer) financeService.checkUsernameAndPassword(username,password);
        if (code==200) {
            logger.info("SingleMode endpoint accessed");
            return HttpResponse.ok(financeService.getStockPriceInSingleMode(stockProvider, stockIndex));
        }
        else
            return HttpResponse.unauthorized();
    }

    @Override
    @Get("/batchMode")
    public HttpResponse<?> getStockPriceBatchMode(@QueryValue String stockProvider, @QueryValue List<String> stockIndex,@QueryValue String username,@QueryValue String password) {
        Integer code = (Integer) financeService.checkUsernameAndPassword(username,password);
        if (code==200) {
            logger.info("BatchMode endpoint accessed");
            return HttpResponse.ok(financeService.getStockInBatchMode(stockProvider, stockIndex));
        }
        else
        return HttpResponse.unauthorized();

    }

}
