# tsp-api

TSP(Security System Service Platform) Backend APIs

## 本地环境搭建

### 上传功能
以下为固件上传功能所需配置的目标目录，暂时使用与input_firmware统一目录
```shell
sudo mkdir -p /opt/input_firmware/
sudo ln -s {INPUT_FIRMWARE_REPO_PATH}/target /opt/input_firmware/target
```

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
