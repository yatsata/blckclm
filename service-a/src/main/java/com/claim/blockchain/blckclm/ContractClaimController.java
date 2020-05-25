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
    private static final String separator = ", ";

    @POST
    @Path("/claim")
    @Consumes("application/json")
    @Produces("application/json")
    public Map getClaimResult(HealthClaimRq request, @QueryParam(value="callerId") String callerId,
                              @QueryParam(value="contractId") String contractId) {
        ContractUtils.processRequestData(request);
        Map map = new HashMap<>();
        String contractArguments = request.getMedicalResearcherId() + separator + request.getInsurerId() +
                                   request.getInsuredId() + separator + request.getInsuredBankAccount() +
                                   request.getExaminationId() + separator + request.getExaminationTopic() +
                                   request.getExaminationSubTopic() + separator + request.getExaminationPrice() +
                                   request.getExaminationSummary();
        map.put(ContractUtils.CONTRACT_EXECUTION_RESULT, ContractUtils.execContract(contractId, callerId,
                                                                                    callerId, contractArguments));
        return map;
    }

    public static void main(String[] args) {
        System.out.println(ContractUtils.CONTRACT_EXECUTION_RESULT + ":" +
                           ContractUtils.execContract("HealthCLMEval",
                                       "0x8b19202841E6C3ebf56c805634FaBcA04823F796",
                                              "0xa70A7F1D913E6d039e33CfD99f7be6007a75b5Fd",
                                    "10000002, 2, 3, 4, 5, 6, 7, 8, 9"));
    }

}