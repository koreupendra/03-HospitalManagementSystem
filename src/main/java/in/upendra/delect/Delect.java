package in.upendra.delect;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.upendra.user;
import in.upendra.dao.DataDao;
@WebServlet("/delect") // Correct the mapping URL
public class Delect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        

        DataDao d = new DataDao();
		PrintWriter pr = resp.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));
      			try {
				boolean suc=d.deleteUser(id);
		         if(suc) {
		        	 pr.write("<h1 style='color:red'>Delect Data Success Fully</h1>");
		                RequestDispatcher rs = req.getRequestDispatcher("user.jsp");
		                rs.include(req, resp);
		         }

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
      			

       
    }
}
