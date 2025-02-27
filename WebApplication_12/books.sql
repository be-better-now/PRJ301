-- Tạo bảng Sách
CREATE TABLE tblBooks (
    BookID Char(5) PRIMARY KEY,
    Title NVARCHAR(200) NOT NULL,
    Author NVARCHAR(100) NOT NULL,
    PublishYear INT,
    Price DECIMAL(10,2),
    Quantity INT DEFAULT 0,
);

-- Thêm dữ liệu mẫu
INSERT INTO tblBooks (BookID, Title, Author, PublishYear, Price, Quantity) VALUES
('B0001', 'Dế Mèn Phiêu Lưu Ký', 'Tô Hoài', 1941, 65000, 10),
('B0002', 'Số Đỏ', 'Vũ Trọng Phụng', 1936, 75000, 5),
('B0003', 'Nhật Ký Trong Tù', 'Hồ Chí Minh', 1943, 55000, 8),
('B0004', 'Truyện Kiều', 'Nguyễn Du', 1820, 85000, 15),
('B0005', 'Tắt Đèn', 'Ngô Tất Tố', 1937, 60000, 12),
('B0006', 'Chí Phèo', 'Nam Cao', 1941, 45000, 20),
('B0007', 'Vang Bóng Một Thời', 'Nguyễn Tuân', 1940, 70000, 7),
('B0008', 'Những Ngã Tư Và Những Cột Đèn', 'Trần Đăng Khoa', 1968, 50000, 9),
('B0009', 'Bên Kia Bờ Hy Vọng', 'Nguyễn Ngọc Thuần', 2005, 80000, 6),
('B0010', 'Mắt Biếc', 'Nguyễn Nhật Ánh', 1990, 95000, 25);




-- Tạo bảng Sách
CREATE TABLE tblFlowers (
    FlowerID Char(5) PRIMARY KEY,
    FlowerName NVARCHAR(200) NOT NULL,    
    Quality NVARCHAR(100) NOT NULL,
	Category VARCHAR(100),
    Price DECIMAL(10,2),
    Quantity INT DEFAULT 0,
    Discount DECIMAL(5,2) DEFAULT 0,
    Status BOOLEAN DEFAULT FALSE,
);

-- Thêm dữ liệu mẫu
INSERT INTO tblBooks (FlowerID, FlowerName, Quality, Category, Price, Quantity, Discount, Status) VALUES
('F0001', 'Bó Hoa Hồng Đỏ', 'Đơn Giản', 'Bó Hoa', 250000, 10),
('F0002', 'Lẵng Hoa Sinh Nhật Ngọt Ngào Quyến Rũ', 'Cao Cấp', 'Lẵng Hoa', 750000, 5),
('F0003', 'Bó Hoa Ruby', 'Đơn Giản', 'Bó Hoa', 200000, 8),
('F0004', 'Bó Hoa Cẩm Chướng', 'Đơn Giản', 'Bó Hoa',300000, 15),
('F0005', 'Hộp Hoa Cúc Mẫu Đơn Xanh Lá', 'Cao Cấp', 'Hộp Hoa', 600000, 12),
('F0006', 'Giỏ Hoa Gấu Bông GoodDays', 'Cao Cấp', 'Giỏ Hoa', 1000000, 20),
('F0007', 'Giỏ Hoa Thỏ Bông JellyCat Xám', 'Cao Cấp', 'Giỏ Hoa', 700000, 7),
('F0008', 'Bó Hoa Cúc Tana', 'Cao Cấp', 'Bó Hoa', 250000, 9),
('F0009', 'Lẵng Hoa Chúc Mừng Sinh Nhật Sếp Nam Đẹp', 'Cao Cấp', 'Lẵng Hoa', 800000, 6),
('F0010', 'Lẵng Hoa Cúc Mẫu Đơn Pastel', 'Cao Cấp', 'Lẵng Hoa', 1000000, 25);