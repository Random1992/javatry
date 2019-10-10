package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BarkingProcess {
    protected int hitPoint;

    private static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);

    // ===================================================================================
    //                                                                               Bark
    //                                                                              =====
    public BarkingProcess(){
        hitPoint = getInitialHitPoint();
    }

    protected int getInitialHitPoint() {
        return 10; // as default
    }
    protected void prepareAbdominalMuscle() {
        logger.debug("...Using my abdominal muscle"); // dummy implementation
        downHitPoint();
    }

    protected void breatheIn() {
        logger.debug("...Breathing in"); // dummy implementation
        downHitPoint();
    }

    protected abstract String getBarkWord();

    protected BarkedSound doBark(String barkWord) {
        downHitPoint();
        return new BarkedSound(barkWord);
    }

    protected void downHitPoint() {
        --hitPoint;
        if (hitPoint == 0) {
            throw new IllegalStateException("I'm very tired, so I want to sleep" + getBarkWord());
        }
    }
    public BarkedSound bark() {
        breatheIn();
        prepareAbdominalMuscle();
        String barkWord = getBarkWord();
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    public int getHitPoint() {
        return hitPoint;
    }
}
