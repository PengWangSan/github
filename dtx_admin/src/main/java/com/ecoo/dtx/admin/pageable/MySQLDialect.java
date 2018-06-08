package com.ecoo.dtx.admin.pageable;

public class MySQLDialect extends Dialect {
	
	final static String LIMIT_SQL_PATTERN = "%s limit %s, %s";
	
	final static String LIMIT_SQL_PATTERN_FIRST = "%s limit %s";
	
	@Override
	public String getLimitString(String sql, int offset, int limit) {
		if (offset == 0) {
			return String.format(LIMIT_SQL_PATTERN_FIRST, sql, limit);
		} 

		return String.format(LIMIT_SQL_PATTERN, sql, offset, limit);
	}

}
