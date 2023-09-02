import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/studentdb?useSSL=false";
        String username = "root";
        String password = "1234";

        String studentId = request.getParameter("rollno");
        String status = request.getParameter("status");

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, username, password);

            String query = "UPDATE studentdetails SET status = ? WHERE rollno = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, status);
            statement.setString(2,studentId );

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                response.getWriter().println("Status updated successfully.");
            } else {
                response.getWriter().println("Failed to update status.");
            }

            statement.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
