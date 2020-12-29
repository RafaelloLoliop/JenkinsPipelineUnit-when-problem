# Bug
Nested stages should not be executed if main stage is skipped

## To reproduce

```
docker build .
```

or 

```
RUN ./gradlew test
```