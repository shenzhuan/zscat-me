本搜索支持luence  solr，需要启动blog cms服务

### 1.luence不需要安装任何东西 直接可以测试



- http://localhost:2008/index/init  会将blog的文章数据创建索引
- http://localhost:2008/index/init1  会将cms 的文章数据创建索引
- 然后在输入框可以输入数据查询
### 2.solr需要先下载群里的solr 文件

 

-  1.解压solr 放入d盘
-   2.解压tomcat，双击bin下面的startup.bat 启动tomcat
-   3.访问http://localhost:8080/solr/#/core1/query 可以测试
-   
-   http://localhost:2008/index1/init  会将blog的文章数据创建索引
-   http://localhost:2008/index1/init1  会将cms 的文章数据创建索引
-   然后在输入框可以输入数据查询
![输入图片说明](https://git.oschina.net/uploads/images/2017/0829/201626_9af43d02_134431.png "solr.png")
![输入图片说明](https://git.oschina.net/uploads/images/2017/0829/201728_41765dbd_134431.png "测试.png")
