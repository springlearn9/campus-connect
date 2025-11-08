-- Sample data for Campus Connect Application
-- This will be automatically loaded when using H2 database

-- Insert sample users
INSERT INTO users (id, name, email, password, role) VALUES 
(1, 'John Doe', 'john@university.edu', 'password123', 'STUDENT'),
(2, 'Jane Smith', 'jane@university.edu', 'password456', 'FACULTY'),
(3, 'Bob Wilson', 'bob@university.edu', 'password789', 'ADMIN');

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

-- Insert sample events
INSERT INTO events (id, title, description, event_date, location, status, created_at, updated_at, posted_by_id) VALUES 
(1, 'Annual Tech Fest 2025', 'Join us for the biggest technology festival featuring workshops, competitions, and exhibitions from top tech companies.', CURRENT_TIMESTAMP + INTERVAL '30' DAY, 'Main Auditorium', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
(2, 'Career Fair 2025', 'Meet with recruiters from leading companies. Bring your resume and dress professionally.', CURRENT_TIMESTAMP + INTERVAL '15' DAY, 'Student Center Hall', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
(3, 'Cultural Night', 'Showcase your talents! Music, dance, drama, and art performances by students.', CURRENT_TIMESTAMP + INTERVAL '7' DAY, 'University Theater', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);

-- Insert sample notices
INSERT INTO notices (id, title, description, priority, category, valid_until, status, created_at, updated_at, posted_by_id) VALUES 
(1, 'Semester Exam Schedule Released', 'The final examination schedule for the current semester has been released. Students can check their exam dates and timings on the university portal.', 'HIGH', 'ACADEMIC', CURRENT_TIMESTAMP + INTERVAL '60' DAY, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
(2, 'Library Hours Extended', 'Due to upcoming exams, the library will remain open 24/7 starting next week. Students need to show their ID cards for access after 10 PM.', 'NORMAL', 'ADMINISTRATIVE', CURRENT_TIMESTAMP + INTERVAL '45' DAY, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
(3, 'New Parking Regulations', 'Please note the new parking regulations effective immediately. All vehicles must display valid parking permits. Unauthorized parking will result in fines.', 'HIGH', 'ADMINISTRATIVE', NULL, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
(4, 'Sports Week Registration Open', 'Registration for annual sports week is now open. Multiple sports categories available. Register at the sports office or online portal.', 'NORMAL', 'EVENT', CURRENT_TIMESTAMP + INTERVAL '20' DAY, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);
