package org.itheima.easychatback.aspect;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.itheima.easychatback.Utils.RedisUtils;
import org.itheima.easychatback.Utils.StringTools;
import org.itheima.easychatback.annotation.GlobalInterceptor;
import org.itheima.easychatback.entity.constants.Constants;
import org.itheima.easychatback.entity.dto.TokenUserInfoDto;
import org.itheima.easychatback.entity.enums.ResponseCodeEnum;
import org.itheima.easychatback.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;


@Aspect
@Component("globalOperationAspect")
public class GlobalOperationAspect {

    @Resource
    private RedisUtils redisUtils;

    public static final Logger logger= LoggerFactory.getLogger(GlobalOperationAspect.class);


    @Before("@annotation(org.itheima.easychatback.annotation.GlobalInterceptor)")
    public void interceptorDo(JoinPoint joinPoint){


        try {
            Method method=((MethodSignature)joinPoint.getSignature()).getMethod();
            GlobalInterceptor interceptor=method.getAnnotation(GlobalInterceptor.class);
            if(interceptor==null){
                return;
            }
            if(interceptor.checkLogin()||interceptor.checkAdmin())
            {
                checkLogin(interceptor.checkAdmin());
            }
        } catch (BusinessException e) {
            logger.error("全局拦截异常",e);
            throw e;
        }catch (Exception e)
        {
            logger.error("全局拦截异常",e);
            throw new BusinessException(ResponseCodeEnum.CODE_600.getMsg());
        }catch (Throwable e)
        {
            logger.error("全局拦截异常",e);
            throw new BusinessException(ResponseCodeEnum.CODE_600.getMsg());
        }

    }


    private void checkLogin(Boolean checkAdmin)
    {
       HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
       String token=request.getHeader("token");
       if(StringTools.isEmpty(token))
       {
           throw new BusinessException(ResponseCodeEnum.CODE_901.getMsg());
       }
        System.out.println(JSON.parseObject((String) redisUtils.get(Constants.REDIS_KEY_WS_TOKEN+token),TokenUserInfoDto.class));
        TokenUserInfoDto tokenUserInfoDto= JSON.parseObject((String) redisUtils.get(Constants.REDIS_KEY_WS_TOKEN+token),TokenUserInfoDto.class);
        if(tokenUserInfoDto==null)
        {
            throw new BusinessException(ResponseCodeEnum.CODE_901.getMsg());
        }
        if(checkAdmin&& !tokenUserInfoDto.getAdmin())
        {
            throw new BusinessException(ResponseCodeEnum.CODE_404.getMsg());
        }
    }
    
}
