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

@WebServlet("/AddFacultyServlet")
public class AddFacultyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String facultyId = request.getParameter("id");
        String facultyName = request.getParameter("faname");
        String facultyEmail = request.getParameter("faemail");
        String facultyPhone = request.getParameter("faphone");
        String facultyPassword = request.getParameter("fapass");
        String facultyDepartment = request.getParameter("fadepartment");

        // Database connection parameters
        String dbUrl = "jdbc:mysql://localhost:3306/database_name";
        String dbUsername = "your_username";
        String dbPassword = "your_password";

        // Create database connection
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb?useSSL=false","root","1234")) {
            // Prepare SQL statement
            String sql = "INSERT INTO faculty (id, name, email, phone, password, Department) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Set parameter values
            statement.setString(1, facultyId);
            statement.setString(2, facultyName);
            statement.setString(3, facultyEmail);
            statement.setString(4, facultyPhone);
            statement.setString(5, facultyPassword);
            statement.setString(6, facultyDepartment);

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
