-- gample data for Campus Connect Application
-- This will be automatically loaded when using H2 database

-- Insert sample users with enhanced profile data
INSERT INTO users (id, name, email, password, role, roll_number, department, phone_number, batch, semester, status, created_at, updated_at) VALUES 
(1, 'John Doe', 'john@university.edu', 'password123', 'STUDENT', 'CS2022001', 'Computer Science', '+1234567890', '2022-2026', '5', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Jane Smith', 'jane@university.edu', 'password456', 'FACULTY', 'FAC001', 'Information Technology', '+1234567891', NULL, NULL, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Bob Wilson', 'bob@university.edu', 'password789', 'ADMIN', 'ADM001', 'Administration', '+1234567892', NULL, NULL, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample lost items with enhanced data
INSERT INTO lost_items (id, item_name, description, location, lost_date, status, category, reward_amount, contact_info, urgent, is_anonymous, created_at, updated_at, user_id) VALUES 
(1, 'Blue Backpack', 'Blue Jansport backpack with my name tag inside. Contains important textbooks and laptop', 'Library 2nd Floor', CURRENT_TIMESTAMP - INTERVAL '2' DAY, 'PENDING', 'ACCESSORIES', 50.0, 'john@university.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(2, 'iPhone 12', 'Black iPhone 12 with a red protective case. Has a crack on the screen', 'Student Center Cafeteria', CURRENT_TIMESTAMP - INTERVAL '1' DAY, 'SEARCHING', 'ELECTRONICS', 100.0, '+1234567890', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(3, 'Car Keys', 'Honda keys with university keychain and small teddy bear keyring', 'Parking Lot B', CURRENT_TIMESTAMP - INTERVAL '3' DAY, 'PENDING', 'OTHER', 20.0, 'bob@university.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);

-- Insert sample found items with enhanced data
INSERT INTO found_items (id, item_name, description, location, found_date, status, category, contact_info, distinctive_features, handover_location, verification_required, is_anonymous, created_at, updated_at, reported_by_id) VALUES 
(1, 'Red Notebook', 'Red spiral notebook with chemistry notes and formulas', 'Science Building Room 205', CURRENT_TIMESTAMP - INTERVAL '1' DAY, 'AVAILABLE', 'BOOKS', 'jane@university.edu', 'Has "Sarah" written on the cover', 'Student Services Office', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
(2, 'Silver Watch', 'Silver digital watch, brand appears to be Casio with black strap', 'Gymnasium Locker Room', CURRENT_TIMESTAMP - INTERVAL '2' DAY, 'AVAILABLE', 'ACCESSORIES', '+1234567891', 'Shows time in 24-hour format, has small scratch on face', 'Security Office', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
(3, 'Reading Glasses', 'Black frame reading glasses in a brown leather case', 'Library Study Room 3', CURRENT_TIMESTAMP - INTERVAL '3' DAY, 'AVAILABLE', 'ACCESSORIES', 'bob@university.edu', 'Progressive lenses, slight wear on nose pads', 'Library Information Desk', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);

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
