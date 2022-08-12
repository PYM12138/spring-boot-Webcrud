package com.atguigu.springbootwebsimulateing.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


public class MyI18n implements LocaleResolver {


    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getParameter("lang");
        //默认就是从运行环境中获取默认地区
        Locale locale=Locale.getDefault();
        if (!StringUtils.isEmpty(lang)){
            String[] s = lang.split("_");
            locale = new Locale(s[0], s[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
