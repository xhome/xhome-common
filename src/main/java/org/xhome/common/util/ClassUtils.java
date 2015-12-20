package org.xhome.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @project xhome-util
 * @author jhat
 * @email cpf624@126.com
 * @date Feb 9, 201310:37:06 PM
 */
public final class ClassUtils {

	private ClassUtils() {
	}

	public static Set<Class<?>> findClasses(Class<?> parent) {
		String packageName = parent.getPackage().getName();
		return findClasses(packageName, parent);
	}

	public static Set<Class<?>> findClasses(String packageName) {
		return findClasses(packageName, null);
	}

	public static Set<Class<?>> findClasses(String packageName, Class<?> parent) {
		try {
			ClassLoader contextCL = Thread.currentThread()
					.getContextClassLoader();
			ClassLoader loader = contextCL == null ? ClassUtils.class
					.getClassLoader() : contextCL;

			String path = packageName.replaceAll("\\.", "/");
			Enumeration<URL> resources = loader.getResources(path);
			Set<Class<?>> returnClasses = new LinkedHashSet<Class<?>>();
			while (resources.hasMoreElements()) {
				URL resource = resources.nextElement();
				Set<Class<?>> classes = findClasses(resource, packageName,
						parent);
				if (classes != null) {
					returnClasses.addAll(classes);
				}
			}
			return returnClasses;
		} catch (IOException e) {
		}
		return null;
	}

	public static Set<Class<?>> findClasses(URL resource, String packageName) {
		return findClasses(resource, packageName, null);
	}

	public static Set<Class<?>> findClasses(URL resource, String packageName,
			Class<?> parent) {
		String type = resource.getProtocol(), file = resource.getFile();
		Set<Class<?>> classes = null;
		if ("file".equals(type)) {
			File dir = new File(file);
			classes = findClassesFromDirectory(dir, packageName, parent);
		} else if ("jar".equals(type)) {
			int index = file.indexOf("jar!") + 3;
			String pathName = file.substring(index + 2);
			if (pathName.startsWith(packageName.replaceAll("\\.", "/"))) {
				JarFile jar;
				try {
					jar = new JarFile(file.substring(5, index));
					classes = findClassesFromJar(jar, pathName, parent);
				} catch (IOException e) {
				}
			}
		}
		return classes;
	}

	public static Set<Class<?>> findClassesFromJar(JarFile jar, String pathName) {
		return findClassesFromJar(jar, pathName, null);
	}

	public static Set<Class<?>> findClassesFromJar(JarFile jar,
			String pathName, Class<?> parent) {
		Enumeration<JarEntry> entries = jar.entries();
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		while (entries.hasMoreElements()) {
			JarEntry entry = entries.nextElement();
			String name = entry.getName();
			if (name.startsWith(pathName) && name.endsWith(".class")
					&& name.indexOf('$') < 0) {
				String className = name.replaceAll("/", ".");
				try {
					Class<?> clazz = Class.forName(className.substring(0,
							className.length() - 6));
					if (parent == null
							|| (parent.isAssignableFrom(clazz) && !clazz
									.equals(parent))) {
						classes.add(clazz);
					}
				} catch (ClassNotFoundException e) {
				}
			}
		}
		return classes;
	}

	public static Set<Class<?>> findClassesFromDirectory(File dir,
			String packageName) {
		return findClassesFromDirectory(dir, packageName, null);
	}

	public static Set<Class<?>> findClassesFromDirectory(File dir,
			String packageName, Class<?> parent) {
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}
		Set<Class<?>> returnClasses = new LinkedHashSet<Class<?>>();
		packageName = (packageName.equals("") || packageName.endsWith(".")) ? packageName
				: packageName + ".";
		for (File file : files) {
			String fileName = file.getName();
			if (file.isDirectory()) {
				Set<Class<?>> classes = findClassesFromDirectory(file,
						packageName + file.getName(), parent);
				if (classes != null) {
					returnClasses.addAll(classes);
				}
			} else if (fileName.endsWith(".class")) {
				try {
					Class<?> clazz = Class.forName(packageName
							+ fileName.substring(0, fileName.length() - 6));
					if (parent == null
							|| (parent.isAssignableFrom(clazz) && !clazz
									.equals(parent))) {
						returnClasses.add(clazz);
					}
				} catch (ClassNotFoundException e) {
				}
			}
		}
		return returnClasses;
	}

	/**
	 * Load resources by given name resourceName. If no results are found,the
	 * recources name is prepended by '/' and tried again This method will try
	 * to load the recources using the following methods(in order):
	 * <ul>
	 * <li>From Thread.currentThread().getContextClassLoader()
	 * <li>From ClassLoaderUtil.class.getClassLoader()
	 * <li>callingClass.getClassLoader()
	 * </ul>
	 * 
	 * @param resourceName
	 *            The name of the resource to load
	 * @param callingClass
	 *            The Class object of the calling object
	 * @return
	 */
	public static URL getResources(String resourceName, Class<?> callingClass) {
		URL url = Thread.currentThread().getContextClassLoader()
				.getResource(resourceName);
		if (url == null) {
			url = ClassUtils.class.getClassLoader().getResource(resourceName);
		}
		if (url == null) {
			ClassLoader cl = callingClass.getClassLoader();
			if (cl != null)
				url = cl.getResource(resourceName);
		}
		if (url == null
				&& resourceName != null
				&& (resourceName.length() == 0 || resourceName.charAt(0) != '/'))
			return getResources('/' + resourceName, callingClass);
		return url;
	}

	/**
	 * load resources as stream
	 * 
	 * @param resourceName
	 *            The name of the resource to load
	 * @param callingClass
	 *            The Class object of the calling object
	 * @return
	 */
	public static InputStream getResourceAsStream(String resourceName,
			Class<?> callingClass) {
		URL url = getResources(resourceName, callingClass);
		try {
			return url != null ? url.openStream() : null;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Load a class with a given name. It will try to load the class in the
	 * following order:
	 * <ul>
	 * <li>From Thread.currentThread().getContextClassLoader()
	 * <li>Using the basic Class.forName()
	 * <li>From ClassLoaderUtil.class.getClassLoader()
	 * <li>From the callingClass.getClassLoader()
	 * </ul>
	 * 
	 * @param className
	 *            The name of the class to load
	 * @param callingClass
	 *            The Class object of the calling object
	 * @throws ClassNotFoundException
	 *             If the class cannot be found anywhere.
	 */
	public static Class<?> loadClass(String className, Class<?> callingClass)
			throws ClassNotFoundException {
		try {
			ClassLoader contextCL = Thread.currentThread()
					.getContextClassLoader();
			ClassLoader loader = contextCL == null ? ClassUtils.class
					.getClassLoader() : contextCL;
			return loader.loadClass(className);
		} catch (ClassNotFoundException e) {
			try {
				return Class.forName(className);
			} catch (ClassNotFoundException ex) {
				try {
					return ClassUtils.class.getClassLoader().loadClass(
							className);
				} catch (ClassNotFoundException exc) {
					return callingClass.getClassLoader().loadClass(className);
				}
			}
		}
	}

}
