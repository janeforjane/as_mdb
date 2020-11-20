package out;

import entity.NumMessage;
import entity.StringMessage;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local
@TransactionManagement(value= TransactionManagementType.BEAN)
public class ReturnMessageEJBImpl implements ReturnMessageEJB {

    @PersistenceUnit(unitName = "input-message")
    private EntityManagerFactory entityManagerFactory; // need for db

    @Override
    public ArrayList<String> getList() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ArrayList<String> stringArrayList = new ArrayList<>();

        entityManager.getTransaction().begin();

        List<StringMessage> from_strings = entityManager.createQuery("from StringMessage ").getResultList();

        entityManager.getTransaction().commit();

        for (int i = 0; i < from_strings.size(); i++) {

            StringMessage stringMessage = from_strings.get(i);
            stringArrayList.add(stringMessage.getName());
        }
        return stringArrayList;
    }

    @Override
    public long getSumOfNums() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        long l = 0;

        entityManager.getTransaction().begin();
        List <NumMessage> from_nums = entityManager.createQuery("from NumMessage ").getResultList();
        entityManager.getTransaction().commit();

        for (int i = 0; i < from_nums.size(); i++) {

            l = l + from_nums.get(i).getNum();

        }
        return l;
    }
}
