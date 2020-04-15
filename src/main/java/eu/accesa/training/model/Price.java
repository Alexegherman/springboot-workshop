package eu.accesa.training.model;

import java.sql.Timestamp;
import java.util.StringJoiner;

public class Price {

    private long id;
    private Double salesPrice;
    private Integer productId;
    private int outletId;
    private Timestamp lastUpdate;
    private String currency;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public int getOutletId() {
        return outletId;
    }

    public void setOutletId(int outletId) {
        this.outletId = outletId;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Price.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("salesPrice=" + salesPrice)
                .add("productId=" + productId)
                .add("outletId=" + outletId)
                .add("lastUpdate=" + lastUpdate)
                .add("currency='" + currency + "'")
                .toString();
    }
}
