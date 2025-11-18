-- Comprehensive sample data for Campus Connect Application
-- This will be automatically loaded when using H2 database

-- ===============================
-- = USERS (25 records)
-- ===============================
INSERT INTO users (id, name, email, password, role, roll_number, department, phone_number, batch, semester, status, created_at, updated_at) VALUES 
-- Students
(1, 'John Doe', 'john@university.edu', 'password123', 'STUDENT', 'CS2022001', 'Computer Science', '+1234567890', '2022-2026', '5', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Emma Wilson', 'emma@university.edu', 'password124', 'STUDENT', 'IT2021015', 'Information Technology', '+1234567891', '2021-2025', '7', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Michael Brown', 'michael@university.edu', 'password125', 'STUDENT', 'EC2023008', 'Electronics', '+1234567892', '2023-2027', '3', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Sarah Davis', 'sarah@university.edu', 'password126', 'STUDENT', 'ME2022012', 'Mechanical Engineering', '+1234567893', '2022-2026', '5', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Alex Johnson', 'alex@university.edu', 'password127', 'STUDENT', 'CE2021019', 'Civil Engineering', '+1234567894', '2021-2025', '7', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Lisa Garcia', 'lisa@university.edu', 'password128', 'STUDENT', 'BT2023005', 'Biotechnology', '+1234567895', '2023-2027', '3', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'Ryan Miller', 'ryan@university.edu', 'password129', 'STUDENT', 'CH2022017', 'Chemistry', '+1234567896', '2022-2026', '5', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'Maya Patel', 'maya@university.edu', 'password130', 'STUDENT', 'PH2021011', 'Physics', '+1234567897', '2021-2025', '7', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'Kevin Lee', 'kevin@university.edu', 'password131', 'STUDENT', 'MA2023013', 'Mathematics', '+1234567898', '2023-2027', '3', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'Sophia Rodriguez', 'sophia@university.edu', 'password132', 'STUDENT', 'EN2022009', 'English Literature', '+1234567899', '2022-2026', '5', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(11, 'Daniel Kim', 'daniel@university.edu', 'password133', 'STUDENT', 'CS2021020', 'Computer Science', '+1234567900', '2021-2025', '7', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(12, 'Olivia Chen', 'olivia@university.edu', 'password134', 'STUDENT', 'IT2023006', 'Information Technology', '+1234567901', '2023-2027', '3', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(13, 'James Wilson', 'james@university.edu', 'password135', 'STUDENT', 'EC2022014', 'Electronics', '+1234567902', '2022-2026', '5', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(14, 'Isabella Martinez', 'isabella@university.edu', 'password136', 'STUDENT', 'ME2021018', 'Mechanical Engineering', '+1234567903', '2021-2025', '7', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(15, 'Christopher Taylor', 'chris@university.edu', 'password137', 'STUDENT', 'CE2023010', 'Civil Engineering', '+1234567904', '2023-2027', '3', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Faculty
(16, 'Dr. Jane Smith', 'jane@university.edu', 'password456', 'FACULTY', 'FAC001', 'Computer Science', '+1234567905', NULL, NULL, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(17, 'Prof. Robert Anderson', 'robert@university.edu', 'password457', 'FACULTY', 'FAC002', 'Information Technology', '+1234567906', NULL, NULL, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(18, 'Dr. Maria Lopez', 'maria@university.edu', 'password458', 'FACULTY', 'FAC003', 'Electronics', '+1234567907', NULL, NULL, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(19, 'Prof. David Thompson', 'david@university.edu', 'password459', 'FACULTY', 'FAC004', 'Mechanical Engineering', '+1234567908', NULL, NULL, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(20, 'Dr. Jennifer White', 'jennifer@university.edu', 'password460', 'FACULTY', 'FAC005', 'Civil Engineering', '+1234567909', NULL, NULL, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Staff & Admin
(21, 'Bob Wilson', 'bob@university.edu', 'password789', 'ADMIN', 'ADM001', 'Administration', '+1234567910', NULL, NULL, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(22, 'Alice Johnson', 'alice@university.edu', 'password101', 'STAFF', 'STAFF001', 'Student Affairs', '+1234567911', NULL, NULL, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(23, 'Mark Davis', 'mark@university.edu', 'password102', 'STAFF', 'STAFF002', 'Library Services', '+1234567912', NULL, NULL, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(24, 'Linda Brown', 'linda@university.edu', 'password103', 'STAFF', 'STAFF003', 'Sports Department', '+1234567913', NULL, NULL, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(25, 'Tom Garcia', 'tom@university.edu', 'password104', 'ADMIN', 'ADM002', 'IT Services', '+1234567914', NULL, NULL, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===============================
-- = LOST ITEMS (15 records)
-- ===============================
INSERT INTO lost_items (id, item_name, description, location, lost_date, status, category, reward_amount, contact_info, urgent, is_anonymous, created_at, updated_at, user_id) VALUES 
(1, 'Blue Backpack', 'Blue Jansport backpack with my name tag inside. Contains important textbooks and laptop', 'Library 2nd Floor', DATEADD('DAY', -2, CURRENT_TIMESTAMP), 'PENDING', 'ACCESSORIES', 50.0, 'john@university.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(2, 'iPhone 12', 'Black iPhone 12 with a red protective case. Has a crack on the screen', 'Student Center Cafeteria', DATEADD('DAY', -1, CURRENT_TIMESTAMP), 'SEARCHING', 'ELECTRONICS', 100.0, '+1234567891', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
(3, 'Car Keys', 'Honda keys with university keychain and small teddy bear keyring', 'Parking Lot B', DATEADD('DAY', -3, CURRENT_TIMESTAMP), 'PENDING', 'OTHER', 20.0, 'michael@university.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
(4, 'Laptop Charger', 'Dell laptop charger, 65W with black cable. Model PA-1650-02D3', 'Computer Lab 301', DATEADD('DAY', -4, CURRENT_TIMESTAMP), 'SEARCHING', 'ELECTRONICS', 30.0, 'sarah@university.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),
(5, 'Red Wallet', 'Red leather wallet with student ID and some cash inside', 'Canteen Area', DATEADD('DAY', -1, CURRENT_TIMESTAMP), 'PENDING', 'ACCESSORIES', 40.0, 'alex@university.edu', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5),
(6, 'Chemistry Textbook', 'Organic Chemistry by Morrison & Boyd, heavily highlighted', 'Chemistry Lab 205', DATEADD('DAY', -5, CURRENT_TIMESTAMP), 'PENDING', 'BOOKS', 15.0, 'lisa@university.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6),
(7, 'Black Umbrella', 'Large black umbrella with wooden handle', 'Main Entrance', DATEADD('DAY', -2, CURRENT_TIMESTAMP), 'SEARCHING', 'OTHER', 10.0, 'ryan@university.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7),
(8, 'Wireless Earbuds', 'Apple AirPods Pro in white charging case', 'Gymnasium', DATEADD('DAY', -3, CURRENT_TIMESTAMP), 'PENDING', 'ELECTRONICS', 80.0, 'maya@university.edu', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8),
(9, 'Calculator', 'Casio scientific calculator FX-991ES Plus', 'Mathematics Department', DATEADD('DAY', -6, CURRENT_TIMESTAMP), 'PENDING', 'OTHER', 25.0, 'kevin@university.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 9),
(10, 'Water Bottle', 'Stainless steel water bottle with university logo', 'Sports Complex', DATEADD('DAY', -1, CURRENT_TIMESTAMP), 'SEARCHING', 'ACCESSORIES', 12.0, 'sophia@university.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10),
(11, 'Notebook Set', 'Set of 3 spiral notebooks with class notes', 'Lecture Hall A', DATEADD('DAY', -4, CURRENT_TIMESTAMP), 'PENDING', 'BOOKS', 18.0, 'daniel@university.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 11),
(12, 'USB Drive', '32GB SanDisk USB drive with important project files', 'Computer Center', DATEADD('DAY', -2, CURRENT_TIMESTAMP), 'SEARCHING', 'ELECTRONICS', 35.0, 'olivia@university.edu', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 12),
(13, 'Prescription Glasses', 'Black frame glasses with anti-glare coating', 'Library Reading Room', DATEADD('DAY', -7, CURRENT_TIMESTAMP), 'PENDING', 'ACCESSORIES', 45.0, 'james@university.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 13),
(14, 'Lab Coat', 'White lab coat size M with name embroidered', 'Physics Lab 401', DATEADD('DAY', -3, CURRENT_TIMESTAMP), 'SEARCHING', 'CLOTHING', 22.0, 'isabella@university.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 14),
(15, 'Wristwatch', 'Silver analog watch with leather strap', 'Student Parking', DATEADD('DAY', -5, CURRENT_TIMESTAMP), 'PENDING', 'ACCESSORIES', 60.0, 'chris@university.edu', false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 15);

-- ===============================
-- = FOUND ITEMS (10 records)
-- ===============================
INSERT INTO found_items (id, item_name, description, location, found_date, status, category, contact_info, distinctive_features, handover_location, verification_required, is_anonymous, created_at, updated_at, reported_by_id) VALUES 
(1, 'Red Notebook', 'Red spiral notebook with chemistry notes and formulas', 'Science Building Room 205', DATEADD('DAY', -1, CURRENT_TIMESTAMP), 'AVAILABLE', 'BOOKS', 'jane@university.edu', 'Has "Sarah" written on the cover', 'Student Services Office', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 16),
(2, 'Silver Watch', 'Silver digital watch, brand appears to be Casio with black strap', 'Gymnasium Locker Room', DATEADD('DAY', -2, CURRENT_TIMESTAMP), 'AVAILABLE', 'ACCESSORIES', 'robert@university.edu', 'Shows time in 24-hour format, has small scratch on face', 'Security Office', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 17),
(3, 'Reading Glasses', 'Black frame reading glasses in a brown leather case', 'Library Study Room 3', DATEADD('DAY', -3, CURRENT_TIMESTAMP), 'AVAILABLE', 'ACCESSORIES', 'maria@university.edu', 'Progressive lenses, slight wear on nose pads', 'Library Information Desk', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 18),
(4, 'Mobile Phone', 'Samsung Galaxy phone with cracked screen protector', 'Cafeteria Table 12', DATEADD('DAY', -1, CURRENT_TIMESTAMP), 'AVAILABLE', 'ELECTRONICS', 'david@university.edu', 'Blue protective case with stickers', 'Student Affairs Office', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 19),
(5, 'Textbook', 'Engineering Mathematics by K.A. Stroud', 'Engineering Block Corridor', DATEADD('DAY', -4, CURRENT_TIMESTAMP), 'AVAILABLE', 'BOOKS', 'jennifer@university.edu', 'Bookmarks on pages 150-200', 'Faculty Office 301', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 20),
(6, 'House Keys', 'Set of 4 keys on a metal ring with car remote', 'Parking Lot A', DATEADD('DAY', -2, CURRENT_TIMESTAMP), 'AVAILABLE', 'OTHER', 'alice@university.edu', 'Honda car remote attached', 'Security Desk', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 22),
(7, 'Headphones', 'Sony over-ear headphones, black color', 'Music Room 105', DATEADD('DAY', -3, CURRENT_TIMESTAMP), 'AVAILABLE', 'ELECTRONICS', 'mark@university.edu', 'Slightly worn padding', 'Library Front Desk', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 23),
(8, 'Scarf', 'Woolen scarf, blue and white striped', 'Main Building Entrance', DATEADD('DAY', -5, CURRENT_TIMESTAMP), 'AVAILABLE', 'CLOTHING', 'linda@university.edu', 'Soft wool material, about 6 feet long', 'Lost & Found Counter', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 24),
(9, 'Pen Drive', '16GB Kingston USB with keychain', 'Lab 205', DATEADD('DAY', -1, CURRENT_TIMESTAMP), 'AVAILABLE', 'ELECTRONICS', 'tom@university.edu', 'Red colored casing', 'IT Help Desk', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 25),
(10, 'ID Card', 'Student ID card in plastic holder', 'Corridor B-Block', DATEADD('DAY', -6, CURRENT_TIMESTAMP), 'CLAIMED', 'DOCUMENTS', 'bob@university.edu', 'Photo ID of female student', 'Administration Office', true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 21);

-- ===============================
-- = EVENTS (15 records)
-- ===============================
INSERT INTO events (id, title, description, event_date, location, status, created_at, updated_at, posted_by_id) VALUES 
(1, 'Annual Tech Fest 2025', 'Join us for the biggest technology festival featuring workshops, competitions, and exhibitions from top tech companies.', DATEADD('DAY', 30, CURRENT_TIMESTAMP), 'Main Auditorium', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 16),
(2, 'Career Fair 2025', 'Meet with recruiters from leading companies. Bring your resume and dress professionally.', DATEADD('DAY', 15, CURRENT_TIMESTAMP), 'Student Center Hall', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 21),
(3, 'Cultural Night', 'Showcase your talents! Music, dance, drama, and art performances by students.', DATEADD('DAY', 7, CURRENT_TIMESTAMP), 'University Theater', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 17),
(4, 'Science Exhibition', 'Display of innovative projects by science students across all departments.', DATEADD('DAY', 20, CURRENT_TIMESTAMP), 'Science Block Atrium', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 18),
(5, 'Sports Day 2025', 'Annual sports competition with various indoor and outdoor games.', DATEADD('DAY', 25, CURRENT_TIMESTAMP), 'Sports Complex', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 24),
(6, 'Coding Marathon', '48-hour coding competition with exciting prizes and industry mentorship.', DATEADD('DAY', 12, CURRENT_TIMESTAMP), 'Computer Lab Complex', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 16),
(7, 'Green Campus Initiative', 'Tree plantation drive and environmental awareness program.', DATEADD('DAY', 5, CURRENT_TIMESTAMP), 'University Garden', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 22),
(8, 'Alumni Meet 2025', 'Connect with successful alumni and learn from their experiences.', DATEADD('DAY', 35, CURRENT_TIMESTAMP), 'Convention Center', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 21),
(9, 'Research Symposium', 'Presentation of research papers by faculty and PhD students.', DATEADD('DAY', 18, CURRENT_TIMESTAMP), 'Conference Hall', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 19),
(10, 'Entrepreneurship Workshop', 'Learn about starting your own business from successful entrepreneurs.', DATEADD('DAY', 10, CURRENT_TIMESTAMP), 'Business Incubation Center', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 17),
(11, 'Blood Donation Camp', 'Annual blood donation drive in collaboration with city hospital.', DATEADD('DAY', 8, CURRENT_TIMESTAMP), 'Medical Center', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 22),
(12, 'Photography Contest', 'Campus photography competition with theme "Life at University".', DATEADD('DAY', 22, CURRENT_TIMESTAMP), 'Art Gallery', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 18),
(13, 'Model United Nations', '3-day MUN conference simulating real UN proceedings.', DATEADD('DAY', 28, CURRENT_TIMESTAMP), 'Debate Hall', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 20),
(14, 'Food Festival', 'Taste dishes from different cultures prepared by international students.', DATEADD('DAY', 14, CURRENT_TIMESTAMP), 'Campus Courtyard', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 23),
(15, 'Robotics Competition', 'Build and program robots to compete in various challenges.', DATEADD('DAY', 40, CURRENT_TIMESTAMP), 'Engineering Workshop', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 19);

-- ===============================
-- = NOTICES (10 records)
-- ===============================
INSERT INTO notices (id, title, description, priority, category, valid_until, status, created_at, updated_at, posted_by_id) VALUES 
(1, 'Semester Exam Schedule Released', 'The final examination schedule for the current semester has been released. Students can check their exam dates and timings on the university portal.', 'HIGH', 'ACADEMIC', DATEADD('DAY', 60, CURRENT_TIMESTAMP), 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
(2, 'Library Hours Extended', 'Due to upcoming exams, the library will remain open 24/7 starting next week. Students need to show their ID cards for access after 10 PM.', 'NORMAL', 'ADMINISTRATIVE', DATEADD('DAY', 45, CURRENT_TIMESTAMP), 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
(3, 'New Parking Regulations', 'Please note the new parking regulations effective immediately. All vehicles must display valid parking permits. Unauthorized parking will result in fines.', 'HIGH', 'ADMINISTRATIVE', NULL, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
(4, 'Sports Week Registration Open', 'Registration for annual sports week is now open. Multiple sports categories available. Register at the sports office or online portal.', 'NORMAL', 'EVENT', DATEADD('DAY', 20, CURRENT_TIMESTAMP), 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),
(5, 'Scholarship Applications Due', 'Merit-based scholarship applications for next semester are due by end of this month. Submit all required documents to the scholarship office.', 'HIGH', 'ACADEMIC', DATEADD('DAY', 15, CURRENT_TIMESTAMP), 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5),
(6, 'Campus WiFi Maintenance', 'Campus-wide WiFi maintenance scheduled for this weekend. Internet services may be intermittent during this period.', 'NORMAL', 'ADMINISTRATIVE', DATEADD('DAY', 3, CURRENT_TIMESTAMP), 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6),
(7, 'Guest Lecture Series', 'Industry experts will be conducting guest lectures throughout the month. Check department notice boards for schedules.', 'NORMAL', 'ACADEMIC', DATEADD('DAY', 30, CURRENT_TIMESTAMP), 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7),
(8, 'Cafeteria Menu Update', 'New healthy meal options have been added to the cafeteria menu. Special dietary requirements can be accommodated upon request.', 'LOW', 'GENERAL', DATEADD('DAY', 90, CURRENT_TIMESTAMP), 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8),
(9, 'Fire Safety Drill', 'Mandatory fire safety drill scheduled for all buildings next Tuesday at 2 PM. All students and staff must participate.', 'HIGH', 'ADMINISTRATIVE', DATEADD('DAY', 7, CURRENT_TIMESTAMP), 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 9),
(10, 'Student Council Elections', 'Nominations open for student council elections. Interested students can submit their applications to the student affairs office.', 'NORMAL', 'EVENT', DATEADD('DAY', 25, CURRENT_TIMESTAMP), 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10);
