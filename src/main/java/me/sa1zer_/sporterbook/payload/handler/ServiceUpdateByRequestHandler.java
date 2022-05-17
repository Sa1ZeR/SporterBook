package me.sa1zer_.sporterbook.payload.handler;

public interface ServiceUpdateByRequestHandler<T, R> {

    T updateByRequest(R request);
}
