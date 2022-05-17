package me.sa1zer_.sporterbook.payload.facade;

public interface AdminMapper<F, T> {

    T adminMap(F from);
}
