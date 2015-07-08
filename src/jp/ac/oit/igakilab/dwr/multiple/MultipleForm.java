package jp.ac.oit.igakilab.dwr.multiple;


/**
 * JavaScriptから渡されるオブジェクト．DWRを利用してJavaScriptからフォームをオブジェクトとして渡す場合，下記を満たしている必要がある．
 * 1. すべてのフィールドがprivateであり，publicなsetter/getterを持っている
 * 2. publicなデフォルトコンストラクタ（引数を持たないコンストラクタ）が存在する
 * 3. クラスがpublicである
 * @author Hiroshi
 *
 */
public class MultipleForm{
    private int max = 20;
    private int multiple = 3;

    public MultipleForm(){
    }

    public void setMax(int max){
        this.max = max;
    }

    public void setMultiple(int multiple){
        this.multiple = multiple;
    }

    public int getMax(){
        return max;
    }

    public int getMultiple(){
        return multiple;
    }
}
