package io.github.sparta.servlets.tags;

/**
 * <p>
 * .
 * </p>
 *
 * @author rapid
 * @version 1.0 2013-09-21 1:00 PM
 * @since JDK 1.5
 */
class Utils {

    public static String BLOCK = "__jsp_override__";

    static String getOverrideVariableName(String name) {
        return BLOCK + name;
    }
}
