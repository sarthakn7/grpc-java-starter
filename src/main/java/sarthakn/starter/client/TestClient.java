package sarthakn.starter.client;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import sarthakn.starter.server.grpc.SimpleRequest;
import sarthakn.starter.server.grpc.SimpleResponse;
import sarthakn.starter.server.grpc.StarterServerGrpc;

public class TestClient {

    private final StarterServerGrpc.StarterServerBlockingStub blockingStub;

    public TestClient(Channel channel) {
        blockingStub = StarterServerGrpc.newBlockingStub(channel);
    }

    public static void main(String[] args) {
        String request = "test_text";

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        String response = new TestClient(channel).test(request);
        System.out.println(response);

        channel.shutdownNow();
    }

    private String test(String request) {
        SimpleResponse response = blockingStub.simpleRpc(SimpleRequest.newBuilder().setText(request).build());
        return response.getText();
    }
}
