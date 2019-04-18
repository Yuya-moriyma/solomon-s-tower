package util;

import java.util.ArrayList;
import java.util.Random;

import java.util.ArrayList;
import java.util.Random;

public class MathUtil {

    //region フィールド

    /**
     * 乱数クラス
     */
    private static Random rdn = new Random();

    //endregion


    //region メソッド

    /**
     * 乱数取得
     * @param range
     * @return
     */
    public static int nextInt(int range) {
        return rdn.nextInt(range);
    }

    /**
     * 乱数配列を作成
     * @param startValue
     * @param range
     * @return
     */
    public static ArrayList getRandomList(int startValue, int range) {
        ArrayList<Integer> list = new ArrayList();
        for(int i = 0; i < range; i++) {
            int count = list.size();
            do {
                int value = rdn.nextInt(range) + startValue;
                if (!list.contains(value)) {
                    list.add(value);
                }
            }
            while(list.size() == count);
        }
        return list;

    }

    /**
     * 配列からランダムに値を一つ取り出す
     * @param list
     * @return
     */
    public static int getRandomValue(ArrayList<Integer> list) {
        return list.get(rdn.nextInt(list.size()));
    }

    //endregion
}
