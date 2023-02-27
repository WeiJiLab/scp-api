# scp-api

SCP(Security Check Platform) Backend APIs

## 本地环境搭建

### 安装mysql、Redis服务

```shell
# 启动mysql、Redis等服务
docker-compose --file local/docker/docker-compose.yml up -d

# 关闭mysql、Redis等服务
docker-compose --file local/docker/docker-compose.yml down -v
```

### 本地启动服务

* 需要安装Java17

* 构建

```shell
./gradlew clean :bootstrap:bootjar
```

* 项目启动

```shell
java -jar ./bootstrap/build/libs/bootstrap.jar
```

## 参考资料

### 参考网站UI设计

* https://gobysec.net/features