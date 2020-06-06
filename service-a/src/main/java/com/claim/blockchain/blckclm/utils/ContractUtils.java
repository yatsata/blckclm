package com.claim.blockchain.blckclm.utils;

import com.claim.blockchain.blckclm.model.HealthClaimRq;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ContractUtils {

    public static void processRequestData(HealthClaimRq request){
        System.out.println(request.getMedicalResearcherId());
    }

    public static String execContract(String contractName, String contractAddress,
                                      String callerId, String contractArguments, String gas){
        if (contractName == null || contractName ==""){
            System.out.println("contractName can't be empty");
            return "";
        }
        if (callerId == null || callerId ==""){
            System.out.println("callerId can't be empty");
            return "";
        }
        String bashExecutable = "C:\\Program Files\\Git\\git-bash.exe";
        String blochChainConfigFolder = "C:/Users/PC.PC-PC/IdeaProjects/blckclm/contract";
        String shAbsPath = "";
        String outFileAbsPath = "";
        String jsAbsPath = "";
        String tempFolder = "D:\\blokchain_temp\\temp";
        java.nio.file.Path tempPath = Paths.get(tempFolder);
        String result = "";
        try {
            java.nio.file.Path shFile = Files.createTempFile(tempPath,"sh", ".sh");
            shAbsPath = shFile.toFile().getAbsolutePath();
            java.nio.file.Path outFile = Files.createTempFile(tempPath,"out", ".properties");
            java.nio.file.Path jsFile = Files.createTempFile(tempPath,"js", ".js");
            outFileAbsPath = outFile.toFile().getAbsolutePath();
            jsAbsPath = jsFile.toFile().getAbsolutePath();
            FileProcessingUtils.createJSFile(jsAbsPath, contractName, contractAddress, callerId,
                                             contractArguments, gas);
            FileProcessingUtils.createSHFile(shAbsPath, jsAbsPath, outFileAbsPath, blochChainConfigFolder);
            Process process = Runtime.getRuntime().exec(bashExecutable+ " " + shAbsPath);
            process.waitFor();
            result = getContractResponse(outFileAbsPath);
            Files.deleteIfExists(Paths.get(shAbsPath));
            Files.deleteIfExists(Paths.get(jsAbsPath));
            Files.deleteIfExists(Paths.get(outFileAbsPath));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getContractResponse(String outFileAbsPath){
        String result = "";
        try {
            result = new String(Files.readAllBytes(Paths.get(outFileAbsPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
