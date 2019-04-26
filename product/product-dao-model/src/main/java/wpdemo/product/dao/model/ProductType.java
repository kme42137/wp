package wpdemo.product.dao.model;


/**
 * @author Kovacs Maria
 */
public enum ProductType {
    VEGETABLE(0, "Zöldség"),
    FRUIT(1, "Gyümölcs"),
    MEAT(2, "Hús"),
    BAKERY(3, "Pékáru"),
    DAIRY(4, "Tejtermék");
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
