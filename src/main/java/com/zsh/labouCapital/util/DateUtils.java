package com.zsh.labouCapital.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateUtils {
    /**
     * 分组函数
     * 
     * @param groupFields
     *          分组字段
     * @param sumFields　
     *          合计字段
     * @param avgFields
     *          平均值字段
     * @return
     */
    public List<Map<String,Object>>  group(List<Map<String,Object>> datas,String[] groupFields,String[] sumFields,String[][] avgFields){
        return group(datas,groupFields,sumFields,avgFields,null);
    }

 
    
    /**
     * 分组函数
     * 
     * @param groupFields
     *          分组字段
     * @param sumFields　
     *          合计字段
     * @param avgFields
     *          平均值字段
     * @param countField
     *          分组后的count新字段
     * @return
     */
    public List<Map<String,Object>>  group(List<Map<String,Object>> datas,String[] groupFields,String[] sumFields,String[][] avgFields,String countField){
        Map<String,Object> temp = new HashMap<String,Object>();
        for (Map<String,Object> data : datas) {
            String groupFieldKey =  "";
            for (String groupField : groupFields) {
                //code_name_   即 code_001|name_test|       code_003|name_test1|
                groupFieldKey+=groupField+"_"+data.get(groupField)+"|";
            }
            //算分组合计
            groupSum(sumFields, temp, data, groupFieldKey);
            //统计分组行数
            countField = groupCount(countField, temp, groupFieldKey);
            //计算平均
            groupAvg(avgFields, countField, temp, groupFieldKey);
        }
        //转换结果
        List<Map<String, Object>> results = covertResult(temp);
        return results;
    }
    /**
     * 分组函数(无平均)
     * 
     * @param groupFields
     *          分组字段
     * @param sumFields　
     *          合计字段

   
     * @return
     */
    public List<Map<String,Object>>  groupNoAvg(List<Map<String,Object>> datas,String[] groupFields,String[] sumFields){
        Map<String,Object> temp = new HashMap<String,Object>();
        for (Map<String,Object> data : datas) {
            String groupFieldKey =  "";
            for (String groupField : groupFields) {
                //code_name_   即 code_001|name_test|       code_003|name_test1|
                groupFieldKey+=groupField+"_"+data.get(groupField)+"|";
            }
            //算分组合计
            groupSum(sumFields, temp, data, groupFieldKey);
           
        
        }
        //转换结果
        List<Map<String, Object>> results = covertResult(temp);
        return results;
    }

    private void groupAvg(String[][] avgFields, String countField,
            Map<String, Object> temp, String groupFieldKey) {
        for (int i = 0; i < avgFields.length; i++) {
            for (int j = 0; j < avgFields.length; j++) {
                double value = (Double) temp.get(groupFieldKey+avgFields[i][0]);
                int count =  (Integer) temp.get(groupFieldKey+countField);
                temp.put(groupFieldKey+avgFields[i][1], value/count);
            }
        }
    }

    /**
     * 统计分组后的条数
     * @param countField
     * @param temp
     * @param groupFieldKey
     */
    private String groupCount(String countField, Map<String, Object> temp,
            String groupFieldKey) {
        if(countField == null || countField.trim().equals(""))countField = "&count";
        String countFieldKey = groupFieldKey+countField;
        if(temp.containsKey(countFieldKey)){
            temp.put(countFieldKey, Integer.parseInt(temp.get(countFieldKey).toString())+1);
        }else{
            temp.put(countFieldKey, 1);
        }
        return countField;
    }

    /**
     * 将temp中的key value 转换为List<Map<String,Object>>对像
     * @param temp
     * @return
     */
    private List<Map<String, Object>> covertResult(Map<String, Object> temp) {
        Map<String,Object> values = new HashMap<String,Object>();
        for (String key : temp.keySet()) {
            if(!values.containsKey(key.substring(0, key.lastIndexOf("|")))){
                //code_001|name_test|quantity_10
                values.put(key.substring(0, key.lastIndexOf("|")), key+"_"+temp.get(key));
                continue;
            }
            for (String rk : values.keySet()) {
                if(key.startsWith(rk)){
                    //code_001|name_test|quantity_10|amount_20
                    values.put(rk, values.get(rk)+key.substring(key.lastIndexOf("|"))+"_"+temp.get(key));
                    break;
                }
            }
        }
        List<Map<String,Object>> results = new ArrayList<Map<String,Object>>();
        Map<String,Object> result;
        for (Object key : values.values()) {
            result = new HashMap<String,Object>();
            String ss[] =  key.toString().split("\\|");
            for (String t : ss) {
                String[] ts = t.split("_");
                result.put(ts[0], ts[1]);
            }
            results.add(result);
        }
        return results;
    }

    /**
     * 计算合计
     * @param sumFields
     * @param temp
     * @param data
     * @param groupFieldKey
     */
    private void groupSum(String[] sumFields, Map<String, Object> temp,
            Map<String, Object> data, String groupFieldKey) {
        for (String sumField : sumFields) {
            String groupFieldKey_sumField =  groupFieldKey+sumField;
            //code_name_quantity  code_name_amount  
            //即code_001|name_test|quantity  code_001|name_test|amount  code_003|name_test1|amount
            if(temp.containsKey(groupFieldKey_sumField)){
                temp.put(groupFieldKey_sumField, Double.parseDouble(data.get(sumField).toString())+Double.parseDouble(temp.get(groupFieldKey_sumField).toString()));
            }else{
                temp.put(groupFieldKey_sumField, Double.parseDouble(data.get(sumField).toString()));
            }
        }
    }
}
