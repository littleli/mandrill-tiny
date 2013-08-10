package cz.najmann.mandrill.api10;

import cz.najmann.mandrill.api10.http.HttpHandler;
import cz.najmann.mandrill.api10.http.SimpleResponse;
import cz.najmann.mandrill.api10.json.JsonError;
import cz.najmann.mandrill.api10.json.JsonHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public final class MandrillServiceFactory {

    private final String key;
    private final HttpHandler httpHandler;
    private final JsonHandler jsonHandler;

    public MandrillServiceFactory(String key, JsonHandler jsonHandler, HttpHandler httpHandler) {
        this.key = key;
        this.jsonHandler = jsonHandler;
        this.httpHandler = httpHandler;
    }

    public <T> T getService(Class<T> category) {
        if (category.getDeclaringClass() == Category.class) {
            return category.cast(Proxy.newProxyInstance(category.getClassLoader(),
                    new Class<?>[]{category},
                    new ApiInvocationHandler(key, jsonHandler, httpHandler)));
        }
        throw new IllegalArgumentException("Only interfaces declared in Categories class can be passed as arguments.");
    }

    private static final class ApiInvocationHandler implements InvocationHandler {

        private static final String MANDRILL = "https://mandrillapp.com/api/1.0/";

        private static String serviceURL(Method m, String extension) {
            StringBuilder sb = new StringBuilder(MANDRILL);
            addMethod(sb, m);
            sb.append(extension);
            return sb.toString();
        }

        private static void addMethod(StringBuilder sb, Method method) {
            sb.append(method.getDeclaringClass().getSimpleName().toLowerCase()).append('/');
            String name = method.getName();
            for (char c : name.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    sb.append('-');
                }
                sb.append(Character.toLowerCase(c));
            }
        }

        private final String key;
        private final JsonHandler jsonHandler;
        private final HttpHandler httpHandler;

        ApiInvocationHandler(String key, JsonHandler jsonHandler, HttpHandler httpHandler) {
            this.key = key;
            this.jsonHandler = jsonHandler;
            this.httpHandler = httpHandler;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Struct params = Struct.with("key", key);

            if (args != null) {
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                for (int i = 0; i < args.length; i++) {
                    Annotation[] annotations = parameterAnnotations[i];
                    for (Annotation annotation : annotations) {
                        if (annotation instanceof Category.Param) {
                            Category.Param param = (Category.Param) annotation;
                            params.put(param.value(), args[i]);
                        }
                    }
                }
            }

            SimpleResponse response = httpHandler.doPost(serviceURL(method, ".json"), jsonHandler.toJson(params));

            int status = response.getStatus();
            if (status < 200 || status > 299) {
                throw new MandrillServiceError(status, jsonHandler.fromJson(response.getContent(), Error.class));
            }

            try {
                return jsonHandler.fromJson(response.getContent(), method.getReturnType());
            } catch (JsonError e) {
                throw new MandrillError(e);
            }
        }
    }
}
