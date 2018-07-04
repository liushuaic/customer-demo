/** 
 * <pre>项目名称:ssme-bigeye 
 * 文件名称:ImportExcel.java 
 * 包名:com.jk.util 
 * 创建日期:2018年6月14日下午2:52:24 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/** 
 * <pre>项目名称：ssme-bigeye    
 * 类名称：ImportExcel    
 * 类描述：    
 * 创建人：戴履龙 
 * 创建时间：2018年6月14日 下午2:52:24    
 * 修改人：戴履龙 
 * 修改时间：2018年6月14日 下午2:52:24    
 * 修改备注：       
 * @version </pre>    
 */
public class ImportExcel {
	
	private POIFSFileSystem fs;
	
	private HSSFWorkbook wb;
	
	private HSSFSheet sheet;
	
	private HSSFRow row;
	/**
	 * <pre>readExcelTitel(读取Excel表格表头内容)   
	 * 创建人：戴履龙    
	 * 创建时间：2018年3月9日 下午9:40:19    
	 * 修改人：戴履龙      
	 * 修改时间：2018年3月9日 下午9:40:19    
	 * 修改备注： 
	 * @param is
	 * @return String 表头内容的数组
	 */
	public String[] readExcelTitel(InputStream is){
		
		try {
			
			fs =new POIFSFileSystem(is);
			
			wb=new HSSFWorkbook(fs);
		} catch (Exception e) {
			
			System.out.println("错误异常1");
			
			// TODO: handle exception
		}
		
		sheet= wb.getSheetAt(0);
		
		//的到首行;
		row=sheet.getRow(0);
		// 标题总列数
		int colNum  = row.getPhysicalNumberOfCells();
		
		String[] title =new String[colNum];
		
		for (int i = 0; i < colNum; i++) {
			
			title[i] = getCellFormatValue(row.getCell((short)i));
			
		}
		
		return title;
	}
	/**
	 * <pre>readExcelContent(读取Excel数据内容)   
	 * 创建人：戴履龙    
	 * 创建时间：2018年3月9日 下午11:06:14    
	 * 修改人：戴履龙       
	 * 修改时间：2018年3月9日 下午11:06:14    
	 * 修改备注： 
	 * @param is
	 * @return</pre>
	 */
	public Map<Integer,String> readExcelContent(InputStream is){
		
		Map<Integer, String > content=new HashMap<Integer, String>();
		
		String str="";
		
		try {
			
			fs=new POIFSFileSystem(is);
			
			wb =new HSSFWorkbook(fs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sheet=wb.getSheetAt(0);
		//得到总行数
		int rowNum = sheet.getLastRowNum();
		
		//由于第0行和第一行已经合并了  在这里索引从2开始
		row=sheet.getRow(2);
		//获取每行的列数
		int colNum = row.getPhysicalNumberOfCells();
		//正文从第二行开始  第一行为表头
		for (int i = 0; i <= rowNum; i++) {
			
			row=sheet.getRow(i);
			
			int j=0;
			
			while (j<colNum) {
				//拼接行内数据
				str+=getCellFormatValue(row.getCell((short)j)).trim()+",";
				
				j++;
			}
			//将每行的数据存入一个Map集和中
			content.put(i, str);
			
			str="";
		}
		return content;
		
	}
	
	/**
	 * <pre>getDateCellValue(获取单元格数据类型为日期类型的数据)   
	 * 创建人：戴履龙    
	 * 创建时间：2018年3月9日 下午11:22:58    
	 * 修改人：戴履龙       
	 * 修改时间：2018年3月9日 下午11:22:58    
	 * 修改备注： 
	 * @param cell
	 * @return result(单元格数据内容)
	 */
	private String getDateCellValue(HSSFCell cell){
		
		String result="";
		
		try {
			
			int cellType=cell.getCellType();
			
			if (cellType== HSSFCell.CELL_TYPE_NUMERIC) {
				
				Date date=cell.getDateCellValue();
				
				result=(date.getYear()+1900)+","+(date.getMonth()+1)+","+(date.getDate());
			}else if (cellType== HSSFCell.CELL_TYPE_STRING) {
				
				String date=getStringCellValue(cell);
				
				result=date.replaceAll("[年月]", ",").replace("日", "").trim();
				
			}else if (cellType ==HSSFCell.CELL_TYPE_BLANK) {
				
				result="";
			}
			
		} catch (Exception e) {
			System.out.println("日期格式不正确");
			
			// TODO: handle exception
		}
		
		return result;
	}
	
	/**
	 * <pre>getStringCellValue(获取单元格数据内容为字符串类型的数据)   
	 * 创建人：戴履龙    
	 * 创建时间：2018年3月9日 下午10:12:03    
	 * 修改人：戴履龙       
	 * 修改时间：2018年3月9日 下午10:12:03    
	 * 修改备注： 
	 * @param cell
	 * @return String 单元格内容 
	 */
	private String getStringCellValue(HSSFCell cell){
		
		String strCell="";
		
		switch (cell.getCellType()) {
			
		case HSSFCell.CELL_TYPE_STRING:   //判断内容是否为String类型
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());//判断内容是否为Number类型
			break; 
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());//判断内容是否为Boolean类型
			break;
		case HSSFCell.CELL_TYPE_BLANK:   //不知道
			strCell="";
			break;
		default :
			strCell="";
		}
		if (strCell.equals("") && strCell==null) {
			return "";
		}
		if (cell==null) {
			return "";
		}
		
		return strCell;
	}
	
	
	
	
	
