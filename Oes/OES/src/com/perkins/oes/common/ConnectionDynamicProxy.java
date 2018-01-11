package com.perkins.oes.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

import com.perkins.oes.AppContext;
import com.perkins.oes.Constants;
import com.perkins.oes.util.DButil;

public class ConnectionDynamicProxy implements InvocationHandler {

    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        ConnectionHolder connectionHolder = (ConnectionHolder) AppContext.getAppContext()
                .getObject(Constants.APP_CONTEXT_CONN);
        boolean needMyClose = false;
        boolean isCommitOrRollbackTran = false;

        if (connectionHolder == null) {
            Connection conn = DButil.getConnection();
            connectionHolder = new ConnectionHolder();
            connectionHolder.setConn(conn);
            if (method.getName().equals("save")) {
                DButil.setAutoCommit(conn, false);
                connectionHolder.setStartTran(true);
                isCommitOrRollbackTran = true;
            }

            AppContext.getAppContext().addObjects(Constants.APP_CONTEXT_CONN, connectionHolder);
            needMyClose = true;
        } else {
            if (method.getName().equals("save")) {
                if (!connectionHolder.isStartTran()) {
                    connectionHolder.setStartTran(true);
                    DButil.setAutoCommit(connectionHolder.getConn(), false);
                    isCommitOrRollbackTran = true;
                }
            }
        }
        try {
            result = method.invoke(target, args);
            if (method.getName().equals("save")) {
                if (isCommitOrRollbackTran) {
                    DButil.commit(connectionHolder.getConn());
                }

            }

        } catch (Throwable throwable) {
            if (method.getName().equals("save")) {
                if (isCommitOrRollbackTran) {
                    DButil.rollback(connectionHolder.getConn());
                }
            }

            throw throwable;
        }

        finally {
            if (needMyClose) {
                connectionHolder = (ConnectionHolder) AppContext.getAppContext()
                        .getObject(Constants.APP_CONTEXT_CONN);
                DButil.close(null, null, connectionHolder.getConn());
                AppContext.getAppContext().removeObject(Constants.APP_CONTEXT_CONN);
                connectionHolder.setConn(null);
                connectionHolder = null;
            }
        }

        return result;
    }

}
