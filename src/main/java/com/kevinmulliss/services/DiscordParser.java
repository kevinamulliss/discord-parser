package com.kevinmulliss.services;

import com.google.gson.Gson;
import com.kevinmulliss.models.ImportWrapper;
import com.kevinmulliss.models.Message;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class DiscordParser implements Parser {

    @Override
    public List<Message> parse(String filePath) throws FileNotFoundException {
        Gson gson = new Gson();
        ImportWrapper imported = gson.fromJson(new FileReader(filePath), ImportWrapper.class);

        return imported.getMessages();
    }
}
