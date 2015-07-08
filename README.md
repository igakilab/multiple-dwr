# 2015年度版 演習

* 1から100までのすべての数値について，以下の条件を満たしながら小さい順にリストに追加する
  executeメソッドを完成させよ．
    * 条件1：数値が3の倍数のときは”Cloud”をリストに追加する．
    * 条件2：数値が5の倍数のときは”Spiral”をリストに追加する．
    * 条件3：数値が3の倍数でも5の倍数でもあるときは”CloudSpiral”をリストに追加する．
      なお，この条件は条件1,2に優先する．
    * 条件4：数値が3の倍数でも5の倍数でもないときは，その数値の10進表現を文字列に変換したものをリストに追加する．
  
* 実行例
    [1, 2, Cloud, 4, Spiral, Cloud, 7, 8, Cloud, Spiral, 11, Cloud, 13, 14, CloudSpiral, 16...


## 2015年度版 課題

* 演習で作成したCloudSpiralPrinterクラスについて，以下の機能拡張を順に実施し，
  SVN リポジトリーにコミットせよ．ただし，機能拡張を1 つ実施するごとにコミットすること．
    * 拡張1：リスト化する範囲を，2進表現で8 桁になるものとする．
    * 拡張2：条件4において，数値の2進表現をリストに追加する．
    * 拡張3：条件4において，数値が素数のときはその数値の10 進表現のみをリストに追加する．

# コンパイル方法

* javac -d target src/jp/enpit/cloud/csprinter/CloudSpiralPrinter.java

# 実行方法

* java -classpath target jp.enpit.cloud.csprinter.CloudSpiralPrinter

