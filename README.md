# 小橙子外卖点单系统

 小橙子外卖点单系统是肥宅快乐队小学期程序设计作业，是一个前后端分离的外卖点单系统，采用SpringBoot+Vue进行开发，上传到github记录一下。

项目地址：[https://github.com/liznsalt/takeout](https://github.com/liznsalt/takeout)

[TOC]

## 整体效果

![系统界面截图1](doc/开发文档/杂/系统界面截图1.png)

![后台界面截图1](doc/开发文档/杂/后台界面截图1.png)

## 技术栈

### 后端技术栈

1. SpringBoot

2. SpringShiro

3. JdbcTemplate

4. MySQL

### 前端技术栈

1. Vue+Vuex+Vue-router

2. ElementUI

3. axios

## 部署

1. 数据库脚本放在数据库文件夹中，在MySQL中`create database takeout`后执行各sql脚本

2. 数据库配置在backend项目的resources目录下的application.yml文件中；由于本项目使用了阿里云的短信服务，所以要在backend项目下的`\common\util\SmsTool`里替换成自己的AK，如下图：

   ![阿里短信服务](doc/开发文档/杂/阿里短信服务.png)

3. 在IntelliJ IDEA中运行backend项目

4. 服务器端已经部署完毕，现在进入frontend目录，在命令行中依次执行如下命令：

   ```
   # 安装依赖，下面命令不成功则使用淘宝镜像cnpm
   npm install
   # 在 localhost:8081 启动项目
   npm run dev
   ```

5. 此时访问`http://localhost:8081/`就可以访问我们的前端项目了，管理员界面和上述步骤一样，访问`http://localhost:8082/`即可

6. 如果想打包则运行下面的命令:

   ```
   npm run build
   ```

   该命令运行成功后，frontend目录下生成一个dist文件夹，将该文件夹中的两个文件static和index.html拷贝到backend项目中resources/static/目录下，则可以直接在IDEA中启动项目即可

## 文档

课程报告和PPT在doc文件夹下,项目开发过程的开发文档在doc文件夹下的开发文档文件夹内

> 都很水233

## 请求流程

下图展示本系统的请求流程，此思路模仿项目[FEBS-Vue](https://github.com/wuyouzhuguli/FEBS-Vue), 可惜没用到缓存：

![请求流程图](doc/开发文档/请求流程图.jpg)



   
