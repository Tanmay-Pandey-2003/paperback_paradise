package in.code.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
	private JdbcUtil() {
	}

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getJdbcConnection() throws SQLException, IOException {
		// Physical Loading and getting Connection
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "system";
        String password = "12345";

        Connection con = DriverManager.getConnection(url, user, password);
		return con;

	}

	public static void cleanUp(Connection con, Statement st, ResultSet rs) throws SQLException {
		if (con != null)
			con.close();
		if (st != null)
			st.close();
		if (rs != null)
			rs.close();
	}
}