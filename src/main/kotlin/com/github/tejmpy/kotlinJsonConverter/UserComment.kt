package com.github.tejmpy.kotlinJsonConverter

import com.fasterxml.jackson.annotation.JsonProperty

data class UserComment(
    @JsonProperty("user_name") val username: String,
    @JsonProperty("comment") val comment: String
)