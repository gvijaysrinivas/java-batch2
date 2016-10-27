<<<<<<< HEAD
package com.fdm.wealthnow.common;
=======
package src.com.fdm.wealthnow.common;
>>>>>>> branch 'master' of https://github.com/badrivasudevan/java-batch2.git

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class DBConnectionFactory {
    private static final int POOL_SIZE = 5;

    public enum ConnectionType {
        ORACLE,
        ORACLE_POOLED
    }

    private static HashMap<ConnectionType, Connector> connectors = new HashMap<>();

    private static OracleConnector oracleConnector =
            new OracleConnector(
                    "jdbc:oracle:thin:@159.100.176.123:1521:XE",
                    "fdm10",
                    "fdm10");

    static {
        connectors.put(ConnectionType.ORACLE, oracleConnector);
    }


    public static Connection getConnection(ConnectionType connectionType)
            throws SQLException {

          Connector connector = connectors.get(connectionType);
          return connector.getConnection();
    }

    public static void closeConnection(ConnectionType connectionType, Connection connection) {
        Connector connector = connectors.get(connectionType);
        connector.closeConnection(connection);
    }
}
