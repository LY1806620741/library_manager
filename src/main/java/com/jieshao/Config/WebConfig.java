//package com.jieshao.Config;
//
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import com.jieshao.aspect.adminAspect;
//
//@Configuration
////@EnableWebMvc
//@ComponentScan
//public class WebConfig  extends WebMvcConfigurerAdapter implements ApplicationContextAware {
//
//	private ApplicationContext applicationContext;
//	
//	public WebConfig(){
//        super();
//    }
//	
//	@Override
//	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
//		// TODO Auto-generated method stub
//		this.applicationContext = applicationContext;
//	}
//	
//	@Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("/static/");
//
//        super.addResourceHandlers(registry);  
//        }
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//        //拦截规则：除了login，其他都拦截判断
//        registry.addInterceptor(new adminAspect()).addPathPatterns("/admin**").excludePathPatterns("/admin/login");
//        //registryr.addResourceHandler("/admin/**").addResourceLocations("classpath:/admin/");//神坑居然把静态资源拦截了
//        
//        //registryr.addResourceHandler("/admin").addResourceLocations("classpath:/META-INF/resources/admin");//主页资源
//        super.addInterceptors(registry);
//        
//    }
//	
////	@Override
////	public void addResourceHandlers(ResourceHandlerRegistry registry) {
////		registry.addResourceHandler("/").addResourceLocations("classpath:/META-INF/resources/");//主页资源
////	}
//
//}
