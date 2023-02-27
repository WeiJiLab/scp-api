# scp-api

SCP(Security Check Platform) Backend APIs

## 本地环境搭建

### 第一种方案 安装mysql、Redis服务

* 1、需要安装Java17
* 2、需要docker, docker-compose环境

* 3、快速创建mysql, redis服务

```shell
# 启动mysql、Redis等服务
docker-compose --file local/docker/docker-compose.yml up -d

# 关闭mysql、Redis等服务
docker-compose --file local/docker/docker-compose.yml down -v
```

* 4、项目构建

```shell
./gradlew clean build :bootstrap:bootjar -x test
```

* 5、项目启动

```shell
java -jar ./bootstrap/build/libs/bootstrap.jar
```

### 第二种方案

* 前提需要安装docker, docker-compose
* 执行以下脚本既可

```shell
./local/set-local-env.sh
```

## 参考资料

### 参考网站UI设计

* https://gobysec.net/features