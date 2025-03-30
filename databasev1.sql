create database Child;
Drop database  Child;

Use Child;

Drop table Users;
CREATE TABLE Users (
  UserID INT PRIMARY KEY AUTO_INCREMENT,  -- ID người dùng, tự động tăng
  Username VARCHAR(50) NOT NULL UNIQUE,   -- Tên đăng nhập (duy nhất, không rỗng)
  PasswordHash VARCHAR(255) NOT NULL,     -- Mật khẩu đã mã hóa
  Email VARCHAR(100) NOT NULL UNIQUE,     -- Email người dùng (duy nhất)
  FullName VARCHAR(100) NOT NULL,         -- Họ và tên đầy đủ
  RoleName ENUM('Admin', 'Doctor', 'User') NOT NULL, -- Vai trò của người dùng (Admin, Doctor, User)
  CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP -- Ngày tạo tài khoản
);
-- Mẫu 1: Tài khoản Admin
INSERT INTO Users (Username, PasswordHash, Email, FullName, RoleName)
VALUES 
-- 5 admin
('Admin_1', 'Dat888', 'Ledat@gmail.com', 'Lê Thanh Đạt ', 'Admin'),
-- 50 docters
('lethibich235', 'Bich235', 'bichle.doctor@gmail.com', 'Lê Thị Bích', 'Doctor'),
('dinhvannam682', 'Nam682', 'namdinh.doctor@gmail.com', 'Đinh Văn Nam', 'Doctor'),
-- 200 Users
('nguyenvantuan578', 'Tuan578', 'N.V.Tuan@gmail.com', 'Nguyễn Văn Tuấn', 'User'),
('lethimai924', 'Mai924', 'L.T.Mai@gmail.com', 'Lê Thị Mai', 'User'),
('phamvanhung367', 'Hung367', 'P.V.Hung@gmail.com', 'Phạm Văn Hùng', 'User'),
('duongthilan452', 'Lan452', 'D.T.Lan@gmail.com', 'Dương Thị Lan', 'User'),
('phanvanminh819', 'Minh819', 'P.V.Minh@gmail.com', 'Phan Văn Minh', 'User');
select* from Users;
-- BẢNG 2: ChildProfiles - Quản lý hồ sơ trẻ em
CREATE TABLE ChildProfiles (
  ChildID INT PRIMARY KEY AUTO_INCREMENT,  -- ID hồ sơ trẻ, tự động tăng
  UserID INT NOT NULL,                      -- Chủ sở hữu hồ sơ này (người dùng)
  ChildName VARCHAR(100) NOT NULL,         -- Tên trẻ
  DateOfBirth DATE NOT NULL,               -- Ngày sinh
  Gender ENUM('Male', 'Female') NOT NULL,  -- Giới tính của trẻ
  HeightCm DECIMAL(5,2),                   -- Chiều cao (cm)
  WeightKg DECIMAL(5,2),                   -- Cân nặng (kg)
  BMI DECIMAL(4,2),                        -- Chỉ số BMI
  FOREIGN KEY (UserID) REFERENCES Users(UserID) -- Liên kết với Users để biết hồ sơ này thuộc về ai
);
INSERT INTO ChildProfiles (UserID, ChildName, DateOfBirth, Gender, HeightCm, WeightKg, BMI)
VALUES 
(4, 'Nguyễn Văn An', '2018-05-15', 'Male', 120.50, 20.30, 13.97), -- Con của nguyenvantuan578
(5, 'Lê Thị Hồng', '2019-08-22', 'Female', 115.00, 18.50, 13.99), -- Con của lethimai924
(6, 'Phạm Văn Bình', '2020-01-10', 'Male', 100.20, 15.80, 15.74), -- Con của phamvanhung367
(7, 'Dương Thị Hoa', '2017-12-03', 'Female', 130.00, 25.00, 14.79), -- Con của duongthilan452
(8, 'Phan Văn Khôi', '2016-09-18', 'Male', 135.70, 28.40, 15.41); -- Con của phanvanminh819
SELECT * FROM ChildProfiles;

