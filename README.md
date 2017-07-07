# multiple-dwr

このリポジトリで学習できること

1. DWR(Direct Web Remoting)を利用してREST形式でJavaのメソッドを呼ぶ方法
   * http://directwebremoting.org/dwr/index.html
2. JavaScriptからDWR(Direct Web Remoting)を利用してJavaのメソッドを呼ぶ方法
3. jUnitを用いた単体テストの作成
4. antを用いたビルドやテストの実行方法
5. Google Chart APIを利用してDWRから取得したデータをグラフで表示する方法
   * URLの引数（クエリパラメータ）を利用する方法
   * Date型の引数を利用してJavaのメソッドを呼び出す方法
   * ArrayListのsortをラムダ式で行う方法
6. PostgresqlとMybatisを使ってDBを利用するWebアプリケーションを開発してみる

本プロジェクトを一から作成する手順を示し，上記の解説を行う．

## 準備
* mergedocからeclipse Pleiades All in One 4.5 Java 64bit Full Editionをダウンロードし，下記ディレクトリ構成になるように展開する(ant buildとtomcatの利用方法説明の関係)．
  * c:\pleiades\eclipse, java, tomcat
    * 任意のバージョンにするため，peiadesのバージョン番号をフォルダ名から削除
* Windowsの環境変数設定
  * コンピュータを右クリック->プロパティ(あるいはコントロールパネルの「システム」）->システムの詳細設定->環境変数
  * 環境変数でユーザ/システム環境変数のどちらかに，変数名：JAVA_HOME，変数値：C:\pleiades\java\8 (要はJDKのbinがあるディレクトリの一つ上）を指定する
同じく変数名：JRE_HOME, 変数値：C:\pleiades\java\8\jre (JREのbinがあるディレクトリの一つ上)を指定する．
* eclipseの設定
  * JDK1.8を明示的に下記のように指定する．
    * ウィンドウ->設定->コンパイラー->コンパイラー準拠レベルを1.8に変更．下の方にインストール済みJREが．．とか警告がでるので，そこでもJRE8を選択する．フルビルドはしてもしなくても良い．
* eclipseプロジェクトの作成
  * 今回作成するeclipseプロジェクトをeclipseのファイル->新規->Javaプロジェクト
  * プロジェクト名を「multiple-dwr」それ以外はデフォルトのまま完了

## DWRを利用してREST形式でJavaのメソッドを呼ぶ方法
DWRを利用することでHTTP/GETの形式でJavaのメソッドを呼ぶことができる．返り値はjsonになる．
* multiple-dwrプロジェクトのsrcフォルダにパッケージを作成する（例：jp.ac.oit.igakilab.dwr.multiple）
* 作成したパッケージ内にMultiplePrinterクラスを作成する．
* MultiplePrinterクラス内に下記helloWorldメソッドを作成する(helloWorldメソッド以外を作成するとコンパイルできないので注意すること）
 * https://github.com/igakilab/multiple-dwr/blob/master/src/jp/ac/oit/igakilab/dwr/multiple/MultiplePrinter.java#L81
* multiple-dwrにWebContentフォルダを作成する．
 * WebContentフォルダ以下にWEB-INFフォルダを作成する．
 * WEB-INFフォルダ以下にlibフォルダを作成する
 * libに下記jarファイルを入れておく
 * https://github.com/igakilab/multiple-dwr/tree/master/WebContent/WEB-INF/lib
  * DWRは3.0.2 ( http://directwebremoting.org/dwr/downloads/index.html ), commons-loggingは1.2を利用
 * WEB-INF以下にweb.xmlとdwr.xmlの2つのファイルを下記内容で作成する
 * web.xmlはdwrを利用するための設定(tomcat用)で，dwrの利用（デバッグモード付き）とREST呼び出しのための設定が記述されている．dwr.xmlはJavaのメソッドやオブジェクトをDWRに認識させるための設定になる．
  * 参考：https://github.com/igakilab/multiple-dwr/tree/master/WebContent/WEB-INF/

#### web.xml
```xml
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
```xml
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
* multiple-dwrプロジェクト直下にbuild.xml, build-windows.xmlを作成し，下記の記述をコピーしておく．
 * https://github.com/igakilab/multiple-dwr/blob/master/build.xml
 * https://github.com/igakilab/multiple-dwr/blob/master/build-windows.xml
* build-windows.xmlにはtomcatとjreのホームディレクトリが絶対パスで指定されている．自分のディレクトリ構造と対応づいてるか確認すること．
* build.xmlを右クリック->実行->Ant Build(2つ並んでるもののうえのほう）を選択
* buildファイルに従って，コンパイルしてwarファイルが作成され，tomcatのwebappsディレクトリに配置される．
 * tomcatはver.8の利用を想定している(恐らくver.7でも動く)．
* tomcatのbinディレクトリ内のstartup.batを実行->tomcatが起動し，multiple-dwr.warが配備（デプロイ）される．
* 正常にtomcatが起動したのを確認後，「http://localhost:8080/multiple-dwr/dwr/jsonp/MultiplePrinter/helloWorld/ryokun/」にアクセス
 * JavaのhelloWorldメソッドに"ryokun"という文字列を引数で与えて呼び出している 
* ブラウザに「{ "reply":"ryokun:HelloWorld"}」と表示されたら成功．
 * 404エラーなど，意図しない振る舞いをする場合はC:\pleiades\tomcat\8\webappsのmultiple-dwr.warとmultiple-dwrフォルダを削除してant buildからやり直してみる


## JavaScriptからDWR(Direct Web Remoting)を利用してJavaのメソッドを呼ぶ方法
* 上記で作成したmultiple-dwrプロジェクトに追加する形で実施する．
* 作成したパッケージに下記クラス（InvalidValueException）を追加する．
 * https://github.com/igakilab/multiple-dwr/blob/master/src/jp/ac/oit/igakilab/dwr/multiple/InvalidValueException.java
* 同じパッケージに下記クラス（MultipleForm）を追加する
 * https://github.com/igakilab/multiple-dwr/blob/master/src/jp/ac/oit/igakilab/dwr/multiple/MultipleForm.java
* MultiplePrinterクラスにexecuteメソッドを追加する
 * https://github.com/igakilab/multiple-dwr/blob/master/src/jp/ac/oit/igakilab/dwr/multiple/MultiplePrinter.java#L27
 * JavaScriptからMultipleFormに入った値を受け取り，解釈して返すメソッド
* dwr.xmlのallowタグの中（createタグの下）に，下記記述を追加する
 * 参考：https://github.com/igakilab/multiple-dwr/blob/master/WebContent/WEB-INF/dwr.xml
 * これは呼び出すメソッドの引数あるいは返り値に指定されたBean（すべてのフィールドについてsetter/getterが定義されており，デフォルトコンストラクタが存在するJavaのクラス）をDWRに指定するための定義である
 * 同様に対象メソッドが例外を投げる場合はその例外を処理するための定義が下記のように必要
```
    <convert converter="bean" match="jp.ac.oit.igakilab.dwr.multiple.MultipleForm" />
    <convert converter="exception" match="java.lang.Exception" />
```
* WebContentの直下に下記index.htmlを作成する
 * https://github.com/igakilab/multiple-dwr/blob/master/WebContent/index.html
* WebContentの直下にjsフォルダを作成して，下記のファイル群をコピーしておく
 * https://github.com/igakilab/multiple-dwr/tree/master/WebContent/js
* build.xmlを右クリック->実行->Ant Build(2つ並んでるもののうえのほう）を選択
* buildファイルのdeployタスクに従って，コンパイルしてwarファイルが作成され，tomcatのwebappsディレクトリに配置される．
* tomcatのbinディレクトリ(C:\pleiades\tomcat\8\bin)内のstartup.batを実行->tomcatが起動し，multiple-dwr.warが配備（デプロイ）される．
* 正常にtomcatが起動したのを確認後，「http://localhost:8080/multiple-dwr/index.html」にアクセス
* 画面が正常にでて，maxと書いてあるテキストフィールドに整数値，multipleに倍数の値を入れて，正常に実行できたらOK

## jUnitでテストを行う。
* multiple-dwrのプロジェクトを右クリック->新規->ソースフォルダを選択し，フォルダー名をtestcaseにする．
* testcaseフォルダを右クリック->新規->JUnitテストケースを選択する．
* 作成されたJUnitテストケースファイルを開き，下記クラス（MultiplePrinterTest.java）を実装する
 * https://github.com/igakilab/multiple-dwr/blob/master/testcase/jp/ac/oit/igakilab/dwr/multiple/MultiplePrinterTest.java
* MultiplePrinterTest.javaを右クリック->実行->JUitテストを選択
* 期待されないテスト結果が返ってきたものがエラーと表示される。
  * 失敗するテストケースが一つある．何が問題か確認し，修正してみること．

## Google Chart APIを利用してDWRから取得したデータをグラフで表示する方法
* 下記3つのファイルで実現している
 - WeightLog.java
 - WeightTransition.java
 - weight.html
* URLの引数（クエリパラメータ）を利用する方法
 - http://.../weight.html?y=2015&m=10&d=20 として呼び出すと，`2015/10/20` がDate型としてJavaメソッド(WeightTransition.execute())に渡され，10/20からその日を含んで7日間の過去の身長・体重データを取得できる
* Date型の引数を利用してJavaのメソッドを呼び出す方法
 - DWRではデフォルトとしてDate型を扱える
* ArrayListのsortをラムダ式で行う方法
 - WeightTransition.execute() 内で実装
 - `weekWlog.sort((a,b)-> a.getDate().compareTo(b.getDate()));`

## PostgresqlとMybatisを使ってDBを利用するWebアプリケーションを開発してみる
### 準備(Database)
 - DBはなんでも良い．たまたまPortable版（インストール作業がファイル解凍だけで環境を汚さない）のPostgreSQLが見つかったので今回はそれを選択した．以下よりzipをDLして好きなところに解凍すればインストールはOK．動かすときはPostgreSQLPortable.exeを実行すれば良い．
   - https://github.com/garethflowers/postgresql-portable/releases
   - 今回はPostgreSQLPortable_9.6.1を利用
   - PostgreSQLPortable.exeを起動すると，「Warning: Console code page (1252) differs..」という警告がでるので，一度「\q」と打って終了し，以下のページに従って「PostgreSQLPortable_9.6.1\App\PgSQL」にあるpgsql.cmdの6行目あたりにある「chcp 1252 > null」という行を「chcp 932 > null」に変更してから，もう一度PostgreSQLPortable.exeを実行する．
      - 参考：http://kenpg2.seesaa.net/article/415046025.html
   - デフォルトでは，ポート：5432，ユーザ名：postgres，DB名：postgres，で自動的に起動する．
      - 参考：https://kenpg.bitbucket.io/blog/201505/02.html
   - Webアプリケーションのために以下のコマンドをPostgreSQLPortableのコンソールに一行ずつ順に入力する．なお，以下のコマンドはコンソールに表示されている`postgres=#`に続けて入力することを想定している(`postgres=#`は接続するDBを変更するとそのDB名に変わる)．

```sql
create database product; -- productという名前のDBを作成する．DBの一覧は「\l」と打つことで確認できる．
\c product -- productという名前のDBに接続する．
create table food(name varchar(10), price int); -- food tableを作成する．「\z」でテーブルの一覧が，「\d tablename」でtablenameで指定したテーブルのスキーマが表示される．
insert into food (name, price) VALUES ('apple', 120); -- 各データをfoodテーブルに追加．
insert into food (name, price) VALUES ('melon', 500); -- 「select * from food;」と実行すると追加されたデータを確認できる．
insert into food (name, price) VALUES ('peach', 200);
```

### 準備（OR Mapper (MyBatis)）
- OR Mapper: DBとJavaのクラスの間を仲介するライブラリのこと．今回はMyBatis（ http://www.mybatis.org/mybatis-3/ja/ ) を利用する．
- mybatisライブラリを下記からダウンロード（今回はmybatis-3.4.4.zip をダウンロード）
  - https://github.com/mybatis/mybatis-3/releases
  - WebContent\WEB-INF\libの中に，↑のzipを解凍するとでてくる「mybatis-3.4.4.jar」を置く．
  - mybatis-3.4.4.jarをeclipse上で右クリック->ビルドパス->ビルドパスに追加，をクリックする．
- PostgreSQLを利用するためのJDBCドライバを下記からダウンロードする．なお，利用するDBがPostgreSQLではない場合，対応するDBのJDBCをドライバをDLして同じようにすれば良い．
  - https://jdbc.postgresql.org/download.html
  - 利用するのがJava8なので，「PostgreSQL JDBC 4.2 Driver, 42.1.1 (postgresql-42.1.1.jar)」をダウンロードする．
  - mybatisライブラリと同じくWebContent\WEB-INF\libの中に↑のjarを置き，eclipse上で右クリック->ビルドパス->ビルドパスに追加，をクリックする．
- eclipseプロジェクトにresourcesというフォルダを追加し，右クリック->ビルドパス->ソースフォルダーとして使用，をクリックする
  - mybatis-config.xmlとproduct_mapper.xmlという以下の2つのファイルを新規に作成する
    - https://github.com/igakilab/multiple-dwr/tree/master/resources
    - mybatis-config.xml は接続先DBの設定やmapper.xmlの指定を行っている
    - product_mapper.xml は↑で作成したproductデータベースのfoodテーブル内の要素をselectするsqlが指定されている
- jp.ac.oit.igakilab.dwr.mybatisというパッケージを追加する
  - ↑のパッケージ内に，DBUtility.javaを作成する
    - https://github.com/igakilab/multiple-dwr/blob/master/src/jp/ac/oit/igakilab/dwr/mybatis/DBUtility.java
    - Mybatisを介してDBにアクセスするための処理が書かれている（どんなプログラムでも再利用可能）

### 準備(Javaプロジェクト)
- build.xmlを以下のように修正する．
  - https://github.com/igakilab/multiple-dwr/blob/master/build.xml
  - 修正箇所は12行目辺りに`<property name="src.res.dir" value="resources" />`を追加したところと`<target name="compile" depends="prepare">`タグ内の60行目辺りに下記を追加したところ．

```xml
	<copy todir="${dest.class.dir}">
		<fileset dir="${src.res.dir}" />
	</copy>
```

- ここまではどんなWebアプリケーションでも同じ(product_mapper.xmlやDBの内容登録等は除く)であるため，再利用可能．以下がアプリによって異なるところ．
### postgresqlにアクセスするWebアプリケーションの実装
- 下記のファイルを指定されたパッケージに追加する
  - jp.ac.oit.igakilab.dwr.mybatis.Food.java
    - https://github.com/igakilab/multiple-dwr/blob/master/src/jp/ac/oit/igakilab/dwr/mybatis/Food.java
    - productsデータベースのfoodテーブルの中身を保存するBeanクラス
  - jp.ac.oit.igakilab.dwr.mybatis.ProductPrinter.java
    - https://github.com/igakilab/multiple-dwr/blob/master/src/jp/ac/oit/igakilab/dwr/mybatis/ProductPrinter.java
    - Webアプリケーションとして公開するメソッドを実装したクラス（テスト用にmainメソッドも実装している）

- dwr.xml (WebContent/WEB-INF/内）の修正
  - https://github.com/igakilab/multiple-dwr/blob/master/WebContent/WEB-INF/dwr.xml
  - 修正箇所は`<dwr><allow>`タグ内に下記を追加したところ．

```xml
    <create creator="new" javascript="ProductPrinter">
      <param name="class" value="jp.ac.oit.igakilab.dwr.mybatis.ProductPrinter"/>
    </create>
    <convert converter="bean" match="jp.ac.oit.igakilab.dwr.mybatis.*" />
```

### 確認
- PostgreSQLが実行されていることを確認する
- jp.ac.oit.igakilab.dwr.mybatis.ProductPrinterを右クリックし，実行->Javaアプリケーション，をクリックする．mainメソッドが実装されているので，mybatisやpostgresqlの設定がうまくいっていれば，下記がコンソールに表示される．

```
apple
120
melon
500
peach
200
```

- これが表示されなければ，mybatisのconfigかDBUtility.java，ProductPrinterクラスなどが何か間違っている(まだDWRとは無関係)．
- ↑が正常に表示されることを確認後，Webアプリケーションを動かしてみる．
- build.xmlを右クリックし，実行->Ant Build(2つ並んでるもののうえのほう）を選択
- buildファイルのdeployタスクに従って，コンパイルしてwarファイルが作成され，tomcatのwebappsディレクトリに配置される．
- tomcatのbinディレクトリ(C:\pleiades\tomcat\8\bin)内のstartup.batを実行->tomcatが起動し，multiple-dwr.warが配備（デプロイ）される．
- 正常にtomcatが起動したのを確認後，「http://localhost:8080/multiple-dwr/dwr/jsonp/ProductPrinter/execute/」にアクセス．
- apple,melon等のproduct DBのfoodテーブルに登録した情報が下記のようにJSON形式で表示されていればOK

```json
[
  {
    "name": "apple",
    "price": 120
  },
  {
    "name": "melon",
    "price": 500
  },
  {
    "name": "peach",
    "price": 200
  }
]
```
