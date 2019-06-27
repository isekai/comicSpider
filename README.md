# comicSpider
comicSpider是基于爬虫之上构建起来的JAVA WEB在线漫画项目。

## 简介
comicSpider是出于个人爱好而制作的开源漫画，源于一种收集癖好，希望将很多很经典，或者已经完结了很久，无人问津，面临失传的作品保存，持久化到自己的硬盘里，或者存在nas里，空闲的时间里就躺在床上慢慢刷着享受。

因为这些漫画已经算是庞大的数据，如果光是躺在硬盘里根本无法整理，所以这样的基础上打算开发一个方便观看的项目，并找到愿意帮助开发前端的人。

漫画的数据源来自于对某漫画网站的爬虫，数据持久化到硬盘和MySQL数据库中，自建爬虫代理，用到Redis缓存下载队列，用到线程池，使用Spring Framework全家桶搭建网络服务。

项目仅用于学术交流。

## 前端
因为前端水平还停留在HTML、Bootstrap、JQuery的程度，所以该项目的前端并是由他人协助开发，目前还处于开发中。

## 安装与搭建
```
git clone https://github.com/isekai/comicSpider.git
```
* 通过idea/ecilipse导入项目，安装lombok插件（因为广泛使用到，不安装会报错）。
* 使用MySQL新建数据库，导入init.sql文件。
* 编辑 *application.yml* 配置文件,配置MySQL、Redis和漫画下载目录。
* 运行ComicSpiderApplication。
* 打开浏览器，输入127.0.0.1:8080/proxy/start爬取代理（ip来自xicidaili，可用的极少），打开127.0.0.1:8080/cartoonmad/start爬取漫画资料和URL，打开127.0.0.1:8080/cartoonmad/download开始下载到硬盘。

## 其他
以上案例、代码及说明仅供学术交流和测试使用，请勿用作商业用途。如需转载请注明出处。