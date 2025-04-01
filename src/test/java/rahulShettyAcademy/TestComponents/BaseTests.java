public class BaseTests {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initialiseDriver() throws IOException {

		// System.setProperty("webdriver.chrome.driver",
		// "/Users/sowmy/Downloads/chromedriver-win64/chromedriver.exe");

		// Using properties file under main/java/resources/GlobalData.properties

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//rahulShettyAcademy//Resources//GlobalData.properties");

		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		// browser=chrome is set in GlobalData.properties under Resources package

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions(); // This is to run the chrome in headless
					
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900)); //run in full screen
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			
			driver = new FirefoxDriver();
			// firefox
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// reading json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to HashMap

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = (new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"));

		FileUtils.copyFile(source, file);
		return (System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initialiseDriver();
		if (driver != null) {
			landingPage = new LandingPage(driver);
			landingPage.goTo();
			return landingPage;
		}
		// Handle the case when driver initialization fails
		return null;

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}