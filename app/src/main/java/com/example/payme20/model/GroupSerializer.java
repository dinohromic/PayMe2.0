package com.example.payme20.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class GroupSerializer extends StdSerializer<Group> {

    public GroupSerializer(){
        this(null);
    }

    public GroupSerializer(Class<Group> g){
        super(g);
    }

    @Override
    public void serialize(Group value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("group_name", value.getGroupName());
        gen.writeObjectField("members", value.getGroupMembers());
        gen.writeObjectField("event", value.getGroupEvents());
        //gen.writeObjectField("debt_handler", value.getDebtHandler());

        gen.writeEndObject();
    }
}
