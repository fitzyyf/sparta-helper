/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package io.github.sparta.helpers.emails;

/**
 * <p>
 * 邮件发送文件描述.
 * </p>
 *
 * @author mumu @yfyang
 * @version 1.0 2013-07-18 PM12:49
 * @since JDK 1.5
 */
public class FileMeta {

    /**
     * The File _ path.
     */
    private String file_path;


    /**
     * The File _ alias.
     */
    private String file_alias;

    /**
     * Instantiates a new File meta.
     *
     * @param file_path the file _ path
     * @param file_alias the file _ alias
     */
    public FileMeta(String file_path, String file_alias) {
        this.file_path = file_path;
        this.file_alias = file_alias;
    }

    /**
     * Gets file _ path.
     *
     * @return the file _ path
     */
    public String getFile_path() {
        return file_path;
    }

    /**
     * Sets file _ path.
     *
     * @param file_path the file _ path
     */
    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    /**
     * Gets file _ alias.
     *
     * @return the file _ alias
     */
    public String getFile_alias() {
        return file_alias;
    }

    /**
     * Sets file _ alias.
     *
     * @param file_alias the file _ alias
     */
    public void setFile_alias(String file_alias) {
        this.file_alias = file_alias;
    }
}
