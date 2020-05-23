package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Iine;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsIineServlet
 */
@WebServlet("/reports/iine")
public class ReportsIineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsIineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String report_id = (String) request.getParameter("report_id");
        String _token = (String) request.getParameter("_token");
        request.setAttribute("report_id", report_id);

        request.setAttribute("_token", request.getSession().getId());

        EntityManager em = DBUtil.createEntityManager();

        Iine i = new Iine();
        i.setEmployee((Employee) request.getSession().getAttribute("login_employee"));
        if (_token != null & _token.equals(request.getSession().getId())) {
            System.out.println(_token + report_id);

            Report r = em.find(Report.class, Integer.parseInt(report_id));
            i.setReport(r);
            em.getTransaction().begin();
            em.persist(i);
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "いいね！");

            response.sendRedirect(request.getContextPath() + "/reports/index");
        }
    }
}
