package com.yyw.p2p.servicecore.util;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtil {

    private static NumberFormat numberFormat = NumberFormat.getNumberInstance();
    static {
        numberFormat.setGroupingUsed(false);
    }

    private static Integer cellNum = 0;

    public static <T> List<T> readExcel(Workbook workbook, InputStream inputStream, Class<T> clazz) throws Exception {
        ArrayList<T> list = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);

        //1.获取标题（即java实体类中的属性名）
        Row title = sheet.getRow(0);
        ArrayList<String> keys = new ArrayList<>();
        if (title!=null){
            //获取该行所有非空单元格的数量
            cellNum = title.getPhysicalNumberOfCells();

            for (int i = 0; i < cellNum; i++) {
                Cell cell = title.getCell(i);
                String value = getCellValue(cell);
                //将这些属性放入列表中
                keys.add(value);
            }
        }

        //2.遍历每一行数据，将该行的每一个单元格的值存储到map中
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowNum; i++) {
            Row row = sheet.getRow(i);
            HashMap map = new HashMap<>();

            for (int j = 0; j < cellNum; j++) {
                Cell cell = row.getCell(j);
                String cellValue = "";
                if (cell==null){
                    continue;
                }
                //获取到当前cell的数据
                cellValue = getCellValue(cell);
                map.put(keys.get(j),cellValue);
            }

            //将map转换成实体类对象
            T t = (T) mapToEntity(map, clazz);
            list.add(t);
        }

        return list;
    }

    private static String getCellValue(Cell cell){
        CellType cellType = cell.getCellType();
        String cellValue = "";

        switch (cellType) {
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)){ //如果是日期可以先将其转换成目标格式
                    Date date = cell.getDateCellValue();
                    cellValue = new SimpleDateFormat("MM-dd-yyyy").format(date);
                }else { //如果不是日期类型则通过numberFormat对数值进行格式化后返回字符串
                    cellValue = numberFormat.format(cell.getNumericCellValue());
                }
                break;
            //TODO: 还有很多其他类型需要判断
        }

        return cellValue;
    }

    //通过反射将map中的属性转换成实体类
    private static <T> T mapToEntity(Map<String,String> map, Class<T> entity) throws Exception{
        T t = null;
        try {
            t = entity.newInstance();
            for (Field field: entity.getDeclaredFields()){
                if (map.containsKey(field.getName())){
                    field.setAccessible(true);
                    //获取map中属性对应的值
                    String value = map.get(field.getName());
                    String type = field.getGenericType().toString();
                    if (type.equals("class java.lang.String")){
                        field.set(t,value);
                    } else if (type.equals("class java.lang.Double")) {
                        field.set(t,Double.parseDouble(String.valueOf(value)));
                    } else if (type.equals("class java.lang.Integer")) {
                        field.set(t,Integer.parseInt(String.valueOf(value)));
                    } else if (type.equals("class java.util.Date")) {
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(value);
                        field.set(t,date);
                    }
                }
                field.setAccessible(false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return t;
    }
}
