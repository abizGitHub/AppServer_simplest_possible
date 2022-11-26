package net;

import com.abiz.header.HttpMethod;

public interface RequestDispatcher {

    String dispatch(HttpMethod method, String url, String body) throws Exception;

}
