package in.upendra.edite;

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
@WebServlet("/editRecord")
public class editeDelete extends HttpServlet {
    DataDao d = new DataDao();
    user u=new user();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
    	
    	int id = Integer.parseInt(request.getParameter("id"));
    	List<user> editData=null;
    	try {
			 editData = d.getAllRecords(id);
		} catch (Exception e) {
		e.printStackTrace();
	}
    	request.setAttribute("editData", editData);

   	request.getRequestDispatcher("update.jsp").forward(request, response);

   }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html");
    	PrintWriter writer = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int phone = Integer.parseInt(request.getParameter("phno"));
     

        // Create a User object with the updated data
       
       u.setId(id);
        u.setName(name);
        u.setEmail(email);
        u.setPhno(phone);
  
 
        boolean success;
        
         try {
			success = d.updateUser(u);
			 if (success) {
				 writer.write("<h1 style='color:green'>Successfully Update</h1>");		     
		        	request.getRequestDispatcher("user.jsp").include(request, response);
		        } else {
		        	writer.write("Record Not Updated Successfully");
		        	request.getRequestDispatcher("user.jsp").include(request, response);
		        }
		} catch (Exception e) {
			e.printStackTrace();
		} 

       
    }
}



