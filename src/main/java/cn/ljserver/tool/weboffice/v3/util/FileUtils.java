package cn.ljserver.tool.weboffice.v3.util;

import cn.ljserver.tool.weboffice.v3.exception.FileTypeNotSupport;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * 文件工具类
 */
public class FileUtils {
    // --------------------------表格---------------------------------
    public static final String S = "s";
    // --------------------------文档---------------------------------
    public static final String W = "w";
    // --------------------------演示---------------------------------
    public static final String P = "p";
    // --------------------------PDF---------------------------------
    public static final String F = "f";
    // --------------------------智能文档-----------------------------
    public static final String O = "o";
    // --------------------------多维表格-----------------------------
    public static final String D = "d";
    // --------------------------file template types---------------------------------
    public static final String[] templateTypes = {S, W, P, D, O};
    // --------------------------img convert types---------------------------------
    public static final String[] imgConvertToDocumentTypes = {"png", "jpg", "jpeg", "bmp", "tif", "tiff", "gif"};
    // --------------------------pdf or img convert---------------------------------
    public static final String[] convertToDocumentTypes = {"docx", "xlsx", "pptx"};
    // --------------------------document convert---------------------------------
    public static final String[] documentConvertTypes = {"docx", "xlsx", "pptx", "doc", "xls", "ppt", "pdf"};
    // --------------------------office convert---------------------------------
    public static final String[] excelToXlsxTypes = {"et", "ett", "xls", "xlt", "xlsx", "xlsm", "xltx", "xltm", "csv", "ets", "eto", "tpxls", "log"};
    public static final String[] wordToDocxTypes = {"doc", "docx", "dot", "dotx", "dotm", "rtf", "mht", "html", "txt", "xml"};
    public static final String[] powerPointToPptxTypes = {"ppt", "pptx", "pptm", "pptm", "ppsm", "pps", "potx", "potm", "dpt", "dps", "pot", "ppsx"};
    // --------------------------preview---------------------------------
    public static final String[] excelPreviewTypes = {"et", "xls", "xlt", "xlsx", "xlsm", "xltx", "xltm", "csv", "ett"};
    public static final String[] wordPreviewTypes = {"doc", "docx", "txt", "dot", "wps", "wpt", "dotx", "docm", "dotm", "xml", "rtf", "mht", "html"};
    public static final String[] powerPointPreviewTypes = {"ppt", "pptx", "pptm", "pptm", "ppsm", "pps", "potx", "potm", "dpt", "dps", "pot", "ppsx"};
    public static final String[] pdfPreviewTypes = {"pdf"};
    // --------------------------img type---------------------------------
    public static final String JPG = "jpg";
    public static final String PNG = "png";
    public static final String[] imgTypes = {JPG, PNG};
    public static final Map<String, String> officeTypes;

    static {
        final Map<String, String> map = new HashMap<>(48);

        // 表格
        Arrays.stream(excelPreviewTypes)
                .forEach(s -> map.put(s, S));

        // 文档
        Arrays.stream(wordPreviewTypes)
                .forEach(s -> map.put(s, W));

        // 演示
        Arrays.stream(powerPointPreviewTypes)
                .forEach(s -> map.put(s, P));

        // PDF
        Arrays.stream(pdfPreviewTypes)
                .forEach(s -> map.put(s, F));

        officeTypes = Collections.unmodifiableMap(map);
    }

    private FileUtils() {
    }

    /**
     * 匹配文件类型
     *
     * @param types     文件类型
     * @param inputType 输入类型
     */
    public static void typeMatchCheck(String[] types, String inputType) {
        boolean noneMatch = Arrays.stream(types)
                .noneMatch(type -> type.equalsIgnoreCase(inputType));
        if (noneMatch) {
            throw new FileTypeNotSupport();
        }
    }

    /**
     * 获取文件类型/后缀
     *
     * @param filePathOrUrl 文件路径或URL
     * @return 文件类型/后缀, 如doc，pdf等，不带 ”.“ 的后缀
     */
    public static String suffix(String filePathOrUrl) {
        // 提取路径或URL中的文件名部分
        String fileName = name(filePathOrUrl);

        // 获取文件扩展名
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        } else {
            throw new FileTypeNotSupport();
        }
    }

    /**
     * 获取文件名称。包含后缀
     *
     * @param filePathOrUrl 文件路径或URL
     * @return 文件名称+后缀, 如a.doc，a.pdf等
     */
    public static String name(String filePathOrUrl) {
        // 验证输入的路径或URL是否合法
        if (filePathOrUrl == null || filePathOrUrl.isEmpty()) {
            throw new FileTypeNotSupport();
        }
        // 提取路径或URL中的文件名部分
        String fileName;
        if (filePathOrUrl.contains("?")) { // 处理URL中可能含有的查询字符串
            fileName = filePathOrUrl.substring(filePathOrUrl.lastIndexOf('/') + 1, filePathOrUrl.indexOf('?'));
        } else {
            fileName = filePathOrUrl.substring(filePathOrUrl.lastIndexOf('/') + 1);
        }
        return fileName;
    }

    /**
     * 判断文件是否支持
     *
     * @param filename 文件名
     * @return 是否支持
     */
    public static boolean support(String filename) {
        final String ext = suffix(filename);
        return !ext.isEmpty() && officeTypes.containsKey(ext);
    }

    /**
     * 判断文件是否支持
     *
     * @param path 文件路径
     * @return 是否支持
     */
    public static boolean support(Path path) {
        return Optional.ofNullable(path)
                .map(Path::getFileName)
                .map(Path::toString)
                .map(FileUtils::support)
                .orElse(false);
    }

    /**
     * 获取文件类型
     *
     * @param filename 文件名
     * @return 文件类型
     */
    public static String officeType(String filename) {
        return Optional.of(suffix(filename))
                .filter(s -> !s.isEmpty())
                .map(officeTypes::get)
                .orElse("");
    }

    /**
     * 获取文件类型
     *
     * @param path 文件路径
     * @return 文件类型
     */
    public static String officeType(Path path) {
        return Optional.ofNullable(path)
                .map(Path::getFileName)
                .map(Path::toString)
                .map(FileUtils::officeType)
                .orElse("");
    }

    /**
     * 读取文件全部内容
     *
     * @param path 文件路径
     * @return 文件内容
     */
    @SneakyThrows
    public static byte[] readAllBytes(Path path) {
        return Files.readAllBytes(path);
    }

    /**
     * 获取文件大小
     *
     * @param path 文件路径
     * @return 文件大小
     */
    @SneakyThrows
    public static long size(Path path) {
        return Files.size(path);
    }

    /**
     * 删除文件
     *
     * @param path 文件路径
     */
    @SneakyThrows
    public static void delete(Path path) {
        Files.delete(path);
    }

    /**
     * 获取一个随机的 UUID
     *
     * @return 随机 UUID
     */
    public static String uuid() {
        // 使用 uuid 来生成临时文件的 id
        // 之前的方式会导致以下问题：
        // 重启后，文件都被重置了，会导致 Web Office 内出现错误
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
