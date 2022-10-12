package com.example.payme20.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/*
public class GroupDeserializer extends StdDeserializer<Group> {

    public GroupDeserializer(){
        this(null);
    }

    public GroupDeserializer(Class<Group> vc){
        super(vc);
    }

    @Override
    public Group deserialize(JsonParser parser, DeserializationContext deserializer){
        Group group = Factory.createGroup();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        try{
            JsonNode nameNode = node.get("name");
            String name = nameNode.asText();


        }

    }
*/
}
