package com.willianantunes.azureb2ccustompolicydesigner.support

import mu.KotlinLogging
import org.apache.ws.commons.schema.*
import org.xml.sax.SAXParseException
import java.io.InputStream
import javax.xml.XMLConstants
import javax.xml.namespace.QName
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.Schema
import javax.xml.validation.SchemaFactory


private val logger = KotlinLogging.logger {}

data class EvaluationDetails(val valid: Boolean, val lineNumber: Int? = null, val columnNumber: Int? = null, val message: String? = null)

fun validXMLFileFormat(fileAsInputStream: InputStream): EvaluationDetails {
    val factory = DocumentBuilderFactory.newInstance()
    factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true)
    val documentBuilder = factory.newDocumentBuilder()

    try {
        documentBuilder.parse(fileAsInputStream)
    } catch (e: SAXParseException) {
        logger.warn { "Received XML has the following problem: ${e.message}" }
        return EvaluationDetails(false, e.lineNumber, e.columnNumber, e.message)
    }

    return EvaluationDetails(true)
}

fun validXMLFile(fileToBeValidated: InputStream, fileWithSchemaDefinition: InputStream): EvaluationDetails {
    val fileToBeValidatedStreamSource = StreamSource(fileToBeValidated)
    val fileWithSchemaDefinitionStreamSource = StreamSource(fileWithSchemaDefinition)

    try {
        val factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        val schema: Schema = factory.newSchema(fileWithSchemaDefinitionStreamSource)
        val validator = schema.newValidator()
        val validationFeature = "http://xml.org/sax/features/validation"
        val schemaFeature = "http://apache.org/xml/features/validation/schema"
        validator.setFeature(validationFeature, true)
        validator.setFeature(schemaFeature, true)
        validator.validate(fileToBeValidatedStreamSource)
    } catch (e: SAXParseException) {
        logger.warn { "Received XML has the following problem: ${e.message}" }
        return EvaluationDetails(false, e.lineNumber, e.columnNumber, e.message)
    }

    return EvaluationDetails(true)
}

fun retrieveChildElement(element: XmlSchemaElement): Pair<MutableList<XmlSchemaElement>, MutableMap<QName, MutableSet<XmlSchemaElement>>> {
    // Data to be retrieved later
    val schemaElements = mutableListOf<XmlSchemaElement>()
    val xsdElements = mutableMapOf<QName, MutableSet<XmlSchemaElement>>()
    // Inner function to add child given a XSD element
    val addChild: (QName, XmlSchemaElement) -> Unit = { qName: QName, child: XmlSchemaElement ->
        val values = xsdElements.getOrDefault(qName, mutableSetOf())
        values.add(child)
        xsdElements[qName] = values
    }

    // Inner function to handle main logic
    fun main(schemaType: XmlSchemaType?, qName: QName) {
        val isItIdentifiedAsComplexType = schemaType is XmlSchemaComplexType

        if (isItIdentifiedAsComplexType) {
            // Get all particles associated with that element Type
            val allParticles = (schemaType as XmlSchemaComplexType?)!!.particle

            when (allParticles) {
                is XmlSchemaSequence -> {
                    allParticles.items.forEach { sequenceMember: XmlSchemaSequenceMember ->
                        if (sequenceMember is XmlSchemaElement) {
                            val xmlSchemaElement = sequenceMember as XmlSchemaElement
                            schemaElements.add(xmlSchemaElement)
                            // Call the method to add the current element as child
                            addChild(qName, xmlSchemaElement)
                            // Call method recursively to get all subsequent element
                            main(xmlSchemaElement.schemaType, xmlSchemaElement.qName)
                        }
                    }
                }
            }
        }
    }
    // Doing the job!
    main(element.schemaType, element.qName)
    // As they're supposed to be filled...
    return Pair(schemaElements, xsdElements)
}


