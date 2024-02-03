package http;

public interface service {
    void doGet(HttpRequest httpRequest,HttpResponse response);
    void doPost(HttpRequest httpRequest,HttpResponse response);
}
