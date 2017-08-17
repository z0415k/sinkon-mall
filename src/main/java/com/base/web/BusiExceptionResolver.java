package com.base.web;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.base.utils.ApplicationException;


/*
 * 异常解析类，如果是ApplicationException异常，则把错误代码解析成错误信息返回给错误页
 * 
 */
public class BusiExceptionResolver extends SimpleMappingExceptionResolver {

	private static final String defaultErrorPage = "error/error";

	private static final String defaultExceptionPage = "error/exception";

	// 定义logger
	private Log log = LogFactory.getLog(BusiExceptionResolver.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
		log.info("出现异常:",ex);
		String viewName = determineViewName(ex, request);
		if (viewName == null) {
			viewName = defaultExceptionPage;
		}

		// 如果是ajax请求,请求header中包含会包含x-requested-with信息
		if (null != request.getHeader("x-requested-with")
				&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
			viewName = defaultErrorPage;
		} else {
			viewName = defaultExceptionPage;
		}

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("exceptionName", ex.getClass().getSimpleName());
		model.put("exception", ex);
		ApplicationException ap = null;
		boolean isApplcationException = false;
		if (ex instanceof ApplicationException) {
			ap = (ApplicationException) ex;
			isApplcationException = true;
		}
		if (ex instanceof UndeclaredThrowableException
				&& ((UndeclaredThrowableException) ex).getUndeclaredThrowable() instanceof ApplicationException) {
			ap = (ApplicationException) ((UndeclaredThrowableException) ex).getUndeclaredThrowable();
			isApplcationException = true;
		}
		if (isApplcationException) {
			String errMsg = null;
			if (ap.getErrCode() != null) {
				// errMsg = ap.getErrCode()+ap.getParams()[0];
				errMsg = ap.getErrCode();
			} else {
				errMsg = ap.getMessage();
			}
			model.put("error", errMsg);
			log.info("Error message:[" + errMsg + "] " + "ClassName:[" + MDC.get("class-name") + "] MethodName:["
					+ MDC.get("method-name") + "]");
		} else {
			log.info(ex.getMessage());
			model.put("error", "系统错误!");
		}
		// 异常写入数据库
		return new ModelAndView(viewName, model);
	}
}
