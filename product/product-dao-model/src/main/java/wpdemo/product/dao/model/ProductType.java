package wpdemo.product.dao.model;


/**
 * @author Kovacs Maria
 */
public enum ProductType {
    VEGETABLE(0, "Zöldség"),
    FRUIT(1, "Gyümölcs"),
    MEAT(2, "Hús, hal, felvágott"),
    BAKERY(3, "Pékáru"),
    DAIRY_EGG(4, "Tejtermék, tojás"),
    SWEETS(5, "Édesség"),
    COCOA_COFFEE_TEA(6, "Kakaó, kávé, tea"),
    ALCOHOL(7, "Alkoholos italok");
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
