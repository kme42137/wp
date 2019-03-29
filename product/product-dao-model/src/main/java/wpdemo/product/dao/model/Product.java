package wpdemo.product.dao.model;

/**
 * @author Kovacs Maria
 */
public class Product {
    private long id;
    private String name;
    private long merchantId;
    private ProductType type;
    private long imageId;
    private String description;

    public Product() {
    }

    public Product(long id, String name, long merchantId, ProductType type, long imageId, String description) {
        this.id = id;
        this.name = name;
        this.merchantId = merchantId;
        this.type = type;
        this.imageId = imageId;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
