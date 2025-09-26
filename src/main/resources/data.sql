-- Categories
insert into Categories(name, images) values ('Phone', 'phone.png');
insert into Categories(name, images) values ('Laptop', 'laptop.png');

-- Users
insert into Users(fullname,email,password,phone) values ('Admin','admin@shop.com','123','0123456789');

-- Products  (chú ý cột desc -> description đã map @Column(name="desc"))
insert into Products(title, quantity, [desc], price, userid, category_id)
values ('iPhone 14', 10, 'Apple', 1500, 1, 1);

insert into Products(title, quantity, [desc], price, userid, category_id)
values ('Dell XPS', 5, 'Ultrabook', 2200, 1, 2);
