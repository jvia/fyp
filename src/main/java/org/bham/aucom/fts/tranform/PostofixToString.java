package org.bham.aucom.fts.tranform;

import net.sf.xcf.fts.nodes.transform.AbstractTransformNode;

public class PostofixToString extends AbstractTransformNode<String, String> {
    private String postfix;

    public PostofixToString(String inPostfix) {
        setPostfix(inPostfix);
    }

    @Override
    protected String transform(String arg0) throws Exception {
        return arg0.concat(getPostfix());
    }

    public void setPostfix(String inNewPostfix) {
        this.postfix = inNewPostfix;
    }

    public String getPostfix() {
        return postfix;
    }

}
