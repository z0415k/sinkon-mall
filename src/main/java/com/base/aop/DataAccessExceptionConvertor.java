package com.base.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.CleanupFailureDataAccessException;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.dao.UncategorizedDataAccessException;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;

import com.base.utils.ApplicationException;
import com.base.utils.SystemException;


/**
 * 实现异常的转换
 */
@Aspect
public class DataAccessExceptionConvertor {

	/**
	 * 定义切入点:所有BaseDao的子类的所有方法 这里可以按需要定义匹配的方法
	 */
	@Pointcut("target(com.sinkon.dao.*)")
//	@pointcut("target(com.zh.weixin.base.dao.basedao)")
	private void getEntityManagerPointCut() {
	}

	/**
	 * 定义切入的时机和执行的方法 这里定义了当方法抛出异常后,执行该方法
	 * 
	 * 该方法的作用是把spring的dao异常 转换成ApplicationException
	 * 
	 * 
	 */
	@AfterThrowing(pointcut = "getEntityManagerPointCut()", throwing = "e")
	public void doDataAccessExceptionConvert(Exception e)
			throws ApplicationException {

		if ((e instanceof CleanupFailureDataAccessException))
			throw new ApplicationException("CleanupFailureDataAccessException",
					null, null, e);
		if ((e instanceof ConcurrencyFailureException))
			throw new ApplicationException("ConcurrencyFailureException", null,
					null, e);
		if ((e instanceof DataAccessResourceFailureException))
			throw new ApplicationException(
					"DataAccessResourceFailureException", null, null, e);
		if ((e instanceof DataIntegrityViolationException))
			throw new ApplicationException("DataIntegrityViolationException",
					null, null, e);
		if ((e instanceof DataRetrievalFailureException))
			throw new ApplicationException("DataRetrievalFailureException",
					null, null, e);
		if ((e instanceof DataSourceLookupFailureException))
			throw new ApplicationException("DataSourceLookupFailureException",
					null, null, e);
		if ((e instanceof InvalidDataAccessApiUsageException))
			throw new ApplicationException(
					"InvalidDataAccessApiUsageException", null, null, e);
		if ((e instanceof InvalidDataAccessResourceUsageException))
			throw new ApplicationException(
					"InvalidDataAccessResourceUsageException", null, null, e);
		if ((e instanceof PermissionDeniedDataAccessException))
			throw new ApplicationException(
					"PermissionDeniedDataAccessException", null, null, e);
		if ((e instanceof UncategorizedDataAccessException))
			throw new ApplicationException("UncategorizedDataAccessException",
					null, null, e);
		if ((e instanceof SystemException))
			throw new ApplicationException("SystemException", null,
					new String[] { e.getMessage() }, e);
		else
			// else to other Exception
			throw new ApplicationException("UnknowedDAOAccessExcepiton", null,
					null, e);
	}

}
