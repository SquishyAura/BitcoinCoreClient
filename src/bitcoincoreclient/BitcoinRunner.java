/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitcoincoreclient;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author doalf
 */
public class BitcoinRunner {
    public static void main(String[] args) throws Exception {
        BitcoinCoreClient client = new BitcoinCoreClient();
        client.checkBalance();
        client.createNewAddress();
        
        List<String> params = new ArrayList<String>();
        params.add(client.newAddress); //address
        params.add("10"); //amount
        
        client.sendToAdress(params);
        
        client.checkBalance();
        
        List<Integer> paramss = new ArrayList<Integer>();
        paramss.add(0); //we use 0 as params to show unconfirmed transactions
        client.listUnspentTransactions(paramss);
    } 
    
    
}
