package out;

import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ReturnNumMessagesServlet", urlPatterns = "/returnNumMessagesServlet")
public class ReturnNumMessagesServlet extends HttpServlet {

    @EJB
    ReturnMessageEJB returnMessageEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
                long sum = returnMessageEJB.getSumOfNums();
                req.setAttribute("sumOfNums", sum);

                this.getServletContext().getRequestDispatcher("/returnSumOfNums.jsp").forward(req,resp); //TODO jsp

        }catch (Exception e) {

            e.printStackTrace();
        }
    }
}
