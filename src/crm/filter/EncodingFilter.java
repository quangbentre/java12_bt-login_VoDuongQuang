package crm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm.util.UrlConst;

@WebFilter(urlPatterns= {UrlConst.ROOT})
public class EncodingFilter implements Filter{
	private int requestCount = 1;
	@Override
	public void doFilter(ServletRequest request, ServletResponse rsp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse response = (HttpServletResponse) rsp;
		int count = requestCount++;
		// xu ly request
		System.out.println("request count >>>"+requestCount++);
		req.setCharacterEncoding("UTF-8");
		System.out.println("set charset for request. req: "+count);
		chain.doFilter(request, rsp);
		System.out.println("set charset for response. rsp: "+count);
		
	}

}
