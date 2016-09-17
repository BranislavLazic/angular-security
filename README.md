# angular-security

Demo application that shows how to implement OAuth2 token based authentication and
authorization in AngularJS application with Java REST API sample application.

## Build & development

Make sure you have MySQL server installed and database called "employee_db".
You can edit database settings in angular-security-rest-oauth/src/main/resources/application.properties file.

In `angular-security-client` directory run `npm install`

In `angular-security-client` directory run `bower install`

In `angular-security-rest` run `mvn clean install`

In `angular-security-rest/target` directory run `java -jar angular-security-rest-0.0.1-SNAPSHOT.jar`

In `angular-security-client` directory run `gulp`.

Your Tomcat will run at `http://localhost:8080` and your client application will run at `http://localhost:8000`
