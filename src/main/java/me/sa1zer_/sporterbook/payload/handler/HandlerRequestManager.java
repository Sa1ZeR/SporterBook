package me.sa1zer_.sporterbook.payload.handler;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class HandlerRequestManager implements HandlerManager {

    private final HashMap<String, RequestHandleable> requests = new HashMap<>();

    @Override
    public void register(String name, RequestHandleable request) {
        requests.put(name, request);
    }

    @Override
    public RequestHandleable getRequest(String name) {
        return requests.get(name);
    }
}
