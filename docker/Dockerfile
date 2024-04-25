FROM openjdk:17-jdk-slim
LABEL maintainer="keepbang <rlqud1125@gmail.com>"
LABEL type="application"
LABEL jdk="17"
LABEL gradle="8.7"
LABEL title="hanghae-ecommerce"
LABEL version="1.0"

WORKDIR /apps
# copy app jar.
COPY build/libs/*.jar /apps/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/apps/app.jar"]