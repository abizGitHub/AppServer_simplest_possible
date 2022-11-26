package com.abiz.request;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestBodyHandler implements Handler {

    private BufferedReader reader;
    private StringBuffer buffer;
    private int contentLength;

    public RequestBodyHandler(BufferedReader reader, int contentLength) {
        this.reader = reader;
        buffer = new StringBuffer(contentLength);
        this.contentLength = contentLength;
    }

    @Override
    public void handle() {
        try {
            int ix = -1;
            while ((ix++ < contentLength)) {
                buffer.append((char) reader.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBody() {
        return buffer.toString();
    }

}
