package crm.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crm.dbconnection.MySqlConnection;
import crm.model.User;
import crm.service.AuthService;
import crm.util.JspConst;
import crm.util.ServletConst;
import crm.util.UrlConst;

@WebServlet(name = ServletConst.AUTH, urlPatterns = {
		UrlConst.AUTH_LOGIN,
		UrlConst.AUTH_LOGOUT,
		UrlConst.AUTH_FORGOT_PASSWORD,
		UrlConst.AUTH_SIGNUP
})
public class AuthServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.AUTH_LOGIN: 
			// cookies demo
			Cookie cookie = new Cookie("firstcookie","Thisisthefirstcookie");
			cookie.setMaxAge(60);
			resp.addCookie(cookie);
			
			// kiem tra cookie - email
			Cookie[] cookies = req.getCookies();
			int cookiesCount = cookies == null ? 0 : cookies.length;
			for(int i = 0; i < cookiesCount; i++)
				if(cookies[i].getName().equals("email"))
					req.setAttribute("email", cookies[i].getValue());
			
			String status = String.valueOf(req.getSession().getAttribute("status"));
			if(!status.equals("null"))
				resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
			else
				req.getRequestDispatcher(JspConst.AUTH_LOGIN)
					.forward(req, resp);
			break;
		case UrlConst.AUTH_LOGOUT: 
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + UrlConst.AUTH_LOGIN);
			break;	
		default:
			throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.AUTH_LOGIN: 
			// cookies demo
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String remember = req.getParameter("rememberUsername");
			
			if(remember != null) {
				Cookie cookie = new Cookie("email", email);
				cookie.setMaxAge(60*60*24*30);
				resp.addCookie(cookie);
			}
			
			System.out.printf("Email: %s, Remember: %s\n", email, remember);
			
			// session demo
			HttpSession currentSession = req.getSession();
			String pingo = (String)currentSession.getAttribute("pingo");
			System.out.printf("Pingo: %s\n", pingo);
			
			// logic dang nhap
			AuthService sv = new AuthService();
			boolean isLogin = sv.isLogin(email, password);
			//show msg sai user or pass
			if(!isLogin)
				req.setAttribute("msg", "User or password not correct!");
			
			if(isLogin) {
				currentSession.setAttribute("status", "Logged in successfully.");
				resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
			} else 
				//resp.sendRedirect(req.getContextPath() + UrlConst.AUTH_LOGIN);
				req.getRequestDispatcher(JspConst.AUTH_LOGIN).forward(req, resp);
				
			break;
		case UrlConst.AUTH_LOGOUT: 
			resp.sendRedirect(req.getContextPath() + UrlConst.AUTH_LOGIN);
			break;	
		default:
			throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
		}
	}
}
