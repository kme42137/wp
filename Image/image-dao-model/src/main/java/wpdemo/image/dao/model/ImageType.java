package wpdemo.image.dao.model;

/**
 * @author Kovacs Maria
 */
public enum ImageType {
    PRODUCT(0),
    MERCHANT_BIG(1),
    MERCHANT_SMALL_1(2),
    MERCHANT_SMALL_2(3);
    private final int id;

    ImageType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
 
}
