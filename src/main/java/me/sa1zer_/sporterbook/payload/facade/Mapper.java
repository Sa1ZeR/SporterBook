package me.sa1zer_.sporterbook.payload.facade;

public interface Mapper<F, T> {

    T map(F from);
}
