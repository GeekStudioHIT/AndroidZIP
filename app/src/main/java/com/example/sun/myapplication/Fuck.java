//package com.example.sun.myapplication;
//
//import org.apache.commons.compress.archivers.ArchiveEntry;
//import org.apache.commons.compress.archivers.zip.*;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
///**
// * Created by Sun on 2016/11/20.
// */
//public class Fuck {
//
//
//    public static int unzip(File inputZip, File outputFolder) throws IOException {
//        int count=0;
//        FileInputStream fis = null;
//        ZipArchiveInputStream zis = null;
//        FileOutputStream fos = null;
//        try {
//            byte[] buffer = new byte[8192];
//            fis = new FileInputStream(inputZip);
//            zis = new ZipArchiveInputStream(fis, "Cp1252", true); // this supports non-USACII names
//            ArchiveEntry entry;
//            while ((entry = zis.getNextEntry()) != null) {
//                File file = new File(outputFolder, entry.getName());
//                if (entry.isDirectory()) {
//                    file.mkdirs();
//                } else {
//                    count++;
//                    file.getParentFile().mkdirs();
//                    fos = new FileOutputStream(file);
//                    int read;
//                    while ((read = zis.read(buffer,0,buffer.length)) != -1)
//                        fos.write(buffer,0,read);
//                    fos.close();
//                    fos=null;
//                }
//            }
//        } finally {
//            try { zis.close(); } catch (Exception e) { }
//            try { fis.close(); } catch (Exception e) { }
//            try { if (fos!=null) fos.close(); } catch (Exception e) { }
//        }
//        return count;
//    }
//}
