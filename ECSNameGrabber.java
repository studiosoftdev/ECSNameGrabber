import org.jsoup.Jsoup; //jsoup library used [1]
import org.jsoup.nodes.Document;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Program that retrieves person's name from their email address.
 * Uses the intranet to access the information, signs in with provided cookie
 */

public class ECSNameGrabber {
	public static void main(String[] args) throws IOException{
		//get email
		System.out.println("type email: ");
		String emailBase = System.console().readLine();

		//get the actual base of the email by splitting it by the @ and taking the first part of the array created
		emailBase = emailBase.split("@")[0];
		String url = "http://secure.ecs.soton.ac.uk/people/" + emailBase; //intranet is way less bloated than the main website so I'd rather put a little work in to access the intranet than combing through the garbage of the main site
		
		//use jsoup to get the html and find the email address within
		Document doc = Jsoup.connect(url).cookie("cookie name", "cookie value").get(); //cookie begins with "shib"
		
		//find the name - first sign is on the title in the html
		String name = doc.title(); //gets title [2]
		name = name.substring(6, name.length()); //removes the "ECS - " before the name in the title
		System.out.println(name);
	}
}

//[1] - Jsoup, used for getting and scanning the html from a website: https://jsoup.org/
//[2] - Gets the title of the html document: https://jsoup.org/apidocs/org/jsoup/nodes/Document.html#title()
