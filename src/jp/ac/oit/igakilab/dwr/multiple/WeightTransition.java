package jp.ac.oit.igakilab.dwr.multiple;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeightTransition {

	//ToDo 引数をDateにして，そのDateから7日前の分ソートして取ってくる処理（データが無い日はDate以外のすべての値を0にする)
	public List<WeightLog> execute(Date date){
		List<WeightLog> wlogOrig = this.makeTestData();

		return wlogOrig;
	}

	/**
	 * テストデータ作成用
	 * @return
	 */
	private List<WeightLog> makeTestData(){
		Calendar cal = Calendar.getInstance();
		cal.set(2015, 9,11);//2015/10/11
		Date d = cal.getTime();
		WeightLog w = new WeightLog(d,60,170.0);

		List<WeightLog> wlog = new ArrayList<WeightLog>();
		wlog.add(w);

		cal.set(2015, 9,12);//2015/10/12
		wlog.add(new WeightLog(cal.getTime(),62,171.1));

		cal.set(2015, 9,13);//2015/10/13
		wlog.add(new WeightLog(cal.getTime(),64,172.5));

		cal.set(2015, 9,14);//2015/10/14
		wlog.add(new WeightLog(cal.getTime(),66,172.7));

		cal.set(2015, 9,18);//2015/10/18
		wlog.add(new WeightLog(cal.getTime(),67,173.2));

		cal.set(2015, 9,19);//2015/10/19
		wlog.add(new WeightLog(cal.getTime(),69,173.3));

		return wlog;
	}

}
