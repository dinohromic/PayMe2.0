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
import java.util.ArrayList;
import java.util.List;

public class GroupDeserializer extends StdDeserializer<Group> {

    public GroupDeserializer(){
        this(null);
    }

    public GroupDeserializer(Class<Group> vc){
        super(vc);
    }

    @Override
    public Group deserialize(JsonParser parser, DeserializationContext deserializer){
        ObjectCodec codec = parser.getCodec();
        JsonNode node = null;
        List<Member> groupMembers = new ArrayList<>();
        List<Event> groupEvents = new ArrayList<>();
        String name = "";
        try {
            node = codec.readTree(parser);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            JsonNode nameNode = node.get("group_name");
            name = nameNode.asText();
            JsonNode members = node.get("members");
            for(int i = 0; i < members.size(); i++) {
                String userName = members.get(i).get("userName").asText();
                String phoneNumber = members.get(i).get("phoneNumber").asText();
                groupMembers.add(new Member(userName, phoneNumber));
            }
            //Gör för events också



        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Group(name, groupMembers);
    }
}
