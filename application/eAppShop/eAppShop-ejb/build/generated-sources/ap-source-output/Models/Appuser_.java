package Models;

import Models.Comment;
import Models.Log;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-24T15:46:25")
@StaticMetamodel(Appuser.class)
public class Appuser_ { 

    public static volatile SingularAttribute<Appuser, Date> timestamp;
    public static volatile SingularAttribute<Appuser, Integer> roleid;
    public static volatile CollectionAttribute<Appuser, Comment> commentCollection;
    public static volatile SingularAttribute<Appuser, String> name;
    public static volatile SingularAttribute<Appuser, Integer> userid;
    public static volatile CollectionAttribute<Appuser, Log> logCollection;
    public static volatile SingularAttribute<Appuser, String> password;

}