package com.benson.esignin.web.aop;

import com.benson.esignin.common.cons.CommonCons;
import com.benson.esignin.common.utils.CommonUtil;
import com.benson.esignin.common.utils.DateUtil;
import com.benson.esignin.common.utils.JsonUtil;
import com.benson.esignin.common.utils.NoticeAdminUtil;
import com.benson.esignin.web.annotation.SysControllerLog;
import com.benson.esignin.web.annotation.SysServiceLog;
import com.benson.esignin.web.domain.entity.SysExceptionLog;
import com.benson.esignin.web.domain.entity.SysLog;
import com.benson.esignin.web.domain.entity.UserInfo;
import com.benson.esignin.web.utils.IPUtil;
import com.benson.esignin.web.utils.SysLogUtil;
import com.benson.esignin.web.utils.UserUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 系统访问日志记录AOP
 *
 * @author: Benson Xu
 * @date: 2016年06月09日 10:07
 */
@Aspect
@Component
public class AccessLogAop {

    private final static Logger logger = Logger.getLogger(AccessLogAop.class);


    /**
     * Service层切入点
     */
    @Pointcut("@annotation(com.benson.esignin.web.annotation.SysServiceLog)")
    public void sysServiceAspect() {}

    /**
     * Controller层切入点
     */
    @Pointcut("@annotation(com.benson.esignin.web.annotation.SysControllerLog)")
    public void sysControllerAspect() {}



    /**
     * 使用环绕增强来监听Controller操作,并记录日志
     * @param pjp
     * @return
     */
    @Around("sysControllerAspect()")
    public Object doControllerLog(ProceedingJoinPoint pjp) {
        logger.info("Enter doControllerLog Method ...");

        String callMethod = String.format("%s.%s", pjp.getTarget().getClass().getSimpleName(), pjp.getSignature().getName());

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 任务开始
        final long startTime = System.currentTimeMillis();

        // 先获取用户名称，防止用户logout时，获取不到信息
        String userName = getUserName(request);

        try {
            // 执行目标Controller方法
            Object obj = pjp.proceed();
            // 任务结束，记录时间
            long consumeTime = getCurrentConsumeTime(startTime);

            // 如果当前操作是登录,那么登录后重新获取用户名
            if ("UserController.login".equals(callMethod)) {
                userName = getUserName(request);
            }

            // 获取操作内容
            String operContent = getAnnotationContent(pjp, false);

            logger.info(String.format("%s操作内容:%s", callMethod, operContent));

            // 添加访问日志
            addSysLog(request, userName, callMethod, operContent, consumeTime);

            //如果执行时间超过5秒，则记录为异常行为
            /*if (sw.getTotalTimeMillis() > 5000) {
                SysLogUtil.addExceptionLog(callMethod+"执行时间超5秒！", FlagConstant.L_T_SYS_EXCEPTION, sw.getTotalTimeMillis(), userName);
            }*/

            // 返回相应的相应Action指令
            return obj;
        } catch (RuntimeException e) {
            //addExceptionLog(e, sw.getTotalTimeMillis(), userName, fullName, deptName, callMethod);
            logger.error(String.format("运行时异常--执行目标[%s]方法:{}", callMethod), e);
            return CommonCons.ERROR;
        } catch (Exception e) {
            //addExceptionLog(e, sw.getTotalTimeMillis(), userName, fullName, deptName, callMethod);
            logger.error(String.format("系统异常--执行目标[%s]方法:{}", callMethod), e);
            return CommonCons.ERROR;
        } catch (Throwable e) {
            //addExceptionLog(e, sw.getTotalTimeMillis(), userName, fullName, deptName, callMethod);
            logger.error(String.format("顶级异常--执行目标[%s]方法:{}", callMethod), e);
            return CommonCons.ERROR;
        } finally {
            logger.info("Exit doControllerLog Method.");
        }
    }


