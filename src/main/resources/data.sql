-- Sample data for Campus Connect Application
-- This will be automatically loaded when using H2 database

-- Insert sample users
INSERT INTO users (id, name, email, password, role) VALUES 
(1, 'John Doe', 'john@university.edu', 'password123', 'STUDENT'),
(2, 'Jane Smith', 'jane@university.edu', 'password456', 'STUDENT'),
(3, 'Bob Wilson', 'bob@university.edu', 'password789', 'FACULTY');

-- Insert sample lost items
INSERT INTO lost_items (id, item_name, description, location, status, created_at, user_id) VALUES 
(1, 'Blue Backpack', 'Blue Jansport backpack with my name tag inside', 'Library 2nd Floor', 'PENDING', CURRENT_TIMESTAMP, 1),
(2, 'iPhone 12', 'Black iPhone 12 with a red case', 'Student Center Cafeteria', 'PENDING', CURRENT_TIMESTAMP, 2),
(3, 'Car Keys', 'Honda keys with university keychain', 'Parking Lot B', 'PENDING', CURRENT_TIMESTAMP, 3);

-- Insert sample found items
INSERT INTO found_items (id, item_name, description, location, found_date, reported_by_id) VALUES 
(1, 'Red Notebook', 'Red spiral notebook with chemistry notes', 'Science Building Room 205', CURRENT_TIMESTAMP, 1),
(2, 'Silver Watch', 'Silver digital watch, brand appears to be Casio', 'Gymnasium Locker Room', CURRENT_TIMESTAMP, 2),
(3, 'Reading Glasses', 'Black frame reading glasses in a brown case', 'Library Study Room 3', CURRENT_TIMESTAMP, 3);
