package wpdemo.image.dao.model;

/**
 * @author Kovacs Maria
 */
public class Image {
    private long id;
    private ImageType type;
    private long contactId;

    public Image() {
    }

    public Image(long id, ImageType type, long contactId) {
        this.id = id;
        this.type = type;
        this.contactId = contactId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }
    
    
}
