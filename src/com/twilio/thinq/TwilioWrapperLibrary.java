package com.twilio.thinq;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Call;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class TwilioWrapperLibrary {

    private static final String THINQ_DOMAIN = "wap.thinq.com";
    private static final String TWIML_RESOURCE_URL = "http://demo.twilio.com/docs/voice.xml";

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
     * @param from      'from' number
     * @param to        'to' number
     * @return Call sid or error string
     */
    public String call(String from, String to){
        if(!this.isClientValid()) {
            return "Invalid Twilio Account details.";
        }

        // Build a filter for the CallList
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Url", this.TWIML_RESOURCE_URL));
        params.add(new BasicNameValuePair("To", "sip:" + to + "@" + this.THINQ_DOMAIN + "?thinQid=" + this.thinQ_id + "&thinQtoken=" + this.thinQ_token));
        params.add(new BasicNameValuePair("From", from));

        CallFactory callFactory = client.getAccount().getCallFactory();
        Call call;
        try {
            call = callFactory.create(params);
            return call.getSid();
        } catch (TwilioRestException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
