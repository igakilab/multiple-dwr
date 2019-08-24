package jp.igakilab.dwr.multiple;

/**
 * DWRでJSから呼ばれるメソッドはすべてpublicでなければならない．また，必要なクラスはすべてdwr.xmlに定義されている必要がある．
 *
 * @author IGAKI
 *
 */
public class MultiplePrinter {
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
