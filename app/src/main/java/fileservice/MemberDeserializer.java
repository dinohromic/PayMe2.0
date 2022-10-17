package fileservice;

import com.example.payme20.model.Member;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;

public class MemberDeserializer extends KeyDeserializer {
    @Override
    public Member deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        return new Member(key);
    }
}
