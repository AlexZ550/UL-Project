package Models;

import Models.Appuser;
import Models.Product;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-24T15:46:25")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, Integer> commentid;
    public static volatile SingularAttribute<Comment, Date> timestamp;
    public static volatile SingularAttribute<Comment, String> text;
    public static volatile SingularAttribute<Comment, Product> productid;
    public static volatile SingularAttribute<Comment, Appuser> userid;

}