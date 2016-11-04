-- Create the database and connect to it.
connect 'jdbc:derby://localhost:1527/aip;create=true';

-- Add a user to the database, username dev, password dev
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('aip','aip');

-- Grant all privileges to user dev
CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.database.fullAccessUsers','aip');

-- Disconnect from the newly created database
disconnect;

-- Reconnect to the newly created database as user dev
connect 'jdbc:derby://localhost:1527/aip;user=aip;password=aip';

exit;