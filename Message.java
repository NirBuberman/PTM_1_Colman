package test;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Message {

    public final byte[] data;
    public final String asText;
    public final double asDouble;
    public final Date date;

    // ctor from byte[]
    public Message(byte[] data) {
        this.data = data.clone();
        this.asText = new String(this.data, StandardCharsets.UTF_8);
        this.asDouble = parseDoubleOrNaN(this.asText);
        this.date = new Date();
    }

    // ctor from String
    public Message(String text) {
        this(text.getBytes(StandardCharsets.UTF_8));
    }

    // ctor from double
    public Message(double value) {
        this(Double.toString(value));
    }

    private static double parseDoubleOrNaN(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }
}
