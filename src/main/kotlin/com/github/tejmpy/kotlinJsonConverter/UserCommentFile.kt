package com.github.tejmpy.kotlinJsonConverter

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


object UserCommentFile {

    private const val outputDir = "out"

    fun readAsUserComments(url: URL): List<UserComment?> {
        val str = read(url)
        return str.toUserCommentList()
    }

    fun readAsUserComments(path: Path): List<UserComment?> {
        val str = Files.readAllLines(path).joinToString()
        return str.toUserCommentList()
    }

    @Throws(IOException::class)
    fun read(url: URL): String {
        try {
            val strm = url.openStream()
            val inputStreamReader = InputStreamReader(strm)
            val inb = BufferedReader(inputStreamReader)
            var line: String?
            val stringBuilder = StringBuilder()
            while (inb.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            inb.close()
            inputStreamReader.close()
            strm.close()
            return stringBuilder.toString()
        } catch (e: IOException) {
            println("Error: $e")
            return ""
        }
    }

    fun write(userComments: List<UserComment?>) {
        val filename = "usercomment-${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"))}.txt"
        File("$outputDir/$filename").createNewFile()
        val file = File("$outputDir/$filename")
        val bufferedWriter = file.bufferedWriter()
        bufferedWriter.use { bw ->
            userComments.forEach {
                it?.apply {
                    bw.appendLine("user_name: $username, comment: $comment")
                }
            }
            true
        }
    }

    private fun String.toUserCommentList(): List<UserComment?> {
        if (this.isEmpty()) {
            return listOf()
        }
        val objectMapper = ObjectMapper()
        return objectMapper.readValue(this, object : TypeReference<List<UserComment?>?>() {}) ?: listOf()
    }
}