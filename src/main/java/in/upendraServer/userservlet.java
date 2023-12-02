package in.upendraServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import in.upendra.user;
import in.upendra.dao.DataDao;

@WebServlet("/user")
public class userservlet extends HttpServlet
{
	DataDao d=new DataDao();
user u=new user();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String phno=req.getParameter("phno");
		int phn=Integer.parseInt(phno);
		PrintWriter pr = resp.getWriter();
		u.setName(name);
		u.setEmail(email);
		u.setPhno(phn);
		try {
			int cun=d.connection(u);
			if(cun>=1) {
				 pr.write("<h1 style='color:green'>Successfully saved Data</h1>");
	                RequestDispatcher rs = req.getRequestDispatcher("index.jsp");
	                rs.include(req, resp);
	            }
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            pr.write("<h1 style='color:red'>Error: " + e.getMessage() + "</h1>");
	        }
	    }

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String id=req.getParameter("id");
	if (id == null || id.isEmpty()) {
        try {
            List<user> allRecords = d.getAllRecords(0);
            req.setAttribute("use", allRecords);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
           
        }
    } else {
        try {
            int id1 = Integer.parseInt(id);
            List<user> user = d.getAllRecords(id1);
            req.setAttribute("user", user);
        } catch (NumberFormatException e) {
            e.printStackTrace();
          
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
      
        }
    }

    req.getRequestDispatcher("user.jsp").forward(req, resp);
}
}

