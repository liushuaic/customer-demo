package com.jk.service;

import com.jk.model.Organization;
import com.jk.model.TreeModel;
import com.jk.model.User;

import java.util.List;

public interface IUserService {

    public List<User> queryList()throws Exception;

    List<TreeModel> queryOrgTree();

    List<Organization> queryGridTree(String pid) throws Exception;

    List<Organization> queryExportExcel() throws Exception;

    void addImportExcel(Organization pzwp) throws Exception;

    int deleteOrganization(String id);

    int saveOrganization(Organization organization);

    Organization queryOrganizationById(String id);

    int updateOrganization(Organization organization);
}
