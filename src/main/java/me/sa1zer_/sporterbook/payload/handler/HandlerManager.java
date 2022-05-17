package me.sa1zer_.sporterbook.payload.handler;

public interface HandlerManager {

    void register(String name, RequestHandleable request);

    RequestHandleable getRequest(String name);
}
