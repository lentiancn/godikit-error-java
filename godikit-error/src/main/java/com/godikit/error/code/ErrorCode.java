package com.godikit.error.code;

import java.io.Serializable;

public interface ErrorCode extends Serializable {

    String getCode();

    String getDescription();
}