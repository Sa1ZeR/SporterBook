package me.sa1zer_.sporterbook.payload.handler;

import me.sa1zer_.sporterbook.domain.model.User;

import java.security.Principal;
import java.util.Optional;

public interface ServiceUpdateByRequestHandler<T, R> {

    T updateByRequest(R request, User... userArgs);
}
