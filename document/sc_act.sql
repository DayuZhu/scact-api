CREATE TABLE `sc_acc_sep_record` (
  `acc_sep_record_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_acc_info_id` int unsigned NOT NULL DEFAULT '0' COMMENT '账户信息id',
  `po_card_name` varchar(64) NOT NULL DEFAULT '' COMMENT '出金持卡人',
  `po_bank_name` varchar(64) NOT NULL DEFAULT '' COMMENT '出金银行名称',
  `po_card_no` varchar(64) NOT NULL DEFAULT '' COMMENT '出金银行卡号',
  `payout_amount` int unsigned NOT NULL DEFAULT '0' COMMENT '出金金额(分）',
  `card_name` varchar(64) NOT NULL DEFAULT '' COMMENT '入金持卡人姓名',
  `bank_name` varchar(64) NOT NULL DEFAULT '' COMMENT '入金银行名称',
  `card_number` varchar(64) NOT NULL DEFAULT '' COMMENT '入金持卡人姓名',
  `income_amount` int unsigned NOT NULL DEFAULT '0' COMMENT '入金金额(分)',
  `out_order_id` int unsigned NOT NULL DEFAULT '0' COMMENT '外部订单ID',
  `out_product_id` int unsigned NOT NULL DEFAULT '0' COMMENT '外部商品ID',
  `product_id` int unsigned NOT NULL DEFAULT '0' COMMENT '产品ID',
  `handler_seq_no` varchar(64) NOT NULL DEFAULT '' COMMENT '处理批次号',
  `status` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0-处理中，1-成功，2-失败，3-未知失败',
  `reason` varchar(1000) NOT NULL DEFAULT '' COMMENT '失败原因',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`acc_sep_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分账流水表';

CREATE TABLE `sc_activity` (
  `activity_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `merchant_id` int unsigned NOT NULL DEFAULT '0' COMMENT '商户ID',
  `activity_name` varchar(128) NOT NULL DEFAULT '' COMMENT '活动名称',
  `activity_desc` varchar(1000) NOT NULL DEFAULT '' COMMENT '活动描述',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '活动开始时间',
  `end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '活动结束时间',
  `state` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '活动状态：0下线，1上线',
  `remark1` varchar(255) NOT NULL DEFAULT '' COMMENT '预留字段1',
  `remark2` varchar(255) NOT NULL DEFAULT '' COMMENT '预留字段2',
  `remark3` varchar(255) NOT NULL DEFAULT '' COMMENT '预留字段3',
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
  `user_acc_info_id` int unsigned NOT NULL DEFAULT '0' COMMENT '账户ID',
  `award_amount` int unsigned NOT NULL DEFAULT '0' COMMENT '中奖金额（分）',
  `remark1` varchar(255) NOT NULL DEFAULT '' COMMENT '预留字段1',
  `remark2` varchar(255) NOT NULL DEFAULT '' COMMENT '预留字段2',
  `remark3` varchar(255) NOT NULL DEFAULT '' COMMENT '预留字段3',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`activity_winners_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动中奖名单';

CREATE TABLE `sc_activity_wins_pdt` (
  `activity_wins_pdt_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `activity_winners_id` int unsigned NOT NULL DEFAULT '0' COMMENT '中奖名单ID',
  `activity_id` int unsigned NOT NULL DEFAULT '0' COMMENT '活动ID',
  `product_id` int unsigned NOT NULL DEFAULT '0' COMMENT '产品ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`activity_wins_pdt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='中奖人与产品关系表';

CREATE TABLE `sc_product` (
  `product_id` int NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `product_type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '商品类型：0-活动券',
  `product_name` varchar(268) DEFAULT NULL COMMENT '产品名称',
  `market_price` int NOT NULL DEFAULT '0' COMMENT '市场价（分）',
  `sell_price` int NOT NULL DEFAULT '0' COMMENT '销售价（分）',
  `out_product_id` int NOT NULL DEFAULT '0' COMMENT '外部产品关联ID',
  `out_product_platform` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '外部产品平台：0-shopXO',
  `state` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0:待审核,1:已上线,2:已下线,3:已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`product_id`),
  KEY `idx_out_product_id` (`out_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品表';

CREATE TABLE `sc_product_ticket` (
  `product_ticket_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `product_id` int unsigned NOT NULL DEFAULT '0' COMMENT '中奖名单ID',
  `ticket_id` int unsigned NOT NULL DEFAULT '0' COMMENT '券码ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`product_ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品与券码关系表';


CREATE TABLE `sc_ticket` (
  `ticket_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ticket_code` varchar(32) NOT NULL DEFAULT '' COMMENT '券码',
  `ticket_pwd` varchar(16) NOT NULL DEFAULT '' COMMENT '券密',
  `nominal_value` int unsigned NOT NULL DEFAULT '0' COMMENT '券面值（分）',
  `state` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '状态：0初始，1已发放则绑定到产品，2已使用则已分账成功，3已冻结',
  `remark1` varchar(255) NOT NULL DEFAULT '' COMMENT '预留字段1',
  `remark2` varchar(255) NOT NULL DEFAULT '' COMMENT '预留字段2',
  `remark3` varchar(255) NOT NULL DEFAULT '' COMMENT '预留字段3',
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
  UNIQUE KEY `idx_uni_mobile` (`mobile`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `sc_user_acc_info` (
  `user_acc_info_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `card_name` varchar(64) NOT NULL DEFAULT '' COMMENT '开户人',
  `bank_name` varchar(64) NOT NULL DEFAULT '' COMMENT '开户行',
  `card_number` varchar(64) NOT NULL DEFAULT '' COMMENT '银行卡号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_acc_info_id`),
  UNIQUE KEY `idx_uni_userid_cardno` (`user_id`,`card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户表信息表';

CREATE TABLE `sc_merchant` (
  `merchant_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '商户id',
  `merchant_name` varchar(30) NOT NULL DEFAULT '' COMMENT '商家名称',
  `social_code` varchar(50) NOT NULL DEFAULT '' COMMENT '社会统一代码',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '法人姓名',
  `mobile` bigint unsigned NOT NULL DEFAULT '0' COMMENT '法人手机号',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '商家地址',
  `boss_name` varchar(10) NOT NULL DEFAULT '' COMMENT '店主名称',
  `boss_tel` bigint unsigned NOT NULL DEFAULT '0' COMMENT '店主电话',
  `create_user_id` int unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_user_name` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人姓名',
  `update_user_id` int unsigned NOT NULL DEFAULT '0' COMMENT '最后一次更新人',
  `update_user_name` varchar(64) NOT NULL DEFAULT '' COMMENT '最后一次更新人姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商户表';

CREATE TABLE `sc_merchant_acc_info` (
  `merchant_acc_info_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `merchant_id` int unsigned NOT NULL DEFAULT '0' COMMENT '商户ID',
  `acc_name` varchar(32) NOT NULL DEFAULT '' COMMENT '姓名',
  `merchant_bank` varchar(64) NOT NULL DEFAULT '' COMMENT '开户行',
  `merchant_card` varchar(64) NOT NULL DEFAULT '' COMMENT '银行卡号',
  `mobile` bigint unsigned NOT NULL DEFAULT '0' COMMENT '手机号',
  `state` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '状态：0 弃用 1使用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`merchant_acc_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商户账户信息表';

CREATE TABLE `sc_merchant_account` (
  `merchant_account_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `merchant_id` int unsigned NOT NULL DEFAULT '0' COMMENT '会员id',
  `balance` int unsigned NOT NULL DEFAULT '0' COMMENT '余额',
  `state` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '用户账户状态，0-冻结，1-正常',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`merchant_account_id`),
  UNIQUE KEY `idx_unq_merchant_id` (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商户资金信息表';


CREATE TABLE `sc_merchant_account_record` (
  `merchant_account_record_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `merchant_id` int unsigned NOT NULL DEFAULT '0' COMMENT '商户id',
  `record_type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '类型 0无 1 活动中奖',
  `income_amount` int unsigned DEFAULT '0' COMMENT '进账',
  `payout_amount` int unsigned DEFAULT '0' COMMENT '出账',
  `reason_desc` varchar(255) DEFAULT NULL COMMENT '描述:活动中奖',
  `activity_winners_id` int unsigned NOT NULL DEFAULT '0' COMMENT '活动中奖名单ID',
  `activity_id` int unsigned NOT NULL DEFAULT '0' COMMENT '活动id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`merchant_account_record_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='商户资金记录表';


