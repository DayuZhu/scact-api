package com.sc.act.api.commons.web.util;

import com.google.common.io.Resources;
import com.sc.act.api.commons.web.constant.CommonConstant;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * 针对Jar包内的文件的工具类.
 * <p>
 * 1.ClassLoader
 * <p>
 * 不指定contextClas时，优先使用Thread.getContextClassLoader()， 如果未设置则使用Guava Resources的ClassLoader
 * <p>
 * 指定contextClass，则直接使用该contextClass的ClassLoader.
 * <p>
 * 2.路径
 * <p>
 * 不指定contextClass时，按URLClassLoader的实现, 从jar file中查找resourceName，
 * <p>
 * 所以resourceName无需以"/"打头即表示jar file中的根目录，带了"/" 反而导致JarFile.getEntry(resouceName)时没有返回.
 * <p>
 * 指定contextClass时，class.getResource()会先对name进行处理再交给classLoader，打头的"/"的会被去除，不以"/"打头则表示与该contextClass package的相对路径,
 * 会先转为绝对路径.
 * <p>
 * 3.同名资源
 * <p>
 * 如果有多个同名资源，除非调用getResources()获取全部资源，否则在URLClassLoader中按ClassPath顺序打开第一个命中的Jar文件.
 */
public class ResourceUtil {

    // 打开单个文件////

    /**
     * 读取规则见本类注释.
     */
    public static URL asUrl(String resourceName) {
        return Resources.getResource(resourceName);
    }

    /**
     * 读取规则见本类注释.
     */
    public static URL asUrl(Class<?> contextClass, String resourceName) throws IOException {
        return Resources.getResource(contextClass, resourceName);
    }

    /**
     * 读取规则见本类注释.
     */
    public static InputStream asStream(String resourceName) throws IOException {
        return Resources.getResource(resourceName).openStream();
    }

    /**
     * 读取文件的每一行，读取规则见本类注释.
     */
    public static InputStream asStream(Class<?> contextClass, String resourceName) throws IOException {
        return Resources.getResource(contextClass, resourceName).openStream();
    }

    ////// 读取单个文件内容／／／／／

    /**
     * 读取文件的每一行，读取规则见本类注释.
     */
    public static String toString(String resourceName) throws IOException {
        return Resources.toString(Resources.getResource(resourceName), CommonConstant.UTF_8);
    }

    /**
     * 读取文件的每一行，读取规则见本类注释.
     */
    public static String toString(Class<?> contextClass, String resourceName) throws IOException {
        return Resources.toString(Resources.getResource(contextClass, resourceName), CommonConstant.UTF_8);
    }

    /**
     * 读取文件的每一行，读取规则见本类注释.
     */
    public static List<String> toLines(String resourceName) throws IOException {
        return Resources.readLines(Resources.getResource(resourceName), CommonConstant.UTF_8);
    }

    /**
     * 读取文件的每一行，读取规则见本类注释.
     */
    public static List<String> toLines(Class<?> contextClass, String resourceName) throws IOException {
        return Resources.readLines(Resources.getResource(contextClass, resourceName), CommonConstant.UTF_8);
    }


}