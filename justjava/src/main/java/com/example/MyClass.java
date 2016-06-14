package com.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyClass {

    public static void main(String[] args) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader bd = null;
        try {
            fis = new FileInputStream("/home/hx/dev/huawei/apps.txt");
            isr = new InputStreamReader(fis);
            bd = new BufferedReader(isr);
            String line = null;
            String cmd = null;
            int len = -1;
            byte[] buf = new byte[1024];
            while ( (line = bd.readLine()) != null ) {
                cmd = "adb pull /system/app/" + line + " /home/hx/dev/huawei/";
                System.out.println( cmd );
                ProcessBuilder builder = new ProcessBuilder(
                        "adb",
                        "pull",
                        "/system/app/" + line,
                        "/home/hx/dev/huawei/"
                        );
                builder.redirectErrorStream(true);
                Process process = builder.start();
                InputStream is = process.getInputStream();
                try {
                    len = -1;
                    while ( (len = is.read(buf)) != -1 ) {
                        System.out.println( new String(buf, 0, len) );
                    }
                } catch (IOException e) {}
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fis ) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != isr ) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bd ) {
                try {
                    bd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
