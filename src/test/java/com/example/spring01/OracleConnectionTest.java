package com.example.spring01;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OracleConnectionTest {
	//import org.slf4j.Logger;
	private static final Logger logger = LoggerFactory.getLogger(OracleConnectionTest.class);
	
	private static final String Driver  = "oracle.jdbc.driver.OracleDriver";
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	private static final String USER = "spring";
	
	private static final String PW = "1234";

	@Test // JUnit이 테스트하는 method --> @Test : JUnit이라는 테스트 프로그램이 테스트하게 됨
	public void test() throws Exception {
		//fail("Not yet implemented");
		Class.forName(Driver);//드라이버 로딩
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PW)){
			logger.info("오라클에 연결되었습니다.");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
