@Echo Bygger Angular, kopiere Angular filer til root, Bygger Java projektet og kopiere det på serveren!
cd C:\Users\Tim\IdeaProjects\webapptest1\src\Angular\grewy-dk\
call ng build
cd C:\Users\Tim\IdeaProjects\webapptest1
call mvn clean
call mvn package
java -jar CopyAngularFiles.jar
copy \\10.0.0.11\webapps\root.war \\10.0.0.11\webapps\Old_versions\root.war
copy target\webapptest1.war \\10.0.0.11\webapps\root.war