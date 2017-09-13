package com.likecode.common.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public final class FileUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * Spring MVC上传文件
     */
    public static void uploadFileSpringMVC(final FileItem file, final File target) {
        try {
            if (file != null) {
                createParentDirs(target);
                file.write(target);
            }
        } catch (Exception e) {
            LOGGER.error("上传文件错误", e);
        }
    }

    /**
     * Spring MVC 图片上传处理 进行压缩（png图片不压缩处理）
     */
    public static void uploadImageWithCompress(final FileItem file, final File target, int size) {
        try {
            if (file != null && ImageIO.read(file.getInputStream()) != null) {
                createParentDirs(target);
                file.write(target);
                if (!file.getFieldName().toUpperCase().endsWith("PNG")) {
                    ImageUtil.resizeFixByWidth(target, size);
                }
            }
        } catch (Exception e) {
            LOGGER.error("上传文件错误", e);
        }
    }

    /**
     * 移动图片 处理 进行压缩（png图片不压缩处理）
     */
    public static void moveImageWithCompress(final File file, final File target, int size) {
        try {
            if (file != null) {
                createParentDirs(target);
                FileUtils.copyFile(file, target);
                ImageUtil.resizeFixByWidth(target, size);
                FileUtils.forceDeleteOnExit(file); // 移动文件之后，把原来的文件删除
            }
        } catch (IOException e) {
            LOGGER.error("上传文件错误", e);
        }
    }

    /**
     * 生成图片对应的缩略图
     */
    public static void createThumbnail(final File file, int size) {
        try {
            if (file != null) {
                File target = new File(file.getAbsolutePath() + size);
                FileUtils.copyFile(file, target);
                ImageUtil.resizeFixByWidth(target, size);
            }
        } catch (IOException e) {
            LOGGER.error("上传文件错误", e);
        }
    }

    /**
     * Spring MVC 图片上传处理 进行压缩（png图片不压缩处理）
     */
    public static void uploadImageWithCompress(final File target, int size) {
        try {
            if (target != null && !target.getName().toUpperCase().endsWith("PNG")) {
                ImageUtil.resizeFixByWidth(target, size);
            }
        } catch (Exception e) {
            LOGGER.error("上传文件错误", e);
        }
    }

    /**
     * 创建父路径
     */
    public static boolean createParentDirs(final File target) {
        if (target != null && target.getParentFile() != null && !target.getParentFile().exists()) {
            boolean success = target.getParentFile().mkdirs();
            return success;
        }
        return true;
    }

    public static void download(HttpServletResponse response, File file, String downLoadName) throws IOException {
        BufferedOutputStream output = null;
        BufferedInputStream input = null;
        try {
            if (file.getName().toLowerCase().endsWith("xls") || file.getName().toLowerCase().endsWith("xlsx")) {
                response.setContentType("application/msexcel;charset=UTF-8");
            } else if (file.getName().endsWith("png")) {
                response.setContentType("application/octet-stream;charset=UTF-8");
            }
            // 文件名
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(downLoadName.getBytes("gbk"), "iso8859-1"));
            response.setContentLength((int) file.length());
            byte[] buffer = new byte[4096];// 缓冲区
            output = new BufferedOutputStream(response.getOutputStream());
            input = new BufferedInputStream(new FileInputStream(file));
            int n = -1;
            // 遍历，开始下载
            while ((n = input.read(buffer, 0, 4096)) > -1) {
                output.write(buffer, 0, n);
            }
            output.flush(); // 不可少
            response.flushBuffer();// 不可少
        } catch (Exception e) {
            e.printStackTrace();
            // 异常自己捕捉
        } finally {
            // 关闭流，不可少
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        }
    }

    public static File getFile(String pathname) {
        File file = new File(pathname);
        createParentDirs(file);
        return file;
    }

    public static boolean isExist(String pathname) {
        File file = new File(pathname);
        return (file != null && file.exists());
    }

}
