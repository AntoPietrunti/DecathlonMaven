FROM debian

MAINTAINER Antonio Pietrunti (a.pietrunti1@studenti.unimol.it)

RUN apt -y update
RUN apt -y install wget --no-install-recommends

RUN apt -y install openjdk-17-jdk --no-install-recommends

# RUN wget https://github.com/AntoPietrunti/DecathlonMaven/releases/download/v0.0.2/decathlon-java-11.jar

COPY /target/decathlon-v0.0.2.jar ./decathlon.jar
WORKDIR /

# CMD java -jar decathlon-java-11.jar
CMD java -jar decathlon.jar
