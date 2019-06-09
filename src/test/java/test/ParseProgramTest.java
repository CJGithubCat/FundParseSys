package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.zsh.labouCapital.util.GenerateWorkDayUtil;
import com.zsh.labouCapital.util.UUIDUtils;

/**
 *<p> Title: ParseProgramTest </p>
 *<p> Description: </p>
 *<p> Copyright: openlo.cn Copyright (C) 2019 </p>
 *
 * @author HP
 * @version
 * @since 2019年6月7日
 */
public class ParseProgramTest {
    
    /**
     * @Title: generateFullYearDayList   
     * @Description: 生成全年的日历;   
     * @param:       
     * @return: void      
     * @throws
     */
    @Test
    public void generateFullYearDayList(){
        List<TCalender> calenderList = new ArrayList<>();
        for(int i=1;i<=12;i++){
            List<String> carList = GenerateWorkDayUtil.generateCalender(2019,i);
            for (String dateStr : carList) {
                try {
                    TCalender calender = new TCalender();
                    calender.setId(UUIDUtils.get());
                    calender.setDateStr(dateStr);
                    calender.setDateType(GenerateWorkDayUtil.isWorkDay(dateStr));
                    calenderList.add(calender);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }              
            }
        }
        try {
            if(calenderList != null && calenderList.size() > 0){
                insertDb(calenderList);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void insertDb(List<TCalender> calenderInfoList){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // MySQL的JDBC连接语句
        // URL编写格式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        String url = "jdbc:mysql://localhost:3306/fund?user=root&password=root";
        // 数据库执行的语句
        String sql = "insert into t_calender(id,date_str,date_type) values(?,?,?)";// 插入一条记录

        // 查询语句
        try {
            Class.forName("com.mysql.jdbc.Driver"); // 加载驱动
            conn = DriverManager.getConnection(url); // 获取数据库连接
            stmt = conn.prepareStatement(sql); // 创建执行环境
            for (TCalender tCalender : calenderInfoList) {
                stmt.setString(1, tCalender.getId());
                stmt.setString(2, tCalender.getDateStr());
                stmt.setInt(3, tCalender.getDateType());
                stmt.addBatch();
            }
            stmt.executeBatch(); // 执行SQL语句
            System.out.println("批量执行完毕");
        }
        catch (ClassNotFoundException e) {
            System.out.println("加载驱动异常");
            e.printStackTrace();
        }
        catch (SQLException e) {
            System.out.println("数据库异常");
            e.printStackTrace();
        }
        finally {
            try {
                if (rs != null)
                    rs.close(); // 关闭结果数据集
                if (stmt != null)
                    stmt.close(); // 关闭执行环境
                if (conn != null)
                    conn.close(); // 关闭数据库连接
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    class TCalender{
        public String id;
        public String dateStr;
        public int dateType;
        public String dateCreate;
        public String dateUpdate;
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getDateStr() {
            return dateStr;
        }
        public void setDateStr(String dateStr) {
            this.dateStr = dateStr;
        }
        public int getDateType() {
            return dateType;
        }
        public void setDateType(int dateType) {
            this.dateType = dateType;
        }
        public String getDateCreate() {
            return dateCreate;
        }
        public void setDateCreate(String dateCreate) {
            this.dateCreate = dateCreate;
        }
        public String getDateUpdate() {
            return dateUpdate;
        }
        public void setDateUpdate(String dateUpdate) {
            this.dateUpdate = dateUpdate;
        }
    }
}
