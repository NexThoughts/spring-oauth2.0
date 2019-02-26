package com.buzzbuilder.buzzbuilderrest.response

public class HttpStatusAndMsg {

    // final Modification cannot be inherited
    public final static Map<Integer, String> exs = new HashMap<>();

    static {

        exs.put(200, "Request Success");
        exs.put(400, "Bad Request"); // parameter problem
        exs.put(401, "NotAuthorization"); // not certified
        exs.put(404, "Not Found");// Cannot find ULR
        exs.put(405, "Method Not Allowed"); // The request method is incorrect
        exs.put(415, "Unsupported Media Type"); // does not support Media Type
        exs.put(500, "Internal Server Error");//Server internal error
        exs.put(1000, "UnKnow Error");//Unknown error

        exs.put(1001, "UnKnowException"); // Unknown exception
        exs.put(1002, "RuntimeException"); // runtime exception
        exs.put(1003, "ClassCastException"); // type conversion exception
        exs.put(1004, "NullPointerException"); // null pointer exception
        exs.put(1005, "IOException");// IO exception
        exs.put(1006, "NoSuchMethodException");//Cannot find method
        exs.put(1007, "IndexOutOfBoundsException");// Array out of bounds
    }


}
