BEGIN
    -- Check if PERSON_TBL exists, if not create it
BEGIN
SELECT COUNT(*) INTO :table_exists FROM user_tables WHERE table_name = 'PERSON_TBL';
IF :table_exists = 0 THEN
            EXECUTE IMMEDIATE 'CREATE TABLE person_tbl (
                                   id NUMBER PRIMARY KEY,
                                   name NVARCHAR2(30),
                                   family NVARCHAR2(30),
                                   gender VARCHAR2(6),
                                   user_id NUMBER REFERENCES user_tbl(id)
                               )';
END IF;
EXCEPTION WHEN OTHERS THEN
        NULL; -- If error occurs, ignore and continue
END;

    -- Check if USER_TBL exists, if not create it
BEGIN
SELECT COUNT(*) INTO :table_exists FROM user_tables WHERE table_name = 'USER_TBL';
IF :table_exists = 0 THEN
            EXECUTE IMMEDIATE 'CREATE TABLE user_tbl (
                                   id NUMBER PRIMARY KEY,
                                   username NVARCHAR2(30) UNIQUE,
                                   password NVARCHAR2(30),
                                   ROLE VARCHAR2(11)
                               )';
END IF;
EXCEPTION WHEN OTHERS THEN
        NULL; -- If error occurs, ignore and continue
END;

    -- Check if MENU_TBL exists, if not create it
BEGIN
SELECT COUNT(*) INTO :table_exists FROM user_tables WHERE table_name = 'MENU_TBL';
IF :table_exists = 0 THEN
            EXECUTE IMMEDIATE 'CREATE TABLE MENU_tbl (
                                   item_id NUMBER PRIMARY KEY,
                                   item_name VARCHAR2(100) NOT NULL,
                                   description VARCHAR2(255),
                                   price NUMBER(10) NOT NULL,
                                   category VARCHAR2(50),
                                   is_available NUMBER(1) DEFAULT 1
                               )';
END IF;
EXCEPTION WHEN OTHERS THEN
        NULL; -- If error occurs, ignore and continue
END;

    -- Check if ITEM_TBL exists, if not create it
BEGIN
SELECT COUNT(*) INTO :table_exists FROM user_tables WHERE table_name = 'ITEM_TBL';
IF :table_exists = 0 THEN
            EXECUTE IMMEDIATE 'CREATE TABLE ITEM_TBL (
                                   ITEM_ID NUMBER PRIMARY KEY,
                                   ITEM_NAME NVARCHAR2(100) NOT NULL,
                                   DESCRIPTION NVARCHAR2(255),
                                   PRICE NUMBER(10) NOT NULL,
                                   CATEGORY VARCHAR2(20),
                                   IS_AVAILABLE NUMBER(1) DEFAULT 1
                               )';
END IF;
EXCEPTION WHEN OTHERS THEN
        NULL; -- If error occurs, ignore and continue
END;

    -- Check if CUSTOMER_TBL exists, if not create it
BEGIN
SELECT COUNT(*) INTO :table_exists FROM user_tables WHERE table_name = 'CUSTOMER_TBL';
IF :table_exists = 0 THEN
            EXECUTE IMMEDIATE 'CREATE TABLE CUSTOMER_TBL (
                                   CUSTOMER_ID NUMBER PRIMARY KEY,
                                   CUSTOMER_NAME NVARCHAR2(20),
                                   CUSTOMER_FAMILY NVARCHAR2(20),
                                   PHONE_NUMBER VARCHAR2(12)
                               )';
END IF;
EXCEPTION WHEN OTHERS THEN
        NULL; -- If error occurs, ignore and continue
END;

    -- Check if ORDER_TBL exists, if not create it
BEGIN
SELECT COUNT(*) INTO :table_exists FROM user_tables WHERE table_name = 'ORDER_TBL';
IF :table_exists = 0 THEN
            EXECUTE IMMEDIATE 'CREATE TABLE ORDER_TBL (
                                   ORDER_ID NUMBER PRIMARY KEY,
                                   ITEMS NVARCHAR2(1000),
                                   TOTAL_PRICE NUMBER(10),
                                   STATUS VARCHAR2(15),
                                   CUSTOMER_ID NUMBER REFERENCES CUSTOMER_TBL(CUSTOMER_ID)
                               )';
END IF;
EXCEPTION WHEN OTHERS THEN
        NULL; -- If error occurs, ignore and continue
