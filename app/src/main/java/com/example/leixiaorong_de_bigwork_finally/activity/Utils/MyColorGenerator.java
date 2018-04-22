package com.example.leixiaorong_de_bigwork_finally.activity.Utils;

import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 文件描述
 *
 * @author Even.P
 * @since 2018/4/22 14:54
 */
public class MyColorGenerator {
    public static MyColorGenerator DEFAULT;

    public static MyColorGenerator MATERIAL;

    static {
        DEFAULT = create(Arrays.asList(
                0xffc1adf4,
                0xff9a93fd,
                0xffd2a9e3,
                0xfff2b1dc,
                0xffff9ed1,
                0xfff0add9,
                0xffd6a8e6,
                0xffb7abfd,
                0xffd58aef
        ));
        MATERIAL = create(Arrays.asList(
                0xffe57373,
                0xfff06292,
                0xffba68c8,
                0xff9575cd,
                0xff7986cb,
                0xff64b5f6,
                0xff4fc3f7,
                0xff4dd0e1,
                0xff4db6ac,
                0xff81c784,
                0xffaed581,
                0xffff8a65,
                0xffd4e157,
                0xffffd54f,
                0xffffb74d,
                0xffa1887f,
                0xff90a4ae
        ));
    }

    private final List<Integer> mColors;
    private final Random mRandom;

    public static MyColorGenerator create(List<Integer> colorList) {
        return new MyColorGenerator(colorList);
    }

    private MyColorGenerator(List<Integer> colorList) {
        mColors = colorList;
        mRandom = new Random(System.currentTimeMillis());
    }

    public int getRandomColor() {
        return mColors.get(mRandom.nextInt(mColors.size()));
    }

    public int getColor(Object key) {
        return mColors.get(Math.abs(key.hashCode()) % mColors.size());
    }
}
