package net;

import com.abiz.header.HeaderHandler;
import com.abiz.header.HttpMethod;
import com.abiz.request.RequestBodyHandler;

import java.io.InputStream;

public class RequestProcessorImpl implements RequestProcessor {

    private InputStream inputStream;
    private HeaderHandler headerHandler;
    private RequestBodyHandler bodyHandler;

    public RequestProcessorImpl(InputStream inputStream) {
        this.inputStream = inputStream;
        headerHandler = new HeaderHandler(inputStream);
    }

    @Override
    public void doProcess() {
        headerHandler.handle();
        headerHandler.getContentLength();
        if (headerHandler.getMethod().equals(HttpMethod.POST)) {
            bodyHandler = new RequestBodyHandler(headerHandler.getReader(), headerHandler.getContentLength());
            bodyHandler.handle();
        }
    }

    @Override
    public String getRequestUrl() {
        return headerHandler.getUrl();
    }

    @Override
    public String getRequestBody() {
        if (bodyHandler == null) return null;
        return bodyHandler.getBody();
    }

    @Override
    public HttpMethod getRequestMethod() {
        return headerHandler.getMethod();
    }

}
