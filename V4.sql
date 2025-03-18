create database Child_Growth_Tracking_System;
Drop database  Child_Growth_Tracking_System;

Use Child_Growth_Tracking_System;

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
('Admin_1', 'Dat888', 'Ledat@gmail.com', 'lê Thanh Đạt ', 'Admin'),
('Admin_2', 'Kha222', 'Khatran@gmail.com', 'Trần Chí Kha ', 'Admin'),
('Admin_3', 'Vu333', 'Vudong@gmail.com', 'Đồng Huy Vũ ', 'Admin'),
('Admin_4', 'Dam444', 'Dampha,@gmail.com', 'Phạm Lã Quang Đam ', 'Admin'),
('Admin_5', 'Hung555', 'Hungnguyenn@gmail.com', 'Nguyễn Tuấn Hùng ', 'Admin'),  
-- 50 docters
('Doctor_1', 'Lan578', 'lanvo@gmail.com', 'Võ Thị Lan', 'Doctor'),
('Doctor_2', 'Hoa924', 'hoale@gmail.com', 'Lê Thị Hoa', 'Doctor'),
('Doctor_3', 'Minh367', 'minhpham@gmail.com', 'Phạm Minh', 'Doctor'),
('Doctor_4', 'Anh452', 'anhduong@gmail.com', 'Dương Tuấn Anh', 'Doctor'),
('Doctor_5', 'Trang819', 'trangphan@gmail.com', 'Phan Thị Trang', 'Doctor'),
('Doctor_6', 'Hung635', 'hungdao@gmail.com', 'Đào Văn Hùng', 'Doctor'),
('Doctor_7', 'Thao294', 'thaonguyen@gmail.com', 'Nguyễn Thị Thảo', 'Doctor'),
('Doctor_8', 'Dat781', 'dathoang@gmail.com', 'Hoàng Tiến Đạt', 'Doctor'),
('Doctor_9', 'Linh436', 'linhle@gmail.com', 'Lê Thị Linh', 'Doctor'),
('Doctor_10', 'Quan592', 'quantran@gmail.com', 'Trần Minh Quân', 'Doctor'),
('Doctor_11', 'Mai847', 'maivu@gmail.com', 'Vũ Thị Mai', 'Doctor'),
('Doctor_12', 'Tuan183', 'tuanbui@gmail.com', 'Bùi Văn Tuấn', 'Doctor'),
('Doctor_13', 'Huong759', 'huongdinh@gmail.com', 'Đinh Thị Hương', 'Doctor'),
('Doctor_14', 'Son264', 'sonngo@gmail.com', 'Ngô Văn Sơn', 'Doctor'),
('Doctor_15', 'Hien391', 'hienpham@gmail.com', 'Phạm Thị Hiền', 'Doctor'),
('Doctor_16', 'Duc648', 'ducnguyen@gmail.com', 'Nguyễn Đức', 'Doctor'),
('Doctor_17', 'Yen523', 'yentran@gmail.com', 'Trần Thị Yến', 'Doctor'),
('Doctor_18', 'Cuong976', 'cuongdo@gmail.com', 'Đỗ Mạnh Cường', 'Doctor'),
('Doctor_19', 'Nga145', 'ngado@gmail.com', 'Đỗ Thị Nga', 'Doctor'),
('Doctor_20', 'Thanh832', 'thanhle@gmail.com', 'Lê Công Thành', 'Doctor'),
('Doctor_21', 'Hanh679', 'hanhvu@gmail.com', 'Vũ Thị Hạnh', 'Doctor'),
('Doctor_22', 'Khanh214', 'khanhhoang@gmail.com', 'Hoàng Gia Khánh', 'Doctor'),
('Doctor_23', 'Hong567', 'hongdang@gmail.com', 'Đặng Thị Hồng', 'Doctor'),
('Doctor_24', 'Truong938', 'truongphan@gmail.com', 'Phan Văn Trường', 'Doctor'),
('Doctor_25', 'Chi475', 'chinguyen@gmail.com', 'Nguyễn Thị Chi', 'Doctor'),
('Doctor_26', 'Binh721', 'binhtrinh@gmail.com', 'Trịnh Văn Bình', 'Doctor'),
('Doctor_27', 'Nhung386', 'nhungpham@gmail.com', 'Phạm Thị Nhung', 'Doctor'),
('Doctor_28', 'Long549', 'longvo@gmail.com', 'Võ Thành Long', 'Doctor'),
('Doctor_29', 'Thu917', 'thuly@gmail.com', 'Lý Thị Thu', 'Doctor'),
('Doctor_30', 'Dung283', 'dungtran@gmail.com', 'Trần Văn Dũng', 'Doctor'),
('Doctor_31', 'Phuong654', 'phuongdoan@gmail.com', 'Đoàn Thị Phương', 'Doctor'),
('Doctor_32', 'Vinh429', 'vinhle@gmail.com', 'Lê Quang Vinh', 'Doctor'),
('Doctor_33', 'Van875', 'vanthai@gmail.com', 'Thái Thị Vân', 'Doctor'),
('Doctor_34', 'Hoang192', 'hoangdao@gmail.com', 'Đào Minh Hoàng', 'Doctor'),
('Doctor_35', 'Giang637', 'giangnguyen@gmail.com', 'Nguyễn Thị Giang', 'Doctor'),
('Doctor_36', 'Phong528', 'phongtran@gmail.com', 'Trần Đình Phong', 'Doctor'),
('Doctor_37', 'Diep941', 'diepvu@gmail.com', 'Vũ Thị Diệp', 'Doctor'),
('Doctor_38', 'Manh362', 'manhhoang@gmail.com', 'Hoàng Đức Mạnh', 'Doctor'),
('Doctor_39', 'Tuyet715', 'tuyetdo@gmail.com', 'Đỗ Thị Tuyết', 'Doctor'),
('Doctor_40', 'Trung483', 'trungnguyen@gmail.com', 'Nguyễn Văn Trung', 'Doctor'),
('Doctor_41', 'My926', 'myvo@gmail.com', 'Võ Thị Mỹ', 'Doctor'),
('Doctor_42', 'Quoc571', 'quoctran@gmail.com', 'Trần Quốc', 'Doctor'),
('Doctor_43', 'Nhu384', 'nhupham@gmail.com', 'Phạm Thị Như', 'Doctor'),
('Doctor_44', 'Hai749', 'hainguyen@gmail.com', 'Nguyễn Văn Hải', 'Doctor'),
('Doctor_45', 'Bich235', 'bichle@gmail.com', 'Lê Thị Bích', 'Doctor'),
('Doctor_46', 'Nam682', 'namdinh@gmail.com', 'Đinh Văn Nam', 'Doctor'),
('Doctor_47', 'Loan497', 'loantran@gmail.com', 'Trần Thị Loan', 'Doctor'),
('Doctor_48', 'Tien853', 'tienhoang@gmail.com', 'Hoàng Minh Tiến', 'Doctor'),
('Doctor_49', 'Huyen621', 'huyenpham@gmail.com', 'Phạm Thị Huyền', 'Doctor'),
('Doctor_50', 'Quang394', 'quangvu@gmail.com', 'Vũ Đình Quang', 'Doctor'),
-- 400 Users
('User_1', 'Tuan578', 'tuannguyen@gmail.com', 'Nguyễn Văn Tuấn', 'User'),
('User_2', 'Mai924', 'maile@gmail.com', 'Lê Thị Mai', 'User'),
('User_3', 'Hung367', 'hungpham@gmail.com', 'Phạm Văn Hùng', 'User'),
('User_4', 'Lan452', 'landuong@gmail.com', 'Dương Thị Lan', 'User'),
('User_5', 'Minh819', 'minhphan@gmail.com', 'Phan Văn Minh', 'User'),
('User_6', 'Hoa635', 'hoadao@gmail.com', 'Đào Thị Hoa', 'User'),
('User_7', 'Nam294', 'namnguyen@gmail.com', 'Nguyễn Văn Nam', 'User'),
('User_8', 'Linh781', 'linhhoang@gmail.com', 'Hoàng Thị Linh', 'User'),
('User_9', 'Duc436', 'ducle@gmail.com', 'Lê Văn Đức', 'User'),
('User_10', 'Nga592', 'ngatran@gmail.com', 'Trần Thị Nga', 'User'),
('User_11', 'Son847', 'sonvu@gmail.com', 'Vũ Văn Sơn', 'User'),
('User_12', 'Huong183', 'huongbui@gmail.com', 'Bùi Thị Hương', 'User'),
('User_13', 'Thanh759', 'thanhdinh@gmail.com', 'Đinh Văn Thành', 'User'),
('User_14', 'Hong264', 'hongngo@gmail.com', 'Ngô Thị Hồng', 'User'),
('User_15', 'Long391', 'longpham@gmail.com', 'Phạm Văn Long', 'User'),
('User_16', 'Van648', 'vannguyen@gmail.com', 'Nguyễn Thị Vân', 'User'),
('User_17', 'Binh523', 'binhtran@gmail.com', 'Trần Văn Bình', 'User'),
('User_18', 'Thu976', 'thudo@gmail.com', 'Đỗ Thị Thu', 'User'),
('User_19', 'Manh145', 'manhdo@gmail.com', 'Đỗ Mạnh', 'User'),
('User_20', 'Nhung832', 'nhungle@gmail.com', 'Lê Thị Nhung', 'User'),
('User_21', 'Quang679', 'quangvuu@gmail.com', 'Vũ Văn Quang', 'User'),
('User_22', 'Trang214', 'tranghoang@gmail.com', 'Hoàng Thị Trang', 'User'),
('User_23', 'Dat567', 'datdang@gmail.com', 'Đặng Văn Đạt', 'User'),
('User_24', 'Anh938', 'anhphan@gmail.com', 'Phan Thị Anh', 'User'),
('User_25', 'Dung475', 'dungnguyen@gmail.com', 'Nguyễn Văn Dũng', 'User'),
('User_26', 'Hanh721', 'hanhtrinh@gmail.com', 'Trịnh Thị Hạnh', 'User'),
('User_27', 'Vinh386', 'vinhpham@gmail.com', 'Phạm Văn Vinh', 'User'),
('User_28', 'My549', 'myvo@gmail.com', 'Võ Thị Mỹ', 'User'),
('User_29', 'Trung917', 'trungly@gmail.com', 'Lý Văn Trung', 'User'),
('User_30', 'Chi283', 'chitran@gmail.com', 'Trần Thị Chi', 'User'),
('User_31', 'Hoang654', 'hoangdoan@gmail.com', 'Đoàn Văn Hoàng', 'User'),
('User_32', 'Yen429', 'yenle@gmail.com', 'Lê Thị Yến', 'User'),
('User_33', 'Tien875', 'tienthai@gmail.com', 'Thái Văn Tiến', 'User'),
('User_34', 'Phuong192', 'phuongdao@gmail.com', 'Đào Thị Phương', 'User'),
('User_35', 'Quan637', 'quannguyen@gmail.com', 'Nguyễn Văn Quân', 'User'),
('User_36', 'Bich528', 'bichtran@gmail.com', 'Trần Thị Bích', 'User'),
('User_37', 'Hai941', 'haivu@gmail.com', 'Vũ Văn Hải', 'User'),
('User_38', 'Huyen362', 'huyenhoang@gmail.com', 'Hoàng Thị Huyền', 'User'),
('User_39', 'Cuong715', 'cuongdo@gmail.com', 'Đỗ Văn Cường', 'User'),
('User_40', 'Giang483', 'giangnguyen@gmail.com', 'Nguyễn Thị Giang', 'User'),
('User_41', 'Phong926', 'phongvo@gmail.com', 'Võ Văn Phong', 'User'),
('User_42', 'Tuyet571', 'tuyettran@gmail.com', 'Trần Thị Tuyết', 'User'),
('User_43', 'Diep384', 'dieppham@gmail.com', 'Phạm Thị Diệp', 'User'),
('User_44', 'Quoc749', 'quocnguyen@gmail.com', 'Nguyễn Quốc', 'User'),
('User_45', 'Nhu235', 'nhule@gmail.com', 'Lê Thị Như', 'User'),
('User_46', 'Loan682', 'loandinh@gmail.com', 'Đinh Thị Loan', 'User'),
('User_47', 'Khanh497', 'khanhtran@gmail.com', 'Trần Gia Khánh', 'User'),
('User_48', 'Truong853', 'truonghoang@gmail.com', 'Hoàng Văn Trường', 'User'),
('User_49', 'Thao621', 'thaopham@gmail.com', 'Phạm Thị Thảo', 'User'),
('User_50', 'Hien394', 'hienvu@gmail.com', 'Vũ Thị Hiền', 'User'),
('User_51', 'Tuan167', 'tuanpham@gmail.com', 'Phạm Văn Tuấn', 'User'),
('User_52', 'Mai283', 'maitran@gmail.com', 'Trần Thị Mai', 'User'),
('User_53', 'Hung495', 'hungvo@gmail.com', 'Võ Văn Hùng', 'User'),
('User_54', 'Lan712', 'lannguyen@gmail.com', 'Nguyễn Thị Lan', 'User'),
('User_55', 'Minh846', 'minhdo@gmail.com', 'Đỗ Văn Minh', 'User'),
('User_56', 'Hoa359', 'hoale@gmail.com', 'Lê Thị Hoa', 'User'),
('User_57', 'Nam624', 'namdinh@gmail.com', 'Đinh Văn Nam', 'User'),
('User_58', 'Linh937', 'linhphan@gmail.com', 'Phan Thị Linh', 'User'),
('User_59', 'Duc185', 'duchoang@gmail.com', 'Hoàng Văn Đức', 'User'),
('User_60', 'Nga472', 'ngadao@gmail.com', 'Đào Thị Nga', 'User'),
('User_61', 'Son813', 'sonnguyen@gmail.com', 'Nguyễn Văn Sơn', 'User'),
('User_62', 'Huong256', 'huongtran@gmail.com', 'Trần Thị Hương', 'User'),
('User_63', 'Thanh695', 'thanhvu@gmail.com', 'Vũ Văn Thành', 'User'),
('User_64', 'Hong342', 'hongtrinh@gmail.com', 'Trịnh Thị Hồng', 'User'),
('User_65', 'Long578', 'longngo@gmail.com', 'Ngô Văn Long', 'User'),
('User_66', 'Van891', 'vanpham@gmail.com', 'Phạm Thị Vân', 'User'),
('User_67', 'Binh237', 'binhle@gmail.com', 'Lê Văn Bình', 'User'),
('User_68', 'Thu625', 'thuhoang@gmail.com', 'Hoàng Thị Thu', 'User'),
('User_69', 'Manh943', 'manhtran@gmail.com', 'Trần Mạnh', 'User'),
('User_70', 'Nhung361', 'nhungdao@gmail.com', 'Đào Thị Nhung', 'User'),
('User_71', 'Quang728', 'quangduong@gmail.com', 'Dương Văn Quang', 'User'),
('User_72', 'Trang194', 'trangnguyen@gmail.com', 'Nguyễn Thị Trang', 'User'),
('User_73', 'Dat537', 'datvo@gmail.com', 'Võ Văn Đạt', 'User'),
('User_74', 'Anh862', 'anhle@gmail.com', 'Lê Thị Anh', 'User'),
('User_75', 'Dung295', 'dungpham@gmail.com', 'Phạm Văn Dũng', 'User'),
('User_76', 'Hanh614', 'hanhhoang@gmail.com', 'Hoàng Thị Hạnh', 'User'),
('User_77', 'Vinh938', 'vinhtran@gmail.com', 'Trần Văn Vinh', 'User'),
('User_78', 'My467', 'mynguyen@gmail.com', 'Nguyễn Thị Mỹ', 'User'),
('User_79', 'Trung839', 'trungdinh@gmail.com', 'Đinh Văn Trung', 'User'),
('User_80', 'Chi215', 'chivo@gmail.com', 'Võ Thị Chi', 'User'),
('User_81', 'Hoang573', 'hoangle@gmail.com', 'Lê Văn Hoàng', 'User'),
('User_82', 'Yen896', 'yendo@gmail.com', 'Đỗ Thị Yến', 'User'),
('User_83', 'Tien312', 'tiennguyen@gmail.com', 'Nguyễn Văn Tiến', 'User'),
('User_84', 'Phuong647', 'phuongtran@gmail.com', 'Trần Thị Phương', 'User'),
('User_85', 'Quan982', 'quanvuong@gmail.com', 'Vương Văn Quân', 'User'),
('User_86', 'Bich419', 'bichdao@gmail.com', 'Đào Thị Bích', 'User'),
('User_87', 'Hai756', 'haipham@gmail.com', 'Phạm Văn Hải', 'User'),
('User_88', 'Huyen293', 'huyenle@gmail.com', 'Lê Thị Huyền', 'User'),
('User_89', 'Cuong524', 'cuongnguyen@gmail.com', 'Nguyễn Văn Cường', 'User'),
('User_90', 'Giang871', 'gianghoang@gmail.com', 'Hoàng Thị Giang', 'User'),
('User_91', 'Phong245', 'phongtran@gmail.com', 'Trần Văn Phong', 'User'),
('User_92', 'Tuyet682', 'tuyetpham@gmail.com', 'Phạm Thị Tuyết', 'User'),
('User_93', 'Diep917', 'diepvu@gmail.com', 'Vũ Thị Diệp', 'User'),
('User_94', 'Quoc358', 'quocle@gmail.com', 'Lê Quốc', 'User'),
('User_95', 'Nhu674', 'nhunguyen@gmail.com', 'Nguyễn Thị Như', 'User'),
('User_96', 'Loan931', 'loantrinh@gmail.com', 'Trịnh Thị Loan', 'User'),
('User_97', 'Khanh264', 'khanhdo@gmail.com', 'Đỗ Gia Khánh', 'User'),
('User_98', 'Truong587', 'truongvu@gmail.com', 'Vũ Văn Trường', 'User'),
('User_99', 'Thao942', 'thaole@gmail.com', 'Lê Thị Thảo', 'User'),
('User_100', 'Hien385', 'hiennguyen@gmail.com', 'Nguyễn Thị Hiền', 'User'),
('User_101', 'An735', 'anpham@gmail.com', 'Phạm Văn An', 'User'),
('User_102', 'Lien216', 'lientran@gmail.com', 'Trần Thị Liên', 'User'),
('User_103', 'Danh593', 'danhvo@gmail.com', 'Võ Văn Danh', 'User'),
('User_104', 'Hau847', 'haudang@gmail.com', 'Đặng Thị Hậu', 'User'),
('User_105', 'Tri329', 'tringuyen@gmail.com', 'Nguyễn Trí', 'User'),
('User_106', 'Kim625', 'kimle@gmail.com', 'Lê Thị Kim', 'User'),
('User_107', 'Tho971', 'thodinh@gmail.com', 'Đinh Văn Thọ', 'User'),
('User_108', 'Chau347', 'chauphan@gmail.com', 'Phan Thị Châu', 'User'),
('User_109', 'Duy682', 'duydo@gmail.com', 'Đỗ Văn Duy', 'User'),
('User_110', 'Xuan926', 'xuantran@gmail.com', 'Trần Thị Xuân', 'User'),
('User_111', 'Thinh514', 'thinhpham@gmail.com', 'Phạm Văn Thịnh', 'User'),
('User_112', 'Ly863', 'lyle@gmail.com', 'Lê Thị Ly', 'User'),
('User_113', 'Nghia259', 'nghianguyen@gmail.com', 'Nguyễn Văn Nghĩa', 'User'),
('User_114', 'Diem794', 'diemvu@gmail.com', 'Vũ Thị Diễm', 'User'),
('User_115', 'Khoa381', 'khoatran@gmail.com', 'Trần Khoa', 'User'),
('User_116', 'Suong652', 'suongdinh@gmail.com', 'Đinh Thị Sương', 'User'),
('User_117', 'Cong973', 'congpham@gmail.com', 'Phạm Văn Công', 'User'),
('User_118', 'Ha417', 'hanguyen@gmail.com', 'Nguyễn Thị Hà', 'User'),
('User_119', 'Lam769', 'lamvo@gmail.com', 'Võ Văn Lâm', 'User'),
('User_120', 'Hien328', 'hiendang@gmail.com', 'Đặng Thị Hiên', 'User'),
('User_121', 'Phu574', 'phule@gmail.com', 'Lê Văn Phú', 'User'),
('User_122', 'Cam896', 'camtran@gmail.com', 'Trần Thị Cẩm', 'User'),
('User_123', 'Dang251', 'dangdo@gmail.com', 'Đỗ Văn Đăng', 'User'),
('User_124', 'Trinh735', 'trinhphan@gmail.com', 'Phan Thị Trinh', 'User'),
('User_125', 'Duan489', 'duannguyen@gmail.com', 'Nguyễn Duẩn', 'User'),
('User_126', 'Le826', 'levu@gmail.com', 'Vũ Thị Lệ', 'User'),
('User_127', 'Luan352', 'luantran@gmail.com', 'Trần Văn Luân', 'User'),
('User_128', 'Hang719', 'hangdinh@gmail.com', 'Đinh Thị Hằng', 'User'),
('User_129', 'Sinh943', 'sinhle@gmail.com', 'Lê Văn Sinh', 'User'),
('User_130', 'Hue267', 'huenguyen@gmail.com', 'Nguyễn Thị Huệ', 'User'),
('User_131', 'Quy582', 'quypham@gmail.com', 'Phạm Văn Quý', 'User'),
('User_132', 'Dong917', 'dongdang@gmail.com', 'Đặng Văn Đông', 'User'),
('User_133', 'Bach354', 'bachtran@gmail.com', 'Trần Thị Bạch', 'User'),
('User_134', 'Vu673', 'vudo@gmail.com', 'Đỗ Văn Vũ', 'User'),
('User_135', 'Oanh926', 'oanhle@gmail.com', 'Lê Thị Oanh', 'User'),
('User_136', 'Loc419', 'loctrinh@gmail.com', 'Trịnh Văn Lộc', 'User'),
('User_137', 'Tam758', 'tampham@gmail.com', 'Phạm Thị Tâm', 'User'),
('User_138', 'Can291', 'cannguyen@gmail.com', 'Nguyễn Văn Cần', 'User'),
('User_139', 'Tien547', 'tienvo@gmail.com', 'Võ Thị Tiên', 'User'),
('User_140', 'Trong862', 'trongtran@gmail.com', 'Trần Văn Trọng', 'User'),
('User_141', 'Uyen243', 'uyenle@gmail.com', 'Lê Thị Uyên', 'User'),
('User_142', 'Ung679', 'ungdao@gmail.com', 'Đào Văn Ưng', 'User'),
('User_143', 'Quyen915', 'quyenpham@gmail.com', 'Phạm Thị Quyên', 'User'),
('User_144', 'Hiep372', 'hiepnguyen@gmail.com', 'Nguyễn Văn Hiệp', 'User'),
('User_145', 'Chung697', 'chungdang@gmail.com', 'Đặng Thị Chung', 'User'),
('User_146', 'Thai436', 'thaivo@gmail.com', 'Võ Văn Thái', 'User'),
('User_147', 'Quynh872', 'quynhtran@gmail.com', 'Trần Thị Quỳnh', 'User'),
('User_148', 'Khoi293', 'khoipham@gmail.com', 'Phạm Văn Khôi', 'User'),
('User_149', 'Thuy526', 'thuynguyen@gmail.com', 'Nguyễn Thị Thúy', 'User'),
('User_150', 'Ba819', 'bale@gmail.com', 'Lê Văn Ba', 'User'),
('User_151', 'Vy265', 'vyhoang@gmail.com', 'Hoàng Thị Vy', 'User'),
('User_152', 'Tu593', 'tutran@gmail.com', 'Trần Văn Tú', 'User'),
('User_153', 'Ngoc847', 'ngocpham@gmail.com', 'Phạm Thị Ngọc', 'User'),
('User_154', 'Minh329', 'minhnguyen@gmail.com', 'Nguyễn Minh', 'User'),
('User_155', 'Luu674', 'luudo@gmail.com', 'Đỗ Văn Lưu', 'User'),
('User_156', 'Ngan913', 'nganle@gmail.com', 'Lê Thị Ngân', 'User'),
('User_157', 'Phat345', 'phatle@gmail.com', 'Lê Phát', 'User'),
('User_158', 'Hoa782', 'hoanguyen@gmail.com', 'Nguyễn Thị Hoa', 'User'),
('User_159', 'Dan429', 'danpham@gmail.com', 'Phạm Đan', 'User'),
('User_160', 'Thuy687', 'thuytran@gmail.com', 'Trần Thúy', 'User'),
('User_161', 'Bao254', 'baovo@gmail.com', 'Võ Bảo', 'User'),
('User_162', 'My673', 'mydo@gmail.com', 'Đỗ Mỹ', 'User'),
('User_163', 'Thang915', 'thangle@gmail.com', 'Lê Thắng', 'User'),
('User_164', 'Hieu462', 'hieunguyen@gmail.com', 'Nguyễn Hiếu', 'User'),
('User_165', 'Xuan794', 'xuanpham@gmail.com', 'Phạm Xuân', 'User'),
('User_166', 'Nhi231', 'nhitran@gmail.com', 'Trần Thị Nhi', 'User'),
('User_167', 'Sang587', 'sangvo@gmail.com', 'Võ Văn Sang', 'User'),
('User_168', 'Trinh815', 'trinhle@gmail.com', 'Lê Thị Trinh', 'User'),
('User_169', 'Si369', 'singuyen@gmail.com', 'Nguyễn Văn Sĩ', 'User'),
('User_170', 'Thanh726', 'thanhpham@gmail.com', 'Phạm Thị Thanh', 'User'),
('User_171', 'Loc194', 'loctran@gmail.com', 'Trần Văn Lộc', 'User'),
('User_172', 'Huyen473', 'huyendo@gmail.com', 'Đỗ Thị Huyền', 'User'),
('User_173', 'Tham816', 'thamle@gmail.com', 'Lê Văn Thắm', 'User'),
('User_174', 'Hanh254', 'hanhnguyen@gmail.com', 'Nguyễn Thị Hạnh', 'User'),
('User_175', 'Duong635', 'duongpham@gmail.com', 'Phạm Văn Dương', 'User'),
('User_176', 'Tuyen912', 'tuyenvo@gmail.com', 'Võ Thị Tuyền', 'User'),
('User_177', 'Quyen385', 'quyentran@gmail.com', 'Trần Văn Quyến', 'User'),
('User_178', 'Oanh762', 'oanhdo@gmail.com', 'Đỗ Thị Oanh', 'User'),
('User_179', 'Dong253', 'dongle@gmail.com', 'Lê Văn Đông', 'User'),
('User_180', 'Hanh574', 'hanhnguyen@gmail.com', 'Nguyễn Thị Hạnh', 'User'),
('User_181', 'Vinh816', 'vinhpham@gmail.com', 'Phạm Văn Vinh', 'User'),
('User_182', 'Thu273', 'thutran@gmail.com', 'Trần Thị Thu', 'User'),
('User_183', 'Bang592', 'bangvo@gmail.com', 'Võ Văn Bằng', 'User'),
('User_184', 'Lien847', 'liendo@gmail.com', 'Đỗ Thị Liên', 'User'),
('User_185', 'Ky329', 'kyle@gmail.com', 'Lê Văn Kỳ', 'User'),
('User_186', 'Tuong651', 'tuongnguyen@gmail.com', 'Nguyễn Tường', 'User'),
('User_187', 'Cam928', 'campham@gmail.com', 'Phạm Thị Cẩm', 'User'),
('User_188', 'Duong315', 'duongtran@gmail.com', 'Trần Dương', 'User'),
('User_189', 'Nguyet743', 'nguyetvo@gmail.com', 'Võ Thị Nguyệt', 'User'),
('User_190', 'Phung267', 'phungdo@gmail.com', 'Đỗ Phụng', 'User'),
('User_191', 'Nhan584', 'nhannguyen@gmail.com', 'Nguyễn Nhàn', 'User'),
('User_192', 'Kien926', 'kienle@gmail.com', 'Lê Kiên', 'User'),
('User_193', 'Phi372', 'phipham@gmail.com', 'Phạm Phi', 'User'),
('User_194', 'Huong716', 'huongtran@gmail.com', 'Trần Thị Hương', 'User'),
('User_195', 'Tra298', 'travo@gmail.com', 'Võ Trà', 'User'),
('User_196', 'Tram641', 'tramdo@gmail.com', 'Đỗ Thị Trâm', 'User'),
('User_197', 'Hoai923', 'hoaile@gmail.com', 'Lê Hoài', 'User'),
('User_198', 'Bao517', 'baonguyen@gmail.com', 'Nguyễn Bảo', 'User'),
('User_199', 'Viet862', 'vietpham@gmail.com', 'Phạm Việt', 'User'),
('User_200', 'Yen349', 'yentran@gmail.com', 'Trần Thị Yến', 'User'),
('User_201', 'Phuc725', 'phucvo@gmail.com', 'Võ Phúc', 'User'),
('User_202', 'Ly196', 'lydo@gmail.com', 'Đỗ Thị Ly', 'User'),
('User_203', 'Hau543', 'haule@gmail.com', 'Lê Hậu', 'User'),
('User_204', 'My874', 'mynguyen@gmail.com', 'Nguyễn Thị Mỹ', 'User'),
('User_205', 'Tu316', 'tupham@gmail.com', 'Phạm Tú', 'User'),
('User_206', 'Chau659', 'chautran@gmail.com', 'Trần Thị Châu', 'User'),
('User_207', 'Hung742', 'hungnguyen@gmail.com', 'Nguyễn Văn Hùng', 'User'),
('User_208', 'Lan195', 'lanle@gmail.com', 'Lê Thị Lan', 'User'),
('User_209', 'Minh483', 'minhpham@gmail.com', 'Phạm Văn Minh', 'User'),
('User_210', 'Hoa927', 'hoado@gmail.com', 'Đỗ Thị Hoa', 'User'),
('User_211', 'Nam364', 'namtran@gmail.com', 'Trần Văn Nam', 'User'),
('User_212', 'Linh589', 'linhvu@gmail.com', 'Vũ Thị Linh', 'User'),
('User_213', 'Duc813', 'ducnguyen@gmail.com', 'Nguyễn Văn Đức', 'User'),
('User_214', 'Nga276', 'ngapham@gmail.com', 'Phạm Thị Nga', 'User'),
('User_215', 'Son641', 'sonle@gmail.com', 'Lê Văn Sơn', 'User'),
('User_216', 'Huong958', 'huongtran@gmail.com', 'Trần Thị Hương', 'User'),
('User_217', 'Thanh392', 'thanhvo@gmail.com', 'Võ Văn Thành', 'User'),
('User_218', 'Hong174', 'hongdinh@gmail.com', 'Đinh Thị Hồng', 'User'),
('User_219', 'Long529', 'longnguyen@gmail.com', 'Nguyễn Văn Long', 'User'),
('User_220', 'Van863', 'vanpham@gmail.com', 'Phạm Thị Vân', 'User'),
('User_221', 'Binh347', 'binhtran@gmail.com', 'Trần Văn Bình', 'User'),
('User_222', 'Thu692', 'thule@gmail.com', 'Lê Thị Thu', 'User'),
('User_223', 'Manh915', 'manhdo@gmail.com', 'Đỗ Mạnh', 'User'),
('User_224', 'Nhung268', 'nhungnguyen@gmail.com', 'Nguyễn Thị Nhung', 'User'),
('User_225', 'Quang573', 'Quangvu@gmail.com', 'Vũ Văn Quang', 'User'),
('User_226', 'Trang841', 'trangtran@gmail.com', 'Trần Thị Trang', 'User'),
('User_227', 'Dat396', 'datpham@gmail.com', 'Phạm Văn Đạt', 'User'),
('User_228', 'Anh752', 'anhle@gmail.com', 'Lê Thị Anh', 'User'),
('User_229', 'Dung194', 'dungnguyen@gmail.com', 'Nguyễn Văn Dũng', 'User'),
('User_230', 'Hanh638', 'hanhvo@gmail.com', 'Võ Thị Hạnh', 'User'),
('User_231', 'Vinh927', 'vinhdinh@gmail.com', 'Đinh Văn Vinh', 'User'),
('User_232', 'My483', 'mytran@gmail.com', 'Trần Thị Mỹ', 'User'),
('User_233', 'Trung715', 'trungnguyen@gmail.com', 'Nguyễn Văn Trung', 'User'),
('User_234', 'Chi269', 'chile@gmail.com', 'Lê Thị Chi', 'User'),
('User_235', 'Hoang584', 'hoangpham@gmail.com', 'Phạm Văn Hoàng', 'User'),
('User_236', 'Yen831', 'yentran@gmail.com', 'Trần Thị Yến', 'User'),
('User_237', 'Tien376', 'tienvu@gmail.com', 'Vũ Văn Tiến', 'User'),
('User_238', 'Phuong692', 'phuongnguyen@gmail.com', 'Nguyễn Thị Phương', 'User'),
('User_239', 'Quan145', 'quando@gmail.com', 'Đỗ Văn Quân', 'User'),
('User_240', 'Bich528', 'bichtran@gmail.com', 'Trần Thị Bích', 'User'),
('User_241', 'Hai973', 'haipham@gmail.com', 'Phạm Văn Hải', 'User'),
('User_242', 'Huyen316', 'huyenle@gmail.com', 'Lê Thị Huyền', 'User'),
('User_243', 'Cuong684', 'cuongnguyen@gmail.com', 'Nguyễn Văn Cường', 'User'),
('User_244', 'Giang927', 'giangvo@gmail.com', 'Võ Thị Giang', 'User'),
('User_245', 'Phong452', 'phongtran@gmail.com', 'Trần Văn Phong', 'User'),
('User_246', 'Tuyet789', 'tuyetpham@gmail.com', 'Phạm Thị Tuyết', 'User'),
('User_247', 'Diep263', 'diepnguyen@gmail.com', 'Nguyễn Thị Diệp', 'User'),
('User_248', 'Quoc591', 'quocle@gmail.com', 'Lê Quốc', 'User'),
('User_249', 'Nhu847', 'nhutran@gmail.com', 'Trần Thị Như', 'User'),
('User_250', 'Loan392', 'loanvo@gmail.com', 'Võ Thị Loan', 'User'),
('User_251', 'Khanh675', 'khanhnguyen@gmail.com', 'Nguyễn Gia Khánh', 'User'),
('User_252', 'Truong128', 'truongpham@gmail.com', 'Phạm Văn Trường', 'User'),
('User_253', 'Thao493', 'thaole@gmail.com', 'Lê Thị Thảo', 'User'),
('User_254', 'Hien756', 'hienvu@gmail.com', 'Vũ Thị Hiền', 'User'),
('User_255', 'An294', 'antran@gmail.com', 'Trần Văn An', 'User'),
('User_256', 'Lien583', 'liennguyen@gmail.com', 'Nguyễn Thị Liên', 'User'),
('User_257', 'Danh847', 'danhpham@gmail.com', 'Phạm Văn Danh', 'User'),
('User_258', 'Hau392', 'haule@gmail.com', 'Lê Thị Hậu', 'User'),
('User_259', 'Tri675', 'tringuyen@gmail.com', 'Nguyễn Trí', 'User'),
('User_260', 'Kim128', 'kimtran@gmail.com', 'Trần Thị Kim', 'User'),
('User_261', 'Tho493', 'thopham@gmail.com', 'Phạm Văn Thọ', 'User'),
('User_262', 'Chau756', 'chaudo@gmail.com', 'Đỗ Thị Châu', 'User'),
('User_263', 'Duy294', 'duyvu@gmail.com', 'Vũ Văn Duy', 'User'),
('User_264', 'Xuan583', 'xuannguyen@gmail.com', 'Nguyễn Thị Xuân', 'User'),
('User_265', 'Thinh847', 'thinhle@gmail.com', 'Lê Văn Thịnh', 'User'),
('User_266', 'Ly392', 'lytran@gmail.com', 'Trần Thị Ly', 'User'),
('User_267', 'Nghia675', 'nghiapham@gmail.com', 'Phạm Văn Nghĩa', 'User'),
('User_268', 'Diem128', 'diemvo@gmail.com', 'Võ Thị Diễm', 'User'),
('User_269', 'Khoa493', 'khoanguyen@gmail.com', 'Nguyễn Khoa', 'User'),
('User_270', 'Suong756', 'suongtran@gmail.com', 'Trần Thị Sương', 'User'),
('User_271', 'Cong294', 'congpham@gmail.com', 'Phạm Văn Công', 'User'),
('User_272', 'Ha583', 'hatran@gmail.com', 'Trần Thị Hà', 'User'),
('User_273', 'Lam847', 'lamle@gmail.com', 'Lê Văn Lâm', 'User'),
('User_274', 'Hien392', 'hienvo@gmail.com', 'Võ Thị Hiên', 'User'),
('User_275', 'Phu675', 'phunguyen@gmail.com', 'Nguyễn Văn Phú', 'User'),
('User_276', 'Cam128', 'campham@gmail.com', 'Phạm Thị Cẩm', 'User'),
('User_277', 'Dang493', 'dangle@gmail.com', 'Lê Văn Đăng', 'User'),
('User_278', 'Trinh756', 'trinhnguyen@gmail.com', 'Nguyễn Thị Trinh', 'User'),
('User_279', 'Duan294', 'duantran@gmail.com', 'Trần Duẩn', 'User'),
('User_280', 'Le583', 'levu@gmail.com', 'Vũ Thị Lệ', 'User'),
('User_281', 'Luan847', 'luanpham@gmail.com', 'Phạm Văn Luân', 'User'),
('User_282', 'Hang392', 'hangnguyen@gmail.com', 'Nguyễn Thị Hằng', 'User'),
('User_283', 'Sinh675', 'sinhle@gmail.com', 'Lê Văn Sinh', 'User'),
('User_284', 'Hue128', 'huetran@gmail.com', 'Trần Thị Huệ', 'User'),
('User_285', 'Quy493', 'quypham@gmail.com', 'Phạm Văn Quý', 'User'),
('User_286', 'Dong756', 'dongnguyen@gmail.com', 'Nguyễn Văn Đông', 'User'),
('User_287', 'Bach294', 'bachtran@gmail.com', 'Trần Thị Bạch', 'User'),
('User_288', 'Vu583', 'vule@gmail.com', 'Lê Văn Vũ', 'User'),
('User_289', 'Oanh847', 'oanhpham@gmail.com', 'Phạm Thị Oanh', 'User'),
('User_290', 'Loc392', 'locnguyen@gmail.com', 'Nguyễn Văn Lộc', 'User'),
('User_291', 'Tam675', 'tamtran@gmail.com', 'Trần Thị Tâm', 'User'),
('User_292', 'Can128', 'canle@gmail.com', 'Lê Văn Cần', 'User'),
('User_293', 'Tien493', 'tienvu@gmail.com', 'Vũ Thị Tiên', 'User'),
('User_294', 'Trong756', 'trongpham@gmail.com', 'Phạm Văn Trọng', 'User'),
('User_295', 'Uyen294', 'uyentran@gmail.com', 'Trần Thị Uyên', 'User'),
('User_296', 'Ung583', 'ungnguyen@gmail.com', 'Nguyễn Văn Ưng', 'User'),
('User_297', 'Quyen847', 'quyenle@gmail.com', 'Lê Thị Quyên', 'User'),
('User_298', 'Hiep392', 'hieppham@gmail.com', 'Phạm Văn Hiệp', 'User'),
('User_299', 'Chung675', 'chungtran@gmail.com', 'Trần Thị Chung', 'User'),
('User_300', 'Thai128', 'thainguyen@gmail.com', 'Nguyễn Văn Thái', 'User'),
('User_301', 'Quynh493', 'quynhtran@gmail.com', 'Trần Thị Quỳnh', 'User'),
('User_302', 'Khoi756', 'khoile@gmail.com', 'Lê Văn Khôi', 'User'),
('User_303', 'Thuy294', 'thuypham@gmail.com', 'Phạm Thị Thúy', 'User'),
('User_304', 'Ba583', 'banguyen@gmail.com', 'Nguyễn Văn Ba', 'User'),
('User_305', 'Vy847', 'vyle@gmail.com', 'Lê Thị Vy', 'User'),
('User_306', 'Tu392', 'tutran@gmail.com', 'Trần Văn Tú', 'User'),
('User_307', 'Ngoc675', 'ngocpham@gmail.com', 'Phạm Thị Ngọc', 'User'),
('User_308', 'Minh128', 'minhnguyen@gmail.com', 'Nguyễn Minh', 'User'),
('User_309', 'Luu493', 'luutran@gmail.com', 'Trần Văn Lưu', 'User'),
('User_310', 'Ngan756', 'nganle@gmail.com', 'Lê Thị Ngân', 'User'),
('User_311', 'Phat294', 'phatnguyen@gmail.com', 'Nguyễn Phát', 'User'),
('User_312', 'Hoa583', 'hoatran@gmail.com', 'Trần Thị Hoa', 'User'),
('User_313', 'Dan847', 'danpham@gmail.com', 'Phạm Đan', 'User'),
('User_314', 'Thuy392', 'thuynguyen@gmail.com', 'Nguyễn Thúy', 'User'),
('User_315', 'Bao675', 'baole@gmail.com', 'Lê Bảo', 'User'),
('User_316', 'My128', 'mytran@gmail.com', 'Trần Mỹ', 'User'),
('User_317', 'Thang493', 'thangpham@gmail.com', 'Phạm Thắng', 'User'),
('User_318', 'Hieu756', 'hieunguyen@gmail.com', 'Nguyễn Hiếu', 'User'),
('User_319', 'Xuan294', 'xuanle@gmail.com', 'Lê Xuân', 'User'),
('User_320', 'Nhi583', 'nhitran@gmail.com', 'Trần Thị Nhi', 'User'),
('User_321', 'Sang847', 'sangpham@gmail.com', 'Phạm Văn Sang', 'User'),
('User_322', 'Trinh392', 'trinhnguyen@gmail.com', 'Nguyễn Thị Trinh', 'User'),
('User_323', 'Si675', 'singuyen@gmail.com', 'Nguyễn Văn Sĩ', 'User'),
('User_324', 'Thanh128', 'thanhtran@gmail.com', 'Trần Thị Thanh', 'User'),
('User_325', 'Loc493', 'loctrinh@gmail.com', 'Trịnh Văn Lộc', 'User'),
('User_326', 'Huyen756', 'huyenle@gmail.com', 'Lê Thị Huyền', 'User'),
('User_327', 'Tham294', 'thamnguyen@gmail.com', 'Nguyễn Văn Thắm', 'User'),
('User_328', 'Hanh583', 'hanhpham@gmail.com', 'Phạm Thị Hạnh', 'User'),
('User_329', 'Duong847', 'duongtran@gmail.com', 'Trần Văn Dương', 'User'),
('User_330', 'Tuyen392', 'tuyenle@gmail.com', 'Lê Thị Tuyền', 'User'),
('User_331', 'Quyen675', 'quyentran@gmail.com', 'Trần Văn Quyến', 'User'),
('User_332', 'Oanh128', 'oanhnguyen@gmail.com', 'Nguyễn Thị Oanh', 'User'),
('User_333', 'Dong493', 'dongpham@gmail.com', 'Phạm Văn Đông', 'User'),
('User_334', 'Hanh756', 'hanhle@gmail.com', 'Lê Thị Hạnh', 'User'),
('User_335', 'Vinh294', 'vinhnguyen@gmail.com', 'Nguyễn Văn Vinh', 'User'),
('User_336', 'Thu583', 'thutran@gmail.com', 'Trần Thị Thu', 'User'),
('User_337', 'Bang847', 'bangpham@gmail.com', 'Phạm Văn Bằng', 'User'),
('User_338', 'Lien392', 'lienle@gmail.com', 'Lê Thị Liên', 'User'),
('User_339', 'Ky675', 'kynguyen@gmail.com', 'Nguyễn Văn Kỳ', 'User'),
('User_340', 'Tuong128', 'tuongtran@gmail.com', 'Trần Tường', 'User'),
('User_341', 'Cam493', 'camle@gmail.com', 'Lê Thị Cẩm', 'User'),
('User_342', 'Duong756', 'duongpham@gmail.com', 'Phạm Dương', 'User'),
('User_343', 'Nguyet294', 'nguyetnguyen@gmail.com', 'Nguyễn Thị Nguyệt', 'User'),
('User_344', 'Phung583', 'phungtran@gmail.com', 'Trần Phụng', 'User'),
('User_345', 'Nhan847', 'nhanle@gmail.com', 'Lê Nhàn', 'User'),
('User_346', 'Kien392', 'kiennguyen@gmail.com', 'Nguyễn Kiên', 'User'),
('User_347', 'Phi675', 'phipham@gmail.com', 'Phạm Phi', 'User'),
('User_348', 'Huong128', 'huongle@gmail.com', 'Lê Thị Hương', 'User'),
('User_349', 'Tra493', 'tranguyen@gmail.com', 'Nguyễn Trà', 'User'),
('User_350', 'Tram756', 'tramtran@gmail.com', 'Trần Thị Trâm', 'User'),
('User_351', 'Hoai294', 'hoaipham@gmail.com', 'Phạm Hoài', 'User'),
('User_352', 'Bao583', 'baonguyen@gmail.com', 'Nguyễn Bảo', 'User'),
('User_353', 'Viet847', 'vietle@gmail.com', 'Lê Việt', 'User'),
('User_354', 'Yen392', 'yennguyen@gmail.com', 'Nguyễn Thị Yến', 'User'),
('User_355', 'Phuc675', 'phuctran@gmail.com', 'Trần Phúc', 'User'),
('User_356', 'Ly128', 'lyle@gmail.com', 'Lê Thị Ly', 'User'),
('User_357', 'Hau493', 'haunguyen@gmail.com', 'Nguyễn Hậu', 'User'),
('User_358', 'My756', 'mypham@gmail.com', 'Phạm Thị Mỹ', 'User'),
('User_359', 'Tu294', 'tutran@gmail.com', 'Trần Tú', 'User'),
('User_360', 'Chau583', 'chaule@gmail.com', 'Lê Thị Châu', 'User'),
('User_361', 'Hung847', 'hungnguyen@gmail.com', 'Nguyễn Văn Hùng', 'User'),
('User_362', 'Lan392', 'lantran@gmail.com', 'Trần Thị Lan', 'User'),
('User_363', 'Minh675', 'minhpham@gmail.com', 'Phạm Văn Minh', 'User'),
('User_364', 'Hoa128', 'hoanguyen@gmail.com', 'Nguyễn Thị Hoa', 'User'),
('User_365', 'Nam493', 'namle@gmail.com', 'Lê Văn Nam', 'User'),
('User_366', 'Linh756', 'linhtran@gmail.com', 'Trần Thị Linh', 'User'),
('User_367', 'Duc294', 'ducnguyen@gmail.com', 'Nguyễn Văn Đức', 'User'),
('User_368', 'Nga583', 'ngapham@gmail.com', 'Phạm Thị Nga', 'User'),
('User_369', 'Son847', 'sonle@gmail.com', 'Lê Văn Sơn', 'User'),
('User_370', 'Huong392', 'huongnguyen@gmail.com', 'Nguyễn Thị Hương', 'User'),
('User_371', 'Thanh675', 'thanhvo@gmail.com', 'Võ Văn Thành', 'User'),
('User_372', 'Hong128', 'hongtran@gmail.com', 'Trần Thị Hồng', 'User'),
('User_373', 'Long493', 'longpham@gmail.com', 'Phạm Văn Long', 'User'),
('User_374', 'Van756', 'vannguyen@gmail.com', 'Nguyễn Thị Vân', 'User'),
('User_375', 'Binh294', 'binhtran@gmail.com', 'Trần Văn Bình', 'User'),
('User_376', 'Thu583', 'thule@gmail.com', 'Lê Thị Thu', 'User'),
('User_377','Manh847', 'manhnguyen@gmail.com', 'Nguyễn Mạnh', 'User'),
('User_378', 'Nhung392', 'nhungtran@gmail.com', 'Trần Thị Nhung', 'User'),
('User_379', 'Quang675', 'quangpham@gmail.com', 'Phạm Văn Quang', 'User'),
('User_380', 'Trang128', 'trangle@gmail.com', 'Lê Thị Trang', 'User'),
('User_381', 'Dat493', 'datnguyen@gmail.com', 'Nguyễn Văn Đạt', 'User'),
('User_382', 'Anh756', 'anhtran@gmail.com', 'Trần Thị Anh', 'User'),
('User_383', 'Dung294', 'dungle@gmail.com', 'Lê Văn Dũng', 'User'),
('User_384', 'Hanh583', 'hanhnguyen@gmail.com', 'Nguyễn Thị Hạnh', 'User'),
('User_385', 'Vinh847', 'vinhpham@gmail.com', 'Phạm Văn Vinh', 'User'),
('User_386', 'My392', 'mytran@gmail.com', 'Trần Thị Mỹ', 'User'),
('User_387', 'Trung675', 'trungnguyen@gmail.com', 'Nguyễn Văn Trung', 'User'),
('User_388', 'Chi128', 'chile@gmail.com', 'Lê Thị Chi', 'User'),
('User_389', 'Hoang493', 'hoangtran@gmail.com', 'Trần Văn Hoàng', 'User'),
('User_390', 'Yen756', 'yenpham@gmail.com', 'Phạm Thị Yến', 'User'),
('User_391', 'Tien294', 'tiennguyen@gmail.com', 'Nguyễn Văn Tiến', 'User'),
('User_392', 'Phuong583', 'phuongtran@gmail.com', 'Trần Thị Phương', 'User'),
('User_393', 'Quan847', 'quanle@gmail.com', 'Lê Văn Quân', 'User'),
('User_394', 'Bich392', 'bichnguyen@gmail.com', 'Nguyễn Thị Bích', 'User'),
('User_395', 'Hai675', 'haipham@gmail.com', 'Phạm Văn Hải', 'User'),
('User_396', 'Huyen128', 'huyentran@gmail.com', 'Trần Thị Huyền', 'User'),
('User_397', 'Cuong493', 'cuongnguyen@gmail.com', 'Nguyễn Văn Cường', 'User'),
('User_398', 'Giang756', 'giangpham@gmail.com', 'Phạm Thị Giang', 'User'),
('User_399', 'Phong294', 'phongtran@gmail.com', 'Trần Văn Phong', 'User'),
('User_400', 'Tuyet583', 'tuyetnguyen@gmail.com', 'Nguyễn Thị Tuyết', 'User');


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

-- BẢNG 4: Alerts - Quản lý cảnh báo sức khỏe của trẻ
CREATE TABLE Alerts (
  AlertID INT PRIMARY KEY AUTO_INCREMENT, -- ID cảnh báo, tự động tăng
  ChildID INT NOT NULL,                   -- Hồ sơ trẻ nhận cảnh báo
  AlertType VARCHAR(50) NOT NULL,         -- Loại cảnh báo (Sốt, Dị ứng, ...)
  Description TEXT NOT NULL,              -- Mô tả chi tiết
  AlertDate DATETIME DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo cảnh báo
  FOREIGN KEY (ChildID) REFERENCES ChildProfiles(ChildID) -- Liên kết với ChildProfiles
);

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