FROM adoptopenjdk/openjdk11:debian-slim AS builder
WORKDIR /home
ADD . /home
RUN ./gradlew clean bootJar

FROM adoptopenjdk/openjdk11:debianslim-jre

ARG user=appuser
ARG group=appusergroup
ARG uid=1000
ARG gid=1000
ARG APP_HOME=/home/appuser

WORKDIR $APP_HOME

RUN ln -fs /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && export DEBIAN_FRONTEND=noninteractive \
    && apt-get update \
    && apt-get install -y tzdata curl \
    && dpkg-reconfigure --frontend noninteractive tzdata \
    && addgroup --system --gid ${gid} $group \
    && adduser --system --disabled-login --ingroup $group -u $uid --no-create-home $user \
    && chown -R ${uid}:${gid} $APP_HOME \
    && chmod -R ug+rw $APP_HOME

RUN curl https://omnitruck.chef.io/install.sh | bash -s -- -P inspec

COPY --from=builder  /home/build/libs/*.jar app.jar
ENV CHEF_LICENSE="accept"
USER ${user}

EXPOSE 8080

CMD ["java","-jar","./app.jar"]
