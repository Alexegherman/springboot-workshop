package eu.accesa.client.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    @Qualifier("longTimeout")
    @Scope("prototype")
    public RestTemplate longTimeout() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        return createRestTemplate(90 * 60 * 1000);
    }

    @Bean
    @Qualifier("shortTimeout")
    @Scope("prototype")
    public RestTemplate shortTimeout() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        return createRestTemplate(5 * 1000);
    }

    private RestTemplate createRestTemplate(int readTimeout) {

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(interceptors);

        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(5000);
        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(readTimeout);

        return restTemplate;
    }


}
