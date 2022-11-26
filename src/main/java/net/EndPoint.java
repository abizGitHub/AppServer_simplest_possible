package net;

import com.abiz.header.HttpMethod;

import java.lang.reflect.Method;

public class EndPoint {

    private Object controller;
    private Method method;
    private HttpMethod httpMethod;

    public EndPoint(Object controller, Method method, HttpMethod httpMethod) {
        this.controller = controller;
        this.method = method;
        this.httpMethod = httpMethod;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public Object getController() {
        return controller;
    }

    public Method getMethod() {
        return method;
    }

}
