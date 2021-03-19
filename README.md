# scp-api
Security Check Platform Backend APIs

## 数据库配置
* 版本 5.7.27
* docker 本地安装 默认 -local.yml
```docker
docker run --name mysql -e MYSQL_ROOT_PASSWORD=12345678 -d -i -p 3306:3306 --restart=always  mysql:5.7.27
```

## Developing with VSCode
### Extensions
* Java Extension Pack
* Spring Boot Extension Pack
* Lombok Annotations Support for VS Code
* YAML

### Project Settings
Open this project with VSCode, following files will be generated.

#### .project
```xml
<?xml version="1.0" encoding="UTF-8"?>
<projectDescription>
	<name>scp-api</name>
	<comment>Project scp-api created by Buildship.</comment>
	<projects>
	</projects>
	<buildSpec>
		<buildCommand>
			<name>org.eclipse.jdt.core.javabuilder</name>
			<arguments>
			</arguments>
		</buildCommand>
		<buildCommand>
			<name>org.eclipse.buildship.core.gradleprojectbuilder</name>
			<arguments>
			</arguments>
		</buildCommand>
	</buildSpec>
	<natures>
		<nature>org.eclipse.jdt.core.javanature</nature>
		<nature>org.eclipse.buildship.core.gradleprojectnature</nature>
	</natures>
	<filteredResources>
		<filter>
			<id>1613445744066</id>
			<name></name>
			<type>30</type>
			<matcher>
				<id>org.eclipse.core.resources.regexFilterMatcher</id>
				<arguments>node_modules|.git|__CREATED_BY_JAVA_LANGUAGE_SERVER__</arguments>
			</matcher>
		</filter>
	</filteredResources>
</projectDescription>
```

#### .classpath
You need modify some settings in '.classpath' file because 'gradle build' command will genereate class files in 'build' folder but not 'bin' folder. Otherwise, the debug launcher cannot found the classes.

* origin
```xml
<?xml version="1.0" encoding="UTF-8"?>
<classpath>
	<classpathentry kind="src" output="bin/main" path="src/main/java">
		<attributes>
			<attribute name="gradle_scope" value="main"/>
			<attribute name="gradle_used_by_scope" value="main,test"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="src" output="bin/main" path="src/main/resources">
		<attributes>
			<attribute name="gradle_scope" value="main"/>
			<attribute name="gradle_used_by_scope" value="main,test"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="src" output="bin/test" path="src/test/java">
		<attributes>
			<attribute name="gradle_scope" value="test"/>
			<attribute name="gradle_used_by_scope" value="test"/>
			<attribute name="test" value="true"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="src" output="bin/test" path="src/test/resources">
		<attributes>
			<attribute name="gradle_scope" value="test"/>
			<attribute name="gradle_used_by_scope" value="test"/>
			<attribute name="test" value="true"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-11/"/>
	<classpathentry kind="con" path="org.eclipse.buildship.core.gradleclasspathcontainer"/>
	<classpathentry kind="output" path="bin/default"/>
</classpath>
```

* modified
```xml
<?xml version="1.0" encoding="UTF-8"?>
<classpath>
	<classpathentry kind="src" output="build/classes/java/main" path="src/main/java">
		<attributes>
			<attribute name="gradle_scope" value="main"/>
			<attribute name="gradle_used_by_scope" value="main,test"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="src" output="build/resources/java/main" path="src/main/resources">
		<attributes>
			<attribute name="gradle_scope" value="main"/>
			<attribute name="gradle_used_by_scope" value="main,test"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="src" output="build/classes/java/test" path="src/test/java">
		<attributes>
			<attribute name="gradle_scope" value="test"/>
			<attribute name="gradle_used_by_scope" value="test"/>
			<attribute name="test" value="true"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="src" output="build/resources/java/test" path="src/test/resources">
		<attributes>
			<attribute name="gradle_scope" value="test"/>
			<attribute name="gradle_used_by_scope" value="test"/>
			<attribute name="test" value="true"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-11/"/>
	<classpathentry kind="con" path="org.eclipse.buildship.core.gradleclasspathcontainer"/>
</classpath>
```

#### .settings/org.eclipse.buildship.core.prefs
```property
arguments=
auto.sync=false
build.scans.enabled=false
connection.gradle.distribution=GRADLE_DISTRIBUTION(WRAPPER)
connection.project.dir=
eclipse.preferences.version=1
gradle.user.home=
java.home=/Library/Java/JavaVirtualMachines/jdk-11.0.9.jdk/Contents/Home
jvm.arguments=
offline.mode=false
override.workspace.settings=true
show.console.view=true
show.executions.view=true
```

### Debug Settings
#### .vscode/launch.json
```json
{
  // Use IntelliSense to learn about possible attributes.
  // Hover to view descriptions of existing attributes.
  // For more information, visit: https://go.microsoft.com/fwlink/?linkid=830387
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Launch ScpApiApplication",
      "request": "launch",
      "mainClass": "com.thoughtworks.security.scpapi.ScpApiApplication",
      "projectName": "scp-api",
      "args": [
        "-Djwt.secret.string=jwtsecret",
        "-Dsaml.cer.path=./",
        "--spring.profiles.active=local"
      ]
    }
  ]
}
```
#### .vscode/settings.json
```json
{
  "java.configuration.updateBuildConfiguration": "automatic"
}
```