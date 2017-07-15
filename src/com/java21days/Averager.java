package com.java21days;

class Averager{
    public static void main(String[] args) {
        int sum = 0;
        
        if(args.length > 0){
            for(int i = 0; i < args.length; i++){
                sum += Integer.parseInt(args[i]);
            }
            System.out.println("Suma wynosi: " + sum);
            System.out.println("Średnia wynosi: " + (float) sum / args.length);
        }
    }
}