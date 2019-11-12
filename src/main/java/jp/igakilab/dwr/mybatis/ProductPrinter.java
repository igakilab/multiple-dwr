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

  public void insertFood(ArrayList<Food> foodList) {
    for (Food f : foodList) {
      System.out.println(f.getName());
      System.out.println(f.getPrice());
    }
  }

  public static void main(String[] args) {
    ProductPrinter pp = new ProductPrinter();
    pp.execute();
  }

}
