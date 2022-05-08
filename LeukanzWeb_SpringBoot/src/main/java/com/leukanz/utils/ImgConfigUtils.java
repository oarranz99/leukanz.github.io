package com.leukanz.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ImgConfigUtils {
	 public static byte[] compress(byte[] data) throws IOException , DataFormatException {  
		   System.out.println("entra");
		   Deflater deflater = new Deflater();  
		   deflater.setInput(data);  

		   ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);   

		   deflater.finish();  
		   byte[] buffer = new byte[1024];   
		   while (!deflater.finished()) {  
		    int count = deflater.deflate(buffer); // returns the generated code... index  
		    outputStream.write(buffer, 0, count);   
		   }  
		   outputStream.close();  
		   byte[] output = outputStream.toByteArray();  

		   System.out.println("Original: " + data.length / 1024 + " Kb");  
		   System.out.println("Compressed: " + output.length / 1024 + " Kb");  
		   return output;  
		  }  

	  public static byte[] decompress(byte[] data) throws IOException, DataFormatException { 
		  ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		  Inflater inflater = new Inflater();
		    inflater.setInput(data);
		    final byte[] buffer = new byte[1024];
		    while (!inflater.finished()) {
		      int count = inflater.inflate(buffer);
		      outputStream.write(buffer, 0, count);
		    }
		    outputStream.close();
		  
		  byte[] output = outputStream.toByteArray();
		  System.out.println("Original: " + data.length);  
		  System.out.println("Compressed: " + output.length);  
		  return output;
			/*
			 * Inflater inflater = new Inflater(true); inflater.setInput(data);
			 * 
			 * ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			 * byte[] buffer = new byte[1024]; while (!inflater.finished()) { int count =
			 * inflater.inflate(buffer); outputStream.write(buffer, 0, count); }
			 * outputStream.close(); byte[] output = outputStream.toByteArray();
			 * 
			 * System.out.println("Original: " + data.length );
			 * System.out.println("Compressed: " + output.length); return output;
			 */
	  }  
	  
	  public static String getRealTimeDate() {
		  SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy'at' HH:mm:ss z");
		  Date date = new Date(System.currentTimeMillis());
		  System.out.println(formatter.format(date));
		  return date.toString();
	  }

}
