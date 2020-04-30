package eu.accesa.client.price;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Component
public class PriceClient {

    private static final Logger log = LoggerFactory.getLogger(PriceClient.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    @Qualifier("shortTimeout")
    private RestTemplate restTemplate;

    @Scheduled(initialDelay = 10000, fixedDelay = 10000)
    public void getPrices() throws ServiceUnavailableException {
        URI serviceWorkshop = serviceUrl().map(s -> s.resolve("/price/all")).orElseThrow(ServiceUnavailableException::new);
        List<Price> prices = (List<Price>) restTemplate.getForObject(serviceWorkshop, List.class);

        if (prices != null) {
            log.info("Returned a list of prices with size: {}", prices.size());
        }
    }

    private Optional<URI> serviceUrl() {
        return discoveryClient.getInstances("spring-workshop-dev")
                .stream()
                .findFirst()
                .map(ServiceInstance::getUri);
    }
}
