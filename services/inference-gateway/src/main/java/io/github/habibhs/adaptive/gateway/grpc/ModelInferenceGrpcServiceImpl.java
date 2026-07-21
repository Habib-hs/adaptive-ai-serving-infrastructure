package io.github.habibhs.adaptive.gateway.grpc;

import io.github.habibhs.adaptive.contracts.inference.v1.InferenceRequest;
import io.github.habibhs.adaptive.contracts.inference.v1.InferenceResponse;
import io.github.habibhs.adaptive.contracts.inference.v1.ModelInferenceServiceGrpc;
import io.github.habibhs.adaptive.contracts.inference.v1.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class ModelInferenceGrpcServiceImpl extends ModelInferenceServiceGrpc.ModelInferenceServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(ModelInferenceGrpcServiceImpl.class);

    @Override
    public void predict(
            InferenceRequest request,
            StreamObserver<InferenceResponse> responseObserver) {

        log.info("Received inference request");
        log.info("Request ID      : {}", request.getRequestId());
        log.info("Model Name      : {}", request.getModelName());
        log.info("Payload Size    : {} bytes", request.getPayload().size());
        log.info("Metadata        : {}", request.getMetadataMap());
        log.info("Timestamp       : {}", request.getRequestTimestamp());

        InferenceResponse response = InferenceResponse.newBuilder()
                .setRequestId(request.getRequestId())
                .setPrediction("Dummy Prediction")
                .setConfidence(0.99f)
                .setModelVersion("v1.0")
                .setStatus(Status.SUCCESS)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

        log.info("Response sent successfully for Request ID: {}", request.getRequestId());
    }
}