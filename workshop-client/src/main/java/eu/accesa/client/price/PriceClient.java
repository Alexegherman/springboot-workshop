package eu.accesa.client.price;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class PriceClient {

    private static final Logger log = LoggerFactory.getLogger(PriceClient.class);

    @Autowired
    @Qualifier("shortTimeout")
    private RestTemplate restTemplate;

    @Scheduled(initialDelay = 10000, fixedDelay = 10000)
    public void getPrices() {
        List<Price> prices = (List<Price>) restTemplate.getForObject("http://localhost:11433/price/all", List.class);

        if (prices != null) {
            log.info("Returned a list of prices with size: {}", prices.size());
        }


    }
}
