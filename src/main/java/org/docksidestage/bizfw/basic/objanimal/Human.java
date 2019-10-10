package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Human extends Animal implements Language{
    public Human(){}
    private static final Logger logger = LoggerFactory.getLogger(Human.class);

    @Override
    public String getBarkWord(){
        return "ShouRyuuKen!";
    }

    @Override
    public void speak() {
        logger.debug("Blablabla......");
    }
}
