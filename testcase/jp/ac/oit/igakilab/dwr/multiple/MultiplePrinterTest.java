package jp.ac.oit.igakilab.dwr.multiple;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MultiplePrinterTest {

	@Test
	public void testExecute() throws InvalidValueException {
		MultiplePrinter mp = new MultiplePrinter();
		MultipleForm input = new MultipleForm();

		//大きさが5で倍数が3のリストを作成
		List<String> listExpected = new ArrayList<String>();
		listExpected = Arrays.asList("1","2","ryokun","4","5");

		//対象クラスに大きさが5で倍数が3のリストを作成してもらい，格納する
		input.setMultiple(3);
		input.setMax(5);
		List<String> listActual = mp.execute(input);


		//listExpectedとlistActualに共通する要素を持つlistCommonを作成する
		List<String> listCommon = new ArrayList<String>();
		for(String exElement:listExpected){
			for(String actElement:listActual){
				if(exElement.equals(actElement)){
					listCommon.add(actElement);
				}
			}
		}

		//listExpectedのサイズとlistCommonのサイズが等しければOK
		assertSame(listExpected.size(),listCommon.size());
	}

	@SuppressWarnings("unused")
	@Test(expected = InvalidValueException.class)
	public void testExecute2() throws InvalidValueException {
		MultiplePrinter mp = new MultiplePrinter();
		MultipleForm input = new MultipleForm();
		input.setMultiple(0);
		input.setMax(5);
		List<String> listActual = mp.execute(input);
		fail("例外が投げられなかった");

	}

	@Test
	public void testHelloWorld() {
		MultiplePrinter mp = new MultiplePrinter();
		assertEquals(mp.helloWorld("ryokun"),"ryokun:HelloWorld");

	}

}
