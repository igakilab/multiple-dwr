package jp.igakilab.dwr.multiple;

import java.util.ArrayList;

/**
 * DWRでJSから呼ばれるメソッドはすべてpublicでなければならない．また，必要なクラスはすべてdwr.xmlに定義されている必要がある．
 *
 * @author IGAKI
 *
 */
public class MultiplePrinter {
  /**
   * 1からmaxまでのすべての数値について， 以下の条件を満たしながら小さい順にリスト化して返す
   * 条件1：数値がmultipleの倍数のときは”ryokun”をリストに追加する．
   *
   * @return 1から10までの数値を変換した文字列のリスト
   */
  public ArrayList<String> execute(MultipleForm input) throws InvalidValueException {
    ArrayList<String> list = new ArrayList<>();
    int max = input.getMax();
    int multiple = input.getMultiple();

    if (multiple <= 0 || max < 0) {
      throw new InvalidValueException("倍数及び最大値は 正の整数(>0)でなければいけません．");
    }
    for (int i = 1; i <= max; i++) {
      if (i % multiple == 0) {
        list.add("ryokun");
      } else {
        list.add(Integer.toString(i));
      }
    }
    return list;
  }

  /**
   * REST呼び出しを行う場合は下記のように呼ぶ
   * http://sample.com:8080/project_name/dwr/jsonp/ClassName/MethodName/param1/
   * と指定する
   * http://localhost:8080/multiple-dwr/dwr/jsonp/MultiplePrinter/helloWorld/ryokun/
   *
   * @param name
   * @return nameに":HelloWorld"を付与したものを返す
   */
  public String helloWorld(String name) {
    return name + ":HelloWorld";

  }
}
