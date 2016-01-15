package jp.ac.oit.igakilab.dwr.multiple;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class WeightTransition {

	public ArrayList<WeightLog> execute(){
		ArrayList<WeightLog> wlogOrig = this.makeTestData();

		return wlogOrig;
	}

	/**
	 * テストデータ作成用
	 * @return
	 */
	private ArrayList<WeightLog> makeTestData(){
		Calendar cal = Calendar.getInstance();
		cal.set(2015, 9,11);//2015/10/11
		Date d = cal.getTime();
		WeightLog w = new WeightLog(d,60.5);

		ArrayList<WeightLog> wlog = new ArrayList<WeightLog>();
		wlog.add(w);

		cal.set(2015, 9,12);//2015/10/12
		wlog.add(new WeightLog(cal.getTime(),62.1));

		cal.set(2015, 9,13);//2015/10/13
		wlog.add(new WeightLog(cal.getTime(),64.3));

		cal.set(2015, 9,14);//2015/10/14
		wlog.add(new WeightLog(cal.getTime(),66.2));

		cal.set(2015, 9,18);//2015/10/18
		wlog.add(new WeightLog(cal.getTime(),67.1));

		cal.set(2015, 9,19);//2015/10/19
		wlog.add(new WeightLog(cal.getTime(),69.0));

		return wlog;
	}

}
