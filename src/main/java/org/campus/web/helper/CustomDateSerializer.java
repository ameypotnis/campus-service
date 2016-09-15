package org.campus.web.helper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.campus.domain.BaseEntity;

import java.io.IOException;
import java.util.Date;

/**
 * Created by amey on 4/8/16.
 */
public class CustomDateSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
        gen.writeString(BaseEntity.toString(value));
    }


}
