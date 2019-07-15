DROP TABLE IF EXISTS `comicspider_comic`;
CREATE TABLE `comicspider_comic`
(
  `comic_id` int unsigned NOT NULL COMMENT '漫画id',
  `is_downloaded` tinyint NOT NULL DEFAULT 0 COMMENT '是否已下载',
  `is_end` tinyint NOT NULL DEFAULT 0 COMMENT '是否已完结',
  `chapter_num` int COMMENT '章节数目',
  `comic_name` varchar(300) COMMENT '漫画名',
  `cover` text COMMENT 'base64封面',
  `category` varchar(30) COMMENT '分类',
  `author` varchar(60) COMMENT '作者',
  `about` text COMMENT '关于',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`comic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `comicspider_chapter`;
CREATE TABLE `comicspider_chapter`
(
  `chapter_id` int unsigned NOT NULL COMMENT '章节id',
  `comic_id` int unsigned NOT NULL COMMENT '漫画id',
  `is_downloadid` tinyint NOT NULL DEFAULT 0 COMMENT '是否已下载',
  `page_num` int NOT NULL COMMENT '页数',
  `chapter_name` varchar(100) COMMENT '章节名',
  PRIMARY KEY (`chapter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `comicspider_proxy`;
CREATE TABLE `comicspider_proxy`
(
  `proxy_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '代理id',
  `ip` varchar(20) COMMENT '代理ip',
  `port` int COMMENT '代理端口',
  `type` varchar(10) comment '协议类型',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`proxy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `comicspider_tag`;
CREATE TABLE `comicspider_tag`
(
  `tag_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `tag_name` varchar(30) COMMENT '标签名',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `comicspider_comict_tag`;
CREATE TABLE `comicspider_comic_tag`
(
  `comic_tag_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '漫画和标签的关系id',
  `comic_id` int NOT NULL COMMENT '漫画id',
  `tag_id` int NOT NULL COMMENT '标签id',
  PRIMARY KEY (`comic_tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;