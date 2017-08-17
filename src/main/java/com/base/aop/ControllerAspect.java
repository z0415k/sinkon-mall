package com.base.aop;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.base.utils.ObjectUtils;


/**
 * 针对控制器的重要方法，实现日志记录aop
 */
@Component
@Aspect
public class ControllerAspect {

	private final Log log = LogFactory.getLog(ControllerAspect.class);
	
/*	@Pointcut("execution(* com.zh.weixin.*.controller.*Controller.insert*(..))"
			+ "|| execution(* com.zh.weixin.*.controller.*Controller.update*(..)) "
			+ "|| execution(* com.zh.weixin.*.controller.*Controller.delete*(..)) "
			+ "|| execution(* com.zh.weixin.*.controller.*Controller.query*(..)) "
			+ "|| execution(* com.zh.weixin.*.controller.*Controller.add*(..)) "
			+ "|| execution(* com.zh.weixin.*.controller.*Controller.*(..)) ")*/
	@Pointcut("execution(* com.sinkon.controller.*Controller.*(..)) ")
	
	public void aPointcut() {
	}

	@Before("aPointcut()")
	public void beforeAdvice(JoinPoint joinPoint) {
		
		log.info("调用类:[" + joinPoint.getTarget().getClass() + "]方法:[" + joinPoint.getSignature().getName() +"]");		
		//是否需要输出参数
		if (log.isInfoEnabled()) {
			Object obj[] = joinPoint.getArgs();
			int index = 1;
			
			for (Object o : obj) {
				//System.out.println(o.getClass().toString());
				if(o==null){
					continue;
				}
				if (o instanceof String) {
					log.info("参数" + index +"类型：[String]参数值：["   + o + "]" );
				} else if (o instanceof HttpServletRequest) {
					HttpServletRequest req = (HttpServletRequest) o;
					log.info("参数" + index +"类型：[HttpServletRequest]参数值：["   + getParamFromRequest(req) + "]" );
				}else if(o instanceof ServletResponse){
					log.info("参数" + index +"类型：[" + o.getClass().getName() + "]参数值：["   + o + "]" );
				} else if  (o instanceof Object) {
					log.info("参数" + index +"类型：[" + o.getClass().getName() + "]参数值：["   + ObjectUtils.toJSON(o) + "]" );
				} else {
					log.info("参数" + index +"类型：[ " + o.getClass().getName() +"]" );
				}
				index++;
			}
		}
		 
	}
	
	@After("aPointcut()")
	public void afterAdvice(JoinPoint joinPoint) {
		
	}
	
	@AfterReturning("aPointcut()")
	public void afterAdviceReturn(JoinPoint joinPoint) {
		
	}
	
	@AfterThrowing("aPointcut()")
	public void afterAdviceThrow(JoinPoint joinPoint) {
		
	}
	/**
	 * 从request中获取参数
	 */
	private String getParamFromRequest(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		Map params = request.getParameterMap();
		if ((params != null) && (params.size() > 0)) {
			Iterator iter = params.entrySet().iterator();
			String[] valueHolder = new String[1];

			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String name = (String) entry.getKey();

				Object value = entry.getValue();
				String[] values;
				if (value instanceof String[]) {
					values = (String[]) value;
				} else {
					valueHolder[0] = value.toString();
					values = valueHolder;
				} 
				for (int i = 0; i < values.length; i++) {
					if (values[i] != null) {
						sb.append(name + "= " + values[i] + ";");
					}
				}

			}
		}
		return sb.toString();
	}

}
