package org.spring.cassandra.example.codec;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;
import org.joda.time.DateTime;

import java.nio.ByteBuffer;

/**
 * Created by bfitouri on 28/10/16.
 */
public class DateTimeCodec extends TypeCodec<DateTime> {


    public DateTimeCodec() {
        super(DataType.timestamp(), DateTime.class);
    }

    @Override
    public DateTime parse(String value) {
        if (value == null || value.equals("NULL"))
            return null;

        try {
            return DateTime.parse(value);
        } catch (IllegalArgumentException iae) {
            throw new InvalidTypeException("Could not parse format: " + value, iae);
        }
    }

    @Override
    public String format(DateTime value) {
        if (value == null)
            return "NULL";

        return Long.toString(value.getMillis());
    }

    @Override
    public ByteBuffer serialize(DateTime value, ProtocolVersion protocolVersion) {
        return value == null ? null : serializeNoBoxing(value.getMillis(), protocolVersion);
    }

    @Override
    public DateTime deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) {
        return bytes == null || bytes.remaining() == 0 ? null: new DateTime(deserializeNoBoxing(bytes, protocolVersion));
    }

    public ByteBuffer serializeNoBoxing(long value, ProtocolVersion protocolVersion) {
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.putLong(0, value);
        return bb;
    }

    public long deserializeNoBoxing(ByteBuffer bytes, ProtocolVersion protocolVersion) {
        if(bytes != null && bytes.remaining() != 0) {
            if(bytes.remaining() != 8) {
                throw new InvalidTypeException("Invalid 64-bits long value, expecting 8 bytes but got " + bytes.remaining());
            } else {
                return bytes.getLong(bytes.position());
            }
        } else {
            return 0L;
        }
    }
}
