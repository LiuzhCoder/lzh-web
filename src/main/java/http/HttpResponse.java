package http;

import java.util.Map;

public class HttpResponse {
    private String url;
    private Integer statusCode;
    private String desc;

    private Map<String,String> header;

    private String body;
}
