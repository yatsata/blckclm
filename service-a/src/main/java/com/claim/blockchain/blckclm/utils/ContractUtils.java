package com.claim.blockchain.blckclm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ContractUtils {

    public static final String CONTRACT_EXECUTION_RESULT = "CONTRACT_EXECUTION_RESULT";

    public static String getContractResponse(){
        String bashExecutable = "C:\\Program Files\\Git\\git-bash.exe";
        String blochChainConfigFolder = "d:/blokchain/helloworld";
        String shAbsPath = "";
        String outFileAbsPath = "";
        String jsAbsPath = "";
        String contractName = "HelloWorld";
        String contractAddress = "0x5DD5e5CFf778058fd45D848d36F02d1b8560d7D7";
        String tempFolder = "D:\\blokchain_temp\\temp";
        java.nio.file.Path tempPath = Paths.get(tempFolder);
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
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        String result = "";
        try (InputStream input = new FileInputStream(outFileAbsPath)){
            Properties contractResult = new Properties();
            contractResult.load(input);
            result = contractResult.getProperty(CONTRACT_EXECUTION_RESULT);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        FileProcessingUtils.deleteFiles(shAbsPath, outFileAbsPath, jsAbsPath);
        return result;
    }
}
