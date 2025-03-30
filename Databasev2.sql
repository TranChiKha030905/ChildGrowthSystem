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
('Admin_Vu_001','Vu111','Vu@gmail.com','Đồng Huy Vũ','Admin'),
('Admin_Kha_002','Kha222','Kha@gmail.com','Nguyễn Văn Kha','Admin'),
('Admin_Dam_003','Dam333','Dam@gmail.com','Nguyễn Văn Đam','Admin'),
('Admin_Hung_004','Hung444','Hung@gmail.com','Nguyễn Văn Hùng','Admin'),
('Admin_Dat_005','Dat555','Dat@gmail.com','Lê Thanh Đạt','Admin'),
-- 50 docters
('Doctor_Bich_006', 'Bich006', 'Bich007@gmail.com', 'Lê Thị Bích', 'Doctor'),
('Doctor_Muoi_007', 'Muoi007', 'Muoi008@gmail.com', 'Lê Văn Mười', 'Doctor'),
('Doctor_Hùng_008', 'Hung008', 'Hung009@gmail.com', 'Nguyễn Văn Hùng', 'Doctor'),
('Doctor_Lan_009', 'Lan009', 'Lan010@gmail.com', 'Trần Thị Lan', 'Doctor'),
('Doctor_Dũng_010', 'Dung010', 'Dung011@gmail.com', 'Phạm Văn Dũng', 'Doctor'),
('Doctor_Mai_011', 'Mai011', 'Mai012@gmail.com', 'Lê Thị Mai', 'Doctor'),
('Doctor_Tuấn_012', 'Tuan012', 'Tuan013@gmail.com', 'Nguyễn Văn Tuấn', 'Doctor'),
('Doctor_Hoa_013', 'Hoa013', 'Hoa014@gmail.com', 'Trần Thị Hoa', 'Doctor'),
('Doctor_Mạnh_014', 'Manh014', 'Manh015@gmail.com', 'Phạm Văn Mạnh', 'Doctor'),
('Doctor_Linh_015', 'Linh015', 'Linh016@gmail.com', 'Lê Thị Linh', 'Doctor'),
('Doctor_Phong_016', 'Phong016', 'Phong017@gmail.com', 'Nguyễn Văn Phong', 'Doctor'),
('Doctor_Thảo_017', 'Thao017', 'Thao018@gmail.com', 'Trần Thị Thảo', 'Doctor'),
('Doctor_Long_018', 'Long018', 'Long019@gmail.com', 'Phạm Văn Long', 'Doctor'),
('Doctor_An_019', 'An019', 'An020@gmail.com', 'Lê Thị An', 'Doctor'),
('Doctor_Hải_020', 'Hai020', 'Hai021@gmail.com', 'Nguyễn Văn Hải', 'Doctor'),
('Doctor_Ngọc_021', 'Ngoc021', 'Ngoc022@gmail.com', 'Trần Thị Ngọc', 'Doctor'),
('Doctor_Duy_022', 'Duy022', 'Duy023@gmail.com', 'Phạm Văn Duy', 'Doctor'),
('Doctor_Hương_023', 'Huong023', 'Huong024@gmail.com', 'Lê Thị Hương', 'Doctor'),
('Doctor_Bình_024', 'Binh024', 'Binh025@gmail.com', 'Nguyễn Văn Bình', 'Doctor'),
('Doctor_Thu_025', 'Thu025', 'Thu026@gmail.com', 'Trần Thị Thu', 'Doctor'),
('Doctor_Nam_026', 'Nam026', 'Nam027@gmail.com', 'Phạm Văn Nam', 'Doctor'),
('Doctor_Yến_027', 'Yen027', 'Yen028@gmail.com', 'Lê Thị Yến', 'Doctor'),
('Doctor_Khôi_028', 'Khoi028', 'Khoi029@gmail.com', 'Nguyễn Văn Khôi', 'Doctor'),
('Doctor_Hằng_029', 'Hang029', 'Hang030@gmail.com', 'Trần Thị Hằng', 'Doctor'),
('Doctor_Quang_030', 'Quang030', 'Quang031@gmail.com', 'Phạm Văn Quang', 'Doctor'),
('Doctor_Trang_031', 'Trang031', 'Trang032@gmail.com', 'Lê Thị Trang', 'Doctor'),
('Doctor_Việt_032', 'Viet032', 'Viet033@gmail.com', 'Nguyễn Văn Việt', 'Doctor'),
('Doctor_Minh_033', 'Minh033', 'Minh034@gmail.com', 'Trần Thị Minh', 'Doctor'),
('Doctor_Thắng_034', 'Thang034', 'Thang035@gmail.com', 'Phạm Văn Thắng', 'Doctor'),
('Doctor_Hiền_035', 'Hien035', 'Hien036@gmail.com', 'Lê Thị Hiền', 'Doctor'),
('Doctor_Trung_036', 'Trung036', 'Trung037@gmail.com', 'Nguyễn Văn Trung', 'Doctor'),
('Doctor_Thúy_037', 'Thuy037', 'Thuy038@gmail.com', 'Trần Thị Thúy', 'Doctor'),
('Doctor_Hiệp_038', 'Hiep038', 'Hiep039@gmail.com', 'Phạm Văn Hiệp', 'Doctor'),
('Doctor_Lệ_039', 'Le039', 'Le040@gmail.com', 'Lê Thị Lệ', 'Doctor'),
('Doctor_Đức_040', 'Duc040', 'Duc041@gmail.com', 'Nguyễn Văn Đức', 'Doctor'),
('Doctor_Hạnh_041', 'Hanh041', 'Hanh042@gmail.com', 'Trần Thị Hạnh', 'Doctor'),
('Doctor_Cường_042', 'Cuong042', 'Cuong043@gmail.com', 'Phạm Văn Cường', 'Doctor'),
('Doctor_Tâm_043', 'Tam043', 'Tam044@gmail.com', 'Lê Thị Tâm', 'Doctor'),
('Doctor_Sơn_044', 'Son044', 'Son045@gmail.com', 'Nguyễn Văn Sơn', 'Doctor'),
('Doctor_Phương_045', 'Phuong045', 'Phuong046@gmail.com', 'Trần Thị Phương', 'Doctor'),
('Doctor_Khoa_046', 'Khoa046', 'Khoa047@gmail.com', 'Phạm Văn Khoa', 'Doctor'),
('Doctor_Thy_047', 'Thy047', 'Thy048@gmail.com', 'Lê Thị Thy', 'Doctor'),
('Doctor_Thiên_048', 'Thien048', 'Thien049@gmail.com', 'Nguyễn Văn Thiên', 'Doctor'),
('Doctor_Hồng_049', 'Hong049', 'Hong050@gmail.com', 'Trần Thị Hồng', 'Doctor'),
('Doctor_Tín_050', 'Tin050', 'Tin051@gmail.com', 'Phạm Văn Tín', 'Doctor'),
('Doctor_Nhi_051', 'Nhi051', 'Nhi052@gmail.com', 'Lê Thị Nhi', 'Doctor'),
('Doctor_Đạt_052', 'Dat052', 'Dat053@gmail.com', 'Nguyễn Văn Đạt', 'Doctor'),
('Doctor_Ly_053', 'Ly053', 'Ly054@gmail.com', 'Trần Thị Ly', 'Doctor'),
('Doctor_Khánh_054', 'Khanh054', 'Khanh055@gmail.com', 'Phạm Văn Khánh', 'Doctor'),
('Doctor_Vân_055', 'Van055', 'Van056@gmail.com', 'Lê Thị Vân', 'Doctor'),
('Doctor_Hoàng_056', 'Hoang056', 'Hoang057@gmail.com', 'Nguyễn Văn Hoàng', 'Doctor'),
-- 200 Users
('User_Phước_057', 'Phuoc057', 'Phuoc057@gmail.com', 'Nguyễn Văn Phước', 'User'),
('User_Thanh_058', 'Thanh058', 'Thanh058@gmail.com', 'Lê Thị Thanh', 'User'),
('User_Kiên_059', 'Kien059', 'Kien059@gmail.com', 'Trần Văn Kiên', 'User'),
('User_Huyền_060', 'Huyen060', 'Huyen060@gmail.com', 'Phạm Thị Huyền', 'User'),
('User_Tài_061', 'Tai061', 'Tai061@gmail.com', 'Nguyễn Văn Tài', 'User'),
('User_Hà_062', 'Ha062', 'Ha062@gmail.com', 'Lê Thị Hà', 'User'),
('User_Vinh_063', 'Vinh063', 'Vinh063@gmail.com', 'Trần Văn Vinh', 'User'),
('User_Mỹ_064', 'My064', 'My064@gmail.com', 'Phạm Thị Mỹ', 'User'),
('User_Chiến_065', 'Chien065', 'Chien065@gmail.com', 'Nguyễn Văn Chiến', 'User'),
('User_Duyên_066', 'Duyen066', 'Duyen066@gmail.com', 'Lê Thị Duyên', 'User'),
('User_Đăng_067', 'Dang067', 'Dang067@gmail.com', 'Trần Văn Đăng', 'User'),
('User_Bảo_068', 'Bao068', 'Bao068@gmail.com', 'Phạm Thị Bảo', 'User'),
('User_Phúc_069', 'Phuc069', 'Phuc069@gmail.com', 'Nguyễn Văn Phúc', 'User'),
('User_Nga_070', 'Nga070', 'Nga070@gmail.com', 'Lê Thị Nga', 'User'),
('User_Khang_071', 'Khang071', 'Khang071@gmail.com', 'Trần Văn Khang', 'User'),
('User_Quỳnh_072', 'Quynh072', 'Quynh072@gmail.com', 'Phạm Thị Quỳnh', 'User'),
('User_Thành_073', 'Thanh073', 'Thanh073@gmail.com', 'Nguyễn Văn Thành', 'User'),
('User_Tuyết_074', 'Tuyet074', 'Tuyet074@gmail.com', 'Lê Thị Tuyết', 'User'),
('User_Lộc_075', 'Loc075', 'Loc075@gmail.com', 'Trần Văn Lộc', 'User'),
('User_Kim_076', 'Kim076', 'Kim076@gmail.com', 'Phạm Thị Kim', 'User'),
('User_Anh_077', 'Anh077', 'Anh077@gmail.com', 'Nguyễn Văn Anh', 'User'),
('User_Diệu_078', 'Dieu078', 'Dieu078@gmail.com', 'Lê Thị Diệu', 'User'),
('User_Nhật_079', 'Nhat079', 'Nhat079@gmail.com', 'Trần Văn Nhật', 'User'),
('User_Tú_080', 'Tu080', 'Tu080@gmail.com', 'Phạm Thị Tú', 'User'),
('User_Hòa_081', 'Hoa081', 'Hoa081@gmail.com', 'Nguyễn Văn Hòa', 'User'),
('User_Liễu_082', 'Lieu082', 'Lieu082@gmail.com', 'Lê Thị Liễu', 'User'),
('User_Tiến_083', 'Tien083', 'Tien083@gmail.com', 'Trần Văn Tiến', 'User'),
('User_Hường_084', 'Huong084', 'Huong084@gmail.com', 'Phạm Thị Hường', 'User'),
('User_Định_085', 'Dinh085', 'Dinh085@gmail.com', 'Nguyễn Văn Định', 'User'),
('User_Phượng_086', 'Phuong086', 'Phuong086@gmail.com', 'Lê Thị Phượng', 'User'),
('User_Lâm_087', 'Lam087', 'Lam087@gmail.com', 'Trần Văn Lâm', 'User'),
('User_Ngân_088', 'Ngan088', 'Ngan088@gmail.com', 'Phạm Thị Ngân', 'User'),
('User_Hưng_089', 'Hung089', 'Hung089@gmail.com', 'Nguyễn Văn Hưng', 'User'),
('User_Đào_090', 'Dao090', 'Dao090@gmail.com', 'Lê Thị Đào', 'User'),
('User_Thiện_091', 'Thien091', 'Thien091@gmail.com', 'Trần Văn Thiện', 'User'),
('User_Sương_092', 'Suong092', 'Suong092@gmail.com', 'Phạm Thị Sương', 'User'),
('User_Lương_093', 'Luong093', 'Luong093@gmail.com', 'Nguyễn Văn Lương', 'User'),
('User_Oanh_094', 'Oanh094', 'Oanh094@gmail.com', 'Lê Thị Oanh', 'User'),
('User_Thông_095', 'Thong095', 'Thong095@gmail.com', 'Trần Văn Thông', 'User'),
('User_Vi_096', 'Vi096', 'Vi096@gmail.com', 'Phạm Thị Vi', 'User'),
('User_Tâm_097', 'Tam097', 'Tam097@gmail.com', 'Nguyễn Văn Tâm', 'User'),
('User_Nguyệt_098', 'Nguyet098', 'Nguyet098@gmail.com', 'Lê Thị Nguyệt', 'User'),
('User_Phi_099', 'Phi099', 'Phi099@gmail.com', 'Trần Văn Phi', 'User'),
('User_Hậu_100', 'Hau100', 'Hau100@gmail.com', 'Phạm Thị Hậu', 'User'),
('User_Hiếu_101', 'Hieu101', 'Hieu101@gmail.com', 'Nguyễn Văn Hiếu', 'User'),
('User_Ái_102', 'Ai102', 'Ai102@gmail.com', 'Lê Thị Ái', 'User'),
('User_Đoàn_103', 'Doan103', 'Doan103@gmail.com', 'Trần Văn Đoàn', 'User'),
('User_Thoa_104', 'Thoa104', 'Thoa104@gmail.com', 'Phạm Thị Thoa', 'User'),
('User_Tịnh_105', 'Tinh105', 'Tinh105@gmail.com', 'Nguyễn Văn Tịnh', 'User'),
('User_Kiều_106', 'Kieu106', 'Kieu106@gmail.com', 'Lê Thị Kiều', 'User'),
('User_Khải_107', 'Khai107', 'Khai107@gmail.com', 'Trần Văn Khải', 'User'),
('User_Vỹ_108', 'Vy108', 'Vy108@gmail.com', 'Phạm Thị Vỹ', 'User'),
('User_Quý_109', 'Quy109', 'Quy109@gmail.com', 'Nguyễn Văn Quý', 'User'),
('User_Thắm_110', 'Tham110', 'Tham110@gmail.com', 'Lê Thị Thắm', 'User'),
('User_Tấn_111', 'Tan111', 'Tan111@gmail.com', 'Trần Văn Tấn', 'User'),
('User_Nhiên_112', 'Nhien112', 'Nhien112@gmail.com', 'Phạm Thị Nhiên', 'User'),
('User_Đông_113', 'Dong113', 'Dong113@gmail.com', 'Nguyễn Văn Đông', 'User'),
('User_Tường_114', 'Tuong114', 'Tuong114@gmail.com', 'Lê Thị Tường', 'User'),
('User_Hảo_115', 'Hao115', 'Hao115@gmail.com', 'Trần Văn Hảo', 'User'),
('User_Lợi_116', 'Loi116', 'Loi116@gmail.com', 'Phạm Thị Lợi', 'User'),
('User_Hợp_117', 'Hop117', 'Hop117@gmail.com', 'Nguyễn Văn Hợp', 'User'),
('User_Diễm_118', 'Diem118', 'Diem118@gmail.com', 'Lê Thị Diễm', 'User'),
('User_Nghĩa_119', 'Nghia119', 'Nghia119@gmail.com', 'Trần Văn Nghĩa', 'User'),
('User_Thương_120', 'Thuong120', 'Thuong120@gmail.com', 'Phạm Thị Thương', 'User'),
('User_Toàn_121', 'Toan121', 'Toan121@gmail.com', 'Nguyễn Văn Toàn', 'User'),
('User_Hồng_122', 'Hong122', 'Hong122@gmail.com', 'Lê Thị Hồng', 'User'),
('User_Lực_123', 'Luc123', 'Luc123@gmail.com', 'Trần Văn Lực', 'User'),
('User_Danh_124', 'Danh124', 'Danh124@gmail.com', 'Phạm Thị Danh', 'User'),
('User_Khiệm_125', 'Khiem125', 'Khiem125@gmail.com', 'Nguyễn Văn Khiệm', 'User'),
('User_Điệp_126', 'Diep126', 'Diep126@gmail.com', 'Lê Thị Điệp', 'User'),
('User_Trí_127', 'Tri127', 'Tri127@gmail.com', 'Trần Văn Trí', 'User'),
('User_Hiền_128', 'Hien128', 'Hien128@gmail.com', 'Phạm Thị Hiền', 'User'),
('User_Thụy_129', 'Thuy129', 'Thuy129@gmail.com', 'Nguyễn Văn Thụy', 'User'),
('User_Mơ_130', 'Mo130', 'Mo130@gmail.com', 'Lê Thị Mơ', 'User'),
('User_Sang_131', 'Sang131', 'Sang131@gmail.com', 'Trần Văn Sang', 'User'),
('User_Vân_132', 'Van132', 'Van132@gmail.com', 'Phạm Thị Vân', 'User'),
('User_Hòa_133', 'Hoa133', 'Hoa133@gmail.com', 'Nguyễn Văn Hòa', 'User'),
('User_Thi_134', 'Thi134', 'Thi134@gmail.com', 'Lê Thị Thi', 'User'),
('User_Điền_135', 'Dien135', 'Dien135@gmail.com', 'Trần Văn Điền', 'User'),
('User_Ly_136', 'Ly136', 'Ly136@gmail.com', 'Phạm Thị Ly', 'User'),
('User_Bá_137', 'Ba137', 'Ba137@gmail.com', 'Nguyễn Văn Bá', 'User'),
('User_Hạnh_138', 'Hanh138', 'Hanh138@gmail.com', 'Lê Thị Hạnh', 'User'),
('User_Công_139', 'Cong139', 'Cong139@gmail.com', 'Trần Văn Công', 'User'),
('User_Thuấn_140', 'Thuan140', 'Thuan140@gmail.com', 'Phạm Thị Thuấn', 'User'),
('User_Thức_141', 'Thuc141', 'Thuc141@gmail.com', 'Nguyễn Văn Thức', 'User'),
('User_Mộng_142', 'Mong142', 'Mong142@gmail.com', 'Lê Thị Mộng', 'User'),
('User_Khoa_143', 'Khoa143', 'Khoa143@gmail.com', 'Trần Văn Khoa', 'User'),
('User_Thanh_144', 'Thanh144', 'Thanh144@gmail.com', 'Phạm Thị Thanh', 'User'),
('User_Quân_145', 'Quan145', 'Quan145@gmail.com', 'Nguyễn Văn Quân', 'User'),
('User_Tuyền_146', 'Tuyen146', 'Tuyen146@gmail.com', 'Lê Thị Tuyền', 'User'),
('User_Vũ_147', 'Vu147', 'Vu147@gmail.com', 'Trần Văn Vũ', 'User'),
('User_Hoa_148', 'Hoa148', 'Hoa148@gmail.com', 'Phạm Thị Hoa', 'User'),
('User_Dương_149', 'Duong149', 'Duong149@gmail.com', 'Nguyễn Văn Dương', 'User'),
('User_Lài_150', 'Lai150', 'Lai150@gmail.com', 'Lê Thị Lài', 'User'),
('User_Phát_151', 'Phat151', 'Phat151@gmail.com', 'Trần Văn Phát', 'User'),
('User_Lộc_152', 'Loc152', 'Loc152@gmail.com', 'Phạm Thị Lộc', 'User'),
('User_Nhân_153', 'Nhan153', 'Nhan153@gmail.com', 'Nguyễn Văn Nhân', 'User'),
('User_Hường_154', 'Huong154', 'Huong154@gmail.com', 'Lê Thị Hường', 'User'),
('User_Tín_155', 'Tin155', 'Tin155@gmail.com', 'Trần Văn Tín', 'User'),
('User_Minh_156', 'Minh156', 'Minh156@gmail.com', 'Phạm Thị Minh', 'User'),
('User_Đại_157', 'Dai157', 'Dai157@gmail.com', 'Nguyễn Văn Đại', 'User'),
('User_Thảo_158', 'Thao158', 'Thao158@gmail.com', 'Lê Thị Thảo', 'User'),
('User_Hòa_159', 'Hoa159', 'Hoa159@gmail.com', 'Trần Văn Hòa', 'User'),
('User_Ngọc_160', 'Ngoc160', 'Ngoc160@gmail.com', 'Phạm Thị Ngọc', 'User'),
('User_Thắng_161', 'Thang161', 'Thang161@gmail.com', 'Nguyễn Văn Thắng', 'User'),
('User_Lan_162', 'Lan162', 'Lan162@gmail.com', 'Lê Thị Lan', 'User'),
('User_Mạnh_163', 'Manh163', 'Manh163@gmail.com', 'Trần Văn Mạnh', 'User'),
('User_Thúy_164', 'Thuy164', 'Thuy164@gmail.com', 'Phạm Thị Thúy', 'User'),
('User_Long_165', 'Long165', 'Long165@gmail.com', 'Nguyễn Văn Long', 'User'),
('User_Hương_166', 'Huong166', 'Huong166@gmail.com', 'Lê Thị Hương', 'User'),
('User_Phong_167', 'Phong167', 'Phong167@gmail.com', 'Trần Văn Phong', 'User'),
('User_An_168', 'An168', 'An168@gmail.com', 'Phạm Thị An', 'User'),
('User_Hải_169', 'Hai169', 'Hai169@gmail.com', 'Nguyễn Văn Hải', 'User'),
('User_Mai_170', 'Mai170', 'Mai170@gmail.com', 'Lê Thị Mai', 'User'),
('User_Duy_171', 'Duy171', 'Duy171@gmail.com', 'Trần Văn Duy', 'User'),
('User_Thu_172', 'Thu172', 'Thu172@gmail.com', 'Phạm Thị Thu', 'User'),
('User_Bình_173', 'Binh173', 'Binh173@gmail.com', 'Nguyễn Văn Bình', 'User'),
('User_Yến_174', 'Yen174', 'Yen174@gmail.com', 'Lê Thị Yến', 'User'),
('User_Nam_175', 'Nam175', 'Nam175@gmail.com', 'Trần Văn Nam', 'User'),
('User_Hằng_176', 'Hang176', 'Hang176@gmail.com', 'Phạm Thị Hằng', 'User'),
('User_Khôi_177', 'Khoi177', 'Khoi177@gmail.com', 'Nguyễn Văn Khôi', 'User'),
('User_Trang_178', 'Trang178', 'Trang178@gmail.com', 'Lê Thị Trang', 'User'),
('User_Quang_179', 'Quang179', 'Quang179@gmail.com', 'Trần Văn Quang', 'User'),
('User_Hiền_180', 'Hien180', 'Hien180@gmail.com', 'Phạm Thị Hiền', 'User'),
('User_Việt_181', 'Viet181', 'Viet181@gmail.com', 'Nguyễn Văn Việt', 'User'),
('User_Thủy_182', 'Thuy182', 'Thuy182@gmail.com', 'Lê Thị Thủy', 'User'),
('User_Trung_183', 'Trung183', 'Trung183@gmail.com', 'Trần Văn Trung', 'User'),
('User_Lệ_184', 'Le184', 'Le184@gmail.com', 'Phạm Thị Lệ', 'User'),
('User_Hiệp_185', 'Hiep185', 'Hiep185@gmail.com', 'Nguyễn Văn Hiệp', 'User'),
('User_Tâm_186', 'Tam186', 'Tam186@gmail.com', 'Lê Thị Tâm', 'User'),
('User_Đức_187', 'Duc187', 'Duc187@gmail.com', 'Trần Văn Đức', 'User'),
('User_Thy_188', 'Thy188', 'Thy188@gmail.com', 'Phạm Thị Thy', 'User'),
('User_Cường_189', 'Cuong189', 'Cuong189@gmail.com', 'Nguyễn Văn Cường', 'User'),
('User_Phương_190', 'Phuong190', 'Phuong190@gmail.com', 'Lê Thị Phương', 'User'),
('User_Sơn_191', 'Son191', 'Son191@gmail.com', 'Trần Văn Sơn', 'User'),
('User_Nhi_192', 'Nhi192', 'Nhi192@gmail.com', 'Phạm Thị Nhi', 'User'),
('User_Khoa_193', 'Khoa193', 'Khoa193@gmail.com', 'Nguyễn Văn Khoa', 'User'),
('User_Hồng_194', 'Hong194', 'Hong194@gmail.com', 'Lê Thị Hồng', 'User'),
('User_Thiên_195', 'Thien195', 'Thien195@gmail.com', 'Trần Văn Thiên', 'User'),
('User_Ly_196', 'Ly196', 'Ly196@gmail.com', 'Phạm Thị Ly', 'User'),
('User_Tín_197', 'Tin197', 'Tin197@gmail.com', 'Nguyễn Văn Tín', 'User'),
('User_Vân_198', 'Van198', 'Van198@gmail.com', 'Lê Thị Vân', 'User'),
('User_Đạt_199', 'Dat199', 'Dat199@gmail.com', 'Trần Văn Đạt', 'User'),
('User_Hoa_200', 'Hoa200', 'Hoa200@gmail.com', 'Phạm Thị Hoa', 'User'),
('User_Khánh_201', 'Khanh201', 'Khanh201@gmail.com', 'Nguyễn Văn Khánh', 'User'),
('User_Thảo_202', 'Thao202', 'Thao202@gmail.com', 'Lê Thị Thảo', 'User'),
('User_Hoàng_203', 'Hoang203', 'Hoang203@gmail.com', 'Trần Văn Hoàng', 'User'),
('User_Minh_204', 'Minh204', 'Minh204@gmail.com', 'Phạm Thị Minh', 'User'),
('User_Thanh_205', 'Thanh205', 'Thanh205@gmail.com', 'Nguyễn Văn Thanh', 'User'),
('User_Huyền_206', 'Huyen206', 'Huyen206@gmail.com', 'Lê Thị Huyền', 'User'),
('User_Kiên_207', 'Kien207', 'Kien207@gmail.com', 'Trần Văn Kiên', 'User'),
('User_Mỹ_208', 'My208', 'My208@gmail.com', 'Phạm Thị Mỹ', 'User'),
('User_Tài_209', 'Tai209', 'Tai209@gmail.com', 'Nguyễn Văn Tài', 'User'),
('User_Hà_210', 'Ha210', 'Ha210@gmail.com', 'Lê Thị Hà', 'User'),
('User_Vinh_211', 'Vinh211', 'Vinh211@gmail.com', 'Trần Văn Vinh', 'User'),
('User_Chiến_212', 'Chien212', 'Chien212@gmail.com', 'Phạm Văn Chiến', 'User'),
('User_Duyên_213', 'Duyen213', 'Duyen213@gmail.com', 'Nguyễn Thị Duyên', 'User'),
('User_Đăng_214', 'Dang214', 'Dang214@gmail.com', 'Lê Văn Đăng', 'User'),
('User_Bảo_215', 'Bao215', 'Bao215@gmail.com', 'Trần Thị Bảo', 'User'),
('User_Phúc_216', 'Phuc216', 'Phuc216@gmail.com', 'Phạm Văn Phúc', 'User'),
('User_Nga_217', 'Nga217', 'Nga217@gmail.com', 'Nguyễn Thị Nga', 'User'),
('User_Khang_218', 'Khang218', 'Khang218@gmail.com', 'Lê Văn Khang', 'User'),
('User_Quỳnh_219', 'Quynh219', 'Quynh219@gmail.com', 'Trần Thị Quỳnh', 'User'),
('User_Thành_220', 'Thanh220', 'Thanh220@gmail.com', 'Phạm Văn Thành', 'User'),
('User_Tuyết_221', 'Tuyet221', 'Tuyet221@gmail.com', 'Nguyễn Thị Tuyết', 'User'),
('User_Lộc_222', 'Loc222', 'Loc222@gmail.com', 'Lê Văn Lộc', 'User'),
('User_Kim_223', 'Kim223', 'Kim223@gmail.com', 'Trần Thị Kim', 'User'),
('User_Anh_224', 'Anh224', 'Anh224@gmail.com', 'Phạm Văn Anh', 'User'),
('User_Diệu_225', 'Dieu225', 'Dieu225@gmail.com', 'Nguyễn Thị Diệu', 'User'),
('User_Nhật_226', 'Nhat226', 'Nhat226@gmail.com', 'Lê Văn Nhật', 'User'),
('User_Tú_227', 'Tu227', 'Tu227@gmail.com', 'Trần Thị Tú', 'User'),
('User_Hòa_228', 'Hoa228', 'Hoa228@gmail.com', 'Phạm Văn Hòa', 'User'),
('User_Liễu_229', 'Lieu229', 'Lieu229@gmail.com', 'Nguyễn Thị Liễu', 'User'),
('User_Tiến_230', 'Tien230', 'Tien230@gmail.com', 'Lê Văn Tiến', 'User'),
('User_Hường_231', 'Huong231', 'Huong231@gmail.com', 'Trần Thị Hường', 'User'),
('User_Định_232', 'Dinh232', 'Dinh232@gmail.com', 'Phạm Văn Định', 'User'),
('User_Phượng_233', 'Phuong233', 'Phuong233@gmail.com', 'Nguyễn Thị Phượng', 'User'),
('User_Lâm_234', 'Lam234', 'Lam234@gmail.com', 'Lê Văn Lâm', 'User'),
('User_Ngân_235', 'Ngan235', 'Ngan235@gmail.com', 'Trần Thị Ngân', 'User'),
('User_Hưng_236', 'Hung236', 'Hung236@gmail.com', 'Phạm Văn Hưng', 'User'),
('User_Đào_237', 'Dao237', 'Dao237@gmail.com', 'Nguyễn Thị Đào', 'User'),
('User_Thiện_238', 'Thien238', 'Thien238@gmail.com', 'Lê Văn Thiện', 'User'),
('User_Sương_239', 'Suong239', 'Suong239@gmail.com','Trần Thị Sương', 'User'),
('User_Lương_240', 'Luong240', 'Luong240@gmail.com', 'Phạm Văn Lương', 'User'),
('User_Oanh_241', 'Oanh241', 'Oanh241@gmail.com', 'Nguyễn Thị Oanh', 'User'),
('User_Thông_242', 'Thong242', 'Thong242@gmail.com', 'Lê Văn Thông', 'User'),
('User_Vi_243', 'Vi243', 'Vi243@gmail.com', 'Trần Thị Vi', 'User'),
('User_Tâm_244', 'Tam244', 'Tam244@gmail.com', 'Phạm Văn Tâm', 'User'),
('User_Nguyệt_245', 'Nguyet245', 'Nguyet245@gmail.com', 'Nguyễn Thị Nguyệt', 'User'),
('User_Phi_246', 'Phi246', 'Phi246@gmail.com', 'Lê Văn Phi', 'User'),
('User_Hậu_247', 'Hau247', 'Hau247@gmail.com', 'Trần Thị Hậu', 'User'),
('User_Hiếu_248', 'Hieu248', 'Hieu248@gmail.com', 'Phạm Văn Hiếu', 'User'),
('User_Ái_249', 'Ai249', 'Ai249@gmail.com', 'Nguyễn Thị Ái', 'User'),
('User_Đoàn_250', 'Doan250', 'Doan250@gmail.com', 'Lê Văn Đoàn', 'User'),
('User_Thoa_251', 'Thoa251', 'Thoa251@gmail.com', 'Trần Thị Thoa', 'User'),
('User_Tịnh_252', 'Tinh252', 'Tinh252@gmail.com', 'Phạm Văn Tịnh', 'User'),
('User_Kiều_253', 'Kieu253', 'Kieu253@gmail.com', 'Nguyễn Thị Kiều', 'User'),
('User_Khải_254', 'Khai254', 'Khai254@gmail.com', 'Lê Văn Khải', 'User'),
('User_Vỹ_255', 'Vy255', 'Vy255@gmail.com', 'Trần Thị Vỹ', 'User'),
('User_Quý_256', 'Quy256', 'Quy256@gmail.com', 'Phạm Văn Quý', 'User'),
('User_Thắm_257', 'Tham257', 'Tham257@gmail.com', 'Nguyễn Thị Thắm', 'User');
select* from Users;
-- BẢNG 2: ChildProfiles - Quản lý hồ sơ trẻ em
Drop table ChildProfiles;
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