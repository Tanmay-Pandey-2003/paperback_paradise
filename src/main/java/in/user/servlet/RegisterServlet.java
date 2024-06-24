package in.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.code.dao.UserDaoImpl;
import in.code.entity.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("fname");
		String email = req.getParameter("email");
		String phno = req.getParameter("phno");
		String password = req.getParameter("password");
		String check = req.getParameter("check");

		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhno(phno);
		HttpSession session = req.getSession();
		if (check != null) {
			UserDaoImpl userDao = new UserDaoImpl();
			boolean usercheck = userDao.checkUser(email);
			if (!usercheck) {
				boolean status = userDao.userRegister(user);
				System.out.println(status);
				if (status) {
					session.setAttribute("successMsg", "Registration Successfull");
					resp.sendRedirect("register.jsp");
				} else {
					session.setAttribute("failMsg", "Registration Unsuccessfull");
					resp.sendRedirect("register.jsp");
				}
			} else {
				session.setAttribute("failMsg", "User Already Exist Try another Email");
				resp.sendRedirect("register.jsp");
			}

		} else {
			session.setAttribute("failMsg", "Please Agree terms and conditions");
			resp.sendRedirect("register.jsp");
		}
	}

}



/*
 * package in.user.servlet;
 * 
 * import java.io.IOException; import java.util.Random;
 * 
 * import javax.servlet.ServletException; import
 * javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import
 * javax.servlet.http.HttpSession;
 * 
 * import in.code.dao.IUserDao; import in.code.dao.UserDaoImpl; import
 * in.code.entity.User; import in.code.util.EmailUtil;
 * 
 * @WebServlet("/register") public class RegisterServlet extends HttpServlet {
 * private static final long serialVersionUID = 1L;
 * 
 * private final IUserDao userDao; // Inject UserDaoImpl or use directly
 * 
 * public RegisterServlet() { this.userDao = new UserDaoImpl(); // Initialize
 * UserDaoImpl in constructor }
 * 
 * protected void doPost(HttpServletRequest req, HttpServletResponse resp)
 * throws ServletException, IOException { String name =
 * req.getParameter("fname"); String email = req.getParameter("verifyEmail");
 * String phno = req.getParameter("phno"); String password =
 * req.getParameter("password"); String check = req.getParameter("check");
 * 
 * HttpSession session = req.getSession();
 * 
 * if (check != null) { boolean userExists = userDao.checkUser(email);
 * 
 * if (!userExists) { // Generate OTP String otp = generateOTP();
 * 
 * // Send OTP via email boolean emailSent = sendOTPEmail(email, otp); if
 * (emailSent) { // Store OTP and email in session for verification
 * session.setAttribute("otp", otp); session.setAttribute("verifiedEmail",
 * email); session.setAttribute("showOTPVerifySection", true);
 * 
 * // Respond with JSON success message resp.setContentType("application/json");
 * resp.getWriter().write("{\"success\": true}"); } else {
 * session.setAttribute("failMsg", "Failed to send OTP. Please try again.");
 * resp.setContentType("application/json"); resp.getWriter().
 * write("{\"success\": false, \"message\": \"Failed to send OTP\"}"); } } else
 * { session.setAttribute("failMsg", "User Already Exists. Try another Email.");
 * resp.setContentType("application/json"); resp.getWriter().
 * write("{\"success\": false, \"message\": \"User Already Exists\"}"); } } else
 * { session.setAttribute("failMsg", "Please Agree to Terms and Conditions");
 * resp.setContentType("application/json"); resp.getWriter().
 * write("{\"success\": false, \"message\": \"Please Agree to Terms and Conditions\"}"
 * ); } }
 * 
 * private String generateOTP() { Random random = new Random(); int otp = 100000
 * + random.nextInt(900000); return String.valueOf(otp); }
 * 
 * private boolean sendOTPEmail(String email, String otp) { // Replace with your
 * email sending logic using EmailUtil or any other method EmailUtil emailUtil =
 * new EmailUtil(); // Example class for sending email return
 * emailUtil.sendEmail(email, "Verification OTP", "Your OTP is: " + otp); } }
 */