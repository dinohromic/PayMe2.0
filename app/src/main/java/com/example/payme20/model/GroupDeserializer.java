package com.example.payme20.model;

import android.icu.text.Edits;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

        try{
            JsonNode nameOfNode = node.get("group_name");
            name = nameOfNode.asText();
            JsonNode event = node.get("events");
            for (int i = 0; i < event.size(); i++) {
                String eventName = event.get(i).get("eventName").asText();
                String payer = event.get(i).get("payer").asText();
                boolean activeEvent = event.get(i).get("activeEvent").asBoolean();
                String date = event.get(i).get("date").asText();
                //Fixa att iterera över en MAP med eventPaymentDetails sedan över eventDebtList.
            }
            } catch (Exception e){
            e.printStackTrace();
        }

        return new Group(name, groupMembers);
    }
}
