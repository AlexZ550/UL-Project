package Models;

import Models.Comment;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-24T15:46:25")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Date> timestamp;
    public static volatile SingularAttribute<Product, Integer> price;
    public static volatile SingularAttribute<Product, Integer> productid;
    public static volatile CollectionAttribute<Product, Comment> commentCollection;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Integer> qty;
    public static volatile SingularAttribute<Product, String> imageurl;

}