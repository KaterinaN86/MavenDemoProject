Simple java Maven "Hello world" project
==========================================================================
## Parent `MavenDemoProject` with two child projects, `ModuleOneDemoProject` and `ModuleTwoDemoProject` 

- Declared method `getAppName()` in class `ModuleOneApp` of child project `ModuleOneDemoProject` so this method can be called in **main** method of class `ModuleTwoApp` in child project `ModuleTwoDemoProject`.

- Parent project contains `App` class whose **main** method prints **Hello world!** in console.

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