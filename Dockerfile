FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /urlshortener/lib
COPY ${DEPENDENCY}/META-INF /urlshortener/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /urlshortener
ENTRYPOINT ["java","-cp","app:urlshortener/lib/*","hello.Application"]