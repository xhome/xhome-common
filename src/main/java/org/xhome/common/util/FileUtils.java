package org.xhome.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * @project xhome-util
 * @author jhat
 * @email cpf624@126.com
 * @date Nov 9, 20113:04:43 PM
 */
public final class FileUtils {
	
	private FileUtils() {}
	
	/**
	 * 重命名文件
	 * 
	 * @param filePath
	 *            待重命名的文件路径
	 * @param newName
	 *            新文件名
	 * @throws IOException
	 */
	public static void renameFile(String filePath, String newName)
			throws IOException {
		renameFile(new File(filePath), newName);
	}
	
	/**
	 * 重命名文件
	 * 
	 * @param file
	 *            待重命名的文件
	 * @param newName
	 *            新文件名
	 * @throws IOException
	 */
	public static void renameFile(File file, String newName) throws IOException {
		if (file == null) throw new IOException("Source must not be null");
		if (newName == null || newName.length() == 0) throw new IOException(
				"New file name must not be empty");
		if (!file.exists()) throw new IOException("Source is not exists");
		File newFile = new File(file.getParent(), newName);
		if (newFile.exists()) throw new IOException("The " + newFile.getPath()
				+ " is already exists");
		if (file.isFile()) org.apache.commons.io.FileUtils.copyFile(file,
				newFile);
		else {
			newFile.mkdirs();
			File[] files = file.listFiles();
			for (File f : files) {
				if (f.isFile()) org.apache.commons.io.FileUtils
						.copyFileToDirectory(f, newFile);
				else org.apache.commons.io.FileUtils.copyDirectoryToDirectory(
						file, newFile);
			}
		}
		deleteFile(file);
	}
	
	/**
	 * 移动文件
	 * 
	 * @param srcFile
	 *            源文件路径
	 * @param destFile
	 *            目的文件路径
	 * @throws IOException
	 */
	public static void moveFile(String srcFile, String destFile)
			throws IOException {
		moveFile(new File(srcFile), new File(destFile));
	}
	
	/**
	 * 移动文件
	 * 
	 * @param srcFile
	 *            源文件
	 * @param destFile
	 *            目的文件路径
	 * @throws IOException
	 */
	public static void moveFile(File srcFile, String destFile)
			throws IOException {
		moveFile(srcFile, new File(destFile));
	}
	
	/**
	 * 移动文件
	 * 
	 * @param srcFile
	 *            源文件路径
	 * @param destFile
	 *            目的文件
	 * @throws IOException
	 */
	public static void moveFile(String srcFile, File destFile)
			throws IOException {
		moveFile(new File(srcFile), destFile);
	}
	
	/**
	 * 移动文件
	 * 
	 * @param srcFile
	 *            源文件
	 * @param destFile
	 *            目的文件
	 * @throws IOException
	 */
	public static void moveFile(File srcFile, File destFile) throws IOException {
		copyFile(srcFile, destFile);
		deleteFile(srcFile);
	}
	
	/**
	 * 删除文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @throws IOException
	 */
	public static void deleteFile(String filePath) throws IOException {
		deleteFile(new File(filePath));
	}
	
	/**
	 * 删除文件
	 * 
	 * @param file
	 *            文件
	 * @throws IOException
	 */
	public static void deleteFile(File file) throws IOException {
		if (file.isDirectory()) org.apache.commons.io.FileUtils
				.deleteDirectory(file);
		else file.delete();
	}
	
	/**
	 * 复制文件
	 * 
	 * @param srcPath
	 *            源文件路径
	 * @param destPath
	 *            目标文件路径
	 * @throws IOException
	 */
	public static void copyFile(String srcPath, String destPath)
			throws IOException {
		copyFile(new File(srcPath), new File(destPath));
	}
	
	/**
	 * 复制文件
	 * 
	 * @param srcPath
	 *            源文件路径
	 * @param destFile
	 *            目标文件
	 * @throws IOException
	 */
	public static void copyFile(String srcPath, File destFile)
			throws IOException {
		copyFile(new File(srcPath), destFile);
	}
	
	/**
	 * 复制文件
	 * 
	 * @param srcFile
	 *            源文件
	 * @param destPath
	 *            目标文件路径
	 * @throws IOException
	 */
	public static void copyFile(File srcFile, String destPath)
			throws IOException {
		copyFile(srcFile, new File(destPath));
	}
	
	/**
	 * 复制文件
	 * 
	 * @param srcFile
	 *            源文件
	 * @param destFile
	 *            目标文件
	 * @throws IOException
	 */
	public static void copyFile(File srcFile, File destFile) throws IOException {
		if (srcFile == null) throw new NullPointerException(
				"Source must not be null");
		if (destFile == null) throw new NullPointerException(
				"Dest must not be null");
		// 判断源文件是否存在
		if (!srcFile.exists()) throw new IOException("Source " + srcFile
				+ "is not exists");
		// 源文件和目标文件相同
		if (srcFile.getPath().equals(destFile.getPath())) throw new IOException(
				"Source and Dest can't be same");
		if (srcFile.isFile()) {
			if (!destFile.exists()) destFile.getParentFile().mkdirs();
			// file->directory
			if (destFile.isDirectory()) org.apache.commons.io.FileUtils
					.copyFileToDirectory(srcFile, destFile);
			// file->file
			else org.apache.commons.io.FileUtils.copyFile(srcFile, destFile);
		} else {
			if (destFile.getParent().startsWith(srcFile.getPath())) throw new IOException(
					"Can't copy parent directory to children");
			if (!destFile.exists()) destFile.mkdirs();
			// directory->file
			if (destFile.isFile()) throw new IOException(
					"Can't copy directory to file");
			// directory->directory
			else {
				org.apache.commons.io.FileUtils.copyDirectoryToDirectory(
						srcFile, destFile);
			}
		}
	}
	
