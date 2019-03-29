package wpdemo.product.dao.model;


/**
 * @author Kovacs Maria
 */
public enum ProductType {
    VEGETABLE(0, "Zoldseg"),
    FRUIT(1, "Gyumolcs"),
    MEAT(2, "Hus"),
    BAKERY(3, "Pekaru");
    private final int id;
    private final String msg;

    ProductType(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return this.id;
    }

    public String getMsg() {
        return this.msg;
    }
}
