package com.willianantunes.azureb2ccustompolicydesigner

import org.springframework.core.io.FileSystemResource
import java.nio.file.Path

fun retrieveFilePathFromTestResources(fileName: String): FileSystemResource {
    val originPaths = arrayOf("test", "resources")
    val pathsFromProvided = fileName.split("/").toTypedArray()
    val paths = originPaths + pathsFromProvided

    return FileSystemResource(Path.of("src", *paths))
}
