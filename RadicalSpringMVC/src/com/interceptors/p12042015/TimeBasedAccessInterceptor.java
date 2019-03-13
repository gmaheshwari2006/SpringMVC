package com.interceptors.p12042015;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TimeBasedAccessInterceptor extends HandlerInterceptorAdapter {
	/*private static final Logger logger = LoggerFactory
            .getLogger(TimeBasedAccessInterceptor.class);*/
	private static final int HOUR_OF_DAY = 24;
     
	private int openingTime;
    private int closingTime;
	public int getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(int openingTime) {
		this.openingTime = openingTime;
	}
	public int getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(int closingTime) {
		this.closingTime = closingTime;
	}
    
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
		 request.setAttribute("startTime", System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (openingTime < hour && hour < closingTime) {
            System.out.println("in time");
            return true;
        }
        //response.sendRedirect("http://localhost:8080/RadicalSpringMVC/webpages/outsideOfficeHours.jsp");
        System.out.println("out of office time");
        return false;
    }
	
	 @Override
	    public void postHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler,
	            ModelAndView modelAndView) throws Exception {
	        System.out.println("Request URL::" + request.getRequestURL().toString()
	                + " Sent to Handler :: Current Time=" + System.currentTimeMillis());
	       
	        //we can add attributes in the modelAndView and use that in the view page
	    }
	 
	    @Override
	    public void afterCompletion(HttpServletRequest request,
	            HttpServletResponse response, Object handler, Exception ex)
	            throws Exception {
	        long startTime = (Long) request.getAttribute("startTime");
	        System.out.println("Request URL::" + request.getRequestURL().toString()
	                + ":: End Time=" + System.currentTimeMillis());
	        System.out.println("Request URL::" + request.getRequestURL().toString()
	                + ":: Time Taken=" + (System.currentTimeMillis() - startTime));
	    }

}