-- BẢNG 3: Memberships - Quản lý gói thành viên & thanh toán
CREATE TABLE Memberships (
  MembershipID INT PRIMARY KEY AUTO_INCREMENT, -- ID gói thành viên, tự động tăng
  UserID INT NOT NULL UNIQUE,                  -- ID người dùng sở hữu gói này
  MembershipType ENUM('Basic', 'Premium') NOT NULL, -- Loại gói thành viên
  Price DECIMAL(10,2) NOT NULL,                -- Giá tiền của gói
  StartDate DATE NOT NULL,                     -- Ngày bắt đầu
  EndDate DATE NOT NULL,                       -- Ngày hết hạn
  PaymentMethod VARCHAR(50) NOT NULL,          -- Phương thức thanh toán
  TransactionStatus ENUM('Success', 'Failed') NOT NULL, -- Trạng thái thanh toán
  FOREIGN KEY (UserID) REFERENCES Users(UserID) -- Liên kết với Users
);
INSERT INTO Memberships (UserID, MembershipType, Price, StartDate, EndDate, PaymentMethod, TransactionStatus)
VALUES 
(4, 'Basic', 150000.00, '2025-03-01', '2025-06-01', 'Credit Card', 'Success'), -- nguyenvantuan578
(5, 'Premium', 300000.00, '2025-03-15', '2025-09-15', 'Bank Transfer', 'Success'), -- lethimai924
(6, 'Basic', 150000.00, '2025-02-20', '2025-05-20', 'Cash', 'Success'), -- phamvanhung367
(7, 'Premium', 300000.00, '2025-03-10', '2025-09-10', 'Credit Card', 'Success'), -- duongthilan452
(8, 'Basic', 150000.00, '2025-03-21', '2025-06-21', 'Mobile Payment', 'Success'); -- phanvanminh819
SELECT * FROM Memberships;

-- BẢNG 4: Alerts - Quản lý cảnh báo sức khỏe của trẻ
CREATE TABLE Alerts (
  AlertID INT PRIMARY KEY AUTO_INCREMENT, -- ID cảnh báo, tự động tăng
  ChildID INT NOT NULL,                   -- Hồ sơ trẻ nhận cảnh báo
  AlertType VARCHAR(50) NOT NULL,         -- Loại cảnh báo (Sốt, Dị ứng, ...)
  Description TEXT NOT NULL,              -- Mô tả chi tiết
  AlertDate DATETIME DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo cảnh báo
  FOREIGN KEY (ChildID) REFERENCES ChildProfiles(ChildID) -- Liên kết với ChildProfiles
);

INSERT INTO Alerts (ChildID, AlertType, Description, AlertDate)
VALUES 
(1, 'Fever', 'Trẻ bị sốt cao 38.5°C, cần theo dõi thêm', '2025-03-20 10:30:00'), -- Nguyễn Văn An
(2, 'Allergy', 'Phát ban đỏ sau khi ăn hải sản', '2025-03-19 14:15:00'), -- Lê Thị Hồng
(3, 'Cough', 'Ho kéo dài hơn 3 ngày, cần thăm khám', '2025-03-21 09:00:00'), -- Phạm Văn Bình
(4, 'Weight Loss', 'Cân nặng giảm bất thường trong 1 tháng', '2025-03-18 16:45:00'), -- Dương Thị Hoa
(5, 'Fever', 'Sốt nhẹ 37.8°C kèm mệt mỏi', '2025-03-21 08:20:00'); -- Phan Văn Khôi
SELECT * FROM Alerts;
-- BẢNG 5: Consultations - Quản lý tư vấn giữa người dùng và bác sĩ
CREATE TABLE Consultations (
  ConsultationID INT PRIMARY KEY AUTO_INCREMENT,  -- ID tư vấn, tự động tăng
  UserID INT NOT NULL,                            -- Người yêu cầu tư vấn
  DoctorID INT NOT NULL,                          -- Bác sĩ tư vấn
  ChildID INT NOT NULL,                           -- Hồ sơ trẻ liên quan
  RequestDate DATETIME DEFAULT CURRENT_TIMESTAMP, -- Ngày yêu cầu tư vấn
  Message TEXT NOT NULL,                          -- Nội dung tư vấn
  Status ENUM('Pending', 'Completed') DEFAULT 'Pending', -- Trạng thái tư vấn
  FeedbackText TEXT,                              -- Nội dung phản hồi từ user
  FeedbackCreatedAt DATETIME,                     -- Thời gian phản hồi
  FOREIGN KEY (UserID) REFERENCES Users(UserID),
  FOREIGN KEY (DoctorID) REFERENCES Users(UserID),
  FOREIGN KEY (ChildID) REFERENCES ChildProfiles(ChildID)
);
INSERT INTO Consultations (UserID, DoctorID, ChildID, RequestDate, Message, Status, FeedbackText, FeedbackCreatedAt)
VALUES 
(4, 2, 1, '2025-03-20 11:00:00', 'Con tôi bị sốt cao, cần tư vấn gấp', 'Completed', 'Cảm ơn bác sĩ, rất hữu ích!', '2025-03-20 15:00:00'), -- nguyenvantuan578 & lethibich235
(5, 3, 2, '2025-03-19 15:00:00', 'Con tôi bị phát ban, có phải dị ứng không?', 'Pending', NULL, NULL), -- lethimai924 & dinhvannam682
(6, 2, 3, '2025-03-21 10:00:00', 'Trẻ ho nhiều, có cần đi khám không?', 'Pending', NULL, NULL), -- phamvanhung367 & lethibich235
(7, 3, 4, '2025-03-18 17:00:00', 'Con tôi giảm cân bất thường, cần kiểm tra gì?', 'Completed', 'Bác sĩ tư vấn rất tận tình', '2025-03-19 09:00:00'), -- duongthilan452 & dinhvannam682
(8, 2, 5, '2025-03-21 09:00:00', 'Con tôi sốt nhẹ, có cần thuốc không?', 'Pending', NULL, NULL); -- phanvanminh819 & lethibich235
SELECT * FROM Consultations;

