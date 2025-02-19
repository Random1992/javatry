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
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.impl.DoorColorBox;
import org.docksidestage.bizfw.colorbox.space.DoorBoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of Date with color-box. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author taimin
 */
public class Step14DateTest extends PlainTestCase {

    // ===================================================================================
    //                                                                               Basic
    //                                                                               =====
    /**
     * What string is date in color-boxes formatted as slash-separated (e.g. 2019/04/24)? <br>
     * (カラーボックスに入っている日付をスラッシュ区切り (e.g. 2019/04/24) のフォーマットしたら？)
     */
    public void test_formatDate() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> dates = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof LocalDateTime || content instanceof LocalDate)
                .map(content -> content.toString())
                .map(content -> content.split("T")[0].replace("-", "/"))
                .collect(Collectors.toList());
        log(dates);
    }

    /**
     * How is it going to be if the slash-separated date string in yellow color-box is converted to LocaDate and toString() is used? <br>
     * (yellowのカラーボックスに入っているSetの中のスラッシュ区切り (e.g. 2019/04/24) の日付文字列をLocalDateに変換してtoString()したら？)
     */
    public void test_parseDate() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> dates = (List<String>) colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName() == "yellow")
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof Set)
                .flatMap(content -> ((Set) content).stream())
                .filter(str -> ((String) str).matches("^[0-9/]+$"))
                .map(str -> LocalDate.of(Integer.parseInt(((String) str).split("/")[0]), Integer.parseInt(((String) str).split("/")[1]),
                        Integer.parseInt(((String) str).split("/")[2])))
                .collect(Collectors.toList());
        log(dates);
    }

    /**
     * What is total of month numbers of date in color-boxes? <br>
     * (カラーボックスに入っている日付の月を全て足したら？)
     */
    public void test_sumMonth() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Integer result = (Integer) colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof LocalDateTime || content instanceof LocalDate)
                .map(content -> content.toString())
                .map(content -> content.split("T")[0].split("-")[1])
                .mapToInt(str -> Integer.parseInt((String) str))
                .sum();
        log(result);
    }

    /**
     * Add 3 days to second-found date in color-boxes, what day of week is it? <br>
     * (カラーボックスに入っている二番目に見つかる日付に3日進めると何曜日？)
     */
    public void test_plusDays_weekOfDay() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Object> dates = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof LocalDateTime || content instanceof LocalDate)
                .collect(Collectors.toList());
        LocalDate data = LocalDate.of(((LocalDate) dates.get(1)).getYear(), ((LocalDate) dates.get(1)).getMonth().getValue(),
                ((LocalDate) dates.get(1)).getDayOfMonth() + 3);
        log(data.getDayOfWeek());
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * How many days (number of day) are between two dates in yellow color-boxes? <br>
     * (yellowのカラーボックスに入っている二つの日付は何日離れている？)
     */
    public void test_diffDay() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Object> dates = colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName() == "yellow")
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof LocalDateTime || content instanceof LocalDate)
                .collect(Collectors.toList());
        LocalDate t1 = ((LocalDateTime) dates.get(0)).toLocalDate();
        LocalDate t2 = (LocalDate) dates.get(1);
        Long duration;
        duration = ChronoUnit.DAYS.between(t1, t2);
        log(duration);
    }

    /**
     * Find LocalDate in yellow color-box,
     * and add same color-box's LocalDateTime's seconds as number of months to it,
     * and add red color-box's Long number as days to it,
     * and subtract the first decimal place of BigDecimal that has three(3) as integer in List in color-boxes from it,
     * What date is it? <br>
     * (yellowのカラーボックスに入っているLocalDateに、同じカラーボックスに入っているLocalDateTimeの秒数を月数として足して、
     * redのカラーボックスに入っているLong型を日数として足して、カラーボックスに入っているリストの中のBigDecimalの整数値が3の小数点第一位の数を日数として引いた日付は？)
     */
    public void test_birthdate() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<Object> dates = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof LocalDateTime || content instanceof LocalDate)
                .collect(Collectors.toList());

        List<String> plus_days1 = colorBoxList.stream()
                .filter(colorBox -> colorBox.getColor().getColorName() == "red")
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof Long)
                .map(content -> content.toString())
                .collect(Collectors.toList());
        List<String> plus_days2 =(List<String>) colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof List)
                .flatMap(content -> ((List) content).stream())
                .filter(value -> value instanceof BigDecimal)
                // .collect(Collectors.toList());
                .filter(value -> ((BigDecimal) value).intValue() == 3)
                .map(value -> value.toString().charAt(2))
                .collect(Collectors.toList());
        Integer plus_days=Integer.parseInt(plus_days1.get(0))+Integer.parseInt(plus_days1.get(0));
        LocalDate result = (LocalDate) dates.get(1);
        result=result.plusMonths(((LocalDateTime) dates.get(0)).getSecond()).plusDays(plus_days);
        log(result);
    }

    /**
     * What second is LocalTime in color-boxes? <br>
     * (カラ\ーボックスに入っているLocalTimeの秒は？)
     */
    public void test_beReader() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        colorBoxList.stream()
                .filter(colorBox -> colorBox instanceof DoorColorBox)
                .flatMap(colorBox -> ((DoorColorBox) colorBox).getDoorSpaceList().stream())
                .forEach(boxSpace -> boxSpace.openTheDoor());
        List<Object> times = colorBoxList.stream()
                .filter(colorBox -> colorBox instanceof DoorColorBox)
                .flatMap(colorBox -> ((DoorColorBox) colorBox).getDoorSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof LocalTime)
                .collect(Collectors.toList());
        log(((LocalTime) times.get(0)).getSecond());
    }
}
