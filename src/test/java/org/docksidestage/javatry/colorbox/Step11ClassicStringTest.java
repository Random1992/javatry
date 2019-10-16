/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.colorbox;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

//import org.apache.jasper.compiler.Node;
import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author taiminzhang
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            ColorBox colorBox = colorBoxList.get(0);
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            int answer = colorName.length();
            log(answer + " (" + colorName + ")"); //5(green) also show name for visual check
        } else {
            log("*not found");
        }
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        Integer maxlen = 0;
        String maxLengthString = null;
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> spacelist = colorBox.getSpaceList();
                for (BoxSpace space : spacelist) {
                    Object content = space.getContent();
                    if (content != null) {
                        if (content instanceof String) {
                            // TODO 張 細かい指摘だけど、 キャスト処理（ここだと (String) content) ）は何回もやるのではなくローカル変数にしてしまうといいとおもうよ！ by もってぃ
                            //      例: final String strContent = (String) content);
                            if (((String) content).length() > maxlen) {
                                maxlen = ((String) content).length();
                                maxLengthString = (String) content;
                            }
                        }
                    }
                }
            }
        } else {
            log("*not found");
        }
        System.out.print(maxLengthString);
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        Integer maxlen = 0;
        Integer minlen = 1000;
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                List<BoxSpace> spacelist = colorBox.getSpaceList();
                for (BoxSpace space : spacelist) {
                    Object content = space.getContent();
                    if (content != null) {
                        if (content instanceof String) {
                            Integer len = ((String) content).length();
                            if (len > maxlen) {
                                maxlen = ((String) content).length();
                            }
                            if (len < minlen) {
                                minlen = ((String) content).length();
                            }
                        }
                    }
                }
            }
        } else {
            log("*not found");
        }
        System.out.print(maxlen - minlen);
    }

    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (without sort) <br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (ソートなしで))
     */
    public void test_length_findSecondMax() {
        Integer maxLength = 0;
        // TODO 張 また細かい指摘だけど、変数名はキャメルケースにしてあげよう！特に省略した変数を使っているときは全て小文字だと変数の意味がわかりづらくなってしまうので by もってぃ
        //         例: seclen -> secLen, maxlentext -> maxLenText
        Integer seclen = 0;
        String maxlentext = null;
        String seclentext = null;
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                // TODO 張 これもキャメルケースにしてあげよう！IntelliJでtypoとしてWarningが出ているよ！ by もってぃ
                List<BoxSpace> spacelist = colorBox.getSpaceList();
                for (BoxSpace space : spacelist) {
                    String content = space.toString();
                    Integer length = content.length();
                    if (length > maxLength) {
                        seclen = maxLength;
                        seclentext = maxlentext;
                        maxLength = content.length();
                        maxlentext = content;

                    }
                    if (length < maxLength && length > seclen) {
                        seclen = length;
                        seclentext = content;
                    }
                }
            }
        } else {
            log("*not found");
        }
        System.out.print(seclentext);
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Integer lenSum = 0;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace space : colorBox.getSpaceList()) {
                    if (space.getContent() instanceof String) {
                        lenSum = lenSum + ((String) space.getContent()).length();
                    }
                }
            }
        }
        System.out.print(lenSum);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Set<String> colorName = new HashSet<>();
        Integer maxlen = 0;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                String name = colorBox.getColor().getColorName();
                if (colorName == null) {
                    colorName.add(name);
                    maxlen = name.length();
                }
                if (name.length() > maxlen) {
                    colorName.clear();
                    colorName.add(name);
                    maxlen = name.length();
                }
                if (name.length() == maxlen) {
                    colorName.add(name);
                }
            }
        }
        log(colorName);
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (boxSpace.getContent() instanceof String) {
                        if (((String) boxSpace.getContent()).startsWith("Water")) {
                            System.out.print(colorBox.getColor());
                        }
                    }
                }
            }
        }
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (boxSpace.getContent() instanceof String) {
                        if (((String) boxSpace.getContent()).endsWith("front")) {
                            System.out.print(colorBox.getColor());
                        }
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with first "front" of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列で、最初の "front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (boxSpace.getContent() instanceof String) {
                        if (((String) boxSpace.getContent()).endsWith("front")) {
                            System.out.print(((String) boxSpace.getContent()).indexOf("front") + 1);
                        }
                    }
                }
            }
        }
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (boxSpace.getContent() instanceof String) {
                        String content = ((String) boxSpace.getContent());
                        if (content.contains("ど")) {
                            if (content.split("ど").length >= 3) {
                                System.out.print(content.lastIndexOf("ど") + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (boxSpace.getContent() instanceof String) {
                        if (((String) boxSpace.getContent()).endsWith("front")) {
                            System.out.print(((String) boxSpace.getContent()).charAt(0));
                        }
                    }
                }
            }
        }
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (boxSpace.getContent() instanceof String) {
                        String content = ((String) boxSpace.getContent());
                        if (content.startsWith("Water")) {
                            System.out.print(content.charAt(content.length() - 1));
                        }
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (boxSpace.getContent() instanceof String) {
                        String content = ((String) boxSpace.getContent());
                        if (content.contains("o")) {
                            System.out.print(content.replace("o", ""));
                        }
                    }
                }
            }
        }
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (boxSpace.getContent() != null) {
                        if (boxSpace.getContent() instanceof File) {
                            String path = ((File) boxSpace.getContent()).getPath();
                            System.out.println(path.replace("/", "\\"));
                        }
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Integer lensum = 0;
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (boxSpace.getContent() != null) {
                        if (boxSpace.getContent() instanceof YourPrivateRoom.DevilBox) {
                            YourPrivateRoom.DevilBox content = (YourPrivateRoom.DevilBox) boxSpace.getContent();
                            content.wakeUp();
                            content.allowMe();
                            content.open();
                            try {
                                lensum = lensum + content.getText().length();
                            } catch (YourPrivateRoom.DevilBoxTextNotFoundException e) {
                            }
                        }
                    }
                }
            }
        }
        log(lensum);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (boxSpace.getContent() != null) {
                        if (boxSpace.getContent() instanceof LinkedHashMap) {
                            LinkedHashMap content = (LinkedHashMap) boxSpace.getContent();
                            String result = "map:" + Map2String(content, "={").replace("{;", "{");
                            log(result);
                        }
                    }
                }
            }
        }
    }

    public String Map2String(LinkedHashMap content, String symbol) {
        String result = symbol;
        for (Object key : content.keySet()) {
            Object segment = content.get(key);
            if (segment instanceof LinkedHashMap) {
                result += ";" + key.toString() + Map2String((LinkedHashMap) segment, symbol) + "}";
            } else {
                result += ";" + key.toString() + "=" + segment.toString();
            }
        }
        return result;
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (boxSpace.getContent() != null) {
                        if (boxSpace.getContent() instanceof LinkedHashMap) {
                            LinkedHashMap content = (LinkedHashMap) boxSpace.getContent();
                            String result = Map2String(content, " = map:{").replace("{;", "{");
                            log(result.substring(" = ".length(), result.length()));
                        }
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (boxSpace.getContent() != null) {
                        if (boxSpace.getContent() instanceof YourPrivateRoom.SecretBox) {
                            String content = ((YourPrivateRoom.SecretBox) boxSpace.getContent()).getText();
                            Map result = String2Map(content);
                            log(result);
                        }
                    }
                }
            }
        }
    }

    public Map String2Map(String content) {
        content = content.substring(content.indexOf("{")+1, content.lastIndexOf("}"));//step 1
        String clean_content = shrink_map(content);         //ignore map:{} to find keys at current level
        List<String> keys = find_keys(clean_content);     //use the clean_content to find keys
        List<String> values = find_values(content, keys);  //use the original content to find values
        Map<String, Object> result = new LinkedHashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            if (values.get(i).contains("map")) {
                result.put(keys.get(i).replace(" ",""), String2Map(values.get(i)));
            } else {
                result.put(keys.get(i).replace(" ",""), values.get(i).replace(";","").replace(" ",""));
            }
        }
        return result;
    }

    public String shrink_map(String content) {
        boolean contain_map = content.contains("map:{ ");
        while (contain_map) {
            Integer index_frond = content.indexOf("map:{ ");
            Integer index_end = content.indexOf("}");
            String segment = content.substring(index_frond, index_end+" }".length()-1);
            Integer number_of_lbracket = segment.length() - segment.replace("{", "").length();
            Integer number_of_rbracket = segment.length() - segment.replace("}", "").length();
            for (int i = 0; i <= number_of_lbracket - number_of_rbracket; i++) {
                content = content.substring(0, index_frond) + content.substring(index_end + 1, content.length());
                index_end = content.indexOf("}");
            }
            contain_map = content.contains("map:{ ");
        }
        return content;
    }

    public List<String> find_keys(String content) {
        List<String> keys = new LinkedList<>();
        for (String segment : content.split(";")) {
            keys.add(segment.split("=")[0].replace(" ", ""));
        }
        return keys;
    }

    public List<String> find_values(String content, List<String> keys) {
        List<String> values = new LinkedList<>();
        int i;
        String segment;
        for (i = 0; i < keys.size() - 1; i++) {
            Integer value_front = content.indexOf(keys.get(i));
            Integer value_end = content.indexOf(keys.get(i + 1));
            segment = content.substring(value_front, value_end);
            values.add(segment.substring(segment.indexOf("=")+1,segment.length()));
        }
        Integer last_value_front = content.indexOf(keys.get(i));
        segment=content.substring(last_value_front, content.length());
        values.add(segment.substring(segment.indexOf("=")+1,segment.length()));
        return values;
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            for (ColorBox colorBox : colorBoxList) {
                for (BoxSpace boxSpace : colorBox.getSpaceList()) {
                    if (boxSpace.getContent() != null) {
                        if (boxSpace.getContent() instanceof YourPrivateRoom.SecretBox) {
                            String content = ((YourPrivateRoom.SecretBox) boxSpace.getContent()).getText();
                            Map result = String2Map(content);
                            log(result);
                        }
                    }
                }
            }
        }
    }
}
