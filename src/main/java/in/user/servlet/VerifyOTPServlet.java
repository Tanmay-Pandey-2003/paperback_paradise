package in.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.code.dao.UserDaoImpl;

@WebServlet("/verifyOTP")
public class VerifyOTPServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String otpEntered = request.getParameter("otp");

        HttpSession session = request.getSession();
        String storedOTP = (String) session.getAttribute("otp");
        String verifiedEmail = (String) session.getAttribute("verifiedEmail");

        if (otpEntered != null && otpEntered.equals(storedOTP)) {
            UserDaoImpl userDao = new UserDaoImpl();
            try {
                boolean userExists = userDao.checkUser(verifiedEmail);

                if (!userExists) {
                    // Registration success
                    session.setAttribute("successMsg", "Registration Successful");
                    session.removeAttribute("otp");
                    session.removeAttribute("verifiedEmail");
                    session.removeAttribute("showOTPVerifySection");
                    response.setContentType("application/json");
                    response.getWriter().write("{\"success\": true}");
                } else {
                    // User already exists
                    session.setAttribute("failMsg", "User Already Exists. Try another Email.");
                    response.setContentType("application/json");
                    response.getWriter().write("{\"success\": false, \"message\": \"User Already Exists\"}");
                }
            } catch (Exception e) {
                // Error handling
                e.printStackTrace();
                session.setAttribute("failMsg", "An error occurred. Please try again.");
                response.setContentType("application/json");
                response.getWriter().write("{\"success\": false, \"message\": \"An error occurred. Please try again.\"}");
            }
        } else {
            // Invalid OTP
            session.setAttribute("failMsg", "Invalid OTP. Please try again.");
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false, \"message\": \"Invalid OTP. Please try again.\"}");
        }
    }
}
