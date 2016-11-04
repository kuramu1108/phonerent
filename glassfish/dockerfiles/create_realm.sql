connect 'jdbc:derby://localhost:1527/aip;user=aip;password=aip';

create view jdbcrealm_user (username, password) as
select email, password
from account;

create view jdbcrealm_group (username, groupname) as
select email, accountType
from account;

disconnect;

exit;