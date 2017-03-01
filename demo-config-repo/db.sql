-- 权限表
DROP TABLE IF EXISTS t_authority;
CREATE TABLE t_authority (  
  id bigint(20) NOT NULL AUTO_INCREMENT,  
  code varchar(64) NOT NULL, 
  name varchar(64) NOT NULL, 
  authority_desc varchar(100) NOT NULL DEFAULT '',  
  pid bigint(20) NOT NULL DEFAULT -1,
  create_user varchar(32) not null DEFAULT '',
  create_date datetime not null DEFAULT now(),
  update_user varchar(32) not null DEFAULT '',
  update_date datetime not null DEFAULT now(),
  CONSTRAINT t_authority_pk PRIMARY KEY (id),
  CONSTRAINT t_authority_un_code UNIQUE KEY (code) 
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci; 

-- 资源表
DROP TABLE IF EXISTS t_resource;
CREATE TABLE t_resource (  
  id bigint(20) NOT NULL AUTO_INCREMENT,  
  name varchar(64) NOT NULL, 
  url varchar(128) NOT NULL, 
  servive_module_id bigint(20) NOT NULL, -- 微服务模块
  pid bigint(20) NOT NULL DEFAULT -1, 
  authority_id bigint(20) NOT NULL, 
  full_id varchar(128) NOT NULL DEFAULT '',
  order_id int NOT NULL DEFAULT 1,
  resource_desc varchar(128) NOT NULL DEFAULT '',
  create_user varchar(32) not null DEFAULT '',
  create_date datetime not null DEFAULT now(),
  update_user varchar(32) not null DEFAULT '',
  update_date datetime not null DEFAULT now(),
  CONSTRAINT t_resource_pk PRIMARY KEY (id),
  CONSTRAINT t_resource_authority_id_fk FOREIGN KEY (authority_id) REFERENCES t_authority (id),
  CONSTRAINT t_resource_servive_module_id_fk FOREIGN KEY (servive_module_id) REFERENCES t_servive_module (id)
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci; 

-- 角色表
DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role (  
  id bigint(20) NOT NULL AUTO_INCREMENT,   
  name varchar(64) NOT NULL, 
  role_desc varchar(128) NOT NULL DEFAULT '',
  create_user varchar(32) not null DEFAULT '',
  create_date datetime not null DEFAULT now(),
  update_user varchar(32) not null DEFAULT '',
  update_date datetime not null DEFAULT now()
  CONSTRAINT t_role_pk PRIMARY KEY (id) 
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci;

-- 角色权限表
DROP TABLE IF EXISTS t_role_authority;
CREATE TABLE t_role_authority (
  id bigint(20) NOT NULL AUTO_INCREMENT, 
  role_id bigint(20) NOT NULL,
  authority_id bigint(20) NOT NULL,
  CONSTRAINT t_role_authority_pk PRIMARY KEY (id),
  CONSTRAINT t_role_authority_authority_id_fk FOREIGN KEY (authority_id) REFERENCES t_authority (id),
  CONSTRAINT t_role_authority_role_id_fk FOREIGN KEY (role_id) REFERENCES t_role (id)
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

-- 用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (  
  id bigint(20) NOT NULL AUTO_INCREMENT,
  account varchar(32) NOT NULL, 
  name varchar(64) NOT NULL, 
  password varchar(256) NOT NULL,
  email varchar(128) NOT NULL DEFAULT '', 
  status varchar(6) NOT NULL DEFAULT 'NORMAL' COMMENT 'NORMAL: 正常。LOCK: 锁定。DEL: 删除。'
  create_user varchar(32) NOT NULL default '',
  create_date datetime NOT NULL default now(),
  update_user varchar(32) NOT NULL default '',
  update_date datetime NOT NULL default now(),
  CONSTRAINT t_user_pk PRIMARY KEY (id)，
  CONSTRAINT t_user_un_account UNIQUE KEY (account) 
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci;

-- 用户角色表
DROP TABLE IF EXISTS t_user_role;
CREATE TABLE t_user_role (
  id bigint(20) NOT NULL AUTO_INCREMENT, 
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  CONSTRAINT t_user_role_pk PRIMARY KEY (id),
  CONSTRAINT t_user_role_user_id_fk FOREIGN KEY (user_id) REFERENCES t_user (id),
  CONSTRAINT t_user_role_role_id_fk FOREIGN KEY (role_id) REFERENCES t_role (id)
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

-- 微服务模块表
DROP TABLE IF EXISTS t_servive_module;
CREATE TABLE t_servive_module (
  id bigint(20) NOT NULL AUTO_INCREMENT, 
  name varchar(32) NOT NULL,
  url varchar(32) NOT NULL,
  create_user varchar(32) NOT NULL default '',
  create_date datetime NOT NULL default now(),
  update_user varchar(32) NOT NULL default '',
  update_date datetime NOT NULL default now(),
  CONSTRAINT t_servive_module_pk PRIMARY KEY (id)
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;