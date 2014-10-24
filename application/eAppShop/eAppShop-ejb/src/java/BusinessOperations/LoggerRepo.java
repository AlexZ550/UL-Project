package BusinessOperations;

import Models.Log;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@JMSDestinationDefinition(name = "LogQueue", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "LogQueue")

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "LogQueue")
})
public class LoggerRepo implements MessageListener {
    
    @PersistenceContext(unitName = "eAppShop-ejbPU")
    private EntityManager em;
    
    
    
    public LoggerRepo() {
    }
    
    
    @Override
    public void onMessage(Message message) {
        System.out.println("Logger received message");
        try {
            Log logEntry = message.getBody(Log.class);
            em.persist(logEntry);
            System.err.println(logEntry.getMessage());
        } catch (JMSException ex) {
            System.err.println("Logger.onMessage handler failed.\n" + ex.toString());
        }
    }
    
}
