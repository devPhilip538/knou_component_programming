package com.springbook.biz.user2.impl;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user2.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/* 오승필/202184-010073 */
@Repository("userDAO")
public class UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    // JDBC 관련 변수
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    /* 오승필/202184-010073 */
    // SQL 명령어들
    private final String USER_GET = "select * from users where id=? and password=?";

    /* 오승필/202184-010073 */
    // CRUD 기능의 메소드 구현
    public UserVO getUser(UserVO vo){
        UserVO user = null;
        try {
            logger.info("===> JDBC로 getUser() 기능 처리");
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_GET);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());
            rs = stmt.executeQuery();

            if(rs.next()){
                user = new UserVO();
                user.setId(rs.getString("ID"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setName(rs.getString("NAME"));
                user.setRole(rs.getString("ROLE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        } return user;
    }
}
