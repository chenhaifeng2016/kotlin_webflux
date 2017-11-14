package com.example.kotlin_webflux.Service


import com.example.kotlin_webflux.dao.UserDao
import com.example.kotlin_webflux.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Service
class UserService {

    @Autowired lateinit var userRepository: UserDao




    fun selectUser(request: ServerRequest): Mono<ServerResponse> {
        val userId = Integer.valueOf(request.pathVariable("id"))!!

        val notFound = ServerResponse.notFound().build()

        val monoUser = this.userRepository.selectUser(userId)

        return monoUser.flatMap { user -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(user)) }
                .switchIfEmpty(notFound)
    }

    fun selectUsers(request: ServerRequest): Mono<ServerResponse> {
        val users = this.userRepository.selectUsers()

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(users, User::class.java)
    }


    fun insertUser(request: ServerRequest): Mono<ServerResponse> {
        val user = request.bodyToMono(User::class.java)

        return ServerResponse.ok().build(this.userRepository.insertUser(user))
    }


}