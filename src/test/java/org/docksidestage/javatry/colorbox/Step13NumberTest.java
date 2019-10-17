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

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Number with color-box. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author taimin
 */
public class Step13NumberTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * How many integer-type values in color-boxes are between 0 and 54? <br>
     * (カラーボックの中に入っているInteger型で、0から54までの値は何個ある？)
     */
    public void test_countZeroToFiftyFour_IntegerOnly() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Long result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof Integer)
                .filter(content -> (Integer) content >= 0 && (Integer) content <= 54)
                .count();
        log(result);
    }

    /**
     * How many number values in color-boxes are between 0 and 54? <br>
     * (カラーボックの中に入っている数値で、0から54までの値は何個ある？)
     */
    public void test_countZeroToFiftyFour_Number() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Long result = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof Number)
                .filter(content -> ((Number) content).doubleValue() >= 0 && ((Number) content).doubleValue() <= 54)
                .count();
        log(result);
    }

    /**
     * What color name is used by color-box that has integer-type content and the biggest width in them? <br>
     * (カラーボックスの中で、Integer型の Content を持っていてBoxSizeの幅が一番大きいカラーボックスの色は？)
     */
    public void test_findColorBigWidthHasInteger() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        ColorBox result = colorBoxList.stream()
                .filter(colorBox -> colorBox.getSpaceList()
                        .stream()
                        .map(boxSpace -> boxSpace.getContent())
                        .anyMatch(content -> content instanceof Integer))
                .max((x, y) -> {
                    int width1 = x.getSize().getWidth();
                    int width2 = y.getSize().getWidth();
                    return width1 < width2 ? -1 : (width1 == width2 ? 0 : 1);
                })
                .get();
        log(result.getColor());
    }

    /**
     * What is total of BigDecimal values in List in color-boxes? <br>
     * (カラーボックスの中に入ってる List の中の BigDecimal を全て足し合わせると？)
     */
    public void test_sumBigDecimalInList() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        double result = colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(spaceList -> spaceList.stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof List)
                .flatMap(content -> ((List) content).stream())
                .filter(value -> value instanceof BigDecimal)
                .mapToDouble(value -> ((BigDecimal) value).doubleValue())
                .sum();
        log(result);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What key is related to value that is max number in Map that has only number in color-boxes? <br>
     * (カラーボックスに入ってる、valueが数値のみの Map の中で一番大きいvalueのkeyは？)
     */
    public void test_findMaxMapNumberValue() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String results = (String) colorBoxList.stream()
                .map(colorBox -> colorBox.getSpaceList())
                .flatMap(spaceList -> spaceList.stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof Map)
                //.map(content -> ((Map) content).entrySet())
                .filter(mapvalues->((Map) mapvalues).values().stream()
                                            .allMatch(mapvalue->mapvalue instanceof Number))
                .flatMap(mapvalues->((Map) mapvalues).entrySet().stream())
                .max((x,y)->{
                    Integer i=(Integer) ((Map.Entry) x).getValue();
                    Integer j=(Integer) ((Map.Entry) y).getValue();
                    return i<j?-1:(i==j?0:1);
                    })
                .map(y->((Map.Entry) y).getKey())
                .get();
        log(results);
    }

    /**
     * What is total of number or number-character values in Map in purple color-box? <br> 
     * (purpleのカラーボックスに入ってる Map の中のvalueの数値・数字の合計は？)
     */
    public void test_sumMapNumberValue() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Object> values=(List<Object>) colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName()=="purple")
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content->content instanceof Map)
                .flatMap(content->((Map) content).values().stream())
                .filter(value->value instanceof Number || value instanceof String)
                .filter(value->value instanceof Number || ((String) value).matches("^[0-9]+$"))
                .collect(Collectors.toList());
        Integer result=0;
        for(Object value:values){
            if(value instanceof String){
                result+=Integer.parseInt((String) value);
            }else{
                result+=(Integer) value;
            }
        }
        log(result);
    }
}
