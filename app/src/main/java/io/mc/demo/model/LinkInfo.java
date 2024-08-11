package io.mc.demo.model;

/**
 * Represents hyperlink info.
*  <p><ul>
*  <li>link text
* <li>url
* <li>index where the link ends
* </ul><p> */
public class LinkInfo {

    private final String text;
    private final String url;
    private final int endIndex;

    private LinkInfo(Builder builder) {
        text = builder.text;
        url = builder.url;
        endIndex = builder.endIndex;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public static final class Builder {
        private String text;
        private String url;
        private int endIndex;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder text(String val) {
            text = val;
            return this;
        }

        public Builder url(String val) {
            url = val;
            return this;
        }

        public Builder endIndex(int val) {
            endIndex = val;
            return this;
        }

        public LinkInfo build() {
            return new LinkInfo(this);
        }
    }
}
