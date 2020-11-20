package in;

import entity.StringMessage;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Random;

@MessageDriven(name = "PutOnStringMDB",activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/string_queue")
})
@TransactionManagement(value= TransactionManagementType.BEAN)
public class PutOnStringMDB implements MessageListener {

    @PersistenceUnit(unitName = "input-message")
    private EntityManagerFactory entityManagerFactory; // need for db


    @Override
    public void onMessage(Message message) {

        TextMessage textMessage = (TextMessage) message;
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            StringMessage stringMessage = new StringMessage();

            stringMessage.setId(new Random().nextInt(1000)+0);
            stringMessage.setName(textMessage.getText());

            entityManager.getTransaction().begin();
            entityManager.persist(stringMessage);
            entityManager.getTransaction().commit();

            System.out.println("it's ok with string");


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//