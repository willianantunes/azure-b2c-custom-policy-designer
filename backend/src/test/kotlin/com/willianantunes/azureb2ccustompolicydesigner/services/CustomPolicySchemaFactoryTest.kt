package com.willianantunes.azureb2ccustompolicydesigner.services

import com.willianantunes.azureb2ccustompolicydesigner.support.retrieveChildElement
import org.apache.ws.commons.schema.XmlSchemaAttribute
import org.apache.ws.commons.schema.XmlSchemaComplexType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.xml.validation.Schema

@SpringBootTest
class CustomPolicySchemaFactoryTest @Autowired constructor(val customPolicySchemaFactory: CustomPolicySchemaFactory) {
    @Test
    fun `Should retrieve schema`() {
        // Act
        val (xmlSchema, schema, schemaFile) = customPolicySchemaFactory.fromLocalFile()
        // Assert
        assertThat(schemaFile.exists()).isTrue
        assertThat(schema).isInstanceOf(Schema::class.java).isNotNull
        assertThat(xmlSchema.targetNamespace).isEqualTo("http://schemas.microsoft.com/online/cpim/schemas/2013/06")
        assertThat(xmlSchema.schemaTypes.size).isEqualTo(112)
        assertThat(xmlSchema.elements.size).isEqualTo(1)
        for(key in xmlSchema.elements.keys){
            assertThat(key.localPart).isEqualTo("TrustFrameworkPolicy")
            val element = xmlSchema.elements[key]!!
            val (mutableList, mutableMap) = retrieveChildElement(element)
            assertThat(mutableList.size).isEqualTo(260)
            assertThat(mutableMap.size).isEqualTo(81)
            // Just to play with some assertions 😁
            // In regard to <BasePolicy>
            val qNameForBasePolicy = mutableMap.filter { it.key.localPart == "BasePolicy" }
            assertThat(qNameForBasePolicy.size).isEqualTo(1)
            val possibleElementsInBasePolicy = qNameForBasePolicy.values.first()
            assertThat(possibleElementsInBasePolicy.size).isEqualTo(2)
            // In regard to <RelyingParty>
            val qNameForRelyingParty = mutableMap.filter { it.key.localPart == "RelyingParty" }
            assertThat(qNameForRelyingParty.size).isEqualTo(1)
            val possibleElementsInRelyingParty = qNameForRelyingParty.values.first()
            assertThat(possibleElementsInRelyingParty.size).isEqualTo(4)
            // Retrieving one of its elements
            val filteringByDefaultUserJourney = possibleElementsInRelyingParty.filter { it.name == "DefaultUserJourney" }
            assertThat(filteringByDefaultUserJourney.size).isEqualTo(1)
            val defaultUserJourneyElement = filteringByDefaultUserJourney.first()
            val schemaComplexTypeForDefaultUserJourneyElement = defaultUserJourneyElement.schemaType as XmlSchemaComplexType
            assertThat(schemaComplexTypeForDefaultUserJourneyElement.attributes.size).isEqualTo(1)
            val attributeFromDefaultUserJourneyElement = schemaComplexTypeForDefaultUserJourneyElement.attributes.first()
            assertThat((attributeFromDefaultUserJourneyElement as XmlSchemaAttribute).name).isEqualTo("ReferenceId")
        }
    }
}
