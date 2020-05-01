package com.claim.blockchain.blckclm.model;

import java.util.Map;

public class HealthClaimRq {

    private String medicalResearcherId;
    private String insurerId;
    private String insuredId;
    private String insuredBankAccount;
    private String examinationId;
    private String examinationTopic;
    private String examinationSubTopic;
    private String examinationPrice;
    private String examinationSummary;
    private Map<String, String> results;

    public String getMedicalResearcherId() { return medicalResearcherId; }

    public void setMedicalResearcherId(String medicalResearcherId) { this.medicalResearcherId = medicalResearcherId; }

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

    public String getInsuredBankAccount() { return insuredBankAccount; }

    public void setInsuredBankAccount(String insuredBankAccount) { this.insuredBankAccount = insuredBankAccount; }

    public String getExaminationId() { return examinationId; }

    public void setExaminationId(String examinationId) { this.examinationId = examinationId; }

    public String getExaminationSummary() {
        return examinationSummary;
    }

    public void setExaminationSummary(String examinationSummary) {
        this.examinationSummary = examinationSummary;
    }

    public String getExaminationTopic() { return examinationTopic; }

    public void setExaminationTopic(String examinationTopic) { this.examinationTopic = examinationTopic; }

    public String getExaminationSubTopic() {  return examinationSubTopic; }

    public void setExaminationSubTopic(String examinationSubTopic) { this.examinationSubTopic = examinationSubTopic; }

    public String getExaminationPrice() {
        return examinationPrice;
    }

    public void setExaminationPrice(String examinationPrice) {
        this.examinationPrice = examinationPrice;
    }

    public Map<String, String> getResults() {
        return results;
    }

    public void setResults(Map<String, String> results) {
        this.results = results;
    }
}
