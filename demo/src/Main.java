import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.thinq.TwilioWrapperLibrary;
import com.twilio.thinq.TwilioWrapperLibraryBuilder;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws TwilioRestException {
        String twilio_account_sid = "asdf1234_yourTwilioAcctSidHere";
        String twilio_account_token = "lkjh0987_yourTwilioAcctTokenHere";
        String thinQ_id = "numeric_thinQ_id";
        String thinQ_token = "qwerty1013_thinQ_token";
        String twiml_url = "http://demo.twilio.com/docs/voice.xml";
        String to_number = "19195551212";
        String from_number = "19195551212";

        TwilioWrapperLibrary library = new TwilioWrapperLibraryBuilder()
                // set twilio account sid, account token
                .twilio(twilio_account_sid, twilio_account_token)
                // set thinQ details
                .thinQ(thinQ_id, thinQ_token)
                // wrap and build the library
                .buildLibrary();
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Url", twiml_url));
        params.add(new BasicNameValuePair("To", to_number));
        params.add(new BasicNameValuePair("From", from_number));

        Call result = library.call(params); //return value is call sid if success, otherwise error message.
        System.out.println("result: " + result.getSid());
    }
}
