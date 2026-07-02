# godikit-error

[![License](https://img.shields.io/github/license/lentiancn/godikit-error-java.svg)](https://github.com/lentiancn/godikit-error-java/LICENSE)
[![Codecov](https://img.shields.io/codecov/c/github/lentiancn/godikit-error-java.svg)](https://codecov.io/gh/lentiancn/godikit-error-java)
[![GitHub Release](https://img.shields.io/github/tag/lentiancn/godikit-error-java.svg?label=release)](https://github.com/lentiancn/godikit-error-java/releases)

English

## Features

- Unified Error utility for Java 8+
- Throwable stack trace conversion
- Utility methods for error handling

## Modules

| Module          | Purpose                              |
|-----------------|--------------------------------------|
| `godikit-error` | Core API (ThrowableUtils)            |

## Quick Start

### Maven

```xml
<dependency>
    <groupId>com.godikit.error</groupId>
    <artifactId>godikit-error</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage

```java
String stackTrace = ThrowableUtils.toString(exception);
```

## Requirements

- Java 8+
- Maven 3.6+

## License

`godikit-error` is licensed under the [MIT License](LICENSE).

## Links

- Docs: https://www.godikit.com/error
- Issues: https://github.com/lentiancn/godikit-error-java/issues