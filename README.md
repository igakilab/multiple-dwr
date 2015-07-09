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
 * WEB-INF以下にweb.xmlとdwr.xmlの2つのファイルを作成する
 * 参考：https://github.com/igakilab/multiple-dwr/tree/master/WebContent/WEB-INF/
 * web.xmlはdwrを利用するための設定(tomcat用)で，dwrの利用（デバッグモード付き）とREST呼び出しのための設定が記述されている．dwr.xmlはJavaのメソッドやオブジェクトをDWRに認識させるための設定になる．
