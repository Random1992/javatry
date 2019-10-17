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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of various type with color-box. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author taimin
 */
public class Step15MiscTypeTest extends PlainTestCase {

    // ===================================================================================
    //                                                                           Exception
    //                                                                           =========
    /**
     * What class name is throw-able object in color-boxes? <br>
     * (カラーボックスに入っているthrowできるオブジェクトのクラス名は？)
     */
    public void test_throwable() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> names=colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content->content instanceof Throwable)
                .map(content->content.getClass().getSimpleName())
                .collect(Collectors.toList());
        log(names);
    }

    /**
     * What message is for exception that is nested by exception in color-boxes? <br>
     * (カラーボックスに入っている例外オブジェクトのネストした例外インスタンスのメッセージは？)
     */
    public void test_nestedException() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content->content instanceof Throwable)
                .forEach(content->log(((Throwable)content).getMessage()));
    }

    // ===================================================================================
    //                                                                           Interface
    //                                                                           =========
    /**
     * What value is returned by justHere() of FavoriteProvider in yellow color-box? <br>
     * (カラーボックスに入っているFavoriteProviderインターフェースのjustHere()メソッドの戻り値は？)
     */
    public void test_interfaceCall() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content->content instanceof YourPrivateRoom.FavoriteProvider)
                .forEach(content->log(((YourPrivateRoom.FavoriteProvider)content).justHere()));
    }

    // ===================================================================================
    //                                                                            Optional
    //                                                                            ========
    /**
     * What keyword is in BoxedStage of BoxedResort in List in beige color-box? (show "none" if no value) <br>
     * (beigeのカラーボックスに入っているListの中のBoxedResortのBoxedStageのkeywordは？(値がなければ固定の"none"という値を))
     */
    public void test_optionalMapping() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName()=="beige")
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace->boxSpace.getContent())
                .filter(content->content instanceof List)
                .flatMap(content->((List) content).stream())
                .filter(boxedresort->((YourPrivateRoom.BoxedResort) boxedresort).getPark().isPresent())
                .map(boxedresort->((YourPrivateRoom.BoxedResort) boxedresort).getPark())
                .map(boxedPark->((YourPrivateRoom.BoxedPark) boxedPark).getStage())
                .forEach(boxStage->{
                    if(boxStage instanceof YourPrivateRoom.BoxedStage){
                        log(((YourPrivateRoom.BoxedStage) boxStage).getKeyword());
                    }else {log("none");}
                                            });
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What line number is makeEighthColorBox() call in getColorBoxList()? <br>
     * (getColorBoxList()メソッドの中のmakeEighthColorBox()メソッドを呼び出している箇所の行数は？)
     */
    public void test_lineNumber() {
    }
}
