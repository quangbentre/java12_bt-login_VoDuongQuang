package crm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.util.JspConst;
import crm.util.ServletConst;
import crm.util.UrlConst;


@WebServlet(name = ServletConst.HOME, urlPatterns = UrlConst.HOME)
public class HomeServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JspConst.HOME).forward(req, resp);
	}
}