END;

    -- Check if PAYMENT_TBL exists, if not create it
BEGIN
SELECT COUNT(*) INTO :table_exists FROM user_tables WHERE table_name = 'PAYMENT_TBL';
IF :table_exists = 0 THEN
            EXECUTE IMMEDIATE 'CREATE TABLE PAYMENT_TBL (
                                   PAYMENT_ID NUMBER PRIMARY KEY,
                                   PAYMENT_METHOD VARCHAR2(11) NOT NULL,
                                   PAYMENT_STATUS VARCHAR2(10) NOT NULL,
                                   AMOUNT NUMBER(10) NOT NULL,
                                   ORDER_ID NUMBER REFERENCES ORDER_TBL(ORDER_ID)
                               )';
END IF;
EXCEPTION WHEN OTHERS THEN
        NULL; -- If error occurs, ignore and continue
END;

    -- Create Sequences
BEGIN
        -- Create sequence for person_tbl if it doesn't exist
BEGIN
SELECT COUNT(*) INTO :seq_exists FROM user_sequences WHERE sequence_name = 'PERSON_SEQ';
IF :seq_exists = 0 THEN
                EXECUTE IMMEDIATE 'CREATE SEQUENCE PERSON_SEQ START WITH 1 INCREMENT BY 1';
END IF;
EXCEPTION WHEN OTHERS THEN
            NULL; -- Ignore errors
END;

        -- Create sequence for user_tbl if it doesn't exist
BEGIN
SELECT COUNT(*) INTO :seq_exists FROM user_sequences WHERE sequence_name = 'USER_SEQ';
IF :seq_exists = 0 THEN
                EXECUTE IMMEDIATE 'CREATE SEQUENCE USER_SEQ START WITH 1 INCREMENT BY 1';
END IF;
EXCEPTION WHEN OTHERS THEN
            NULL; -- Ignore errors
END;

        -- Create sequence for MENU_tbl if it doesn't exist
BEGIN
SELECT COUNT(*) INTO :seq_exists FROM user_sequences WHERE sequence_name = 'MENU_SEQ';
IF :seq_exists = 0 THEN
                EXECUTE IMMEDIATE 'CREATE SEQUENCE MENU_SEQ START WITH 1 INCREMENT BY 1';
END IF;
EXCEPTION WHEN OTHERS THEN
            NULL; -- Ignore errors
END;

        -- Create sequence for ITEM_TBL if it doesn't exist
BEGIN
SELECT COUNT(*) INTO :seq_exists FROM user_sequences WHERE sequence_name = 'ITEM_SEQ';
IF :seq_exists = 0 THEN
                EXECUTE IMMEDIATE 'CREATE SEQUENCE ITEM_SEQ START WITH 1 INCREMENT BY 1';
END IF;
EXCEPTION WHEN OTHERS THEN
            NULL; -- Ignore errors
END;

        -- Create sequence for CUSTOMER_TBL if it doesn't exist
BEGIN
SELECT COUNT(*) INTO :seq_exists FROM user_sequences WHERE sequence_name = 'CUSTOMER_SEQ';
IF :seq_exists = 0 THEN
                EXECUTE IMMEDIATE 'CREATE SEQUENCE CUSTOMER_SEQ START WITH 1 INCREMENT BY 1';
END IF;
EXCEPTION WHEN OTHERS THEN
            NULL; -- Ignore errors
END;

        -- Create sequence for ORDER_TBL if it doesn't exist
BEGIN
SELECT COUNT(*) INTO :seq_exists FROM user_sequences WHERE sequence_name = 'ORDER_SEQ';
IF :seq_exists = 0 THEN
                EXECUTE IMMEDIATE 'CREATE SEQUENCE ORDER_SEQ START WITH 1 INCREMENT BY 1';
END IF;
EXCEPTION WHEN OTHERS THEN
            NULL; -- Ignore errors
END;

        -- Create sequence for PAYMENT_TBL if it doesn't exist
BEGIN
SELECT COUNT(*) INTO :seq_exists FROM user_sequences WHERE sequence_name = 'PAYMENT_SEQ';
IF :seq_exists = 0 THEN
                EXECUTE IMMEDIATE 'CREATE SEQUENCE PAYMENT_SEQ START WITH 1 INCREMENT BY 1';
END IF;
EXCEPTION WHEN OTHERS THEN
            NULL; -- Ignore errors
END;
END;

END;
