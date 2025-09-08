# Introduction
TODO: Give a short introduction of your project. Let this section explain the objectives or the motivation behind this project.

# Getting Started
TODO: Guide users through getting your code up and running on their own system. In this section you can talk about:
1.	Installation process
2.	Software dependencies
3.	Latest releases
4.	API references

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

# Contribute
TODO: Explain how other users and developers can contribute to make your code better.

If you want to learn more about creating good readme files then refer the following [guidelines](https://docs.microsoft.com/en-us/azure/devops/repos/git/create-a-readme?view=azure-devops). You can also seek inspiration from the below readme files:
- [ASP.NET Core](https://github.com/aspnet/Home)
- [Visual Studio Code](https://github.com/Microsoft/vscode)
- [Chakra Core](https://github.com/Microsoft/ChakraCore)