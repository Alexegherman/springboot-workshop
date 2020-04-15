package eu.accesa.training.db;

import eu.accesa.training.model.Price;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PriceMapper {

    @Insert("INSERT INTO price ( outletId, productId, salesprice, lastupdate, currency) " +
            "VALUES (#{price.outletId}, #{price.productId}, #{price.salesPrice}, #{price.lastUpdate}, #{price.currency})")
    void insertPrice(@Param("price") Price price);

    @Select("SELECT * FROM price")
    @ResultType(Price.class)
    List<Price> getPrices();

    @ResultType(Price.class)
    @Options(fetchSize = 100)
    @Select("SELECT * FROM price")
    void getPricesStream(PriceHandler priceHandler);
}
