from contextlib import asynccontextmanager

from fastapi import FastAPI

from app.grpc.server import start_grpc_server


@asynccontextmanager
async def lifespan(app: FastAPI):
    grpc_server = start_grpc_server()
    yield
    grpc_server.stop(grace=5)


app = FastAPI(lifespan=lifespan)


@app.get("/health")
def health():
    return {
        "status": "UP"
    }

print("Starting FastAPI application...")