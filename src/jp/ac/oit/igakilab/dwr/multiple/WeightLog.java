package jp.ac.oit.igakilab.dwr.multiple;

import java.util.Date;

/**
 * 日ごとの体重を記録するクラス
 * @author Hiroshi
 *
 */
public class WeightLog {
	private Date date;
	private int weight;
	private double height;

	/**
	 * デフォルトコンストラクタ
	 * 引数付きコンストラクタがある場合は必須
	 */
	public WeightLog(){}

	public WeightLog(Date d, int weight, double height){
		this.date = d;
		this.weight = weight;
		this.height = height;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
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
	public void setWeight(int weight) {
		this.weight = weight;
	}


}