	/**
	 * 判断是否为路径
	 * 
	 * @param filePath
	 *            文件路径
	 */
	public static boolean isDirectory(String filePath) {
		return new File(filePath).isDirectory();
	}
	
	/**
	 * 判断是否为文件
	 * 
	 * @param filePath
	 *            文件路径
	 */
	public static boolean isFile(String filePath) {
		return new File(filePath).isFile();
	}
	
	/**
	 * 判断一个文件是否存在
	 * 
	 * @param filePath
	 *            文件路径
	 */
	public static boolean exists(String filePath) {
		return new File(filePath).exists();
	}
	
	/**
	 * 读取文件内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 * @throws IOException
	 */
	public static String readFileToString(String filePath) throws IOException {
		return org.apache.commons.io.FileUtils.readFileToString(new File(
				filePath));
	}
	
	/**
	 * 读取文件内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @param encoding
	 *            文件编码
	 * @return
	 * @throws IOException
	 */
	public static String readFileToString(String filePath, String encoding)
			throws IOException {
		return org.apache.commons.io.FileUtils.readFileToString(new File(
				filePath), encoding);
	}
	
	/**
	 * 读取文件内容
	 * 
	 * @param file
	 *            文件
	 * @return
	 * @throws IOException
	 */
	public static String readFileToString(File file) throws IOException {
		return org.apache.commons.io.FileUtils.readFileToString(file);
	}
	
	/**
	 * 读取文件内容
	 * 
	 * @param file
	 *            文件
	 * @param encoding
	 *            文件编码
	 * @return
	 * @throws IOException
	 */
	public static String readFileToString(File file, String encoding)
			throws IOException {
		return org.apache.commons.io.FileUtils.readFileToString(file, encoding);
	}
	
	/**
	 * 从输入流中读取文件
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static String readFileToString(InputStream in) throws IOException {
		if (in == null) return "";
		StringBuffer sb = new StringBuffer();
		byte bytes[] = new byte[1024];
		int result = 0;
		while ((result = in.read(bytes)) != -1) {
			sb.append(new String(bytes, 0, result));
		}
		return sb.toString();
	}
	
	/**
	 * 保存文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @param data
	 *            待保存的内容
	 * @throws IOException
	 */
	public static void writeStringToFile(String filePath, String data)
			throws IOException {
		org.apache.commons.io.FileUtils.writeStringToFile(new File(filePath),
				data);
	}
	
	/**
	 * 保存文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @param data
	 *            待保存的内容
	 * @param encoding
	 *            文件编码
	 * @throws IOException
	 */
	public static void writeStringToFile(String filePath, String data,
			String encoding) throws IOException {
		org.apache.commons.io.FileUtils.writeStringToFile(new File(filePath),
				data, encoding);
	}
	
	/**
	 * 保存文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @param data
	 *            待保存的内容
	 * @param append
	 *            追加
	 * @throws IOException
	 */
	public static void writeStringToFile(String filePath, String data,
			boolean append) throws IOException {
		org.apache.commons.io.FileUtils.writeStringToFile(new File(filePath),
				data, append);
	}
	
	/**
	 * 保存文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @param data
	 *            待保存的内容
	 * @param encoding
	 *            文件编码
	 * @param append
	 *            追加
	 * @throws IOException
	 */
	public static void writeStringToFile(String filePath, String data,
			String encoding, boolean append) throws IOException {
		org.apache.commons.io.FileUtils.writeStringToFile(new File(filePath),
				data, encoding, append);
	}
	
	/**
	 * 保存文件
	 * 
	 * @param file
	 *            文件
	 * @param data
	 *            待保存的内容
	 * @throws IOException
	 */
	public static void writeStringToFile(File file, String data)
			throws IOException {
		org.apache.commons.io.FileUtils.writeStringToFile(file, data);
	}
	
	/**
	 * 保存文件
	 * 
	 * @param file
	 *            文件
	 * @param data
	 *            待保存的内容
	 * @param encoding
	 *            文件编码
	 * @throws IOException
	 */
	public static void writeStringToFile(File file, String data, String encoding)
			throws IOException {
		org.apache.commons.io.FileUtils.writeStringToFile(file, data, encoding);
	}
	
	/**
	 * 保存文件
	 * 
	 * @param file
	 *            文件
	 * @param data
	 *            待保存的内容
	 * @param append
	 *            追加
	 * @throws IOException
	 */
	public static void writeStringToFile(File file, String data, boolean append)
			throws IOException {
		org.apache.commons.io.FileUtils.writeStringToFile(file, data, append);
	}
	
	/**
	 * 保存文件
	 * 
	 * @param file
	 *            文件
	 * @param data
	 *            待保存的内容
	 * @param encoding
	 *            文件编码
	 * @param append
	 *            追加
	 * @throws IOException
	 */
	public static void writeStringToFile(File file, String data,
			String encoding, boolean append) throws IOException {
		org.apache.commons.io.FileUtils.writeStringToFile(file, data, encoding,
				append);
	}
	
	public static void copy(InputStream in, OutputStream out)
			throws IOException {
		copy(in, out, 4096);
	}
	
	public static void copy(InputStream in, OutputStream out, int bufsize)
			throws IOException {
		int len = 0;
		byte[] bytes = new byte[bufsize];
		while (-1 != (len = in.read(bytes))) {
			out.write(bytes, 0, len);
		}
		out.flush();
		out.close();
		in.close();
	}
	
}
