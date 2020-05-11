package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
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

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String report_id = (String) request.getParameter("id");
        if (report_id != null && report_id.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Iine i = new Iine();
            
            i.setEmployee((Employee)request.getSession().getAttribute("login_employee"));
            Report r =em.find(Report.class, Integer.parseInt(request.getParameter("id")));
            i.setReport(r);
            em.getTransaction().begin();
            em.persist(i);
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "いいね　しました。");

            response.sendRedirect(request.getContextPath() + "/reports/index");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/index.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

    }

}
