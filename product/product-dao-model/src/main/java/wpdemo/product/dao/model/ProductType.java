package wpdemo.product.dao.model;


/**
 * @author Kovacs Maria
 */
public enum ProductType {
    NONE(0,""),
    VEGETABLE(1, "Zöldség"),
    FRUIT(2, "Gyümölcs"),
    MEAT(3, "Hús, hal, felvágott"),
    BAKERY(4, "Pékáru"),
    DAIRY_EGG(5, "Tejtermék, tojás"),
    SWEETS(6, "Édesség"),
    COCOA_COFFEE_TEA(7, "Kakaó, kávé, tea"),
    ALCOHOL(8, "Alkoholos italok");
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
