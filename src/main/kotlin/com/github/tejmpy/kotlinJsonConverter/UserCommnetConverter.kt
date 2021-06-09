package com.github.tejmpy.kotlinJsonConverter


object UserCommnetConverter {

    fun convert(
        userComments: List<UserComment?>,
        length: Int,
        word: String
    ): List<UserComment> {
        val result = mutableListOf<UserComment>()
        userComments.forEach {
            it?.apply {
                result.add(
                    it.cutComment(length).addWordToUsername(word)
                )
            }
        }
        return result
    }

    private fun UserComment.cutComment(length: Int = 20): UserComment {
        return UserComment(
            username = this.username,
            comment = this.comment.take(length)
        )
    }

    private fun UserComment.addWordToUsername(word: String = ""): UserComment {
        return UserComment(
            username = this.username + word,
            comment = this.comment
        )
    }
}