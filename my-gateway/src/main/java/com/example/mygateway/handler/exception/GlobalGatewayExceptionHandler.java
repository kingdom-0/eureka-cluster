package com.example.mygateway.handler.exception;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * @author Edison Wang
 * @version 1.0
 * @description TODO
 * @date 2023/5/5 9:58
 */

@Slf4j
@Getter
@Setter
public class GlobalGatewayExceptionHandler implements ErrorWebExceptionHandler {

    private List<HttpMessageReader<?>> messageReaders = Collections.emptyList();
    private List<HttpMessageWriter<?>> messageWriters = Collections.emptyList();
    private List<ViewResolver> viewResolvers = Collections.emptyList();
    private ThreadLocal<ResponseResult> threadLocal = new ThreadLocal<ResponseResult>();

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        log.error("Exception was handled by global exception handler. ex:{}",ex.getMessage());
        ServerHttpResponse response = exchange.getResponse();
        if(response.isCommitted()){
            return Mono.error(ex);
        }
        ResponseResult result = new ResponseResult(500,"test message");
        threadLocal.set(result);
        ServerRequest newRequest = ServerRequest.create(exchange,this.messageReaders);
        return RouterFunctions.route(RequestPredicates.all(),this::renderErrorResponse).route(newRequest)
                .switchIfEmpty(Mono.error(ex))
                .flatMap((handler)->handler.handle(newRequest))
                .flatMap((responseData)->write(exchange,responseData));
    }

    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request){
        return ServerResponse.status(ResponseResult.SERVICE_ERROR_CODE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(threadLocal.get()));
    }

    private Mono<? extends Void> write(ServerWebExchange exchange, ServerResponse response){
        exchange.getResponse().getHeaders().setContentType(response.headers().getContentType());
        return response.writeTo(exchange,new ResponseContext());
    }

    private class ResponseContext implements ServerResponse.Context{

        private ResponseContext(){

        }

        @Override
        public List<HttpMessageWriter<?>> messageWriters() {
            return GlobalGatewayExceptionHandler.this.messageWriters;
        }

        @Override
        public List<ViewResolver> viewResolvers() {
            return GlobalGatewayExceptionHandler.this.viewResolvers;
        }
    }
}

@Data
@RequiredArgsConstructor
@AllArgsConstructor
class ResponseResult{
    //TODO,add other type codes and messages.
    public static int SERVICE_ERROR_CODE = 500;
    public static String SERVICE_ERROR_MESSAGE = "test message";

    private int code;
    private String message;
}
