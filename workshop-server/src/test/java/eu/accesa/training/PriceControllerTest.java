package eu.accesa.training;

import eu.accesa.training.controller.PriceController;
import eu.accesa.training.model.Price;
import eu.accesa.training.model.TestUser;
import eu.accesa.training.service.PriceService;
import eu.accesa.training.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PriceController.class)
public class PriceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PriceService priceService;

    @MockBean
    private UserService userService;

    @Test
    public void getPricesTest() throws Exception {

        Price price = new Price();
        price.setOutletId(9999);
        price.setCurrency("EUR");
        price.setProductId(123);
        price.setLastUpdate(System.currentTimeMillis());
        price.setSalesPrice(23.45);

        TestUser user = new TestUser();
        user.setUsername("alex");

        List<Price> prices = Arrays.asList(price);

        given(priceService.getAllPrices()).willReturn(prices);
        given(userService.findByUsername(anyString())).willReturn(user);

        mvc.perform(get("/price/all")
                .contentType("application/json"))
                .andExpect(status().isOk());

    }

}
