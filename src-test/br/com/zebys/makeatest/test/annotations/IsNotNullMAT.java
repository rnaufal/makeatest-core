package br.com.zebys.makeatest.test.annotations;

public class IsNotNullMAT {

	public boolean make(Object object){
		if(object == null){
			return false;
		}
		return true;
	}
	
}
