# sportsbook ui-automation framework

BDD-Framework using Java for programming language, Selenium for automation framework in addition with Cucumber 7 for
feature files and Gherkin for BDD language, and Junit 4 for test annotations and assertions.

Dependencies:

- [cucumber-java](https://mvnrepository.com/artifact/io.cucumber/cucumber-java)
- [cucumber-junit](https://mvnrepository.com/artifact/io.cucumber/cucumber-junit)
- [selenium-java](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)
- [webdrivermanager](https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager)
- [cucumber-core](https://mvnrepository.com/artifact/io.cucumber/cucumber-core)
-

Plugin:

- [maven-compiler-plugin](https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-compiler-plugin)

# Local  Environment Setup

This documents details how to set-up a local environment for QA Engineers

## Universal environment preparation

### Prerequisites

This should be done after onboarding process has been completed, you should have

- Working Android emulator with Fubo sportsbook app able to be launched

#### Installing Java and Java Plugins

1. Install [Java JDK 8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
2. Install [IntelliJ](https://www.jetbrains.com/idea/download/#section=mac)
3. Open IntelliJ, in the top left corner click IntelliJ IDEA > Prefrences > Plugins
4. Install two additional plugins Cucumber for Java, Gherkin, Kotlin

#### Cross browser testing (Optional)

The default browser for automation
is [Google Chrome](https://www.google.com/chrome/?brand=YTUH&geo=US&gclid=CjwKCAjw7--KBhAMEiwAxfpkWArw7h3Fuo9UXIiwgcidahv7mW9lY_CyDKWA72MvHGj42bU6UiTBDhoCbFoQAvD_BwE&gclsrc=aw.ds)
so you will need to have Chrome installed. For cross browser testing you will need to install the following browsers as
well

- [Firefix](https://www.mozilla.org/en-US/firefox/new/)
- [Edge](https://www.microsoft.com/en-us/edge?brand=M022&OCID=AID2200279_SEM_CjwKCAjw7--KBhAMEiwAxfpkWKR2yMJIA7F8QFsaR8Is9IDoFFYtv_VO2wH8LwhAD0bmilZ-P6CzmhoC-mgQAvD_BwE:G:s&ef_id=CjwKCAjw7--KBhAMEiwAxfpkWKR2yMJIA7F8QFsaR8Is9IDoFFYtv_VO2wH8LwhAD0bmilZ-P6CzmhoC-mgQAvD_BwE:G:s&gclid=CjwKCAjw7--KBhAMEiwAxfpkWKR2yMJIA7F8QFsaR8Is9IDoFFYtv_VO2wH8LwhAD0bmilZ-P6CzmhoC-mgQAvD_BwE)
- [Safari](https://support.apple.com/downloads/safari)
- [Chrome](https://chromeenterprise.google/browser/download/?utm_source=adwords&utm_medium=cpc&utm_campaign=2022-H2-chromebrowser-paidmed-paiddisplay-other-chromebrowserent&utm_term=downloadnow-chrome-browser-download&utm_content=GCEJ&brand=GCEJ&gclid=CjwKCAjwpqCZBhAbEiwAa7pXecKMOMDU2ksfHusUWvj56RosV9mj_d8s_rGuyIC2Sg8-x5DfMIFSHBoCYnoQAvD_BwE&gclsrc=aw.ds#windows-tab)


