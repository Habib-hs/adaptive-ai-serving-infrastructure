import grpc

from concurrent import futures

from app.generated import inference_pb2_grpc
from app.grpc.model_inference_service import ModelInferenceService


def start_grpc_server():

    server = grpc.server(
        futures.ThreadPoolExecutor(max_workers=10)
    )

    inference_pb2_grpc.add_ModelInferenceServiceServicer_to_server(
        ModelInferenceService(),
        server
    )

    server.add_insecure_port("[::]:50051")

    server.start()

    print("gRPC Server started on port 50051")

    return server
