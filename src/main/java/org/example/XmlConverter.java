package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlConverter {

    private static final XmlMapper mapper = new XmlMapper();

    public static XmlObject toXmlObject(final String xml) {
        try {
            return mapper.readValue(xml, XmlObject.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toXmlString(final XmlObject obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
