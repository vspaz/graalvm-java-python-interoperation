package org.vspaz.python;

public class StringOps{
    public String multiply(String text, int times) {
        return String.valueOf(text).repeat(Math.max(0, times));
    }
}

