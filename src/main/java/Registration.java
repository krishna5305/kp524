import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public Registration() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html"); // Set the content type
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String attendeesno = request.getParameter("attendeesno");

        System.out.println("Servlet doGet() invoked");
        System.out.println("Received data - Name: " + name + ", Phone: " + phone + ", Email: " + email + ", Attendees: " + attendeesno);

        Connection con = null;
        Statement stmt = null;
        try {
            // Load the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/realdb", "root", "krishna@30");
            stmt = con.createStatement();

            // Insert data into the 'event' table
            String query = "INSERT INTO event (name, phone, email, attendeesno) VALUES ('" + name + "', '" + phone + "', '" + email + "', '" + attendeesno + "')";
            int result = stmt.executeUpdate(query);

            // Send feedback to the user
            out.println("<html><head><title>REGISTRATION SUCCESS</title></head><body bgcolor='wheat'><center><h1>");
            if (result != 0) {
                System.out.println("Database insertion successful.");
                out.println("REGISTRATION SUCCESS");  // Static response for testing
            } else {
                System.out.println("Database insertion failed.");
                out.println("REGISTRATION FAILED. Please try again.");
            }
            out.println("</h1></center></body></html>");

        } catch (ClassNotFoundException e) {
            out.println("Error loading database driver: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Forward POST requests to doGet
    }
}
