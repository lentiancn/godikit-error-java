package com.godikit.error.code;

import java.util.Arrays;
import java.util.List;

public class BaseErrorCodeProvider implements ErrorCodeProvider {

    @Override
    public void register(List<ErrorCode> codes) {
        codes.addAll(Arrays.asList(BaseErrorCode.values()));
    }
}
