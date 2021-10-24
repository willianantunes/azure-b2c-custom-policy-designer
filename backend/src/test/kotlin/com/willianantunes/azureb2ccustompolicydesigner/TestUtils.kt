package com.willianantunes.azureb2ccustompolicydesigner

import org.springframework.core.io.FileSystemResource
import java.nio.file.Path

fun retrieveFilePathFromTestResources(fileName: String): FileSystemResource {
    return FileSystemResource(Path.of("src", "test", "resources", fileName))
}
