from app.generated import inference_pb2_grpc
from app.generated import inference_pb2


class ModelInferenceService(inference_pb2_grpc.ModelInferenceServiceServicer):
    def Predict(self, request, context): 
     return inference_pb2.InferenceResponse(
            request_id=request.request_id,
            prediction="Positive",
            confidence=0.95,
            model_version="v1.0.0",
            status=inference_pb2.SUCCESS,
            error_message=""
        )

