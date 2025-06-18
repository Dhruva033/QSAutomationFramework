package com.qs.utility;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadExcel {

    private static final Logger log = LoggerFactory.getLogger(ReadExcel.class);

    /**
     * This method helps to fetch the data from the Excel file  using fillo
     * @param sheetName<sheetName as String >
     * @return <Object [] []>
     */
    public static Object[][] getTestData(String sheetName) {
        Fillo fillo = new Fillo();
        List<Map<String, Object>> dataList = new ArrayList<>();
        try {
            Connection connection = fillo.getConnection(Constants.EXCEL_PROPERTIES_PATH);
            String query = "SELECT * FROM " + sheetName + " WHERE TestCaseID IS NOT NULL AND TestCaseID <> '' AND ScriptRun <> 'skip'";
            Recordset recordset = connection.executeQuery(query);
            List<String> fieldNames = recordset.getFieldNames();

            while (recordset.next()) {
                Map<String, Object> rowMap = new HashMap<>();

                for (String fieldName : fieldNames) {
                    rowMap.put(fieldName, recordset.getField(fieldName));
                }
                dataList.add(rowMap);
            }
            recordset.close();
            connection.close();
        } catch (Exception e) {
            log.error("Error while reading test data from sheet: {}", sheetName);
        }
        if (dataList.isEmpty()) {
            log.error("No test data found in sheet: {}", sheetName);

        }
        Object[][] dataArray = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i][0] = dataList.get(i);
        }
        return dataArray;
    }
}
