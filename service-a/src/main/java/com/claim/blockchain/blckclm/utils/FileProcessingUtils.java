package com.claim.blockchain.blckclm.utils;

import java.io.FileWriter;
import java.io.IOException;

public class FileProcessingUtils {

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

    public static void createJSFile(String jsFile, String contractName, String contractAddress,
                                    String contractArguments, String result){
        FileWriter writer = null;
        try {
            writer = new FileWriter(jsFile, true);
            writer.write("var myContract = artifacts.require('" + contractName + "');\n");
            writer.write("var contract_address = '" + contractAddress + "';\n");
            writer.write("module.exports = function() {\n");
            writer.write("  async function getContractResponse() {\n");
            writer.write("    let ins = await myContract.at(contract_address);\n");
            writer.write("    let res = await ins.evaluate(" + contractArguments +  ");\n");
            writer.write("    console.log(\"" + result + ":\" + res.toString())\n");
            writer.write("  }\n");
            writer.write("  getContractResponse();\n");
            writer.write("}\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
