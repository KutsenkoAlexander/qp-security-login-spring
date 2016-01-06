package ua.vza.kay.qp.utils;

import java.io.File;

/**
 * Created by Aleksandr on 21.07.15.
 */
public class DefineOS {
    public static String osDefine(){
        File path = null;
        String os = System.getProperty("os.name").toLowerCase();
        if(os.indexOf( "win" ) >= 0){
            if(new File("D:\\UploadsQP\\").exists()){
                return "D:\\UploadsQP\\";
            }
            else {
                new File("D:\\UploadsQP\\").mkdir();
                return "D:\\UploadsQP\\";
            }
        }else if(os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0){
            if(new File("/payara41/UploadsQP/").exists()){
                return "/payara41/UploadsQP/";
            }
            else {
                if(new File("/payara41/UploadsQP/").mkdir())
                    return "/payara41/UploadsQP/";
                else return null;
            }
        }else{
            return null;
        }
    }
}
