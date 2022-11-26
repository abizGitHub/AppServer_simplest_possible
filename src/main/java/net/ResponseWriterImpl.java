package net;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class ResponseWriterImpl implements ResponseWriter {

    @Override
    public void write(HttpStatus status, String response, OutputStream outputStream) {
        try {
            Writer writer = new OutputStreamWriter(outputStream);
            if (status.equals(HttpStatus.OK)) {
                writer.write("HTTP/1.1 200");
                writer.write("\n");
                writer.write("Connection: keep-alive");
                writer.write("\n");
                writer.write("Content-Type: text/csv;charset=UTF-8");
                writer.write("\n");
                writer.write("Keep-Alive: timeout=60");
                writer.write("\n");
                //writer.write("Content-Length: " + (response.length()));
                writer.write("\n");
                writer.write(response);
                writer.write("\n");
                writer.flush();
                writer.close();
            } else {
                writer.write("HTTP/1.1 404");
                writer.write("\n");
                writer.write("\n");
                writer.write("Err!");
                writer.write("\n");
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
