package com.perkins.oes.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;

import com.perkins.oes.common.BlockAbstract;
import com.perkins.oes.util.SpringUtil;

public class BlockTaglib extends TagSupport {

    private static final long serialVersionUID = -3386562961231042141L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {

        ApplicationContext applicationContext = SpringUtil.getApplicationContext();
        BlockAbstract block = (BlockAbstract) applicationContext.getBean(name);
        String content = block.displayBlock(pageContext);
        JspWriter out = pageContext.getOut();
        try {
            out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    @Override
    public void release() {
        super.release();
    }

}
