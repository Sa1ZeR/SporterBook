package me.sa1zer_.sporterbook.payload.handler;

import org.springframework.beans.factory.annotation.Autowired;

public interface RequestHandleable {

    @Autowired
    default void registerByMySelf(HandlerManager handlerManager) {
        handlerManager.register(this.getClass().getName(), this);
    }
}
