#multiple-dwr
このリポジトリで学習できること

1. DWRを利用してREST形式でJavaのメソッドを呼ぶ方法
2. JavaScriptからDWR(Direct Web Remoting)を利用してJavaのメソッドを呼ぶ方法
3. jUnitを用いた単体テストの作成
4. antを用いたテスト・ビルドの方法

本プロジェクトを一から作成する手順を示し，上記の解説を行う．

##準備
* mergedocからeclipse Pleiades All in One 4.5 Java 64bit Full Editionをダウンロードし，下記ディレクトリ構成になるように展開する(ant buildとtomcatの利用方法説明の関係)．
  * c:\pleiades4.5\eclipse, java, tomcat
* Windowsの環境変数設定
  * コンピュータを右クリック->プロパティ(あるいはコントロールパネルの「システム」）->システムの詳細設定->環境変数
  * 環境変数でユーザ/システム環境変数のどちらかに，変数名：JAVA_HOME，変数値：C:\pleiades4.5\java\7 (要はJDKのbinがあるディレクトリの一つ上）を指定する
同じく変数名：JRE_HOME, 変数値：C:\pleiades4.5\java\7\jre (JREのbinがあるディレクトリの一つ上)を指定する．
* eclipseの設定
  * ソースコードがJDK1.7準拠で書かれているので，1.7を明示的に下記のように指定する．
    * ウィンドウ->設定->コンパイラー->コンパイラー準拠レベルを1.7に変更．下の方にインストール済みJREが．．とか警告がでるので，そこでもJRE7を選択する．フルビルドはしてもしなくても良い．
* eclipseプロジェクトの作成
  * 今回作成するeclipseプロジェクトをeclipseのファイル->新規->Javaプロジェクト
  * プロジェクト名を「multiple-dwr」それ以外はデフォルトのまま完了

## DWRを利用してREST形式でJavaのメソッドを呼ぶ方法
DWRを利用することでHTTP/GETの形式でJavaのメソッドを呼ぶことができる．返り値はjsonになる．
* multiple-dwrプロジェクトのsrcフォルダにパッケージを作成する（例：jp.ac.oit.igakilab.dwr.multiple）
* 作成したパッケージ内にMultiplePrinterクラスを作成する．
* MultiplePrinterクラス内に下記helloWorldメソッドを作成する
 * https://github.com/igakilab/multiple-dwr/blob/master/src/jp/ac/oit/igakilab/dwr/multiple/MultiplePrinter.java#L81
* multiple-dwrにWebContentフォルダを作成する．
 * WebContentフォルダ以下にjsフォルダとWEB-INFフォルダを作成する．
 * WEB-INFフォルダ以下にlibフォルダを作成する
 * libに下記jarファイルを入れておく
 * https://github.com/igakilab/multiple-dwr/tree/master/WebContent/WEB-INF/lib
 * WEB-INF以下にweb.xmlとdwr.xmlの2つのファイルを下記内容で作成する

#### web.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE web-app
    PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
    "http://java.sun.com/dtd/web-app_2_3.dtd">
<web-app>
  <servlet>
    <servlet-name>dwr-invoker</servlet-name>
    <servlet-class>
      org.directwebremoting.servlet.DwrServlet
    </servlet-class>
    <init-param>
      <param-name>debug</param-name>
      <param-value>true</param-value>
    </init-param>
   <init-param>
     <param-name>jsonpEnabled</param-name>
     <param-value>true</param-value>
    </init-param>
  </servlet>
  <servlet-mapping>
    <servlet-name>dwr-invoker</servlet-name>
    <url-pattern>/dwr/*</url-pattern>
  </servlet-mapping>
</web-app>
```

#### dwr.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE dwr PUBLIC
    "-//GetAhead Limited//DTD Direct Web Remoting 3.0//EN"
    "http://getahead.org/dwr//dwr30.dtd">
<dwr>
  <allow>
    <create creator="new" javascript="MultiplePrinter">
      <param name="class" value="jp.ac.oit.igakilab.dwr.multiple.MultiplePrinter"/>
    </create>
  </allow>
</dwr>
```
* web.xmlはdwrを利用するための設定(tomcat用)で，dwrの利用（デバッグモード付き）とREST呼び出しのための設定が記述されている．dwr.xmlはJavaのメソッドやオブジェクトをDWRに認識させるための設定になる．
* 参考：https://github.com/igakilab/multiple-dwr/tree/master/WebContent/WEB-INF/ 
* multiple-dwrプロジェクトにbuild.xml, build-windows.xmlを作成し，下記の記述をコピーしておく．
 * https://github.com/igakilab/multiple-dwr/blob/master/build.xml
 * https://github.com/igakilab/multiple-dwr/blob/master/build-windows.xml
* build-windows.xmlにはtomcatとjreのホームディレクトリが絶対パスで指定されている．自分のディレクトリ構造と対応づいてるか確認すること．
* build.xmlを右クリック->実行->Ant Build(2つ並んでるもののうえのほう）を選択
* buildファイルに従って，コンパイルしてwarファイルが作成され，tomcatのwebappsディレクトリに配置される．
 * tomcatはver.7の利用を想定している．
* tomcatのbinディレクトリ内のstartup.batを実行->tomcatが起動し，multiple-dwr.warが配備（デプロイ）される．
* 正常にtomcatが起動したのを確認後，「http://localhost:8080/multiple-dwr/dwr/jsonp/MultiplePrinter/helloWorld/ryokun/」にアクセス
* ブラウザに「{ "reply":"ryokun:HelloWorld"}」と表示されたら成功．
