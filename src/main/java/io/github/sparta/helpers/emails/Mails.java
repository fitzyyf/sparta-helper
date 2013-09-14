/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package io.github.sparta.helpers.emails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 邮件发送工具.
 * </p>
 *
 * @author mumu@yfyang
 * @version 1.0 2013-07-18 AM11:02
 * @since JDK 1.5
 */
public class Mails {
    private static final String SMTP_HOST = "mail.smtp.host";
    private static final String SMTP_PORT = "mail.smtp.port";
    private static final String SMTP_AUTH = "mail.smtp.auth";
    private static Logger logger = LoggerFactory.getLogger(Mails.class);
    private final MailAccount mailAccount;

    private Mails(MailAccount mailAccount) {
        this.mailAccount = mailAccount;
    }

    public static Mails mailToAcctount(final MailAccount mailAccount) {
        return new Mails(mailAccount);
    }

    /**
     * 发送邮件给单收件人
     *
     * @param to      收件人
     * @param subject 标题
     * @param content 正文
     * @param isHtml  是否为HTML
     */
    public void sendToSingle(String to
            , String subject, String content
            , List<FileMeta> fileNames
            , boolean isHtml) {
        List<String> list = Lists.newArrayList();
        list.add(to);
        send(list, subject, content, isHtml, fileNames);
    }

    /**
     * 使用默认的设置账户发送邮件
     *
     * @param tos     收件人列表
     * @param subject 标题
     * @param content 正文
     * @param isHtml  是否为HTML
     */
    public void send(Collection<String> tos
            , String subject
            , String content
            , boolean isHtml
            , List<FileMeta> fileNames) {
        try {
            send(mailAccount, tos, fileNames, subject, content, isHtml);
        } catch (MessagingException e) {
            logger.error("Send mail error!", e);
        }
    }

    /**
     * 发送邮件给多人
     *
     * @param mailAccount 邮件认证对象
     * @param tos         收件人列表
     * @param fileNames   文件
     * @param subject     标题
     * @param content     正文
     * @param isHtml      是否为HTML格式
     * @throws javax.mail.MessagingException
     */
    public static void send(final MailAccount mailAccount, Collection<String> tos, List<FileMeta> fileNames,
                            String subject, String content, boolean isHtml)
            throws MessagingException {
        Properties p = new Properties();
        p.put(SMTP_HOST, mailAccount.getHost());
        p.put(SMTP_PORT, mailAccount.getPort());
        p.put(SMTP_AUTH, mailAccount.isAuth());

        //认证登录
        Session session = Session.getDefaultInstance(p,
                mailAccount.isAuth() ?
                        new Authenticator() {
                            @Override
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(mailAccount.getUser(), mailAccount.getPass());
                            }
                        } : null
        );

        final Message mailMessage = new MimeMessage(session);
        mailMessage.setFrom(new InternetAddress(mailAccount.getFrom()));
        mailMessage.setSubject(subject);
        mailMessage.setSentDate(new Date());

        if (isHtml) {
            Multipart mainPart = new MimeMultipart();
            MimeBodyPart contentpart = createContent(content);
            mainPart.addBodyPart(contentpart);

            if (fileNames != null && !fileNames.isEmpty()) {
                for (FileMeta fileMeta : fileNames) {
                    mainPart.addBodyPart(createAttachment(fileMeta));
                }
            }
            mailMessage.setContent(mainPart);
            mailMessage.saveChanges();
        } else {
            mailMessage.setText(content);
        }
        // 群发
        mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Joiner.on(",").join(tos)));
        Transport.send(mailMessage);
        logger.info("Send mail to {} successed.");

    }

    private static MimeBodyPart createContent(String body) throws MessagingException {
        //创建代表组合Mime消息的MimeMultipart对象，将该MimeMultipart对象保存到MimeBodyPart对象
        MimeBodyPart contentPart = new MimeBodyPart();
        MimeMultipart contentMultipart = new MimeMultipart("related");

        //创建用于保存HTML正文的MimeBodyPart对象，并将它保存到MimeMultipart中
        MimeBodyPart htmlbodypart = new MimeBodyPart();
        htmlbodypart.setContent(body, "text/html;charset=UTF-8");
        contentMultipart.addBodyPart(htmlbodypart);


        //将MimeMultipart对象保存到MimeBodyPart对象
        contentPart.setContent(contentMultipart);
        return contentPart;
    }

    private static MimeBodyPart createAttachment(FileMeta fileMeta) throws MessagingException {
        //创建保存附件的MimeBodyPart对象，并加入附件内容和相应的信息
        final MimeBodyPart attachPart = new MimeBodyPart();
        FileDataSource fsd = new FileDataSource(fileMeta.getFile_path());
        attachPart.setDataHandler(new DataHandler(fsd));
        attachPart.setFileName(fileMeta.getFile_alias());
        attachPart.setHeader("content-id", fileMeta.getFile_alias());
        return attachPart;
    }
}
