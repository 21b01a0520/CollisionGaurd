import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String usr = request.getParameter("student_username");
        String pwd= request.getParameter("student_password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb?useSSL=false", "root", "1234");
            Statement stm = con.createStatement();
            ResultSet studentResult = stm.executeQuery("select * from students where sid = '" + usr + "' and spassword = '" + pwd + "'");

            if (studentResult.next()) {

                response.sendRedirect("index.html");
            } else {
             
              response.sendRedirect("error.html");
                }
     

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
