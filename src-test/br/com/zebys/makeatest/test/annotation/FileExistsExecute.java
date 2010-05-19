package br.com.zebys.makeatest.test.annotation;

import br.com.zebys.makeatest.MakeATestExecuteInterface;

public class FileExistsExecute implements MakeATestExecuteInterface {

	@Override
	public void execute() {
		System.out.println("Execute file exists.");

	}

}
