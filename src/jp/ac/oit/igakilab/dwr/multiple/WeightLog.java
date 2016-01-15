package jp.ac.oit.igakilab.dwr.multiple;

import java.util.Date;

/**
 * 日ごとの体重を記録するクラス
 * @author Hiroshi
 *
 */
public class WeightLog {
	private Date date;
	private double weight;

	public WeightLog(){}

	public WeightLog(Date d, double weight){
		this.date = d;
		this.weight = weight;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}


}
