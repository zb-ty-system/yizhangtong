package com.zillionfortune.cif.support.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zillionfortune.cif.support.common.Constants;
import com.zillionfortune.cif.support.util.IPUtil;
import com.zillionfortune.cif.support.util.SessionUtil;

/**
 * 日志处理过滤器
 *
 * @author caobin
 * @version 1.0 2012-11-15
 */
public class LoggingProcessFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                                                                                                                      throws ServletException,
                                                                                                                      IOException {

        //扩展日志追踪号
        MDC.put(Constants.SESSION_ID, SessionUtil.getSessionId());
        MDC.put(Constants.CLIENT_IP, IPUtil.getIpAddr(request));
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.get(Constants.CLIENT_IP);
            MDC.clear();
        }
    }
}
