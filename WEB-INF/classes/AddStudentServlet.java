import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String studentId = request.getParameter("id");
        String studentName = request.getParameter("stname");
        String studentEmail = request.getParameter("stemail");
        String studentPhone = request.getParameter("stphone");
        String studentPassword = request.getParameter("stpass");
        String studentCourse = request.getParameter("stcourse");

        // Database connection parameters
        String dbUrl = "jdbc:mysql://localhost:3306/database_name";
        String dbUsername = "your_username";
        String dbPassword = "your_password";

        // Create database connection
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb?useSSL=false","root","1234")) {
            // Prepare SQL statement
            String sql = "INSERT INTO students (sid, sname, semail, sphone, spassword, scourse) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set parameter values
            statement.setString(1, studentId);
            statement.setString(2, studentName);
            statement.setString(3, studentEmail);
            statement.setString(4, studentPhone);
            statement.setString(5, studentPassword);
            statement.setString(6, studentCourse);

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                // Data inserted successfully
                response.sendRedirect("Login_cg.html");
            } else {
                // Failed to insert data
                response.sendRedirect("error.html");
            }
        } catch (SQLException e) {
            // Handle database errors
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
