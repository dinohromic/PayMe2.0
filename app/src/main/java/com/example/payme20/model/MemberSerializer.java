package com.example.payme20.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.StringWriter;

public class MemberSerializer extends JsonSerializer<Member> {

    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public void serialize(Member value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        StringWriter write = new StringWriter();
        mapper.writeValue(write, value.toString());
        gen.writeFieldName(write.toString());
    }
}