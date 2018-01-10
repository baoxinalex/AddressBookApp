package com.xbao.db_conn_factory;

import com.xbao.dao.ContactDAOImp;
import com.xbao.dao.FileSvc;
import com.xbao.services.ContactSvc;


public class FileConnFactory implements DBConnFactory {

    private FileSvc fs = new FileSvc();
    @Override
    public ContactSvc getContactSvc() {
        return new ContactSvc(new ContactDAOImp(fs));
    }
}
