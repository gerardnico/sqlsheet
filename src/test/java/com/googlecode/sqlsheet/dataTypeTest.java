package com.googlecode.sqlsheet;

import org.junit.Assert;
import org.junit.Test;

import java.sql.*;

/**
 * @author Klaus Hauschild
 * @since 6.6
 */
public class dataTypeTest {

    @Test
    public void dataTypeTest() throws Exception {

        Class.forName("com.googlecode.sqlsheet.Driver");


        final Connection conn = DriverManager.getConnection("jdbc:xls:file:" + ClassLoader.getSystemResource("dataType.xlsx").getFile() + "?readStreaming=no");
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery("SELECT * FROM datatype");
        Assert.assertEquals("We expect 7 columns", 7 , results.getMetaData().getColumnCount());

        ResultSetMetaData resultSetMetaData = results.getMetaData();

        // Data Type
        Assert.assertEquals("We expect for the first VARCHAR","VARCHAR", resultSetMetaData.getColumnTypeName(1));
        Assert.assertEquals("We expect for the second DATE","DATE", resultSetMetaData.getColumnTypeName(2));
        Assert.assertEquals("We expect for the third DOUBLE","DOUBLE", resultSetMetaData.getColumnTypeName(3));

        // Mixed data type
        Assert.assertEquals("We expect for the fourth VARCHAR","VARCHAR", resultSetMetaData.getColumnTypeName(4));

        // Null first
        Assert.assertEquals("We expect for the fifth  DATE","DATE", resultSetMetaData.getColumnTypeName(5));
        Assert.assertEquals("We expect for the sixth DOUBLE","DOUBLE", resultSetMetaData.getColumnTypeName(6));
        Assert.assertEquals("We expect for the seventh VARCHAR","VARCHAR", resultSetMetaData.getColumnTypeName(7));
        
        conn.close();
    }

}
