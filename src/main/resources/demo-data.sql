-- noinspection SqlResolveForFile

-- noinspection SqlNoDataSourceInspectionForFile

--product group
INSERT INTO t_product_group(id,name,active) values(1,'et ürünleri',true);
INSERT INTO t_product_group(id,name,active) values(2,'kuru gıda',true);
INSERT INTO t_product_group(id,name,active) values(3,'sebzeler',true);
INSERT INTO t_product_group(id,name,active) values(4,'meyveler',true);
INSERT INTO t_product_group(id,name,active) values(5,'abcproduct groups 5',true);
INSERT INTO t_product_group(id,name,active) values(6,'qweproduct groups 6',true);
INSERT INTO t_product_group(id,name,active) values(7,'ghjproduct groups 7',true);
INSERT INTO t_product_group(id,name,active) values(8,'product groups 8',true);

--measurement unit
INSERT INTO t_measurement_unit(id,name,active,short_name) values(1,'Kilogram',true,'Kg');
INSERT INTO t_measurement_unit(id,name,active,short_name) values(2,'Litre',true,'Lt');
INSERT INTO t_measurement_unit(id,name,active,short_name) values(3,'Adet',true,'Ad');
INSERT INTO t_measurement_unit(id,name,active,short_name) values(4,'Metre',true,'Mt');
INSERT INTO t_measurement_unit(id,name,active,short_name) values(5,'Gram',true,'Gr');
INSERT INTO t_measurement_unit(id,name,active,short_name) values(6,'Ton',true,'Tn');

--food group
INSERT INTO t_food_group(id,name,active) values(1,'pilavlar',true);
INSERT INTO t_food_group(id,name,active) values(2,'çorbalar',true);
INSERT INTO t_food_group(id,name,active) values(3,'köfteler',true);
INSERT INTO t_food_group(id,name,active) values(4,'tatlılar',true);
INSERT INTO t_food_group(id,name,active) values(5,'kebaplar',true);
INSERT INTO t_food_group(id,name,active) values(6,'salatalar',true);
INSERT INTO t_food_group(id,name,active) values(7,'ghjfood group 7',true);
INSERT INTO t_food_group(id,name,active) values(8,'food group 8',true);

--products

INSERT INTO t_product(id,name,active,product_group_id,measurement_unit_id) values(1,'pirinç',true,2,1);
INSERT INTO t_product(id,name,active,product_group_id,measurement_unit_id) values(2,'bulgur',true,2,1);
INSERT INTO t_product(id,name,active,product_group_id,measurement_unit_id) values(3,'makarna',true,2,1);
INSERT INTO t_product(id,name,active,product_group_id,measurement_unit_id) values(4,'kuru fasulye',true,2,1);
INSERT INTO t_product(id,name,active,product_group_id,measurement_unit_id) values(5,'barbunya',true,2,1);

INSERT INTO t_product(id,name,active,product_group_id,measurement_unit_id) values(6,'kırmızı et',true,1,1);
INSERT INTO t_product(id,name,active,product_group_id,measurement_unit_id) values(7,'beyaz et',true,1,1);
INSERT INTO t_product(id,name,active,product_group_id,measurement_unit_id) values(8,'ciğer',true,1,1);

INSERT INTO t_product(id,name,active,product_group_id,measurement_unit_id) values(9,'yağ',true,5,2);

INSERT INTO t_product(id,name,active,product_group_id,measurement_unit_id) values(10,'mercimek',true,2,1);

--warehouses

INSERT INTO t_warehouse(id,name,active) values(1,'depo 1',true);

INSERT INTO t_warehouse(id,name,active) values(2,'depo 2',true);

--dining halls
INSERT INTO t_dining_hall(id,name,active,warehouse_id) values(1,'yemekhane 1',true,1);
INSERT INTO t_dining_hall(id,name,active,warehouse_id) values(2,'yemekhane 2',true,1);
INSERT INTO t_dining_hall(id,name,active,warehouse_id) values(3,'yemekhane 3',true,2);
INSERT INTO t_dining_hall(id,name,active,warehouse_id) values(4,'yemekhane 4',true,2);

