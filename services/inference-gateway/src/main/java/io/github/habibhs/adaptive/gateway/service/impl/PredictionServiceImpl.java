package io.github.habibhs.adaptive.gateway.service.impl;

import com.google.protobuf.ByteString;
import io.github.habibhs.adaptive.contracts.inference.v1.InferenceRequest;
import io.github.habibhs.adaptive.contracts.inference.v1.InferenceResponse;
import io.github.habibhs.adaptive.contracts.inference.v1.ModelInferenceServiceGrpc;
import io.github.habibhs.adaptive.gateway.dto.PredictionRequestDto;
import io.github.habibhs.adaptive.gateway.dto.PredictionResponseDto;
import io.github.habibhs.adaptive.gateway.service.PredictionService;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Builds the Protobuf gRPC request from the incoming REST DTO, invokes the
 * Python inference service over gRPC, and converts the gRPC response back
 * into a plain DTO. Protobuf types never leave this class.
 */
@Service
public class PredictionServiceImpl implements PredictionService {

    private static final Logger log = LoggerFactory.getLogger(PredictionServiceImpl.class);

    @GrpcClient("inference-service")
    private ModelInferenceServiceGrpc.ModelInferenceServiceBlockingStub inferenceStub;

    @Override
    public PredictionResponseDto predict(PredictionRequestDto request) {
        String requestId = UUID.randomUUID().toString();

        InferenceRequest grpcRequest = InferenceRequest.newBuilder()
                .setRequestId(requestId)
                .setModelName("default-model")
                .setPayload(ByteString.copyFromUtf8(request.text()))
                .setRequestTimestamp(System.currentTimeMillis())
                .build();

        log.info("Sending InferenceRequest [requestId={}] to Python gRPC service", requestId);

        InferenceResponse grpcResponse = inferenceStub.predict(grpcRequest);

        log.info("Received InferenceResponse [requestId={}, prediction={}, confidence={}]",
                grpcResponse.getRequestId(), grpcResponse.getPrediction(), grpcResponse.getConfidence());

        return new PredictionResponseDto(grpcResponse.getPrediction(), grpcResponse.getConfidence());
    }
}



