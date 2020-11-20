package in;

import entity.NumMessage;
import entity.StringMessage;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.Random;

@MessageDriven(name = "PutOnNumMDB",activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/num_queue")})
@TransactionManagement(value= TransactionManagementType.BEAN)
public class PutOnNumMDB implements MessageListener {

    @PersistenceUnit(unitName = "input-message")
    private EntityManagerFactory entityManagerFactory; // need for db


    @Override
    public void onMessage(Message message) {

        ObjectMessage numMessage = (ObjectMessage) message;
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            NumMessage num = new NumMessage();
            Integer l = (Integer) numMessage.getObject();

            num.setId(new Random().nextInt(1000)+0);
            num.setNum(l);

            entityManager.getTransaction().begin();
            entityManager.persist(num);
            entityManager.getTransaction().commit();

            System.out.println("it's ok with num");

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
