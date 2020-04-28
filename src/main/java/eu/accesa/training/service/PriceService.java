package eu.accesa.training.service;

import eu.accesa.training.aop.LogExecutionTime;
import eu.accesa.training.db.PriceHandler;
import eu.accesa.training.db.PriceMapper;
import eu.accesa.training.model.Price;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;

@Component
public class PriceService {

    private static Logger log = LoggerFactory.getLogger(PriceService.class);
    private int id = 10;

    private PriceMapper priceMapper;

    @Autowired
    public PriceService(PriceMapper priceMapper){
        this.priceMapper = priceMapper;
    }

    //@Scheduled(initialDelay = 10000, fixedDelay = 10000)
    public void addPrice() {
        Price price = new Price();
        price.setOutletId(9999);
        price.setCurrency("EUR");
        price.setProductId(123 + id);
        price.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        price.setSalesPrice(23.45);
        log.info("Insert price with product id: {}", price.getProductId());
        id++;
        priceMapper.insertPrice(price);
    }

    @LogExecutionTime
    public List<Price> getAllPrices() {
        return priceMapper.getPrices();
    }

    @LogExecutionTime
    public void streamPrices(HttpServletResponse response) {
        PriceHandler handler = new PriceHandler(response);
        priceMapper.getPricesStream(handler);
    }


}
