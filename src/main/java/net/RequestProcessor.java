package net;

import com.abiz.header.HttpMethod;

public interface RequestProcessor {

    void doProcess();

    String getRequestUrl();

    String getRequestBody();

    HttpMethod getRequestMethod();
}
