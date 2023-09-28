# snowId-Generator
简单的雪花算法❄️生成器



```sql
CREATE TABLE WORKER_NODE
(
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增 id',
    `hostname` VARCHAR(64) NOT NULL COMMENT 'host',
    `port` VARCHAR(64) NOT NULL COMMENT 'port',
    `type` int NOT NULL DEFAULT '1' COMMENT '1:容器，2:真实机器',
    `launch_date` date NOT NULL COMMENT '启动时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
)
 COMMENT='snowId 的 workId 分配表',ENGINE = INNODB;
```