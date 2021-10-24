package com.willianantunes.azureb2ccustompolicydesigner.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpRequest
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter
import org.springframework.web.client.RestTemplate


@Configuration
class RestConfiguration {
    @Bean
    fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        objectMapper.registerModule(JavaTimeModule())

        return objectMapper
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun restTemplate(objectMapper: ObjectMapper): RestTemplate {
        val restTemplateBuilder = RestTemplateBuilder()

        return restTemplateBuilder
            .additionalInterceptors(RestTemplateNoCacheHeaderModifierInterceptor())
            .messageConverters(commonMessageConverters(objectMapper))
            .build()
    }

    fun commonMessageConverters(objectMapper: ObjectMapper): MutableList<HttpMessageConverter<out Any>> {
        val converter = MappingJackson2HttpMessageConverter()
        converter.setSupportedMediaTypes(listOf(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON))
        converter.setObjectMapper(objectMapper)
        val formHttpMessageConverter = FormHttpMessageConverter()
        val stringHttpMessageConverter = StringHttpMessageConverter()
        val xmlHttpMessageConverter = Jaxb2RootElementHttpMessageConverter()

        val messageConverters = mutableListOf(
            converter,
            formHttpMessageConverter,
            stringHttpMessageConverter,
            xmlHttpMessageConverter
        )

        return messageConverters
    }

    class RestTemplateNoCacheHeaderModifierInterceptor : ClientHttpRequestInterceptor {
        private fun disableCaching(headers: HttpHeaders) {
            headers.setExpires(0)
            headers.setCacheControl("no-cache, no-store, max-age=0, must-revalidate")
        }

        override fun intercept(request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
            disableCaching(request.getHeaders())
            return execution.execute(request, body)
        }
    }
}
