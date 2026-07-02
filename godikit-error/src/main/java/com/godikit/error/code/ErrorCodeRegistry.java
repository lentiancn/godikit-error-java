package com.godikit.error.code;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorCodeRegistry {

    private static final Map<String, ErrorCode> CODE_MAP = new ConcurrentHashMap<>();
    private static final Map<String, ErrorCode> NAME_MAP = new ConcurrentHashMap<>();
    private static final ErrorCodeRegistry INSTANCE = new ErrorCodeRegistry();
    private static volatile boolean initialized = false;

    public static ErrorCodeRegistry getInstance() {
        return INSTANCE;
    }

    public synchronized void initialize() {
        if (initialized) {
            return;
        }

        ServiceLoader<ErrorCodeProvider> loader = ServiceLoader.load(ErrorCodeProvider.class);
        for (ErrorCodeProvider provider : loader) {
            List<ErrorCode> codes = new ArrayList<>();
            provider.register(codes);
            for (ErrorCode code : codes) {
                register(code);
            }
        }

        initialized = true;
    }

    public void register(ErrorCode code) {
        if (code == null) {
            return;
        }
        CODE_MAP.put(code.getCode(), code);
        if (code instanceof Enum) {
            NAME_MAP.put(((Enum<?>) code).name(), code);
        }
    }

    public ErrorCode getByCode(String code) {
        if (!initialized) {
            initialize();
        }
        return CODE_MAP.get(code);
    }

    public ErrorCode getByCode(String code, ErrorCode def) {
        ErrorCode errorCode = getByCode(code);
        if (errorCode != null) {
            return errorCode;
        }
        return def;
    }

    public ErrorCode getByName(String name) {
        if (!initialized) {
            initialize();
        }
        return NAME_MAP.get(name);
    }

    public List<ErrorCode> getAll() {
        if (!initialized) {
            initialize();
        }
        return new ArrayList<>(CODE_MAP.values());
    }

    public boolean contains(String code) {
        if (!initialized) {
            initialize();
        }
        return CODE_MAP.containsKey(code);
    }
}