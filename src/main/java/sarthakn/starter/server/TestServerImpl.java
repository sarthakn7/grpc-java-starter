package sarthakn.starter.server;

import io.grpc.stub.StreamObserver;
import sarthakn.starter.server.grpc.SimpleRequest;
import sarthakn.starter.server.grpc.SimpleResponse;
import sarthakn.starter.server.grpc.StarterServerGrpc;

public class TestServerImpl extends StarterServerGrpc.StarterServerImplBase {

    @Override
    public void simpleRpc(SimpleRequest request, StreamObserver<SimpleResponse> responseObserver) {
        SimpleResponse response = SimpleResponse.newBuilder()
                .setText("Response: " + request.getText())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
