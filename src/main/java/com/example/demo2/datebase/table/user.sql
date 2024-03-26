use mall;
CREATE TABLE user
(
    user_id            INT          NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '使用者ID',
    email              VARCHAR(256) NOT NULL UNIQUE KEY COMMENT '使用者帳號',
    NAME               VARCHAR(50)  NOT NULL DEFAULT '0' COMMENT '使用者姓名',
    ACCOUNT            VARCHAR(50)  NOT NULL DEFAULT '0' COMMENT '使用者帳號',
    password           VARCHAR(256) NOT NULL DEFAULT '0' COMMENT '使用者密碼(加密過)',
    created_date       TIMESTAMP    NOT NULL,
    last_modified_date TIMESTAMP    NOT NULL
)
    COMMENT='使用者'
    COLLATE='utf8_general_ci';
