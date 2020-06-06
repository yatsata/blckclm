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
    private static final String quote = "\"";

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
        String contractArguments = quote+request.getMedicalResearcherId()+quote+separator+
                                   quote+request.getInsurerId()+quote+separator+
                                   quote+request.getInsuredId()+quote+separator+
                                   quote+request.getInsuredBankAccount()+quote+separator+
                                   quote+request.getExaminationId()+quote+separator+
                                   quote+request.getExaminationTopic()+quote+separator+
                                   quote+request.getExaminationSubTopic()+quote+separator+
                                   quote+request.getExaminationPrice()+quote+separator+
                                   quote+request.getExaminationSummary()+quote;
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