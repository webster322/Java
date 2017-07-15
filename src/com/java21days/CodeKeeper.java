package com.java21days;

import java.util.*;

public class CodeKeeper{
    ArrayList list;
    String[] codes = { "alfa", "lambda", "gamma", "delta", "zeta" };
    public CodeKeeper(String[] userCodes){
        list = new ArrayList();
        for(int i = 0; i < codes.length; i++){
            addCode(codes[i]);
        }
        
        for(int j = 0; j < userCodes.length; j++){
            addCode(codes[j]);
        }
        
        for(Iterator ite = list.iterator(); ite.hasNext(); ){
            String output = (String) ite.next();
            System.out.println(output);
        }
    }
    
    private void addCode(String code){
        if(!list.contains(code)){
            list.add(code);
        }
    }
    
    public static void main(String[] args) {
        CodeKeeper keeper = new CodeKeeper(args);
    }
}