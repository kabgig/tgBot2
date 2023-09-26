-- V2__add_date_columns.sql

-- Add a 'created_at' column to the 'ACTIVE_CHAT' table
ALTER TABLE ACTIVE_CHAT
ADD created_at TIMESTAMP;

-- Add a 'created_at' column to the 'active_chat' table
ALTER TABLE active_chat
ADD created_at TIMESTAMP;

-- Add a 'created_at' column to the 'incomes' table
ALTER TABLE incomes
ADD created_at TIMESTAMP;

-- Add a 'created_at' column to the 'spend' table
ALTER TABLE spend
ADD created_at TIMESTAMP;
