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
        try {
            node = codec.readTree(parser);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            JsonNode nameNode = node.get("group_name");
            String name = nameNode.asText();
            JsonNode members = node.get("members");
            while(members.elements().hasNext()) {
                String userName = members.elements().next().get("userName").toString();
                String phoneNumber = members.elements().next().get("phoneNumber").toString();
                groupMembers.add(new Member(userName, phoneNumber));
            }
            System.out.println(members.elements().next().get("phoneNumber"));
            System.out.println(name);
            System.out.println(nameNode);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
