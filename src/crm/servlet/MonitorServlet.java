package crm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crm.dbconnection.MySqlConnection;
import crm.util.ServletConst;
import crm.util.UrlConst;

@WebServlet(name = ServletConst.MONITOR, urlPatterns = {
		UrlConst.HEALTH,
		UrlConst.INVALIDATE
})
public class MonitorServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
		case UrlConst.HEALTH: {
			//session demo
			HttpSession currentSession = req.getSession();
			currentSession.setAttribute("pingo", "this is the first session attribute");
			currentSession.setMaxInactiveInterval(60*60);
			
			if(MySqlConnection.getConnection() != null)
				resp.getWriter().append("Database connection has been established successfully.");
			else 
				resp.getWriter().append("Database connection could not be established.");
				
			break;
		}
		case UrlConst.INVALIDATE:
			req.getSession().invalidate();
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}
}
