package org.example.service;

import java.util.List;

public interface Printer<T> {
    void print(List<T> data);

    void print(T data);
}
