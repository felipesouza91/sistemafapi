package com.sistemaf.core.openapi;


import com.fasterxml.classmate.TypeResolver;
import com.sistemaf.api.docs.models.CitiesModelOpenApi;
import com.sistemaf.api.exceptionhandler.Problem;
import com.sistemaf.api.docs.models.PageableModelOpenApi;
import com.sistemaf.domain.model.Cidade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableOpenApi
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket apiDocket() {
        //TODO: refactor pages models to a custons model after register
        TypeResolver typeResolver = new TypeResolver();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.sistemaf.api"))
                    .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, this.globalGetResponses())
                .globalResponses(HttpMethod.POST, this.globalPostResponses())
                .globalResponses(HttpMethod.PUT, this.globalPutResponses())
                .globalResponses(HttpMethod.DELETE, this.globalDeleteResponses())
                .ignoredParameterTypes(HttpServletResponse.class)
                .additionalModels(typeResolver.resolve(Problem.class))
                .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(Page.class, Cidade.class), CitiesModelOpenApi.class
                ))
                .apiInfo(this.apiInfo())
                .tags(
                    new Tag("Client", "Manager Client"),
                    this.apiTags().toArray(new Tag[0])
                );
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Sistema Api")
                .description("API for clients")
                .version("0.0.3")
                .contact(new Contact("Felipe Santana", "https://fsantana.dev", "felipe@fsantana.dev"))
                .build();
    }

    private List<Tag> apiTags() {
        return Arrays.asList(
                new Tag("City", "Manager citys"),
                new Tag("Neighborhood", "Manager neighborhoods"),
                new Tag("Treatment", "Manager treatments"),
                new Tag("Contact", "Manager contacts"),
                new Tag("Dvr", "Manager Dvrs"),
                new Tag("Manufacturer", "Manager manufacturers"),
                new Tag("Close Order of Service", "Manager Closure Order of Services"),
                new Tag("Access Group", "Manager Access Groups"),
                new Tag("Group", "Manager groups"),
                new Tag("Service Order Reason", "Manager Service Order Reasons"),
                new Tag("Service Order", "Manager Service Orders"),
                new Tag("Partition", "Manager Partitions"),
                new Tag("Permission", "Manager Permissions"),
                new Tag("Product", "Manager Products"),
                new Tag("Token", "Manager token"),
                new Tag("Recording Check", "Manager Recording Checks"),
                new Tag("Client Information", "Manager Client Information"),
                new Tag("User", "Manager users"),
                new Tag("Profile", "Manager profile")
        );
    }

    private List<Response> globalGetResponses() {
        return Arrays.asList(
            new ResponseBuilder().code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .description("Internal server error")
                    .representation(MediaType.APPLICATION_JSON)
                    .apply(r -> r.model(
                            m -> m.referenceModel( ref ->
                                    ref.key( k ->
                                            k.qualifiedModelName( q ->
                                                    q.namespace("com.sistemaf.api.exceptionhandler").name("Problem"))))
                    )).build(),
            new ResponseBuilder().code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                .description("Resource has no requested representation").build()
        );
    }

    private List<Response> globalPutResponses() {
        return this.globalPostResponses();
    }

    private List<Response> globalDeleteResponses() {
        return Arrays.asList(
                new ResponseBuilder().code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Invalid request ( Client error )").representation(MediaType.APPLICATION_JSON)
                        .apply(r -> r.model(
                                m -> m.referenceModel( ref ->
                                        ref.key( k ->
                                                k.qualifiedModelName( q ->
                                                        q.namespace("com.sistemaf.api.exceptionhandler").name("Problem"))))
                        )).build(),
                new ResponseBuilder().code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Internal server error").representation(MediaType.APPLICATION_JSON)
                        .apply(r -> r.model(
                                m -> m.referenceModel( ref ->
                                        ref.key( k ->
                                                k.qualifiedModelName( q ->
                                                        q.namespace("com.sistemaf.api.exceptionhandler").name("Problem"))))
                        )).build()
        );
    }

    private List<Response> globalPostResponses() {
        List<Response> list = new ArrayList<>();
        list.addAll(this.globalGetResponses());
        list.addAll(Arrays.asList(
            new ResponseBuilder().code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .description("Invalid request ( Client error )")
                .representation(MediaType.APPLICATION_JSON)
                    .apply(r -> r.model(
                            m -> m.referenceModel( ref ->
                                    ref.key( k ->
                                            k.qualifiedModelName( q ->
                                                    q.namespace("com.sistemaf.api.exceptionhandler").name("Problem"))))
                    )).build(),
            new ResponseBuilder().code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                .description("Unsupported Media Type").build()
        ));
        return list;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.
                addResourceHandler("/docs/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    }

   @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/docs")      	
        	.setViewName("redirect:/docs/index.html");
   }

}
