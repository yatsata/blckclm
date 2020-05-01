package com.claim.blockchain.blckclm;

import com.claim.blockchain.blckclm.model.HealthClaimRq;
import com.claim.blockchain.blckclm.utils.ContractUtils;

import javax.inject.Singleton;
import javax.ws.rs.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Path("/contract-result")
@Singleton
public class ContractClaimController {

    private static final String testContract = "HealthCLMEval";
    private static final String testContractAddress = "0x8b19202841E6C3ebf56c805634FaBcA04823F796";
    private static final String separator = ", ";

    @POST
    @Path("/claim")
    @Consumes("application/json")
    @Produces("application/json")
    public Map getClaimResult(HealthClaimRq request) {
        ContractUtils.processRequestData(request);
        Map map = new HashMap<>();
        String contractArguments = request.getMedicalResearcherId() + separator + request.getInsurerId() +
                                   request.getInsuredId() + separator + request.getInsuredBankAccount() +
                                   request.getExaminationId() + separator + request.getExaminationTopic() +
                                   request.getExaminationSubTopic() + separator + request.getExaminationPrice() +
                                   request.getExaminationSummary();
        map.put(ContractUtils.CONTRACT_EXECUTION_RESULT, ContractUtils.execContract(testContract, testContractAddress,
                                                                                    contractArguments));
        return map;
    }

    public static void main(String[] args) {
        System.out.println(ContractUtils.CONTRACT_EXECUTION_RESULT + ":" +
                           ContractUtils.execContract(testContract, testContractAddress, "1, 2, 3, 4, 5, 6, 7, 8, 9"));
    }

}