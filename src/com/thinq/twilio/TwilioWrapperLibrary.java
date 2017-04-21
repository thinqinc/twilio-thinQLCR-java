package com.thinq.twilio;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Call;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Objects;

public class TwilioWrapperLibrary {

    private static final String THINQ_DOMAIN = "wap.thinq.com";

    private TwilioRestClient client;
    private String twilio_account_sid;
    private String twilio_account_token;
    private String thinQ_id;
    private String thinQ_token;


    public TwilioWrapperLibrary(){

    }

    /**
     * Initialize Twilio Wrapper Object
     * @param twilio_account_sid    twilio account sid
     * @param twilio_account_token  twilio account token
     * @param thinQ_id      thinQid received when signed up at thinQ website
     * @param thinQ_token   thinQtoken received when signed up at thinQ website
     */

    public TwilioWrapperLibrary(String twilio_account_sid, String twilio_account_token, String thinQ_id, String thinQ_token) {

        this.twilio_account_sid = twilio_account_sid;
        this.twilio_account_token = twilio_account_token;
        this.thinQ_id = thinQ_id;
        this.thinQ_token = thinQ_token;

        this.client = new TwilioRestClient(this.twilio_account_sid, this.twilio_account_token);
    }

    /**
     * Check if the twilio client is initialized properly.
     */
    public boolean isClientValid(){
        return this.client != null && this.client.getAccount() != null;
    }

    /**
     * Initiate a call to the customer
     * pass through of ArrayList of NameValuePairs that Twilio object expects
     */

    public Call call(ArrayList<NameValuePair> params) throws TwilioRestException {
        CallFactory callFactory = this.client.getAccount().getCallFactory();
        Call call;

        // ensure the To field is properly formatted
        String to_number;
        ArrayList<NameValuePair> twilioparams = new ArrayList<NameValuePair>();
        for (NameValuePair temp : params) {
            if (Objects.equals(temp.getName(), "To")) {
                to_number = temp.getValue().startsWith("sip:") ? temp.getValue() : "sip:" + temp.getValue() + "@" + THINQ_DOMAIN + "?thinQid=" + thinQ_id + "&thinQtoken=" + thinQ_token;
                twilioparams.add(new BasicNameValuePair("To", to_number));
            } else {
                twilioparams.add(new BasicNameValuePair(temp.getName(), temp.getValue()));
            }
        }

        call = callFactory.create(twilioparams);
        return call;
    }
}
