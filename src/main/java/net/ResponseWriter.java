package net;

import java.io.OutputStream;

public interface ResponseWriter {

    void write(HttpStatus status, String response, OutputStream outputStream);

}
