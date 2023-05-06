package com.example.mygateway.config;

import com.example.mygateway.handler.exception.GlobalGatewayExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * @author Edison Wang
 * @version 1.0
 * @description TODO
 * @date 2023/5/5 11:29
 */

@Configuration
public class GlobalGatewayExceptionConfig {

    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                                             ServerCodecConfigurer serverCodecConfigurer){
        GlobalGatewayExceptionHandler globalGatewayExceptionHandler = new GlobalGatewayExceptionHandler();
        globalGatewayExceptionHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        globalGatewayExceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        globalGatewayExceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return globalGatewayExceptionHandler;
    }
}
