package com.wiley.cfireader.utils;

public class BrowserStackTest {
/*
    public WebDriver driver;
    public static ThreadLocal<String> environment = new ThreadLocal<>() ;
    public static String build = null;
    private Local l;
    public static ThreadLocal<String> scenario = new ThreadLocal<>();

    public synchronized void setUp(String environment) throws Exception {
        JSONParser parser = new JSONParser();
        //JSONObject config = (JSONObject) parser.parse(new FileReader(KeyMapper.STACK_CONF));
        JSONObject envs = (JSONObject) config.get("environments");
        build = System.getenv("BROWSERSTACK_BUILD_NAME");
        if(build == null ){
            build = System.getProperty("BROWSERSTACK_BUILD_NAME");
        }
        DesiredCapabilities capabilities = new DesiredCapabilities();

        Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (capabilities.getCapability(pair.getKey().toString()) == null) {
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
                capabilities.setCapability("resolution", "1920x1080");
                capabilities.setCapability("name", scenario.get() );
                capabilities.setCapability("build", build);
            }
        }

        String username = System.getenv("BROWSERSTACK_USERNAME");
        if (username == null) {
            username = (String) config.get("user");
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = (String) config.get("key");
        }

        if (capabilities.getCapability("browserstack.local") != null
                && capabilities.getCapability("browserstack.local") == "true") {
            l = new Local();
            Map<String, String> options = new HashMap<String, String>();
            options.put("key", accessKey);
            l.start(options);
        }

        driver = new RemoteWebDriver(
                new URL("http://" + username + ":" + accessKey + "@" + config.get("server") + "/wd/hub"), capabilities);
        WebDriverRunner.setWebDriver(driver);
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        if (l != null) {
            l.stop();
        }
    }*/
}

