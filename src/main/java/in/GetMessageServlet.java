package in;

import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetMessageServlet", urlPatterns = "/getMessageServlet")
public class GetMessageServlet extends HttpServlet {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/queue/string_queue") // add to standalone.xml
    private Queue stringQueue;

    @Resource(mappedName = "java:/queue/num_queue") // add to standalone.xml
    private Queue numQueue;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String s1 = req.getParameter("name");
            Session session = connectionFactory.createConnection().createSession();

            if (isInt(s1)) {
                //TODO put s1 in numqueue

                MessageProducer messageProducerNum = session.createProducer(numQueue);
                Integer i = Integer.parseInt(s1);
                ObjectMessage objectMessage = session.createObjectMessage(i);

                messageProducerNum.send(objectMessage);

                messageProducerNum.close();

                resp.sendRedirect("tryAgain.html");

            }else {
                //TODO put s1 in stringqueue

                MessageProducer messageProducerString = session.createProducer(stringQueue);
                TextMessage textMessage = session.createTextMessage(s1);

                messageProducerString.send(textMessage);

                messageProducerString.close();

                resp.sendRedirect("tryAgain.html");
            }

            session.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
