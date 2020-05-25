pragma solidity >= 0.5.0 < 0.7.0;
pragma experimental ABIEncoderV2;

contract HealthCLMEval {
    string constant _researcherId = "10000002";
    string constant _insuredId = "20000001";
    string constant _examinationTopic = "HLT_TST";
    string constant _examinationSubTopic = "SEX_DIS";
    string constant examinationSummaryPositive = "TRUE";
    string constant clmEvaluation = "{ \"claimEvaluation\":";
    string constant forPayment = " \"Payment approved\",";
    string constant noPayment = " \"Payment rejected\", \"reason\": ";
    string constant medIdResMismatch = "\"medicalResearcherId no in trusted medical research id list\" }";
    string constant noClient = "\"Insured not client of the company\" }";
    string constant noTopic = "\"Examination is not a topic of this contract\" }";
    string constant positiveSexRes = "\"No payment for positive result on positive results for SEX_DIS\" }";

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

    function evaluate(string memory medicalResearcherId, string memory insurerId,  string memory insuredId,
                      string memory insuredBankAccount, string memory examinationId, string memory examinationTopic,
                      string memory examinationSubTopic, string memory examinationPrice,
                      string memory examinationSummary) public returns(string memory){
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

        if(keccak256(abi.encodePacked(recordData.medicalResearcherId)) != keccak256(abi.encodePacked(_researcherId))){
            return string(abi.encodePacked(clmEvaluation, noPayment, medIdResMismatch));
        }
        if(keccak256(abi.encodePacked(recordData.insuredId)) != keccak256(abi.encodePacked(_insuredId))){
            return string(abi.encodePacked(clmEvaluation, noPayment, noClient));
        }
        if(keccak256(abi.encodePacked(recordData.examinationTopic)) != keccak256(abi.encodePacked(_examinationTopic))){
            return string(abi.encodePacked(clmEvaluation, noPayment, noTopic));
        }
        if(keccak256(abi.encodePacked(recordData.examinationSubTopic)) == keccak256(abi.encodePacked(_examinationSubTopic)) &&
        keccak256(abi.encodePacked(recordData.examinationSummary)) == keccak256(abi.encodePacked(examinationSummaryPositive))){
            return string(abi.encodePacked(clmEvaluation, noPayment, positiveSexRes));
        }
        string memory toAccount = string(abi.encodePacked("\"toAccount\": ", "\"", recordData.insuredBankAccount, "\" }"));
        return string(abi.encodePacked(clmEvaluation, forPayment, toAccount));
    }

    event LogResponse(string response);

}
