package com.willianantunes.azureb2ccustompolicydesigner.services

import com.willianantunes.azureb2ccustompolicydesigner.support.retrieveFilePathFromMainResources
import org.apache.ws.commons.schema.XmlSchema
import org.apache.ws.commons.schema.XmlSchemaCollection
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.Schema
import javax.xml.validation.SchemaFactory

@Service
class CustomPolicySchemaFactory(
    @Value("\${custom-policy-settings.schema-definition.local}") private val sourceFile: String
) {
    fun fromLocalFile(): Triple<XmlSchema, Schema, File> {
        val fileSystemResource = retrieveFilePathFromMainResources(sourceFile)

        // https://stackoverflow.com/a/66889828/3899136
        val schemaCollection = XmlSchemaCollection()
        val xmlSchema = schemaCollection.read(StreamSource(fileSystemResource.inputStream))

        // I got this error when I tried to use the file as it is: `lineNumber: 3714; columnNumber: 42; InvalidRegex`
        // You can understand why it happens here: https://stackoverflow.com/a/21576300/3899136
        val factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        val schema = factory.newSchema(fileSystemResource.file)

        return Triple(xmlSchema, schema, fileSystemResource.file)
    }
}
