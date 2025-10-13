package com.shopeasy.application.mapper;

public interface Mapper<I, O> {
    O map(I input);
}
