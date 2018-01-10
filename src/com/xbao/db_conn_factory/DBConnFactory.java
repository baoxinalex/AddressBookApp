package com.xbao.db_conn_factory;

import com.xbao.services.ContactSvc;

public interface DBConnFactory {
    ContactSvc getContactSvc();
}
