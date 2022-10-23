package com.example.payme20.fileservice;

import com.example.payme20.model.Member;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;

/**
 * Custom deserializer of the class Member
 */
public class MemberDeserializer extends KeyDeserializer {
    @Override
    public Member deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        return new Member(key);
    }
}
