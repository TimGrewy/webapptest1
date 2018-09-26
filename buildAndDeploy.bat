call mvn clean
call mvn package
copy target\webapptest1.war \\10.0.0.11\webapps\root.war