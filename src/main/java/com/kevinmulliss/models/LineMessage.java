package com.kevinmulliss.models;

import java.util.Date;

public class LineMessage extends Message {
    public LineMessage(Date timestamp, String content, Author author) {
        super(timestamp, content, author);
    }
}
