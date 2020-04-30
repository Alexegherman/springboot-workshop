package eu.accesa.training.controller;

import eu.accesa.training.config.CustomProperties;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Distributed Configuration Controller")
@RestController
public class DistributedConfigurationController {

    private static Logger log = LoggerFactory.getLogger(DistributedConfigurationController.class);

    @Value("${custom.prop}")
    String value;

    @Autowired
    private CustomProperties customProperties;

    @GetMapping("/customConfigValue")
    public String getCustomConfig() {
        log.info("The property is: {}", value);
        return value;
    }

    @GetMapping("/customConfigProperty")
    public String customConfigProperty() {
        return customProperties.getProp();
    }
}
