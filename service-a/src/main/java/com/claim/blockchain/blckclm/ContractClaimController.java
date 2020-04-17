package com.claim.blockchain.blckclm;

import com.claim.blockchain.blckclm.utils.ContractUtils;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Path("/contract-result")
@Singleton
public class ContractClaimController {


    @GET
    @Path("/claim")
    @Produces("application/json")
    public Map getClaimResult() {
        Map map = new HashMap<>();
        map.put(ContractUtils.CONTRACT_EXECUTION_RESULT, ContractUtils.getContractResponse());
        return map;
    }

    public static void main(String[] args) {
        System.out.println(ContractUtils.CONTRACT_EXECUTION_RESULT + ":" + ContractUtils.getContractResponse());
    }

}