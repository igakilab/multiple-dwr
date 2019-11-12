package jp.igakilab.dwr.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class ProductPrinter {
  SqlSessionFactory factory = DBUtility.getSqlSessionFactory();

  public List<Food> execute() {
    List<Food> foodList = new ArrayList<>();
    try (SqlSession session = factory.openSession()) {
      foodList = session.selectList("igakilab.mybatis.ProductMapper.selectFood");
      for (Food f : foodList) {
        System.out.println(f.getName());
        System.out.println(f.getPrice());
      }
    }
    return foodList;
  }

  /**
   * Foodクラスと同じデータ構造のオブジェクトをJSから受け取り，拡張for文で1つずつinsertする例
   *
   * @param foodList
   */
  public void insertFood(ArrayList<Food> foodList) {
    try (SqlSession session = factory.openSession()) {
      for (Food f : foodList) {
        int ret = session.insert("igakilab.mybatis.ProductMapper.insertFood", f);// 1つずつinsert
        System.out.println("Return:" + ret);
        System.out.println(f.getName());
        System.out.println(f.getPrice());
      }
      session.commit();// これを呼び出すと書き込まれる
    }
  }

  public static void main(String[] args) {
    ProductPrinter pp = new ProductPrinter();
    pp.execute();
  }

}
