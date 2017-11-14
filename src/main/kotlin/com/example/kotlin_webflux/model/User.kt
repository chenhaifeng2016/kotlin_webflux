package com.example.kotlin_webflux.model

import com.fasterxml.jackson.annotation.JsonProperty

class User(@JsonProperty("userid") val userid: Int, @JsonProperty("username") val username: String) {

    override fun toString(): String {
        return "User{" +
                "userid='" + userid + "\'" +
                ", username=" + username +
                "}"
    }
}
