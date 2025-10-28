# Introduction

TODO: Give a short introduction of your project. Let this section explain the objectives or the motivation behind this project.

# Getting Started

TODO: Guide users through getting your code up and running on their own system. In this section you can talk about:

1. Installation process
2. Software dependencies
3. Latest releases
4. API references

# Allure Installation in Windows

https://allurereport.org/docs/install-for-windows/

1. Open the Windows PowerShell
2. Enter the below commands one by one
   a. Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
   b. Invoke-RestMethod -Uri https://get.scoop.sh | Invoke-Expression
3. Once scope is installed in System , hit below command to install the Allure
   a. scoop install allure
4. Once install we can check the latest version using below command
   allure --version
5. Now Run the testcases in IDE,
6. After run check the allure-results folder should be created in project directory
7. Open the Terminal in IDE and hit the below command
   allure serve {Path where allure-result folder located in system}

# Build and Test

TODO: Describe and show how to build your code and run the tests.



# PlayWright Configuration


1.  Installation process  


   First install Node.js in your system using then only Playwright commands works

	https://nodejs.org/en/download/     

   In POM.xml file paste the below dependency       
   
   <!-- https://mvnrepository.com/artifact/com.microsoft.playwright/playwright -->
   ```xml
   <dependency>
       <groupId>com.microsoft.playwright</groupId>
       <artifactId>playwright</artifactId>
       <version>1.55.0</version>
   </dependency>
   ```
2.  Install playwright binaries       

   a. Using code :       
   
      Playwright pw = Playwright.create();       
      
      Browser browser= pw.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));       
      
      Page page = browser.newPage();       
   
   b. Using command line       
   
      mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"       
      
      Also you can see the more details on below link     
      https://playwright.dev/java/docs/next/browsers                                                    



3.  Generate Test cases or Record the script       
      mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen https://www.amazon.in/" 


4.  Debug the Playwright script in Playwright

     Open Edit Configuration 
     In Environment Variable , please this command **PWDEBUG=1**

5. Tracing the script and screen >> This is useful for check complete traceblity of scenario/testcase
    Need to provie this code first 
   context.tracing().start(new Tracing.StartOptions()
   .setScreenshots(true)
   .setSnapshots(true)
   .setSources(true));

    Stop Tracking 
    
    // Stop tracing and export it into a zip archive.
    context.tracing().stop(new Tracing.StopOptions()
    .setPath(Paths.get("trace.zip")));

    Once this complete, in folder structure a file with name trace.zip will be reated
    We can open the file on url https://trace.playwright.dev/

    We can also open this file on local with below command
   mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace trace.zip"

6. API references # Build and Test TODO: Describe and show how to build your code and run the tests. Contribute

TODO: Explain how other users and developers can contribute to make your code better.

If you want to learn more about creating good readme files then refer the following [guidelines](https://docs.microsoft.com/en-us/azure/devops/repos/git/create-a-readme?view=azure-devops). You can also seek inspiration from the below readme files:

* [ASP.NET Core](https://github.com/aspnet/Home)
* [Visual Studio Code](https://github.com/Microsoft/vscode)
* [Chakra Core](https://github.com/Microsoft/ChakraCore)
