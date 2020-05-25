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
    public String getClaimResult(HealthClaimRq request,
                              @QueryParam(value="callerId") String callerId,
                              @QueryParam(value="contractName") String contractName,
                              @QueryParam(value="contractAddress") String contractAddress) {
        ContractUtils.processRequestData(request);
        String gas = "50894";
        String contractArguments = request.getMedicalResearcherId() + separator + request.getInsurerId() +
                                   request.getInsuredId() + separator + request.getInsuredBankAccount() +
                                   request.getExaminationId() + separator + request.getExaminationTopic() +
                                   request.getExaminationSubTopic() + separator + request.getExaminationPrice() +
                                   request.getExaminationSummary();
        return ContractUtils.execContract(contractName, contractAddress,callerId, contractArguments, gas);
    }

    public static void main(String[] args) {
        System.out.println(ContractUtils.execContract("HealthCLMEval",
                                       "0xB9569aaA9eb1b13a9053f844da60002b624B3568",
                                              "0xa70A7F1D913E6d039e33CfD99f7be6007a75b5Fd",
                                                 "\"10000002\", \"2\", \"20000001\", \"468895565\", \"5\", \"HLT_TST\", \"SEX_DIS\", \"8\", \"9\"",
                                                        "50894"));
    }

}