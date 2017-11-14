package com.example.kotlin_webflux.router





import com.example.kotlin_webflux.Service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RequestPredicates.accept
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route


@Configuration
class RouterConfig {

    @Autowired lateinit var userService: UserService

    @Bean
    fun routerFunction(): RouterFunction<*> {

        return route(GET("/api/v1/users").and(accept(APPLICATION_JSON)), HandlerFunction { userService.selectUsers(it) })

                .and(route(GET("/api/v1/users/{id}").and(accept(APPLICATION_JSON)), HandlerFunction { userService.selectUser(it) }))
    }

}