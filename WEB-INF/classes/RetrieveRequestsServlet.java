import java.util.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RetrieveRequestsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/studentdb?useSSL=false";
        String username = "root";
        String password = "1234";

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM studentdetails";
            PreparedStatement statement = con.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student Requests</title>");
            out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
            out.println("<style>");
            out.println("table {");
            out.println("    width: 80%;");
            out.println("    margin: 0 auto;"); // Align the table at the center
            out.println("    border-collapse: collapse;");
            out.println("}");
            out.println("th, td {");
            out.println("    border: 1px solid black;");
            out.println("    padding: 8px;");
            out.println("}");
            out.println(".btn-container {");
            out.println("    display: flex;");
            out.println("    justify-content: center;");
            out.println("    margin-top: 20px;");
            out.println("}");
            out.println(".btn {");
            out.println("    margin: 5px;");
            out.println("}");
            out.println(".approved {");
            out.println("    background-color:  #a6d8a8;");
            out.println("}");
            out.println(".declined {");
            out.println("    background-color:  #eaa8a8;");
            out.println("}");
            out.println("</style>");
            out.println("<script src='https://code.jquery.com/jquery-3.6.0.min.js'></script>");
out.println("<script>");
out.println("$(document).ready(function() {");
out.println("  $('.approve-btn').click(function() {");
out.println("    var row = $(this).closest('tr');");
out.println("    row.addClass('approved');");
out.println("    row.find('.status').text('Approved');");
out.println("    var studentId = row.find('.rollno').text();");
out.println("    updateStatus(studentId, 'Approved');");
out.println("  });");
out.println("  $('.decline-btn').click(function() {");
out.println("    var row = $(this).closest('tr');");
out.println("    row.addClass('declined');");
out.println("    row.find('.status').text('Declined');");
out.println("    var studentId = row.find('.rollno').text();");
out.println("    updateStatus(studentId, 'Declined');");
out.println("  });");
out.println("});");
out.println("function updateStatus(rollno, status) {");  // Change parameter name from studentId to rollno
out.println("  $.ajax({");
out.println("    url: 'UpdateStatusServlet',");
out.println("    method: 'POST',");
out.println("    data: {");
out.println("      rollno: rollno,");  // Change parameter name from studentId to rollno
out.println("      status: status");
out.println("    },");
out.println("    success: function(response) {");
out.println("      console.log('Status updated successfully.');");
out.println("    },");
out.println("    error: function(xhr, status, error) {");
out.println("      console.error('Error updating status: ' + error);");
out.println("    }");
out.println("  });");
out.println("}");
out.println("</script>");

            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h1>Student Requests</h1>");

            out.println("<table class='table'>");
            out.println("<thead class='thead-dark'>");
            out.println("<tr>");
            out.println("<th>Student ID</th>");
            out.println("<th>Student Name</th>");
            out.println("<th>Branch</th>");
            out.println("<th>Project Name</th>");
            out.println("<th>Project Description</th>");
            out.println("<th>Status</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            while (resultSet.next()) {
                out.println("<tr>");
                out.println("<td class='rollno'>" + resultSet.getString(1) + "</td>");
                out.println("<td>" + resultSet.getString(2) + "</td>");
                out.println("<td>" + resultSet.getString(3) + "</td>");
                out.println("<td>" + resultSet.getString(4) + "</td>");
                out.println("<td>" + resultSet.getString(5) + "</td>");
                out.println("<td class='status'>");
	                if ("Approved".equals(status)) {
                    out.print("Approved");
                    out.println("<button class='btn btn-danger decline-btn'>Decline</button>");
                } else if ("Declined".equals(status)) {
                    out.print("Declined");
                    out.println("<button class='btn btn-success approve-btn'>Approve</button>");
                } else {

                out.println("<button class='btn btn-success approve-btn'>Approve</button>");
                out.println("<button class='btn btn-danger decline-btn'>Decline</button>");
}
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            resultSet.close();
            statement.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
