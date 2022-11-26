package net;

import com.abiz.header.HttpMethod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BasedOnUrlDispatch implements RequestDispatcher {

    private static Map<String, EndPoint> endPoints = new HashMap<>();

    @Override
    public String dispatch(HttpMethod httpMethod, String url, String body) throws Exception {
        EndPoint endPoint = endPoints.get(url);
        String[] split = url.split("/");
        String controllerName = split[1];
        String methodName = split[2];
        String pathVariable = null;
        if (endPoint == null) {
            endPoint = lookUpEndPoint(httpMethod, controllerName, methodName);
            if (endPoint == null) return null;
            endPoints.put(url, endPoint);
        }
        if (split.length > 3) pathVariable = split[3];

        Object controller = endPoint.getController();
        if (httpMethod.equals(HttpMethod.POST)) {
            endPoint.getMethod().setAccessible(true);
            return (String) endPoint.getMethod().invoke(controller, body);
        } else if (httpMethod.equals(HttpMethod.GET)) {
            if (endPoint.getMethod().getParameterCount() == 0) {
                return (String) endPoint.getMethod().invoke(controller);
            } else {
                return (String) endPoint.getMethod().invoke(controller, pathVariable);
            }
        } else return null;
    }

    private EndPoint lookUpEndPoint(HttpMethod httpMethod, String controllerName, String methodName) {
        try {
            Class<?> controller = Class.forName("com.abiz.controller." + controllerName);
            Method[] classMethods = controller.getDeclaredMethods();
            Method endPointMethod = Arrays.stream(classMethods).filter(m1 -> (
                    m1.getName().equals(methodName)
                            &&
                            m1.isAnnotationPresent(HttpMapping.class)
                            &&
                            m1.getAnnotation(HttpMapping.class).method().equals(httpMethod))
            ).findFirst().get();
            EndPoint endPoint = new EndPoint(controller.getDeclaredConstructor().newInstance(),
                    endPointMethod,
                    httpMethod);
            return endPoint;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
