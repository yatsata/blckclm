package com.claim.blockchain.blckclm.model;

import java.util.Map;

public class HealthClaimRq {

    private String senderId;
    private String insurerId;
    private String insuredId;
    private String examinationSummary;
    private Map<String, String> results;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(String insurerId) {
        this.insurerId = insurerId;
    }

    public String getInsuredId() {
        return insuredId;
    }

    public void setInsuredId(String insuredId) {
        this.insuredId = insuredId;
    }

    public String getExaminationSummary() {
        return examinationSummary;
    }

    public void setExaminationSummary(String examinationSummary) {
        this.examinationSummary = examinationSummary;
    }

    public Map<String, String> getResults() {
        return results;
    }

    public void setResults(Map<String, String> results) {
        this.results = results;
    }
}
