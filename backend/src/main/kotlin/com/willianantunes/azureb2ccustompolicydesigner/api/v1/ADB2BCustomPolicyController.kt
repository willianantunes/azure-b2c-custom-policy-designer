package com.willianantunes.azureb2ccustompolicydesigner.api.v1

import com.willianantunes.azureb2ccustompolicydesigner.services.CustomPolicySchemaFactory
import com.willianantunes.azureb2ccustompolicydesigner.support.EvaluationDetails
import com.willianantunes.azureb2ccustompolicydesigner.support.validXMLFile
import com.willianantunes.azureb2ccustompolicydesigner.support.validXMLFileFormat
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/api/v1/ad-b2c-custom-policies")
class ADB2BCustomPolicyController(private val customPolicySchemaFactory: CustomPolicySchemaFactory) {
    @PostMapping
    fun evaluatePolicy(@RequestParam("file") customPolicyFile: MultipartFile): EvaluationDetails {
        val filename = customPolicyFile.originalFilename!!
        logger.debug { "Initializing evaluation of $filename" }

        if (!filename.endsWith(".xml")) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "You should have posted a XML file")
        }

        if (!validXMLFileFormat(customPolicyFile.inputStream).valid) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Your XML file is invalid")
        }

        val schemaFile = customPolicySchemaFactory.fromLocalFile().third
        val result = validXMLFile(customPolicyFile.inputStream, schemaFile.inputStream())
        logger.debug { "Provided document evaluated as $result" }

        return result
    }
}
