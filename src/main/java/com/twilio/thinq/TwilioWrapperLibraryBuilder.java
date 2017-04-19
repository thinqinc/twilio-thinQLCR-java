package com.twilio.thinq;

public class TwilioWrapperLibraryBuilder {
    private String twilio_account_sid;
    private String twilio_account_token;
    private String thinQ_id;
    private String thinQ_token;


    public TwilioWrapperLibraryBuilder() { }

    /**
     * Wrap the library and return the library object.
     * @return
     */

    public TwilioWrapperLibrary buildLibrary()
    {
        return new TwilioWrapperLibrary( this.twilio_account_sid, this.twilio_account_token, this.thinQ_id, this.thinQ_token);
    }

    /**
     * Set customer phone number
     * @param _id   thinQid received when signed up at thinQ website
     * @param _token   thinQtoken received when signed up at thinQ website
     * @return
     */

    public TwilioWrapperLibraryBuilder thinQ(String _id, String _token)
    {
        this.thinQ_id = _id;
        this.thinQ_token = _token;
        return this;
    }

    /**
     * Set twilio acount details
     * @param _sid  twilio account sid
     * @param _token    twilio account token
     * @return
     */

    public TwilioWrapperLibraryBuilder twilio(String _sid, String _token)
    {
        this.twilio_account_sid = _sid;
        this.twilio_account_token = _token;
        return this;
    }
}
