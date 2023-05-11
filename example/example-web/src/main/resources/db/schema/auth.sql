
DROP TABLE IF EXISTS sys_dept;
CREATE TABLE sys_dept (
  id bigint NOT NULL,
  name varchar(50) DEFAULT NULL,
  manager bigint DEFAULT NULL,
  parent_id bigint DEFAULT NULL,
  expand int DEFAULT NULL,
  data_authority bigint DEFAULT NULL,
  dep_type varchar(255) DEFAULT NULL,
  create_name varchar(45) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  update_name varchar(45) DEFAULT NULL,
  update_date datetime DEFAULT NULL,
  is_deleted smallint DEFAULT NULL,
  name_short varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS sys_dept_role;
CREATE TABLE sys_dept_role (
  id bigint NOT NULL,
  dept_id bigint DEFAULT NULL,
  role_id bigint DEFAULT NULL,
  create_name varchar(45) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  update_name varchar(45) DEFAULT NULL,
  update_date datetime DEFAULT NULL,
  is_deleted smallint DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS sys_login_log;
CREATE TABLE sys_login_log (
  id int NOT NULL,
  log_name varchar(255) DEFAULT NULL,
  request_uri varchar(255) DEFAULT NULL,
  request_host varchar(255) DEFAULT NULL,
  request_ip varchar(255) DEFAULT NULL,
  request_detail varchar(255) DEFAULT NULL,
  request_method varchar(255) DEFAULT NULL,
  user_agent varchar(555) DEFAULT NULL,
  grant_type varchar(255) DEFAULT NULL,
  user_name varchar(255) DEFAULT NULL,
  client_id varchar(11) DEFAULT NULL,
  token varchar(255) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS sys_oauth_client_details;
CREATE TABLE sys_oauth_client_details (
  client_id varchar(32) NOT NULL,
  resource_ids varchar(256) DEFAULT NULL,
  client_secret varchar(256) DEFAULT NULL,
  scope varchar(256) DEFAULT NULL,
  authorized_grant_types varchar(256) DEFAULT NULL,
  web_server_redirect_uri varchar(256) DEFAULT NULL,
  authorities varchar(256) DEFAULT NULL,
  access_token_validity int DEFAULT NULL,
  refresh_token_validity int DEFAULT NULL,
  additional_information varchar(4096) DEFAULT NULL,
  autoapprove varchar(256) DEFAULT NULL,
  PRIMARY KEY (client_id)
);

DROP TABLE IF EXISTS sys_resource;
CREATE TABLE sys_resource (
  id bigint NOT NULL,
  parent_id bigint DEFAULT NULL,
  list_display int DEFAULT NULL,
  details_display int DEFAULT NULL,
  code varchar(50) DEFAULT NULL,
  name varchar(50) DEFAULT NULL,
  url varchar(500) DEFAULT NULL,
  picture_url varchar(500) DEFAULT NULL,
  icon varchar(500) DEFAULT NULL,
  method varchar(20) DEFAULT NULL,
  type int DEFAULT NULL,
  leaf int DEFAULT '1',
  node_level int DEFAULT '1',
  object_code varchar(100) DEFAULT NULL,
  readonly int DEFAULT NULL,
  display int DEFAULT NULL,
  description varchar(200) DEFAULT NULL,
  sort int DEFAULT '0',
  default_role int DEFAULT '1',
  enabled int DEFAULT '1',
  create_name varchar(45) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  update_name varchar(45) DEFAULT NULL,
  update_date datetime DEFAULT NULL,
  is_deleted smallint DEFAULT NULL,
  component varchar(100) DEFAULT NULL,
  component_name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
  id bigint NOT NULL,
  code varchar(50) DEFAULT NULL,
  name varchar(50) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  enabled int DEFAULT '1',
  create_name varchar(45) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  update_name varchar(45) DEFAULT NULL,
  update_date datetime DEFAULT NULL,
  is_deleted smallint DEFAULT NULL,
  default_role int DEFAULT '0',
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS sys_role_resource;
CREATE TABLE sys_role_resource (
  id bigint NOT NULL,
  role_id bigint DEFAULT NULL,
  resource_id bigint DEFAULT NULL,
  is_checked smallint DEFAULT NULL,
  create_name varchar(45) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  update_name varchar(45) DEFAULT NULL,
  update_date datetime DEFAULT NULL,
  is_deleted smallint DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
  id bigint NOT NULL,
  code varchar(100) DEFAULT NULL,
  username varchar(100) DEFAULT NULL,
  real_name varchar(100) DEFAULT NULL,
  dept_id bigint DEFAULT NULL,
  customer_name varchar(100) DEFAULT NULL,
  sex varchar(50) DEFAULT NULL,
  cellphone varchar(50) DEFAULT NULL,
  employment_date datetime DEFAULT NULL,
  birthday datetime DEFAULT NULL,
  icon varchar(200) DEFAULT NULL,
  work_city varchar(100) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  description varchar(2000) DEFAULT NULL,
  password varchar(500) DEFAULT NULL,
  salt varchar(200) DEFAULT NULL,
  admin int DEFAULT '0',
  login int DEFAULT '1',
  enabled int DEFAULT '1',
  last_login_date datetime DEFAULT NULL,
  activated int DEFAULT '0',
  activate_date datetime DEFAULT NULL,
  user_role varchar(255) DEFAULT NULL,
  total_credit decimal(20,2) DEFAULT NULL,
  available_credit decimal(20,2) DEFAULT NULL,
  phone_number varchar(50) DEFAULT NULL,
  first_login smallint DEFAULT '1',
  is_wonder varchar(10) DEFAULT NULL,
  wonder_id bigint DEFAULT NULL,
  wonder_name varchar(100) DEFAULT NULL,
  monitor int DEFAULT NULL,
  lock_flag char(1) DEFAULT '0',
  user_type int DEFAULT NULL,
  create_name varchar(45) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  update_name varchar(45) DEFAULT NULL,
  update_date datetime DEFAULT NULL,
  is_deleted smallint DEFAULT NULL,
  label varchar(100) DEFAULT NULL,
  id_card varchar(255) DEFAULT NULL,
  grade varchar(255) DEFAULT NULL,
  status int DEFAULT NULL,
  position varchar(255) DEFAULT NULL,
  tfd_id varchar(255) DEFAULT NULL,
  is_wonder_admin int DEFAULT NULL,
  province varchar(100) DEFAULT NULL,
  city varchar(100) DEFAULT NULL,
  county varchar(100) DEFAULT NULL,
  working_hours datetime DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role (
  id bigint NOT NULL,
  role_id bigint DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  update_name varchar(45) NOT NULL,
  update_date datetime DEFAULT NULL,
  create_name varchar(45) NOT NULL,
  create_date datetime DEFAULT NULL,
  is_deleted smallint NOT NULL,
  PRIMARY KEY (id)
);
