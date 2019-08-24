package jp.igakilab.dwr.mybatis;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBUtility {
  private SqlSessionFactory sessionFactory;
  private static DBUtility dbutil = new DBUtility();

  private DBUtility() {
    sessionFactory = createSqlSessionFactory();
  }

  public static SqlSessionFactory getSqlSessionFactory() {
    return dbutil.sessionFactory;
  }

  private SqlSessionFactory createSqlSessionFactory() {
    SqlSessionFactory ssf = null;

    try {
      ssf = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return ssf;
  }
}