--stocks
INSERT INTO t_stock(id,active,warehouse_id,product_id,status_time,quantity,unit_price) values(1,true,1,1,'1970-01-01 02:00:00',12.0,100);
INSERT INTO t_stock(id,active,warehouse_id,product_id,status_time,quantity,unit_price) values(2,true,2,1,'1970-01-01 02:00:00',15.0,120);
INSERT INTO t_stock(id,active,warehouse_id,product_id,status_time,quantity,unit_price) values(3,true,1,2,'1970-01-01 02:00:00',11.0,125);
INSERT INTO t_stock(id,active,warehouse_id,product_id,status_time,quantity,unit_price) values(4,true,2,2,'1970-01-01 02:00:00',13.0,140);

--stock-transaction
INSERT INTO t_stock_transaction(id,active,stock_id,transaction_time,quantity,unit_price,transaction_type) values(1,true,1,'2022-05-05 09:00:00',5.0,140,0);
INSERT INTO t_stock_transaction(id,active,stock_id,transaction_time,quantity,unit_price,transaction_type) values(2,true,1,'2022-05-06 09:30:00',4.0,120,1);
INSERT INTO t_stock_transaction(id,active,stock_id,transaction_time,quantity,unit_price,transaction_type) values(3,true,2,'2022-05-05 11:00:00',2.0,130,0);
INSERT INTO t_stock_transaction(id,active,stock_id,transaction_time,quantity,unit_price,transaction_type) values(4,true,2,'2022-04-05 14:00:00',1.1,140,1);
INSERT INTO t_stock_transaction(id,active,stock_id,transaction_time,quantity,unit_price,transaction_type) values(5,true,3,'2022-05-08 17:30:00',4.0,120,1);
INSERT INTO t_stock_transaction(id,active,stock_id,transaction_time,quantity,unit_price,transaction_type) values(6,true,4,'2022-03-05 15:00:00',7.0,110,0);

--stock-transaction-in
INSERT INTO t_stock_in_transaction(id,active) VALUES ( 2,true );
INSERT INTO t_stock_in_transaction(id,active) VALUES ( 4,true );
INSERT INTO t_stock_in_transaction(id,active) VALUES ( 5,true );

--stock-transaction-out
INSERT INTO t_stock_out_transaction(id,active) VALUES ( 1,true );
INSERT INTO t_stock_out_transaction(id,active) VALUES ( 3,true );
INSERT INTO t_stock_out_transaction(id,active) VALUES ( 6,true );

--foods

INSERT INTO t_food(id,name,active,food_group_id) values(1,'prinç pilavı',true,1);
INSERT INTO t_food(id,name,active,food_group_id) values(2,'bulgur pilavı',true,1);
INSERT INTO t_food(id,name,active,food_group_id) values(3,'mercimek çorbası',true,2);

--foodproduct
INSERT INTO t_food_product(id,active,food_id,product_id,status_time,quantity) values(1,true,1,1,'1970-01-01 02:00:00',0.5);
INSERT INTO t_food_product(id,active,food_id,product_id,status_time,quantity) values(2,true,1,9,'1970-01-01 02:00:00',0.1);
INSERT INTO t_food_product(id,active,food_id,product_id,status_time,quantity) values(3,true,2,2,'1970-01-01 02:00:00',0.4);
INSERT INTO t_food_product(id,active,food_id,product_id,status_time,quantity) values(4,true,2,9,'1970-01-01 02:00:00',0.05);
INSERT INTO t_food_product(id,active,food_id,product_id,status_time,quantity) values(5,true,3,10,'1970-01-01 02:00:00',0.45);
INSERT INTO t_food_product(id,active,food_id,product_id,status_time,quantity) values(6,true,3,9,'1970-01-01 02:00:00',0.04);

