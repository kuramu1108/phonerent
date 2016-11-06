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

insert into simPlan (id, bonussms, credit, phoneNumber, simName, price) values
    (2100, 12, 12.00, '0451050494', 'Raymond', 21.00);
insert into simPlan (id, bonussms, credit, phoneNumber, simName, price) values
    (2380, 19, 21.00, '0431911088', 'Po-Hao', 30.00);

insert into account(id, accountType, deliveryAddress, email, firstName, isActivate, lastName, password, passwordResetId, phoneNumber, creditCard_id, shoppingCart_id) values
    (2001, 'Admins', '2007 George St', 'Rose@gmail.com', 'Rose', 1, 'Rosetta', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '0451050494', null, null);

