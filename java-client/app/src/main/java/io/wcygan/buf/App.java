package io.wcygan.buf;

public class App {
    // TODO @wcygan: send a gRPC message to the server
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
