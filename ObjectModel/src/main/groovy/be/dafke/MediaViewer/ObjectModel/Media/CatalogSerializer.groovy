package be.dafke.MediaViewer.ObjectModel.Media

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator

class CatalogSerializer extends JsonSerializer<Catalog> {

    @Override
    void serialize(Catalog catalog, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        ToXmlGenerator xmlGen = (ToXmlGenerator) gen
        xmlGen.writeStartObject()
        for (Map.Entry<String, Media> entry : catalog.getMediaFiles().entrySet()) {
            xmlGen.writeObjectFieldStart("name")
            writeAttributes(xmlGen, entry.getKey())
            xmlGen.writeRaw(entry.getValue().toString())
            xmlGen.writeEndObject()
        }
        xmlGen.writeEndObject()
    }

    private void writeAttributes(ToXmlGenerator gen, String key) throws IOException {
        gen.setNextIsAttribute(true)
        gen.writeFieldName("key")
        gen.writeString(key)
        gen.setNextIsAttribute(false)
    }
}