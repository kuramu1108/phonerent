#!/usr/bin/env bash

asadmin start-database

/usr/local/glassfish4/javadb/bin/ij /usr/local/bin/create_db.sql

asadmin start-domain

asadmin login

asadmin create-jdbc-connection-pool --datasourceclassname org.apache.derby.jdbc.ClientDataSource40 --restype javax.sql.DataSource --property user=aip:password=aip:databaseName=aip:serverName=localhost:portNumber=1527 AIPPool

asadmin create-jdbc-resource --connectionpoolid AIPPool jdbc/aip

asadmin deploy --name phonerent /usr/local/bin/phonerent.ear

/usr/local/glassfish4/javadb/bin/ij /usr/local/bin/create_realm.sql

asadmin create-auth-realm --classname com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm --property jaas-context=jdbcRealm:datasource-jndi=jdbc/aip:user-table=jdbcrealm_user:user-name-column=username:password-column=password:group-table=jdbcrealm_group:group-table-user-name-column=username:group-name-column=groupname:encoding=Hex:digestrealm-password-enc-algorithm=SHA-256 aipRealm
# start GlassFish - verbose forces to run in bg
asadmin create-auth-realm --help

#asadmin stop-domain
#asadmin start-domain --verbose

while ! tail -f /usr/local/glassfish4/glassfish/domains/domain1/logs/server.log ; do sleep 1 ; done