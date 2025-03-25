create database Child_Growth_Tracking_System;

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
('Admin_1', 'Dat888', 'Ledat@gmail.com', 'Lê Thanh Đạt ', 'Admin'),
('Admin_2', 'Kha222', 'Khatran@gmail.com', 'Trần Chí Kha ', 'Admin'),
('Admin_3', 'Vu333', 'Vudong@gmail.com', 'Đồng Huy Vũ ', 'Admin'),
('Admin_4', 'Dam444', 'Dampha@gmail.com', 'Phạm Lã Quang Đam ', 'Admin'),
('Admin_5', 'Hung555', 'Hungnguyen@gmail.com', 'Nguyễn Tuấn Hùng ', 'Admin'),  
-- 50 docters
('vothilan578', 'Lan578', 'lanvo.doctor@gmail.com', 'Võ Thị Lan', 'Doctor'),
('lethihoa924', 'Hoa924', 'hoale.doctor@gmail.com', 'Lê Thị Hoa', 'Doctor'),
('phamminh367', 'Minh367', 'minhpham.doctor@gmail.com', 'Phạm Minh', 'Doctor'),
('duongtuananh452', 'Anh452', 'anhduong.doctor@gmail.com', 'Dương Tuấn Anh', 'Doctor'),
('phanthitrang819', 'Trang819', 'trangphan.doctor@gmail.com', 'Phan Thị Trang', 'Doctor'),
('daovanhung635', 'Hung635', 'hungdao.doctor@gmail.com', 'Đào Văn Hùng', 'Doctor'),
('nguyenthithao294', 'Thao294', 'thaonguyen.doctor@gmail.com', 'Nguyễn Thị Thảo', 'Doctor'),
('hoangtiendat781', 'Dat781', 'dathoang.doctor@gmail.com', 'Hoàng Tiến Đạt', 'Doctor'),
('lethilinh436', 'Linh436', 'linhle.doctor@gmail.com', 'Lê Thị Linh', 'Doctor'),
('tranminhquan592', 'Quan592', 'quantran.doctor@gmail.com', 'Trần Minh Quân', 'Doctor'),
('vuthimai847', 'Mai847', 'maivu.doctor@gmail.com', 'Vũ Thị Mai', 'Doctor'),
('buivantuan183', 'Tuan183', 'tuanbui.doctor@gmail.com', 'Bùi Văn Tuấn', 'Doctor'),
('dinhthihuong759', 'Huong759', 'huongdinh.doctor@gmail.com', 'Đinh Thị Hương', 'Doctor'),
('ngovanson264', 'Son264', 'sonngo.doctor@gmail.com', 'Ngô Văn Sơn', 'Doctor'),
('phamthihien391', 'Hien391', 'hienpham.doctor@gmail.com', 'Phạm Thị Hiền', 'Doctor'),
('nguyenduc648', 'Duc648', 'ducnguyen.doctor@gmail.com', 'Nguyễn Đức', 'Doctor'),
('tranthiyen523', 'Yen523', 'yentran.doctor@gmail.com', 'Trần Thị Yến', 'Doctor'),
('domanhcuong976', 'Cuong976', 'cuongdo.doctor@gmail.com', 'Đỗ Mạnh Cường', 'Doctor'),
('dothinga145', 'Nga145', 'ngado.doctor@gmail.com', 'Đỗ Thị Nga', 'Doctor'),
('lecongthanh832', 'Thanh832', 'thanhle.doctor@gmail.com', 'Lê Công Thành', 'Doctor'),
('vuthihanh679', 'Hanh679', 'hanhvu.doctor@gmail.com', 'Vũ Thị Hạnh', 'Doctor'),
('hoangiakhanh214', 'Khanh214', 'khanhhoang.doctor@gmail.com', 'Hoàng Gia Khánh', 'Doctor'),
('dangthihong567', 'Hong567', 'hongdang.doctor@gmail.com', 'Đặng Thị Hồng', 'Doctor'),
('phanvantruong938', 'Truong938', 'truongphan.doctor@gmail.com', 'Phan Văn Trường', 'Doctor'),
('nguyenthichi475', 'Chi475', 'chinguyen.doctor@gmail.com', 'Nguyễn Thị Chi', 'Doctor'),
('trinhvanbinh721', 'Binh721', 'binhtrinh.doctor@gmail.com', 'Trịnh Văn Bình', 'Doctor'),
('phamthinhung386', 'Nhung386', 'nhungpham.doctor@gmail.com', 'Phạm Thị Nhung', 'Doctor'),
('vothanhlong549', 'Long549', 'longvo.doctor@gmail.com', 'Võ Thành Long', 'Doctor'),
('lythithu917', 'Thu917', 'thuly.doctor@gmail.com', 'Lý Thị Thu', 'Doctor'),
('tranvandung283', 'Dung283', 'dungtran.doctor@gmail.com', 'Trần Văn Dũng', 'Doctor'),
('doanthiphuong654', 'Phuong654', 'phuongdoan.doctor@gmail.com', 'Đoàn Thị Phương', 'Doctor'),
('lequangvinh429', 'Vinh429', 'vinhle.doctor@gmail.com', 'Lê Quang Vinh', 'Doctor'),
('thaithivan875', 'Van875', 'vanthai.doctor@gmail.com', 'Thái Thị Vân', 'Doctor'),
('daominhhoang192', 'Hoang192', 'hoangdao.doctor@gmail.com', 'Đào Minh Hoàng', 'Doctor'),
('nguyenthigiang637', 'Giang637', 'giangnguyen.doctor@gmail.com', 'Nguyễn Thị Giang', 'Doctor'),
('trandinhphong528', 'Phong528', 'phongtran.doctor@gmail.com', 'Trần Đình Phong', 'Doctor'),
('vuthidiep941', 'Diep941', 'diepvu.doctor@gmail.com', 'Vũ Thị Diệp', 'Doctor'),
('hoangducmanh362', 'Manh362', 'manhhoang.doctor@gmail.com', 'Hoàng Đức Mạnh', 'Doctor'),
('dothituyet715', 'Tuyet715', 'tuyetdo.doctor@gmail.com', 'Đỗ Thị Tuyết', 'Doctor'),
('nguyenvantrung483', 'Trung483', 'trungnguyen.doctor@gmail.com', 'Nguyễn Văn Trung', 'Doctor'),
('vothimy926', 'My926', 'myvo.doctor@gmail.com', 'Võ Thị Mỹ', 'Doctor'),
('tranquoc571', 'Quoc571', 'quoctran.doctor@gmail.com', 'Trần Quốc', 'Doctor'),
('phamthinhu384', 'Nhu384', 'nhupham.doctor@gmail.com', 'Phạm Thị Như', 'Doctor'),
('nguyenvanhai749', 'Hai749', 'hainguyen.doctor@gmail.com', 'Nguyễn Văn Hải', 'Doctor'),
('lethibich235', 'Bich235', 'bichle.doctor@gmail.com', 'Lê Thị Bích', 'Doctor'),
('dinhvannam682', 'Nam682', 'namdinh.doctor@gmail.com', 'Đinh Văn Nam', 'Doctor'),
('tranthiloan497', 'Loan497', 'loantran.doctor@gmail.com', 'Trần Thị Loan', 'Doctor'),
('hoangminhtien853', 'Tien853', 'tienhoang.doctor@gmail.com', 'Hoàng Minh Tiến', 'Doctor'),
('phamthihuyen621', 'Huyen621', 'huyenpham.doctor@gmail.com', 'Phạm Thị Huyền', 'Doctor'),
('nguyenthithu123', 'Thu123', 'thunguyen.doctor@gmail.com', 'Nguyễn Thị Thu', 'Doctor'),
('vudinhquang394', 'Quang394', 'quangvu.doctor@gmail.com', 'Vũ Đình Quang', 'Doctor'),
-- 200 Users
('nguyenvantuan578', 'Tuan578', 'N.V.Tuan@gmail.com', 'Nguyễn Văn Tuấn', 'User'),
('lethimai924', 'Mai924', 'L.T.Mai@gmail.com', 'Lê Thị Mai', 'User'),
('phamvanhung367', 'Hung367', 'P.V.Hung@gmail.com', 'Phạm Văn Hùng', 'User'),
('duongthilan452', 'Lan452', 'D.T.Lan@gmail.com', 'Dương Thị Lan', 'User'),
('phanvanminh819', 'Minh819', 'P.V.Minh@gmail.com', 'Phan Văn Minh', 'User'),
('daothihoa635', 'Hoa635', 'D.T.Hoa@gmail.com', 'Đào Thị Hoa', 'User'),
('nguyenvannam294', 'Nam294', 'N.V.Nam@gmail.com', 'Nguyễn Văn Nam', 'User'),
('hoangthilinh781', 'Linh781', 'H.T.Linh@gmail.com', 'Hoang Thị Linh', 'User'),
('levanduc436', 'Duc436', 'L.V.Duc@gmail.com', 'Lê Văn Đức', 'User'),
('tranthinga592', 'Nga592', 'T.T.Nga@gmail.com', 'Trần Thị Nga', 'User'),
('vuvanson847', 'Son847', 'V.V.Son@gmail.com', 'Vũ Văn Sơn', 'User'),
('buithihuong183', 'Huong183', 'B.T.Huong@gmail.com', 'Bùi Thị Hương', 'User'),
('dinhvanthanh759', 'Thanh759', 'D.V.Thanh@gmail.com', 'Đinh Văn Thành', 'User'),
('ngothihong264', 'Hong264', 'N.T.Hong@gmail.com', 'Ngô Thị Hồng', 'User'),
('phamvantam391', 'Long391', 'P.V.Tam@gmail.com', 'Phạm Văn Tám', 'User'),
('nguyenthivan648', 'Van648', 'N.T.Van@gmail.com', 'Nguyễn Thị Vân', 'User'),
('tranvanbinh523', 'Binh523', 'T.V.Binh@gmail.com', 'Trần Văn Bình', 'User'),
('dothithu976', 'Thu976', 'D.T.Thu@gmail.com', 'Đỗ Thị Thu', 'User'),
('domanh145', 'Manh145', 'D.Manh@gmail.com', 'Đỗ Mạnh', 'User'),
('lethinhung832', 'Nhung832', 'L.T.Nhung@gmail.com', 'Lê Thị Nhung', 'User'),
('vuvanquang679', 'Quang679', 'V.V.Quang@gmail.com', 'Vũ Văn Quang', 'User'),
('hoangthitrang214', 'Trang214', 'H.T.Trang@gmail.com', 'Hoang Thị Trang', 'User'),
('dangvandat567', 'Dat567', 'D.V.Dat@gmail.com', 'Đặng Văn Đạt', 'User'),
('phanthianh938', 'Anh938', 'P.T.Anh@gmail.com', 'Phan Thị Anh', 'User'),
('nguyenvandung475', 'Dung475', 'N.V.Dung@gmail.com', 'Nguyễn Văn Dũng', 'User'),
('trinhthihanh721', 'Hanh721', 'T.T.Hanh@gmail.com', 'Trịnh Thị Hạnh', 'User'),
('phamvanvin386', 'Vin386', 'P.V.Vin@gmail.com', 'Phạm Văn Vin', 'User'),
('vothimy549', 'My549', 'V.T.My@gmail.com', 'Võ Thị Mỹ', 'User'),
('lyvantrung917', 'Trung917', 'L.V.Trung@gmail.com', 'Lý Văn Trung', 'User'),
('tranthichi283', 'Chi283', 'T.T.Chi@gmail.com', 'Trần Thị Chi', 'User'),
('doanvanhoang654', 'Hoang654', 'D.V.Hoang@gmail.com', 'Đoàn Văn Hoàng', 'User'),
('lethiyen429', 'Yen429', 'L.T.Yen@gmail.com', 'Lê Thị Yến', 'User'),
('thaivantien875', 'Tien875', 'T.V.Tien@gmail.com', 'Thái Văn Tiến', 'User'),
('daothiphuong192', 'Phuong192', 'D.T.Phuong@gmail.com', 'Đào Thị Phương', 'User'),
('nguyenvanquan637', 'Quan637', 'N.V.Quan@gmail.com', 'Nguyễn Văn Quân', 'User'),
('tranthibich528', 'Bich528', 'T.T.Bich@gmail.com', 'Trần Thị Bích', 'User'),
('vuvanhai941', 'Hai941', 'V.V.Hai@gmail.com', 'Vũ Văn Hải', 'User'),
('hoangthihuyen362', 'Huyen362', 'H.T.Huyen@gmail.com', 'Hoang Thị Huyền', 'User'),
('dovancuong715', 'Cuong715', 'D.V.Cuong@gmail.com', 'Đỗ Văn Cường', 'User'),
('nguyenthigiang483', 'Giang483', 'N.T.Giang@gmail.com', 'Nguyễn Thị Giang', 'User'),
('vovanphong926', 'Phong926', 'V.V.Phong@gmail.com', 'Võ Văn Phong', 'User'),
('tranthituyet571', 'Tuyet571', 'T.T.Tuyet@gmail.com', 'Trần Thị Tuyết', 'User'),
('phamthidiep384', 'Diep384', 'P.T.Diep@gmail.com', 'Phạm Thị Diệp', 'User'),
('nguyenquoc749', 'Quoc749', 'N.Quoc@gmail.com', 'Nguyễn Quốc', 'User'),
('lethinhu235', 'Nhu235', 'L.T.Nhu@gmail.com', 'Lê Thị Như', 'User'),
('dinhthiloan682', 'Loan682', 'D.T.Loan@gmail.com', 'Đinh Thị Loan', 'User'),
('trangiakhanh497', 'Khanh497', 'T.G.Khanh@gmail.com', 'Trần Gia Khánh', 'User'),
('hoangvantruong853', 'Truong853', 'H.V.Truong@gmail.com', 'Hoang Văn Trường', 'User'),
('phamthithao621', 'Thao621', 'P.T.Thao@gmail.com', 'Phạm Thị Thảo', 'User'),
('vuthitam394', 'Tam394', 'V.T.Tam@gmail.com', 'Vũ Thị Tâm', 'User'),
('phamvantuan167', 'Tuan167', 'P.V.Tuan@gmail.com', 'Phạm Văn Tuấn', 'User'),
('tranthimai283', 'Mai283', 'T.T.Mai@gmail.com', 'Trần Thị Mai', 'User'),
('vovanhung495', 'Hung495', 'V.V.Hung@gmail.com', 'Võ Văn Hùng', 'User'),
('nguyenthilan712', 'Lan712', 'N.T.Lan@gmail.com', 'Nguyễn Thị Lan', 'User'),
('dovanminh846', 'Minh846', 'D.V.Minh@gmail.com', 'Đỗ Văn Minh', 'User'),
('lethihoa359', 'Hoa359', 'L.T.Hoa@gmail.com', 'Lê Thị Hoa', 'User'),
('dinhvannam624', 'Nam624', 'D.V.Nam@gmail.com', 'Đinh Văn Nam', 'User'),
('phanthilinh937', 'Linh937', 'P.T.Linh@gmail.com', 'Phan Thị Linh', 'User'),
('hoangvanduc185', 'Duc185', 'H.V.Duc@gmail.com', 'Hoang Văn Đức', 'User'),
('daothinga472', 'Nga472', 'D.T.Nga@gmail.com', 'Đào Thị Nga', 'User'),
('nguyenvanson813', 'Son813', 'N.V.Son@gmail.com', 'Nguyễn Văn Sơn', 'User'),
('tranthihuong256', 'Huong256', 'T.T.Huong@gmail.com', 'Trần Thị Hương', 'User'),
('vuvanthanh695', 'Thanh695', 'V.V.Thanh@gmail.com', 'Vũ Văn Thành', 'User'),
('trinhthihong342', 'Hong342', 'T.T.Hong@gmail.com', 'Trịnh Thị Hồng', 'User'),
('ngovanlong578', 'Long578', 'N.V.Long@gmail.com', 'Ngô Văn Long', 'User'),
('phamthivan891', 'Van891', 'P.T.Van@gmail.com', 'Phạm Thị Vân', 'User'),
('levanbinh237', 'Binh237', 'L.V.Binh@gmail.com', 'Lê Văn Bình', 'User'),
('hoangthithu625', 'Thu625', 'H.T.Thu@gmail.com', 'Hoang Thị Thu', 'User'),
('tranmanh943', 'Manh943', 'T.Manh@gmail.com', 'Trần Mạnh', 'User'),
('daothinhung361', 'Nhung361', 'D.T.Nhung@gmail.com', 'Đào Thị Nhung', 'User'),
('duongvanquang728', 'Quang728', 'D.V.Quang@gmail.com', 'Dương Văn Quang', 'User'),
('nguyenthitrang194', 'Trang194', 'N.T.Trang@gmail.com', 'Nguyễn Thị Trang', 'User'),
('vovandat537', 'Dat537', 'V.V.Dat@gmail.com', 'Võ Văn Đạt', 'User'),
('lethianh862', 'Anh862', 'L.T.Anh@gmail.com', 'Lê Thị Anh', 'User'),
('phamvandung295', 'Dung295', 'P.V.Dung@gmail.com', 'Phạm Văn Dũng', 'User'),
('hoangthihanh614', 'Hanh614', 'H.T.Hanh@gmail.com', 'Hoang Thị Hạnh', 'User'),
('tranvanvinh938', 'Vinh938', 'T.V.Vinh@gmail.com', 'Trần Văn Vinh', 'User'),
('nguyenthimy467', 'My467', 'N.T.My@gmail.com', 'Nguyễn Thị Mỹ', 'User'),
('dinhvantrung839', 'Trung839', 'D.V.Trung@gmail.com', 'Đinh Văn Trung', 'User'),
('vothichi215', 'Chi215', 'V.T.Chi@gmail.com', 'Võ Thị Chi', 'User'),
('levanhoang573', 'Hoang573', 'L.V.Hoang@gmail.com', 'Lê Văn Hoàng', 'User'),
('dothiyen896', 'Yen896', 'D.T.Yen@gmail.com', 'Đỗ Thị Yến', 'User'),
('nguyenvantien312', 'Tien312', 'N.V.Tien@gmail.com', 'Nguyễn Văn Tiến', 'User'),
('tranthiphuong647', 'Phuong647', 'T.T.Phuong@gmail.com', 'Trần Thị Phương', 'User'),
('vuongvanquan982', 'Quan982', 'V.V.Quan@gmail.com', 'Vương Văn Quân', 'User'),
('daothibich419', 'Bich419', 'D.T.Bich@gmail.com', 'Đào Thị Bích', 'User'),
('nhatvanhai756', 'Hai756', 'N.V.Hai@gmail.com', 'Nhật Văn Hải', 'User'),
('cuuthihuyen293', 'Huyen293', 'C.T.Huyen@gmail.com', 'Cửu Thị Huyền', 'User'),
('nguyenvancuong524', 'Cuong524', 'N.V.Cuong@gmail.com', 'Nguyễn Văn Cường', 'User'),
('hoangthigiang871', 'Giang871', 'H.T.Giang@gmail.com', 'Hoang Thị Giang', 'User'),
('tranvanbom245', 'Bom245', 'T.V.Bom@gmail.com', 'Trần Văn Bom', 'User'),
('phamthituyet682', 'Tuyet682', 'P.T.Tuyet@gmail.com', 'Phạm Thị Tuyết', 'User'),
('vuthidiep917', 'Diep917', 'V.T.Diep@gmail.com', 'Vũ Thị Diệp', 'User'),
('lequoc358', 'Quoc358', 'L.Quoc@gmail.com', 'Lê Quốc', 'User'),
('nguyenthinhu674', 'Nhu674', 'N.T.Nhu@gmail.com', 'Nguyễn Thị Như', 'User'),
('trinhthiloan931', 'Loan931', 'T.T.Loan@gmail.com', 'Trịnh Thị Loan', 'User'),
('dogiakhanh264', 'Khanh264', 'D.G.Khanh@gmail.com', 'Đỗ Gia Khánh', 'User'),
('vuvantruong587', 'Truong587', 'V.V.Truong@gmail.com', 'Vũ Văn Trường', 'User'),
('lethithao942', 'Thao942', 'L.T.Thao@gmail.com', 'Lê Thị Thảo', 'User'),
('nguyenthihien385', 'Hien385', 'N.T.Hien@gmail.com', 'Nguyễn Thị Hiền', 'User'),
('phamvanan735', 'An735', 'P.V.An@gmail.com', 'Phạm Văn An', 'User'),
('tranthilien216', 'Lien216', 'T.T.Lien@gmail.com', 'Trần Thị Liên', 'User'),
('vovandanh593', 'Danh593', 'V.V.Danh@gmail.com', 'Võ Văn Danh', 'User'),
('dangthihau847', 'Hau847', 'D.T.Hau@gmail.com', 'Đặng Thị Hậu', 'User'),
('nguyentri329', 'Tri329', 'N.Tri@gmail.com', 'Nguyễn Trí', 'User'),
('lethikim625', 'Kim625', 'L.T.Kim@gmail.com', 'Lê Thị Kim', 'User'),
('dinhvantho971', 'Tho971', 'D.V.Tho@gmail.com', 'Đinh Văn Thọ', 'User'),
('phanthichau347', 'Chau347', 'P.T.Chau@gmail.com', 'Phan Thị Châu', 'User'),
('dovanduy682', 'Duy682', 'D.V.Duy@gmail.com', 'Đỗ Văn Duy', 'User'),
('tranthixuan926', 'Xuan926', 'T.T.Xuan@gmail.com', 'Trần Thị Xuân', 'User'),
('phamvanthinh514', 'Thinh514', 'P.V.Thinh@gmail.com', 'Phạm Văn Thịnh', 'User'),
('lethily863', 'Ly863', 'L.T.Ly@gmail.com', 'Lê Thị Ly', 'User'),
('nguyenvannghia259', 'Nghia259', 'N.V.Nghia@gmail.com', 'Nguyễn Văn Nghĩa', 'User'),
('vuthidiem794', 'Diem794', 'V.T.Diem@gmail.com', 'Vũ Thị Diễm', 'User'),
('trankhoa381', 'Khoa381', 'T.Khoa@gmail.com', 'Trần Khoa', 'User'),
('dinhthisuong652', 'Suong652', 'D.T.Suong@gmail.com', 'Đinh Thị Sương', 'User'),
('phamvancong973', 'Cong973', 'P.V.Cong@gmail.com', 'Phạm Văn Công', 'User'),
('nguyenthiha417', 'Ha417', 'N.T.Ha@gmail.com', 'Nguyễn Thị Hà', 'User'),
('vovanlam769', 'Lam769', 'V.V.Lam@gmail.com', 'Võ Văn Lâm', 'User'),
('dangthihien328', 'Hien328', 'D.T.Hien@gmail.com', 'Đặng Thị Hiên', 'User'),
('levanphu574', 'Phu574', 'L.V.Phu@gmail.com', 'Lê Văn Phú', 'User'),
('tranthicam896', 'Cam896', 'T.T.Cam@gmail.com', 'Trần Thị Cẩm', 'User'),
('dovandang251', 'Dang251', 'D.V.Dang@gmail.com', 'Đỗ Văn Đăng', 'User'),
('phanthitrinh735', 'Trinh735', 'P.T.Trinh@gmail.com', 'Phan Thị Trinh', 'User'),
('nguyenduan489', 'Duan489', 'N.Duan@gmail.com', 'Nguyễn Duẩn', 'User'),
('vuthile826', 'Le826', 'V.T.Le@gmail.com', 'Vũ Thị Lệ', 'User'),
('tranvanluan352', 'Luan352', 'T.V.Luan@gmail.com', 'Trần Văn Luân', 'User'),
('dinhthihang719', 'Hang719', 'D.T.Hang@gmail.com', 'Đinh Thị Hằng', 'User'),
('levansinh943', 'Sinh943', 'L.V.Sinh@gmail.com', 'Lê Văn Sinh', 'User'),
('nguyenthihue267', 'Hue267', 'N.T.Hue@gmail.com', 'Nguyễn Thị Huệ', 'User'),
('phamvanquy582', 'Quy582', 'P.V.Quy@gmail.com', 'Phạm Văn Quý', 'User'),
('dangvandong917', 'Dong917', 'D.V.Dong@gmail.com', 'Đặng Văn Đông', 'User'),
('tranthibach354', 'Bach354', 'T.T.Bach@gmail.com', 'Trần Thị Bạch', 'User'),
('dovanvu673', 'Vu673', 'D.V.Vu@gmail.com', 'Đỗ Văn Vũ', 'User'),
('lethioanh926', 'Oanh926', 'L.T.Oanh@gmail.com', 'Lê Thị Oanh', 'User'),
('trinhvanloc419', 'Loc419', 'T.V.1Loc@gmail.com', 'Trịnh Văn Lộc', 'User'),
('phamthitam758', 'Tam758', 'P.T.Tam@gmail.com', 'Phạm Thị Tâm', 'User'),
('nguyenvancan291', 'Can291', 'N.V.Can@gmail.com', 'Nguyễn Văn Cần', 'User'),
('vothitien547', 'Tien547', 'V.T.Tien@gmail.com', 'Võ Thị Tiên', 'User'),
('tranvantrong862', 'Trong862', 'T.V.Trong@gmail.com', 'Trần Văn Trọng', 'User'),
('lethuyen243', 'Uyen243', 'L.T.Uyen@gmail.com', 'Lê Thị Uyên', 'User'),
('daovanung679', 'Ung679', 'D.V.Ung@gmail.com', 'Đào Văn Ưng', 'User'),
('phamthiquyen915', 'Quyen915', 'P.T.Quyen@gmail.com', 'Phạm Thị Quyên', 'User'),
('nguyenvanhiep372', 'Hiep372', 'N.V.Hiep@gmail.com', 'Nguyễn Văn Hiệp', 'User'),
('dangthichung697', 'Chung697', 'D.T.Chung@gmail.com', 'Đặng Thị Chung', 'User'),
('vovanthai436', 'Thai436', 'V.V.Thai@gmail.com', 'Võ Văn Thái', 'User'),
('tranthiquynh872', 'Quynh872', 'T.T.Quynh@gmail.com', 'Trần Thị Quỳnh', 'User'),
('phamvankhoi293', 'Khoi293', 'P.V.Khoi@gmail.com', 'Phạm Văn Khôi', 'User'),
('nguyenthithuy526', 'Thuy526', 'N.T.Thuy@gmail.com', 'Nguyễn Thị Thúy', 'User'),
('levanba819', 'Ba819', 'L.V.Ba@gmail.com', 'Lê Văn Ba', 'User'),
('hoangthivy265', 'Vy265', 'H.T.Vy@gmail.com', 'Hoang Thị Vy', 'User'),
('tranvantu593', 'Tu593', 'T.V.Tu@gmail.com', 'Trần Văn Tú', 'User'),
('phamthingoc847', 'Ngoc847', 'P.T.Ngoc@gmail.com', 'Phạm Thị Ngọc', 'User'),
('nguyenminh329', 'Minh329', 'N.Minh@gmail.com', 'Nguyễn Minh', 'User'),
('dovanluu674', 'Luu674', 'D.V.Luu@gmail.com', 'Đỗ Văn Lưu', 'User'),
('lethingan913', 'Ngan913', 'L.T.Ngan@gmail.com', 'Lê Thị Ngân', 'User'),
('lephat345', 'Phat345', 'L.Phat@gmail.com', 'Lê Phát', 'User'),
('nguyenthihoa782', 'Hoa782', 'N.T.Hoa@gmail.com', 'Nguyễn Thị Hoa', 'User'),
('phamdan429', 'Dan429', 'P.Dan@gmail.com', 'Phạm Đan', 'User'),
('tranthuy687', 'Thuy687', 'T.Thuy@gmail.com', 'Trần Thúy', 'User'),
('vobao254', 'Bao254', 'V.Bao@gmail.com', 'Võ Bảo', 'User'),
('domy673', 'My673', 'D.My@gmail.com', 'Đỗ Mỹ', 'User'),
('lethang915', 'Thang915', 'L.Thang@gmail.com', 'Lê Thắng', 'User'),
('nguyenhieu462', 'Hieu462', 'N.Hieu@gmail.com', 'Nguyễn Hiếu', 'User'),
('phamxuan794', 'Xuan794', 'P.Xuan@gmail.com', 'Phạm Xuân', 'User'),
('tranthinhi231', 'Nhi231', 'T.T.Nhi@gmail.com', 'Trần Thị Nhi', 'User'),
('vovansang587', 'Sang587', 'V.V.Sang@gmail.com', 'Võ Văn Sang', 'User'),
('lethitrinh815', 'Trinh815', 'L.T.Trinh@gmail.com', 'Lê Thị Trinh', 'User'),
('nguyenvansi369', 'Si369', 'N.V.Si@gmail.com', 'Nguyễn Văn Sĩ', 'User'),
('phamthithanh726', 'Thanh726', 'P.T.Thanh@gmail.com', 'Phạm Thị Thanh', 'User'),
('tranvanloc194', 'Loc194', 'T.V.Loc@gmail.com', 'Trần Văn Lộc', 'User'),
('dothihuyen473', 'Huyen473', 'D.T.Huyen@gmail.com', 'Đỗ Thị Huyền', 'User'),
('levantham816', 'Tham816', 'L.V.Tham@gmail.com', 'Lê Văn Thắm', 'User'),
('nguyenthihanh254', 'Hanh254', 'N.T.2Hanh@gmail.com', 'Nguyễn Thị Hạnh', 'User'),
('phamvanduong635', 'Duong635', 'P.V.Duong@gmail.com', 'Phạm Văn Dương', 'User'),
('vothituyen912', 'Tuyen912', 'V.T.Tuyen@gmail.com', 'Võ Thị Tuyền', 'User'),
('tranvanquyen385', 'Quyen385', 'T.V.Quyen@gmail.com', 'Trần Văn Quyến', 'User'),
('dothioanh762', 'Oanh762', 'D.T.Oanh@gmail.com', 'Đỗ Thị Oanh', 'User'),
('levandong253', 'Dong253', 'L.V.Dong@gmail.com', 'Lê Văn Đông', 'User'),
('nguyenthihanh574', 'Hanh574', 'N.T.Hanh@gmail.com', 'Nguyễn Thị Hạnh', 'User'),
('phamvanvinh816', 'Vinh816', 'P.V.Vinh@gmail.com', 'Phạm Văn Vinh', 'User'),
('tranthithu273', 'Thu273', 'T.T.Thu@gmail.com', 'Trần Thị Thu', 'User'),
('vovanbang592', 'Bang592', 'V.V.Bang@gmail.com', 'Võ Văn Bằng', 'User'),
('dothilien847', 'Lien847', 'D.T.Lien@gmail.com', 'Đỗ Thị Liên', 'User'),
('levanky329', 'Ky329', 'L.V.Ky@gmail.com', 'Lê Văn Kỳ', 'User'),
('nguyentuong651', 'Tuong651', 'N.Tuong@gmail.com', 'Nguyễn Tường', 'User'),
('phamthicam928', 'Cam928', 'P.T.Cam@gmail.com', 'Phạm Thị Cẩm', 'User'),
('tranduong315', 'Duong315', 'T.Duong@gmail.com', 'Trần Dương', 'User'),
('vothinguyet743', 'Nguyet743', 'V.T.Nguyet@gmail.com', 'Võ Thị Nguyệt', 'User'),
('dophung267', 'Phung267', 'D.Phung@gmail.com', 'Đỗ Phụng', 'User'),
('nguyennhan584', 'Nhan584', 'N.Nhan@gmail.com', 'Nguyễn Nhàn', 'User'),
('lekien926', 'Kien926', 'L.Kien@gmail.com', 'Lê Kiên', 'User'),
('phamphi372', 'Phi372', 'P.Phi@gmail.com', 'Phạm Phi', 'User'),
('tranthihuong716', 'Huong716', 'T.T.3Huong@gmail.com', 'Trần Thị Hương', 'User'),
('vo-tra298', 'Tra298', 'V.Tra@gmail.com', 'Võ Trà', 'User'),
('dothitram641', 'Tram641', 'D.T.Tram@gmail.com', 'Đỗ Thị Trâm', 'User'),
('lehoai923', 'Hoai923', 'L.Hoai@gmail.com', 'Lê Hoài', 'User'),
('nguyenbao5127', 'Bao517', 'N.Bao1@gmail.com', 'Nguyễn Bảo', 'User'),
('phamviet862', 'Viet862', 'P.Viet@gmail.com', 'Phạm Việt', 'User'),
('tranthiyen349', 'Yen349', 'T.T.Yen@gmail.com', 'Trần Thị Yến', 'User'),
('nguyenbao517', 'Bao517', 'N.Bao@gmail.com', 'Nguyễn Bảo', 'User');
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