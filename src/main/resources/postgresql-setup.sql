-- PostgreSQL Database Setup for Campus Connect
-- Run these commands in PostgreSQL to set up the database

-- Create database
CREATE DATABASE campus_connect_db;

-- Create user (optional, you can use default postgres user)
CREATE USER campus_user WITH PASSWORD 'your_secure_password';

-- Grant privileges
GRANT ALL PRIVILEGES ON DATABASE campus_connect_db TO campus_user;

-- Connect to the database
\c campus_connect_db;

-- Grant schema privileges
GRANT ALL ON SCHEMA public TO campus_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO campus_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO campus_user;