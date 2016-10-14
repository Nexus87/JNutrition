package com.jnutrition;

class NodeNotFoundException extends RuntimeException {
    public NodeNotFoundException(String nodeName) {
        super("Could not found node with name " + nodeName);
    }
}
