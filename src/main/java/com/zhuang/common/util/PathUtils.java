package com.zhuang.common.util;

public class PathUtils {

    public static String getPathByPackage(String packageName) {
        return packageName.replaceAll("\\.", "/");
    }

    public static String combine(String... paths) {
        StringBuilder sbResult = new StringBuilder();
        String prePath = null;
        for (int i = 0; i < paths.length; i++) {
            if (paths[i] == null || paths[i].isEmpty()) continue;
            paths[i] = paths[i].replaceAll("\\\\", "/");
            if (i > 0 && prePath != null && !prePath.endsWith("/")) {
                if (!paths[i].startsWith("/")) {
                    paths[i] = "/" + paths[i];
                }
            }
            sbResult.append(paths[i]);
            prePath = paths[i];
        }
        return sbResult.toString();
    }

    public static String getAbsolutePath(String relatePath) {
        if (relatePath == null || relatePath.isEmpty()) return relatePath;
        if (relatePath.startsWith(".")) {
            return PathUtils.combine(PathUtils.class.getResource("/").getPath(), relatePath.substring(relatePath.startsWith("./") ? 2 : 1));
        }
        return relatePath;
    }
}
