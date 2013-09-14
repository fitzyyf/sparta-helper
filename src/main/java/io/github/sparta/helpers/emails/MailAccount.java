/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package io.github.sparta.helpers.emails;

import com.google.common.base.Objects;

/**
 * <p>
 * .
 * </p>
 *
 * @author mumu@yfyang
 * @version 1.0 2013-07-18 AM11:03
 * @since JDK 1.5
 */
public class MailAccount {

    private String host;
    private String port;
    private boolean auth;
    private String user;
    private String pass;
    private String from;


    private MailAccount() {
    }

    /**
     * Gets host.
     *
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets host.
     *
     * @param host the host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * Sets port.
     *
     * @param port the port
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * Is auth.
     *
     * @return the boolean
     */
    public boolean isAuth() {
        return auth;
    }

    /**
     * Sets auth.
     *
     * @param isAuth the is auth
     */
    public void setAuth(boolean isAuth) {
        this.auth = isAuth;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets pass.
     *
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * Sets pass.
     *
     * @param pass the pass
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Gets from.
     *
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets from.
     *
     * @param from the from
     */
    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("host", host)
                .add("port", port)
                .add("auth", auth)
                .add("user", user)
                .add("pass", pass)
                .add("from", from)
                .toString();
    }
}
