package com.example.retrofitexample;

public class TransactionResponse {
    private String message;
    private String resultCode;
    private String TransactionDate;
    private String MobileNo;

    public TransactionResponse(String message, String resultCode, String TransactionDate, String MobileNo) {
        this.message = message;
        this.resultCode = resultCode;
        this.TransactionDate = TransactionDate;
        this.MobileNo = MobileNo;
    }

    public String getMessage() {
        return message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getTransactionDate() {
        return TransactionDate;
    }

    public String getMobileNo() {
        return MobileNo;
    }

}