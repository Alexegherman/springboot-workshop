package eu.accesa.training.db;

import eu.accesa.training.model.Price;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PriceHandler  implements ResultHandler<Price> {
    private static Logger log = LoggerFactory.getLogger(PriceHandler.class);

    private HttpServletResponse response;

    public PriceHandler(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void handleResult(ResultContext<? extends Price> resultContext) {
        try {
            response.getOutputStream().write(((Price) resultContext.getResultObject()).toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(" >>> Streamed price: {}", resultContext.getResultObject());
    }

}