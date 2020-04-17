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


    @POST
    @Path("/claim")
    @Consumes("application/json")
    @Produces("application/json")
    public Map getClaimResult(HealthClaimRq request) {
        ContractUtils.processRequestData(request);
        Map map = new HashMap<>();
        map.put(ContractUtils.CONTRACT_EXECUTION_RESULT, ContractUtils.execContract());
        return map;
    }

    public static void main(String[] args) {
        System.out.println(ContractUtils.CONTRACT_EXECUTION_RESULT + ":" + ContractUtils.execContract());
    }

}