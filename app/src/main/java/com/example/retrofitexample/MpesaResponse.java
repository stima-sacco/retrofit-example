package com.example.retrofitexample;

public class MpesaResponse {
    private String MerchantRequestID;
    private String CheckoutRequestID;
    private String ResponseCode;
    private String ResponseDescription;
    private String CustomerMessage;

    public MpesaResponse(String MerchantRequestID, String CheckoutRequestID, String ResponseCode, String ResponseDescription, String CustomerMessage) {
        this.MerchantRequestID = MerchantRequestID;
        this.CheckoutRequestID = CheckoutRequestID;
        this.ResponseCode = ResponseCode;
        this.ResponseDescription = ResponseDescription;
        this.CustomerMessage = CustomerMessage;
    }

    public String getMerchantRequestID() {
        return MerchantRequestID;
    }

    public String getCheckoutRequestID() {
        return CheckoutRequestID;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public String getResponseDescription() {
        return ResponseDescription;
    }

    public String getCustomerMessage() {
        return CustomerMessage;
    }
}