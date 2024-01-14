package io.wcygan.buf;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.wcygan.buf.hello.v1.HelloServiceGrpc;
import io.wcygan.buf.hello.v1.SayHelloRequest;
import io.wcygan.buf.hello.v1.SayHelloResponse;

public class App {
    public static void main(String[] args) {
        // Step 1: Create a channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        // Step 2: Create a stub
        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        // Step 3: Call the service method
        SayHelloRequest request = SayHelloRequest.newBuilder().setName("World").build();
        SayHelloResponse response = stub.sayHello(request);

        System.out.println(response.getMessage());

        // Don't forget to shut down the channel when you're done
        channel.shutdown();
    }
}