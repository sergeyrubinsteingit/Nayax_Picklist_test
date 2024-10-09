// Include the chrome driver
    require("chromedriver");
    require("selenium-webdriver")
    // Include selenium webdriver
    let webdriver = require("selenium-webdriver");
    var By = require("selenium-webdriver").By;
    let browser = new webdriver.Builder();
    let driver = browser.forBrowser("chrome").build();
     
    driver.get('https://lambdatest.github.io/sample-todo-app/');
     
    driver.findElement(By.name('li1')).click();
    driver.findElement(By.name('li2')).click();
    driver.findElement(By.id("sampletodotext")).sendKeys("New item");
    driver.findElement(By.id("addbutton")).click();
     
    driver.takeScreenshot().then(
        function(image) {
            require('fs').writeFileSync('captured_image_3.png', image, 'base64');
        }
    );
     
    driver.quit();/**
 * 
 */