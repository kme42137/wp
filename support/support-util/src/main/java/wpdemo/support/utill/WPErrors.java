package wpdemo.support.utill;

/**
 * @author Kovacs Maria
 */
public enum WPErrors {
    EMPTY_FIELD(0,"Ezt a mezőt ki kell tölteni."),
    USED_NICKNAME(1,"Ezt az azonosítót már használja vaki."),
    USED_EMAIL(2,"Ezzel az e-mail címmel már van regisztrált felhasználó."),
    INVALID_EMAIL(3,"Nem megfelelő az e-mail cím formátuma."),
    SHORT_PASSWORD(4, "Túl rövid a megadott jelszó."),
    LOGIN_DENIED(5, "Hibás felhasználó azonosító vagy jelszó.");
    
    private final int id;
    private final String msg;

    private WPErrors(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }
}
