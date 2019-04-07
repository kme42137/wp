package wpdemo.support.utill;

/**
 * @author Kovacs Maria
 */
public class WPException extends RuntimeException{
    public String message;
    public int errCode;
   
    public WPException(WPErrors code) {
        this.message = code.getMsg();
        this.errCode = code.getId();
    }
    
     public int getErrCode() {
        return errCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
