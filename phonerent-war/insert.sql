/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  TOSHIBA
 * Created: 28/10/2016
 */

insert into phone (id, manufacturer, modelName, price) values
    (19999, 'Apple', 'Iphone 7', 30.00);
insert into phone (id, manufacturer, modelName, price) values
    (22202, 'Samsung', 'S7', 20.00);
insert into phone (id, manufacturer, modelName, price) values
    (24150, 'Apple', 'Iphone 7 Plus', 11.00);
insert into phone (id, manufacturer, modelName, price) values
    (28900, 'Apple', 'Iphone 6s', 6.00);
insert into phone (id, manufacturer, modelName, price) values
    (33404, 'Apple', 'Ipad Air 2', 7.00);
insert into phone (id, manufacturer, modelName, price) values
    (37654, 'Samsung', 'Galaxy s7 Edge', 20.00);
insert into phone (id, manufacturer, modelName, price) values
    (39875, 'Samsung', 'Galaxy s6', 10.00);
insert into phone (id, manufacturer, modelName, price) values
    (43201, 'Samsung', 'Note 7', 2.00);

insert into simPlan (id, bonussms, credit, phoneNumber, simName, price) values
    (2100, 12, 12.00, '0451050494', 'Optus', 21.00);
insert into simPlan (id, bonussms, credit, phoneNumber, simName, price) values
    (2380, 19, 21.00, '0431911088', 'Optus $30', 30.00);
insert into simPlan (id, bonussms, credit, phoneNumber, simName, price) values
    (2710, 1, 3.00, '0451111010', 'Telstra $5', 5.00);
insert into simPlan (id, bonussms, credit, phoneNumber, simName, price) values
    (2880, 10, 9.00, '0476721212', 'Telstra $10', 10.00);
insert into simPlan (id, bonussms, credit, phoneNumber, simName, price) values
    (3202, 15, 35.00, '0451767307', 'Telstra $30', 30.00);
insert into simPlan (id, bonussms, credit, phoneNumber, simName, price) values
    (4681, 28, 9.00, '0431090575', 'Vodaphone $15', 15.00);
insert into simPlan (id, bonussms, credit, phoneNumber, simName, price) values
    (8107, 37, 15.00, '0451131395', 'Vodaphone $20', 20.00);
insert into simPlan (id, bonussms, credit, phoneNumber, simName, price) values
    (16270, 59, 24.00, '0431910102', 'Vodaphone $30', 30.00);

insert into account(id, accountType, deliveryAddress, email, firstName, isActivate, lastName, password, passwordResetId, phoneNumber, creditCard_id, shoppingCart_id) values
    (2001, 'Admins', '2007 George St', 'colagarychen@gmail.com', 'Rose', 1, 'Rosetta', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '0451050494', null, null);

