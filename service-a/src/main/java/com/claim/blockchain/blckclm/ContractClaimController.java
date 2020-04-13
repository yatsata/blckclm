package com.claim.blockchain.blckclm;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 */
@Path("/contract-result")
@Singleton
public class ContractClaimController {

    private static final String CONTRACT_EXECUTION_RESULT = "CONTRACT_EXECUTION_RESULT";

    @GET
    @Path("/claim")
    @Produces("application/json")
    public Map getClaimResult() {
        Map map = new HashMap<>();
        map.put(CONTRACT_EXECUTION_RESULT, getContractResponse());
        return map;
    }

    public static void main(String[] args) {
        System.out.println(CONTRACT_EXECUTION_RESULT + ":" + getContractResponse());
    }

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
            createJSFile(jsAbsPath, contractName, contractAddress);
            createSHFile(shAbsPath, jsAbsPath, outFileAbsPath, blochChainConfigFolder);
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
        deleteFiles(shAbsPath, outFileAbsPath, jsAbsPath);
        return result;
    }

    private static void deleteFiles(String shAbsPath, String outFileAbsPath, String jsAbsPath) {
        try {
            Files.deleteIfExists(Paths.get(shAbsPath));
            Files.deleteIfExists(Paths.get(outFileAbsPath));
            Files.deleteIfExists(Paths.get(jsAbsPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createSHFile(String shFile, String jsFile, String outputFile, String blochChainConfigFolder){
        FileWriter writer = null;
        try {
            writer = new FileWriter(shFile, true);
            writer.write("cd " + blochChainConfigFolder +"\n");
            jsFile = jsFile.replaceAll("\\\\","/");
            outputFile = outputFile.replaceAll("\\\\","/");
            writer.write("truffle exec " + jsFile +" | tee " + outputFile);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createJSFile(String jsFile, String contractName, String contractAddress){
        FileWriter writer = null;
        try {
            writer = new FileWriter(jsFile, true);
            writer.write("var myContract = artifacts.require('" + contractName + "');\n");
            writer.write("var contract_address = '" + contractAddress + "';\n");
            writer.write("module.exports = function() {\n");
            writer.write("  async function getContractResponse() {\n");
            writer.write("    let ins = await myContract.at(contract_address);\n");
            writer.write("    let res = await ins.getMessage();\n");
            writer.write("    console.log(\"" + CONTRACT_EXECUTION_RESULT + ":\" + res.toString())\n");
            writer.write("  }\n");
            writer.write("  getContractResponse();\n");
            writer.write("}\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}