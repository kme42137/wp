package wpdemo.visitor.dao.model;


/**
 * @author Kovacs Maria
 */
public interface IVisitorDao {
    public Visitor create(Visitor pVisitor);
    
    public Visitor modify(long pOldVisitorId, Visitor pVisitor);

    public boolean delete(long pVisitorId);

    public Visitor get(long pVisitorId);

    public Visitor getByName(String pNickname);

    public Visitor getByEmail(String pEmail);
    
    public long getMerchantId(Visitor pVisitor);
        
}
