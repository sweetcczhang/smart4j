package zcc.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by 张城城 on 2018/3/29.
 */
public final class ClassUtil {
    private static final Logger LOGGER= LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     * @return
     */
    public static ClassLoader getClassLoader(){

        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     * @param clasName
     * @param isInitialized
     * @return
     */
    public static Class<?> loadClass(String clasName, boolean isInitialized){
        Class<?> cls;
        try {
            cls = Class.forName(clasName, isInitialized, getClassLoader());
        }catch (ClassNotFoundException e){
            LOGGER.error("load class failure",e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    /**
     * 获取指定包下所有类
     * @param packageName
     * @return
     */
    public static List<Class<?>> getClassSet(String packageName){
        List<Class<?>> classSet = new ArrayList<Class<?>>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/"));
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                if(url!=null){
                    //获取协议名分别为file与jar
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")){
                        //若在class目录中，则执行添加类操作
                        String packagePath = url.getPath().replaceAll("%20"," ");
                        addClass(classSet, packagePath,packageName);
                    }else if(protocol.equals("jar")){
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection!=null){
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile!=null){
                                Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
                                while (jarEntryEnumeration.hasMoreElements()){
                                    JarEntry jarEntry = jarEntryEnumeration.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if(jarEntryName.equals(".class")){
                                        String className = jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/",".");
                                        doAddClass(classSet,className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void addClass(List<Class<?>> classSet, String packagePath, final String packageName){
        final File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return (pathname.isFile() && pathname.getName().endsWith(".class")) || pathname.isDirectory();
            }
        });
        for (File file : files){
            String fileName = file.getName();
            if (file.isFile()){
                String className = fileName.substring(0,fileName.lastIndexOf("."));
                if (StringUtil.isNotEmpty(className)){
                    className = packageName +"."+className;
                }
                doAddClass(classSet,className);
            }else {
                String subPackagePath = fileName;
                if(StringUtil.isNotEmpty(subPackagePath)){
                    subPackagePath = packagePath +"/"+subPackagePath;
                }
                String subPackageName = fileName;
                if(StringUtil.isNotEmpty(subPackageName)){
                    subPackagePath = packageName + "."+subPackageName;
                }
                addClass(classSet,subPackagePath,subPackagePath);
            }

        }
    }
    public static void doAddClass(List<Class<?>> classSet, String className){
        Class<?> cls = loadClass(className,false);
        classSet.add(cls);
    }

}
