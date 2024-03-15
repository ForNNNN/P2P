package com.yyw.p2p.common.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    //通过配置 Docket 对象，可以设置 Swagger 的各种配置选项，例如 API 的基本信息、安全配置、扫描哪些包下的接口等。
    //一旦配置完成，Swagger 将会自动生成 API 文档，并提供一个交互式的 UI 界面（比如当前为http://localhost:8110/swagger-ui.html），方便开发人员查看和测试 API。
    @Bean
    public Docket adminApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2) //指定使用 Swagger2 的规范
                .groupName("adminApi")
                .select()
                .paths(Predicates.and(PathSelectors.regex("/admin/.*"))) //以admin为前缀下的所有所有路径都属于adminApi这个group，在该页面下
                .build()
                .apiInfo(adminApiInfo());
    }


    //配置另一个Docket对象，用来区分上面后台管理的接口以及生成独立的测试页面
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .select()
                .paths(Predicates.and(PathSelectors.regex("/web/.*"))) //以web为前缀下的所有所有路径都属于webApi这个group，在该页面下
                .build()
                .apiInfo(webApiInfo());
    }


    //apiInfo对象用来对测试页面的标题，描述，联系人信息等进行配置
    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("P2P后台管理系统API文档")
                .description("本文档描述了P2P后台管理系统的各个模块的接口的调用方式")
                .version("1.6")
                .contact(new Contact("yyw","http://yyw.com","yueyangwang311@gmail.com"))
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("P2P前台网页API文档")
                .description("本文档描述了P2P前台各个模块的接口的调用方式")
                .version("1.6")
                .contact(new Contact("yyw","http://yyw.com","yueyangwang311@gmail.com"))
                .build();
    }

    /*
    Swagger相关的注解：
    1.标记在Controller类上：@Api
      标记在Controller中的requestHandler方法上：@ApiOperation
      标记在Controller中的requestHandler方法中的参数上：@ApiParam
    2.标记在entity实体类上：@ApiModel
      标记在entity实体类中的属性上：@ApiModelProperty
     */
}
