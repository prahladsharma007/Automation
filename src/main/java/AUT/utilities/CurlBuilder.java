package AUT.utilities;

import java.util.Map;

public class CurlBuilder {
    private final StringBuilder curlCommand;

    public CurlBuilder() {
        this.curlCommand = new StringBuilder("curl");
    }

    public CurlBuilder setMethod(String method) {
        if (method != null && !method.isEmpty()) {
            curlCommand.append(" -X ").append(method.toUpperCase());
        }
        return this;
    }

    public CurlBuilder setUrl(String url) {
        if (url != null && !url.isEmpty()) {
            curlCommand.append(" \"").append(url).append("\"");
        }
        return this;
    }

    public CurlBuilder addHeader(String name, String value) {
        if (name != null && value != null && !name.isEmpty() && !value.isEmpty()) {
            curlCommand.append(" -H \"").append(name).append(": ").append(value).append("\"");
        }
        return this;
    }

    public CurlBuilder addHeaders(Map<String, String> headers) {
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(this::addHeader);
        }
        return this;
    }

    public CurlBuilder addQueryParam(String name, String value) {
        if (name != null && value != null && !name.isEmpty() && !value.isEmpty()) {
            curlCommand.append(" -G --data-urlencode \"").append(name).append("=").append(value).append("\"");
        }
        return this;
    }

    public CurlBuilder addQueryParams(Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            params.forEach(this::addQueryParam);
        }
        return this;
    }

    public CurlBuilder addFormParam(String name, String value) {
        if (name != null && value != null && !name.isEmpty() && !value.isEmpty()) {
            curlCommand.append(" --data \"").append(name).append("=").append(value).append("\"");
        }
        return this;
    }

    public CurlBuilder addFormParams(Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            params.forEach(this::addFormParam);
        }
        return this;
    }

    public CurlBuilder setBody(String body) {
        if (body != null && !body.isEmpty()) {
            curlCommand.append(" -d '").append(body.replaceAll("'", "\\\\'")).append("'");
        }
        return this;
    }

    public CurlBuilder setPathParams(Map<String, String> pathParams, String url) {
        if (pathParams != null && !pathParams.isEmpty()) {
            for (Map.Entry<String, String> entry : pathParams.entrySet()) {
                url = url.replace("{" + entry.getKey() + "}", entry.getValue());
            }
        }
        setUrl(url);
        return this;
    }

    public String build() {
        return curlCommand.toString();
    }
}
