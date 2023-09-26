-- V2__add_creation_date_columns.sql

-- Add a 'creation_date' column with DATETIME type to the 'incomes' table
ALTER TABLE incomes
ADD creationDate DATETIME;

-- Add a 'creation_date' column with DATETIME type to the 'spend' table
ALTER TABLE spend
ADD creationDate DATETIME;
