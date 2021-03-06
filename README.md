# 1. multiple-dwr

- このリポジトリで学習できること
<!-- TOC -->

- [multiple-dwr](#multiple-dwr)
- [準備](#準備)
  - [準備(ソフトウェアのインストール)](#準備ソフトウェアのインストール)
  - [準備(環境変数の設定)](#準備環境変数の設定)
  - [準備(vscode拡張機能のインストール)](#準備vscode拡張機能のインストール)
  - [準備(プロジェクトフォルダの作成と初期設定)](#準備プロジェクトフォルダの作成と初期設定)
  - [準備(Javaプロジェクトの設定とディレクトリの作成)](#準備javaプロジェクトの設定とディレクトリの作成)
  - [準備(build.gradleの設定)](#準備buildgradleの設定)
  - [準備(Tomcat for Javaの設定)](#準備tomcat-for-javaの設定)
- [DWRを利用したWebアプリケーション開発](#dwrを利用したwebアプリケーション開発)
  - [DWRを利用してREST形式でJavaのメソッドを呼ぶ](#dwrを利用してrest形式でjavaのメソッドを呼ぶ)
      - [web.xml](#webxml)
      - [dwr.xml](#dwrxml)
  - [JavaScriptからDWR(Direct Web Remoting)を利用してJavaのメソッドを呼ぶ](#javascriptからdwrdirect-web-remotingを利用してjavaのメソッドを呼ぶ)
  - [PostgresqlとMybatisを使ってDBを利用するWebアプリケーションを開発してみる](#postgresqlとmybatisを使ってdbを利用するwebアプリケーションを開発してみる)
    - [準備(Database)](#準備database)
    - [準備（OR Mapper (MyBatis)）](#準備or-mapper-mybatis)
    - [postgresqlにアクセスするJavaアプリケーションの実装](#postgresqlにアクセスするjavaアプリケーションの実装)
      - [確認](#確認)
    - [postgresqlにアクセスするWebアプリケーションの実装](#postgresqlにアクセスするwebアプリケーションの実装)
    - [postgresqlのデータをListで受け取ってhtmlにjsで表示するサンプル](#postgresqlのデータをlistで受け取ってhtmlにjsで表示するサンプル)
    - [htmlで作成したjsonオブジェクトの配列をdwr経由でJavaにわたすサンプル](#htmlで作成したjsonオブジェクトの配列をdwr経由でjavaにわたすサンプル)
    - [htmlで作成したjsonオブジェクトの配列をdwr経由でJavaにわたし，Postgresqlに書き込むサンプル](#htmlで作成したjsonオブジェクトの配列をdwr経由でjavaにわたしpostgresqlに書き込むサンプル)
    - [配列を含むjsonオブジェクトをhtml(js)からdwr経由でJavaにわたすサンプル](#配列を含むjsonオブジェクトをhtmljsからdwr経由でjavaにわたすサンプル)
    - [クッキーを使うサンプル](#クッキーを使うサンプル)
    - [DBのupdateを実行する](#dbのupdateを実行する)
- [おまけ](#おまけ)
  - [jUnitでテストを行う。](#junitでテストを行う)

<!-- /TOC -->

- 本プロジェクトを一から作成する手順を示し，上記の解説を行う．
- なお，本READMEはvscode版になっている．過去に公開したeclipse版は↓を参照すること
  - https://github.com/igakilab/multiple-dwr/tree/v1.0

# 2. 準備

## 2.1. 準備(ソフトウェアのインストール)
- ``C:\igakilab``ディレクトリを作成する．すべてのソフトウェアのセットアップ完了時には以下のようなディレクトリ構成になる．以降このチュートリアルでは，このディレクトリ構成に従っているものとして説明を行う．また，バージョン番号はチュートリアル作成時のもの．
```
C:\igakilab\apache-tomcat-8.5.45\bin
C:\igakilab\apache-tomcat-8.5.45\conf
C:\igakilab\apache-tomcat-8.5.45\lib
:
C:\igakilab\gradle-5.6\bin
C:\igakilab\gradle-5.6\init.d
:
C:\igakilab\openjdk1.8.0.XXX\bin
C:\igakilab\openjdk1.8.0.XXX\include
C:\igakilab\openjdk1.8.0.XXX\jre
C:\igakilab\openjdk1.8.0.XXX\lib
:
C:\igakilab\PostgreSQLPortable-10.1.1\App
C:\igakilab\PostgreSQLPortable-10.1.1\Data
:
C:\igakilab\vscode-portable-win64-1.37.1-16\app
C:\igakilab\vscode-portable-win64-1.37.1-16\data
C:\igakilab\vscode-portable-win64-1.XX.Y-Z\vscode-portable.exe
:
```
- 以降自分ですべてのソフトウェアのインストールを行う場合は各ソフトウェアを↓の指示に従ってダウンロードし，igakilabの下に解凍すること．当研究室内ではNASにある``shared\all\archives``に置いてある``multiple-dwr-software20190824.exe`` を``c:\igakilab``に配置し，実行するとすべてのソフトウェアが解凍される．
- [Visual Studio Code(vscode) Portable](https://portapps.io/app/vscode-portable/)をigakilab以下にインストールする
  - 統合開発エディタ．本家vscodeをインストールしても良いが，個人的にはportable版のほうが安定してるイメージ
- [Apache Tomcat](https://tomcat.apache.org/) から64-bit Windows zipをダウンロードし，igakilab以下に解凍する
  - Webアプリケーションサーバ．Tomcat8系で動作確認をしている
- [Openjdk8](https://developers.redhat.com/products/openjdk/download)をダウンロードし，igakilab以下に解凍する．
  - DL時にRedhatのアカウント作成が必要
- [Gradle](https://gradle.org/releases/) からbinary onlyを選択してgradle-X.Y-bin.zipファイルをダウンロードし，igakilab以下に解凍する．
  - ビルドツール．javaファイルをコンパイルしたり，一つにまとめてwarファイル(tomcatでウェブアプリを公開するときに利用）するのに利用する
- [PostgreSQL Portable](https://github.com/garethflowers/postgresql-portable/releases/) から zipファイルをダウンロードし，igakilab以下に解凍する．
  - DBMS．データベースを利用する際に便利なので利用している．別に他のDBMSでも構わない．

## 2.2. 準備(環境変数の設定)
- Windowsの環境変数設定
  - 左下のWindowsアイコンを右クリック->システム->[バージョン情報]を下にスクロールしたところにある[関連設定]の[システム情報]をクリック->[システムの詳細設定]->[環境変数]をクリック->[ユーザー環境変数]の中の[Path]を選択し，[編集]をクリック
  - [環境変数名の編集]画面が開くので，[新規]をクリックし，``C:\igakilab\openjdk1.8.0.212\bin``を入力する(インストールしたopenjdkのフォルダ名を確認すること)．もう一度[新規]をクリックし，``C:\igakilab\gradle-5.6\bin``を入力する．最後にOKをクリックして編集画面を閉じる．

## 2.3. 準備(vscode拡張機能のインストール)
- vscodeを起動し，画面左端にあるアイコンの一番下のもの(Extensions)をクリックし，下記の拡張機能をインストールする
  - 検索窓に拡張機能名を入力し，対象の拡張機能を選択して，InstallをクリックすればOK．
- Java Extension Pack
- Japanese Language Pack for Visual Studio Code
- Tomcat for Java
- EvilInspector
  - おまけ．全角スペースを強調表示してくれる．
- 拡張機能をインストールしたらvscodeを再起動する

## 2.4. 準備(プロジェクトフォルダの作成と初期設定)
- ``multiple-dwr``という名前のディレクトリをどこかに作成する（どこでも良い）
- vscodeを起動し，[ファイル]->[フォルダーを開く]から``multiple-dwr``フォルダーを開く
- vscode左下の歯車アイコンをクリックし，[設定]をクリック
- 設定の[ワークスペース]をクリックしたあと，右上のファイルアイコン(設定(JSON)を開く)をクリック
- `multiple-dwr`に`.vscode\settings.json`ファイルが作成されるので，↓を丸ごとコピペしてvscodeを再起動する
  - 使い勝手を良くするための設定なので好きに変更してOK．
```json
{
  "files.encoding": "utf8",
  "workbench.editor.enablePreview": false,
  "workbench.colorTheme": "Default Light+",
  "workbench.editor.labelFormat": "short", // タブに表示する文字列の設定
  "workbench.editor.tabSizing": "shrink", // タブの表示設定
  "files.eol": "\n",
  "files.autoSave": "onFocusChange",
  "files.insertFinalNewline": true, // ファイルの保存時に末尾を改行
  "files.trimFinalNewlines": true, // ファイルの保存時に最終行以降をトリミング
  "files.trimTrailingWhitespace": true, // ファイルの保存時に行末の空白をトリミング
  "editor.codeLens": false,
  "editor.renderWhitespace": "all",
  "editor.fontFamily": "Consolas, monospace",
  "editor.fontSize": 15,
  "editor.formatOnPaste": true,
  "editor.formatOnSave": true,
  "editor.formatOnType": true,
  "editor.tabSize": 2,
  "editor.tabCompletion": "on",
  "editor.detectIndentation": false,
  "editor.wordWrap": "on",
  "editor.renderControlCharacters": true,
  "editor.minimap.showSlider": "always",
  "editor.renderLineHighlight": "all", // 選択行を行番号含めすべてハイライト
  "editor.quickSuggestions": {
    "comments": false, // コメント内では無効
    "strings": true, // 文字列内では有効
    "other": true //  その他の場所で有効
  },
  "editor.folding": true, // コードの折りたたみを許可
  "editor.lineNumbers": "on", // 行番号の表示
  "editor.matchBrackets": true, // 対応する括弧の強調表示をオンに
  "git.enabled": false,
  "telemetry.enableTelemetry": false,
  "telemetry.enableCrashReporter": false,
  "java.configuration.updateBuildConfiguration": "automatic",
}
```

## 2.5. 準備(Javaプロジェクトの設定とディレクトリの作成)
- vscodeの[ターミナル]->[新しいターミナル]をクリックする
- 恐らくpowershellが開くので，ターミナル画面でEnterしてから，`gradle init --type java-application`と入力してEnterする
- 入力項目がいくつか表示されるが，``Source package``以外はデフォルトでOK.``Source package``のところだけ`jp.igakilab.dwr.multiple`と入力する．その後，java及びgradleのPATH設定が正常に行われていれば，自動的に指定したパッケージディレクトリなどが作成される
- 追加で`multiple-dwr\src\main\webapp\WEB-INF`ディレクトリを作成する．大文字小文字など間違えないようにすること．

## 2.6. 準備(build.gradleの設定)
- Web application ARchive(WARファイル)を作成するためにGradleというビルドツールを利用する．↑のJavaプロジェクトの設定が完了していれば，`build.gradle`ファイルができているので，vscodeで開くこと
- `build.gradle` ファイルの中身をすべて消し，↓をコピペすること
```gradle
apply plugin: 'java'
apply plugin: 'war'

sourceCompatibility = '1.8'
targetCompatibility = '1.8'

[compileJava, compileTestJava]*.options*.encoding = 'utf-8'

repositories {
     jcenter()
}

dependencies{
    compile group: 'org.directwebremoting', name: 'dwr', version: '3.0.2-RELEASE'
    compile group: 'commons-logging', name: 'commons-logging', version: '1.2'
    testCompile 'junit:junit:4.12'
    compile group: 'org.mybatis', name: 'mybatis', version: '3.5.2'
    compile group: 'org.postgresql', name: 'postgresql', version: '42.2.6'
}
```
- 保存してしばらくするとdependenciesで指定されたライブラリが自動的にDLされる（なのでネットワークに接続した状態で実施すること）．
- dependenciesの1つ目はDWRのライブラリ，2つ目はloggingツール（DWRが利用する）．3つ目はテストツール．4,5個目はデータベース利用のためのライブラリになっている．

## 2.7. 準備(Tomcat for Javaの設定)
- vscodeのTomcatプラグインの設定を行う
  - 参考：https://code.visualstudio.com/docs/java/java-tomcat-jetty
- vscode画面左下に[TOMCAT SERVERS]というメニューが表示されている（左下歯車アイコンのすぐ右側）のでクリックする
- [TOMCAT SERVERS]が展開され，メニューバー右側に[+]アイコン(Add Tomcat Server)が表示されるのでそれをクリックする
- ダイヤログが表示されるので，`C:\igakilab\apache-tomcat-8.5.45` を指定して，[Select Tomcat Directory]をクリックする．
- [TOMCAT SERVERS]の下に`■ apache-tomcat-8.5.45` (四角は赤色)と表示されればOK

# 3. DWRを利用したWebアプリケーション開発

## 3.1. DWRを利用してREST形式でJavaのメソッドを呼ぶ
- DWRを利用することでHTTP/GETの形式でJavaのメソッドを呼ぶことができる．返り値はjsonになる．
* multiple-dwrプロジェクトの`src\main\java`フォルダにパッケージを作成する（例：jp.igakilab.dwr.multiple）
* 作成したパッケージ内にMultiplePrinterクラスを作成する．
* MultiplePrinterクラスの中身を下記のようにし，helloWorldメソッドを作成する(helloWorldメソッド以外を作成するとコンパイルできないので注意すること）
  * https://github.com/igakilab/multiple-dwr/blob/1df86590ccf348c0c495be00cb9a2036e3fb152b/src/main/java/jp/igakilab/dwr/multiple/MultiplePrinter.java
* WEB-INF以下にweb.xmlとdwr.xmlの2つのファイルを下記内容で作成する
* web.xmlはdwrを利用するための設定(tomcat用)で，dwrの利用（デバッグモード付き）とREST呼び出しのための設定が記述されている．dwr.xmlはJavaのメソッドやオブジェクトをDWRに認識させるための設定になる．
  * 参考：https://github.com/igakilab/multiple-dwr/tree/1df86590ccf348c0c495be00cb9a2036e3fb152b/src/main/webapp/WEB-INF

#### 3.1.1. web.xml
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

#### 3.1.2. dwr.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE dwr PUBLIC
    "-//GetAhead Limited//DTD Direct Web Remoting 3.0//EN"
    "http://getahead.org/dwr//dwr30.dtd">
<dwr>
  <allow>
    <create creator="new" javascript="MultiplePrinter">
      <param name="class" value="jp.igakilab.dwr.multiple.MultiplePrinter"/>
    </create>
  </allow>
</dwr>
```
- vscodeの[ターミナル]が開いていればそこに，開いていない場合は[ターミナル]->[新しいターミナル]をクリックしてターミナルを開く．
- 恐らくpowershellが開くので，ターミナル画面でEnterしてから，`gradle war`と入力してEnterする
- 以下のように表示されればOK．
```
BUILD SUCCESSFUL in 2s
2 actionable tasks: 2 executed
```
- `multiple-dwr\build\libs` に `multiple-dwr.war` が作成されているので，右クリックして[Run on Tomcat Server]を選択する
- 画面右下の[出力]エリアに下記のようなメッセージが表示されればOK（ディレクトリ名は環境によって異なるので，必ずしも一致しない）
  - Tomcatの起動ログがすべて表示されるのでその中の一行

```
[C:\igakilab\vscode-portable-win64-1.37.1-16\data\appdata\Code\User\workspaceStorage\90c0604480e14b8f3db535f310ce0373\adashen.vscode-tomcat\tomcat\apache-tomcat-8.5.45\webapps\multiple-dwr] has finished in [825] ms
```
- 画面左下の[TOMCAT SERVERS]の下に[● apache-tomcat-8.5.45]と表示されればOK（丸は緑色）
* 正常にtomcatが起動したのを確認後，[http://localhost:8080/multiple-dwr/dwr/jsonp/MultiplePrinter/helloWorld/ryokun/](http://localhost:8080/multiple-dwr/dwr/jsonp/MultiplePrinter/helloWorld/ryokun/)にアクセス
  * JavaのhelloWorldメソッドに"ryokun"という文字列を引数で与えて呼び出している
* ブラウザに「{ "reply":"ryokun:HelloWorld"}」と表示されたら成功．
  * 404エラーなど，意図しない振る舞いをする場合は[TOMCAT SERVERS]の[●apache-tomcat-8.5.45]を右クリックして[Stop]を選択し，web.xmlやdwr.xmlを確認してから，`gradle war`を入力するところからやり直すこと．

> コラム
> * 上はtomcat pluginでの実行例なので，最終的に完成したWarをプラグインを利用せずに実行したい場合は，`C:\igakilab\apache-tomcat-8.5.45\webapps` に`multiple-dwr.war` ファイルを直接置き，tomcatのbinディレクトリ内のstartup.batを実行すると良い．tomcatが起動し，multiple-dwr.warが配備（デプロイ）される．

## 3.2. JavaScriptからDWR(Direct Web Remoting)を利用してJavaのメソッドを呼ぶ
* 上記で作成したmultiple-dwrプロジェクトに追加する形で実施する．
- 画面左下の[TOMCAT SERVERS]の下の[● apache-tomcat-8.5.45]を右クリックして[Stop]を選択しておくこと．
- 作成したパッケージに`InvalidValueException.java`, `MultipleForm.java` を新規に追加し，`MultiplePrinter` クラスに`execute` メソッド（JavaScriptからMultipleFormに入った値を受け取り，解釈して返すメソッド）を追加すること
  - 各javaファイルは下記のとおりにすること
  - https://github.com/igakilab/multiple-dwr/tree/71a947b3fa9d6b06b29cc592459e16508387c394/src/main/java/jp/igakilab/dwr/multiple
* dwr.xmlのallowタグの中（createタグの下）に，下記記述を追加する
  * 参考：https://github.com/igakilab/multiple-dwr/blob/71a947b3fa9d6b06b29cc592459e16508387c394/src/main/webapp/WEB-INF/dwr.xml
  * これは呼び出すメソッドの引数あるいは返り値に指定されたBean（すべてのフィールドについてsetter/getterが定義されており，デフォルトコンストラクタが存在するJavaのクラス）をDWRに指定するための定義である
  * 同様に対象メソッドが例外を投げる場合はその例外を処理するための定義が下記のように必要
```xml
    <convert converter="bean" match="jp.igakilab.dwr.multiple.MultipleForm" />
    <convert converter="exception" match="java.lang.Exception" />
```
* `multiple-dwr\src\main\webapp`に下記`index.html`を作成する
  * https://github.com/igakilab/multiple-dwr/blob/71a947b3fa9d6b06b29cc592459e16508387c394/src/main/webapp/index.html
* `multiple-dwr\src\main\webapp`にjsフォルダを作成して，下記のファイル群をコピーしておく
  * https://github.com/igakilab/multiple-dwr/tree/71a947b3fa9d6b06b29cc592459e16508387c394/src/main/webapp/js
- vscodeの[ターミナル]が開いていればそこに，開いていない場合は[ターミナル]->[新しいターミナル]をクリックしてターミナルを開く．
- 恐らくpowershellが開くので，ターミナル画面でEnterしてから，`gradle war`と入力してEnterする
- 以下のように表示されればOK．
```
BUILD SUCCESSFUL in 2s
2 actionable tasks: 2 executed
```
- `multiple-dwr\build\libs` に `multiple-dwr.war` が作成されているので，右クリックして[Run on Tomcat Server]を選択する
  - Runの前に，画面左下の[TOMCAT SERVERS]の下の[■ apache-tomcat-8.5.45]を確認すること（止まっていればOK）．止まっていない場合は[TOMCAT SERVERS]の下の[●apache-tomcat-8.5.45]を右クリックして[Stop]を選択すること．
- 画面右下の[出力]エリアに下記のようなメッセージが表示されればOK（ディレクトリ名は環境によって異なるので，必ずしも一致しない）
  - Tomcatの起動ログがすべて表示されるのでその中の一行

```
[C:\igakilab\vscode-portable-win64-1.37.1-16\data\appdata\Code\User\workspaceStorage\90c0604480e14b8f3db535f310ce0373\adashen.vscode-tomcat\tomcat\apache-tomcat-8.5.45\webapps\multiple-dwr] has finished in [825] ms
```
- 画面左下の[TOMCAT SERVERS]の下に[● apache-tomcat-8.5.45]と表示されればOK（丸は緑色）
* 正常にtomcatが起動したのを確認後，http://localhost:8080/multiple-dwr/index.html にアクセス
* 画面が正常にでて，maxと書いてあるテキストフィールドに整数値，multipleに倍数の値を入れて，正常に実行できたらOK

## 3.3. PostgresqlとMybatisを使ってDBを利用するWebアプリケーションを開発してみる

### 3.3.1. 準備(Database)
 - DBはなんでも良い．たまたまPortable版（インストール作業がファイル解凍だけで環境を汚さない）のPostgreSQLが見つかったので今回はそれを選択した．動かすときはPostgreSQLPortable.exeを実行すれば良い．
 - PostgreSQLPortable.exeを起動すると，「Warning: Console code page (1252) differs..」という警告がでるので，一度「\q」と打って終了し，以下のページに従って`C:\igakilab\PostgreSQLPortable_10.4.1\App\PgSQL`にある`pgsql.cmd`の6行目あたりにある「chcp 1252 > null」という行を「chcp 932 > null」に変更してから，もう一度PostgreSQLPortable.exeを実行する．
   - 参考：http://kenpg2.seesaa.net/article/415046025.html
   - デフォルトでは，ポート：5432，ユーザ名：postgres，DB名：postgres，で自動的に起動する．
      - 参考：https://kenpg.bitbucket.io/blog/201505/02.html
- Webアプリケーションのために以下のコマンドをPostgreSQLPortableのコンソールに一行ずつ順に入力する．なお，以下のコマンドはコンソールに表示されている`postgres=#`あるいは`product=#`に続けて入力することを想定している(`postgres=#`は接続するDBを変更するとそのDB名に変わる)．
```sql
-- productという名前のDBを作成する．DBの一覧は「\l」と打つことで確認できる．
postgres=＃ create database product;
CREATE DATABASE -- productという名前のDBが作成された
-- productという名前のDBに接続する．
postgres=＃ \c product
データベース "product" にユーザ "postgres" として接続しました。
-- food tableを作成する．「\z」でテーブルの一覧が，「\d tablename」でtablenameで指定したテーブルのスキーマが表示される．
product=＃ create table food(name varchar(10), price int);
CREATE TABLE
product=＃ insert into food (name, price) VALUES ('apple', 120); -- 各データをfoodテーブルに追加．
INSERT 0 1
product=＃ insert into food (name, price) VALUES ('melon', 500);
INSERT 0 1
product=＃ insert into food (name, price) VALUES ('peach', 200);
INSERT 0 1
```
- 「select * from food;」と実行すると下記のように追加されたデータを確認できる．
```
 name  | price
-------+-------
 apple |   120
 melon |   500
 peach |   200
(3 行)
```
- postgresqlの操作についてはこちらを参考に： https://qiita.com/aosho235/items/c657e2fcd15fa0647471

### 3.3.2. 準備（OR Mapper (MyBatis)）
- OR Mapper: DBとJavaのクラスの間を仲介するライブラリのこと．今回はMyBatis（ http://www.mybatis.org/mybatis-3/ja/ ) を利用する．
- mybatisライブラリ3.5.2を利用する．DLやビルドパスの設定等はgradleが勝手にやってくれる．
- PostgreSQLを利用するためのJDBCドライバ（要はJavaからpostgresqlを利用するためのソフトウェア）をセットアップする．詳細はgradleが(以下略
  - なお，利用するDBがPostgreSQLではない場合，対応するDBのJDBCドライバをgradleのdependenciesに設定すれば良い．
  - `multiple-dwr\src\main\resources` にmybatis-config.xmlとProductMapper.xmlという以下の2つのファイルを新規に作成する
    - https://github.com/igakilab/multiple-dwr/tree/4ca442ae36236509f4ca2fde6c6d8128bc592759/src/main/resources
    - mybatis-config.xml は接続先DBの設定やmapper.xmlの指定を行っている
    - ProductMapper.xml は↑で作成したproductデータベースのfoodテーブル内の要素をselectするsqlを指定している
- jp.igakilab.dwr.mybatisというパッケージを追加する
  - ↑のパッケージ内に，DBUtility.javaを作成する
    - https://github.com/igakilab/multiple-dwr/blob/4ca442ae36236509f4ca2fde6c6d8128bc592759/src/main/java/jp/igakilab/dwr/mybatis/DBUtility.java
    - Mybatisを介してDBにアクセスするための処理が書かれている（どんなプログラムでも再利用可能）
- ここまではどんなWebアプリケーションでもほぼ同じ(ProductMapper.xmlやDBの内容登録（insert）等は除く)であるため，再利用可能．以下がアプリによって異なるところ．

### 3.3.3. postgresqlにアクセスするJavaアプリケーションの実装
- 下記のファイルを指定されたパッケージに追加する
  - jp.igakilab.dwr.mybatis.Food.java
    - https://github.com/igakilab/multiple-dwr/blob/4ca442ae36236509f4ca2fde6c6d8128bc592759/src/main/java/jp/igakilab/dwr/mybatis/Food.java
    - productsデータベースのfoodテーブルの中身を保存するBeanクラス
  - jp.igakilab.dwr.mybatis.ProductPrinter.java
    - https://github.com/igakilab/multiple-dwr/blob/4ca442ae36236509f4ca2fde6c6d8128bc592759/src/main/java/jp/igakilab/dwr/mybatis/ProductPrinter.java
    - Webアプリケーションとして公開するメソッドを実装したクラス（テスト用にmainメソッドも実装している）

#### 3.3.3.1. 確認
- PostgreSQLが実行されていることを確認する
- jp.igakilab.dwr.mybatis.ProductPrinterを選択し，F5を押す．
- `.vscode\launch.json` ファイルが開いて，ProductPrinterクラスの`main`メソッドを実行する設定が自動的に行われるので，そのままもう一度`jp.igakilab.dwr.mybatis.ProductPrinter`を選択し，F5を押す．
- vscodeのデバッガが起動し，ProductPrinterの`main` メソッドを実行してくれる．
- mybatisやpostgresqlの設定がうまくいっていれば，下記がコンソールに表示される．

```
apple
120
melon
500
peach
200
```

- これが表示されなければ，mybatisのconfigかDBUtility.java，ProductPrinterクラスなどが何か間違っている(まだDWRとは無関係)．

### 3.3.4. postgresqlにアクセスするWebアプリケーションの実装
- 画面左下の[TOMCAT SERVERS]の下の[● apache-tomcat-8.5.45]を右クリックして[Stop]を選択しておくこと．
- dwr.xml (WebContent/WEB-INF/内）の修正
  - https://github.com/igakilab/multiple-dwr/blob/4ca442ae36236509f4ca2fde6c6d8128bc592759/src/main/webapp/WEB-INF/dwr.xml
  - 修正箇所は`<dwr><allow>`タグ内に下記を追加したところ．

```xml
    <create creator="new" javascript="ProductPrinter">
      <param name="class" value="jp.igakilab.dwr.mybatis.ProductPrinter"/>
    </create>
    <convert converter="bean" match="jp.igakilab.dwr.mybatis.*" />
```

- vscodeの[ターミナル]が開いていればそこに，開いていない場合は[ターミナル]->[新しいターミナル]をクリックしてターミナルを開く．
- 恐らくpowershellが開くので，ターミナル画面でEnterしてから，`gradle war`と入力してEnterする
- 以下のように表示されればOK．
```
BUILD SUCCESSFUL in 2s
2 actionable tasks: 2 executed
```
- `multiple-dwr\build\libs` に `multiple-dwr.war` が作成されているので，右クリックして[Run on Tomcat Server]を選択する
  - Runの前に，画面左下の[TOMCAT SERVERS]の下の[■ apache-tomcat-8.5.45]を確認すること（止まっていればOK）．止まっていない場合は[TOMCAT SERVERS]の下の[●apache-tomcat-8.5.45]を右クリックして[Stop]を選択すること．
- tomcatが起動し，multiple-dwr.warが配備（デプロイ）される．
- 正常にtomcatが起動したのを確認後，[http://localhost:8080/multiple-dwr/dwr/jsonp/ProductPrinter/execute/](http://localhost:8080/multiple-dwr/dwr/jsonp/ProductPrinter/execute/)にアクセス．
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

### 3.3.5. postgresqlのデータをListで受け取ってhtmlにjsで表示するサンプル
- https://github.com/igakilab/multiple-dwr/commit/caae18c9088c3f71a8cbd38b43073da8187899f1
- この↑array.html実装．
- http://localhost:8080/multiple-dwr/array.html にアクセスし，Sendボタンをクリックすると表示される．
- ProductPrinter.execute()から帰ってくるList<Food>がarray.htmlのL15のdataに返ってくる．今回の例ではデータが2つなので，data[0]~data[2]に以下のように格納されている．

```javascript
0: {name: "apple", price: 120}
1: {name: "melon", price: 500}
2: {name: "peach", price: 200}
```

- array.htmlのL19のforEachでは，dataから1つずつ取得し，1つ分をfoodという変数にほりこんでいる．forEachの中ではfood.nameなどで各値を取得している．
- data変数に何が入っているかは，L17のconsole.log(data)でブラウザの検証機能のconsole画面などで確認できるようになっている．

### 3.3.6. htmlで作成したjsonオブジェクトの配列をdwr経由でJavaにわたすサンプル
- https://github.com/igakilab/multiple-dwr/commit/871cfeca270888a503f5304e244865073f766699
  - これの` public void insertFood(ArrayList<Food> foodList) `とJSのinsertArray()が相当する
- http://localhost:8080/multiple-dwr/array.html にアクセスし，InsertArrayボタンをクリックすると，console.logに`insert_food実行完了`と表示され，tomcatのコンソールにさんまやぶり大根の情報が表示される．
- html側で，javascriptが作成した辞書型の配列をinsertFoodにわたすと，Java側でArrayList<Food>として受け取ることができる．
- 注意：`gradlw war` を実行して，Tomcatを実行したままwarを更新しても，Javaのファイルが更新されないことがあるので（おそらくtomcatの問題），その場合は一度TomcatをStopしてから書き込むこと．

### 3.3.7. htmlで作成したjsonオブジェクトの配列をdwr経由でJavaにわたし，Postgresqlに書き込むサンプル
- https://github.com/igakilab/multiple-dwr/commit/f7b46ab5e6df2e8386af638328b86f1a5557f9bc
  - ポイント：public void insertFood(ArrayList<Food> foodList)で，FoodクラスのArrayListを受け取り，拡張for文で一つずつFoodクラスのオブジェクトをinsertしているところ．なお，最後にsession.commit()を呼ばないとDBへの書き込みは行われない．
- http://localhost:8080/multiple-dwr/array.html にアクセスし，InsertArrayボタンをクリックすると，console.logに`insert_food実行完了`と表示され，postgresqlのproduct dbのfoodテーブルに書き込まれる．

### 3.3.8. 配列を含むjsonオブジェクトをhtml(js)からdwr経由でJavaにわたすサンプル
- https://github.com/igakilab/multiple-dwr/commit/db0475d0488893f2f88125faae63fac8fae6f19d
  - jsonオブジェクトと同じ構造のJavaのオブジェクト（この場合はDeck.java）を作成し，Java側の仮引数で受け取ると良い．jsonの配列はJava側でArrayListとして受け取れる
- http://localhost:8080/multiple-dwr/array.html にアクセスし，InsertCardsボタンをクリックすると，console.logに`insertDeck実行完了`と表示され，tomcatのコンソールにユーザ名及び数値が表示される．

### 3.3.9. クッキーを使うサンプル
- https://github.com/igakilab/multiple-dwr/commit/19041b3ef329ac7eeddd0b873eb718dc1a0a2576
  - jquery cookieを利用して，playerというkeyにフォームで入力した文字列をクッキーとして保存して利用するサンプル．
  - http://localhost:8080/multiple-dwr/game.html にアクセスし，テキストフィールドに文字列を入力してSendボタンをクリックすると，クッキーに値が保存されて，logoutボタンが表示されまる．logoutボタンをクリックするとクッキーが削除されて，またSendボタンが表示される．

### 3.3.10. DBのupdateを実行する
- https://github.com/igakilab/multiple-dwr/commit/ecfb6089233bd45232f8ec5b4930719dd71fed98
  - mybatisのupdateのテスト．ProductPrinter.javaのmainメソッド内の処理はテスト用．
- http://localhost:8080/multiple-dwr/array.html にアクセスし，GetFoodListを一度押して，melonの名前と値段を確認した後，Update Melon Priceボタンをクリックしたあとにもう一度GetFoodListを実行する．melonの値段が100000になっていればupdateが成功している．

# 4. おまけ

## 4.1. jUnitでテストを行う。
- `multiple-dwr\src\test\java\jp\igakilab\dwr\multiple`に下記クラス（MultiplePrinterTest.java）を実装する
  * https://github.com/igakilab/multiple-dwr/blob/1975ac857be185d56cc7be74ad02eb75cf5cdfdb/src/test/java/jp/igakilab/dwr/multiple/MultiplePrinterTest.java
- vscodeの[ターミナル]が開いていればそこに，開いていない場合は[ターミナル]->[新しいターミナル]をクリックしてターミナルを開く．
- 恐らくpowershellが開くので，ターミナル画面でEnterしてから，`gradle test`と入力してEnterする
* 期待されないテスト結果が返ってきたものがエラーと表示される。
  * 失敗するテストケースが一つある．何が問題か確認し，修正してみること．
  - There were failing tests. See the report at: file:///C:/Users/???????????????/multiple-dwr/build/reports/tests/test/index.html と表示されるので，対象のファイルをブラウザで開いてみるとどこでエラーが発生したのかが分かる．
- 自分の作成したJavaのプログラムが正常に動作するかを確認したり，バグが発生したところをちゃんと直せたか確認したりするのに有用なので，積極的に使ってみると良い．
