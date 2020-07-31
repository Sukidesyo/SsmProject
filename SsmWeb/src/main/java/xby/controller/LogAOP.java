package xby.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import xby.domain.SysLog;
import xby.domain.UserInfo;
import xby.service.ISysLogService;

import javax.servlet.http.HttpServletRequest;
import java.lang.management.MemoryType;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAOP {
    @Autowired
    private ISysLogService sysLogService;
    @Autowired
    private HttpServletRequest request;
    private Date visitTime;
    private Class clazz;
    private Method method;
    @Before("execution(* xby.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception{
        visitTime = new Date();
        clazz = jp.getTarget().getClass();

        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0)
        {
            method = clazz.getMethod(methodName);

        }
        else {
            Class classArgs[]=new Class[args.length];
            for(int i=0;i<args.length;i++)
            {
                classArgs[i]=args[i].getClass();
            }
            method=clazz.getMethod(methodName,classArgs);
        }

    }

    @After("execution(* xby.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception{

        String url=null;
        Date time = new Date();

        if(clazz!=null&&method!=null&&clazz!=LogAOP.class)
        {
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null)
            {
                String[] classValue = classAnnotation.value();
                String classUrl=classValue[0];

                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null)
                {
                    String[] methodValue = methodAnnotation.value();
                    String methodUrl=methodValue[0];
                    url=methodUrl+classUrl;
                }
            }
        }
        String ip=request.getRemoteAddr();
        SecurityContext context = SecurityContextHolder.getContext();
        String username = ((User) context.getAuthentication().getPrincipal()).getUsername();
        SysLog sysLog = new SysLog();
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setVisitTime(time);
        sysLog.setUsername(username);
        sysLog.setMethod(clazz.getName()+method.getName());
        sysLog.setExecutionTime(System.currentTimeMillis()-visitTime.getTime());

        sysLogService.save(sysLog);

    }
}
