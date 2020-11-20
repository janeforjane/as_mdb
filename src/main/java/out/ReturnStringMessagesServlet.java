package out;


import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ReturnStringMessagesServlet", urlPatterns = "/returnStringMessagesServlet")
public class ReturnStringMessagesServlet extends HttpServlet {

        @EJB
        ReturnMessageEJB returnMessageEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
                int i = 0;

                ArrayList<String> stringArrayList = returnMessageEJB.getList();
                req.setAttribute("stringArrayList", stringArrayList);

                this.getServletContext().getRequestDispatcher("/returnStrings.jsp").forward(req,resp); //TODO jsp

        }catch (Exception e) {

            e.printStackTrace();
        }
    }
}
