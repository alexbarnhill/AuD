public enum TreeTraversalOrderType {
    /**
     * <b>Pre-order</b> - process the <b>current node first</b>, than the left child and <b>finally the right</b> child
     */
    PRE,
    /**
     * <b>In-order</b> - process the <b>left child first</b>, than the current node and <b>finally the right</b> child
     */
    IN,
    /**
     * <b>Post-order</b> - process the <b>left child first</b>, than the right child and <b>finally the current</b> node
     */
    POST
}