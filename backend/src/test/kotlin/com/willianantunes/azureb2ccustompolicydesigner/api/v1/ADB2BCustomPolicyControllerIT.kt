package com.willianantunes.azureb2ccustompolicydesigner.api.v1

import com.willianantunes.azureb2ccustompolicydesigner.retrieveFilePathFromTestResources
import org.assertj.core.api.Assertions.assertThat
import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.util.LinkedMultiValueMap

private const val REQUEST_PATH_AD_B2C_CUSTOM_POLICY = "/api/v1/ad-b2c-custom-policies"

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ADB2BCustomPolicyControllerIT @Autowired constructor(val restTemplate: TestRestTemplate) {
    @Test
    fun `Should return 400 given uploaded file is not a XML`() {
        // Arrange
        val headers = HttpHeaders()
        headers.contentType = MediaType.MULTIPART_FORM_DATA
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        val whereTheFileIsFromSystemResource = retrieveFilePathFromTestResources("dummy.txt")
        val multipart = LinkedMultiValueMap<String, FileSystemResource>()
        multipart.add("file", whereTheFileIsFromSystemResource)
        // Act
        val responseEntity = restTemplate.postForEntity(REQUEST_PATH_AD_B2C_CUSTOM_POLICY, HttpEntity(multipart, headers), String::class.java)
        // Assert
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(responseEntity.headers.contentType.toString()).isEqualTo(MediaType.APPLICATION_JSON_VALUE)
        val bodyAsJson = JSONObject(responseEntity.body)
        assertThat(bodyAsJson["message"]).isEqualTo("You should have posted a XML file")
    }

    @Test
    fun `Should return 400 given uploaded file is not a valid XML file`() {
        // Arrange
        val headers = HttpHeaders()
        headers.contentType = MediaType.MULTIPART_FORM_DATA
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        val whereTheFileIsFromSystemResource = retrieveFilePathFromTestResources("invalid.xml")
        val multipart = LinkedMultiValueMap<String, FileSystemResource>()
        multipart.add("file", whereTheFileIsFromSystemResource)
        // Act
        val responseEntity = restTemplate.postForEntity(REQUEST_PATH_AD_B2C_CUSTOM_POLICY, HttpEntity(multipart, headers), String::class.java)
        // Assert
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(responseEntity.headers.contentType.toString()).isEqualTo(MediaType.APPLICATION_JSON_VALUE)
        val bodyAsJson = JSONObject(responseEntity.body)
        assertThat(bodyAsJson["message"]).isEqualTo("Your XML file is invalid")
    }
}
