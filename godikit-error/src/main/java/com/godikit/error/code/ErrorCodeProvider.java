package com.godikit.error.code;

import java.util.List;

public interface ErrorCodeProvider {

    void register(List<ErrorCode> codes);
}