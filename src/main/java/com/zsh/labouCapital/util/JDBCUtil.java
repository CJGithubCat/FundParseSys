package com.zsh.labouCapital.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * Title: JDBCUtil
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: openlo.cn Copyright (C) 2019
 * </p>
 *
 * @author HP
 * @version
 * @since 2019年6月8日
 */
public class JDBCUtil {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/fund";
    private static final String user = "root";
    private static final String password = "root";

    static {
        try {
            Class.forName(driver);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet rs, PreparedStatement ps, Connection con) {
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                if (ps != null) {
                    try {
                        ps.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                    finally {
                        if (con != null) {
                            try {
                                con.close();
                            }
                            catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * @throws SQLException 
     * @Title: getPrepareStatement   
     * @Description: TODO   
     * @param: @return      
     * @return: PreparedStatement      
     * @throws
     */
    public static PreparedStatement getPrepareStatement(String sql) throws SQLException{
        //1.获取连接
        Connection connection = getConnection();
        PreparedStatement pStatement = connection.prepareStatement(sql);
        return pStatement;
    }
}
