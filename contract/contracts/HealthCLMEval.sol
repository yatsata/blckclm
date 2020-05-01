pragma solidity >= 0.5.0 < 0.7.0;
pragma experimental ABIEncoderV2;

contract HealthCLMEval {
  constructor() public {
  }
   struct HealthRq{
        string medicalResearcherId;
        string insurerId;
        string insuredId;
        string insuredBankAccount;
        string examinationId;
        string examinationTopic;
        string examinationSubTopic;
        string examinationPrice;
        string examinationSummary;
    }

    function evaluate(string memory medicalResearcherId, string memory insurerId, string memory insuredId,
        string memory insuredBankAccount, string memory examinationId, string memory examinationTopic,
        string memory examinationSubTopic, string memory examinationPrice,
        string memory examinationSummary)public pure returns(string memory){
        HealthRq memory rq;
        rq.medicalResearcherId = medicalResearcherId;
        rq.insurerId = insurerId;
        rq.insuredId = insuredId;
        rq.insuredBankAccount = insuredBankAccount;
        rq.examinationSummary = examinationSummary;
        rq.examinationPrice = examinationPrice;
        rq.examinationTopic = examinationTopic;
        rq.examinationSubTopic = examinationSubTopic;
        rq.examinationId = examinationId;
        return process(rq);
    }

    function process(HealthRq memory recordData) public pure returns(string memory) {
        return recordData.insuredId;
    }
}
