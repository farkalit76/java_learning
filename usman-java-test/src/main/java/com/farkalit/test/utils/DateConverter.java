package com.farkalit.test.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateConverter {

  public static final String TOCONVERTDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	public static void main(String[] args) {
		convertDate("24-02-2021", "dd-MM-yyyy");
		convertDateFormat("24-02-2021", "dd-MM-yyyy", "dd/MM/yyyy");

        System.out.println(String.valueOf(new Date()));
        System.out.println(String.valueOf(LocalDateTime.now()));
        String timeStamp =
            DateTimeFormatter.ofPattern(TOCONVERTDATEFORMAT).format(LocalDateTime.now());
        System.out.println(timeStamp);

	}

	private static String convertDate(String dateStr, String inputFormat) {
		
		String convertedDt = "";
		try {
			DateFormat format = new SimpleDateFormat(inputFormat, Locale.ENGLISH);
			Date date = format.parse(dateStr);
		    DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
		    convertedDt = outputFormatter.format(date);
		    System.out.println("convertedDt:"+convertedDt);
		} catch (Exception e) {
			System.err.println("Date conversion/parsing error:"+e.getMessage());
		}
		return convertedDt;
	}
	
	private static String convertDateFormat(String dateStr, String inputFormat, String outputFormat) {
		
		String convertedDt = "";
		try {
			DateFormat format = new SimpleDateFormat(inputFormat, Locale.ENGLISH);
			Date date = format.parse(dateStr);
		    DateFormat outputFormatter = new SimpleDateFormat(outputFormat);
		    convertedDt = outputFormatter.format(date);
		    System.out.println("convertedDt:"+convertedDt);
		} catch (Exception e) {
			System.err.println("Date conversion/parsing error:"+e.getMessage());
		}
		return convertedDt;
	}

}
