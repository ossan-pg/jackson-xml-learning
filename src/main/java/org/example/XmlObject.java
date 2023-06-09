package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.*;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "root")
// @JsonIgnoreProperties は各クラスに付与した方が安全かも
@JsonIgnoreProperties(ignoreUnknown = true)
public class XmlObject {

    // メソッド名を "getStatus()" にすると XmlMapper#writeValueAsString() に
    // 実行されてしまい、結果が想定外のものになってしまうため、別名にする。
    // XmlMapper#writeValueAsString() の内部では、恐らくリフレクションを使用して
    // "get" で始まる＋引数なしの getter を全て実行している。
    public String status() {
        if(this.head == null || this.head.status == null) {
            // 要素がない or 値が未設定の場合は空文字を返す
            return "";
        } else {
            return this.head.status;
        }
    }

    // status() に合わせて "get" なしのメソッド名にした。
    public String item(final String name) {
        // items を Map で実装できればもっとシンプルな処理になるが、
        // XmlMapper によるマッピングが恐らくできない＋
        // 速度的にも問題にならないはずなので List のままにしておく。
        return this.body.items.stream()
                .filter(data -> name.equals(data.name))
                .findFirst()
                .map(Body.Item::getContent)
                .orElse(""); // 要素がない場合は空文字を返す
    }

    ////////////////////////////////////////
    private Head head;

    @Data
    static class Head {
        String status;
        Time time;

        @Data
        static class Time {
            String req;
            String res;
        }
    }

    ////////////////////////////////////////
    private Body body;

    @Data
    static class Body {
        List<Item> items;

        @Data
        static class Item {
            @JacksonXmlProperty(isAttribute = true)
            String name;
            @JacksonXmlText
            String content;

        }
    }

    ////////////////////////////////////////
    private Foot foot;

    @Data
    static class Foot {
        String extra;
    }
}
