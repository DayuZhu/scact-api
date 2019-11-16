CREATE TABLE `sc_account_info` (
  `account_info_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `bank_name` varchar(64) NOT NULL DEFAULT '' COMMENT '开户行',
  `card_number` varchar(64) NOT NULL DEFAULT '' COMMENT '银行卡号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`account_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户表信息表';

CREATE TABLE `sc_acc_sep_record` (
  `acc_sep_record_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_info_id` int unsigned NOT NULL DEFAULT '0' COMMENT '账户信息id',
  `amount` int unsigned NOT NULL DEFAULT '0' COMMENT '金额',
  `status` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0-处理中，1-成功，2-失败，3-未知失败',
  `reason` varchar(1000) NOT NULL DEFAULT '' COMMENT '失败原因',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`acc_sep_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分账流水表';

CREATE TABLE `sc_activity` (
  `activity_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `activity_name` varchar(128) NOT NULL DEFAULT '' COMMENT '活动名称',
  `activity_desc` varchar(1000) NOT NULL DEFAULT '' COMMENT '活动描述',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '活动开始时间',
  `end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '活动结束时间',
  `state` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '活动状态：0下线，1上线',
  `create_user_id` int unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_user_name` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人姓名',
  `update_user_id` int unsigned NOT NULL DEFAULT '0' COMMENT '最后一次更新人',
  `update_user_name` varchar(64) NOT NULL DEFAULT '' COMMENT '最后一次更新人姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动表';


CREATE TABLE `sc_activity_winners` (
  `activity_winners_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `activity_id` int unsigned NOT NULL DEFAULT '0' COMMENT '活动id',
  `user_id` int unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `account_info_id` int unsigned NOT NULL DEFAULT '0' COMMENT '账户ID',
  `award_amount` int unsigned NOT NULL DEFAULT '0' COMMENT '中奖金额',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`activity_winners_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动中奖名单';

CREATE TABLE `sc_ticket` (
  `ticket_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ticket_code` varchar(64) NOT NULL DEFAULT '' COMMENT '券码',
  `ticket_pwd` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '券密',
  `nominal_value` int unsigned NOT NULL DEFAULT '0' COMMENT '券面值',
  `state` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '状态：0初始，1已发放，2已使用，3已冻结',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='券码表';

CREATE TABLE `sc_user` (
  `user_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '用户姓名',
  `gender` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '用户的性别,1男，2女,0未知',
  `mobile` bigint unsigned NOT NULL DEFAULT '0' COMMENT '手机号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


CREATE TABLE `sc_winners_ticket` (
  `winners_ticket_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `activity_winners_id` int unsigned NOT NULL DEFAULT '0' COMMENT '中奖名单ID',
  `ticket_id` int unsigned NOT NULL DEFAULT '0' COMMENT '券码ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`winners_ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='中奖名单券码关系表';