    /**
     * <pre>getCellFormatValue(根据HSSFCell 类型设置数据)   
     * 创建人：戴履龙  
     * 创建时间：2018年3月9日 下午11:20:20    
     * 修改人：戴履龙       
     * 修改时间：2018年3月9日 下午11:20:20    
     * 修改备注： 
     * @param cell
     * @return</pre>
     */
    private String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                    
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }
    /**
     * <pre>inputExcelTitle(读出表头)   
     * 创建人：戴履龙   
     * 创建时间：2018年3月9日 下午11:50:05    
     * 修改人：戴履龙       
     * 修改时间：2018年3月9日 下午11:50:05    
     * 修改备注： 
     * @param path(Excel 表格路径)
     * @return title (表头集和数组)
     */
    public static String[] inputExcelTitle(String path){
    	String[] title=null;
    	
		try {
			InputStream is = new FileInputStream(path);
			
			ImportExcel imp=new ImportExcel();
			
			title=imp.readExcelTitel(is);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return title;
		
    }
    /**
     * <pre>inputExcel(读出数据)   
     * 创建人：戴履龙    
     * 创建时间：2018年3月10日 上午12:15:43    
     * 修改人：戴履龙       
     * 修改时间：2018年3月10日 上午12:15:43    
     * 修改备注： 
     * @return</pre>
     */
    public static Map<Integer, String> inputExcel(String path){
    	
    	Map<Integer, String> map=null;
		
		try {
			// 对读取Excel表格内容测试
			
			InputStream is2 = new FileInputStream(path);
			
			ImportExcel imp=new ImportExcel();
			
			map= imp.readExcelContent(is2);
	    				
	    	//这里由于xls合并了单元格需要对索引特殊处理
	    				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
		return map;
    }
    
    /*public static void main(String[] args) {
		
		try {
			// 对读取Excel表格标题测试
			
			InputStream is = new FileInputStream("E:/360/CellFormatExcel.xls");
			
			ImportExcel imp=new ImportExcel();
			
			String[] title=imp.readExcelTitel(is);
			
			for (String s : title) {
				System.out.println(s+",");
			}
			
			System.out.println("------------------------");
			
			// 对读取Excel表格内容测试
			
			InputStream is2=new FileInputStream("E:/360/CellFormatExcel.xls");
			
			Map<Integer, String> map= imp.readExcelContent(is2);
			
			System.out.println("获取Excel表格的内容");
			
			//这里由于xls合并了单元格需要对索引特殊处理
			
			for (int i = 2; i <= map.size()+1; i++) {
				System.out.println(map.get(i));
			}
			
		} catch (FileNotFoundException e) {
			
			System.out.println("异常错误");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}*/
}
