Simple java Maven "Hello world"
==========================================================================
  ## Parent **MavenDemoProject** with two child projects, **ModuleOneDemoProject** and **ModuleTwoDemoProject**

- Declared method `getAppName()` in class `ModuleOneApp` of child project `ModuleOneDemoProject`, called by **main** method of class `ModuleTwoApp` in child project `ModuleTwoDemoProject`.

- Parent project contains `App` class with **main** method that prints **Hello world!** in console.

  ## Adding dependencies to projects using dependency management

- Parent **pom** configuration
  -  Added dependencies to the dependencyManagement section of the parent POM. In this section versions for all dependencies are specified.
  ```
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>3.8.1</version>
                <scope>test</scope>
            </dependency>
            <dependency>
                <groupId>org.javatuples</groupId>
                <artifactId>javatuples</artifactId>
                <version>1.2</version>
            </dependency>
            <dependency>
                <groupId>com.blibli.oss.helpers</groupId>
                <artifactId>java-common-helper</artifactId>
                <version>0.0.12</version>
            </dependency>
            <dependency>
                <groupId>com.github.fracpete</groupId>
                <artifactId>maven-dependency-helper</artifactId>
                <version>0.0.3</version>
            </dependency>
            <dependency>
                <groupId>org.codehaus.plexus</groupId>
                <artifactId>plexus-component-annotations</artifactId>
                <version>2.0.0</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
  ```
  - The `<dependencyManagement>` tag precedes the `<dependencies>` tag which is used to declare the dependencies.
  - Dependencies declared in parent **pom** file: `junit`, `javatuples`, `java-common-helper`, `maven-dependency-helper` and `plexus-component-annotations`.
  - Child projects **pom** configuration
    - **ModuleOneDemoProject** `<dependencies>` section:
    ``` 
    <dependencies>
      <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      </dependency>
      <dependency>
      <groupId>org.javatuples</groupId>
      <artifactId>javatuples</artifactId>
      </dependency>
      <dependency>
      <groupId>com.blibli.oss.helpers</groupId>
      <artifactId>java-common-helper</artifactId>
      </dependency>
      </dependencies>
    ````
    - **ModuleTwoDemoProject** `<dependencies>` section:
    ``` 
     <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
        <dependency>
            <groupId>org.javatuples</groupId>
            <artifactId>javatuples</artifactId>
        </dependency>
        <dependency>
            <groupId>org.codehaus.plexus</groupId>
            <artifactId>plexus-component-annotations</artifactId>
        </dependency>
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>ModuleOneDemoProject</artifactId>
            <version>${revision}</version>
        </dependency>
    </dependencies>
    ````
  ## Adding plugins to projects using plugin management

- Parent **pom** configuration
  -  Added plugins to the pluginManagement section of the parent POM. `<pluginManagement>` tag is embedded in `<build>` tag.
  ```
    <pluginManagement>
            <plugins>
                <!--> Maven core plugin configuration, takes effect immediately <-->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.10.0</version>
                    <configuration>
                        <source>1.8</source>
                        <target>1.8</target>
                    </configuration>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>2.22.0</version>
                </plugin>
                <plugin>
                    <groupId>org.eclipse.jetty</groupId>
                    <artifactId>jetty-maven-plugin</artifactId>
                    <version>9.2.11.v20150529</version>
                </plugin>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                    <version>2.3.0.RELEASE</version>
                </plugin>
            </plugins>
        </pluginManagement>
  ```
  - Child projects **pom** configuration
    - **ModuleOneDemoProject** `<plugins>` section:
    ``` 
      <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
    ````
    - **ModuleTwoDemoProject** `<plugins>` section:
    ``` 
    <build>
        <plugins>
            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
    ````
  ## Building projects
- Projects can be built using command: ```mvn install```.
- If only tests need to be executed, command: ```mvn test``` can be used. **Clean** can be added before **install** or **test** to clear existing classes from previous compile.
- **jar** files for each child project are stored in target corresponding **target** project folders: ```MavenDemoProject\ModuleOneDemoProject\target``` and ```MavenDemoProject\ModuleTwoDemoProject\target```.
- **jar** files can also be found in Maven's local repository which is a directory on the local machine that stores all the project artifacts. Usually, this directory is named **.m2**.

## Upgrading project's version
- Upgraded version can be defined in parent **pom** file using the **<revision>** tag which is embedded in the **properties** tag:
```
<properties>
  <revision>DEMO-VERSION-1.0</revision>
  <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  <maven.compiler.source>17</maven.compiler.source>
  <maven.compiler.target>17</maven.compiler.target>
  </properties>
```
- In the **<parent>** tag of each child we use the specified version using the **revision** property:
```
    <parent>
        <artifactId>MavenDemoProject</artifactId>
        <groupId>org.example</groupId>
        <version>${revision}</version>
    </parent>
```
- Version can be automatically updated in parent and child projects **pom** files by using ```versions:set``` from the [versions-maven plugin](https://www.mojohaus.org/versions-maven-plugin/):
```
mvn versions:set -DnewVersion=DEMO-VERSION-1.0
```
- Changes can be reverted using command: ```mvn versions:revert``` or committed using ```mvn versions:commit```

  - **Note:** to dispense of generated backup poms the following command can be used ```mvn versions:set -DnewVersion=DEMO-VERSION-1.0 -DgenerateBackupPoms=false```
  
- After upgrading version and all projects are built with ```mvn install``` command, created **.jar** files will be renamed accordingly (example: ```ModuleOneDemoProject-1.0-SNAPSHOT.jar``` will be renamed to ```ModuleOneDemoProject-DEMO-VERSION-1.0.jar```) in the corresponding **target** folder of the child project.

  
     
