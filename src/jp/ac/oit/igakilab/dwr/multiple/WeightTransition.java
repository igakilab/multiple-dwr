package jp.ac.oit.igakilab.dwr.multiple;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeightTransition {

	//ToDo 引数をDateにして，そのDateから7日前の分ソートして取ってくる処理（データが無い日はDate以外のすべての値を0にする)
	public List<WeightLog> execute(Date date){
		System.out.println(date);
		List<WeightLog> wlogOrig = this.makeTestData();
		List<WeightLog> weekWlog = new ArrayList<WeightLog>();

		Calendar calInput = Calendar.getInstance();
		calInput.setTime(date);

		//特定の日のデータが存在するかどうか
		boolean existsData = false;

		//dateから7日遡ってwlogOrigから値を取得する
		for(int i=0;i<7;i++){
			for(WeightLog w:wlogOrig){
				if(this.compareDate(calInput.getTime(), w.getDate())){
					weekWlog.add(w);
					existsData = true;
					break;
				}
			}
			//その日のデータが存在しなかった（一致するデータがwlogOrigに存在しなかった場合）
			if(!existsData){
				WeightLog w = new WeightLog(calInput.getTime(),0,0);
				weekWlog.add(w);
			}else existsData = false;
			calInput.add(Calendar.DATE, -1);
		}

		//Java8のラムダ式使ったsort：dateの昇順
		//参考：http://ttlg.hateblo.jp/entry/2015/02/08/181600
		weekWlog.sort((a,b)-> a.getDate().compareTo(b.getDate()));
		return weekWlog;
	}

	/**
	 * 特定の日が空いている場合に，空(0)のデータを保管するメソッド
	 * 10/13のデータが無い場合に，10/13のデータを値0で作成し，保管する
	 * @return
	 */
	private List<WeightLog> supplementWeekWeightLog(){
		return null;
	}

	/**
	 * inputDateとlogDateのyear, month, dayを比較し，一致する場合にtrueを返すメソッド
	 * @param inputDate
	 * @param logDate
	 * @return
	 */
	boolean compareDate(Date inputDate, Date logDate){
		Calendar calInput = Calendar.getInstance();
		calInput.setTime(inputDate);
		int iYear,iMonth,iDay;

		iYear = calInput.get(Calendar.YEAR);
	    iMonth = calInput.get(Calendar.MONTH);
	    iDay = calInput.get(Calendar.DAY_OF_MONTH);

		Calendar calLog = Calendar.getInstance();
		calLog.setTime(logDate);
		int lYear, lMonth, lDay;

		lYear = calLog.get(Calendar.YEAR);
	    lMonth = calLog.get(Calendar.MONTH);
	    lDay = calLog.get(Calendar.DAY_OF_MONTH);

	    if(iYear==lYear && iMonth==lMonth && iDay==lDay) return true;
	    else return false;
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

		cal.set(2015, 9,14);//2015/10/14
		wlog.add(new WeightLog(cal.getTime(),66,172.7));

		cal.set(2015, 9,12);//2015/10/12
		wlog.add(new WeightLog(cal.getTime(),62,171.1));

		cal.set(2015, 9,13);//2015/10/13
		wlog.add(new WeightLog(cal.getTime(),64,172.5));

		cal.set(2015, 9,18);//2015/10/18
		wlog.add(new WeightLog(cal.getTime(),67,173.2));

		cal.set(2015, 9,19);//2015/10/19
		wlog.add(new WeightLog(cal.getTime(),69,173.3));

		return wlog;
	}
}
