package Models;

import Models.Appuser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-24T15:46:25")
@StaticMetamodel(Log.class)
public class Log_ { 

    public static volatile SingularAttribute<Log, String> message;
    public static volatile SingularAttribute<Log, Date> timestamp;
    public static volatile SingularAttribute<Log, Appuser> userid;
    public static volatile SingularAttribute<Log, Integer> logid;

}