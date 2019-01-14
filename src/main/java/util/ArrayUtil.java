package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayUtil {

    //region メソッド

    /**
     * シャッフル
     * @param array
     */
    public static void shuffle(ArrayList<Integer> array) {
        Collections.shuffle(array);
    }


    /**
     * ソート
     * @param array
     */
    public static void sort (ArrayList<Integer> array) {
        for (int i = 0; i < array.size() - 1; i++) {
            bubbleSort(array, i);
        }
    }

    /**
     * バブルソート
     * @param list
     * @param count
     */
    private static void bubbleSort(ArrayList<Integer> list, int count)
    {
        for (int i = 0; i < list.size() - 1 - count; i++)
        {
            int left = list.get(i);
            int right = list.get(i + 1);
            if(right < left) {
                swap(list, i);
            }
        }
    }

    /**
     * 隣接する要素を入れ替える
     * @param list
     * @param index
     */
    private static void swap(ArrayList list, int index)
    {
        Object obj = list.remove(index);
        list.add(index + 1, obj);
    }

    /**
     * 数列構造体をリストに変換
     * @param construct
     * @return
     */
    public static List<Integer> convertToNumbers(String construct) {
        List<Integer> list = new ArrayList<Integer>();
        String[] values = construct.split(",");
        for(String value : values) {
            list.add(Integer.parseInt(value));
        }
        return list;
    }

    /**
     * 数列構造体をリストに変換
     * @param construct
     * @return
     */
    public static List<String> convertToTextBlock(String construct) {
        List<String> list = new ArrayList<String>();
        String[] values = construct.split(",");
        for(String value : values) {
            list.add(value);
        }
        return list;
    }
    //endregion
}
