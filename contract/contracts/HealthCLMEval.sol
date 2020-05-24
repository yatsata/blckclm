pragma solidity >= 0.5.0 < 0.7.0;
pragma experimental ABIEncoderV2;

contract HealthCLMEval {
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

    function evaluate(string memory medicalResearcherId,string memory insurerId,  string memory insuredId, string memory insuredBankAccount,
        string memory examinationId, string memory examinationTopic, string memory examinationSubTopic, string memory examinationPrice,
        string memory examinationSummary)public returns(string memory){
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
        string memory rs = process(rq);
        emit LogResponse(rs);
        return rs;
    }

    function process(HealthRq memory recordData) public returns(string memory) {
        string memory researcherId = "10000002";
        string memory insuredId = "20000001";
        string memory examinationTopic = "HLT_TST";
        string memory examinationSubTopic = "SEX_DIS";
        string memory examinationSummaryPositive = "TRUE";
        if(keccak256(abi.encodePacked(recordData.medicalResearcherId)) != keccak256(abi.encodePacked(researcherId))){
            return "medicalResearcherId no in trusted medialc reseach id list";
        }
        if(keccak256(abi.encodePacked(recordData.insuredId)) != keccak256(abi.encodePacked(insuredId))){
            return "Insured not client of the company";
        }
        if(keccak256(abi.encodePacked(recordData.examinationTopic)) != keccak256(abi.encodePacked(examinationTopic))){
            return "Examination is not a topic of this contrat";
        }
        if(keccak256(abi.encodePacked(recordData.examinationSubTopic)) == keccak256(abi.encodePacked(examinationSubTopic)) &&
        keccak256(abi.encodePacked(recordData.examinationSummary)) == keccak256(abi.encodePacked(examinationSummaryPositive))){
            return "No payment for positive result on positive results for SEX_DIS";
        }
        string memory response = "Claim_Evaluation: FOR_PAYMENT";
        string memory toAccount = string(abi.encodePacked("To_Account", recordData.insuredBankAccount));
        return string(abi.encodePacked(response, toAccount));
    }

    event LogResponse(string response);

}
