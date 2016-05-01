FROM java:openjdk-8-jdk

RUN rm /bin/sh && ln -s /bin/bash /bin/sh

ENV GRADLE_VERSION 2.8
ENV GRADLE_HASH fe801ce2166e6c5b48b3e7ba81277c41
WORKDIR /usr/lib
RUN wget https://downloads.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
    && unzip "gradle-${GRADLE_VERSION}-bin.zip" \
    && ln -s "/usr/lib/gradle-${GRADLE_VERSION}/bin/gradle" /usr/bin/gradle \
    && rm "gradle-${GRADLE_VERSION}-bin.zip"

WORKDIR /usr/local/src/big-data

ENV GRADLE_HOME /usr/src/gradle
ENV PATH $PATH:$GRADLE_HOME/bin

ADD build.gradle /usr/local/src/big-data
ADD gradle /usr/local/src/big-data/gradle
ADD gradlew /usr/local/src/big-data
ADD src /usr/local/src/big-data/src

# Plan a
RUN gradle shadowJar && mv build/libs/big-data-1.0-SNAPSHOT-all.jar /usr/local/bin/big-data.jar

ENTRYPOINT ["java", "-jar", "/usr/local/bin/big-data.jar"]
