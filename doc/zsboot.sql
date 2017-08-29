/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : zsboot

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-29 12:36:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for a_blog
-- ----------------------------
DROP TABLE IF EXISTS `a_blog`;
CREATE TABLE `a_blog` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `summary` text COMMENT '摘要',
  `typename` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `typeId` bigint(11) DEFAULT NULL,
  `releaseDate` datetime DEFAULT NULL COMMENT '发布时间',
  `clickHit` int(11) DEFAULT NULL,
  `replyHit` int(11) DEFAULT '0',
  `content` longtext,
  `keyWord` varchar(200) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `blogger_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `typeId` (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_blog
-- ----------------------------
INSERT INTO `a_blog` VALUES ('1', 'springmvc dubbox mybatis bootstreap ace商城', 'http://git.oschina.net/java798/java/blob/master/1.md?dir=0&filepath=1.md&oid=e8b4e10ec67f1c911f5c0a37aed572d7fe33c16a&sha=7cfe7cdbf4af27bbeb4237689b793c916cfc49ac', '456', 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '2', '2016-10-27 19:16:09', '0', '0', null, '', null, '3');
INSERT INTO `a_blog` VALUES ('2', '基于Spring Boot/Spring Security/thymeleaf的通用后台管理系统', 'http://git.oschina.net/java798/java/blob/master/2.md?dir=0&filepath=2.md&oid=3b239703a89cda6cf92e847fa6617ae1c14d2fda&sha=74da60b3f436a5c846597e4c6a7eadc828d007b0', 'https://github.com/jonsychen/admin', 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '1', '2016-10-27 19:19:11', '0', '0', null, '', null, '3');
INSERT INTO `a_blog` VALUES ('3', 'motan springmvc mybatis分布式框架', 'http://git.oschina.net/java798/java/blob/master/3.md?dir=0&filepath=3.md&oid=cc25fe47830f52661884d164f1ac51062b2ea18a&sha=74da60b3f436a5c846597e4c6a7eadc828d007b0', 'http://git.oschina.net/catshen/zsWing', 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '1', '2016-10-27 19:20:05', '0', '0', null, '', null, '3');
INSERT INTO `a_blog` VALUES ('4', 'dubbo springmvc mybatis 分布式框架', 'http://git.oschina.net/java798/java/blob/master/4.md?dir=0&filepath=4.md&oid=323d323ecf51e29db214c559f3af0686ff8a668f&sha=74da60b3f436a5c846597e4c6a7eadc828d007b0', null, 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '1', '2016-10-27 19:20:31', '0', '0', null, '', null, '3');
INSERT INTO `a_blog` VALUES ('5', 'springmvc mybatis cms系统', 'https://item.taobao.com/item.htm?spm=a1z10.1-c.w4004-15435279502.10.Kj2FOi&id=544466360964', null, 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '1', '2016-10-27 19:20:59', '0', '0', null, '', null, '3');
INSERT INTO `a_blog` VALUES ('6', 'springmvc mybatis blog系统', 'https://item.taobao.com/item.htm?spm=a1z10.1-c.w4004-15435279502.12.Kj2FOi&id=544459900907', null, 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '3', '2016-10-27 19:21:57', '0', '0', null, '', null, '3');
INSERT INTO `a_blog` VALUES ('7', 'springmvc mybatis zsShop商城', 'http://git.oschina.net/java798/java/blob/master/1.md?dir=0&filepath=1.md&oid=e8b4e10ec67f1c911f5c0a37aed572d7fe33c16a&sha=7cfe7cdbf4af27bbeb4237689b793c916cfc49ac', null, 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '2', '2016-10-27 19:22:21', '0', '0', null, '', null, '3');
INSERT INTO `a_blog` VALUES ('8', 'springboot springclod mybatis 通用mapper redis ，kafka ，beetl ，freemaker blog cms shop 权限管理', 'http://git.oschina.net/java798/java/blob/master/5.md?dir=0&filepath=5.md&oid=5d38eeb10f57945b995aa685a373ba98f1f90047&sha=74da60b3f436a5c846597e4c6a7eadc828d007b0', null, 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '2', '2016-10-27 19:25:43', '0', '0', null, '', null, '3');
INSERT INTO `a_blog` VALUES ('9', 'springmvc mybatis zscat分布式框架', 'https://item.taobao.com/item.htm?spm=2013.1.w4023-13644460896.2.zzJJnx&id=531768085930', null, 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '1', null, null, '0', null, null, null, null);
INSERT INTO `a_blog` VALUES ('10', 'dubbo springmvc hibernate activit5 工作流分布式框架', 'https://item.taobao.com/item.htm?spm=a1z10.1-c.w4004-15435279502.16.Kj2FOi&id=531796684149', null, 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '1', null, null, '0', null, null, null, null);
INSERT INTO `a_blog` VALUES ('11', 'springmvc mybatis jeeplus H+商城', 'https://item.taobao.com/item.htm?spm=a1z10.1-c.w4004-15435279502.2.Kj2FOi&id=551249776866', null, 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '2', null, null, '0', null, null, null, null);
INSERT INTO `a_blog` VALUES ('12', 'spring boot + thymeleaf 开发个人博客项目. http://www.eumji025.com', 'http://git.oschina.net/java798/java/blob/master/6.md?dir=0&filepath=6.md&oid=f75f8eb028fa9e57da560527cbe8c8aab7a715e9&sha=d9475f6401ef156449dd2008b26f83c7be862a31', 'http://git.oschina.net/catshen/zblog', 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '3', null, null, '0', null, null, null, null);
INSERT INTO `a_blog` VALUES ('13', 'Docker+SpringBoot+Mybatis+thymeleaf的开源Java博客系统 https://blog.hanshuai.xin/ ', 'http://git.oschina.net/java798/java/blob/master/7.md?dir=0&filepath=7.md&oid=38f6069bcec2d963af3d50f89f42cf3b82ab2ca5&sha=d9475f6401ef156449dd2008b26f83c7be862a31', 'http://git.oschina.net/catshen/My-Blog', 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '3', null, null, '0', null, null, null, null);
INSERT INTO `a_blog` VALUES ('14', 'CMS是由Spring-SpringMVC-MyBatis-MySQL', 'http://git.oschina.net/java798/java/blob/master/8.md?dir=0&filepath=8.md&oid=8ec249e656c397840156a13b1f96b94fa53a3a93&sha=d9475f6401ef156449dd2008b26f83c7be862a31', 'http://git.oschina.net/catshen/TeaCMS', 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '3', null, null, '0', null, null, null, null);
INSERT INTO `a_blog` VALUES ('15', 'CMS Spring Data JPA、Hibernate、Shiro、 Spring MVC、Layer、Mysql', 'http://git.oschina.net/java798/java/blob/master/9.md?dir=0&filepath=9.md&oid=3fc46d7d5489438e251392f906d4b60d8e00b37a&sha=d9475f6401ef156449dd2008b26f83c7be862a31', 'http://git.oschina.net/catshen/tianti', 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '1', null, null, '0', null, null, null, null);
INSERT INTO `a_blog` VALUES ('16', 'jadmin是基于jsyso + LayUI搭建的后台管理系统，目前已自带权限控制模块', 'http://git.oschina.net/java798/java/blob/master/10.md?dir=0&filepath=10.md&oid=6082ca4d5c6dbb9aba5d2fb5cf2ac86d1e2060a5&sha=d9475f6401ef156449dd2008b26f83c7be862a31', 'http://git.oschina.net/catshen/jadmin', 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '1', null, null, '0', null, null, null, null);
INSERT INTO `a_blog` VALUES ('17', 'JFinalShop商城系统是B2C模式的电子商城', 'http://git.oschina.net/java798/java/blob/master/11.md?dir=0&filepath=11.md&oid=e66d7e7181f0f47d1f2cb84dae28ae606cd4ce11&sha=d9475f6401ef156449dd2008b26f83c7be862a31', null, 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '2', null, null, '0', null, null, null, null);
INSERT INTO `a_blog` VALUES ('18', 'Spring MVC、Shiro、MyBatis、Bootstrap、Vue2.0等框架，包含：管理员管理、角色管理、菜单管理', 'http://git.oschina.net/java798/java/blob/master/12.md?dir=0&filepath=12.md&oid=3fc46d7d5489438e251392f906d4b60d8e00b37a&sha=d9475f6401ef156449dd2008b26f83c7be862a31', null, 'https://github.com/jonsychen/admin/raw/master/etc/index.png', '1', null, null, '0', null, null, null, null);
INSERT INTO `a_blog` VALUES ('19', 'Spring Boot 结合网络爬虫开发的完整视频网站，演示地址：http://www.ictgu.cn', 'http://git.oschina.net/java798/java/blob/master/13.md', null, null, '1', null, null, '0', null, null, null, null);

-- ----------------------------
-- Table structure for a_blogtype
-- ----------------------------
DROP TABLE IF EXISTS `a_blogtype`;
CREATE TABLE `a_blogtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(30) DEFAULT NULL,
  `orderNo` int(11) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记(0活null 正常 1,删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_blogtype
-- ----------------------------
INSERT INTO `a_blogtype` VALUES ('1', 'cms项目', '1', null, null, null, null, null, '0');
INSERT INTO `a_blogtype` VALUES ('2', '商城项目', '2', null, null, null, null, null, '0');
INSERT INTO `a_blogtype` VALUES ('3', 'blog项目', '3', null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for a_link
-- ----------------------------
DROP TABLE IF EXISTS `a_link`;
CREATE TABLE `a_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `linkName` varchar(100) DEFAULT NULL,
  `linkUrl` varchar(200) DEFAULT NULL,
  `orderNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_link
-- ----------------------------
INSERT INTO `a_link` VALUES ('1', 'zscat官网', 'http://blog.csdn.net/a1439226817/article/details/68483563', '1');
INSERT INTO `a_link` VALUES ('2', 'zscat商城', 'http://blog.csdn.net/a1439226817/article/details/64121666', '2');
INSERT INTO `a_link` VALUES ('3', 'zscat内容管理系统', 'https://shop150554856.taobao.com/?spm=2013.1.1000126.d21.yzAhaP', '3');
INSERT INTO `a_link` VALUES ('4', 'zscat后台', 'http://zscat.top/', '4');
INSERT INTO `a_link` VALUES ('5', 'dangdang', 'http://blog.csdn.net/a1439226817/article/details/64437915', '5');
INSERT INTO `a_link` VALUES ('6', 'zscat店铺', 'https://shop150554856.taobao.com/?spm=2013.1.1000126.d21.yzAhaP', '6');

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `color` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '颜色 文章  视频',
  `content` longtext CHARACTER SET utf8 COMMENT '内容',
  `createDate` date DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  `hits` int(11) DEFAULT '0' COMMENT '点击量',
  `htmlid` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT 'htmlId',
  `image` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片',
  `isWord` int(11) DEFAULT '0' COMMENT 'isWord',
  `keywords` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '标题',
  `weight` int(11) DEFAULT '0' COMMENT '权重',
  `CATEGORY_ID` bigint(20) DEFAULT NULL,
  `delFlag` int(11) NOT NULL DEFAULT '0',
  `updateDate` date DEFAULT NULL,
  `createby_id` bigint(20) DEFAULT NULL,
  `updateby_id` bigint(20) DEFAULT NULL,
  `moreimage` varchar(3600) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORYNAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `siteid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK935C9C00CA4E9944` (`CATEGORY_ID`) USING BTREE,
  KEY `FK935C9C00E80591EC` (`updateby_id`),
  KEY `FK935C9C00ECC85199` (`createby_id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of cms_article
-- ----------------------------
INSERT INTO `cms_article` VALUES ('116', '1', '<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"width: 16px;\"><tbody style=\"padding: 0px; margin: 0px; list-style: none; border: 0px; outline: 0px;\"><tr style=\"padding: 0px; margin: 0px; list-style: none; border: 0px; outline: 0px;\" class=\"firstRow\"><td style=\"padding: 0px; margin: 0px; list-style: none; border: 0px; outline: 0px;\">第一天长沙——望城——长沙&nbsp;&nbsp;</td></tr><tr style=\"padding: 0px; margin: 0px; list-style: none; border: 0px; outline: 0px;\"><td style=\"padding: 0px; margin: 0px; list-style: none; border: 0px; outline: 0px; line-height: 20px;\">8:00在长沙阿波罗广场门口集合，8:30出发前往光明松树谷（车程约1个小时）；到达后游览松鼠谷。在饲养员的指导下您可以在松鼠大观园与松鼠零距离的接触，可以抚摸它们，给它们喂食，还可以让可爱的松鼠停在您的手掌上 肩膀上来个自拍；您还可以自费参观松鼠游乐园-水上充气乐园、松林漂移车、亲子小火车、旋转木马、碰碰车、蹦床、水果旋风等游乐项目；您还可以和您的小伙伴在空中萌鼠城堡来一次丛林大冒险；竹林爬网可以让小朋友尽情攀爬释放天性；户外高空拓展非常适合企业和团体进驻体验。之后乘车返回长沙，结束愉快的旅途！！！</td></tr></tbody></table><p><br/></p>', '2016-11-23', '第一天长沙——望城——长沙  8:00在长沙阿波罗广场门口集合，8:30出发前往光明松树谷（车程约1个小时）；到达后游览松鼠谷。在饲养员的指导下您可以在松鼠大观园与松鼠零距离的接触，可以抚摸它们，给它们喂食，还可以让可爱的松鼠停在您的手掌上 肩膀上来个自拍；您还可以自费参观松鼠游乐园-水上充气乐园、松林漂移车', '0', null, 'bf46b5b7-f5a9-4b89-be50-4487cece6ae1.jpg', '0', null, '光明松鼠谷主题乐园一日游', '1', '72', '0', null, null, null, '', '岳麓区', '2');
INSERT INTO `cms_article` VALUES ('117', '1', '<h2 style=\"padding: 0px; margin: 0px; font-size: 18px; color: rgb(54, 61, 64);\">基本信息</h2><ul class=\"base-info-list\" style=\"padding: 0px; margin: 0px; list-style: none;\"><li><p>楼盘地址</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">门头沟新城核心区，长安街西延长线南侧</p></li><li><p>物业类型</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">商办</p></li><li><p>建筑类型</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">商办</p></li><li><p>环&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;线</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">6环外，距6环2.1km</p></li><li><p>开&nbsp;&nbsp;发&nbsp;&nbsp;商</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">华润置地</p></li></ul><h2 style=\"padding: 0px; margin: 0px; font-size: 18px; color: rgb(54, 61, 64);\">销售信息</h2><ul class=\"base-info-list\" style=\"padding: 0px; margin: 0px; list-style: none;\"><li><p>开发商报价</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">37,000元/平米</p><p><a href=\"http://www.comjia.com/project/129/price.html\" looyu_bound=\"1\" style=\"cursor: pointer; color: rgb(0, 116, 193); text-decoration: none;\"><span class=\"ico ico-trend\" style=\"display: inline-block; font-size: 0px; line-height: 0; width: 18px; height: 16px; background-image: url(http://www.comjia.com/images/bg-icon.png); overflow: hidden; float: left; margin: 8px 5px 0px 0px; background-position: 0px -640px; background-repeat: no-repeat no-repeat;\">趋势</span>价格走势</a></p></li><li><p>销售状态</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">在售</p></li><li><p>优惠信息</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\"><br/></p></li><li><p>最新开盘</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">2016-08-27</p></li><li><p>最早交房</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">2016-12</p></li><li><p>楼盘户型</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">一居(4)</p><p><a href=\"http://www.comjia.com/project/129/ht.html\" looyu_bound=\"1\" style=\"cursor: pointer; color: rgb(0, 116, 193); text-decoration: none;\"><span class=\"ico ico-htype\" style=\"display: inline-block; font-size: 0px; line-height: 0; width: 18px; height: 16px; background-image: url(http://www.comjia.com/images/bg-icon.png); overflow: hidden; float: left; margin: 7px 5px 0px 0px; background-position: 0px -680px; background-repeat: no-repeat no-repeat;\">趋势</span>户型分析</a></p></li><li><p>产权年限</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">50年</p></li><li><p>剩余产权年限</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">47年</p></li></ul><h2 style=\"padding: 0px; margin: 0px; font-size: 18px; color: rgb(54, 61, 64);\">小区详情</h2><ul class=\"base-info-list\" style=\"padding: 0px; margin: 0px; list-style: none;\"><li><p>物业公司</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">华润置地（北京）物业管理有限公司</p></li><li><p>物业费用</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">3.98元/m²/月</p><p><span class=\"ico ico-tips\" style=\"display: inline-block; font-size: 0px; line-height: 0; width: 16px; height: 16px; background-image: url(http://www.comjia.com/images/bg-icon.png); overflow: hidden; cursor: pointer; float: left; margin-top: 8px; background-position: 0px -600px; background-repeat: no-repeat no-repeat;\"></span></p></li><li><p>水电燃气</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">商水商电</p><p><span class=\"ico ico-tips\" style=\"display: inline-block; font-size: 0px; line-height: 0; width: 16px; height: 16px; background-image: url(http://www.comjia.com/images/bg-icon.png); overflow: hidden; cursor: pointer; float: left; margin-top: 8px; background-position: 0px -600px; background-repeat: no-repeat no-repeat;\"></span></p></li><li><p>供暖方式</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">集中供暖</p></li><li><p>绿化率</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">30%</p></li><li><p>车位情况</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">1:0.4</p></li><li><p>容积率</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">4</p><p><span class=\"ico ico-tips\" style=\"display: inline-block; font-size: 0px; line-height: 0; width: 16px; height: 16px; background-image: url(http://www.comjia.com/images/bg-icon.png); overflow: hidden; cursor: pointer; float: left; margin-top: 8px; background-position: 0px -600px; background-repeat: no-repeat no-repeat;\"></span></p></li><li><p>装修状况</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">毛坯</p></li><li><p>楼栋信息</p><p class=\"date\" style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">2016年11月23日：</p><p><br/></p><p class=\"date\" style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">1#：21层，4梯12户，238户</p><p><br/></p><p class=\"date\" style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">4#：20层，3梯14户，264户</p></li></ul><p><br/></p>', '2016-11-23', '基本信息楼盘地址门头沟新城核心区，长安街西延长线南侧物业类型商办建筑类型商办环       线6环外，距6环2.1km开  发  商华润置地销售信息开发商报价37,000元/平米趋势价格走势销售状态在售优惠信息最新开盘2016-08-27最早交房2016-12楼盘户型一居(4)趋势户型分析产权年限50年剩余产', '0', null, '2d4ad9af-7cac-4853-abf4-f3a546d47113.jpg', '0', null, '华润悦景湾', '1', '63', '0', null, null, null, '', '昌平区', '1');
INSERT INTO `cms_article` VALUES ('118', '1', '<h2 style=\"padding: 0px; margin: 0px; font-size: 18px; color: rgb(54, 61, 64);\">基本信息</h2><ul class=\"base-info-list\" style=\"padding: 0px; margin: 0px; list-style: none;\"><li><p>楼盘地址</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">房山良乡大学城北站</p></li><li><p>物业类型</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">商办</p></li><li><p>建筑类型</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">商办</p></li><li><p>环&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;线</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">5环至6环之间，距5环8km，距6环5.5km</p></li><li><p>开&nbsp;&nbsp;发&nbsp;&nbsp;商</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">绿地集团</p></li></ul><h2 style=\"padding: 0px; margin: 0px; font-size: 18px; color: rgb(54, 61, 64);\">销售信息</h2><ul class=\"base-info-list\" style=\"padding: 0px; margin: 0px; list-style: none;\"><li><p>开发商报价</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">25,000元/平米</p><p><a href=\"http://www.comjia.com/project/20000706/price.html\" looyu_bound=\"1\" style=\"cursor: pointer; color: rgb(0, 116, 193); text-decoration: none;\"><span class=\"ico ico-trend\" style=\"display: inline-block; font-size: 0px; line-height: 0; width: 18px; height: 16px; background-image: url(http://www.comjia.com/images/bg-icon.png); overflow: hidden; float: left; margin: 8px 5px 0px 0px; background-position: 0px -640px; background-repeat: no-repeat no-repeat;\">趋势</span>价格走势</a></p></li><li><p>销售状态</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">在售</p></li><li><p>优惠信息</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">3万抵6万，全款98折，贷款99折</p></li><li><p>最新开盘</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">2016-06-26</p></li><li><p>最早交房</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">2017-06</p></li><li><p>楼盘户型</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">不限(4)</p><p><a href=\"http://www.comjia.com/project/20000706/ht.html\" looyu_bound=\"1\" style=\"cursor: pointer; color: rgb(0, 116, 193); text-decoration: none;\"><span class=\"ico ico-htype\" style=\"display: inline-block; font-size: 0px; line-height: 0; width: 18px; height: 16px; background-image: url(http://www.comjia.com/images/bg-icon.png); overflow: hidden; float: left; margin: 7px 5px 0px 0px; background-position: 0px -680px; background-repeat: no-repeat no-repeat;\">趋势</span>户型分析</a></p></li><li><p>产权年限</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">50年</p></li><li><p>剩余产权年限</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">46年</p></li></ul><h2 style=\"padding: 0px; margin: 0px; font-size: 18px; color: rgb(54, 61, 64);\">小区详情</h2><ul class=\"base-info-list\" style=\"padding: 0px; margin: 0px; list-style: none;\"><li><p>物业公司</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">绿地物业</p></li><li><p>物业费用</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">6元/m²/月</p><p><span class=\"ico ico-tips\" style=\"display: inline-block; font-size: 0px; line-height: 0; width: 16px; height: 16px; background-image: url(http://www.comjia.com/images/bg-icon.png); overflow: hidden; cursor: pointer; float: left; margin-top: 8px; background-position: 0px -600px; background-repeat: no-repeat no-repeat;\"></span></p></li><li><p>水电燃气</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">商水商电</p><p><span class=\"ico ico-tips\" style=\"display: inline-block; font-size: 0px; line-height: 0; width: 16px; height: 16px; background-image: url(http://www.comjia.com/images/bg-icon.png); overflow: hidden; cursor: pointer; float: left; margin-top: 8px; background-position: 0px -600px; background-repeat: no-repeat no-repeat;\"></span></p></li><li><p>供暖方式</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\"><br/></p></li><li><p>绿化率</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">35%</p></li><li><p>车位情况</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">1：1.5</p></li><li><p>容积率</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">2.5</p><p><span class=\"ico ico-tips\" style=\"display: inline-block; font-size: 0px; line-height: 0; width: 16px; height: 16px; background-image: url(http://www.comjia.com/images/bg-icon.png); overflow: hidden; cursor: pointer; float: left; margin-top: 8px; background-position: 0px -600px; background-repeat: no-repeat no-repeat;\"></span></p></li><li><p>装修状况</p><p style=\"padding: 0px; margin-top: 0px; margin-right: 15px; margin-bottom: 0px; font-size: 14px; overflow: hidden; float: left; color: rgb(102, 102, 102);\">毛坯</p></li></ul><p><br/></p>', '2016-11-23', '基本信息楼盘地址房山良乡大学城北站物业类型商办建筑类型商办环       线5环至6环之间，距5环8km，距6环5.5km开  发  商绿地集团销售信息开发商报价25,000元/平米趋势价格走势销售状态在售优惠信息3万抵6万，全款98折，贷款99折最新开盘2016-06-26最早交房2017-06楼盘户型不限', '0', null, '474ab9a6-0b37-407c-af2f-a84e7c582607.jpg', '0', null, '绿地悦公馆', '1', '62', '0', null, null, null, '', '海淀区', '1');
INSERT INTO `cms_article` VALUES ('119', '1', '<p>楼盘信息</p><ul style=\"margin: 0px; padding: 0px; list-style: none;\"><li><span class=\"field fl\" style=\"float: left; color: rgb(120, 120, 120); display: block; padding: 0px 10px 0px 0px;\">楼盘名称：</span><p class=\"text fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 233.296875px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;\">家和苑</p></li><li><span class=\"field fl\" style=\"float: left; color: rgb(120, 120, 120); display: block; padding: 0px 10px 0px 0px;\">占地面积：</span><p class=\"text fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 233.296875px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;\">33302平米</p></li><li><span class=\"field fl\" style=\"float: left; color: rgb(120, 120, 120); display: block; padding: 0px 10px 0px 0px;\">所属区域：</span><p class=\"text fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 233.296875px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;\">岳麓 枫林一路</p></li><li><span class=\"field fl\" style=\"float: left; color: rgb(120, 120, 120); display: block; padding: 0px 10px 0px 0px;\">车位数量：</span><p class=\"text fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 233.296875px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;\">454</p></li><li><span class=\"field fl\" style=\"float: left; color: rgb(120, 120, 120); display: block; padding: 0px 10px 0px 0px;\">物业类型：</span><p class=\"text fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 233.296875px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;\">住宅</p></li><li><span class=\"field fl\" style=\"float: left; color: rgb(120, 120, 120); display: block; padding: 0px 10px 0px 0px;\">容<span class=\"gap-hfword\" style=\"display: inline-block; overflow: hidden; width: 0.5em;\"></span>积<span class=\"gap-hfword\" style=\"display: inline-block; overflow: hidden; width: 0.5em;\"></span>率：</span><p class=\"text fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 233.296875px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;\">3.29</p></li><li><span class=\"field fl\" style=\"float: left; color: rgb(120, 120, 120); display: block; padding: 0px 10px 0px 0px;\">装修情况：</span><p class=\"text fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 233.296875px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;\">暂无数据</p></li><li><span class=\"field fl\" style=\"float: left; color: rgb(120, 120, 120); display: block; padding: 0px 10px 0px 0px;\">绿<span class=\"gap-hfword\" style=\"display: inline-block; overflow: hidden; width: 0.5em;\"></span>化<span class=\"gap-hfword\" style=\"display: inline-block; overflow: hidden; width: 0.5em;\"></span>率：</span><p class=\"text fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 233.296875px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;\">40%</p></li><li><span class=\"field fl\" style=\"float: left; color: rgb(120, 120, 120); display: block; padding: 0px 10px 0px 0px;\">平均房价：</span><p class=\"text fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 233.296875px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;\">5800 元/m²</p></li><li><span class=\"field fl\" style=\"float: left; color: rgb(120, 120, 120); display: block; padding: 0px 10px 0px 0px;\">物业公司：</span><p class=\"text fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 233.296875px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;\">长沙市美景物业管理有限公司</p></li><li><span class=\"field fl\" style=\"float: left; color: rgb(120, 120, 120); display: block; padding: 0px 10px 0px 0px;\">开<span class=\"gap-hfword\" style=\"display: inline-block; overflow: hidden; width: 0.5em;\"></span>发<span class=\"gap-hfword\" style=\"display: inline-block; overflow: hidden; width: 0.5em;\"></span>商：</span><p class=\"text fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 233.296875px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;\">湖南世锦置业有限公司</p></li><li><span class=\"field fl\" style=\"float: left; color: rgb(120, 120, 120); display: block; padding: 0px 10px 0px 0px;\">停<span class=\"gap-hfword\" style=\"display: inline-block; overflow: hidden; width: 0.5em;\"></span>车<span class=\"gap-hfword\" style=\"display: inline-block; overflow: hidden; width: 0.5em;\"></span>费：</span><p class=\"text fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 233.296875px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;\">130.0</p></li></ul><p><br/></p><p><br/></p><p><br/></p><p><br/></p><p><img src=\"http://image.zscat.com/upload1/20161123/1479902752678.jpg\" style=\"float:none;\" title=\"CvtcKVgpfxOAY2z6AAFXwez171U255.jpg\"/></p><p><img src=\"http://image.zscat.com/upload1/20161123/1479902755869.jpg\" style=\"float:none;\" title=\"i (4).jpg\"/></p><p><img src=\"http://image.zscat.com/upload1/20161123/1479902759842.jpg\" style=\"float:none;\" title=\"i (5).jpg\"/></p><p><br/></p>', '2016-11-23', '楼盘信息楼盘名称：家和苑占地面积：33302平米所属区域：岳麓 枫林一路车位数量：454物业类型：住宅容积率：3.29装修情况：暂无数据绿化率：40%平均房价：5800 元/m²物业公司：长沙市美景物业管理有限公司开发商：湖南世锦置业有限公司停车费：130.0', '0', null, '43a76d69-f474-4449-a81e-f5a5484626dc.jpg', '0', null, '家和苑', '1', '66', '0', null, null, null, 'http://image.zscat.com/upload1/20161123/1479902752678.jpg,http://image.zscat.com/upload1/20161123/1479902755869.jpg,http://image.zscat.com/upload1/20161123/1479902759842.jpg,', '岳麓区', '1');
INSERT INTO `cms_article` VALUES ('120', '1', '<ul style=\"margin: 0px; padding: 0px; list-style: none; color: rgb(46, 46, 46); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, Tahoma, &#39;Microsoft YaHei&#39;, 微软雅黑, sans-serif; font-size: 12px; line-height: 18px; white-space: normal; background-color: rgb(255, 255, 255);\"><li><p class=\"date fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 72px; text-align: right;\"><span style=\"font-size: 16px;\">11月16日</span><span style=\"color: rgb(204, 204, 204);\">2016年</span></p><p><span class=\"icons_saledetails node-circle\" style=\"width: 18px; height: 18px; display: block; font-size: 0px; line-height: 0; overflow: hidden; background-image: url(http://changsha.qfang.com/themes/default/images/frontend/details/icons_saledetails.gif); position: absolute; left: -10px; top: 0px; background-position: -256px -305px; background-repeat: no-repeat no-repeat;\"></span></p><p>保利西海岸业态涵盖超高层住宅、写字楼、soho公寓、大型集中商业及滨江商业街。楼盘目前在售93-1785平临街商铺，均价2.5-5万/平。</p></li><li><p class=\"date fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 72px; text-align: right;\"><span style=\"font-size: 16px;\">10月13日</span><span style=\"color: rgb(204, 204, 204);\">2016年</span></p><p><span class=\"icons_saledetails node-circle\" style=\"width: 18px; height: 18px; display: block; font-size: 0px; line-height: 0; overflow: hidden; background-image: url(http://changsha.qfang.com/themes/default/images/frontend/details/icons_saledetails.gif); position: absolute; left: -10px; top: 0px; background-position: -256px -305px; background-repeat: no-repeat no-repeat;\"></span></p><p class=\"fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left;\"><a href=\"http://changsha.qfang.com/newhouse/info/detail/56457\" target=\"_blank\" style=\"color: rgb(51, 51, 51); text-decoration: none; outline: none; transition: color 0.3s, background-color 0.3s, border-color 0.3s; -webkit-transition: color 0.3s, background-color 0.3s, border-color 0.3s;\"><img width=\"150\" height=\"112\" src=\"http://yun200.qfangimg.com/group1/150x112/M00/20/FC/CvtcKVf_Jk2AZ29VAAEPCcf6aOA689.jpg\" style=\"border: none; display: inline-block; vertical-align: top;\"/></a></p><p class=\"title\" style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 16px; font-weight: bold; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;\"><a href=\"http://changsha.qfang.com/newhouse/info/detail/56457\" target=\"_blank\" style=\"color: rgb(51, 51, 51); text-decoration: none; outline: none; transition: color 0.3s, background-color 0.3s, border-color 0.3s; -webkit-transition: color 0.3s, background-color 0.3s, border-color 0.3s;\">保利西海岸 滨江新城城市综合体</a></p><p class=\"text\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px;\">保利西海岸，位于潇湘大道与岳麓大道交汇处西南角，地处大河西先导区门户、滨江新城桥头堡，占据长沙滨江中心地段，具备地理位置优势。 保利西海岸，临近三座跨江大桥、一条过江隧道、长株潭城际轻轨、以及规划中的桐梓坡路过江隧道与地铁4、6号线，城市交通便利，可快速通达星城各个角落。</p></li><li><p class=\"date fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 72px; text-align: right;\"><span style=\"font-size: 16px;\">08月15日</span><span style=\"color: rgb(204, 204, 204);\">2016年</span></p><p><span class=\"icons_saledetails node-circle\" style=\"width: 18px; height: 18px; display: block; font-size: 0px; line-height: 0; overflow: hidden; background-image: url(http://changsha.qfang.com/themes/default/images/frontend/details/icons_saledetails.gif); position: absolute; left: -10px; top: 0px; background-position: -256px -305px; background-repeat: no-repeat no-repeat;\"></span></p><p>保利西海岸A区、B区均在售，B区可售房源已不多，目前主推A区房源。A区在售A1栋、A2栋，户型有平层和复式楼两种。平层房源：130平3+1房，160平5房，折后均价11000-12000元/平。复式房源：131平、137平，均为3+1房设计，折后均价12000-14000元/平。B区在售B3栋、B5栋、B6栋，户型有平层和复式楼两种。房源面积约140平，折后均价8000元/平。购房可享：交1万抵3 &nbsp;</p></li></ul><p><br/></p>', '2016-11-23', '11月16日2016年保利西海岸业态涵盖超高层住宅、写字楼、soho公寓、大型集中商业及滨江商业街。楼盘目前在售93-1785平临街商铺，均价2.5-5万/平。10月13日2016年保利西海岸 滨江新城城市综合体保利西海岸，位于潇湘大道与岳麓大道交汇处西南角，地处大河西先导区门户、滨江新城桥头堡，占据长沙滨江', '0', null, 'a6c2b855-b791-4f3e-9f6d-c3f6a9d11ffd.jpg', '0', null, '保利西海岸', '1', '66', '0', null, null, null, 'http://yun200.qfangimg.com/group1/150x112/M00/20/FC/CvtcKVf_Jk2AZ29VAAEPCcf6aOA689.jpg,', '岳麓区', '1');
INSERT INTO `cms_article` VALUES ('121', '2', '<p>楼盘动态</p><ul style=\"margin: 0px; padding: 0px; list-style: none;\"><li><p class=\"date fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 72px; text-align: right;\"><span style=\"font-size: 16px;\">08月02日</span><span style=\"color: rgb(204, 204, 204);\">2016年</span></p><p><span class=\"icons_saledetails node-circle\" style=\"width: 18px; height: 18px; display: block; font-size: 0px; line-height: 0; overflow: hidden; background-image: url(http://changsha.qfang.com/themes/default/images/frontend/details/icons_saledetails.gif); position: absolute; left: -10px; top: 0px; background-position: -256px -305px; background-repeat: no-repeat no-repeat;\"></span></p><p>五矿麓谷科技产业园在售B4、B5栋1210-1280平厂房，均价3800元/平。另在售C5、C6、C9栋1980、2460平企业独栋，均价5800元/平。全款享98折，按揭享99折。</p></li><li><p class=\"date fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 72px; text-align: right;\"><span style=\"font-size: 16px;\">04月18日</span><span style=\"color: rgb(204, 204, 204);\">2016年</span></p><p><span class=\"icons_saledetails node-circle\" style=\"width: 18px; height: 18px; display: block; font-size: 0px; line-height: 0; overflow: hidden; background-image: url(http://changsha.qfang.com/themes/default/images/frontend/details/icons_saledetails.gif); position: absolute; left: -10px; top: 0px; background-position: -256px -305px; background-repeat: no-repeat no-repeat;\"></span></p><p>&nbsp; 五矿麓谷科技产业园在售B4、B5栋1210-1280平厂房，均价3800元/平。另在售C5、C6、C9栋1980、2460平企业独栋，均价5800元/平。全款享98折，按揭享99折。</p></li><li><p class=\"date fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 72px; text-align: right;\"><span style=\"font-size: 16px;\">03月25日</span><span style=\"color: rgb(204, 204, 204);\">2016年</span></p><p><span class=\"icons_saledetails node-circle\" style=\"width: 18px; height: 18px; display: block; font-size: 0px; line-height: 0; overflow: hidden; background-image: url(http://changsha.qfang.com/themes/default/images/frontend/details/icons_saledetails.gif); position: absolute; left: -10px; top: 0px; background-position: -256px -305px; background-repeat: no-repeat no-repeat;\"></span></p><p>&nbsp; 五矿麓谷科技产业园在售B4、B5栋1210-1280平厂房，均价3800元/平。另在售C5、C6、C9栋1980、2460平企业独栋，均价5800元/平。全款享98折，按揭享99折。</p></li></ul><p><br/></p>', '2016-11-23', '楼盘动态08月02日2016年五矿麓谷科技产业园在售B4、B5栋1210-1280平厂房，均价3800元/平。另在售C5、C6、C9栋1980、2460平企业独栋，均价5800元/平。全款享98折，按揭享99折。04月18日2016年  五矿麓谷科技产业园在售B4、B5栋1210-1280平厂房，均价3800', '0', null, 'a1f86560-8c38-471a-90b6-290c28f5ec70.jpg', '0', null, '五矿麓谷科技产业园', '1', '66', '0', null, null, null, '', '岳麓区', '1');
INSERT INTO `cms_article` VALUES ('122', '3', '<ul style=\"margin: 0px; padding: 0px; list-style: none; color: rgb(46, 46, 46); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, Tahoma, &#39;Microsoft YaHei&#39;, 微软雅黑, sans-serif; font-size: 12px; line-height: 18px; white-space: normal; background-color: rgb(255, 255, 255);\"><li><p class=\"date fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 72px; text-align: right;\"><span style=\"font-size: 16px;\">11月07日</span><span style=\"color: rgb(204, 204, 204);\">2016年</span></p><p><span class=\"icons_saledetails node-circle\" style=\"width: 18px; height: 18px; display: block; font-size: 0px; line-height: 0; overflow: hidden; background-image: url(http://changsha.qfang.com/themes/default/images/frontend/details/icons_saledetails.gif); position: absolute; left: -10px; top: 0px; background-position: -256px -305px; background-repeat: no-repeat no-repeat;\"></span></p><p>洋湖时代广场现主要在售17#LOFT公寓，户型面积为35、39、62平，均价6600元/平。另在售少量住宅，均价8500元/平。</p></li><li><p class=\"date fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 72px; text-align: right;\"><span style=\"font-size: 16px;\">10月25日</span><span style=\"color: rgb(204, 204, 204);\">2016年</span></p><p><span class=\"icons_saledetails node-circle\" style=\"width: 18px; height: 18px; display: block; font-size: 0px; line-height: 0; overflow: hidden; background-image: url(http://changsha.qfang.com/themes/default/images/frontend/details/icons_saledetails.gif); position: absolute; left: -10px; top: 0px; background-position: -256px -305px; background-repeat: no-repeat no-repeat;\"></span></p><p class=\"fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left;\"><a href=\"http://changsha.qfang.com/newhouse/info/detail/57061\" target=\"_blank\" style=\"color: rgb(51, 51, 51); text-decoration: none; outline: none; transition: color 0.3s, background-color 0.3s, border-color 0.3s; -webkit-transition: color 0.3s, background-color 0.3s, border-color 0.3s;\"><img width=\"150\" height=\"112\" src=\"http://yun200.qfangimg.com/group1/150x112/M00/22/DD/CvtcKlgOyPGAK1csAAFGcNYHDlQ713.jpg\" style=\"border: none; display: inline-block; vertical-align: top;\"/></a></p><p class=\"title\" style=\"margin-top: 0px; margin-bottom: 10px; padding: 0px; font-size: 16px; font-weight: bold; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;\"><a href=\"http://changsha.qfang.com/newhouse/info/detail/57061\" target=\"_blank\" style=\"color: rgb(51, 51, 51); text-decoration: none; outline: none; transition: color 0.3s, background-color 0.3s, border-color 0.3s; -webkit-transition: color 0.3s, background-color 0.3s, border-color 0.3s;\">洋湖时代广场 洋湖板块杰出之作</a></p><p class=\"text\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px;\">洋湖时代广场（曾用名：洋湖style）位于洋湖板块，学士路与象咀路交汇处的东南角，项目东临洋湖湿地公园， 洋湖时代广场周边呈“三纵三横三桥”格局，其中由西往东的纵向道路有象咀路、车塘河路、含浦大道，三横由北往南分别为云栖路、学士路、洋湖大道，三桥由北往南分别是猴子石大桥、湘府路大桥、黑石铺大桥，项目本身是一个集住宅、写字楼、酒店于一体的区域及商业综合体。</p></li><li><p class=\"date fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 72px; text-align: right;\"><span style=\"font-size: 16px;\">10月24日</span><span style=\"color: rgb(204, 204, 204);\">2016年</span></p><p><span class=\"icons_saledetails node-circle\" style=\"width: 18px; height: 18px; display: block; font-size: 0px; line-height: 0; overflow: hidden; background-image: url(http://changsha.qfang.com/themes/default/images/frontend/details/icons_saledetails.gif); position: absolute; left: -10px; top: 0px; background-position: -256px -305px; background-repeat: no-repeat no-repeat;\"></span></p><p>洋湖时代广场目前在售17号栋公寓，面积为33-62平，均价为6200元/平。另在售11、12号栋住宅，93平户型剩余5户，价格为8200元/平，96平户型剩余2户，价格为8500元/平，132平户型剩余1户，价格为8500元/平。</p></li></ul><p><br/></p>', '2016-11-23', '11月07日2016年洋湖时代广场现主要在售17#LOFT公寓，户型面积为35、39、62平，均价6600元/平。另在售少量住宅，均价8500元/平。10月25日2016年洋湖时代广场 洋湖板块杰出之作洋湖时代广场（曾用名：洋湖style）位于洋湖板块，学士路与象咀路交汇处的东南角，项目东临洋湖湿地公园， 洋', '0', null, '1a445a0f-06f9-4fbd-a46f-abab9ba4b5c6.jpg', '0', null, '洋湖时代广场', '1', '66', '0', null, null, null, 'http://yun200.qfangimg.com/group1/150x112/M00/22/DD/CvtcKlgOyPGAK1csAAFGcNYHDlQ713.jpg,', '岳麓区', '1');
INSERT INTO `cms_article` VALUES ('123', '1', '<ul style=\"margin: 0px; padding: 0px; list-style: none; color: rgb(46, 46, 46); font-family: &#39;Helvetica Neue&#39;, Helvetica, Arial, Tahoma, &#39;Microsoft YaHei&#39;, 微软雅黑, sans-serif; font-size: 12px; line-height: 18px; white-space: normal; background-color: rgb(255, 255, 255);\"><li><p class=\"date fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 72px; text-align: right;\"><span style=\"font-size: 16px;\">11月17日</span><span style=\"color: rgb(204, 204, 204);\">2016年</span></p><p><span class=\"icons_saledetails node-circle\" style=\"width: 18px; height: 18px; display: block; font-size: 0px; line-height: 0; overflow: hidden; background-image: url(http://changsha.qfang.com/themes/default/images/frontend/details/icons_saledetails.gif); position: absolute; left: -10px; top: 0px; background-position: -256px -305px; background-repeat: no-repeat no-repeat;\"></span></p><p>才子嘉都现主推公寓和住宅。其中14#户型面积为公寓50-70平，在售公寓位于4楼、5楼和6楼。23#住宅户型面积为75平、109平，在售房源位于3楼、4楼、18楼和顶楼，折后均价7800-7900元/平。</p></li><li><p class=\"date fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 72px; text-align: right;\"><span style=\"font-size: 16px;\">10月20日</span><span style=\"color: rgb(204, 204, 204);\">2016年</span></p><p><span class=\"icons_saledetails node-circle\" style=\"width: 18px; height: 18px; display: block; font-size: 0px; line-height: 0; overflow: hidden; background-image: url(http://changsha.qfang.com/themes/default/images/frontend/details/icons_saledetails.gif); position: absolute; left: -10px; top: 0px; background-position: -256px -305px; background-repeat: no-repeat no-repeat;\"></span></p><p>才子嘉都现主推14#公寓，在售公寓位于4楼、5楼和6楼50-60平，均价7600-7700元/平。另23#住宅在售，户型面积为75平、109平，在售房源位于3楼和顶楼，均价8300元/平。</p></li><li><p class=\"date fl\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; float: left; width: 72px; text-align: right;\"><span style=\"font-size: 16px;\">09月23日</span><span style=\"color: rgb(204, 204, 204);\">2016年</span></p><p><span class=\"icons_saledetails node-circle\" style=\"width: 18px; height: 18px; display: block; font-size: 0px; line-height: 0; overflow: hidden; background-image: url(http://changsha.qfang.com/themes/default/images/frontend/details/icons_saledetails.gif); position: absolute; left: -10px; top: 0px; background-position: -256px -305px; background-repeat: no-repeat no-repeat;\"></span></p><p>才子嘉都现均价6000-7000元/平。现仅顶楼有售，10#有66平公寓，15、16、22#有96、126平在售。</p></li></ul><p><br/></p>', '2016-11-23', '11月17日2016年才子嘉都现主推公寓和住宅。其中14#户型面积为公寓50-70平，在售公寓位于4楼、5楼和6楼。23#住宅户型面积为75平、109平，在售房源位于3楼、4楼、18楼和顶楼，折后均价7800-7900元/平。10月20日2016年才子嘉都现主推14#公寓，在售公寓位于4楼、5楼和6楼50-6', '0', null, 'd6b34ce2-5db6-4831-ac54-3323e8b03090.jpg', '0', null, '才子嘉都', '1', '65', '0', null, null, null, '', '天心区', '1');
INSERT INTO `cms_article` VALUES ('124', '1', '<p class=\"main-desc-p\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; line-height: 20px; color: rgb(102, 102, 102);\"><span class=\"main-desc-tip\" style=\"margin: 0px; padding: 0px; list-style: none; font-weight: 700;\">大家印象：</span>&nbsp;毗临湘江、文物古迹众多的岳麓山，是南岳衡山七十二峰之一，有一份古老的墨香，是一个寻幽访古的好去处。&nbsp;<a class=\"click-more-info\" style=\"margin: 0px; padding: 0px; list-style: none; background-color: transparent; color: rgb(0, 145, 217); cursor: pointer;\">[详细简介]</a></p><p><span class=\"main-besttime\" style=\"margin: 0px; padding: 0px; list-style: none; width: 270px; display: block; color: rgb(102, 102, 102);\"><span style=\"margin: 0px; padding: 0px; list-style: none; width: 270px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 18px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); font-family: globel-iconfont !important;\"></span>到达与离开：岳麓山景区位于长沙市...</span></span><span class=\"main-besttime\" style=\"margin: 0px; padding: 0px; list-style: none; width: 270px; display: block; color: rgb(102, 102, 102);\"><span style=\"margin: 0px; padding: 0px; list-style: none; width: 270px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 18px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); font-family: globel-iconfont !important;\"></span>景点类型：山峰</span></span><span class=\"main-besttime\" style=\"margin: 0px; padding: 0px; list-style: none; width: 270px; display: block; color: rgb(102, 102, 102);\"><span style=\"margin: 0px; padding: 0px; list-style: none; width: 270px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 18px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); font-family: globel-iconfont !important;\"></span>最佳季节：四季皆宜。 春天的岳...</span></span><span class=\"main-dcnt\" style=\"margin: 0px; padding: 0px; list-style: none; width: 270px; display: block; color: rgb(102, 102, 102);\"><span style=\"margin: 0px; padding: 0px; list-style: none; width: 270px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 18px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); font-family: globel-iconfont !important;\"></span>建议游玩：4-6小时</span></span></p><p><br/></p><p><span class=\"title\" style=\"margin: 0px 10px 0px 0px; padding: 0px; list-style: none; font-size: 30px; font-family: &#39;microsoft yahei&#39;; color: rgb(17, 17, 17); display: inline-block; vertical-align: top;\">特价门票</span></p><ul style=\"margin: 0px; padding: 0px; list-style: none;\"><li><span class=\"ticket-td ticket-index\" style=\"margin: 23px 13px 0px 11px; padding: 0px; list-style: none; vertical-align: top; float: left; position: relative; display: inline-block; width: 15px; height: 15px; border-top-left-radius: 50%; border-top-right-radius: 50%; border-bottom-right-radius: 50%; border-bottom-left-radius: 50%; border: 1px solid rgb(255, 142, 12); color: rgb(255, 142, 12); line-height: 1;\"><span style=\"margin: 0px; padding: 0px; list-style: none; display: inline-block; width: 15px; height: 15px; font-size: 10px; line-height: 16px; text-align: center; font-family: Arial; -webkit-transform: scale(0.8333);\">01</span></span><p><span class=\"name\" style=\"margin: 0px 0px 7px; padding: 0px; list-style: none; display: inline-block;\"><span class=\"clicklog\" clicklog=\"{&quot;pos&quot;:&quot;ticketBookTitle&quot;,&quot;locate&quot;:&quot;area-main&quot;}\" target=\"_blank\" pb-id=\"ticketMainBookTitle_ctripticket2\" pb-index=\"0\" pb-attr-tickettype=\"ctripticket2\" style=\"margin: 0px; padding: 0px; list-style: none; font-size: 14px; line-height: 24px; font-family: &#39;microsoft yahei&#39;; cursor: pointer; display: inline; overflow: hidden;\">长沙岳麓山索道成人票（下行）</span><span class=\"globel-iconfont\" style=\"margin: 0px 0px 0px -1em; padding: 0px; list-style: none; font-size: 20px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; display: inline-block; vertical-align: top; line-height: 1.25; cursor: pointer; position: relative; left: 1em; font-family: globel-iconfont !important;\"></span></span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; cursor: pointer;\"><span class=\"bao-text\" style=\"margin: 0px; padding: 0px; list-style: none; display: inline-block; float: left; width: 170px;\"><span class=\"globel-iconfont\" style=\"margin: 0px; padding: 0px; list-style: none; font-size: 24px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; display: inline-block; width: 24px; height: 24px; vertical-align: top; cursor: pointer; color: rgb(90, 206, 178); font-family: globel-iconfont !important;\"></span>&nbsp;<span style=\"margin: 0px; padding: 0px; list-style: none; width: 140px; display: inline-block;\">当天15:00前可预订</span></span></p><p><span class=\"price-container\" style=\"margin: 6px 0px 0px; padding: 0px; list-style: none; display: inline-block;\"><span class=\"price-now\" style=\"margin: 0px; padding: 0px; list-style: none; color: rgb(255, 139, 6); font-size: 18px; line-height: 1; font-family: Arial;\"><span class=\"icon-rmb\" style=\"margin: 0px; padding: 0px; list-style: none; font-weight: 700; letter-spacing: -2px;\">￥</span>25</span></span><a href=\"https://lvyou.baidu.com/plan/ajax/ur?qt=sceneticket&src=ctripticket2&url=http%3A%2F%2Flvyou.baidu.com%2Fbusiness%2Fticket%2Forderfill%2Fqt%3Dorder_input%26partner_id%3Dctripticket2%26scope_id%3D9011%26ticket_id%3D2461666%26scope_name%3D%26order_from%3Dlvyou%26is_adult_ticket%3D0%26is_into_scope%3D0%26from%3Dpc%26uid%3D\" clicklog=\"{&quot;pos&quot;:&quot;ticketBookBtn&quot;,&quot;locate&quot;:&quot;area-main&quot;,&quot;refer&quot;:&quot;jingdian&quot;}\" target=\"_blank\" class=\"btn-booking clicklog\" pb-id=\"ticketMainBookBtnctripticket2\" pb-index=\"0\" pb-attr-tickettype=\"ctripticket2\" data-iscloseloop=\"1\" style=\"margin: 0px 22px 0px 0px; padding: 0px 20px; list-style: none; text-decoration: none; background-color: rgb(255, 139, 6); color: rgb(255, 255, 255); display: inline-block; border-top-left-radius: 3px; border-top-right-radius: 3px; border-bottom-right-radius: 3px; border-bottom-left-radius: 3px; font-size: 12px; line-height: 30px; font-family: &#39;microsoft yahei&#39;; text-align: center; float: right; cursor: pointer;\">预订</a></p></li><li><span class=\"ticket-td ticket-index\" style=\"margin: 23px 13px 0px 11px; padding: 0px; list-style: none; vertical-align: top; float: left; position: relative; display: inline-block; width: 15px; height: 15px; border-top-left-radius: 50%; border-top-right-radius: 50%; border-bottom-right-radius: 50%; border-bottom-left-radius: 50%; border: 1px solid rgb(255, 142, 12); color: rgb(255, 142, 12); line-height: 1;\"><span style=\"margin: 0px; padding: 0px; list-style: none; display: inline-block; width: 15px; height: 15px; font-size: 10px; line-height: 16px; text-align: center; font-family: Arial; -webkit-transform: scale(0.8333);\">02</span></span><p><span class=\"name\" style=\"margin: 0px 0px 7px; padding: 0px; list-style: none; display: inline-block;\"><span class=\"clicklog\" clicklog=\"{&quot;pos&quot;:&quot;ticketBookTitle&quot;,&quot;locate&quot;:&quot;area-main&quot;}\" target=\"_blank\" pb-id=\"ticketMainBookTitle_ctripticket2\" pb-index=\"1\" pb-attr-tickettype=\"ctripticket2\" style=\"margin: 0px; padding: 0px; list-style: none; font-size: 14px; line-height: 24px; font-family: &#39;microsoft yahei&#39;; cursor: pointer; display: inline; overflow: hidden;\">长沙岳麓山索道成人票（上行）</span><span class=\"globel-iconfont\" style=\"margin: 0px 0px 0px -1em; padding: 0px; list-style: none; font-size: 20px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; display: inline-block; vertical-align: top; line-height: 1.25; cursor: pointer; position: relative; left: 1em; font-family: globel-iconfont !important;\"></span></span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; cursor: pointer;\"><span class=\"bao-text\" style=\"margin: 0px; padding: 0px; list-style: none; display: inline-block; float: left; width: 170px;\"><span class=\"globel-iconfont\" style=\"margin: 0px; padding: 0px; list-style: none; font-size: 24px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; display: inline-block; width: 24px; height: 24px; vertical-align: top; cursor: pointer; color: rgb(90, 206, 178); font-family: globel-iconfont !important;\"></span>&nbsp;<span style=\"margin: 0px; padding: 0px; list-style: none; width: 140px; display: inline-block;\">当天15:00前可预订</span></span></p><p><span class=\"price-container\" style=\"margin: 6px 0px 0px; padding: 0px; list-style: none; display: inline-block;\"><span class=\"price-now\" style=\"margin: 0px; padding: 0px; list-style: none; color: rgb(255, 139, 6); font-size: 18px; line-height: 1; font-family: Arial;\"><span class=\"icon-rmb\" style=\"margin: 0px; padding: 0px; list-style: none; font-weight: 700; letter-spacing: -2px;\">￥</span>30</span></span><a href=\"https://lvyou.baidu.com/plan/ajax/ur?qt=sceneticket&src=ctripticket2&url=http%3A%2F%2Flvyou.baidu.com%2Fbusiness%2Fticket%2Forderfill%2Fqt%3Dorder_input%26partner_id%3Dctripticket2%26scope_id%3D9011%26ticket_id%3D1665055%26scope_name%3D%26order_from%3Dlvyou%26is_adult_ticket%3D0%26is_into_scope%3D0%26from%3Dpc%26uid%3D\" clicklog=\"{&quot;pos&quot;:&quot;ticketBookBtn&quot;,&quot;locate&quot;:&quot;area-main&quot;,&quot;refer&quot;:&quot;jingdian&quot;}\" target=\"_blank\" class=\"btn-booking clicklog\" pb-id=\"ticketMainBookBtnctripticket2\" pb-index=\"1\" pb-attr-tickettype=\"ctripticket2\" data-iscloseloop=\"1\" style=\"margin: 0px 22px 0px 0px; padding: 0px 20px; list-style: none; text-decoration: none; background-color: rgb(255, 139, 6); color: rgb(255, 255, 255); display: inline-block; border-top-left-radius: 3px; border-top-right-radius: 3px; border-bottom-right-radius: 3px; border-bottom-left-radius: 3px; font-size: 12px; line-height: 30px; font-family: &#39;microsoft yahei&#39;; text-align: center; float: right; cursor: pointer;\">预订</a></p></li><li><span class=\"ticket-td ticket-index\" style=\"margin: 23px 13px 0px 11px; padding: 0px; list-style: none; vertical-align: top; float: left; position: relative; display: inline-block; width: 15px; height: 15px; border-top-left-radius: 50%; border-top-right-radius: 50%; border-bottom-right-radius: 50%; border-bottom-left-radius: 50%; border: 1px solid rgb(255, 142, 12); color: rgb(255, 142, 12); line-height: 1;\"><span style=\"margin: 0px; padding: 0px; list-style: none; display: inline-block; width: 15px; height: 15px; font-size: 10px; line-height: 16px; text-align: center; font-family: Arial; -webkit-transform: scale(0.8333);\">03</span></span><p><span class=\"name\" style=\"margin: 0px 0px 7px; padding: 0px; list-style: none; display: inline-block;\"><span class=\"clicklog\" clicklog=\"{&quot;pos&quot;:&quot;ticketBookTitle&quot;,&quot;locate&quot;:&quot;area-main&quot;}\" target=\"_blank\" pb-id=\"ticketMainBookTitle_ctripticket2\" pb-index=\"2\" pb-attr-tickettype=\"ctripticket2\" style=\"margin: 0px; padding: 0px; list-style: none; font-size: 14px; line-height: 24px; font-family: &#39;microsoft yahei&#39;; cursor: pointer; display: inline; overflow: hidden;\">长沙岳麓山索道成人票（往返）</span><span class=\"globel-iconfont\" style=\"margin: 0px 0px 0px -1em; padding: 0px; list-style: none; font-size: 20px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; display: inline-block; vertical-align: top; line-height: 1.25; cursor: pointer; position: relative; left: 1em; font-family: globel-iconfont !important;\"></span></span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; cursor: pointer;\"><span class=\"bao-text\" style=\"margin: 0px; padding: 0px; list-style: none; display: inline-block; float: left; width: 170px;\"><span class=\"globel-iconfont\" style=\"margin: 0px; padding: 0px; list-style: none; font-size: 24px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; display: inline-block; width: 24px; height: 24px; vertical-align: top; cursor: pointer; color: rgb(90, 206, 178); font-family: globel-iconfont !important;\"></span>&nbsp;<span style=\"margin: 0px; padding: 0px; list-style: none; width: 140px; display: inline-block;\">当天14:00前可预订</span></span></p><p><span class=\"price-container\" style=\"margin: 6px 0px 0px; padding: 0px; list-style: none; display: inline-block;\"><span class=\"price-now\" style=\"margin: 0px; padding: 0px; list-style: none; color: rgb(255, 139, 6); font-size: 18px; line-height: 1; font-family: Arial;\"><span class=\"icon-rmb\" style=\"margin: 0px; padding: 0px; list-style: none; font-weight: 700; letter-spacing: -2px;\">￥</span>50</span></span><a href=\"https://lvyou.baidu.com/plan/ajax/ur?qt=sceneticket&src=ctripticket2&url=http%3A%2F%2Flvyou.baidu.com%2Fbusiness%2Fticket%2Forderfill%2Fqt%3Dorder_input%26partner_id%3Dctripticket2%26scope_id%3D9011%26ticket_id%3D2461694%26scope_name%3D%26order_from%3Dlvyou%26is_adult_ticket%3D0%26is_into_scope%3D0%26from%3Dpc%26uid%3D\" clicklog=\"{&quot;pos&quot;:&quot;ticketBookBtn&quot;,&quot;locate&quot;:&quot;area-main&quot;,&quot;refer&quot;:&quot;jingdian&quot;}\" target=\"_blank\" class=\"btn-booking clicklog\" pb-id=\"ticketMainBookBtnctripticket2\" pb-index=\"2\" pb-attr-tickettype=\"ctripticket2\" data-iscloseloop=\"1\" style=\"margin: 0px 22px 0px 0px; padding: 0px 20px; list-style: none; text-decoration: none; background-color: rgb(255, 139, 6); color: rgb(255, 255, 255); display: inline-block; border-top-left-radius: 3px; border-top-right-radius: 3px; border-bottom-right-radius: 3px; border-bottom-left-radius: 3px; font-size: 12px; line-height: 30px; font-family: &#39;microsoft yahei&#39;; text-align: center; float: right; cursor: pointer;\">预订</a></p></li></ul><p><br/></p>', '2016-11-23', '大家印象： 毗临湘江、文物古迹众多的岳麓山，是南岳衡山七十二峰之一，有一份古老的墨香，是一个寻幽访古的好去处。 [详细简介]到达与离开：岳麓山景区位于长沙市...景点类型：山峰最佳季节：四季皆宜。 春天的岳...建议游玩：4-6小时特价门票01长沙岳麓山索道成人票（下行） 当天15:00前可预订￥', '0', null, '7fccbee7-413a-4062-bd9c-2dc2abd050c9.jpg', '0', null, '关于岳麓山', '1', '72', '0', null, null, null, '', '岳麓区', '2');
INSERT INTO `cms_article` VALUES ('125', '1', '<p class=\"main-desc-p\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; line-height: 20px; color: rgb(102, 102, 102);\"><span class=\"main-desc-tip\" style=\"margin: 0px; padding: 0px; list-style: none; font-weight: 700;\">大家印象：</span>&nbsp;橘子洲晚上的烟火可是一道靓丽的风景，印象最深的就是喷泉和烟花，小火车挺好玩的。毛爷爷巨大的雕塑特霸气...&nbsp;<a class=\"click-more-info\" style=\"margin: 0px; padding: 0px; list-style: none; background-color: transparent; color: rgb(0, 145, 217); cursor: pointer;\">[详细简介]</a></p><p><span class=\"point-rank\" style=\"margin: 0px 0px 5px; padding: 0px; list-style: none; width: 270px; display: block; color: rgb(102, 102, 102); height: 26px;\">长沙193个景点中排名第<span class=\"rank\" style=\"margin: 0px 0px 0px 3px; padding: 0px; list-style: none; display: inline; position: relative; width: auto; color: rgb(255, 156, 0); font-size: 14px;\">3</span></span><span class=\"main-besttime\" style=\"margin: 0px; padding: 0px; list-style: none; width: 270px; display: block; color: rgb(102, 102, 102);\"><span style=\"margin: 0px; padding: 0px; list-style: none; width: 270px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 18px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); font-family: globel-iconfont !important;\"></span>景点类型：其他</span></span><span class=\"main-besttime\" style=\"margin: 0px; padding: 0px; list-style: none; width: 270px; display: block; color: rgb(102, 102, 102);\"><span style=\"margin: 0px; padding: 0px; list-style: none; width: 270px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 18px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); font-family: globel-iconfont !important;\"></span>最佳季节：四季皆宜。春天江鸥点...</span></span><span class=\"main-dcnt\" style=\"margin: 0px; padding: 0px; list-style: none; width: 270px; display: block; color: rgb(102, 102, 102);\"><span style=\"margin: 0px; padding: 0px; list-style: none; width: 270px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 18px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); font-family: globel-iconfont !important;\"></span>建议游玩：2-3小时</span></span></p><p><img src=\"http://image.zscat.com/upload1/20161123/1479903249824.jpg\" style=\"float:none;\" title=\"thumbnails (1).jpg\"/></p><p><img src=\"http://image.zscat.com/upload1/20161123/1479903252226.jpg\" style=\"float:none;\" title=\"thumbnails (2).jpg\"/></p><p><br/></p>', '2016-11-23', '大家印象： 橘子洲晚上的烟火可是一道靓丽的风景，印象最深的就是喷泉和烟花，小火车挺好玩的。毛爷爷巨大的雕塑特霸气... [详细简介]长沙193个景点中排名第3景点类型：其他最佳季节：四季皆宜。春天江鸥点...建议游玩：2-3小时', '0', null, 'a37f5203-b76e-42b8-98e5-47d4b841dd95.jpg', '0', null, '关于橘子洲', '1', '72', '0', null, null, null, 'http://image.zscat.com/upload1/20161123/1479903249824.jpg,http://image.zscat.com/upload1/20161123/1479903252226.jpg,', '岳麓区', '2');
INSERT INTO `cms_article` VALUES ('126', '2', '<p><span style=\"margin: 0px; padding: 0px; list-style: none; line-height: 30px; color: rgb(51, 51, 51); font-weight: 700;\">大家印象</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none;\">西汉长沙国丞相利仓及其家属墓葬，为后人了解西汉历史提供资料。还算保存的完好，在这里可见中国古人的智慧。</p><p><span style=\"margin: 0px; padding: 0px; list-style: none; line-height: 30px; color: rgb(51, 51, 51); font-weight: 700;\">走进马王堆汉墓</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none;\">马王堆位于长沙市东郊长浏公路北侧，距市中心约4公里。 马王堆并非“马王”之堆，而是西汉初期长沙国丞相、轪侯利仓及其家属的墓地。堆上东西又各突起土冢一个，其间相距20余米。形似马鞍，故也称为马鞍堆。 堆上分布西汉墓三座，三座汉墓中，二号墓葬的是汉初长沙国丞相轪侯利苍，一号墓是利苍妻的墓，三号墓是利苍之子的墓。现在一、二号墓坑已填塞，其中出土的女尸、素纱禅衣及一大批西汉器皿和帛书画等都保存于湖南省博物馆；三号墓坑经过整理加固，供人们参观。 马王堆汉墓是西汉初期长沙国丞相、轪侯利仓及其家属的墓葬。长沙为汉长沙国首府临湘县所在地。该墓地曾被讹传为五代十国时楚王马殷的墓地，故称马王堆；又曾被附会为长沙王刘发埋葬其母程、唐二姬的“双女”。 湖南省博物馆与中国科学院考古研究所1972年发掘了1号墓；1973至1974年初，发掘了2号、3号墓。 马王堆三座汉墓共出土珍贵文物3000多件，绝大多数保存完好。其中五百多件各种漆器，制作精致，纹饰华丽，光泽如新。珍贵的是一号墓的大量丝织品，保护完好。品种众多，有绢、绮、罗、纱、锦等。有一件素纱襌衣，轻若烟雾，薄如蝉翼，该衣长1.28米，且有长袖，重量仅49克，织造技巧之高超，真是天工巧夺。出土的帛画，为我国现存最早的描写当时现实生活的大型作品。还有彩俑、乐器、兵器、印章、帛书等珍品。 从最早发掘的一号墓中出土了一具保存完好的女尸，据考证为利苍的妻子辛追，年龄约五十岁左右，出土时软组织有弹性，关节能活动，血管清晰可见，为世界考古史上前所未见的不腐湿尸，此后将此类古尸命名为马王堆尸。</p><p><span class=\"main-besttime\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span></span></span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">景点类型：</span><span style=\"margin: 0px 5px 10px 0px; padding: 0px; list-style: none; width: auto;\">历史遗址</span></p><p><span class=\"main-besttime\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">最佳季节：</span>四季皆宜。</p></span></span><span class=\"main-dcnt\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">建议游玩：</span>3-5小时</p></span></span><span class=\"main-ticket-price\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">门票：</span>免费不免票的原则，要出示身份证排队领票。每天限量5000人，发票时间为每个开放日的8:30-16:00，门票有限，发完即止。讲解免费。</p></span></span><span class=\"main-open\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">开放时间：</span>全年开放 夏季：08:00~16:30 冬季：09:00~16:00</p></span></span><span class=\"main-address\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">地址：</span>长沙市区东郊四千米处的浏阳河旁的马王堆乡</p></span></span><span class=\"main-phone\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">电话：</span>0731-5810110</p></span></span></p><p></p><p><br/></p>', '2016-11-23', '大家印象西汉长沙国丞相利仓及其家属墓葬，为后人了解西汉历史提供资料。还算保存的完好，在这里可见中国古人的智慧。走进马王堆汉墓马王堆位于长沙市东郊长浏公路北侧，距市中心约4公里。 马王堆并非“马王”之堆，而是西汉初期长沙国丞相、轪侯利仓及其家属的墓地。堆上东西又各突起土冢一个，其间相距20余米。形似马鞍，故也称', '0', null, 'c4103e55-f6a5-4b07-91d3-8992d1f0ea86.jpg', '0', null, '关于马王堆汉墓', '1', '73', '0', null, null, null, '', '芙蓉区', '2');
INSERT INTO `cms_article` VALUES ('127', '1', '<p><span style=\"margin: 0px; padding: 0px; list-style: none; line-height: 30px; color: rgb(51, 51, 51); font-weight: 700;\">大家印象</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none;\">北京文化底蕴深厚，是一个古典与现代结合的城市。旅游资源十分丰富，景色都很宏伟壮观。北京的交通和购物都很方便，也有很多美食，小吃和北京烤鸭好吃，雾霾比较严重，空气质量不行。</p><p><span style=\"margin: 0px; padding: 0px; list-style: none; line-height: 30px; color: rgb(51, 51, 51); font-weight: 700;\">走进北京</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none;\">北京，中国首都。每个人心中，都有一个“我爱北京天安门”的北京情结，都曾梦想着生活在传说中的紫禁城；梦想着穿梭在王朔笔下的胡同和大院；也梦想着爬上万里长城，大喊：我是好汉！在每个人心中，都一个人属于自己的北京。 北京是一座包容万象、海纳百川的城市。三千年的历史，六朝故都，这里荟萃了自元明清以来的中华文化，荟萃了众多名胜古迹和人文景观。这里汇聚了八方来客，宗教、文化、语言在这里融合，兼容并蓄。如果想准确的描绘出北京的模样，无异于痴人说梦，北京在红宫墙外的宁静小路上，在胡同儿的转弯拐角儿，在国贸的匆匆路旁，也在未名湖边的石砖小径。只有用心去感受，去聆听，才能听到北京的内心独白。 在我的心里，北京表面上它是现代大都会，但是内心却有抹不去的古朴和怀旧。闲庭信步在逐渐少去的胡同里，走进那热气腾腾的涮肉店，那才是真正的北京。 有许多著名的学府如清华、北大等也汇聚于此，可以漫步校园中想象朱自清、胡适等昔日大师在校任教时的情景。 还有许多闻名遐迩的自然景观，如红叶迷人的香山公园、竹林遍地的紫竹院、环境幽雅的玉渊潭……</p><p><span class=\"main-besttime\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span></span></span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">最佳季节：</span>9月-10月。这是来北京旅游的最佳时节。 秋季的北京一年中最美丽的，此时秋高气爽，气候宜人，空气质量最佳。尤其是十月下旬（霜降前后）至十一月上旬，是红叶层林尽染的时候，著名的香山“红叶节”也将在此期间举行。 另一个在北京旅游观光的好时间是每年12月初至次年2月中旬，这个时候来北京就一定要去北京的庙会逛一逛。正月里（农历大年三十到二月初一）是举办庙会最为集中的日子，厂甸、五显财神庙、白云观、雍和宫、东岳庙、龙潭湖等都会举办规模盛大的庙会，处处人山人海、热闹非凡，一派和谐欢乐的景象。</p><p><span class=\"main-dcnt\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">建议游玩：</span>5-7天</p></span></span></p><p></p><p><br/></p>', '2016-11-23', '大家印象北京文化底蕴深厚，是一个古典与现代结合的城市。旅游资源十分丰富，景色都很宏伟壮观。北京的交通和购物都很方便，也有很多美食，小吃和北京烤鸭好吃，雾霾比较严重，空气质量不行。走进北京北京，中国首都。每个人心中，都有一个“我爱北京天安门”的北京情结，都曾梦想着生活在传说中的紫禁城；梦想着穿梭在王朔笔下的胡同', '0', null, '59afdb18-2f69-4e96-ba90-154e79c34879.jpg', '0', null, '关于北京', '1', '70', '0', null, null, null, '', '朝阳区', '2');
INSERT INTO `cms_article` VALUES ('128', '1', '<p><span style=\"margin: 0px; padding: 0px; list-style: none; line-height: 30px; color: rgb(51, 51, 51); font-weight: 700;\">大家印象</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none;\">故宫是一直向往的神圣之地，风景很美。故宫的建筑群保存得非常完好，无论是恢宏的气势、绝佳的建筑，还是深厚的历史文化底蕴都让人印象深刻。天气好的时候看得更加真切。</p><p><span style=\"margin: 0px; padding: 0px; list-style: none; line-height: 30px; color: rgb(51, 51, 51); font-weight: 700;\">走进故宫</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none;\">北京故宫，旧称紫禁城，是中国明清两代24位皇帝的皇宫。是无与伦比的古代建筑杰作，也是世界现存最大、最完整的木质结构的古建筑群。 故宫宫殿建筑均是木结构、黄琉璃瓦顶、青白石底座，饰以金碧辉煌的彩画。被誉为世界五大宫之一（北京故宫、法国凡尔赛宫、英国白金汉宫、美国白宫、俄罗斯克里姆林宫）。 故宫的建筑沿着一条南北向中轴线排列并向两旁展开，南北取直，左右对称。依据其布局与功用分为“外朝”与“内廷”两大部分，以乾清门为界，乾清门以南为外朝，以北为内廷。外朝、内廷的建筑气氛迥然不同。 故宫有4个门，正门名午门，东门名东华门，西门名西华门，北门名神武门。面对北门神武门，有用土、石筑成的景山，满山松柏成林。 外朝以太和殿、中和殿、保和殿三大殿为中心，其中三大殿中的&quot;太和殿”俗称“金銮殿”，是皇帝举行朝会 的地方，也称为“前朝”。是封建皇帝行使权力、举行盛典的地方。此外两翼东有文华殿、文渊阁、上驷院、南三所；西有武英殿、内务府等建筑。建筑造型宏伟壮丽，庭院明朗开阔，象征封建政权至高无上。 内廷以乾清宫、交泰殿、坤宁宫后三宫为中心，两翼为养心殿、东六宫、西六宫、斋宫、毓庆宫，后有御花园。是封建帝王与后妃居住之所。内廷东部的宁寿宫是当年乾隆皇帝退位后养老而修建。内廷西部有慈宁宫、寿安宫等。此外还有重华宫，北五所等建筑。庭院深邃，建筑紧凑，自成一体，秩序井然。</p><p><span class=\"main-besttime\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span></span></span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">景点类型：</span><span style=\"margin: 0px 5px 10px 0px; padding: 0px; list-style: none; width: auto;\">公园</span><span style=\"margin: 0px 5px 10px 0px; padding: 0px; list-style: none; width: auto;\">历史建筑</span><span style=\"margin: 0px 5px 10px 0px; padding: 0px; list-style: none; width: auto;\">历史遗址</span></p><p><span class=\"main-besttime\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">最佳季节：</span>四季皆宜。春季气候舒适，在故宫赏花是不错的选择；夏季炎热，可在室内参观；秋季，北京秋高气爽，红叶满地，游览故宫非常合适；冬季虽然寒冷，但是故宫的雪景确实美不胜收。</p></span></span><span class=\"main-dcnt\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">建议游玩：</span>3-4小时</p></span></span><span class=\"main-ticket-price\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">门票：</span>旺季（4月1日~10月31日）：60.00元 淡季（11月1日~3月31日）：40.00元 珍宝馆（即进入宁寿宫区，含戏曲馆、石鼓馆）：10.00元 钟表馆（即进入奉先殿区）：10.00元</p></span></span><span class=\"main-open\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">开放时间：</span>1. 旺季（4月1日~10月31日）：08:30~17:00 停止售票时间：16:00 停止入场时间：16:10 2. 淡季（11月1日~3月31日）：08:30~16:30 停止售票时间：15:30 停止入场时间：15:40 Tips：除法定节假日和暑期（7月1日~8月31日）外，故宫博物院全年实行周一下午闭馆的措施。每周一开馆时间为08:30~12:00，停止售票时间为11:00，停止检票时间为11:10，闭馆时间为12:00。</p></span></span><span class=\"main-address\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">地址：</span>北京市东城区景山前街4号</p></span></span><span class=\"main-phone\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">电话：</span>86-10-85007422,85007421</p></span></span></p><p></p><p><br/></p>', '2016-11-23', '大家印象故宫是一直向往的神圣之地，风景很美。故宫的建筑群保存得非常完好，无论是恢宏的气势、绝佳的建筑，还是深厚的历史文化底蕴都让人印象深刻。天气好的时候看得更加真切。走进故宫北京故宫，旧称紫禁城，是中国明清两代24位皇帝的皇宫。是无与伦比的古代建筑杰作，也是世界现存最大、最完整的木质结构的古建筑群。 故宫宫殿', '0', null, '62a72273-77ac-41b0-a643-09c86b71baef.jpg', '0', null, '关于故宫', '1', '71', '0', null, null, null, '', '海定区', '2');
INSERT INTO `cms_article` VALUES ('129', '1', '<p><span style=\"margin: 0px; padding: 0px; list-style: none; line-height: 30px; color: rgb(51, 51, 51); font-weight: 700;\">大家印象</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none;\">颐和园是现今保存最完整的皇家园林，北方的皇室园林更体现出大方的特色。昆明湖的湖水很清澈，视野也辽阔，长廊的壁画最为经典。夏季的荷花和金鱼特别美，深冬的雪景也很赞。</p><p><span style=\"margin: 0px; padding: 0px; list-style: none; line-height: 30px; color: rgb(51, 51, 51); font-weight: 700;\">走进颐和园</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none;\">颐和园位于北京西北郊海淀区内，距北京城区15公里，是我国现存规模最大，保存最完整的皇家园林之一，也是享誉世界的旅游胜地之一。 颐和园是利用昆明湖、万寿山为基址，以杭州西湖风景为蓝本，汲取江南园林的某些设计手法和意境而建成的一座大型天然山水园，也是保存得最完整的一座皇家行宫御苑，被誉为皇家园林博物馆。 颐和园景区规模宏大，园内建筑以佛香阁为中心，园中有景点建筑物百余座、大小院落20余处，3555古建筑，面积70000多平方米，共有亭、台、楼、阁、廊、榭等不同形式的建筑3000多间。古树名木1600余株。其中佛香阁、长廊、石舫、苏州街、十七孔桥、谐趣园、大戏台等都已成为家喻户晓的代表性建筑。</p><p><span class=\"main-besttime\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span></span></span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">景点类型：</span><span style=\"margin: 0px 5px 10px 0px; padding: 0px; list-style: none; width: auto;\">历史建筑</span></p><p><span class=\"main-besttime\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">最佳季节：</span>9-10月最佳。北京的春秋不冷不热，气候适中，是理想的旅游季节，尤其是秋季天高气爽，气候宜人；夏季虽炎热，但是景色绝佳，在昆明湖游船赏景再合适不过了；冬季寒冷，湖面结冰，白雪下的颐和园也别有一番神秘的景致。</p></span></span><span class=\"main-dcnt\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">建议游玩：</span>4-5小时</p></span></span><span class=\"main-ticket-price\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">门票：</span>1. 旺季（4月1日~10月31日）：30.00元 德和园：5.00元 佛香阁：10.00元 苏州街：10.00元 文昌院：20.00元 联票（含门票、文昌院、德和园、佛香阁、苏州街澹宁堂）：60.00元 2. 淡季（11月1日~3月31日）：20.00元 德和园：5.00元 佛香阁：10.00元 苏州街：10.00元 文昌院：20.00元 联票（含门票、文昌院、德和园、佛香阁、苏州街澹宁堂）：50.00元</p></span></span><span class=\"main-open\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">开放时间：</span>1. 旺季（4月1日~10月31日）：06:30~20:00 停止售票时间：18:00 园中园（含文昌院、德和园、佛香阁、苏州街澹宁堂）：08:30~17:00 2. 淡季（11月1日~3月31日）：07:00~19:00 停止售票时间：17:00 园中园（含文昌院、德和园、佛香阁、苏州街澹宁堂）：09:00~18:00</p></span></span><span class=\"main-address\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">地址：</span>北京市海淀区新建宫门路19号</p></span></span><span class=\"main-phone\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">电话：</span>010-62881144</p></span></span><span class=\"main-website\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">官方网站：</span>http://www.summerpalace-china.com</p></span></span></p><p></p><p><br/></p>', '2016-11-23', '大家印象颐和园是现今保存最完整的皇家园林，北方的皇室园林更体现出大方的特色。昆明湖的湖水很清澈，视野也辽阔，长廊的壁画最为经典。夏季的荷花和金鱼特别美，深冬的雪景也很赞。走进颐和园颐和园位于北京西北郊海淀区内，距北京城区15公里，是我国现存规模最大，保存最完整的皇家园林之一，也是享誉世界的旅游胜地之一。 颐和', '0', null, '090da8fd-9749-438d-89f6-5d6c70487bfe.jpg', '0', null, '关于颐和园', '1', '70', '0', null, null, null, '', '朝阳区', '2');
INSERT INTO `cms_article` VALUES ('131', '1', '<p><span style=\"margin: 0px; padding: 0px; list-style: none; line-height: 30px; color: rgb(51, 51, 51); font-weight: 700;\">大家印象</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none;\">八达岭长城壮观宏伟，风景秀丽，不愧是世界八大奇迹之一，象征着中国古代劳动人民智慧的结晶。有的台阶挺陡峭的，爬起来有些吃力。八达岭长城离市区远，节假日人山人海，千万别去。</p><p><span style=\"margin: 0px; padding: 0px; list-style: none; line-height: 30px; color: rgb(51, 51, 51); font-weight: 700;\">走进八达岭长城</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none;\">八达岭长城位于北京市延庆县军都山关沟古道北口。是明长城中保存最好、也最具代表性的地段。联合国“世界文化遗产”之一。 八达岭长城典型地表现了万里长城雄伟险峻的风貌。作为北京的屏障，这里山峦重叠，形势险要。气势极其磅礴的城墙南北盘旋延伸于群峦峻岭之中。依山势向两侧展开的长城雄峙危崖，陡壁悬崖上古人所书的&quot;天险&quot;二字，确切的概括了八达岭位置的军事重要性。 八达岭长城驰名中外，誉满全球。是万里长城向游人开放最早的地段。“不到长城非好汉”。迄今，先后有尼克松、里根、撒切尔、戈尔巴乔夫、伊丽莎白等372位外国首脑和众多的世界风云人物登上八达岭观光游览。 八达岭长城为居庸关的重要前哨，古称“居庸之险不在关而在八达岭”。明长城的八达岭段是长城建筑最精华段，集巍峨险峻、秀丽苍翠于一体，“玉关天堑”为明代居庸关八景之一。 八达岭长城其关城为东窄西宽的梯形，建于明弘治十八年，嘉靖、万历年间曾修葺。关城有东西二门，东门额题“居庸外镇”，刻于嘉靖十八年；西门额题“北门锁钥”，刻于万历十年。 八达岭是历史上许多重大事件的见证。第一帝王秦始皇东临碣石后，从八达岭取道大同，再驾返咸阳。肖太后巡幸、元太祖入关、元代皇帝每年两次往返北京和上都之间、明代帝王北伐、李自成攻陷北京、清代天子亲征……八达岭都是必经之地。近代史上，慈禧西逃泪洒八达岭、詹天佑在八达岭主持修筑中国自力修建的第一条铁路——京张铁路、孙中山先生登临八达岭长城等，留下了许多历史典故和珍贵的历史回忆，是历史名地。 八达岭景区除了长城外，还有长城碑林、五郎像、石佛寺石像、戚继光景园、袁崇焕景园、长城碑林景园等景点。</p><p><span class=\"main-besttime\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span></span></span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">到达与离开：</span></p><p><span class=\"main-besttime\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">景点类型：</span><span style=\"margin: 0px 5px 10px 0px; padding: 0px; list-style: none; width: auto;\">历史建筑</span></p></span></span><span class=\"main-besttime\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">最佳季节：</span>3月-5月和9月-11月为最佳登长城时间。 春季是北京气温最舒适的季节，此时游人较少，可尽情领略雄关漫道。 秋季则是天高气爽，登长城看万山树叶红遍奇景。 冬夏二季气候条件比春秋略逊，但是冬季被大雪覆盖的长城更有庄严巍巍气势。</p></span></span><span class=\"main-dcnt\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">建议游玩：</span>3-4小时</p></span></span><span class=\"main-ticket-price\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">门票：</span>旺季（4~10月）：45.00元 淡季（11~3月）：40.00元</p></span></span><span class=\"main-open\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">开放时间：</span>旺季（4~10月）：06:30~19:00 淡季（11~3月）：07:00~18:00</p></span></span><span class=\"main-address\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">地址：</span>北京市延庆县军都山关沟古道北口</p></span></span><span class=\"main-phone\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">电话：</span>010-69121226</p></span></span><span class=\"main-website\" style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: 653px; display: block;\"><span class=\"globel-iconfont\" style=\"margin: 0px 5px 0px 0px; padding: 0px; list-style: none; font-size: 16px; -webkit-font-smoothing: antialiased; -webkit-text-stroke-width: 0.2px; color: rgb(165, 170, 173); vertical-align: top; font-family: globel-iconfont !important;\"></span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style: none; display: inline-block; width: 630.46875px; vertical-align: top;\"><span style=\"margin: 0px 0px 10px; padding: 0px; list-style: none; width: auto; font-weight: 700;\">官方网站：</span>www.badaling.gov.cn</p></span></span></p><p></p><p><br/></p>', '2016-11-23', '大家印象八达岭长城壮观宏伟，风景秀丽，不愧是世界八大奇迹之一，象征着中国古代劳动人民智慧的结晶。有的台阶挺陡峭的，爬起来有些吃力。八达岭长城离市区远，节假日人山人海，千万别去。走进八达岭长城八达岭长城位于北京市延庆县军都山关沟古道北口。是明长城中保存最好、也最具代表性的地段。联合国“世界文化遗产”之一。 八达', '0', null, '679e8864-90fc-4a82-a2c0-7ce8bd6d5006.jpg', '0', null, '关于八达岭长城', '1', '70', '0', null, null, null, '', '朝阳区', '2');
INSERT INTO `cms_article` VALUES ('132', '1', '<section class=\"poi-dishes\" style=\"margin: 0px 0px 20px; padding: 0px; color: rgb(51, 51, 51); font-family: tahoma, arial, 宋体, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;\"><h3 class=\"title-hd\" style=\"margin: 0px 0px 15px; padding: 0px 0px 0px 10px; font-size: 20px; border-left-width: 3px; border-left-style: solid; border-left-color: rgb(255, 191, 0); font-weight: normal; line-height: normal; font-family: 微软雅黑;\">推荐菜</h3><ul class=\"dishes-list taggleMore__list\" style=\"margin: 0px 0px 22px; padding: 0px 15px; list-style: none;\"><li>炸酱面(4233)</li><li>爆肚(1998)</li><li>蓑衣黄瓜(1980)</li><li>炸灌肠(1721)</li><li>炒红果(1669)</li><li>芥末墩(1623)</li><li>肉丁炸酱面(1588)</li><li>驴打滚(1479)</li><li>麻豆腐(1432)</li><li>豆汁(1180)</li><li>艾窝窝(1141)</li><li>茄泥(1070)</li><li>糖醋里脊(999)</li><li>腔骨(917)</li><li>茶汤(902)</li><li>灌肠(857)</li><li><a href=\"https://lvyou.baidu.com/scene/poi/restaurant?surl=beijing&place_uid=39d43f82c7673a9fa8059d04###\" class=\"taggleMore__anchor\" style=\"text-decoration: none; color: rgb(0, 126, 217);\">更多<span class=\"triangle-down\" style=\"display: inline-block; position: relative; width: 0px; height: 0px; margin: 0px auto; border-width: 6px; border-style: solid dashed dashed; border-color: rgb(137, 137, 137) transparent transparent; top: 5px;\"><span style=\"position: absolute; top: -8px; left: -7px; display: block; width: 0px; height: 0px; font-size: 0px; line-height: 0; border-width: 7px; border-style: solid dashed dashed; border-color: rgb(255, 255, 255) transparent transparent;\"></span></span></a></li></ul><ul style=\"margin: 0px; padding: 0px 15px; list-style: none;\"></ul></section><p><section class=\"poi-recommend\" style=\"margin: 0px 0px 60px; padding: 0px 0px 20px; border-bottom-style: dotted; border-bottom-width: 1px; border-bottom-color: rgb(221, 221, 221); color: rgb(51, 51, 51); font-family: tahoma, arial, 宋体, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;\"><p class=\"description\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px 15px; font-size: 14px; line-height: 24px; font-family: normal;\">典型老北京馆子，店小二吆喝着端上来各种地道北京美食。</p></section></p><p><br/></p>', '2016-11-23', '推荐菜炸酱面(4233)爆肚(1998)蓑衣黄瓜(1980)炸灌肠(1721)炒红果(1669)芥末墩(1623)肉丁炸酱面(1588)驴打滚(1479)麻豆腐(1432)豆汁(1180)艾窝窝(1141)茄泥(1070)糖醋里脊(999)腔骨(917)茶汤(902)灌肠(857)更多典型老北京馆子，店小二吆', '0', null, 'ea5cf1f3-dc85-45b1-be5b-714ee6a08db3.jpg', '0', null, '海碗居(牡丹园店)', '1', '74', '0', null, null, null, '', '东城', '2');
INSERT INTO `cms_article` VALUES ('133', '1', '<section class=\"poi-dishes\" style=\"margin: 0px 0px 20px; padding: 0px; color: rgb(51, 51, 51); font-family: tahoma, arial, 宋体, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;\"><h3 class=\"title-hd\" style=\"margin: 0px 0px 15px; padding: 0px 0px 0px 10px; font-size: 20px; border-left-width: 3px; border-left-style: solid; border-left-color: rgb(255, 191, 0); font-weight: normal; line-height: normal; font-family: 微软雅黑;\">推荐菜</h3><ul class=\"dishes-list taggleMore__list\" style=\"margin: 0px 0px 22px; padding: 0px 15px; list-style: none;\"><li>烤鸭(19974)</li><li>芥末鸭掌(6773)</li><li>盐水鸭肝(5989)</li><li>火燎鸭心(5273)</li><li>鸭架汤(4544)</li><li>豌豆黄(2561)</li><li>片皮鸭(2278)</li><li>小鸭酥(2192)</li><li>干烧四鲜(1879)</li><li>鸭汤(1600)</li><li>酸辣黄瓜(1368)</li><li>鸭肝(1325)</li><li>芥茉鸭掌(1251)</li><li>鸭舌(1145)</li><li>糟溜鸭三白(1016)</li><li>雀巢鸭宝(976)</li><li><a href=\"https://lvyou.baidu.com/scene/poi/restaurant?surl=beijing&place_uid=79cceb3678185b901f58009d###\" class=\"taggleMore__anchor\" style=\"text-decoration: none; color: rgb(0, 126, 217);\">更多<span class=\"triangle-down\" style=\"display: inline-block; position: relative; width: 0px; height: 0px; margin: 0px auto; border-width: 6px; border-style: solid dashed dashed; border-color: rgb(137, 137, 137) transparent transparent; top: 5px;\"><span style=\"position: absolute; top: -8px; left: -7px; display: block; width: 0px; height: 0px; font-size: 0px; line-height: 0; border-width: 7px; border-style: solid dashed dashed; border-color: rgb(255, 255, 255) transparent transparent;\"></span></span></a></li></ul><ul style=\"margin: 0px; padding: 0px 15px; list-style: none;\"></ul></section><p><section class=\"poi-recommend\" style=\"margin: 0px 0px 60px; padding: 0px 0px 20px; border-bottom-style: dotted; border-bottom-width: 1px; border-bottom-color: rgb(221, 221, 221); color: rgb(51, 51, 51); font-family: tahoma, arial, 宋体, sans-serif; font-size: 12px; line-height: 18px; white-space: normal;\"><p class=\"description\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px 15px; font-size: 14px; line-height: 24px; font-family: normal;\">全球最大的烤鸭店，环境和味道绝对超一流！</p></section></p><p><br/></p>', '2016-11-23', '推荐菜烤鸭(19974)芥末鸭掌(6773)盐水鸭肝(5989)火燎鸭心(5273)鸭架汤(4544)豌豆黄(2561)片皮鸭(2278)小鸭酥(2192)干烧四鲜(1879)鸭汤(1600)酸辣黄瓜(1368)鸭肝(1325)芥茉鸭掌(1251)鸭舌(1145)糟溜鸭三白(1016)雀巢鸭宝(976)更多全', '0', null, 'f61062b1-4070-4d6f-8fbe-2da746a15d50.jpg', '0', null, '全聚德(和平门店)', '1', '75', '0', null, null, null, '', '西城', '2');
INSERT INTO `cms_article` VALUES ('134', 'vedio', '<p><img src=\"http://image.zscat.com/upload1/20161209/1481272185740.gif\" title=\"1环境搭建.gif\"/></p>', '2016-12-09', '', '0', null, '8257b51f-5319-4056-bb48-18f5e57448f0.jpg', '0', null, 'zscat', '1', '60', '0', null, null, null, '', '天津房产', '1');
INSERT INTO `cms_article` VALUES ('135', 'text', '<p><img src=\"http://image.zscat.com/upload1/20161209/1481272185740.gif\" title=\"1环境搭建.gif\"/></p>', '2016-12-09', '', '0', null, '8257b51f-5319-4056-bb48-18f5e57448f0.jpg', '0', null, 'zscat', '1', '60', '0', null, null, null, '', '天津房产', '1');
INSERT INTO `cms_article` VALUES ('136', 'subject', '<p><img src=\"http://image.zscat.com/upload1/20161209/1481272185740.gif\" title=\"1环境搭建.gif\"/></p>', '2016-12-09', '', '0', null, '8257b51f-5319-4056-bb48-18f5e57448f0.jpg', '0', null, 'zscat', '1', '60', '0', null, null, null, '', '天津房产', '1');
INSERT INTO `cms_article` VALUES ('137', 'activity', '<p><img src=\"http://image.zscat.com/upload1/20161209/1481272185740.gif\" title=\"1环境搭建.gif\"/></p>', '2016-12-09', '', '0', null, '8257b51f-5319-4056-bb48-18f5e57448f0.jpg', '0', null, 'zscat', '1', '60', '0', null, null, null, '', '天津房产', '1');
INSERT INTO `cms_article` VALUES ('138', 'quwen', '<p><img src=\"http://image.zscat.com/upload1/20161209/1481272185740.gif\" title=\"1环境搭建.gif\"/></p>', '2016-12-09', '', '0', null, '8257b51f-5319-4056-bb48-18f5e57448f0.jpg', '0', null, 'zscat', '1', '60', '0', null, null, null, '', '天津房产', '1');
INSERT INTO `cms_article` VALUES ('139', 'reader', '<p><img src=\"http://image.zscat.com/upload1/20161209/1481272185740.gif\" title=\"1环境搭建.gif\"/></p>', '2016-12-09', '', '0', null, '8257b51f-5319-4056-bb48-18f5e57448f0.jpg', '0', null, 'zscat', '1', '60', '0', null, null, null, '', '天津房产', '1');
INSERT INTO `cms_article` VALUES ('140', 'vedio', '<p><img src=\"http://image.zscat.com/upload1/20161209/1481272185740.gif\" title=\"1环境搭建.gif\"/></p>', '2016-12-09', '', '0', null, '8257b51f-5319-4056-bb48-18f5e57448f0.jpg', '0', null, 'zscat', '1', '60', '0', null, null, null, '', '天津房产', '1');
INSERT INTO `cms_article` VALUES ('141', 'vedio', '<p><img src=\"http://image.zscat.com/upload1/20161209/1481272185740.gif\" title=\"1环境搭建.gif\"/></p>', '2016-12-09', '', '0', null, '8257b51f-5319-4056-bb48-18f5e57448f0.jpg', '0', null, 'zscat', '1', '60', '0', null, null, null, '', '天津房产', '1');

-- ----------------------------
-- Table structure for cms_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_category`;
CREATE TABLE `cms_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `keywords` varchar(255) DEFAULT NULL COMMENT '关键字',
  `module` varchar(255) DEFAULT NULL COMMENT '模块',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `parent_id` bigint(20) DEFAULT NULL,
  `grade` int(11) NOT NULL COMMENT '级别',
  `isShow` int(11) NOT NULL COMMENT '是否展示',
  `orderNo` int(11) NOT NULL,
  `site_id` bigint(20) DEFAULT NULL COMMENT '网址',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `parent_ids` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2478CF34EA0C9E18` (`parent_id`) USING BTREE,
  KEY `FK2478CF341EEC3DA4` (`site_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_category
-- ----------------------------
INSERT INTO `cms_category` VALUES ('60', '1', null, '1', 'houseNav', '天津房产', '0', '1', '1', '1', '1', '1', '0,');
INSERT INTO `cms_category` VALUES ('61', '1', null, '1', '0', '朝阳区', '59', '1', '1', '1', '1', '1', '0,59,');
INSERT INTO `cms_category` VALUES ('62', '1', null, '1', '0', '海淀区', '59', '1', '1', '1', '1', '1', '0,59,');
INSERT INTO `cms_category` VALUES ('63', '1', null, '1', '0', '昌平区', '59', '1', '1', '1', '1', '1', '0,59,');
INSERT INTO `cms_category` VALUES ('64', '1', null, '1', 'houseNav', '长沙房产', '0', '1', '1', '1', '1', '1', '0,');
INSERT INTO `cms_category` VALUES ('65', '1', null, '1', '0', '天心区', '64', '1', '1', '1', '1', '1', '0,64,');
INSERT INTO `cms_category` VALUES ('66', '1', null, '1', '0', '岳麓区', '64', '1', '1', '1', '1', '1', '0,64,');
INSERT INTO `cms_category` VALUES ('67', '1', null, '1', 'travelNav', '北京旅游', '0', '1', '1', '1', '2', '1', '0,');
INSERT INTO `cms_category` VALUES ('68', '1', null, '1', 'travelNav', '天津旅游', '0', '1', '1', '1', '2', '1', '0,');
INSERT INTO `cms_category` VALUES ('69', '1', null, '1', 'travelNav', '长沙旅游', '0', '1', '1', '1', '2', '1', '0,');
INSERT INTO `cms_category` VALUES ('70', '1', null, '1', '0', '朝阳区', '67', '1', '1', '1', '2', '1', '0,67,');
INSERT INTO `cms_category` VALUES ('71', '1', null, '1', '0', '海定区', '67', '1', '1', '1', '2', '1', '0,67,');
INSERT INTO `cms_category` VALUES ('72', '1', null, '1', '0', '岳麓区', '69', '1', '1', '1', '2', '1', '0,69,');
INSERT INTO `cms_category` VALUES ('73', '1', null, '去', '0', '芙蓉区', '69', '1', '1', '1', '2', '1', '0,69,');
INSERT INTO `cms_category` VALUES ('74', '1', null, '1', '0', '东城', '67', '1', '1', '1', '2', '1', '0,67,');
INSERT INTO `cms_category` VALUES ('75', '1', null, '1', '0', '西城', '67', '1', '1', '1', '2', '1', '0,67,');

-- ----------------------------
-- Table structure for cms_comment
-- ----------------------------
DROP TABLE IF EXISTS `cms_comment`;
CREATE TABLE `cms_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `auditDate` date DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `delFlag` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  `auditer_id` bigint(20) DEFAULT NULL,
  `headPath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF7A9BD69C09E6470` (`article_id`),
  KEY `FKF7A9BD696F3C6204` (`auditer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=415 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_comment
-- ----------------------------
INSERT INTO `cms_comment` VALUES ('13', null, '测试头像。。。', '2014-06-13 11:45:03', '0', '', '118.113.170.115', 'haha', '', '59', null, '/assets/img/face/6.png');
INSERT INTO `cms_comment` VALUES ('14', null, '<img alt=\"哭\" src=\"/assets/comp/xheditor/xheditor_emot/default/cry.gif\" />哈哈', '2014-06-13 12:13:01', '0', '', '118.113.170.115', 'hello', '', '59', null, '/assets/img/face/6.png');
INSERT INTO `cms_comment` VALUES ('15', null, '<img alt=\"微笑\" src=\"/assets/comp/xheditor/xheditor_emot/default/smile.gif\" /><img alt=\"大哭\" src=\"/assets/comp/xheditor/xheditor_emot/default/wail.gif\" /><img alt=\"敲打\" src=\"/assets/comp/xheditor/xheditor_emot/default/knock.gif\" />', '2014-06-13 12:14:19', '0', '', '118.113.170.115', '匿名', '', '59', null, '/assets/img/face/5.png');
INSERT INTO `cms_comment` VALUES ('16', null, '&nbsp;&nbsp;不好意思&nbsp;找遍你的网站&nbsp;我都没有看到本站链接&nbsp;麻烦加上去', '2014-06-15 21:19:41', '0', '', '59.55.14.121', '星少·', '', '35', null, '/assets/img/face/5.png');
INSERT INTO `cms_comment` VALUES ('17', null, '您好，贵站好像没有把我站添加上去，处理好后，邮件可以直接通知我！', '2014-06-16 09:55:07', '0', 'youqinglianjie@tiejiang.org', '101.231.214.242', '铁匠运维网', 'www.tiejiang.org', '35', null, '/assets/img/face/12.png');
INSERT INTO `cms_comment` VALUES ('18', null, '<img alt=\"敲打\" src=\"/assets/comp/xheditor/xheditor_emot/default/knock.gif\" /><img alt=\"惊讶\" src=\"/assets/comp/xheditor/xheditor_emot/default/ohmy.gif\" />', '2014-06-16 14:26:38', '0', '', '171.216.78.13', 'cy', '', '48', null, '/assets/img/face/3.png');
INSERT INTO `cms_comment` VALUES ('20', null, '支持一下', '2014-06-22 18:33:57', '0', 'qanlgeokdcw@gmail.com', '86.51.26.18', '博彩网', 'http://www.7bocai8.com', '59', null, '/assets/img/face/1.png');
INSERT INTO `cms_comment` VALUES ('21', null, '好凶', '2014-06-25 19:06:40', '0', '123123', '171.212.145.135', '32123', '123231', '62', null, '/assets/img/face/6.png');
INSERT INTO `cms_comment` VALUES ('22', null, '立欣社 &nbsp; &nbsp; &nbsp; &nbsp;www.78786866.com &nbsp; &nbsp;申请友链 &nbsp; &nbsp; &nbsp;已加贵站链接', '2014-06-27 14:25:00', '0', '2278786866@qq.com', '115.58.98.218', '立欣社', 'www.78786866.com', '35', null, '/assets/img/face/2.png');
INSERT INTO `cms_comment` VALUES ('23', null, '平兄，可以哦~&nbsp;', '2014-07-04 18:15:36', '0', '', '112.193.56.158', 'hewk', '', '35', null, '/assets/img/face/8.png');
INSERT INTO `cms_comment` VALUES ('24', null, '网站启用新域名：http://www.hackerr.org/<br />原站点名不变：苏晨\'s Blog', '2014-07-05 14:28:19', '0', '1142428579@qq.com', '112.5.236.67', '苏晨', 'www.hackerr.org', '35', null, '/assets/img/face/2.png');
INSERT INTO `cms_comment` VALUES ('25', null, '<p>小平 可以哦~ <br /></p><p>猜我是谁 哈哈<br /></p>', '2014-07-16 17:20:21', '0', '', '125.69.127.54', '~~~~', '', '35', null, '/assets/img/face/1.png');
INSERT INTO `cms_comment` VALUES ('75', null, '平平 &nbsp;顶一个', '2014-08-18 16:59:34', '0', '', '175.152.118.30', '昵称', '', '35', null, '/assets/img/face/1.png');
INSERT INTO `cms_comment` VALUES ('256', null, '谢谢分享<img alt=\"奋斗\" src=\"/assets/comp/xheditor/xheditor_emot/default/struggle.gif\" />', '2014-09-18 15:59:38', '0', '2100103948@qq.com', '58.19.46.154', '武汉装饰公司', 'http://www.rkzs.com/', '71', null, '/assets/img/face/10.png');
INSERT INTO `cms_comment` VALUES ('257', null, '学习了不错', '2014-09-18 16:00:28', '0', '2100103948@qq.com', '58.19.46.154', '武汉装饰公司', 'http：//www.rkzs.com/', '69', null, '/assets/img/face/8.png');
INSERT INTO `cms_comment` VALUES ('412', null, '<img alt=\"微笑\" src=\"/assets/comp/xheditor/xheditor_emot/default/smile.gif\" />', '2014-09-22 13:12:14', '0', '', '218.249.133.163', '11', '', '71', null, '/assets/img/face/1.png');
INSERT INTO `cms_comment` VALUES ('413', null, null, '2014-09-24 09:02:48', '0', null, '50.57.190.90', null, null, null, null, null);
INSERT INTO `cms_comment` VALUES ('414', null, null, '2014-09-28 21:39:04', '0', null, '69.84.207.246', null, null, null, null, null);

-- ----------------------------
-- Table structure for cms_img
-- ----------------------------
DROP TABLE IF EXISTS `cms_img`;
CREATE TABLE `cms_img` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `addTime` datetime DEFAULT NULL,
  `typeid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_img
-- ----------------------------
INSERT INTO `cms_img` VALUES ('59', 'tu', '1479177164826.jpg', '15/1479177164826.jpg', '2016-11-15 10:32:44', null);
INSERT INTO `cms_img` VALUES ('60', 'tu', '1479177295719.jpg', '15/1479177295719.jpg', '2016-11-15 10:34:55', null);
INSERT INTO `cms_img` VALUES ('61', 'tu', '1479177303673.jpg', '15/1479177303673.jpg', '2016-11-15 10:35:03', null);

-- ----------------------------
-- Table structure for cms_link
-- ----------------------------
DROP TABLE IF EXISTS `cms_link`;
CREATE TABLE `cms_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `color` varchar(255) DEFAULT NULL COMMENT '颜色',
  `href` varchar(255) DEFAULT NULL COMMENT '链接',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `weight` int(11) DEFAULT NULL COMMENT '权重',
  `weightDate` date DEFAULT NULL,
  `CATEGORY_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7BC64D30CA4E9944` (`CATEGORY_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_link
-- ----------------------------
INSERT INTO `cms_link` VALUES ('1', 'red', 'http://www.baidu.com', 'img', '百度', '999', null, '18');
INSERT INTO `cms_link` VALUES ('2', 'black', 'http://www.taobao.com/', 'img', '淘宝网', '999', null, '17');
INSERT INTO `cms_link` VALUES ('3', 'black', 'http://www.aipp.me/', 'http://www.aipp.me/favicon.ico', 'b0y\'s blog', '1', null, '35');
INSERT INTO `cms_link` VALUES ('5', 'black', 'http://www.super-man.cc/', 'http://www.super-man.cc/content/templates/loper/images/favicon.ico', '陌小染\'s blog', '1', null, '35');
INSERT INTO `cms_link` VALUES ('6', 'black', 'http://www.hackerr.org/', 'http://www.hackerr.org/favicon.ico', '苏晨‘s Blog', '1', null, '35');
INSERT INTO `cms_link` VALUES ('7', 'black', 'http://www.meileche.com', 'http://www.meileche.com/image/meileche.jpg', '美乐车', '1', null, '35');
INSERT INTO `cms_link` VALUES ('8', 'black', 'http://www.tiejiang.org/', 'http://www.google.com/s2/favicons?domain=www.tiejiang.org', '铁匠运维网', '1', null, '35');
INSERT INTO `cms_link` VALUES ('9', 'black', 'http://www.duoluosb.com/', 'http://www.duoluosb.com/Favicon.ico', '堕络\'s Blog', '1', null, '35');
INSERT INTO `cms_link` VALUES ('10', 'black', 'http://0ol.org/', 'http://www.google.com/s2/favicons?domain=0ol.org', '星少‘S Blog', '1', null, '35');
INSERT INTO `cms_link` VALUES ('11', 'black', 'http://www.bccsafe.com/', 'http://www.bccsafe.com/wp-content/themes/20130801171447/images/favicon.ico', 'BccSafe\'s Blog', '1', null, '35');
INSERT INTO `cms_link` VALUES ('12', 'black', 'http://www.hkzn.cn/', 'http://www.hkzn.cn/wp-content/themes/fengying/images/favicon.ico', '指南针Sec', '1', null, '35');
INSERT INTO `cms_link` VALUES ('13', 'black', 'http://prsec.ml/', 'http://www.google.com/s2/favicons?domain=www.prsec.ml', 'Passer\'s Blog', '1', null, '35');
INSERT INTO `cms_link` VALUES ('14', 'black', 'http://www.78786866.com', 'http://www.78786866.com/wp-content/uploads/2014/05/favicon.ico', '立欣社', '1', null, '35');

-- ----------------------------
-- Table structure for cms_site
-- ----------------------------
DROP TABLE IF EXISTS `cms_site`;
CREATE TABLE `cms_site` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `copyright` varchar(255) DEFAULT NULL COMMENT '版权',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `keywords` varchar(255) DEFAULT NULL COMMENT '关键字',
  `logo` varchar(255) DEFAULT NULL COMMENT 'logo',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `theme` varchar(255) DEFAULT NULL COMMENT '主题',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_site
-- ----------------------------
INSERT INTO `cms_site` VALUES ('1', '@copy2016', '房产cms', 'blog', 'blog', 'blog', 'blog', '房产cms');
INSERT INTO `cms_site` VALUES ('2', '@copy2016', '旅游cms', 'news', 'news', 'news', 'news thems', '旅游cms');

-- ----------------------------
-- Table structure for country
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `Id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `countryname` varchar(255) DEFAULT NULL COMMENT '名称',
  `countrycode` varchar(255) DEFAULT NULL COMMENT '代码',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='国家信息';

-- ----------------------------
-- Records of country
-- ----------------------------

-- ----------------------------
-- Table structure for schema_version
-- ----------------------------
DROP TABLE IF EXISTS `schema_version`;
CREATE TABLE `schema_version` (
  `version_rank` int(11) NOT NULL,
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`version`),
  KEY `schema_version_vr_idx` (`version_rank`),
  KEY `schema_version_ir_idx` (`installed_rank`),
  KEY `schema_version_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of schema_version
-- ----------------------------
INSERT INTO `schema_version` VALUES ('1', '1', '1', '<< Flyway Baseline >>', 'BASELINE', '<< Flyway Baseline >>', null, 'root', '2017-05-08 18:55:26', '0', '1');

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` bigint(20) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) DEFAULT NULL COMMENT '所有父级编号',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `name` varchar(100) NOT NULL COMMENT '区域名称',
  `type` char(1) DEFAULT NULL COMMENT '区域类型',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记(0活null 正常 1,删除)',
  `icon` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_area_parent_id` (`parent_id`),
  KEY `sys_area_parent_ids` (`parent_ids`(255)),
  KEY `sys_area_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='区域表';

-- ----------------------------
-- Records of sys_area
-- ----------------------------
INSERT INTO `sys_area` VALUES ('1', '0', '0,', '100000', '中国', '1', '1', '2013-05-27 08:00:00', '2,超级管理员', '2015-02-28 20:37:03', '', '0', 'fa fa-institution');
INSERT INTO `sys_area` VALUES ('2', '1', '0,1,', '110000', '北京市', '2', '1', '2013-05-27 08:00:00', '22', '2015-01-20 22:15:47', '', '0', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('3', '2', '0,1,2,', '110101', '东城区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', null);
INSERT INTO `sys_area` VALUES ('4', '2', '0,1,2,', '110102', '西城区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', null);
INSERT INTO `sys_area` VALUES ('5', '2', '0,1,2,', '110103', '朝阳区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', null);
INSERT INTO `sys_area` VALUES ('6', '2', '0,1,2,', '110104', '丰台区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', null);
INSERT INTO `sys_area` VALUES ('7', '2', '0,1,2,', '110105', '海淀区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', null);
INSERT INTO `sys_area` VALUES ('8', '1', '0,1,', '370000', '山东省', '2', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0', null);
INSERT INTO `sys_area` VALUES ('9', '8', '0,1,8,', '370531', '济南市', '3', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0', null);
INSERT INTO `sys_area` VALUES ('10', '9', '0,1,8,9,', '370532', '高新区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0', null);
INSERT INTO `sys_area` VALUES ('11', '9', '0,1,8,9,', '370533', '历城区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0', null);
INSERT INTO `sys_area` VALUES ('12', '9', '0,1,8,9,', '370534', '历下区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0', null);
INSERT INTO `sys_area` VALUES ('13', '9', '0,1,8,9,', '370535', '天桥区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0', null);
INSERT INTO `sys_area` VALUES ('14', '9', '0,1,8,9,', '370536', '槐荫区', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0', null);
INSERT INTO `sys_area` VALUES ('15', '1', '0,1,', '110000x', '测试导入', '2', '2,超级管理员', '2015-01-31 20:49:31', '22', '2015-01-31 20:49:31', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('16', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 20:57:38', '22', '2015-01-31 20:57:38', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('17', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 20:59:10', '22', '2015-01-31 20:59:10', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('18', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 21:00:07', '22', '2015-01-31 21:00:07', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('19', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 21:01:37', '22', '2015-01-31 21:01:37', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('20', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 21:04:52', '22', '2015-01-31 21:04:52', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('21', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 21:08:07', '22', '2015-01-31 21:08:07', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('22', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 21:09:53', '22', '2015-01-31 21:09:53', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('23', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 21:11:53', '22', '2015-01-31 21:11:53', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('24', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 21:13:31', '22', '2015-01-31 21:13:31', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('25', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 22:15:36', '22', '2015-01-31 22:15:36', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('26', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 22:16:18', '22', '2015-01-31 22:16:18', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('27', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 22:16:53', '22', '2015-01-31 22:16:53', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('28', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 22:17:20', '22', '2015-01-31 22:17:20', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('29', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 22:18:00', '22', '2015-01-31 22:18:00', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('30', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 22:19:39', '22', '2015-01-31 22:19:39', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('31', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 22:21:10', '22', '2015-01-31 22:21:10', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('32', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 22:21:37', '22', '2015-01-31 22:21:37', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('33', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-01-31 22:23:28', '22', '2015-01-31 22:23:28', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('34', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-02-02 01:27:54', '22', '2015-02-02 01:27:54', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('35', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-02-02 01:28:44', '22', '2015-02-02 01:28:44', '', '1', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('36', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-02-02 01:30:40', '22', '2015-02-02 01:30:40', '', '0', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('37', '1', '0,1,', '110000', '测试导入', '2', '2,超级管理员', '2015-02-02 01:31:00', '22', '2015-02-02 01:31:00', '', '0', 'fa fa-smile-o');
INSERT INTO `sys_area` VALUES ('38', '1', '0,1,', '10010', '湖南', '2', '2,超级管理员', '2015-10-25 11:05:06', null, '2015-10-25 11:05:06', '', '0', 'fa fa-bus');
INSERT INTO `sys_area` VALUES ('39', '38', '0,1,38,', '10010', '岳阳', '3', '2,超级管理员', '2015-10-25 11:05:42', null, '2015-10-25 11:05:42', '', '0', 'fa fa-ils');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序（升序）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '正常', '0', 'del_flag', '删除标记', '10', '1', '2013-05-27 08:00:00', '2,超级管理员', '2015-02-28 23:07:13', null, '0');
INSERT INTO `sys_dict` VALUES ('2', '删除', '1', 'del_flag', '删除标记', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('3', '显示', '1', 'show_hide', '显示/隐藏', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('4', '隐藏', '0', 'show_hide', '显示/隐藏', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('5', '是', '1', 'yes_no', '是/否', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('6', '否', '0', 'yes_no', '是/否', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('17', '国家', '1', 'sys_area_type', '区域类型', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('18', '省份、直辖市', '2', 'sys_area_type', '区域类型', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('19', '地市', '3', 'sys_area_type', '区域类型', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('20', '区县', '4', 'sys_area_type', '区域类型', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('22', '部门', '2', 'sys_office_type', '机构类型', '70', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('23', '一级', '1', 'sys_office_grade', '机构等级', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('24', '二级', '2', 'sys_office_grade', '机构等级', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('25', '三级', '3', 'sys_office_grade', '机构等级', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('26', '四级', '4', 'sys_office_grade', '机构等级', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('27', '所有数据', '1', 'sys_data_scope', '数据范围', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('28', '所在公司及以下数据', '2', 'sys_data_scope', '数据范围', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('29', '所在公司数据', '3', 'sys_data_scope', '数据范围', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('30', '所在部门及以下数据', '4', 'sys_data_scope', '数据范围', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('31', '所在部门数据', '5', 'sys_data_scope', '数据范围', '50', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('32', '仅本人数据', '8', 'sys_data_scope', '数据范围', '90', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('33', '按明细设置', '9', 'sys_data_scope', '数据范围', '100', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('34', '系统管理', '1', 'sys_user_type', '用户类型', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('35', '部门经理', '2', 'sys_user_type', '用户类型', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('36', '普通用户', '3', 'sys_user_type', '用户类型', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('62', '操作日志', '1', 'sys_log_type', '日志类型', '30', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('63', '异常日志', '2', 'sys_log_type', '日志类型', '40', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('64', '单表增删改查', 'single', 'prj_template_type', '代码模板', '10', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('65', '所有entity和dao', 'entityAndDao', 'prj_template_type', '代码模板', '20', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('66', '公司', '1', 'sys_office_type', '', '1', null, '2015-01-10 22:15:43', null, '2015-01-10 22:15:43', null, '0');
INSERT INTO `sys_dict` VALUES ('67', '等级', '1', 'score', '', '1', '2,超级管理员', '2015-12-28 22:19:24', null, '2015-12-28 22:19:24', null, '0');
INSERT INTO `sys_dict` VALUES ('68', '首页导航', '0', 'nav_type', '', '1', null, null, null, null, null, '0');
INSERT INTO `sys_dict` VALUES ('69', '底部导航', '1', 'nav_type', '', '2', null, null, null, null, null, '0');
INSERT INTO `sys_dict` VALUES ('70', '文章', '0', 'cms_cate_type', '1', '1', null, null, null, null, null, '0');
INSERT INTO `sys_dict` VALUES ('71', '图片', '1', 'cms_cate_type', '2', '2', null, null, null, null, null, '0');
INSERT INTO `sys_dict` VALUES ('72', '阅读', 'reader', 'cms_wap_type', '1', '1', null, '2016-11-17 12:58:42', null, '2016-11-17 12:58:42', null, '0');
INSERT INTO `sys_dict` VALUES ('73', '图集', 'img', 'cms_wap_type', '2', '2', null, '2016-11-17 12:58:55', null, '2016-11-17 12:58:55', null, '0');
INSERT INTO `sys_dict` VALUES ('74', '趣闻', 'quwen', 'cms_wap_type', '', '4', null, '2016-11-20 11:17:29', null, '2016-11-20 11:17:29', null, '0');
INSERT INTO `sys_dict` VALUES ('75', '活动', 'activity', 'cms_wap_type', '5', '5', null, '2016-11-20 11:17:47', null, '2016-11-20 11:17:47', null, '0');
INSERT INTO `sys_dict` VALUES ('76', '萌专题', 'subject', 'cms_wap_type', '6', '6', null, '2016-11-20 11:18:12', null, '2016-11-20 11:18:12', null, '0');
INSERT INTO `sys_dict` VALUES ('77', '视频', 'vedio', 'cms_wap_type', '7', '7', null, '2016-11-20 11:18:35', null, '2016-11-20 11:18:35', null, '0');
INSERT INTO `sys_dict` VALUES ('78', '新鲜事', 'text', 'cms_wap_type', '', '1', null, '2016-11-20 11:18:50', null, '2016-11-20 11:18:50', null, '0');
INSERT INTO `sys_dict` VALUES ('79', '房产导航', 'houseNav', 'cms_cate_type', '2', '2', null, '2016-11-20 15:47:57', null, '2016-11-20 15:47:57', null, '0');
INSERT INTO `sys_dict` VALUES ('80', '旅游导航', 'travelNav', 'cms_cate_type', '2', '2', null, '2016-11-20 16:36:45', null, '2016-11-20 16:37:29', null, '0');
INSERT INTO `sys_dict` VALUES ('81', '地铁线', '1', 'cms_wap_type', '1', '1', '1', '2016-11-23 15:23:05', null, '2016-11-23 15:23:05', null, '0');
INSERT INTO `sys_dict` VALUES ('82', '靠海边', '2', 'cms_wap_type', '2', '2', '1', '2016-11-23 15:29:16', null, '2016-11-23 15:29:16', null, '0');
INSERT INTO `sys_dict` VALUES ('83', '靠公交', '3', 'cms_wap_type', '3', '3', '1', '2016-11-23 15:32:17', null, '2016-11-23 15:32:17', null, '0');
INSERT INTO `sys_dict` VALUES ('84', '风景旅游', '1', 'cms_wap_type', '1', '1', '2', '2016-11-23 15:35:12', null, '2016-11-23 15:35:12', null, '0');
INSERT INTO `sys_dict` VALUES ('85', '古迹旅游', '2', 'cms_wap_type', '2', '2', '2', '2016-11-23 15:47:54', null, '2016-11-23 15:47:54', null, '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) DEFAULT '1' COMMENT '日志类型',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(5) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `exception` text COMMENT '异常信息',
  `description` text COMMENT '描述',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`),
  KEY `sys_log_request_uri` (`request_uri`),
  KEY `sys_log_type` (`type`),
  KEY `sys_log_create_date` (`create_date`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('75', '0', null, '2017-03-08 17:21:14', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/index', 'GET', null, '/pig-web/index', null);
INSERT INTO `sys_log` VALUES ('76', '7', null, '2017-03-08 17:21:33', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/login', 'POST', null, '/pig-web/login', null);
INSERT INTO `sys_log` VALUES ('77', '0', null, '2017-03-08 17:21:33', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/index', 'GET', null, '/pig-web/index', null);
INSERT INTO `sys_log` VALUES ('78', '6', null, '2017-03-08 17:21:36', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/role', 'GET', null, '/pig-web/role', null);
INSERT INTO `sys_log` VALUES ('79', '4', null, '2017-03-08 17:21:36', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/tag/treeselect', 'POST', null, '/pig-web/tag/treeselect', null);
INSERT INTO `sys_log` VALUES ('80', '9', null, '2017-03-08 17:21:36', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/tag/treeselect', 'POST', null, '/pig-web/tag/treeselect', null);
INSERT INTO `sys_log` VALUES ('81', '5', null, '2017-03-08 17:21:36', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/role/list', 'POST', null, '/pig-web/role/list', null);
INSERT INTO `sys_log` VALUES ('82', '9', null, '2017-03-08 17:21:36', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/menu/tree', 'POST', null, '/pig-web/menu/tree', null);
INSERT INTO `sys_log` VALUES ('83', '7', null, '2017-03-08 17:21:36', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/office/tree', 'POST', null, '/pig-web/office/tree', null);
INSERT INTO `sys_log` VALUES ('84', '9', null, '2017-03-08 17:21:37', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/office', 'GET', null, '/pig-web/office', null);
INSERT INTO `sys_log` VALUES ('85', '0', null, '2017-03-08 17:21:37', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/office/list', 'POST', null, '/pig-web/office/list', null);
INSERT INTO `sys_log` VALUES ('86', '3', null, '2017-03-08 17:21:37', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/menu', 'GET', null, '/pig-web/menu', null);
INSERT INTO `sys_log` VALUES ('87', '0', null, '2017-03-08 17:21:37', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/menu/list', 'POST', null, '/pig-web/menu/list', null);
INSERT INTO `sys_log` VALUES ('88', '1', null, '2017-03-08 17:21:38', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/area', 'GET', null, '/pig-web/area', null);
INSERT INTO `sys_log` VALUES ('89', '4', null, '2017-03-08 17:21:38', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/area/list', 'POST', null, '/pig-web/area/list', null);
INSERT INTO `sys_log` VALUES ('90', '8', null, '2017-03-08 17:21:38', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/dict', 'GET', null, '/pig-web/dict', null);
INSERT INTO `sys_log` VALUES ('91', '5', null, '2017-03-08 17:21:38', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/dict/list', 'POST', null, '/pig-web/dict/list', null);
INSERT INTO `sys_log` VALUES ('92', '1', null, '2017-03-08 17:21:39', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/syslog', 'GET', null, '/pig-web/syslog', null);
INSERT INTO `sys_log` VALUES ('93', '0', null, '2017-03-08 17:21:39', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36', '/pig-web/syslog/list', 'POST', null, '/pig-web/syslog/list', null);

-- ----------------------------
-- Table structure for sys_office
-- ----------------------------
DROP TABLE IF EXISTS `sys_office`;
CREATE TABLE `sys_office` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` bigint(20) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `area_id` bigint(20) NOT NULL COMMENT '归属区域',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `name` varchar(100) NOT NULL COMMENT '机构名称',
  `type` char(1) DEFAULT NULL COMMENT '机构类型',
  `grade` char(1) DEFAULT NULL COMMENT '机构等级',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `zip_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `master` varchar(100) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `fax` varchar(200) DEFAULT NULL COMMENT '传真',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `icon` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_office_parent_id` (`parent_id`),
  KEY `sys_office_parent_ids` (`parent_ids`(255)),
  KEY `sys_office_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='机构表';

-- ----------------------------
-- Records of sys_office
-- ----------------------------
INSERT INTO `sys_office` VALUES ('1', '0', '0,', '2', '100000', '北京市总公司', '1', '1', '', null, '', '', '', '', '1', '2013-05-27 08:00:00', '2,超级管理员', '2015-02-28 20:49:57', '', '0', 'fa fa-bicycle');
INSERT INTO `sys_office` VALUES ('2', '1', '0,1,', '2', '100001', '公司领导', '2', '1', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', null);
INSERT INTO `sys_office` VALUES ('3', '1', '0,1,', '2', '100002', '人力部', '2', '1', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', null);
INSERT INTO `sys_office` VALUES ('4', '1', '0,1,', '2', '100003', '市场部', '2', '1', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', null);
INSERT INTO `sys_office` VALUES ('5', '1', '0,1,', '2', '100004', '技术部', '2', '4', '', null, '', '', '', '', '1', '2013-05-27 08:00:00', '22', '2015-01-24 16:39:03', '', '0', '');
INSERT INTO `sys_office` VALUES ('6', '1', '0,1,', '2', '100005', '研发部', '2', '1', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', null);
INSERT INTO `sys_office` VALUES ('7', '1', '0,1,', '3', '200000', '山东省分公司', '1', '2', '', '', '', '', '', '', '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', '', '0', null);
INSERT INTO `sys_office` VALUES ('8', '7', '0,1,7,', '8', '200001', '公司领导', '2', '2', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('9', '7', '0,1,7,', '8', '200002', '综合部', '2', '2', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('10', '7', '0,1,7,', '8', '200003', '市场部', '2', '2', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('11', '7', '0,1,7,', '8', '200004', '技术部', '2', '2', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('12', '7', '0,1,7,', '9', '201000', '济南市分公司', '1', '3', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('13', '12', '0,1,7,12,', '9', '201001', '公司领导', '2', '3', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('14', '12', '0,1,7,12,', '9', '201002', '综合部', '2', '3', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('15', '12', '0,1,7,12,', '9', '201003', '市场部', '2', '3', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('16', '12', '0,1,7,12,', '9', '201004', '技术部', '2', '3', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('17', '12', '0,1,7,12,', '11', '201010', '济南市历城区分公司', '1', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('18', '17', '0,1,7,12,17,', '11', '201011', '公司领导', '2', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('19', '17', '0,1,7,12,17,', '11', '201012', '综合部', '2', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('20', '17', '0,1,7,12,17,', '11', '201013', '市场部', '2', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('21', '17', '0,1,7,12,17,', '11', '201014', '技术部', '2', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('22', '12', '0,1,7,12,', '12', '201020', '济南市历下区分公司', '1', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('23', '22', '0,1,7,12,22,', '12', '201021', '公司领导', '2', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('24', '22', '0,1,7,12,22,', '12', '201022', '综合部', '2', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('25', '22', '0,1,7,12,22,', '12', '201023', '市场部', '2', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('26', '22', '0,1,7,12,22,', '12', '201024', '技术部', '2', '4', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '2', '2014-11-23 22:00:25', null, '0', null);
INSERT INTO `sys_office` VALUES ('27', '5', '0,1,5,', '2', null, '技术1部门', '2', null, '', null, '', '', '', '', null, '2015-01-11 15:19:53', null, '2015-01-11 15:19:53', '', '0', null);
INSERT INTO `sys_office` VALUES ('28', '0', '0,', '1', null, '测试公司名字一定要长', '1', null, '', null, '', '', '', '', null, '2015-01-11 23:48:22', null, '2015-01-12 21:46:32', '', '1', null);
INSERT INTO `sys_office` VALUES ('29', '28', '0,28,', '1', null, '测试部门1', '2', null, '', null, '', '', '', '', null, '2015-01-11 23:48:35', null, '2015-01-11 23:48:35', '', '1', null);
INSERT INTO `sys_office` VALUES ('30', '10', '0,1,7,10,', '1', null, '市场子部门', '2', null, '', null, '', '', '', '', null, '2015-01-13 22:56:14', null, '2015-01-13 22:56:14', '', '0', null);
INSERT INTO `sys_office` VALUES ('35', '0', '0,', '1', null, 'fsdfsdf', '2', '1', '', null, '', '', '', '', null, '2015-01-14 23:13:43', null, '2015-01-14 23:13:43', '', '0', null);
INSERT INTO `sys_office` VALUES ('36', '35', '0,35,', '1', null, '2222', '2', '2', '', null, '', '', '', '', '2', '2015-01-18 20:29:53', '2', '2015-01-18 20:34:08', '', '0', null);
INSERT INTO `sys_office` VALUES ('37', '1', '0,1,', '2', null, '测测', '2', '2', '', null, '', '', '', '', '22', '2015-01-24 15:19:09', null, '2015-01-24 15:19:09', '', '0', '');
INSERT INTO `sys_office` VALUES ('38', '5', '0,1,5,', '2', null, 'sdsd', '2', '3', '', null, '', '', '', '', '22', '2015-01-24 17:08:50', null, '2015-01-24 17:08:50', '', '0', '');
INSERT INTO `sys_office` VALUES ('39', '35', '0,35,', '1', null, 'sssddd', '2', '2', '', null, '', '', '', '', '2', '2015-01-24 17:35:09', null, '2015-01-24 17:35:09', '', '0', '');
INSERT INTO `sys_office` VALUES ('40', '1', '0,1,', '2', null, '测试', '2', '2', '', null, '', '', '', '', '22', '2015-01-25 10:23:15', null, '2015-01-25 10:23:15', '', '1', '');
INSERT INTO `sys_office` VALUES ('41', '1', '0,1,', '2', null, 'aaaa', '2', '2', '', null, '', '', '', '', '22', '2015-01-25 21:34:43', null, '2015-01-25 21:34:43', '', '0', '');
INSERT INTO `sys_office` VALUES ('42', '1', '0,1,', '2', null, 'aaaa', '2', '2', '', null, '', '', '', '', '22', '2015-01-25 21:37:13', null, '2015-01-25 21:37:13', '', '1', '');
INSERT INTO `sys_office` VALUES ('43', '1', '0,1,', '2', null, 'ffffddd', '2', '2', '', null, '', '', '', '', '22', '2015-01-25 21:37:48', null, '2015-01-25 21:37:48', '', '0', '');
INSERT INTO `sys_office` VALUES ('45', '1', '0,1,', '2', null, '测试自动赋权', '2', '2', '', null, '', '', '', '', '22', '2015-01-27 20:02:50', null, '2015-01-27 20:02:50', '', '0', '');
INSERT INTO `sys_office` VALUES ('46', '1', '0,1,', '2', null, 'cc22', '2', '2', '', null, '', '', '', '', '22', '2015-01-27 20:19:45', null, '2015-01-27 20:19:45', '', '0', '');
INSERT INTO `sys_office` VALUES ('47', '0', '0,', '1', null, 'sss', '1', '1', '', null, '', '', '', '', '2', '2015-01-28 21:46:00', null, '2015-01-28 21:46:00', '', '1', '');
INSERT INTO `sys_office` VALUES ('48', '1', '0,1,', '1', null, 'dd', '1', '2', '', null, '', '', '', '', '22', '2015-01-28 22:33:04', null, '2015-01-28 22:33:04', '', '0', '');
INSERT INTO `sys_office` VALUES ('49', '0', '0,', '1', null, 'xcxzcxc', '2', '1', '', null, '', '', '', '', '22', '2015-01-28 22:55:37', null, '2015-01-28 22:55:37', '', '0', '');
INSERT INTO `sys_office` VALUES ('50', '0', '0,', '1', null, '北京zscat科技有限公司', '1', '1', '', null, 'zscat', '', '', '', '2,超级管理员', '2016-12-21 16:34:37', null, '2016-12-21 16:34:37', '', '0', 'fa fa-angellist');
INSERT INTO `sys_office` VALUES ('51', '50', '0,50,', '1', null, '技术部', '2', '2', '', null, 'zscatDep', '', '', '', '2,超级管理员', '2016-12-21 16:35:06', null, '2016-12-21 16:35:06', '', '0', 'fa fa-cc-mastercard');
INSERT INTO `sys_office` VALUES ('52', '0', '0,50,0,', '1', null, '北京xxx科技有限公司', '1', '3', '', null, 'xxx', '', '', '', '2,超级管理员', '2016-12-21 16:35:45', null, '2016-12-21 16:35:45', '', '0', 'fa fa-copyright');
INSERT INTO `sys_office` VALUES ('53', '52', '0,50,0,52,', '1', null, '人事部', '2', '4', '', null, '', '', '', '', '2,超级管理员', '2016-12-21 16:36:06', null, '2016-12-21 16:36:06', '', '0', 'fa fa-ioxhost');
INSERT INTO `sys_office` VALUES ('54', '50', '0,50,', '1', null, '人事部', '2', '2', '', null, '人事', '', '', '', '2,超级管理员', '2016-12-21 16:36:24', null, '2016-12-21 16:36:24', '', '0', '');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '资源名称',
  `common` char(1) DEFAULT '0' COMMENT '是否是公共资源(0.不是 1.是)',
  `icon` varchar(64) DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT '1' COMMENT '排序号',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级id',
  `type` char(1) DEFAULT '0' COMMENT '类型(0.菜单 1.按钮)',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` char(1) DEFAULT '0' COMMENT '状态(0.正常 1.禁用)',
  `parent_ids` varchar(2000) DEFAULT NULL COMMENT '父级集合',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  `permission_str` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=256 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '菜单配置', '0', 'fa fa-list', '5', '188', '0', 'menu', '', '0', '0,188,', null, '2015-03-11 23:12:27', null, '2,超级管理员', '0', null);
INSERT INTO `sys_resource` VALUES ('181', '区域管理', '0', 'fa fa-globe', '6', '188', '0', 'area', '', '0', '0,188,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('188', '系统管理', '0', 'fa fa-cogs', '1', '0', '0', '', '', '0', '0,', null, '2015-03-12 23:57:18', null, '2,超级管理员', '0', null);
INSERT INTO `sys_resource` VALUES ('190', '字典管理', '0', 'fa fa-calculator', '7', '188', '0', 'dict', '', '0', '0,188,', null, '2015-03-11 23:12:41', null, '2,超级管理员', '0', null);
INSERT INTO `sys_resource` VALUES ('192', '机构管理', '0', 'fa fa-sitemap', '4', '188', '0', 'office', '', '0', '0,188,', null, '2015-03-11 23:08:59', null, '2,超级管理员', '0', null);
INSERT INTO `sys_resource` VALUES ('193', '用户管理', '0', 'fa fa-calculator', '1', '188', '0', 'sysuser', '', '0', '0,188,', null, '2016-11-25 22:04:42', null, '2,超级管理员', '0', null);
INSERT INTO `sys_resource` VALUES ('194', '角色管理', '0', 'fa fa-graduation-cap', '2', '188', '0', 'role', '', '0', '0,188,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('195', '日志查询', '0', 'fa fa-copy', '8', '188', '0', 'syslog', '', '0', '0,188,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('203', '搜索按钮', '0', 'fa fa-angellist', '1', '181', '1', 'sys:area:find', '这是一个按钮级别的示例，页面为添加，请添加@if(auth.hasPermission(\"sys:area:find\")){}测试', '0', '0,188,181,', '2015-01-20 20:50:16', '2015-01-20 20:57:38', '22', '22', '0', null);
INSERT INTO `sys_resource` VALUES ('204', '系统监控', '0', 'fa fa-binoculars', '6', '0', '0', '', '', '0', '0,', '2015-03-03 20:11:10', '2015-03-11 23:12:56', '2,超级管理员', '2,超级管理员', '0', null);
INSERT INTO `sys_resource` VALUES ('205', 'Ehcache监控', '0', 'fa fa-crosshairs', '1', '204', '0', 'monitor/ehcache', '', '0', '0,204,', '2015-03-03 20:11:19', '2015-03-11 23:15:52', '2,超级管理员', '2,超级管理员', '0', null);
INSERT INTO `sys_resource` VALUES ('206', 'jvm监控', '0', 'fa fa-flash', '1', '204', '0', 'monitor/jvm', '', '0', '0,204,', '2015-03-08 11:17:00', '2015-03-11 23:20:19', '2,超级管理员', '2,超级管理员', '0', null);
INSERT INTO `sys_resource` VALUES ('207', '执行sql', '0', 'fa fa-ge', '1', '204', '0', 'monitor/db/sql', '', '0', '0,204,', '2015-03-09 21:07:49', '2015-03-11 23:18:39', '2,超级管理员', '2,超级管理员', '0', null);
INSERT INTO `sys_resource` VALUES ('208', '数据库监控', '0', 'fa fa-github-alt', '1', '204', '0', 'monitor/db/druid', '', '0', '0,204,', '2015-03-10 21:11:20', '2015-03-11 23:19:56', '2,超级管理员', '2,超级管理员', '0', null);
INSERT INTO `sys_resource` VALUES ('214', '博客管理', '0', 'fa fa-bell-slash', '1', '0', '0', '', '', '0', '0,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('215', '博客类型', '0', '', '1', '214', '0', 'blogType', '', '0', '0,214,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('216', '文章管理', '0', '', '1', '214', '0', 'blog', '', '0', '0,214,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('228', '内容管理', '0', '', '1', '0', '0', '', '', '0', '0,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('229', '文章管理', '0', '', '1', '228', '0', 'cmsArticle', '', '0', '0,228,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('230', '网站链接', '0', '', '4', '228', '0', 'cmsLink', '', '0', '0,228,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('231', '增加', '0', 'fa fa-area-chart', '1', '230', '1', 'cms:cmsLink:add', '', '0', '0,228,230,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('232', '官网管理', '0', 'fa fa-youtube', '1', '0', '0', '', '', '0', '0,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('233', '产品管理', '0', 'fa fa-area-chart', '1', '232', '0', 'product', '', '0', '0,232,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('234', '产品类型', '0', 'fa fa-bicycle', '2', '232', '0', 'productType', '', '0', '0,232,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('235', '官网信息', '0', 'fa fa-cc-mastercard', '4', '232', '0', 'gwInfo', '', '0', '0,232,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('236', '官网导航', '0', 'fa fa-arrows-alt', '1', '232', '0', 'nav', '', '0', '0,232,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('237', '编辑', '0', '', '1', '233', '1', 'gw:product:edit', '', '0', '0,232,233,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('238', '删除', '0', '', '1', '233', '1', 'gw:product:delete', '', '0', '0,232,233,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('239', '删除', '0', '', '1', '236', '1', 'gw:nav:delete', '', '0', '0,232,236,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('242', '文章管理', '0', '', '1', '214', '0', 'blog', '', '0', '0,214,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('244', '站点管理', '0', '', '2', '228', '0', 'cmsSite', '', '0', '0,228,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('245', '栏目管理', '0', '', '3', '228', '0', 'cmsCategory', '', '0', '0,228,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('246', '图片管理', '0', '', '7', '228', '0', 'cmsImg', '', '0', '0,228,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('247', '模板管理', '0', '', '2', '214', '0', 'blogTemplate', '', '0', '0,214,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('249', '留言管理', '0', '', '3', '214', '0', 'comment', '', '0', '0,214,', '2016-11-25 22:01:00', '2016-11-25 22:01:00', null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('250', '友好链接', '0', '', '4', '214', '0', 'link', '', '0', '0,214,', '2016-11-25 22:02:09', '2016-11-25 22:02:09', null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('251', 'database', '0', '', '5', '204', '0', 'database', '', '0', '0,204,', '2016-11-29 11:18:04', '2016-11-29 11:18:04', null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('253', '图表展示', '0', 'fa fa-area-chart', '9', '188', '0', 'chart', '', '0', '0,188,', null, null, null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('254', 'luence管理', '0', 'fa fa-cc', '1', '188', '0', 'luence', '', '0', '0,188,', '2017-03-02 11:02:33', '2017-03-02 11:03:06', null, null, '0', null);
INSERT INTO `sys_resource` VALUES ('255', 'redis管理', '0', 'fa fa-cc-mastercard', '1', '188', '0', 'redis', '', '0', '0,188,', '2017-03-02 11:02:57', '2017-03-02 11:02:57', null, null, '0', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `office_id` bigint(20) DEFAULT NULL COMMENT '归属机构',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `data_scope` char(1) DEFAULT NULL COMMENT '数据范围',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_role_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('21', '49', 'test', '8', '2,超级管理员', '2016-03-08 22:14:31', null, '2016-03-08 22:14:31', null, '0');
INSERT INTO `sys_role` VALUES ('22', '49', 'zhuan', '5', '2,超级管理员', '2016-03-08 22:18:25', '2,超级管理员', '2016-03-08 22:18:46', null, '0');
INSERT INTO `sys_role` VALUES ('23', '50', '数据测试', '9', '2,超级管理员', '2016-12-21 16:38:08', null, '2016-12-21 16:38:08', null, '0');
INSERT INTO `sys_role` VALUES ('24', '50', '所有数据', '1', '2,超级管理员', '2016-12-21 16:42:36', null, '2016-12-21 16:42:36', null, '0');
INSERT INTO `sys_role` VALUES ('25', '50', '所在公司及以下数据', '2', '2,超级管理员', '2016-12-21 16:43:26', null, '2016-12-21 16:43:26', null, '0');
INSERT INTO `sys_role` VALUES ('26', '50', '所在公司数据', '3', '2,超级管理员', '2016-12-21 16:43:53', '2,超级管理员', '2016-12-21 16:45:11', null, '0');
INSERT INTO `sys_role` VALUES ('27', '50', '所在部门及以下数据', '4', '2,超级管理员', '2016-12-21 16:44:19', '2,超级管理员', '2016-12-21 16:59:51', null, '0');
INSERT INTO `sys_role` VALUES ('28', '50', '所在部门数据', '5', '2,超级管理员', '2016-12-21 16:44:40', null, '2016-12-21 16:44:40', null, '0');
INSERT INTO `sys_role` VALUES ('29', '50', '本人数据', '8', '2,超级管理员', '2016-12-21 16:44:58', null, '2016-12-21 16:44:58', null, '0');
INSERT INTO `sys_role` VALUES ('30', null, 'shen', null, null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for sys_role_office
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_office`;
CREATE TABLE `sys_role_office` (
  `role_id` bigint(20) NOT NULL COMMENT '角色编号',
  `office_id` bigint(20) NOT NULL COMMENT '机构编号',
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-机构';

-- ----------------------------
-- Records of sys_role_office
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1538 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('1334', '21', '188', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1335', '21', '1', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1336', '21', '190', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1337', '21', '195', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1338', '21', '189', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1339', '21', '181', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1340', '21', '203', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1341', '21', '192', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1342', '21', '193', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1343', '21', '194', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1344', '21', '204', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1345', '21', '205', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1346', '21', '206', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1347', '21', '207', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1348', '21', '208', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1469', '22', '188', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1470', '22', '1', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1471', '22', '181', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1472', '22', '203', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1473', '22', '190', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1474', '22', '192', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1475', '22', '193', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1476', '22', '195', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1477', '22', '194', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1478', '22', '214', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1479', '22', '215', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1480', '22', '228', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1481', '22', '229', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1482', '22', '244', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1483', '22', '245', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1484', '22', '230', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1485', '22', '231', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1486', '22', '232', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1487', '22', '233', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1488', '22', '236', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1489', '22', '234', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1490', '22', '235', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1491', '22', '204', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1492', '22', '205', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1493', '22', '206', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1494', '22', '207', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1495', '22', '208', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1496', '23', '214', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1497', '23', '215', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1498', '23', '242', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1499', '23', '247', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1500', '23', '249', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1501', '23', '250', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1502', '24', '214', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1503', '24', '215', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1504', '24', '242', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1505', '24', '247', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1506', '24', '249', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1507', '24', '250', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1508', '25', '214', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1509', '25', '215', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1510', '25', '242', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1511', '25', '247', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1512', '25', '249', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1513', '25', '250', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1514', '28', '214', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1515', '28', '215', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1516', '28', '242', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1517', '28', '247', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1518', '28', '249', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1519', '28', '250', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1520', '29', '214', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1521', '29', '215', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1522', '29', '242', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1523', '29', '247', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1524', '29', '249', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1525', '29', '250', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1526', '26', '214', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1527', '26', '215', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1528', '26', '242', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1529', '26', '247', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1530', '26', '249', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1531', '26', '250', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1532', '27', '214', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1533', '27', '215', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1534', '27', '242', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1535', '27', '247', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1536', '27', '249', null, null, null, null, '0');
INSERT INTO `sys_role_resource` VALUES ('1537', '27', '250', null, null, null, null, '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `company_id` bigint(20) DEFAULT NULL COMMENT '归属公司',
  `office_id` bigint(20) DEFAULT NULL COMMENT '归属部门',
  `username` varchar(100) DEFAULT NULL COMMENT '登录名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `no` varchar(100) DEFAULT NULL COMMENT '工号',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(200) DEFAULT NULL COMMENT '手机',
  `user_type` char(1) DEFAULT '0' COMMENT '用户类型(0.普通 1.系统超级管理员)',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  `status` char(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `sys_user_office_id` (`office_id`),
  KEY `sys_user_company_id` (`company_id`),
  KEY `sys_user_update_date` (`update_date`),
  KEY `sys_user_del_flag` (`del_flag`),
  KEY `sys_user_login_name` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('27', '1', '1', 'admin', '86f3059b228c8acf99e69734b6bb32cc', '', 'admin', '', '', '', '1', '0:0:0:0:0:0:0:1', '2017-03-08 20:22:31', '2,超级管理员', '2015-11-06 14:01:01', '27,shen', '2017-03-08 20:22:31', '', '0', '0');
INSERT INTO `sys_user` VALUES ('33', '50', '51', 'geren', '8274aeaeadd1a6e2b4097cb03fa3fd93', '', '个人', '', '', '', '0', null, null, '2,超级管理员', '2016-12-21 16:38:48', null, '2016-12-21 16:38:48', '', '0', '0');
INSERT INTO `sys_user` VALUES ('34', '50', '50', 'bumen', '94a33792d99db0b5d42732477e08d8a0', '', '部门', '', '', '', '0', null, null, '2,超级管理员', '2016-12-21 16:39:20', null, '2016-12-21 16:39:20', '', '1', '0');
INSERT INTO `sys_user` VALUES ('35', '50', '50', '111111', '2e030f4ec30f015da3ffa0cdda972119', '', '本人', '', '', '', '1', '0:0:0:0:0:0:0:1', '2017-03-28 16:39:01', '2,超级管理员', '2016-12-21 16:50:59', '35,本人', '2017-03-28 16:39:01', '', '0', '0');
INSERT INTO `sys_user` VALUES ('36', '50', '50', '222222', 'd76b7a7eea5c7d4a051eed8f61c03986', '', '所在部门数据', '', '', '', '0', '0:0:0:0:0:0:0:1', '2016-12-21 17:26:37', '2,超级管理员', '2016-12-21 16:51:31', '36,所在部门数据', '2016-12-21 17:26:37', '', '0', '0');
INSERT INTO `sys_user` VALUES ('37', '50', '50', '333333', '9e2407837a557fe8f8116667084471c4', '', '所在部门及以下数据', '', '', '', '0', '0:0:0:0:0:0:0:1', '2016-12-21 16:58:13', '2,超级管理员', '2016-12-21 16:51:58', '37,所在部门及以下数据', '2016-12-21 16:58:13', '', '0', '0');
INSERT INTO `sys_user` VALUES ('39', '50', '50', '555555', '', '', '所在公司及以下数据', '22@qq.com', '', '13146587711', '0', '0:0:0:0:0:0:0:1', '2016-12-21 17:03:38', '2,超级管理员', '2016-12-21 16:52:57', '39,所在公司及以下数据', '2016-12-21 17:03:38', '', '0', '0');
INSERT INTO `sys_user` VALUES ('41', null, null, '111', '', null, '111', '111@qq.com', null, '13146587722', '0', null, null, null, null, null, null, null, '0', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('94', '21', '32', null, null, null, null, '0');
INSERT INTO `sys_user_role` VALUES ('95', '21', '33', null, null, null, null, '0');
INSERT INTO `sys_user_role` VALUES ('96', '21', '31', null, null, null, null, '0');
INSERT INTO `sys_user_role` VALUES ('97', '21', '39', null, null, null, null, '0');

-- ----------------------------
-- Table structure for tt_goods
-- ----------------------------
DROP TABLE IF EXISTS `tt_goods`;
CREATE TABLE `tt_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `remark` longtext,
  `summary` longtext,
  `clickhit` int(255) DEFAULT '0' COMMENT '0',
  `typeid` bigint(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `typename` varchar(255) DEFAULT NULL,
  `type` bigint(255) DEFAULT NULL,
  `orderno` varchar(255) DEFAULT NULL,
  `prices` varchar(255) DEFAULT NULL,
  `imgmore` varchar(1000) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `delflag` char(1) DEFAULT '0' COMMENT '删除标记(0活null 正常 1,删除)',
  `sellhit` int(11) DEFAULT '0' COMMENT '销售量',
  `iscom` int(255) DEFAULT NULL COMMENT '1推荐，2不推荐',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tt_goods
-- ----------------------------
INSERT INTO `tt_goods` VALUES ('33', 'zscat分布式框架', 'spring,springmvc ,通用mapper, dubbo,zookeep', '<p style=\"margin-top: 1.12em; margin-bottom: 1.12em; margin-left: 42pt; padding: 0px; text-indent: 21pt;\">开源分布式框架zsCat 559182393</p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; margin-left: 42pt; padding: 0px; text-indent: 21pt;\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2541510480/TB2nOAEpXXXXXXOXFXXXXXXXXXX_!!2541510480.png\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2541510480/TB2MmkOpXXXXXcBXpXXXXXXXXXX_!!2541510480.png\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2541510480/TB2Qwi2oVXXXXcCXpXXXXXXXXXX_!!2541510480.png\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2541510480/TB2FLC5oVXXXXb.XpXXXXXXXXXX_!!2541510480.png\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2541510480/TB2WwzyoVXXXXblXXXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"554\" height=\"314\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2541510480/TB2R9LyoVXXXXbcXXXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"553\" height=\"415\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2541510480/TB2crHzoVXXXXa0XXXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"553\" height=\"290\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2541510480/TB2nsu2oVXXXXcFXpXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"553\" height=\"232\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2541510480/TB283y3oVXXXXcAXpXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"554\" height=\"281\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2541510480/TB2hEndoVXXXXaeXpXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"554\" height=\"317\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2541510480/TB2hffmoVXXXXc3XXXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"554\" height=\"396\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2541510480/TB2VF11oVXXXXcvXpXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"554\" height=\"93\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2541510480/TB2oSHboVXXXXaEXpXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"554\" height=\"102\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2541510480/TB2eEPXoVXXXXaYXpXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"554\" height=\"289\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2541510480/TB2k7bxoVXXXXa.XXXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"1362\" height=\"582\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2541510480/TB2SorfoVXXXXX0XpXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"1364\" height=\"610\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2541510480/TB2tYK6oVXXXXb2XpXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"1364\" height=\"580\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2541510480/TB23U5ZoVXXXXcAXpXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"1364\" height=\"580\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2541510480/TB2mDrnoVXXXXcFXXXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"1361\" height=\"696\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2541510480/TB2RfraoVXXXXarXpXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"1364\" height=\"699\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2541510480/TB2qwjgoVXXXXX5XpXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"1360\" height=\"586\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2541510480/TB2uvYloVXXXXXXXpXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"1362\" height=\"582\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2541510480/TB2wCvsoVXXXXbWXXXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"1364\" height=\"610\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2541510480/TB2xd2noVXXXXcOXXXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"1363\" height=\"592\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2541510480/TB27AWSoVXXXXX5XFXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"1364\" height=\"580\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2541510480/TB2dKiWoVXXXXXcXFXXXXXXXXXX_!!2541510480.png\" class=\"\" width=\"1356\" height=\"585\" style=\"border: 0px; vertical-align: top;\"/></p><h5 style=\"margin: 0px 0px 0px 10px; padding: 0px; font-size: 15px; height: 22px;\">邻家好货</h5><p><br/></p>,', '开源分布式框架zsCat 559182393邻家好货', '186', '11', 'zscat.png', '框架', '246', null, '50', 'https://img.alicdn.com/imgextra/i1/2541510480/TB2nOAEpXXXXXXOXFXXXXXXXXXX_!!2541510480.png,https://img.alicdn.com/imgextra/i1/2541510480/TB2MmkOpXXXXXcBXpXXXXXXXXXX_!!2541510480.png,https://img.alicdn.com/imgextra/i1/2541510480/TB2Qwi2oVXXXXcCXpXXXXXXXXXX_!!2541510480.png,', '2016-12-23 12:07:18', '0', '0', null);
INSERT INTO `tt_goods` VALUES ('34', 'zscat商城', 'mybatisplus ,ssm,dubbo', '<p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\">qq &nbsp;951449465</p><p>dubbo 权限设计，spring，springmvc ，mybatis，beetl &nbsp;sso &nbsp;solr搜索引擎 &nbsp; &nbsp; &nbsp; &nbsp;演示地址 &nbsp;http://zscat.carp.mopaasapp.com/login http://shop.zscat&nbsp;</p><p>开源分布式框架zsCat 559182393</p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; margin-left: 42pt; padding: 0px; text-indent: 21pt;\"><br/></p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; margin-left: 42pt; padding: 0px; text-indent: 21pt;\">开源分布式框架zsCat 559182393</p><p>&nbsp;1.技术关键字&nbsp;如&nbsp;redis、dubbo&nbsp;等<br/>2.服务关键字&nbsp;如&nbsp;商品，商城，电商等<br/>3.关联关键字&nbsp;&nbsp;如分布式，购物网，外包，商城私活<br/>等&nbsp;</p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\"><span style=\"font-family: 宋体; font-size: 10.5pt;\">zsCat&nbsp;<span style=\"font-family:宋体\">多用户 多商家模式商城</span></span><span style=\"font-family: 宋体; font-size: 10.5pt;\"></span></p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\"><span style=\"font-family: 宋体; font-size: 10.5pt;\">java shiro Spring springmvc &nbsp;mybatis mybatis-plus &nbsp;redis rabbitmq</span><span style=\"font-size: 10.5pt; line-height: 1.5; font-family: 宋体;\"><span style=\"font-family:宋体\">将服务提出来，预留</span></span><span style=\"font-size: 10.5pt; line-height: 1.5; font-family: 宋体;\">dubbo&nbsp;<span style=\"font-family:宋体\">分布式服务结构</span></span></p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\"><span style=\"font-size: 10.5pt; line-height: 1.5; font-family: 宋体;\"><span style=\"font-family:宋体\"></span></span></p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\"><span style=\"font-size: 10.5pt; line-height: 1.5; font-family: 宋体;\"><span style=\"font-family:宋体\"></span></span></p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\"><span style=\"font-size: 10.5pt; line-height: 1.5; font-family: 宋体;\"><span style=\"font-family:宋体\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2541510480/TB2CnxvXZwb61Bjy0FfXXXvlpXa_!!2541510480.png\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2541510480/TB27jdzX_ga61BjSspiXXXUSXXa_!!2541510480.png\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2541510480/TB2GDtvXZwb61Bjy0FfXXXvlpXa_!!2541510480.png\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2541510480/TB28X4yXZIa61Bjy0FiXXc1XpXa_!!2541510480.png\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2541510480/TB2x3JAXZga61Bjy1XaXXafzVXa_!!2541510480.png\" class=\"\" width=\"683\" height=\"384\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2541510480/TB2zHxxXZsX61Bjy1XdXXa0aFXa_!!2541510480.png\" class=\"\" width=\"222\" height=\"623\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2541510480/TB2uzlyX_ga61BjSspfXXadSpXa_!!2541510480.png\" class=\"\" width=\"683\" height=\"384\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2541510480/TB2f3FAXZga61Bjy1XaXXafzVXa_!!2541510480.png\" class=\"\" width=\"683\" height=\"384\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2541510480/TB2QENyX4IX61BjSsplXXazrpXa_!!2541510480.png\" class=\"\" width=\"683\" height=\"384\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2541510480/TB2btdzX_gX61BjSspmXXaFcFXa_!!2541510480.png\" class=\"\" width=\"683\" height=\"384\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2541510480/TB2YwuqX4Ab61BjSZFBXXc9pFXa_!!2541510480.png\" class=\"\" width=\"1366\" height=\"768\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2541510480/TB2TF8xX4oa61Bjy0FaXXcHwpXa_!!2541510480.png\" class=\"\" width=\"1366\" height=\"768\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2541510480/TB2VUesX3Uc61BjSZFmXXbJHFXa_!!2541510480.png\" class=\"\" width=\"683\" height=\"384\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2541510480/TB2CAOrX4wb61BjSZFlXXbuoVXa_!!2541510480.png\" class=\"\" width=\"683\" height=\"384\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2541510480/TB2zUasX3Uc61BjSZFmXXbJHFXa_!!2541510480.png\" class=\"\" width=\"683\" height=\"384\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2541510480/TB2q01qXZkd61BjSZPhXXcb9VXa_!!2541510480.png\" class=\"\" width=\"683\" height=\"384\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2541510480/TB2mfurX9Zb61BjSZPfXXaU.pXa_!!2541510480.png\" class=\"\" width=\"683\" height=\"384\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2541510480/TB27JapX37c61BjSZFKXXb6hFXa_!!2541510480.png\" class=\"\" width=\"683\" height=\"384\" style=\"border: 0px; vertical-align: top;\"/></span></span></p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\"><span style=\"font-family: 宋体; font-size: 10.5pt;\"></span></p><h5 style=\"margin: 0px 0px 0px 10px; padding: 0px; font-size: 15px; white-space: normal; widows: auto; background-color: rgb(255, 255, 255);\">看了该宝贝的人还看了</h5><ul style=\"margin: 0px; padding: 0px; list-style: none;\"><li></li></ul><p><br/></p>,', 'qq  951449465dubbo 权限设计，spring，springmvc ，mybatis，beetl  sso  solr搜索引擎        演示地址  http://zscat.carp.mopaasapp.com/login http://shop.zscat 开源分布式框架zsCat 55', '148', '11', 'shop.png', '框架', '238', null, '200', 'https://img.alicdn.com/imgextra/i1/2541510480/TB2CnxvXZwb61Bjy0FfXXXvlpXa_!!2541510480.png,https://img.alicdn.com/imgextra/i4/2541510480/TB27jdzX_ga61BjSspiXXXUSXXa_!!2541510480.png,https://img.alicdn.com/imgextra/i1/2541510480/TB2GDtvXZwb61Bjy0FfXXXvlpXa_!!2541510480.png,', '2016-12-23 12:54:02', '0', '0', null);
INSERT INTO `tt_goods` VALUES ('35', 'zsShop商城', 'spring,springmvc ,通用mapper, dubbo,zookeep', '<p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\"><span style=\"color: rgb(108, 108, 108); font-size: 12px; line-height: 18px;\">spring，springmvc ，mybatis，通用mapping ，redis beetl &nbsp;</span><span style=\"line-height: 1.5;\">AmazeUI-2.4.2 &nbsp;jquery</span></p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\"><span style=\"line-height: 1.5;\"></span>后台权限管理 精确到按钮级别 &nbsp;菜单配置 字典管理 机构管理 用户 &nbsp;角色管理 &nbsp;商品管理 &nbsp;类别管理</p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\">订单管理 &nbsp; 购物车 &nbsp;广告 &nbsp;文章管理等</p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\">前台 首页菜单 购物详情 &nbsp;个人中心</p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\">树形组件 &nbsp;下拉组件 &nbsp;选择树组件 等等</p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\"><span style=\"color: rgb(108, 108, 108); font-size: 12px; line-height: 18px;\"></span></p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\"><br/></p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\">登录后 顶部显示后台管理 ，可以查看</p><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\"><span style=\"font-size: 12px;\"><a target=\"_blank\" style=\"color: rgb(51, 102, 204); outline: 0px;\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2541510480/TB2tvhtbNmJ.eBjy0FhXXbBdFXa_!!2541510480.png\" style=\"border: 0px; vertical-align: top; height: 600px; width: 600px; float: none; margin: 0px;\"/></a></span><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2541510480/TB2wkprbSuJ.eBjy0FgXXXBBXXa_!!2541510480.jpg\" style=\"border: 0px; vertical-align: top; height: 500px; width: 500px; float: none; margin: 0px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2541510480/TB2QGpnbHeI.eBjSspkXXaXqVXa_!!2541510480.png\" style=\"border: 0px; vertical-align: top; height: 500px; width: 500px; float: none; margin: 0px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2541510480/TB2eRlebHaI.eBjy1XdXXcoqXXa_!!2541510480.png\" style=\"border: 0px; vertical-align: top; height: 500px; width: 500px; float: none; margin: 0px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2541510480/TB2T6FkbM1J.eBjy0FaXXaXeVXa_!!2541510480.png\" class=\"\" style=\"border: 0px; vertical-align: top; height: 500px; width: 500px; float: none; margin: 0px;\"/><img src=\"https://img.alicdn.com/imgextra/i3/2541510480/TB2iTtMbR9J.eBjy0FoXXXyvpXa_!!2541510480.png\" class=\"\" align=\"absmiddle\" width=\"1366\" height=\"768\" style=\"border: 0px; vertical-align: top;\"/><img src=\"https://img.alicdn.com/imgextra/i2/2541510480/TB2GfVubIeJ.eBjy0FiXXXqapXa_!!2541510480.jpg\" class=\"\" align=\"absmiddle\" width=\"1242\" height=\"2208\" style=\"border: 0px; vertical-align: top;\"/></p><h5 style=\"margin: 0px 0px 0px 10px; padding: 0px; font-size: 15px;\">买了该宝贝的人还买了</h5><p><br/></p>,', 'spring，springmvc ，mybatis，通用mapping ，redis beetl  AmazeUI-2.4.2  jquery后台权限管理 精确到按钮级别  菜单配置 字典管理 机构管理 用户  角色管理  商品管理  类别管理订单管理   购物车  广告  文章管理等前台 首页菜单 购物详情', '96', '10', 'zsshop.jpg', '框架', '238', null, '150', 'https://img.alicdn.com/imgextra/i3/2541510480/TB2tvhtbNmJ.eBjy0FhXXbBdFXa_!!2541510480.png,https://img.alicdn.com/imgextra/i4/2541510480/TB2wkprbSuJ.eBjy0FgXXXBBXXa_!!2541510480.jpg,https://img.alicdn.com/imgextra/i3/2541510480/TB2QGpnbHeI.eBjSspkXXaXqVXa_!!2541510480.png,', '2016-12-23 12:58:40', '0', '0', null);
INSERT INTO `tt_goods` VALUES ('36', 'zscatPlus', 'spring,springmvc ,通用mapper, dubbo,zookeep', '<p><img src=\"/zsTrade/static/ueditor/jsp/upload1/20161223/1482469194786.png\" style=\"float:none;\" title=\"H.png\"/></p><p><img src=\"/zsTrade/static/ueditor/jsp/upload1/20161223/1482469198359.png\" style=\"float:none;\" title=\"QQ图片20161012163538.png\"/></p><p><img src=\"/zsTrade/static/ueditor/jsp/upload1/20161223/1482469195105.png\" style=\"float:none;\" title=\"QQ图片20161012163729.png\"/></p><p><br/></p>,', '', '95', '11', 'H.png', '框架', '247', null, '50', '/zsTrade/static/ueditor/jsp/upload1/20161223/1482469194786.png,/zsTrade/static/ueditor/jsp/upload1/20161223/1482469198359.png,/zsTrade/static/ueditor/jsp/upload1/20161223/1482469195105.png,', '2016-12-23 12:59:55', '0', '0', '1');
INSERT INTO `tt_goods` VALUES ('37', 'zscat社区', 'layui,ssm', '<p><img src=\"/zsTrade/static/ueditor/jsp/upload1/20161223/1482469481972.png\" style=\"float:none;\" title=\"blog.png\"/></p><p><img src=\"/zsTrade/static/ueditor/jsp/upload1/20161223/1482469481086.png\" style=\"float:none;\" title=\"QQ截图20161104134602.png\"/></p><p><img src=\"/zsTrade/static/ueditor/jsp/upload1/20161223/1482469482244.png\" style=\"float:none;\" title=\"QQ截图20161104135156.png\"/></p><p><img src=\"/zsTrade/static/ueditor/jsp/upload1/20161223/1482469487209.jpg\" style=\"float:none;\" title=\"04.jpg\"/></p><p><img src=\"/zsTrade/static/ueditor/jsp/upload1/20161223/1482469481047.png\" style=\"float:none;\" title=\"QQ图片20161028110015.png\"/></p><p><img src=\"/zsTrade/static/ueditor/jsp/upload1/20161223/1482469481495.png\" style=\"float:none;\" title=\"QQ图片20161028110044.png\"/></p><p><img src=\"/zsTrade/static/ueditor/jsp/upload1/20161223/1482469480820.png\" style=\"float:none;\" title=\"QQ图片20161028111335.png\"/></p><p><br/></p>,', '', '20', '10', 'blog.png', '框架', '245', null, '20', '/zsTrade/static/ueditor/jsp/upload1/20161223/1482469481972.png,/zsTrade/static/ueditor/jsp/upload1/20161223/1482469481086.png,/zsTrade/static/ueditor/jsp/upload1/20161223/1482469482244.png,', '2017-01-24 13:04:44', '0', '0', null);
INSERT INTO `tt_goods` VALUES ('38', 'zsCms', 'ssm', '<p><img src=\"/zsTrade/static/ueditor/jsp/upload1/20161223/1482469612881.png\" style=\"float:none;\" title=\"cms.png\"/></p><p><img src=\"/zsTrade/static/ueditor/jsp/upload1/20161223/1482469616227.png\" style=\"float:none;\" title=\"QQ图片20161028130524.png\"/></p><p><br/></p>,', '', '18', '10', 'cms.png', '框架', '246', null, '30', '/zsTrade/static/ueditor/jsp/upload1/20161223/1482469612881.png,/zsTrade/static/ueditor/jsp/upload1/20161223/1482469616227.png,', '2016-12-23 13:06:55', '0', '0', '2');
INSERT INTO `tt_goods` VALUES ('39', 'zscatLte分布式框架', 'spring', '<p style=\"margin-bottom: 15px; orphans: 2; white-space: normal; widows: 2; box-sizing: inherit; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; background-color: rgb(250, 250, 250); margin-top: 0px !important;\">zscatLte是一个轻量级权限管理系统，其核心设计目标是开发迅速、学习简单、轻量级、易扩展。<br style=\"box-sizing: inherit;\"/>使用zscatLte搭建项目，只需编写30%左右代码，其余的代码交给系统自动生成。<br style=\"box-sizing: inherit;\"/>一个月的工作量，一周就能完成，剩余的时间可以陪家人、朋友、撩妹、钓凯子等，从此踏入高富帅、白富美行业。</p><p style=\"margin-top: 0px; margin-bottom: 15px; orphans: 2; white-space: normal; widows: 2; box-sizing: inherit; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; background-color: rgb(250, 250, 250);\"><strong style=\"box-sizing: inherit;\">具有如下特点</strong></p><ul class=\"task-list\" style=\"orphans: 2; white-space: normal; widows: 2; box-sizing: inherit; font-size: 14px; line-height: 24px; margin: 0px 0px 15px; padding-left: 30px; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; background-color: rgb(250, 250, 250);\"><li>轻量级的权限系统，只涉及Spring、Shiro、Mybatis后端框架，降低学习使用成本</li><li>友好的代码结构及注释，便于阅读及二次开发</li><li>支持HTML、JSP、Velocity、Freemarker等视图，零技术门槛</li><li>灵活的权限控制，可控制到页面或按钮，满足绝大部分的权限需求(如需控制到按钮级别，需使用Shiro标签，控制按钮的显示或隐藏)</li><li>页面交互使用Vue2.x，极大的提高了开发效率</li><li>完善的代码生成机制，可在线生成entity、xml、dao、service、page、js代码，减少70%以上的开发任务</li><li>引入quartz定时任务，可动态完成任务的添加、修改、删除、暂停、恢复及日志查看等功能</li><li>引入路由机制，刷新页面会停留在当前页</li></ul><p><img src=\"/static/ueditor/jsp/upload1/20161227/1482833332927.png\" style=\"float:none;\" title=\"blog.png\"/></p><p><img src=\"/static/ueditor/jsp/upload1/20161227/1482833337146.png\" style=\"float:none;\" title=\"gen.png\"/></p><p><img src=\"/static/ueditor/jsp/upload1/20161227/1482833336912.png\" style=\"float:none;\" title=\"menu.png\"/></p><p><img src=\"/static/ueditor/jsp/upload1/20161227/1482833335881.png\" style=\"float:none;\" title=\"zscatlte.png\"/></p><p><br/></p>,', 'zscatLte是一个轻量级权限管理系统，其核心设计目标是开发迅速、学习简单、轻量级、易扩展。使用zscatLte搭建项目，只需编写30%左右代码，其余的代码交给系统自动生成。一个月的工作量，一周就能完成，剩余的时间可以陪家人、朋友、撩妹、钓凯子等，从此踏入高富帅、白富美行业。具有如下特点轻量级的权限系统，只', '49', '11', 'zscatlte.png', '框架', '247', null, '50', '/static/ueditor/jsp/upload1/20161227/1482833332927.png,/static/ueditor/jsp/upload1/20161227/1482833337146.png,/static/ueditor/jsp/upload1/20161227/1482833336912.png,', '2016-12-27 18:08:57', '0', '0', '1');
INSERT INTO `tt_goods` VALUES ('40', 'java后台框架', 'hibernate', '<p><img src=\"/static/ueditor/jsp/upload1/20161227/1482833394317.png\" style=\"float:none;\" title=\"blog.png\"/></p><p><img src=\"/static/ueditor/jsp/upload1/20161227/1482833403014.png\" style=\"float:none;\" title=\"menu.png\"/></p><p><img src=\"/static/ueditor/jsp/upload1/20161227/1482833395840.png\" style=\"float:none;\" title=\"role.png\"/></p><p><br/></p>,', '', '41', '10', 'blog.png', '框架', '247', null, '1', '/static/ueditor/jsp/upload1/20161227/1482833394317.png,/static/ueditor/jsp/upload1/20161227/1482833403014.png,/static/ueditor/jsp/upload1/20161227/1482833395840.png,', '2017-01-24 18:09:58', '0', '0', null);
INSERT INTO `tt_goods` VALUES ('41', 'ssh后台框架_activiy5', 'ssh', '<h3 style=\"box-sizing: inherit; font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; line-height: 1.33em; margin: 20px 0px 10px; padding: 0px; font-size: 18px; -webkit-font-smoothing: antialiased; cursor: text; position: relative; color: rgba(0, 0, 0, 0.8); white-space: normal; background-color: rgb(250, 250, 250);\">ommon</h3><ul class=\"task-list\" style=\"box-sizing: inherit; font-size: 14px; line-height: 24px; margin: 0px 0px 15px; padding-left: 30px; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; white-space: normal; background-color: rgb(250, 250, 250);\"><li>泛型封装dao、service、controller基类，包含分页，查询条件封装，从而达到快速crud操作(参考<a href=\"https://github.com/springside\" target=\"_blank\" style=\"box-sizing: inherit; color: rgb(65, 131, 196); text-decoration: none; background-color: transparent; cursor: pointer !important; word-wrap: break-word; margin-top: 0px; margin-bottom: 0px; background-position: initial initial; background-repeat: initial initial;\">springside4</a>&quot;)</li><li>简易代码生成器,增加字段配置功能、生成页面功能，实现增删查改不需要写一句代码(参考<a href=\"https://github.com/thinkgem/jeesite\" target=\"_blank\" style=\"box-sizing: inherit; color: rgb(65, 131, 196); text-decoration: none; background-color: transparent; cursor: pointer !important; word-wrap: break-word; margin-top: 0px; margin-bottom: 0px; background-position: initial initial; background-repeat: initial initial;\">jeesite</a>)</li><li>ehcache缓存</li><li>springmvc 整合hibernate validater 进行后端数据验证</li></ul><h3 style=\"box-sizing: inherit; font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; line-height: 1.33em; margin: 20px 0px 10px; padding: 0px; font-size: 18px; -webkit-font-smoothing: antialiased; cursor: text; position: relative; color: rgba(0, 0, 0, 0.8); white-space: normal; background-color: rgb(250, 250, 250);\"><a class=\"anchor\" id=\"系统权限管理_6\" href=\"http://git.oschina.net/rguess/thinker#系统权限管理_6\" style=\"box-sizing: inherit; color: rgb(65, 131, 196); text-decoration: none; background-color: transparent; cursor: pointer; word-wrap: break-word; display: block; padding-left: 30px; margin-left: -20px; position: absolute; top: 0px; left: 0px; bottom: 0px; outline: none; background-position: initial initial; background-repeat: initial initial;\"></a>系统权限管理</h3><ul class=\"task-list\" style=\"box-sizing: inherit; font-size: 14px; line-height: 24px; margin: 0px 0px 15px; padding-left: 30px; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; white-space: normal; background-color: rgb(250, 250, 250);\"><li>系统管理——包含用户、角色、权限、资源、菜单管理</li><li>权限管理——使用框架apache shiro进行系统认证、授权操作</li><ul class=\"task-list\" style=\"box-sizing: inherit; margin: 0px; padding-left: 30px;\"><li>系统认证、授权操作</li><li>按钮粒度的权限控制</li><li>并发登录人数控制</li><li>后端使用注解进行方法级别的权限控制</li></ul><li>系统登录,操作日志记录,查询,分析</li></ul><h3 style=\"box-sizing: inherit; font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; line-height: 1.33em; margin: 20px 0px 10px; padding: 0px; font-size: 18px; -webkit-font-smoothing: antialiased; cursor: text; position: relative; color: rgba(0, 0, 0, 0.8); white-space: normal; background-color: rgb(250, 250, 250);\"><a class=\"anchor\" id=\"oa工作流程_7\" href=\"http://git.oschina.net/rguess/thinker#oa工作流程_7\" style=\"box-sizing: inherit; color: rgb(65, 131, 196); text-decoration: none; background-color: transparent; cursor: pointer; word-wrap: break-word; display: block; padding-left: 30px; margin-left: -20px; position: absolute; top: 0px; left: 0px; bottom: 0px; outline: none; background-position: initial initial; background-repeat: initial initial;\"></a>OA工作流程</h3><ul class=\"task-list\" style=\"box-sizing: inherit; font-size: 14px; line-height: 24px; margin: 0px 0px 15px; padding-left: 30px; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; white-space: normal; background-color: rgb(250, 250, 250);\"><li>集成activiti(工作流引擎)</li><ul class=\"task-list\" style=\"box-sizing: inherit; margin: 0px; padding-left: 30px;\"><li>整合spring</li><li>工作流引擎并完成工作流通用基类、常用工具类</li><li>通用待办,已办,我的流程等常用查询</li><li>定义业务开发需实现的接口</li><li>流程部署</li><li>请假流程例子</li><li>自由工作流设计例子</li></ul></ul><h3 style=\"box-sizing: inherit; font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; line-height: 1.33em; margin: 20px 0px 10px; padding: 0px; font-size: 18px; -webkit-font-smoothing: antialiased; cursor: text; position: relative; color: rgba(0, 0, 0, 0.8); white-space: normal; background-color: rgb(250, 250, 250);\"><a class=\"anchor\" id=\"内容管理_8\" href=\"http://git.oschina.net/rguess/thinker#内容管理_8\" style=\"box-sizing: inherit; color: rgb(65, 131, 196); text-decoration: none; background-color: transparent; cursor: pointer; word-wrap: break-word; display: block; padding-left: 30px; margin-left: -20px; position: absolute; top: 0px; left: 0px; bottom: 0px; outline: none; background-position: initial initial; background-repeat: initial initial;\"></a>内容管理</h3><ul class=\"task-list\" style=\"box-sizing: inherit; font-size: 14px; line-height: 24px; margin: 0px 0px 15px; padding-left: 30px; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; white-space: normal; background-color: rgb(250, 250, 250);\"><li>实现栏目，文章，系统静态资源的管理</li><li>实现上传word发表文章</li><li>实现ueditor富文本方式发表文章</li><li>aop+freemarker+线程实现首页静态化</li><li>实现多个站点的管理</li><li>基于该cms的个人博客网站</li></ul><h3 style=\"box-sizing: inherit; font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; line-height: 1.33em; margin: 20px 0px 10px; padding: 0px; font-size: 18px; -webkit-font-smoothing: antialiased; cursor: text; position: relative; color: rgba(0, 0, 0, 0.8); white-space: normal; background-color: rgb(250, 250, 250);\"><a class=\"anchor\" id=\"utils_9\" href=\"http://git.oschina.net/rguess/thinker#utils_9\" style=\"box-sizing: inherit; color: rgb(65, 131, 196); text-decoration: none; background-color: transparent; cursor: pointer; word-wrap: break-word; display: block; padding-left: 30px; margin-left: -20px; position: absolute; top: 0px; left: 0px; bottom: 0px; outline: none; background-position: initial initial; background-repeat: initial initial;\"></a>utils</h3><ul class=\"task-list\" style=\"box-sizing: inherit; font-size: 14px; line-height: 24px; margin: 0px 0px 15px; padding-left: 30px; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; white-space: normal; background-color: rgb(250, 250, 250);\"><li>开发中常用工具类整理</li><ul class=\"task-list\" style=\"box-sizing: inherit; margin: 0px; padding-left: 30px;\"><li>基于apache poi的通用导出excel工具类</li><li>各种类型相互转化工具类</li><li>Http与Servlet工具类</li><li>常用日期处理工具类</li><li>各种编码,解码,加密等工作类</li><li>反射操作封装</li><li>文件操作</li><li>WordToHtml</li><li>spring注解方式实现定时任务调度，实现mysql数据备份</li><li>...</li></ul><li>整理了丰富多样的js、jquery插件</li><ul class=\"task-list\" style=\"box-sizing: inherit; margin: 0px; padding-left: 30px;\"><li>bootstrap</li><li>jquery validation、修改验证后显示以适应bootstrap，显得更加美观、添加常用验证</li><li>自定义的js分页插件(配合bootstrap-paginator)</li><li>ztree(jquery 树插件)</li><li>datetimepicker(日历控件)</li><li>gritter(提示框)</li><li>jquery-slimscroll(定义局部元素滚动)</li><li>jquery-treegrid(树插件能更好的与bootstrap table融合以展示数据)</li><li>bootstrap-tree(简易的树插件)</li><li>chosen-bootstrap(下拉框选择控件)</li><li>font-awesome(字体)</li><li>uniform(表单美化)</li><li>jquery blockUI(遮罩层)</li><li>ueditor(富文本编辑器)</li><li>bootstrap-fileupload</li><li>jquery fileupload多文件上传带进度条</li><li>....</li></ul></ul><h3 style=\"box-sizing: inherit; font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; line-height: 1.33em; margin: 20px 0px 10px; padding: 0px; font-size: 18px; -webkit-font-smoothing: antialiased; cursor: text; position: relative; color: rgba(0, 0, 0, 0.8); white-space: normal; background-color: rgb(250, 250, 250);\"><a class=\"anchor\" id=\"others_10\" href=\"http://git.oschina.net/rguess/thinker#others_10\" style=\"box-sizing: inherit; color: rgb(65, 131, 196); text-decoration: none; background-color: transparent; cursor: pointer; word-wrap: break-word; display: block; padding-left: 30px; margin-left: -20px; position: absolute; top: 0px; left: 0px; bottom: 0px; outline: none; background-position: initial initial; background-repeat: initial initial;\"></a>others</h3><ul class=\"task-list\" style=\"box-sizing: inherit; font-size: 14px; line-height: 24px; margin: 0px 0px 15px; padding-left: 30px; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; white-space: normal; background-color: rgb(250, 250, 250);\"><li>集成jsper report动态报表工具(未完成)</li><li>jpush消息推送</li></ul><p><img src=\"/static/ueditor/jsp/upload1/20161227/1482833523239.png\" style=\"float:none;\" title=\"cms.png\"/></p><p><img src=\"/static/ueditor/jsp/upload1/20161227/1482833525442.png\" style=\"float:none;\" title=\"liuc2.png\"/></p><p><img src=\"/static/ueditor/jsp/upload1/20161227/1482833517232.png\" style=\"float:none;\" title=\"menu.png\"/></p><p><img src=\"/static/ueditor/jsp/upload1/20161227/1482833524270.png\" style=\"float:none;\" title=\"role1.png\"/></p><p><img src=\"/static/ueditor/jsp/upload1/20161227/1482833524459.png\" style=\"float:none;\" title=\"流程.png\"/></p><p><br/></p>,', 'ommon泛型封装dao、service、controller基类，包含分页，查询条件封装，从而达到快速crud操作(参考springside4\")简易代码生成器,增加字段配置功能、生成页面功能，实现增删查改不需要写一句代码(参考jeesite)ehcache缓存springmvc 整合hibernate v', '24', '10', 'home1.png', '框架', '247', null, '50', '/static/ueditor/jsp/upload1/20161227/1482833523239.png,/static/ueditor/jsp/upload1/20161227/1482833525442.png,/static/ueditor/jsp/upload1/20161227/1482833517232.png,', '2016-12-27 18:12:03', '0', '0', null);
INSERT INTO `tt_goods` VALUES ('42', '因库网校系统', 'ssm', '<p style=\"box-sizing: inherit; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250); margin-top: 0px !important;\"><strong style=\"box-sizing: inherit;\">免费开源网校系统源代码轻松搭建在线教育平台</strong></p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">因酷交流群①：468278040 (满)</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\"><br/></p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">因酷交流群②：164295773</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">官网:<a href=\"http://http//www.inxedu.com\" class=\"md_relative_url\" target=\"_blank\" style=\"box-sizing: inherit; color: rgb(65, 131, 196); text-decoration: none; background-color: transparent; cursor: pointer !important; word-wrap: break-word; background-position: initial initial; background-repeat: initial initial;\">http://www.inxedu.com</a></p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">演示站:<a href=\"http://http//demo1.inxedu.com\" class=\"md_relative_url\" target=\"_blank\" style=\"box-sizing: inherit; color: rgb(65, 131, 196); text-decoration: none; background-color: transparent; cursor: pointer !important; word-wrap: break-word; background-position: initial initial; background-repeat: initial initial;\">http://demo1.inxedu.com</a></p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">账号:<a href=\"mailto:demo@inxedu.com\" style=\"box-sizing: inherit; color: rgb(65, 131, 196); text-decoration: none; background-color: transparent; cursor: pointer !important; word-wrap: break-word; background-position: initial initial; background-repeat: initial initial;\">demo@inxedu.com</a></p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">密码:111111<br style=\"box-sizing: inherit;\"/><br style=\"box-sizing: inherit;\"/>网站功能模块:</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">课程功能</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">咨询功能</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">问答功能</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">首页banner推荐</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">播放模块</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">个人中心模块</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">个人资料模块</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">修改头像模块</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">收藏课程模块</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">问题总结:<br style=\"box-sizing: inherit;\"/>项目导入如果get set报错请添加lombok插件就可以正常使用了</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\"><strong style=\"box-sizing: inherit;\">技术框架</strong></p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">核心框架：Spring Framework</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">视图框架：Spring MVC</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">持久层框架：MyBatis 3</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">JS框架：jQuery</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">富文本：kindeditor</p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\"><strong style=\"box-sizing: inherit;\">开发环境</strong></p><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">建议开发者使用以下环境，这样避免版本带来的问题</p><p><img src=\"/static/ueditor/jsp/upload1/20161227/1482833798198.png\" style=\"float:none;\" title=\"1index.png\"/></p><p><img src=\"/static/ueditor/jsp/upload1/20161227/1482833794932.png\" style=\"float:none;\" title=\"2index.png\"/></p><p><img src=\"/static/ueditor/jsp/upload1/20161227/1482833799763.png\" style=\"float:none;\" title=\"3index.png\"/></p><p><br/></p>,', '免费开源网校系统源代码轻松搭建在线教育平台因酷交流群①：468278040 (满)因酷交流群②：164295773官网:http://www.inxedu.com演示站:http://demo1.inxedu.com账号:demo@inxedu.com密码:111111网站功能模块:课程功能咨询功能问答功能首', '5', '10', '1index.png', '框架', '238', null, '1', '/static/ueditor/jsp/upload1/20161227/1482833798198.png,/static/ueditor/jsp/upload1/20161227/1482833794932.png,/static/ueditor/jsp/upload1/20161227/1482833799763.png,', '2016-12-27 18:16:38', '0', '0', '1');
INSERT INTO `tt_goods` VALUES ('43', 'jeesite后台框架', '', '<p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">1、后端</p><ul class=\"task-list\" style=\"box-sizing: inherit; font-size: 14px; line-height: 24px; margin: 0px 0px 15px; padding-left: 30px; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; white-space: normal; background-color: rgb(250, 250, 250);\"><li>核心框架：Spring Framework 4.1</li><li>安全框架：Apache Shiro 1.2</li><li>视图框架：Spring MVC 4.1</li><li>服务端验证：Hibernate Validator 5.2</li><li>布局框架：SiteMesh 2.4</li><li>工作流引擎：Activiti 5.21</li><li>任务调度：Spring Task 4.1</li><li>持久层框架：MyBatis 3.2</li><li>数据库连接池：Alibaba Druid 1.0</li><li>缓存框架：Ehcache 2.6、Redis</li><li>日志管理：SLF4J 1.7、Log4j</li><li>工具类：Apache Commons、Jackson 2.2、Xstream 1.4、Dozer 5.3、POI 3.9</li></ul><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">2、前端</p><ul class=\"task-list\" style=\"box-sizing: inherit; font-size: 14px; line-height: 24px; margin: 0px 0px 15px; padding-left: 30px; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; white-space: normal; background-color: rgb(250, 250, 250);\"><li>JS框架：jQuery 1.9。</li><li>CSS框架：Twitter Bootstrap 2.3.1（稳定是后台，UI方面根据需求自己升级改造吧）。</li><li>客户端验证：JQuery Validation Plugin 1.11。</li><li>富文本在线编辑：CKEditor</li><li>在线文件管理：CKFinder</li><li>动态页签：Jerichotab</li><li>手机端框架：Jingle</li><li>数据表格：jqGrid</li><li>对话框：jQuery jBox</li><li>下拉选择框：jQuery Select2</li><li>树结构控件：jQuery zTree</li><li>日期控件： My97DatePicker</li></ul><p style=\"box-sizing: inherit; margin-top: 0px; margin-bottom: 15px; line-height: 25px; text-align: justify; word-break: break-word; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; font-size: 15px; white-space: normal; background-color: rgb(250, 250, 250);\">4、平台</p><ul class=\"task-list\" style=\"box-sizing: inherit; font-size: 14px; line-height: 24px; margin: 0px 0px 15px; padding-left: 30px; color: rgba(0, 0, 0, 0.8); font-family: Lato, &#39;Helvetica Neue&#39;, Arial, Helvetica, &#39;Microsoft YaHei&#39;, sans-serif; white-space: normal; background-color: rgb(250, 250, 250);\"><li>服务器中间件：在Java EE 5规范（Servlet 2.5、JSP 2.1）下开发，支持应用服务器中间件 有Tomcat 6+、Jboss 7+、WebLogic 10+、WebSphere 8+。</li><li>数据库支持：目前仅提供MySql和Oracle数据库的支持，但不限于数据库，平台留有其它数据库支持接口， 你可以很方便的更改为其它数据库，如：SqlServer 2008、MySql 5.5、H2等</li><li>开发环境：Java、Eclipse Java EE 4.3、Maven 3.1、Git</li></ul><p><br/></p>,', '1、后端核心框架：Spring Framework 4.1安全框架：Apache Shiro 1.2视图框架：Spring MVC 4.1服务端验证：Hibernate Validator 5.2布局框架：SiteMesh 2.4工作流引擎：Activiti 5.21任务调度：Spring Task 4.1持', '16', '10', 'zscat.png', '框架', '247', null, '', '', '2016-12-27 18:21:25', '0', '0', null);
INSERT INTO `tt_goods` VALUES ('44', 'zscat_妹子后台框架', 'ssm', '<p><img src=\"/static/ueditor/jsp/upload1/20161227/1482834426033.png\" style=\"float:none;\" title=\"1meizi.png\"/></p><p><img src=\"/static/ueditor/jsp/upload1/20161227/1482834430190.png\" style=\"float:none;\" title=\"2meizi.png\"/></p><p><img src=\"/static/ueditor/jsp/upload1/20161227/1482834431132.png\" style=\"float:none;\" title=\"3妹子1.png\"/></p><p><br/></p>,', '', '30', '10', '1meizi.png', '框架', '247', null, '55', '/static/ueditor/jsp/upload1/20161227/1482834426033.png,/static/ueditor/jsp/upload1/20161227/1482834430190.png,/static/ueditor/jsp/upload1/20161227/1482834431132.png,', '2016-12-27 18:27:07', '0', '0', null);
INSERT INTO `tt_goods` VALUES ('49', '华迪空气净化器KJ420P-HD280', '', '<ul class=\"attributes-list\" style=\"margin: 0px; padding: 0px 15px; list-style: none; clear: both;\"><li>品牌:&nbsp;华迪</li><li>型号:&nbsp;KJ420P-HD280</li><li>噪音:&nbsp;0-35dB</li><li>控制方式:&nbsp;其他</li><li>智能类型:&nbsp;其他</li><li>颗粒物CADR值:&nbsp;420.1立方米/小时</li><li>颗粒物CCM值:&nbsp;P4(12000≤M)</li><li>风量:&nbsp;600立方米/小时</li><li>适用面积:&nbsp;30m^2-50m^2</li><li>功能:&nbsp;定时 除VOC 除花粉 除颗粒物 除甲醛 除烟除尘 杀菌 加湿 加氧</li><li>空气净化产品类别:&nbsp;空气净化器</li><li>风量:&nbsp;400 立方米/小时以上</li><li>电源方式:&nbsp;交流电</li><li>售后服务:&nbsp;店铺三包</li><li>适用对象:&nbsp;家用 卧室 办公室</li><li>空气净化器风量:&nbsp;400 立方米/小时</li></ul><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2591435385/TB2Qmv4XrRkpuFjSspmXXc.9XXa_!!2591435385.jpg\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2591435385/TB2XOn3XxXlpuFjSsphXXbJOXXa_!!2591435385.jpg\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2591435385/TB2JCv4Xr8kpuFjy0FcXXaUhpXa_!!2591435385.jpg\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2591435385/TB2Nrj2XBNkpuFjy0FaXXbRCVXa_!!2591435385.jpg\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2591435385/TB22Wv4XwJlpuFjSspjXXcT.pXa_!!2591435385.jpg\" class=\"\" width=\"750\" height=\"567\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2591435385/TB2plL4XC0jpuFjy0FlXXc0bpXa_!!2591435385.jpg\" class=\"\" width=\"750\" height=\"567\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2591435385/TB2T6T3XrVkpuFjSspcXXbSMVXa_!!2591435385.jpg\" class=\"\" width=\"750\" height=\"531\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2591435385/TB2TRL4XC0jpuFjy0FlXXc0bpXa_!!2591435385.jpg\" class=\"\" width=\"750\" height=\"720\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2591435385/TB2nzn2Xw0kpuFjSspdXXX4YXXa_!!2591435385.jpg\" class=\"\" width=\"750\" height=\"759\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2591435385/TB2Wmz4Xr8kpuFjy0FcXXaUhpXa_!!2591435385.jpg\" class=\"\" width=\"750\" height=\"828\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2591435385/TB2GAn3XxXkpuFjy0FiXXbUfFXa_!!2591435385.jpg\" class=\"\" width=\"750\" height=\"684\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2591435385/TB2PAn3XxXkpuFjy0FiXXbUfFXa_!!2591435385.jpg\" class=\"\" width=\"750\" height=\"477\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2591435385/TB2_Y_3XCtkpuFjy0FhXXXQzFXa_!!2591435385.jpg\" class=\"\" width=\"750\" height=\"720\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2591435385/TB2VJP4XrtlpuFjSspoXXbcDpXa_!!2591435385.jpg\" class=\"\" width=\"750\" height=\"651\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/></p><p><br/></p>,', '品牌: 华迪型号: KJ420P-HD280噪音: 0-35dB控制方式: 其他智能类型: 其他颗粒物CADR值: 420.1立方米/小时颗粒物CCM值: P4(12000≤M)风量: 600立方米/小时适用面积: 30m^2-50m^2功能: 定时 除VOC 除花粉 除颗粒物 除甲醛 除烟除尘 杀菌 加湿 ', '11', '13', '1智能.jpg', '智能家居', '250', null, '3980', 'https://img.alicdn.com/imgextra/i2/2591435385/TB2Qmv4XrRkpuFjSspmXXc.9XXa_!!2591435385.jpg,https://img.alicdn.com/imgextra/i2/2591435385/TB2XOn3XxXlpuFjSsphXXbJOXXa_!!2591435385.jpg,https://img.alicdn.com/imgextra/i1/2591435385/TB2JCv4Xr8kpuFjy0FcXXaUhpXa_!!2591435385.jpg,', '2017-01-06 13:22:42', '0', '0', '2');
INSERT INTO `tt_goods` VALUES ('50', '华迪智能空气净化器', '', '<ul class=\"attributes-list\" style=\"margin: 0px; padding: 0px 15px; list-style: none; clear: both;\"><li>品牌:&nbsp;华迪</li><li>型号:&nbsp;HD-AP100</li><li>智能类型:&nbsp;其他</li></ul><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\"><img src=\"https://img.alicdn.com/imgextra/i2/2591435385/TB2Jw99spXXXXcNXpXXXXXXXXXX_!!2591435385.png\" align=\"absmiddle\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img src=\"https://img.alicdn.com/imgextra/i3/2591435385/TB2MW2xspXXXXXVXpXXXXXXXXXX_!!2591435385.png\" align=\"absmiddle\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img src=\"https://img.alicdn.com/imgextra/i4/2591435385/TB2xO6yspXXXXXVXpXXXXXXXXXX_!!2591435385.png\" align=\"absmiddle\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img src=\"https://img.alicdn.com/imgextra/i4/2591435385/TB2Tcm_spXXXXXiXFXXXXXXXXXX_!!2591435385.png\" align=\"absmiddle\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img src=\"https://img.alicdn.com/imgextra/i1/2591435385/TB2oxv8spXXXXXCXXXXXXXXXXXX_!!2591435385.png\" class=\"\" align=\"absmiddle\" width=\"800\" height=\"626\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img src=\"https://img.alicdn.com/imgextra/i4/2591435385/TB2xOHgspXXXXceXpXXXXXXXXXX_!!2591435385.png\" class=\"\" align=\"absmiddle\" width=\"800\" height=\"610\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img src=\"https://img.alicdn.com/imgextra/i3/2591435385/TB2JTHLspXXXXcqXXXXXXXXXXXX_!!2591435385.png\" class=\"\" align=\"absmiddle\" width=\"800\" height=\"539\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/></p><p><br/></p>,', '品牌: 华迪型号: HD-AP100智能类型: 其他', '11', '13', '1智能.jpg', '智能家居', '250', null, '2280', 'https://img.alicdn.com/imgextra/i2/2591435385/TB2Jw99spXXXXcNXpXXXXXXXXXX_!!2591435385.png,https://img.alicdn.com/imgextra/i3/2591435385/TB2MW2xspXXXXXVXpXXXXXXXXXX_!!2591435385.png,https://img.alicdn.com/imgextra/i4/2591435385/TB2xO6yspXXXXXVXpXXXXXXXXXX_!!2591435385.png,', '2017-01-06 13:24:02', '0', '0', null);
INSERT INTO `tt_goods` VALUES ('51', '华迪智能网关HDG6200', '', '<ul class=\"attributes-list\" style=\"margin: 0px; padding: 0px 15px; list-style: none; clear: both;\"><li>兼容平台:&nbsp;ANDROID</li><li>品牌:&nbsp;华迪</li><li>连接方式:&nbsp;Wi-Fi连接</li><li>颜色:&nbsp;白色</li><li>颜色分类:&nbsp;白色</li></ul><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\"><span style=\"color: rgb(108, 108, 108); line-height: 14.4px; font-size: 12px;\">支持智能家居功能、云电脑功能、网络机顶盒功能、无线路由器热点功能、支持与指定型号智能路由器家庭联网功能、支持物业管理系统、支持宽频系统、整合支持数百个互联网应用<img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2591435385/TB2R5nXspXXXXa_XpXXXXXXXXXX_!!2591435385.jpg\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2591435385/TB2EfYaspXXXXbPXpXXXXXXXXXX_!!2591435385.jpg\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/></span></p><p><br/></p>,', '兼容平台: ANDROID品牌: 华迪连接方式: Wi-Fi连接颜色: 白色颜色分类: 白色支持智能家居功能、云电脑功能、网络机顶盒功能、无线路由器热点功能、支持与指定型号智能路由器家庭联网功能、支持物业管理系统、支持宽频系统、整合支持数百个互联网应用', '0', '13', '3智能.png', '智能家居', '251', null, '339', 'https://img.alicdn.com/imgextra/i4/2591435385/TB2R5nXspXXXXa_XpXXXXXXXXXX_!!2591435385.jpg,https://img.alicdn.com/imgextra/i1/2591435385/TB2EfYaspXXXXbPXpXXXXXXXXXX_!!2591435385.jpg,', '2017-01-06 13:25:33', '0', '0', '2');
INSERT INTO `tt_goods` VALUES ('52', '华迪智能插排', '', '<p><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2591435385/TB2fBlRaH1K.eBjSszbXXcTHpXa_!!2591435385.png\" style=\"border: 0px; vertical-align: top; font-family: tahoma, arial, 宋体, sans-serif; font-size: 14px; line-height: 21px; white-space: normal; background-color: rgb(255, 255, 255); max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2591435385/TB2jAxMaHaI.eBjSspaXXXIKpXa_!!2591435385.png\" style=\"border: 0px; vertical-align: top; font-family: tahoma, arial, 宋体, sans-serif; font-size: 14px; line-height: 21px; white-space: normal; background-color: rgb(255, 255, 255); max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i4/2591435385/TB2WeRSaNmJ.eBjy0FhXXbBdFXa_!!2591435385.png\" style=\"border: 0px; vertical-align: top; font-family: tahoma, arial, 宋体, sans-serif; font-size: 14px; line-height: 21px; white-space: normal; background-color: rgb(255, 255, 255); max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2591435385/TB2o1RQaIeJ.eBjy0FiXXXqapXa_!!2591435385.png\" style=\"border: 0px; vertical-align: top; font-family: tahoma, arial, 宋体, sans-serif; font-size: 14px; line-height: 21px; white-space: normal; background-color: rgb(255, 255, 255); max-width: 750px;\"/></p>,', '', '1', '13', '4智能.jpg', '智能家居', '251', null, '139', 'https://img.alicdn.com/imgextra/i3/2591435385/TB2fBlRaH1K.eBjSszbXXcTHpXa_!!2591435385.png,https://img.alicdn.com/imgextra/i2/2591435385/TB2jAxMaHaI.eBjSspaXXXIKpXa_!!2591435385.png,https://img.alicdn.com/imgextra/i4/2591435385/TB2WeRSaNmJ.eBjy0FhXXbBdFXa_!!2591435385.png,', '2017-01-06 13:26:38', '0', '0', null);
INSERT INTO `tt_goods` VALUES ('53', '华迪智能控制面板', '', '<ul class=\"attributes-list\" style=\"margin: 0px; padding: 0px 15px; list-style: none; clear: both;\"><li>品牌:&nbsp;华迪</li><li>型号:&nbsp;HD-CP100</li><li>智能类型:&nbsp;其他</li><li>颜色分类:&nbsp;白色 黑色</li></ul><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2591435385/TB2FvL6aigSXeFjy0FcXXahAXXa_!!2591435385.png\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2591435385/TB29AVQaHaI.eBjSszdXXaB6XXa_!!2591435385.png\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2591435385/TB2BztNaHeI.eBjSspkXXaXqVXa_!!2591435385.png\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2591435385/TB2EvP6aigSXeFjy0FcXXahAXXa_!!2591435385.png\" style=\"border: 0px; vertical-align: top; max-width: 750px;\"/></p><p><br/></p>,', '品牌: 华迪型号: HD-CP100智能类型: 其他颜色分类: 白色 黑色', '6', '13', '5智能.png', '智能家居', '251', null, '129', 'https://img.alicdn.com/imgextra/i3/2591435385/TB2FvL6aigSXeFjy0FcXXahAXXa_!!2591435385.png,https://img.alicdn.com/imgextra/i2/2591435385/TB29AVQaHaI.eBjSszdXXaB6XXa_!!2591435385.png,https://img.alicdn.com/imgextra/i1/2591435385/TB2BztNaHeI.eBjSspkXXaXqVXa_!!2591435385.png,', '2017-01-06 13:27:41', '0', '0', '1');
INSERT INTO `tt_goods` VALUES ('54', '华迪智能灯泡', '', '<ul class=\"attributes-list\" style=\"margin: 0px; padding: 0px 15px; list-style: none; clear: both;\"><li>品牌:&nbsp;华迪</li><li>型号:&nbsp;HD-LL100</li><li>智能类型:&nbsp;其他</li><li>颜色分类:&nbsp;白色</li></ul><p style=\"margin-top: 1.12em; margin-bottom: 1.12em; padding: 0px;\"><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2591435385/TB2lxjGoFXXXXa0XXXXXXXXXXXX_!!2591435385.png\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i2/2591435385/TB2Tb_woFXXXXcxXXXXXXXXXXXX_!!2591435385.png\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i3/2591435385/TB28PHAoFXXXXaGXXXXXXXXXXXX_!!2591435385.png\" style=\"border: 0px; vertical-align: top;\"/><img align=\"absmiddle\" src=\"https://img.alicdn.com/imgextra/i1/2591435385/TB2ZVHuoFXXXXXAXpXXXXXXXXXX_!!2591435385.png\" style=\"border: 0px; vertical-align: top;\"/></p><p><br/></p>,', '品牌: 华迪型号: HD-LL100智能类型: 其他颜色分类: 白色', '3', '13', '6智能.png', '智能家居', '251', null, '99', 'https://img.alicdn.com/imgextra/i3/2591435385/TB2lxjGoFXXXXa0XXXXXXXXXXXX_!!2591435385.png,https://img.alicdn.com/imgextra/i2/2591435385/TB2Tb_woFXXXXcxXXXXXXXXXXXX_!!2591435385.png,https://img.alicdn.com/imgextra/i3/2591435385/TB28PHAoFXXXXaGXXXXXXXXXXXX_!!2591435385.png,', '2017-01-06 13:28:46', '0', '0', '2');
INSERT INTO `tt_goods` VALUES ('55', '苹果', '11,22,44', '<p>44444444444444444444444444444pingg苹果是很好吃的水果，一起来吃更多的苹果吧</p><p><img src=\"/zsShop/static/ueditor/jsp/upload1/20170426/1493183331497.png\" title=\"logo.png\"/></p><p><img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\"/><img src=\"http://img.baidu.com/hi/jx2/j_0026.gif\"/></p>,', '44444444444444444444444444444pingg苹果是很好吃的水果，一起来吃更多的苹果吧', '0', '10', 'QQ截图20170317093720.png', '框架', '213', null, '22', '/zsShop/static/ueditor/jsp/upload1/20170426/1493183331497.png,http://img.baidu.com/hi/jx2/j_0002.gif,http://img.baidu.com/hi/jx2/j_0026.gif,', '2017-04-26 13:09:13', '0', '0', null);
INSERT INTO `tt_goods` VALUES ('56', '橘子', '444，55，66', '<p>橘子是很有营养的水果，我很喜欢吃水果的<img src=\"/zsShop/static/ueditor/jsp/upload1/20170426/1493183690784.png\" title=\"grok.png\"/></p>,', '橘子是很有营养的水果，我很喜欢吃水果的', '0', '10', 'logo.png', '框架', '212', null, '44', '/zsShop/static/ueditor/jsp/upload1/20170426/1493183690784.png,', '2017-04-26 13:15:01', '0', '0', null);

-- ----------------------------
-- Table structure for tt_type
-- ----------------------------
DROP TABLE IF EXISTS `tt_type`;
CREATE TABLE `tt_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `delflag` char(1) DEFAULT '0' COMMENT '删除标记(0活null 正常 1,删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tt_type
-- ----------------------------
INSERT INTO `tt_type` VALUES ('10', '框架', '0');
INSERT INTO `tt_type` VALUES ('11', 'test', '0');

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '地址ID',
  `member_id` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '会员ID',
  `true_name` varchar(50) NOT NULL COMMENT '会员姓名',
  `area_id` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '地区ID',
  `city_id` bigint(9) DEFAULT NULL COMMENT '市级ID',
  `area_info` varchar(255) NOT NULL DEFAULT '' COMMENT '地区内容',
  `address` varchar(255) NOT NULL COMMENT '地址',
  `tel_phone` varchar(20) DEFAULT NULL COMMENT '座机电话',
  `mob_phone` varchar(15) DEFAULT NULL COMMENT '手机电话',
  `is_default` enum('0','1') NOT NULL DEFAULT '0' COMMENT '1默认收货地址',
  `province_id` mediumint(10) DEFAULT '0' COMMENT '省级id',
  `zip_code` int(50) DEFAULT NULL COMMENT '邮编',
  PRIMARY KEY (`id`),
  KEY `member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='买家地址信息表';

-- ----------------------------
-- Records of t_address
-- ----------------------------
INSERT INTO `t_address` VALUES ('21', '10', 'Daisy', '41', '36', '北京,北京市,朝阳区', 'SOHO 1105', null, '13520085712', '0', '1', null);
INSERT INTO `t_address` VALUES ('23', '10', 'Daisy', '41', '36', '北京,北京市,朝阳区', 'SOHOnimeide', '01084420880', '13520085712', '0', '1', '0');
INSERT INTO `t_address` VALUES ('24', '10', 'Daisy', '41', '36', '北京,北京市,朝阳区', 'SOHO5678', '0102345435', '13520083333', '1', '1', '111111');
INSERT INTO `t_address` VALUES ('25', '10', 'Daisy', '1417', '95', '内蒙古自治区,呼和浩特市,和林格尔县', 'SOHOwangjing1105 909090', null, '13520085712', '0', '5', null);
INSERT INTO `t_address` VALUES ('26', '10', 'Tony', '1298', '84', '山西省,太原市,万柏林区', '1109', '01083323432', '13589098788', '0', '4', '123321');
INSERT INTO `t_address` VALUES ('29', '12', '鲍睿', '41', '36', '北京,北京市,朝阳区', '尚都soho南塔803', '18610984906', '18610984906', '0', '1', '111111');
INSERT INTO `t_address` VALUES ('30', '13', '谢进伟', '41', '36', '北京,北京市,朝阳区', '东大桥soho南塔803', '13520085712', '13520085712', '0', '1', '10020');
INSERT INTO `t_address` VALUES ('31', '16', 'zrh', '37', '36', '北京,北京市,东城区', 'dfuhfkjsdhfkj', '111111111', '11111111111', '1', '1', '111111');
INSERT INTO `t_address` VALUES ('32', '14', '王明', '41', '36', '北京,北京市,朝阳区', '北京市朝阳区soho尚都', '101-23211132', '13398420124', '0', '1', '100000');
INSERT INTO `t_address` VALUES ('35', '19', '问3', '38', '36', '北京,北京市,西城区', '33', '11122131421', '13654547041', '1', '1', '111111');
INSERT INTO `t_address` VALUES ('36', '22', '123', '37', '36', '北京,北京市,东城区', 'tuu', '的方法', '123', '1', '1', '123');
INSERT INTO `t_address` VALUES ('38', '105', '阿爸', '2528', '224', '山东省,青岛市,市南区', '海水浴场', '88888888', '13500000000', '0', '15', '500006');
INSERT INTO `t_address` VALUES ('39', '28', '吴世勋', '41', '36', '北京,北京市,朝阳区', '世贸天阶soho南塔803', '88888888', '11111111111', '1', '1', '111111');
INSERT INTO `t_address` VALUES ('40', '28', 'zrh', '143', '39', '上海,上海市,黄浦区', '黄埔66号', '9999999999', '18888888888', '0', '9', '666666');
INSERT INTO `t_address` VALUES ('41', '7', '刘鱼凯', '38', '36', '北京,北京市,西城区', '南锣鼓巷八大胡同113号', '12342342424', '13562345564', '1', '1', '11122');
INSERT INTO `t_address` VALUES ('43', '22', '康兰', '47', '36', '北京,北京市,通州区', '尚都会', '15363586', '18569325152', '0', '1', '253658');
INSERT INTO `t_address` VALUES ('44', '28', 'pcl', '2146', '175', '浙江省,杭州市,西湖区', 'qweqwe22', '11111111', '11111111111', '0', '11', '22222');
INSERT INTO `t_address` VALUES ('45', '36', '康兰', '41', '36', '北京,北京市,朝阳区', '尚都南塔803', '0102346742', '18610810207', '1', '1', '100000');
INSERT INTO `t_address` VALUES ('46', '10', 'srttrw', '41', '36', '北京,北京市,朝阳区', 'qqqqqqqqqqqqqq', '010-23453324', '18733195969', '0', '1', '4536546');
INSERT INTO `t_address` VALUES ('47', '45', '吴世勋', '41', '36', '北京,北京市,朝阳区', '世贸天阶SOHO南塔803', '0102222222', '18568652555', '1', '1', '250082');
INSERT INTO `t_address` VALUES ('48', '45', '周杰伦', '2146', '175', '浙江省,杭州市,西湖区', '美胡路66 号', '0108888888', '15355353896', '0', '11', '250002');
INSERT INTO `t_address` VALUES ('51', '47', '黄科源', '37', '36', '北京,北京市,东城区', '尚都', '010-11111111', '18601320411', '1', '1', '100000');
INSERT INTO `t_address` VALUES ('52', '65', 'gfdsgfds', '3038', '289', '广东省,广州市,从化市', 'gfsdgfg', '22381904', '13631390449', '0', '19', '510000');
INSERT INTO `t_address` VALUES ('53', '14', '王明', '2920', '275', '湖南省,长沙市,芙蓉区', '建湘路517号', '0731-88888888', '13900000000', '0', '18', '410001');
INSERT INTO `t_address` VALUES ('54', '44', '11', '56', '40', '天津,天津市,河东区', '112323', '13322223333', '13322223333', '0', '2', '432123');
INSERT INTO `t_address` VALUES ('56', '105', '222', '2609', '235', '山东省,临沂市,沂南县', '22', '1', '13333333333', '0', '15', '1');
INSERT INTO `t_address` VALUES ('57', '70', '123123', '2916', '275', '湖南省,长沙市,岳麓区', '123123', '07727777777', '15910576548', '0', '18', '100000');
INSERT INTO `t_address` VALUES ('58', '72', '林建茂', '41', '36', '北京,北京市,朝阳区', 'SOHO尚度', '077271111111', '15910576548', '0', '1', '100000');
INSERT INTO `t_address` VALUES ('59', '15', '12312', '1127', '73', '河北省,石家庄市,井陉矿区', '123123', '13422222222', '13422222222', '0', '3', '144444');
INSERT INTO `t_address` VALUES ('60', '15', 'test', '2630', '237', '山东省,聊城市,东阿县', '13423', '', '13333333333', '0', '15', '122222');
INSERT INTO `t_address` VALUES ('61', '75', 'ddd', '56', '40', '天津,天津市,河东区', 'eedf', '123123', '12312312312', '0', '2', '433123');
INSERT INTO `t_address` VALUES ('62', '79', 'BFF', '543', '45055', '海外,海外,荷兰', 'FAFASDFASFD', '0031234567890', '13587898765', '0', '35', '5655');
INSERT INTO `t_address` VALUES ('63', '7', '管庆芳', '41', '36', '北京,北京市,朝阳区', '尚都SOHO南塔803', '', '18311340545', '0', '1', '10000');
INSERT INTO `t_address` VALUES ('64', '81', 'KWG', '37', '36', '北京,北京市,东城区', 'SHANGDI', null, '18810004259', '0', '1', '100000');
INSERT INTO `t_address` VALUES ('65', '68', '孙', '56', '40', '天津,天津市,河东区', '放烟花', null, '15010033670', '0', '2', '100043');
INSERT INTO `t_address` VALUES ('67', '82', '张三', '41', '36', '北京,北京市,朝阳区', '朝阳大街145号', null, '13888888888', '0', '1', '153000');
INSERT INTO `t_address` VALUES ('68', '82', '李四', '37', '36', '北京,北京市,东城区', '东城大街188号', null, '13800000000', '0', '1', '100000');
INSERT INTO `t_address` VALUES ('69', '82', '王二', '1310', '85', '山西省,大同市,大同县', '大同街14号', null, '13800000000', '0', '4', '100000');
INSERT INTO `t_address` VALUES ('70', '82', '张三', '42', '36', '北京,北京市,丰台区', '丰台大街146号', null, '13800000000', '0', '1', '100000');
INSERT INTO `t_address` VALUES ('71', '82', '李四', '56', '40', '天津,天津市,河东区', '河东大街145号', null, '13800000000', '0', '2', '100000');
INSERT INTO `t_address` VALUES ('72', '82', '李四', '1129', '73', '河北省,石家庄市,平山县', '平山街11号', null, '13800000000', '1', '3', '100000');
INSERT INTO `t_address` VALUES ('73', '82', '李四', '37', '36', '北京,北京市,东城区', '东城大街145号', null, '13800000000', '0', '1', '100000');
INSERT INTO `t_address` VALUES ('74', '83', '刘刚', '2523', '224', '山东省,青岛市,即墨市', 'xx街道', null, '13233445566', '0', '15', '134567');
INSERT INTO `t_address` VALUES ('75', '88', 'aaa', '1127', '73', '河北省,石家庄市,井陉矿区', 'aaaa', null, '18538263378', '0', '3', '452009');
INSERT INTO `t_address` VALUES ('76', '93', 'test', '1531', '107', '辽宁省,沈阳市,铁西区', 'test', null, '13898826242', '0', '6', '110167');
INSERT INTO `t_address` VALUES ('77', '84', 'guan', '41', '36', '北京,北京市,朝阳区', '尚都SOHO南塔803', null, '18322090903', '0', '1', '100000');
INSERT INTO `t_address` VALUES ('78', '105', '111', '41', '36', '北京,北京市,朝阳区', '1111', null, '13333333333', '0', '1', '100000');
INSERT INTO `t_address` VALUES ('79', '96', 'lza', '56', '40', '天津,天津市,河东区', '2222', null, '18810028190', '0', '2', '100000');
INSERT INTO `t_address` VALUES ('80', '97', 'lza', '41', '36', '北京,北京市,朝阳区', '还不发货我无法和', null, '18810028190', '0', '1', '111111');
INSERT INTO `t_address` VALUES ('81', '101', 'zhuan1', '3146', '307', '广东省,潮州市,湘桥区', 'xxx', null, '15989289511', '0', '19', '121212');
INSERT INTO `t_address` VALUES ('82', '100', 'shenzhuan', '3028', '288', '湖南省,湘西土家族苗族自治州,保靖县', '21', '12121121212', '12121212121', '0', '18', '212212');
INSERT INTO `t_address` VALUES ('83', '105', '555', '55', '40', '天津天津市和平区', '555555', '5555', null, '1', '0', null);

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `summary` text COMMENT '摘要',
  `releaseDate` datetime DEFAULT NULL COMMENT '发布时间',
  `clickHit` int(11) DEFAULT NULL,
  `replyHit` int(11) DEFAULT '0',
  `content` longtext,
  `typeId` bigint(11) DEFAULT NULL,
  `keyWord` varchar(200) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `blogger_id` bigint(20) DEFAULT NULL,
  `typename` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `typeId` (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES ('87', 'Spring MVC注解故障追踪记', null, '2016-10-27 19:16:09', '0', '0', null, '1', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('88', '分布式系统互斥性与幂等性问题的分析与解决', null, '2016-10-27 19:19:11', '0', '0', null, '1', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('89', '大促活动前团购系统流量预算和容量评估', null, '2016-10-27 19:20:05', '0', '0', null, '1', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('90', '美团数据库运维自动化系统构建之路', null, '2016-10-27 19:20:31', '0', '0', null, '1', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('91', '美团外卖订单中心的演进', null, '2016-10-27 19:20:59', '0', '0', null, '1', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('92', '分布式队列编程优化篇', null, '2016-10-27 19:21:57', '0', '0', null, '1', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('93', '分布式队列编程：模型、实战', null, '2016-10-27 19:22:21', '0', '0', null, '1', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('94', 'Spring Boot快速入门', null, '2016-10-27 19:25:43', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('95', 'Spring Boot开发Web应用', null, '2016-10-27 19:26:11', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('96', 'Spring Boot工程结构推荐', null, '2016-10-27 19:26:30', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('97', 'Spring Boot构建RESTful API与单元测试', null, '2016-10-27 19:26:55', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('98', 'Spring Boot中使用Swagger2构建强大的RESTful API文档', null, '2016-10-27 19:27:20', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('99', 'Spring Boot中使用JdbcTemplate访问数据库', null, '2016-10-27 19:27:42', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('100', 'Spring Boot中使用Spring-data-jpa让数据访问更简单、更优雅', null, '2016-10-27 19:28:08', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('101', 'Spring Boot多数据源配置与使用', null, '2016-10-27 19:30:35', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('102', 'Spring Boot日志管理', null, '2016-10-27 19:30:55', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('103', 'Spring Boot中使用Redis数据库', null, '2016-10-27 19:31:21', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('104', 'Spring Boot中使用MongoDB数据库', null, '2016-10-27 19:31:42', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('105', 'Spring Boot中Web应用的统一异常处理', null, '2016-10-27 19:32:07', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('106', 'Spring Boot属性配置文件详解', null, '2016-10-27 19:32:30', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('107', 'Spring Boot中使用@Async实现异步调用', null, '2016-10-27 19:32:55', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('108', 'Spring Boot中对log4j进行多环境不同日志级别的控制', null, '2016-10-27 19:33:15', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('109', 'Spring Boot中使用AOP统一处理Web请求日志', null, '2016-10-27 19:33:44', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('110', 'Spring Boot中的事务管理', null, '2016-10-27 19:34:08', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('111', 'Spring Boot中使用Spring Security进行安全控制', null, '2016-10-27 19:34:31', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('112', 'Spring Boot中的缓存支持（一）注解配置与EhCache使用', null, '2016-10-27 19:34:58', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('113', 'Spring Boot中的缓存支持（一）注解配置与EhCache使用', null, '2016-10-27 19:35:23', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('114', 'Spring Boot中的缓存支持（二）使用Redis做集中式缓存', null, '2016-10-27 19:35:52', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('115', 'Spring Boot整合MyBatis', null, '2016-10-27 19:36:18', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('116', 'Spring Boot中使用MyBatis注解配置详解', null, '2016-10-27 19:36:38', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('117', 'Spring Boot中使用RabbitMQ', null, '2016-10-27 19:37:02', '0', '0', null, '2', '', null, '3', null, null);
INSERT INTO `t_blog` VALUES ('118', 'zscat11111', null, '2016-11-03 13:50:57', null, null, null, '1', null, '0', '4', null, null);
INSERT INTO `t_blog` VALUES ('119', 'zscat2222', null, '2016-11-03 13:52:39', null, '0', null, '1', null, '0', '4', null, null);
INSERT INTO `t_blog` VALUES ('120', 'zsCat22', null, '2016-11-03 13:53:05', null, '0', null, '1', null, '0', '4', null, null);
INSERT INTO `t_blog` VALUES ('121', 'zsCat22', null, '2016-11-03 13:53:20', null, '0', null, '1', null, '0', '4', null, null);
INSERT INTO `t_blog` VALUES ('122', 'zscat3', null, '2016-11-03 13:55:21', null, '0', null, '1', null, '0', '4', null, null);
INSERT INTO `t_blog` VALUES ('125', 'ZSCAT分布式框架是啥？', null, '2016-11-04 13:30:13', '0', '0', null, '3', '', null, '1', null, null);
INSERT INTO `t_blog` VALUES ('126', '演示地址', null, '2016-11-04 13:39:17', '0', '0', null, null, '', null, '1', null, null);

-- ----------------------------
-- Table structure for t_blogger
-- ----------------------------
DROP TABLE IF EXISTS `t_blogger`;
CREATE TABLE `t_blogger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `profile` text,
  `nickName` varchar(50) DEFAULT NULL,
  `sign` varchar(100) DEFAULT NULL,
  `imageName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blogger
-- ----------------------------
INSERT INTO `t_blogger` VALUES ('1', 'zhuan', '4ea43719b3aaf171beb332da787a47d3', null, '转', null, null);
INSERT INTO `t_blogger` VALUES ('2', 'shen', 'e6449d1935ae1119a02d6d1f83158587', null, '神', null, null);
INSERT INTO `t_blogger` VALUES ('5', '123456', '5f1d7a84db00d2fce00b31a7fc73224f', null, '123456', null, null);

-- ----------------------------
-- Table structure for t_blogtype
-- ----------------------------
DROP TABLE IF EXISTS `t_blogtype`;
CREATE TABLE `t_blogtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(30) DEFAULT NULL,
  `orderNo` int(11) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记(0活null 正常 1,删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blogtype
-- ----------------------------
INSERT INTO `t_blogtype` VALUES ('1', '美团技术', '2', null, null, null, null, null, '0');
INSERT INTO `t_blogtype` VALUES ('2', 'springboot', '1', null, null, null, null, null, '0');
INSERT INTO `t_blogtype` VALUES ('3', 'zscat', '3', null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for t_blog_template
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_template`;
CREATE TABLE `t_blog_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `summary` text COMMENT '摘要',
  `releaseDate` datetime DEFAULT NULL COMMENT '发布时间',
  `clickHit` int(11) DEFAULT NULL,
  `replyHit` int(11) DEFAULT NULL,
  `content` longtext,
  `keyWord` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog_template
-- ----------------------------
INSERT INTO `t_blog_template` VALUES ('118', '女生博客模板', '女生博客模板', '2016-10-28 10:47:50', '0', '0', '<p>女生博客模板</p><p><img src=\"/pig/static/ueditor/jsp/upload1/20161028/36981477622865805.jpg\" title=\"04.jpg\"/></p>,', 'front/blog/indexns');
INSERT INTO `t_blog_template` VALUES ('119', '个人博客', '', '2016-10-28 11:02:13', '0', '0', '<p><img src=\"/pig/static/ueditor/jsp/upload1/20161028/361477623729685.png\" title=\"QQ图片20161028110015.png\"/></p>,', 'front/blog/indexone');
INSERT INTO `t_blog_template` VALUES ('120', '草根博客', '', '2016-10-28 11:02:58', '0', '0', '<p><img src=\"/pig/static/ueditor/jsp/upload1/20161028/48901477623774727.png\" title=\"QQ图片20161028110044.png\"/></p>,', 'front/blog/indexc');
INSERT INTO `t_blog_template` VALUES ('121', 'java1234', '', '2016-10-28 11:05:32', '0', '0', '<p><img src=\"/pig/static/ueditor/jsp/upload1/20161028/61671477623928764.png\" title=\"QQ图片20161028110509.png\"/></p>,', 'front/blog/index');

-- ----------------------------
-- Table structure for t_cart
-- ----------------------------
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE `t_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goodsid` bigint(20) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  `goodsname` varchar(255) DEFAULT NULL,
  `price` varchar(10) DEFAULT NULL,
  `count` int(11) DEFAULT NULL COMMENT '1',
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cart
-- ----------------------------

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `userIp` varchar(50) DEFAULT NULL,
  `blogId` int(11) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `commentDate` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `blogger_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `blogger` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('1', '111', '85', '鬼才知道这些鬼东西', '2016-11-01 12:49:39', '1', '3', 'Storm 的可靠性保证测试', null);
INSERT INTO `t_comment` VALUES ('2', null, '86', 'http://localhost:8081/pig/front/blog/shequDetail/85  测试', '2016-11-03 13:15:22', null, '4', '分布式会话跟踪系统架构设计与实践', null);
INSERT INTO `t_comment` VALUES ('3', '0:0:0:0:0:0:0:1', '122', '我来回得zacat3', '2016-11-03 13:56:10', null, '3', 'zscat3', '转');
INSERT INTO `t_comment` VALUES ('4', '0:0:0:0:0:0:0:1', '118', '123456', '2017-04-10 20:05:57', null, null, 'zscat11111', null);

-- ----------------------------
-- Table structure for t_consult
-- ----------------------------
DROP TABLE IF EXISTS `t_consult`;
CREATE TABLE `t_consult` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '咨询编号',
  `goods_id` bigint(11) unsigned DEFAULT '0' COMMENT '商品编号',
  `cgoods_name` varchar(100) NOT NULL COMMENT '商品名称',
  `member_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '咨询发布者会员编号(0：游客)',
  `cmember_name` varchar(100) DEFAULT NULL COMMENT '会员名称',
  `store_id` bigint(11) unsigned NOT NULL DEFAULT '0' COMMENT '店铺编号',
  `email` varchar(255) DEFAULT NULL COMMENT '咨询发布者邮箱',
  `consult_content` varchar(255) DEFAULT NULL COMMENT '咨询内容',
  `consult_addtime` bigint(13) DEFAULT NULL COMMENT '咨询添加时间',
  `consult_reply` varchar(255) DEFAULT NULL COMMENT '咨询回复内容',
  `consult_reply_time` bigint(13) DEFAULT NULL COMMENT '咨询回复时间',
  `isanonymous` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0表示不匿名 1表示匿名',
  `is_del` tinyint(1) DEFAULT NULL,
  `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `goods_id` (`goods_id`) USING BTREE,
  KEY `seller_id` (`store_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='产品咨询表';

-- ----------------------------
-- Records of t_consult
-- ----------------------------
INSERT INTO `t_consult` VALUES ('4', '4', 'æäºå¨åè³æº', '5', 'gyh99', '10', null, 'è¿ä¸ªè³æºå¤å°é±', '20150803102942', '你的麻烦你给return ', null, '0', '0', null, null);
INSERT INTO `t_consult` VALUES ('5', '30', '可情小美 欧美范春夏款时尚OL职业女超高跟鞋 糖果色漆皮尖头夜店性感', '0', '匿名', '9', null, '1111', '20150822101401', null, null, '1', '0', null, null);
INSERT INTO `t_consult` VALUES ('6', '30', '可情小美 欧美范春夏款时尚OL职业女超高跟鞋 糖果色漆皮尖头夜店性感', '0', '匿名', '9', null, '你好', '20150822101414', null, null, '1', '0', null, null);
INSERT INTO `t_consult` VALUES ('7', '36', ' 琪可朵高跟鞋尖头真皮蝴蝶结2015新款婚鞋  01甜美粉色 ', '0', '匿名', '9', null, '[', '20150827205953', null, null, '1', '0', null, null);
INSERT INTO `t_consult` VALUES ('8', '5', 'iphone6', '28', '吴世勋', '10', null, '是国行吗？', '20150831142848', '是的哦，亲~~', null, '0', '0', null, null);
INSERT INTO `t_consult` VALUES ('9', '32', '琪可朵高跟鞋尖头真皮细跟蝴蝶结2015新款女鞋 03淡紫色 40', '28', '吴世勋', '9', null, '紫色色差大吗？是什么紫色？', '20150901093741', null, null, '0', '0', null, null);
INSERT INTO `t_consult` VALUES ('10', '32', '琪可朵高跟鞋尖头真皮细跟蝴蝶结2015新款女鞋 03淡紫色 40', '28', '吴世勋', '9', null, '对方水电费', '20150906165708', null, null, '0', '1', null, null);
INSERT INTO `t_consult` VALUES ('11', '32', '琪可朵高跟鞋尖头真皮细跟蝴蝶结2015新款女鞋 03淡紫色 40', '28', '吴世勋', '9', null, '双方的所发生的', '20150906165714', null, null, '0', '1', null, null);
INSERT INTO `t_consult` VALUES ('12', '26', ' 凡牧2015夏装新款棉麻短袖连衣裙女韩版修身', '16', 'zrh', '9', null, '裙子如何', '20150906165850', '保证正品哦，亲', null, '0', '0', null, null);
INSERT INTO `t_consult` VALUES ('13', '25', '碧月茹香 2015夏新款文艺棉麻宽松大码连衣裙 617绿色 XXL', '0', '匿名', '9', null, 'ddd', '20150924095004', null, null, '1', '0', '20150924095004', null);
INSERT INTO `t_consult` VALUES ('14', '25', '碧月茹香 2015夏新款文艺棉麻宽松大码连衣裙 617绿色 XXL', '0', '匿名', '9', null, 'sfsf', '20150924095012', null, null, '1', '0', '20150924095012', null);
INSERT INTO `t_consult` VALUES ('15', '11', 'Apple MacBook Air MJVE2CH/A 13.3英寸宽屏笔记本电脑 128GB 闪存', '14', 'demo', '9', null, '啊实打实', '20151008165249', null, null, '0', '0', '20151008165249', null);
INSERT INTO `t_consult` VALUES ('16', '11', 'Apple MacBook Air MJVE2CH/A 13.3��?��?��?????��????��?��?????�̨�?? 128GB ��?a?-?', '0', '匿名', '9', null, null, '20151008165249', null, null, '1', '0', '20151008165249', null);
INSERT INTO `t_consult` VALUES ('17', '72', '111', '0', '匿名', '9', null, 'ni hao', '20151009142454', '2123123', '1444704656508', '1', '0', '20151009142454', '1444704656508');
INSERT INTO `t_consult` VALUES ('18', '76', 'sony', '14', 'demo', '12', null, '11111', '20151010103337', null, null, '0', '0', '20151010103337', null);
INSERT INTO `t_consult` VALUES ('19', '35', ' bubufeifei2015夏季新款高跟鞋女 水钻细跟女鞋婚鞋单鞋 珍珠白', '84', 'guan2015', '9', null, 'ghijvbghj空调同 宋瑞', '20151016094325', null, null, '0', '0', '20151016094325', null);
INSERT INTO `t_consult` VALUES ('20', '35', ' bubufeifei2015夏季新款高跟鞋女 水钻细跟女鞋婚鞋单鞋 珍珠白', '84', 'guan2015', '9', null, '心狠我我你为了她你同同虐酷我同他们拉我', '20151016094359', null, null, '0', '0', '20151016094359', null);

-- ----------------------------
-- Table structure for t_favorites
-- ----------------------------
DROP TABLE IF EXISTS `t_favorites`;
CREATE TABLE `t_favorites` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '会员ID',
  `fav_id` bigint(10) unsigned NOT NULL COMMENT '收藏ID',
  `fav_type` varchar(20) DEFAULT NULL COMMENT '收藏类型',
  `fav_time` bigint(13) DEFAULT NULL COMMENT '收藏时间',
  `member_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='买家收藏表';

-- ----------------------------
-- Records of t_favorites
-- ----------------------------
INSERT INTO `t_favorites` VALUES ('1', '5', null, null, '105');
INSERT INTO `t_favorites` VALUES ('2', '6', null, null, '105');

-- ----------------------------
-- Table structure for t_floor
-- ----------------------------
DROP TABLE IF EXISTS `t_floor`;
CREATE TABLE `t_floor` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `name` varchar(100) DEFAULT NULL COMMENT '类型名称',
  `typesort` int(3) DEFAULT '0' COMMENT '商品类型排序',
  `parent_id` bigint(10) DEFAULT NULL COMMENT '父id',
  `parent_ids` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `del_flag` varchar(255) DEFAULT NULL,
  `advimg` varchar(255) DEFAULT NULL,
  `advurl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品类型表';

-- ----------------------------
-- Records of t_floor
-- ----------------------------
INSERT INTO `t_floor` VALUES ('1', 'zsCat系列', '0', '0', null, '每一个zscat项目都有一个故事,每一个zscat项目都有一个故事', null, 'main.png', '');
INSERT INTO `t_floor` VALUES ('3', 'demo', '3', null, null, 'demole,demole', null, 'sys.png', null);

-- ----------------------------
-- Table structure for t_floor_product
-- ----------------------------
DROP TABLE IF EXISTS `t_floor_product`;
CREATE TABLE `t_floor_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `productid` bigint(20) DEFAULT NULL,
  `floorid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_floor_product
-- ----------------------------
INSERT INTO `t_floor_product` VALUES ('15', '34', '1');
INSERT INTO `t_floor_product` VALUES ('16', '33', '1');
INSERT INTO `t_floor_product` VALUES ('17', '36', '1');
INSERT INTO `t_floor_product` VALUES ('18', '35', '1');
INSERT INTO `t_floor_product` VALUES ('19', '37', '1');
INSERT INTO `t_floor_product` VALUES ('20', '38', '1');
INSERT INTO `t_floor_product` VALUES ('27', '39', '3');
INSERT INTO `t_floor_product` VALUES ('28', '40', '3');
INSERT INTO `t_floor_product` VALUES ('29', '41', '3');
INSERT INTO `t_floor_product` VALUES ('30', '43', '3');
INSERT INTO `t_floor_product` VALUES ('31', '44', '3');
INSERT INTO `t_floor_product` VALUES ('32', '35', '3');

-- ----------------------------
-- Table structure for t_goodsorder
-- ----------------------------
DROP TABLE IF EXISTS `t_goodsorder`;
CREATE TABLE `t_goodsorder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goodsid` bigint(20) DEFAULT NULL,
  `orderid` bigint(20) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goodsorder
-- ----------------------------
INSERT INTO `t_goodsorder` VALUES ('3', '36', '4', null);
INSERT INTO `t_goodsorder` VALUES ('4', '35', '7', null);
INSERT INTO `t_goodsorder` VALUES ('5', '36', '8', null);
INSERT INTO `t_goodsorder` VALUES ('6', '34', '9', null);
INSERT INTO `t_goodsorder` VALUES ('7', '34', '10', null);
INSERT INTO `t_goodsorder` VALUES ('8', '33', '11', null);
INSERT INTO `t_goodsorder` VALUES ('9', '34', '12', null);
INSERT INTO `t_goodsorder` VALUES ('10', '34', '13', null);
INSERT INTO `t_goodsorder` VALUES ('11', '34', '14', null);
INSERT INTO `t_goodsorder` VALUES ('12', '34', '15', null);
INSERT INTO `t_goodsorder` VALUES ('13', '36', '16', null);
INSERT INTO `t_goodsorder` VALUES ('14', '36', '17', null);
INSERT INTO `t_goodsorder` VALUES ('15', '36', '18', null);
INSERT INTO `t_goodsorder` VALUES ('16', '34', '19', null);
INSERT INTO `t_goodsorder` VALUES ('17', '35', '20', null);
INSERT INTO `t_goodsorder` VALUES ('18', '34', '22', null);
INSERT INTO `t_goodsorder` VALUES ('19', '36', '23', null);
INSERT INTO `t_goodsorder` VALUES ('20', '36', '24', null);
INSERT INTO `t_goodsorder` VALUES ('21', '36', '25', null);
INSERT INTO `t_goodsorder` VALUES ('22', '35', '26', null);
INSERT INTO `t_goodsorder` VALUES ('23', '36', '27', null);
INSERT INTO `t_goodsorder` VALUES ('24', '36', '28', null);
INSERT INTO `t_goodsorder` VALUES ('25', '33', '31', null);
INSERT INTO `t_goodsorder` VALUES ('26', '34', '32', null);
INSERT INTO `t_goodsorder` VALUES ('27', '33', '33', null);
INSERT INTO `t_goodsorder` VALUES ('28', '36', '33', null);
INSERT INTO `t_goodsorder` VALUES ('29', '35', '37', null);
INSERT INTO `t_goodsorder` VALUES ('30', '36', '38', null);
INSERT INTO `t_goodsorder` VALUES ('31', '34', '39', null);
INSERT INTO `t_goodsorder` VALUES ('32', '35', '40', null);
INSERT INTO `t_goodsorder` VALUES ('33', '35', '41', null);
INSERT INTO `t_goodsorder` VALUES ('34', '35', '42', null);
INSERT INTO `t_goodsorder` VALUES ('35', '34', '43', null);
INSERT INTO `t_goodsorder` VALUES ('36', '34', '44', null);
INSERT INTO `t_goodsorder` VALUES ('37', '36', '45', null);
INSERT INTO `t_goodsorder` VALUES ('38', '35', '46', null);
INSERT INTO `t_goodsorder` VALUES ('39', '36', '47', null);
INSERT INTO `t_goodsorder` VALUES ('40', '33', '48', null);
INSERT INTO `t_goodsorder` VALUES ('41', '33', '49', null);
INSERT INTO `t_goodsorder` VALUES ('42', '35', '50', null);
INSERT INTO `t_goodsorder` VALUES ('43', '33', '51', null);
INSERT INTO `t_goodsorder` VALUES ('44', '33', '52', null);
INSERT INTO `t_goodsorder` VALUES ('45', '33', '53', null);
INSERT INTO `t_goodsorder` VALUES ('46', '35', '54', null);
INSERT INTO `t_goodsorder` VALUES ('47', '33', '56', null);
INSERT INTO `t_goodsorder` VALUES ('48', '33', '57', null);
INSERT INTO `t_goodsorder` VALUES ('49', '34', '58', null);
INSERT INTO `t_goodsorder` VALUES ('50', '36', null, null);
INSERT INTO `t_goodsorder` VALUES ('51', '34', null, null);
INSERT INTO `t_goodsorder` VALUES ('52', '33', null, null);
INSERT INTO `t_goodsorder` VALUES ('53', '33', null, null);
INSERT INTO `t_goodsorder` VALUES ('54', '33', null, null);
INSERT INTO `t_goodsorder` VALUES ('55', '34', null, null);
INSERT INTO `t_goodsorder` VALUES ('56', '34', null, null);
INSERT INTO `t_goodsorder` VALUES ('57', '33', null, null);
INSERT INTO `t_goodsorder` VALUES ('58', '33', null, null);
INSERT INTO `t_goodsorder` VALUES ('59', '33', null, null);
INSERT INTO `t_goodsorder` VALUES ('60', '33', null, null);

-- ----------------------------
-- Table structure for t_link
-- ----------------------------
DROP TABLE IF EXISTS `t_link`;
CREATE TABLE `t_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `linkName` varchar(100) DEFAULT NULL,
  `linkUrl` varchar(200) DEFAULT NULL,
  `orderNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_link
-- ----------------------------
INSERT INTO `t_link` VALUES ('1', 'zscat官网', 'http://blog.csdn.net/a1439226817/article/details/68483563', '1');
INSERT INTO `t_link` VALUES ('2', 'zscat商城', 'http://blog.csdn.net/a1439226817/article/details/64121666', '2');
INSERT INTO `t_link` VALUES ('3', 'zscat内容管理系统', 'https://shop150554856.taobao.com/?spm=2013.1.1000126.d21.yzAhaP', '3');
INSERT INTO `t_link` VALUES ('4', 'zscat后台', 'http://zscat.top/', '4');
INSERT INTO `t_link` VALUES ('5', 'dangdang', 'http://blog.csdn.net/a1439226817/article/details/64437915', '5');
INSERT INTO `t_link` VALUES ('6', 'zscat店铺', 'https://shop150554856.taobao.com/?spm=2013.1.1000126.d21.yzAhaP', '6');
INSERT INTO `t_link` VALUES ('7', '百度1', 'https://www.baidu.com1/', '8');

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `addtime` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `trueName` varchar(255) DEFAULT NULL,
  `gold` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_member
-- ----------------------------
INSERT INTO `t_member` VALUES ('2', '56e9113e-42e0-4262-a841-a98c3725adc0.jpg', 'xxxx', '2016-11-16 00:00:00', null, null, null, null, null, null, null);
INSERT INTO `t_member` VALUES ('3', 'fbb4679e-6df1-4893-abd4-66b2dabbe45e.jpg', '111', '2016-11-02 00:00:00', null, null, null, null, null, null, null);
INSERT INTO `t_member` VALUES ('4', '3a5f3d94-00cb-46d6-a11b-dac7fdf6bfd6.png', '22', '2016-11-09 00:00:00', null, null, null, null, null, null, null);
INSERT INTO `t_member` VALUES ('5', '7bb95469-9649-4bf9-b58d-d34a4a4cd3bb.jpg', '11', '2016-11-01 00:00:00', null, null, null, null, null, null, null);
INSERT INTO `t_member` VALUES ('6', '90748514-bda3-4017-a60d-8f5968b91f73.jpg', '111', '2016-11-02 00:00:00', null, null, null, null, null, null, null);
INSERT INTO `t_member` VALUES ('27', 'admin', '86f3059b228c8acf99e69734b6bb32cc', '2016-11-01 00:00:00', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `totalprice` decimal(10,0) DEFAULT NULL COMMENT '总价格',
  `totalcount` int(255) DEFAULT NULL COMMENT '总个数',
  `ordersn` varchar(255) DEFAULT NULL,
  `status` int(255) DEFAULT '1' COMMENT '1',
  `userid` bigint(11) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `paymentid` bigint(11) DEFAULT NULL,
  `addressid` bigint(20) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `usercontent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('53', '22', '1', 'e5f621e2-e601-46bd-a', '1', '4', '2017-01-13 23:30:50', '2', '1', 'shen', '1');
INSERT INTO `t_order` VALUES ('54', '22', '1', '83d8be8c-f12f-4f92-a2db', '2', '4', '2017-01-13 23:35:25', '1', '1', 'shen', '1');
INSERT INTO `t_order` VALUES ('56', '11', '1', '03122304-63f0-42f7-', '3', '4', '2017-01-13 23:39:13', '2', '1', 'shen', '1');
INSERT INTO `t_order` VALUES ('57', '2', '1', '58d1fec3-3709-4c8a-ad02', '4', '4', '2017-01-13 23:51:41', '2', '1', 'shen', '1');
INSERT INTO `t_order` VALUES ('58', '0', '1', 'd373e88a-b338-4c7d-8453', '9', '4', '2017-01-14 10:04:27', '1', '1', 'shen', '1');
INSERT INTO `t_order` VALUES ('60', '0', '1', 'df8f27e0-d32b-445a-', '2', '4', '2017-01-14 11:20:23', '1', '1', 'shen', '1');

-- ----------------------------
-- Table structure for t_order_log
-- ----------------------------
DROP TABLE IF EXISTS `t_order_log`;
CREATE TABLE `t_order_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '订单处理历史索引id',
  `order_id` bigint(11) NOT NULL COMMENT '订单id',
  `order_state` varchar(20) NOT NULL COMMENT '订单状态信息',
  `change_state` varchar(20) DEFAULT NULL COMMENT '下一步订单状态信息',
  `state_info` varchar(20) NOT NULL COMMENT '订单状态描述',
  `create_time` bigint(13) DEFAULT NULL COMMENT '处理时间',
  `operator` varchar(30) NOT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=845 DEFAULT CHARSET=utf8 COMMENT='订单处理历史表';

-- ----------------------------
-- Records of t_order_log
-- ----------------------------
INSERT INTO `t_order_log` VALUES ('788', '4', '1', null, '提交订单', '1483803815006', 'shen');
INSERT INTO `t_order_log` VALUES ('791', '7', '1', null, '提交订单', '1483804513480', 'shen');
INSERT INTO `t_order_log` VALUES ('792', '8', '1', null, '提交订单', '1483804583773', 'shen');
INSERT INTO `t_order_log` VALUES ('793', '9', '1', null, '提交订单', '1483804663554', 'shen');
INSERT INTO `t_order_log` VALUES ('794', '10', '1', null, '提交订单', '1483804723362', 'shen');
INSERT INTO `t_order_log` VALUES ('795', '11', '1', null, '提交订单', '1483805141748', 'shen');
INSERT INTO `t_order_log` VALUES ('796', '12', '1', null, '提交订单', '1483805551294', 'shen');
INSERT INTO `t_order_log` VALUES ('797', '13', '1', null, '提交订单', '1483805853705', 'shen');
INSERT INTO `t_order_log` VALUES ('798', '14', '1', null, '提交订单', '1483806080232', 'shen');
INSERT INTO `t_order_log` VALUES ('799', '15', '1', null, '提交订单', '1483806374875', 'shen');
INSERT INTO `t_order_log` VALUES ('800', '16', '1', null, '提交订单', '1484046509585', 'shen');
INSERT INTO `t_order_log` VALUES ('801', '17', '1', null, '提交订单', '1484046722016', 'shen');
INSERT INTO `t_order_log` VALUES ('802', '18', '1', null, '提交订单', '1484046800575', 'shen');
INSERT INTO `t_order_log` VALUES ('803', '19', '1', null, '提交订单', '1484048320003', 'shen');
INSERT INTO `t_order_log` VALUES ('804', '20', '1', null, '提交订单', '1484049300946', 'shen');
INSERT INTO `t_order_log` VALUES ('806', '22', '1', null, '提交订单', '1484049828047', 'shen');
INSERT INTO `t_order_log` VALUES ('807', '23', '1', null, '提交订单', '1484049913398', 'shen');
INSERT INTO `t_order_log` VALUES ('808', '24', '1', null, '提交订单', '1484050043597', 'shen');
INSERT INTO `t_order_log` VALUES ('809', '25', '1', null, '提交订单', '1484050307631', 'shen');
INSERT INTO `t_order_log` VALUES ('810', '26', '1', null, '提交订单', '1484051296588', 'shen');
INSERT INTO `t_order_log` VALUES ('811', '27', '1', null, '提交订单', '1484051412266', 'shen');
INSERT INTO `t_order_log` VALUES ('812', '28', '1', null, '提交订单', '1484051500695', 'shen');
INSERT INTO `t_order_log` VALUES ('815', '31', '1', null, '提交订单', '1484051703553', 'shen');
INSERT INTO `t_order_log` VALUES ('816', '32', '1', null, '提交订单', '1484051802388', 'shen');
INSERT INTO `t_order_log` VALUES ('817', '33', '1', null, '提交订单', '1484315540959', 'shen');
INSERT INTO `t_order_log` VALUES ('821', '37', '1', null, '提交订单', '1484315841469', 'shen');
INSERT INTO `t_order_log` VALUES ('822', '38', '1', null, '提交订单', '1484316076083', 'shen');
INSERT INTO `t_order_log` VALUES ('823', '39', '1', null, '提交订单', '1484316264682', 'shen');
INSERT INTO `t_order_log` VALUES ('824', '40', '1', null, '提交订单', '1484316439996', 'shen');
INSERT INTO `t_order_log` VALUES ('825', '41', '1', null, '提交订单', '1484316625413', 'shen');
INSERT INTO `t_order_log` VALUES ('826', '42', '1', null, '提交订单', '1484317764499', 'shen');
INSERT INTO `t_order_log` VALUES ('827', '43', '1', null, '提交订单', '1484318223678', 'shen');
INSERT INTO `t_order_log` VALUES ('828', '44', '1', null, '提交订单', '1484318491706', 'shen');
INSERT INTO `t_order_log` VALUES ('829', '45', '1', null, '提交订单', '1484318617169', 'shen');
INSERT INTO `t_order_log` VALUES ('830', '46', '1', null, '提交订单', '1484318928232', 'shen');
INSERT INTO `t_order_log` VALUES ('831', '47', '1', null, '提交订单', '1484320745393', 'shen');
INSERT INTO `t_order_log` VALUES ('832', '48', '1', null, '提交订单', '1484320800712', 'shen');
INSERT INTO `t_order_log` VALUES ('833', '49', '1', null, '提交订单', '1484320847486', 'shen');
INSERT INTO `t_order_log` VALUES ('834', '50', '1', null, '提交订单', '1484321146204', 'shen');
INSERT INTO `t_order_log` VALUES ('835', '51', '1', null, '提交订单', '1484321308134', 'shen');
INSERT INTO `t_order_log` VALUES ('836', '52', '1', null, '提交订单', '1484321360583', 'shen');
INSERT INTO `t_order_log` VALUES ('837', '53', '1', null, '提交订单', '1484321450966', 'shen');
INSERT INTO `t_order_log` VALUES ('838', '54', '1', null, '提交订单', '1484321725223', 'shen');
INSERT INTO `t_order_log` VALUES ('840', '56', '1', null, '提交订单', '1484321953147', 'shen');
INSERT INTO `t_order_log` VALUES ('841', '57', '1', null, '提交订单', '1484322701812', 'shen');
INSERT INTO `t_order_log` VALUES ('842', '58', '1', null, '提交订单', '1484359467424', 'shen');
INSERT INTO `t_order_log` VALUES ('844', '60', '1', null, '提交订单', '1484364023272', 'shen');

-- ----------------------------
-- Table structure for t_payment
-- ----------------------------
DROP TABLE IF EXISTS `t_payment`;
CREATE TABLE `t_payment` (
  `id` bigint(1) unsigned NOT NULL AUTO_INCREMENT COMMENT '支付索引id',
  `payment_code` char(10) NOT NULL COMMENT '支付代码名称',
  `payment_name` char(10) NOT NULL COMMENT '支付名称',
  `payment_config` text COMMENT '支付接口配置信息',
  `payment_state` enum('0','1') DEFAULT NULL COMMENT '接口状态0禁用1启用',
  `is_del` tinyint(4) DEFAULT '0' COMMENT '是否删除0:未删除;1:已删除',
  `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '修改时间',
  `payment_logo` varchar(55) DEFAULT NULL COMMENT '支付方式logo',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='支付方式表';

-- ----------------------------
-- Records of t_payment
-- ----------------------------
INSERT INTO `t_payment` VALUES ('4', 'HDFK', '货到付款', null, '0', '0', null, '1477439942147', '/upload/img/paymentlogo/1472811585098.jpg');
INSERT INTO `t_payment` VALUES ('5', 'ZFB', '支付宝', null, '1', '1', null, '1471342291209', '/upload/img/paymentlogo/1444315841296png');
INSERT INTO `t_payment` VALUES ('6', 'CFT', '财付通', null, '0', '1', null, '1472811599287', '/upload/img/paymentlogo/1472811598068.jpg');
INSERT INTO `t_payment` VALUES ('7', 'YL', '银联', null, '0', '1', null, '1477560997884', '/upload/img/paymentlogo/1438941545519.jpg');
INSERT INTO `t_payment` VALUES ('8', 'YCK', '预存款', null, '0', '0', null, '1477560976598', '/upload/img/paymentlogo/1472811617333.jpg');
INSERT INTO `t_payment` VALUES ('9', 'wx', '微信支付', null, '0', '1', '1472810647292', '1477439911735', '/upload/img/paymentlogo/1477439826026.png');
INSERT INTO `t_payment` VALUES ('10', 'weiscan', '微信支付', null, '1', '0', '1477439891594', null, '/upload/img/paymentlogo/1477439889314.png');
INSERT INTO `t_payment` VALUES ('11', 'YL', '银联', null, '1', '0', '1477561111787', '1477561183135', '/upload/img/paymentlogo/1477561141382.jpg');

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `remark` longtext,
  `summary` longtext,
  `clickHit` int(255) DEFAULT '0' COMMENT '0',
  `typeid` bigint(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `typename` varchar(255) DEFAULT NULL,
  `type` bigint(255) DEFAULT NULL,
  `orderby` varchar(255) DEFAULT NULL,
  `prices` varchar(255) DEFAULT NULL,
  `imgmore` varchar(1000) DEFAULT NULL,
  `create_by` bigint(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记(0活null 正常 1,删除)',
  `replyhit` int(11) DEFAULT '0' COMMENT '评价量',
  `sellhit` int(11) DEFAULT '0' COMMENT '销售量',
  `iscom` int(255) DEFAULT NULL COMMENT '1推荐，2不推荐',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES ('33', 'zscat分布式框架', 'spring,springmvc ,通用mapper, dubbo,zookeep', null, null, '189', '11', 'zscat.png', '框架', '246', null, '50', null, '2', '2016-12-23 12:07:18', '0', '0', '0', null);
INSERT INTO `t_product` VALUES ('34', 'zscat商城', 'mybatisplus ,ssm,dubbo', null, null, '148', '11', 'shop.png', '框架', '238', null, '200', null, '2', '2016-12-23 12:54:02', '0', '0', '0', null);
INSERT INTO `t_product` VALUES ('35', 'zsShop商城', 'spring,springmvc ,通用mapper, dubbo,zookeep', null, null, '96', '10', 'zsshop.jpg', '框架', '238', null, '150', null, '2', '2016-12-23 12:58:40', '0', '0', '0', null);
INSERT INTO `t_product` VALUES ('36', 'zscatPlus', 'spring,springmvc ,通用mapper, dubbo,zookeep', null, null, '94', '11', 'H.png', '框架', '247', null, '50', null, '2', '2016-12-23 12:59:55', '0', '0', '0', '1');
INSERT INTO `t_product` VALUES ('37', 'zscat社区', 'layui,ssm', null, null, '20', '10', 'blog.png', '框架', '245', null, '20', null, '2', '2017-01-24 13:04:44', '0', '0', '0', null);
INSERT INTO `t_product` VALUES ('38', 'zsCms', 'ssm', null, null, '18', '10', 'cms.png', '框架', '246', null, '30', null, '2', '2016-12-23 13:06:55', '0', '0', '0', '2');
INSERT INTO `t_product` VALUES ('39', 'zscatLte分布式框架', 'spring', null, null, '49', '11', 'zscatlte.png', '框架', '247', null, '50', null, '34', '2016-12-27 18:08:57', '0', '0', '0', '1');
INSERT INTO `t_product` VALUES ('40', 'java后台框架', 'hibernate', null, null, '41', '10', 'blog.png', '框架', '247', null, '1', null, '34', '2017-01-24 18:09:58', '0', '0', '0', null);
INSERT INTO `t_product` VALUES ('41', 'ssh后台框架_activiy5', 'ssh', null, null, '24', '10', 'home1.png', '框架', '247', null, '50', null, '34', '2016-12-27 18:12:03', '0', '0', '0', null);
INSERT INTO `t_product` VALUES ('42', '因库网校系统', 'ssm', null, null, '5', '10', '1index.png', '框架', '238', null, '1', null, '34', '2016-12-27 18:16:38', '0', '0', '0', '1');
INSERT INTO `t_product` VALUES ('43', 'jeesite后台框架', '', null, null, '16', '10', 'zscat.png', '框架', '247', null, '', null, '34', '2016-12-27 18:21:25', '0', '0', '0', null);
INSERT INTO `t_product` VALUES ('44', 'zscat_妹子后台框架', 'ssm', null, null, '30', '10', '1meizi.png', '框架', '247', null, '55', null, '34', '2016-12-27 18:27:07', '0', '0', '0', null);
INSERT INTO `t_product` VALUES ('49', '华迪空气净化器KJ420P-HD280', '', null, null, '11', '13', '1智能.jpg', '智能家居', '250', null, '3980', null, '72', '2017-01-06 13:22:42', '0', '0', '0', '2');
INSERT INTO `t_product` VALUES ('50', '华迪智能空气净化器', '', null, null, '11', '13', '1智能.jpg', '智能家居', '250', null, '2280', null, '72', '2017-01-06 13:24:02', '0', '0', '0', null);
INSERT INTO `t_product` VALUES ('51', '华迪智能网关HDG6200', '', null, null, '0', '13', '3智能.png', '智能家居', '251', null, '339', null, '72', '2017-01-06 13:25:33', '0', '0', '0', '2');
INSERT INTO `t_product` VALUES ('52', '华迪智能插排', '', null, null, '1', '13', '4智能.jpg', '智能家居', '251', null, '139', null, '72', '2017-01-06 13:26:38', '0', '0', '0', null);
INSERT INTO `t_product` VALUES ('53', '华迪智能控制面板', '', null, null, '6', '13', '5智能.png', '智能家居', '251', null, '129', null, '72', '2017-01-06 13:27:41', '0', '0', '0', '1');
INSERT INTO `t_product` VALUES ('54', '华迪智能灯泡', '', null, null, '3', '13', '6智能.png', '智能家居', '251', null, '99', null, '72', '2017-01-06 13:28:46', '0', '0', '0', '2');

-- ----------------------------
-- Table structure for t_producttype
-- ----------------------------
DROP TABLE IF EXISTS `t_producttype`;
CREATE TABLE `t_producttype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `create_by` bigint(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记(0活null 正常 1,删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_producttype
-- ----------------------------
INSERT INTO `t_producttype` VALUES ('10', '框架', '2', '2016-12-22 09:59:15', '0');
INSERT INTO `t_producttype` VALUES ('11', 'test', '2', '2016-12-22 10:09:37', '0');

-- ----------------------------
-- Table structure for t_product_class
-- ----------------------------
DROP TABLE IF EXISTS `t_product_class`;
CREATE TABLE `t_product_class` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '索引ID',
  `name` varchar(100) NOT NULL COMMENT '分类名称',
  `pic` varchar(255) DEFAULT NULL COMMENT '分类图片',
  `del_flag` char(1) NOT NULL DEFAULT '1' COMMENT '前台显示，0为否，1为是，默认为1',
  `title` varchar(200) DEFAULT NULL COMMENT '名称',
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=255 DEFAULT CHARSET=utf8 COMMENT='商品分类表';

-- ----------------------------
-- Records of t_product_class
-- ----------------------------
INSERT INTO `t_product_class` VALUES ('1', '全部类别', null, '1', 'spring是很好的项目', '0', '0,');
INSERT INTO `t_product_class` VALUES ('212', '智能家居', null, '1', 'mybatis是很不错的项目2', '1', '212,0,,');
INSERT INTO `t_product_class` VALUES ('213', 'zscat系列', null, '1', 'zscat项目1', '1', '0,1,');
INSERT INTO `t_product_class` VALUES ('214', '前端/后台', null, '1', '11', '1', '0,1,');
INSERT INTO `t_product_class` VALUES ('215', 'cms项目', null, '1', '2222', '1', '1,');
INSERT INTO `t_product_class` VALUES ('216', 'oa项目', null, '1', '33', '1', '0,1,');
INSERT INTO `t_product_class` VALUES ('217', '官网项目', null, '1', '44', '1', '0,1,');
INSERT INTO `t_product_class` VALUES ('218', '餐饮项目', null, '1', null, '1', null);
INSERT INTO `t_product_class` VALUES ('228', '美容项目', null, '1', null, '1', null);
INSERT INTO `t_product_class` VALUES ('229', '宠物项目', null, '1', null, '1', null);
INSERT INTO `t_product_class` VALUES ('230', '交友项目', null, '1', null, '1', null);
INSERT INTO `t_product_class` VALUES ('231', '体育项目', null, '1', null, '1', null);
INSERT INTO `t_product_class` VALUES ('232', '后台', null, '1', '后台', '214', '0,1,214,');
INSERT INTO `t_product_class` VALUES ('233', '前端', null, '1', '前端', '214', '0,1,214,');
INSERT INTO `t_product_class` VALUES ('234', '商城项目', null, '1', '商城项目', '232', '0,1,214,232,');
INSERT INTO `t_product_class` VALUES ('235', '教育网站', null, '1', '教育网站', '232', '0,1,214,232,');
INSERT INTO `t_product_class` VALUES ('236', 'hplus', null, '1', 'hplus', '233', '0,1,214,233,');
INSERT INTO `t_product_class` VALUES ('237', 'h-ui', null, '1', 'h-ui', '233', '0,1,214,233,');
INSERT INTO `t_product_class` VALUES ('238', '商城', null, '1', null, '213', '238,0,1,,');
INSERT INTO `t_product_class` VALUES ('240', 'java', null, '1', null, '238', '240,238,0,1,,,');
INSERT INTO `t_product_class` VALUES ('241', 'php', null, '1', null, '238', '241,238,0,1,,,');
INSERT INTO `t_product_class` VALUES ('242', 'nodejs', null, '1', null, '238', '242,238,0,1,,,');
INSERT INTO `t_product_class` VALUES ('243', 'java', null, '1', null, '239', '243,239,0,1,,,');
INSERT INTO `t_product_class` VALUES ('244', 'php', null, '1', null, '239', '244,239,0,1,,,');
INSERT INTO `t_product_class` VALUES ('246', '分布式', null, '1', null, '238', '246,238,0,1,,,');
INSERT INTO `t_product_class` VALUES ('247', '后台框架', null, '1', null, '213', '247,0,1,,');
INSERT INTO `t_product_class` VALUES ('248', 'java', null, '1', null, '247', '248,247,0,1,,,');
INSERT INTO `t_product_class` VALUES ('249', 'php', null, '1', null, '247', '249,247,0,1,,,');
INSERT INTO `t_product_class` VALUES ('250', '空气净化', null, '1', null, '212', '250,212,0,,,');
INSERT INTO `t_product_class` VALUES ('251', '其他智能', null, '1', null, '212', '251,212,0,,,');
INSERT INTO `t_product_class` VALUES ('252', '空气净化器', null, '1', null, '250', '252,250,212,0,,,,');
INSERT INTO `t_product_class` VALUES ('253', '智能灯泡', null, '1', null, '251', '253,251,212,0,,,,');
INSERT INTO `t_product_class` VALUES ('254', '智能插排', null, '1', null, '251', '254,251,212,0,,,,');

-- ----------------------------
-- Table structure for t_reply
-- ----------------------------
DROP TABLE IF EXISTS `t_reply`;
CREATE TABLE `t_reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goodsid` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  `userid` bigint(20) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_reply
-- ----------------------------

-- ----------------------------
-- Table structure for zhihu_url
-- ----------------------------
DROP TABLE IF EXISTS `zhihu_url`;
CREATE TABLE `zhihu_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `md5_url` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `key_md5_url` (`md5_url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhihu_url
-- ----------------------------

-- ----------------------------
-- Table structure for zhihu_user
-- ----------------------------
DROP TABLE IF EXISTS `zhihu_user`;
CREATE TABLE `zhihu_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_token` varchar(100) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `business` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `employment` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `agrees` int(11) DEFAULT NULL,
  `thanks` int(11) DEFAULT NULL,
  `asks` int(11) DEFAULT NULL,
  `answers` int(11) DEFAULT NULL,
  `posts` int(11) DEFAULT NULL,
  `followees` int(11) DEFAULT NULL,
  `followers` int(11) DEFAULT NULL,
  `hashId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `key_user_token` (`user_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhihu_user
-- ----------------------------