-- BẢNG 6: Reports - Quản lý báo cáo & dashboard
CREATE TABLE Reports (
  ReportID INT PRIMARY KEY AUTO_INCREMENT,    -- ID báo cáo, tự động tăng
  ReportName VARCHAR(100) NOT NULL,           -- Tên báo cáo
  Description TEXT,                           -- Mô tả báo cáo
  GeneratedAt DATETIME DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo báo cáo
  GeneratedByUserID INT NOT NULL,             -- Người tạo báo cáo (Admin)
  ReportData JSON,                            -- Dữ liệu thống kê dạng JSON
  FOREIGN KEY (GeneratedByUserID) REFERENCES Users(UserID) -- Liên kết với Users
);
INSERT INTO Reports (ReportName, Description, GeneratedAt, GeneratedByUserID, ReportData)
VALUES 
('Health Stats Mar 2025', 'Thống kê sức khỏe trẻ em tháng 3/2025', '2025-03-21 14:00:00', 1, '{"total_children": 5, "avg_bmi": 14.78, "alerts": 5}'), -- Admin_1
('Membership Report', 'Báo cáo gói thành viên tháng 3/2025', '2025-03-21 15:00:00', 1, '{"basic": 3, "premium": 2, "total_revenue": 1050000}'); -- Admin_1
SELECT * FROM Reports;
-- BẢNG 7: ContentPosts - Quản lý Blog & FAQ
CREATE TABLE ContentPosts (
  PostID INT PRIMARY KEY AUTO_INCREMENT,  -- ID bài viết, tự động tăng
  Title VARCHAR(255) NOT NULL,            -- Tiêu đề bài viết
  Content TEXT NOT NULL,                  -- Nội dung bài viết
  PostType ENUM('Blog', 'FAQ') NOT NULL,  -- Loại bài (Blog hoặc FAQ)
  AuthorID INT,                            -- Người viết bài (nếu có)
  CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo bài viết
  FOREIGN KEY (AuthorID) REFERENCES Users(UserID) -- Liên kết với Users (Admin viết bài)
);
INSERT INTO ContentPosts (Title, Content, PostType, AuthorID, CreatedAt)
VALUES 
('Cách chăm sóc trẻ bị sốt', 'Hướng dẫn hạ sốt tại nhà và khi nào cần đi bác sĩ...', 'Blog', 1, '2025-03-20 08:00:00'), -- Admin_1
('Dị ứng ở trẻ: Những điều cần biết', 'Nguyên nhân, triệu chứng và cách xử lý dị ứng...', 'Blog', 1, '2025-03-19 10:00:00'), -- Admin_1
('Trẻ ho lâu có nguy hiểm không?', 'Giải đáp thắc mắc về ho kéo dài ở trẻ...', 'FAQ', 1, '2025-03-21 09:00:00'); -- Admin_1
SELECT * FROM ContentPosts;