--menu
INSERT INTO t_menu(id,name,active) values(1,'menu 1',true);
INSERT INTO t_menu(id,name,active) values(2,'menu 2',true);
INSERT INTO t_menu(id,name,active) values(3,'menu 3',true);

--menufoods
INSERT INTO t_menu_food(id,active,menu_id,food_id,status_time) values(1,true,1,1,'1970-01-01 02:00:00');
INSERT INTO t_menu_food(id,active,menu_id,food_id,status_time) values(2,true,1,2,'1970-01-01 02:00:00');
INSERT INTO t_menu_food(id,active,menu_id,food_id,status_time) values(3,true,1,3,'1970-01-01 02:00:00');
INSERT INTO t_menu_food(id,active,menu_id,food_id,status_time) values(4,true,2,2,'1970-01-01 02:00:00');
INSERT INTO t_menu_food(id,active,menu_id,food_id,status_time) values(5,true,3,2,'1970-01-01 02:00:00');
INSERT INTO t_menu_food(id,active,menu_id,food_id,status_time) values(6,true,3,3,'1970-01-01 02:00:00');

--foodproducts
INSERT INTO t_menu_product(id,active,menu_id,product_id,status_time,quantity) values(1,true,1,1,'1970-01-01 02:00:00',0.5);
INSERT INTO t_menu_product(id,active,menu_id,product_id,status_time,quantity) values(2,true,1,2,'1970-01-01 02:00:00',0.5);

--meal
INSERT INTO t_meal(id,active,dining_hall_id,meal_date,meal_time,number_of_people,menu_id,status_time) values(1,true,1,'2023-01-01','12:00:00',100,1,'1970-01-01 02:00:00');
INSERT INTO t_meal(id,active,dining_hall_id,meal_date,meal_time,number_of_people,menu_id,status_time) values(2,true,1,'2023-02-02','13:00:00',120,1,'1970-01-01 02:00:00');
INSERT INTO t_meal(id,active,dining_hall_id,meal_date,meal_time,number_of_people,menu_id,status_time) values(3,true,2,'2023-03-03','16:00:00',130,2,'1970-01-01 02:00:00');
INSERT INTO t_meal(id,active,dining_hall_id,meal_date,meal_time,number_of_people,menu_id,status_time) values(4,true,3,'2023-03-05','17:00:00',132,2,'1970-01-01 02:00:00');
INSERT INTO t_meal(id,active,dining_hall_id,meal_date,meal_time,number_of_people,menu_id,status_time) values(5,true,4,'2023-04-05','12:00:00',145,3,'1970-01-01 02:00:00');

--bill

INSERT INTO t_bill(id,active,bill_time,warehouse_id) values(1,true,'2021-04-02 14:00:00',1);
INSERT INTO t_bill(id,active,bill_time,warehouse_id) values(2,true,'2020-05-02 10:00:00',1);
INSERT INTO t_bill(id,active,bill_time,warehouse_id) values(3,true,'2021-04-07 15:00:00',2);
INSERT INTO t_bill(id,active,bill_time,warehouse_id) values(4,true,'2021-06-06 09:00:00',2);

--bill products

INSERT INTO t_bill_product(id,active,bill_id,product_id,quantity,unit_price,status_time) values(1,true,1,1,1000,10.5,'1970-01-01 02:00:00');
INSERT INTO t_bill_product(id,active,bill_id,product_id,quantity,unit_price,status_time) values(2,true,1,2,140,11.5,'1970-01-01 02:00:00');
INSERT INTO t_bill_product(id,active,bill_id,product_id,quantity,unit_price,status_time) values(3,true,1,3,1440,21.5,'1970-01-01 02:00:00');
INSERT INTO t_bill_product(id,active,bill_id,product_id,quantity,unit_price,status_time) values(4,true,1,4,1240,25.0,'1970-01-01 02:00:00');


