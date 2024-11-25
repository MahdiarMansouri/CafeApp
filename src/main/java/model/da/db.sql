create table person_tbl
(
    id           number primary key,
    name         nvarchar2(30),
    family       nvarchar2(30),
    gender       varchar2(6),
    national_id  varchar2(10),
    phone_number varchar2(13),

    user_id references user_tbl
);

create sequence person_seq start with 1 increment by 1;


create table user_tbl
(
    id       number primary key,
    username nvarchar2(30) unique,
    password nvarchar2(30),
    ROLE     VARCHAR2(11)
);

create sequence user_seq start with 1 increment by 1;

CREATE TABLE MENU_tbl
(
    item_id      NUMBER PRIMARY KEY,
    item_name    VARCHAR2(100) NOT NULL,
    description  VARCHAR2(255),
    price        NUMBER(10)    NOT NULL,
    category     VARCHAR2(50),
    is_available NUMBER(1) DEFAULT 1
);

CREATE SEQUENCE MENU_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE ITEM_TBL
(
    ITEM_ID      NUMBER PRIMARY KEY,
    ITEM_NAME    NVARCHAR2(100) NOT NULL,
    DESCRIPTION  NVARCHAR2(255),
    PRICE        NUMBER(10)     NOT NULL,
    CATEGORY     VARCHAR2(20),
    IS_AVAILABLE NUMBER(1) DEFAULT 1
);

CREATE SEQUENCE ITEM_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE CUSTOMER_TBL
(
    CUSTOMER_ID     NUMBER PRIMARY KEY,
    CUSTOMER_NAME   NVARCHAR2(20),
    CUSTOMER_FAMILY NVARCHAR2(20),
    PHONE_NUMBER    varchar2(12)
);
CREATE SEQUENCE CUSTOMER_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE ORDER_TBL
(
    ORDER_ID    NUMBER PRIMARY KEY,
    ITEMS       NVARCHAR2(1000),
    TOTAL_PRICE NUMBER(10),
    STATUS      VARCHAR2(15),
    CUSTOMER_ID REFERENCES CUSTOMER_TBL
);

CREATE SEQUENCE ORDER_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE PAYMENT_TBL
(
    PAYMENT_ID     NUMBER PRIMARY KEY,
    PAYMENT_METHOD VARCHAR2(11) NOT NULL,
    PAYMENT_STATUS VARCHAR2(10) NOT NULL,
    AMOUNT         NUMBER(10)   NOT NULL,
    ORDER_ID REFERENCES ORDER_TBL
);

CREATE SEQUENCE PAYMENT_SEQ START WITH 1 INCREMENT BY 1;


