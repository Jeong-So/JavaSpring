package com.example.client.dto;

// generic Exchange
public class Req<T> {  // generic Type으로 받으면 됨

    private Header header;

    private T resBody;

    public static class Header{
        private String responseCode;

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        @Override
        public String toString() {
            return "Header{" +
                    "responseCode='" + responseCode + '\'' +
                    '}';
        }
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getResBody() {
        return resBody;
    }

    public void setResBody(T resBody) {
        this.resBody = resBody;
    }

    @Override
    public String toString() {
        return "Req{" +
                "header=" + header +
                ", body=" + resBody +
                '}';
    }
}

/*
JSON 형태에서 BODY가 바뀔경우
{
    "header" : {
        "response_code" : "OK"
    },
    "body" : {
        "book" : "spring boot",
        "page" : 1024
    }
}

{
    "header" : {
        "response_code" : "OK"
    },
    "body" : {
        "name" : "steve",
        "age" : 10
    }
}

 */
