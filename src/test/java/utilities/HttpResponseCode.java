package utilities;
import org.openqa.selenium.WebDriver;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.given;

public class HttpResponseCode {

	public HttpResponseCode(WebDriver driver) {

	}
	
	public int checkHttpResponseCode(String url) {
        Response response =
                given().get(url)
                        .then().extract().response();
 
        return response.getStatusCode();
    }
 
}
