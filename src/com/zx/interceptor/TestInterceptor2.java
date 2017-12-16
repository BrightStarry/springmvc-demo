package com.zx.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zx.domain.User;

public class TestInterceptor2 implements HandlerInterceptor{
	//请求全部处理完成后调用的方法，相当于servlet的销毁方法。
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("执行拦截器的afterCompletion方法2");
	}
	
	//请求经过目标Controller处理完成后，再次发送出去，未到达发送目标地时调用的方法
	//可以通过ModelAndView参数来修改响应的视图，或者发往视图的方法，以及其中的所有参数
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("执行拦截器的postHandle方法2");
		
		
	}
	
	//在请求到达目标的controller之前调用的方法
	//返回值表示是否需要将当前请求拦截下来，如果返回fasle，当前请求将被终止
	//Object arg2 表示当前被拦截的请求的目标对象
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("执行拦截器的preHandle方法2");
		return true;
	}

}
