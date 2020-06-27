package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/reports/delete")
public class ReportsDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        Employee login_employee = (Employee) request.getSession().getAttribute("login_employee");

        String report_id = (String) request.getParameter("report_id");
        String _token = (String) request.getParameter("_token");
        request.setAttribute("report_id", report_id);
        request.setAttribute("_token", request.getSession().getId());


            if(_token != null && _token.equals(request.getSession().getId())) {
                EntityManager em = DBUtil.createEntityManager();
                em.getTransaction().begin();
                Query query = em.createQuery("DELETE FROM Iine i WHERE i.employee.id = :employee AND i.report.id = :report");
                query.setParameter("employee", login_employee.getId());
                query.setParameter("report", Integer.valueOf(report_id));
                query.executeUpdate();
                em.getTransaction().commit();
                em.close();
                }

                request.getSession().setAttribute("flush", "いいねを取り消しました");

                response.sendRedirect(request.getContextPath() + "/reports/index");

            }



}

