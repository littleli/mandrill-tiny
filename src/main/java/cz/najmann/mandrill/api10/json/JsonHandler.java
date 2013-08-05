package cz.najmann.mandrill.api10.json;

/**
 * User: littleli
 * Date: 4.8.13
 * Time: 22:20
 */
public interface JsonHandler {

    /**
     * Unmarshall given json string to object
     *
     * @param json string to contain json
     * @param t    type token which is represented by json parameter
     * @param <T>  type token
     * @return instance of the object created out of json input
     * @throws JsonError
     */
    <T> T fromJson(String json, Class<T> t);

    /**
     * Marshall given object to json
     *
     * @param o object to be serialised to json representation
     * @return string containing json
     * @throws JsonError
     */
    String toJson(Object o);
}
