package com.abiz.header;

import com.abiz.request.Handler;

public interface RequestProcessor extends Handler {

    String getBody();

    HttpMethod getMethod();

}
