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

import java.io.File;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, using Stream API you can. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author your_name_here
 */
public class Step12StreamStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * What is color name length of first color-box? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String answer = colorBoxList.stream()
                .findFirst()
                .map(colorBox -> colorBox.getColor().getColorName())
                .map(colorName -> colorName.length() + " (" + colorName + ")")
                .orElse("*not found");
        log(answer);//5 (green)
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String answer = (String) colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(spacelist -> spacelist.stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String)
                .max((i1, i2) -> {
                    int x = ((String) i1).length();
                    int y = ((String) i2).length();
                    return (x < y) ? -1 : (x == y ? 0 : 1);
                })
                .get();
        log(answer);
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Integer> content_len = colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(spacelist -> spacelist.stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String)
                .map(content -> ((String) content).length())
                .collect(Collectors.toList());

        Integer maxlen = content_len.stream().max((i1, i2) -> {
            return (i1 < i1) ? -1 : (i1 == i2 ? 0 : 1);
        }).get();
        Integer minlen = content_len.stream().max((i1, i2) -> {
            return (i1 < i1) ? 1 : (i1 == i2 ? 0 : -1);
        }).get();
        log(maxlen - minlen);
    }

    // has small #adjustmemts from ClassicStringTest
    //  o sort allowed in Stream
    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (sort allowed in Stream)<br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (Streamでのソートありで))
     */
    public void test_length_findSecondMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> strings = colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(spacelist -> spacelist.stream())
                .map(boxSpace -> boxSpace.toString())
                .collect(Collectors.toList());
        strings.remove(strings.stream().max((i1, i2) -> {
            int x = i1.length();
            int y = i2.length();
            return (x < y) ? -1 : (x == y ? 0 : 1);
        }).get());
        String answer = strings.stream().max((i1, i2) -> {
            int x = i1.length();
            int y = i2.length();
            return (x < y) ? -1 : (x == y ? 0 : 1);
        }).get();
        log(answer);
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int sumlen = colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(spacelist -> spacelist.stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String)
                .mapToInt(content -> ((String) content).length())
                .sum();
        log(sumlen);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> names = colorBoxList.stream().map(colorBox -> colorBox.getColor().getColorName()).collect(Collectors.toList());
        int max_len = names.stream().map(name -> name.length()).max((x, y) -> {
            return (x < y) ? -1 : (x == y ? 0 : 1);
        }).get();
        Set results = names.stream().filter(name -> name.length() == max_len).collect(Collectors.toSet());
        log(results);
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
        List<String> results = colorBoxList.stream()
                .filter(colorBox -> colorBox.getSpaceList()
                        .stream()
                        .map(boxSpace -> boxSpace.getContent())
                        .anyMatch(content -> content instanceof String && ((String) content).startsWith("Water")))
                .map(colorBox -> colorBox.getColor().getColorName())
                .collect(Collectors.toList());
        log(results);
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> results = colorBoxList.stream()
                .filter(colorBox -> colorBox.getSpaceList()
                        .stream()
                        .map(boxSpace -> boxSpace.getContent())
                        .anyMatch(content -> content instanceof String && ((String) content).endsWith("front")))
                .map(colorBox -> colorBox.getColor().getColorName())
                .collect(Collectors.toList());
        log(results);
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
        List<Integer> results = colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(SpaceList -> SpaceList.stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof String)
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> ((String) content).endsWith("front"))
                .map(content -> ((String) content).indexOf("front"))
                .collect(Collectors.toList());
        log(results);
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Integer> results = colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(SpaceList -> SpaceList.stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof String)
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> ((String) content).split("ど").length >= 3)
                .map(content -> ((String) content).lastIndexOf("ど"))
                .collect(Collectors.toList());
        log(results);
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
        List<Object> results = colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(SpaceList -> SpaceList.stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof String)
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> ((String) content).endsWith("front"))
                .map(content -> ((String) content).charAt(0))
                .collect(Collectors.toList());
        log(results);
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Object> results = colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(SpaceList -> SpaceList.stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof String)
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> ((String) content).startsWith("Water"))
                .map(content -> ((String) content).charAt(((String) content).length() - 1))
                .collect(Collectors.toList());
        log(results);
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
        List<Object> results = colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(SpaceList -> SpaceList.stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof String)
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> ((String) content).contains("o"))
                .map(content -> ((String) content).replace("o", ""))
                .collect(Collectors.toList());
        log(results);
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Object> results = colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(SpaceList -> SpaceList.stream())
                .filter(boxSpace -> boxSpace.getContent() instanceof File)
                .map(boxSpace -> boxSpace.getContent().toString().replace("/", "\\"))
                .collect(Collectors.toList());
        log(results);
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
        List<YourPrivateRoom.DevilBox> devilboxs = colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(boxSpaces -> boxSpaces.stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof YourPrivateRoom.DevilBox)
                .map(content -> (YourPrivateRoom.DevilBox) content)
                .collect(Collectors.toList());
        devilboxs.stream().forEach(content -> {
            content.wakeUp();
            content.allowMe();
            content.open();
        });
        int result = 0;
        for (YourPrivateRoom.DevilBox content : devilboxs) {
            try {
                result += content.getText().length();
            } catch (YourPrivateRoom.DevilBoxTextNotFoundException e) {
            }
        }
        log(result);
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
        colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(boxSpaces -> boxSpaces.stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof Map)
                .forEach(content -> {
                    String result = map2string((Map) content, " ={");
                    result = "map:" + result.substring(result.indexOf("=") + 1, result.length()).replace("{ ;", "{") + " }";
                    log(result);
                });
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(boxSpaces -> boxSpaces.stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof Map)
                .forEach(content -> {
                    String result = map2string((Map) content, " = map:{").replace("{ ;", "{");
                    result = result.substring(" = ".length(), result.length()) + " }";
                    log(result);
                });
    }

    public String map2string(Map content, String symbol) {
        String result = symbol;
        for (Object key : content.keySet()) {
            Object segment = content.get(key);
            if (segment instanceof Map) {
                result += " ; " + key.toString() + map2string((Map) segment, symbol) + " }";
            } else {
                result += " ; " + key.toString() + " = " + segment.toString();
            }
        }
        return result;
    }
    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    // has small #adjustmemts from ClassicStringTest
    //  o comment out because of too difficult to be stream?
    ///**
    // * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_flat() {
    //}
    //
    ///**
    // * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_nested() {
    //}
}
