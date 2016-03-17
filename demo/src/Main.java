import com.twilio.thinq.TwilioWrapperLibrary;
import com.twilio.thinq.TwilioWrapperLibraryBuilder;


public class Main {
    public static void main(String[] agrgs){
        TwilioWrapperLibrary library = new TwilioWrapperLibraryBuilder()
                // set twilio account sid, account token
                .twilio("ACa5a21802beff96f147d40bf98c957038", "7852c807435af28d468344ca57a49d2a")
                // set thinQ details
                .thinQ("11001", "0c82a54f22f775a3ed8b97b2dea74036")
                // wrap and build the library
                .buildLibrary();
        String result = library.call("19193365890", "19192334784"); //return value is call sid if success, otherwise error message.
        System.out.println("result: " + result);
    }
}
