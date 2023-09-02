import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FLoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String usrf = request.getParameter("faculty_username");
        String pwdf= request.getParameter("faculty_password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb?useSSL=false", "root", "1234");
            Statement stm1 = con.createStatement();
            ResultSet facultyResult = stm1.executeQuery("select * from faculty where id = '" + usrf + "' and password = '" + pwdf + "'");

            if (facultyResult.next()) {

                response.sendRedirect("findex.html");
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
