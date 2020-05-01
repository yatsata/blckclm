package com.claim.blockchain.blckclm.utils;

import com.claim.blockchain.blckclm.model.HealthClaimRq;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ContractUtils {

    public static final String CONTRACT_EXECUTION_RESULT = "CONTRACT_EXECUTION_RESULT";

    public static void processRequestData(HealthClaimRq request){
        System.out.println(request.getSenderId());
    }

    public static String execContract(String contractName, String contractAddress){
        if (contractName == null || contractName ==""){
            System.out.println("Contract name can't be empty");
            return "";
        }
        String bashExecutable = "C:\\Program Files\\Git\\git-bash.exe";
        String blochChainConfigFolder = "d:/blokchain/helloworld";
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
            FileProcessingUtils.createJSFile(jsAbsPath, contractName, contractAddress, CONTRACT_EXECUTION_RESULT);
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
        try (InputStream input = new FileInputStream(outFileAbsPath)){
            Properties contractResult = new Properties();
            contractResult.load(input);
            result = contractResult.getProperty(CONTRACT_EXECUTION_RESULT);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
