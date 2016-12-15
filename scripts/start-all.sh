ij < ./scripts/drop_tables.sql
/usr/local/apache-tomcat-8.0.38/bin/shutdown.sh
mvn clean dependency:copy-dependencies package
cp -r ./web-application/target/fpx-sf-notification-webapp /usr/local/apache-tomcat-8.0.38/webapps/
/usr/local/apache-tomcat-8.0.38/bin/startup.sh
stopNetworkServer
(cd ../database;startNetworkServer&)
java -jar data-service/target/fpx-sf-notification-data-service.jar --init_db --property properties/sf.config --shutdown_in 120
 

