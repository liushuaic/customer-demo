package com.jk.controller;

import com.jk.model.Organization;
import com.jk.model.TreeModel;
import com.jk.model.User;
import com.jk.service.IUserService;
import com.jk.util.ExportExcel;
import com.jk.util.ImportExcel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("userController")
public class UserController {


    @Resource
    private IUserService userService;
    private HttpServletResponse response;

    @RequestMapping("queryList")
    public ModelAndView queryList() throws Exception {
        ModelAndView mav = new ModelAndView();
      List<User> queryList = userService.queryList();
      mav.addObject("queryList",queryList);
        mav.setViewName("index");
             return mav;
    }


    @RequestMapping("queryOrgTree")
    @ResponseBody
    public List<TreeModel> queryOrgTree(){
        List<TreeModel> treelist = userService.queryOrgTree();
         return treelist;
    }

    @RequestMapping("queryGridTree")
    @ResponseBody
    public List<Organization> queryGridTree() throws Exception {
        String pid = "0";
        List<Organization> gridtree = queryGridTree(pid);
        return gridtree;
    }
    private List<Organization> queryGridTree(String pid) throws Exception {
        List<Organization> gridtree = userService.queryGridTree(pid);
        for(Organization organization : gridtree){
            String id = organization.getId();
            List<Organization> organizations = queryGridTree(id);
            if(organizations != null && organizations.size()>0){
                organization.setChildren(organizations);
            }
        }
        return gridtree;
    }

     @RequestMapping("ExportExcel")
     @ResponseBody
    public String ExportExcel() throws Exception{
        //new一个list集合用来接收数据库查出来的值
        List<Organization> list = new ArrayList<Organization>();
        //定义存储路径
        String path = "D:\\ftpserver\\aa.xls";
        //标头
        String title = "物品";
        //列名
        String[] rowName ={"id","ocode","text","onickname","otype","ofunctionary"};
        //从数据库中查询出数据
        list = userService.queryExportExcel();
        //new一个list集合放数据
        List<Object[]> arrlist = new ArrayList<Object[]>();
        //new一个object数组来接收数据
        Object[] objs = null;
        for (int i = 0; i < list.size(); i++){
            Organization pzwp = new Organization();
            pzwp = list.get(i);
            objs = new Object[rowName.length];
            objs[0] = pzwp.getId();
            objs[1] = pzwp.getOcode();
            objs[2] = pzwp.getText();
            objs[3] = pzwp.getOnickname();
            objs[4] = pzwp.getOtype();
            objs[5] = pzwp.getOfunctionary();
            arrlist.add(objs);
        }
        ExportExcel exportexcel = new ExportExcel(title,rowName,arrlist,path,response);
        try {
            exportexcel.export();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         return "1";
    }


    //导入
    @RequestMapping("importExcel")
    @ResponseBody
    public void importExcel()throws Exception{
        //读取文件
        String path = "D:\\ftpserver\\aa.xls";
        //读取头部
        String[] title = ImportExcel.inputExcelTitle(path);
        //读取内容
        Map<Integer, String> WupinMap = ImportExcel.inputExcel(path);
        //循坏读取的内容
        for (int i = 3;i<WupinMap.size(); i++) {
            Organization pzwp = new Organization();
            //取出每一列数据
            String str = WupinMap.get(i);
            //截取
            String str2  = str.substring(0,str.length()-1);
            //根据逗号分隔
            String[] str3 = str2.split(",");
            //循环数组
            for (int j = 0; j < str3.length; j++) {
                if(j==0){
                    pzwp.setId(str3[j]);
                }else if(j==1){
                    pzwp.setOcode(str3[j]);
                }else if(j==2) {
                    pzwp.setText(str3[j]);
                }else if(j==3) {
                    pzwp.setOcommand(str3[j]);
                }else if(j==4) {
                    pzwp.setPid(str3[j]);
                }else if(j==5){
                    pzwp.setOtype(str3[j]);
                }
            }
            userService.addImportExcel(pzwp);
        }
    }


    @RequestMapping("deleteOrganization")
    @ResponseBody
    public String deleteOrganization(String id){
        int i = userService.deleteOrganization(id);
        return "i";
    }

    @RequestMapping("saveOrganization")
    @ResponseBody
    public String saveOrganization(Organization organization){
     int i = userService.saveOrganization(organization);
        return "i";
    }

    @RequestMapping("queryOrganizationById")
    public String queryOrganizationById(String id, Model model){
        Organization organization = userService.queryOrganizationById(id);
        model.addAttribute("organization",organization);
        return "organization/update";
    }

    @RequestMapping("updateOrganization")
    @ResponseBody
    public Integer updateOrganization(Organization organization){
          int i = userService.updateOrganization(organization);
        return i;
    }



}
