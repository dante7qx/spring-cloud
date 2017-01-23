package org.dante.demo.getway.config.session;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SessionController {
	private static final Logger logger = LoggerFactory.getLogger(SessionController.class);
	
	@RequestMapping(value = "/session-timeout")
    public void sessionTimeout(HttpServletRequest request,HttpServletResponse response) throws IOException {  
		logger.debug("current session is timeout!");
		
        if (request.getHeader("x-requested-with") != null  
                && request.getHeader("x-requested-with").equalsIgnoreCase(  
                        "XMLHttpRequest")) { // ajax 超时处理  
            response.getWriter().print("timeout");  //设置超时标识
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().close();
        } else {
             response.sendRedirect(request.getContextPath()+"/login");  
        }
    }
}
