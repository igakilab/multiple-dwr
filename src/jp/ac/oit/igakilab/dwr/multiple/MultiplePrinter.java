package jp.ac.oit.igakilab.dwr.multiple;

import java.util.ArrayList;
import java.util.List;

public class MultiplePrinter {

//    public static void main(String[] args) throws InvalidValueException {
//        CloudSpiralPrinter csp = new CloudSpiralPrinter();
//        CSPInput input = new CSPInput();
//        List<String> list = csp.execute(input);
//        System.out.println(list);
//    }


    /**
     * 1から10までのすべての数値について，
     * 以下の条件を満たしながら小さい順にリスト化して返す
     *   条件1：数値が3の倍数のときは”ryokun”をリストに追加する．
     * @return 1から10までの数値を変換した文字列のリスト
     */
    public List<String> execute(MultipleForm input) throws InvalidValueException {
        List<String> list = new ArrayList<>();
        int max = input.getMax();
        int multiple = input.getMultiple();


        if (multiple < 0 || max < 0){
        	throw new InvalidValueException("倍数は 正の整数(>0)でなければいけません．現在の値：" + multiple);
        }
        for(int i= 1; i<=max; i++){
        	if(i % multiple == 0){
        		list.add("ryokun");
        	}else{
        		list.add(Integer.toString(i));
        	}
        }

        return list;
    }

    /**
     * REST呼び出しを想定．
     * http://sample.com:8080/project_name/dwr/jsonp/ClassName/MethodName/param1/param2/ と指定する
     * 呼び出し例： http://localhost:8080/multiple-dwr/dwr/jsonp/MultiplePrinter/executeJson/ryokun/3/
     * @param msg multipleで指定された倍数のときに入れ替える文字列
     * @param multiple 倍数 (3の倍数とか5の倍数を指定する） >0 であるかをチェックし例外を投げる
     * @return multipleで指定された倍数の数字をmsgと入れ替えた1~10までのリストを返す
     * @throws InvalidValueException
     */
    public List<String> executeJson(String msg, int multiple) throws InvalidValueException {
        List<String> list = new ArrayList<>();


        if (multiple < 0){
        	throw new InvalidValueException("倍数は 正の整数(>0)でなければいけません．現在の値：" + multiple);
        }
        for(int i= 1; i<=10; i++){
        	if(i % multiple == 0){
        		list.add(msg);
        	}else{
        		list.add(Integer.toString(i));
        	}
        }

        return list;
    }

    /**
     * REST呼び出しを行う場合は下記のように呼ぶ
     * http://sample.com:8080/project_name/dwr/jsonp/ClassName/MethodName/param1/ と指定する
     * http://localhost:8080/multiple-dwr/dwr/jsonp/MultiplePrinter/helloWorld/ryokun/
     * @param name
     * @return nameに":HelloWorld"を付与したものを返す
     */
    public String helloWorld(String name){
    	return name + ":HelloWorld";

    }
}
