/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  garysnmb
 * Created: 2016/10/17
 */

create view jdbcrealm_user (username, password) as
select email, password
from account;

create view jdbcrealm_group (username, groupname) as
select email, accountType
from account