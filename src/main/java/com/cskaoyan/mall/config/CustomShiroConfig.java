package com.cskaoyan.mall.config;

import com.cskaoyan.mall.shiro.AdminRealm;
import com.cskaoyan.mall.shiro.CustomRealmAuthenticator;
import com.cskaoyan.mall.shiro.WxRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Configuration
public class CustomShiroConfig {
    //shiroFilter
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //如果访问url没有通过认证，会重定向到loginUrl
        shiroFilterFactoryBean.setLoginUrl("/");
        //安全控制器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器配置
        //请求的url
        //anon匿名的
        //authc认证
        //perms权限
        HashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/admin/auth/login", "anon");
        filterChainDefinitionMap.put("/pic/**", "anon");
        filterChainDefinitionMap.put("/wx/home/index", "anon");
        filterChainDefinitionMap.put("/wx/catalog/index", "anon");
        filterChainDefinitionMap.put("/wx/catalog/current", "anon");
        filterChainDefinitionMap.put("/wx/auth/login_by_weixin", "anon");
        filterChainDefinitionMap.put("/wx/auth/login", "anon");
        filterChainDefinitionMap.put("/wx/auth/register", "anon");
        filterChainDefinitionMap.put("/wx/auth/reset", "anon");
        filterChainDefinitionMap.put("/wx/auth/regCaptcha", "anon");
        filterChainDefinitionMap.put("/wx/search/**", "anon");
        filterChainDefinitionMap.put("/wx/topic/**", "anon");
        filterChainDefinitionMap.put("/wx/brand/**", "anon");
        filterChainDefinitionMap.put("/wx/goods/**", "anon");
        filterChainDefinitionMap.put("/wx/storage/**", "anon");
        filterChainDefinitionMap.put("/wx/comment/**", "anon");

        filterChainDefinitionMap.put("/admin/**", "authc");
        filterChainDefinitionMap.put("/wx/**", "authc");
        //filterChainDefinitionMap.put("/admin/logout","logout");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    //securityManager
    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("adminRealm") AdminRealm adminRealm,
                                                     @Qualifier("wxRealm") WxRealm wxRealm,
                                                     CustomRealmAuthenticator customRealmAuthenticator,
                                                     DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(adminRealm);
        realms.add(wxRealm);
        securityManager.setRealms(realms);
        securityManager.setAuthenticator(customRealmAuthenticator);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean
    public CustomRealmAuthenticator customRealmAuthenticator(@Qualifier("adminRealm") AdminRealm adminRealm,
                                                             @Qualifier("wxRealm") WxRealm wxRealm) {
        CustomRealmAuthenticator customRealmAuthenticator = new CustomRealmAuthenticator();
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(adminRealm);
        realms.add(wxRealm);
        customRealmAuthenticator.setRealms(realms);
        return customRealmAuthenticator;
    }

    //声明式使用鉴权注解的开关
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    //自定义的sessionManager
    @Bean
    public DefaultWebSessionManager webSessionManager() {
        return new MallSessionManager();
    }
}
