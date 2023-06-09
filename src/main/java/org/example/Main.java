package org.example;

public class Main {
    public static void main(String[] args) {
        final String xml = """
                <?xml version="1.0" encoding="UTF-8"?>
                
                <root>
                    <head>
                        <status>AA</status>
                        <time>
                            <req>20230101123456</req>
                            <res>20230101123457</res>
                        </time>
                    </head>
                    <body>
                        <items>
                            <item name="item1">abc</item>
                            <item name="item2">def</item>
                            <item name="item3">xyz</item>
                        </items>
                    </body>
                    <foot>
                        <extra>hoge</extra>
                    </foot>
                </root>
                """;

        final XmlObject o = XmlConverter.toXmlObject(xml);
        System.out.println(o);
        System.out.println("status=[" + o.status() + "]");
        System.out.println("item1=[" + o.item("item1") + "]");
        System.out.println("item2=[" + o.item("item2") + "]");
        System.out.println("item3=[" + o.item("item3") + "]");
        System.out.println("item4=[" + o.item("item4") + "]");

        System.out.println("========================================");

        final String s = XmlConverter.toXmlString(o);
        System.out.println(s);
    }
}