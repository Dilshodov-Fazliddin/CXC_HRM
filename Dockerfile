FROM openjdk:19
EXPOSE 8080
ARG JAR_FILE=target/CXC_HRM-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} cxc
ENTRYPOINT ["java","-jar","cxc"]