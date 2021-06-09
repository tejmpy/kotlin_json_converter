package com.github.tejmpy.kotlinJsonConverter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.net.URL
import kotlin.io.path.Path

@SpringBootApplication
class KotlinJsonConverterApplication

fun main(args: Array<String>) {
    runApplication<KotlinJsonConverterApplication>(*args)

    val input = args[0]
    val inputOpt = InputFilePlace.valueOf(args[1])

    val userComments =
        when (inputOpt) {
            InputFilePlace.LOCAL -> UserCommentFile.readAsUserComments(Path(input))
            InputFilePlace.REMOTE -> UserCommentFile.readAsUserComments(URL(input))
        }

    val convertUserComment = UserCommnetConverter.convert(
        userComments = userComments,
        length = 20,
        word = "さん"
    )

    convertUserComment.forEach {
        it.apply {
            println("user_name: $username, comment: $comment")
        }
    }
    UserCommentFile.write(convertUserComment)
}
