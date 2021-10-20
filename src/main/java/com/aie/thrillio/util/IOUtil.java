package com.aie.thrillio.util;

import java.io.*;

public class IOUtil {
    public static void read(String[] data, String fileName){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"))){
            String line;
            int count = 0;
            while ((line = br.readLine()) != null){
                data[count] = line;
                count++;
            }
        }
        catch (UnsupportedEncodingException | FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
