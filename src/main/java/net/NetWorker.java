package net;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Callable;

public class NetWorker implements Callable<HttpStatus> {

    private Socket socket;

    public NetWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public HttpStatus call() throws Exception {
        System.err.println(Thread.currentThread());
        InputStream inputStream = socket.getInputStream();
        RequestProcessor processor = new RequestProcessorImpl(inputStream);
        processor.doProcess();
        RequestDispatcher dispatcher = new BasedOnUrlDispatch();
        String response = dispatcher.dispatch(
                processor.getRequestMethod(),
                processor.getRequestUrl(),
                processor.getRequestBody()
        );
        OutputStream outputStream = socket.getOutputStream();
        HttpStatus status;
        if (response == null) {
            status = HttpStatus.Error;
        } else
            status = HttpStatus.OK;
        new ResponseWriterImpl().write(status, response, outputStream);
        Thread.sleep(500);
        inputStream.close();
        outputStream.close();
        socket.close();
        return HttpStatus.OK;
    }
}
