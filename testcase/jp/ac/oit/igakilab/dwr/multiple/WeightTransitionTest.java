package jp.ac.oit.igakilab.dwr.multiple;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;

public class WeightTransitionTest {

	@Test
	public void testExecute() {
		WeightTransition wt = new WeightTransition();
		Calendar cal = Calendar.getInstance();
		cal.set(2015, 9,11);//2015/10/11

		List<WeightLog> wlog = wt.execute(cal.getTime());
		for(WeightLog w:wlog){
			System.out.println(w.getDate());
		}
		assertSame(wlog.size(),7);
	}

}
