import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String name = req.getParameter("studentName");
        String rollno = req.getParameter("rollno");
        String branch = req.getParameter("Branch");
        String projectname = req.getParameter("projectName");
        String projectdescription = req.getParameter("projectDescription");
         String status = req.getParameter("status");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb?useSSL=false", "root", "1234");
            String q = "INSERT INTO studentdetails(rollno,stname, sbranch, sprojectname, sprojectdescription,status) VALUES (?,?,?,?,?,?)";
            PreparedStatement stm = con.prepareStatement(q);
            stm.setString(1,rollno);  
            stm.setString(2, name);
            stm.setString(3, branch);
            stm.setString(4, projectname);
            stm.setString(5, projectdescription);
            stm.setString(6,status); 

            int x = stm.executeUpdate();
            System.out.println("Data Updated Successfully.. " + x);
            if (x > 0) {
             res.sendRedirect("success.html");
            } else {
                out.println("Not Successful");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
