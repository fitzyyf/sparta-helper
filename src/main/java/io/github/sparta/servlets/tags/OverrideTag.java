package io.github.sparta.servlets.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * <p>
 * .
 * </p>
 *
 * @author rapid
 * @version 1.0 2013-09-21 1:00 PM
 * @since JDK 1.5
 */
public class OverrideTag extends BodyTagSupport {
    private static final long serialVersionUID = -4238919529721421148L;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int doStartTag() throws JspException {
        return isOverrided() ? SKIP_BODY : EVAL_BODY_BUFFERED;
    }

    @Override
    public int doEndTag() throws JspException {
        if (isOverrided()) {
            return EVAL_PAGE;
        }
        final BodyContent b = getBodyContent();
//		System.out.println("Override.content:"+b.getString());
        String varName = Utils.getOverrideVariableName(name);

        pageContext.getRequest().setAttribute(varName, b.getString());
        return EVAL_PAGE;
    }

    private boolean isOverrided() {
        final String varName = Utils.getOverrideVariableName(name);
        return pageContext.getRequest().getAttribute(varName) != null;
    }
}
