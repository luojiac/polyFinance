package com.jnshu.interceptor;

import com.jnshu.dao.UserMapper1;
import com.jnshu.entity.User;
import com.jnshu.exception.MyException;
import com.jnshu.utils.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 支付相关拦截器,验证用户是否实名和绑定银行卡
 * @author wangqichao
 */
@Component(value = "payInterceptor")
public class PayInterceptor implements HandlerInterceptor {
    @Autowired
    UserMapper1 userMapper1;

    private static final Logger log= LoggerFactory.getLogger(PayInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取用户id
        long id=0;
        String uidS= CookieUtil.getCookieValue(httpServletRequest,"uid");
        if (uidS!=null) {
            id = Long.parseLong(uidS);
        }
        if(uidS==null){
            log.error("用户未登录，跳转登录接口");
            throw new MyException(10001,"用户未登录，请先登录");
        }
        System.out.println("开始支付拦截，用户id为"+id);
        User user= userMapper1.getUserRealStatusById(id);
        if(user.getRealStatus()==1&&user.getDefaultCard()>0){
            System.out.println("验证通过，放行");
            return true;
        }
        //如果未实名就去未实名的接口，返回的code是10010
        if(user.getRealStatus()!=1){
            log.error("用户"+id+"未实名，跳转到返回错误信息为未实名的接口");
//            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/a/u/unrealname");
            throw new MyException(10010,"用户未实名，请先实名申请");
//            return false;
        }
        else {
            log.error("用户"+id+"未绑定银行卡，跳转到返回错误信息为未绑卡的接口");
//            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/a/u/nocard");
            throw new MyException(10020,"用户未绑定银行卡，请先绑定银行卡");
//            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
