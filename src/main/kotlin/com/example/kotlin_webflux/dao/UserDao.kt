package com.example.kotlin_webflux.dao



import com.example.kotlin_webflux.model.User
import org.springframework.http.MediaType
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Repository
class UserDao {
    var users: MutableMap<Int, User> = hashMapOf()

    constructor() {
        this.users[1] = User(1, "chf")
        this.users[2] = User(2, "cjf")
    }


    fun selectUser(id: Int): Mono<User> {
        return Mono.justOrEmpty(this.users[id])
    }


    fun selectUsers(): Flux<User> {
        return Flux.fromIterable(this.users.values)
    }

    fun insertUser(user: Mono<User>): Mono<Void> {
        return user.doOnNext {
            val id = this.users.size + 1

            users.put(id, it)

            println("Saved ${user} with ${id}")

        }.thenEmpty(Mono.empty())

    }


}