    /**
     * 对Service层做异常拦截,并记录日志和通知
     * @param jp 连接点对象
     * @param e 异常对象
     */
    @AfterThrowing(pointcut = "sysServiceAspect()", throwing = "e")
    public void doServiceLog(JoinPoint jp, Throwable e) {
        logger.info("Enter doServiceLog Method ...");

        // 请求的方法
        String callMethod = String.format("%s.%s", jp.getTarget().getClass().getSimpleName(), jp.getSignature().getName());

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        try {

            String operContent = getAnnotationContent(jp, true);

            logger.info(String.format("%s操作内容:%s", callMethod, operContent));

            // 获取用户请求方法的参数并序列化为JSON格式字符串
            String params = "";
            if (CommonUtil.isNotNull(jp.getArgs()) && jp.getArgs().length > 0) {
                for ( int i = 0; i < jp.getArgs().length; i++) {
                    params += JsonUtil.toJson(jp.getArgs()[i]) + "; ";
                }
            }

            // 读取IP地址
            String ip = IPUtil.getIpAddr(request);
            // 读取用户名
            String userName = getUserName(request);
            // 读取异常信息
            String exceptionMsg = SysLogUtil.getExceptionMsg(e);
            // 读取部分日志
            //exceptionMsg = SysLogUtil.getPartForException(exceptionMsg, 10);
            // 创建异常信息
            SysExceptionLog sysExceptionLog = new SysExceptionLog(ip,userName,callMethod,operContent,exceptionMsg,DateUtil.getCurrentDateTime(),"0");
            // 保存异常日志
            SysLogUtil.addSysExceptionLog(sysExceptionLog);

            // 异常通知
            exceptionMsg = "请求接口："+callMethod+" <br/>请求参数：" + params + "<br/> 异常内容：<br/>" + exceptionMsg;
            NoticeAdminUtil.sendNotice(operContent+"[异常通知]", exceptionMsg);
        } catch (Exception ex) {
            logger.error(String.format("Service层异常--执行目标[%s]方法:{}", callMethod), ex);
        } finally {
            logger.info("Exit doServiceLog Method.");
        }
    }


    /**
     * 获取当前消耗时间
     * @param startTime
     * @return
     */
    private long getCurrentConsumeTime(final long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * 获取当前用户名称
     * @param request
     * @return
     */
    private String getUserName(HttpServletRequest request) {

        UserInfo userInfo = UserUtil.getLoginUser(request);

        String userName = CommonUtil.isNotNull(userInfo)?userInfo.getUserName():CommonCons.NOT_FOUND;

        return userName;
    }


    // 添加系统日志
    private void addSysLog(HttpServletRequest request, String userName, String moduleName, String operContent, long consumeTime) {
        // 读取IP地址
        String ip = IPUtil.getIpAddr(request);
        // 创建日志信息
        SysLog log = new SysLog(ip, userName, moduleName, operContent, DateUtil.getCurrentDateTime(), String.valueOf(consumeTime));
        // 产生日志记录
        SysLogUtil.addSysLog(log);
    }


    /**
     * 获取注解内容
     * @param jp
     * @param isServiceLog 是否是Service层Log, true or false
     * @return
     */
    public String getAnnotationContent(JoinPoint jp, boolean isServiceLog) {

        String description = CommonCons.EMPTY_STRING;

        String targetName = jp.getTarget().getClass().getName();
        String methodName = jp.getSignature().getName();
        Object[] arguments = jp.getArgs();
        Class targetClass = null;
        try {

            targetClass = Class.forName(targetName);

            Method[] methods = targetClass.getMethods();
            // 循环遍历获取目标方法
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    // 目标接口或者类中可能存在多个重载方法,需要参数类型匹配
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        if (isServiceLog)
                            description = method.getAnnotation(SysServiceLog.class).content();
                        else
                            description = method.getAnnotation(SysControllerLog.class).content();
                        break;
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            logger.error("获取Annotation Description时发生异常:", e);
        } catch (Exception e) {
            logger.error("获取Annotation Description时发生异常:", e);
        }

        return description;
    }

}
