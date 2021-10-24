package com.willianantunes.azureb2ccustompolicydesigner.support

import mu.KotlinLogging
import org.xml.sax.SAXParseException
import java.io.InputStream
import javax.xml.XMLConstants
import javax.xml.parsers.DocumentBuilderFactory

private val logger = KotlinLogging.logger {}

fun validXMLFile(fileAsInputStream: InputStream): Boolean {
    val factory = DocumentBuilderFactory.newInstance()
    factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true)
    val documentBuilder = factory.newDocumentBuilder()

    try {
        documentBuilder.parse(fileAsInputStream)
    } catch (e: SAXParseException) {
        logger.warn { "Received XML has the following problem: ${e.message}" }
        return false
    }

    return true
}
