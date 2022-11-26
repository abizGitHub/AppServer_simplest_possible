package com.abiz.header;

import com.abiz.request.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HeaderHandler implements Handler {

    private InputStream inputStream;

    private BufferedReader reader;

    private HttpMethod method;

    private String url;

    private int contentLength;

    public HeaderHandler(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void handle() {
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String firstLine = reader.readLine();
            String[] split = firstLine.split("[\s]");
            method = HttpMethod.valueOf(split[0]);
            url = split[1];
            String header;
            if (method.equals(HttpMethod.GET)) {
                while (!reader.readLine().isEmpty()) ;
            } else if (method.equals(HttpMethod.POST)) {
                while (!(header = reader.readLine()).startsWith("Content-Length")) ;
                String l = header.replace("Content-Length", "").replaceAll("[\s]*:[\s]*", "");
                contentLength = Integer.valueOf(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HttpMethod getMethod() {
        return method;
    }

    public int getContentLength() {
        return contentLength;
    }

    public String getUrl() {
        return url;
    }

    public BufferedReader getReader() {
        return reader;
    }
}
