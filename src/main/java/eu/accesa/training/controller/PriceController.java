package eu.accesa.training.controller;


import eu.accesa.training.model.Price;
import eu.accesa.training.service.PriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "PriceController")
@RestController
public class PriceController {

    private PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @ApiOperation("Get all prices")
    @GetMapping("/price/all")
    public List<Price> getAllPrices() {
        return priceService.getAllPrices();
    }

    @ApiOperation("Stream all prices")
    @GetMapping("/price/stream")
    public void streamPrices(HttpServletResponse response) {
        priceService.streamPrices(response);
    }

}
