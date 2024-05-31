package com.kevinmulliss.services;
import com.kevinmulliss.models.Message;

import java.io.FileNotFoundException;
import java.util.List;

public interface Parser {

    List<Message> parse(String filePath) throws FileNotFoundException;
}
