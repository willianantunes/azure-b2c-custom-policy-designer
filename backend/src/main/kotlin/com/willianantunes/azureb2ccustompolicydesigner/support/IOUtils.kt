package com.willianantunes.azureb2ccustompolicydesigner.support

import org.springframework.core.io.FileSystemResource
import java.nio.file.Path

fun retrieveFilePathFromMainResources(fileName: String): FileSystemResource {
    return FileSystemResource(Path.of("src", "main", "resources", fileName))
}
