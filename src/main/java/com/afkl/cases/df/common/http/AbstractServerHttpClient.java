package com.afkl.cases.df.common.http;

import com.afkl.cases.df.common.exception.HttpException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by pvaughan on 02/12/2016.
 */
public abstract class AbstractServerHttpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractServerHttpClient.class.getName());

    protected String throwException(RequestBuilder requestBuilder, HttpResponse response, String responseContent) throws HttpException {
        throw new HttpException(
                getExceptionMessage(requestBuilder, response),
                responseContent
        );
    }

    protected String throwException(RequestBuilder requestBuilder, HttpResponse response, Exception e) throws HttpException {
        throw new HttpException(
                getExceptionMessage(requestBuilder, response),
                e
        );
    }

    protected String getExceptionMessage(RequestBuilder requestBuilder, HttpResponse response) {
        StringBuilder builder = new StringBuilder("Exception appeared while executing HTTP request: \n ");
        builder.append("\t").append("URL: [").append(requestBuilder.getUri()).append("]\n");
        builder.append("\t").append("method: [").append(requestBuilder.getMethod()).append("]\n");
        if (requestBuilder.getEntity() != null) {
            try {
                builder.append("\t").append("entity: [").append(EntityUtils.toString(requestBuilder.getEntity())).append("]\n");
            } catch (IOException e) {
                LOGGER.error("Exception appeared while printing entity:", e);
            }
        }
        builder.append("\t").append("parameters: [");
        for (final NameValuePair nameValuePair : requestBuilder.getParameters()) {
            builder.append(nameValuePair.getName()).append(" = ").append(nameValuePair.getValue()).append("; ");
        }
        builder.append("]\n");
        if (response != null) {
            builder.append("Response code: [").append(response.getStatusLine().getStatusCode()).append("]\n");
        }

        return builder.toString();
    }

    public String doRequest(RequestBuilder requestBuilder) throws HttpException {
        throw new RuntimeException("doRequest not implemented");
    }
}

