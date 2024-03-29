# Test automation framework



##### Run tests
All tests:
```bash
mvn -DsuiteXmlFile=defaultSuite.xml test
```

##### Launch management commands
Clean maven:
```bash
mvn clean
```
View Allure reports:
```bash
mvn allure:serve
```

##### About Builder

Please note that after saving the builder, its parameters will be saved and remain static without your intervention. To use different parameters, save different builders. For example:
```
    private final BoardBuilder firstBoardBody = BoardBuilder.builder().build();
    private final BoardBuilder secondBoardBody = BoardBuilder.builder().build();
```

You can also try to change one parameter
```
        String changedName = "Mura";
        String changedStatus = "Changed";

        BoardBuilder boardBody = BoardBuilder.builder().status(changedStatus).name(changedName).build();
```
# Maven Profiles Usage Guide for Multiple Browsers

##### Run tests on different Multiple Browsers

By default, will use Chrome, but you can use the browser that you need, putting the name of the browser instead of ```chrome``` 

All tests:
```bash
mvn -DsuiteXmlFile=defaultSuite.xml -P chrome test
```
```Don't use like in this screenshot it is incorrect, you can use only selected one of them.```

![Don't use like in this screenshot it is incorrect, you can use only selected one of them.](src/main/resources/mavenProfile.png)
