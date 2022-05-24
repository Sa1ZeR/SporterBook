#
# Build stage
#
#FROM gradle:7.4.2-jdk17-alpine@sha256:29e49b10984e585d8118b7d0bc452f944e386458df27371b49b4ac1dec4b7fda
#RUN mkdir /sporterbook
#WORKDIR /sporterbook
#COPY . /sporterbook
#RUN ./gradlew bootJar -x test
#
# Package stage
#
FROM openjdk:17.0.2-slim@sha256:779635c0c3d23cc8dbab2d8c1ee4cf2a9202e198dfc8f4c0b279824d9b8e0f22
COPY ./build/libs/sporterbook-0.0.1-SNAPSHOT.jar /sporterbook/
VOLUME /sporterbook/
WORKDIR /sporterbook/
EXPOSE 3000
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar sporterbook-0.0.1-SNAPSHOT.jar"]