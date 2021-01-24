package TTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class test4 {
	static ArrayList<String> bg=new ArrayList<String>();
	static String ans ;
	static String[][] opc;
	static String go="https://quizzory.com/id/5fd750f938c1a909331a311f";

	public static void main(String [] args ) {

		Scanner sc = new Scanner(System.in);
		System.out.println("eneter url");
		go=sc.next();
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe"); 
		WebDriver driver=new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 30);


		driver.navigate().to(go);
		driver.manage().window().maximize();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"start-survey-button-text\"]")));
		driver.findElement(By.xpath("//*[@id=\"start-survey-button-text\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"user-info-1\"]")).sendKeys("xxxxxx");
		try { driver.findElement(By.xpath("//*[@id=\"user-info-2\"]")).sendKeys("xxxxxx");
		driver.findElement(By.xpath("//*[@id=\"user-info-3\"]")).sendKeys("xxxxxx");
		}catch(Exception e) {
			System.out.println("e:101");
		}
		driver.findElement(By.xpath("//*[@id=\"quiz-start\"]")).click();




		List<WebElement> questions =driver.findElements(By.className("card-action"));
		String [][] opt=new String[questions.size()][6];


		for(int j=0;j<questions.size();j++) {
			List<WebElement>options =questions.get(j).findElements(By.className("mdc-form-field"));
			int n=options.size();
			for(int i=0;i<n;i++)
				opt[j][i]=options.get(i).getText();
		}


		opc=opt;
		System.out.println("enter no of option");
		int n=sc.nextInt();
		driver.close();
		for(int i=0;i<n;i++) {
			//			vpn();
			trya(i);
		}
		System.out.println(ans);
		System.out.println(printA(bg));


	}


	static void trya (int n)  {
		
		System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe"); 
		WebDriver driver=new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		driver.navigate().to(go);
		driver.manage().window().maximize();
		String sd="";
		Scanner sc = new Scanner(System.in);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"start-survey-button-text\"]")));
		driver.findElement(By.xpath("//*[@id=\"start-survey-button-text\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"user-info-1\"]")).sendKeys("xxxxxx");
		try { driver.findElement(By.xpath("//*[@id=\"user-info-2\"]")).sendKeys("xxxxxx");
		driver.findElement(By.xpath("//*[@id=\"user-info-3\"]")).sendKeys("xxxxxx");
		}catch(Exception e) {
			System.out.println("e:101");
		}
		driver.findElement(By.xpath("//*[@id=\"quiz-start\"]")).click();


		for(int i=0;i<opc.length;i++) {


			if(opc[i][n]==null)continue;
			System.out.println(opc[i][n]);

			try{
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='"+opc[i][n]+"']")));
				WebElement ele =driver.findElement(By.xpath("//*[text()='"+opc[i][n]+"']"));

				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].click()", ele);
//				System.out.println("is clicked");
//				sc.next();
			}catch(Exception e) {sd="\n "+opc[i][n];continue;
			}
		}
		
		
		List<WebElement> m =driver.findElements(By.className("answer-text-field"));
		for(int i=0;i<m.size();i++) {
			m.get(i).sendKeys("xxx");
		}
		
		
		
		if(!sd.equals("")){
			System.out.println("Select" +sd+"\nand enter Y");
			sc.next();
			}

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"submission\"]")));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()",driver.findElement(By.xpath("//*[@id=\"submission\"]")));
		
		if(driver.findElement(By.xpath("//*[@id=\"submission\"]")).isDisplayed()) {
			System.out.println("select");
			for(int i=0;i<opc.length;i++) {
				if(opc[i][n]==null)continue;
				try{
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='"+opc[i][n]+"']")));
					WebElement ele =driver.findElement(By.xpath("//*[text()='"+opc[i][n]+"']"));

//					JavascriptExecutor jse = (JavascriptExecutor)driver;
					jse.executeScript("arguments[0].click()", ele);
//					System.out.println("is clicked");
//					sc.next();
				}catch(Exception e) {sd="\n "+opc[i][n];continue;
				}
			}
				
			
		}
//		check(driver, n, sc);
		
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/p")));
		List<WebElement> s=driver.findElements(By.className("response-question-card"));
		System.out.println(s.size());
		for(int i=0;i<s.size();i++) {
			System.out.println(s.size());
			String d=s.get(i).findElement(By.className("response-text")).getAttribute("style");

			if(d.contentEquals("color: green;")) {
				String hg = s.get(i).findElement(By.className("response-question-title")).getText()+" : "+
						s.get(i).findElement(By.className("response-text")).getText()+"\n";
				System.out.println(hg);
				bg.add(s.get(i).findElement(By.className("response-text")).getText());
				ans+=hg;
			}
		}
		
	}

	static String printA(ArrayList<String>s){
		String g="";
		for(int i=0;i<s.size();i++) {
			g+=s.get(i)+" ";
		}
		return g;
	}


	static void vpn() {
		System.out.println("Change vpn");
		Scanner sc = new Scanner(System.in);
		sc.next();

	}
	static void check(WebDriver driver,int n,Scanner sc) {
		if(driver.findElement(By.xpath("//*[@id=\"submission\"]")).isDisplayed()) {
			System.out.println("select");
			for(int i=0;i<opc.length;i++) {
				if(opc[i][n]==null)continue;
				try{
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='"+opc[i][n]+"']")));
					WebElement ele =driver.findElement(By.xpath("//*[text()='"+opc[i][n]+"']"));

					JavascriptExecutor jse = (JavascriptExecutor)driver;
					jse.executeScript("arguments[0].click()", ele);
//					System.out.println("is clicked");
//					sc.next();
				}catch(Exception e) {sd="\n "+opc[i][n];continue;
				}
			}
				
			
		}
	}
}


 
