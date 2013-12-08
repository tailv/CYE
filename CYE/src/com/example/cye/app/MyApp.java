package com.example.cye.app;

import android.app.Application;
import android.os.Environment;
import java.io.File;

public class MyApp extends Application {

	public static final String FILE_TEXT = "cye.txt";
	public static final String FOLDER_APP = "CYE";
	public static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
	        + File.separator + FOLDER_APP;

}
