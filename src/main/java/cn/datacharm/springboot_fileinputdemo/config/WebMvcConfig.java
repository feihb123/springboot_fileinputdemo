package cn.datacharm.springboot_fileinputdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        //如果是Windows系统
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/img/**")
                    //项目外路径
                    .addResourceLocations("file:G:/img/");

        } else {  //linux 和mac
            registry.addResourceHandler("/img/**")
                    .addResourceLocations("file:/webapps/img");
        }
    }


}
