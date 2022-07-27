package com.cihanpacal.dininghall.config.logging;

import com.cihanpacal.dininghall.model.entity.UserLog;
import com.cihanpacal.dininghall.repository.UserLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class LogginInterceptor extends HandlerInterceptorAdapter {

    private final UserLogRepository userLogRepository;


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        HttpServletRequest requestCacheWrapperObject
                = new ContentCachingRequestWrapper(request);
        HttpServletResponse responseCacheWrapperObject
                =new ContentCachingResponseWrapper(response);

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName().equals("anonymousUser") ? "Anonim Kullanıcı" :authentication.getName() ;

        String requestUrl = requestCacheWrapperObject.getScheme() + "://"
                + requestCacheWrapperObject.getServerName()
                + ":" + requestCacheWrapperObject.getServerPort()
                + requestCacheWrapperObject.getContextPath()
                + requestCacheWrapperObject.getRequestURI()
                + (requestCacheWrapperObject.getQueryString()!=null ? "?"+requestCacheWrapperObject.getQueryString() : "" );

        String method=requestCacheWrapperObject.getMethod();
        String clientIp = requestCacheWrapperObject.getRemoteAddr();

       Integer statusCode=responseCacheWrapperObject.getStatus();



        if(!method.equals(HttpMethod.GET.toString())){
            UserLog userLog=new UserLog();

            userLog.setUsername(username);
            userLog.setStatusCode(statusCode);
            userLog.setHttpMethodName(method);
            userLog.setUrl(requestUrl);
            userLog.setIpAddress(clientIp);
            userLog.setTime(LocalDateTime.now());


            userLogRepository.save(userLog);
        }

    }
}
