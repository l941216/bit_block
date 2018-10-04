DROP TABLE IF EXISTS `b_bluebooth`;
CREATE TABLE `b_bluebooth` (
  `blue_id` varchar(32) NOT NULL,
  `bluetoothMac` varchar(32) NOT NULL COMMENT '蓝牙Mac地址',
  `add_time` varchar(20) NOT NULL COMMENT '添加时间',
  `password` varchar(20) default null COMMENT '备份密码',
  `ver_code` varchar(20) default null COMMENT '验证码',
  PRIMARY KEY (`blue_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='蓝牙锁设备表';



DROP TABLE IF EXISTS `b_blue_customer`;
CREATE TABLE `EbhUserBluetooth` (
  `blue_customer_id` varchar(32) NOT NULL,
  `fk_customer_id` varchar(32) NOT NULL COMMENT '蓝牙所属用户Id',
  `fk_bluetooth_id` VARCHAR(32) NOT NUll COMMENT '蓝牙ID',
  `add_time` varchar(20) NOT NULL COMMENT '添加时间',
  `update_time` varchar(20) NOT NULL COMMENT '更新时间记录用户解绑时间',
  `use_status` TINYINT(1) DEFAULT 1 COMMENT '状态0不启用，1启用',

  PRIMARY KEY (`blue_customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户蓝牙锁设备表';



DROP TABLE IF EXISTS `b_bluetooth_record`;
CREATE TABLE `b_bluetooth_record` (
  `id` varchar(32) NOT NULL,
  `fk_customer_id` varchar(32) NOT NULL COMMENT '被授权用户Id',
  `fk_blue_customer_id` varchar(32) NOT NULL COMMENT '用户设备绑定ID',
  `add_time` varchar(20) NOT NULL COMMENT '开锁时间/请求时间',
  `type_auth` INTEGER NOT NULL COMMENT '状态 1请求记录，2授权记录，3开锁记录，4取钥匙记录，5归还钥匙记录，6关锁记录',
  `status` INTEGER NOT NULL COMMENT '状态 1未读 2已读',
  `record_code` bigint(13) NOT NULL COMMENT 'code码', -- --------------------------------------------------------------------------------------
  `is_send` INTEGER DEFAULT 0 COMMENT '状态 1继续推送 2不再通知',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户蓝牙锁操作记录表';
