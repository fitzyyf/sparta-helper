/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package io.github.sparta.helpers.date;

import java.util.Date;

/**
 * <p>
 * .
 * </p>
 *
 * @author mumu@yfyang
 * @version 1.0 2013-06-08 下午12:50
 * @since JDK 1.5
 */
public interface DateProvider {

    Date getDate();

    public static final DateProvider DEFAULT = new CurrentDateProvider();

    public static class CurrentDateProvider implements DateProvider {

        @Override
        public Date getDate() {
            return new Date();
        }
    }

    public static class ConfigurableDateProvider implements DateProvider {

        private final Date date;

        public ConfigurableDateProvider(Date date) {
            this.date = date;
        }

        @Override
        public Date getDate() {
            return date;
        }
    }
}
