package com.fetching.storing.data.operation;

public class FindDataType {
	public boolean isInt(String str) {
		try {
			int i = Integer.parseInt(str);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}	
	}
	public boolean isFloat(String str) {
		if(str.contains(".")) {
			try {
				float i=Float.parseFloat(str);
				return true;
			}
			catch(NumberFormatException e) {
				return false;
			}
		}
		return false;
		
	}
	public boolean isLong(String str) {
		try {
			int i = Integer.parseInt(str);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}	
	}
	
}
