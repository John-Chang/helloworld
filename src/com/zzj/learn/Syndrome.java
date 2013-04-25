package com.zzj.learn;

public class Syndrome {
	public int symptom() {
		int sum = 0; // never used
		try{
			int a = 100;
			int b = 99;
			if(a > b){
				while(a < b){ //can't be executed
					throw new Exception("should not be here");
				}
			}else{ // if statement is always true
				System.out.println("result is: " /*+ result*/);
			}
		}catch(Exception e){
		}
		finally{
			// return should not be in finally block
			return 1;
		}
	}
}
